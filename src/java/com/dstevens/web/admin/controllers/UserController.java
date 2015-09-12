package com.dstevens.web.admin.controllers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;

import com.dstevens.config.controllers.BadRequestException;
import com.dstevens.config.controllers.ResourceNotFoundException;
import com.dstevens.user.UserCreator;
import com.dstevens.user.DisplayableUser;
import com.dstevens.user.Role;
import com.dstevens.user.User;
import com.dstevens.user.UserRepository;
import com.dstevens.user.patronage.Patronage;
import com.dstevens.user.patronage.PatronageRepository;
import com.google.gson.Gson;

@Controller
public class UserController {

    private final UserRepository userRepository;
    private final PatronageRepository patronageRepository;
	private final UserCreator accountCreator;

    @Autowired
    public UserController(UserRepository userRepository, PatronageRepository patronageRepository, UserCreator accountCreator) {
        this.userRepository = userRepository;
        this.patronageRepository = patronageRepository;
		this.accountCreator = accountCreator;
    }
    
    @RequestMapping(value ="/admin/users/upload", method = RequestMethod.POST)
    @ResponseStatus(value=HttpStatus.CREATED)
    public @ResponseBody String importUsers(@RequestParam("users") MultipartFile multipartFile) {
        if (!multipartFile.isEmpty()) {
        	Path path = createTempFile();
            try (BufferedReader reader = readFileInto(multipartFile, path)) {
            	List<String> lines = reader.lines().collect(Collectors.toList());
            	for (String line : lines) {
					String[] pieces = line.split(",");
					String email = pieces[0];
					Integer patronageYear = Integer.parseInt(pieces[1]);
					Date expirationDate = getExpirationDateFrom(pieces);
					User newUser = accountCreator.createUser(email, "password", "", "", "", "");
					userRepository.save(newUser.withPatronage(new Patronage(patronageYear, expirationDate, "")));
				}
            	return "You successfully uploaded " + lines.size() + " users";
            } catch (IOException e) {
				throw new IllegalStateException("Could not read lines from multipart file", e);
			} finally {
				silentlyDelete(path);
			}
        } else {
            return "You failed to upload users because the file was empty.";
        }
    }

	private Date getExpirationDateFrom(String[] pieces) {
		try {
			return new SimpleDateFormat("yyyy-MM-dd").parse(pieces[2]);
		} catch (ParseException e) {
			throw new IllegalStateException("Failed to import user " + pieces[0] + ": Expiration date " + pieces[2] + " was improperly formatted");
		}
	}

	private void silentlyDelete(Path path) {
		try {
			Files.delete(path);
		} catch (IOException e) {
			System.err.println("Failed to cleanup temp file because " + e);
			e.printStackTrace(System.err);
		}
	}

	private Path createTempFile() {
		try {
			return Files.createTempFile("tempFile", ".csv");
		} catch (IOException e) {
			throw new IllegalStateException("Faile to create tempFile.csv", e);
		}
	}

	private BufferedReader readFileInto(MultipartFile multipartFile, Path path) {
		try {
			Files.copy(multipartFile.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
			return new BufferedReader(new FileReader(path.toFile()));
		} catch (IOException e) {
			throw new IllegalStateException("Failed to read multipart file", e);
		}
	}
	
    @RequestMapping(value = "/admin/users", method = RequestMethod.POST)
    @ResponseStatus(value=HttpStatus.CREATED)
    public @ResponseBody String createUser(@RequestBody DisplayableUser displayableUser, HttpServletResponse response) {
        Patronage patronage = patronageRepository.findPatronageByMembershipId(displayableUser.membershipId);
        if(patronage != null && patronage.getUser() != null) {
            throw new IllegalArgumentException("Patronage " + displayableUser.membershipId + " already has a user.");
        }
        User user = userRepository.save(displayableUser.to());
        if(patronage != null) {
            user = user.withPatronage(patronage);
            patronage = patronage.forUser(user);
            user = userRepository.save(user);
        }
        addLocationHeader(response, user);
        return new Gson().toJson(DisplayableUser.fromOn(user, new Date()));
    }
    
    @RequestMapping(value = "/admin/users/{id}", method = RequestMethod.PUT)
    public @ResponseBody String updateUser(@PathVariable Integer id, @RequestBody DisplayableUser displayableUser, HttpServletResponse response) {
        User user = userRepository.findUser(id);
        if(user == null) {
            throw new ResourceNotFoundException("No user " + id + " found");
        }
        User userToUpdateTo = displayableUser.to();
        
        Patronage patronage = patronageRepository.findPatronageByMembershipId(displayableUser.membershipId);
        if(patronage == null) {
            if(user.getPatronage() != null) {
                throw new BadRequestException("User " + user.getId() + " is associated with patronage " + user.getPatronage().displayMembershipId());
            }
        } else {
            if(patronage.getUser() != null && user.getPatronage() != null) {
                if(patronage.getUser().getId() == user.getId() &&
                   patronage.getId() == user.getPatronage().getId()) {
                   user = user.withPatronage(patronage);
               } else {
                   throw new BadRequestException("User " + user.getId() + " is associated with patronage " + user.getPatronage().displayMembershipId());
               }
            } else if(patronage.getUser() == null && user.getPatronage() == null) {
                user = user.withPatronage(patronage);
            } else {
                throw new BadRequestException("Could not associate user " + user.getId() + " cannot be associated with patronage " + patronage);
            }
        }
        
        if(userToUpdateTo.getRoles() != null) {
            user = user.withRoles(userToUpdateTo.getRoles());
        }
        if(userToUpdateTo.getFirstName() != null) {
            user = user.withFirstName(userToUpdateTo.getFirstName());
        }
        if(userToUpdateTo.getLastName() != null) {
            user = user.withLastName(userToUpdateTo.getLastName());
        }
        
        User updatedUser = userRepository.save(user);
        addLocationHeader(response, updatedUser);
        return new Gson().toJson(DisplayableUser.fromOn(updatedUser, new Date()));

    }

    private void addLocationHeader(HttpServletResponse response, User user) {
        response.addHeader("LOCATION", "/admin/users/" + user.getId());
    }
    
    @RequestMapping(value = "/admin/users", method = RequestMethod.GET)
    public @ResponseBody String getUsers() {
        List<DisplayableUser> collect = StreamSupport.stream(userRepository.findAllUndeleted().spliterator(), false).
                                                      map((User u) -> DisplayableUser.fromOn(u, new Date())).
                                                      sorted().
                                                      collect(Collectors.toList());
        return new Gson().toJson(collect);
    }
    
    @RequestMapping(value = "/admin/users/{id}", method = RequestMethod.GET)
    public @ResponseBody String getUser(@PathVariable Integer id) {
        return new Gson().toJson(DisplayableUser.fromOn(userRepository.findUser(id), new Date()));
    }
    
    @RequestMapping(value = "/admin/users/clients", method = RequestMethod.GET)
    public @ResponseBody String getClients() {
        List<DisplayableUser> collect = StreamSupport.stream(userRepository.findClients().spliterator(), false).
                map((User u) -> DisplayableUser.fromOn(u, new Date())).
                sorted().
                collect(Collectors.toList());
        return new Gson().toJson(collect);
    }
    
    @RequestMapping(value = "/admin/users/patrons", method = RequestMethod.GET)
    public @ResponseBody String getPatrons() {
        List<DisplayableUser> collect = StreamSupport.stream(userRepository.findPatrons().spliterator(), false).
                map((User u) -> DisplayableUser.fromOn(u, new Date())).
                sorted().
                collect(Collectors.toList());
        return new Gson().toJson(collect);
    }
    
    @RequestMapping(value = "/admin/users/patrons/active", method = RequestMethod.GET)
    public @ResponseBody String getActivePatrons() {
        List<DisplayableUser> collect = StreamSupport.stream(userRepository.findPatrons().spliterator(), false).
                map((User u) -> DisplayableUser.fromOn(u, new Date())).
                filter((DisplayableUser t) -> t.activePatron).
                sorted().
                collect(Collectors.toList());
        return new Gson().toJson(collect);
    }
    
    @RequestMapping(value = "/admin/users/patrons/active/{year}/{month}", method = RequestMethod.GET)
    public @ResponseBody String getActivePatronsOn(@PathVariable int year, @PathVariable int month) {
        List<DisplayableUser> collect = StreamSupport.stream(userRepository.findPatrons().spliterator(), false).
                map((User u) -> DisplayableUser.fromOn(u, dateOf(year, month))).
                filter((DisplayableUser t) -> t.activePatron).
                sorted().
                collect(Collectors.toList());
        return new Gson().toJson(collect);
    }
    
    @RequestMapping(value = "/admin/users/patrons/expired", method = RequestMethod.GET)
    public @ResponseBody String getInactivePatrons() {
        List<DisplayableUser> collect = StreamSupport.stream(userRepository.findAllUndeleted().spliterator(), false).
                map((User u) -> DisplayableUser.fromOn(u, new Date())).
                filter((DisplayableUser t) -> t.membershipId != null && !t.activePatron).
                sorted().
                collect(Collectors.toList());
        return new Gson().toJson(collect);
    }
    
    @RequestMapping(value = "/admin/users/patrons/expired/{year}/{month}", method = RequestMethod.GET)
    public @ResponseBody String getInactivePatronsOn(@PathVariable int year, @PathVariable int month) {
        List<DisplayableUser> collect = StreamSupport.stream(userRepository.findPatrons().spliterator(), false).
                map((User u) -> DisplayableUser.fromOn(u, dateOf(year, month))).
                filter((DisplayableUser t) -> !t.activePatron).
                sorted().
                collect(Collectors.toList());
        return new Gson().toJson(collect);
    }

    private Date dateOf(int year, int month) {
        return Date.from(LocalDate.of(year, month, 1).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }
    
    @RequestMapping(value = "/admin/users/role/{roleId}", method = RequestMethod.GET)
    public @ResponseBody String getUsersWithRole(@PathVariable int roleId) {
        List<DisplayableUser> collect = StreamSupport.stream(userRepository.findAllUndeleted().spliterator(), false).
                filter((User t) -> t.getRoles().contains(Role.values()[roleId])).
                map((User u) -> DisplayableUser.fromOn(u, new Date())).
                sorted().
                collect(Collectors.toList());
        return new Gson().toJson(collect);
    }
}

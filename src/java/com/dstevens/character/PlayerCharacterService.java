package com.dstevens.character;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dstevens.character.status.PlayerCharacterStatus;
import com.dstevens.character.status.PlayerStatusChange;
import com.dstevens.suppliers.ClockSupplier;
import com.dstevens.troupe.Troupe;
import com.dstevens.troupe.TroupeRepository;
import com.dstevens.user.PlayerCharacterOwnership;
import com.dstevens.user.User;
import com.dstevens.user.UserRepository;

@Service
public class PlayerCharacterService {

	private final PlayerCharacterRepository repository;
	private final PlayerCharacterFactory factory;
	private final UserRepository userRepository;
	private final TroupeRepository troupeRepository;
	private final ClockSupplier clockSupplier;

	@Autowired
	public PlayerCharacterService(PlayerCharacterRepository repository, PlayerCharacterFactory factory,
			                      UserRepository userRepository, TroupeRepository troupeRepository,
			                      ClockSupplier clockSupplier) {
		this.repository = repository;
		this.factory = factory;
		this.userRepository = userRepository;
		this.troupeRepository = troupeRepository;
		this.clockSupplier = clockSupplier;
	}
	
	public PlayerCharacter createCharacter(User user, Troupe troupe, Setting setting, String characterName) {
		PlayerCharacter character = repository.update(factory.createPlayerCharacter(characterName, setting).beginCreation());
		userRepository.save(user.withCharacter(new PlayerCharacterOwnership(user, character, troupe.getVenue(), secondaryCharacterStatus())));
		troupeRepository.save(troupe.withCharacter(character));
		return character;
	}

	private PlayerStatusChange secondaryCharacterStatus() {
		return new PlayerStatusChange(PlayerCharacterStatus.SECONDARY, Date.from(clockSupplier.get().instant()));
	}
	
	public PlayerCharacter getCharacter(Integer id) {
		return repository.findWithId(id);
	}

	public void save(PlayerCharacter character) {
		repository.update(character);
	}
	
}

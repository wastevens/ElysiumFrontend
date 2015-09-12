package com.dstevens.web.utilities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class MultipartFileReader {

	public List<String> readLinesFrom(MultipartFile file) {
        if (!file.isEmpty()) {
        	Path path = createTempFile();
            try (BufferedReader reader = readFileInto(file, path)) {
            	return reader.lines().collect(Collectors.toList());
            } catch (IOException e) {
				throw new IllegalStateException("Could not read lines from multipart file", e);
			} finally {
				silentlyDelete(path);
			}
        } else {
        	throw new IllegalArgumentException("Failed to upload users because the file was empty.");
        }
	}
	
	private Path createTempFile() {
		try {
			return Files.createTempFile("tempFile", ".import");
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
	
	private void silentlyDelete(Path path) {
		try {
			Files.delete(path);
		} catch (IOException e) {
			System.err.println("Failed to cleanup temp file because " + e);
			e.printStackTrace(System.err);
		}
	}
}

package ml.paulobatista.simitrieve.filter;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class Cleaner {
	
	public List<String> removeBlanckLine(List<String> sourceCode) {
		List<String> filtered = new ArrayList<>();
		
		for(String line : sourceCode) {
			if(!line.trim().isEmpty() || line.matches("\\s+")) {
				filtered.add(line);
			}
		}
		return filtered;
	}
	public List<String> removeNonWordCharacters(List<String> sourceCode) {
		List<String> sourceCodeWithoutNonWordCharacters = new ArrayList<>();
		
		for(String line : sourceCode) {
			sourceCodeWithoutNonWordCharacters.addAll(removeNonWordCharacters(line));
		}
		
		return sourceCodeWithoutNonWordCharacters;
	}
	
	public List<String> removeNonWordCharacters(String text) {
		List<String> withoutNonWOrdCharacter = new ArrayList<>();
		
		String[] splittedText = text.split("[\\W]");
		
		for(String word : splittedText) {
			withoutNonWOrdCharacter.add(word);
		}
		
		return withoutNonWOrdCharacter;
	}
	
	public List<String> removeDigits(List<String> sourceCode) {
		List<String> filtered = new ArrayList<>();
		
		for(String line : sourceCode) {
			if(!StringUtils.isNumeric(line)) {
				filtered.add(line);
			}
		}
		return filtered;
	}
}

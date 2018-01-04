package ml.paulobatista.simitrieve.tokenizer;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import ml.paulobatista.simitrieve.filter.Cleaner;

public class Tokenizer {
	
	
	public List<String> tokenizeString(String text) {
		StringTokenizer tokenizer = new StringTokenizer(text, " ");
		int size = tokenizer.countTokens();
		
		List<String> tokens = new ArrayList<String>();
		
		for(int index = 0; index < size; index++ ) {
			tokens.add(tokenizer.nextToken());
		}
		
		Cleaner cleaner = new Cleaner();
		
		tokens = cleaner.removeNonWordCharacters(tokens);
		
		return tokens;
	}
	
	
	public List<String> tokenize(List<String> sourceCode) {
		List<String> tokenized = new ArrayList<String>();
		
		for(String line : sourceCode) {
			
			tokenized.addAll(tokenizeString(line));
		}
		
		Cleaner cleaner = new Cleaner();
		
		tokenized = cleaner.removeBlanckLine(tokenized);
		tokenized = cleaner.removeDigits(tokenized);
		return tokenized;
	}
	
	//public List<String> tokenize(List<String> sourceCode, camelcase)
	
}

package ml.paulobatista.simitrieve.tokenizer;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import ml.paulobatista.simitrieve.filter.CamelCaseTool;
import ml.paulobatista.simitrieve.filter.Cleaner;

public class Tokenizer {
	
	
	public List<String> tokenizeString(String text) {
		StringTokenizer tokenizer = new StringTokenizer(text, " ");
		int size = tokenizer.countTokens();
		
		List<String> tokens = new ArrayList<>();
		
		for(int index = 0; index < size; index++ ) {
			tokens.add(tokenizer.nextToken());
		}
		
		Cleaner cleaner = new Cleaner();
		
		tokens = cleaner.removeNonWordCharacters(tokens);
		
		return tokens;
	}
	
	private List<String> cleanWordList(List<String> wordList) {
		List<String> cleanWordList = new ArrayList<>();
		Cleaner cleaner = new Cleaner();
		
		cleanWordList = cleaner.removeBlanckLine(wordList);
		cleanWordList = cleaner.removeDigits(cleanWordList);
		cleanWordList = cleaner.removeJunkWords(cleanWordList);
		
		return cleanWordList;
	}
	
	public List<String> tokenize(List<String> sourceCode) {
		List<String> tokenized = new ArrayList<>();
		
		for(String line : sourceCode) {
			
			tokenized.addAll(tokenizeString(line));
		}
		
		tokenized = cleanWordList(tokenized);
		
		return tokenized;
		
	}
	
	//public List<String> tokenize(List<String> sourceCode, camelcase)
	
}

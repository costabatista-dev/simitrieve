package ml.paulobatista.simitrieve.tokenizer;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import ml.paulobatista.simitrieve.filter.CamelCaseTool;
import ml.paulobatista.simitrieve.filter.Cleaner;
import ml.paulobatista.simitrieve.filter.CommentRemover;
import ml.paulobatista.simitrieve.filter.Dictionary;
import ml.paulobatista.simitrieve.filter.WordStemmer;
import ml.paulobatista.simitrieve.process.Process;

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
	
	
	private List<String> toLowerCaseWordList(List<String> wordList) {
		List<String> lowerList = new ArrayList<>();
		
		for(String word : wordList) {
			lowerList.add(word.toLowerCase());
		}
		
		return lowerList;
	}
	
	public List<String> tokenize(List<String> sourceCode, Process process) {
		List<String> tokenized = new ArrayList<>();
		
		if(!process.getComment().getOption()) {
			CommentRemover commentRemover = new CommentRemover();
			
			sourceCode = commentRemover.removeComments(sourceCode, process.getProgrammingLanguage());
		}
		
		tokenized = tokenize(sourceCode);
		
		Dictionary dictionary = new Dictionary(process.getProgrammingLanguage());
		
		
		tokenized = dictionary.removeStopwords(tokenized);
		
		if(process.getCamelCase().getOption()) {
			CamelCaseTool camelCaseTool = new CamelCaseTool();
			tokenized = camelCaseTool.splitWordList(tokenized);
			
			Cleaner cleaner = new Cleaner();
			
			tokenized = cleaner.removeDigits(tokenized);
			tokenized = cleaner.removeJunkWords(tokenized);
		}
		
		if(process.getStem().getOption()) {
			WordStemmer wordStemmer = new WordStemmer();
			
			tokenized = wordStemmer.wordListStemming(tokenized);
			
			Cleaner cleaner = new Cleaner();
			
			tokenized = cleaner.removeJunkWords(tokenized);
			
		}
		
		
		tokenized = toLowerCaseWordList(tokenized);
		
		return tokenized;
	}
	

	
}

/**
 * 
 */
package ml.paulobatista.simitrieve.filter;

import java.util.ArrayList;
import java.util.List;

import com.uttesh.exude.stemming.Stemmer;

/**
 * @author Paulo Batista
 *
 */
public class WordStemmer {

	public String wordStemming(String word) {
		Stemmer stemmer = new Stemmer();
		String stemmed = stemmer.stem(word);
		
		return stemmed;
	}
	
	public List<String> wordListStemming(List<String> wordList) {
		List<String> stemmedList = new ArrayList<>();
		
		for(String word :  wordList) {
			stemmedList.add(wordStemming(word));
		}
		
		return stemmedList;
	}
}

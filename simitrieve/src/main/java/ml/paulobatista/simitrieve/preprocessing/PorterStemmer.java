/**
 * 
 */
package ml.paulobatista.simitrieve.preprocessing;

import java.util.List;

import com.uttesh.exude.stemming.Stemmer;

/**
 * @author paulo
 *
 */
public class PorterStemmer {
	
	public String stemTerm(String term) {
		Stemmer stemmer = new Stemmer();
		return stemmer.stem(term);
	}
	
	public List<String> stemTermList(List<String>  termList) {
		for(String s : termList) {
			s = this.stemTerm(s);
		}
		
		return termList;
	}
	
}

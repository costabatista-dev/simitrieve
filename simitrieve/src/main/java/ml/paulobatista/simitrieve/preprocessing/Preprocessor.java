/**
 * 
 */
package ml.paulobatista.simitrieve.preprocessing;

import java.util.LinkedHashMap;

import ml.paulobatista.simitrieve.entity.ProgrammingFile;
import ml.paulobatista.simitrieve.entity.Project;

/**
 * @author paulo
 *
 */
public class Preprocessor {
	// this class will implement the logic of preprocessing methods.

	public Project removeComments(Project project) {
		throw new UnsupportedOperationException("Not implemented yet.");
	}

	public void removeStopwords(Project project) {
		StopwordsRemover stpwRemover = new StopwordsRemover();
		stpwRemover.removeStopwords(project);
	}

	public void removeSpecialCharacters(Project project) {
		String content;
		for (ProgrammingFile pf : project) {
			content = pf.getSourceCode().replaceAll("[\\W]", " ");
			pf.setSourceCode(content);
		}
	}

	public void removeNonConceptWords(Project project) {
		String content;

		for (ProgrammingFile pf : project) {
			content = pf.getSourceCode();
			content = content.replaceAll("\\s\\w\\w?\\s", " ");
			pf.setSourceCode(content);
		}

	}

	private String[] tokenizeSourceCode(ProgrammingFile programmingFile) {
		String[] tokenized = programmingFile.getSourceCode().split("[\\s\\n]+");
		return tokenized;
	}

	private LinkedHashMap<String, Integer> getQuantifiedTerms(String[] tokenizedSourceCode) {
		LinkedHashMap<String, Integer> quantified = new LinkedHashMap<>();
		Integer frequency;
		for (String term : tokenizedSourceCode) {
			if (quantified.containsKey(term)) {
				frequency = quantified.get(term) + 1;
				quantified.put(term, frequency);
			} else {

				frequency = 1;
				quantified.put(term, frequency);

			}
		}

		return quantified;
	}

	public void tokenize(Project project) {
		LinkedHashMap<String, Integer> hash;
		for (ProgrammingFile pf : project) {
			String[] tokenized = this.tokenizeSourceCode(pf);
			
			hash = this.getQuantifiedTerms(tokenized);
			String firstKey = (String) hash.keySet().toArray()[0];
			hash.remove(firstKey);
			pf.setQuantifiedTerms(hash);
		}
	}
}

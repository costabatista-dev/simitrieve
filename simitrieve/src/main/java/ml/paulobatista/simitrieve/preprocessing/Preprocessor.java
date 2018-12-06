/**
 * 
 */
package ml.paulobatista.simitrieve.preprocessing;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import ml.paulobatista.simitrieve.entity.ProgrammingFile;
import ml.paulobatista.simitrieve.entity.Project;
import ml.paulobatista.simitrieve.entity.Quartile;

/**
 * @author paulo
 *
 */
public class Preprocessor {

	public void removeComments(Project project) {
		CommentRemover commentRemover = new CommentRemover();

		commentRemover.removeComments(project);
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
			content = content.replaceAll("\\s\\w\\w?\\s", "");
			pf.setSourceCode(content);
		}

	}

	private String stemmingFileContent(ProgrammingFile programmingFile) {
		PorterStemmer stemmer = new PorterStemmer();
		StringBuilder builder = new StringBuilder();
		String[] splitted = programmingFile.getSourceCode().split("\\s+");

		for (String s : splitted) {
			builder.append(stemmer.stemTerm(s));
			builder.append(" ");
		}
		return builder.toString();
	}

	public void stemming(Project project) {
		project.forEach(programmingFile -> {
			programmingFile.setSourceCode(this.stemmingFileContent(programmingFile));

		});
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

	private LinkedHashMap<String, Integer> sortQuantifiedTerms(LinkedHashMap<String, Integer> quantifiedTerms) {
		LinkedHashMap<String, Integer> result = quantifiedTerms.entrySet().stream()
				.sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).collect(Collectors.toMap(
						Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> oldValue, LinkedHashMap::new));
		return result;
	}

	public void tokenize(Project project) {
		LinkedHashMap<String, Integer> hash;
		for (ProgrammingFile pf : project) {
			String[] tokenized = this.tokenizeSourceCode(pf);

			hash = this.getQuantifiedTerms(tokenized);
			try {
				String firstKey = (String) hash.keySet().toArray()[0];
				hash.remove(firstKey);

			} catch (ArrayIndexOutOfBoundsException except) {
				System.out.println(except.getMessage() + ": it's an empty file.");
			}

			hash = this.sortQuantifiedTerms(hash);
			pf.setQuantifiedTerms(hash);
		}
	}

	private void removeLessFrequencyTerms(ProgrammingFile programmingFile, float percent) {
		LinkedHashMap<String, Integer> hash = programmingFile.getQuantifiedTerms();
		int removeNumber = Math.round(hash.size() * percent);

		List<String> keyList = new ArrayList<>();
		hash.keySet().forEach(key -> keyList.add(key));
		List<String> subkeyList = keyList.subList(keyList.size() - removeNumber, keyList.size());
		subkeyList.forEach(key -> hash.remove(key));

		programmingFile.setQuantifiedTerms(hash);
	}

	public void removeLessFrequencyTerms(Project project, float percent) {
		project.forEach(pf -> this.removeLessFrequencyTerms(pf, percent));
	}

	public void removeLessFrequencyTerms(Project project, Quartile quart) {
		this.removeLessFrequencyTerms(project, quart.getPercent());
	}

	public void splitCamelCase(Project project) {
		CamelCaseSplitter camelCaseSplitter = new CamelCaseSplitter();
		camelCaseSplitter.splitCamelCase(project);
	}

}

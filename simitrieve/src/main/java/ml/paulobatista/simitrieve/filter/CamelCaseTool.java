/**
 * 
 */
package ml.paulobatista.simitrieve.filter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Paulo Batista
 *
 */
public class CamelCaseTool {

	public String[] splitWord(String word) {
		String[] split;
		split = word.split("(?<=[a-z])(?=[A-Z])|(?<=[A-Z])(?=[A-Z][a-z])	");

		return split;
	}

	public List<String> splitWordList(List<String> wordList) {
		List<String> splitList = new ArrayList<>();

		for (String word : wordList) {
			String[] splitWord = splitWord(word);

			for (String wordpart : splitWord) {
				splitList.add(wordpart);
			}
		}
		
		return splitList;
	}

}

/**
 * 
 */
package ml.paulobatista.simitrieve.similarity.normalization;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ml.paulobatista.simitrieve.entity.BagOfWords;
import ml.paulobatista.simitrieve.entity.TokenList;

/**
 * @author Paulo Batista
 *
 */
public class TFIDFManager {

	public double[][] getTF(BagOfWords bagOfWords) {
		List<String> classes = bagOfWords.getClasses();
		List<String> words = bagOfWords.getWords();

		double[][] values = new double[words.size()][classes.size()];

		for (int column = 0; column < classes.size(); column++) {
			String className = classes.get(column);
			double[] lineValues = getTF(bagOfWords, className);

			for (int line = 0; line < words.size(); line++) {
				values[line][column] = lineValues[line];
			}
		}

		return values;

	}

	public Map<String, Double> getIDF(BagOfWords bagOfWords, List<TokenList> allTokenLists) {
		List<String> words = bagOfWords.getWords();
		List<String> classes = bagOfWords.getClasses();

		int numberOfDocuments = classes.size();

		Map<String, Double> idfValues = new HashMap<>();

		for (String word : words) {
			int documentRate = getRateDocument(allTokenLists, word);
			double idf = (double) Math.log((double) numberOfDocuments / (double) documentRate);
			Double idfValue = new Double(idf);

			idfValues.put(word, idfValue);
		}

		return idfValues;

	}

	private int getRateDocument(List<TokenList> allTokenLists, String word) {
		int documentRate = 0;

		for (TokenList tokenList : allTokenLists) {
			if (tokenList.contains(word)) {
				documentRate++;
			}
		}

		// error threat. if documentRate is equals zero.

		return documentRate;
	}

	private double[] getTF(BagOfWords bagOfWords, String className) {
		double totalTermsOfClass = getTotalTermsOfClass(bagOfWords, className);
		List<String> classes = bagOfWords.getClasses();
		int indexOfClass = classes.indexOf(className);

		double values[] = getColumnFromArray(bagOfWords.getValues(), indexOfClass);

		for (int index = 0; index < values.length; index++) {
			values[index] = (double) (values[index] / totalTermsOfClass);
		}

		return values;
	}

	private double getTotalTermsOfClass(BagOfWords bagOfWords, String className) {
		List<String> classes = bagOfWords.getClasses();

		int indexOfClass = classes.indexOf(className);

		double[][] values = bagOfWords.getValues();

		double[] terms = getColumnFromArray(values, indexOfClass);

		double totalTermsOfClass = getSum(terms);

		return totalTermsOfClass;
	}

	private double getSum(double[] array) {
		double sum = 0.0;

		for (int index = 0; index < array.length; index++) {
			sum += array[index];
		}

		return sum;
	}

	private double[] getColumnFromArray(double[][] array, int columnIndex) {
		double[] values = new double[array.length];

		for (int line = 0; line < array.length; line++) {
			values[line] = array[line][columnIndex];
		}

		return values;
	}
}

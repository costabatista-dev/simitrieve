/**
 * 
 */
package ml.paulobatista.simitrieve.similarity.normalization;

import java.util.List;

import ml.paulobatista.simitrieve.entity.BagOfWords;

/**
 * @author Paulo Batista
 *
 */
public class TFIDFManager {
	
	public double[][] getTF(BagOfWords bagOfWords) {
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
		
		for(int index = 0; index < array.length; index++) {
			sum += array[index];
		}
		
		return sum;
	}
	
	private double[] getColumnFromArray(double[][] array, int columnIndex) {
		double[] values = new double[array.length];
		
		for(int line = 0; line < array.length; line++) {
			values[line] = array[line][columnIndex];
		}
		
		return values;
	}
}

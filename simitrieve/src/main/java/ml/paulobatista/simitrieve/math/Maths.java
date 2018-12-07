/**
 * 
 */
package ml.paulobatista.simitrieve.math;

/**
 * @author costa
 *
 */
public class Maths {

	public static double truncate(double value) {
		double truncated = Math.round(value * 10000) / 10000d;

		return truncated;
	}
	

	public static double cosineSimilarity(double[] leftArray, double[] rightArray) {
		double product = 0.0;

		double normalizedLefArray = 0.0, normalizedRightArray = 0.0;

		int size = leftArray.length;

		for (int index = 0; index < size; index++) {
			product += (leftArray[index] * rightArray[index]);

			normalizedLefArray += Math.pow(leftArray[index], 2);
			normalizedRightArray += Math.pow(rightArray[index], 2);
		}

		double divisor = (Math.sqrt(normalizedLefArray) * Math.sqrt(normalizedRightArray));

		double cosine = (product / divisor);

		return truncate(cosine);
	}

}

/**
 * 
 */
package ml.paulobatista.simitrieve.similarity.normalization;

import Jama.Matrix;
import Jama.SingularValueDecomposition;
import ml.paulobatista.simitrieve.entity.BagOfWords;

/**
 * @author Paulo Batista
 *
 */
public class LSIManager {

	public double[][] getLSI(BagOfWords bagOfWords) {
		double[][] values = bagOfWords.getValues();
		
		Matrix matrix =  doubleArrayToMatrix(values);
		Matrix lsiMatrix = getLSI(matrix);
		
		double[][] lsiValues = lsiMatrix.getArray();
		
		return lsiValues;
	}
	
	
	
	public Matrix getLSI(Matrix values) {
		SingularValueDecomposition svd = new SingularValueDecomposition(values);
		
		Matrix wordVector = svd.getU();
		Matrix sigma = svd.getS();
		Matrix documentVector = svd.getV();
		
		int k = (int) Math.floor(Math.sqrt(values.getColumnDimension()));
		
		Matrix reducedWordVector = wordVector.getMatrix(0, wordVector.getRowDimension() - 1, 0, k - 1);
		
		Matrix reducedSigma = sigma.getMatrix(0, k - 1, 0, k - 1);
		
		Matrix reducedDocumentVector = documentVector.getMatrix(0, documentVector.getRowDimension() - 1, 0, k - 1);
		
		Matrix weights = reducedWordVector.times(reducedSigma).times(reducedDocumentVector.transpose());
		
		for(int column = 0; column < weights.getColumnDimension(); column++) {
			double sumCol = sumColumn(weights.getMatrix(0, weights.getRowDimension() - 1, column, column));
			
			for(int line = 0; line < weights.getRowDimension(); line++) {
				weights.set(line, column, Math.abs((weights.get(line, column)) / sumCol));
			}
		}
		
		return weights;
		
	}
	
	private Matrix doubleArrayToMatrix(double[][] values) {
		Matrix matrix = new Matrix(values);

		return matrix;
	}
	
	
	private double sumColumn(Matrix colMatrix) {
		double sum = 0.0;
		
		for(int index = 0; index <  colMatrix.getRowDimension(); index++) {
			sum += colMatrix.get(index, 0);
		}
		
		return sum;
	}
	
	public double[] getColumnFromArray(double[][] array, int columnIndex) {
		double[] values = new double[array.length];

		for (int line = 0; line < array.length; line++) {
			values[line] = array[line][columnIndex];
		}

		return values;
	}
}

/**
 * 
 */
package ml.paulobatista.simitrieve.math;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;

import Jama.Matrix;
import Jama.SingularValueDecomposition;
import ml.paulobatista.simitrieve.entity.project.ProgrammingFile;
import ml.paulobatista.simitrieve.entity.project.Project;

/**
 * @author costa
 *
 */
public class TermWeight {

	private double[] prepareArrayToTermWeighting(ProgrammingFile programmingFile, List<String> baseKeys) {
		LinkedHashMap<String, Integer> tokenized = programmingFile.getQuantifiedTerms();

		double[] tw = new double[baseKeys.size()];
		int[] i = new int[] { 0 };
		i[0] = 0;

		baseKeys.forEach(key -> {
			if (tokenized.containsKey(key)) {
				tw[i[0]] = tokenized.get(key);

			} else {
				tw[i[0]] = 0.0;
			}
			i[0]++;
		});
		return tw;
	}

	private LinkedHashMap<String, Double> prepareHashToTFIDFWeighting(ProgrammingFile programmingFile,
			LinkedHashMap<String, Double> idf) {
		LinkedHashMap<String, Double> tfidf = new LinkedHashMap<>();
		programmingFile.getQuantifiedTerms().entrySet().forEach(entry -> {
			tfidf.put(entry.getKey(), this.getTermFrequency(programmingFile, entry.getKey()) * idf.get(entry.getKey()));
		});

		return tfidf;
	}

	private double[] prepareArrayToTFIDFWeighting(LinkedHashMap<String, Double> tfidf, List<String> baseKeys) {
		double[] values = new double[baseKeys.size()];
		int[] i = new int[] { 0 };

		baseKeys.forEach(key -> {
			if (tfidf.containsKey(key))
				values[i[0]] = tfidf.get(key);
			else {
				values[i[0]] = 0.0;
			}
			i[0]++;
		});

		return values;
	}

	private List<String> getBaseKeys(ProgrammingFile fpf, ProgrammingFile spf) {
		List<String> baseKeys = new ArrayList<>();

		fpf.getQuantifiedTerms().keySet().forEach(key -> {
			baseKeys.add(key);
		});

		spf.getQuantifiedTerms().keySet().forEach(key -> {
			if (!baseKeys.contains(key))
				baseKeys.add(key);
		});

		return baseKeys;

	}

	public LinkedHashMap<ProgrammingFile, LinkedHashMap<ProgrammingFile, Double>> calculateTermFrequencySimilarity(
			Project project) {
		LinkedHashMap<ProgrammingFile, LinkedHashMap<ProgrammingFile, Double>> similarities = new LinkedHashMap<>();
		List<String> baseKeys = new ArrayList<>();
		LinkedHashMap<ProgrammingFile, Double> hash2 = new LinkedHashMap<>();

		for (ProgrammingFile pf1 : project) {
			hash2.clear();
			for (ProgrammingFile pf2 : project) {

				if (pf1 != pf2) {
					baseKeys.addAll(this.getBaseKeys(pf1, pf2));
					double[] fArray = this.prepareArrayToTermWeighting(pf1, baseKeys);
					double[] sArray = this.prepareArrayToTermWeighting(pf2, baseKeys);
					double similarity = Maths.cosineSimilarity(fArray, sArray);
					hash2.put(pf2, similarity);
					baseKeys.clear();
				}
			}
			similarities.put(pf1, hash2);
		}

		return similarities;
	}

	public LinkedHashMap<ProgrammingFile, LinkedHashMap<ProgrammingFile, Double>> calculateTFIDFSimilarity(
			Project project) {
		LinkedHashMap<String, Double> idf = this.getInverseDocumentFrequencies(project);
		LinkedHashMap<ProgrammingFile, LinkedHashMap<ProgrammingFile, Double>> similarities = new LinkedHashMap<>();
		LinkedHashMap<ProgrammingFile, Double> spair = new LinkedHashMap<>();
		List<String> baseKeys = new ArrayList<>();

		project.forEach(pf1 -> {
			spair.clear();
			project.forEach(pf2 -> {

				if (pf1 != pf2) {
					baseKeys.addAll(this.getBaseKeys(pf1, pf2));
					LinkedHashMap<String, Double> hash1 = this.prepareHashToTFIDFWeighting(pf1, idf);
					LinkedHashMap<String, Double> hash2 = this.prepareHashToTFIDFWeighting(pf2, idf);

					double[] fArray = this.prepareArrayToTFIDFWeighting(hash1, baseKeys);
					double[] sArray = this.prepareArrayToTFIDFWeighting(hash2, baseKeys);
					double similarity = Maths.cosineSimilarity(fArray, sArray);

					spair.put(pf2, similarity);

					baseKeys.clear();
				}
			});
			similarities.put(pf1, spair);
		});

		return similarities;
	}

	public LinkedHashMap<ProgrammingFile, LinkedHashMap<ProgrammingFile, Double>> calculateLSISimilarity(
			Project project) {

		LinkedHashMap<ProgrammingFile, double[]> lsiFrequencies = this.getLSIFrequencies(project);
		LinkedHashMap<ProgrammingFile, Double> hash2 = new LinkedHashMap<>();
		LinkedHashMap<ProgrammingFile, LinkedHashMap<ProgrammingFile, Double>> similarities = new LinkedHashMap<>();
		
		project.forEach(pf1 -> {
			hash2.clear();
			project.forEach(pf2 -> {
				if (pf1 != pf2) {			
					hash2.put(pf2, Maths.cosineSimilarity(lsiFrequencies.get(pf1), lsiFrequencies.get(pf2)));
				}
			});
			similarities.put(pf1, hash2);
		});
		
		return similarities;
	}

	private double getTermFrequency(ProgrammingFile programmingFile, String term) {
		LinkedHashMap<String, Integer> hash = programmingFile.getQuantifiedTerms();
		double total = 0.0;

		for (Entry<String, Integer> element : hash.entrySet()) {
			total += element.getValue();
		}

		double times = hash.get(term);

		return (times / total);

	}

	private LinkedHashMap<String, Double> getInverseDocumentFrequencies(Project project) {
		double total = (double) project.size();
		List<String> terms = this.getProjectTerms(project);
		LinkedHashMap<String, Double> inverseDocumentFrequencies = new LinkedHashMap<>();

		terms.forEach(t -> {
			double idf = Math.log((total / this.getDocumentAppearences(project, t)));
			inverseDocumentFrequencies.put(t, idf);
		});

		return inverseDocumentFrequencies;
	}

	private double getDocumentAppearences(Project project, String term) {
		double appearences = 0.0;

		for (ProgrammingFile pf : project) {
			if (pf.getQuantifiedTerms().containsKey(term))
				appearences++;
		}

		return appearences;
	}

	private List<String> getProjectTerms(Project project) {
		List<String> terms = new ArrayList<>();

		for (ProgrammingFile pf : project) {
			for (Entry<String, Integer> entry : pf.getQuantifiedTerms().entrySet()) {
				if (!terms.contains(entry.getKey()))
					terms.add(entry.getKey());
			}
		}

		return terms;
	}

	private LinkedHashMap<ProgrammingFile, double[]> getLSIFrequencies(Project project) {
		List<String> projectTerms = this.getProjectTerms(project);
		double[][] lsiFrequencies = new double[projectTerms.size()][project.size()];
		int i = 0;
	
		for (ProgrammingFile pf : project) {
			double[] fileFrequencies = this.prepareArrayToTermWeighting(pf, projectTerms);
			for(int j = 0; j < fileFrequencies.length; j++) {
				lsiFrequencies[j][i] = fileFrequencies[j];
			}
			
			i++;
		}

		
		Matrix matrix = doubleArrayToMatrix(lsiFrequencies);
		Matrix lsiMatrix = getLSIMatrix(matrix);

		lsiFrequencies = lsiMatrix.getArray();

		LinkedHashMap<ProgrammingFile, double[]> lsiHash = new LinkedHashMap<>();

		i = 0;
		
		for (ProgrammingFile pf : project) {
			lsiHash.put(pf, this.getColumnFromArray(lsiFrequencies, i));
			i++;
		}

		return lsiHash;
	}

	private Matrix getLSIMatrix(Matrix values) {
		SingularValueDecomposition svd = new SingularValueDecomposition(values);

		Matrix wordVector = svd.getU();
		Matrix sigma = svd.getS();
		Matrix documentVector = svd.getV();

		int k = (int) Math.floor(Math.sqrt(values.getColumnDimension()));

		Matrix reducedWordVector = wordVector.getMatrix(0, wordVector.getRowDimension() - 1, 0, k - 1);

		Matrix reducedSigma = sigma.getMatrix(0, k - 1, 0, k - 1);

		Matrix reducedDocumentVector = documentVector.getMatrix(0, documentVector.getColumnDimension() - 1, 0, k - 1);

		Matrix weights = reducedWordVector.times(reducedSigma).times(reducedDocumentVector.transpose());

		for (int column = 0; column < weights.getColumnDimension(); column++) {
			double sumCol = sumColumn(weights.getMatrix(0, weights.getRowDimension() - 1, column, column));

			for (int line = 0; line < weights.getRowDimension(); line++) {
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

		for (int index = 0; index < colMatrix.getRowDimension(); index++) {
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

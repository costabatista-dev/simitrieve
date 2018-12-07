/**
 * 
 */
package ml.paulobatista.simitrieve.math;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import ml.paulobatista.simitrieve.entity.ProgrammingFile;
import ml.paulobatista.simitrieve.entity.Project;

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

	public LinkedHashMap<ProgrammingFile, LinkedHashMap<ProgrammingFile, Double>> calculateTermWeightSimilarity(
			Project project) {
		LinkedHashMap<ProgrammingFile, LinkedHashMap<ProgrammingFile, Double>> similarities = new LinkedHashMap<>();
		List<String> baseKeys = new ArrayList<>();
		LinkedHashMap<ProgrammingFile, Double> hash2 = new LinkedHashMap<>();
		
		for (ProgrammingFile pf1 : project) {
			hash2 = new LinkedHashMap<>();
			for (ProgrammingFile pf2 : project) {
				
				if (pf1 != pf2) {
					baseKeys.addAll(this.getBaseKeys(pf1, pf2));
					double[] fArray = this.prepareArrayToTermWeighting(pf1, baseKeys);
					double[] sArray = this.prepareArrayToTermWeighting(pf2, baseKeys);
					double similarity = Similarity.cosineSimilarity(fArray, sArray);
					hash2.put(pf2, similarity);

				}
			}
			similarities.put(pf1, hash2);
		}

		return similarities;
	}

}

/**
 * 
 */
package ml.paulobatista.simitrieve;

import java.util.LinkedHashMap;

import ml.paulobatista.simitrieve.args.ArgumentManager;
import ml.paulobatista.simitrieve.entity.ProgrammingFile;
import ml.paulobatista.simitrieve.entity.Project;
import ml.paulobatista.simitrieve.math.TermWeight;

/**
 * @author paulo
 *
 */
public class App {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArgumentManager am = new ArgumentManager();
		Project project = am.execute(args);

		LinkedHashMap<ProgrammingFile, LinkedHashMap<ProgrammingFile, Double>> map = new TermWeight()
				.calculateTFIDFSimilarity(project);

		map.entrySet().forEach(item -> {
			LinkedHashMap<ProgrammingFile, Double> submap = item.getValue();
			submap.entrySet().forEach(item2 -> {
				System.out.println(
						item.getKey().getName() + " x " + item2.getKey().getName() + ": " + item2.getValue());
			});
		});
	}

}

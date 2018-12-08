/**
 * 
 */
package ml.paulobatista.simitrieve;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import ml.paulobatista.simitrieve.args.ArgumentManager;
import ml.paulobatista.simitrieve.csv.CSVManager;
import ml.paulobatista.simitrieve.entity.csv.CSVData;
import ml.paulobatista.simitrieve.entity.project.ProgrammingFile;
import ml.paulobatista.simitrieve.entity.project.Project;
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

		LinkedHashMap<ProgrammingFile, LinkedHashMap<ProgrammingFile, Double>> similarityHash = new TermWeight()
				.calculateLSISimilarity(project);

		CSVData csvData = new CSVData(new String[] {"First Path", "Second Path", "First File", "Second File", "Similarity"});
		List<String[]> content = new ArrayList<>();
		
		similarityHash.entrySet().forEach(file1 -> {
			LinkedHashMap<ProgrammingFile, Double> secondHash = file1.getValue();
			secondHash.entrySet().forEach(file2 -> {
				
				String fPath = file1.getKey().getPath();
				String fName = file1.getKey().getName();
				String sPath = file2.getKey().getPath();
				String sName = file2.getKey().getName();
				String similarity = file2.getValue().toString();
				content.add(new String[] {fPath, sPath, fName, sName, similarity});
				
			});
		});
		
		csvData.setContent(content);
		csvData.setDataName(am.getOutputFileName());
		
		CSVManager csvManager = new CSVManager(csvData);
		csvManager.writeCSV();
	}

}

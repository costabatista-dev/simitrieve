/**
 * 
 */
package ml.paulobatista.simitrieve.similarity;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import ml.paulobatista.simitrieve.args.ArgumentManager;
import ml.paulobatista.simitrieve.csv.CSVManager;
import ml.paulobatista.simitrieve.entity.csv.CSVData;
import ml.paulobatista.simitrieve.entity.project.ProgrammingFile;
import ml.paulobatista.simitrieve.entity.project.Project;
import ml.paulobatista.simitrieve.entity.project.TermWeightFactor;

/**
 * @author costa
 *
 */
public class ConceptualCouplingManager {
	private String termWeightFactor;
	private String outputFileName;

	public ConceptualCouplingManager() {

	}

	public ConceptualCouplingManager(String termWeightFactor) {
		// TODO Auto-generated constructor stub
		this.termWeightFactor = termWeightFactor;
	}

	public ConceptualCouplingManager(ArgumentManager am) {
		// TODO Auto-generated constructor stub
		this.termWeightFactor = am.getTermWeighting();
		this.outputFileName = am.getOutputFileName();
	}

	public void setTermWeightFactor(String termWeightFactor) {
		this.termWeightFactor = termWeightFactor;
	}

	public String getTermWeightFactor() {
		return this.termWeightFactor;
	}

	public void setOutputFileName(String outputFileName) {
		this.outputFileName = outputFileName;
	}

	public String getOutputFileName() {
		return this.outputFileName;
	}

	private void writeConceptualCouplingCSVOutput(
			LinkedHashMap<ProgrammingFile, LinkedHashMap<ProgrammingFile, Double>> similarityHash) {
		CSVData csvData = new CSVData(
				new String[] { "First Path", "Second Path", "First File", "Second File", "Similarity" });
		List<String[]> content = new ArrayList<>();

		similarityHash.entrySet().forEach(file1 -> {
			LinkedHashMap<ProgrammingFile, Double> secondHash = file1.getValue();
			secondHash.entrySet().forEach(file2 -> {

				String fPath = file1.getKey().getPath();
				String fName = file1.getKey().getName();
				String sPath = file2.getKey().getPath();
				String sName = file2.getKey().getName();
				String similarity = file2.getValue().toString();
				content.add(new String[] { fPath, sPath, fName, sName, similarity });

			});
		});

		csvData.setContent(content);
		csvData.setDataName(this.outputFileName);

		CSVManager csvManager = new CSVManager(csvData);
		csvManager.writeCSV();
	}

	public void executeConceptualCouplingObtainProcess(ArgumentManager am, Project project) {
		this.termWeightFactor = am.getTermWeighting();
		this.outputFileName = am.getOutputFileName();
		this.executeConceptualCouplingObtainProcess(project);
	}

	public void executeConceptualCouplingObtainProcess(Project project) {
		LinkedHashMap<ProgrammingFile, LinkedHashMap<ProgrammingFile, Double>> similarityHash = null;

		if (this.termWeightFactor.equals(TermWeightFactor.TF.getFactor())) {
			similarityHash = new TermWeight().calculateTermFrequencySimilarity(project);
		} else if (this.termWeightFactor.equals(TermWeightFactor.TFIDF.getFactor())) {
			similarityHash = new TermWeight().calculateTFIDFSimilarity(project);
		} else if (this.termWeightFactor.equals(TermWeightFactor.LSI.getFactor())) {
			similarityHash = new TermWeight().calculateLSISimilarity(project);
		} else {
			throw new UnsupportedOperationException("Term Weight Factor Not Identified Error.");
		}

		this.writeConceptualCouplingCSVOutput(similarityHash);
	}

}

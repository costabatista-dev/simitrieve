/**
 * 
 */
package ml.paulobatista.simitrieve.csv;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVWriter;

import ml.paulobatista.simitrieve.entity.CosineSimilarity;
import ml.paulobatista.simitrieve.entity.Project;
import ml.paulobatista.simitrieve.entity.process.CamelCase;
import ml.paulobatista.simitrieve.entity.process.Comment;
import ml.paulobatista.simitrieve.entity.process.Normalization;
import ml.paulobatista.simitrieve.entity.process.Quantile;
import ml.paulobatista.simitrieve.entity.process.Stem;
import ml.paulobatista.simitrieve.error.ErrorHandler;
import ml.paulobatista.simitrieve.process.Process;
import ml.paulobatista.simitrieve.scan.feature.FeatureScanner;

/**
 * @author costa
 *
 */
public class CSVManager {

	public void writeProjectFeaturesCSV(Project project) {
		FeatureScanner fScanner = new FeatureScanner();

		int numberOfClasses = fScanner.getNumberOfClasses(project);
		int numberOfPackages = fScanner.getNumberOfPackages(project);
		int numberOfLinesOfCode = fScanner.getNumberOfLinesOfCode(project);
		String programmingLanguageName = fScanner.getProgrammingLanguageName(project);
		String version = fScanner.getProjectVersion(project);
		String projectName = fScanner.getProjectName(project);

		String[] header = new String[] { "Feature", "Value" };

		List<String[]> info = new ArrayList<>();

		info.add(new String[] { "Name", projectName });
		info.add(new String[] { "Version", version });
		info.add(new String[] { "Language", programmingLanguageName });
		info.add(new String[] { "NOP", Integer.toString(numberOfPackages) });
		info.add(new String[] { "NOC", Integer.toString(numberOfClasses) });
		info.add(new String[] { "LOC", Integer.toString(numberOfLinesOfCode) });

		String outputDirectoryName = fScanner.getProjectName(project) + "_stats" + File.separator + "features";

		createOutputDiretctory(outputDirectoryName);

		String outputName = fScanner.getProjectName(project) + "_features.csv";

		writeCSV(outputDirectoryName, outputName, header, info);
	}

	public void writeProjectSimilarityCSV(List<CosineSimilarity> similarities, Project project, Process process) {
		
		String outputDirectoryName = project.getName() + "_stats" + File.separator + "similarities";
		
		String outputName = project.getName() + "_" + project.getVersion() + getCommentTag(process.getComment()) +
				getCamelCaseTag(process.getCamelCase()) + getStemTag(process.getStem()) + getQuantileTag(process.getQuantile()) +
				getNormalizationTag(process.getNormalization()) + ".csv";
		
		String[] header = new String[] {"First Package","Second Package", "First Class", "Second Class", "Similarity"};
		List<String[]> info = new ArrayList<>();
		for(CosineSimilarity similarity : similarities) {
			String[] currentInfo = new String[] {similarity.getFirstPackage(), similarity.getSecondPackage(),
					similarity.getFirstClass(), similarity.getSecondClass(), Double.toString(similarity.getSimilarity())};
			
			info.add(currentInfo);
		}
		
		createOutputDiretctory(outputDirectoryName);
		
		writeCSV(outputDirectoryName, outputName, header, info);
	}

	private String getCommentTag(Comment comment) {

		if (comment.equals(Comment.YES)) {
			return "Yco";
		}
		return "Nco";
	}

	private String getStemTag(Stem stem) {
		if (stem.equals(Stem.YES)) {
			return "Ys";
		}

		return "Ns";
	}

	private String getCamelCaseTag(CamelCase camelCase) {
		if(camelCase.equals(CamelCase.YES)) {
			return "Yca";
		}
		
		return "Nca";
	}
	
	private String getQuantileTag(Quantile quantile) {
		if(quantile.equals(Quantile.FIRST)) {
			return "Qfi";
		}
		
		else if(quantile.equals(Quantile.SECOND)) {
			return "Qse";
		}
		
		else if(quantile.equals(Quantile.THIRD)) {
			return "Qth";
		}
		
		else if(quantile.equals(Quantile.FOURTH)) {
			return "Qfo";
		}
		
		else {
			//error threat.
			return null;
		}
	}
	
	private String getNormalizationTag(Normalization normalization) {
		if(normalization.equals(Normalization.NAIVE)) {
			return "Naive";
		}
		else if(normalization.equals(Normalization.TFIDF)) {
			return "Tfidf";
		}
		else if(normalization.equals(Normalization.LSI)) {
			return "Lsi";
		}
		else {
			//error threat.
			return null;
		}
	}

	private void writeCSV(String outputDirectory, String outputFile, String[] header, List<String[]> info) {
		String csv = outputDirectory + File.separator + outputFile;

		try {
			CSVWriter csvWriter = new CSVWriter(new FileWriter(csv));
			csvWriter.writeNext(header);
			csvWriter.writeAll(info);
			csvWriter.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private boolean createOutputDiretctory(String directory) {
		File file = new File(directory);
		boolean status;
		if (!file.exists()) {
			file.mkdirs();
		}

		if (file.exists()) {
			status = true;
		} else {
			System.out.println("In CSVManager:");
			ErrorHandler.directoryCannotBeCreated(directory);
			status = false;
		}

		return status;
	}
}

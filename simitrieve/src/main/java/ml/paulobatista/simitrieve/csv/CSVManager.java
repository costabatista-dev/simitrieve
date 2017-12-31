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

import ml.paulobatista.simitrieve.entity.Project;
import ml.paulobatista.simitrieve.error.ErrorHandler;
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
		
		String[] header = new String[] {"Feature", "Value"};
		
		List<String[]> info = new ArrayList<String[]>();
		
		info.add(new String[] {"Name", projectName});
		info.add(new String[] {"Version", version});
		info.add(new String[] {"Language", programmingLanguageName});
		info.add(new String[] {"NOP", Integer.toString(numberOfPackages)});
		info.add(new String[] {"NOC", Integer.toString(numberOfClasses)});
		info.add(new String[] {"LOC", Integer.toString(numberOfLinesOfCode)});
		
		String outputDirectoryName = fScanner.getProjectName(project) + "_stats" 
				+ File.separator + "features";
				
		createOutputDiretctory(outputDirectoryName);
		
		String outputName = fScanner.getProjectName(project) + "_features.csv";
		
		writeCSV(outputDirectoryName, outputName, header, info);
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
		if(!file.exists()) {
			file.mkdirs();
		}
		
		if(file.exists()) {
			status = true;
		}
		else {
			System.out.println("In CSVManager:");
			ErrorHandler.directoryCannotBeCreated(directory);
			status = false;
		}
		
		return status;
	}
}

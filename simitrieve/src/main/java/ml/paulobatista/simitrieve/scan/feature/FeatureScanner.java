/**
 * 
 */
package ml.paulobatista.simitrieve.scan.feature;

import java.util.ArrayList;
import java.util.List;

import ml.paulobatista.simitrieve.entity.Class;
import ml.paulobatista.simitrieve.entity.Package;
import ml.paulobatista.simitrieve.entity.ProgrammingLanguage;
import ml.paulobatista.simitrieve.entity.Project;
import ml.paulobatista.simitrieve.error.ErrorHandler;

/**
 * @author costa
 *
 */
public class FeatureScanner {

	public String getProjectName(Project project) {
		String projectName = project.getName();
		
		return projectName;
	}
	
	public String getProjectVersion(Project project) {
		String version = project.getVersion();
		if(version == null || version.equals("")) {
			System.out.println("In Feature Scanner:");
			ErrorHandler.projectVersionIsNULL();
		}
		return version;
	}
	
	public String getProgrammingLanguageName(Project project) {
		ProgrammingLanguage programmingLanguage = project.getProgrammingLanguage();
		
		String programmingLanguageName = programmingLanguage.getProgrammingLanguage();
		
		return programmingLanguageName;
	}
	
	public List<String> getPackageNameList(Project project) {
		List<String> packageNameList = new ArrayList<>();
		
		for(Package packageIterator : project.getPackages()) {
			packageNameList.add(packageIterator.getName());
		}
		
		return packageNameList;
	}
	
	public List<String> getClassNameList(Project project) {
		List<String> classNameList = new ArrayList<>();
		
		for(Package packageIterator : project.getPackages()) {
			classNameList.addAll(getClassNameList(packageIterator));
		}
		
		return classNameList;
	}

	public int getNumberOfClasses(Project project) {
		int numberOfClasses = 0;
		
		for(Package packageIterator : project.getPackages()) {
			numberOfClasses += getNumberOfClasses(packageIterator);
		}
		
		return numberOfClasses;
	}
	public int getNumberOfClasses(Package packageFile) {
		int numberOfClasses = packageFile.getClasses().size();
		
		return numberOfClasses;
	}
	public int getNumberOfPackages(Project project) {
		int numberOfPackages = 0;
		
		numberOfPackages = project.getPackages().size();
		
		return numberOfPackages;
	}
	
	public List<String> getClassNameList(Package packageFile) {
		List<String> classNameList = new ArrayList<>();
		
		for(Class classIterator : packageFile.getClasses()) {
			classNameList.add(classIterator.getName());
		}
		
		
		return classNameList;
	}
	
	public int getNumberOfLinesOfCode(Project project) {
		int numberOfLinesOfCode = 0;
		
		for(Package packageIterator : project.getPackages()) {
			numberOfLinesOfCode += getNumberOfLinesOfCode(packageIterator);
		}
		
		return numberOfLinesOfCode;
	}
	
	public int getNumberOfLinesOfCode(Package packageFile) {
		int numbeOfLinesOfCode = 0;
		
		for(Class classIterator : packageFile.getClasses()) {
			numbeOfLinesOfCode += getNumberOfLinesOfCode(classIterator);
		}
		
		return numbeOfLinesOfCode;
	}
	
	public int getNumberOfLinesOfCode(Class classFile) {
		int numberOfLinesOfCode = 0;
		numberOfLinesOfCode = classFile.getSourceCode().size();
		
		return numberOfLinesOfCode;
	}
	

}

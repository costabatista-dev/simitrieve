/**
 * 
 */
package ml.paulobatista.simitrieve.entity.factory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import ml.paulobatista.simitrieve.entity.Class;
import ml.paulobatista.simitrieve.entity.Package;
import ml.paulobatista.simitrieve.entity.Project;
import ml.paulobatista.simitrieve.entity.process.ProgrammingLanguage;
import ml.paulobatista.simitrieve.error.ErrorHandler;
import ml.paulobatista.simitrieve.scan.Pattern;
import ml.paulobatista.simitrieve.scan.Scanner;

/**
 * @author paulo
 *
 */

public class ProjectFactory {

	public Project getProject(String root, String projectNameValue, ProgrammingLanguage programmingLanguage) {
		Scanner scan = new Scanner();
		
		List<File> files = scan.getFiles(root, programmingLanguage);
		
		List<Class> classes = getClassList(files);
		
		String projectName = projectNameValue;

		List<Package> packages = getPackageList(classes, projectName);
		
		Project project = new Project();
		
		project.setName(projectName);
		project.setPackages(packages);
		project.setProgrammingLanguage(programmingLanguage);
		
		return project;
	}
	
	public List<Package> getPackageList(List<Class> classes, String projectName) {
		List<Package> packages = new ArrayList<>();
		String packageName =  null;
		
		for (Class classIterator : classes) {
			packageName = getPackageName(classIterator, projectName);
			if(!contains(packageName, packages)) {
				packages.add(getPackageInstance(classIterator, projectName));
			}
			else {
				int indexOfPackage = indexOf(packageName, packages);
				packages.get(indexOfPackage).addClass(classIterator);
			}
		}
		return packages;
	}

	public List<Class> getClassList(List<File> files) {
		List<Class> classes = new ArrayList<>();

		for (File iteratorFile : files) {
			classes.add(getClassInstance(iteratorFile));
		}

		return classes;
	}
	
	@Deprecated
	@SuppressWarnings("unused")
	private String getProjectName(String root) {
		String projectName = new String(root);
		int begin = projectName.lastIndexOf(File.separator);
		
		projectName = projectName.substring(begin);
		projectName = projectName.replace(File.separator,"");
		
		return projectName;
	}
	
	// this method add the class that is passed by parameter.
	private Package getPackageInstance(Class classFile, String projectName) {

		String packageName = getPackageName(classFile, projectName);
		
		List<Class> classes = new ArrayList<>();
		classes.add(classFile);
		
		Package pack = new Package(packageName, classes);		
		
		return pack;
	}
	
	private boolean contains(String packageName, List<Package> packages) {
		
		for(Package pack : packages) {
			String name = pack.getName();
			if(name.equals(packageName)) {
				return true;
			}
		}
		return false;
	}

	private int indexOf(String packageName, List<Package> packages) {
	
		String name = null;
		for(int i = 0; i < packages.size(); i++) {
			name = packages.get(i).getName();
			if(packageName.equals(name)) {
				return i;
			}
		}
		
		return -1;
	}
	private String getPackageName(Class classFile, String projectName) {
		String absoluteName = new String(classFile.getPath());
		String className = new String(classFile.getName());
		
		int begin = absoluteName.indexOf(projectName);
		
		if(begin < 0) {
			System.out.println("In ProjectFactor: ");
			ErrorHandler.projectNameIsWrong();
		}
		int end = absoluteName.lastIndexOf(className);
	
		String name = new String(absoluteName.substring(begin, end));
		
		name = name.replaceAll("\\" + File.separator, "\\.");
		if (name.endsWith(".")) {
			name = name.substring(0, name.length() - 1);
		}
		
		return name;
	}

	private Class getClassInstance(File file) {
		Class newClass = new Class();
		String name = getClassName(file);
		String path = file.getPath();

		Scanner scan = new Scanner();
		List<String> sourceCode = scan.getTextFromFile(file);

		newClass.setName(name);
		newClass.setPath(path);
		newClass.setSourceCode(sourceCode);

		return newClass;
	}

	private String getClassName(File file) {
		String absoluteName = file.getName();
		
		String name = null;

		if( absoluteName.endsWith(Pattern.java()[0])) {
			name =  absoluteName.replace(Pattern.java()[0], "");
		}
		else if(absoluteName.endsWith(Pattern.javascript()[0])) {
			name =  absoluteName.replace(Pattern.javascript()[0], "");
		}
		else if(absoluteName.endsWith(Pattern.python()[0])) {
			name = absoluteName.replace(Pattern.python()[0], "");
		}
		else if(absoluteName.endsWith(Pattern.ruby()[0])) {
			name = absoluteName.replace(Pattern.ruby()[0], "");
		}
		else if(absoluteName.endsWith(Pattern.cpp()[0])) {
			name = absoluteName.replace(Pattern.cpp()[0], "");
		}
		else if(absoluteName.endsWith(Pattern.cpp()[1])) {
			name = absoluteName.replace(Pattern.cpp()[1], "");
		}
		else if(absoluteName.endsWith(Pattern.cpp()[2])) {
			name = absoluteName.replace(Pattern.cpp()[2], "");
		}
		else if (absoluteName.endsWith(Pattern.cpp()[3])) {
			name = absoluteName.replace(Pattern.cpp()[3], "");
		}
		else {
			System.out.println("In ProjectFactory class:");
			ErrorHandler.UnexpectedFileExtension();
		}
		
		return name;
	}

}

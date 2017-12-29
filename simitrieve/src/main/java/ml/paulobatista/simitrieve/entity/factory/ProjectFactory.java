/**
 * 
 */
package ml.paulobatista.simitrieve.entity.factory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import ml.paulobatista.simitrieve.entity.Class;
import ml.paulobatista.simitrieve.entity.Package;
import ml.paulobatista.simitrieve.error.ErrorHandler;
import ml.paulobatista.simitrieve.scan.Pattern;
import ml.paulobatista.simitrieve.scan.Scanner;

/**
 * @author paulo
 *
 */
public class ProjectFactory {

	public List<Package> getPackageList(List<Class> classes) {

		// not supported yet
		return null;
	}

	public List<Class> getClassList(List<File> files) {
		List<Class> classes = new ArrayList<Class>();

		for (File iteratorFile : files) {
			classes.add(getClassInstance(iteratorFile));
		}

		return classes;
	}

	private Package getPackageInstance(Class classFile) {
		// not supported yet
		return null;
	}

	private String getPackageName(Class classFile, String projectName) {
		String absoluteName = classFile.getPath();
		String className = classFile.getName();
		
		int begin = absoluteName.indexOf(projectName);
		int end = absoluteName.indexOf(className);
		
		String name = absoluteName.substring(begin, end);
		
		name = name.replaceAll("\\" + File.separator, "\\.");
		name = name.substring(0, name.length() - 1);

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

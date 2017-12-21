/**
 * 
 */
package ml.paulobatista.simitrieve.entity.factory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import ml.paulobatista.simitrieve.entity.Class;
import ml.paulobatista.simitrieve.scan.Pattern;
import ml.paulobatista.simitrieve.scan.Scanner;

/**
 * @author paulo
 *
 */
public class ProjectFactory {
	
	
	public List<Class> getClassList(List<File> files) {
		List<Class> classes = new ArrayList<Class>();
		
		for(File iteratorFile: files) {
			classes.add(getClassInstance(iteratorFile));
		}
		
		return classes;
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
		
		name = absoluteName.endsWith(Pattern.java()[0]) ?  absoluteName.replace(Pattern.java()[0], "") : null;
		name = absoluteName.endsWith(Pattern.javascript()[0]) ?  absoluteName.replace(Pattern.javascript()[0], "") : null;
		name = absoluteName.endsWith(Pattern.python()[0]) ?  absoluteName.replace(Pattern.python()[0], "") : null;
		name = absoluteName.endsWith(Pattern.ruby()[0]) ?  absoluteName.replace(Pattern.ruby()[0], "") : null;
		name = absoluteName.endsWith(Pattern.cpp()[0]) ?  absoluteName.replace(Pattern.cpp()[0], "") : null;
		name = absoluteName.endsWith(Pattern.java()[1]) ?  absoluteName.replace(Pattern.java()[1], "") : null;
		name = absoluteName.endsWith(Pattern.java()[2]) ?  absoluteName.replace(Pattern.java()[2], "") : null;
		name = absoluteName.endsWith(Pattern.java()[3]) ?  absoluteName.replace(Pattern.java()[3], "") : null;
		
		return name;
	}

}

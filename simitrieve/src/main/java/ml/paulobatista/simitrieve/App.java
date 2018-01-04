package ml.paulobatista.simitrieve;

import java.io.File;
import java.util.List;

import ml.paulobatista.simitrieve.csv.CSVManager;
import ml.paulobatista.simitrieve.entity.Class;
import ml.paulobatista.simitrieve.entity.Package;
import ml.paulobatista.simitrieve.entity.Project;
import ml.paulobatista.simitrieve.entity.factory.ProjectFactory;
import ml.paulobatista.simitrieve.entity.process.ProgrammingLanguage;
import ml.paulobatista.simitrieve.filter.CommentRemover;
import ml.paulobatista.simitrieve.tokenizer.Tokenizer;
/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		String root = System.getProperty("user.dir") + File.separator + "jabref-4.0";
		//System.out.println(root);
		Project project = new ProjectFactory().getProject(root, ProgrammingLanguage.JAVA);
		project.setVersion("4.0");
		
		List<String> text;
		for (Package pack : project.getPackages()) {
			for(Class c  : pack.getClasses()) {
				text = c.getSourceCode();
				text = new CommentRemover().removeJavaComments(text);
				text = new Tokenizer().tokenize(text);
				
				for(String line : text) {
					System.out.println(line);
				}
			}
		}
		CSVManager csvManager = new CSVManager();
		
		csvManager.writeProjectFeaturesCSV(project);
		
		
		
		
		//FeatureScanner fScanner = new FeatureScanner();
		
		/*System.out.println("Project name: " + fScanner.getProjectName(project));
		System.out.println("Programming language: " + fScanner.getProgrammingLanguageName(project));
		System.out.println("Lines of code: " + fScanner.getNumberOfLinesOfCode(project));
		System.out.println("Number of Classes: " + fScanner.getNumberOfClasses(project));
		System.out.println("Number of Packages: " + fScanner.getNumberOfPackages(project));
		
		/*System.out.println("Project name: " + project.getName());
		System.out.println("Packages: ");
	
		for (Package pack : project.getPackages()) {
			System.out.println(pack.getName());
		}
		
		System.out.println("Classes: ");
		
		for(Package pack: project.getPackages()) {
			for(Class classIterator : pack.getClasses()) {
				System.out.println("Class: " + classIterator.getName());
				for(String line : classIterator.getSourceCode()) {
					System.out.println(line);
				}
			}
		}*/
		
	}
}

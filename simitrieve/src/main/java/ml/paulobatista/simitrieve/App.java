package ml.paulobatista.simitrieve;

import java.io.File;
import java.util.List;

import ml.paulobatista.simitrieve.csv.CSVManager;
import ml.paulobatista.simitrieve.entity.Project;
import ml.paulobatista.simitrieve.entity.Token;
import ml.paulobatista.simitrieve.entity.TokenList;
import ml.paulobatista.simitrieve.entity.factory.ProjectFactory;
import ml.paulobatista.simitrieve.entity.process.CamelCase;
import ml.paulobatista.simitrieve.entity.process.Comment;
import ml.paulobatista.simitrieve.entity.process.ProgrammingLanguage;
import ml.paulobatista.simitrieve.entity.process.Stem;
import ml.paulobatista.simitrieve.process.Process;
import ml.paulobatista.simitrieve.tokenizer.TokenManager;
/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		String root = System.getProperty("user.dir") + File.separator + "jabref-4.0";
	
		Project project = new ProjectFactory().getProject(root, ProgrammingLanguage.JAVA);
		project.setVersion("4.0");
		
		Process process = new Process();
		process.setProgrammingLanguage(ProgrammingLanguage.JAVA);
		process.setCamelCase(CamelCase.YES);
		process.setComment(Comment.NO);
		process.setStem(Stem.YES);
		
		TokenManager tokenManager = new TokenManager();
		
		List<TokenList> allTokenLists = tokenManager.getAllTokenList(project, process);
		
		for(TokenList tokenList: allTokenLists) {
			System.out.println("Package: " + tokenList.getPackageName());
			System.out.println("Class: " + tokenList.getClassName());
			
			for(Token token : tokenList) {
				System.out.println("word: " + token.getValue());
				System.out.println("quantity " + token.getQuantity());
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

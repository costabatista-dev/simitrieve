package ml.paulobatista.simitrieve;

import java.io.File;
import java.util.List;

import ml.paulobatista.simitrieve.csv.CSVManager;
import ml.paulobatista.simitrieve.entity.CosineSimilarity;
import ml.paulobatista.simitrieve.entity.Project;
import ml.paulobatista.simitrieve.entity.Token;
import ml.paulobatista.simitrieve.entity.TokenList;
import ml.paulobatista.simitrieve.entity.factory.ProjectFactory;
import ml.paulobatista.simitrieve.entity.process.CamelCase;
import ml.paulobatista.simitrieve.entity.process.Comment;
import ml.paulobatista.simitrieve.entity.process.Normalization;
import ml.paulobatista.simitrieve.entity.process.ProgrammingLanguage;
import ml.paulobatista.simitrieve.entity.process.Quantile;
import ml.paulobatista.simitrieve.entity.process.Stem;
import ml.paulobatista.simitrieve.process.Process;
import ml.paulobatista.simitrieve.similarity.SimilarityRetriever;
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
		process.setQuantile(Quantile.FIRST);
		process.setNormalization(Normalization.NAIVE);
		TokenManager tokenManager = new TokenManager();
		
		List<TokenList> allTokenLists = tokenManager.getAllTokenList(project, process);
		
		TokenList tokenListProject = tokenManager.getProjectTokenList(allTokenLists);
		
		/*for(Token token : tokenListProject) {
			System.out.println("word: " + token.getValue());
			System.out.println("quantity " + token.getQuantity());
			System.out.println("------------");
		}*/
		
		System.out.println("Size of token project list: " + tokenListProject.size());
		for(TokenList tokenList: allTokenLists) {
			System.out.println("Package: " + tokenList.getPackageName());
			System.out.println("Class: " + tokenList.getClassName());
			
			for(Token token : tokenList) {
				System.out.println("word: " + token.getValue());
				System.out.println("quantity " + token.getQuantity());
			}
		}
		
		List<CosineSimilarity> similarities = new SimilarityRetriever().getCosineSimilarities(allTokenLists, process);
	
		for(CosineSimilarity cosineSimilarity : similarities) {
			System.out.println("First Package: " + cosineSimilarity.getFirstPackage());
			System.out.println("First Class: " + cosineSimilarity.getFirstClass());
			System.out.println("Second Package: " + cosineSimilarity.getSecondPackage());
			System.out.println("Second Class: " + cosineSimilarity.getSecondClass());
			System.out.println("Similarity: " + cosineSimilarity.getSimilarity());
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

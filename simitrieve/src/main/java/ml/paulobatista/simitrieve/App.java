/**
 * 
 */
package ml.paulobatista.simitrieve;

import ml.paulobatista.simitrieve.entity.ProgrammingFile;
import ml.paulobatista.simitrieve.entity.Project;
import ml.paulobatista.simitrieve.preprocessing.CamelCaseSplitter;
import ml.paulobatista.simitrieve.preprocessing.CommentRemover;
import ml.paulobatista.simitrieve.preprocessing.Preprocessor;

/**
 * @author paulo
 *
 */
public class App {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Project project = new Project("agera", new String[] {"java"});
		CommentRemover cr = new CommentRemover();
		cr.removeComments(project);
		Preprocessor pcp = new Preprocessor();
		pcp.removeSpecialCharacters(project);
		pcp.removeStopwords(project);
		pcp.removeNonConceptWords(project);
		CamelCaseSplitter ccs = new CamelCaseSplitter();
		ccs.splitCamelCase(project);
		
		for(ProgrammingFile pf : project) {
			System.out.println(pf.getSourceCode());
		}
	}

}

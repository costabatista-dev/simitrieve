/**
 * 
 */
package ml.paulobatista.simitrieve.preprocessing;

import ml.paulobatista.simitrieve.entity.ProgrammingFile;
import ml.paulobatista.simitrieve.entity.Project;

/**
 * @author paulo
 *
 */
public class Preprocessor {
	//this class will implement the logic of preprocessing methods.
	
	public Project removeComments(Project project) {
		throw new UnsupportedOperationException("Not implemented yet.");
	}
	
	public void removeStopwords(Project project) {
		StopwordsRemover stpwRemover = new StopwordsRemover();
		stpwRemover.removeStopwords(project);
	}
	
	
	public void removeSpecialCharacters(Project project) {
		String content;
		for(ProgrammingFile pf : project) {
			content = pf.getSourceCode().replaceAll("[\\W]", " ");
			pf.setSourceCode(content);
		}
	}
	
	public void removeNonConceptWords(Project project) {
		String content;
		
		for(ProgrammingFile pf : project) {
			content = pf.getSourceCode();
			content = content.replaceAll("\\s\\w\\w?\\s", " ");
			pf.setSourceCode(content);
		}
		
	}

}

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
	
	public Project removeStopwords(Project project) {
		throw new UnsupportedOperationException("Not implemented yet.");
	}
	
	
	public void removeSpecialCharacters(Project project) {
		String content;
		for(ProgrammingFile pf : project) {
			content = pf.getSourceCode().replaceAll("[\\W]", " ");
			pf.setSourceCode(content);
		}
	}

}

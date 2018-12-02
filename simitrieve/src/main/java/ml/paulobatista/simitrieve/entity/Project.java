/**
 * 
 */
package ml.paulobatista.simitrieve.entity;

import java.util.ArrayList;

import ml.paulobatista.simitrieve.preprocessing.read.ProjectReader;

/**
 * @author paulo
 *
 */
public class Project extends ArrayList<ProgrammingFile> {
	private static final long serialVersionUID = -1438536360244243911L;
	private Language language;
	
	public Project() {

	}

	public Project(String root, String[] ext) {
		this.addAll(new ProjectReader().getProject(root, ext));
		this.setProjectLanguage(ext);
	}
	
	public void setLanguage(Language language) {
		this.language = language;
	}
	
	public Language getLanguage() {
		return this.language;
	}
	
	private void setProjectLanguage(String[] ext) {
		for(String s : ext) {
			if(s.equals("cpp") || s.equals("cc") || s.equals("c++")) {
				setLanguage(Language.CPP);
				break;
			}
			else if(s.equals("java")) {
				setLanguage(Language.JAVA);
				break;
			}
			else if(s.equals("js")) {
				setLanguage(Language.JAVASCRIPT);
				break;
			}
			else if(s.equals("py")) {
				setLanguage(Language.PYTHON);
				break;
			}
			else if(s.equals("rb")) {
				setLanguage(Language.RUBY);
			}
			else {
				throw new UnsupportedOperationException("Language not supported.");
			}
		}
	}

}

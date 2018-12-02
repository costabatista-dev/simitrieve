/**
 * 
 */
package ml.paulobatista.simitrieve;

import java.util.ArrayList;

/**
 * @author paulo
 *
 */
public class Project extends ArrayList<ProgrammingFile> {
	private static final long serialVersionUID = -1438536360244243911L;

	public Project() {

	}

	public Project(String root, String[] ext) {
		this.addAll(new ProjectReader().getProject(root, ext));

	}

}

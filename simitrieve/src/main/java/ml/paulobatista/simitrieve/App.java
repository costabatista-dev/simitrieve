/**
 * 
 */
package ml.paulobatista.simitrieve;

import java.util.List;

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
		//List<String> paths = new ProjectReader().getProgrammingFilePaths("react",new String[] {"js"});
		Project proj = new Project("react", new String[] {"js"});
		for(ProgrammingFile pf: proj) {
			System.out.println("--------------");
			System.out.println(pf.getPath());
			System.out.println(pf.getContent());
		}
	}

}

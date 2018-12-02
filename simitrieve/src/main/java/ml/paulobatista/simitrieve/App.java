/**
 * 
 */
package ml.paulobatista.simitrieve;

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
		
		for(ProgrammingFile pf : project) {
			System.out.println(pf.getSourceCode());
		}
	}

}

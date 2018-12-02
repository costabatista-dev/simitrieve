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
		//List<String> paths = new ProjectReader().getProgrammingFilePaths("react",new String[] {"js"});
		Project proj = new Project("flask", new String[] {"py"});
		CommentRemover cr = new CommentRemover();
		for(ProgrammingFile pf: proj) {
			System.out.println("--------------");
			System.out.println(pf.getPath());
			//System.out.println(cr.removeJavaComment(pf.getSourceCode()));
			System.out.println(cr.removePyComments(pf.getSourceCode()));
			
		}
	}

}

/**
 * 
 */
package ml.paulobatista.simitrieve;

import ml.paulobatista.simitrieve.args.ArgumentManager;
import ml.paulobatista.simitrieve.entity.Project;

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
		ArgumentManager am = new ArgumentManager();
		Project project = am.execute(args);
		
		project.forEach(pf -> {
			pf.getQuantifiedTerms().entrySet().forEach(item -> {
				System.out.println(item.getKey() + ": " + item.getValue());
			});
		});
	}

}

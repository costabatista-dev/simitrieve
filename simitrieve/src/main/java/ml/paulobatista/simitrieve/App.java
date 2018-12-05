/**
 * 
 */
package ml.paulobatista.simitrieve;

import ml.paulobatista.simitrieve.args.ArgumentManager;

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
		am.execute(args);
		String path = am.getProjectPath();
		System.out.println(path);
		String name = am.getProjectName();
		System.out.println(name);
	}
	
}

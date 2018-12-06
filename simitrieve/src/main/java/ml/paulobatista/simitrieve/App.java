/**
 * 
 */
package ml.paulobatista.simitrieve;

import ml.paulobatista.simitrieve.args.ArgumentManager;
import ml.paulobatista.simitrieve.entity.Language;

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
		am.applyProcessSettings(args);
		String path = am.getProjectPath();
		System.out.println(path);
		String name = am.getProjectName();
		System.out.println(name);
		Language lng = am.getLanguage();
		System.out.println(lng);
		float percent = am.getRemoveLessFrequencyPercent();
		System.out.println(percent);
	}
	
}

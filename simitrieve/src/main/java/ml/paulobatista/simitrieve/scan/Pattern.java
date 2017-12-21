/**
 * 
 */
package ml.paulobatista.simitrieve.scan;

/**
 * @author paulo
 *
 */
public class Pattern {
	public static String python() {
		return ".py";
	}
	
	public static String java() {
		return ".java";
	}
	
	public static String ruby() {
		return ".rb";
	}
	
	public static String javascript() {
		return ".js";
	}
	
	public static String[] cpp() {
		String[] patterns = {".cpp",".cc",".h"};
		return patterns;
	}
}

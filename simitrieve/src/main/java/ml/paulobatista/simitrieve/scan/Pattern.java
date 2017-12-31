/**
 * 
 */
package ml.paulobatista.simitrieve.scan;

/**
 * @author paulo
 *
 */
public class Pattern {
	
	
	public static String[] python() {
		String[] pattern = {".py"};
		return pattern;
	}
	
	public static String[] java() {
		String[] pattern = {".java"};
		return pattern;
	}
	
	public static String[] ruby() {
		String[] pattern = {".rb"};
		return pattern;
	}
	
	public static String[] javascript() {
		String[] pattern = {".js"};
		return pattern;
	}
	
	public static String[] cpp() {
		String[] patterns = {".cpp",".cc", ".c",".h"};
		return patterns;
	}
}

/**
 * 
 */
package ml.paulobatista.simitrieve.entity;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * @author paulo
 *
 */
public class ProgrammingFile {
	private String path;
	private String sourceCode;
	private LinkedHashMap<String, Integer> quantifiedTerms;
	
	public ProgrammingFile() {
		this.quantifiedTerms = new LinkedHashMap<>();
	}
	
	public ProgrammingFile(String path, String sourceCode) {
		this.path = path;
		this.sourceCode = sourceCode;
		this.quantifiedTerms = new LinkedHashMap<>();
	}
	
	public void setPath(String path) {
		this.path = path;
	}
	
	public void setSourceCode(String sourceCode) {
		this.sourceCode = sourceCode;
	}
	
	public void setQuantifiedTerms(LinkedHashMap<String, Integer> quantifiedTerms) {
		this.quantifiedTerms = quantifiedTerms;
	}
	
	public String getPath() {
		return this.path;
	}
	
	public String getSourceCode() {
		return this.sourceCode;
	}
	
	public HashMap<String, Integer> getQuantifiedTerms() {
		return this.quantifiedTerms;
	}
}

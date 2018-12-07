/**
 * 
 */
package ml.paulobatista.simitrieve.entity;

import java.io.File;
import java.util.LinkedHashMap;

/**
 * @author paulo
 *
 */
public class ProgrammingFile {
	private String path;
	private String name;
	private String sourceCode;
	private LinkedHashMap<String, Integer> quantifiedTerms;
	
	public ProgrammingFile() {
		this.quantifiedTerms = new LinkedHashMap<>();
	}
	
	public ProgrammingFile(String path, String sourceCode) {
		this.path = path;
		this.sourceCode = sourceCode;
		this.quantifiedTerms = new LinkedHashMap<>();
		
		String name = path.substring(path.lastIndexOf(File.separator) + 1);
		this.name = name;
	}
	
	public void setPath(String path) {
		this.path = path;
	}
	
	public void setName(String name) {
		this.name = name;
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
	
	public String getName() {
		return this.name;
	}
	
	public String getSourceCode() {
		return this.sourceCode;
	}
	
	public LinkedHashMap<String, Integer> getQuantifiedTerms() {
		return this.quantifiedTerms;
	}
}

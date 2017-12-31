/**
 * 
 */
package ml.paulobatista.simitrieve.entity;

import java.util.List;

/**
 * @author paulo
 *
 */
public class Class {
	private String name;
	private String path;
	private List<String> sourceCode;

	public Class() {
		
	}
	
	public Class(String path, List<String> sourceCode) {
		setPath(path);
		setSourceCode(sourceCode);
	}
	
	public List<String> getSourceCode() {
		return sourceCode;
	}

	public void setSourceCode(List<String> sourceCode) {
		this.sourceCode = sourceCode;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPath() {
		return path;
	}
	
	public void setPath(String path) {
		this.path = path;
	}
}

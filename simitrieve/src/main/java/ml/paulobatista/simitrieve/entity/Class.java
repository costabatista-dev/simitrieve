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
	private List<String> sourceCode;

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
	
}

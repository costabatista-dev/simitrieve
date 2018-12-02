/**
 * 
 */
package ml.paulobatista.simitrieve;

/**
 * @author paulo
 *
 */
public class ProgrammingFile {
	private String path;
	private String sourceCode;
	
	public ProgrammingFile() {
		
	}
	
	public ProgrammingFile(String path, String sourceCode) {
		this.path = path;
		this.sourceCode = sourceCode;
	}
	
	public void setPath(String path) {
		this.path = path;
	}
	
	public void setSourceCode(String sourceCode) {
		this.sourceCode = sourceCode;
	}
	
	public String getPath() {
		return this.path;
	}
	
	public String getSourceCode() {
		return this.sourceCode;
	}
}

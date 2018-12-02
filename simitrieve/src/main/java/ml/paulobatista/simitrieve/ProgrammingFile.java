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
	private String content;
	
	public ProgrammingFile() {
		
	}
	
	public ProgrammingFile(String path, String content) {
		this.path = path;
		this.content = content;
	}
	
	public void setPath(String path) {
		this.path = path;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getPath() {
		return this.path;
	}
	
	public String getContent() {
		return this.content;
	}
}

/**
 * 
 */
package ml.paulobatista.simitrieve.entity;

/**
 * @author paulo
 *
 */
public enum ProgrammingLanguage {
	PYTHON("Python"), JAVASCRIPT("Javascript"),
	JAVA("Java"), RUBY("Ruby"), CPP("Cpp");
	
	private String programmingLanguage;
	
	ProgrammingLanguage(String programmingLanguage) {
		this.programmingLanguage = programmingLanguage;
	}
	
	public String getProgrammingLanguage() {
		return this.programmingLanguage;
	}
}

/**
 * 
 */
package ml.paulobatista.simitrieve;

/**
 * @author paulo
 *
 */
public enum Language {
	CPP("cpp"),
	JAVA("java"),
	JAVASCRIPT("js"), 
	PYTHON("py"),
	RUBY("rb");
	
	private final String languageCode;
	
	Language(String lng) {
		this.languageCode = lng;
	}
	
	public String getLanguageCode() {
		return this.languageCode;
	}

}

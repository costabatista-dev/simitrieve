/**
 * 
 */
package ml.paulobatista.simitrieve.entity.process;

/**
 * @author Paulo Batista
 *
 */
public enum Normalization {
	TFIDF("tfidf"),
	NAIVE("naive"),
	LSI("lsi");
	
	
	private String technique;
	
	Normalization(String technique) {
		this.technique = technique;
	}

	public String getTechnique() {
		return this.technique;
	}
}

/**
 * 
 */
package ml.paulobatista.simitrieve.entity.project;

/**
 * @author costa
 *
 */
public enum TermWeightFactor {
	TF("tf"),
	TFIDF("tfidf"),
	LSI("lsi");
	
	private final String factor;
	
	private TermWeightFactor(String factor) {
		// TODO Auto-generated constructor stub
		this.factor = factor;
	}
	
	public String getFactor() {
		return this.factor;
	}
}

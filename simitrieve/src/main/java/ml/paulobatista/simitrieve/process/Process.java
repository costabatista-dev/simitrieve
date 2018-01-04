/**
 * 
 */
package ml.paulobatista.simitrieve.process;

import ml.paulobatista.simitrieve.entity.process.*;

/**
 * @author Paulo Batista
 *
 */
public class Process {
	private ProgrammingLanguage programmingLanguage;
	private CamelCase camelCase;
	private Quantile quantile;
	private Stem stem;
	private Comment comment;
	private Normalization normalization;
	
	
	
	
	public void setProgrammingLanguage(ProgrammingLanguage programmingLanguage) {
		this.programmingLanguage = programmingLanguage;
	}
	
	public ProgrammingLanguage getProgrammingLanguage() {
		return this.programmingLanguage;
	}
	
	public void setCamelCase(CamelCase camelCase) {
		this.camelCase = camelCase;
	}
	
	public CamelCase getCamelCase() {
		return this.camelCase;
	}
	
	public void setQuantile(Quantile quantile) {
		this.quantile = quantile;
	}
	
	public Quantile getQuantile() {
		return this.quantile;
	}
	
	public void setStem(Stem stem) {
		this.stem = stem;
	}
	
	public Stem getStem() {
		return this.stem;
	}
	
	public void setComment(Comment comment) {
		this.comment = comment;
	}
	
	public Comment getComment() {
		return this.comment;
	}
	
	public void setNormalization(Normalization normalization) {
		this.normalization = normalization;
	}
	
	public Normalization getNormalization() {
		return this.normalization;
	}
}

/**
 * 
 */
package ml.paulobatista.simitrieve.entity;

/**
 * @author Paulo Batista
 *
 */
public class CosineSimilarity {
	private String firstClass;
	private String firstPackage;
	private String secondClass;
	private String secondPackage;
	private double similarity;
	
	public CosineSimilarity(String firstClass, String secondClass) {
		this.firstClass = firstClass;
		this.secondClass = secondClass;
	}

	public void setFirstClass(String firstClass) {
		this.firstClass = firstClass;
	}
	
	public String getFirstClass() {
		return this.firstClass;
	}
	
	public void setSecondClass(String secondClass) {
		this.secondClass = secondClass;
	}
	
	public String getSecondClass() {
		return this.secondClass;
	}
	
	public void setSimilarity(double similarity) {
		this.similarity = similarity;
	}
	
	public double getSimilarity() {
		return this.similarity;
	}
	
	public void setFirstPackage(String firstPackage) {
		this.firstPackage = firstPackage;
	}
	
	public String getFirstPackage() {
		return this.firstPackage;
	}
	
	public void setSecondPackage(String secondPackage) {
		this.secondPackage = secondPackage;
	}
	
	public String getSecondPackage() {
		return this.secondPackage;
	}
}

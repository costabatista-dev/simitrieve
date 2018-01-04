/**
 * 
 */
package ml.paulobatista.simitrieve.entity.process;

/**
 * @author Paulo Batista
 *
 */
public enum Quantile {
	FIRST(0.25),
	SECOND(0.5),
	THIRD(0.75),
	FOURTH(1);
	
	private double value;
	
	Quantile(double value) {
		this.value = value;
	}
	
	public double getValue() {
		return this.value;
	}
}

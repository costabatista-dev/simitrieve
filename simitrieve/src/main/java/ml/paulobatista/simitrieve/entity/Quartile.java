/**
 * 
 */
package ml.paulobatista.simitrieve.entity;

/**
 * @author costa
 *
 */
public enum Quartile {
	FIRST((float) 0.25),
	SECOND((float) 0.5),
	THIRD((float) 0.75);
	
	private final float percent;
	
	Quartile(float percent) {
		this.percent = percent;
	}
	
	public float getPercent() {
		return this.percent;
	}
}

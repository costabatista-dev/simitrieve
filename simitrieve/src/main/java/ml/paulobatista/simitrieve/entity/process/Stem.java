/**
 * 
 */
package ml.paulobatista.simitrieve.entity.process;

/**
 * @author Paulo Batista
 *
 */
public enum Stem {
	YES(true),
	NO(false);
	
	private boolean option;
	
	Stem(boolean option) {
		this.option = option;
	}
	
	
	public boolean getOption() {
		return this.option;
	}
}

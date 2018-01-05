/**
 * 
 */
package ml.paulobatista.simitrieve.entity.process;

/**
 * @author Paulo Batista
 *
 */
public enum CamelCase {
	
	YES(true),
	NO(false);
	
	private boolean option;
	
	CamelCase(boolean option) {
		this.option = option;
	}
	
	public boolean getOption() {
		return this.option;
	}
	
	 
}

/**
 * 
 */
package ml.paulobatista.simitrieve.entity.process;

/**
 * @author Paulo Batista
 *
 */
public enum Comment {
	
	YES(true),
	NO(false);
	
	private boolean option;
	
	Comment(boolean option) {
		this.option = option;
	}
	
	public boolean getOption() {
		return this.option;
	}
}

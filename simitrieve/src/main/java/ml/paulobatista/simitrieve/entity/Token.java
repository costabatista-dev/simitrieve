/**
 * 
 */
package ml.paulobatista.simitrieve.entity;

/**
 * @author costa
 *
 */
public class Token implements Comparable{
	private String value;
	private int quantity;
	
	
	
	public Token() {
		
	}
	
	public Token(String value) {
		this.value = value;
		this.quantity = 1;
	}
	
	public Token(String value, int quantity) {
		this.value = value;
		this.quantity = quantity;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public int getQuantity() {
		return this.quantity;
	}

	@Override
	public int compareTo(Object anotherObject) {
		Token anotherToken = (Token) anotherObject;
		
		if(this.quantity < anotherToken.getQuantity()) {
			return 1;
		}
		
		if(this.quantity > anotherToken.getQuantity()) {
			return -1;
		}
		
		return 0;
	}
}

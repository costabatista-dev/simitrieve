/**
 * 
 */
package ml.paulobatista.simitrieve.entity;

import java.util.ArrayList;

import ml.paulobatista.simitrieve.error.ErrorHandler;

/**
 * @author costa
 *
 */
@SuppressWarnings("serial")
public class TokenList extends ArrayList<Token> {
	private String className;
	private String packageName;
	
	public TokenList() {
		
	}
	
	public TokenList(String className, String packageName) {
		this.className = className;
		this.packageName = packageName;
	}
	
	public void setClassName(String className) {
		this.className = className;
	}
	
	public String getClassName() {
		return this.className;
	}
	
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	
	public String getPackageName() {
		return this.packageName;
	}
	
	public boolean contains(Token token) {
		String value = token.getValue();
		
		boolean stats = contains(value);
		
		return stats;
	}
	
	public boolean contains(String value) {
		String valueToBeCompared = null;
		
		for(Token token : this) {
			valueToBeCompared = token.getValue();
			
			if(valueToBeCompared.equals(value)) {
				return true;
			}
		}
		
		return false;
	}
	
	public int getIndexOf(Token token) {
		String value = token.getValue();
		
		int index = getIndexOf(value);
		
		return index;
	}
	
	public int getIndexOf(String value) {
		String valueToBeCompared = null;
		
		for(int index = 0; index < this.size(); index++) {
			valueToBeCompared = this.get(index).getValue();
			
			if(valueToBeCompared.equals(value)) {
				return index;
			}
		}

		System.out.println("In TokenList: ");
		ErrorHandler.tokenListDoNotContainsToken(value);
		
		return -1;
	}
	
	public void addQuantity(Token token) {
		String value = token.getValue();
		
		addQuantity(value);
	}
	
	public void addQuantity(Token token, int quantity) {
		String value = token.getValue();
		
		addQuantity(value, quantity);
		
	}
	
	public void addQuantity(String value) {
		int index = getIndexOf(value);
		
		int quantity = this.get(index).getQuantity();
		quantity = quantity + 1;
		
		this.get(index).setQuantity(quantity);
	}
	
	public void addQuantity(String value, int quantity) {
		int index = getIndexOf(value);
		int newQuantity = this.get(index).getQuantity();
		
		newQuantity = quantity + newQuantity;
		
		this.get(index).setQuantity(newQuantity);
		
	}
	
	public void setQuantity(Token token, int quantity) {
		String value = token.getValue();
		setQuantity(value, quantity);
	}
	
	public void setQuantity(String value, int quantity) {
		int index = getIndexOf(value);
		this.get(index).setQuantity(quantity);
	}
}

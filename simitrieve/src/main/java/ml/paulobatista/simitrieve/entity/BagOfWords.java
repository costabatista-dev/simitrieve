/**
 * 
 */
package ml.paulobatista.simitrieve.entity;


import java.util.ArrayList;
import java.util.List;

/**
 * @author costa
 *
 */
public class BagOfWords {
	List<String> classes;
	List<String> words;

	double values[][];
	
	public BagOfWords() {
	
	}
	
	public BagOfWords(List<TokenList> allTokenLists) {
		this.words = getWords(allTokenLists);
		this.classes = getClasses(allTokenLists);
		this.values = getValues(allTokenLists);
	}
	
	public BagOfWords(List<String> classes, List<String> words) {
		this.classes = classes;
		this.words = words;
		
		int sizeOfLines = this.words.size();
		int sizeOfColumns = this.classes.size();
		
		this.values = new double[sizeOfLines][sizeOfColumns];
	}
	
	private double[][] getValues(List<TokenList> allTokenLists) {
		double[][] values = new double[this.words.size()][this.classes.size()];
		// error threat --- whether words or classes are null.
		for(int line = 0; line < this.words.size(); line++) {
			for(int column = 0; column < this.classes.size(); column++) {
				String className = this.classes.get(column);
				TokenList tokenList = getTokenList(allTokenLists, className);
				
				String word = this.words.get(line);
				
				if(tokenList.contains(word)) {
					double value = (double) tokenList.getQuantity(word);
					values[line][column] = value;
				}
				else {
					values[line][column] = 0.0;
				}
			}
		}
		
		return values;
		
	}
	
	private TokenList getTokenList(List<TokenList> allTokenLists, String className) {
		for(TokenList tokenList : allTokenLists) {
			String currentClassName = tokenList.getClassName();
			if(currentClassName.equals(className)) {
				return tokenList;
			}
		}
		
		//error threat.
		
		return null;
	}
	
	private List<String> getClasses(List<TokenList> allTokenLists) {
		List<String> classes = new ArrayList<>();
		
		for(TokenList tokenList : allTokenLists) {
			String className = new String(tokenList.getClassName());
			
			if(!classes.contains(className)) {
				classes.add(className);
			}
		}
		
		return classes;
	}
	
	
	private List<String> getWords(List<TokenList> allTokenLists) {
		List<String> words = new ArrayList<>();
		
		for(TokenList tokenList : allTokenLists) {
			for(Token token : tokenList) {
				String word = new String(token.getValue());
				
				if(!words.contains(word)) {
					words.add(word);
				}
			}
		}
		
		return words;
	}
	
	
	public void setClasses(List<String> classes) {
		this.classes = classes;
	}
	
	public List<String> getClasses() {
		return this.classes;
	}
	
	public void setWords(List<String> words) {
		this.words = words;
	}
	
	public List<String> getWords() {
		return this.words;
	} 
	
	public void setValues(double[][] values) {
		this.values = values;
	}
	
	public double[][] getValues() {
		return this.values;
	}
	
	public int indexOfWord(String word) {
		int index = indexOf(word, this.words);
		//Error threat
		return index;
	}
	
	public int indexOfClass(String className) {
		int index = indexOf(className, this.classes);
		
		//Error threat
		return index;		
	}
	
	private int indexOf(String value, List<String> list) {
		String currentValue = null;
		
		for(int index = 0; index < list.size(); index++) {
			currentValue = list.get(index);
			if(currentValue.equals(value)) {
				return index;
			}
		}
		return -1;
	}
	
	public Position getPosition(String className, String word) {
		int y = this.indexOfWord(word);
		int x = this.indexOfClass(className);
		
		Position position = new Position(x,y);
		
		return position;
	}
}

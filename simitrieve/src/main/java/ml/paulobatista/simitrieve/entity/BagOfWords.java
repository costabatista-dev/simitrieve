/**
 * 
 */
package ml.paulobatista.simitrieve.entity;


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
	
	public BagOfWords(List<String> classes, List<String> words) {
		this.classes = classes;
		this.words = words;
		
		int sizeOfLines = this.words.size();
		int sizeOfColumns = this.classes.size();
		
		this.values = new double[sizeOfLines][sizeOfColumns];
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

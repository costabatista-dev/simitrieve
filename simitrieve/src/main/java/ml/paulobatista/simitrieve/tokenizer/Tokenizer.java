package ml.paulobatista.simitrieve.tokenizer;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Tokenizer {
	
	
	public List<String> tokenizeString(String text) {
		//not supported yet.
		StringTokenizer tokenizer = new StringTokenizer(text, "");
		int size = tokenizer.countTokens();
		
		List<String> tokens = new ArrayList<String>();
		
		for(int index = 0; index < size; index++ ) {
			tokens.add(tokenizer.nextToken());
		}
		return tokens;
	}
	
	
}

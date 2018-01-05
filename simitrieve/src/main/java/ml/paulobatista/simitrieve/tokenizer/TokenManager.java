/**
 * 
 */
package ml.paulobatista.simitrieve.tokenizer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ml.paulobatista.simitrieve.entity.Class;
import ml.paulobatista.simitrieve.entity.Package;
import ml.paulobatista.simitrieve.entity.Project;
import ml.paulobatista.simitrieve.entity.Token;
import ml.paulobatista.simitrieve.entity.TokenList;
import ml.paulobatista.simitrieve.process.Process;

/**
 * @author Paulo Batista
 *
 */
public class TokenManager {

	public Token getToken(String word) {
		Token token = new Token(word);

		return token;
	}
	
	public TokenList getProjectTokenList(List<TokenList> tokenLists) {
		//not supported yet.
		return null;
	}

	public List<TokenList> getAllTokenList(Project project, Process process) {
		List<TokenList> allTokenLists = new ArrayList<>();
		List<Package> packages = new ArrayList<>();
		
		packages.addAll(project.getPackages());
		
		for(Package packageIterator : packages) {
			allTokenLists.addAll(getAllTokenList(packageIterator, process));
		}
		
		return allTokenLists;
	}

	public List<TokenList> getAllTokenList(Package packageEntity, Process process) {
		List<TokenList> allTokenLists = new ArrayList<>();
		List<Class> classes = new ArrayList<>();
		classes.addAll(packageEntity.getClasses());
		
		for(Class classIterator : classes) {
			List<String> sourceCode = new ArrayList<>();
			sourceCode.addAll(classIterator.getSourceCode());
			String packageName = packageEntity.getName();
			String className = classIterator.getName();
			
			allTokenLists.add(getTokenList(sourceCode, process, packageName, className));
		}
		
		return allTokenLists;
	}

	
	@SuppressWarnings("unchecked")
	public TokenList getTokenList(List<String> wordList, Process process, String packageName, String className) {
		TokenList tokenList = new TokenList(packageName, className);
		
		Tokenizer tokenizer = new Tokenizer();
		
		List<String> tokenized = new ArrayList<>();
		tokenized = tokenizer.tokenize(wordList, process);

		for (String word : tokenized) {
			addTokenToTokenList(tokenList, word);
		}
		Collections.sort(tokenList);
		return tokenList;

	}

	private void addTokenToTokenList(TokenList tokenList, String word) {
		if (!tokenList.contains(word)) {
			Token token = new Token(word);
			tokenList.add(token);
		} else {
			tokenList.addQuantity(word);
		}
	}
}

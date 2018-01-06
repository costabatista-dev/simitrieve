/**
 * 
 */
package ml.paulobatista.simitrieve.similarity;


import java.util.ArrayList;
import java.util.List;

import ml.paulobatista.simitrieve.entity.CosineSimilarity;
import ml.paulobatista.simitrieve.entity.Token;
import ml.paulobatista.simitrieve.entity.TokenList;
import ml.paulobatista.simitrieve.entity.process.Normalization;
import ml.paulobatista.simitrieve.process.Process;
/**
 * @author Paulo Batista
 *
 */
public class SimilarityRetriever {


	public List<CosineSimilarity> getCosineSimilarities(List<TokenList> allTokenList, Process process) {
		Normalization normalization = process.getNormalization();
		if(normalization.equals(Normalization.NAIVE)) {
			return getCosineSimilarityNaiveNormalization(allTokenList);
		}
		else {
			//error threat.
		}
		return null;
	}
	
	private List<CosineSimilarity> getCosineSimilarityNaiveNormalization(List<TokenList> allTokenList) {
		List<CosineSimilarity> similarityList = new ArrayList<>();
		int size = allTokenList.size();
		
		for(int index = 0; index < size; index++) {
			for(int index2 = index + 1; index2 < size; index2++) {
				TokenList leftTokenList = allTokenList.get(index);
				TokenList rightTokenList = allTokenList.get(index2);
				similarityList.add(getCosineSimilarityNaiveNormalization(leftTokenList, rightTokenList));
			}
		}
		
		return similarityList;
	}
	
	private CosineSimilarity getCosineSimilarityNaiveNormalization(TokenList leftTokenList, TokenList rightTokenList) {
		
		List<String> wordList = getWordList(leftTokenList, rightTokenList);
		
		double[] leftArray = getArrayQuantity(leftTokenList, wordList);
		double[] rightArray = getArrayQuantity(rightTokenList, wordList);
		
		
		leftArray = getNaiveNormalizedLeftArray(leftArray, rightArray);
		rightArray = getNaiveNormalizedLeftArray(rightArray, leftArray);
		
		String firstClass = leftTokenList.getClassName();
		String secondClass = rightTokenList.getClassName();
		
		CosineSimilarity cosineSimilarity = new CosineSimilarity(firstClass, secondClass);
		
		double similarity = getCosineSimilarity(leftArray, rightArray);
		
		cosineSimilarity.setSimilarity(similarity);
		
		String firstPackage = leftTokenList.getPackageName();
		String secondPackage = rightTokenList.getPackageName();
		
		cosineSimilarity.setFirstPackage(firstPackage);
		cosineSimilarity.setSecondPackage(secondPackage);
		
		return cosineSimilarity;
	}
	
	
	private double[] getNaiveNormalizedLeftArray(double[] leftArray, double[] rightArray) {
		// error threat. if arrays have different size between them.
		
		int size = leftArray.length;
		double[] normalizedLeft = new double[size];
		double[] sum = new double[size];
		
		for(int index = 0; index < size; index++) {
			sum[index] = leftArray[index] + rightArray[index];
			normalizedLeft[index] = (double) (leftArray[index]/sum[index]);
		}
		
		return normalizedLeft;
	}
	
	private List<String> getWordList(TokenList leftTokenList, TokenList rightTokenList) {
		List<String> words = new ArrayList<>();
		String value = null;
		for(Token token : leftTokenList) {
			value = token.getValue();
			
			if(!words.contains(value)) {
				words.add(value);
			}
		}
		
		for(Token token : rightTokenList) {
			value = token.getValue();
			
			if(!words.contains(value)) {
				words.add(value);
			}
		}
		
		return words;
	}
	
	
	private double[] getArrayQuantity(TokenList tokenList, List<String> wordList) {
		int length = wordList.size();
		double[] arrayQuantity = new double[length];
		String word = null;
		
		for(int index = 0; index < length; index++) {
			word = wordList.get(index);
			
			if(tokenList.contains(word)) {
				arrayQuantity[index] = tokenList.getQuantity(word);
			}
			else {
				arrayQuantity[index] = 0.0;
			}
		}
		
		return arrayQuantity;
	}
	

	
	public double getCosineSimilarity(double[] leftArray, double[] rightArray) {
		double product = 0.0;
		
		double normalizedLefArray = 0.0, normalizedRightArray = 0.0;
		
		if (leftArray.length != rightArray.length) {
			// error threat.
		}
		
		int size = leftArray.length;
		
		for(int index = 0; index < size; index++) {
			product += (leftArray[index] * rightArray[index]);
			
			normalizedLefArray += Math.pow(leftArray[index], 2);
			normalizedRightArray += Math.pow(rightArray[index], 2);
		}
		
		double divisor = (Math.sqrt(normalizedLefArray) * Math.sqrt(normalizedRightArray));
		
		double cosine = (product/divisor);
		

		
		return cosine;
	}
	
	@SuppressWarnings("unused")
	private double truncate(double value) {
		double truncated = Math.round(value * 100) / 100d;
		
		return truncated;
	}
}

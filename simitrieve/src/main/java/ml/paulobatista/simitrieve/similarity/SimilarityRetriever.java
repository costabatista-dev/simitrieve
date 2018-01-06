/**
 * 
 */
package ml.paulobatista.simitrieve.similarity;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ml.paulobatista.simitrieve.entity.BagOfWords;
import ml.paulobatista.simitrieve.entity.CosineSimilarity;
import ml.paulobatista.simitrieve.entity.Token;
import ml.paulobatista.simitrieve.entity.TokenList;
import ml.paulobatista.simitrieve.entity.process.Normalization;
import ml.paulobatista.simitrieve.process.Process;
import ml.paulobatista.simitrieve.similarity.normalization.LSIManager;
import ml.paulobatista.simitrieve.similarity.normalization.TFIDFManager;
/**
 * @author Paulo Batista
 *
 */
public class SimilarityRetriever {


	public List<CosineSimilarity> getCosineSimilarities(List<TokenList> allTokenLists, Process process) {
		Normalization normalization = process.getNormalization();
		if(normalization.equals(Normalization.NAIVE)) {
			return getCosineSimilarityNaiveNormalization(allTokenLists);
		}
		else if(normalization.equals(Normalization.TFIDF)) {
			return getCosineSimilarityTFIDFNormalization(allTokenLists);
		}
		else if(normalization.equals(Normalization.LSI)) {
			return getCosineSimilarityLSINormalization(allTokenLists);
		}
		else {
			//error threat.
		}
		return null;
	}
	
	private List<CosineSimilarity> getCosineSimilarityLSINormalization(List<TokenList> allTokenLists) {
		BagOfWords bagOfWords = new BagOfWords(allTokenLists);
		List<String> classes = bagOfWords.getClasses();
		Map<String, String> packages = bagOfWords.getPackages();
		LSIManager lsiManager = new LSIManager();
		
		double[][] lsiValues = lsiManager.getLSI(bagOfWords);
		
		List<CosineSimilarity> similarities = new ArrayList<>();
		
		for(int index1 = 0; index1 < classes.size(); index1++) {
			for(int index2 = index1 + 1; index2 < classes.size(); index2++) {
				String firstClass = classes.get(index1);
				String secondClass = classes.get(index2);
				String firstPackage = packages.get(firstClass);
				String secondPackage = packages.get(secondClass);
				
				double[] firstArray = lsiManager.getColumnFromArray(lsiValues, index1);
				double[] secondArray = lsiManager.getColumnFromArray(lsiValues, index2);
				
				CosineSimilarity cosineSimilarity = new CosineSimilarity(firstClass, secondClass);
				
				cosineSimilarity.setFirstPackage(firstPackage);
				cosineSimilarity.setSecondPackage(secondPackage);
				
				setCosineSimilarity(cosineSimilarity, firstArray, secondArray);
				
				similarities.add(cosineSimilarity);
				
			}
		}
		
		return similarities;
	}
	
	private List<CosineSimilarity> getCosineSimilarityTFIDFNormalization(List<TokenList> allTokenLists) {
		BagOfWords bagOfWords = new BagOfWords(allTokenLists);
		List<String> classes = bagOfWords.getClasses();
		Map<String, String> packages = bagOfWords.getPackages();
		TFIDFManager tfidfManager = new TFIDFManager();
		
		double[][] tfidfValues = tfidfManager.getTFIDF(bagOfWords, allTokenLists);
		
		
		List<CosineSimilarity> similarities = new ArrayList<>();
		
		for(int index1 = 0; index1 < classes.size(); index1++) {
			for(int index2 = index1 + 1; index2 < classes.size(); index2++) {
				String firstClass = classes.get(index1);
				String secondClass = classes.get(index2);
				String firstPackage = packages.get(firstClass);
				String secondPackage = packages.get(secondClass);
				
				double[] firstArray = tfidfManager.getColumnFromArray(tfidfValues, index1);
				double[] secondArray = tfidfManager.getColumnFromArray(tfidfValues, index2);
				
				CosineSimilarity cosineSimilarity = new CosineSimilarity(firstClass, secondClass);
				cosineSimilarity.setFirstPackage(firstPackage);
				cosineSimilarity.setSecondPackage(secondPackage);
				
				setCosineSimilarity(cosineSimilarity, firstArray, secondArray);
				
				similarities.add(cosineSimilarity);
			}
		}
		
		return similarities;
		
	}
	
	private CosineSimilarity setCosineSimilarity(CosineSimilarity cosineSimilarity, double[] firstArray, double[] secondArray) {
		double similarity = getCosineSimilarity(firstArray, secondArray);
		
		cosineSimilarity.setSimilarity(similarity);
		return cosineSimilarity;
	}
	
	
	
	private List<CosineSimilarity> getCosineSimilarityNaiveNormalization(List<TokenList> allTokenLists) {
		List<CosineSimilarity> similarityList = new ArrayList<>();
		int size = allTokenLists.size();
		
		for(int index = 0; index < size; index++) {
			for(int index2 = index + 1; index2 < size; index2++) {
				TokenList leftTokenList = allTokenLists.get(index);
				TokenList rightTokenList = allTokenLists.get(index2);
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

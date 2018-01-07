/**
 * 
 */
package ml.paulobatista.simitrieve.filter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ml.paulobatista.simitrieve.entity.process.ProgrammingLanguage;
import ml.paulobatista.simitrieve.error.ErrorHandler;

/**
 * @author Paulo Batista
 *
 */

@SuppressWarnings("serial")
public class Dictionary extends ArrayList<String> {
	private ProgrammingLanguage programmingLanguage;

	public Dictionary(ProgrammingLanguage programmingLanguage) {
		this.programmingLanguage = programmingLanguage;
		loadStopwords();
	}

	public void setProgrammingLanguage(ProgrammingLanguage programmingLanguage) {
		this.programmingLanguage = programmingLanguage;
	}

	public ProgrammingLanguage getProgrammingLanguage() {
		return this.programmingLanguage;
	}

	private void loadStopwords() {
		String dictionary = null;
		if(ProgrammingLanguage.JAVA.equals(this.programmingLanguage)) {
			dictionary = "java.dic";
		}
		
		else if(ProgrammingLanguage.JAVASCRIPT.equals(this.programmingLanguage)) {
			dictionary = "javascript.dic";
		}
		
		else if(ProgrammingLanguage.PYTHON.equals(this.programmingLanguage)) {
			dictionary = "python.dic";
		}
		
		else if(ProgrammingLanguage.RUBY.equals(this.programmingLanguage)) {
			dictionary = "ruby.dic";
		}
		
		else if(ProgrammingLanguage.CPP.equals(this.programmingLanguage)) {
			dictionary = "cpp.dic";
		}
		
		else {
			System.out.println("In Dictionary language selection, in Dictionary:");
			ErrorHandler.unexpectedProjectProgrammingLanguage();
		}

		try {
			File file = new File(System.getProperty("user.dir") + File.separator + "dic" + File.separator + dictionary);

			FileReader reader = new FileReader(file);
			BufferedReader buffer = new BufferedReader(reader);
			
			List<String> words = new ArrayList<>();
			String line = null;

			while ((line = buffer.readLine()) != null && !line.matches("\\s+$")) {
				words.add(line.trim());
			}

			this.addAll(words);

			buffer.close();
			reader.close();
		} catch (IOException e) {
			// TODO: handle exception
			System.out.println("In Dictionary, loadStopwords method:");
			ErrorHandler.errorInDictionaryReading(e.getMessage());

		}
		
		if(this.isEmpty()) {
			System.out.println("In Dictionary, loadStopwords method:");
			ErrorHandler.errorInDictionaryReading("Dictionary content is empty");
		}
	}
	
	public List<String> removeStopwords(List<String> wordList) {
		List<String> filtered = new ArrayList<>();
		
		for(String word : wordList) {
			if(!this.contains(word)) {
				filtered.add(word);
			}
		}
		
		return filtered;
	}
}

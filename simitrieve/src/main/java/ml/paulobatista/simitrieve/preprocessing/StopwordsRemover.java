/**
 * 
 */
package ml.paulobatista.simitrieve.preprocessing;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import ml.paulobatista.simitrieve.entity.Language;
import ml.paulobatista.simitrieve.entity.ProgrammingFile;
import ml.paulobatista.simitrieve.entity.Project;

/**
 * @author paulo
 *
 */
public class StopwordsRemover {
	private static final String mainPath = "dic" + File.separator;
	private final String javadic;
	private final String cppdic;
	private final String jsdic;
	private final String pydic;
	private final String rbdic;

	public StopwordsRemover() {
		this.javadic = StopwordsRemover.mainPath + "java.dic";
		this.cppdic = StopwordsRemover.mainPath + "cpp.dic";
		this.jsdic = StopwordsRemover.mainPath + "javascript.dic";
		this.pydic = StopwordsRemover.mainPath + "python.dic";
		this.rbdic = StopwordsRemover.mainPath + "ruby.dic";
	}

	public String getJavadic() {
		return this.javadic;
	}

	public String getCppdic() {
		return this.cppdic;
	}

	public String getJsdic() {
		return this.jsdic;
	}

	public String getPydic() {
		return this.pydic;
	}

	public String getRbdic() {
		return this.rbdic;
	}

	private String getDicPath(String language) {
		if (language.equals("cpp")) {
			return this.cppdic;
		} else if (language.equals("java")) {
			return this.javadic;
		} else if (language.equals("js")) {
			return this.jsdic;
		} else if (language.equals("py")) {
			return this.pydic;
		} else if (language.equals("rb")) {
			return this.rbdic;
		} else {
			return null;
		}
	}

	private List<String> getDictionaryContent(String dicPath) {
		File f = new File(dicPath);
		List<String> content = null;
		try {
			 content = Files.readAllLines(f.toPath());
		}catch(IOException ioe) {
			ioe.getMessage();
		}
		return content;
	}

	public List<String> loadDictionary(Language language) {
		String dicPath = this.getDicPath(language.getLanguageCode());
		return this.getDictionaryContent(dicPath);
	}
	
	public void removeStopwords(Project project) {
		List<String> dictionary = this.loadDictionary(project.getLanguage());
		String content;
		for(ProgrammingFile pf : project) {
			content = pf.getSourceCode();
			for(String stpw : dictionary) {
				content = content.replaceAll(stpw, " ");
			}
			
			content = content.replaceAll("\\d", " ");
			pf.setSourceCode(content);
		}
	}
}

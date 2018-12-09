/**
 * 
 */
package ml.paulobatista.simitrieve.preprocessing;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ml.paulobatista.simitrieve.entity.project.Language;
import ml.paulobatista.simitrieve.entity.project.ProgrammingFile;
import ml.paulobatista.simitrieve.entity.project.Project;

/**
 * @author paulo
 *
 */
public class CommentRemover {
	private String removeStandardJavaComment(String sourceCode) {
		Pattern regex = Pattern.compile("(\\/\\/.*$)|(\\/\\*.*\\*\\/)", Pattern.DOTALL);
		Matcher regexMatcher = regex.matcher(sourceCode);

		sourceCode = regexMatcher.replaceAll(" ");

		return sourceCode;

	}

	public String removeJavaComment(String sourceCode) {
		return this.removeStandardJavaComment(sourceCode);
	}

	public String removeCppComment(String sourceCode) {
		return this.removeStandardJavaComment(sourceCode);
	}

	public String removeJsComments(String sourceCode) {
		return this.removeStandardJavaComment(sourceCode);
	}

	public String removePyComments(String sourceCode) {
		Pattern regex = Pattern.compile("(\"\"\".*\"\"\")|(\'\'\'.*\'\'\')|(#.*$)", Pattern.DOTALL);
		Matcher regexMatcher = regex.matcher(sourceCode);

		sourceCode = regexMatcher.replaceAll(" ");

		return sourceCode;
	}

	public String removeRbComments(String sourceCode) {
		Pattern regex = Pattern.compile("(=begin.*=end)|(#.*$)", Pattern.DOTALL);
		Matcher regexMatcher = regex.matcher(sourceCode);
		sourceCode = regexMatcher.replaceAll(" ");
		return sourceCode;
	}

	public void removeCommentFromFile(ProgrammingFile pfile, Language lng) {
		String sourceCode = pfile.getSourceCode();
		if (lng.equals(Language.CPP)) {
			sourceCode = this.removeCppComment(sourceCode);
		} else if (lng.equals(Language.JAVA)) {
			sourceCode = this.removeJavaComment(sourceCode);
		} else if (lng.equals(Language.JAVASCRIPT)) {
			sourceCode = this.removeJsComments(sourceCode);
		} else if (lng.equals(Language.PYTHON)) {
			sourceCode = this.removePyComments(sourceCode);
		} else if (lng.equals(Language.RUBY)) {
			sourceCode = this.removeRbComments(sourceCode);
		} else {
			throw new UnsupportedOperationException("Language not supported.");
		}

		pfile.setSourceCode(sourceCode);
	}

	public void removeComments(Project project) {
		Language language = project.getLanguage();

		project.forEach(programmingFile -> {
			this.removeCommentFromFile(programmingFile, language);
		});
	}
}

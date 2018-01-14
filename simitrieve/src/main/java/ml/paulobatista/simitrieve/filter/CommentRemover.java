/**
 * 
 */
package ml.paulobatista.simitrieve.filter;

import java.util.ArrayList;
import java.util.List;

import ml.paulobatista.simitrieve.entity.process.ProgrammingLanguage;

/**
 * @author costa
 *
 */
public class CommentRemover {

	public List<String> removeComments(List<String> sourceCode, ProgrammingLanguage programmingLanguage) {
		if (programmingLanguage.equals(ProgrammingLanguage.JAVA)) {
			return removeJavaComments(sourceCode);
		} else if (programmingLanguage.equals(ProgrammingLanguage.JAVASCRIPT)) {
			return removeJavascriptComments(sourceCode);
		} else if (programmingLanguage.equals(ProgrammingLanguage.CPP)) {
			return removeCppComents(sourceCode);
		} else if (programmingLanguage.equals(ProgrammingLanguage.PYTHON)) {
			return removePythonComments(sourceCode);
		} else if (programmingLanguage.equals(ProgrammingLanguage.RUBY)) {
			return removeRubyComments(sourceCode);
		} else {
			// error threats
		}

		return null;
	}

	public List<String> removeJavaComments(List<String> sourceCode) {

		List<String> filtered = new ArrayList<>();

		filtered = removeCommentJavaCppJavascriptPattern(sourceCode);

		return filtered;
	}

	public List<String> removePythonComments(List<String> sourceCode) {
		List<String> filtered = new ArrayList<>();

		int sizeOfCode = sourceCode.size();
		String inLineComment_1 = ".*#.*";
		String codeLine;
		String patternComment_1 = ".*'''.*";
		String patternComment_2 = ".*\"\"\".*";
		String inLineComment_2 = ".*'''.*'''.*";
		String inLineComment_3 = ".*\"\"\".*\"\"\".*";
		try {
			for (int index = 0; index < sizeOfCode; index++) {
				codeLine = sourceCode.get(index);

				if (codeLine.matches(inLineComment_1)) {
					codeLine = codeLine.replaceAll("#.*", "");
					filtered.add(codeLine);
				} else if (codeLine.matches(inLineComment_2)) {
					codeLine = codeLine.replaceAll("'''.*'''", "");
					filtered.add(codeLine);
				} else if (codeLine.matches(inLineComment_3)) {
					codeLine = codeLine.replaceAll("\"\"\".*\"\"\"", "");
					filtered.add(codeLine);
				} else if (codeLine.matches(patternComment_1)) {
					codeLine = codeLine.replaceAll("'''.*", "");
					filtered.add(codeLine);

					while (!codeLine.matches(patternComment_1) && index < sizeOfCode) {
						codeLine = sourceCode.get(index);
						index++;
					}

					codeLine = codeLine.replaceAll(".*'''", "");
					filtered.add(codeLine);
				} else if (codeLine.matches(patternComment_2)) {
					codeLine = codeLine.replaceAll("\"\"\".*", "");
					filtered.add(codeLine);

					while (!codeLine.matches(patternComment_2) && index < sizeOfCode) {
						codeLine = sourceCode.get(index);
						index++;
					}

					codeLine = codeLine.replaceAll(".*\"\"\"", "");

					filtered.add(codeLine);
				} else {
					filtered.add(codeLine);
				}
			}
		} catch (Exception e) {
			e.getMessage();
		}

		return filtered;

	}

	public List<String> removeRubyComments(List<String> sourceCode) {
		List<String> filtered = new ArrayList<>();
		String lineOfCode;

		for (int index = 0; index < sourceCode.size(); index++) {
			lineOfCode = sourceCode.get(index);

			if (lineOfCode.matches(".*=begin.*")) {

				lineOfCode = lineOfCode.replaceAll("=begin.*", "");
				filtered.add(lineOfCode);

				while (!lineOfCode.matches(".*=end.*")) {
					index++;
					lineOfCode = sourceCode.get(index);
				}
				lineOfCode = lineOfCode.replaceAll(".*=end", "");
				filtered.add(lineOfCode);
			} else {
				filtered.add(lineOfCode.replaceAll("#.*", ""));
			}

		}

		return filtered;
	}

	public List<String> removeCppComents(List<String> sourceCode) {
		List<String> filtered = new ArrayList<>();
		filtered = removeCommentJavaCppJavascriptPattern(sourceCode);

		return filtered;
	}

	public List<String> removeJavascriptComments(List<String> sourceCode) {

		List<String> filtered = new ArrayList<>();

		filtered = (removeCommentJavaCppJavascriptPattern(sourceCode));

		return filtered;
	}

	private List<String> removeCommentJavaCppJavascriptPattern(List<String> sourceCode) {
		List<String> filtered = null;
		filtered = new ArrayList<>();
		try {

			for (int i = 0; i < sourceCode.size(); i++) {
				String line = sourceCode.get(i);
				if (line.matches(".*//.*")) {
					if (line.split("//") != null) {
						line = line.split("//")[0];
					}

				} else if (line.matches(".+/\\*.*\\*/.+")) {
					if (line.split("/\\*.*\\*/")[0] != null && line.split("/\\*.*\\*/")[1] != null) {
						line = line.split("/\\*.*\\*/")[0] + " " + line.split("/\\*.*\\*/")[1];
					}

				} else if (line.matches("\\s*/\\*.*\\*/\\s*")) {
					line = "";
				} else if (line.matches(".+/\\*.*\\*/\\s*")) {
					if (line.split("/\\*.*\\*/\\s*")[0] != null) {
						line = line.split("/\\*.*\\*/\\s*")[0];
					}

				} else if (line.matches(".*/\\*.*")) {
					while (!line.matches(".*\\*/.*") && i < sourceCode.size()) {
						line = sourceCode.get(i);
						i++;
					}
					if (line.matches(".*\\*/.+")) {
						if (line.split(".*\\*/")[1] != null) {
							line = line.split(".*\\*/")[1];
						}
					}
				}
				filtered.add(line);
			}
		} catch (Exception e) {
			// System.out.println("In CommmentRemover:");
			// System.out.println(e.getCause());
			e.getStackTrace();
			// error threat
		}

		// if (filtered == null || filtered.isEmpty()) {
		// ErrorHandler.removeCommentsError();
		// }
		return filtered;
	}
}

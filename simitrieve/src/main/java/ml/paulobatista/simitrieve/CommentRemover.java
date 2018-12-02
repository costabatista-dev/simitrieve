/**
 * 
 */
package ml.paulobatista.simitrieve;

/**
 * @author paulo
 *
 */
public class CommentRemover {
	private String removeStandardJavaComment(String sourceCode) {
		StringBuilder codeWithoutComments = new StringBuilder();
		boolean continueCondition = true;
		for (int i = 0; i < sourceCode.length(); i++) {
			if (sourceCode.charAt(i) == '/' && sourceCode.charAt(i + 1) == '*') {
				while (continueCondition) {
					i++;
					if (sourceCode.charAt(i) == '*' && sourceCode.charAt(i + 1) == '/') {
						i += 2;
						continueCondition = false;
					}
				}
			}

			continueCondition = true;

			if (sourceCode.charAt(i) == '/' && sourceCode.charAt(i + 1) == '/') {
				while (continueCondition) {
					i++;
					if (sourceCode.charAt(i) == '\n') {
						i++;
						continueCondition = false;
					}
				}
			}

			continueCondition = true;

			codeWithoutComments.append(sourceCode.charAt(i));
		}

		return codeWithoutComments.toString();

	}

	public String removeJavaComment(String sourceCode) {
		return this.removeStandardJavaComment(sourceCode);
	}

	public String removeCppComment(String sourceCode) {
		return this.removeStandardJavaComment(sourceCode);
	}

	public String removeJsComments(String sourceCode) {
		return sourceCode;

	}

	public String removePyComments(String sourceCode) {
		StringBuilder withoutComments = new StringBuilder();
		boolean continueCondition = true;

		for (int i = 0; i < sourceCode.length(); i++) {
			if (sourceCode.charAt(i) == '#') {
				i++;
				while (sourceCode.charAt(i) != '\n') {
					i++;
				}
			}

			if (sourceCode.charAt(i) == '\'' && sourceCode.charAt(i + 1) == '\'' && sourceCode.charAt(i + 2) == '\'') {
				i += 3;
				while (continueCondition) {
					if (sourceCode.charAt(i) == '\'' && sourceCode.charAt(i + 1) == '\''
							&& sourceCode.charAt(i + 2) == '\'') {
						i += 3;
						continueCondition = false;
					}
					i++;
				}
			}

			continueCondition = true;

			if (sourceCode.charAt(i) == '\"' && sourceCode.charAt(i + 1) == '\"' && sourceCode.charAt(i + 2) == '\"') {
				i += 3;
				while (continueCondition) {
					if (sourceCode.charAt(i) == '\"' && sourceCode.charAt(i + 1) == '\"'
							&& sourceCode.charAt(i + 2) == '\"') {
						i += 3;
						continueCondition = false;
					}
					i++;
				}
			}

			continueCondition = true;
			withoutComments.append(sourceCode.charAt(i));
		}

		return withoutComments.toString();

	}
	
	public String removeRbComments(String sourceCode) {
		StringBuilder withoutComments = new StringBuilder();
		boolean continueCondition = true;
		
		for(int i = 0; i < sourceCode.length(); i++) {
			if(sourceCode.charAt(i) == '#') {
				i++;
				while(sourceCode.charAt(i) != '\n') {
					i++;
				}
				i++;
			}
			
			if(sourceCode.substring(i).startsWith("=begin")) {
				i += 6;
				while(continueCondition) {
					if(sourceCode.substring(i).startsWith("=end")) {
						continueCondition = false;
						i += 4;
					}
					i++;
 				}
			}
			
			continueCondition = true;
			withoutComments.append(sourceCode.charAt(i));
		}
		
		return withoutComments.toString();
	}
}
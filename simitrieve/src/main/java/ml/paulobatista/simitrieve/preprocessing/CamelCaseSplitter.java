package ml.paulobatista.simitrieve.preprocessing;

import ml.paulobatista.simitrieve.entity.ProgrammingFile;
import ml.paulobatista.simitrieve.entity.Project;

public class CamelCaseSplitter {
	private String splitTerm(String term) {
		String[] splittedTerms;
		splittedTerms = term.split("(?<=[a-z])(?=[A-Z])|(?<=[A-Z])(?=[A-Z][a-z])|_");
		
		StringBuilder builder = new StringBuilder();
		
		for(String t : splittedTerms) {
			builder.append(t);
			builder.append(" ");
		}

		return builder.toString();
	}
	
	private String lowerCasering(String sourceCode) {
		return sourceCode.toLowerCase();
	}
	
	public void splitCamelCase(Project project) {
		String content;
		
		for(ProgrammingFile pf : project) {
			content = pf.getSourceCode();
			content = this.splitTerm(content);
			content = this.lowerCasering(content);
			pf.setSourceCode(content);
		}
	}
}

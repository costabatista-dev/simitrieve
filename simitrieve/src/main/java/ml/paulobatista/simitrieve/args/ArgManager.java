/**
 * 
 */
package ml.paulobatista.simitrieve.args;

/**
 * @author costa
 *
 */
public class ArgManager {
	private boolean commentsUse;
	private boolean camelcaseDivision;
	private boolean stemTerms;
	private boolean removeLessFrequency;
	
	public ArgManager() {
		this.setDefaultValues();
	}
	
	public void setDefaultValues() {
		this.setCommentsUse(true);
		this.setCamelcaseDivision(false);
		this.setStemTerms(false);
		this.setRemoveLessFrequency(false);
	}
	
	private String getProjectPath(String[] args) {
		for(String s : args) {
			if(s.startsWith("--path")) return s.split(" ")[1];
			
			if(s.startsWith("-P")) return s.split(" ")[1];
		}
		return null;
	}
	
	private String getProjectLanguage(String[] args) {
		for(String s : args) {
			if(s.startsWith("--lang")) return s.split(" ")[1];
			if(s.startsWith("-L")) return s.split(" ")[1];
		}
		return null;
	}
	
	private String getCamelCaseUsing(String[] args) {
		for(String s : args) {
			if(s.startsWith("--camel")) return s.split(" ")[1];
			if(s.startsWith("-Ca")) return s.split(" ")[1];
		}
		
		return null;
	}
	
	private String getCommentsUsing(String[] args) {
		for(String s : args) {
			if(s.startsWith("--comment")) return s.split(" ")[1];
			if(s.startsWith("-Co")) return s.split(" ")[1];
		}
		
		return null;
	}
	
	private String getRemoveLessFrequency(String[] args) {
		for(String s : args) {
			if(s.startsWith("--lessfrequency")) return s.split(" ")[1];
			if(s.startsWith("-R")) return s.split(" ")[1];
		}
		
		return null;
	}
	
	private void setArguments() {
		
	}
	
	public void argumentsHandling(String[] args) {
		String path = this.getProjectPath(args);
		String lng = this.getProjectLanguage(args);
		String comments = this.getCommentsUsing(args);
		String camelCase = this.getCamelCaseUsing(args);
		String removeLessFrequency = this.getRemoveLessFrequency(args);
	}




	/**
	 * @return the camelcaseDivision
	 */
	public boolean isCamelcaseDivision() {
		return camelcaseDivision;
	}

	/**
	 * @param camelcaseDivision the camelcaseDivision to set
	 */
	public void setCamelcaseDivision(boolean camelcaseDivision) {
		this.camelcaseDivision = camelcaseDivision;
	}

	/**
	 * @return the commentsUse
	 */
	public boolean isCommentsUse() {
		return commentsUse;
	}

	/**
	 * @param commentsUse the commentsUse to set
	 */
	public void setCommentsUse(boolean commentsUse) {
		this.commentsUse = commentsUse;
	}

	/**
	 * @return the stemTerms
	 */
	public boolean isStemTerms() {
		return stemTerms;
	}

	/**
	 * @param stemTerms the stemTerms to set
	 */
	public void setStemTerms(boolean stemTerms) {
		this.stemTerms = stemTerms;
	}

	/**
	 * @return the removeLessFrequency
	 */
	public boolean isRemoveLessFrequency() {
		return removeLessFrequency;
	}

	/**
	 * @param removeLessFrequency the removeLessFrequency to set
	 */
	public void setRemoveLessFrequency(boolean removeLessFrequency) {
		this.removeLessFrequency = removeLessFrequency;
	}
}

package ml.paulobatista.simitrieve.args;

import java.io.File;

import ml.paulobatista.simitrieve.entity.Language;
import ml.paulobatista.simitrieve.entity.Quartile;

public class ArgumentManager {
	private String projectPath;
	private String projectName;
	private Language language;
	private boolean comments;
	private boolean camelcase;
	private float removeLessFrequencyPercent;

	public String getProjectPath() {
		return this.projectPath;
	}

	public void setProjectPath(String projectPath) {
		this.projectPath = projectPath;
	}

	public String getProjectName() {
		return this.projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public boolean getComments() {
		return this.comments;
	}

	public void setComments(boolean comments) {
		this.comments = comments;
	}

	public boolean getCamelCase() {
		return this.camelcase;
	}

	public void setCamelCase(boolean camelcase) {
		this.camelcase = camelcase;
	}

	public Language getLanguage() {
		return this.language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public float getRemoveLessFrequencyPercent() {
		return this.removeLessFrequencyPercent;
	}

	public void setRemoveLessFrequencyPercent(float percent) {
		this.removeLessFrequencyPercent = percent;
	}

	private String getStringArgumentProperty(String[] args, String fOption, String sOption) {
		for (int i = 0; i < args.length; i++) {
			if (args[i].equals(fOption) || args[i].equals(sOption))
				return args[i + 1];
		}

		return null;
	}

	private Boolean getBooleanArgumentProperty(String[] args, String fOption, String sOption) {
		for (int i = 0; i < args.length; i++) {
			if (args[i].equals(fOption) || args[i].equals(sOption)) {
				if (args[i + 1].equals("yes") || args[i + 1].equals("y"))
					return true;
				else if (args[i + 1].equals("no") || args[i + 1].equals("n"))
					return false;
			}
		}
		return null;
	}

	private String getProjectPath(String[] args) {
		return this.getStringArgumentProperty(args, "--path", "-P");
	}

	private String getProjectName(String[] args) {
		return this.getStringArgumentProperty(args, "--projectname", "-N");
	}

	private Language getProjectLanguage(String[] args) {
		String lng = this.getStringArgumentProperty(args, "--language", "-L");
		for (Language l : Language.values()) {
			if (l.getLanguageCode().equals(lng))
				return l;
		}
		return null;
	}

	private boolean getComments(String[] args) {
		return this.getBooleanArgumentProperty(args, "--comments", "-co");
	}

	private boolean getCamelCase(String[] args) {
		return this.getBooleanArgumentProperty(args, "--camelcase", "-ca");
	}

	private boolean isPercent(String arg) {
		return arg.matches("^([0-9]+)(\\.[0-9]*)?$");
	}

	private Double getRemoveLessFrequencyPercent(String[] args) {
		for (int i = 0; i < args.length; i++) {
			if (args[i].equals("-R") || args[i].equals("--removeLessFrequency")) {
				if (this.isPercent(args[i + 1]))
					return Double.parseDouble(args[i + 1]);
				else if (args[i + 1].equals("1qt"))
					return (double) Quartile.FIRST.getPercent();
				else if (args[i + 1].equals("2qt"))
					return (double) Quartile.SECOND.getPercent();
				else if (args[i + 1].equals("3qt"))
					return (double) Quartile.THIRD.getPercent();
			}
		}
		return null;
	}

	private void setPreprocessingValues(String[] args) {
		this.setCamelCase(this.getCamelCase(args));
		this.setComments(this.getComments(args));
		this.setRemoveLessFrequencyPercent(this.getRemoveLessFrequencyPercent(args).floatValue());
	}
	
	private String getDefaultProjectName(String[] args) {
		if(this.projectName == null) {
			System.out.println("chegou aqui");
			String path = this.projectPath;
			int index = path.lastIndexOf(File.separator);
			
			if(index != path.length() - 1) return path.substring(index+1);
			else {
				path = path.substring(0, path.length() - 1);
				index = path.lastIndexOf(File.separator) + 1;
				return path.substring(index);
			}
			
		}
		else return this.projectName;
	}

	private void setDefaultProcessValues(String args[]) {
		this.setProjectName(this.getDefaultProjectName(args));
	}
	
	private void setProcessValues(String[] args) {
		this.setProjectPath(this.getProjectPath(args));
		this.setProjectName(this.getProjectName(args));
		this.setLanguage(this.getProjectLanguage(args));

	}
	
	public void applyProcessSettings(String[] args) {
		this.setProcessValues(args);
		this.setDefaultProcessValues(args);
		this.setPreprocessingValues(args);
	}
}

package ml.paulobatista.simitrieve.args;

import java.io.File;

import ml.paulobatista.simitrieve.entity.project.Language;
import ml.paulobatista.simitrieve.entity.project.Project;
import ml.paulobatista.simitrieve.entity.project.Quartile;
import ml.paulobatista.simitrieve.preprocessing.Preprocessor;
import ml.paulobatista.simitrieve.preprocessing.read.ProjectReader;

public class ArgumentManager {
	private String projectPath;
	private String projectName;
	private String version;
	private String outputFileName;
	private Language language;
	private boolean comments;
	private boolean camelcase;
	private boolean stemming;
	private float removeLessFrequencyPercent;
	private String[] extensionsFile;

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
	
	public String getOutputFileName() {
		return this.outputFileName;
	}
	
	public void setOutputFileName(String outputFileName) {
		this.outputFileName = outputFileName;
	}

	public String getVersion() {
		return this.version;
	}

	public void setVersion(String version) {
		this.version = version;
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
	
	public boolean getStemming() {
		return this.stemming;
	}
	
	public void setStemming(boolean stemming) {
		this.stemming = stemming;
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

	public String[] getExtensionsFile() {
		return this.extensionsFile;
	}

	public void setExtenstionsFile(String[] extensionsFile) {
		this.extensionsFile = extensionsFile;
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
				if (args[i + 1].trim().equals("yes") || args[i + 1].trim().equals("y")) {
					return true;
				}
				else if (args[i + 1].trim().equals("no") || args[i + 1].trim().equals("n")) {
					
					return false;
				}
					
			}
		}
		return null;
	}

	private String getOutputFileName(String[] args) {
		return this.getStringArgumentProperty(args, "-O", "--outputfilename");
	}
	
	private String getProjectPath(String[] args) {
		return this.getStringArgumentProperty(args, "--path", "-P");
	}

	private String getProjectName(String[] args) {
		String name = this.getStringArgumentProperty(args, "--projectname", "-N");
		if(name == null) {
			name = this.getDefaultProjectName(args);
		}
		return name;
	}

	private String getVersion(String[] args) {
		return this.getStringArgumentProperty(args, "--version", "-V");
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
		Boolean comments = this.getBooleanArgumentProperty(args, "--comments", "-co");
		if(comments == null) {
			return true;
		}
		return comments;
	}

	private boolean getCamelCase(String[] args) {
		Boolean camelcase = this.getBooleanArgumentProperty(args, "--camelcase", "-ca");
		if(camelcase == null) {
			return false;
		}
		return camelcase;
	}
	
	private boolean getStemming(String[] args) {
		Boolean stemming = this.getBooleanArgumentProperty(args, "--stemming", "-S");
		if(stemming == null) {
			return false;
		}
		return stemming;
	}
	

	private String[] getExtensionsFileFromLanguage() {
		if (this.language.equals(Language.CPP))
			return new String[] { ".cpp", ".cc", ".h" };
		if (this.language.equals(Language.JAVA))
			return new String[] { "java" };
		if (this.language.equals(Language.JAVASCRIPT))
			return new String[] { ".js" };
		if (this.language.equals(Language.PYTHON))
			return new String[] { ".py" };
		if (this.language.equals(Language.RUBY))
			return new String[] { ".rb" };
		return null;
	}

	private boolean isPercent(String arg) {
		return arg.matches("^([0-9]+)(\\.[0-9]*)?$");
	}

	private Double getRemoveLessFrequencyPercent(String[] args) {
		for (int i = 0; i < args.length; i++) {
			if (args[i].equals("-R") || args[i].equals("--removeLessFrequency")) {
				if (this.isPercent(args[i + 1])) {
					return Double.parseDouble(args[i + 1]);
				}
				else if (args[i + 1].equals("1qt"))
					return (double) Quartile.FIRST.getPercent();
				else if (args[i + 1].equals("2qt"))
					return (double) Quartile.SECOND.getPercent();
				else if (args[i + 1].equals("3qt"))
					return (double) Quartile.THIRD.getPercent();
			}
		}
		return 0.0;
	}

	private void setPreprocessingValues(String[] args) {
		this.setCamelCase(this.getCamelCase(args));
		this.setComments(this.getComments(args));
		this.setRemoveLessFrequencyPercent(this.getRemoveLessFrequencyPercent(args).floatValue());
		this.setStemming(this.getStemming(args));
	}

	private String getDefaultProjectName(String[] args) {
		if (this.projectName == null) {
			String path = this.projectPath;
			int index = path.lastIndexOf(File.separator);

			if (index != path.length() - 1)
				return path.substring(index + 1);
			else {
				path = path.substring(0, path.length() - 1);
				index = path.lastIndexOf(File.separator) + 1;
				return path.substring(index);
			}

		} else
			return this.projectName;
	}

	
	private void setProcessValues(String[] args) {
		this.setProjectPath(this.getProjectPath(args));
		this.setProjectName(this.getProjectName(args));
		this.setLanguage(this.getProjectLanguage(args));
		this.setExtenstionsFile(this.getExtensionsFileFromLanguage());
		this.setVersion(this.getVersion(args));
		this.setOutputFileName(this.getOutputFileName(args));
	}

	public void applyProcessSettings(String[] args) {
		this.setProcessValues(args);
		this.setPreprocessingValues(args);
	}

	private void executePreprocessing(Project project) {
		Preprocessor preprocessor = new Preprocessor();

		if (!this.comments) preprocessor.removeComments(project);
		preprocessor.removeSpecialCharacters(project);
		preprocessor.removeStopwords(project);
		if(this.camelcase) preprocessor.splitCamelCase(project);
		preprocessor.removeNonConceptWords(project);
		if(this.stemming) preprocessor.stemming(project);
		preprocessor.tokenize(project);
		if (this.getRemoveLessFrequencyPercent() != 0)
			preprocessor.removeLessFrequencyTerms(project, this.removeLessFrequencyPercent);

	}

	public Project execute(String[] args) {
		this.applyProcessSettings(args);
		ProjectReader reader = new ProjectReader();
		Project project = reader.getProject(this.projectPath, this.extensionsFile);
		project.setLanguage(this.language);
		project.setName(this.getProjectName());
		this.executePreprocessing(project);

		return project;
	}
}

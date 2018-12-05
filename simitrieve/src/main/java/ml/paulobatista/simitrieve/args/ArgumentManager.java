package ml.paulobatista.simitrieve.args;

public class ArgumentManager {
	private String projectPath;
	private String projectName;

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
	
	private String getStringArgumentProperty(String[] args, String fOption, String sOption) {
		for(int i = 0; i < args.length; i++) {
			if(args[i].equals(fOption) || args[i].equals(sOption)) return args[i+1];
		}
		
		return null;
	}
	
	private String getProjectPath(String[] args) {
		return this.getStringArgumentProperty(args, "--path", "-P");
	}
	
	private String getProjectName(String[] args) {
		return this.getStringArgumentProperty(args, "--projectname", "-N");
	}

	public void setDefaultPreprocessingValues(String[] args) {
		//this.setProjectPath(this.getProjectPath(args));
	}
	
	public void setProcessValues(String args[]) {
		this.setProjectPath(this.getProjectPath(args));
		this.setProjectName(this.getProjectName(args));
	}
	
	public void execute(String[] args) {
		this.setProcessValues(args);
	}
}

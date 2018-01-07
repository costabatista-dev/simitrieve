/**
 * 
 */
package ml.paulobatista.simitrieve.cmd;

import ml.paulobatista.simitrieve.entity.process.CamelCase;
import ml.paulobatista.simitrieve.entity.process.Comment;
import ml.paulobatista.simitrieve.entity.process.Normalization;
import ml.paulobatista.simitrieve.entity.process.ProgrammingLanguage;
import ml.paulobatista.simitrieve.entity.process.Quantile;
import ml.paulobatista.simitrieve.entity.process.Stem;
import ml.paulobatista.simitrieve.process.Process;

/**
 * @author Paulo Batista
 *
 */
public class CMDManager {
	private String programmingLanguage;
	private String projectName;
	private String projectPath;
	private String projectVersion;
	private String comments;
	private String camelCase;
	private String stemming;
	private String quantile;
	private String normalization;

	
	public CMDManager(String [] args) {
		if(args[0].equals("similarity")) {
			this.projectPath = args[1];
			this.projectName = args[2];
			this.projectVersion = args[3];
			this.programmingLanguage = args[4];
			this.comments = args[5];
			this.camelCase = args[6];
			this.stemming = args[7];
			this.quantile = args[8];
			this.normalization = args[9];
		}
		else if(args[0].equals("feature")) {
			this.projectPath = args[1];
			this.projectName = args[2];
			this.projectVersion = args[3];
			this.programmingLanguage = args[4];
		}
	}
	
	public Process getProcess() {
		Process process = new Process();
		process.setComment(getProcessComments());
		process.setCamelCase(getProcessCamelCase());
		process.setStem(getProcessStemming());
		process.setQuantile(getProcessQuantile());
		process.setNormalization(getProcessNormalization());
		process.setProgrammingLanguage(getProcessProgrammingLanguage());
		
		return process;
	}

	private ProgrammingLanguage getProcessProgrammingLanguage() {
		if(this.programmingLanguage.equals("java")) {
			return ProgrammingLanguage.JAVA;
		}
		else if(this.programmingLanguage.equals("javascript")) {
			return ProgrammingLanguage.JAVASCRIPT;
		}
		else if(this.programmingLanguage.equals("python")) {
			return ProgrammingLanguage.PYTHON;
		}
		else if(this.programmingLanguage.equals("ruby")) {
			return ProgrammingLanguage.RUBY;
		}
		else if(this.programmingLanguage.equals("cpp")) {
			return ProgrammingLanguage.CPP;
		}
		else {
			//error threatment.
			return null;
		}
	}
	private Comment getProcessComments() {
		if (this.comments.equals("yco")) {
			return Comment.YES;
		}
		else if(this.comments.equals("nco")) {
			return Comment.NO;
		}
		else  {
			//error threat.
			return null;
		}
	}

	private CamelCase getProcessCamelCase() {
		if(this.camelCase.equals("yca")) {
			return CamelCase.YES;
		}
		else if(this.camelCase.equals("nca")) {
			return CamelCase.NO;
		}
		else {
			//error threat.
			return null;
		}
	}
	
	private Quantile getProcessQuantile() {
		if(this.quantile.equals("qfi")) {
			return Quantile.FIRST;
		}
		else if(this.quantile.equals("qse")) {
			return Quantile.SECOND;
		}
		else if(this.quantile.equals("qth")) {
			return Quantile.THIRD;
		}
		else if(this.quantile.equals("qfo")) {
			return Quantile.FOURTH;
		}
		else {
			// error threat
			return null;
		}
	}
	
	private Normalization getProcessNormalization() {
		if(this.normalization.equals("naive")) {
			return Normalization.NAIVE;
		}
		else if(this.normalization.equals("lsi")) {
			return Normalization.LSI;
		}
		else if(this.normalization.equals("tfidf")) {
			return Normalization.TFIDF;
		}
		else {
			//error threat.
			return null;
		}
	}
	
	private Stem getProcessStemming() {
		if(this.stemming.equals("ys")) {
			return Stem.YES;
		}
		else if(this.stemming.equals("ns")) {
			return Stem.NO;
		}
		else {
			//error threat.
			return null;
		}
		
	}

	public String getProgrammingLanguage() {
		return programmingLanguage;
	}

	public void setProgrammingLanguage(String programmingLanguage) {
		this.programmingLanguage = programmingLanguage;
	}

	public String getProjectPath() {
		return projectPath;
	}

	public void setProjectPath(String projectPath) {
		this.projectPath = projectPath;
	}

	public String getProjectVersion() {
		return projectVersion;
	}

	public void setProjectVersion(String projectVersion) {
		// error threat.
		this.projectVersion = projectVersion;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		// error threat.
		this.comments = comments;
	}

	public String getCamelCase() {
		// error threat.
		return camelCase;
	}

	public void setCamelCase(String camelCase) {
		// error threat.
		this.camelCase = camelCase;
	}

	public String getStemming() {
		return stemming;
	}

	public void setStemming(String stemming) {
		// error threat.
		this.stemming = stemming;
	}

	public String getQuantile() {
		return quantile;
	}

	public void setQuantile(String quantile) {
		// error threat.
		this.quantile = quantile;
	}

	public String getNormalization() {
		return normalization;
	}

	public void setNormalization(String normalization) {
		// error threat.
		this.normalization = normalization;
	}
	
	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

}

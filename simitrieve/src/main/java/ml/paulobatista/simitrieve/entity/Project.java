package ml.paulobatista.simitrieve.entity;

import java.util.List;

import ml.paulobatista.simitrieve.entity.process.ProgrammingLanguage;

public class Project {
	private String name; 
	private String version;
	private ProgrammingLanguage programmingLanguage;
	private List<Package> packages;
	
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public ProgrammingLanguage getProgrammingLanguage() {
		return programmingLanguage;
	}
	
	public void setProgrammingLanguage(ProgrammingLanguage programmingLanguage) {
		this.programmingLanguage = programmingLanguage;
	}
	
	public List<Package> getPackages() {
		return packages;
	}
	
	public void setPackages(List<Package> packages) {
		this.packages = packages;
	}
	
	public void setVersion(String version) {
		this.version = version;
	}
	
	public String getVersion() {
		return this.version;
	}
}

package ml.paulobatista.simitrieve.entity;

import java.util.List;

public class Package {
	private String name;
	private List<Class> classes;
	
	public Package() {
		
	}
	
	public Package(String name, List<Class> classes) {
		setName(name);
		setClasses(classes);
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public List<Class> getClasses() {
		return classes;
	}
	
	public void setClasses(List<Class> classes) {
		this.classes = classes;
	}
}

package database.entities;

import java.io.Serializable;

public class ProjectData implements Serializable {
	
private static final long serialVersionUID = 1L;
	
	private Long id;
	private String projectName;
	private String fte;
	private int capacity;
	private String projectData;
	
	
	
	public ProjectData() { }
	
	public ProjectData(Long id, String projectName) {
		super();
		this.id = id;
		this.projectName = projectName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getFte() {
		return fte;
	}

	public void setFte(String fte) {
		this.fte = fte;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public String getProjectData() {
		return projectData;
	}

	public void setProjectData(String projectData) {
		this.projectData = projectData;
	}
	
	
	

}

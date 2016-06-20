package database.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class ProjectFake implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<ProjectData> projects;
	private String console;
	   
    private String project;  
    //private List<SelectItem> cars;
	
	@PostConstruct
	public void init() {
		projects = new ArrayList<ProjectData>();
		projects.add(new ProjectData(1L, "Connect"));
		projects.add(new ProjectData(2L, "SOM"));

	}

	public List<ProjectData> getProjects() {
		return projects;
	}

	public void setProjects(List<ProjectData> projects) {
		this.projects = projects;
	}

	
}

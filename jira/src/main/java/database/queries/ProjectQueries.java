package database.queries;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;


import contollers.ProjectController;

@Named
@SessionScoped
public class ProjectQueries implements Serializable {

/*	private static final long serialVersionUID = 1L;

	private List<Project> projects;

	public ProjectQueries() {
		projects = new ArrayList<Project>();
		projects.add(new Project(1L, "Connect"));
		projects.add(new Project(2L, "SOM"));
	
	}

	public Project find(long id) {
		for (Project project : projects) {
			if (project.getId().equals(id)) {
				return project;
			}
		}
		return null;
	}

	public Project find(String name) {
		for (Project project : projects) {
			if (project.getProjectName().equals(name)) {
				return project;
			}
		}
		return null;
	}

	public void save(Project project) {
		projects.add(project);
	}*/
}

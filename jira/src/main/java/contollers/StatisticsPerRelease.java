/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contollers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import jpaContollers.MainController;
import org.primefaces.model.chart.PieChartModel;
import pojo.Project;
import pojo.Release;
import pojo.Sprint;

/**
 *
 * @author Administrator
 */
@Named
@SessionScoped
public class StatisticsPerRelease implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @EJB
    MainController mainController;
    
    @Inject
    SessionController sessionController;
    
    private List<Project> projects;
    
    private Project projectSelected;
    private Release releaseSelected;
    
    private Long projectSelectedId;
    private Long releaseSelectedId;
    
    private PieChartModel pieModel1;
    
    @PostConstruct
    public void init() {
        projectSelected = new Project();
        releaseSelected = new Release();
        projects = mainController.getAllProjects();
        
        pieModel1 = new PieChartModel();
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }
    
    public Project getProjectById(Long id, List<Project> projects) {
        for (Project p : projects) {
            if (p.getId().equals(id)) {
                return p;
            }
        }
        return null;
    }
    
    
     public void setProjectSelectedId(Long projectSelectedId) {
        projectSelected = getProjectById(projectSelectedId, projects);
        releaseSelectedId = null;        
        releaseSelected = new Release();     
        this.projectSelectedId = projectSelectedId;
    }
     
     public Release getReleasesByReleaseId(Long idRelease, List<Release> releases) {
        for (Release r : releases) {
            if (r.getId().equals(idRelease)) {
                return r;
            }
        }
        return null;

    }
     
     public void setReleaseSelectedId(Long releaseSelectedId) {
        releaseSelected = getReleasesByReleaseId(releaseSelectedId, projectSelected.getReleases());       
        this.releaseSelectedId = releaseSelectedId;
        
    }
     
     public Project getProjectSelected() {
        return projectSelected;
    }

    public Release getReleaseSelected() {
        return releaseSelected;
    }
    
    public Long getProjectSelectedId() {
        return projectSelectedId;
    }
  
    
    public Long getReleaseSelectedId() {
        return releaseSelectedId;
    }
    
   
    
    public Sprint getSprintByName(List<Sprint> sprints, String nameGiven) {
        for (Sprint s : sprints) {
            if (s.getName().equals(nameGiven)) {
                return s;
            }
        }
        return null;
    }

    public void createPieModel1() {
        
        List<Sprint> sprintsPerRelease =  releaseSelected.getSprints();
        
        pieModel1 = new PieChartModel();
         
        
        List<Sprint> sprintTotal = new ArrayList<>();
        for (Sprint s : sprintsPerRelease) {
            Sprint sprintFound = getSprintByName(sprintTotal, s.getName());
            if (sprintFound != null) {
                sprintFound.setCapacity((Long.valueOf(sprintFound.getCapacity()) + Long.valueOf(s.getCapacity()).toString()));

            } else {
                sprintTotal.add(s);
            }
        }
        
        for (Sprint s: sprintTotal){        
            pieModel1.set(s.getName(), Long.valueOf(s.getCapacity()));
        }     
         
       
        
         
        pieModel1.setTitle(projectSelected.getName() + " " + releaseSelected.getName());
        pieModel1.setLegendPosition("w");
       
    }

    public PieChartModel getPieModel1() {
        return pieModel1;
    }
    
    public boolean isReleaseSelectedIdCompleted(){
        return releaseSelectedId!=null;
    }
    
}

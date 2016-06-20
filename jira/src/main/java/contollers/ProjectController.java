package contollers;


import pojo.Account;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;
import pojo.Project;
import pojo.Release;
import pojo.Sprint;
import javax.faces.bean.ManagedBean;
import jpaContollers.MainController;

@Named
@SessionScoped
public class ProjectController implements Serializable {

    private static final long serialVersionUID = 1L;

    private String console;

    private Long projectSelectedId;
    private Long releaseSelectedId;
    private Long sprintSelectedId;

    private Project projectSelected;
    private Release releaseSelected;
    private Sprint sprintSelected;

    //private List<SelectItem> projects;
    private List<Project> projects;
 

    
    @EJB
    MainController mainController;
    

    @PostConstruct
    public void init(){
        //projects
        projectSelected = new Project();
        releaseSelected = new Release();
        sprintSelected = new Sprint();

        projects = mainController.getAllProjects();
       
//        
    }

    public String getConsole() {
        return console;
    }

    public void setConsole(String console) {
        this.console = console;
    }

//    public Project getProjectSelected() {
//        return projectSelected;
//    }
//
//    public void setProjectSelected(Project projectSelected) {
//        this.projectSelected = projectSelected;
//    }
    
    public void setProjectByType(Account user){
        if (!(user.getRole().equals("ADMIN"))){
            this.projects = user.getAccountProjects();
        }
    }
    
    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

 

//    public Release getReleaseSelected() {
//        return releaseSelected;
//    }
//
//    public void setReleaseSelected(Release releaseSelected) {
//        this.releaseSelected = releaseSelected;
//    }
//
//    public Sprint getSprintSelected() {
//        return sprintSelected;
//    }
//
//    public void setSprintSelected(Sprint sprintSelected) {
//        this.sprintSelected = sprintSelected;
//    }
    public Long getProjectSelectedId() {
        return projectSelectedId;
    }  
    
    public void setProjectSelectedId(Long projectSelectedId) {     
        projectSelected = projects.get(Integer.valueOf(projectSelectedId.toString()));
        releaseSelectedId = null;
        sprintSelectedId = null;
        releaseSelected = new Release();
        sprintSelected = new Sprint();
        this.projectSelectedId = projectSelectedId;
    }

    public Long getReleaseSelectedId() {
        return releaseSelectedId;
    }

    public void setReleaseSelectedId(Long releaseSelectedId) {
        releaseSelected = projectSelected.getReleases().get(Integer.valueOf(releaseSelectedId.toString()));
        sprintSelectedId = null;
        sprintSelected = new Sprint();
        this.releaseSelectedId = releaseSelectedId;
    }

    public Long getSprintSelectedId() {
        return sprintSelectedId;
    }

    public void setSprintSelectedId(Long sprintSelectedId) {
        sprintSelected = releaseSelected.getSprints().get(Integer.valueOf(sprintSelectedId.toString()));
        this.sprintSelectedId = sprintSelectedId;
    }

    public Project getProjectSelected() {
        return projectSelected;
    }

    public Release getReleaseSelected() {
        return releaseSelected;
    }

    public Sprint getSprintSelected() {
        return sprintSelected;
    }
    
  
     

}

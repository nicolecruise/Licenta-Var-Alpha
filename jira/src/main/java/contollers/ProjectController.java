package contollers;


import pojo.Account;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import pojo.Project;
import pojo.Release;
import pojo.Sprint;
import javax.inject.Inject;
import jpaContollers.MainController;
import pojo.RowRaport;

@Named
@SessionScoped
public class ProjectController implements Serializable {

    private static final long serialVersionUID = 1L;

  

    private Long projectSelectedId;
    private Long releaseSelectedId;
    private Long sprintSelectedId;

    private Project projectSelected;
    private Release releaseSelected;
    private Sprint sprintSelected;

    //private List<SelectItem> projects;
    private List<Project> projects;
    
    private List<String> projectsSelectedIds;
    
    
    private List<Project> allProjects;
    
    private List<String> allReleases;
    
    private List<String> releasesSelected;
    
    private List<RowRaport> rowsRaport;
    
    
 

    
    @EJB
    MainController mainController;
    @Inject
    SessionController sessionController;

    @PostConstruct
    public void init(){
        //projects
        projectSelected = new Project();
        releaseSelected = new Release();
        sprintSelected = new Sprint();
        
        projectsSelectedIds = new ArrayList<>();
        
        allProjects = mainController.getAllProjects();

        projects = mainController.getAllProjects();
        
        setProjectByType(sessionController.getUser());
        
        allReleases = new ArrayList<>();
        allReleases.add("R1");
        allReleases.add("R2");
        allReleases.add("R3");
        allReleases.add("R4");
        allReleases.add("R5");
        allReleases.add("R6");
        
        rowsRaport = new ArrayList<>();
        
        
       
       
    }   
    
    
    
    
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
    
    
  public void updateSprint(){
      mainController.updatecapacityForSprintSelected(sprintSelected);
  
  }

    public List<String> getProjectsSelectedIds() {
        return projectsSelectedIds;
    }

    public void setProjectsSelectedIds(List<String> projectsSelectedIds) {
        this.projectsSelectedIds = projectsSelectedIds;
    }

    public List<Project> getAllProjects() {
        return allProjects;
    }

    public void setAllProjects(List<Project> allProjects) {
        this.allProjects = allProjects;
    }

    public List<String> getAllReleases() {
        return allReleases;
    }

    public void setAllReleases(List<String> allReleases) {
        this.allReleases = allReleases;
    }

    public List<String> getReleasesSelected() {
        return releasesSelected;
    }

    public void setReleasesSelected(List<String> releasesSelected) {
        this.releasesSelected = releasesSelected;
    }
  
  
     

}

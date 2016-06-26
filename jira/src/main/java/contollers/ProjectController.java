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
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.HorizontalBarChartModel;
import pojo.RowRaport;
import pojo.RowTotalPerRelease;

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

    private List<Project> projects;

    private List<String> projectsSelectedIds;
    private List<String> releasesSelectedIds;

    private List<Project> allProjects;

    private List<Release> allReleases;

    private List<String> releasesSelected;

    private List<RowRaport> rowsRaport;

    private List<RowTotalPerRelease> rowsTotalPerRelease;

    @EJB
    MainController mainController;
    @Inject
    SessionController sessionController;
    
    @Inject
    StatisticsPerRelease statisticsPerRelease;

    private BarChartModel barModel;
    

    @PostConstruct
    public void init() {
        //projects
        projectSelected = new Project();
        releaseSelected = new Release();
        sprintSelected = new Sprint();
        projects = new ArrayList<>();

        projectsSelectedIds = new ArrayList<>();
        releasesSelectedIds = new ArrayList<>();

        allProjects = mainController.getAllProjects();

        projects = mainController.getAllProjects();

        setProjectByType(sessionController.getUser());

        allReleases = mainController.getAllReleases();

        rowsRaport = new ArrayList<>();
        rowsTotalPerRelease = new ArrayList<>();

        barModel = new BarChartModel();

    }

    
    private void createBarModel() {
        barModel = initBarModel();

        barModel.setTitle("Total story points per Release-Preject");
        barModel.setLegendPosition("ne");

        Axis xAxis = barModel.getAxis(AxisType.X);
        xAxis.setLabel("Project-Release");

        Axis yAxis = barModel.getAxis(AxisType.Y);
        yAxis.setLabel("Total story points");
        yAxis.setMin(0);
        yAxis.setMax(999);
    }

    private BarChartModel initBarModel() {
        BarChartModel model = new BarChartModel();
 
        ChartSeries statistics = new ChartSeries();
        statistics.setLabel("Total story points");
        
        for(RowTotalPerRelease total: rowsTotalPerRelease){
            Long totalSp = Long.valueOf(total.getTotalPerRelease());
            statistics.set(total.getProjectName() + " " + total.getReleaseName() + " " + total.getReleaseYear(), totalSp);
        }
        
        model.addSeries(statistics);
     
         
        return model;
    }

    public BarChartModel getBarModel() {
        return barModel;
    }

   

    public void setProjectByType(Account user) {
        if (user != null && user.getRole() != null) {
            if (!(user.getRole().equals("ADMIN"))) {
                this.projects = user.getAccountProjects();
            }
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
        sprintSelectedId = null;
        releaseSelected = new Release();
        sprintSelected = new Sprint();
        this.projectSelectedId = projectSelectedId;
    }

    public Long getReleaseSelectedId() {
        return releaseSelectedId;
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
        sprintSelectedId = null;
        sprintSelected = new Sprint();
        this.releaseSelectedId = releaseSelectedId;
    }

    public Long getSprintSelectedId() {
        return sprintSelectedId;
    }

    public Sprint getSprintBySprintId(Long idSprint, List<Sprint> sprints) {
        for (Sprint s : sprints) {
            if (s.getId().equals(idSprint)) {
                return s;
            }
        }
        return null;

    }

    public void setSprintSelectedId(Long sprintSelectedId) {
        sprintSelected = getSprintBySprintId(sprintSelectedId, releaseSelected.getSprints());
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

    public void updateSprint() {

        mainController.updatecapacityForSprintSelected(mainController.getProjectReleaseIdByIdProjectAndIdIdRelease(projectSelected.getId(), releaseSelected.getId()), sprintSelected);

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

    public List<Release> getAllReleases() {
        return allReleases;
    }

    public void setAllReleases(List<Release> allReleases) {
        this.allReleases = allReleases;
    }

    public List<String> getReleasesSelected() {
        return releasesSelected;
    }

    public void setReleasesSelected(List<String> releasesSelected) {
        this.releasesSelected = releasesSelected;
    }

    public List<String> getReleasesSelectedIds() {
        return releasesSelectedIds;
    }

    public void setReleasesSelectedIds(List<String> releasesSelectedIds) {
        this.releasesSelectedIds = releasesSelectedIds;
    }

    public List<RowRaport> getRowsRaport() {
        return rowsRaport;
    }

    public void setRowsRaport(List<RowRaport> rowsRaport) {

        this.rowsRaport = mainController.getRowRaport(projectsSelectedIds, releasesSelectedIds);

        this.rowsTotalPerRelease = mainController.getProjectRelesesPerReleaseFromStoryPointsSelected(projectsSelectedIds, releasesSelectedIds);
    }

    public void setRows() {
        

        this.rowsRaport = mainController.getRowRaport(projectsSelectedIds, releasesSelectedIds);

        this.rowsTotalPerRelease = mainController.getProjectRelesesPerReleaseFromStoryPointsSelected(projectsSelectedIds, releasesSelectedIds);
        
        createBarModel();
    }

    public List<RowTotalPerRelease> getRowsTotalPerRelease() {
        return rowsTotalPerRelease;
    }

    public void setRowsTotalPerRelease(List<RowTotalPerRelease> rowsTotalPerRelease) {
        this.rowsTotalPerRelease = rowsTotalPerRelease;
    }
    
    
    public boolean isSelected() {
        return rowsRaport!=null && rowsRaport.size()>0;
    }

}

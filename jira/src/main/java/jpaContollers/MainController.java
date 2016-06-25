/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpaContollers;

import databaseentities.AccountDB;
import databaseentities.AccountprojectsDB;
import databaseentities.JiraprojectDB;
import databaseentities.JirareleaseDB;
import databaseentities.JirasprintDB;
import databaseentities.ProjectreleaseDB;
import databaseentities.ProjectreleasesprintDB;
import databaseentities.StorypointDB;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import pojo.Account;
import pojo.Project;
import pojo.Release;
import pojo.RowRaport;
import pojo.RowTotalPerRelease;
import pojo.Sprint;

/**
 *
 * @author Oana
 */
@Stateless
public class MainController {

    @EJB
    private AccountDBJpaController accountJpaController;
    @EJB
    private JiraprojectDBJpaController projectJpaController;
    @EJB
    private JirareleaseDBJpaController releaseJpaController;
    @EJB
    private ProjectreleaseDBJpaController projectreleaseDBJpaController;
    @EJB
    private JirasprintDBJpaController sprintJpaController;
    @EJB
    private ProjectreleasesprintDBJpaController projectreleasesprintDBJpaController;
    @EJB
    private AccountprojectsDBJpaController accountprojectsJpaController;
    @EJB
    private StorypointJpaController storypointJpaController;
    @EJB
    private ItemDBJpaController itemDBJpaController;
    @EJB
    private EfforttypeDBJpaController efforttypeDBJpaController;
    

    public boolean register(String username, String password) {
        AccountDB u = accountJpaController.getUserByName(username);
        if (u == null) {
            try {
                accountJpaController.create(new AccountDB(0L, username, password, "REJECTED", "USER"));
            } catch (Exception ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }
            return true;
        }
        return false;
    }

    public Account login(String user, String parola) {

        AccountDB u = accountJpaController.getUserByName(user);
        if (u != null) {
            if (u.getName().equals(parola)) {
                return new Account(u.getId(), u.getName(), u.getPassword(), u.getStatus(), u.getRole());
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public List<Account> getAccounts() {
        List<AccountDB> accountsFromDatabase = accountJpaController.findAccountDBEntities();
        List<Account> accounts = new ArrayList<>();

        for (AccountDB adb : accountsFromDatabase) {
            accounts.add(new Account(adb.getId(), adb.getName(), adb.getPassword(), adb.getStatus(), adb.getRole()));
        }
        return accounts;
    }

    public List<Sprint> getSprintsByRelease(Long idProjectRelease) {
        List<ProjectreleasesprintDB> sprintsFromDatabase = projectreleasesprintDBJpaController.getSprintsByProjectReleaseId(idProjectRelease);
        List<Sprint> sprintsFound = new ArrayList<>();
        for (ProjectreleasesprintDB sprintReleaseProject : sprintsFromDatabase) {
            JirasprintDB sprintFound = sprintJpaController.findJirasprintDB(sprintReleaseProject.getIdsprint());
            sprintsFound.add(new Sprint(sprintFound.getId(), sprintFound.getName(), sprintReleaseProject.getCapacity()));
        }
        return sprintsFound;
    }

    public List<Release> getReleasesByProject(Long idProject) {
        List<ProjectreleaseDB> projectRel = projectreleaseDBJpaController.getProjectReleaseByIdProject(idProject);
        List<Release> releasesFound = new ArrayList<>();
        for (ProjectreleaseDB projectRelease : projectRel) {
            JirareleaseDB releaseFound = releaseJpaController.findJirareleaseDB(projectRelease.getIdrelease());
            releasesFound.add(new Release(releaseFound.getId(), releaseFound.getName(), getSprintsByRelease(projectRelease.getId())));
        }
        return releasesFound;
    }

    public List<Project> getProjectsByAccount(Long idAccount) {
        List<AccountprojectsDB> accountprojectsFromDatabase = accountprojectsJpaController.getProjectsByAccount(idAccount);
        List<Project> projects = new ArrayList<>();

        for (AccountprojectsDB apdb : accountprojectsFromDatabase) {
            projects.add(new Project(apdb.getAccountproject(), getReleasesByProject(apdb.getAccountproject()), projectJpaController.findProjectDB(apdb.getAccountproject()).getName()));
        }
        return projects;

    }

    public List<Project> getAllProjects() {
        List<JiraprojectDB> projectsFromDatabase = projectJpaController.findProjectDBEntities();
        List<Project> projects = new ArrayList<>();
        
        for (JiraprojectDB pdb : projectsFromDatabase) {
            List<Release> releasesFound = getReleasesByProject(pdb.getId());
            projects.add(new Project(pdb.getId(), releasesFound, pdb.getName()));
        }
        return projects;

    }

    public void updateStatus(Account account) {
        AccountDB accountDB = accountJpaController.findAccountDB(account.getId());
        accountDB.setStatus(account.getStatus());

        accountJpaController.update(accountDB);
    }

    public void updateRole(Account account) {        
        AccountDB accountDB = accountJpaController.findAccountDB(account.getId());            
               
        accountDB.setRole(account.getRole());

        accountJpaController.update(accountDB);
    }

    public void removeAccount(Account account) {
        AccountDB accountDB = accountJpaController.findAccountDB(account.getId());

        accountJpaController.remove(accountDB);
        accountprojectsJpaController.removeByAccountId(accountDB.getId());
    }

    public void addAccount(Account account) {
        accountJpaController.create(new AccountDB(Long.MIN_VALUE, account.getName(), account.getPassword(), account.getStatus(), account.getRole()));
    }

    public void addProjectsToAccount(Long accountId, List<Project> projects) {

        List<Long> idProjects = new ArrayList<>();
        for (Project p : projects) {
            idProjects.add(p.getId());
        }

        accountprojectsJpaController.addProjectsByAccountId(accountId, idProjects);
    }

    public List<Long> getProjectsForAccount(Long id) {
        List<AccountprojectsDB> accountprojects = accountprojectsJpaController.getProjectsByAccount(id);

        List<Long> projectIds = new ArrayList<>();
        for (AccountprojectsDB ap : accountprojects) {
            projectIds.add(ap.getAccountproject());
        }

        return projectIds;

    }

    public void updatecapacityForSprintSelected(Long projectReleaseId, Sprint sprint) {
        ProjectreleasesprintDB projectreleasesprintDB = projectreleasesprintDBJpaController.findByIdProjectReleaseAndIdSprint(projectReleaseId, sprint.getId());
        projectreleasesprintDB.setCapacity(sprint.getCapacity());
        projectreleasesprintDBJpaController.update(projectreleasesprintDB);


    }

    public Account findAccountByName(String userName) {
        AccountDB userFound = accountJpaController.getUserByName(userName);
        if (userFound != null) {
            return new Account(userFound.getId(), userFound.getName(), userFound.getPassword(), userFound.getStatus(), userFound.getRole());
        } else {
            return null;
        }

    }
    
  
    public Long getProjectReleaseIdByIdProjectAndIdIdRelease(Long idproject, Long idRelease) {
        ProjectreleaseDB projectreleaseDB = projectreleaseDBJpaController.findByProjectAndRelease(idproject, idRelease);
        return  projectreleaseDB.getId();
        
    }

    public List<Release> getAllReleases() {
        List<Release> releases = new ArrayList<>();
        List<JirareleaseDB> jirareleaseDBs = releaseJpaController.findJirareleaseDBEntities();
        
        for (JirareleaseDB jr: jirareleaseDBs) {
            releases.add(new Release(jr.getId(), jr.getName(), getSprintsByRelease(jr.getId())));
        }
        return releases;
                
    }

   
    
    
    public List<StorypointDB> getRowRaport2(List<String> projectsSelectedIds, List<String> releasesSelectedIds){
        
        List<ProjectreleaseDB> projectReleases = new ArrayList<>();
        
        for (String projectId: projectsSelectedIds){
            for (String releaseId: releasesSelectedIds){
                ProjectreleaseDB projectRelease = projectreleaseDBJpaController.findByProjectAndRelease(Long.valueOf(projectId), Long.valueOf(releaseId));
                if (projectRelease!=null)
                    projectReleases.add(projectRelease);            
            }        
        }
        
        List<ProjectreleasesprintDB> projectReleasesSprints = new ArrayList<>();
        
        for (ProjectreleaseDB projectReleaseFound: projectReleases){            
            List<ProjectreleasesprintDB> projectreleasesprintDB = projectreleasesprintDBJpaController.findByProjectRelease(projectReleaseFound.getId());
            if (projectreleasesprintDB!=null && projectreleasesprintDB.size()>0)
                projectReleasesSprints.addAll(projectreleasesprintDB);
        }
    
        List<StorypointDB> storyPoints = new ArrayList<>();
        
        for (ProjectreleasesprintDB projectReleaseSprint: projectReleasesSprints){
            List<StorypointDB> storypointDB = storypointJpaController.findByProjectReleaseSprintId(projectReleaseSprint.getId());
            if (storypointDB!=null && storypointDB.size()>0){
                storyPoints.addAll(storypointDB);
            }
        }
        
        return storyPoints;       
        
    }   
    
    public List<RowRaport> getRowRaport(List<String> projectsSelectedIds, List<String> releasesSelectedIds) {

        List<StorypointDB> storyPoints = getRowRaport2(projectsSelectedIds, releasesSelectedIds);

        List<RowRaport> rowRaports = new ArrayList<>();
        for (StorypointDB storyPointDB : storyPoints) {
            ProjectreleasesprintDB projectreleasesprintDB = projectreleasesprintDBJpaController.findProjectreleasesprintDB(storyPointDB.getIdprojectreleasesprint());
            ProjectreleaseDB projectreleaseDB = projectreleaseDBJpaController.findProjectreleaseDB(projectreleasesprintDB.getIdprojectrelease());

            JiraprojectDB jiraprojectDB = projectJpaController.findProjectDB(projectreleaseDB.getIdproject());
            JirareleaseDB jirareleaseDB = releaseJpaController.findJirareleaseDB(projectreleaseDB.getIdrelease());

            rowRaports.add(new RowRaport(projectreleaseDB.getAn(), jirareleaseDB.getName(), jiraprojectDB.getName(), efforttypeDBJpaController.findEfforttypeDB(storyPointDB.getIdefforttype()).getName(), itemDBJpaController.findItemDB(storyPointDB.getIditem()).getName(), storyPointDB.getStorypoint(), sprintJpaController.findJirasprintDB(projectreleasesprintDB.getIdsprint()).getName()));

        }
        return rowRaports;
    }
   
    
    public List<RowTotalPerRelease> getProjectRelesesPerReleaseFromStoryPointsSelected(List<String> projectsSelectedIds, List<String> releasesSelectedIds){
        List<StorypointDB> storyPoints = getRowRaport2(projectsSelectedIds, releasesSelectedIds);
    
        
        List<RowTotalPerRelease> rowTotalPerReleases = new ArrayList<>();
        for (StorypointDB storyPointDB : storyPoints) {
            ProjectreleasesprintDB projectreleasesprintDB = projectreleasesprintDBJpaController.findProjectreleasesprintDB(storyPointDB.getIdprojectreleasesprint());
            ProjectreleaseDB projectreleaseDB = projectreleaseDBJpaController.findProjectreleaseDB(projectreleasesprintDB.getIdprojectrelease());

            RowTotalPerRelease rowTotalPerRelease = getItemByProjectRelease(rowTotalPerReleases, projectreleaseDB.getId());
            if (rowTotalPerRelease!=null){
                
                Long total = Long.valueOf(rowTotalPerRelease.getTotalPerRelease()) + Long.valueOf(storyPointDB.getStorypoint());
                rowTotalPerRelease.setTotalPerRelease(total.toString());
            }else {
                JiraprojectDB jiraprojectDB = projectJpaController.findProjectDB(projectreleaseDB.getIdproject());
                JirareleaseDB jirareleaseDB = releaseJpaController.findJirareleaseDB(projectreleaseDB.getIdrelease());
                rowTotalPerReleases.add(new RowTotalPerRelease(projectreleaseDB.getId(), projectreleaseDB.getAn(), jirareleaseDB.getName(), jiraprojectDB.getName(), storyPointDB.getStorypoint()));
            }
        }
        return rowTotalPerReleases;
    }
    
    
    public RowTotalPerRelease getItemByProjectRelease(List<RowTotalPerRelease> rowTotalPerReleases, Long idProjectRelease){
        
        for (RowTotalPerRelease rowTotalPerRelease: rowTotalPerReleases){
            if (rowTotalPerRelease.getIdProjectRelease().equals(idProjectRelease))
                return rowTotalPerRelease;

        }
        return null;
    }
    
    

}

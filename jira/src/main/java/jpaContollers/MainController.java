/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpaContollers;

import databaseentities.AccountDB;
import databaseentities.AccountprojectsDB;
import databaseentities.JiraprojectDB;

import databaseentities.ReleaseDB;
import databaseentities.SprintDB;
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
    private ReleaseDBJpaController releaseJpaController;
    @EJB
    private SprintDBJpaController sprintJpaController;
    @EJB
    private AccountprojectsDBJpaController accountprojectsJpaController;

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

    public List<Sprint> getSprintsByRelease(Long idRelease) {
        List<SprintDB> sprintsFromDatabase = sprintJpaController.getSprintsByRelease(idRelease);
        List<Sprint> sprints = new ArrayList<>();
        for (SprintDB sdb : sprintsFromDatabase) {
            sprints.add(new Sprint(sdb.getId(), sdb.getName(), Long.valueOf(sdb.getCapacity())));
        }
        return sprints;
    }

    public List<Release> getReleasesByProject(Long idProject) {
        List<ReleaseDB> releasessFromDatabase = releaseJpaController.getReleasesByProject(idProject);
        List<Release> releases = new ArrayList<>();
        for (ReleaseDB rdb : releasessFromDatabase) {
            releases.add(new Release(rdb.getId(), rdb.getName(), getSprintsByRelease(rdb.getId())));
        }
        return releases;
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
            //projects.add(new Project(pdb.getId(), getReleasesByProject(pdb.getId()), pdb.getName()));
            projects.add(new Project(pdb.getId(), new ArrayList<Release>(), pdb.getName()));
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

    public void updatecapacityForSprintSelected(Sprint sprintselected) {
        SprintDB sprintDB = sprintJpaController.findSprintDB(sprintselected.getId());
        sprintDB.setCapacity(sprintselected.getCapacity().toString());
        sprintJpaController.update(sprintDB);

    }

    public Account findAccountByName(String userName) {
        AccountDB userFound = accountJpaController.getUserByName(userName);
        if (userFound != null) {
            return new Account(userFound.getId(), userFound.getName(), userFound.getPassword(), userFound.getStatus(), userFound.getRole());
        } else {
            return null;
        }

    }

}

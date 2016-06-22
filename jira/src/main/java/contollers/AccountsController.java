package contollers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import pojo.Account;
import javax.inject.Inject;
import jpaContollers.MainController;
import pojo.Project;

@Named
@SessionScoped
public class AccountsController implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<Account> accounts;
    
    
    
    private List<String> selectedList;
    
    @Inject
    ProjectController projectView;
    
    @EJB
    MainController mainController;
    
    private List<Project> allProjects;
    
   
    @PostConstruct
    public void init() {       
      
        
        selectedList = new ArrayList<>();                    
      
     
        accounts = mainController.getAccounts();      
        for (Account account: accounts){
            takeTheProjectsForAccountFromDataBase(account);
        }
        
        

    }
    
    
    public void takeTheProjectsForAccountFromDataBase(Account account){
        List<Long> projectsPerAcccount = mainController.getProjectsForAccount(account.getId());
        
        allProjects = projectView.getProjects();
        
        for (Long prId: projectsPerAcccount){
            account.getAccountProjects().add(findProjectById(prId, allProjects));
        }
        
       
        
    }


    public void approve(long id) {
        
        for (Account account : accounts) {
            if (account.getId().equals(id)) {
                account.setStatus("APPROVED");
                mainController.updateStatus(account);
            }
        }
        
        
    }

    public void reject(long id) {
        for (Account account : accounts) {
            if (account.getId().equals(id)) {
                account.setStatus("REJECTED");
                mainController.updateStatus(account);
            }
        }
    }

    public void remove(long id) {
        for (Account account : accounts) {
            if (account.getId().equals(id)) {
                accounts.remove(account);
                mainController.removeAccount(account);
                return;
            }
        }
    }

    

    public void save(Account account) {
        accounts.add(account);
        mainController.addAccount(account);
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

  

    public List<String> getSelectedList() {
        return selectedList;
    }

    public void setSelectedList(List<String> selectedList) {
        this.selectedList = selectedList;
    }
    
    public Account findAccountById(Long id) {
        for (Account account : accounts) {
            if (account.getId().equals(id)) {
                return account;
            }
        }
        return null;
    }
    
    
    
    public void setProjectsToAccount(Long id){
        List<Project> projectsSelected = new ArrayList<>();
        for(String idIter: findAccountById(id).getAccountProjectsIds()){
            projectsSelected.add(findProjectById(Long.valueOf(idIter), allProjects));
        }
        
        findAccountById(id).setAccountProjects(projectsSelected);     
        
        mainController.addProjectsToAccount(id, projectsSelected);
    }
    
    
    
    public Project findProjectById(Long id, List<Project> projects) {
        if (projects!=null){
            for (Project p : projects) {
                if (p.getId().equals(id)) {
                    return p;
                }
            }
            
        }
        return null;
    }

     public Account find(String name) {
        for (Account account : accounts) {
            if (account.getName().equals(name)) {
                return account;
            }
        }
        return null;
    }
    
}

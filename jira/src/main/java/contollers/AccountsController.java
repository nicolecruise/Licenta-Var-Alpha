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
import jpaContollers.SomeEJB;
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
    
    @EJB
    private SomeEJB someEJB;
 
    @PostConstruct
    public void init() {
        
      
        
        selectedList = new ArrayList<>();       
              
      
        someEJB.something();
        accounts = mainController.getAccounts();       
        //accounts = new ArrayList<>();
        
        
        

    }

    public Account find(long id) {
        for (Account account : accounts) {
            if (account.getId().equals(id)) {
                return account;
            }
        }
        return null;
    }

    public void approve(long id) {
//        for (Account account : accounts) {
//            if (account.getId().equals(id)) {
//                account.setStatus(Status.APPROVED);
//            }
//        }
    }

    public void reject(long id) {
//        for (Account account : accounts) {
//            if (account.getId().equals(id)) {
//                account.setStatus(Status.REJECTED);
//            }
//        }
    }

    public void remove(long id) {
//        for (Account account : accounts) {
//            if (account.getId().equals(id)) {
//                accounts.remove(account);
//                return;
//            }
//        }
    }

    public Account find(String name) {
        for (Account account : accounts) {
            if (account.getName().equals(name)) {
                return account;
            }
        }
        return null;
    }

    public void save(Account account) {
        accounts.add(account);
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public List<String> getSelectedList() {
        return selectedList;
    }

    public void setSelectedList(List<String> selectedList) {
        this.selectedList = selectedList;
    }
    
    
   

   
    
    public void setProjectsToAccount(Long id){
        List<Project> projectsSelected = new ArrayList<>();
        for(String idIter: accounts.get(Integer.valueOf(id.toString())).getAccountProjectsIds()){
            projectsSelected.add(projectView.getProjects().get(Integer.valueOf(idIter)));
        }
        
        accounts.get(Integer.valueOf(id.toString())).setAccountProjects(projectsSelected);
        
        
        
    }

}

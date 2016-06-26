package contollers;

import pojo.Account;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;



import java.io.Serializable;


@Named
@SessionScoped
public class SessionController implements Serializable {

    private static final long serialVersionUID = 1L;

    private Account user;

    
   
    public String logout() {
        
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        user = null;
        return "/login.xhtml?faces-redirect=true";
    }
    
    public String logInAction() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/login.xhtml?faces-redirect=true";
    }

    
            
   public String registrationAction() {
        //FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/registration.xhtml?faces-redirect=true";
    }
            
    public Account getUser() {
        return user;
    }

    public void setUser(Account user) {
        this.user = user;
    }

    public boolean isAdmin() {
        if (user != null && user.getRole()!=null) {

            return user.getRole().equals("ADMIN");
        } else {
            return false;
        }
    }
    
    public boolean isLogIn() {
        return user != null;
    }
    
    public boolean isLogInAndApproved() {
        
        if(user !=null && user.getStatus().equals("APPROVED")){
            return true;
        }
        else return false;
        
    }
}

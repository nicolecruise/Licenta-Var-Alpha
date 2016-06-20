package contollers;

import contollers.SessionController;
import pojo.Account;
import contollers.ProjectController;
import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import database.entities.User;
import contollers.AccountsController;
import database.queries.UserQueries;

@Named
@SessionScoped
public class LoginController implements Serializable {

	private static final long serialVersionUID = 1L;	
        
        @Inject
        private AccountsController accountsQueries;
        
        @Inject
        private ProjectController projectView;

	@Inject
	private SessionController sessionController;
	
	private String name;
	private String password;

	public String login() {
		Account user = accountsQueries.find(name);
		if (user == null) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("User not found"));
			return null;
		} else if (!user.getPassword().equals(password)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("User or passsword wrong"));
			return null;
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Welcome: " + user.getName()));
			sessionController.setUser(user);
                        projectView.setProjectByType(user);
			return "index.xhtml?faces-redirect=true";
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}

package contollers;

import contollers.AccountsController;
import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import database.entities.User;
import database.entities.UserType;
import database.queries.UserQueries;
import jpaContollers.MainController;
import pojo.Account;

@Named
@SessionScoped
public class RegistrationController implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Inject
	private AccountsController accountsController;
        
        @Inject
	private MainController mainController;

	private String name;
	private String password;
	private String confirmPassword;


	public String registration() {
		Account user = mainController.findAccountByName(name);
		if (user != null) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("A user already exists with this name"));
		} else if (!password.equals(confirmPassword)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Passwords didn't match"));
		} else if (name == null || name.isEmpty() ) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Please chose a valid user name"));
		} else if (password == null || password.isEmpty() || password.length() < 3) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Please chose a valid password"));
		} else {
                        Account newAccount = new Account(Long.MIN_VALUE, name, password, "REJECTED", "USER");
			mainController.addAccount(newAccount);                    
                        accountsController.getAccounts().add(mainController.findAccountByName(name));
		}
		return "login.xhtml?faces-redirect=true";
	}
	
	/*
	
	public void registration() {
		try {
			
			displayMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Registration completed!"));
		} catch (Exception e) {
			displayMessage(new FacesMessage(FacesMessage.SEVERITY_WARN, "Registration Error", "Failed Registration"));
		}
	}

	private void displayMessage(FacesMessage message) {
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
	
	
	public void validatePassword() {
		User user = userRegistrationQueries.find(name);
		
	    FacesMessage message;

	    if (!user.getPassword().equals(user.getConfirmPassword()) ){
	        message = new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Registration failed.Password didn't match.");
	    }else{
	        message = new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Registration successful");
	    }

	    FacesContext.getCurrentInstance().addMessage("form:password", message);
	}
*/
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
	
	public String getConfirmPassword() {
		return confirmPassword;
		
	}
	
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

}

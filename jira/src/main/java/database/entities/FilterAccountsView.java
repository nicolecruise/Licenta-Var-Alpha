package database.entities;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import contollers.AccountsController;


@Named
@SessionScoped

public class FilterAccountsView implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private List<AccountsController> accounts;
	private List<AccountsController> filteredAccounts;
	
	
	public List<AccountsController> getAccounts() {
		return accounts;
	}
	public void setAccounts(List<AccountsController> accounts) {
		this.accounts = accounts;
	}
	public List<AccountsController> getFilteredAccounts() {
		return filteredAccounts;
	}
	public void setFilteredAccounts(List<AccountsController> filteredAccounts) {
		this.filteredAccounts = filteredAccounts;
	}

	

}

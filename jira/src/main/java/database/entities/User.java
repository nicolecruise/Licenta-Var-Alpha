package database.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name = User.GET_ALL_USERS, query = "SELECT u from User u")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	public final static String GET_ALL_USERS = "GET_ALL_USERS";

	private Long id;
	private String name;
	private String password;
	private UserType userType;

	public User() {
	}

	public User(Long id, UserType userType, String name, String password) {
		this.id = id;
		this.userType = userType;
		this.name = name;
		this.password = password;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	@Enumerated(EnumType.STRING)
	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}
}

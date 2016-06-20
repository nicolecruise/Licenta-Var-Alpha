package database.queries;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import database.entities.User;
import database.entities.UserType;
import java.io.Serializable;

@Named
@SessionScoped
public class UserQueries implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<User> users;

	public UserQueries() {
		users = new ArrayList<>();
		users.add(new User(1L, UserType.USER, "Gigi", "Gigi"));
		users.add(new User(2L, UserType.USER, "Leo", "barosanu"));
		users.add(new User(3L, UserType.ADMIN, "Gogu", "patronu"));
	}

	public User find(long id) {
		for (User user : users) {
			if (user.getId().equals(id)) {
				return user;
			}
		}
		return null;
	}

	public User find(String name) {
		for (User user : users) {
			if (user.getName().equals(name)) {
				return user;
			}
		}
		return null;
	}

	public void save(User user) {
		users.add(user);
	}
}

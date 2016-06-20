package pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Account implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;
    private String password;
    private String status;

    private String role;

    private List<Project> accountProjects;
    private List<String> accountProjectsIds;

    public Account() {
    }

    public Account(Long id, String name, String password, String status, String role) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.status = status;
        this.role = role;
        this.accountProjects = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Project> getAccountProjects() {
        return accountProjects;
    }

    public void setAccountProjects(List<Project> accountProjects) {
        this.accountProjects = accountProjects;
    }

    public List<String> getAccountProjectsIds() {
        return accountProjectsIds;
    }

    public void setAccountProjectsIds(List<String> accountProjectsIds) {
        this.accountProjectsIds = accountProjectsIds;
    }

}

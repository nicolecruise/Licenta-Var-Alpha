/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pojo;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Oana
 */
public class Project implements Serializable, Cloneable {
    
    private Long id;
    private List<Release> releases;
    private String name;

    public Project() {
    }

    public Project(Long id, List<Release> releases, String name) {
        this.id = id;
        this.releases = releases;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Release> getReleases() {
        return releases;
    }

    public void setReleases(List<Release> releases) {
        this.releases = releases;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    @Override
//    public String toString() {
//        return name;
//    }
    
    @Override
    public boolean equals(Object other) {
        return (other instanceof Project) && (id != null)
            ? id.equals(((Project) other).id)
            : (other == this);
    }

    @Override
    public int hashCode() {
        return (id != null)
            ? (this.getClass().hashCode() + id.hashCode())
            : super.hashCode();
    }

    @Override
    public String toString() {
        return String.format("Project[%d, %s]", id, releases, name);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
}

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
public class Release implements Serializable{
    
    private Long id;
    private String name;
    private List<Sprint> sprints;

    public Release() {
    }

    public Release(Long id, String name, List<Sprint> sprints) {
        this.id = id;
        this.name = name;
        this.sprints = sprints;
    }

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

    public List<Sprint> getSprints() {
        return sprints;
    }

    public void setSprints(List<Sprint> sprints) {
        this.sprints = sprints;
    }

//    @Override
//    public String toString() {
//        return name;
//    }
    
    @Override
    public boolean equals(Object other) {
        return (other instanceof Release) && (id != null)
            ? id.equals(((Release) other).id)
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
        return String.format("Release[%d, %s]", id, name, sprints);
    }

   
    
    
}

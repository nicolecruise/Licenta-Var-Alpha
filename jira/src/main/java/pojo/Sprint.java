    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pojo;

import java.io.Serializable;

/**
 *
 * @author Oana
 */
public class Sprint implements Serializable, Cloneable{
    
    private Long id;
    private String name;
    private String capacity;
   

    public Sprint() {
    }

    public Sprint(Long id, String name, String capacity) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
       
    }
    
    public Sprint(Sprint s){
        this.id=s.id+1;
        this.name=s.name;
        this.capacity=s.capacity;
    
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

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }


    @Override
    public boolean equals(Object other) {
        return (other instanceof Sprint) && (id != null)
            ? id.equals(((Sprint) other).id)
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
        return String.format("Release[%d, %s]", id, name, capacity);
    }

    @Override
    public Sprint clone() throws CloneNotSupportedException {
        Sprint returned = new Sprint();
        returned.id = this.id;
        returned.name = this.name;
        returned.capacity = this.capacity;
        return returned;
    }
    
    
    
    
    
}

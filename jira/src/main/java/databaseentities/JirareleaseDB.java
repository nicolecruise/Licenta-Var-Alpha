/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseentities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author iradoi
 */
@Entity
@Table(name = "jirarelease")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "JirareleaseDB.findAll", query = "SELECT j FROM JirareleaseDB j"),
    @NamedQuery(name = "JirareleaseDB.findById", query = "SELECT j FROM JirareleaseDB j WHERE j.id = :id"),
    @NamedQuery(name = "JirareleaseDB.findByName", query = "SELECT j FROM JirareleaseDB j WHERE j.name = :name")})
public class JirareleaseDB implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "name")
    private String name;

    public JirareleaseDB() {
    }

    public JirareleaseDB(Long id) {
        this.id = id;
    }

    public JirareleaseDB(Long id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof JirareleaseDB)) {
            return false;
        }
        JirareleaseDB other = (JirareleaseDB) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "databaseentities.JirareleaseDB[ id=" + id + " ]";
    }
    
}

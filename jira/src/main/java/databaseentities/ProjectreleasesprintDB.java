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
 * @author Administrator
 */
@Entity
@Table(name = "projectreleasesprint")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProjectreleasesprintDB.findAll", query = "SELECT p FROM ProjectreleasesprintDB p"),
    @NamedQuery(name = "ProjectreleasesprintDB.findById", query = "SELECT p FROM ProjectreleasesprintDB p WHERE p.id = :id"),
    @NamedQuery(name = "ProjectreleasesprintDB.findByIdprojectrelease", query = "SELECT p FROM ProjectreleasesprintDB p WHERE p.idprojectrelease = :idprojectrelease"),
    @NamedQuery(name = "ProjectreleasesprintDB.findByIdsprint", query = "SELECT p FROM ProjectreleasesprintDB p WHERE p.idsprint = :idsprint"),
    @NamedQuery(name = "ProjectreleasesprintDB.findByCapacity", query = "SELECT p FROM ProjectreleasesprintDB p WHERE p.capacity = :capacity"),
    @NamedQuery(name = "ProjectreleasesprintDB.findByIdProjectReleaseAndIdSprint", query = "SELECT p FROM ProjectreleasesprintDB p WHERE p.idprojectrelease = :idprojectrelease AND p.idsprint = :idsprint")})
public class ProjectreleasesprintDB implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idprojectrelease")
    private long idprojectrelease;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idsprint")
    private long idsprint;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "capacity")
    private String capacity;

    public ProjectreleasesprintDB() {
    }

    public ProjectreleasesprintDB(Long id) {
        this.id = id;
    }

    public ProjectreleasesprintDB(Long id, long idprojectrelease, long idsprint, String capacity) {
        this.id = id;
        this.idprojectrelease = idprojectrelease;
        this.idsprint = idsprint;
        this.capacity = capacity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getIdprojectrelease() {
        return idprojectrelease;
    }

    public void setIdprojectrelease(long idprojectrelease) {
        this.idprojectrelease = idprojectrelease;
    }

    public long getIdsprint() {
        return idsprint;
    }

    public void setIdsprint(long idsprint) {
        this.idsprint = idsprint;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
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
        if (!(object instanceof ProjectreleasesprintDB)) {
            return false;
        }
        ProjectreleasesprintDB other = (ProjectreleasesprintDB) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "databaseentities.ProjectreleasesprintDB[ id=" + id + " ]";
    }
    
}

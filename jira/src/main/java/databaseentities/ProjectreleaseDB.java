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
@Table(name = "projectrelease")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProjectreleaseDB.findAll", query = "SELECT p FROM ProjectreleaseDB p"),
    @NamedQuery(name = "ProjectreleaseDB.findById", query = "SELECT p FROM ProjectreleaseDB p WHERE p.id = :id"),
    @NamedQuery(name = "ProjectreleaseDB.findByIdproject", query = "SELECT p FROM ProjectreleaseDB p WHERE p.idproject = :idproject"),
    @NamedQuery(name = "ProjectreleaseDB.findByIdrelease", query = "SELECT p FROM ProjectreleaseDB p WHERE p.idrelease = :idrelease"),
    @NamedQuery(name = "ProjectreleaseDB.findByAn", query = "SELECT p FROM ProjectreleaseDB p WHERE p.an = :an"),
    @NamedQuery(name = "ProjectreleaseDB.findByProjectAndRelease", query = "SELECT p FROM ProjectreleaseDB p WHERE p.idproject = :idproject AND p.idrelease = :idrelease")})
public class ProjectreleaseDB implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idproject")
    private long idproject;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idrelease")
    private long idrelease;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "an")
    private String an;

    public ProjectreleaseDB() {
    }

    public ProjectreleaseDB(Long id) {
        this.id = id;
    }

    public ProjectreleaseDB(Long id, long idproject, long idrelease, String an) {
        this.id = id;
        this.idproject = idproject;
        this.idrelease = idrelease;
        this.an = an;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getIdproject() {
        return idproject;
    }

    public void setIdproject(long idproject) {
        this.idproject = idproject;
    }

    public long getIdrelease() {
        return idrelease;
    }

    public void setIdrelease(long idrelease) {
        this.idrelease = idrelease;
    }

    public String getAn() {
        return an;
    }

    public void setAn(String an) {
        this.an = an;
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
        if (!(object instanceof ProjectreleaseDB)) {
            return false;
        }
        ProjectreleaseDB other = (ProjectreleaseDB) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "databaseentities.ProjectreleaseDB[ id=" + id + " ]";
    }
    
}

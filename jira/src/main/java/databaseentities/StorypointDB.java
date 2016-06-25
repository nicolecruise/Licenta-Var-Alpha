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
@Table(name = "storypoint")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StorypointDB.findAll", query = "SELECT s FROM StorypointDB s"),
    @NamedQuery(name = "StorypointDB.findById", query = "SELECT s FROM StorypointDB s WHERE s.id = :id"),
    @NamedQuery(name = "StorypointDB.findByIdprojectreleasesprint", query = "SELECT s FROM StorypointDB s WHERE s.idprojectreleasesprint = :idprojectreleasesprint"),
    @NamedQuery(name = "StorypointDB.findByIdefforttype", query = "SELECT s FROM StorypointDB s WHERE s.idefforttype = :idefforttype"),
    @NamedQuery(name = "StorypointDB.findByIditem", query = "SELECT s FROM StorypointDB s WHERE s.iditem = :iditem"),
    @NamedQuery(name = "StorypointDB.findByStorypoint", query = "SELECT s FROM StorypointDB s WHERE s.storypoint = :storypoint")})
public class StorypointDB implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idprojectreleasesprint")
    private long idprojectreleasesprint;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idefforttype")
    private long idefforttype;
    @Basic(optional = false)
    @NotNull
    @Column(name = "iditem")
    private long iditem;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "storypoint")
    private String storypoint;

    public StorypointDB() {
    }

    public StorypointDB(Long id) {
        this.id = id;
    }

    public StorypointDB(Long id, long idprojectreleasesprint, long idefforttype, long iditem, String storypoint) {
        this.id = id;
        this.idprojectreleasesprint = idprojectreleasesprint;
        this.idefforttype = idefforttype;
        this.iditem = iditem;
        this.storypoint = storypoint;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getIdprojectreleasesprint() {
        return idprojectreleasesprint;
    }

    public void setIdprojectreleasesprint(long idprojectreleasesprint) {
        this.idprojectreleasesprint = idprojectreleasesprint;
    }

    public long getIdefforttype() {
        return idefforttype;
    }

    public void setIdefforttype(long idefforttype) {
        this.idefforttype = idefforttype;
    }

    public long getIditem() {
        return iditem;
    }

    public void setIditem(long iditem) {
        this.iditem = iditem;
    }

    public String getStorypoint() {
        return storypoint;
    }

    public void setStorypoint(String storypoint) {
        this.storypoint = storypoint;
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
        if (!(object instanceof StorypointDB)) {
            return false;
        }
        StorypointDB other = (StorypointDB) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "databaseentities.Storypoint[ id=" + id + " ]";
    }
    
}

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
 * @author Oana
 */
@Entity
@Table(name = "sprint")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SprintDB.findAll", query = "SELECT s FROM SprintDB s"),
    @NamedQuery(name = "SprintDB.findById", query = "SELECT s FROM SprintDB s WHERE s.id = :id"),
    @NamedQuery(name = "SprintDB.findByName", query = "SELECT s FROM SprintDB s WHERE s.name = :name"),
    @NamedQuery(name = "SprintDB.findByCapacity", query = "SELECT s FROM SprintDB s WHERE s.capacity = :capacity"),
    @NamedQuery(name = "SprintDB.findByIdRelase", query = "SELECT s FROM SprintDB s WHERE s.idRelase = :idRelase")})
public class SprintDB implements Serializable {
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
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "capacity")
    private String capacity;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idRelase")
    private long idRelase;

    public SprintDB() {
    }

    public SprintDB(Long id) {
        this.id = id;
    }

    public SprintDB(Long id, String name, String capacity, long idRelase) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.idRelase = idRelase;
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

    public long getIdRelase() {
        return idRelase;
    }

    public void setIdRelase(long idRelase) {
        this.idRelase = idRelase;
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
        if (!(object instanceof SprintDB)) {
            return false;
        }
        SprintDB other = (SprintDB) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "databaseentities.SprintDB[ id=" + id + " ]";
    }
    
}

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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Oana
 */
@Entity
@Table(name = "accountprojects")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AccountprojectsDB.findAll", query = "SELECT a FROM AccountprojectsDB a"),
    @NamedQuery(name = "AccountprojectsDB.findById", query = "SELECT a FROM AccountprojectsDB a WHERE a.id = :id"),
    @NamedQuery(name = "AccountprojectsDB.findByAccountid", query = "SELECT a FROM AccountprojectsDB a WHERE a.accountid = :accountid"),
    @NamedQuery(name = "AccountprojectsDB.findByAccountproject", query = "SELECT a FROM AccountprojectsDB a WHERE a.accountproject = :accountproject")})
public class AccountprojectsDB implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "accountid")
    private long accountid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "accountproject")
    private long accountproject;

    public AccountprojectsDB() {
    }

    public AccountprojectsDB(Long id) {
        this.id = id;
    }

    public AccountprojectsDB(Long id, long accountid, long accountproject) {
        this.id = id;
        this.accountid = accountid;
        this.accountproject = accountproject;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getAccountid() {
        return accountid;
    }

    public void setAccountid(long accountid) {
        this.accountid = accountid;
    }

    public long getAccountproject() {
        return accountproject;
    }

    public void setAccountproject(long accountproject) {
        this.accountproject = accountproject;
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
        if (!(object instanceof AccountprojectsDB)) {
            return false;
        }
        AccountprojectsDB other = (AccountprojectsDB) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "databaseentities.AccountprojectsDB[ id=" + id + " ]";
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jpaContollers;

import databaseentities.AccountDB;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.UserTransaction;
import jpaContollers.exceptions.NonexistentEntityException;
import jpaContollers.exceptions.RollbackFailureException;

/**
 *
 * @author Oana
 */
@Stateless
public class AccountDBJpaController implements Serializable {



    @PersistenceContext
    private EntityManager em;
    
    public void create(AccountDB accountDB){      
            em.persist(accountDB);
}   

    public List<AccountDB> findAccountDBEntities() {
        return findAccountDBEntities(true, -1, -1);
    }

    public List<AccountDB> findAccountDBEntities(int maxResults, int firstResult) {
        return findAccountDBEntities(false, maxResults, firstResult);
    }

    private List<AccountDB> findAccountDBEntities(boolean all, int maxResults, int firstResult) {
        
        
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(AccountDB.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
       
    }

    public AccountDB findAccountDB(Long id) {
        
                    return em.find(AccountDB.class, id);
         
    }

    public int getAccountDBCount() {        
       
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<AccountDB> rt = cq.from(AccountDB.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        
    }
    
    public AccountDB getUserByName(String username) {
        
        Query q = em.createNamedQuery("AccountDB.findByName");
        q.setParameter("name", username);
        List<AccountDB> users = q.getResultList();
        if (users.size() > 0) {
            return users.get(0);
        } else {
            return null;
        }
    }
    
}

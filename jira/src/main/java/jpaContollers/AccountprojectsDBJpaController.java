/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jpaContollers;

import databaseentities.AccountprojectsDB;
import databaseentities.ProjectDB;
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
public class AccountprojectsDBJpaController implements Serializable {

    @PersistenceContext
    private EntityManager em;

    public void create(AccountprojectsDB accountprojectsDB){
       
            em.persist(accountprojectsDB);
           
    }

    

    public List<AccountprojectsDB> findAccountprojectsDBEntities() {
        return findAccountprojectsDBEntities(true, -1, -1);
    }

    public List<AccountprojectsDB> findAccountprojectsDBEntities(int maxResults, int firstResult) {
        return findAccountprojectsDBEntities(false, maxResults, firstResult);
    }

    private List<AccountprojectsDB> findAccountprojectsDBEntities(boolean all, int maxResults, int firstResult) {
        
        
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(AccountprojectsDB.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        
    }

    public AccountprojectsDB findAccountprojectsDB(Long id) {
       
       
            return em.find(AccountprojectsDB.class, id);
        
    }

    public int getAccountprojectsDBCount() {
        
       
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<AccountprojectsDB> rt = cq.from(AccountprojectsDB.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        
    }
    
    
    public List<AccountprojectsDB> getProjectsByAccount(Long idAccount) {
       
        Query q = em.createNamedQuery("AccountprojectsDB.findByAccountid");
        q.setParameter("accountid", idAccount);
        List<AccountprojectsDB> accountprojectsDB = q.getResultList();

        return accountprojectsDB;
    }
    
}

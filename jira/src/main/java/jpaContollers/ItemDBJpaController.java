/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpaContollers;

import databaseentities.ItemDB;
import databaseentities.JiraprojectDB;
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
 * @author Administrator
 */
@Stateless
public class ItemDBJpaController implements Serializable {

   @PersistenceContext
    private EntityManager em;

    public void create(JiraprojectDB projectDB) throws RollbackFailureException, Exception {
       
            em.persist(projectDB);
           
    }

    public List<ItemDB> findItemDBEntities() {
        return findItemDBEntities(true, -1, -1);
    }

    public List<ItemDB> findItemDBEntities(int maxResults, int firstResult) {
        return findItemDBEntities(false, maxResults, firstResult);
    }

    private List<ItemDB> findItemDBEntities(boolean all, int maxResults, int firstResult) {
       
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ItemDB.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
       
    }

    public ItemDB findItemDB(Long id) {
        
            return em.find(ItemDB.class, id);
        
    }

    public int getItemDBCount() {
       
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ItemDB> rt = cq.from(ItemDB.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
       
    }
    
}

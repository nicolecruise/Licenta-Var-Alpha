/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpaContollers;

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
 * @author iradoi
 */
@Stateless
public class JiraprojectDBJpaController implements Serializable {

    @PersistenceContext
    private EntityManager em;

    public void create(JiraprojectDB projectDB) throws RollbackFailureException, Exception {
       
            em.persist(projectDB);
           
    }

   
    public List<JiraprojectDB> findProjectDBEntities() {
        return findProjectDBEntities(true, -1, -1);
    }

    public List<JiraprojectDB> findProjectDBEntities(int maxResults, int firstResult) {
        return findProjectDBEntities(false, maxResults, firstResult);
    }

    private List<JiraprojectDB> findProjectDBEntities(boolean all, int maxResults, int firstResult) {
        
      
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(JiraprojectDB.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        
    }

    public JiraprojectDB findProjectDB(Long id) {
       
     
            return em.find(JiraprojectDB.class, id);
       
    }

    public int getProjectDBCount() {
        
        
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<JiraprojectDB> rt = cq.from(JiraprojectDB.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        
    }
}

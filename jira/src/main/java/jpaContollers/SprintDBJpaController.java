/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpaContollers;

import databaseentities.SprintDB;
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
public class SprintDBJpaController implements Serializable {

    @PersistenceContext
    private EntityManager em;

    public void create(SprintDB sprintDB) throws RollbackFailureException, Exception {
      
            em.persist(sprintDB);
           
    }

    

    public List<SprintDB> findSprintDBEntities() {
        return findSprintDBEntities(true, -1, -1);
    }

    public List<SprintDB> findSprintDBEntities(int maxResults, int firstResult) {
        return findSprintDBEntities(false, maxResults, firstResult);
    }

    private List<SprintDB> findSprintDBEntities(boolean all, int maxResults, int firstResult) {
       
       
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(SprintDB.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
       
    }

    public SprintDB findSprintDB(Long id) {
       
      
            return em.find(SprintDB.class, id);
       
    }

    public int getSprintDBCount() {
    
        
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<SprintDB> rt = cq.from(SprintDB.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        
    }

    public List<SprintDB> getSprintsByRelease(Long idRelease) {
        
        Query q = em.createNamedQuery("SprintDB.findByIdRelase");
        q.setParameter("idRelease", idRelease);
        List<SprintDB> sprintsByRelease = q.getResultList();

        return sprintsByRelease;
    }
}

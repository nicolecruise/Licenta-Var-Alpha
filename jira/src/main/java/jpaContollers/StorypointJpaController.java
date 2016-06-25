/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpaContollers;

import databaseentities.JiraprojectDB;
import databaseentities.ProjectreleasesprintDB;
import databaseentities.StorypointDB;
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
public class StorypointJpaController implements Serializable {

   @PersistenceContext
    private EntityManager em;

    public void create(JiraprojectDB projectDB) throws RollbackFailureException, Exception {

        em.persist(projectDB);

    }
    public List<StorypointDB> findStorypointEntities() {
        return findStorypointEntities(true, -1, -1);
    }

    public List<StorypointDB> findStorypointEntities(int maxResults, int firstResult) {
        return findStorypointEntities(false, maxResults, firstResult);
    }

    private List<StorypointDB> findStorypointEntities(boolean all, int maxResults, int firstResult) {
       
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(StorypointDB.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
       
    }

    public StorypointDB findStorypoint(Long id) {
       
            return em.find(StorypointDB.class, id);
        
    }

    public int getStorypointCount() {
        
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<StorypointDB> rt = cq.from(StorypointDB.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
       
    }

    public List<StorypointDB> findByProjectReleaseSprintId(Long idProjectReleaseSprint) {
        
        
        Query q = em.createNamedQuery("StorypointDB.findByIdprojectreleasesprint");
        q.setParameter("idprojectreleasesprint", idProjectReleaseSprint);
      
        List<StorypointDB> storyPoints = q.getResultList();
        return storyPoints;
    }
    
}

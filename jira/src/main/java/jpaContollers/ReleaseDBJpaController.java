/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpaContollers;

import databaseentities.ReleaseDB;
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
public class ReleaseDBJpaController implements Serializable {

    @PersistenceContext
    private EntityManager em;

    public void create(ReleaseDB releaseDB) throws RollbackFailureException, Exception {
        
            em.persist(releaseDB);
            
    }

   

    public List<ReleaseDB> findReleaseDBEntities() {
        return findReleaseDBEntities(true, -1, -1);
    }

    public List<ReleaseDB> findReleaseDBEntities(int maxResults, int firstResult) {
        return findReleaseDBEntities(false, maxResults, firstResult);
    }

    private List<ReleaseDB> findReleaseDBEntities(boolean all, int maxResults, int firstResult) {
        
        
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ReleaseDB.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        
    }

    public ReleaseDB findReleaseDB(Long id) {
       
       
            return em.find(ReleaseDB.class, id);
      
    }

    public int getReleaseDBCount() {
       
       
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ReleaseDB> rt = cq.from(ReleaseDB.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        
    }

    public List<ReleaseDB> getReleasesByProject(Long idProject) {
        
        Query q = em.createNamedQuery("ReleaseDB.findByIdProject");
        q.setParameter("idProject", idProject);
        List<ReleaseDB> releasesByProject = q.getResultList();

        return releasesByProject;
    }

}

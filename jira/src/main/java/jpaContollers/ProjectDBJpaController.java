/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpaContollers;

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
public class ProjectDBJpaController implements Serializable {

    @PersistenceContext
    private EntityManager em;

    public void create(ProjectDB projectDB) throws RollbackFailureException, Exception {
       
            em.persist(projectDB);
           
    }

   
    public List<ProjectDB> findProjectDBEntities() {
        return findProjectDBEntities(true, -1, -1);
    }

    public List<ProjectDB> findProjectDBEntities(int maxResults, int firstResult) {
        return findProjectDBEntities(false, maxResults, firstResult);
    }

    private List<ProjectDB> findProjectDBEntities(boolean all, int maxResults, int firstResult) {
        
      
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ProjectDB.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        
    }

    public ProjectDB findProjectDB(Long id) {
       
     
            return em.find(ProjectDB.class, id);
       
    }

    public int getProjectDBCount() {
        
        
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ProjectDB> rt = cq.from(ProjectDB.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpaContollers;

import databaseentities.AccountDB;
import databaseentities.JiraprojectDB;
import databaseentities.ProjectreleaseDB;
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
public class ProjectreleaseDBJpaController implements Serializable {

    @PersistenceContext
    private EntityManager em;


    public void create(JiraprojectDB projectDB) throws RollbackFailureException, Exception {
       
            em.persist(projectDB);
           
    }

    public List<ProjectreleaseDB> findProjectreleaseDBEntities() {
        return findProjectreleaseDBEntities(true, -1, -1);
    }

    public List<ProjectreleaseDB> findProjectreleaseDBEntities(int maxResults, int firstResult) {
        return findProjectreleaseDBEntities(false, maxResults, firstResult);
    }

    private List<ProjectreleaseDB> findProjectreleaseDBEntities(boolean all, int maxResults, int firstResult) {
      
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ProjectreleaseDB.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
       
    }

    public ProjectreleaseDB findProjectreleaseDB(Long id) {
      
            return em.find(ProjectreleaseDB.class, id);
      
    }

    public List<ProjectreleaseDB> getProjectReleaseByIdProject(Long idProject){
        
     Query q = em.createNamedQuery("ProjectreleaseDB.findByIdproject");
        q.setParameter("idproject", idProject);
        List<ProjectreleaseDB> prrel = q.getResultList();
        return prrel;
    }

    public ProjectreleaseDB findByProjectAndRelease(Long idproject, Long idRelease) {
        
        Query q = em.createNamedQuery("ProjectreleaseDB.findByProjectAndRelease");
        q.setParameter("idproject", idproject);
        q.setParameter("idrelease", idRelease);
        
        List<ProjectreleaseDB> prRel = q.getResultList();
        if (prRel.size() > 0) {
            return prRel.get(0);
        } else {
            return null;
        }
    }
    
}

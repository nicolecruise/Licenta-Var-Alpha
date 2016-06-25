/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpaContollers;

import databaseentities.AccountDB;
import databaseentities.JiraprojectDB;
import databaseentities.ProjectreleaseDB;
import databaseentities.ProjectreleasesprintDB;
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
public class ProjectreleasesprintDBJpaController implements Serializable {

    @PersistenceContext
    private EntityManager em;

    public void create(JiraprojectDB projectDB) throws RollbackFailureException, Exception {

        em.persist(projectDB);

    }

    public List<ProjectreleasesprintDB> findProjectreleasesprintDBEntities() {
        return findProjectreleasesprintDBEntities(true, -1, -1);
    }

    public List<ProjectreleasesprintDB> findProjectreleasesprintDBEntities(int maxResults, int firstResult) {
        return findProjectreleasesprintDBEntities(false, maxResults, firstResult);
    }

    private List<ProjectreleasesprintDB> findProjectreleasesprintDBEntities(boolean all, int maxResults, int firstResult) {

        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(ProjectreleasesprintDB.class));
        Query q = em.createQuery(cq);
        if (!all) {
            q.setMaxResults(maxResults);
            q.setFirstResult(firstResult);
        }
        return q.getResultList();

    }

    public ProjectreleasesprintDB findProjectreleasesprintDB(Long id) {

        return em.find(ProjectreleasesprintDB.class, id);

    }

    public int getProjectreleasesprintDBCount() {

        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        Root<ProjectreleasesprintDB> rt = cq.from(ProjectreleasesprintDB.class);
        cq.select(em.getCriteriaBuilder().count(rt));
        Query q = em.createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();

    }
    
    public List<ProjectreleasesprintDB> getSprintsByProjectReleaseId(Long idProjectRelease){
        
     Query q = em.createNamedQuery("ProjectreleasesprintDB.findByIdprojectrelease");
        q.setParameter("idprojectrelease", idProjectRelease);
        List<ProjectreleasesprintDB> projectReleaseSprints = q.getResultList();
        return projectReleaseSprints;
    }
    
    public void update(ProjectreleasesprintDB projectreleasesprintDB){
          em.merge(projectreleasesprintDB);
    }
    
    public ProjectreleasesprintDB findByIdProjectReleaseAndIdSprint(Long idProjectRelease, Long idSprint){
    
    Query q = em.createNamedQuery("ProjectreleasesprintDB.findByIdProjectReleaseAndIdSprint");
        q.setParameter("idprojectrelease", idProjectRelease);
        q.setParameter("idsprint", idSprint);
        List<ProjectreleasesprintDB> sprintProjectsReleases = q.getResultList();
        if (sprintProjectsReleases.size() > 0) {
            return sprintProjectsReleases.get(0);
        } else {
            return null;
        }
    }

    public List<ProjectreleasesprintDB> findByProjectRelease(Long idProjectRelease) {
        Query q = em.createNamedQuery("ProjectreleasesprintDB.findByIdprojectrelease");
        q.setParameter("idprojectrelease", idProjectRelease);
      
        List<ProjectreleasesprintDB> sprintProjectsReleases = q.getResultList();
       
        return sprintProjectsReleases;
        
    }

}

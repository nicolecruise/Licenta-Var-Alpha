/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpaContollers;

import databaseentities.EfforttypeDB;
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
public class EfforttypeDBJpaController implements Serializable {

    @PersistenceContext
    private EntityManager em;

    public void create(JiraprojectDB projectDB) throws RollbackFailureException, Exception {
       
            em.persist(projectDB);
           
    }

    public List<EfforttypeDB> findEfforttypeDBEntities() {
        return findEfforttypeDBEntities(true, -1, -1);
    }

    public List<EfforttypeDB> findEfforttypeDBEntities(int maxResults, int firstResult) {
        return findEfforttypeDBEntities(false, maxResults, firstResult);
    }

    private List<EfforttypeDB> findEfforttypeDBEntities(boolean all, int maxResults, int firstResult) {
        
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(EfforttypeDB.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
       
    }

    public EfforttypeDB findEfforttypeDB(Long id) {
       
            return em.find(EfforttypeDB.class, id);
       
    }

    public int getEfforttypeDBCount() {
       
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<EfforttypeDB> rt = cq.from(EfforttypeDB.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        
    }
    
}

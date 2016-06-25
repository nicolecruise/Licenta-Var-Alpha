/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpaContollers;

import databaseentities.JiraprojectDB;
import databaseentities.JirasprintDB;
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
public class JirasprintDBJpaController implements Serializable {

   @PersistenceContext
    private EntityManager em;

     public void create(JiraprojectDB projectDB) throws RollbackFailureException, Exception {
       
            em.persist(projectDB);
           
    }

    public List<JirasprintDB> findJirasprintDBEntities() {
        return findJirasprintDBEntities(true, -1, -1);
    }

    public List<JirasprintDB> findJirasprintDBEntities(int maxResults, int firstResult) {
        return findJirasprintDBEntities(false, maxResults, firstResult);
    }

    private List<JirasprintDB> findJirasprintDBEntities(boolean all, int maxResults, int firstResult) {
       
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(JirasprintDB.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
       
    }

    public JirasprintDB findJirasprintDB(Long id) {
     
            return em.find(JirasprintDB.class, id);
        
    }

   
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpaContollers;

import databaseentities.JiraprojectDB;
import databaseentities.JirareleaseDB;
import java.io.Serializable;
import java.util.ArrayList;
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
public class JirareleaseDBJpaController implements Serializable {

    @PersistenceContext
    private EntityManager em;

     public void create(JiraprojectDB projectDB) throws RollbackFailureException, Exception {
       
            em.persist(projectDB);
           
    }

   
    public List<JirareleaseDB> findJirareleaseDBEntities() {
        return findJirareleaseDBEntities(true, -1, -1);
    }

    public List<JirareleaseDB> findJirareleaseDBEntities(int maxResults, int firstResult) {
        return findJirareleaseDBEntities(false, maxResults, firstResult);
    }

    private List<JirareleaseDB> findJirareleaseDBEntities(boolean all, int maxResults, int firstResult) {
      
    
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(JirareleaseDB.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
       
    }

    public JirareleaseDB findJirareleaseDB(Long id) {
    
            return em.find(JirareleaseDB.class, id);
        
    }

    

   
    
}

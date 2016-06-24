/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpaContollers;

import databaseentities.JirareleaseDB;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.UserTransaction;
import jpaContollers.exceptions.NonexistentEntityException;
import jpaContollers.exceptions.RollbackFailureException;

/**
 *
 * @author iradoi
 */
public class JirareleaseDBJpaController implements Serializable {

    public JirareleaseDBJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(JirareleaseDB jirareleaseDB) throws RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            em.persist(jirareleaseDB);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(JirareleaseDB jirareleaseDB) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            jirareleaseDB = em.merge(jirareleaseDB);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = jirareleaseDB.getId();
                if (findJirareleaseDB(id) == null) {
                    throw new NonexistentEntityException("The jirareleaseDB with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            JirareleaseDB jirareleaseDB;
            try {
                jirareleaseDB = em.getReference(JirareleaseDB.class, id);
                jirareleaseDB.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The jirareleaseDB with id " + id + " no longer exists.", enfe);
            }
            em.remove(jirareleaseDB);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<JirareleaseDB> findJirareleaseDBEntities() {
        return findJirareleaseDBEntities(true, -1, -1);
    }

    public List<JirareleaseDB> findJirareleaseDBEntities(int maxResults, int firstResult) {
        return findJirareleaseDBEntities(false, maxResults, firstResult);
    }

    private List<JirareleaseDB> findJirareleaseDBEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(JirareleaseDB.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public JirareleaseDB findJirareleaseDB(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(JirareleaseDB.class, id);
        } finally {
            em.close();
        }
    }

    public int getJirareleaseDBCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<JirareleaseDB> rt = cq.from(JirareleaseDB.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}

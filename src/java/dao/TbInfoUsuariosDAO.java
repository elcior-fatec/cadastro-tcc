package dao;

import dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import models.TbLoginUsuarios;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import models.TbInfoUsuarios;

/**
 *
 * @author elcior.carvalho
 */
public class TbInfoUsuariosDAO implements Serializable {

    public TbInfoUsuariosDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TbInfoUsuarios tbInfoUsuarios) {
        if (tbInfoUsuarios.getTbLoginUsuariosCollection() == null) {
            tbInfoUsuarios.setTbLoginUsuariosCollection(new ArrayList<TbLoginUsuarios>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<TbLoginUsuarios> attachedTbLoginUsuariosCollection = new ArrayList<TbLoginUsuarios>();
            for (TbLoginUsuarios tbLoginUsuariosCollectionTbLoginUsuariosToAttach : tbInfoUsuarios.getTbLoginUsuariosCollection()) {
                tbLoginUsuariosCollectionTbLoginUsuariosToAttach = em.getReference(tbLoginUsuariosCollectionTbLoginUsuariosToAttach.getClass(), tbLoginUsuariosCollectionTbLoginUsuariosToAttach.getIdLogin());
                attachedTbLoginUsuariosCollection.add(tbLoginUsuariosCollectionTbLoginUsuariosToAttach);
            }
            tbInfoUsuarios.setTbLoginUsuariosCollection(attachedTbLoginUsuariosCollection);
            em.persist(tbInfoUsuarios);
            for (TbLoginUsuarios tbLoginUsuariosCollectionTbLoginUsuarios : tbInfoUsuarios.getTbLoginUsuariosCollection()) {
                TbInfoUsuarios oldIdUserOfTbLoginUsuariosCollectionTbLoginUsuarios = tbLoginUsuariosCollectionTbLoginUsuarios.getIdUser();
                tbLoginUsuariosCollectionTbLoginUsuarios.setIdUser(tbInfoUsuarios);
                tbLoginUsuariosCollectionTbLoginUsuarios = em.merge(tbLoginUsuariosCollectionTbLoginUsuarios);
                if (oldIdUserOfTbLoginUsuariosCollectionTbLoginUsuarios != null) {
                    oldIdUserOfTbLoginUsuariosCollectionTbLoginUsuarios.getTbLoginUsuariosCollection().remove(tbLoginUsuariosCollectionTbLoginUsuarios);
                    oldIdUserOfTbLoginUsuariosCollectionTbLoginUsuarios = em.merge(oldIdUserOfTbLoginUsuariosCollectionTbLoginUsuarios);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TbInfoUsuarios tbInfoUsuarios) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TbInfoUsuarios persistentTbInfoUsuarios = em.find(TbInfoUsuarios.class, tbInfoUsuarios.getIdUser());
            Collection<TbLoginUsuarios> tbLoginUsuariosCollectionOld = persistentTbInfoUsuarios.getTbLoginUsuariosCollection();
            Collection<TbLoginUsuarios> tbLoginUsuariosCollectionNew = tbInfoUsuarios.getTbLoginUsuariosCollection();
            Collection<TbLoginUsuarios> attachedTbLoginUsuariosCollectionNew = new ArrayList<TbLoginUsuarios>();
            for (TbLoginUsuarios tbLoginUsuariosCollectionNewTbLoginUsuariosToAttach : tbLoginUsuariosCollectionNew) {
                tbLoginUsuariosCollectionNewTbLoginUsuariosToAttach = em.getReference(tbLoginUsuariosCollectionNewTbLoginUsuariosToAttach.getClass(), tbLoginUsuariosCollectionNewTbLoginUsuariosToAttach.getIdLogin());
                attachedTbLoginUsuariosCollectionNew.add(tbLoginUsuariosCollectionNewTbLoginUsuariosToAttach);
            }
            tbLoginUsuariosCollectionNew = attachedTbLoginUsuariosCollectionNew;
            tbInfoUsuarios.setTbLoginUsuariosCollection(tbLoginUsuariosCollectionNew);
            tbInfoUsuarios = em.merge(tbInfoUsuarios);
            for (TbLoginUsuarios tbLoginUsuariosCollectionOldTbLoginUsuarios : tbLoginUsuariosCollectionOld) {
                if (!tbLoginUsuariosCollectionNew.contains(tbLoginUsuariosCollectionOldTbLoginUsuarios)) {
                    tbLoginUsuariosCollectionOldTbLoginUsuarios.setIdUser(null);
                    tbLoginUsuariosCollectionOldTbLoginUsuarios = em.merge(tbLoginUsuariosCollectionOldTbLoginUsuarios);
                }
            }
            for (TbLoginUsuarios tbLoginUsuariosCollectionNewTbLoginUsuarios : tbLoginUsuariosCollectionNew) {
                if (!tbLoginUsuariosCollectionOld.contains(tbLoginUsuariosCollectionNewTbLoginUsuarios)) {
                    TbInfoUsuarios oldIdUserOfTbLoginUsuariosCollectionNewTbLoginUsuarios = tbLoginUsuariosCollectionNewTbLoginUsuarios.getIdUser();
                    tbLoginUsuariosCollectionNewTbLoginUsuarios.setIdUser(tbInfoUsuarios);
                    tbLoginUsuariosCollectionNewTbLoginUsuarios = em.merge(tbLoginUsuariosCollectionNewTbLoginUsuarios);
                    if (oldIdUserOfTbLoginUsuariosCollectionNewTbLoginUsuarios != null && !oldIdUserOfTbLoginUsuariosCollectionNewTbLoginUsuarios.equals(tbInfoUsuarios)) {
                        oldIdUserOfTbLoginUsuariosCollectionNewTbLoginUsuarios.getTbLoginUsuariosCollection().remove(tbLoginUsuariosCollectionNewTbLoginUsuarios);
                        oldIdUserOfTbLoginUsuariosCollectionNewTbLoginUsuarios = em.merge(oldIdUserOfTbLoginUsuariosCollectionNewTbLoginUsuarios);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tbInfoUsuarios.getIdUser();
                if (findTbInfoUsuarios(id) == null) {
                    throw new NonexistentEntityException("The tbInfoUsuarios with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TbInfoUsuarios tbInfoUsuarios;
            try {
                tbInfoUsuarios = em.getReference(TbInfoUsuarios.class, id);
                tbInfoUsuarios.getIdUser();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tbInfoUsuarios with id " + id + " no longer exists.", enfe);
            }
            Collection<TbLoginUsuarios> tbLoginUsuariosCollection = tbInfoUsuarios.getTbLoginUsuariosCollection();
            for (TbLoginUsuarios tbLoginUsuariosCollectionTbLoginUsuarios : tbLoginUsuariosCollection) {
                tbLoginUsuariosCollectionTbLoginUsuarios.setIdUser(null);
                tbLoginUsuariosCollectionTbLoginUsuarios = em.merge(tbLoginUsuariosCollectionTbLoginUsuarios);
            }
            em.remove(tbInfoUsuarios);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TbInfoUsuarios> findTbInfoUsuariosEntities() {
        return findTbInfoUsuariosEntities(true, -1, -1);
    }

    public List<TbInfoUsuarios> findTbInfoUsuariosEntities(int maxResults, int firstResult) {
        return findTbInfoUsuariosEntities(false, maxResults, firstResult);
    }

    private List<TbInfoUsuarios> findTbInfoUsuariosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TbInfoUsuarios.class));
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

    public TbInfoUsuarios findTbInfoUsuarios(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TbInfoUsuarios.class, id);
        } finally {
            em.close();
        }
    }

    public int getTbInfoUsuariosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TbInfoUsuarios> rt = cq.from(TbInfoUsuarios.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}

package dao;

import dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import models.TbInfoUsuarios;
import models.TbLoginUsuarios;

/**
 *
 * @author elcior.carvalho
 */
public class TbLoginUsuariosDAO implements Serializable {

    public TbLoginUsuariosDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TbLoginUsuarios tbLoginUsuarios) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TbInfoUsuarios idUser = tbLoginUsuarios.getIdUser();
            if (idUser != null) {
                idUser = em.getReference(idUser.getClass(), idUser.getIdUser());
                tbLoginUsuarios.setIdUser(idUser);
            }
            em.persist(tbLoginUsuarios);
            if (idUser != null) {
                idUser.getTbLoginUsuariosCollection().add(tbLoginUsuarios);
                idUser = em.merge(idUser);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TbLoginUsuarios tbLoginUsuarios) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TbLoginUsuarios persistentTbLoginUsuarios = em.find(TbLoginUsuarios.class, tbLoginUsuarios.getIdLogin());
            TbInfoUsuarios idUserOld = persistentTbLoginUsuarios.getIdUser();
            TbInfoUsuarios idUserNew = tbLoginUsuarios.getIdUser();
            if (idUserNew != null) {
                idUserNew = em.getReference(idUserNew.getClass(), idUserNew.getIdUser());
                tbLoginUsuarios.setIdUser(idUserNew);
            }
            tbLoginUsuarios = em.merge(tbLoginUsuarios);
            if (idUserOld != null && !idUserOld.equals(idUserNew)) {
                idUserOld.getTbLoginUsuariosCollection().remove(tbLoginUsuarios);
                idUserOld = em.merge(idUserOld);
            }
            if (idUserNew != null && !idUserNew.equals(idUserOld)) {
                idUserNew.getTbLoginUsuariosCollection().add(tbLoginUsuarios);
                idUserNew = em.merge(idUserNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tbLoginUsuarios.getIdLogin();
                if (findTbLoginUsuarios(id) == null) {
                    throw new NonexistentEntityException("The tbLoginUsuarios with id " + id + " no longer exists.");
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
            TbLoginUsuarios tbLoginUsuarios;
            try {
                tbLoginUsuarios = em.getReference(TbLoginUsuarios.class, id);
                tbLoginUsuarios.getIdLogin();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tbLoginUsuarios with id " + id + " no longer exists.", enfe);
            }
            TbInfoUsuarios idUser = tbLoginUsuarios.getIdUser();
            if (idUser != null) {
                idUser.getTbLoginUsuariosCollection().remove(tbLoginUsuarios);
                idUser = em.merge(idUser);
            }
            em.remove(tbLoginUsuarios);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TbLoginUsuarios> findTbLoginUsuariosEntities() {
        return findTbLoginUsuariosEntities(true, -1, -1);
    }

    public List<TbLoginUsuarios> findTbLoginUsuariosEntities(int maxResults, int firstResult) {
        return findTbLoginUsuariosEntities(false, maxResults, firstResult);
    }

    private List<TbLoginUsuarios> findTbLoginUsuariosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TbLoginUsuarios.class));
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

    public TbLoginUsuarios findTbLoginUsuarios(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TbLoginUsuarios.class, id);
        } finally {
            em.close();
        }
    }

    public int getTbLoginUsuariosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TbLoginUsuarios> rt = cq.from(TbLoginUsuarios.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}

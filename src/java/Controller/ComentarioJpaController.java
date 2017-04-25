/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Controller.exceptions.NonexistentEntityException;
import Controller.exceptions.PreexistingEntityException;
import Controller.exceptions.RollbackFailureException;
import Entity.Comentario;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Entity.Restaurante;
import Entity.Usuario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Produccion
 */
public class ComentarioJpaController implements Serializable {

    public ComentarioJpaController(EntityManagerFactory emf) {

        this.emf = emf;
    }

    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Comentario comentario) throws PreexistingEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        UserTransaction utx = (UserTransaction) em.getTransaction();
        try {
            utx.begin();
            em = getEntityManager();
            Restaurante codRestaurante = comentario.getCodRestaurante();
            if (codRestaurante != null) {
                codRestaurante = em.getReference(codRestaurante.getClass(), codRestaurante.getCodRestaurante());
                comentario.setCodRestaurante(codRestaurante);
            }
            Usuario codUsuario = comentario.getCodUsuario();
            if (codUsuario != null) {
                codUsuario = em.getReference(codUsuario.getClass(), codUsuario.getCodUsuario());
                comentario.setCodUsuario(codUsuario);
            }
            em.persist(comentario);
            if (codRestaurante != null) {
                codRestaurante.getComentarioCollection().add(comentario);
                codRestaurante = em.merge(codRestaurante);
            }
            if (codUsuario != null) {
                codUsuario.getComentarioCollection().add(comentario);
                codUsuario = em.merge(codUsuario);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findComentario(comentario.getCodComentario()) != null) {
                throw new PreexistingEntityException("Comentario " + comentario + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Comentario comentario) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        UserTransaction utx = (UserTransaction) em.getTransaction();
        try {
            utx.begin();
            em = getEntityManager();
            Comentario persistentComentario = em.find(Comentario.class, comentario.getCodComentario());
            Restaurante codRestauranteOld = persistentComentario.getCodRestaurante();
            Restaurante codRestauranteNew = comentario.getCodRestaurante();
            Usuario codUsuarioOld = persistentComentario.getCodUsuario();
            Usuario codUsuarioNew = comentario.getCodUsuario();
            if (codRestauranteNew != null) {
                codRestauranteNew = em.getReference(codRestauranteNew.getClass(), codRestauranteNew.getCodRestaurante());
                comentario.setCodRestaurante(codRestauranteNew);
            }
            if (codUsuarioNew != null) {
                codUsuarioNew = em.getReference(codUsuarioNew.getClass(), codUsuarioNew.getCodUsuario());
                comentario.setCodUsuario(codUsuarioNew);
            }
            comentario = em.merge(comentario);
            if (codRestauranteOld != null && !codRestauranteOld.equals(codRestauranteNew)) {
                codRestauranteOld.getComentarioCollection().remove(comentario);
                codRestauranteOld = em.merge(codRestauranteOld);
            }
            if (codRestauranteNew != null && !codRestauranteNew.equals(codRestauranteOld)) {
                codRestauranteNew.getComentarioCollection().add(comentario);
                codRestauranteNew = em.merge(codRestauranteNew);
            }
            if (codUsuarioOld != null && !codUsuarioOld.equals(codUsuarioNew)) {
                codUsuarioOld.getComentarioCollection().remove(comentario);
                codUsuarioOld = em.merge(codUsuarioOld);
            }
            if (codUsuarioNew != null && !codUsuarioNew.equals(codUsuarioOld)) {
                codUsuarioNew.getComentarioCollection().add(comentario);
                codUsuarioNew = em.merge(codUsuarioNew);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = comentario.getCodComentario();
                if (findComentario(id) == null) {
                    throw new NonexistentEntityException("The comentario with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        UserTransaction utx = (UserTransaction) em.getTransaction();
        try {
            utx.begin();
            em = getEntityManager();
            Comentario comentario;
            try {
                comentario = em.getReference(Comentario.class, id);
                comentario.getCodComentario();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The comentario with id " + id + " no longer exists.", enfe);
            }
            Restaurante codRestaurante = comentario.getCodRestaurante();
            if (codRestaurante != null) {
                codRestaurante.getComentarioCollection().remove(comentario);
                codRestaurante = em.merge(codRestaurante);
            }
            Usuario codUsuario = comentario.getCodUsuario();
            if (codUsuario != null) {
                codUsuario.getComentarioCollection().remove(comentario);
                codUsuario = em.merge(codUsuario);
            }
            em.remove(comentario);
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

    public List<Comentario> findComentarioEntities() {
        return findComentarioEntities(true, -1, -1);
    }

    public List<Comentario> findComentarioEntities(int maxResults, int firstResult) {
        return findComentarioEntities(false, maxResults, firstResult);
    }

    private List<Comentario> findComentarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Comentario.class));
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

    public Comentario findComentario(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Comentario.class, id);
        } finally {
            em.close();
        }
    }

    public int getComentarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Comentario> rt = cq.from(Comentario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}

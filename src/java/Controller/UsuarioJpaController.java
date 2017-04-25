/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Controller.exceptions.NonexistentEntityException;
import Controller.exceptions.PreexistingEntityException;
import Controller.exceptions.RollbackFailureException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Entity.Calificacion;
import java.util.ArrayList;
import java.util.Collection;
import Entity.Comentario;
import Entity.Usuario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Produccion
 */
public class UsuarioJpaController implements Serializable {

    public UsuarioJpaController(EntityManagerFactory emf) {

        this.emf = emf;
    }

    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Usuario usuario) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (usuario.getCalificacionCollection() == null) {
            usuario.setCalificacionCollection(new ArrayList<Calificacion>());
        }
        if (usuario.getComentarioCollection() == null) {
            usuario.setComentarioCollection(new ArrayList<Comentario>());
        }
        EntityManager em = null;
        UserTransaction utx = (UserTransaction) em.getTransaction();
        try {
            utx.begin();
            em = getEntityManager();
            Collection<Calificacion> attachedCalificacionCollection = new ArrayList<Calificacion>();
            for (Calificacion calificacionCollectionCalificacionToAttach : usuario.getCalificacionCollection()) {
                calificacionCollectionCalificacionToAttach = em.getReference(calificacionCollectionCalificacionToAttach.getClass(), calificacionCollectionCalificacionToAttach.getCodValor());
                attachedCalificacionCollection.add(calificacionCollectionCalificacionToAttach);
            }
            usuario.setCalificacionCollection(attachedCalificacionCollection);
            Collection<Comentario> attachedComentarioCollection = new ArrayList<Comentario>();
            for (Comentario comentarioCollectionComentarioToAttach : usuario.getComentarioCollection()) {
                comentarioCollectionComentarioToAttach = em.getReference(comentarioCollectionComentarioToAttach.getClass(), comentarioCollectionComentarioToAttach.getCodComentario());
                attachedComentarioCollection.add(comentarioCollectionComentarioToAttach);
            }
            usuario.setComentarioCollection(attachedComentarioCollection);
            em.persist(usuario);
            for (Calificacion calificacionCollectionCalificacion : usuario.getCalificacionCollection()) {
                Usuario oldCodUsuarioOfCalificacionCollectionCalificacion = calificacionCollectionCalificacion.getCodUsuario();
                calificacionCollectionCalificacion.setCodUsuario(usuario);
                calificacionCollectionCalificacion = em.merge(calificacionCollectionCalificacion);
                if (oldCodUsuarioOfCalificacionCollectionCalificacion != null) {
                    oldCodUsuarioOfCalificacionCollectionCalificacion.getCalificacionCollection().remove(calificacionCollectionCalificacion);
                    oldCodUsuarioOfCalificacionCollectionCalificacion = em.merge(oldCodUsuarioOfCalificacionCollectionCalificacion);
                }
            }
            for (Comentario comentarioCollectionComentario : usuario.getComentarioCollection()) {
                Usuario oldCodUsuarioOfComentarioCollectionComentario = comentarioCollectionComentario.getCodUsuario();
                comentarioCollectionComentario.setCodUsuario(usuario);
                comentarioCollectionComentario = em.merge(comentarioCollectionComentario);
                if (oldCodUsuarioOfComentarioCollectionComentario != null) {
                    oldCodUsuarioOfComentarioCollectionComentario.getComentarioCollection().remove(comentarioCollectionComentario);
                    oldCodUsuarioOfComentarioCollectionComentario = em.merge(oldCodUsuarioOfComentarioCollectionComentario);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findUsuario(usuario.getCodUsuario()) != null) {
                throw new PreexistingEntityException("Usuario " + usuario + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Usuario usuario) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        UserTransaction utx = (UserTransaction) em.getTransaction();
        try {
            utx.begin();
            em = getEntityManager();
            Usuario persistentUsuario = em.find(Usuario.class, usuario.getCodUsuario());
            Collection<Calificacion> calificacionCollectionOld = persistentUsuario.getCalificacionCollection();
            Collection<Calificacion> calificacionCollectionNew = usuario.getCalificacionCollection();
            Collection<Comentario> comentarioCollectionOld = persistentUsuario.getComentarioCollection();
            Collection<Comentario> comentarioCollectionNew = usuario.getComentarioCollection();
            Collection<Calificacion> attachedCalificacionCollectionNew = new ArrayList<Calificacion>();
            for (Calificacion calificacionCollectionNewCalificacionToAttach : calificacionCollectionNew) {
                calificacionCollectionNewCalificacionToAttach = em.getReference(calificacionCollectionNewCalificacionToAttach.getClass(), calificacionCollectionNewCalificacionToAttach.getCodValor());
                attachedCalificacionCollectionNew.add(calificacionCollectionNewCalificacionToAttach);
            }
            calificacionCollectionNew = attachedCalificacionCollectionNew;
            usuario.setCalificacionCollection(calificacionCollectionNew);
            Collection<Comentario> attachedComentarioCollectionNew = new ArrayList<Comentario>();
            for (Comentario comentarioCollectionNewComentarioToAttach : comentarioCollectionNew) {
                comentarioCollectionNewComentarioToAttach = em.getReference(comentarioCollectionNewComentarioToAttach.getClass(), comentarioCollectionNewComentarioToAttach.getCodComentario());
                attachedComentarioCollectionNew.add(comentarioCollectionNewComentarioToAttach);
            }
            comentarioCollectionNew = attachedComentarioCollectionNew;
            usuario.setComentarioCollection(comentarioCollectionNew);
            usuario = em.merge(usuario);
            for (Calificacion calificacionCollectionOldCalificacion : calificacionCollectionOld) {
                if (!calificacionCollectionNew.contains(calificacionCollectionOldCalificacion)) {
                    calificacionCollectionOldCalificacion.setCodUsuario(null);
                    calificacionCollectionOldCalificacion = em.merge(calificacionCollectionOldCalificacion);
                }
            }
            for (Calificacion calificacionCollectionNewCalificacion : calificacionCollectionNew) {
                if (!calificacionCollectionOld.contains(calificacionCollectionNewCalificacion)) {
                    Usuario oldCodUsuarioOfCalificacionCollectionNewCalificacion = calificacionCollectionNewCalificacion.getCodUsuario();
                    calificacionCollectionNewCalificacion.setCodUsuario(usuario);
                    calificacionCollectionNewCalificacion = em.merge(calificacionCollectionNewCalificacion);
                    if (oldCodUsuarioOfCalificacionCollectionNewCalificacion != null && !oldCodUsuarioOfCalificacionCollectionNewCalificacion.equals(usuario)) {
                        oldCodUsuarioOfCalificacionCollectionNewCalificacion.getCalificacionCollection().remove(calificacionCollectionNewCalificacion);
                        oldCodUsuarioOfCalificacionCollectionNewCalificacion = em.merge(oldCodUsuarioOfCalificacionCollectionNewCalificacion);
                    }
                }
            }
            for (Comentario comentarioCollectionOldComentario : comentarioCollectionOld) {
                if (!comentarioCollectionNew.contains(comentarioCollectionOldComentario)) {
                    comentarioCollectionOldComentario.setCodUsuario(null);
                    comentarioCollectionOldComentario = em.merge(comentarioCollectionOldComentario);
                }
            }
            for (Comentario comentarioCollectionNewComentario : comentarioCollectionNew) {
                if (!comentarioCollectionOld.contains(comentarioCollectionNewComentario)) {
                    Usuario oldCodUsuarioOfComentarioCollectionNewComentario = comentarioCollectionNewComentario.getCodUsuario();
                    comentarioCollectionNewComentario.setCodUsuario(usuario);
                    comentarioCollectionNewComentario = em.merge(comentarioCollectionNewComentario);
                    if (oldCodUsuarioOfComentarioCollectionNewComentario != null && !oldCodUsuarioOfComentarioCollectionNewComentario.equals(usuario)) {
                        oldCodUsuarioOfComentarioCollectionNewComentario.getComentarioCollection().remove(comentarioCollectionNewComentario);
                        oldCodUsuarioOfComentarioCollectionNewComentario = em.merge(oldCodUsuarioOfComentarioCollectionNewComentario);
                    }
                }
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
                Integer id = usuario.getCodUsuario();
                if (findUsuario(id) == null) {
                    throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.");
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
            Usuario usuario;
            try {
                usuario = em.getReference(Usuario.class, id);
                usuario.getCodUsuario();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.", enfe);
            }
            Collection<Calificacion> calificacionCollection = usuario.getCalificacionCollection();
            for (Calificacion calificacionCollectionCalificacion : calificacionCollection) {
                calificacionCollectionCalificacion.setCodUsuario(null);
                calificacionCollectionCalificacion = em.merge(calificacionCollectionCalificacion);
            }
            Collection<Comentario> comentarioCollection = usuario.getComentarioCollection();
            for (Comentario comentarioCollectionComentario : comentarioCollection) {
                comentarioCollectionComentario.setCodUsuario(null);
                comentarioCollectionComentario = em.merge(comentarioCollectionComentario);
            }
            em.remove(usuario);
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

    public List<Usuario> findUsuarioEntities() {
        return findUsuarioEntities(true, -1, -1);
    }

    public List<Usuario> findUsuarioEntities(int maxResults, int firstResult) {
        return findUsuarioEntities(false, maxResults, firstResult);
    }

    private List<Usuario> findUsuarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Usuario.class));
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

    public Usuario findUsuario(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usuario.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsuarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Usuario> rt = cq.from(Usuario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Controller.exceptions.NonexistentEntityException;
import Controller.exceptions.RollbackFailureException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Entity.Rangohora;
import Entity.Horario;
import Entity.Tipocomida;
import Entity.Calificacion;
import java.util.ArrayList;
import java.util.Collection;
import Entity.Comentario;
import Entity.Restaurante;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Produccion
 */
public class RestauranteJpaController implements Serializable {

    public RestauranteJpaController(EntityManagerFactory emf) {

        this.emf = emf;
    }

    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Restaurante restaurante) throws RollbackFailureException, Exception {
        if (restaurante.getCalificacionCollection() == null) {
            restaurante.setCalificacionCollection(new ArrayList<Calificacion>());
        }
        if (restaurante.getComentarioCollection() == null) {
            restaurante.setComentarioCollection(new ArrayList<Comentario>());
        }
        EntityManager em = null;
        UserTransaction utx = (UserTransaction) em.getTransaction();
        try {
            utx.begin();
            em = getEntityManager();
            Rangohora codrangoHora = restaurante.getCodrangoHora();
            if (codrangoHora != null) {
                codrangoHora = em.getReference(codrangoHora.getClass(), codrangoHora.getCodRHora());
                restaurante.setCodrangoHora(codrangoHora);
            }
            Horario codHorario = restaurante.getCodHorario();
            if (codHorario != null) {
                codHorario = em.getReference(codHorario.getClass(), codHorario.getCodHorario());
                restaurante.setCodHorario(codHorario);
            }
            Tipocomida codTipoComida = restaurante.getCodTipoComida();
            if (codTipoComida != null) {
                codTipoComida = em.getReference(codTipoComida.getClass(), codTipoComida.getCodTComida());
                restaurante.setCodTipoComida(codTipoComida);
            }
            Collection<Calificacion> attachedCalificacionCollection = new ArrayList<Calificacion>();
            for (Calificacion calificacionCollectionCalificacionToAttach : restaurante.getCalificacionCollection()) {
                calificacionCollectionCalificacionToAttach = em.getReference(calificacionCollectionCalificacionToAttach.getClass(), calificacionCollectionCalificacionToAttach.getCodValor());
                attachedCalificacionCollection.add(calificacionCollectionCalificacionToAttach);
            }
            restaurante.setCalificacionCollection(attachedCalificacionCollection);
            Collection<Comentario> attachedComentarioCollection = new ArrayList<Comentario>();
            for (Comentario comentarioCollectionComentarioToAttach : restaurante.getComentarioCollection()) {
                comentarioCollectionComentarioToAttach = em.getReference(comentarioCollectionComentarioToAttach.getClass(), comentarioCollectionComentarioToAttach.getCodComentario());
                attachedComentarioCollection.add(comentarioCollectionComentarioToAttach);
            }
            restaurante.setComentarioCollection(attachedComentarioCollection);
            em.persist(restaurante);
            if (codrangoHora != null) {
                codrangoHora.getRestauranteCollection().add(restaurante);
                codrangoHora = em.merge(codrangoHora);
            }
            if (codHorario != null) {
                codHorario.getRestauranteCollection().add(restaurante);
                codHorario = em.merge(codHorario);
            }
            if (codTipoComida != null) {
                codTipoComida.getRestauranteCollection().add(restaurante);
                codTipoComida = em.merge(codTipoComida);
            }
            for (Calificacion calificacionCollectionCalificacion : restaurante.getCalificacionCollection()) {
                Restaurante oldCodRestauranteOfCalificacionCollectionCalificacion = calificacionCollectionCalificacion.getCodRestaurante();
                calificacionCollectionCalificacion.setCodRestaurante(restaurante);
                calificacionCollectionCalificacion = em.merge(calificacionCollectionCalificacion);
                if (oldCodRestauranteOfCalificacionCollectionCalificacion != null) {
                    oldCodRestauranteOfCalificacionCollectionCalificacion.getCalificacionCollection().remove(calificacionCollectionCalificacion);
                    oldCodRestauranteOfCalificacionCollectionCalificacion = em.merge(oldCodRestauranteOfCalificacionCollectionCalificacion);
                }
            }
            for (Comentario comentarioCollectionComentario : restaurante.getComentarioCollection()) {
                Restaurante oldCodRestauranteOfComentarioCollectionComentario = comentarioCollectionComentario.getCodRestaurante();
                comentarioCollectionComentario.setCodRestaurante(restaurante);
                comentarioCollectionComentario = em.merge(comentarioCollectionComentario);
                if (oldCodRestauranteOfComentarioCollectionComentario != null) {
                    oldCodRestauranteOfComentarioCollectionComentario.getComentarioCollection().remove(comentarioCollectionComentario);
                    oldCodRestauranteOfComentarioCollectionComentario = em.merge(oldCodRestauranteOfComentarioCollectionComentario);
                }
            }
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

    public void edit(Restaurante restaurante) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        UserTransaction utx = (UserTransaction) em.getTransaction();
        try {
            utx.begin();
            em = getEntityManager();
            Restaurante persistentRestaurante = em.find(Restaurante.class, restaurante.getCodRestaurante());
            Rangohora codrangoHoraOld = persistentRestaurante.getCodrangoHora();
            Rangohora codrangoHoraNew = restaurante.getCodrangoHora();
            Horario codHorarioOld = persistentRestaurante.getCodHorario();
            Horario codHorarioNew = restaurante.getCodHorario();
            Tipocomida codTipoComidaOld = persistentRestaurante.getCodTipoComida();
            Tipocomida codTipoComidaNew = restaurante.getCodTipoComida();
            Collection<Calificacion> calificacionCollectionOld = persistentRestaurante.getCalificacionCollection();
            Collection<Calificacion> calificacionCollectionNew = restaurante.getCalificacionCollection();
            Collection<Comentario> comentarioCollectionOld = persistentRestaurante.getComentarioCollection();
            Collection<Comentario> comentarioCollectionNew = restaurante.getComentarioCollection();
            if (codrangoHoraNew != null) {
                codrangoHoraNew = em.getReference(codrangoHoraNew.getClass(), codrangoHoraNew.getCodRHora());
                restaurante.setCodrangoHora(codrangoHoraNew);
            }
            if (codHorarioNew != null) {
                codHorarioNew = em.getReference(codHorarioNew.getClass(), codHorarioNew.getCodHorario());
                restaurante.setCodHorario(codHorarioNew);
            }
            if (codTipoComidaNew != null) {
                codTipoComidaNew = em.getReference(codTipoComidaNew.getClass(), codTipoComidaNew.getCodTComida());
                restaurante.setCodTipoComida(codTipoComidaNew);
            }
            Collection<Calificacion> attachedCalificacionCollectionNew = new ArrayList<Calificacion>();
            for (Calificacion calificacionCollectionNewCalificacionToAttach : calificacionCollectionNew) {
                calificacionCollectionNewCalificacionToAttach = em.getReference(calificacionCollectionNewCalificacionToAttach.getClass(), calificacionCollectionNewCalificacionToAttach.getCodValor());
                attachedCalificacionCollectionNew.add(calificacionCollectionNewCalificacionToAttach);
            }
            calificacionCollectionNew = attachedCalificacionCollectionNew;
            restaurante.setCalificacionCollection(calificacionCollectionNew);
            Collection<Comentario> attachedComentarioCollectionNew = new ArrayList<Comentario>();
            for (Comentario comentarioCollectionNewComentarioToAttach : comentarioCollectionNew) {
                comentarioCollectionNewComentarioToAttach = em.getReference(comentarioCollectionNewComentarioToAttach.getClass(), comentarioCollectionNewComentarioToAttach.getCodComentario());
                attachedComentarioCollectionNew.add(comentarioCollectionNewComentarioToAttach);
            }
            comentarioCollectionNew = attachedComentarioCollectionNew;
            restaurante.setComentarioCollection(comentarioCollectionNew);
            restaurante = em.merge(restaurante);
            if (codrangoHoraOld != null && !codrangoHoraOld.equals(codrangoHoraNew)) {
                codrangoHoraOld.getRestauranteCollection().remove(restaurante);
                codrangoHoraOld = em.merge(codrangoHoraOld);
            }
            if (codrangoHoraNew != null && !codrangoHoraNew.equals(codrangoHoraOld)) {
                codrangoHoraNew.getRestauranteCollection().add(restaurante);
                codrangoHoraNew = em.merge(codrangoHoraNew);
            }
            if (codHorarioOld != null && !codHorarioOld.equals(codHorarioNew)) {
                codHorarioOld.getRestauranteCollection().remove(restaurante);
                codHorarioOld = em.merge(codHorarioOld);
            }
            if (codHorarioNew != null && !codHorarioNew.equals(codHorarioOld)) {
                codHorarioNew.getRestauranteCollection().add(restaurante);
                codHorarioNew = em.merge(codHorarioNew);
            }
            if (codTipoComidaOld != null && !codTipoComidaOld.equals(codTipoComidaNew)) {
                codTipoComidaOld.getRestauranteCollection().remove(restaurante);
                codTipoComidaOld = em.merge(codTipoComidaOld);
            }
            if (codTipoComidaNew != null && !codTipoComidaNew.equals(codTipoComidaOld)) {
                codTipoComidaNew.getRestauranteCollection().add(restaurante);
                codTipoComidaNew = em.merge(codTipoComidaNew);
            }
            for (Calificacion calificacionCollectionOldCalificacion : calificacionCollectionOld) {
                if (!calificacionCollectionNew.contains(calificacionCollectionOldCalificacion)) {
                    calificacionCollectionOldCalificacion.setCodRestaurante(null);
                    calificacionCollectionOldCalificacion = em.merge(calificacionCollectionOldCalificacion);
                }
            }
            for (Calificacion calificacionCollectionNewCalificacion : calificacionCollectionNew) {
                if (!calificacionCollectionOld.contains(calificacionCollectionNewCalificacion)) {
                    Restaurante oldCodRestauranteOfCalificacionCollectionNewCalificacion = calificacionCollectionNewCalificacion.getCodRestaurante();
                    calificacionCollectionNewCalificacion.setCodRestaurante(restaurante);
                    calificacionCollectionNewCalificacion = em.merge(calificacionCollectionNewCalificacion);
                    if (oldCodRestauranteOfCalificacionCollectionNewCalificacion != null && !oldCodRestauranteOfCalificacionCollectionNewCalificacion.equals(restaurante)) {
                        oldCodRestauranteOfCalificacionCollectionNewCalificacion.getCalificacionCollection().remove(calificacionCollectionNewCalificacion);
                        oldCodRestauranteOfCalificacionCollectionNewCalificacion = em.merge(oldCodRestauranteOfCalificacionCollectionNewCalificacion);
                    }
                }
            }
            for (Comentario comentarioCollectionOldComentario : comentarioCollectionOld) {
                if (!comentarioCollectionNew.contains(comentarioCollectionOldComentario)) {
                    comentarioCollectionOldComentario.setCodRestaurante(null);
                    comentarioCollectionOldComentario = em.merge(comentarioCollectionOldComentario);
                }
            }
            for (Comentario comentarioCollectionNewComentario : comentarioCollectionNew) {
                if (!comentarioCollectionOld.contains(comentarioCollectionNewComentario)) {
                    Restaurante oldCodRestauranteOfComentarioCollectionNewComentario = comentarioCollectionNewComentario.getCodRestaurante();
                    comentarioCollectionNewComentario.setCodRestaurante(restaurante);
                    comentarioCollectionNewComentario = em.merge(comentarioCollectionNewComentario);
                    if (oldCodRestauranteOfComentarioCollectionNewComentario != null && !oldCodRestauranteOfComentarioCollectionNewComentario.equals(restaurante)) {
                        oldCodRestauranteOfComentarioCollectionNewComentario.getComentarioCollection().remove(comentarioCollectionNewComentario);
                        oldCodRestauranteOfComentarioCollectionNewComentario = em.merge(oldCodRestauranteOfComentarioCollectionNewComentario);
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
                Integer id = restaurante.getCodRestaurante();
                if (findRestaurante(id) == null) {
                    throw new NonexistentEntityException("The restaurante with id " + id + " no longer exists.");
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
            Restaurante restaurante;
            try {
                restaurante = em.getReference(Restaurante.class, id);
                restaurante.getCodRestaurante();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The restaurante with id " + id + " no longer exists.", enfe);
            }
            Rangohora codrangoHora = restaurante.getCodrangoHora();
            if (codrangoHora != null) {
                codrangoHora.getRestauranteCollection().remove(restaurante);
                codrangoHora = em.merge(codrangoHora);
            }
            Horario codHorario = restaurante.getCodHorario();
            if (codHorario != null) {
                codHorario.getRestauranteCollection().remove(restaurante);
                codHorario = em.merge(codHorario);
            }
            Tipocomida codTipoComida = restaurante.getCodTipoComida();
            if (codTipoComida != null) {
                codTipoComida.getRestauranteCollection().remove(restaurante);
                codTipoComida = em.merge(codTipoComida);
            }
            Collection<Calificacion> calificacionCollection = restaurante.getCalificacionCollection();
            for (Calificacion calificacionCollectionCalificacion : calificacionCollection) {
                calificacionCollectionCalificacion.setCodRestaurante(null);
                calificacionCollectionCalificacion = em.merge(calificacionCollectionCalificacion);
            }
            Collection<Comentario> comentarioCollection = restaurante.getComentarioCollection();
            for (Comentario comentarioCollectionComentario : comentarioCollection) {
                comentarioCollectionComentario.setCodRestaurante(null);
                comentarioCollectionComentario = em.merge(comentarioCollectionComentario);
            }
            em.remove(restaurante);
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

    public List<Restaurante> findRestauranteEntities() {
        return findRestauranteEntities(true, -1, -1);
    }

    public List<Restaurante> findRestauranteEntities(int maxResults, int firstResult) {
        return findRestauranteEntities(false, maxResults, firstResult);
    }

    private List<Restaurante> findRestauranteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Restaurante.class));
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

    public Restaurante findRestaurante(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Restaurante.class, id);
        } finally {
            em.close();
        }
    }

    public int getRestauranteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Restaurante> rt = cq.from(Restaurante.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}

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
import Entity.Horario;
import Entity.Rangohora;
import java.util.ArrayList;
import java.util.Collection;
import Entity.Restaurante;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Produccion
 */
public class RangohoraJpaController implements Serializable {

    public RangohoraJpaController(EntityManagerFactory emf) {

        this.emf = emf;
    }

    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Rangohora rangohora) throws RollbackFailureException, Exception {
        if (rangohora.getHorarioCollection() == null) {
            rangohora.setHorarioCollection(new ArrayList<Horario>());
        }
        if (rangohora.getRestauranteCollection() == null) {
            rangohora.setRestauranteCollection(new ArrayList<Restaurante>());
        }
        EntityManager em = null;
        UserTransaction utx = (UserTransaction) em.getTransaction();
        try {
            utx.begin();
            em = getEntityManager();
            Collection<Horario> attachedHorarioCollection = new ArrayList<Horario>();
            for (Horario horarioCollectionHorarioToAttach : rangohora.getHorarioCollection()) {
                horarioCollectionHorarioToAttach = em.getReference(horarioCollectionHorarioToAttach.getClass(), horarioCollectionHorarioToAttach.getCodHorario());
                attachedHorarioCollection.add(horarioCollectionHorarioToAttach);
            }
            rangohora.setHorarioCollection(attachedHorarioCollection);
            Collection<Restaurante> attachedRestauranteCollection = new ArrayList<Restaurante>();
            for (Restaurante restauranteCollectionRestauranteToAttach : rangohora.getRestauranteCollection()) {
                restauranteCollectionRestauranteToAttach = em.getReference(restauranteCollectionRestauranteToAttach.getClass(), restauranteCollectionRestauranteToAttach.getCodRestaurante());
                attachedRestauranteCollection.add(restauranteCollectionRestauranteToAttach);
            }
            rangohora.setRestauranteCollection(attachedRestauranteCollection);
            em.persist(rangohora);
            for (Horario horarioCollectionHorario : rangohora.getHorarioCollection()) {
                Rangohora oldCodRHoraOfHorarioCollectionHorario = horarioCollectionHorario.getCodRHora();
                horarioCollectionHorario.setCodRHora(rangohora);
                horarioCollectionHorario = em.merge(horarioCollectionHorario);
                if (oldCodRHoraOfHorarioCollectionHorario != null) {
                    oldCodRHoraOfHorarioCollectionHorario.getHorarioCollection().remove(horarioCollectionHorario);
                    oldCodRHoraOfHorarioCollectionHorario = em.merge(oldCodRHoraOfHorarioCollectionHorario);
                }
            }
            for (Restaurante restauranteCollectionRestaurante : rangohora.getRestauranteCollection()) {
                Rangohora oldCodrangoHoraOfRestauranteCollectionRestaurante = restauranteCollectionRestaurante.getCodrangoHora();
                restauranteCollectionRestaurante.setCodrangoHora(rangohora);
                restauranteCollectionRestaurante = em.merge(restauranteCollectionRestaurante);
                if (oldCodrangoHoraOfRestauranteCollectionRestaurante != null) {
                    oldCodrangoHoraOfRestauranteCollectionRestaurante.getRestauranteCollection().remove(restauranteCollectionRestaurante);
                    oldCodrangoHoraOfRestauranteCollectionRestaurante = em.merge(oldCodrangoHoraOfRestauranteCollectionRestaurante);
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

    public void edit(Rangohora rangohora) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        UserTransaction utx = (UserTransaction) em.getTransaction();
        try {
            utx.begin();
            em = getEntityManager();
            Rangohora persistentRangohora = em.find(Rangohora.class, rangohora.getCodRHora());
            Collection<Horario> horarioCollectionOld = persistentRangohora.getHorarioCollection();
            Collection<Horario> horarioCollectionNew = rangohora.getHorarioCollection();
            Collection<Restaurante> restauranteCollectionOld = persistentRangohora.getRestauranteCollection();
            Collection<Restaurante> restauranteCollectionNew = rangohora.getRestauranteCollection();
            Collection<Horario> attachedHorarioCollectionNew = new ArrayList<Horario>();
            for (Horario horarioCollectionNewHorarioToAttach : horarioCollectionNew) {
                horarioCollectionNewHorarioToAttach = em.getReference(horarioCollectionNewHorarioToAttach.getClass(), horarioCollectionNewHorarioToAttach.getCodHorario());
                attachedHorarioCollectionNew.add(horarioCollectionNewHorarioToAttach);
            }
            horarioCollectionNew = attachedHorarioCollectionNew;
            rangohora.setHorarioCollection(horarioCollectionNew);
            Collection<Restaurante> attachedRestauranteCollectionNew = new ArrayList<Restaurante>();
            for (Restaurante restauranteCollectionNewRestauranteToAttach : restauranteCollectionNew) {
                restauranteCollectionNewRestauranteToAttach = em.getReference(restauranteCollectionNewRestauranteToAttach.getClass(), restauranteCollectionNewRestauranteToAttach.getCodRestaurante());
                attachedRestauranteCollectionNew.add(restauranteCollectionNewRestauranteToAttach);
            }
            restauranteCollectionNew = attachedRestauranteCollectionNew;
            rangohora.setRestauranteCollection(restauranteCollectionNew);
            rangohora = em.merge(rangohora);
            for (Horario horarioCollectionOldHorario : horarioCollectionOld) {
                if (!horarioCollectionNew.contains(horarioCollectionOldHorario)) {
                    horarioCollectionOldHorario.setCodRHora(null);
                    horarioCollectionOldHorario = em.merge(horarioCollectionOldHorario);
                }
            }
            for (Horario horarioCollectionNewHorario : horarioCollectionNew) {
                if (!horarioCollectionOld.contains(horarioCollectionNewHorario)) {
                    Rangohora oldCodRHoraOfHorarioCollectionNewHorario = horarioCollectionNewHorario.getCodRHora();
                    horarioCollectionNewHorario.setCodRHora(rangohora);
                    horarioCollectionNewHorario = em.merge(horarioCollectionNewHorario);
                    if (oldCodRHoraOfHorarioCollectionNewHorario != null && !oldCodRHoraOfHorarioCollectionNewHorario.equals(rangohora)) {
                        oldCodRHoraOfHorarioCollectionNewHorario.getHorarioCollection().remove(horarioCollectionNewHorario);
                        oldCodRHoraOfHorarioCollectionNewHorario = em.merge(oldCodRHoraOfHorarioCollectionNewHorario);
                    }
                }
            }
            for (Restaurante restauranteCollectionOldRestaurante : restauranteCollectionOld) {
                if (!restauranteCollectionNew.contains(restauranteCollectionOldRestaurante)) {
                    restauranteCollectionOldRestaurante.setCodrangoHora(null);
                    restauranteCollectionOldRestaurante = em.merge(restauranteCollectionOldRestaurante);
                }
            }
            for (Restaurante restauranteCollectionNewRestaurante : restauranteCollectionNew) {
                if (!restauranteCollectionOld.contains(restauranteCollectionNewRestaurante)) {
                    Rangohora oldCodrangoHoraOfRestauranteCollectionNewRestaurante = restauranteCollectionNewRestaurante.getCodrangoHora();
                    restauranteCollectionNewRestaurante.setCodrangoHora(rangohora);
                    restauranteCollectionNewRestaurante = em.merge(restauranteCollectionNewRestaurante);
                    if (oldCodrangoHoraOfRestauranteCollectionNewRestaurante != null && !oldCodrangoHoraOfRestauranteCollectionNewRestaurante.equals(rangohora)) {
                        oldCodrangoHoraOfRestauranteCollectionNewRestaurante.getRestauranteCollection().remove(restauranteCollectionNewRestaurante);
                        oldCodrangoHoraOfRestauranteCollectionNewRestaurante = em.merge(oldCodrangoHoraOfRestauranteCollectionNewRestaurante);
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
                Integer id = rangohora.getCodRHora();
                if (findRangohora(id) == null) {
                    throw new NonexistentEntityException("The rangohora with id " + id + " no longer exists.");
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
            Rangohora rangohora;
            try {
                rangohora = em.getReference(Rangohora.class, id);
                rangohora.getCodRHora();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The rangohora with id " + id + " no longer exists.", enfe);
            }
            Collection<Horario> horarioCollection = rangohora.getHorarioCollection();
            for (Horario horarioCollectionHorario : horarioCollection) {
                horarioCollectionHorario.setCodRHora(null);
                horarioCollectionHorario = em.merge(horarioCollectionHorario);
            }
            Collection<Restaurante> restauranteCollection = rangohora.getRestauranteCollection();
            for (Restaurante restauranteCollectionRestaurante : restauranteCollection) {
                restauranteCollectionRestaurante.setCodrangoHora(null);
                restauranteCollectionRestaurante = em.merge(restauranteCollectionRestaurante);
            }
            em.remove(rangohora);
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

    public List<Rangohora> findRangohoraEntities() {
        return findRangohoraEntities(true, -1, -1);
    }

    public List<Rangohora> findRangohoraEntities(int maxResults, int firstResult) {
        return findRangohoraEntities(false, maxResults, firstResult);
    }

    private List<Rangohora> findRangohoraEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Rangohora.class));
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

    public Rangohora findRangohora(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Rangohora.class, id);
        } finally {
            em.close();
        }
    }

    public int getRangohoraCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Rangohora> rt = cq.from(Rangohora.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}

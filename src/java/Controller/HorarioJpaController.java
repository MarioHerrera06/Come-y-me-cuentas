/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Controller.exceptions.NonexistentEntityException;
import Controller.exceptions.PreexistingEntityException;
import Controller.exceptions.RollbackFailureException;
import Entity.Horario;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Entity.Rangohora;
import Entity.Restaurante;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Produccion
 */
public class HorarioJpaController implements Serializable {

    public HorarioJpaController(EntityManagerFactory emf) {

        this.emf = emf;
    }

    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Horario horario) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (horario.getRestauranteCollection() == null) {
            horario.setRestauranteCollection(new ArrayList<Restaurante>());
        }
        EntityManager em = null;
        UserTransaction utx = (UserTransaction) em.getTransaction();
        try {
            utx.begin();
            em = getEntityManager();
            Rangohora codRHora = horario.getCodRHora();
            if (codRHora != null) {
                codRHora = em.getReference(codRHora.getClass(), codRHora.getCodRHora());
                horario.setCodRHora(codRHora);
            }
            Collection<Restaurante> attachedRestauranteCollection = new ArrayList<Restaurante>();
            for (Restaurante restauranteCollectionRestauranteToAttach : horario.getRestauranteCollection()) {
                restauranteCollectionRestauranteToAttach = em.getReference(restauranteCollectionRestauranteToAttach.getClass(), restauranteCollectionRestauranteToAttach.getCodRestaurante());
                attachedRestauranteCollection.add(restauranteCollectionRestauranteToAttach);
            }
            horario.setRestauranteCollection(attachedRestauranteCollection);
            em.persist(horario);
            if (codRHora != null) {
                codRHora.getHorarioCollection().add(horario);
                codRHora = em.merge(codRHora);
            }
            for (Restaurante restauranteCollectionRestaurante : horario.getRestauranteCollection()) {
                Horario oldCodHorarioOfRestauranteCollectionRestaurante = restauranteCollectionRestaurante.getCodHorario();
                restauranteCollectionRestaurante.setCodHorario(horario);
                restauranteCollectionRestaurante = em.merge(restauranteCollectionRestaurante);
                if (oldCodHorarioOfRestauranteCollectionRestaurante != null) {
                    oldCodHorarioOfRestauranteCollectionRestaurante.getRestauranteCollection().remove(restauranteCollectionRestaurante);
                    oldCodHorarioOfRestauranteCollectionRestaurante = em.merge(oldCodHorarioOfRestauranteCollectionRestaurante);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findHorario(horario.getCodHorario()) != null) {
                throw new PreexistingEntityException("Horario " + horario + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Horario horario) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        UserTransaction utx = (UserTransaction) em.getTransaction();
        try {
            utx.begin();
            em = getEntityManager();
            Horario persistentHorario = em.find(Horario.class, horario.getCodHorario());
            Rangohora codRHoraOld = persistentHorario.getCodRHora();
            Rangohora codRHoraNew = horario.getCodRHora();
            Collection<Restaurante> restauranteCollectionOld = persistentHorario.getRestauranteCollection();
            Collection<Restaurante> restauranteCollectionNew = horario.getRestauranteCollection();
            if (codRHoraNew != null) {
                codRHoraNew = em.getReference(codRHoraNew.getClass(), codRHoraNew.getCodRHora());
                horario.setCodRHora(codRHoraNew);
            }
            Collection<Restaurante> attachedRestauranteCollectionNew = new ArrayList<Restaurante>();
            for (Restaurante restauranteCollectionNewRestauranteToAttach : restauranteCollectionNew) {
                restauranteCollectionNewRestauranteToAttach = em.getReference(restauranteCollectionNewRestauranteToAttach.getClass(), restauranteCollectionNewRestauranteToAttach.getCodRestaurante());
                attachedRestauranteCollectionNew.add(restauranteCollectionNewRestauranteToAttach);
            }
            restauranteCollectionNew = attachedRestauranteCollectionNew;
            horario.setRestauranteCollection(restauranteCollectionNew);
            horario = em.merge(horario);
            if (codRHoraOld != null && !codRHoraOld.equals(codRHoraNew)) {
                codRHoraOld.getHorarioCollection().remove(horario);
                codRHoraOld = em.merge(codRHoraOld);
            }
            if (codRHoraNew != null && !codRHoraNew.equals(codRHoraOld)) {
                codRHoraNew.getHorarioCollection().add(horario);
                codRHoraNew = em.merge(codRHoraNew);
            }
            for (Restaurante restauranteCollectionOldRestaurante : restauranteCollectionOld) {
                if (!restauranteCollectionNew.contains(restauranteCollectionOldRestaurante)) {
                    restauranteCollectionOldRestaurante.setCodHorario(null);
                    restauranteCollectionOldRestaurante = em.merge(restauranteCollectionOldRestaurante);
                }
            }
            for (Restaurante restauranteCollectionNewRestaurante : restauranteCollectionNew) {
                if (!restauranteCollectionOld.contains(restauranteCollectionNewRestaurante)) {
                    Horario oldCodHorarioOfRestauranteCollectionNewRestaurante = restauranteCollectionNewRestaurante.getCodHorario();
                    restauranteCollectionNewRestaurante.setCodHorario(horario);
                    restauranteCollectionNewRestaurante = em.merge(restauranteCollectionNewRestaurante);
                    if (oldCodHorarioOfRestauranteCollectionNewRestaurante != null && !oldCodHorarioOfRestauranteCollectionNewRestaurante.equals(horario)) {
                        oldCodHorarioOfRestauranteCollectionNewRestaurante.getRestauranteCollection().remove(restauranteCollectionNewRestaurante);
                        oldCodHorarioOfRestauranteCollectionNewRestaurante = em.merge(oldCodHorarioOfRestauranteCollectionNewRestaurante);
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
                Integer id = horario.getCodHorario();
                if (findHorario(id) == null) {
                    throw new NonexistentEntityException("The horario with id " + id + " no longer exists.");
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
            Horario horario;
            try {
                horario = em.getReference(Horario.class, id);
                horario.getCodHorario();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The horario with id " + id + " no longer exists.", enfe);
            }
            Rangohora codRHora = horario.getCodRHora();
            if (codRHora != null) {
                codRHora.getHorarioCollection().remove(horario);
                codRHora = em.merge(codRHora);
            }
            Collection<Restaurante> restauranteCollection = horario.getRestauranteCollection();
            for (Restaurante restauranteCollectionRestaurante : restauranteCollection) {
                restauranteCollectionRestaurante.setCodHorario(null);
                restauranteCollectionRestaurante = em.merge(restauranteCollectionRestaurante);
            }
            em.remove(horario);
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

    public List<Horario> findHorarioEntities() {
        return findHorarioEntities(true, -1, -1);
    }

    public List<Horario> findHorarioEntities(int maxResults, int firstResult) {
        return findHorarioEntities(false, maxResults, firstResult);
    }

    private List<Horario> findHorarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Horario.class));
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

    public Horario findHorario(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Horario.class, id);
        } finally {
            em.close();
        }
    }

    public int getHorarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Horario> rt = cq.from(Horario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}

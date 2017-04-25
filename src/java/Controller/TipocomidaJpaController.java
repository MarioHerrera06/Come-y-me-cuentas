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
import Entity.Restaurante;
import Entity.Tipocomida;
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
public class TipocomidaJpaController implements Serializable {

    public TipocomidaJpaController(EntityManagerFactory emf) {

        this.emf = emf;
    }

    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tipocomida tipocomida) throws RollbackFailureException, Exception {
        if (tipocomida.getRestauranteCollection() == null) {
            tipocomida.setRestauranteCollection(new ArrayList<Restaurante>());
        }
        EntityManager em = null;
        UserTransaction utx = (UserTransaction) em.getTransaction();
        try {
            utx.begin();
            em = getEntityManager();
            Collection<Restaurante> attachedRestauranteCollection = new ArrayList<Restaurante>();
            for (Restaurante restauranteCollectionRestauranteToAttach : tipocomida.getRestauranteCollection()) {
                restauranteCollectionRestauranteToAttach = em.getReference(restauranteCollectionRestauranteToAttach.getClass(), restauranteCollectionRestauranteToAttach.getCodRestaurante());
                attachedRestauranteCollection.add(restauranteCollectionRestauranteToAttach);
            }
            tipocomida.setRestauranteCollection(attachedRestauranteCollection);
            em.persist(tipocomida);
            for (Restaurante restauranteCollectionRestaurante : tipocomida.getRestauranteCollection()) {
                Tipocomida oldCodTipoComidaOfRestauranteCollectionRestaurante = restauranteCollectionRestaurante.getCodTipoComida();
                restauranteCollectionRestaurante.setCodTipoComida(tipocomida);
                restauranteCollectionRestaurante = em.merge(restauranteCollectionRestaurante);
                if (oldCodTipoComidaOfRestauranteCollectionRestaurante != null) {
                    oldCodTipoComidaOfRestauranteCollectionRestaurante.getRestauranteCollection().remove(restauranteCollectionRestaurante);
                    oldCodTipoComidaOfRestauranteCollectionRestaurante = em.merge(oldCodTipoComidaOfRestauranteCollectionRestaurante);
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

    public void edit(Tipocomida tipocomida) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        UserTransaction utx = (UserTransaction) em.getTransaction();
        try {
            utx.begin();
            em = getEntityManager();
            Tipocomida persistentTipocomida = em.find(Tipocomida.class, tipocomida.getCodTComida());
            Collection<Restaurante> restauranteCollectionOld = persistentTipocomida.getRestauranteCollection();
            Collection<Restaurante> restauranteCollectionNew = tipocomida.getRestauranteCollection();
            Collection<Restaurante> attachedRestauranteCollectionNew = new ArrayList<Restaurante>();
            for (Restaurante restauranteCollectionNewRestauranteToAttach : restauranteCollectionNew) {
                restauranteCollectionNewRestauranteToAttach = em.getReference(restauranteCollectionNewRestauranteToAttach.getClass(), restauranteCollectionNewRestauranteToAttach.getCodRestaurante());
                attachedRestauranteCollectionNew.add(restauranteCollectionNewRestauranteToAttach);
            }
            restauranteCollectionNew = attachedRestauranteCollectionNew;
            tipocomida.setRestauranteCollection(restauranteCollectionNew);
            tipocomida = em.merge(tipocomida);
            for (Restaurante restauranteCollectionOldRestaurante : restauranteCollectionOld) {
                if (!restauranteCollectionNew.contains(restauranteCollectionOldRestaurante)) {
                    restauranteCollectionOldRestaurante.setCodTipoComida(null);
                    restauranteCollectionOldRestaurante = em.merge(restauranteCollectionOldRestaurante);
                }
            }
            for (Restaurante restauranteCollectionNewRestaurante : restauranteCollectionNew) {
                if (!restauranteCollectionOld.contains(restauranteCollectionNewRestaurante)) {
                    Tipocomida oldCodTipoComidaOfRestauranteCollectionNewRestaurante = restauranteCollectionNewRestaurante.getCodTipoComida();
                    restauranteCollectionNewRestaurante.setCodTipoComida(tipocomida);
                    restauranteCollectionNewRestaurante = em.merge(restauranteCollectionNewRestaurante);
                    if (oldCodTipoComidaOfRestauranteCollectionNewRestaurante != null && !oldCodTipoComidaOfRestauranteCollectionNewRestaurante.equals(tipocomida)) {
                        oldCodTipoComidaOfRestauranteCollectionNewRestaurante.getRestauranteCollection().remove(restauranteCollectionNewRestaurante);
                        oldCodTipoComidaOfRestauranteCollectionNewRestaurante = em.merge(oldCodTipoComidaOfRestauranteCollectionNewRestaurante);
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
                Integer id = tipocomida.getCodTComida();
                if (findTipocomida(id) == null) {
                    throw new NonexistentEntityException("The tipocomida with id " + id + " no longer exists.");
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
            Tipocomida tipocomida;
            try {
                tipocomida = em.getReference(Tipocomida.class, id);
                tipocomida.getCodTComida();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipocomida with id " + id + " no longer exists.", enfe);
            }
            Collection<Restaurante> restauranteCollection = tipocomida.getRestauranteCollection();
            for (Restaurante restauranteCollectionRestaurante : restauranteCollection) {
                restauranteCollectionRestaurante.setCodTipoComida(null);
                restauranteCollectionRestaurante = em.merge(restauranteCollectionRestaurante);
            }
            em.remove(tipocomida);
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

    public List<Tipocomida> findTipocomidaEntities() {
        return findTipocomidaEntities(true, -1, -1);
    }

    public List<Tipocomida> findTipocomidaEntities(int maxResults, int firstResult) {
        return findTipocomidaEntities(false, maxResults, firstResult);
    }

    private List<Tipocomida> findTipocomidaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tipocomida.class));
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

    public Tipocomida findTipocomida(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tipocomida.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipocomidaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Tipocomida> rt = cq.from(Tipocomida.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}

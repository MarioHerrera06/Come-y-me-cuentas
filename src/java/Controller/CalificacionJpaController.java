package Controller;

import Controller.exceptions.NonexistentEntityException;
import Controller.exceptions.PreexistingEntityException;
import Controller.exceptions.RollbackFailureException;
import Entity.Calificacion;
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

public class CalificacionJpaController implements Serializable {

    public CalificacionJpaController(EntityManagerFactory emf) {

        this.emf = emf;
    }

    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Calificacion calificacion) throws PreexistingEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        UserTransaction utx = (UserTransaction) em.getTransaction();

        try {
            utx.begin();
            em = getEntityManager();
            Restaurante codRestaurante = calificacion.getCodRestaurante();
            if (codRestaurante != null) {
                codRestaurante = em.getReference(codRestaurante.getClass(), codRestaurante.getCodRestaurante());
                calificacion.setCodRestaurante(codRestaurante);
            }
            Usuario codUsuario = calificacion.getCodUsuario();
            if (codUsuario != null) {
                codUsuario = em.getReference(codUsuario.getClass(), codUsuario.getCodUsuario());
                calificacion.setCodUsuario(codUsuario);
            }
            em.persist(calificacion);
            if (codRestaurante != null) {
                codRestaurante.getCalificacionCollection().add(calificacion);
                codRestaurante = em.merge(codRestaurante);
            }
            if (codUsuario != null) {
                codUsuario.getCalificacionCollection().add(calificacion);
                codUsuario = em.merge(codUsuario);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findCalificacion(calificacion.getCodValor()) != null) {
                throw new PreexistingEntityException("Calificacion " + calificacion + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Calificacion calificacion) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        UserTransaction utx = (UserTransaction) em.getTransaction();
        try {
            utx.begin();
            em = getEntityManager();
            Calificacion persistentCalificacion = em.find(Calificacion.class, calificacion.getCodValor());
            Restaurante codRestauranteOld = persistentCalificacion.getCodRestaurante();
            Restaurante codRestauranteNew = calificacion.getCodRestaurante();
            Usuario codUsuarioOld = persistentCalificacion.getCodUsuario();
            Usuario codUsuarioNew = calificacion.getCodUsuario();
            if (codRestauranteNew != null) {
                codRestauranteNew = em.getReference(codRestauranteNew.getClass(), codRestauranteNew.getCodRestaurante());
                calificacion.setCodRestaurante(codRestauranteNew);
            }
            if (codUsuarioNew != null) {
                codUsuarioNew = em.getReference(codUsuarioNew.getClass(), codUsuarioNew.getCodUsuario());
                calificacion.setCodUsuario(codUsuarioNew);
            }
            calificacion = em.merge(calificacion);
            if (codRestauranteOld != null && !codRestauranteOld.equals(codRestauranteNew)) {
                codRestauranteOld.getCalificacionCollection().remove(calificacion);
                codRestauranteOld = em.merge(codRestauranteOld);
            }
            if (codRestauranteNew != null && !codRestauranteNew.equals(codRestauranteOld)) {
                codRestauranteNew.getCalificacionCollection().add(calificacion);
                codRestauranteNew = em.merge(codRestauranteNew);
            }
            if (codUsuarioOld != null && !codUsuarioOld.equals(codUsuarioNew)) {
                codUsuarioOld.getCalificacionCollection().remove(calificacion);
                codUsuarioOld = em.merge(codUsuarioOld);
            }
            if (codUsuarioNew != null && !codUsuarioNew.equals(codUsuarioOld)) {
                codUsuarioNew.getCalificacionCollection().add(calificacion);
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
                Integer id = calificacion.getCodValor();
                if (findCalificacion(id) == null) {
                    throw new NonexistentEntityException("The calificacion with id " + id + " no longer exists.");
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
            Calificacion calificacion;
            try {
                calificacion = em.getReference(Calificacion.class, id);
                calificacion.getCodValor();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The calificacion with id " + id + " no longer exists.", enfe);
            }
            Restaurante codRestaurante = calificacion.getCodRestaurante();
            if (codRestaurante != null) {
                codRestaurante.getCalificacionCollection().remove(calificacion);
                codRestaurante = em.merge(codRestaurante);
            }
            Usuario codUsuario = calificacion.getCodUsuario();
            if (codUsuario != null) {
                codUsuario.getCalificacionCollection().remove(calificacion);
                codUsuario = em.merge(codUsuario);
            }
            em.remove(calificacion);
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

    public List<Calificacion> findCalificacionEntities() {
        return findCalificacionEntities(true, -1, -1);
    }

    public List<Calificacion> findCalificacionEntities(int maxResults, int firstResult) {
        return findCalificacionEntities(false, maxResults, firstResult);
    }

    private List<Calificacion> findCalificacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Calificacion.class));
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

    public Calificacion findCalificacion(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Calificacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getCalificacionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Calificacion> rt = cq.from(Calificacion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}

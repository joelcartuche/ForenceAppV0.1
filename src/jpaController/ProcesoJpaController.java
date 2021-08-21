/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpaController;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import jpaController.exceptions.NonexistentEntityException;
import modelos.Perito;
import modelos.Proceso;
import modelos.Solicitante;

/**
 *
 * @author joelc
 */
public class ProcesoJpaController implements Serializable {

    public ProcesoJpaController() {
    }

    public ProcesoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("ForenceAppv1PU");
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Proceso proceso) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Perito idPerito = proceso.getIdPerito();
            if (idPerito != null) {
                idPerito = em.getReference(idPerito.getClass(), idPerito.getIdPerito());
                proceso.setIdPerito(idPerito);
            }
            Solicitante idSolicitante = proceso.getIdSolicitante();
            if (idSolicitante != null) {
                idSolicitante = em.getReference(idSolicitante.getClass(), idSolicitante.getIdSolicitante());
                proceso.setIdSolicitante(idSolicitante);
            }
            em.persist(proceso);
            if (idPerito != null) {
                idPerito.getProcesoCollection().add(proceso);
                idPerito = em.merge(idPerito);
            }
            if (idSolicitante != null) {
                idSolicitante.getProcesoCollection().add(proceso);
                idSolicitante = em.merge(idSolicitante);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Proceso proceso) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Proceso persistentProceso = em.find(Proceso.class, proceso.getIdProceso());
            Perito idPeritoOld = persistentProceso.getIdPerito();
            Perito idPeritoNew = proceso.getIdPerito();
            Solicitante idSolicitanteOld = persistentProceso.getIdSolicitante();
            Solicitante idSolicitanteNew = proceso.getIdSolicitante();
            if (idPeritoNew != null) {
                idPeritoNew = em.getReference(idPeritoNew.getClass(), idPeritoNew.getIdPerito());
                proceso.setIdPerito(idPeritoNew);
            }
            if (idSolicitanteNew != null) {
                idSolicitanteNew = em.getReference(idSolicitanteNew.getClass(), idSolicitanteNew.getIdSolicitante());
                proceso.setIdSolicitante(idSolicitanteNew);
            }
            proceso = em.merge(proceso);
            if (idPeritoOld != null && !idPeritoOld.equals(idPeritoNew)) {
                idPeritoOld.getProcesoCollection().remove(proceso);
                idPeritoOld = em.merge(idPeritoOld);
            }
            if (idPeritoNew != null && !idPeritoNew.equals(idPeritoOld)) {
                idPeritoNew.getProcesoCollection().add(proceso);
                idPeritoNew = em.merge(idPeritoNew);
            }
            if (idSolicitanteOld != null && !idSolicitanteOld.equals(idSolicitanteNew)) {
                idSolicitanteOld.getProcesoCollection().remove(proceso);
                idSolicitanteOld = em.merge(idSolicitanteOld);
            }
            if (idSolicitanteNew != null && !idSolicitanteNew.equals(idSolicitanteOld)) {
                idSolicitanteNew.getProcesoCollection().add(proceso);
                idSolicitanteNew = em.merge(idSolicitanteNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = proceso.getIdProceso();
                if (findProceso(id) == null) {
                    throw new NonexistentEntityException("The proceso with id " + id + " no longer exists.");
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
            Proceso proceso;
            try {
                proceso = em.getReference(Proceso.class, id);
                proceso.getIdProceso();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The proceso with id " + id + " no longer exists.", enfe);
            }
            Perito idPerito = proceso.getIdPerito();
            if (idPerito != null) {
                idPerito.getProcesoCollection().remove(proceso);
                idPerito = em.merge(idPerito);
            }
            Solicitante idSolicitante = proceso.getIdSolicitante();
            if (idSolicitante != null) {
                idSolicitante.getProcesoCollection().remove(proceso);
                idSolicitante = em.merge(idSolicitante);
            }
            em.remove(proceso);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Proceso> findProcesoEntities() {
        return findProcesoEntities(true, -1, -1);
    }

    public List<Proceso> findProcesoEntities(int maxResults, int firstResult) {
        return findProcesoEntities(false, maxResults, firstResult);
    }

    private List<Proceso> findProcesoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Proceso.class));
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

    public Proceso findProceso(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Proceso.class, id);
        } finally {
            em.close();
        }
    }

    public int getProcesoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Proceso> rt = cq.from(Proceso.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}

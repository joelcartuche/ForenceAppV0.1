/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpaController;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import modelos.Persona;
import modelos.Proceso;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import jpaController.exceptions.IllegalOrphanException;
import jpaController.exceptions.NonexistentEntityException;
import modelos.Solicitante;

/**
 *
 * @author joelc
 */
public class SolicitanteJpaController implements Serializable {

    public SolicitanteJpaController() {
    }

    public SolicitanteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("ForenceAppv1PU");

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Solicitante solicitante) {
        if (solicitante.getProcesoCollection() == null) {
            solicitante.setProcesoCollection(new ArrayList<Proceso>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Persona idPersona = solicitante.getIdPersona();
            if (idPersona != null) {
                idPersona = em.getReference(idPersona.getClass(), idPersona.getIdPersona());
                solicitante.setIdPersona(idPersona);
            }
            Collection<Proceso> attachedProcesoCollection = new ArrayList<Proceso>();
            for (Proceso procesoCollectionProcesoToAttach : solicitante.getProcesoCollection()) {
                procesoCollectionProcesoToAttach = em.getReference(procesoCollectionProcesoToAttach.getClass(), procesoCollectionProcesoToAttach.getIdProceso());
                attachedProcesoCollection.add(procesoCollectionProcesoToAttach);
            }
            solicitante.setProcesoCollection(attachedProcesoCollection);
            em.persist(solicitante);
            if (idPersona != null) {
                idPersona.getSolicitanteCollection().add(solicitante);
                idPersona = em.merge(idPersona);
            }
            for (Proceso procesoCollectionProceso : solicitante.getProcesoCollection()) {
                Solicitante oldIdSolicitanteOfProcesoCollectionProceso = procesoCollectionProceso.getIdSolicitante();
                procesoCollectionProceso.setIdSolicitante(solicitante);
                procesoCollectionProceso = em.merge(procesoCollectionProceso);
                if (oldIdSolicitanteOfProcesoCollectionProceso != null) {
                    oldIdSolicitanteOfProcesoCollectionProceso.getProcesoCollection().remove(procesoCollectionProceso);
                    oldIdSolicitanteOfProcesoCollectionProceso = em.merge(oldIdSolicitanteOfProcesoCollectionProceso);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Solicitante solicitante) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Solicitante persistentSolicitante = em.find(Solicitante.class, solicitante.getIdSolicitante());
            Persona idPersonaOld = persistentSolicitante.getIdPersona();
            Persona idPersonaNew = solicitante.getIdPersona();
            Collection<Proceso> procesoCollectionOld = persistentSolicitante.getProcesoCollection();
            Collection<Proceso> procesoCollectionNew = solicitante.getProcesoCollection();
            List<String> illegalOrphanMessages = null;
            for (Proceso procesoCollectionOldProceso : procesoCollectionOld) {
                if (!procesoCollectionNew.contains(procesoCollectionOldProceso)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Proceso " + procesoCollectionOldProceso + " since its idSolicitante field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idPersonaNew != null) {
                idPersonaNew = em.getReference(idPersonaNew.getClass(), idPersonaNew.getIdPersona());
                solicitante.setIdPersona(idPersonaNew);
            }
            Collection<Proceso> attachedProcesoCollectionNew = new ArrayList<Proceso>();
            for (Proceso procesoCollectionNewProcesoToAttach : procesoCollectionNew) {
                procesoCollectionNewProcesoToAttach = em.getReference(procesoCollectionNewProcesoToAttach.getClass(), procesoCollectionNewProcesoToAttach.getIdProceso());
                attachedProcesoCollectionNew.add(procesoCollectionNewProcesoToAttach);
            }
            procesoCollectionNew = attachedProcesoCollectionNew;
            solicitante.setProcesoCollection(procesoCollectionNew);
            solicitante = em.merge(solicitante);
            if (idPersonaOld != null && !idPersonaOld.equals(idPersonaNew)) {
                idPersonaOld.getSolicitanteCollection().remove(solicitante);
                idPersonaOld = em.merge(idPersonaOld);
            }
            if (idPersonaNew != null && !idPersonaNew.equals(idPersonaOld)) {
                idPersonaNew.getSolicitanteCollection().add(solicitante);
                idPersonaNew = em.merge(idPersonaNew);
            }
            for (Proceso procesoCollectionNewProceso : procesoCollectionNew) {
                if (!procesoCollectionOld.contains(procesoCollectionNewProceso)) {
                    Solicitante oldIdSolicitanteOfProcesoCollectionNewProceso = procesoCollectionNewProceso.getIdSolicitante();
                    procesoCollectionNewProceso.setIdSolicitante(solicitante);
                    procesoCollectionNewProceso = em.merge(procesoCollectionNewProceso);
                    if (oldIdSolicitanteOfProcesoCollectionNewProceso != null && !oldIdSolicitanteOfProcesoCollectionNewProceso.equals(solicitante)) {
                        oldIdSolicitanteOfProcesoCollectionNewProceso.getProcesoCollection().remove(procesoCollectionNewProceso);
                        oldIdSolicitanteOfProcesoCollectionNewProceso = em.merge(oldIdSolicitanteOfProcesoCollectionNewProceso);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = solicitante.getIdSolicitante();
                if (findSolicitante(id) == null) {
                    throw new NonexistentEntityException("The solicitante with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Solicitante solicitante;
            try {
                solicitante = em.getReference(Solicitante.class, id);
                solicitante.getIdSolicitante();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The solicitante with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Proceso> procesoCollectionOrphanCheck = solicitante.getProcesoCollection();
            for (Proceso procesoCollectionOrphanCheckProceso : procesoCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Solicitante (" + solicitante + ") cannot be destroyed since the Proceso " + procesoCollectionOrphanCheckProceso + " in its procesoCollection field has a non-nullable idSolicitante field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Persona idPersona = solicitante.getIdPersona();
            if (idPersona != null) {
                idPersona.getSolicitanteCollection().remove(solicitante);
                idPersona = em.merge(idPersona);
            }
            em.remove(solicitante);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Solicitante> findSolicitanteEntities() {
        return findSolicitanteEntities(true, -1, -1);
    }

    public List<Solicitante> findSolicitanteEntities(int maxResults, int firstResult) {
        return findSolicitanteEntities(false, maxResults, firstResult);
    }

    private List<Solicitante> findSolicitanteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Solicitante.class));
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

    public Solicitante findSolicitante(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Solicitante.class, id);
        } finally {
            em.close();
        }
    }

    public int getSolicitanteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Solicitante> rt = cq.from(Solicitante.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}

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
import modelos.Perito;

/**
 *
 * @author joelc
 */
public class PeritoJpaController implements Serializable {

    public PeritoJpaController() {
    }

    public PeritoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("ForenceAppv1PU");

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Perito perito) {
        if (perito.getProcesoCollection() == null) {
            perito.setProcesoCollection(new ArrayList<Proceso>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Persona idPersona = perito.getIdPersona();
            if (idPersona != null) {
                idPersona = em.getReference(idPersona.getClass(), idPersona.getIdPersona());
                perito.setIdPersona(idPersona);
            }
            Collection<Proceso> attachedProcesoCollection = new ArrayList<Proceso>();
            for (Proceso procesoCollectionProcesoToAttach : perito.getProcesoCollection()) {
                procesoCollectionProcesoToAttach = em.getReference(procesoCollectionProcesoToAttach.getClass(), procesoCollectionProcesoToAttach.getIdProceso());
                attachedProcesoCollection.add(procesoCollectionProcesoToAttach);
            }
            perito.setProcesoCollection(attachedProcesoCollection);
            em.persist(perito);
            if (idPersona != null) {
                idPersona.getPeritoCollection().add(perito);
                idPersona = em.merge(idPersona);
            }
            for (Proceso procesoCollectionProceso : perito.getProcesoCollection()) {
                Perito oldIdPeritoOfProcesoCollectionProceso = procesoCollectionProceso.getIdPerito();
                procesoCollectionProceso.setIdPerito(perito);
                procesoCollectionProceso = em.merge(procesoCollectionProceso);
                if (oldIdPeritoOfProcesoCollectionProceso != null) {
                    oldIdPeritoOfProcesoCollectionProceso.getProcesoCollection().remove(procesoCollectionProceso);
                    oldIdPeritoOfProcesoCollectionProceso = em.merge(oldIdPeritoOfProcesoCollectionProceso);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Perito perito) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Perito persistentPerito = em.find(Perito.class, perito.getIdPerito());
            Persona idPersonaOld = persistentPerito.getIdPersona();
            Persona idPersonaNew = perito.getIdPersona();
            Collection<Proceso> procesoCollectionOld = persistentPerito.getProcesoCollection();
            Collection<Proceso> procesoCollectionNew = perito.getProcesoCollection();
            List<String> illegalOrphanMessages = null;
            for (Proceso procesoCollectionOldProceso : procesoCollectionOld) {
                if (!procesoCollectionNew.contains(procesoCollectionOldProceso)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Proceso " + procesoCollectionOldProceso + " since its idPerito field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idPersonaNew != null) {
                idPersonaNew = em.getReference(idPersonaNew.getClass(), idPersonaNew.getIdPersona());
                perito.setIdPersona(idPersonaNew);
            }
            Collection<Proceso> attachedProcesoCollectionNew = new ArrayList<Proceso>();
            for (Proceso procesoCollectionNewProcesoToAttach : procesoCollectionNew) {
                procesoCollectionNewProcesoToAttach = em.getReference(procesoCollectionNewProcesoToAttach.getClass(), procesoCollectionNewProcesoToAttach.getIdProceso());
                attachedProcesoCollectionNew.add(procesoCollectionNewProcesoToAttach);
            }
            procesoCollectionNew = attachedProcesoCollectionNew;
            perito.setProcesoCollection(procesoCollectionNew);
            perito = em.merge(perito);
            if (idPersonaOld != null && !idPersonaOld.equals(idPersonaNew)) {
                idPersonaOld.getPeritoCollection().remove(perito);
                idPersonaOld = em.merge(idPersonaOld);
            }
            if (idPersonaNew != null && !idPersonaNew.equals(idPersonaOld)) {
                idPersonaNew.getPeritoCollection().add(perito);
                idPersonaNew = em.merge(idPersonaNew);
            }
            for (Proceso procesoCollectionNewProceso : procesoCollectionNew) {
                if (!procesoCollectionOld.contains(procesoCollectionNewProceso)) {
                    Perito oldIdPeritoOfProcesoCollectionNewProceso = procesoCollectionNewProceso.getIdPerito();
                    procesoCollectionNewProceso.setIdPerito(perito);
                    procesoCollectionNewProceso = em.merge(procesoCollectionNewProceso);
                    if (oldIdPeritoOfProcesoCollectionNewProceso != null && !oldIdPeritoOfProcesoCollectionNewProceso.equals(perito)) {
                        oldIdPeritoOfProcesoCollectionNewProceso.getProcesoCollection().remove(procesoCollectionNewProceso);
                        oldIdPeritoOfProcesoCollectionNewProceso = em.merge(oldIdPeritoOfProcesoCollectionNewProceso);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = perito.getIdPerito();
                if (findPerito(id) == null) {
                    throw new NonexistentEntityException("The perito with id " + id + " no longer exists.");
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
            Perito perito;
            try {
                perito = em.getReference(Perito.class, id);
                perito.getIdPerito();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The perito with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Proceso> procesoCollectionOrphanCheck = perito.getProcesoCollection();
            for (Proceso procesoCollectionOrphanCheckProceso : procesoCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Perito (" + perito + ") cannot be destroyed since the Proceso " + procesoCollectionOrphanCheckProceso + " in its procesoCollection field has a non-nullable idPerito field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Persona idPersona = perito.getIdPersona();
            if (idPersona != null) {
                idPersona.getPeritoCollection().remove(perito);
                idPersona = em.merge(idPersona);
            }
            em.remove(perito);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Perito> findPeritoEntities() {
        return findPeritoEntities(true, -1, -1);
    }

    public List<Perito> findPeritoEntities(int maxResults, int firstResult) {
        return findPeritoEntities(false, maxResults, firstResult);
    }

    private List<Perito> findPeritoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Perito.class));
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

    public Perito findPerito(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Perito.class, id);
        } finally {
            em.close();
        }
    }

    public int getPeritoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Perito> rt = cq.from(Perito.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}

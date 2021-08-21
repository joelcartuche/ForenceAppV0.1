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
import modelos.Perito;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import jpaController.exceptions.IllegalOrphanException;
import jpaController.exceptions.NonexistentEntityException;
import modelos.Persona;
import modelos.Solicitante;

/**
 *
 * @author joelc
 */
public class PersonaJpaController implements Serializable {

    public PersonaJpaController() {
    }

    public PersonaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("ForenceAppv1PU");

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Persona persona) {
        if (persona.getPeritoCollection() == null) {
            persona.setPeritoCollection(new ArrayList<Perito>());
        }
        if (persona.getSolicitanteCollection() == null) {
            persona.setSolicitanteCollection(new ArrayList<Solicitante>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Perito> attachedPeritoCollection = new ArrayList<Perito>();
            for (Perito peritoCollectionPeritoToAttach : persona.getPeritoCollection()) {
                peritoCollectionPeritoToAttach = em.getReference(peritoCollectionPeritoToAttach.getClass(), peritoCollectionPeritoToAttach.getIdPerito());
                attachedPeritoCollection.add(peritoCollectionPeritoToAttach);
            }
            persona.setPeritoCollection(attachedPeritoCollection);
            Collection<Solicitante> attachedSolicitanteCollection = new ArrayList<Solicitante>();
            for (Solicitante solicitanteCollectionSolicitanteToAttach : persona.getSolicitanteCollection()) {
                solicitanteCollectionSolicitanteToAttach = em.getReference(solicitanteCollectionSolicitanteToAttach.getClass(), solicitanteCollectionSolicitanteToAttach.getIdSolicitante());
                attachedSolicitanteCollection.add(solicitanteCollectionSolicitanteToAttach);
            }
            persona.setSolicitanteCollection(attachedSolicitanteCollection);
            em.persist(persona);
            for (Perito peritoCollectionPerito : persona.getPeritoCollection()) {
                Persona oldIdPersonaOfPeritoCollectionPerito = peritoCollectionPerito.getIdPersona();
                peritoCollectionPerito.setIdPersona(persona);
                peritoCollectionPerito = em.merge(peritoCollectionPerito);
                if (oldIdPersonaOfPeritoCollectionPerito != null) {
                    oldIdPersonaOfPeritoCollectionPerito.getPeritoCollection().remove(peritoCollectionPerito);
                    oldIdPersonaOfPeritoCollectionPerito = em.merge(oldIdPersonaOfPeritoCollectionPerito);
                }
            }
            for (Solicitante solicitanteCollectionSolicitante : persona.getSolicitanteCollection()) {
                Persona oldIdPersonaOfSolicitanteCollectionSolicitante = solicitanteCollectionSolicitante.getIdPersona();
                solicitanteCollectionSolicitante.setIdPersona(persona);
                solicitanteCollectionSolicitante = em.merge(solicitanteCollectionSolicitante);
                if (oldIdPersonaOfSolicitanteCollectionSolicitante != null) {
                    oldIdPersonaOfSolicitanteCollectionSolicitante.getSolicitanteCollection().remove(solicitanteCollectionSolicitante);
                    oldIdPersonaOfSolicitanteCollectionSolicitante = em.merge(oldIdPersonaOfSolicitanteCollectionSolicitante);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Persona persona) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Persona persistentPersona = em.find(Persona.class, persona.getIdPersona());
            Collection<Perito> peritoCollectionOld = persistentPersona.getPeritoCollection();
            Collection<Perito> peritoCollectionNew = persona.getPeritoCollection();
            Collection<Solicitante> solicitanteCollectionOld = persistentPersona.getSolicitanteCollection();
            Collection<Solicitante> solicitanteCollectionNew = persona.getSolicitanteCollection();
            List<String> illegalOrphanMessages = null;
            for (Perito peritoCollectionOldPerito : peritoCollectionOld) {
                if (!peritoCollectionNew.contains(peritoCollectionOldPerito)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Perito " + peritoCollectionOldPerito + " since its idPersona field is not nullable.");
                }
            }
            for (Solicitante solicitanteCollectionOldSolicitante : solicitanteCollectionOld) {
                if (!solicitanteCollectionNew.contains(solicitanteCollectionOldSolicitante)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Solicitante " + solicitanteCollectionOldSolicitante + " since its idPersona field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Perito> attachedPeritoCollectionNew = new ArrayList<Perito>();
            for (Perito peritoCollectionNewPeritoToAttach : peritoCollectionNew) {
                peritoCollectionNewPeritoToAttach = em.getReference(peritoCollectionNewPeritoToAttach.getClass(), peritoCollectionNewPeritoToAttach.getIdPerito());
                attachedPeritoCollectionNew.add(peritoCollectionNewPeritoToAttach);
            }
            peritoCollectionNew = attachedPeritoCollectionNew;
            persona.setPeritoCollection(peritoCollectionNew);
            Collection<Solicitante> attachedSolicitanteCollectionNew = new ArrayList<Solicitante>();
            for (Solicitante solicitanteCollectionNewSolicitanteToAttach : solicitanteCollectionNew) {
                solicitanteCollectionNewSolicitanteToAttach = em.getReference(solicitanteCollectionNewSolicitanteToAttach.getClass(), solicitanteCollectionNewSolicitanteToAttach.getIdSolicitante());
                attachedSolicitanteCollectionNew.add(solicitanteCollectionNewSolicitanteToAttach);
            }
            solicitanteCollectionNew = attachedSolicitanteCollectionNew;
            persona.setSolicitanteCollection(solicitanteCollectionNew);
            persona = em.merge(persona);
            for (Perito peritoCollectionNewPerito : peritoCollectionNew) {
                if (!peritoCollectionOld.contains(peritoCollectionNewPerito)) {
                    Persona oldIdPersonaOfPeritoCollectionNewPerito = peritoCollectionNewPerito.getIdPersona();
                    peritoCollectionNewPerito.setIdPersona(persona);
                    peritoCollectionNewPerito = em.merge(peritoCollectionNewPerito);
                    if (oldIdPersonaOfPeritoCollectionNewPerito != null && !oldIdPersonaOfPeritoCollectionNewPerito.equals(persona)) {
                        oldIdPersonaOfPeritoCollectionNewPerito.getPeritoCollection().remove(peritoCollectionNewPerito);
                        oldIdPersonaOfPeritoCollectionNewPerito = em.merge(oldIdPersonaOfPeritoCollectionNewPerito);
                    }
                }
            }
            for (Solicitante solicitanteCollectionNewSolicitante : solicitanteCollectionNew) {
                if (!solicitanteCollectionOld.contains(solicitanteCollectionNewSolicitante)) {
                    Persona oldIdPersonaOfSolicitanteCollectionNewSolicitante = solicitanteCollectionNewSolicitante.getIdPersona();
                    solicitanteCollectionNewSolicitante.setIdPersona(persona);
                    solicitanteCollectionNewSolicitante = em.merge(solicitanteCollectionNewSolicitante);
                    if (oldIdPersonaOfSolicitanteCollectionNewSolicitante != null && !oldIdPersonaOfSolicitanteCollectionNewSolicitante.equals(persona)) {
                        oldIdPersonaOfSolicitanteCollectionNewSolicitante.getSolicitanteCollection().remove(solicitanteCollectionNewSolicitante);
                        oldIdPersonaOfSolicitanteCollectionNewSolicitante = em.merge(oldIdPersonaOfSolicitanteCollectionNewSolicitante);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = persona.getIdPersona();
                if (findPersona(id) == null) {
                    throw new NonexistentEntityException("The persona with id " + id + " no longer exists.");
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
            Persona persona;
            try {
                persona = em.getReference(Persona.class, id);
                persona.getIdPersona();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The persona with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Perito> peritoCollectionOrphanCheck = persona.getPeritoCollection();
            for (Perito peritoCollectionOrphanCheckPerito : peritoCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Persona (" + persona + ") cannot be destroyed since the Perito " + peritoCollectionOrphanCheckPerito + " in its peritoCollection field has a non-nullable idPersona field.");
            }
            Collection<Solicitante> solicitanteCollectionOrphanCheck = persona.getSolicitanteCollection();
            for (Solicitante solicitanteCollectionOrphanCheckSolicitante : solicitanteCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Persona (" + persona + ") cannot be destroyed since the Solicitante " + solicitanteCollectionOrphanCheckSolicitante + " in its solicitanteCollection field has a non-nullable idPersona field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(persona);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Persona> findPersonaEntities() {
        return findPersonaEntities(true, -1, -1);
    }

    public List<Persona> findPersonaEntities(int maxResults, int firstResult) {
        return findPersonaEntities(false, maxResults, firstResult);
    }

    private List<Persona> findPersonaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Persona.class));
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

    public Persona findPersona(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Persona.class, id);
        } finally {
            em.close();
        }
    }

    public int getPersonaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Persona> rt = cq.from(Persona.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}

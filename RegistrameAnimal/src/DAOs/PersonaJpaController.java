/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import DAOs.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.Mascota;
import entidades.Persona;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author emi
 */
public class PersonaJpaController implements Serializable {

    public PersonaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Persona persona) {
        if (persona.getMascotasRegistradas() == null) {
            persona.setMascotasRegistradas(new ArrayList<Mascota>());
        }
        if (persona.getMascotasSeguidas() == null) {
            persona.setMascotasSeguidas(new ArrayList<Mascota>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Mascota> attachedMascotasRegistradas = new ArrayList<Mascota>();
            for (Mascota mascotasRegistradasMascotaToAttach : persona.getMascotasRegistradas()) {
                mascotasRegistradasMascotaToAttach = em.getReference(mascotasRegistradasMascotaToAttach.getClass(), mascotasRegistradasMascotaToAttach.getId());
                attachedMascotasRegistradas.add(mascotasRegistradasMascotaToAttach);
            }
            persona.setMascotasRegistradas(attachedMascotasRegistradas);
            Collection<Mascota> attachedMascotasSeguidas = new ArrayList<Mascota>();
            for (Mascota mascotasSeguidasMascotaToAttach : persona.getMascotasSeguidas()) {
                mascotasSeguidasMascotaToAttach = em.getReference(mascotasSeguidasMascotaToAttach.getClass(), mascotasSeguidasMascotaToAttach.getId());
                attachedMascotasSeguidas.add(mascotasSeguidasMascotaToAttach);
            }
            persona.setMascotasSeguidas(attachedMascotasSeguidas);
            em.persist(persona);
            for (Mascota mascotasRegistradasMascota : persona.getMascotasRegistradas()) {
                Persona oldRegistradoPorOfMascotasRegistradasMascota = mascotasRegistradasMascota.getRegistradoPor();
                mascotasRegistradasMascota.setRegistradoPor(persona);
                mascotasRegistradasMascota = em.merge(mascotasRegistradasMascota);
                if (oldRegistradoPorOfMascotasRegistradasMascota != null) {
                    oldRegistradoPorOfMascotasRegistradasMascota.getMascotasRegistradas().remove(mascotasRegistradasMascota);
                    oldRegistradoPorOfMascotasRegistradasMascota = em.merge(oldRegistradoPorOfMascotasRegistradasMascota);
                }
            }
            for (Mascota mascotasSeguidasMascota : persona.getMascotasSeguidas()) {
                Persona oldRegistradoPorOfMascotasSeguidasMascota = mascotasSeguidasMascota.getRegistradoPor();
                mascotasSeguidasMascota.setRegistradoPor(persona);
                mascotasSeguidasMascota = em.merge(mascotasSeguidasMascota);
                if (oldRegistradoPorOfMascotasSeguidasMascota != null) {
                    oldRegistradoPorOfMascotasSeguidasMascota.getMascotasSeguidas().remove(mascotasSeguidasMascota);
                    oldRegistradoPorOfMascotasSeguidasMascota = em.merge(oldRegistradoPorOfMascotasSeguidasMascota);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Persona persona) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Persona persistentPersona = em.find(Persona.class, persona.getId());
            Collection<Mascota> mascotasRegistradasOld = persistentPersona.getMascotasRegistradas();
            Collection<Mascota> mascotasRegistradasNew = persona.getMascotasRegistradas();
            Collection<Mascota> mascotasSeguidasOld = persistentPersona.getMascotasSeguidas();
            Collection<Mascota> mascotasSeguidasNew = persona.getMascotasSeguidas();
            Collection<Mascota> attachedMascotasRegistradasNew = new ArrayList<Mascota>();
            for (Mascota mascotasRegistradasNewMascotaToAttach : mascotasRegistradasNew) {
                mascotasRegistradasNewMascotaToAttach = em.getReference(mascotasRegistradasNewMascotaToAttach.getClass(), mascotasRegistradasNewMascotaToAttach.getId());
                attachedMascotasRegistradasNew.add(mascotasRegistradasNewMascotaToAttach);
            }
            mascotasRegistradasNew = attachedMascotasRegistradasNew;
            persona.setMascotasRegistradas(mascotasRegistradasNew);
            Collection<Mascota> attachedMascotasSeguidasNew = new ArrayList<Mascota>();
            for (Mascota mascotasSeguidasNewMascotaToAttach : mascotasSeguidasNew) {
                mascotasSeguidasNewMascotaToAttach = em.getReference(mascotasSeguidasNewMascotaToAttach.getClass(), mascotasSeguidasNewMascotaToAttach.getId());
                attachedMascotasSeguidasNew.add(mascotasSeguidasNewMascotaToAttach);
            }
            mascotasSeguidasNew = attachedMascotasSeguidasNew;
            persona.setMascotasSeguidas(mascotasSeguidasNew);
            persona = em.merge(persona);
            for (Mascota mascotasRegistradasOldMascota : mascotasRegistradasOld) {
                if (!mascotasRegistradasNew.contains(mascotasRegistradasOldMascota)) {
                    mascotasRegistradasOldMascota.setRegistradoPor(null);
                    mascotasRegistradasOldMascota = em.merge(mascotasRegistradasOldMascota);
                }
            }
            for (Mascota mascotasRegistradasNewMascota : mascotasRegistradasNew) {
                if (!mascotasRegistradasOld.contains(mascotasRegistradasNewMascota)) {
                    Persona oldRegistradoPorOfMascotasRegistradasNewMascota = mascotasRegistradasNewMascota.getRegistradoPor();
                    mascotasRegistradasNewMascota.setRegistradoPor(persona);
                    mascotasRegistradasNewMascota = em.merge(mascotasRegistradasNewMascota);
                    if (oldRegistradoPorOfMascotasRegistradasNewMascota != null && !oldRegistradoPorOfMascotasRegistradasNewMascota.equals(persona)) {
                        oldRegistradoPorOfMascotasRegistradasNewMascota.getMascotasRegistradas().remove(mascotasRegistradasNewMascota);
                        oldRegistradoPorOfMascotasRegistradasNewMascota = em.merge(oldRegistradoPorOfMascotasRegistradasNewMascota);
                    }
                }
            }
            for (Mascota mascotasSeguidasOldMascota : mascotasSeguidasOld) {
                if (!mascotasSeguidasNew.contains(mascotasSeguidasOldMascota)) {
                    mascotasSeguidasOldMascota.setRegistradoPor(null);
                    mascotasSeguidasOldMascota = em.merge(mascotasSeguidasOldMascota);
                }
            }
            for (Mascota mascotasSeguidasNewMascota : mascotasSeguidasNew) {
                if (!mascotasSeguidasOld.contains(mascotasSeguidasNewMascota)) {
                    Persona oldRegistradoPorOfMascotasSeguidasNewMascota = mascotasSeguidasNewMascota.getRegistradoPor();
                    mascotasSeguidasNewMascota.setRegistradoPor(persona);
                    mascotasSeguidasNewMascota = em.merge(mascotasSeguidasNewMascota);
                    if (oldRegistradoPorOfMascotasSeguidasNewMascota != null && !oldRegistradoPorOfMascotasSeguidasNewMascota.equals(persona)) {
                        oldRegistradoPorOfMascotasSeguidasNewMascota.getMascotasSeguidas().remove(mascotasSeguidasNewMascota);
                        oldRegistradoPorOfMascotasSeguidasNewMascota = em.merge(oldRegistradoPorOfMascotasSeguidasNewMascota);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = persona.getId();
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

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Persona persona;
            try {
                persona = em.getReference(Persona.class, id);
                persona.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The persona with id " + id + " no longer exists.", enfe);
            }
            Collection<Mascota> mascotasRegistradas = persona.getMascotasRegistradas();
            for (Mascota mascotasRegistradasMascota : mascotasRegistradas) {
                mascotasRegistradasMascota.setRegistradoPor(null);
                mascotasRegistradasMascota = em.merge(mascotasRegistradasMascota);
            }
            Collection<Mascota> mascotasSeguidas = persona.getMascotasSeguidas();
            for (Mascota mascotasSeguidasMascota : mascotasSeguidas) {
                mascotasSeguidasMascota.setRegistradoPor(null);
                mascotasSeguidasMascota = em.merge(mascotasSeguidasMascota);
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

    public Persona findPersona(Long id) {
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

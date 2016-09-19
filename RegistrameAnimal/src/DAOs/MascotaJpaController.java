/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import DAOs.exceptions.NonexistentEntityException;
import entidades.Mascota;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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
public class MascotaJpaController implements Serializable {

    public MascotaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Mascota mascota) {
        if (mascota.getSeguidores() == null) {
            mascota.setSeguidores(new ArrayList<Persona>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Persona registradoPor = mascota.getRegistradoPor();
            if (registradoPor != null) {
                registradoPor = em.getReference(registradoPor.getClass(), registradoPor.getId());
                mascota.setRegistradoPor(registradoPor);
            }
            Collection<Persona> attachedSeguidores = new ArrayList<Persona>();
            for (Persona seguidoresPersonaToAttach : mascota.getSeguidores()) {
                seguidoresPersonaToAttach = em.getReference(seguidoresPersonaToAttach.getClass(), seguidoresPersonaToAttach.getId());
                attachedSeguidores.add(seguidoresPersonaToAttach);
            }
            mascota.setSeguidores(attachedSeguidores);
            em.persist(mascota);
            if (registradoPor != null) {
                registradoPor.getMascotasRegistradas().add(mascota);
                registradoPor = em.merge(registradoPor);
            }
            for (Persona seguidoresPersona : mascota.getSeguidores()) {
                seguidoresPersona.getMascotasSeguidas().add(mascota);
                seguidoresPersona = em.merge(seguidoresPersona);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Mascota mascota) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Mascota persistentMascota = em.find(Mascota.class, mascota.getId());
            Persona registradoPorOld = persistentMascota.getRegistradoPor();
            Persona registradoPorNew = mascota.getRegistradoPor();
            Collection<Persona> seguidoresOld = persistentMascota.getSeguidores();
            Collection<Persona> seguidoresNew = mascota.getSeguidores();
            if (registradoPorNew != null) {
                registradoPorNew = em.getReference(registradoPorNew.getClass(), registradoPorNew.getId());
                mascota.setRegistradoPor(registradoPorNew);
            }
            Collection<Persona> attachedSeguidoresNew = new ArrayList<Persona>();
            for (Persona seguidoresNewPersonaToAttach : seguidoresNew) {
                seguidoresNewPersonaToAttach = em.getReference(seguidoresNewPersonaToAttach.getClass(), seguidoresNewPersonaToAttach.getId());
                attachedSeguidoresNew.add(seguidoresNewPersonaToAttach);
            }
            seguidoresNew = attachedSeguidoresNew;
            mascota.setSeguidores(seguidoresNew);
            mascota = em.merge(mascota);
            if (registradoPorOld != null && !registradoPorOld.equals(registradoPorNew)) {
                registradoPorOld.getMascotasRegistradas().remove(mascota);
                registradoPorOld = em.merge(registradoPorOld);
            }
            if (registradoPorNew != null && !registradoPorNew.equals(registradoPorOld)) {
                registradoPorNew.getMascotasRegistradas().add(mascota);
                registradoPorNew = em.merge(registradoPorNew);
            }
            for (Persona seguidoresOldPersona : seguidoresOld) {
                if (!seguidoresNew.contains(seguidoresOldPersona)) {
                    seguidoresOldPersona.getMascotasSeguidas().remove(mascota);
                    seguidoresOldPersona = em.merge(seguidoresOldPersona);
                }
            }
            for (Persona seguidoresNewPersona : seguidoresNew) {
                if (!seguidoresOld.contains(seguidoresNewPersona)) {
                    seguidoresNewPersona.getMascotasSeguidas().add(mascota);
                    seguidoresNewPersona = em.merge(seguidoresNewPersona);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = mascota.getId();
                if (findMascota(id) == null) {
                    throw new NonexistentEntityException("The mascota with id " + id + " no longer exists.");
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
            Mascota mascota;
            try {
                mascota = em.getReference(Mascota.class, id);
                mascota.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The mascota with id " + id + " no longer exists.", enfe);
            }
            Persona registradoPor = mascota.getRegistradoPor();
            if (registradoPor != null) {
                registradoPor.getMascotasRegistradas().remove(mascota);
                registradoPor = em.merge(registradoPor);
            }
            Collection<Persona> seguidores = mascota.getSeguidores();
            for (Persona seguidoresPersona : seguidores) {
                seguidoresPersona.getMascotasSeguidas().remove(mascota);
                seguidoresPersona = em.merge(seguidoresPersona);
            }
            em.remove(mascota);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Mascota> findMascotaEntities() {
        return findMascotaEntities(true, -1, -1);
    }

    public List<Mascota> findMascotaEntities(int maxResults, int firstResult) {
        return findMascotaEntities(false, maxResults, firstResult);
    }

    private List<Mascota> findMascotaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Mascota.class));
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

    public Mascota findMascota(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Mascota.class, id);
        } finally {
            em.close();
        }
    }

    public int getMascotaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Mascota> rt = cq.from(Mascota.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}


package com.mycompany.carrillolorena_pruebatec2.persistence;

import com.mycompany.carrillolorena_pruebatec2.logic.Appointment;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.mycompany.carrillolorena_pruebatec2.logic.Citizen;
import com.mycompany.carrillolorena_pruebatec2.persistence.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class AppointmentJpaController implements Serializable {

    public AppointmentJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;
    
    public AppointmentJpaController(){
        this.emf = Persistence.createEntityManagerFactory("AppointmentPU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Appointment appointment) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Citizen citizen = appointment.getCitizen();
            if (citizen != null) {
                citizen = em.getReference(citizen.getClass(), citizen.getCitizenId());
                appointment.setCitizen(citizen);
            }
            em.persist(appointment);
            if (citizen != null) {
                citizen.getAppointment().add(appointment);
                citizen = em.merge(citizen);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Appointment appointment) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Appointment persistentAppointment = em.find(Appointment.class, appointment.getAppointmentId());
            Citizen citizenOld = persistentAppointment.getCitizen();
            Citizen citizenNew = appointment.getCitizen();
            if (citizenNew != null) {
                citizenNew = em.getReference(citizenNew.getClass(), citizenNew.getCitizenId());
                appointment.setCitizen(citizenNew);
            }
            appointment = em.merge(appointment);
            if (citizenOld != null && !citizenOld.equals(citizenNew)) {
                citizenOld.getAppointment().remove(appointment);
                citizenOld = em.merge(citizenOld);
            }
            if (citizenNew != null && !citizenNew.equals(citizenOld)) {
                citizenNew.getAppointment().add(appointment);
                citizenNew = em.merge(citizenNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = appointment.getAppointmentId();
                if (findAppointment(id) == null) {
                    throw new NonexistentEntityException("The appointment with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(int id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Appointment appointment;
            try {
                appointment = em.getReference(Appointment.class, id);
                appointment.getAppointmentId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The appointment with id " + id + " no longer exists.", enfe);
            }
            Citizen citizen = appointment.getCitizen();
            if (citizen != null) {
                citizen.getAppointment().remove(appointment);
                citizen = em.merge(citizen);
            }
            em.remove(appointment);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Appointment> findAppointmentEntities() {
        return findAppointmentEntities(true, -1, -1);
    }

    public List<Appointment> findAppointmentEntities(int maxResults, int firstResult) {
        return findAppointmentEntities(false, maxResults, firstResult);
    }

    private List<Appointment> findAppointmentEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Appointment.class));
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

    public Appointment findAppointment(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Appointment.class, id);
        } finally {
            em.close();
        }
    }

    public int getAppointmentCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Appointment> rt = cq.from(Appointment.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.carrillolorena_pruebatec2.persistence;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.mycompany.carrillolorena_pruebatec2.logic.Appointment;
import com.mycompany.carrillolorena_pruebatec2.logic.Citizen;
import com.mycompany.carrillolorena_pruebatec2.persistence.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author chish
 */
public class CitizenJpaController implements Serializable {

    public CitizenJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;
    
    public CitizenJpaController(){
        emf = Persistence.createEntityManagerFactory("AppointmentPU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Citizen citizen) {
        if (citizen.getAppointment() == null) {
            citizen.setAppointment(new ArrayList<Appointment>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Appointment> attachedAppointment = new ArrayList<Appointment>();
            for (Appointment appointmentAppointmentToAttach : citizen.getAppointment()) {
                appointmentAppointmentToAttach = em.getReference(appointmentAppointmentToAttach.getClass(), appointmentAppointmentToAttach.getAppointmentId());
                attachedAppointment.add(appointmentAppointmentToAttach);
            }
            citizen.setAppointment(attachedAppointment);
            em.persist(citizen);
            for (Appointment appointmentAppointment : citizen.getAppointment()) {
                Citizen oldCitizenOfAppointmentAppointment = appointmentAppointment.getCitizen();
                appointmentAppointment.setCitizen(citizen);
                appointmentAppointment = em.merge(appointmentAppointment);
                if (oldCitizenOfAppointmentAppointment != null) {
                    oldCitizenOfAppointmentAppointment.getAppointment().remove(appointmentAppointment);
                    oldCitizenOfAppointmentAppointment = em.merge(oldCitizenOfAppointmentAppointment);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Citizen citizen) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Citizen persistentCitizen = em.find(Citizen.class, citizen.getCitizenId());
            List<Appointment> appointmentOld = persistentCitizen.getAppointment();
            List<Appointment> appointmentNew = citizen.getAppointment();
            List<Appointment> attachedAppointmentNew = new ArrayList<Appointment>();
            for (Appointment appointmentNewAppointmentToAttach : appointmentNew) {
                appointmentNewAppointmentToAttach = em.getReference(appointmentNewAppointmentToAttach.getClass(), appointmentNewAppointmentToAttach.getAppointmentId());
                attachedAppointmentNew.add(appointmentNewAppointmentToAttach);
            }
            appointmentNew = attachedAppointmentNew;
            citizen.setAppointment(appointmentNew);
            citizen = em.merge(citizen);
            for (Appointment appointmentOldAppointment : appointmentOld) {
                if (!appointmentNew.contains(appointmentOldAppointment)) {
                    appointmentOldAppointment.setCitizen(null);
                    appointmentOldAppointment = em.merge(appointmentOldAppointment);
                }
            }
            for (Appointment appointmentNewAppointment : appointmentNew) {
                if (!appointmentOld.contains(appointmentNewAppointment)) {
                    Citizen oldCitizenOfAppointmentNewAppointment = appointmentNewAppointment.getCitizen();
                    appointmentNewAppointment.setCitizen(citizen);
                    appointmentNewAppointment = em.merge(appointmentNewAppointment);
                    if (oldCitizenOfAppointmentNewAppointment != null && !oldCitizenOfAppointmentNewAppointment.equals(citizen)) {
                        oldCitizenOfAppointmentNewAppointment.getAppointment().remove(appointmentNewAppointment);
                        oldCitizenOfAppointmentNewAppointment = em.merge(oldCitizenOfAppointmentNewAppointment);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = citizen.getCitizenId();
                if (findCitizen(id) == null) {
                    throw new NonexistentEntityException("The citizen with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Citizen citizen;
            try {
                citizen = em.getReference(Citizen.class, id);
                citizen.getCitizenId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The citizen with id " + id + " no longer exists.", enfe);
            }
            List<Appointment> appointment = citizen.getAppointment();
            for (Appointment appointmentAppointment : appointment) {
                appointmentAppointment.setCitizen(null);
                appointmentAppointment = em.merge(appointmentAppointment);
            }
            em.remove(citizen);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Citizen> findCitizenEntities() {
        return findCitizenEntities(true, -1, -1);
    }

    public List<Citizen> findCitizenEntities(int maxResults, int firstResult) {
        return findCitizenEntities(false, maxResults, firstResult);
    }

    private List<Citizen> findCitizenEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Citizen.class));
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

    public Citizen findCitizen(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Citizen.class, id);
        } finally {
            em.close();
        }
    }
   

    public int getCitizenCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Citizen> rt = cq.from(Citizen.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public Citizen findByDni(String dni) {
        EntityManager em = getEntityManager();
        try {
            Query query = em.createQuery("SELECT c FROM Citizen c WHERE c.dni = :dni");
            query.setParameter("dni", dni);
            return (Citizen) query.getSingleResult();
        } finally {
            em.close();
        }
    }
    
}

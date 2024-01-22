
package com.mycompany.carrillolorena_pruebatec2.persistence;

import com.mycompany.carrillolorena_pruebatec2.logic.Appointment;
import com.mycompany.carrillolorena_pruebatec2.logic.Citizen;
import com.mycompany.carrillolorena_pruebatec2.persistence.exceptions.NonexistentEntityException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class PersistenceController {
    
    CitizenJpaController citizenJPA = new CitizenJpaController();
    AppointmentJpaController appointmentJPA = new AppointmentJpaController();

    // Crear una cita
    public void createCitizen(Citizen citizen) {
        try {
            citizenJPA.create(citizen);
        } catch (Exception e) {
            // Manejar la excepción adecuadamente (por ejemplo, loguearla)
            e.printStackTrace();
        }
    }

    // Crear una cita
    public void createAppointment(Appointment appointment) {
        try {
            appointmentJPA.create(appointment);
        } catch (Exception e) {
            // Manejar la excepción adecuadamente (por ejemplo, loguearla)
            e.printStackTrace();
        }
    }

    // Eliminar un ciudadano por DNI
    // Eliminar un ciudadano por DNI
   public void deleteCitizen(String dni) {
      try {
        citizenJPA.destroy(dni);
      } catch (NonexistentEntityException ex) {
        // Manejar el caso en el que no se encuentre el ciudadano
        System.out.println("No se encontró un ciudadano con el DNI: " + dni);
        // Opcional: loguear la excepción
        Logger.getLogger(PersistenceController.class.getName()).log(Level.SEVERE, null, ex);
    }
}

    // Eliminar una cita por ID
    public void deleteAppointment(int id) {
        try {
            appointmentJPA.destroy(id);
        } catch (Exception e) {
            // Manejar la excepción adecuadamente (por ejemplo, loguearla)
            e.printStackTrace();
        }
    }

    // Editar un ciudadano
    public void editarCitizen(Citizen citizen) {
        try {
            citizenJPA.edit(citizen);
        } catch (Exception e) {
            // Manejar la excepción adecuadamente (por ejemplo, loguearla)
            e.printStackTrace();
        }
    }

    // Editar una cita
    public void editAppointment(Appointment appointment) {
        try {
            appointmentJPA.edit(appointment);
        } catch (Exception e) {
            // Manejar la excepción adecuadamente (por ejemplo, loguearla)
            e.printStackTrace();
        }
    }

    // Obtener todos los ciudadanos
    public List<Citizen> getCitizen() {
        return citizenJPA.findCitizenEntities();
    }

    // Obtener todas las citas
    public List<Appointment> getAppointment() {
        return appointmentJPA.findAppointmentEntities();
    }

    // Obtener una cita por ID
    public Appointment getAppointmentId(int id) {
        return appointmentJPA.findAppointment(id);
    }

    // Obtener un ciudadano por ID
    public Citizen getCitizenId(int id) {
        return citizenJPA.findCitizen(id);
    }

    public Citizen getCitizenByDni(String dni) {
        return citizenJPA.findByDni(dni);
    }
}
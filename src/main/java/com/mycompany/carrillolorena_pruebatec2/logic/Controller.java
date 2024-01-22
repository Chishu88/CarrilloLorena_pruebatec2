
package com.mycompany.carrillolorena_pruebatec2.logic;

import com.mycompany.carrillolorena_pruebatec2.persistence.PersistenceController;
import java.util.List;


public class Controller {
    
    PersistenceController persisController = new PersistenceController();
    
    public void createCitizen(Citizen citizen) {
        persisController.createCitizen(citizen);
    }

    public void createAppointment(Appointment appointment) {
        persisController.createAppointment(appointment);
    }

    public void deleteCitizen(String dni) {
        persisController.deleteCitizen(dni);
    }

    public void deleteAppointment(int id) {
        persisController.deleteAppointment(id);
    }


    public void editarCitizen(Citizen citizen) {
        persisController.editarCitizen(citizen);
    }

    public void editAppointment(Appointment appointment) {
        persisController.editAppointment(appointment);
    }


    public List<Citizen> getCitizen() {
        return persisController.getCitizen();
    }

    public List<Appointment> getAppointment() {
        return persisController.getAppointment();
    }

    public Appointment getAppointmentId(int id) {
        return persisController.getAppointmentId(id);
    }

   /* public List<Appointment> traerTurnosDePersona(String dni) {
        return persisController.traerTurnosDePersona(dni);
    }*/

     public Citizen getCitizenByDni(String dni) {
        return persisController.getCitizenByDni(dni);
    }

    public Citizen getCitizenId(int id) {
        return persisController.getCitizenId(id);
    }
}
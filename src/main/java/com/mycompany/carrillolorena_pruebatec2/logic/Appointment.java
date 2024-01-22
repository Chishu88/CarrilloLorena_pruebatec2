
package com.mycompany.carrillolorena_pruebatec2.logic;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Appointment implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int appointmentId;
    
    private LocalDate date;
    
    private String description;
    
    private String status;
    
    @ManyToOne
    @JoinColumn(name = "citizen_id")
    private Citizen citizen;

    public Appointment() {
    }

    public Appointment( LocalDate date, String description, String status, Citizen citizen) {
        this.date = date;
        this.description = description;
        this.status = status;
        this.citizen = citizen;
    }

    public int getAppointmentId() {   return appointmentId;}

    public void setAppointmentId(int appointmentId) { this.appointmentId = appointmentId;}

    public LocalDate getDate() {  return date;}

    public void setDate(LocalDate date) { this.date = date;}

    public String getDescription() { return description;}

    public void setDescription(String description) {   this.description = description;}

    public String getStatus() { return status;}

    public void setStatus(String status) { this.status = status;}

    public Citizen getCitizen() {  return citizen; }

    public void setCitizen(Citizen citizen) { this.citizen = citizen; }

    @Override
    public String toString() {
        return "Appointment{" + "appointmentId=" + appointmentId + ", date=" + date + ", description=" + description + ", status=" + status + ", citizen=" + citizen + '}';
    }
    
    
    
}

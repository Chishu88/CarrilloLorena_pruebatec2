
package com.mycompany.carrillolorena_pruebatec2.logic;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Citizen implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int citizenId;
    
    private String dni;
    
    private String name;
    
    private String lastName;
    
    @OneToMany(mappedBy = "citizen")
    private List<Appointment> appointment;

    public Citizen() {
    }

    public Citizen(String dni, String name, String lastName, List<Appointment> appointment) {
        this.dni = dni;
        this.name = name;
        this.lastName = lastName;
        this.appointment = appointment;
    }

    public int getCitizenId() {  return citizenId;}

    public void setCitizenId(int citizenId) { this.citizenId = citizenId; }

    public String getDni() {return dni;}

    public void setDni(String dni) { this.dni = dni;}

    public String getName() { return name;}

    public void setName(String name) { this.name = name;}

    public String getLastName() { return lastName;}

    public void setLastName(String lastName) {  this.lastName = lastName;}

    public List<Appointment> getAppointment() { return appointment;}

    public void setAppointment(List<Appointment> appointment) { this.appointment = appointment;}

    @Override
    public String toString() {
        return "Citizen{" + "citizenId=" + citizenId + ", dni=" + dni + ", name=" + name + ", lastName=" + lastName + ", appointment=" + appointment + '}';
    }
    
    
}

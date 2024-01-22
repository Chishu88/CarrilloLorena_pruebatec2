
package com.mycompany.carrillolorena_pruebatec2.servlets;

import com.mycompany.carrillolorena_pruebatec2.logic.Appointment;
import com.mycompany.carrillolorena_pruebatec2.logic.Citizen;
import com.mycompany.carrillolorena_pruebatec2.logic.Controller;
import java.io.IOException;
import java.time.LocalDate;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "AppointmentSv", urlPatterns = {"/AppointmentSv"})
public class AppointmentSv extends HttpServlet {

    private Controller controller = new Controller();
 
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
  
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
       
       String dni = request.getParameter("dni");
    String description = request.getParameter("description");
    LocalDate date = LocalDate.parse(request.getParameter("date"));
    String status = request.getParameter("status");

    // Obtener el ciudadano por DNI
    Citizen citizen = controller.getCitizenByDni(dni);

    if (citizen != null) {
        // Crear nuevo turno y asignar al ciudadano
        Appointment appointment = new Appointment(date, description, status, citizen);
        controller.createAppointment(appointment);

        // Redirigir a la página principal
        response.sendRedirect("index.jsp");
    } else {
        // Ciudadano no encontrado, manejar la situación según tus necesidades
        request.setAttribute("errorMessage", "Ciudadano no encontrado");
        // Puedes redirigir a una página de error o mostrar el mensaje en la página principal
        request.getRequestDispatcher("index.jsp").forward(request, response);
       }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

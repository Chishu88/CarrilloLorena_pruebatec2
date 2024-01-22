
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

    // Controller instance to interact with the business logic
    private Controller controller = new Controller();
 
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
  
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
/**
     * This servlet handles HTTP POST requests, typically used for form submissions.
     * It retrieves parameters from the form, interacts with a Controller to handle business logic, 
     * and redirects to the main page (index.jsp). If the citizen is not found, 
     * it sets an error message attribute for handling in the frontend.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
       // Retrieve parameters from the form submission
    String dni = request.getParameter("dni");
    String description = request.getParameter("description");
    LocalDate date = LocalDate.parse(request.getParameter("date"));
    String status = request.getParameter("status");

    // Obtain the citizen by their DNI (identification number)
    Citizen citizen = controller.getCitizenByDni(dni);

    if (citizen != null) {
        // Create a new appointment and assign it to the citizen
        Appointment appointment = new Appointment(date, description, status, citizen);
        controller.createAppointment(appointment);

        // Redirect to the main page (index.jsp) after successful appointment creation
        response.sendRedirect("index.jsp");
    } else {
        // Handle the case where the citizen is not found
        // Set an error message attribute for display in the frontend
        request.setAttribute("errorMessage", "Ciudadano no encontrado");
        // You can redirect to an error page or display the message on the main page
        request.getRequestDispatcher("index.jsp").forward(request, response);
       }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

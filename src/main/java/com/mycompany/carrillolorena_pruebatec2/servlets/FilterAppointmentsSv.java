
package com.mycompany.carrillolorena_pruebatec2.servlets;

import com.mycompany.carrillolorena_pruebatec2.logic.Appointment;
import com.mycompany.carrillolorena_pruebatec2.logic.Controller;
import java.io.IOException;
import java.io.ObjectInputFilter.Status;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "FilterAppointmentsSv", urlPatterns = {"/FilterAppointmentsSv"})
public class FilterAppointmentsSv extends HttpServlet {

    private Controller controller = new Controller();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
  
    }

  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Obtener la lista completa de citas desde el controlador
    /*List<Appointment> allAppointments = controller.getAppointment();

    // Verificar si se proporcionó una fecha en los parámetros de la solicitud
    String dateParameter = request.getParameter("date");
    LocalDate date = null;

    if (dateParameter != null && !dateParameter.isEmpty()) {
        date = LocalDate.parse(dateParameter);
    }

    final LocalDate finalDate = date;

    // Filtrar las citas por fecha usando streams
    List<Appointment> filteredAppointments = allAppointments.stream()
            .filter(appointment -> (finalDate == null || appointment.getDate().isEqual(finalDate)))
            .collect(Collectors.toList());

    // Establecer las citas filtradas como atributo en la solicitud
    request.setAttribute("filteredAppointments", filteredAppointments);

    // Redirigir a la página index.jsp
    request.getRequestDispatcher("index.jsp").forward(request, response);*/
    
    // Obtener la lista completa de citas desde el controlador
    List<Appointment> allAppointments = controller.getAppointment();

    // Verificar si se proporcionó una fecha en los parámetros de la solicitud
    String dateParameter = request.getParameter("dateFilter");
    final LocalDate date = (dateParameter != null && !dateParameter.isEmpty()) ? LocalDate.parse(dateParameter) : null;

    // Verificar si se proporcionó un estado en los parámetros de la solicitud
    String statusParameter = request.getParameter("status");

    // Filtrar las citas por fecha y estado usando streams
    List<Appointment> filteredAppointments = allAppointments.stream()
            .filter(appointment -> (date == null || appointment.getDate().isEqual(date)))
            .filter(appointment -> (statusParameter == null || statusParameter.isEmpty() || appointment.getStatus().equalsIgnoreCase(statusParameter)))
            .collect(Collectors.toList());

    // Establecer las citas filtradas como atributo en la solicitud
    request.setAttribute("filteredAppointments", filteredAppointments);

    // Redirigir a la página index.jsp
    request.getRequestDispatcher("index.jsp").forward(request, response);
}
        
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.carrillolorena_pruebatec2.servlets;

import com.mycompany.carrillolorena_pruebatec2.logic.Citizen;
import com.mycompany.carrillolorena_pruebatec2.logic.Controller;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/** 
     * This servlet handles HTTP POST request, tipically used for form submissions.
     *  It retrieves parameters the form, creates a Citizen instance, sets its values,
     *  and then calls the createCitizien method in the Controller to
     * handle business logic.
     */
   
@WebServlet(name = "CitizenSv", urlPatterns = {"/CitizenSv"})
public class CitizenSv extends HttpServlet {

    private Controller controller = new Controller();
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        
        Citizen citizen = new Citizen();
        
        String citizenDni = request.getParameter("dni");
        String citizenName = request.getParameter("name");
        String citizenLastName = request.getParameter("lastName");

        citizen.setDni(citizenDni);
        citizen.setName(citizenName);
        citizen.setLastName(citizenLastName);
        controller.createCitizen(citizen);
        response.sendRedirect("appointment.jsp");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}

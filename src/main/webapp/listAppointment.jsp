
<%@page import="com.mycompany.carrillolorena_pruebatec2.logic.Appointment"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>List of Appointments</title>
    </head>
    <body>

        <h1>List of Appointments</h1>

        <div class="ver-appointments">
            <form action="AppointmentSv" method="GET">
                <button type="submit">Show Appointments</button>
            </form>
            <br>
            <% if (request.getAttribute("appointments") != null) { %>
                <table border="1">
                    <thead>
                        <tr>
                            <th>Description</th>
                            <th>Date</th>
                            <th>Status</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% for (Appointment appointment : (List<Appointment>) request.getAttribute("appointments")) { %>
                            <tr>
                                <td><%= appointment.getDescription() %></td>
                                <td><%= appointment.getDate() %></td>
                                <td><%= appointment.getStatus() %></td>
                            </tr>
                        <% } %>
                    </tbody>
                </table>
            <% } else { %>
                <p>No appointments available</p>
            <% } %>
        </div>

    </body>
</html>

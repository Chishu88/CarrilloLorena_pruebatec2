

<%@page import="java.util.List"%>
<%@page import="com.mycompany.carrillolorena_pruebatec2.logic.Appointment"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Appointment Management</title>
    </head>
    <body>

        <h1>Appointment Management</h1>

        <nav>
            <a href="appointment.jsp">Appointment</a>
        </nav>


        <!-- Form to add a new appointment -->
        <form action="CitizenSv" method="POST">
            <label for="dni">DNI:</label>
            <input type="text" name="dni" required><br>

            <label for="name">Name:</label>
            <input type="text" name="name" required><br>

            <label for="lastName">Last Name:</label>
            <input type="text" name="lastName" required><br>

            <button type="submit"> Add Citizen </button>

        </form>

        <hr>

        <div class="Appointments">
    <h2> Table appointments </h2>
    <form action="ListAppointmentsSv" method="GET">
        <button type="submit">Mostrar gestiones</button>
    </form>
    <br>
    <% if (request.getAttribute("appointments") != null) { %>
        <table border="1">
            <thead>
                <tr>
                    <th style="width: 10%;">Id</th>
                    <th style="width: 15%;">Date</th>
                    <th style="width: 40%;">Description</th>
                    <th style="width: 20%;">Status</th>
                    <th style="width: 15%;">Actions</th>
                </tr>
            </thead>
            <tbody>
                <% for (Appointment appointment : (List<Appointment>) request.getAttribute("appointments")) { %>
                    <tr>
                        <td><%= appointment.getAppointmentId() %></td>
                        <td><%= appointment.getDate() %></td>
                        <td><%= appointment.getDescription() %></td>
                        <td><%= appointment.getStatus() %></td>
                        <td>
                            <!-- Botón de Editar -->
                            <form action="EditAppointmentSv" method="GET">
                                <input type="hidden" name="appointmentId" value="<%= appointment.getAppointmentId()%>">
                                <button type="submit">Editar</button>
                            </form>

                            <!-- Botón de Borrar -->
                            <form action="DeleteAppointmentSv" method="POST">
                                <input type="hidden" name="appointmentId" value="<%= appointment.getAppointmentId()%>">
                                <button type="submit">Borrar</button>
                            </form>
                        </td>
                    </tr>
                <% } %>
            </tbody>
        </table>
    <% } %>

   <div class="Appointments">
    <%-- Mostrar citas filtradas --%>
    <h2>Table filtered appointments</h2>

    <!-- Formulario para filtrar citas -->
    <form action="FilterAppointmentsSv" method="GET">
        <label for="date">Date to Filter Appointments:</label>
        <input type="date" name="date" required><br>

        <label for="status">Appointment Status:</label>
        <select name="status">
            <option value="Waiting">Waiting</option>
            <option value="Attended">Attended</option>
        </select><br>

        <button type="submit">Filter Appointments</button>
    </form>

    <% if (request.getAttribute("filteredAppointments") != null) { %>
        <table border="1">
            <thead>
                <tr>
                    <th style="width: 10%;">Id</th>
                    <th style="width: 15%;">Date</th>
                    <th style="width: 40%;">Description</th>
                    <th style="width: 20%;">Status</th>
                    <th style="width: 15%;">Actions</th>
                </tr>
            </thead>
            <tbody>
                <% for (Appointment appointment : (List<Appointment>) request.getAttribute("filteredAppointments")) { %>
                    <tr>
                        <td><%= appointment.getAppointmentId() %></td>
                        <td><%= appointment.getDate() %></td>
                        <td><%= appointment.getDescription() %></td>
                        <td><%= appointment.getStatus() %></td>
                        <td>
                            <!-- Botón de Editar -->
                            <form action="EditAppointmentSv" method="GET">
                                <input type="hidden" name="appointmentId" value="<%= appointment.getAppointmentId()%>">
                                <button type="submit">Editar</button>
                            </form>

                            <!-- Botón de Borrar -->
                            <form action="DeleteAppointmentSv" method="POST">
                                <input type="hidden" name="appointmentId" value="<%= appointment.getAppointmentId()%>">
                                <button type="submit">Borrar</button>
                            </form>
                        </td>
                    </tr>
                <% } %>
            </tbody>
        </table>
    <% } else { %>
        <p>No appointments found.</p>
    <% } %>
</div>

</body>
</html>

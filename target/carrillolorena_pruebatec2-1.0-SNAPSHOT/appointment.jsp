
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Assign Appointment</title>
    </head>
    <h1>Assign Appointment</h1>

<!-- Formulario para asignar turno a un ciudadano existente -->
<form action="AppointmentSv" method="POST">
    <label for="dni">DNI:</label>
    <input type="text" name="dni" required><br>

    <label for="description">Description of the procedure:</label>
    <input type="text" name="description" required><br>

    <label for="date">Date:</label>
    <input type="date" name="date" required><br>

    <label for="status">Appointment Status:</label>
    <select name="status">
        <option value="Waiting">Waiting</option>
        <option value="Attended">Attended</option>
    </select><br>

    <button type="submit">Assign Appointment</button>
</form>

</body>
</html>

# CarrilloLorena_pruebatec2

# Turn Management Application
This is a simple Java web application for managing turns, developed as a test project to assess knowledge in Java, Java Web, JPA, and functional programming. The application allows users to perform CRUD operations on turns, with an emphasis on effective interaction with the backend, user interface, and database.

## Case Description
A government entity requires the development of a turn management application. The application should store turns for different procedures and citizens assigned to them in a database. Turns are assigned based on arrival order and can have one of two states: "Waiting" or "Attended."

*Operations*
Add a New Turn:

Users can input information about a new turn, including number, date, and description of the procedure.
Each turn is exclusive to a citizen, but a citizen can have more than one turn on different occasions.
List Turns:

The application should display a list of all turns along with the assigned citizens, based on a user-provided date.
Filter Turns:

Users can choose to view turns that are "Waiting" (not yet attended) or "Attended."
Filtering is based on the user-provided date and the turn status.
Technical Requirements
Backend Development: Java + Servlets
Database Access: Utilize JPA for database interaction. Configure a data source and entities/tables with their corresponding attributes and relationships.
Data Management: Use Java collections and objects to manage data before interacting with the database.
Functional Programming: Utilize functions lambda, optionals, utils, or streams at least once in the code for better consideration regarding code quality and optimization.
Project Structure
The project is structured with Maven and includes the following components:

*Entities:*

Citizen: Represents information about a citizen.
Appointment: Represents information about a turn.
Controllers:

AppointmentJpaController: Manages the persistence of Appointment entities.
CitizenJpaController: Manages the persistence of Citizen entities.
PersistenceController: Handles general persistence operations.
Servlets:

AppointmentSv: Servlet for managing the creation of new turns.
CitizenSv: Servlet for handling citizen-related operations.
FilterAppointmentsSv: Servlet for filtering turns based on date and status.
ListAppointmentsSv: Servlet for listing turns.
The project uses a Maven web, JPA, JSP, and MySQL database with Workbench.

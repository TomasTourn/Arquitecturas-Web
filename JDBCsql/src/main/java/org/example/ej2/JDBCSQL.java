package org.example.ej2;

import org.example.PersonaDaoDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class JDBCSQL {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3307/arquiDB";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            PersonaDaoDB personaDAO = new PersonaDaoDB(connection);

            personaDAO.createTable();
           // personaDAO.addPersona(new Persona(4, "Lulo", 27));
           // personaDAO.addPersona(new Persona(5, "Lucho", 27));

            List<Persona> personas = personaDAO.getAllPersonas();
            personas.forEach(System.out::println);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

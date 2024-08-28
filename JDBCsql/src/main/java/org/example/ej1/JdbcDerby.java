package org.example.ej1;



import org.example.PersonaDao;
import org.example.PersonaDaoDB;
import org.example.ej2.Persona;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class JdbcDerby {

    private static final String driver = "org.apache.derby.jdbc.EmbeddedDriver";
    private static final String url="jdbc:derby:MyDerbyDb;create=true";


    public static void main(String[] args) {

        try {
            //carga controlador jdbc
            Class.forName(driver);
                //establece la conexión
            try (Connection conn = DriverManager.getConnection(url)) {
                //instancia el dao
                PersonaDao personaDao = new PersonaDaoDB(conn);

                //crea tabla
                personaDao.createTable();

                //add
                //personaDao.addPersona(new Persona(1,"zenzible",23));
                //personaDao.addPersona(new Persona(2,"el dev",27));

                // Obtener y mostrar todas las personas
                List<Persona> personas = personaDao.getAllPersonas();
                personas.forEach(System.out::println);

            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}


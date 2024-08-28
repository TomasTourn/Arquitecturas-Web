package org.example;

import org.example.ej2.Persona;

import java.sql.SQLException;
import java.util.List;

public interface PersonaDao {
    void createTable() throws SQLException;
    void addPersona(Persona persona) throws SQLException;
    List<Persona> getAllPersonas() throws SQLException;
}

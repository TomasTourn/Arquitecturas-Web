package org.example;

import org.example.ej2.Persona;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonaDaoDB implements PersonaDao {

    protected Connection connection;

    public PersonaDaoDB(Connection connection){
        this.connection=connection;
    }

    @Override
    public void createTable() throws SQLException {
        if (!tableExists("persona")){
            String table="CREATE TABLE persona (" +
                    "id INT PRIMARY KEY, " +
                    "nombre VARCHAR(500), " +
                    "edad INT)";
            try(Statement stmt = connection.createStatement()){
                stmt.execute(table);
            }
        }
    }

    @Override
    public List<Persona> getAllPersonas() throws SQLException {
        List<Persona> personas = new ArrayList<>();
        String query= "SELECT * FROM persona";
        try(Statement stmt= connection.createStatement();
            ResultSet rs= stmt.executeQuery(query)){
            while (rs.next()){
                int id= rs.getInt("id");
                String nombre = rs.getString("nombre");
                int edad= rs.getInt("edad");
                personas.add(new Persona(id,nombre,edad));
            }
        }
        return personas;
    }
    public void addPersona(Persona persona)throws SQLException{
        String insert="INSERT INTO persona(id,nombre,edad) VALUES(?,?,?)";
        try(PreparedStatement ps =connection.prepareStatement(insert)){
            ps.setInt(1,persona.getId());
            ps.setString(2,persona.getNombre());
            ps.setInt(3,persona.getEdad());
            ps.executeUpdate();
        }
    }
    public boolean tableExists(String tabla) throws SQLException {
        boolean exists = false;
        try (ResultSet rs = connection.getMetaData().getTables(null, null, tabla.toUpperCase(), null)) {
            if (rs.next()) {
                exists = true;
            }
        }
        return exists;

    }
}

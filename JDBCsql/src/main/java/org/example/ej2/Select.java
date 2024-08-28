package org.example.ej2;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;

public class Select {
    private static java.sql.ResultSet ResultSet;

    public static void main(String[] args) {

        String driver = "com.mysql.cj.jdbc.Driver";

        try {
            Class.forName(driver).getDeclaredConstructor().newInstance();

        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException |
                 ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }
        String uri="jdbc:mysql://localhost:3307/arquiDB";
        try {
            Connection conn= DriverManager.getConnection(uri,"root","root");
            conn.setAutoCommit(false);
            String select = "SELECT * FROM persona";
            PreparedStatement ps= conn.prepareStatement(select);
            ResultSet rs =ps.executeQuery();
            while(rs.next()){
                System.out.println(rs.getInt(1)+", "+rs.getString(2)+", "+rs.getInt(3));
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

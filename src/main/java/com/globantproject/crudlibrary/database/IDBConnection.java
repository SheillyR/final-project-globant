package com.globantproject.crudlibrary.database;

import java.sql.Connection;
import java.sql.DriverManager;
import static com.globantproject.crudlibrary.database.DataBase.*;

public interface IDBConnection {
    default Connection connectToDB() {
        Connection connection = null;

        try {
            // Llamar al driver
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL+DB, USER, PASSWORD);
            if (connection != null){
                System.out.println("se estableció la conexión");
            }
        } catch (Exception e) {
            // Fines de debug
            e.printStackTrace();
        } finally {
            return connection;
        }
    }
}

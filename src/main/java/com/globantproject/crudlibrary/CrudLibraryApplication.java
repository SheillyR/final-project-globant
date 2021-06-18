package com.globantproject.crudlibrary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.*;

@SpringBootApplication
public class CrudLibraryApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudLibraryApplication.class, args);
/*
		Connection connection = null;

		System.out.println("First");
		//INSERT

		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); //hace referencia a la librería
			connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/final_project",
					"root", "emae1712");
		} catch (ClassNotFoundException exception) {
			exception.getStackTrace();
		} catch (SQLException ex) {
			ex.getStackTrace();
		}

		try {

				String queryCreate = "INSERT INTO libros(Title, Author, EditorialYear, State)" +
						"VALUES (?,?,?,?);";
				PreparedStatement sentence = connection.prepareStatement(queryCreate);

				sentence.setString(1, "Test Title");
				sentence.setString(2, "Test Author");
				sentence.setInt(3,2021);
				sentence.setBoolean(4,true);
				sentence.executeUpdate();
			} catch (SQLException e) {
				e.getStackTrace();
			}
 */
	}
}

package com.locadora.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class BaseDAO {
	
	protected static Connection con;
	protected static Statement comando;
	
	static String DB_HOST = "localhost:3306";
	static String DB_DATABASE = "locadora";
	static String DB_USER = "root";
	static String DB_PASSWORD = "";

	protected static void conectar() {  
		try {  
			Class.forName("com.mysql.jdbc.Driver");  
			con = DriverManager.getConnection("jdbc:mysql://" + DB_HOST + "/" + DB_DATABASE, DB_USER, DB_PASSWORD);    
			comando = con.createStatement();   
		} catch (SQLException e) {  
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}  
	}  

	protected static void fechar() {  
		try {  
			comando.close();  
			con.close();   
		} catch (SQLException e) {  
			e.printStackTrace();  
		}  
	}  

}

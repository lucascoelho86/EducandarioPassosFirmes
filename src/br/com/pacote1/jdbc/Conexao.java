package br.com.pacote1.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	
	public static Connection getConnection(){
		Connection con = null;
		
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/crud_web", "root", "");
			System.out.println("Conectado com sucesso!");
		} catch (SQLException e) {
			System.out.println("N�o pode conectar: " + e.getMessage());
		}
		return con;
	}

}

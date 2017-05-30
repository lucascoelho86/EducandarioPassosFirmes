package br.com.pacote1.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	
	public static Connection getConnection(){
		Connection con = null;
		
		try {
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				System.out.println("Driver não encontrado!");
			}
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/crud_web", "root", "");
			System.out.println("Conectado com sucesso!");
		} catch (SQLException e) {
			System.out.println("Não pode conectar: " + e.getMessage());
		}
		return con;
	}

}

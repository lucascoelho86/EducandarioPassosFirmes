package br.com.educandariopassosfirmes.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Conexao {
	
	public static Connection con = null;
	
	public static Connection getConnection(){
		
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
	
	public static PreparedStatement getPreparedStatement(String sql) {
		
		if (con == null) {
			con = getConnection();
		}

		try {
			return con.prepareStatement(sql);
		} catch (SQLException e) {
			System.out.println("Erro de SQL: " + e.getMessage());
			throw new RuntimeException(
					"Erro SQLException ao executar getPreparedStatement em Conexao.java", e);
		}
	}

}

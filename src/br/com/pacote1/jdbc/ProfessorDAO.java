package br.com.pacote1.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.pacote1.entidades.Professor;

public class ProfessorDAO {

	private Connection con = Conexao.getConnection();
	
	public void cadastrar(Professor pProfessor){
		
		String sql = "INSERT INTO PROFESSOR (id_funcionario) values (?)";
		
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			
			preparador.setString(1, pProfessor.getId());
			
			preparador.execute();
			preparador.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}

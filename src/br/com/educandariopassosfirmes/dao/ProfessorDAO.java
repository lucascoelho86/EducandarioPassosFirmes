package br.com.educandariopassosfirmes.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.educandariopassosfirmes.entidades.Professor;

public class ProfessorDAO extends Conexao{

	public void cadastrar(Professor pProfessor){
		
		String sql = "INSERT INTO PROFESSOR (id_funcionario) values (?)";
		
		try {
			PreparedStatement preparador = getPreparedStatement(sql);
			
			preparador.setString(1, pProfessor.getId());
			
			preparador.execute();
			preparador.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void alterar(Professor pProfessor){
		
		String sql = "UPDATE PROFESSOR SET id_funcionario=? WHERE id_funcionario=?";
		
		try {
			PreparedStatement preparador = getPreparedStatement(sql);
			
			preparador.setString(1, pProfessor.getId());
			preparador.setString(2, pProfessor.getId());
			
			preparador.execute();
			preparador.close();
			
			System.out.println("Alterado com sucesso!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void excluir(String pId){
		
		String sql = "DELETE FROM PROFESSOR WHERE id_funcionario=?";
		
		try {
			PreparedStatement preparador = getPreparedStatement(sql);
			
			preparador.setString(1, pId);
			
			preparador.execute();
			preparador.close();
			
			System.out.println("Excluído com sucesso!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
}

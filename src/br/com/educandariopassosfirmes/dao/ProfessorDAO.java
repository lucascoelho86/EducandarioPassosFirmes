package br.com.educandariopassosfirmes.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.educandariopassosfirmes.entidades.Professor;

public class ProfessorDAO extends Conexao{

	public void incluir(Professor pProfessor){
		
		String sql = "INSERT INTO PROFESSOR (ID_PESSOA, FORMACAO, ESTADO_CIVIL, DEPENDENTE, DATA_ADMISSAO, CARGA_HORARIO, SALARIO) values (?,?,?,?,?,?,?)";
		
		try {
			PreparedStatement preparador = getPreparedStatement(sql);
			
			preparador.setString(1, pProfessor.getId());
			preparador.setString(2, pProfessor.getFormacao());
			preparador.setString(3, pProfessor.getEstadoCivil());
			preparador.setInt(4, pProfessor.getQtDependente());
			preparador.setDate(5, pProfessor.getDtAdmissao());
			preparador.setInt(6, pProfessor.getCargaHoraria());
			preparador.setDouble(7, pProfessor.getSalario());
			
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

package br.com.educandariopassosfirmes.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
		
		String sql = "UPDATE PROFESSOR SET FORMACAO=?, ESTADO_CIVIL=?, DEPENDENTE=?, DATA_ADMISSAO=?, CARGA_HORARIO=?, SALARIO=? WHERE ID_PESSOA=?";
		
		try {
			PreparedStatement preparador = getPreparedStatement(sql);
			
			preparador.setString(1, pProfessor.getFormacao());
			preparador.setString(2, pProfessor.getEstadoCivil());
			preparador.setInt(3, pProfessor.getQtDependente());
			preparador.setDate(4, pProfessor.getDtAdmissao());
			preparador.setInt(5, pProfessor.getCargaHoraria());
			preparador.setDouble(6, pProfessor.getSalario());
			preparador.setString(7, pProfessor.getId());
			
			preparador.execute();
			preparador.close();
			
			System.out.println("Alterado com sucesso!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void excluir(String pId){
		
		String sql = "DELETE FROM PROFESSOR WHERE ID_PESSOA=?";
		
		try {
			PreparedStatement preparador = getPreparedStatement(sql);
			
			preparador.setString(1, pId);
			
			preparador.execute();
			preparador.close();
			
			System.out.println("Exclu�do com sucesso!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Professor> consultar(String pId){
		String sql = "SELECT * FROM PROFESSOR ";
		String where = "WHERE ";
		String sql2 = "ID_PESSOA = ?";
		String sqlComplementar = "";
		Professor professor = null;
		ArrayList<Professor> listProfessor = new ArrayList<Professor>();
		int contador=0;
		try{
			
			if(pId != null && !pId.equals("")){
				sqlComplementar = sql2;
			}
			
			if(!sqlComplementar.equals("")){
				sql = sql + where + sqlComplementar;
			}
			
			PreparedStatement preparador = getPreparedStatement(sql);
			
			if(pId != null && !pId.equals("")){
				contador++;
				preparador.setString(contador, pId);
			}
			
			ResultSet resultado = preparador.executeQuery();
			
			while(resultado.next()){
				professor = new Professor();
				
				professor.setId(resultado.getString("ID_PESSOA"));
				professor.setFormacao(resultado.getString("FORMACAO"));
				professor.setEstadoCivil(resultado.getString("ESTADO_CIVIL"));
				professor.setQtDependente(resultado.getInt("DEPENDENTE"));
				professor.setDtAdmissao(resultado.getDate("DATA_ADMISSAO"));
				professor.setCargaHoraria(resultado.getInt("CARGA_HORARIO"));
				professor.setSalario(resultado.getDouble("SALARIO"));
				
				listProfessor.add(professor);
				
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return listProfessor;
		
	}
	
	
}

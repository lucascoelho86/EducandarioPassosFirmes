package br.com.educandariopassosfirmes.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.educandariopassosfirmes.entidades.ProfessorDisciplina;

public class ProfessorDisciplinaDAO extends Conexao{

	public void cadastrar(ProfessorDisciplina pProfessorDisciplina){
		
		String sql = "INSERT INTO PROFESSOR_DISCIPLINA (ID_PESSOA, ID_DISCIPLINA) values (?,?)";
		
		try {
			PreparedStatement preparador = getPreparedStatement(sql);
			
			preparador.setString(1, pProfessorDisciplina.getId_professor());
			preparador.setInt(2, pProfessorDisciplina.getId_disciplina());
			
			preparador.execute();
			preparador.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<ProfessorDisciplina> consultar(String pIdProfessor, Integer pIdDisciplina){
		String sql = "SELECT * FROM PROFESSOR_DISCIPLINA ";
		String where = "WHERE ";
		String sql2 = "ID_PESSOA = ?";
		String sql3 = "ID_DISCIPLINA = ?";
		String conector = "";
		String sqlComplementar = "";
		ProfessorDisciplina professorDisciplina = null;
		ArrayList<ProfessorDisciplina> listProfessorDisciplina = new ArrayList<ProfessorDisciplina>();
		int contador=0;
		try{
			
			if(pIdProfessor != null && !pIdProfessor.equals("")){
				sqlComplementar = sql2;
				conector = "\n AND ";
			}

			if(pIdDisciplina != null && pIdDisciplina != 0){
				sqlComplementar = sqlComplementar + conector + sql3;
			}
			
			if(!sqlComplementar.equals("")){
				sql = sql + where + sqlComplementar;
			}
			
			PreparedStatement preparador = getPreparedStatement(sql);
			
			if(pIdProfessor != null && !pIdProfessor.equals("")){
				contador++;
				preparador.setString(contador, pIdProfessor);
			}

			if(pIdDisciplina != null && pIdDisciplina != 0){
				contador++;
				preparador.setInt(contador, pIdDisciplina);
			}
			
			ResultSet resultado = preparador.executeQuery();
			
			while(resultado.next()){
				professorDisciplina = new ProfessorDisciplina();
				
				professorDisciplina.setId_professor(resultado.getString("ID_PESSOA"));
				professorDisciplina.setId_disciplina(resultado.getInt("ID_DISCIPLINA"));
				
				listProfessorDisciplina.add(professorDisciplina);
				
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return listProfessorDisciplina;
		
	}
	
	public void alterar(ProfessorDisciplina pProfessorDisciplina){
		
		String sql = "UPDATE PROFESSOR_DISCIPLINA SET id_professor=?, id_disciplina=? WHERE id_professor=?";
		
		try {
			PreparedStatement preparador = getPreparedStatement(sql);
			
			preparador.setString(1, pProfessorDisciplina.getId_professor());
			preparador.setInt(2, pProfessorDisciplina.getId_disciplina());
			preparador.setString(3, pProfessorDisciplina.getId_professor());
			
			preparador.execute();
			preparador.close();
			
			System.out.println("Alterado com sucesso!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void excluir(String pIdProfessor, String pIdDisciplina){
		
		String sql = "DELETE FROM PROFESSOR_DISCIPLINA ";
		String whereProfessor = "WHERE ID_PESSOA=?";
		String whereDisciplina = "WHERE ID_DISCIPLINA=?";
		
		try {
			
			if(pIdProfessor != null && !pIdProfessor.equals("")) {
				sql = sql + whereProfessor;
			}
			
			if(pIdDisciplina != null && !pIdDisciplina.equals("") && !pIdDisciplina.equals("0")) {
				sql = sql + whereDisciplina;
			}
			
			PreparedStatement preparador = getPreparedStatement(sql);
			
			if(pIdProfessor != null && !pIdProfessor.equals("")) {
				preparador.setString(1, pIdProfessor);
			}
			
			if(pIdDisciplina != null && !pIdDisciplina.equals("") && !pIdDisciplina.equals("0")) {
				preparador.setString(1, pIdDisciplina);
			}
			
			preparador.execute();
			preparador.close();
			
			System.out.println("Deletado com sucesso!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}

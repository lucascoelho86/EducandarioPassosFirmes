package br.com.pacote1.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.pacote1.entidades.Pessoa;
import br.com.pacote1.entidades.ProfessorDisciplina;

public class ProfessorDisciplinaDAO {

	private Connection con = Conexao.getConnection();
	
	public void cadastrar(ProfessorDisciplina pProfessorDisciplina){
		
		String sql = "INSERT INTO PROFESSOR_DISCIPLINA (id_professor, id_disciplina) values (?,?)";
		
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			
			preparador.setString(1, pProfessorDisciplina.getId_professor());
			preparador.setInt(2, pProfessorDisciplina.getId_disciplina());
			
			preparador.execute();
			preparador.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<ProfessorDisciplina> consultar(String pIdProfessor){
		String sql = "SELECT * FROM PROFESSOR_DISCIPLINA ";
		String where = "WHERE ";
		String sql2 = "ID_PROFESSOR = ?";
		String sqlComplementar = "";
		ProfessorDisciplina professorDisciplina = null;
		List<ProfessorDisciplina> listProfessorDisciplina = new ArrayList<ProfessorDisciplina>();
		int contador=0;
		try{
			
			if(pIdProfessor != null && !pIdProfessor.equals("")){
				sqlComplementar = sql2;
			}
			
			if(!sqlComplementar.equals("")){
				sql = sql + where + sqlComplementar;
			}
			
			PreparedStatement preparador = con.prepareStatement(sql);
			
			if(pIdProfessor != null && !pIdProfessor.equals("")){
				contador++;
				preparador.setString(contador, pIdProfessor);
			}
			
			ResultSet resultado = preparador.executeQuery();
			
			while(resultado.next()){
				professorDisciplina = new ProfessorDisciplina();
				
				professorDisciplina.setId_professor(resultado.getString("id_professor"));
				professorDisciplina.setId_disciplina(resultado.getInt("id_disciplina"));
				
				listProfessorDisciplina.add(professorDisciplina);
				
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return listProfessorDisciplina;
		
	}
	
}

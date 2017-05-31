package br.com.pacote1.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
	
}

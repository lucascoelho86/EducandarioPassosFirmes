package br.com.pacote1.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.pacote1.entidades.TurmaAluno;

public class TurmaAlunoDAO {

	private Connection con = Conexao.getConnection();
	
	 public void cadastrar(TurmaAluno pTurmaAluno){
		
		String sql = "INSERT INTO TURMA_ALUNO (id_turma, id_aluno) values (?, ?)";
		
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			
			preparador.setInt(1, pTurmaAluno.getIdTurma());
			preparador.setString(2, pTurmaAluno.getIdAluno());
			
			preparador.execute();
			preparador.close();
			
			System.out.println("Cadastrado com sucesso!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}

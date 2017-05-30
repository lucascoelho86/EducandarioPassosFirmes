package br.com.pacote1.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.pacote1.entidades.ResponsavelAluno;

public class ResponsavelAlunoDAO {

	private Connection con = Conexao.getConnection();
	
	 public void cadastrar(ResponsavelAluno pResponsavelAluno){
		
		String sql = "INSERT INTO RESPONSAVEL_ALUNO (id_responsavel, id_aluno) values (?, ?)";
		
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			
			preparador.setString(1, pResponsavelAluno.getIdResponsavel());
			preparador.setString(2, pResponsavelAluno.getIdAluno());
			
			preparador.execute();
			preparador.close();
			
			System.out.println("Cadastrado com sucesso!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}

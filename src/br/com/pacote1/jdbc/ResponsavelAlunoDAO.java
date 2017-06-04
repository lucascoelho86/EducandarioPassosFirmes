package br.com.pacote1.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.pacote1.entidades.Pessoa;
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
	 
	 public ResponsavelAluno consultar(String pMatricula){
			String sql = "SELECT * FROM RESPONSAVEL_ALUNO ";
			String where = "WHERE ";
			String sql2 = "ID_ALUNO = ?";
			String sqlComplementar = "";
			ResponsavelAluno responsavelAluno = null;
			int contador=0;
			try{
				
				if(pMatricula != null && !pMatricula.equals("")){
					sqlComplementar = sql2;
				}
				
				if(!sqlComplementar.equals("")){
					sql = sql + where + sqlComplementar;
				}
				
				PreparedStatement preparador = con.prepareStatement(sql);
				
				if(pMatricula != null && !pMatricula.equals("")){
					contador++;
					preparador.setString(contador, pMatricula);
				}
				
				ResultSet resultado = preparador.executeQuery();
				
				if(resultado.next()){
					responsavelAluno = new ResponsavelAluno();
					
					responsavelAluno.setIdResponsavel(resultado.getString("id_responsavel"));
					responsavelAluno.setIdAluno(resultado.getString("id_aluno"));
					
				}
			}catch(SQLException e){
				e.printStackTrace();
			}
			
			return responsavelAluno;
			
		}
	
}

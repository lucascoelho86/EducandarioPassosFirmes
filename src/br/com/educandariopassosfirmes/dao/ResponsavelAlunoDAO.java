package br.com.educandariopassosfirmes.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.educandariopassosfirmes.entidades.ResponsavelAluno;

public class ResponsavelAlunoDAO extends Conexao{

	 public void cadastrar(ResponsavelAluno pResponsavelAluno){
		
		String sql = "INSERT INTO RESPONSAVEL_ALUNO (id_responsavel, id_aluno) values (?, ?)";
		
		try {
			PreparedStatement preparador = getPreparedStatement(sql);
			
			preparador.setString(1, pResponsavelAluno.getIdResponsavel());
			preparador.setString(2, pResponsavelAluno.getIdAluno());
			
			preparador.execute();
			preparador.close();
			
			System.out.println("Cadastrado com sucesso!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	 
	 public List<ResponsavelAluno> consultar(String pMatricula){
			String sql = "SELECT * FROM RESPONSAVEL_ALUNO ";
			String where = "WHERE ";
			String sql2 = "ID_ALUNO = ?";
			String sqlComplementar = "";
			List<ResponsavelAluno> listResponsavelAluno = new ArrayList<ResponsavelAluno>();
			ResponsavelAluno responsavelAluno = null;
			int contador=0;
			try{
				
				if(pMatricula != null && !pMatricula.equals("")){
					sqlComplementar = sql2;
				}
				
				if(!sqlComplementar.equals("")){
					sql = sql + where + sqlComplementar;
				}
				
				PreparedStatement preparador = getPreparedStatement(sql);
				
				if(pMatricula != null && !pMatricula.equals("")){
					contador++;
					preparador.setString(contador, pMatricula);
				}
				
				ResultSet resultado = preparador.executeQuery();
				
				if(resultado.next()){
					responsavelAluno = new ResponsavelAluno();
					
					responsavelAluno.setIdResponsavel(resultado.getString("id_responsavel"));
					responsavelAluno.setIdAluno(resultado.getString("id_aluno"));
					
					listResponsavelAluno.add(responsavelAluno);
				}
			}catch(SQLException e){
				e.printStackTrace();
			}
			
			return listResponsavelAluno;
			
	}
	 
	 public void excluir(String pId){
			
			String sql = "DELETE FROM RESPONSAVEL_ALUNO WHERE id_aluno=?";
			
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

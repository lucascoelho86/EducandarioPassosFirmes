package br.com.educandariopassosfirmes.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.educandariopassosfirmes.entidades.Aluno;

public class AlunoDAO extends Conexao{

	 public void cadastrar(Aluno pAluno){
		
		String sql = "INSERT INTO ALUNO (ID_PESSOA, ID_TURMA, ID_RESPONSAVEL, DT_MATRICULA, NEC_ESPECIAL, CD_CARTEIRA_ESTUDANTE) values (?,?,?,?,?,?)";
		
		try {
			PreparedStatement preparador = getPreparedStatement(sql);
			
			preparador.setString(1, pAluno.getId());
			preparador.setString(2, pAluno.getIdTurma());
			preparador.setString(3, pAluno.getIdResponsavel());
			preparador.setDate(4, pAluno.getDtMatricula());
			preparador.setString(5, pAluno.getNecessidadeEspecial());
			preparador.setString(6, pAluno.getCdCarteiraEstudante());
			
			preparador.execute();
			preparador.close();
			
			System.out.println("Cadastrado com sucesso!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	 
	public String consultarMaiorMatriculaNoAno(String pAno){
		
		String sql = "SELECT MAX(ID_PESSOA) FROM ALUNO WHERE SUBSTRING(ID_PESSOA, 1, 4) = ?";
		
		String maiorMatricula = "";
		
		try {
			PreparedStatement preparador = getPreparedStatement(sql);
			
			preparador.setString(1, pAno);
			
			ResultSet resultado = preparador.executeQuery();

			while(resultado.next()){
				
				if(resultado.getString("MAX(ID_PESSOA)") != null) {
					maiorMatricula = resultado.getString("MAX(ID_PESSOA)");	
				}
			}
			
			preparador.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return maiorMatricula;
	}
	
	public Aluno consultar(String pMatricula){
		
		String sql = "SELECT * FROM ALUNO ";
		String where = "WHERE ";
		String sql2 = "ID_PESSOA = ?";
		String sqlComplementar = "";
		Aluno aluno = null;
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
				aluno = new Aluno();
				
				aluno.setId(resultado.getString("id_pessoa"));
				
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return aluno;
	}
	
	public void excluir(String pId){
		
		String sql = "DELETE FROM Aluno WHERE id_pessoa=?";
		
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

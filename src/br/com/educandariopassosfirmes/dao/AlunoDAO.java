package br.com.educandariopassosfirmes.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
	
	public ArrayList<Aluno> consultar(String pMatricula, String pTurma, String pResponsavel){
		
		String sql = "SELECT * FROM ALUNO ";
		String where = "WHERE ";
		String sql2 = "ID_PESSOA = ?";
		String sql3 = "ID_TURMA = ?";
		String sql4 = "ID_RESPONSAVEL = ?";
		String conector = "";
		String sqlComplementar = "";
		Aluno aluno = null;
		ArrayList<Aluno>colecaoAluno = new ArrayList<Aluno>();
		int contador=0;
		try{
			if(pMatricula != null && !pMatricula.equals("")){
				sqlComplementar = sql2;
				conector = "\n AND ";
			}
			
			if(pTurma != null && !pTurma.equals("")){
				sqlComplementar = sqlComplementar + conector + sql3;
				conector = "\n AND ";
			}
			
			if(pResponsavel != null && !pResponsavel.equals("")){
				sqlComplementar = sqlComplementar + conector + sql4;
			}
			
			if(!sqlComplementar.equals("")){
				sql = sql + where + sqlComplementar;
			}
			
			PreparedStatement preparador = getPreparedStatement(sql);
			
			if(pMatricula != null && !pMatricula.equals("")){
				contador++;
				preparador.setString(contador, pMatricula);
			}
			
			if(pTurma != null && !pTurma.equals("")){
				contador++;
				preparador.setString(contador, pTurma);
			}
			
			if(pResponsavel != null && !pResponsavel.equals("")){
				contador++;
				preparador.setString(contador, pResponsavel);
			}
			
			ResultSet resultado = preparador.executeQuery();
			
			while(resultado.next()){
				aluno = new Aluno();
				
				aluno.setId(resultado.getString("ID_PESSOA"));
				aluno.setIdTurma(resultado.getString("ID_TURMA"));
				aluno.setIdResponsavel(resultado.getString("ID_RESPONSAVEL"));
				aluno.setDtMatricula(resultado.getDate("DT_MATRICULA"));
				aluno.setNecessidadeEspecial(resultado.getString("NEC_ESPECIAL"));
				aluno.setDetalheNecessidadeEspecial(resultado.getString("DETALHE_NEC_ESPECIAL"));
				aluno.setCdCarteiraEstudante(resultado.getString("CD_CARTEIRA_ESTUDANTE"));
				aluno.setCdCertidaoNascimento(resultado.getString("CD_CERTIDAO_NASCIMENTO"));
				
				colecaoAluno.add(aluno);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return colecaoAluno;
	}
	
	public void excluir(String pId){
		
		String sql = "DELETE FROM ALUNO WHERE ID_PESSOA=?";
		
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

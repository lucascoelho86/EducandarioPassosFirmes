package br.com.educandariopassosfirmes.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.educandariopassosfirmes.entidades.Disciplina;

public class DisciplinaDAO extends Conexao{

	 public ArrayList<Disciplina> consultar(Integer pIdDisciplina, String pSigla, String pDescricao){
		String sql = "SELECT * FROM DISCIPLINA ";
		String where = "WHERE ";
		String sql1 = "ID_DISCIPLINA = ? ";
		String sql2 = "SIGLA_DISCIPLINA = ? ";
		String sql3 = "DS_DISCIPLINA = ?";
		String sqlComplementar = "";
		Disciplina disciplina = null;
		int contador=0;
		ArrayList<Disciplina>colecaoRetorno = new ArrayList<Disciplina>();
		try{
			if(pIdDisciplina != null && pIdDisciplina != 0){
				sqlComplementar = sql1;
			}
			
			if(pSigla != null && !pSigla.equals("")){
				sqlComplementar = sql2;
			}

			if(pDescricao != null && !pDescricao.equals("")){
				sqlComplementar = sqlComplementar + sql3;
			}
				
			if(!sqlComplementar.equals("")){
				sql = sql + where + sqlComplementar;
			}
				
			PreparedStatement preparador = getPreparedStatement(sql);
				
			if(pIdDisciplina != null && pIdDisciplina != 0){
				contador++;
				preparador.setInt(contador, pIdDisciplina);
			}

			if(pSigla != null && !pSigla.equals("")){
				contador++;
				preparador.setString(contador, pSigla);
			}

			if(pDescricao != null && !pDescricao.equals("")){
				contador++;
				preparador.setString(contador, pDescricao);
			}
				
			ResultSet resultado = preparador.executeQuery();
			
			while(resultado.next()){
				disciplina = new Disciplina();
					
				disciplina.setIdDisciplina(resultado.getInt("ID_DISCIPLINA"));
				disciplina.setDsDisciplina(resultado.getString("DS_DISCIPLINA"));
				disciplina.setSiglaDisciplina(resultado.getString("SIGLA_DISCIPLINA"));
								
				colecaoRetorno.add(disciplina);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
			
		return colecaoRetorno;
			
	}
	 
	 public Integer consultarMaiorIdDisciplina(){
		String sql = "SELECT MAX(ID_DISCIPLINA) FROM DISCIPLINA";
		Integer idDisciplina = 0;
		try{
					
			PreparedStatement preparador = getPreparedStatement(sql);
					
			ResultSet resultado = preparador.executeQuery();
				
			while(resultado.next()){
				idDisciplina = resultado.getInt("MAX(ID_DISCIPLINA)");
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
				
		return idDisciplina;
				
	}
	 
	 public void incluir(Disciplina pDisciplina){
			
		String sql = "INSERT INTO DISCIPLINA (DS_DISCIPLINA, SIGLA_DISCIPLINA) values (?,?)";
			
		try {
			PreparedStatement preparador = getPreparedStatement(sql);
				
			preparador.setString(1, pDisciplina.getDsDisciplina());
			preparador.setString(2, pDisciplina.getSiglaDisciplina());
				
			preparador.execute();
			preparador.close();
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	 
	 public void alterar(Disciplina pDisciplina){
			
		String sql = "UPDATE DISCIPLINA SET DS_DISCIPLINA=?, SIGLA_DISCIPLINA=? WHERE ID_DISCIPLINA=?";
			
		try {
			PreparedStatement preparador = getPreparedStatement(sql);
				
			preparador.setString(1, pDisciplina.getDsDisciplina());
			preparador.setString(2, pDisciplina.getSiglaDisciplina());
			preparador.setInt(3, pDisciplina.getIdDisciplina());
				
			preparador.execute();
			preparador.close();
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	 
	 public void excluir(Integer pIdDisciplina){
			
		String sql = "DELETE FROM DISCIPLINA WHERE ID_DISCIPLINA=?";
			
		try {
			PreparedStatement preparador = getPreparedStatement(sql);
				
			preparador.setInt(1, pIdDisciplina);
				
			preparador.execute();
			preparador.close();
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}

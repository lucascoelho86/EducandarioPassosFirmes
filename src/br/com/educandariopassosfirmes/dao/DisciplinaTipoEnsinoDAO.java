package br.com.educandariopassosfirmes.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.educandariopassosfirmes.entidades.Disciplina;
import br.com.educandariopassosfirmes.entidades.DisciplinaTipoEnsino;

public class DisciplinaTipoEnsinoDAO extends Conexao {
	
	public ArrayList<DisciplinaTipoEnsino> consultarDisciplinaTipoEnsino(Integer pIdDisciplina){
		ArrayList<DisciplinaTipoEnsino> colecaoRetorno = new ArrayList<DisciplinaTipoEnsino>();
		String sql = "SELECT * FROM DISCIPLINA_TIPO_ENSINO ";
		String where = "WHERE ";
		String sql1 = "ID_DISCIPLINA = ? ";
		String sqlComplementar = "";
		DisciplinaTipoEnsino disciplinaTipoEnsino = null;
		int contador=0;
		try{
			if(pIdDisciplina != null && pIdDisciplina != 0){
				sqlComplementar = sql1;
			}
			
			if(!sqlComplementar.equals("")){
				sql = sql + where + sqlComplementar;
			}
			
			PreparedStatement preparador = getPreparedStatement(sql);
			
			if(pIdDisciplina != null && pIdDisciplina != 0){
				contador++;
				preparador.setInt(contador, pIdDisciplina);
			}
			
			ResultSet resultado = preparador.executeQuery();
			
			while(resultado.next()){
				disciplinaTipoEnsino = new DisciplinaTipoEnsino();
				
				disciplinaTipoEnsino.setIdDisciplina(resultado.getInt("ID_DISCIPLINA"));
				disciplinaTipoEnsino.setCdTipoEnsino(resultado.getInt("CD_TIPO_ENSINO"));
				
				colecaoRetorno.add(disciplinaTipoEnsino);
				
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return colecaoRetorno;
	}
	
	public void alterar(DisciplinaTipoEnsino pDisciplinaTipoEnsino){
		
		String sql = "UPDATE DISCIPLINA_TIPO_ENSINO SET CD_TIPO_ENSINO=? WHERE ID_DISCIPLINA=?";
			
		try {
			PreparedStatement preparador = getPreparedStatement(sql);
				
			preparador.setInt(1, pDisciplinaTipoEnsino.getCdTipoEnsino());
			preparador.setInt(2, pDisciplinaTipoEnsino.getIdDisciplina());
				
			preparador.execute();
			preparador.close();
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void excluir(Integer pIdDisciplina){
		
		String sql = "DELETE FROM DISCIPLINA_TIPO_ENSINO WHERE ID_DISCIPLINA=?";
		
		try {
			PreparedStatement preparador = getPreparedStatement(sql);
			
			preparador.setInt(1, pIdDisciplina);
			
			preparador.execute();
			preparador.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void incluir(Integer pDisciplina, Integer pCdTipoEntidade){
		
		String sql = "INSERT INTO DISCIPLINA_TIPO_ENSINO (ID_DISCIPLINA, CD_TIPO_ENSINO) values (?,?)";
			
		try {
			PreparedStatement preparador = getPreparedStatement(sql);
				
			preparador.setInt(1, pDisciplina);
			preparador.setInt(2, pCdTipoEntidade);
				
			preparador.execute();
			preparador.close();
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

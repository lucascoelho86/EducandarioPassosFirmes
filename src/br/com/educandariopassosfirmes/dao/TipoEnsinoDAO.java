package br.com.educandariopassosfirmes.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.educandariopassosfirmes.entidades.TipoEnsino;

public class TipoEnsinoDAO extends Conexao {
	
	public ArrayList<TipoEnsino> consultarTodosTipoEnsino(){
		ArrayList<TipoEnsino> colecaoRetorno = new ArrayList<TipoEnsino>();
		String sql = "SELECT * FROM TIPO_ENSINO";
		TipoEnsino tipoEnsino = null;
		try{
			
			PreparedStatement preparador = getPreparedStatement(sql);
			
			ResultSet resultado = preparador.executeQuery();
			
			while(resultado.next()){
				tipoEnsino = new TipoEnsino();
				
				tipoEnsino.setCdTipoEnsino(resultado.getInt("CD_TIPO_ENSINO"));
				tipoEnsino.setDsTipoEnsino(resultado.getString("DS_TIPO_ENSINO"));
				
				colecaoRetorno.add(tipoEnsino);
				
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return colecaoRetorno;
	}
	
}

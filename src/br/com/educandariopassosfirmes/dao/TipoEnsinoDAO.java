package br.com.educandariopassosfirmes.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.educandariopassosfirmes.entidades.TipoEnsino;

public class TipoEnsinoDAO extends Conexao {
	
	public TipoEnsino consultar(Integer pCdTipoEnsino){
		
		String sql = "SELECT * FROM TIPO_ENSINO ";
		String where = "WHERE ";
		String sql2 = "CD_TIPO_ENSINO = ?";
		String sqlComplementar = "";
		TipoEnsino tipoEnsino = null;
		int contador=0;
		try{
			if(pCdTipoEnsino != null){
				sqlComplementar = sql2;
			}
			
			if(!sqlComplementar.equals("")){
				sql = sql + where + sqlComplementar;
			}
			
			PreparedStatement preparador = getPreparedStatement(sql);
			
			if(pCdTipoEnsino != null && !pCdTipoEnsino.equals("")){
				contador++;
				preparador.setInt(contador, pCdTipoEnsino);
			}
			
			ResultSet resultado = preparador.executeQuery();
			
			if(resultado.next()){
				tipoEnsino = new TipoEnsino();
				
				tipoEnsino.setCdTipoEnsino(resultado.getInt("CD_TIPO_ENSINO"));
				tipoEnsino.setCdTipoEnsino(resultado.getInt("DS_TIPO_ENSINO"));
				
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return tipoEnsino;
	}
}

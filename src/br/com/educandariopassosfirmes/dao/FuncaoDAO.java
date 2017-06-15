package br.com.educandariopassosfirmes.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.educandariopassosfirmes.entidades.Funcao;

public class FuncaoDAO extends Conexao{

	 public Funcao consultar(Integer pFuncao){
			String sql = "SELECT * FROM FUNCAO ";
			String where = "WHERE ";
			String sql2 = "ID_FUNCAO = ?";
			String sqlComplementar = "";
			Funcao funcao = null;
			int contador=0;
			try{
				
				if(pFuncao != null){
					sqlComplementar = sql2;
				}
				
				if(!sqlComplementar.equals("")){
					sql = sql + where + sqlComplementar;
				}
				
				PreparedStatement preparador = getPreparedStatement(sql);
				
				if(pFuncao != null){
					contador++;
					preparador.setInt(contador, pFuncao);
				}
				
				ResultSet resultado = preparador.executeQuery();
				
				while(resultado.next()){
					funcao = new Funcao();
					
					funcao.setId_funcao(resultado.getInt("id_funcao"));
					funcao.setDs_funcao(resultado.getString("ds_funcao"));
					
				}
			}catch(SQLException e){
				e.printStackTrace();
			}
			
			return funcao;
			
		}
	
}

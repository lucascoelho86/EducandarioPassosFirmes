package br.com.educandariopassosfirmes.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.educandariopassosfirmes.entidades.PerfilFuncao;

public class PerfilFuncaoDAO extends Conexao{

	 public PerfilFuncao consultar(Integer pPerfil, Integer pFuncao){
			String sql = "SELECT * FROM PERFIL_FUNCAO ";
			String where = "WHERE ";
			String sql2 = "ID_PERFIL = ?";
			String sql3 = "ID_FUNCAO = ?";
			String conector = "";
			String sqlComplementar = "";
			PerfilFuncao perfilFuncao = null;
			int contador=0;
			try{
				
				if(pPerfil != null){
					sqlComplementar = sql2;
					conector = "\n AND ";
				}
				
				if(pFuncao != null){
					sqlComplementar = sqlComplementar + conector + sql3;
				}
				
				if(!sqlComplementar.equals("")){
					sql = sql + where + sqlComplementar;
				}
				
				PreparedStatement preparador = getPreparedStatement(sql);
				
				if(pPerfil != null){
					contador++;
					preparador.setInt(contador, pPerfil);
				}
				
				if(pFuncao != null){
					contador++;
					preparador.setInt(contador, pFuncao);
				}
				
				ResultSet resultado = preparador.executeQuery();
				
				while(resultado.next()){
					perfilFuncao = new PerfilFuncao();
					
					perfilFuncao.setId_perfil(resultado.getInt("id_perfil"));
					perfilFuncao.setId_funcao(resultado.getInt("id_funcao"));
					
				}
			}catch(SQLException e){
				e.printStackTrace();
			}
			
			return perfilFuncao;
			
		}
	
}

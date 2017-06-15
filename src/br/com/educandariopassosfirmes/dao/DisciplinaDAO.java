package br.com.educandariopassosfirmes.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.educandariopassosfirmes.entidades.Disciplina;

public class DisciplinaDAO extends Conexao{

	 public Disciplina consultar(Integer pDisciplina){
			String sql = "SELECT * FROM DISCIPLINA ";
			String where = "WHERE ";
			String sql2 = "ID_DISCIPLINA = ?";
			String sqlComplementar = "";
			Disciplina disciplina = null;
			int contador=0;
			try{
				if(pDisciplina != null && pDisciplina != 0){
					sqlComplementar = sql2;
				}
				
				if(!sqlComplementar.equals("")){
					sql = sql + where + sqlComplementar;
				}
				
				PreparedStatement preparador = getPreparedStatement(sql);
				
				if(pDisciplina != null && pDisciplina != 0){
					contador++;
					preparador.setInt(contador, pDisciplina);
				}
				
				ResultSet resultado = preparador.executeQuery();
				
				if(resultado.next()){
					disciplina = new Disciplina();
					
					disciplina.setIdDisciplina(resultado.getInt("id_disciplina"));
					disciplina.setDsDisciplina(resultado.getString("ds_disciplina"));
					
				}
			}catch(SQLException e){
				e.printStackTrace();
			}
			
			return disciplina;
			
		}
	
}

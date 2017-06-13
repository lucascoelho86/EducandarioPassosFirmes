package br.com.pacote1.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.pacote1.entidades.Turma;

public class TurmaDAO {

	private Connection con = Conexao.getConnection();
	
	 public Turma consultar(Integer pTurma){
			String sql = "SELECT * FROM TURMA ";
			String where = "WHERE ";
			String sql2 = "ID_TURMA = ?";
			String sqlComplementar = "";
			Turma turma = null;
			int contador=0;
			try{
				
				if(pTurma != null && pTurma != 0){
					sqlComplementar = sql2;
				}
				
				if(!sqlComplementar.equals("")){
					sql = sql + where + sqlComplementar;
				}
				
				PreparedStatement preparador = con.prepareStatement(sql);
				
				if(pTurma != null && pTurma != 0){
					contador++;
					preparador.setInt(contador, pTurma);
				}
				
				ResultSet resultado = preparador.executeQuery();
				
				if(resultado.next()){
					turma = new Turma();
					
					turma.setIdTurma(resultado.getInt("id_turma"));
					turma.setDsTurma(resultado.getString("ds_turma"));
					
				}
			}catch(SQLException e){
				e.printStackTrace();
			}
			
			return turma;
			
		}
	
}
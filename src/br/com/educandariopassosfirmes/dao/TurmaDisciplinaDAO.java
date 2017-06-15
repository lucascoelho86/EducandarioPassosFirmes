package br.com.educandariopassosfirmes.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.educandariopassosfirmes.entidades.TurmaDisciplina;

public class TurmaDisciplinaDAO extends Conexao{

	 public List<TurmaDisciplina> consultar(Integer pTurma, Integer pDisciplina){
			String sql = "SELECT * FROM TURMA_DISCIPLINA ";
			String where = "WHERE ";
			String sql2 = "ID_TURMA = ?";
			String sql3 = "ID_DISCIPLINA = ?";
			String conector = "";
			String sqlComplementar = "";
			TurmaDisciplina turmaDisciplina = null;
			List<TurmaDisciplina> listTurmaDisciplina = new ArrayList<TurmaDisciplina>();
			int contador=0;
			try{
				
				if(pTurma != null && pTurma != 0){
					sqlComplementar = sql2;
					conector = "\n AND ";
				}
				
				if(pDisciplina != null && pDisciplina != 0){
					sqlComplementar = sqlComplementar + conector + sql3;
				}
				
				if(!sqlComplementar.equals("")){
					sql = sql + where + sqlComplementar;
				}
				
				PreparedStatement preparador = getPreparedStatement(sql);
				
				if(pTurma != null && pTurma != 0){
					contador++;
					preparador.setInt(contador, pTurma);
				}
				
				if(pDisciplina != null && pDisciplina != 0){
					contador++;
					preparador.setInt(contador, pDisciplina);
				}
				
				ResultSet resultado = preparador.executeQuery();
				
				while(resultado.next()){
					turmaDisciplina = new TurmaDisciplina();
					
					turmaDisciplina.setIdTurma(resultado.getInt("id_turma"));
					turmaDisciplina.setIdDisciplina(resultado.getInt("id_disciplina"));
					
					listTurmaDisciplina.add(turmaDisciplina);
					
				}
			}catch(SQLException e){
				e.printStackTrace();
			}
			
			return listTurmaDisciplina;
			
		}
	 
	 public void excluir(String pId){
			
			String sql = "DELETE FROM TURMA_ALUNO WHERE id_aluno=?";
			
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

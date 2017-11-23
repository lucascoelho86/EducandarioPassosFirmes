package br.com.educandariopassosfirmes.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.educandariopassosfirmes.entidades.Turma;

public class TurmaDAO extends Conexao{

	 public ArrayList<Turma> consultar(String pIdTurma, String pDescricao, String pTurno){
		String sql = "SELECT * FROM TURMA ";
		String where = "WHERE ";
		String sql1 = "ID_TURMA = ?";
		String sql2 = "DS_TURMA LIKE ?";
		String sql3 = "TURNO = ?";
		String conector = "";
		String sqlComplementar = "";
		ArrayList<Turma>colecaoTurma = new ArrayList<Turma>();
		Turma turma = null;
		int contador=0;
		try{
				
			if(pIdTurma != null && !pIdTurma.equals("")){
				sqlComplementar = sql1;
				conector = "\n AND ";
			}
			
			if(pDescricao != null && !pDescricao.equals("")){
				sqlComplementar = sqlComplementar + conector + sql2;
				conector = "\n AND ";
			}

			if(pTurno != null && !pTurno.equals("")){
				sqlComplementar = sqlComplementar + conector + sql3;
			}
				
			if(!sqlComplementar.equals("")){
				sql = sql + where + sqlComplementar;
			}
				
			PreparedStatement preparador = getPreparedStatement(sql);
				
			if(pIdTurma != null && !pIdTurma.equals("")){
				contador++;
				preparador.setString(contador, pIdTurma);
			}
			
			if(pDescricao != null && !pDescricao.equals("")){
				contador++;
				preparador.setString(contador, "%" + pDescricao + "%");
			}

			if(pTurno != null && !pTurno.equals("")){
				contador++;
				preparador.setString(contador, pTurno);
			}
				
			ResultSet resultado = preparador.executeQuery();
				
			while(resultado.next()){
				turma = new Turma();
					
				turma.setIdTurma(resultado.getString("ID_TURMA"));
				turma.setDsTurma(resultado.getString("DS_TURMA"));
				turma.setTurno(resultado.getString("TURNO"));
				turma.setQtMaxAlunos(resultado.getInt("QUANTIDADE_MAX_ALUNOS"));
				turma.setSala(resultado.getString("SALA"));
					
				colecaoTurma.add(turma);
					
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
			
		return colecaoTurma;
			
	}
	 
	 public void incluir(Turma pTurma){
			
		String sql = "INSERT INTO TURMA (ID_TURMA, DS_TURMA, TURNO, QUANTIDADE_MAX_ALUNOS, SALA) values (?,?,?,?,?)";
				
		try {
			PreparedStatement preparador = getPreparedStatement(sql);
					
			preparador.setString(1, pTurma.getIdTurma());
			preparador.setString(2, pTurma.getDsTurma());
			preparador.setString(3, pTurma.getTurno());
			preparador.setInt(4, pTurma.getQtMaxAlunos());
			preparador.setString(5, pTurma.getSala());
					
			preparador.execute();
			preparador.close();
					
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	 
	 public void alterar(Turma pTurma){
			
		String sql = "UPDATE TURMA SET DS_TURMA=?, TURNO=?, QUANTIDADE_MAX_ALUNOS=? SALA=? WHERE ID_TURMA=?";
				
		try {
			PreparedStatement preparador = getPreparedStatement(sql);
					
			preparador.setString(1, pTurma.getDsTurma());
			preparador.setString(2, pTurma.getTurno());
			preparador.setInt(3, pTurma.getQtMaxAlunos());
			preparador.setString(4, pTurma.getSala());
			preparador.setString(5, pTurma.getIdTurma());
					
			preparador.execute();
			preparador.close();
					
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	 
	 public void excluir(String pIdTurma){
			
		String sql = "DELETE FROM TURMA WHERE ID_TURMA=?";
				
		try {
			PreparedStatement preparador = getPreparedStatement(sql);
					
			preparador.setString(1, pIdTurma);
					
			preparador.execute();
			preparador.close();
					
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}

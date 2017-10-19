package br.com.educandariopassosfirmes.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.educandariopassosfirmes.entidades.Disciplina;
import br.com.educandariopassosfirmes.entidades.Turma;

public class TurmaDAO extends Conexao{

	 public ArrayList<Turma> consultar(String pDescricao, String pTurno){
		String sql = "SELECT * FROM TURMA ";
		String where = "WHERE ";
		String sql1 = "DS_TURMA LIKE ?";
		String sql2 = "TURNO = ?";
		String sqlComplementar = "";
		ArrayList<Turma>colecaoTurma = new ArrayList<Turma>();
		Turma turma = null;
		int contador=0;
		try{
				
			if(pDescricao != null && !pDescricao.equals("")){
				sqlComplementar = sql1;
			}

			if(pTurno != null && !pTurno.equals("")){
				sqlComplementar = sql2;
			}
				
			if(!sqlComplementar.equals("")){
				sql = sql + where + sqlComplementar;
			}
				
			PreparedStatement preparador = getPreparedStatement(sql);
				
			if(pDescricao != null && !pDescricao.equals("")){
				contador++;
				preparador.setString(contador, pDescricao);
			}

			if(pTurno != null && !pTurno.equals("")){
				contador++;
				preparador.setString(contador, pTurno);
			}
				
			ResultSet resultado = preparador.executeQuery();
				
			while(resultado.next()){
				turma = new Turma();
					
				turma.setIdTurma(resultado.getInt("ID_TURMA"));
				turma.setDsTurma(resultado.getString("DS_TURMA"));
				turma.setTurno(resultado.getString("TURNO"));
				turma.setQtMaxAlunos(resultado.getInt("QUANTIDADE_MAX_ALUNOS"));
					
				colecaoTurma.add(turma);
					
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
			
		return colecaoTurma;
			
	}
	 
	 public void incluir(Disciplina pDisciplina){
			
		String sql = "INSERT INTO TURMA (DS_TURMA, TURNO, QUANTIDADE_MAX_ALUNOS) values (?,?,?)";
				
		try {
			PreparedStatement preparador = getPreparedStatement(sql);
					
			preparador.setString(1, pDisciplina.getDsDisciplina());
			preparador.setString(2, pDisciplina.getSiglaDisciplina());
			preparador.setInt(3, pDisciplina.getCargaHorariaMinima());
					
			preparador.execute();
			preparador.close();
					
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}

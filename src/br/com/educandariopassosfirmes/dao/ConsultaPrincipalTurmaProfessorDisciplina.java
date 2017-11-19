package br.com.educandariopassosfirmes.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class ConsultaPrincipalTurmaProfessorDisciplina extends Conexao{
	
	private static final String SQL = "SELECT \n\t" + "TURMA.ID_TURMA" + ",\n\t" + "TURMA.DS_TURMA" + ",\n\t"
				+ "PESSOA.ID_PESSOA" + ",\n\t"	+ "PESSOA.NOME" + ",\n\t" + "DISCIPLINA.ID_DISCIPLINA" + ",\n\t"
				+ "DISCIPLINA.DS_DISCIPLINA" + "\n" + "FROM \n\t" + "PESSOA \n" + "INNER JOIN \n\t"
				+ "TURMA_PROFESSOR_DISCIPLINA ON PESSOA.ID_PESSOA = TURMA_PROFESSOR_DISCIPLINA.ID_PESSOA \n"
				+ "INNER JOIN \n\t" + "TURMA ON TURMA.ID_TURMA = TURMA_PROFESSOR_DISCIPLINA.ID_TURMA  \n"
				+ "INNER JOIN \n\t" + "DISCIPLINA ON DISCIPLINA.ID_DISCIPLINA = TURMA_PROFESSOR_DISCIPLINA.ID_DISCIPLINA";
	
	public ArrayList<LinkedHashMap<String, String>> consultar(String pTurma, String pMatricula, String pDisciplina){
		
		String sql = "";
		String where = "WHERE \n\t";
		String conector = "";
		String sqlComplementar = "";
		ArrayList<LinkedHashMap<String, String>> colecaoDados = new ArrayList<LinkedHashMap<String, String>>();
		LinkedHashMap<String, String> dados = new LinkedHashMap<String, String>();
		int contador=0;
		try{
			if(pTurma != null && !pTurma.equals("") && !pTurma.equals("0")){
				sqlComplementar = "TURMA.ID_TURMA = ?";
				conector = "\n\t" + "AND ";
			}

			if(pMatricula != null && !pMatricula.equals("") && !pMatricula.equals("0")){
				sqlComplementar = sqlComplementar + conector + "PESSOA.ID_PESSOA = ?";
				conector = "\n\t" + "AND ";
			}
			
			if(pDisciplina != null && !pDisciplina.equals("") && !pDisciplina.equals("0")){
				sqlComplementar = sqlComplementar + conector + "TURMA_PROFESSOR_DISCIPLINA.ID_DISCIPLINA = ?";
			}
			
			if(!sqlComplementar.equals("")){
				sql = SQL + "\n" + where + sqlComplementar;
			}else {
				sql = SQL;
			}
			
			PreparedStatement preparador = getPreparedStatement(sql);
			
			if(pTurma != null && !pTurma.equals("") && !pTurma.equals("0")){
				contador++;
				preparador.setString(contador, pTurma);
			}
			
			if(pMatricula != null && !pMatricula.equals("") && !pMatricula.equals("0")){
				contador++;
				preparador.setString(contador, pMatricula);
			}
			
			if(pDisciplina != null && !pDisciplina.equals("") && !pDisciplina.equals("0")){
				contador++;
				preparador.setString(contador, pDisciplina);
			}
			
			ResultSet resultado = preparador.executeQuery();
			
			while(resultado.next()){
				dados = new LinkedHashMap<String, String>();
				
				dados.put("ID_TURMA", resultado.getString("ID_TURMA"));
				dados.put("DS_TURMA", resultado.getString("DS_TURMA"));
				dados.put("ID_PESSOA", resultado.getString("ID_PESSOA"));
				dados.put("NOME", resultado.getString("NOME"));
				dados.put("ID_DISCIPLINA", resultado.getString("ID_DISCIPLINA"));
				dados.put("DS_DISCIPLINA", resultado.getString("DS_DISCIPLINA"));
				
				colecaoDados.add(dados);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return colecaoDados;
	}
	
}

package br.com.educandariopassosfirmes.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class ConsultaPrincipalProfessor extends Conexao{
	
	private static final String SQL_COMPLETO = "SELECT \n\t" + "PESSOA.ID_PESSOA" + ",\n\t"
				+ "PESSOA.NOME" + ",\n\t" + "PROFESSOR_DISCIPLINA.ID_DISCIPLINA" + ",\n\t" + "DISCIPLINA.DS_DISCIPLINA"
				+ "\n" + "FROM \n\t" + "PESSOA \n" + "INNER JOIN \n\t" + "PROFESSOR ON PESSOA.ID_PESSOA = PROFESSOR.ID_PESSOA \n"
				+ "INNER JOIN \n\t" + "PROFESSOR_DISCIPLINA ON PROFESSOR_DISCIPLINA.ID_PESSOA = PROFESSOR.ID_PESSOA  \n"
				+ "INNER JOIN \n\t" + "DISCIPLINA ON PROFESSOR_DISCIPLINA.ID_DISCIPLINA = DISCIPLINA.ID_DISCIPLINA";

	private static final String SQL_COMPLETO_DISCIPLINA = "SELECT DISTINCT \n\t" + "PROFESSOR_DISCIPLINA.ID_DISCIPLINA"
			+ ",\n\t" + "DISCIPLINA.DS_DISCIPLINA"	+ "\n" + "FROM \n\t" + "PESSOA \n" + "INNER JOIN \n\t"
			+ "PROFESSOR_DISCIPLINA ON PROFESSOR_DISCIPLINA.ID_PESSOA = PESSOA.ID_PESSOA  \n"
			+ "INNER JOIN \n\t" + "DISCIPLINA ON PROFESSOR_DISCIPLINA.ID_DISCIPLINA = DISCIPLINA.ID_DISCIPLINA";
	
	private static final String SQL_SIMPLES = "SELECT \n\t" + "PESSOA.ID_PESSOA" + ",\n\t"
			+ "PESSOA.NOME" + "\n" + "FROM \n\t" + "PESSOA \n" + "INNER JOIN \n\t" + "PROFESSOR ON PESSOA.ID_PESSOA = PROFESSOR.ID_PESSOA";
	
	public ArrayList<LinkedHashMap<String, String>> consultar(String pMatricula, String pNome, String pDisciplina, boolean pConsultaCompleta, boolean pApenasDisciplinaProf){
		
		String sql = "";
		String where = "WHERE \n\t";
		String conector = "";
		String sqlComplementar = "";
		ArrayList<LinkedHashMap<String, String>> colecaoDados = new ArrayList<LinkedHashMap<String, String>>();
		LinkedHashMap<String, String> dados = new LinkedHashMap<String, String>();
		int contador=0;
		try{
			if(pNome != null && !pNome.equals("")){
				sqlComplementar = "PESSOA.NOME LIKE ?";
				conector = "\n\t" + "AND ";
			}

			if(pMatricula != null && !pMatricula.equals("")){
				sqlComplementar = sqlComplementar + conector + "PESSOA.ID_PESSOA = ?";
				conector = "\n\t" + "AND ";
			}
			
			if(pDisciplina != null && !pDisciplina.equals("") && !pDisciplina.equals("0")){
				sqlComplementar = sqlComplementar + conector + "PROFESSOR_DISCIPLINA.ID_DISCIPLINA = ?";
			}
			
			if(!sqlComplementar.equals("")){
				if(pConsultaCompleta || (pDisciplina != null && !pDisciplina.equals("") && !pDisciplina.equals("0"))) {
					sql = SQL_COMPLETO + "\n" + where + sqlComplementar;
				}else {
					sql = SQL_SIMPLES + "\n" + where + sqlComplementar;
				}
			}else {
				if(pApenasDisciplinaProf) {
					sql = SQL_COMPLETO_DISCIPLINA;
				}else if(pConsultaCompleta || (pDisciplina != null && !pDisciplina.equals("") && !pDisciplina.equals("0"))) {
					sql = SQL_COMPLETO;
				}else {
					sql = SQL_SIMPLES;
				}
			}
			
			PreparedStatement preparador = getPreparedStatement(sql);
			
			if(pNome != null && !pNome.equals("")){
				contador++;
				preparador.setString(contador, "%" + pNome + "%");
			}
			
			if(pMatricula != null && !pMatricula.equals("")){
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
				
				if(!pApenasDisciplinaProf) {
					dados.put("ID_PESSOA", resultado.getString("ID_PESSOA"));
					dados.put("NOME", resultado.getString("NOME"));
				}
				
				if (pApenasDisciplinaProf || pConsultaCompleta || (pDisciplina != null && !pDisciplina.equals("") && !pDisciplina.equals("0"))) {
					dados.put("ID_DISCIPLINA", resultado.getString("ID_DISCIPLINA"));
					dados.put("DS_DISCIPLINA", resultado.getString("DS_DISCIPLINA"));
				}
				
				colecaoDados.add(dados);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return colecaoDados;
	}
	
}

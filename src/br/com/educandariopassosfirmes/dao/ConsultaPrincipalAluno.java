package br.com.educandariopassosfirmes.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class ConsultaPrincipalAluno extends Conexao{
	
	private static final String SQL = "SELECT \n\t" + "PESSOA.ID_PESSOA" + ",\n\t"
				+ "pessoa.NOME" + ",\n\t" + "ALUNO.ID_TURMA" + ",\n\t" + "TURMA.DS_TURMA" + "\n" + "FROM \n\t"
				+ "PESSOA \n" + "INNER JOIN \n\t" + "ALUNO ON PESSOA.ID_PESSOA = ALUNO.ID_PESSOA \n"
				+ "INNER JOIN \n\t" + "TURMA ON TURMA.ID_TURMA = ALUNO.ID_TURMA";
			
	public ArrayList<LinkedHashMap<String, String>> consultar(String pMatricula, String pNome, String pTurma){
		
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
			
			if(pTurma != null && !pTurma.equals("") && !pTurma.equals("0")){
				sqlComplementar = sqlComplementar + conector + "ALUNO.ID_TURMA = ?";
			}
			
			if(!sqlComplementar.equals("")){
				sql = SQL + "\n" + where + sqlComplementar;
			}else {
				sql = SQL;
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
			
			if(pTurma != null && !pTurma.equals("") && !pTurma.equals("0")){
				contador++;
				preparador.setString(contador, pTurma);
			}
			
			ResultSet resultado = preparador.executeQuery();
			
			while(resultado.next()){
				dados = new LinkedHashMap<String, String>();
				
				dados.put("ID_PESSOA", resultado.getString("ID_PESSOA"));
				dados.put("NOME", resultado.getString("NOME"));
				dados.put("ID_TURMA", resultado.getString("ID_TURMA"));
				dados.put("DS_TURMA", resultado.getString("DS_TURMA"));
				
				colecaoDados.add(dados);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return colecaoDados;
	}
	
}

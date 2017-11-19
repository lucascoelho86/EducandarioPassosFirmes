package br.com.educandariopassosfirmes.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.educandariopassosfirmes.entidades.Disciplina;
import br.com.educandariopassosfirmes.entidades.TurmaProfessorDisciplina;

public class TurmaProfessorDisciplinaDAO extends Conexao {

	public ArrayList<TurmaProfessorDisciplina> consultar(String pTurma, String pProfessor, String pDisciplina) {
		String sql = "SELECT * FROM TURMA_PROFESSOR_DISCIPLINA ";
		String where = "WHERE ";
		String sql2 = "ID_TURMA = ?";
		String sql3 = "ID_PESSOA = ?";
		String sql4 = "ID_DISCIPLINA = ?";
		String conector = "";
		String sqlComplementar = "";
		TurmaProfessorDisciplina turmaProfessorDisciplina = null;
		ArrayList<TurmaProfessorDisciplina> listTurmaDisciplina = new ArrayList<TurmaProfessorDisciplina>();
		int contador = 0;
		try {

			if (pTurma != null && !pTurma.equals("")) {
				sqlComplementar = sql2;
				conector = "\n AND ";
			}

			if (pProfessor != null && !pProfessor.equals("")) {
				sqlComplementar = sqlComplementar + conector + sql3;
				conector = "\n AND ";
			}

			if (pDisciplina != null && !pDisciplina.equals("")) {
				sqlComplementar = sqlComplementar + conector + sql4;
			}

			if (!sqlComplementar.equals("")) {
				sql = sql + where + sqlComplementar;
			}

			PreparedStatement preparador = getPreparedStatement(sql);

			if (pTurma != null && !pTurma.equals("")) {
				contador++;
				preparador.setString(contador, pTurma);
			}

			if (pProfessor != null && !pProfessor.equals("")) {
				contador++;
				preparador.setString(contador, pProfessor);
			}

			if (pDisciplina != null && !pDisciplina.equals("")) {
				contador++;
				preparador.setString(contador, pDisciplina);
			}

			ResultSet resultado = preparador.executeQuery();

			while (resultado.next()) {
				turmaProfessorDisciplina = new TurmaProfessorDisciplina();

				turmaProfessorDisciplina.setIdTurma(resultado.getString("ID_TURMA"));
				turmaProfessorDisciplina.setIdProfessor(resultado.getString("ID_PESSOA"));
				turmaProfessorDisciplina.setIdDisciplina(resultado.getInt("ID_DISCIPLINA"));
				turmaProfessorDisciplina.setCargaHorariaMinima(resultado.getInt("CARGA_HORARIA_MIN"));
				turmaProfessorDisciplina.setAssuntoPrimeiraUnidade(resultado.getString("ASSUNTO_PRIMEIRA_UNIDADE"));
				turmaProfessorDisciplina.setAssuntoSegundaUnidade(resultado.getString("ASSUNTO_SEGUNDA_UNIDADE"));
				turmaProfessorDisciplina.setAssuntoTerceiraUnidade(resultado.getString("ASSUNTO_TERCEIRA_UNIDADE"));
				turmaProfessorDisciplina.setAssuntoQuartaUnidade(resultado.getString("ASSUNTO_QUARTA_UNIDADE"));

				listTurmaDisciplina.add(turmaProfessorDisciplina);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listTurmaDisciplina;

	}

	public void excluir(String pIdTurma, String pIdProfessor, String pIdDisciplina) {

		String sql = "DELETE FROM TURMA_PROFESSOR_DISCIPLINA WHERE ID_TURMA=? AND ID_PESSOA=? AND ID_DISCIPLINA=?";

		try {
			PreparedStatement preparador = getPreparedStatement(sql);

			preparador.setString(1, pIdTurma);
			preparador.setString(2, pIdProfessor);
			preparador.setString(3, pIdDisciplina);

			preparador.execute();
			preparador.close();

			System.out.println("Excluído com sucesso!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void incluir(TurmaProfessorDisciplina pTurmaProfessorDisciplina) {

		String sql = "INSERT INTO TURMA_PROFESSOR_DISCIPLINA (ID_TURMA, ID_PESSOA, ID_DISCIPLINA, CARGA_HORARIA_MIN, ASSUNTO_PRIMEIRA_UNIDADE, ASSUNTO_SEGUNDA_UNIDADE, ASSUNTO_TERCEIRA_UNIDADE, ASSUNTO_QUARTA_UNIDADE) values (?,?,?,?,?,?,?,?)";

		try {
			PreparedStatement preparador = getPreparedStatement(sql);

			preparador.setString(1, pTurmaProfessorDisciplina.getIdTurma());
			preparador.setString(2, pTurmaProfessorDisciplina.getIdProfessor());
			preparador.setInt(3, pTurmaProfessorDisciplina.getIdDisciplina());
			preparador.setInt(4, pTurmaProfessorDisciplina.getCargaHorariaMinima());
			preparador.setString(5, pTurmaProfessorDisciplina.getAssuntoPrimeiraUnidade());
			preparador.setString(6, pTurmaProfessorDisciplina.getAssuntoSegundaUnidade());
			preparador.setString(7, pTurmaProfessorDisciplina.getAssuntoTerceiraUnidade());
			preparador.setString(8, pTurmaProfessorDisciplina.getAssuntoQuartaUnidade());

			preparador.execute();
			preparador.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void alterar(TurmaProfessorDisciplina pTurmaProfessorDisciplina){
		
		String sql = "UPDATE TURMA_PROFESSOR_DISCIPLINA SET CARGA_HORARIA_MIN=?, ASSUNTO_PRIMEIRA_UNIDADE=?, ASSUNTO_SEGUNDA_UNIDADE=?, ASSUNTO_TERCEIRA_UNIDADE=?, ASSUNTO_QUARTA_UNIDADE=? WHERE ID_TURMA=? AND ID_PESSOA=? AND ID_DISCIPLINA=?";
			
		try {
			PreparedStatement preparador = getPreparedStatement(sql);
				
			preparador.setInt(1, pTurmaProfessorDisciplina.getCargaHorariaMinima());
			preparador.setString(2, pTurmaProfessorDisciplina.getAssuntoPrimeiraUnidade());
			preparador.setString(3, pTurmaProfessorDisciplina.getAssuntoSegundaUnidade());
			preparador.setString(4, pTurmaProfessorDisciplina.getAssuntoTerceiraUnidade());
			preparador.setString(5, pTurmaProfessorDisciplina.getAssuntoQuartaUnidade());
			preparador.setString(6, pTurmaProfessorDisciplina.getIdTurma());
			preparador.setString(7, pTurmaProfessorDisciplina.getIdProfessor());
			preparador.setInt(8, pTurmaProfessorDisciplina.getIdDisciplina());
				
			preparador.execute();
			preparador.close();
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}

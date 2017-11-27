package br.com.educandariopassosfirmes.rn;

import java.util.ArrayList;

import br.com.educandariopassosfirmes.dao.AlunoDAO;
import br.com.educandariopassosfirmes.dao.TurmaDAO;
import br.com.educandariopassosfirmes.dao.TurmaProfessorDisciplinaDAO;
import br.com.educandariopassosfirmes.entidades.Aluno;
import br.com.educandariopassosfirmes.entidades.Turma;
import br.com.educandariopassosfirmes.entidades.TurmaProfessorDisciplina;

public class RegraNegocioAlteracaoProfessor {
	
	public void executar(String pIdTurmaAnt, String pIdTurmaAtual, Turma pTurmaAtual){
		TurmaProfessorDisciplinaDAO	turmaProfessorDisciplinaDAO = new TurmaProfessorDisciplinaDAO();
		AlunoDAO alunoDAO = new AlunoDAO();
		TurmaDAO turmaDAO = new TurmaDAO();
		
		ArrayList<TurmaProfessorDisciplina> consultaProgramacao = turmaProfessorDisciplinaDAO.consultar(pIdTurmaAnt, "", "");
		ArrayList<Aluno> consultaAlunos = alunoDAO.consultar("", pIdTurmaAnt, "");
		
		turmaDAO.incluir(pTurmaAtual);
		
		if(!consultaProgramacao.isEmpty()) {
			for(int x = 0; x < consultaProgramacao.size(); x++) {
				TurmaProfessorDisciplina turmaProfessorDisciplina = consultaProgramacao.get(x);
				
				String idProfessor = turmaProfessorDisciplina.getIdProfessor();
				Integer idDisciplina = turmaProfessorDisciplina.getIdDisciplina();
				turmaProfessorDisciplinaDAO.excluir(pIdTurmaAnt, idProfessor, String.valueOf(idDisciplina));
				
				turmaProfessorDisciplina.setIdTurma(pIdTurmaAtual);
				turmaProfessorDisciplinaDAO.incluir(turmaProfessorDisciplina);
			}
		}

		if(!consultaAlunos.isEmpty()) {
			for(int x = 0; x < consultaAlunos.size(); x++) {
				Aluno aluno = consultaAlunos.get(x);
				
				String idAluno = aluno.getId();
				alunoDAO.excluir(idAluno);
				
				aluno.setIdTurma(pIdTurmaAtual);
				alunoDAO.cadastrar(aluno);
			}
		}
		
		turmaDAO.excluir(pIdTurmaAnt);
	}
	
}

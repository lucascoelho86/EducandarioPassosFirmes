package br.com.educandariopassosfirmes.rn;

import java.util.ArrayList;

import br.com.educandariopassosfirmes.dao.AlunoDAO;
import br.com.educandariopassosfirmes.dao.PessoaDAO;
import br.com.educandariopassosfirmes.dao.ResponsavelDAO;
import br.com.educandariopassosfirmes.entidades.Aluno;

public class RegraNegocioAlteracaoTodosResponsaveis {
	
	public void executar(String pCpfAnterior, String pCpf){
		AlunoDAO alunoDAO = new AlunoDAO();
		PessoaDAO pessoaDAO = new PessoaDAO();
		ResponsavelDAO responsavelDAO = new ResponsavelDAO();
		ArrayList<Aluno> consultaAluno = alunoDAO.consultar("", "", pCpfAnterior);
		
		for(int x = 0; x < consultaAluno.size(); x++) {
			Aluno aluno = consultaAluno.get(x);
			Aluno alunoAux = aluno;
			
			alunoDAO.excluir(aluno.getId());
			
			alunoAux.setIdResponsavel(pCpf);
			
			alunoDAO.cadastrar(alunoAux);
		}
		
		responsavelDAO.excluir(pCpfAnterior);
		pessoaDAO.excluir(pCpfAnterior);		
	}
	
}

package br.com.educandariopassosfirmes.servlet;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import br.com.educandariopassosfirmes.dao.AlunoDAO;
import br.com.educandariopassosfirmes.dao.PessoaDAO;
import br.com.educandariopassosfirmes.dao.PessoaPerfilDAO;
import br.com.educandariopassosfirmes.dao.ResponsavelAlunoDAO;
import br.com.educandariopassosfirmes.dao.ResponsavelDAO;
import br.com.educandariopassosfirmes.dao.TurmaAlunoDAO;
import br.com.educandariopassosfirmes.entidades.ResponsavelAluno;

public class ServletMontagemExcluirAluno extends Servlet {
	
	private String proximo;
	
	public String execute(HttpServletRequest request) {
		
		proximo = "sucesso.jsp";
		PessoaDAO pessoaDAO = new PessoaDAO();
		AlunoDAO alunoDAO = new AlunoDAO();
		PessoaPerfilDAO pessoaPerfilDAO = new PessoaPerfilDAO();
		ResponsavelAluno responsavelAluno = new ResponsavelAluno();
		List<ResponsavelAluno> listResponsavelAluno = new ArrayList<ResponsavelAluno>();
		ResponsavelAlunoDAO responsavelAlunoDAO = new ResponsavelAlunoDAO();
		ResponsavelDAO responsavelDAO = new ResponsavelDAO();
		TurmaAlunoDAO turmaAlunoDAO = new TurmaAlunoDAO();
		
		String matricula = request.getParameter("matricula");
		
		pessoaDAO.excluir(matricula);
		
		alunoDAO.excluir(matricula);
		
		pessoaPerfilDAO.excluir(matricula);
		
		listResponsavelAluno = responsavelAlunoDAO.consultar(matricula);
		
		if(listResponsavelAluno.size() == 1){
			
			responsavelAluno = listResponsavelAluno.get(0);
			
			pessoaDAO.excluir(responsavelAluno.getIdResponsavel());
			
			pessoaPerfilDAO.excluir(responsavelAluno.getIdResponsavel());
			
			responsavelAlunoDAO.excluir(matricula);
			
			responsavelDAO.excluir(responsavelAluno.getIdResponsavel());			
		}
		
		turmaAlunoDAO.excluir(matricula);
		
		return proximo;
	}
	
}

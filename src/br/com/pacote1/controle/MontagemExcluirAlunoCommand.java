package br.com.pacote1.controle;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import br.com.pacote1.entidades.ResponsavelAluno;
import br.com.pacote1.jdbc.AlunoDAO;
import br.com.pacote1.jdbc.PessoaDAO;
import br.com.pacote1.jdbc.PessoaPerfilDAO;
import br.com.pacote1.jdbc.ResponsavelAlunoDAO;
import br.com.pacote1.jdbc.ResponsavelDAO;
import br.com.pacote1.jdbc.TurmaAlunoDAO;

public class MontagemExcluirAlunoCommand implements Command {
	
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

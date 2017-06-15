package br.com.educandariopassosfirmes.servlet;

import javax.servlet.http.HttpServletRequest;

import br.com.educandariopassosfirmes.dao.FuncionarioDAO;
import br.com.educandariopassosfirmes.dao.PessoaDAO;
import br.com.educandariopassosfirmes.dao.PessoaPerfilDAO;
import br.com.educandariopassosfirmes.dao.ProfessorDAO;
import br.com.educandariopassosfirmes.dao.ProfessorDisciplinaDAO;
import br.com.educandariopassosfirmes.entidades.ProfessorDisciplina;

public class ServletMontagemExcluirFuncionario extends Servlet {
	
	private String proximo;
	
	public String execute(HttpServletRequest request) {
		
		proximo = "sucesso.jsp";
		PessoaDAO pessoaDAO = new PessoaDAO();
		PessoaPerfilDAO pessoaPerfilDAO = new PessoaPerfilDAO();
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		ProfessorDAO professorDAO = new ProfessorDAO();
		ProfessorDisciplina professorDisciplina = new ProfessorDisciplina();
		ProfessorDisciplinaDAO professorDisciplinaDAO = new ProfessorDisciplinaDAO();
		
		String idFuncao = request.getParameter("idFuncao");
		String cpf = request.getParameter("cpf");
		pessoaDAO.excluir(cpf);
		
		pessoaPerfilDAO.excluir(cpf);
		
		funcionarioDAO.excluir(cpf);
		
		if(idFuncao.equals("2") || idFuncao.equals("4")){
			professorDAO.excluir(cpf);
			
			professorDisciplina.setId_professor(cpf);
			professorDisciplinaDAO.excluir(professorDisciplina);
			
		}
		
		return proximo;
	}
	
}

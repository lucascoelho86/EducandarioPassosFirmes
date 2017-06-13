package br.com.pacote1.controle;

import javax.servlet.http.HttpServletRequest;

import br.com.pacote1.entidades.ProfessorDisciplina;
import br.com.pacote1.jdbc.FuncionarioDAO;
import br.com.pacote1.jdbc.PessoaDAO;
import br.com.pacote1.jdbc.PessoaPerfilDAO;
import br.com.pacote1.jdbc.ProfessorDAO;
import br.com.pacote1.jdbc.ProfessorDisciplinaDAO;

public class MontagemExcluirFuncionarioCommand implements Command {
	
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

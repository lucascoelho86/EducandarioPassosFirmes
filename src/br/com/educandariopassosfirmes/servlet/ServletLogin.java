package br.com.educandariopassosfirmes.servlet;

import javax.servlet.http.HttpServletRequest;

import br.com.educandariopassosfirmes.dao.FuncionarioDAO;
import br.com.educandariopassosfirmes.entidades.Funcionario;

public class ServletLogin extends Servlet {
	
	private String proximo;
	
	private FuncionarioDAO funcionarioDAO;
	
	public String execute(HttpServletRequest request) {
		// Sempre iniciar com o caso onde o erro pode acontecer
		proximo = "login.jsp";
		funcionarioDAO = new FuncionarioDAO();
		
		String usuario = request.getParameter("login");
		String senha = request.getParameter("senha");
				
		try {
			
			Funcionario funcionario = funcionarioDAO.buscarPorIdSenha(usuario, senha);
			
			if(funcionario != null){
				proximo = "home.jsp";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return proximo;
	}
	
}

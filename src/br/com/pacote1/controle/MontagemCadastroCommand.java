package br.com.pacote1.controle;

import javax.servlet.http.HttpServletRequest;

public class MontagemCadastroCommand implements Command {
	
	private String proximo;
	
	public String execute(HttpServletRequest request) {
		
		proximo = "home.jsp";
		
		String cadastrarAluno = request.getParameter("CadastrarAluno");
		String consultarAluno = request.getParameter("ConsultarAluno");
		String cadastrarFunc = request.getParameter("CadastrarFunc");
		String consultarFunc = request.getParameter("ConsultarAluno");
		
		if (cadastrarAluno != null) {
			proximo = "cadastroAluno.jsp";
		}else if(cadastrarFunc != null){
			proximo = "cadastroFuncionario.jsp";
		}else if(consultarAluno != null){
			proximo = "consultarAluno.jsp";
		}
				
		return proximo;
	}
	
}

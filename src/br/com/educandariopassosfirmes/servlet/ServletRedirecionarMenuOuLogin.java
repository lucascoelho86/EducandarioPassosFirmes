package br.com.educandariopassosfirmes.servlet;

import javax.servlet.http.HttpServletRequest;

public class ServletRedirecionarMenuOuLogin extends Servlet {
	
	private String proximo;
	
	public String execute(HttpServletRequest request) {
		
		String botaoMenu = request.getParameter("menu");
		String botaoSair = request.getParameter("sair");
		
		if(botaoMenu != null && !botaoMenu.equals("")){
			proximo = "home.jsp";
		}else if(botaoSair != null && !botaoSair.equals("")){
			proximo = "login.jsp";
		}
		
		return proximo;
	}
	
}

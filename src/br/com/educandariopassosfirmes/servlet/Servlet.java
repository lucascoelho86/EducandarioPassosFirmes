package br.com.educandariopassosfirmes.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

/**
 * Interface para o padrão Command
 * 
 * @author Devmedia
 * 
 */
public abstract class Servlet extends HttpServlet{

	/**
	 * Método de execução do comando
	 * 
	 * @param request
	 * @return
	 */
	public abstract String execute(HttpServletRequest request);
}

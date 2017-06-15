package br.com.educandariopassosfirmes.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

/**
 * Interface para o padr�o Command
 * 
 * @author Devmedia
 * 
 */
public abstract class Servlet extends HttpServlet{

	/**
	 * M�todo de execu��o do comando
	 * 
	 * @param request
	 * @return
	 */
	public abstract String execute(HttpServletRequest request);
}

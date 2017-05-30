package br.com.pacote1.controle;

import javax.servlet.http.HttpServletRequest;

/**
 * Interface para o padr�o Command
 * 
 * @author Devmedia
 * 
 */
public interface Command {

	/**
	 * M�todo de execu��o do comando
	 * 
	 * @param request
	 * @return
	 */
	public String execute(HttpServletRequest request);
}

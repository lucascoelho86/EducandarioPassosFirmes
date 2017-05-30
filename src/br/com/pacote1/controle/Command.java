package br.com.pacote1.controle;

import javax.servlet.http.HttpServletRequest;

/**
 * Interface para o padrão Command
 * 
 * @author Devmedia
 * 
 */
public interface Command {

	/**
	 * Método de execução do comando
	 * 
	 * @param request
	 * @return
	 */
	public String execute(HttpServletRequest request);
}

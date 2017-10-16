package br.com.educandariopassosfirmes.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletGenerico
 */
@WebServlet("/ServletGenerico")
public abstract class ServletGenerico extends HttpServlet {

	public static final String NM_EVENTO = "evento";
	public static final String NM_EVENTO_EXIBIR_INCLUSAO = "exibirInclusao";
	public static final String NM_EVENTO_PROCESSAR_INCLUSAO = "processarInclusao";
	public static final String NM_EVENTO_EXIBIR_ALTERACAO = "exibirAlteracao";
	public static final String NM_EVENTO_PROCESSAR_ALTERACAO = "processarAlteracao";
	public static final String NM_EVENTO_EXCLUIR = "excluir";
	public static final String NM_EVENTO_CONSULTAR_TODOS = "consultarTodos";
	public static final String NM_PARAMETRO_CHAVE_PRIMARIA = "chavePrimaria";

	public abstract void exibirInclusao(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException;
	
	public abstract void processarInclusao(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException;

	public abstract void exibirAlteracao(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException;
	
	public abstract void processarAlteracao(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException;

	public abstract void excluir(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException;

	public abstract void consultarTodos(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException;

	public void redirecionarPagina(HttpServletRequest request,
			HttpServletResponse response, String nomeJsp)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher(nomeJsp);
		dispatcher.forward(request, response);
	}

}

package br.com.educandariopassosfirmes.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/PessoaControle")
public class ServletPrincipal extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private Map<String, Servlet> comandos = new HashMap<String, Servlet>();

	@Override
	public void init() throws ServletException {
		comandos.put("login", new ServletLogin());
		comandos.put("cadastro", new ServletMontagemCadastro());
		comandos.put("cadastroAluno", new ServletMontagemCadastroAluno());
		comandos.put("cadastroFuncionario", new ServletMontagemCadastroFuncionario());
		comandos.put("consultarAluno", new ServletMontagemConsultarAluno());
		comandos.put("alterarAluno", new ServletMontagemAlteracaoAluno());
		comandos.put("consultarFuncionario", new ServletMontagemConsultarFuncionario());
		comandos.put("alterarFuncionario", new ServletMontagemAlteracaoFuncionario());
		comandos.put("excluirFuncionario", new ServletMontagemExcluirFuncionario());
		comandos.put("excluirAluno", new ServletMontagemExcluirAluno());
		comandos.put("cadastrarNotas", new ServletMontagemNotasAluno());
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String acao = request.getParameter("acao");
		String proxima = null;
		try {
			Servlet comando = verificarComand(acao);
			proxima = comando.execute(request);
		} catch (Exception e) {
			request.setAttribute("msgErro", e.getMessage());
		}
		request.getRequestDispatcher(proxima).forward(request, response);
	}
	
	private Servlet verificarComand(String acao) {
		Servlet comando = null;
		for (String key : comandos.keySet()) {
			if (key.equalsIgnoreCase(acao)) {
				comando = comandos.get(key);
			}
		}
		return comando;
	}
	
	/**
	 * Retorna um atributo do request como Object
	 *
	 * @param pNmAtributo Atributo a ser retornado como Object
	 * @param pRequest Request HTTP
	 *
	 * @return
	 *
	 * @throws ExcecaoParametroRequestInvalido Nao utilizada
	 */
	public static Object getAtributoOpcional(String pNmAtributo, HttpServletRequest pRequest){
		return pRequest.getAttribute(pNmAtributo);
	}

}

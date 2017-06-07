package br.com.pacote1.controle;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/PessoaControle")
public class PessoaControle extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private Map<String, Command> comandos = new HashMap<String, Command>();

	@Override
	public void init() throws ServletException {
		comandos.put("login", new LoginCommand());
		comandos.put("cadastro", new MontagemCadastroCommand());
		comandos.put("cadastroAluno", new MontagemCadastroAlunoCommand());
		comandos.put("cadastroFuncionario", new MontagemCadastroFuncionarioCommand());
		comandos.put("consultarAluno", new MontagemConsultarAlunoCommand());
		comandos.put("alterarAluno", new MontagemAlteracaoAlunoCommand());
		comandos.put("consultarFuncionario", new MontagemConsultarFuncionarioCommand());
		comandos.put("alterarFuncionario", new MontagemAlteracaoFuncionarioCommand());
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String acao = request.getParameter("acao");
		String proxima = null;
		try {
			Command comando = verificarComand(acao);
			proxima = comando.execute(request);
		} catch (Exception e) {
			request.setAttribute("msgErro", e.getMessage());
		}
		request.getRequestDispatcher(proxima).forward(request, response);
	}
	
	private Command verificarComand(String acao) {
		Command comando = null;
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

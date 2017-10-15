package br.com.educandariopassosfirmes.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.educandariopassosfirmes.dao.DisciplinaDAO;
import br.com.educandariopassosfirmes.entidades.Disciplina;


/**
 * Servlet implementation class ServletDisciplina
 */
@WebServlet("/ServletDisciplina")
public class ServletDisciplina extends ServletGenerico {

	public static final String NM_JSP_CONSULTAR = "/disciplina/consultarDisciplina.jsp";
	// private static final String NM_JSP_ALTERAR_MARCA_MOTO =
	// "/marcaMoto/alterarMarcaMoto.jsp";

	private static final String NM_JSP_INCLUIR_SERVICO = "/disciplina/cadastrarDisciplina.jsp";

	private static final String NM_JSP_ALTERAR_SERVICO = "/categoriaProduto/alterarCategoriaProduto.jsp";

	private static final String NM_SERVLET = ServletDisciplina.class.getSimpleName();

	public static final String NM_PARAMETRO_CodigoCategoriaProduto = "cdCategoriaProduto";
	public static final String NM_PARAMETRO_NomeCategoriaProduto = "nmCategoriaProduto";
	public static final String NM_PARAMETRO_Descricao = "descricao";
	public static final String NM_PARAMETRO_ArrayCategoriaProduto = "alCategoriaProduto";
	
	public static final String NM_PARAMETRO_CHAVE = "chave";
	
	//Par�metros inclus�o disciplina
	public static final String NM_PARAMETRO_SIGLA_DISCIPLINA = "siglaDisciplina";
	public static final String NM_PARAMETRO_DS_DISCIPLINA = "descricaoDisciplina";
	public static final String NM_PARAMETRO_TX_PRIMEIRA_UNIDADE = "txPrimeiraUnidade";
	public static final String NM_PARAMETRO_TX_SEGUNDA_UNIDADE = "txSegundaUnidade";
	public static final String NM_PARAMETRO_TX_TERCEIRA_UNIDADE = "txTerceiraUnidade";
	public static final String NM_PARAMETRO_TX_QUARTA_UNIDADE = "txQuartaUnidade";
	public static final String NM_PARAMETRO_CAMPO_CARGA_HORARIA = "cargaHoraria";
	public static final String NM_PARAMETRO_SELECT_TIPO_ENSINO = "selectTipoEnsino";
	public static final String NM_PARAMETRO_COLECAO_DISCIPLINA = "colecaoDisciplina";
	
	//Constantes utilizadas na inclus�o de disciplinas
	public static final String NM_TIPO_ENSINO_BASICO = "Educa��o Infantil";
	public static final String NM_TIPO_ENSINO_FUNDAMENTAL = "Ensino Fundamental I";

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// declara as variaveis
		String acao = "";

		// recupera o evento desejado
		acao = request.getParameter(this.NM_EVENTO);

		if (acao != null
				&& acao.equalsIgnoreCase(this.NM_EVENTO_EXIBIR_INCLUSAO)) {
			this.exibirInclusao(request, response);
		} else if (acao != null
				&& acao.equalsIgnoreCase(this.NM_EVENTO_PROCESSAR_INCLUSAO)) {
			this.processarInclusao(request, response);
		} else if (acao != null
				&& acao.equalsIgnoreCase(this.NM_EVENTO_CONSULTAR_TODOS)) {
			this.consultarTodos(request, response);
		} else if (acao != null
				&& acao.equalsIgnoreCase(this.NM_EVENTO_EXCLUIR)) {
			//this.excluir(request, response);
		} else if (acao != null
				&& acao.equalsIgnoreCase(this.NM_EVENTO_EXIBIR_ALTERACAO)) {
			//this.exibirAlteracao(request, response);
		} else if (acao != null
				&& acao.equalsIgnoreCase(this.NM_EVENTO_PROCESSAR_ALTERACAO)) {
			//this.processarAlteracao(request, response);
		} else {
			// caso nao tenha nenhum evento, redireciona para a pagina de
			// consulta
			this.redirecionarPagina(request, response, this.NM_JSP_CONSULTAR);
		}

	}

	@Override
	public void exibirInclusao(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// redireciona para a pagina de inclusao
		this.redirecionarPagina(request, response,
				this.NM_JSP_INCLUIR_SERVICO);
	};

	@Override
	public void processarInclusao(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// declara as variaveis
		String sigla = "";
		String descricao = "";
		String assuntoPrimeiraUnidade = "";
		String assuntoSegundaUnidade = "";
		String assuntoTerceiraUnidade = "";
		String assuntoQuartaUnidade = "";
		String cargaHoraria = "";
		String cdTipoEnsino = "";

		// recupera os parametros do request
		sigla = request.getParameter(NM_PARAMETRO_SIGLA_DISCIPLINA);
		descricao = request.getParameter(NM_PARAMETRO_DS_DISCIPLINA);
		assuntoPrimeiraUnidade = request.getParameter(NM_PARAMETRO_TX_PRIMEIRA_UNIDADE);
		assuntoSegundaUnidade = request.getParameter(NM_PARAMETRO_TX_SEGUNDA_UNIDADE);
		assuntoTerceiraUnidade = request.getParameter(NM_PARAMETRO_TX_TERCEIRA_UNIDADE);
		assuntoQuartaUnidade = request.getParameter(NM_PARAMETRO_TX_QUARTA_UNIDADE);
		cargaHoraria = request.getParameter(NM_PARAMETRO_CAMPO_CARGA_HORARIA);
		cdTipoEnsino = request.getParameter(NM_PARAMETRO_SELECT_TIPO_ENSINO);

		//monta a entidade disciplina para incluir
		Disciplina disciplina = new Disciplina();
		disciplina.setSiglaDisciplina(sigla);
		disciplina.setDsDisciplina(descricao);
		disciplina.setAssuntoPrimeiraUnidade(assuntoPrimeiraUnidade);
		disciplina.setAssuntoSegundaUnidade(assuntoSegundaUnidade);
		disciplina.setAssuntoTerceiraUnidade(assuntoTerceiraUnidade);
		disciplina.setAssuntoQuartaUnidade(assuntoQuartaUnidade);
		disciplina.setCargaHorariaMinima(Integer.valueOf(cargaHoraria));

		//inclui em DISCIPLINA
		DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
		disciplinaDAO.incluir(disciplina);
		
		this.redirecionarPagina(request, response, this.NM_JSP_CONSULTAR);

	};

	@Override
	public void consultarTodos(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// recupera os parametros do request
		String siglaDisciplina = (String) request.getParameter(NM_PARAMETRO_SIGLA_DISCIPLINA);
		String dsDisciplina = (String) request.getParameter(NM_PARAMETRO_DS_DISCIPLINA);

		DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
		ArrayList<Disciplina>colecaoDisciplina = disciplinaDAO.consultar(siglaDisciplina, dsDisciplina);
				
		request.setAttribute(NM_PARAMETRO_COLECAO_DISCIPLINA, colecaoDisciplina);

		this.redirecionarPagina(request, response, this.NM_JSP_CONSULTAR);
	}
/*
	@Override
	public void excluir(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// declara as variaveis
		String codigo = "";

		// recupera os parametros do request
		codigo = request
				.getParameter(this.NM_PARAMETRO_CodigoCategoriaProduto);

		//so exclui se o codigo do servico vier preenchido
		if (codigo != null && !codigo.equals("")) {
			DAOCategoriaProduto.excluir(Integer.valueOf(codigo));
		}

		this.consultarTodos(request, response);
	}*/
/*
	@Override
	public void exibirAlteracao(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// declara as variaveis
		EntidadeCategoriaProduto eCategoriaProduto;
		String codigoCategoria = "";
		
		// recupera os parametros do request
		codigoCategoria = request.getParameter(this.NM_PARAMETRO_CodigoCategoriaProduto);
		
		//consulta por codigo para recuperar os valores e setar no request				
		ArrayList<EntidadeCategoriaProduto> alCategoriaProduto = DAOCategoriaProduto.consultarPorCodigo(Integer.valueOf(codigoCategoria));
		eCategoriaProduto = alCategoriaProduto.get(0);
	
		//seta os atributos no request para recuperar na JSP
		request.setAttribute(this.NM_PARAMETRO_CodigoCategoriaProduto,
				String.valueOf(eCategoriaProduto.getCodigo()));
		request.setAttribute(this.NM_PARAMETRO_NomeCategoriaProduto,
				eCategoriaProduto.getNome());
		request.setAttribute(this.NM_PARAMETRO_Descricao,
				eCategoriaProduto.getDescricao());

		this.redirecionarPagina(request, response,
				this.NM_JSP_ALTERAR_SERVICO);
	}*/
/*
	@Override
	public void processarAlteracao(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// declara as variaveis
		String codigo = "";
		String nome = "";
		String descricao = "";

		// recupera os parametros do request
		codigo = request.getParameter(this.NM_PARAMETRO_CodigoCategoriaProduto);
		nome = request.getParameter(this.NM_PARAMETRO_NomeCategoriaProduto);
		descricao = request.getParameter(this.NM_PARAMETRO_Descricao);

		//monta a entidade servico para alterar
		EntidadeCategoriaProduto eCategoriaProduto = new EntidadeCategoriaProduto();
		eCategoriaProduto.setCodigo(Integer.valueOf(codigo));
		eCategoriaProduto.setNome(nome);
		eCategoriaProduto.setDescricao(descricao);

		//altera em categoria_produto
		DAOCategoriaProduto.alterar(eCategoriaProduto);
		
		this.redirecionarPagina(request, response, this.NM_JSP_CONSULTAR);
	}*/

}

package br.com.educandariopassosfirmes.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.educandariopassosfirmes.dao.DisciplinaDAO;
import br.com.educandariopassosfirmes.dao.ProfessorDisciplinaDAO;
import br.com.educandariopassosfirmes.dao.TurmaProfessorDisciplinaDAO;
import br.com.educandariopassosfirmes.entidades.Disciplina;
import br.com.educandariopassosfirmes.entidades.TurmaProfessorDisciplina;


/**
 * Servlet implementation class ServletDisciplina
 */
@WebServlet("/ServletDisciplina")
public class ServletDisciplina extends ServletGenerico {

	public static final String NM_JSP_CONSULTAR = "/disciplina/consultarDisciplina.jsp";

	private static final String NM_JSP_INCLUIR_SERVICO = "/disciplina/cadastrarDisciplina.jsp";

	private static final String NM_JSP_ALTERAR_DISCIPLINA = "/disciplina/alterarDisciplina.jsp";
	
	private static final String NM_JSP_EXCECAO_PROGRAMACAO = "/disciplina/excecaoProgramacao.jsp";

	public static final String NM_PARAMETRO_CodigoCategoriaProduto = "cdCategoriaProduto";
	public static final String NM_PARAMETRO_NomeCategoriaProduto = "nmCategoriaProduto";
	public static final String NM_PARAMETRO_Descricao = "descricao";
	public static final String NM_PARAMETRO_ArrayCategoriaProduto = "alCategoriaProduto";
	
	public static final String NM_PARAMETRO_CHAVE = "chave";
	
	//Par�metros inclus�o disciplina
	public static final String NM_PARAMETRO_ID_DISCIPLINA = "idDisciplina";
	public static final String NM_PARAMETRO_SIGLA_DISCIPLINA = "siglaDisciplina";
	public static final String NM_PARAMETRO_DS_DISCIPLINA = "descricaoDisciplina";
	public static final String NM_PARAMETRO_TX_PRIMEIRA_UNIDADE = "txPrimeiraUnidade";
	public static final String NM_PARAMETRO_TX_SEGUNDA_UNIDADE = "txSegundaUnidade";
	public static final String NM_PARAMETRO_TX_TERCEIRA_UNIDADE = "txTerceiraUnidade";
	public static final String NM_PARAMETRO_TX_QUARTA_UNIDADE = "txQuartaUnidade";
	public static final String NM_PARAMETRO_CAMPO_CARGA_HORARIA = "cargaHoraria";
	public static final String NM_PARAMETRO_SELECT_TIPO_ENSINO = "selectTipoEnsino";
	public static final String NM_PARAMETRO_COLECAO_DISCIPLINA = "colecaoDisciplina";
	
	public static final String NM_EVENTO_EXCLUIR_PROGRAMACAO_E_DISCIPLINA = "excluirProgramacaoDisciplina";
	public static final String NM_PARAMETRO_TAMANHO = "tamanho";
	
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
			this.excluir(request, response);
		} else if (acao != null
				&& acao.equalsIgnoreCase(this.NM_EVENTO_EXIBIR_ALTERACAO)) {
			this.exibirAlteracao(request, response);
		} else if (acao != null
				&& acao.equalsIgnoreCase(this.NM_EVENTO_PROCESSAR_ALTERACAO)) {
			this.processarAlteracao(request, response);
		} else if (acao != null
				&& acao.equalsIgnoreCase(NM_EVENTO_EXCLUIR_PROGRAMACAO_E_DISCIPLINA)) {
			this.excluirProgramacaoDisciplina(request, response);
		} else {
			// caso nao tenha nenhum evento, redireciona para a pagina de consulta
			this.redirecionarPagina(request, response, NM_JSP_CONSULTAR);
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

		// recupera os parametros do request
		sigla = request.getParameter(NM_PARAMETRO_SIGLA_DISCIPLINA);
		descricao = request.getParameter(NM_PARAMETRO_DS_DISCIPLINA);
		assuntoPrimeiraUnidade = request.getParameter(NM_PARAMETRO_TX_PRIMEIRA_UNIDADE);
		assuntoSegundaUnidade = request.getParameter(NM_PARAMETRO_TX_SEGUNDA_UNIDADE);
		assuntoTerceiraUnidade = request.getParameter(NM_PARAMETRO_TX_TERCEIRA_UNIDADE);
		assuntoQuartaUnidade = request.getParameter(NM_PARAMETRO_TX_QUARTA_UNIDADE);
		cargaHoraria = request.getParameter(NM_PARAMETRO_CAMPO_CARGA_HORARIA);

		//monta a entidade disciplina para incluir
		Disciplina disciplina = new Disciplina();
		disciplina.setSiglaDisciplina(sigla);
		disciplina.setDsDisciplina(descricao);
		
		//inclui em DISCIPLINA
		DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
		disciplinaDAO.incluir(disciplina);
		
		this.redirecionarPagina(request, response, NM_JSP_CONSULTAR);

	};

	@Override
	public void consultarTodos(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// recupera os parametros do request
		String siglaDisciplina = (String) request.getParameter(NM_PARAMETRO_SIGLA_DISCIPLINA);
		String dsDisciplina = (String) request.getParameter(NM_PARAMETRO_DS_DISCIPLINA);

		DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
		ArrayList<Disciplina>colecaoDisciplina = disciplinaDAO.consultar(0, siglaDisciplina, dsDisciplina);
				
		request.setAttribute(NM_PARAMETRO_COLECAO_DISCIPLINA, colecaoDisciplina);

		this.redirecionarPagina(request, response, NM_JSP_CONSULTAR);
	}

	@Override
	public void excluir(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// declara as variaveis
		String chave = "";
				
		// recupera os parametros do request
		chave = request.getParameter(NM_PARAMETRO_CHAVE);
				
		String[] chaveDisciplina = chave.split(";");
		String idDisciplina = chaveDisciplina[0];
		
		TurmaProfessorDisciplinaDAO turmaProfessorDisciplinaDAO = new TurmaProfessorDisciplinaDAO();
		ArrayList<TurmaProfessorDisciplina> consultaTurmaProfessorDisciplina = turmaProfessorDisciplinaDAO.consultar("", "", idDisciplina);
		
		if(!consultaTurmaProfessorDisciplina.isEmpty()) {
			DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
			ArrayList<Disciplina> consultaDisciplina = disciplinaDAO.consultar(Integer.valueOf(idDisciplina), "", "");
			Disciplina disciplina = consultaDisciplina.get(0);
			
			String nome = disciplina.getDsDisciplina();
			
			if(nome == null) {
				nome = "";
			}
			
			request.setAttribute(NM_PARAMETRO_ID_DISCIPLINA, idDisciplina);
			request.setAttribute(NM_PARAMETRO_DS_DISCIPLINA, nome.equals("") ? nome : nome.toUpperCase());
			request.setAttribute(NM_PARAMETRO_TAMANHO, String.valueOf(consultaTurmaProfessorDisciplina.size()));
			this.redirecionarPagina(request, response, NM_JSP_EXCECAO_PROGRAMACAO);
		}else {
			ProfessorDisciplinaDAO professorDisciplinaDAO = new ProfessorDisciplinaDAO();
			professorDisciplinaDAO.excluir("", idDisciplina);

			DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
			disciplinaDAO.excluir(Integer.valueOf(idDisciplina));

			this.redirecionarPagina(request, response, NM_JSP_CONSULTAR);
		}
	}

	@Override
	public void exibirAlteracao(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// declara as variaveis
		String chave = "";
		
		// recupera os parametros do request
		chave = request.getParameter(NM_PARAMETRO_CHAVE);
		
		String[] chaveDisciplina = chave.split(";");
		String idDisciplina = chaveDisciplina[0];
		String siglaDisciplina = "";
		String dsDisciplina = "";
		DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
				
		//consulta pelo id da disciplina recuperar os valores e setar no request				
		ArrayList<Disciplina> colecaoDisciplina = disciplinaDAO.consultar(Integer.valueOf(idDisciplina),"", "");
		
		Iterator<Disciplina>itDisciplina = colecaoDisciplina.iterator();
		while(itDisciplina.hasNext()) {
			Disciplina disciplina = itDisciplina.next();
			
			siglaDisciplina = disciplina.getSiglaDisciplina();
			dsDisciplina = disciplina.getDsDisciplina();
		}
		
		//seta os atributos no request para recuperar na JSP
		request.setAttribute(NM_PARAMETRO_ID_DISCIPLINA, idDisciplina);
		request.setAttribute(NM_PARAMETRO_SIGLA_DISCIPLINA, siglaDisciplina);
		request.setAttribute(NM_PARAMETRO_DS_DISCIPLINA, dsDisciplina);

		this.redirecionarPagina(request, response, NM_JSP_ALTERAR_DISCIPLINA);
	}

	@Override
	public void processarAlteracao(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// declara as variaveis
		String idDisciplina = "";
		String sigla = "";
		String descricao = "";

		// recupera os parametros do request
		idDisciplina = request.getParameter(NM_PARAMETRO_ID_DISCIPLINA);
		sigla = request.getParameter(NM_PARAMETRO_SIGLA_DISCIPLINA);
		descricao = request.getParameter(NM_PARAMETRO_DS_DISCIPLINA);

		//monta a entidade disciplina para alterar
		Disciplina disciplina = new Disciplina();
		disciplina.setIdDisciplina(Integer.valueOf(idDisciplina));
		disciplina.setSiglaDisciplina(sigla);
		disciplina.setDsDisciplina(descricao);

		//alterar em DISCIPLINA
		DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
		disciplinaDAO.alterar(disciplina);
		
		this.redirecionarPagina(request, response, NM_JSP_CONSULTAR);
	}
	
	public void excluirProgramacaoDisciplina(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// declara as variaveis
		String chave = "";
						
		// recupera os parametros do request
		chave = request.getParameter(NM_PARAMETRO_ID_DISCIPLINA);
						
		String idDisciplina = chave;
		
		TurmaProfessorDisciplinaDAO turmaProfessorDisciplinaDAO = new TurmaProfessorDisciplinaDAO();
		ArrayList<TurmaProfessorDisciplina> consultaturmaProfessorDisciplina = turmaProfessorDisciplinaDAO.consultar("", "", idDisciplina);
		
		for(int x = 0; x < consultaturmaProfessorDisciplina.size(); x++) {
			TurmaProfessorDisciplina turmaProfessorDisciplina = consultaturmaProfessorDisciplina.get(x);
			
			String turma = turmaProfessorDisciplina.getIdTurma();
			String professor = turmaProfessorDisciplina.getIdProfessor();
			Integer disciplina = turmaProfessorDisciplina.getIdDisciplina();
			
			turmaProfessorDisciplinaDAO.excluir(turma, professor, String.valueOf(disciplina));
		}
		
		ProfessorDisciplinaDAO professorDisciplinaDAO = new ProfessorDisciplinaDAO();
		professorDisciplinaDAO.excluir("", idDisciplina);

		DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
		disciplinaDAO.excluir(Integer.valueOf(idDisciplina));

		this.redirecionarPagina(request, response, NM_JSP_CONSULTAR);
		
	}

}

package br.com.educandariopassosfirmes.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.educandariopassosfirmes.dao.TurmaDAO;
import br.com.educandariopassosfirmes.entidades.Turma;


/**
 * Servlet implementation class ServletProgramacao
 */
@WebServlet("/ServletProgramacao")
public class ServletProgramacao extends ServletGenerico {

	public static final String NM_JSP_CONSULTAR = "/programacaoTurma/consultarProgramacao.jsp";

	private static final String NM_JSP_INCLUIR_SERVICO = "/programacaoTurma/cadastrarProgramacao.jsp";

	private static final String NM_JSP_ALTERAR_TURMA = "/programacaoTurma/alterarProgramacao.jsp";
	
	public static final String NM_EVENTO_PROCESSAR_CONSULTA_SELECT_PROFESSOR = "processarConsultaSelectProfessor";
	public static final String NM_EVENTO_PROCESSAR_CONSULTA_SELECT_DISCIPLINA = "processarConsultaSelectDisciplina";
	
	public static final String NM_PARAMETRO_CHAVE = "chave";
	
	//Par�metros inclus�o disciplina
	public static final String NM_PARAMETRO_SELECT_TURMA = "selectTurma";
	public static final String NM_PARAMETRO_SELECT_PROFESSOR = "selectProfessor";
	public static final String NM_PARAMETRO_SELECT_DISCIPLINA = "selectDisciplina";
	public static final String NM_PARAMETRO_DS_TURMA = "descricaoTurma";
	public static final String NM_PARAMETRO_TURNO = "turno";
	public static final String NM_PARAMETRO_QT_MAX_ALUNOS = "qtMaxAlunos";
	public static final String NM_PARAMETRO_SELECT_TURNO = "selectTurno";
	public static final String NM_PARAMETRO_COLECAO_TURMA = "colecaoTurma";
	public static final String NM_PARAMETRO_COLECAO_PROFESSOR_DISCIPLINA = "colecaoProfessorDisciplina";
	public static final String NM_PARAMETRO_TX_PRIMEIRA_UNIDADE = "txPrimeiraUnidade";
	public static final String NM_PARAMETRO_TX_SEGUNDA_UNIDADE = "txSegundaUnidade";
	public static final String NM_PARAMETRO_TX_TERCEIRA_UNIDADE = "txTerceiraUnidade";
	public static final String NM_PARAMETRO_TX_QUARTA_UNIDADE = "txQuartaUnidade";
	public static final String NM_PARAMETRO_CAMPO_CARGA_HORARIA = "cargaHoraria";
	
	//Constantes utilizadas na inclus�o de turmas
	public static final String NM_TURNO_MANHA = "Matutino";
	public static final String NM_TURNO_TARDE = "Vespertino";

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
				&& acao.equalsIgnoreCase(NM_EVENTO_PROCESSAR_CONSULTA_SELECT_PROFESSOR)) {
			this.processarConsultaSelectProfessor(request, response);
		} else if (acao != null
				&& acao.equalsIgnoreCase(NM_EVENTO_PROCESSAR_CONSULTA_SELECT_DISCIPLINA)) {
			this.processarConsultaSelectDisciplina(request, response);
		} else {
			// caso nao tenha nenhum evento, redireciona para a pagina de consulta
			this.redirecionarPagina(request, response, NM_JSP_CONSULTAR);
		}

	}

	@Override
	public void exibirInclusao(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String descricao = "";
		String qtMaxAlunos = "";
		
		descricao = request.getParameter(NM_PARAMETRO_DS_TURMA);
		qtMaxAlunos = request.getParameter(NM_PARAMETRO_QT_MAX_ALUNOS);
		
		request.setAttribute(NM_PARAMETRO_DS_TURMA, descricao);
		request.setAttribute(NM_PARAMETRO_QT_MAX_ALUNOS, qtMaxAlunos);
		
		// redireciona para a pagina de inclusao
		this.redirecionarPagina(request, response,	NM_JSP_INCLUIR_SERVICO);
	};

	@Override
	public void processarInclusao(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// declara as variaveis
		String descricao = "";
		String siglaTurma = "";
		String turno = "";
		String dsTurno = "";
		String qtMaxAlunos = "";

		// recupera os parametros do request
		descricao = request.getParameter(NM_PARAMETRO_DS_TURMA);
		turno = request.getParameter(NM_PARAMETRO_SELECT_TURNO);
		qtMaxAlunos = request.getParameter(NM_PARAMETRO_QT_MAX_ALUNOS);

		if(turno.equals("1")) {
			dsTurno = NM_TURNO_MANHA;
		}else if(turno.equals("2")) {
			dsTurno = NM_TURNO_TARDE;
		}
		
		//monta a entidade disciplina para incluir
		Turma turma = new Turma();
		turma.setIdTurma(siglaTurma);
		turma.setDsTurma(descricao);
		turma.setTurno(dsTurno);
		turma.setQtMaxAlunos(Integer.valueOf(qtMaxAlunos));

		//inclui em TURMA
		TurmaDAO turmaDAO = new TurmaDAO();
		turmaDAO.incluir(turma);
		
		this.redirecionarPagina(request, response, NM_JSP_CONSULTAR);

	};

	@Override
	public void consultarTodos(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String dsTurno = "";
		// recupera os parametros do request
		String dsDisciplina = (String) request.getParameter(NM_PARAMETRO_DS_TURMA);
		String turno = (String) request.getParameter(NM_PARAMETRO_SELECT_TURNO);

		if(turno.equals("1")) {
			dsTurno = NM_TURNO_MANHA;
		}else if(turno.equals("2")) {
			dsTurno = NM_TURNO_TARDE;
		}
		
		TurmaDAO turmaDAO = new TurmaDAO();
		//consultar todas as turmas
		ArrayList<Turma> colecaoTurma = turmaDAO.consultar("", dsDisciplina, dsTurno);
		
		request.setAttribute(NM_PARAMETRO_COLECAO_TURMA, colecaoTurma);

		this.redirecionarPagina(request, response, NM_JSP_CONSULTAR);
	}

	@Override
	public void excluir(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// declara as variaveis
		String chave = "";
				
		// recupera os parametros do request
		chave = request.getParameter(NM_PARAMETRO_CHAVE);
				
		String[] chaveTurma = chave.split(";");
		String idTurma = chaveTurma[0];

		TurmaDAO turmaDAO = new TurmaDAO();		
		turmaDAO.excluir(idTurma);
		
		this.redirecionarPagina(request, response, NM_JSP_CONSULTAR);
	}

	@Override
	public void exibirAlteracao(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// declara as variaveis
		String chave = "";
		
		// recupera os parametros do request
		chave = request.getParameter(NM_PARAMETRO_CHAVE);
		
		String[] chaveTurma = chave.split(";");
		String idTurma = chaveTurma[0];
		String dsTurma = chaveTurma[1];
		String turno = chaveTurma[2];
		String qtMaxAlunos = chaveTurma[3];
		
		//seta os atributos no request para recuperar na JSP
		request.setAttribute(NM_PARAMETRO_SIGLA_TURMA, idTurma);
		request.setAttribute(NM_PARAMETRO_DS_TURMA, dsTurma);
		
		if(turno.equals(NM_TURNO_MANHA)) {
			request.setAttribute(NM_PARAMETRO_TURNO, "1");			
		}else{
			request.setAttribute(NM_PARAMETRO_TURNO, "2");
		}
		request.setAttribute(NM_PARAMETRO_QT_MAX_ALUNOS, qtMaxAlunos);

		this.redirecionarPagina(request, response, NM_JSP_ALTERAR_TURMA);
	}

	@Override
	public void processarAlteracao(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// declara as variaveis
		String idTurma = "";
		String descricao = "";
		String turno = "";
		String dsTurno = "";
		String qtMaxAlunos = "";

		// recupera os parametros do request
		idTurma = request.getParameter(NM_PARAMETRO_SIGLA_TURMA);
		descricao = request.getParameter(NM_PARAMETRO_DS_TURMA);
		turno = request.getParameter(NM_PARAMETRO_SELECT_TURNO);
		qtMaxAlunos = request.getParameter(NM_PARAMETRO_QT_MAX_ALUNOS);

		if(turno.equals("1")) {
			dsTurno = NM_TURNO_MANHA;
		}else if(turno.equals("2")) {
			dsTurno = NM_TURNO_TARDE;
		}
				
		//monta a entidade disciplina para alterar
		Turma turma = new Turma();
		turma.setIdTurma(idTurma);
		turma.setDsTurma(descricao);
		turma.setTurno(dsTurno);
		turma.setQtMaxAlunos(Integer.valueOf(qtMaxAlunos));

		//altera em TURMA
		TurmaDAO turmaDAO = new TurmaDAO();
		turmaDAO.alterar(turma);
				
		this.redirecionarPagina(request, response, NM_JSP_CONSULTAR);
	}
	
	public void processarConsultaSelectProfessor(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// declara as variaveis
		String valorSelectTurma = "";
		String valorSelectProfessor = "";
		String eventoSelectProfessor = "";
		String txPrimeiraUnidade = "";
		String txSegundaUnidade = "";
		String txTerceiraUnidade = "";
		String txQuartaUnidade = "";
		String cargaHoraria = "";

		// recupera os parametros do request
		valorSelectTurma = request.getParameter(NM_PARAMETRO_SELECT_TURMA);
		valorSelectProfessor = request.getParameter(NM_PARAMETRO_SELECT_PROFESSOR);
		eventoSelectProfessor = request.getParameter(NM_EVENTO);
		txPrimeiraUnidade = request.getParameter(NM_PARAMETRO_TX_PRIMEIRA_UNIDADE);
		txSegundaUnidade = request.getParameter(NM_PARAMETRO_TX_SEGUNDA_UNIDADE);
		txTerceiraUnidade = request.getParameter(NM_PARAMETRO_TX_TERCEIRA_UNIDADE);
		txQuartaUnidade = request.getParameter(NM_PARAMETRO_TX_QUARTA_UNIDADE);
		cargaHoraria = request.getParameter(NM_PARAMETRO_CAMPO_CARGA_HORARIA);

		request.setAttribute(NM_EVENTO_PROCESSAR_CONSULTA_SELECT_PROFESSOR, eventoSelectProfessor);
		request.setAttribute(NM_PARAMETRO_SELECT_PROFESSOR, valorSelectProfessor);
		request.setAttribute(NM_PARAMETRO_SELECT_TURMA, valorSelectTurma);
		request.setAttribute(NM_PARAMETRO_TX_PRIMEIRA_UNIDADE, txPrimeiraUnidade);
		request.setAttribute(NM_PARAMETRO_TX_SEGUNDA_UNIDADE, txSegundaUnidade);
		request.setAttribute(NM_PARAMETRO_TX_TERCEIRA_UNIDADE, txTerceiraUnidade);
		request.setAttribute(NM_PARAMETRO_TX_QUARTA_UNIDADE, txQuartaUnidade);
		request.setAttribute(NM_PARAMETRO_CAMPO_CARGA_HORARIA, cargaHoraria);
		this.redirecionarPagina(request, response, NM_JSP_INCLUIR_SERVICO);
		
	}

	public void processarConsultaSelectDisciplina(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		// declara as variaveis
		String valorSelectTurma = "";
		String valorSelectDisciplina = "";
		String eventoSelectDisciplina = "";
		String txPrimeiraUnidade = "";
		String txSegundaUnidade = "";
		String txTerceiraUnidade = "";
		String txQuartaUnidade = "";
		String cargaHoraria = "";
		
		// recupera os parametros do request
		valorSelectTurma = request.getParameter(NM_PARAMETRO_SELECT_TURMA);
		valorSelectDisciplina = request.getParameter(NM_PARAMETRO_SELECT_DISCIPLINA);
		eventoSelectDisciplina = request.getParameter(NM_EVENTO);
		txPrimeiraUnidade = request.getParameter(NM_PARAMETRO_TX_PRIMEIRA_UNIDADE);
		txSegundaUnidade = request.getParameter(NM_PARAMETRO_TX_SEGUNDA_UNIDADE);
		txTerceiraUnidade = request.getParameter(NM_PARAMETRO_TX_TERCEIRA_UNIDADE);
		txQuartaUnidade = request.getParameter(NM_PARAMETRO_TX_QUARTA_UNIDADE);
		cargaHoraria = request.getParameter(NM_PARAMETRO_CAMPO_CARGA_HORARIA);
		
		request.setAttribute(NM_EVENTO_PROCESSAR_CONSULTA_SELECT_DISCIPLINA, eventoSelectDisciplina);
		request.setAttribute(NM_PARAMETRO_SELECT_DISCIPLINA, valorSelectDisciplina);
		request.setAttribute(NM_PARAMETRO_SELECT_TURMA, valorSelectTurma);
		request.setAttribute(NM_PARAMETRO_TX_PRIMEIRA_UNIDADE, txPrimeiraUnidade);
		request.setAttribute(NM_PARAMETRO_TX_SEGUNDA_UNIDADE, txSegundaUnidade);
		request.setAttribute(NM_PARAMETRO_TX_TERCEIRA_UNIDADE, txTerceiraUnidade);
		request.setAttribute(NM_PARAMETRO_TX_QUARTA_UNIDADE, txQuartaUnidade);
		request.setAttribute(NM_PARAMETRO_CAMPO_CARGA_HORARIA, cargaHoraria);
		this.redirecionarPagina(request, response, NM_JSP_INCLUIR_SERVICO);
	}

}

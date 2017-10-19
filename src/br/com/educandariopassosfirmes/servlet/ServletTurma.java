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
import br.com.educandariopassosfirmes.dao.DisciplinaTipoEnsinoDAO;
import br.com.educandariopassosfirmes.dao.TurmaDAO;
import br.com.educandariopassosfirmes.entidades.Disciplina;
import br.com.educandariopassosfirmes.entidades.DisciplinaTipoEnsino;
import br.com.educandariopassosfirmes.entidades.Turma;


/**
 * Servlet implementation class ServletTurma
 */
@WebServlet("/ServletTurma")
public class ServletTurma extends ServletGenerico {

	public static final String NM_JSP_CONSULTAR = "/turma/consultarTurma.jsp";

	private static final String NM_JSP_INCLUIR_SERVICO = "/turma/cadastrarTurma.jsp";

	private static final String NM_JSP_ALTERAR_DISCIPLINA = "/turma/alterarTurma.jsp";

	public static final String NM_PARAMETRO_CHAVE = "chave";
	
	//Parâmetros inclusão disciplina
	public static final String NM_PARAMETRO_ID_DISCIPLINA = "idDisciplina";
	public static final String NM_PARAMETRO_SIGLA_DISCIPLINA = "siglaDisciplina";
	public static final String NM_PARAMETRO_DS_TURMA = "descricaoTurma";
	public static final String NM_PARAMETRO_TURNO = "turno";
	public static final String NM_PARAMETRO_QT_MAX_ALUNOS = "qtMaxAlunos";
	public static final String NM_PARAMETRO_TX_PRIMEIRA_UNIDADE = "txPrimeiraUnidade";
	public static final String NM_PARAMETRO_TX_SEGUNDA_UNIDADE = "txSegundaUnidade";
	public static final String NM_PARAMETRO_TX_TERCEIRA_UNIDADE = "txTerceiraUnidade";
	public static final String NM_PARAMETRO_TX_QUARTA_UNIDADE = "txQuartaUnidade";
	public static final String NM_PARAMETRO_CAMPO_CARGA_HORARIA = "cargaHoraria";
	public static final String NM_PARAMETRO_SELECT_TURNO = "selectTurno";
	public static final String NM_PARAMETRO_COLECAO_TURMA = "colecaoTurma";
	public static final String NM_PARAMETRO_CD_TIPO_ENSINO = "cdTipoEnsino";
	
	//Constantes utilizadas na inclusão de disciplinas
	public static final String NM_TIPO_ENSINO_BASICO = "Educação Infantil";
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
		} else {
			// caso nao tenha nenhum evento, redireciona para a pagina de consulta
			this.redirecionarPagina(request, response, NM_JSP_CONSULTAR);
		}

	}

	@Override
	public void exibirInclusao(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// redireciona para a pagina de inclusao
		this.redirecionarPagina(request, response,	NM_JSP_INCLUIR_SERVICO);
	};

	@Override
	public void processarInclusao(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// declara as variaveis
		String descricao = "";
		String turno = "";
		String qtMaxAlunos = "";

		// recupera os parametros do request
		descricao = request.getParameter(NM_PARAMETRO_DS_TURMA);
		turno = request.getParameter(NM_PARAMETRO_TURNO);
		qtMaxAlunos = request.getParameter(NM_PARAMETRO_QT_MAX_ALUNOS);

		//monta a entidade disciplina para incluir
		Turma turma = new Turma();
		turma.setDsTurma(descricao);
		turma.setTurno(turno);
		turma.setQtMaxAlunos(Integer.valueOf(qtMaxAlunos));

		//inclui em TURMA
		TurmaDAO turmaDAO = new TurmaDAO();
		//turmaDAO.incluir(turma);
		
		//Integer maiorIdDisciplina = disciplinaDAO.consultarMaiorIdDisciplina();
		
		//inclui em DISCIPLINA_TIPO_ENSINO
		//DisciplinaTipoEnsinoDAO disciplinaTipoEnsinoDAO = new DisciplinaTipoEnsinoDAO();
		//disciplinaTipoEnsinoDAO.incluir(maiorIdDisciplina, Integer.valueOf(cdTipoEnsino));
		
		this.redirecionarPagina(request, response, NM_JSP_CONSULTAR);

	};

	@Override
	public void consultarTodos(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// recupera os parametros do request
		String siglaDisciplina = (String) request.getParameter(NM_PARAMETRO_SIGLA_DISCIPLINA);
		String dsDisciplina = (String) request.getParameter(NM_PARAMETRO_DS_TURMA);

		TurmaDAO turmaDAO = new TurmaDAO();
		//consultar todas as turmas
		ArrayList<Turma> colecaoTurma = turmaDAO.consultar("", "");
		
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
				
		String[] chaveDisciplina = chave.split(";");
		String idDisciplina = chaveDisciplina[0];

		DisciplinaTipoEnsinoDAO disciplinaTipoEnsinoDAO = new DisciplinaTipoEnsinoDAO();		
		disciplinaTipoEnsinoDAO.excluir(Integer.valueOf(idDisciplina));
		
		DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
		disciplinaDAO.excluir(Integer.valueOf(idDisciplina));

		this.redirecionarPagina(request, response, NM_JSP_CONSULTAR);
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
		String assuntoPrimeiraUnidade = "";
		String assuntoSegundaUnidade = "";
		String assuntoTerceiraUnidade = "";
		String assuntoQuartaUnidade = "";
		Integer cargaHoraria = 0;
		Integer cdTipoEnsino = 0;
		DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
		DisciplinaTipoEnsinoDAO disciplinaTipoEnsinoDAO = new DisciplinaTipoEnsinoDAO();
				
		//consulta pelo id da disciplina recuperar os valores e setar no request				
		ArrayList<Disciplina> colecaoDisciplina = disciplinaDAO.consultar(Integer.valueOf(idDisciplina),"", "");
		ArrayList<DisciplinaTipoEnsino> colecaoDisciplinaTpoEnsino = disciplinaTipoEnsinoDAO.consultarDisciplinaTipoEnsino(Integer.valueOf(idDisciplina));
		
		Iterator<Disciplina>itDisciplina = colecaoDisciplina.iterator();
		while(itDisciplina.hasNext()) {
			Disciplina disciplina = itDisciplina.next();
			
			siglaDisciplina = disciplina.getSiglaDisciplina();
			dsDisciplina = disciplina.getDsDisciplina();
			assuntoPrimeiraUnidade = disciplina.getAssuntoPrimeiraUnidade();
			assuntoSegundaUnidade = disciplina.getAssuntoSegundaUnidade();
			assuntoTerceiraUnidade = disciplina.getAssuntoTerceiraUnidade();
			assuntoQuartaUnidade = disciplina.getAssuntoQuartaUnidade();
			cargaHoraria = disciplina.getCargaHorariaMinima();
		}
		
		Iterator<DisciplinaTipoEnsino>itDisciplinaTipoEnsino = colecaoDisciplinaTpoEnsino.iterator();
		while(itDisciplinaTipoEnsino.hasNext()) {
			DisciplinaTipoEnsino disciplinaTipoEnsino = itDisciplinaTipoEnsino.next();
			
			cdTipoEnsino = disciplinaTipoEnsino.getCdTipoEnsino();
		}
		
		//seta os atributos no request para recuperar na JSP
		request.setAttribute(NM_PARAMETRO_ID_DISCIPLINA, idDisciplina);
		request.setAttribute(NM_PARAMETRO_SIGLA_DISCIPLINA, siglaDisciplina);
		request.setAttribute(NM_PARAMETRO_DS_TURMA, dsDisciplina);
		request.setAttribute(NM_PARAMETRO_TX_PRIMEIRA_UNIDADE, assuntoPrimeiraUnidade);
		request.setAttribute(NM_PARAMETRO_TX_SEGUNDA_UNIDADE, assuntoSegundaUnidade);
		request.setAttribute(NM_PARAMETRO_TX_TERCEIRA_UNIDADE, assuntoTerceiraUnidade);
		request.setAttribute(NM_PARAMETRO_TX_QUARTA_UNIDADE, assuntoQuartaUnidade);
		request.setAttribute(NM_PARAMETRO_CAMPO_CARGA_HORARIA, cargaHoraria);
		request.setAttribute(NM_PARAMETRO_CD_TIPO_ENSINO, cdTipoEnsino);

		this.redirecionarPagina(request, response, NM_JSP_ALTERAR_DISCIPLINA);
	}

	@Override
	public void processarAlteracao(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// declara as variaveis
		String idDisciplina = "";
		String sigla = "";
		String descricao = "";
		String assuntoPrimeiraUnidade = "";
		String assuntoSegundaUnidade = "";
		String assuntoTerceiraUnidade = "";
		String assuntoQuartaUnidade = "";
		String cargaHoraria = "";
		String cdTipoEnsino = "";

		// recupera os parametros do request
		idDisciplina = request.getParameter(NM_PARAMETRO_ID_DISCIPLINA);
		sigla = request.getParameter(NM_PARAMETRO_SIGLA_DISCIPLINA);
		descricao = request.getParameter(NM_PARAMETRO_DS_TURMA);
		assuntoPrimeiraUnidade = request.getParameter(NM_PARAMETRO_TX_PRIMEIRA_UNIDADE);
		assuntoSegundaUnidade = request.getParameter(NM_PARAMETRO_TX_SEGUNDA_UNIDADE);
		assuntoTerceiraUnidade = request.getParameter(NM_PARAMETRO_TX_TERCEIRA_UNIDADE);
		assuntoQuartaUnidade = request.getParameter(NM_PARAMETRO_TX_QUARTA_UNIDADE);
		cargaHoraria = request.getParameter(NM_PARAMETRO_CAMPO_CARGA_HORARIA);
		cdTipoEnsino = request.getParameter(NM_PARAMETRO_SELECT_TURNO);

		//monta a entidade disciplina para alterar
		Disciplina disciplina = new Disciplina();
		disciplina.setIdDisciplina(Integer.valueOf(idDisciplina));
		disciplina.setSiglaDisciplina(sigla);
		disciplina.setDsDisciplina(descricao);
		disciplina.setAssuntoPrimeiraUnidade(assuntoPrimeiraUnidade);
		disciplina.setAssuntoSegundaUnidade(assuntoSegundaUnidade);
		disciplina.setAssuntoTerceiraUnidade(assuntoTerceiraUnidade);
		disciplina.setAssuntoQuartaUnidade(assuntoQuartaUnidade);
		disciplina.setCargaHorariaMinima(Integer.valueOf(cargaHoraria));

		//alterar em DISCIPLINA
		DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
		disciplinaDAO.alterar(disciplina);
		
		//monta a entidade disciplinaTpoEnsino para alterar
		DisciplinaTipoEnsino disciplinaTipoEnsino = new DisciplinaTipoEnsino();
		disciplinaTipoEnsino.setIdDisciplina(Integer.valueOf(idDisciplina));
		disciplinaTipoEnsino.setCdTipoEnsino(Integer.valueOf(cdTipoEnsino));
		
		//alterar em DISCIPLINA_TIPO_ENSINO
		DisciplinaTipoEnsinoDAO disciplinaTipoEnsinoDAO = new DisciplinaTipoEnsinoDAO();
		disciplinaTipoEnsinoDAO.alterar(disciplinaTipoEnsino);
				
		this.redirecionarPagina(request, response, NM_JSP_CONSULTAR);
	}

}

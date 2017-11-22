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
 * Servlet implementation class ServletTurma
 */
@WebServlet("/ServletTurma")
public class ServletTurma extends ServletGenerico {

	public static final String NM_JSP_CONSULTAR = "/turma/consultarTurma.jsp";

	private static final String NM_JSP_INCLUIR_SERVICO = "/turma/cadastrarTurma.jsp";

	private static final String NM_JSP_ALTERAR_TURMA = "/turma/alterarTurma.jsp";
	
	public static final String NM_PARAMETRO_CHAVE = "chave";
	
	//Parâmetros inclusão disciplina
	public static final String NM_PARAMETRO_SIGLA_TURMA = "siglaTurma";
	public static final String NM_PARAMETRO_DS_TURMA = "descricaoTurma";
	public static final String NM_PARAMETRO_TURNO = "turno";
	public static final String NM_PARAMETRO_QT_MAX_ALUNOS = "qtMaxAlunos";
	public static final String NM_PARAMETRO_SALA = "sala";
	public static final String NM_PARAMETRO_SELECT_TURNO = "selectTurno";
	public static final String NM_PARAMETRO_COLECAO_TURMA = "colecaoTurma";
	
	//Constantes utilizadas na inclusão de turmas
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
		String sala = "";

		// recupera os parametros do request
		siglaTurma = request.getParameter(NM_PARAMETRO_SIGLA_TURMA);
		descricao = request.getParameter(NM_PARAMETRO_DS_TURMA);
		turno = request.getParameter(NM_PARAMETRO_SELECT_TURNO);
		qtMaxAlunos = request.getParameter(NM_PARAMETRO_QT_MAX_ALUNOS);
		sala = request.getParameter(NM_PARAMETRO_SALA);

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
		turma.setSala(sala);
		
		if(qtMaxAlunos != null && !qtMaxAlunos.equals("")){
			turma.setQtMaxAlunos(Integer.valueOf(qtMaxAlunos));
		}

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
		
		TurmaDAO turmaDAO = new TurmaDAO();
		ArrayList<Turma> consultaTurma = turmaDAO.consultar(idTurma, "", "");
		Turma turma = consultaTurma.get(0);
		
		//seta os atributos no request para recuperar na JSP
		request.setAttribute(NM_PARAMETRO_SIGLA_TURMA, idTurma);
		request.setAttribute(NM_PARAMETRO_DS_TURMA, turma.getDsTurma());
		
		if(turma.getTurno().equals(NM_TURNO_MANHA)) {
			request.setAttribute(NM_PARAMETRO_TURNO, "1");			
		}else{
			request.setAttribute(NM_PARAMETRO_TURNO, "2");
		}
		request.setAttribute(NM_PARAMETRO_QT_MAX_ALUNOS, String.valueOf(turma.getQtMaxAlunos()));
		request.setAttribute(NM_PARAMETRO_SALA, turma.getSala());

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
		String sala = "";

		// recupera os parametros do request
		idTurma = request.getParameter(NM_PARAMETRO_SIGLA_TURMA);
		descricao = request.getParameter(NM_PARAMETRO_DS_TURMA);
		turno = request.getParameter(NM_PARAMETRO_SELECT_TURNO);
		qtMaxAlunos = request.getParameter(NM_PARAMETRO_QT_MAX_ALUNOS);
		sala = request.getParameter(NM_PARAMETRO_SALA);

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
		turma.setSala(sala);
		
		if(qtMaxAlunos != null && !qtMaxAlunos.equals("")){
			turma.setQtMaxAlunos(Integer.valueOf(qtMaxAlunos));
		}

		//altera em TURMA
		TurmaDAO turmaDAO = new TurmaDAO();
		turmaDAO.alterar(turma);
				
		this.redirecionarPagina(request, response, NM_JSP_CONSULTAR);
	}

}

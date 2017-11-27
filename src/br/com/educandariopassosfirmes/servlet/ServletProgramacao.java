package br.com.educandariopassosfirmes.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.educandariopassosfirmes.dao.ConsultaPrincipalTurmaProfessorDisciplina;
import br.com.educandariopassosfirmes.dao.TurmaProfessorDisciplinaDAO;
import br.com.educandariopassosfirmes.entidades.TurmaProfessorDisciplina;

/**
 * Servlet implementation class ServletProgramacao
 */
@WebServlet("/ServletProgramacao")
public class ServletProgramacao extends ServletGenerico {

	public static final String NM_JSP_CONSULTAR = "/programacaoTurma/consultarProgramacao.jsp";

	public static final String NM_JSP_INCLUIR_SERVICO = "/programacaoTurma/cadastrarProgramacao.jsp";

	public static final String NM_JSP_ALTERAR_TURMA = "/programacaoTurma/alterarProgramacao.jsp";

	public static final String NM_EVENTO_PROCESSAR_CONSULTA_SELECT_PROFESSOR = "processarConsultaSelectProfessor";
	
	public static final String NM_EVENTO_PROCESSAR_CONSULTA_SELECT_DISCIPLINA = "processarConsultaSelectDisciplina";
	
	public static final String NM_EVENTO_TELA = "eventoTela";

	public static final String NM_PARAMETRO_CHAVE = "chave";

	// Parâmetros inclusão disciplina
	public static final String NM_PARAMETRO_SELECT_TURMA = "selectTurma";
	public static final String NM_PARAMETRO_SELECT_PROFESSOR = "selectProfessor";
	public static final String NM_PARAMETRO_SELECT_DISCIPLINA = "selectDisciplina";
	public static final String NM_PARAMETRO_SELECT_TURMA_ANT = "selectTurmaAnt";
	public static final String NM_PARAMETRO_SELECT_PROFESSOR_ANT = "selectProfessorAnt";
	public static final String NM_PARAMETRO_SELECT_DISCIPLINA_ANT = "selectDisciplinaAnt";
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
	public static final String NM_PARAMETRO_PROGRAMACAO = "programacao";

	// Constantes utilizadas na inclusão de turmas
	public static final String NM_TURNO_MANHA = "Matutino";
	public static final String NM_TURNO_TARDE = "Vespertino";

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// declara as variaveis
		String acao = "";

		// recupera o evento desejado
		acao = request.getParameter(this.NM_EVENTO);

		if (acao != null && acao.equalsIgnoreCase(this.NM_EVENTO_EXIBIR_INCLUSAO)) {
			this.exibirInclusao(request, response);
		} else if (acao != null && acao.equalsIgnoreCase(this.NM_EVENTO_PROCESSAR_INCLUSAO)) {
			this.processarInclusao(request, response);
		} else if (acao != null && acao.equalsIgnoreCase(this.NM_EVENTO_CONSULTAR_TODOS)) {
			this.consultarTodos(request, response);
		} else if (acao != null && acao.equalsIgnoreCase(this.NM_EVENTO_EXCLUIR)) {
			this.excluir(request, response);
		} else if (acao != null && acao.equalsIgnoreCase(this.NM_EVENTO_EXIBIR_ALTERACAO)) {
			this.exibirAlteracao(request, response);
		} else if (acao != null && acao.equalsIgnoreCase(this.NM_EVENTO_PROCESSAR_ALTERACAO)) {
			this.processarAlteracao(request, response);
		} else if (acao != null && acao.equalsIgnoreCase(NM_EVENTO_PROCESSAR_CONSULTA_SELECT_PROFESSOR)) {
			this.processarConsultaSelectProfessor(request, response);
		} else if (acao != null && acao.equalsIgnoreCase(NM_EVENTO_PROCESSAR_CONSULTA_SELECT_DISCIPLINA)) {
			this.processarConsultaSelectDisciplina(request, response);
		} else {
			// caso nao tenha nenhum evento, redireciona para a pagina de consulta
			this.redirecionarPagina(request, response, NM_JSP_CONSULTAR);
		}

	}

	@Override
	public void exibirInclusao(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String descricao = "";
		String qtMaxAlunos = "";
		String programacao = "";

		descricao = request.getParameter(NM_PARAMETRO_DS_TURMA);
		qtMaxAlunos = request.getParameter(NM_PARAMETRO_QT_MAX_ALUNOS);
		
		TurmaProfessorDisciplinaDAO turmaProfessorDisciplinaDAO = new TurmaProfessorDisciplinaDAO();
		ArrayList<TurmaProfessorDisciplina> consultaProgramacao = turmaProfessorDisciplinaDAO.consultar("", "", "");
		
		for(int x = 0; x < consultaProgramacao.size(); x++) {
			TurmaProfessorDisciplina turmaProfessorDisciplina = consultaProgramacao.get(x);
			
			programacao += turmaProfessorDisciplina.getIdTurma() + ";" + turmaProfessorDisciplina.getIdProfessor()
			+ ";" + turmaProfessorDisciplina.getIdDisciplina() + ":";
		}

		request.setAttribute(NM_PARAMETRO_DS_TURMA, descricao);
		request.setAttribute(NM_PARAMETRO_QT_MAX_ALUNOS, qtMaxAlunos);
		request.setAttribute(NM_PARAMETRO_PROGRAMACAO, programacao);

		// redireciona para a pagina de inclusao
		this.redirecionarPagina(request, response, NM_JSP_INCLUIR_SERVICO);
	};

	@Override
	public void processarInclusao(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// declara as variaveis
		String valorSelectTurma = "";
		String valorSelectProfessor = "";
		String valorSelectDisciplina = "";
		String txPrimeiraUnidade = "";
		String txSegundaUnidade = "";
		String txTerceiraUnidade = "";
		String txQuartaUnidade = "";
		String cargaHoraria = "";

		// recupera os parametros do request
		valorSelectTurma = request.getParameter(NM_PARAMETRO_SELECT_TURMA);
		valorSelectProfessor = request.getParameter(NM_PARAMETRO_SELECT_PROFESSOR);
		valorSelectDisciplina = request.getParameter(NM_PARAMETRO_SELECT_DISCIPLINA);
		txPrimeiraUnidade = request.getParameter(NM_PARAMETRO_TX_PRIMEIRA_UNIDADE);
		txSegundaUnidade = request.getParameter(NM_PARAMETRO_TX_SEGUNDA_UNIDADE);
		txTerceiraUnidade = request.getParameter(NM_PARAMETRO_TX_TERCEIRA_UNIDADE);
		txQuartaUnidade = request.getParameter(NM_PARAMETRO_TX_QUARTA_UNIDADE);
		cargaHoraria = request.getParameter(NM_PARAMETRO_CAMPO_CARGA_HORARIA);

		TurmaProfessorDisciplina turmaProfessorDisciplina = new TurmaProfessorDisciplina();

		turmaProfessorDisciplina.setIdTurma(valorSelectTurma);
		turmaProfessorDisciplina.setIdProfessor(valorSelectProfessor);
		
		if(valorSelectDisciplina != null && !valorSelectDisciplina.equals("0")){
			turmaProfessorDisciplina.setIdDisciplina(Integer.valueOf(valorSelectDisciplina));
		}
		
		turmaProfessorDisciplina.setAssuntoPrimeiraUnidade(txPrimeiraUnidade);
		turmaProfessorDisciplina.setAssuntoSegundaUnidade(txSegundaUnidade);
		turmaProfessorDisciplina.setAssuntoTerceiraUnidade(txTerceiraUnidade);
		turmaProfessorDisciplina.setAssuntoQuartaUnidade(txQuartaUnidade);
		
		if(cargaHoraria != null && !cargaHoraria.equals("")){
			turmaProfessorDisciplina.setCargaHorariaMinima(Integer.valueOf(cargaHoraria));
		}

		TurmaProfessorDisciplinaDAO turmaProfessorDisciplinaDAO = new TurmaProfessorDisciplinaDAO();
		turmaProfessorDisciplinaDAO.incluir(turmaProfessorDisciplina);

		this.redirecionarPagina(request, response, NM_JSP_CONSULTAR);

	};

	@Override
	public void consultarTodos(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// recupera os parametros do request
		String idTurma = (String) request.getParameter(NM_PARAMETRO_SELECT_TURMA);
		String idProfessor = (String) request.getParameter(NM_PARAMETRO_SELECT_PROFESSOR);
		String idDisciplina = (String) request.getParameter(NM_PARAMETRO_SELECT_DISCIPLINA);

		ConsultaPrincipalTurmaProfessorDisciplina consultaTurmaProfessorDisciplina = new ConsultaPrincipalTurmaProfessorDisciplina();
		ArrayList<LinkedHashMap<String, String>> consulta = consultaTurmaProfessorDisciplina.consultar(idTurma, idProfessor, idDisciplina);

		request.setAttribute(NM_PARAMETRO_SELECT_TURMA, idTurma);
		request.setAttribute(NM_PARAMETRO_SELECT_PROFESSOR, idProfessor);
		request.setAttribute(NM_PARAMETRO_SELECT_DISCIPLINA, idDisciplina);
		request.setAttribute(NM_PARAMETRO_COLECAO_TURMA, consulta);

		this.redirecionarPagina(request, response, NM_JSP_CONSULTAR);
	}

	@Override
	public void excluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// declara as variaveis
		String chave = "";

		// recupera os parametros do request
		chave = request.getParameter(NM_PARAMETRO_CHAVE);

		String[] chaveTurma = chave.split(";");
		String idTurma = chaveTurma[0];
		String idProfessor = chaveTurma[1];
		String idDisciplina = chaveTurma[2];

		TurmaProfessorDisciplinaDAO turmaProfessorDisciplinaDAO = new TurmaProfessorDisciplinaDAO();
		turmaProfessorDisciplinaDAO.excluir(idTurma, idProfessor, idDisciplina);

		this.redirecionarPagina(request, response, NM_JSP_CONSULTAR);
	}

	@Override
	public void exibirAlteracao(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// declara as variaveis
		String chave = "";
		String programacao = "";

		// recupera os parametros do request
		chave = request.getParameter(NM_PARAMETRO_CHAVE);

		String[] chaveTurma = chave.split(";");
		String idTurma = chaveTurma[0];
		String idProfessor = chaveTurma[1];
		String idDisciplina = chaveTurma[2];

		TurmaProfessorDisciplinaDAO turmaProfessorDisciplinaDAO = new TurmaProfessorDisciplinaDAO();
		ArrayList<TurmaProfessorDisciplina> colecao = turmaProfessorDisciplinaDAO.consultar(idTurma, idProfessor,
				idDisciplina);

		TurmaProfessorDisciplina turmaProfessorDisciplina = colecao.get(0);
		
		ArrayList<TurmaProfessorDisciplina> consultaProgramacao = turmaProfessorDisciplinaDAO.consultar("", "", "");
		
		for(int x = 0; x < consultaProgramacao.size(); x++) {
			TurmaProfessorDisciplina turmaProfessorDisciplinaAux = consultaProgramacao.get(x);
			
			programacao += turmaProfessorDisciplinaAux.getIdTurma() + ";" + turmaProfessorDisciplinaAux.getIdProfessor()
			+ ";" + turmaProfessorDisciplinaAux.getIdDisciplina() + ":";
		}

		request.setAttribute(NM_PARAMETRO_PROGRAMACAO, programacao);
		request.setAttribute(NM_PARAMETRO_SELECT_TURMA, turmaProfessorDisciplina.getIdTurma());
		request.setAttribute(NM_PARAMETRO_SELECT_PROFESSOR, turmaProfessorDisciplina.getIdProfessor());
		request.setAttribute(NM_PARAMETRO_SELECT_DISCIPLINA,
				String.valueOf(turmaProfessorDisciplina.getIdDisciplina()));
		request.setAttribute(NM_PARAMETRO_CAMPO_CARGA_HORARIA,
				String.valueOf(turmaProfessorDisciplina.getCargaHorariaMinima()));
		request.setAttribute(NM_PARAMETRO_TX_PRIMEIRA_UNIDADE, turmaProfessorDisciplina.getAssuntoPrimeiraUnidade());
		request.setAttribute(NM_PARAMETRO_TX_SEGUNDA_UNIDADE, turmaProfessorDisciplina.getAssuntoSegundaUnidade());
		request.setAttribute(NM_PARAMETRO_TX_TERCEIRA_UNIDADE, turmaProfessorDisciplina.getAssuntoTerceiraUnidade());
		request.setAttribute(NM_PARAMETRO_TX_QUARTA_UNIDADE, turmaProfessorDisciplina.getAssuntoQuartaUnidade());

		this.redirecionarPagina(request, response, NM_JSP_ALTERAR_TURMA);
	}

	@Override
	public void processarAlteracao(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// declara as variaveis
		String valorSelectTurma = "";
		String valorSelectProfessor = "";
		String valorSelectDisciplina = "";
		String txPrimeiraUnidade = "";
		String txSegundaUnidade = "";
		String txTerceiraUnidade = "";
		String txQuartaUnidade = "";
		String cargaHoraria = "";

		// recupera os parametros do request
		valorSelectTurma = request.getParameter(NM_PARAMETRO_SELECT_TURMA);
		valorSelectProfessor = request.getParameter(NM_PARAMETRO_SELECT_PROFESSOR);
		valorSelectDisciplina = request.getParameter(NM_PARAMETRO_SELECT_DISCIPLINA);
		txPrimeiraUnidade = request.getParameter(NM_PARAMETRO_TX_PRIMEIRA_UNIDADE);
		txSegundaUnidade = request.getParameter(NM_PARAMETRO_TX_SEGUNDA_UNIDADE);
		txTerceiraUnidade = request.getParameter(NM_PARAMETRO_TX_TERCEIRA_UNIDADE);
		txQuartaUnidade = request.getParameter(NM_PARAMETRO_TX_QUARTA_UNIDADE);
		cargaHoraria = request.getParameter(NM_PARAMETRO_CAMPO_CARGA_HORARIA);

		TurmaProfessorDisciplina turmaProfessorDisciplina = new TurmaProfessorDisciplina();

		turmaProfessorDisciplina.setIdTurma(valorSelectTurma);
		turmaProfessorDisciplina.setIdProfessor(valorSelectProfessor);
		turmaProfessorDisciplina.setIdDisciplina(Integer.valueOf(valorSelectDisciplina));
		turmaProfessorDisciplina.setAssuntoPrimeiraUnidade(txPrimeiraUnidade);
		turmaProfessorDisciplina.setAssuntoSegundaUnidade(txSegundaUnidade);
		turmaProfessorDisciplina.setAssuntoTerceiraUnidade(txTerceiraUnidade);
		turmaProfessorDisciplina.setAssuntoQuartaUnidade(txQuartaUnidade);
		
		if(cargaHoraria != null && !cargaHoraria.equals("")){
			turmaProfessorDisciplina.setCargaHorariaMinima(Integer.valueOf(cargaHoraria));
		}
		
		TurmaProfessorDisciplinaDAO turmaProfessorDisciplinaDAO = new TurmaProfessorDisciplinaDAO();
		turmaProfessorDisciplinaDAO.alterar(turmaProfessorDisciplina);

		this.redirecionarPagina(request, response, NM_JSP_CONSULTAR);
	}

	public void processarConsultaSelectProfessor(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// declara as variaveis
		String valorSelectTurma = "";
		String valorSelectProfessor = "";
		String eventoSelectProfessor = "";
		String txPrimeiraUnidade = "";
		String txSegundaUnidade = "";
		String txTerceiraUnidade = "";
		String txQuartaUnidade = "";
		String cargaHoraria = "";
		String programacao = "";
		String eventoTela = "";
		String turmaAnt = "";
		String professorAnt = "";
		String disciplinaAnt = "";

		// recupera os parametros do request
		valorSelectTurma = request.getParameter(NM_PARAMETRO_SELECT_TURMA);
		valorSelectProfessor = request.getParameter(NM_PARAMETRO_SELECT_PROFESSOR);
		eventoSelectProfessor = request.getParameter(NM_EVENTO);
		txPrimeiraUnidade = request.getParameter(NM_PARAMETRO_TX_PRIMEIRA_UNIDADE);
		txSegundaUnidade = request.getParameter(NM_PARAMETRO_TX_SEGUNDA_UNIDADE);
		txTerceiraUnidade = request.getParameter(NM_PARAMETRO_TX_TERCEIRA_UNIDADE);
		txQuartaUnidade = request.getParameter(NM_PARAMETRO_TX_QUARTA_UNIDADE);
		cargaHoraria = request.getParameter(NM_PARAMETRO_CAMPO_CARGA_HORARIA);
		programacao = request.getParameter(NM_PARAMETRO_PROGRAMACAO);
		eventoTela = request.getParameter(NM_EVENTO_TELA);

		turmaAnt = request.getParameter(NM_PARAMETRO_SELECT_TURMA_ANT);
		professorAnt = request.getParameter(NM_PARAMETRO_SELECT_PROFESSOR_ANT);
		disciplinaAnt = request.getParameter(NM_PARAMETRO_SELECT_DISCIPLINA_ANT);
		
		request.setAttribute(NM_PARAMETRO_SELECT_TURMA_ANT, turmaAnt);
		request.setAttribute(NM_PARAMETRO_SELECT_PROFESSOR_ANT, professorAnt);
		request.setAttribute(NM_PARAMETRO_SELECT_DISCIPLINA_ANT, disciplinaAnt);
		
		request.setAttribute(NM_PARAMETRO_PROGRAMACAO, programacao);
		request.setAttribute(NM_EVENTO_PROCESSAR_CONSULTA_SELECT_PROFESSOR, eventoSelectProfessor);
		request.setAttribute(NM_PARAMETRO_SELECT_PROFESSOR, valorSelectProfessor);
		request.setAttribute(NM_PARAMETRO_SELECT_TURMA, valorSelectTurma);
		request.setAttribute(NM_PARAMETRO_TX_PRIMEIRA_UNIDADE, txPrimeiraUnidade);
		request.setAttribute(NM_PARAMETRO_TX_SEGUNDA_UNIDADE, txSegundaUnidade);
		request.setAttribute(NM_PARAMETRO_TX_TERCEIRA_UNIDADE, txTerceiraUnidade);
		request.setAttribute(NM_PARAMETRO_TX_QUARTA_UNIDADE, txQuartaUnidade);
		request.setAttribute(NM_PARAMETRO_CAMPO_CARGA_HORARIA, cargaHoraria);
		
		if(eventoTela.equals(NM_JSP_INCLUIR_SERVICO)) {
			this.redirecionarPagina(request, response, NM_JSP_INCLUIR_SERVICO);
		}else if(eventoTela.equals(NM_JSP_CONSULTAR)) {
			this.redirecionarPagina(request, response, NM_JSP_CONSULTAR);
		}else {
			this.redirecionarPagina(request, response, NM_JSP_ALTERAR_TURMA);
		}

	}

	public void processarConsultaSelectDisciplina(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// declara as variaveis
		String valorSelectTurma = "";
		String valorSelectDisciplina = "";
		String eventoSelectDisciplina = "";
		String txPrimeiraUnidade = "";
		String txSegundaUnidade = "";
		String txTerceiraUnidade = "";
		String txQuartaUnidade = "";
		String cargaHoraria = "";
		String programacao = "";
		String eventoTela = "";
		String turmaAnt = "";
		String professorAnt = "";
		String disciplinaAnt = "";

		// recupera os parametros do request
		valorSelectTurma = request.getParameter(NM_PARAMETRO_SELECT_TURMA);
		valorSelectDisciplina = request.getParameter(NM_PARAMETRO_SELECT_DISCIPLINA);
		eventoSelectDisciplina = request.getParameter(NM_EVENTO);
		txPrimeiraUnidade = request.getParameter(NM_PARAMETRO_TX_PRIMEIRA_UNIDADE);
		txSegundaUnidade = request.getParameter(NM_PARAMETRO_TX_SEGUNDA_UNIDADE);
		txTerceiraUnidade = request.getParameter(NM_PARAMETRO_TX_TERCEIRA_UNIDADE);
		txQuartaUnidade = request.getParameter(NM_PARAMETRO_TX_QUARTA_UNIDADE);
		cargaHoraria = request.getParameter(NM_PARAMETRO_CAMPO_CARGA_HORARIA);
		programacao = request.getParameter(NM_PARAMETRO_PROGRAMACAO);
		eventoTela = request.getParameter(NM_EVENTO_TELA);

		turmaAnt = request.getParameter(NM_PARAMETRO_SELECT_TURMA_ANT);
		professorAnt = request.getParameter(NM_PARAMETRO_SELECT_PROFESSOR_ANT);
		disciplinaAnt = request.getParameter(NM_PARAMETRO_SELECT_DISCIPLINA_ANT);
		
		request.setAttribute(NM_PARAMETRO_SELECT_TURMA_ANT, turmaAnt);
		request.setAttribute(NM_PARAMETRO_SELECT_PROFESSOR_ANT, professorAnt);
		request.setAttribute(NM_PARAMETRO_SELECT_DISCIPLINA_ANT, disciplinaAnt);
		
		request.setAttribute(NM_PARAMETRO_PROGRAMACAO, programacao);
		request.setAttribute(NM_EVENTO_PROCESSAR_CONSULTA_SELECT_DISCIPLINA, eventoSelectDisciplina);
		request.setAttribute(NM_PARAMETRO_SELECT_DISCIPLINA, valorSelectDisciplina);
		request.setAttribute(NM_PARAMETRO_SELECT_TURMA, valorSelectTurma);
		request.setAttribute(NM_PARAMETRO_TX_PRIMEIRA_UNIDADE, txPrimeiraUnidade);
		request.setAttribute(NM_PARAMETRO_TX_SEGUNDA_UNIDADE, txSegundaUnidade);
		request.setAttribute(NM_PARAMETRO_TX_TERCEIRA_UNIDADE, txTerceiraUnidade);
		request.setAttribute(NM_PARAMETRO_TX_QUARTA_UNIDADE, txQuartaUnidade);
		request.setAttribute(NM_PARAMETRO_CAMPO_CARGA_HORARIA, cargaHoraria);
		
		if(eventoTela.equals(NM_JSP_INCLUIR_SERVICO)) {
			this.redirecionarPagina(request, response, NM_JSP_INCLUIR_SERVICO);
		}else if(eventoTela.equals(NM_JSP_CONSULTAR)) {
			this.redirecionarPagina(request, response, NM_JSP_CONSULTAR);
		}else {
			this.redirecionarPagina(request, response, NM_JSP_ALTERAR_TURMA);
		}
	}

}

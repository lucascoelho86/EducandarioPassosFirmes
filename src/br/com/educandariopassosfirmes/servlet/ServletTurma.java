package br.com.educandariopassosfirmes.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.educandariopassosfirmes.dao.AlunoDAO;
import br.com.educandariopassosfirmes.dao.PessoaDAO;
import br.com.educandariopassosfirmes.dao.TurmaDAO;
import br.com.educandariopassosfirmes.dao.TurmaProfessorDisciplinaDAO;
import br.com.educandariopassosfirmes.documentos.FrequenciaTurma;
import br.com.educandariopassosfirmes.documentos.RelacaoAlunosTurma;
import br.com.educandariopassosfirmes.entidades.Aluno;
import br.com.educandariopassosfirmes.entidades.Pessoa;
import br.com.educandariopassosfirmes.entidades.Turma;
import br.com.educandariopassosfirmes.entidades.TurmaProfessorDisciplina;
import br.com.educandariopassosfirmes.rn.RegraNegocioAlteracaoTurma;


/**
 * Servlet implementation class ServletTurma
 */
@WebServlet("/ServletTurma")
public class ServletTurma extends ServletGenerico {

	public static final String NM_JSP_CONSULTAR = "/turma/consultarTurma.jsp";

	public static final String NM_JSP_EXCECAO = "/turma/excecao.jsp";
	
	private static final String NM_JSP_EXCECAO_PROGRAMACAO = "/turma/excecaoProgramacao.jsp";

	private static final String NM_JSP_INCLUIR_SERVICO = "/turma/cadastrarTurma.jsp";

	private static final String NM_JSP_ALTERAR_TURMA = "/turma/alterarTurma.jsp";
	
	public static final String NM_PARAMETRO_CHAVE = "chave";
	
	public static final String NM_EVENTO_IMPRIMIR = "imprimirRelacao";

	public static final String NM_EVENTO_EXCLUIR_PROGRAMACAO_E_TURMA = "excluirProgramacaoTurma";

	public static final String NM_EVENTO_CHAMADA = "imprimirChamada";
	
	//Parâmetros inclusão disciplina
	public static final String NM_PARAMETRO_SIGLA_TURMA_ANT = "siglaTurmaAnt";
	public static final String NM_PARAMETRO_SIGLA_TURMA = "siglaTurma";
	public static final String NM_PARAMETRO_DS_TURMA = "descricaoTurma";
	public static final String NM_PARAMETRO_TURNO = "turno";
	public static final String NM_PARAMETRO_QT_MAX_ALUNOS = "qtMaxAlunos";
	public static final String NM_PARAMETRO_SALA = "sala";
	public static final String NM_PARAMETRO_SALA_ANT = "salaAnterior";
	public static final String NM_PARAMETRO_SELECT_TURNO = "selectTurno";
	public static final String NM_PARAMETRO_COLECAO_TURMA = "colecaoTurma";
	public static final String NM_PARAMETRO_COLECAO_SALAS_MANHA = "salasManha";
	public static final String NM_PARAMETRO_COLECAO_SALAS_TARDE = "salasTarde";
	public static final String NM_PARAMETRO_TAMANHO = "tamanho";
	
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
		} else if (acao != null
				&& acao.equalsIgnoreCase(NM_EVENTO_IMPRIMIR)) {
			this.imprimirRelacao(request, response);
		} else if (acao != null
				&& acao.equalsIgnoreCase(NM_EVENTO_CHAMADA)) {
			this.imprimirChamada(request, response);
		} else if (acao != null
				&& acao.equalsIgnoreCase(NM_EVENTO_EXCLUIR_PROGRAMACAO_E_TURMA)) {
			this.excluirProgramacaoTurma(request, response);
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
		String salasManha = "";
		String salasTarde = "";
		descricao = request.getParameter(NM_PARAMETRO_DS_TURMA);
		qtMaxAlunos = request.getParameter(NM_PARAMETRO_QT_MAX_ALUNOS);
		
		TurmaDAO turmaDAO = new TurmaDAO();
		ArrayList<Turma> consultaTurma = turmaDAO.consultar("", "", "");

		for(int x = 0; x < consultaTurma.size(); x++) {
			Turma turma = consultaTurma.get(x);
			
			if(turma.getTurno().equals(NM_TURNO_MANHA)) {
				salasManha += turma.getSala() + ";";
			}else {
				salasTarde += turma.getSala()  + ";";
			}
		}
		
		request.setAttribute(NM_PARAMETRO_DS_TURMA, descricao);
		request.setAttribute(NM_PARAMETRO_QT_MAX_ALUNOS, qtMaxAlunos);
		request.setAttribute(NM_PARAMETRO_COLECAO_SALAS_MANHA, salasManha);
		request.setAttribute(NM_PARAMETRO_COLECAO_SALAS_TARDE, salasTarde);
		
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
		String sigla = (String) request.getParameter(NM_PARAMETRO_SIGLA_TURMA);
		String descricao = (String) request.getParameter(NM_PARAMETRO_DS_TURMA);
		String turno = (String) request.getParameter(NM_PARAMETRO_SELECT_TURNO);

		if(turno.equals("1")) {
			dsTurno = NM_TURNO_MANHA;
		}else if(turno.equals("2")) {
			dsTurno = NM_TURNO_TARDE;
		}
		
		TurmaDAO turmaDAO = new TurmaDAO();
		//consultar todas as turmas
		ArrayList<Turma> colecaoTurma = turmaDAO.consultar(sigla, descricao, dsTurno);
		
		request.setAttribute(NM_PARAMETRO_SIGLA_TURMA, sigla);
		request.setAttribute(NM_PARAMETRO_DS_TURMA, descricao);
		request.setAttribute(NM_PARAMETRO_SELECT_TURNO, turno);
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

		AlunoDAO alunoDAO = new AlunoDAO();
		PessoaDAO pessoaDAO = new PessoaDAO();
		ArrayList<String> alunosTurma = new ArrayList<String>();
		ArrayList<Aluno> consultaAluno = alunoDAO.consultar("", idTurma, "");
		
		for(int x = 0; x < consultaAluno.size(); x++) {
			Aluno aluno = consultaAluno.get(x);
			
			ArrayList<Pessoa> consultaPessoa = pessoaDAO.consultar(aluno.getId(), "");
			Pessoa pessoa = consultaPessoa.get(0);
			
			alunosTurma.add(pessoa.getId() + ";" + pessoa.getNome());
		}
		
		TurmaProfessorDisciplinaDAO turmaProfessorDisciplinaDAO = new TurmaProfessorDisciplinaDAO();
		ArrayList<TurmaProfessorDisciplina> consultaTurmaProfessorDisciplina = turmaProfessorDisciplinaDAO.consultar(idTurma, "", "");

		if(alunosTurma.isEmpty() && consultaTurmaProfessorDisciplina.isEmpty()) {
			TurmaDAO turmaDAO = new TurmaDAO();
			turmaDAO.excluir(idTurma);
			this.redirecionarPagina(request, response, NM_JSP_CONSULTAR);
		}else if(!alunosTurma.isEmpty()){
			request.setAttribute(NM_PARAMETRO_SIGLA_TURMA, idTurma);
			request.setAttribute(NM_PARAMETRO_COLECAO_TURMA, alunosTurma);
			this.redirecionarPagina(request, response, NM_JSP_EXCECAO);
		}else if(!consultaTurmaProfessorDisciplina.isEmpty()) {
			TurmaDAO turmaDAO = new TurmaDAO();
			ArrayList<Turma> consultaTurma = turmaDAO.consultar(idTurma, "", "");
			Turma turma = consultaTurma.get(0);
			
			String dsTurma = turma.getDsTurma();
			
			if(dsTurma == null) {
				dsTurma = "";
			}
			
			request.setAttribute(NM_PARAMETRO_SIGLA_TURMA, idTurma);
			request.setAttribute(NM_PARAMETRO_DS_TURMA, dsTurma.equals("") ? dsTurma : dsTurma.toUpperCase());
			request.setAttribute(NM_PARAMETRO_TAMANHO, String.valueOf(consultaTurmaProfessorDisciplina.size()));
			this.redirecionarPagina(request, response, NM_JSP_EXCECAO_PROGRAMACAO);
		}
		
	}

	public void excluirProgramacaoTurma(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// declara as variaveis
		String chave = "";
		
		// recupera os parametros do request
		chave = request.getParameter(NM_PARAMETRO_SIGLA_TURMA);
		
		String[] chaveTurma = chave.split(";");
		String idTurma = chaveTurma[0];
						
		TurmaProfessorDisciplinaDAO turmaProfessorDisciplinaDAO = new TurmaProfessorDisciplinaDAO();
		ArrayList<TurmaProfessorDisciplina> consultaturmaProfessorDisciplina = turmaProfessorDisciplinaDAO.consultar(idTurma, "", "");
		
		for(int x = 0; x < consultaturmaProfessorDisciplina.size(); x++) {
			TurmaProfessorDisciplina turmaProfessorDisciplina = consultaturmaProfessorDisciplina.get(x);
			
			String turma = turmaProfessorDisciplina.getIdTurma();
			String professor = turmaProfessorDisciplina.getIdProfessor();
			Integer disciplina = turmaProfessorDisciplina.getIdDisciplina();
			
			turmaProfessorDisciplinaDAO.excluir(turma, professor, String.valueOf(disciplina));
		}
		
		TurmaDAO turmaDAO = new TurmaDAO();
		turmaDAO.excluir(idTurma);
		
		this.redirecionarPagina(request, response, NM_JSP_CONSULTAR);
		
	}

	@Override
	public void exibirAlteracao(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// declara as variaveis
		String chave = "";
		String salasManha = "";
		String salasTarde = "";
		
		// recupera os parametros do request
		chave = request.getParameter(NM_PARAMETRO_CHAVE);
		
		String[] chaveTurma = chave.split(";");
		String idTurma = chaveTurma[0];
		
		TurmaDAO turmaDAO = new TurmaDAO();
		ArrayList<Turma> consultaTurma = turmaDAO.consultar(idTurma, "", "");
		Turma turma = consultaTurma.get(0);
		
		ArrayList<Turma> consultarTordasTurmas = turmaDAO.consultar("", "", "");

		for(int x = 0; x < consultarTordasTurmas.size(); x++) {
			Turma turmaAux = consultarTordasTurmas.get(x);
			
			if(turmaAux.getTurno().equals(NM_TURNO_MANHA)) {
				salasManha += turmaAux.getIdTurma() + ";" + turmaAux.getSala() + ":";
			}else {
				salasTarde += turmaAux.getIdTurma() + ";" + turmaAux.getSala() + ":";
			}
		}
		
		//seta os atributos no request para recuperar na JSP
		request.setAttribute(NM_PARAMETRO_SIGLA_TURMA, idTurma);
		request.setAttribute(NM_PARAMETRO_DS_TURMA, turma.getDsTurma());
		request.setAttribute(NM_PARAMETRO_COLECAO_SALAS_MANHA, salasManha);
		request.setAttribute(NM_PARAMETRO_COLECAO_SALAS_TARDE, salasTarde);
		
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
		String idTurmaAnt = "";
		String descricao = "";
		String turno = "";
		String dsTurno = "";
		String qtMaxAlunos = "";
		String sala = "";

		// recupera os parametros do request
		idTurma = request.getParameter(NM_PARAMETRO_SIGLA_TURMA);
		idTurmaAnt = request.getParameter(NM_PARAMETRO_SIGLA_TURMA_ANT);
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

		TurmaDAO turmaDAO = new TurmaDAO();

		if(idTurma.equals(idTurmaAnt)) {
			//altera em TURMA
			turmaDAO.alterar(turma);
		}else {
			RegraNegocioAlteracaoTurma regra = new RegraNegocioAlteracaoTurma();
			regra.executar(idTurmaAnt, idTurma, turma);
		}
				
		this.redirecionarPagina(request, response, NM_JSP_CONSULTAR);
	}

	public void imprimirRelacao(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		// declara as variaveis
		String turma = "";
		String colecaoTurmaAluno = "";
		
		turma = request.getParameter(NM_PARAMETRO_SIGLA_TURMA);
		colecaoTurmaAluno = request.getParameter(NM_PARAMETRO_COLECAO_TURMA);
		
		RelacaoAlunosTurma relatorio = new RelacaoAlunosTurma();
		relatorio.gerar(turma, colecaoTurmaAluno);
		
		this.redirecionarPagina(request, response, NM_JSP_CONSULTAR);
	}

	public void imprimirChamada(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		// declara as variaveis
		String chave = "";
		
		chave = request.getParameter(NM_PARAMETRO_CHAVE);
		
		String[] chaveTurma = chave.split(";");
		String idTurma = chaveTurma[0];
		
		FrequenciaTurma relatorio = new FrequenciaTurma();
		relatorio.gerar(idTurma);
		
		this.redirecionarPagina(request, response, NM_JSP_CONSULTAR);
	}

}

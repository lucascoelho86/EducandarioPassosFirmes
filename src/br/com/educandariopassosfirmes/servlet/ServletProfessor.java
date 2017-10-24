package br.com.educandariopassosfirmes.servlet;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.educandariopassosfirmes.dao.PessoaDAO;
import br.com.educandariopassosfirmes.dao.ProfessorDAO;
import br.com.educandariopassosfirmes.dao.TurmaDAO;
import br.com.educandariopassosfirmes.entidades.Pessoa;
import br.com.educandariopassosfirmes.entidades.Professor;
import br.com.educandariopassosfirmes.entidades.Turma;


/**
 * Servlet implementation class ServletProfessor
 */
@WebServlet("/ServletProfessor")
public class ServletProfessor extends ServletGenerico {

	public static final String NM_JSP_CONSULTAR = "/professor/consultarProfessor.jsp";

	private static final String NM_JSP_INCLUIR_SERVICO = "/professor/cadastrarProfessor.jsp";

	private static final String NM_JSP_ALTERAR_TURMA = "/professor/alterarProfessor.jsp";

	public static final String NM_PARAMETRO_CHAVE = "chave";
	
	//Parâmetros inclusão disciplina
	public static final String NM_PARAMETRO_ID_TURMA = "idTurma";
	public static final String NM_PARAMETRO_DS_TURMA = "descricaoTurma";
	public static final String NM_PARAMETRO_TURNO = "turno";
	public static final String NM_PARAMETRO_QT_MAX_ALUNOS = "qtMaxAlunos";
	public static final String NM_PARAMETRO_SELECT_TURNO = "selectTurno";
	public static final String NM_PARAMETRO_COLECAO_TURMA = "colecaoTurma";

	public static final String NM_PARAMETRO_NOME = "nome";
	public static final String NM_PARAMETRO_DT_NASCIMENTO = "dtNascimento";
	public static final String NM_PARAMETRO_NATURALIDADE = "naturalidade";
	public static final String NM_PARAMETRO_ENDERECO = "endereco";
	public static final String NM_PARAMETRO_NUMERO = "numero";
	public static final String NM_PARAMETRO_BAIRRO = "bairro";
	public static final String NM_PARAMETRO_CIDADE = "cidade";
	public static final String NM_PARAMETRO_ESTADO = "estado";
	public static final String NM_PARAMETRO_TELEFONE = "telefone";
	public static final String NM_PARAMETRO_IDENTIDADE = "identidade";
	public static final String NM_PARAMETRO_CPF = "cpf";
	public static final String NM_PARAMETRO_FORMACAO = "formacao";
	public static final String NM_PARAMETRO_ESTADO_CIVIL = "estadoCivil";
	public static final String NM_PARAMETRO_QT_DEPENDENTE = "qtDependente";
	public static final String NM_PARAMETRO_DT_ADMISSAO = "dtAdmissao";
	public static final String NM_PARAMETRO_CARGA_HORARIA = "cargaHoraria";
	public static final String NM_PARAMETRO_SALARIO = "salario";
	
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
		String nome = "";
		String dtNascimento = "";
		String naturalidade = "";
		String endereco = "";
		String numero = "";
		String bairro = "";
		String cidade = "";
		String estado = "";
		String telefone = "";
		String identidade = "";
		String cpf = "";
		String formacao = "";
		String estadoCivil = "";
		String qtDependente = "";
		String dtAdmissao = "";
		String cargaHoraria = "";
		String salario = "";
		Date dtNasc = null;
		Date dtAdm = null;

		// recupera os parametros do request
		nome = request.getParameter(NM_PARAMETRO_NOME);
		dtNascimento = request.getParameter(NM_PARAMETRO_DT_NASCIMENTO);
		naturalidade = request.getParameter(NM_PARAMETRO_NATURALIDADE);
		endereco = request.getParameter(NM_PARAMETRO_ENDERECO);
		numero = request.getParameter(NM_PARAMETRO_NUMERO);
		bairro = request.getParameter(NM_PARAMETRO_BAIRRO);
		cidade = request.getParameter(NM_PARAMETRO_CIDADE);
		estado = request.getParameter(NM_PARAMETRO_ESTADO);
		telefone = request.getParameter(NM_PARAMETRO_TELEFONE);
		identidade = request.getParameter(NM_PARAMETRO_IDENTIDADE);
		cpf = request.getParameter(NM_PARAMETRO_CPF);
		formacao = request.getParameter(NM_PARAMETRO_FORMACAO);
		estadoCivil = request.getParameter(NM_PARAMETRO_ESTADO_CIVIL);
		qtDependente = request.getParameter(NM_PARAMETRO_QT_DEPENDENTE);
		dtAdmissao = request.getParameter(NM_PARAMETRO_DT_ADMISSAO);
		cargaHoraria = request.getParameter(NM_PARAMETRO_CARGA_HORARIA);
		salario = request.getParameter(NM_PARAMETRO_SALARIO);
		
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		
		try {
			dtNasc = (Date) formato.parse(dtNascimento);
			dtAdm = (Date) formato.parse(dtAdmissao);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//monta a entidade pessoa para incluir
		Pessoa pessoa = new Pessoa();
		pessoa.setId(cpf);
		pessoa.setNome(nome);
		pessoa.setDtNascimento(dtNasc);
		pessoa.setNaturalidade(naturalidade);
		pessoa.setEndereco(endereco);
		pessoa.setNumero(Integer.valueOf(numero));
		pessoa.setBairro(bairro);
		pessoa.setCidade(cidade);
		pessoa.setEstado(estado);
		pessoa.setTelefone(telefone);
		pessoa.setIdentidade(identidade);
		
		//inclui em PESSOA
		PessoaDAO pessoaDAO = new PessoaDAO();
		pessoaDAO.incluir(pessoa);
		
		//monta a entidade professor para incluir
		Professor professor = new Professor();
		professor.setId(cpf);
		professor.setFormacao(formacao);
		professor.setEstadoCivil(estadoCivil);
		professor.setQtDependente(Integer.valueOf(qtDependente));
		professor.setDtAdmissao(dtAdm);
		professor.setCargaHoraria(Integer.valueOf(cargaHoraria));
		professor.setSalario(Double.valueOf(salario));
		
		//inclui em PESSOA
		ProfessorDAO professorDAO = new ProfessorDAO();
		professorDAO.incluir(professor);
		
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
		ArrayList<Turma> colecaoTurma = turmaDAO.consultar(dsDisciplina, dsTurno);
		
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
		turmaDAO.excluir(Integer.valueOf(idTurma));
		
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
		request.setAttribute(NM_PARAMETRO_ID_TURMA, idTurma);
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
		idTurma = request.getParameter(NM_PARAMETRO_ID_TURMA);
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
		turma.setIdTurma(Integer.valueOf(idTurma));
		turma.setDsTurma(descricao);
		turma.setTurno(dsTurno);
		turma.setQtMaxAlunos(Integer.valueOf(qtMaxAlunos));

		//altera em TURMA
		TurmaDAO turmaDAO = new TurmaDAO();
		turmaDAO.alterar(turma);
				
		this.redirecionarPagina(request, response, NM_JSP_CONSULTAR);
	}

}

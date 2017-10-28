package br.com.educandariopassosfirmes.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

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

	private static final String NM_JSP_ALTERAR_PROFESSOR = "/professor/alterarProfessor.jsp";

	public static final String NM_PARAMETRO_CHAVE = "chave";
	
	//Parâmetros inclusão disciplina
	public static final String NM_PARAMETRO_ID_TURMA = "idTurma";
	public static final String NM_PARAMETRO_DS_TURMA = "descricaoTurma";
	public static final String NM_PARAMETRO_TURNO = "turno";
	public static final String NM_PARAMETRO_QT_MAX_ALUNOS = "qtMaxAlunos";
	public static final String NM_PARAMETRO_SELECT_DISCIPLINA = "selectTurno";
	public static final String NM_PARAMETRO_COLECAO_PESSOA = "colecaoPessoa";

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
			dtNasc = formato.parse(dtNascimento);
			dtAdm = formato.parse(dtAdmissao);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//monta a entidade pessoa para incluir
		Pessoa pessoa = new Pessoa();
		pessoa.setId(cpf);
		pessoa.setNome(nome);
		pessoa.setDtNascimento(new java.sql.Date(dtNasc.getTime()));
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
		professor.setDtAdmissao(new java.sql.Date(dtAdm.getTime()));
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

		// recupera os parametros do request
		String cpf = (String) request.getParameter(NM_PARAMETRO_CPF);
		String nome = (String) request.getParameter(NM_PARAMETRO_NOME);
		String disciplina = (String) request.getParameter(NM_PARAMETRO_SELECT_DISCIPLINA);

		//consultar professor
		PessoaDAO pessoaDAO = new PessoaDAO();
		ArrayList<Pessoa> colecaoPessoa = pessoaDAO.consultar(cpf, nome);
		
		request.setAttribute(NM_PARAMETRO_COLECAO_PESSOA, colecaoPessoa);

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
		
		String[] chaveProfessor = chave.split(";");
		String idProfessor = chaveProfessor[0];
		
		PessoaDAO pessoaDAO = new PessoaDAO();
		ArrayList<Pessoa>colecaoPessoa = pessoaDAO.consultar(idProfessor, "");
		
		Iterator<Pessoa> itPessoa = colecaoPessoa.iterator();
		while(itPessoa.hasNext()) {
			Pessoa pessoa = itPessoa.next();
			
			//seta os atributos no request para recuperar na JSP
			request.setAttribute(NM_PARAMETRO_NOME, pessoa.getNome());
			request.setAttribute(NM_PARAMETRO_DT_NASCIMENTO, String.valueOf(pessoa.getDtNascimento()));
			request.setAttribute(NM_PARAMETRO_NATURALIDADE, pessoa.getNaturalidade());
			request.setAttribute(NM_PARAMETRO_ENDERECO, pessoa.getEndereco());
			request.setAttribute(NM_PARAMETRO_NUMERO, String.valueOf(pessoa.getNumero()));
			request.setAttribute(NM_PARAMETRO_BAIRRO, pessoa.getBairro());
			request.setAttribute(NM_PARAMETRO_CIDADE, pessoa.getCidade());
			request.setAttribute(NM_PARAMETRO_ESTADO, pessoa.getEstado());
			request.setAttribute(NM_PARAMETRO_TELEFONE, pessoa.getTelefone());
			request.setAttribute(NM_PARAMETRO_IDENTIDADE, pessoa.getIdentidade());
			request.setAttribute(NM_PARAMETRO_CPF, pessoa.getId());
			
		}
		
		ProfessorDAO professorDAO = new ProfessorDAO();
		ArrayList<Professor> colecaoProfessor = professorDAO.consultar(idProfessor);
		
		Iterator<Professor> itProfessor = colecaoProfessor.iterator();
		while(itProfessor.hasNext()) {
			Professor professor = itProfessor.next();
			
			//seta os atributos no request para recuperar na JSP
			request.setAttribute(NM_PARAMETRO_FORMACAO, professor.getFormacao());
			request.setAttribute(NM_PARAMETRO_ESTADO_CIVIL, professor.getEstadoCivil());
			request.setAttribute(NM_PARAMETRO_QT_DEPENDENTE, String.valueOf(professor.getQtDependente()));
			request.setAttribute(NM_PARAMETRO_DT_ADMISSAO, String.valueOf(professor.getDtAdmissao()));
			request.setAttribute(NM_PARAMETRO_CARGA_HORARIA, String.valueOf(professor.getCargaHoraria()));
			request.setAttribute(NM_PARAMETRO_SALARIO, String.valueOf(professor.getSalario()));
			
		}
		
		this.redirecionarPagina(request, response, NM_JSP_ALTERAR_PROFESSOR);
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
		turno = request.getParameter(NM_PARAMETRO_SELECT_DISCIPLINA);
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

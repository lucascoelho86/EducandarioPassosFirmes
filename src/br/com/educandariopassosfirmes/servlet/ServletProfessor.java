package br.com.educandariopassosfirmes.servlet;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.educandariopassosfirmes.dao.ConsultaPrincipalProfessor;
import br.com.educandariopassosfirmes.dao.PessoaDAO;
import br.com.educandariopassosfirmes.dao.ProfessorDAO;
import br.com.educandariopassosfirmes.dao.ProfessorDisciplinaDAO;
import br.com.educandariopassosfirmes.entidades.Pessoa;
import br.com.educandariopassosfirmes.entidades.Professor;
import br.com.educandariopassosfirmes.entidades.ProfessorDisciplina;


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
	public static final String NM_PARAMETRO_ID_DISCIPLINA = "idDisciplina";
	public static final String NM_PARAMETRO_DS_DISCIPLINA = "descricaoDisciplina";
	public static final String NM_PARAMETRO_TAMANHO_COLECAO_DISCIPLINA = "tamanhoColecaoDisciplina";
	public static final String NM_PARAMETRO_COLECAO_PROFESSOR_DISCIPLINA = "colecaoProfessorDisciplina";

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
	public static final String NM_PARAMETRO_CPF_ANTERIOR = "cpfAnterior";
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
		String idDisciplina = "";
		String tamanhoColecaoDisciplina = "";
		Date dtNasc = null;
		Date dtAdm = null;
		double valorSalario = 0;

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
		tamanhoColecaoDisciplina = request.getParameter(NM_PARAMETRO_TAMANHO_COLECAO_DISCIPLINA);
		
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		
		try {
			if(dtNascimento != null && !dtNascimento.equals("")) {
				dtNasc = formato.parse(dtNascimento);
			}
			
			if(dtAdmissao != null && !dtAdmissao.equals("")) {
				dtAdm = formato.parse(dtAdmissao);
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		cpf = cpf.replace(".", "");
		cpf = cpf.replace("-", "");
		
		telefone = telefone.replace("(", "");
		telefone = telefone.replace(")", "");
		telefone = telefone.replace("-", "");
		
		DecimalFormatSymbols dfs = new DecimalFormatSymbols (new Locale ("pt", "BR"));
		DecimalFormat df = new DecimalFormat ("#,##0.00", dfs);
		try {
			valorSalario = df.parse (salario).doubleValue();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//monta a entidade pessoa para incluir
		Pessoa pessoa = new Pessoa();
		pessoa.setId(cpf);
		pessoa.setNome(nome);
		
		if(dtNasc != null) {
			pessoa.setDtNascimento(new java.sql.Date(dtNasc.getTime()));
		}
		
		pessoa.setNaturalidade(naturalidade);
		pessoa.setEndereco(endereco);
		
		if(numero != null && !numero.equals("")) {
			pessoa.setNumero(Integer.valueOf(numero));
		}

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
		
		if(qtDependente != null && !qtDependente.equals("")) {
			professor.setQtDependente(Integer.valueOf(qtDependente));
		}

		if(dtAdm != null) {
			professor.setDtAdmissao(new java.sql.Date(dtAdm.getTime()));
		}
		
		if(cargaHoraria != null && !cargaHoraria.equals("")) {
			professor.setCargaHoraria(Integer.valueOf(cargaHoraria));
		}
		
		professor.setSalario(valorSalario);
		
		//inclui em PESSOA
		ProfessorDAO professorDAO = new ProfessorDAO();
		professorDAO.incluir(professor);
		
		for(int x = 0; x < Integer.valueOf(tamanhoColecaoDisciplina); x++) {
			idDisciplina = request.getParameter(NM_PARAMETRO_ID_DISCIPLINA + x);
			if(idDisciplina != null) {
				ProfessorDisciplina professorDisciplina = new ProfessorDisciplina();
				professorDisciplina.setId_professor(cpf);
				professorDisciplina.setId_disciplina(Integer.valueOf(idDisciplina));
				
				ProfessorDisciplinaDAO professorDisciplinaDAO = new ProfessorDisciplinaDAO();
				professorDisciplinaDAO.cadastrar(professorDisciplina);
			}
		}
		
		this.redirecionarPagina(request, response, NM_JSP_CONSULTAR);

	};

	@Override
	public void consultarTodos(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		ArrayList<LinkedHashMap<String, String>>colecaoProfessor = new ArrayList<LinkedHashMap<String, String>>();
		
		// recupera os parametros do request
		String cpf = (String) request.getParameter(NM_PARAMETRO_CPF);
		String nome = (String) request.getParameter(NM_PARAMETRO_NOME);
		String disciplina = (String) request.getParameter(NM_PARAMETRO_SELECT_DISCIPLINA);

		request.setAttribute(NM_PARAMETRO_CPF, cpf);
		
		cpf = cpf.replace(".", "");
		cpf = cpf.replace("-", "");
		
		ConsultaPrincipalProfessor consulta = new ConsultaPrincipalProfessor();
		
		colecaoProfessor = consulta.consultar(cpf, nome, disciplina, false);
		
		request.setAttribute(NM_PARAMETRO_NOME, nome);
		request.setAttribute(NM_PARAMETRO_SELECT_DISCIPLINA, disciplina);
		request.setAttribute(NM_PARAMETRO_COLECAO_PESSOA, colecaoProfessor);
		
		this.redirecionarPagina(request, response, NM_JSP_CONSULTAR);
	}

	@Override
	public void excluir(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// declara as variaveis
		String chave = "";
				
		// recupera os parametros do request
		chave = request.getParameter(NM_PARAMETRO_CHAVE);
				
		String idProfessor = chave;

		ProfessorDAO professorDAO = new ProfessorDAO();		
		professorDAO.excluir(idProfessor);
		
		PessoaDAO pessoaDAO = new PessoaDAO();
		pessoaDAO.excluir(idProfessor);
		
		this.redirecionarPagina(request, response, NM_JSP_CONSULTAR);
	}

	@Override
	public void exibirAlteracao(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// declara as variaveis
		String chave = "";
		
		// recupera os parametros do request
		chave = request.getParameter(NM_PARAMETRO_CHAVE);
		
		String idProfessor = chave;
		
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
			request.setAttribute(NM_PARAMETRO_SALARIO, professor.getSalario());
			
		}
		
		ProfessorDisciplinaDAO professorDisciplinaDAO = new ProfessorDisciplinaDAO();
		ArrayList<ProfessorDisciplina>colecaoProfessorDisciplina = professorDisciplinaDAO.consultar(idProfessor, 0);
		request.setAttribute(NM_PARAMETRO_COLECAO_PROFESSOR_DISCIPLINA, colecaoProfessorDisciplina);
		
		this.redirecionarPagina(request, response, NM_JSP_ALTERAR_PROFESSOR);
	}

	@Override
	public void processarAlteracao(HttpServletRequest request,
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
		String idDisciplina = "";
		String tamanhoColecaoDisciplina = "";
		Date dtNasc = null;
		Date dtAdm = null;
		double valorSalario = 0;

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
		tamanhoColecaoDisciplina = request.getParameter(NM_PARAMETRO_TAMANHO_COLECAO_DISCIPLINA);
				
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
				
		try {
			if(dtNascimento != null && !dtNascimento.equals("")) {
				dtNasc = formato.parse(dtNascimento);
			}
			
			if(dtAdmissao != null && !dtAdmissao.equals("")) {
				dtAdm = formato.parse(dtAdmissao);
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		cpf = cpf.replace(".", "");
		cpf = cpf.replace("-", "");
		
		telefone = telefone.replace("(", "");
		telefone = telefone.replace(")", "");
		telefone = telefone.replace("-", "");
		
		DecimalFormatSymbols dfs = new DecimalFormatSymbols (new Locale ("pt", "BR"));
		DecimalFormat df = new DecimalFormat ("#,##0.00", dfs);
		try {
			valorSalario = df.parse (salario).doubleValue();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		//monta a entidade pessoa para alterar
		Pessoa pessoa = new Pessoa();
		pessoa.setId(cpf);
		pessoa.setNome(nome);
		
		if(dtNasc != null) {
			pessoa.setDtNascimento(new java.sql.Date(dtNasc.getTime()));
		}
		
		pessoa.setNaturalidade(naturalidade);
		pessoa.setEndereco(endereco);
		
		if(numero != null && !numero.equals("")) {
			pessoa.setNumero(Integer.valueOf(numero));
		}
		
		pessoa.setBairro(bairro);
		pessoa.setCidade(cidade);
		pessoa.setEstado(estado);
		pessoa.setTelefone(telefone);
		pessoa.setIdentidade(identidade);
				
		//altera em PESSOA
		PessoaDAO pessoaDAO = new PessoaDAO();
		pessoaDAO.alterar(pessoa);
				
		//monta a entidade professor para alterar
		Professor professor = new Professor();
		professor.setId(cpf);
		professor.setFormacao(formacao);
		professor.setEstadoCivil(estadoCivil);
		
		if(qtDependente != null && !qtDependente.equals("")) {
			professor.setQtDependente(Integer.valueOf(qtDependente));
		}
		
		if(dtAdm != null) {
			professor.setDtAdmissao(new java.sql.Date(dtAdm.getTime()));
		}
		
		if(cargaHoraria != null && !cargaHoraria.equals("")) {
			professor.setCargaHoraria(Integer.valueOf(cargaHoraria));
		}
		
		professor.setSalario(valorSalario);
				
		//altera em PESSOA
		ProfessorDAO professorDAO = new ProfessorDAO();
		professorDAO.alterar(professor);
		
		//primeiro exclui depois inclui novamente
		ProfessorDisciplinaDAO professorDisciplinaDAO = new ProfessorDisciplinaDAO();
		professorDisciplinaDAO.excluir(cpf);
		
		for(int x = 0; x < Integer.valueOf(tamanhoColecaoDisciplina); x++) {
			idDisciplina = request.getParameter(NM_PARAMETRO_ID_DISCIPLINA + x);
			if(idDisciplina != null) {
				ProfessorDisciplina professorDisciplina = new ProfessorDisciplina();
				professorDisciplina.setId_professor(cpf);
				professorDisciplina.setId_disciplina(Integer.valueOf(idDisciplina));
				
				professorDisciplinaDAO = new ProfessorDisciplinaDAO();
				professorDisciplinaDAO.cadastrar(professorDisciplina);
			}
		}
				
		this.redirecionarPagina(request, response, NM_JSP_CONSULTAR);
	}

}

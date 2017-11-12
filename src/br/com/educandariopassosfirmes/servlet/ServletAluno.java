package br.com.educandariopassosfirmes.servlet;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.educandariopassosfirmes.dao.AlunoDAO;
import br.com.educandariopassosfirmes.dao.PessoaDAO;
import br.com.educandariopassosfirmes.dao.ProfessorDAO;
import br.com.educandariopassosfirmes.dao.ProfessorDisciplinaDAO;
import br.com.educandariopassosfirmes.dao.ResponsavelDAO;
import br.com.educandariopassosfirmes.entidades.Aluno;
import br.com.educandariopassosfirmes.entidades.Pessoa;
import br.com.educandariopassosfirmes.entidades.Professor;
import br.com.educandariopassosfirmes.entidades.ProfessorDisciplina;
import br.com.educandariopassosfirmes.entidades.Responsavel;


/**
 * Servlet implementation class ServletAluno
 */
@WebServlet("/ServletAluno")
public class ServletAluno extends ServletGenerico {

	public static final String NM_JSP_CONSULTAR = "/aluno/consultarAluno.jsp";

	private static final String NM_JSP_INCLUIR_ALUNO = "/aluno/cadastrarAluno.jsp";

	private static final String NM_JSP_ALTERAR_ALUNO = "/aluno/alterarAluno.jsp";

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
	public static final String NM_PARAMETRO_NOME_RESP = "nomeResp";
	public static final String NM_PARAMETRO_DT_NASCIMENTO = "dtNascimento";
	public static final String NM_PARAMETRO_DT_NASCIMENTO_RESP = "dtNascimentoResp";
	public static final String NM_PARAMETRO_NATURALIDADE = "naturalidade";
	public static final String NM_PARAMETRO_NATURALIDADE_RESP = "naturalidadeResp";
	public static final String NM_PARAMETRO_ENDERECO = "endereco";
	public static final String NM_PARAMETRO_ENDERECO_RESP = "enderecoResp";
	public static final String NM_PARAMETRO_NUMERO = "numero";
	public static final String NM_PARAMETRO_NUMERO_RESP = "numeroResp";
	public static final String NM_PARAMETRO_BAIRRO = "bairro";
	public static final String NM_PARAMETRO_BAIRRO_RESP = "bairroResp";
	public static final String NM_PARAMETRO_CIDADE = "cidade";
	public static final String NM_PARAMETRO_CIDADE_RESP = "cidadeResp";
	public static final String NM_PARAMETRO_ESTADO = "estado";
	public static final String NM_PARAMETRO_ESTADO_RESP = "estadoResp";
	public static final String NM_PARAMETRO_CERTIDAO_NASC = "certidaoNasc";
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
	
	public static final String NM_PARAMETRO_PARENTESCO = "parentesco";
	public static final String NM_PARAMETRO_NECESSIDADE_ESPECIAL = "necEspecial";
	public static final String NM_PARAMETRO_ESCOLARIDADE = "escolaridade";
	public static final String NM_PARAMETRO_PROFISSAO = "profissao";
	public static final String NM_PARAMETRO_RENDA = "renda";
	public static final String NM_PARAMETRO_MATRICULA = "matricula";
	public static final String NM_PARAMETRO_DT_MATRICULA = "dtMatricula";
	public static final String NM_PARAMETRO_CARTEIRA_ESTUDANTE = "nrCarteiraEstudante";
	public static final String NM_PARAMETRO_TURMA = "turma";
	
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
		this.redirecionarPagina(request, response,	NM_JSP_INCLUIR_ALUNO);
	};

	@Override
	public void processarInclusao(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// declara as variaveis aluno
		String nome = "";
		String dtNascimento = "";
		String naturalidade = "";
		String endereco = "";
		String numero = "";
		String bairro = "";
		String cidade = "";
		String estado = "";
		String certidaoNasc = "";
		String matricula = "";
		String dtMatricula = "";
		String carteiraEstudante = "";
		String necEspecial = "";
		String turma = "";
		
		// declara as variaveis responsável
		String nomeResp = "";
		String dtNascimentoResp = "";
		String naturalidadeResp = "";
		String enderecoResp = "";
		String numeroResp = "";
		String bairroResp = "";
		String cidadeResp = "";
		String estadoResp = "";
		String telefone = "";
		String identidade = "";
		String cpf = "";
		String parentesco = "";
		String estadoCivil = "";
		String escolaridade = "";
		String profissao = "";
		String renda = "";
		
		String tamanhoColecaoDisciplina = "";
		Date dtNasc = null;
		Date dtNascResp = null;
		Date dtMatr = null;
		double valorSalario = 0;

		// recupera os parametros do request Aluno
		nome = request.getParameter(NM_PARAMETRO_NOME);
		dtNascimento = request.getParameter(NM_PARAMETRO_DT_NASCIMENTO);
		naturalidade = request.getParameter(NM_PARAMETRO_NATURALIDADE);
		endereco = request.getParameter(NM_PARAMETRO_ENDERECO);
		numero = request.getParameter(NM_PARAMETRO_NUMERO);
		bairro = request.getParameter(NM_PARAMETRO_BAIRRO);
		cidade = request.getParameter(NM_PARAMETRO_CIDADE);
		estado = request.getParameter(NM_PARAMETRO_ESTADO);
		certidaoNasc = request.getParameter(NM_PARAMETRO_CERTIDAO_NASC);
		matricula = request.getParameter(NM_PARAMETRO_MATRICULA);
		dtMatricula = request.getParameter(NM_PARAMETRO_DT_MATRICULA);
		carteiraEstudante = request.getParameter(NM_PARAMETRO_CARTEIRA_ESTUDANTE);
		necEspecial = request.getParameter(NM_PARAMETRO_NECESSIDADE_ESPECIAL);
		turma = request.getParameter(NM_PARAMETRO_TURMA);

		// recupera os parametros do request responsável
		nomeResp = request.getParameter(NM_PARAMETRO_NOME_RESP);
		dtNascimentoResp = request.getParameter(NM_PARAMETRO_DT_NASCIMENTO_RESP);
		naturalidadeResp = request.getParameter(NM_PARAMETRO_NATURALIDADE_RESP);
		enderecoResp = request.getParameter(NM_PARAMETRO_ENDERECO_RESP);
		numeroResp = request.getParameter(NM_PARAMETRO_NUMERO_RESP);
		bairroResp = request.getParameter(NM_PARAMETRO_BAIRRO_RESP);
		cidadeResp = request.getParameter(NM_PARAMETRO_CIDADE_RESP);
		estadoResp = request.getParameter(NM_PARAMETRO_ESTADO_RESP);
		telefone = request.getParameter(NM_PARAMETRO_TELEFONE);
		identidade = request.getParameter(NM_PARAMETRO_IDENTIDADE);
		cpf = request.getParameter(NM_PARAMETRO_CPF);
		parentesco = request.getParameter(NM_PARAMETRO_PARENTESCO);
		estadoCivil = request.getParameter(NM_PARAMETRO_ESTADO_CIVIL);
		escolaridade = request.getParameter(NM_PARAMETRO_ESCOLARIDADE);
		profissao = request.getParameter(NM_PARAMETRO_PROFISSAO);
		renda = request.getParameter(NM_PARAMETRO_RENDA);

		tamanhoColecaoDisciplina = request.getParameter(NM_PARAMETRO_TAMANHO_COLECAO_DISCIPLINA);
		
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		
		try {
			dtNasc = formato.parse(dtNascimento);
			dtMatr = formato.parse(dtMatricula);
			dtNascResp = formato.parse(dtNascimentoResp);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		matricula = matricula.replace(".", "");
		
		carteiraEstudante = carteiraEstudante.replace("-", "");
		
		cpf = cpf.replace(".", "");
		cpf = cpf.replace("-", "");
		
		telefone = telefone.replace("(", "");
		telefone = telefone.replace(")", "");
		telefone = telefone.replace("-", "");
		
		DecimalFormatSymbols dfs = new DecimalFormatSymbols (new Locale ("pt", "BR"));
		DecimalFormat df = new DecimalFormat ("#,##0.00", dfs);
		try {
			valorSalario = df.parse (renda).doubleValue();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//monta a entidade pessoa para incluir o responsável
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

		//inclui em PESSOA 2x
		PessoaDAO pessoaDAO = new PessoaDAO();
		pessoaDAO.incluir(pessoa);
		
		Responsavel responsavel = new Responsavel();
		responsavel.setId(cpf);
		responsavel.setParentesco(parentesco);
		responsavel.setEstadoCivil(estadoCivil);
		responsavel.setEscolaridade(escolaridade);
		responsavel.setProfissao(profissao);
		responsavel.setRenda(valorSalario);
		
		ResponsavelDAO responsavelDAO = new ResponsavelDAO();
		responsavelDAO.cadastrar(responsavel);

		//monta a entidade pessoa para incluir aluno
		pessoa = new Pessoa();
		pessoa.setId(matricula);
		pessoa.setNome(nome);
		pessoa.setDtNascimento(new java.sql.Date(dtNasc.getTime()));
		pessoa.setNaturalidade(naturalidade);
		pessoa.setEndereco(endereco);
		pessoa.setNumero(Integer.valueOf(numero));
		pessoa.setBairro(bairro);
		pessoa.setCidade(cidade);
		pessoa.setEstado(estado);
		
		pessoaDAO = new PessoaDAO();
		pessoaDAO.incluir(pessoa);
		
		Aluno aluno = new Aluno();
		aluno.setId(matricula);
		aluno.setIdResponsavel(cpf);
		aluno.setIdTurma(turma);
		aluno.setDtMatricula(new java.sql.Date(dtMatr.getTime()));
		aluno.setNecessidadeEspecial(necEspecial);
		aluno.setCdCarteiraEstudante(carteiraEstudante);
		
		AlunoDAO alunoDAO = new AlunoDAO();
		alunoDAO.cadastrar(aluno);
		
		this.redirecionarPagina(request, response, NM_JSP_CONSULTAR);

	};

	@Override
	public void consultarTodos(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		ArrayList<ProfessorDisciplina>colecaoProfessorDisciplina = new ArrayList<ProfessorDisciplina>();
		
		// recupera os parametros do request
		String cpf = (String) request.getParameter(NM_PARAMETRO_CPF);
		String nome = (String) request.getParameter(NM_PARAMETRO_NOME);
		String disciplina = (String) request.getParameter(NM_PARAMETRO_SELECT_DISCIPLINA);

		cpf = cpf.replace(".", "");
		cpf = cpf.replace("-", "");
		
		if((cpf != null && !cpf.equals("")) || (disciplina != null && !disciplina.equals("0"))){
			ProfessorDisciplinaDAO professorDisciplinaDAO = new ProfessorDisciplinaDAO();
			colecaoProfessorDisciplina = professorDisciplinaDAO.consultar(cpf, Integer.valueOf(disciplina));
		}
		
		ArrayList<Pessoa> colecaoPessoa = new ArrayList<Pessoa>();
		PessoaDAO pessoaDAO = new PessoaDAO();
		
		if(!colecaoProfessorDisciplina.isEmpty()){
			for(int x = 0; x < colecaoProfessorDisciplina.size(); x++){
				ProfessorDisciplina professorDisciplina = colecaoProfessorDisciplina.get(x);
				pessoaDAO = new PessoaDAO();
				ArrayList<Pessoa> colecaoPessoaAux = pessoaDAO.consultar(professorDisciplina.getId_professor(), "");
				colecaoPessoa.add(colecaoPessoaAux.get(0));
			}
		}
		
		//consultar professor se ele não tiver cadastrado em nenhuma disciplina para ministrar e se o campo disciplina tiver selecionado alguma disciplina
		if(colecaoPessoa.isEmpty() && (disciplina != null && disciplina.equals("0"))){
			pessoaDAO = new PessoaDAO();
			colecaoPessoa = pessoaDAO.consultar(cpf, nome);			
		}		
		
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
		
		this.redirecionarPagina(request, response, NM_JSP_ALTERAR_ALUNO);
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
			dtNasc = formato.parse(dtNascimento);
			dtAdm = formato.parse(dtAdmissao);
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
		pessoa.setDtNascimento(new java.sql.Date(dtNasc.getTime()));
		pessoa.setNaturalidade(naturalidade);
		pessoa.setEndereco(endereco);
		pessoa.setNumero(Integer.valueOf(numero));
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
		professor.setQtDependente(Integer.valueOf(qtDependente));
		professor.setDtAdmissao(new java.sql.Date(dtAdm.getTime()));
		professor.setCargaHoraria(Integer.valueOf(cargaHoraria));
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

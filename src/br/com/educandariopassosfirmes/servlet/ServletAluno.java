package br.com.educandariopassosfirmes.servlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.educandariopassosfirmes.dao.AlunoDAO;
import br.com.educandariopassosfirmes.dao.ConsultaPrincipalAluno;
import br.com.educandariopassosfirmes.dao.PessoaDAO;
import br.com.educandariopassosfirmes.dao.ResponsavelDAO;
import br.com.educandariopassosfirmes.entidades.Aluno;
import br.com.educandariopassosfirmes.entidades.Pessoa;
import br.com.educandariopassosfirmes.entidades.Responsavel;


/**
 * Servlet implementation class ServletAluno
 */
@WebServlet("/ServletAluno")
public class ServletAluno extends ServletGenerico {

	public static final String NM_JSP_CONSULTAR = "/aluno/consultarAluno.jsp";

	private static final String NM_JSP_INCLUIR_ALUNO = "/aluno/cadastrarAluno.jsp";

	private static final String NM_JSP_ALTERAR_ALUNO = "/aluno/alterarAluno.jsp";

	public static final String NM_EVENTO_RESPONSAVEL_CADASTRADO = "consultarResponsavelCadastrado";

	public static final String NM_PARAMETRO_CHAVE = "chave";
	
	//Parâmetros inclusão disciplina
	public static final String NM_PARAMETRO_ID_TURMA = "idTurma";
	public static final String NM_PARAMETRO_DS_TURMA = "descricaoTurma";
	public static final String NM_PARAMETRO_TURNO = "turno";
	public static final String NM_PARAMETRO_QT_MAX_ALUNOS = "qtMaxAlunos";
	public static final String NM_PARAMETRO_SELECT_TURMA = "selectTurma";
	public static final String NM_PARAMETRO_COLECAO_PESSOA = "colecaoPessoa";
	public static final String NM_PARAMETRO_ID_DISCIPLINA = "idDisciplina";
	public static final String NM_PARAMETRO_DS_DISCIPLINA = "descricaoDisciplina";
	public static final String NM_PARAMETRO_TAMANHO_COLECAO_DISCIPLINA = "tamanhoColecaoDisciplina";
	public static final String NM_PARAMETRO_COLECAO_PROFESSOR_DISCIPLINA = "colecaoProfessorDisciplina";
	
	public static final String NM_PARAMETRO_ALUNO = "aluno";
	public static final String NM_PARAMETRO_PESSOA_ALUNO = "pessoaAluno";
	public static final String NM_PARAMETRO_RESPONSAVEL = "responsavel";
	public static final String NM_PARAMETRO_PESSOA_RESPONSAVEL = "pessoaResponsavel";

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
	public static final String NM_PARAMETRO_CPF_RESP_CADASTRADO = "cpfRespCadastrado";
	public static final String NM_PARAMETRO_FORMACAO = "formacao";
	public static final String NM_PARAMETRO_ESTADO_CIVIL = "estadoCivil";
	public static final String NM_PARAMETRO_QT_DEPENDENTE = "qtDependente";
	public static final String NM_PARAMETRO_DT_ADMISSAO = "dtAdmissao";
	public static final String NM_PARAMETRO_CARGA_HORARIA = "cargaHoraria";
	public static final String NM_PARAMETRO_SALARIO = "salario";
	
	public static final String NM_PARAMETRO_PARENTESCO = "parentesco";
	public static final String NM_PARAMETRO_NECESSIDADE_ESPECIAL = "necEspecial";
	public static final String NM_PARAMETRO_DS_NECESSIDADE_ESPECIAL = "dsNecEspecial";
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
		} else if (acao != null
				&& acao.equalsIgnoreCase(NM_EVENTO_RESPONSAVEL_CADASTRADO)) {
			this.consultarResponsavelCadastrado(request, response);
		} else {
			// caso nao tenha nenhum evento, redireciona para a pagina de consulta
			this.redirecionarPagina(request, response, NM_JSP_CONSULTAR);
		}

	}

	@Override
	public void exibirInclusao(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
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
		String dsNecEspecial = "";
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
		dsNecEspecial = request.getParameter(NM_PARAMETRO_DS_NECESSIDADE_ESPECIAL);
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

		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		
		try {
			
			if(dtNascimento != null && !dtNascimento.equals("")) {
				dtNasc = formato.parse(dtNascimento);
			}
			
			if(dtMatricula != null && !dtMatricula.equals("")) {
				dtMatr = formato.parse(dtMatricula);
			}
			
			if(dtNascimentoResp != null && !dtNascimentoResp.equals("")) {
				dtNascResp = formato.parse(dtNascimentoResp);
			}
			
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
			if(renda != null && !renda.equals("")) {
				valorSalario = df.parse (renda).doubleValue();
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//monta a entidade pessoa para incluir o responsável
		Pessoa pessoa = new Pessoa();
		pessoa.setId(cpf);
		pessoa.setNome(nomeResp);
		
		if(dtNascResp != null) {
			pessoa.setDtNascimento(new java.sql.Date(dtNascResp.getTime()));
		}
		
		pessoa.setNaturalidade(naturalidadeResp);
		pessoa.setEndereco(enderecoResp);
		
		if(numeroResp != null && !numeroResp.equals("")) {
			pessoa.setNumero(Integer.valueOf(numeroResp));
		}
		
		pessoa.setBairro(bairroResp);
		pessoa.setCidade(cidadeResp);
		pessoa.setEstado(estadoResp);
		pessoa.setTelefone(telefone);
		pessoa.setIdentidade(identidade);

		//inclui em PESSOA 2x
		PessoaDAO pessoaDAO = new PessoaDAO();
		
		ArrayList<Pessoa> verificarSeExistePessoa = pessoaDAO.consultar(cpf, nome);
		
		if(verificarSeExistePessoa.isEmpty()) {
			pessoaDAO = new PessoaDAO();
			pessoaDAO.incluir(pessoa);
		}
		
		Responsavel responsavel = new Responsavel();
		responsavel.setId(cpf);
		responsavel.setParentesco(parentesco);
		responsavel.setEstadoCivil(estadoCivil);
		responsavel.setEscolaridade(escolaridade);
		responsavel.setProfissao(profissao);
		responsavel.setRenda(valorSalario);
		
		ResponsavelDAO responsavelDAO = new ResponsavelDAO();
		
		Responsavel verificarSeExisteResponsavel = responsavelDAO.consultar(cpf);
		
		if(verificarSeExisteResponsavel == null) {
			responsavelDAO = new ResponsavelDAO();
			responsavelDAO.cadastrar(responsavel);
		}
		
		//monta a entidade pessoa para incluir aluno
		pessoa = new Pessoa();
		pessoa.setId(matricula);
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
		
		pessoaDAO = new PessoaDAO();
		pessoaDAO.incluir(pessoa);
		
		Aluno aluno = new Aluno();
		aluno.setId(matricula);
		aluno.setIdResponsavel(cpf);
		aluno.setIdTurma(turma);
		
		if(dtMatr != null) {
			aluno.setDtMatricula(new java.sql.Date(dtMatr.getTime()));
		}
		
		aluno.setNecessidadeEspecial(necEspecial);
		
		if(necEspecial != null && necEspecial.equals("S")) {
			aluno.setDetalheNecessidadeEspecial(dsNecEspecial);			
		}else {
			aluno.setDetalheNecessidadeEspecial("");
		}
		
		aluno.setCdCarteiraEstudante(carteiraEstudante);
		aluno.setCdCertidaoNascimento(certidaoNasc);
		
		AlunoDAO alunoDAO = new AlunoDAO();
		alunoDAO.cadastrar(aluno);
		
		this.redirecionarPagina(request, response, NM_JSP_CONSULTAR);

	};

	@Override
	public void consultarTodos(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		ArrayList<LinkedHashMap<String, String>>colecaoAluno = new ArrayList<LinkedHashMap<String, String>>();
		
		// recupera os parametros do request
		String matricula = (String) request.getParameter(NM_PARAMETRO_MATRICULA);
		String nome = (String) request.getParameter(NM_PARAMETRO_NOME);
		String turma = (String) request.getParameter(NM_PARAMETRO_SELECT_TURMA);

		matricula = matricula.replace(".", "");
		
		ConsultaPrincipalAluno consulta = new ConsultaPrincipalAluno();
		
		colecaoAluno = consulta.consultar(matricula, nome, turma);
		
		request.setAttribute(NM_PARAMETRO_COLECAO_PESSOA, colecaoAluno);

		this.redirecionarPagina(request, response, NM_JSP_CONSULTAR);
	}

	@Override
	public void excluir(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// declara as variaveis
		String chave = "";
				
		// recupera os parametros do request
		chave = request.getParameter(NM_PARAMETRO_CHAVE);
		
		AlunoDAO alunoDAO = new AlunoDAO();
		ArrayList<Aluno> consultaAluno = alunoDAO.consultar(chave, "", "");
		Aluno aluno = consultaAluno.get(0);
		String idResponsavel = aluno.getIdResponsavel();
		
		alunoDAO = new AlunoDAO();
		alunoDAO.excluir(chave);
		
		PessoaDAO pessoaDAO = new PessoaDAO();
		pessoaDAO.excluir(chave);
		
		alunoDAO = new AlunoDAO();
		ArrayList<Aluno> consultaAlunoComMesmoResponsavel = alunoDAO.consultar("", "", idResponsavel);
		
		if(consultaAlunoComMesmoResponsavel.isEmpty()) {
			ResponsavelDAO responsavelDAO = new ResponsavelDAO();
			responsavelDAO.excluir(idResponsavel);
			
			pessoaDAO = new PessoaDAO();
			pessoaDAO.excluir(idResponsavel);
		}
				
		this.redirecionarPagina(request, response, NM_JSP_CONSULTAR);
	}

	@Override
	public void exibirAlteracao(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// declara as variaveis
		String chave = "";
		
		// recupera os parametros do request
		chave = request.getParameter(NM_PARAMETRO_CHAVE);
		
		AlunoDAO alunoDAO = new AlunoDAO();
		ArrayList<Aluno> consultaAluno = alunoDAO.consultar(chave, "", "");
		Aluno aluno = consultaAluno.get(0);
		String idResponsavel = aluno.getIdResponsavel();
		
		ResponsavelDAO responsavelDAO = new ResponsavelDAO();
		Responsavel responsavel = responsavelDAO.consultar(idResponsavel);
		
		PessoaDAO pessoaDAO = new PessoaDAO();
		ArrayList<Pessoa> consultaPessoaAluno = pessoaDAO.consultar(chave, "");
		Pessoa pessoaAluno = consultaPessoaAluno.get(0);
		
		pessoaDAO = new PessoaDAO();
		ArrayList<Pessoa> consultaPessoaResp = pessoaDAO.consultar(idResponsavel, "");
		Pessoa pessoaResp = consultaPessoaResp.get(0);
		
		request.setAttribute(NM_PARAMETRO_ALUNO, aluno);
		request.setAttribute(NM_PARAMETRO_PESSOA_ALUNO, pessoaAluno);
		request.setAttribute(NM_PARAMETRO_RESPONSAVEL, responsavel);
		request.setAttribute(NM_PARAMETRO_PESSOA_RESPONSAVEL, pessoaResp);
		
		this.redirecionarPagina(request, response, NM_JSP_ALTERAR_ALUNO);
	}

	@Override
	public void processarAlteracao(HttpServletRequest request,
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
		String dsNecEspecial = "";
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
		dsNecEspecial = request.getParameter(NM_PARAMETRO_DS_NECESSIDADE_ESPECIAL);
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

		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
				
		try {
			
			if(dtNascimento != null && !dtNascimento.equals("")) {
				dtNasc = formato.parse(dtNascimento);
			}
			
			if(dtMatricula != null && !dtMatricula.equals("")) {
				dtMatr = formato.parse(dtMatricula);
			}
			
			if(dtNascimentoResp != null && !dtNascimentoResp.equals("")) {
				dtNascResp = formato.parse(dtNascimentoResp);
			}
			
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
		pessoa.setNome(nomeResp);
		
		if(dtNascResp != null) {
			pessoa.setDtNascimento(new java.sql.Date(dtNascResp.getTime()));
		}
		
		pessoa.setNaturalidade(naturalidadeResp);
		pessoa.setEndereco(enderecoResp);
		
		if(numeroResp != null && !numeroResp.equals("")) {
			pessoa.setNumero(Integer.valueOf(numeroResp));
		}
		
		pessoa.setBairro(bairroResp);
		pessoa.setCidade(cidadeResp);
		pessoa.setEstado(estadoResp);
		pessoa.setTelefone(telefone);
		pessoa.setIdentidade(identidade);

		//inclui em PESSOA 2x
		PessoaDAO pessoaDAO = new PessoaDAO();
		pessoaDAO.alterar(pessoa);
				
		Responsavel responsavel = new Responsavel();
		responsavel.setId(cpf);
		responsavel.setParentesco(parentesco);
		responsavel.setEstadoCivil(estadoCivil);
		responsavel.setEscolaridade(escolaridade);
		responsavel.setProfissao(profissao);
		responsavel.setRenda(valorSalario);
				
		ResponsavelDAO responsavelDAO = new ResponsavelDAO();
		responsavelDAO.alterar(responsavel);

		//monta a entidade pessoa para incluir aluno
		pessoa = new Pessoa();
		pessoa.setId(matricula);
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
				
		pessoaDAO = new PessoaDAO();
		pessoaDAO.alterar(pessoa);
				
		Aluno aluno = new Aluno();
		aluno.setId(matricula);
		aluno.setIdResponsavel(cpf);
		aluno.setIdTurma(turma);
		
		if(dtMatr != null) {
			aluno.setDtMatricula(new java.sql.Date(dtMatr.getTime()));
		}
		
		aluno.setNecessidadeEspecial(necEspecial);
		
		if(necEspecial != null && necEspecial.equals("S")) {
			aluno.setDetalheNecessidadeEspecial(dsNecEspecial);			
		}else {
			aluno.setDetalheNecessidadeEspecial("");
		}
		
		aluno.setCdCarteiraEstudante(carteiraEstudante);
		aluno.setCdCertidaoNascimento(certidaoNasc);
				
		AlunoDAO alunoDAO = new AlunoDAO();
		alunoDAO.alterar(aluno);
				
		this.redirecionarPagina(request, response, NM_JSP_CONSULTAR);
	}
	
	public void consultarResponsavelCadastrado(HttpServletRequest request,
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
		String dsNecEspecial = "";
		String turma = "";
				
		String cpf = "";
				
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
		dsNecEspecial = request.getParameter(NM_PARAMETRO_DS_NECESSIDADE_ESPECIAL);
		turma = request.getParameter(NM_PARAMETRO_TURMA);

		// recupera os parametros do request responsável
		cpf = request.getParameter(NM_PARAMETRO_CPF_RESP_CADASTRADO);

		cpf = cpf.replace(".", "");
		cpf = cpf.replace("-", "");
		
		ResponsavelDAO responsavelDAO = new ResponsavelDAO();
		Responsavel responsavel = responsavelDAO.consultar(cpf);
		
		Pessoa pessoa = null;
		if(responsavel != null) {
			PessoaDAO pessoaDAO = new PessoaDAO();
			ArrayList<Pessoa> consultaPessoa = pessoaDAO.consultar(cpf, "");
			pessoa = consultaPessoa.get(0);
			
			request.setAttribute(NM_PARAMETRO_PARENTESCO, responsavel.getParentesco());
			request.setAttribute(NM_PARAMETRO_ESTADO_CIVIL, responsavel.getEstadoCivil());
			request.setAttribute(NM_PARAMETRO_ESCOLARIDADE, responsavel.getEscolaridade());
			request.setAttribute(NM_PARAMETRO_PROFISSAO, responsavel.getProfissao());
			
			double salario = responsavel.getRenda();

			BigDecimal valor = new BigDecimal(String.valueOf(salario));
			NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
			String salarioFormatado = nf.format(valor);
			salarioFormatado = salarioFormatado.replace("R$", "");
			salarioFormatado = salarioFormatado.trim();
			
			request.setAttribute(NM_PARAMETRO_RENDA, salarioFormatado);
		}
		
		if(pessoa != null) {
			
			request.setAttribute(NM_PARAMETRO_NOME_RESP, pessoa.getNome());
			request.setAttribute(NM_PARAMETRO_DT_NASCIMENTO_RESP, pessoa.getDtNascimento());
			request.setAttribute(NM_PARAMETRO_NATURALIDADE_RESP, pessoa.getNaturalidade());
			request.setAttribute(NM_PARAMETRO_ENDERECO_RESP, pessoa.getEndereco());
			request.setAttribute(NM_PARAMETRO_NUMERO_RESP, String.valueOf(pessoa.getNumero()));
			request.setAttribute(NM_PARAMETRO_BAIRRO_RESP, pessoa.getBairro());
			request.setAttribute(NM_PARAMETRO_CIDADE_RESP, pessoa.getCidade());
			request.setAttribute(NM_PARAMETRO_ESTADO_RESP, pessoa.getEstado());
			request.setAttribute(NM_PARAMETRO_TELEFONE, pessoa.getTelefone());
			request.setAttribute(NM_PARAMETRO_IDENTIDADE, pessoa.getIdentidade());
			request.setAttribute(NM_PARAMETRO_CPF, pessoa.getId());
		}
		
		request.setAttribute(NM_PARAMETRO_NOME, nome);
		request.setAttribute(NM_PARAMETRO_DT_NASCIMENTO, dtNascimento);
		request.setAttribute(NM_PARAMETRO_NATURALIDADE, naturalidade);
		request.setAttribute(NM_PARAMETRO_ENDERECO, endereco);
		request.setAttribute(NM_PARAMETRO_NUMERO, numero);
		request.setAttribute(NM_PARAMETRO_BAIRRO, bairro);
		request.setAttribute(NM_PARAMETRO_CIDADE, cidade);
		request.setAttribute(NM_PARAMETRO_ESTADO, estado);
		request.setAttribute(NM_PARAMETRO_CERTIDAO_NASC, certidaoNasc);
		request.setAttribute(NM_PARAMETRO_CARTEIRA_ESTUDANTE, carteiraEstudante);
		request.setAttribute(NM_PARAMETRO_NECESSIDADE_ESPECIAL, necEspecial);
		request.setAttribute(NM_PARAMETRO_DS_NECESSIDADE_ESPECIAL, dsNecEspecial);
		request.setAttribute(NM_PARAMETRO_TURMA, turma);
		
		this.redirecionarPagina(request, response, NM_JSP_INCLUIR_ALUNO);
	}

}

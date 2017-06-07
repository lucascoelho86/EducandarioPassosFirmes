package br.com.pacote1.controle;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpServletRequest;

import br.com.pacote1.entidades.Funcionario;
import br.com.pacote1.entidades.PerfilFuncao;
import br.com.pacote1.entidades.Pessoa;
import br.com.pacote1.entidades.PessoaPerfil;
import br.com.pacote1.entidades.Professor;
import br.com.pacote1.entidades.ProfessorDisciplina;
import br.com.pacote1.jdbc.FuncionarioDAO;
import br.com.pacote1.jdbc.PerfilFuncaoDAO;
import br.com.pacote1.jdbc.PessoaDAO;
import br.com.pacote1.jdbc.PessoaPerfilDAO;
import br.com.pacote1.jdbc.ProfessorDAO;
import br.com.pacote1.jdbc.ProfessorDisciplinaDAO;

public class MontagemAlteracaoFuncionarioCommand implements Command {
	
	private String proximo;
	
	public String execute(HttpServletRequest request) {
		
		proximo = "alteracaoFuncionario.jsp";
		Pessoa pessoa = new Pessoa();
		PessoaDAO pessoaDAO = new PessoaDAO();
		PessoaPerfil pessoaPerfil = new PessoaPerfil();
		PessoaPerfilDAO pessoaPerfilDAO = new PessoaPerfilDAO();
		Funcionario funcionario = new Funcionario();
		FuncionarioDAO FuncionarioDAO = new FuncionarioDAO();
		Professor professor = new Professor();
		ProfessorDAO professorDAO = new ProfessorDAO();
		
		String nome = request.getParameter("nome");
		String dataNascimento = request.getParameter("dataNascimento");
		String cpf = request.getParameter("cpf");
		String naturalidade = request.getParameter("naturalidade");
		String endereco = request.getParameter("endereço");
		String numero = request.getParameter("numero");
		String bairro = request.getParameter("bairro");
		String cidade = request.getParameter("cidade");
		String estado = request.getParameter("estado");
		String telefone = request.getParameter("telefone");
		String funcao = request.getParameter("funcao");
		String senha = request.getParameter("senha");		
		String portugues = request.getParameter("MatPort");
		String matematica = request.getParameter("MatMate");
		String ciencias = request.getParameter("MatCien");
		String geografia = request.getParameter("MatGeo");
		String ingles = request.getParameter("MatIng");
		String historia = request.getParameter("MatHist");
		String naturezaSociedade = request.getParameter("MatNatSoc");
		
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate data = LocalDate.parse(dataNascimento, formato);
		
		pessoa.setId(cpf);
		pessoa.setNome(nome);
		pessoa.setDtNascimento(data);
		pessoa.setNaturalidade(naturalidade);			
		pessoa.setEndereco(endereco);
		pessoa.setNumero(Integer.valueOf(numero));
		pessoa.setBairro(bairro);
		pessoa.setCidade(cidade);
		pessoa.setEstado(estado);
		pessoa.setTelefone(telefone);
			
		pessoaDAO.alterar(pessoa);
		
		pessoaPerfil.setId(cpf);
		pessoaPerfil.setId_perfil(buscarPerfilPelaFuncao(Integer.valueOf(funcao)));
		pessoaPerfilDAO.alterar(pessoaPerfil);
		
		funcionario.setId(cpf);
		funcionario.setSenha(senha);
		FuncionarioDAO.alterar(funcionario);
		
		if(funcao.equals("2") || funcao.equals("4")){
			professor.setId(cpf);
			professorDAO.alterar(professor);
			
			adicionarDisciplina(cpf, portugues, matematica, ciencias, geografia, ingles, historia, naturezaSociedade);
			
		}
		
		return proximo;
	}
	
	public void adicionarDisciplina(String pCpf, String pPortugues, String pMatematica, String pCiencias, String pGeografia,
			String pIngles, String pHistoria, String pMaturezaSociedade){
		ProfessorDisciplina professorDisciplina = new ProfessorDisciplina();
		ProfessorDisciplinaDAO professorDisciplinaDAO = new ProfessorDisciplinaDAO();

		professorDisciplina.setId_professor(pCpf);
		professorDisciplinaDAO.excluir(professorDisciplina);
		
		if(pPortugues != null){
			professorDisciplina.setId_professor(pCpf);
			professorDisciplina.setId_disciplina(Integer.valueOf(pPortugues));
			professorDisciplinaDAO.cadastrar(professorDisciplina);
		}
		
		if(pMatematica != null){
			professorDisciplina.setId_professor(pCpf);
			professorDisciplina.setId_disciplina(Integer.valueOf(pMatematica));
			professorDisciplinaDAO.cadastrar(professorDisciplina);
		}
		
		if(pCiencias != null){
			professorDisciplina.setId_professor(pCpf);
			professorDisciplina.setId_disciplina(Integer.valueOf(pCiencias));
			professorDisciplinaDAO.cadastrar(professorDisciplina);
		}
		
		if(pGeografia != null){
			professorDisciplina.setId_professor(pCpf);
			professorDisciplina.setId_disciplina(Integer.valueOf(pGeografia));
			professorDisciplinaDAO.cadastrar(professorDisciplina);
		}
		
		if(pIngles != null){
			professorDisciplina.setId_professor(pCpf);
			professorDisciplina.setId_disciplina(Integer.valueOf(pIngles));
			professorDisciplinaDAO.cadastrar(professorDisciplina);
		}
		
		if(pHistoria != null){
			professorDisciplina.setId_professor(pCpf);
			professorDisciplina.setId_disciplina(Integer.valueOf(pHistoria));
			professorDisciplinaDAO.cadastrar(professorDisciplina);
		}
		
		if(pMaturezaSociedade != null){
			professorDisciplina.setId_professor(pCpf);
			professorDisciplina.setId_disciplina(Integer.valueOf(pMaturezaSociedade));
			professorDisciplinaDAO.cadastrar(professorDisciplina);
		}
		
	}
	
	public int buscarPerfilPelaFuncao(int pFuncao){
		PerfilFuncaoDAO perfilFuncaoDAO = new PerfilFuncaoDAO();
		
		PerfilFuncao perfilFuncao = perfilFuncaoDAO.consultar(null, pFuncao);
		
		int perfil = perfilFuncao.getId_perfil();
		
		return perfil;
	}
	
}

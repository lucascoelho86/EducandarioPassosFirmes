package br.com.pacote1.controle;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpServletRequest;

import br.com.pacote1.entidades.Funcionario;
import br.com.pacote1.entidades.PerfilFuncao;
import br.com.pacote1.entidades.Pessoa;
import br.com.pacote1.entidades.PessoaPerfil;
import br.com.pacote1.jdbc.FuncionarioDAO;
import br.com.pacote1.jdbc.PerfilFuncaoDAO;
import br.com.pacote1.jdbc.PessoaDAO;
import br.com.pacote1.jdbc.PessoaPerfilDAO;

public class MontagemCadastroFuncionarioCommand implements Command {
	
	private String proximo;
	
	public String execute(HttpServletRequest request) {
		
		proximo = "cadastroFuncionario.jsp";
		Pessoa pessoa = new Pessoa();
		PessoaDAO pessoaDAO = new PessoaDAO();
		PessoaPerfil pessoaPerfil = new PessoaPerfil();
		PessoaPerfilDAO pessoaPerfilDAO = new PessoaPerfilDAO();
		Funcionario funcionario = new Funcionario();
		FuncionarioDAO FuncionarioDAO = new FuncionarioDAO();
		
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
			
		pessoaDAO.cadastrar(pessoa);
		
		pessoaPerfil.setId(cpf);
		pessoaPerfil.setId_perfil(buscarPerfilPelaFuncao(Integer.valueOf(funcao)));
		pessoaPerfilDAO.cadastrar(pessoaPerfil);
		
		funcionario.setId(cpf);
		funcionario.setSenha(senha);
		FuncionarioDAO.cadastrar(funcionario);
		
		return proximo;
	}
	
	public int buscarPerfilPelaFuncao(int pFuncao){
		PerfilFuncaoDAO perfilFuncaoDAO = new PerfilFuncaoDAO();
		
		PerfilFuncao perfilFuncao = perfilFuncaoDAO.consultar(pFuncao);
		
		int perfil = perfilFuncao.getId_perfil();
		
		return perfil;
	}
	
}

package br.com.pacote1.teste;

import java.util.List;

import br.com.pacote1.entidades.Pessoa;
import br.com.pacote1.jdbc.PessoaDAO;

public class TestePessoaDAO {

	public static void main(String[] args) {
		
//		testeCadastrar();
//		testeAlterar();
//		testeExcluir();
		testeBuscarTodos();
		
	}
	
	private static void testeCadastrar(){
		Pessoa pessoa = new Pessoa();
		
		pessoa.setId(1);
		pessoa.setNome("Lucas Nascimento");
		pessoa.setLogin("lcn");
		pessoa.setSenha("123");
		
		PessoaDAO pessoaDAO = new PessoaDAO();
		
		pessoaDAO.cadastrar(pessoa);
	}
	
	private static void testeAlterar(){
		Pessoa pessoa = new Pessoa();
		
		pessoa.setId(1);
		pessoa.setNome("Lucas Coelho");
		pessoa.setLogin("lcdn");
		pessoa.setSenha("321");
		
		PessoaDAO pessoaDAO = new PessoaDAO();
		
		pessoaDAO.alterar(pessoa);
	}
	
	private static void testeExcluir(){
		Pessoa pessoa = new Pessoa();
		
		pessoa.setId(1);
		
		PessoaDAO pessoaDAO = new PessoaDAO();
		
		pessoaDAO.excluir(pessoa);
	}
	
	private static void testeBuscarTodos(){
		PessoaDAO pessoaDao = new PessoaDAO();
		List<Pessoa> listaResultado = pessoaDao.buscaTodos();
		
		for(Pessoa p: listaResultado){
			System.out.println(p.getId() + " " + p.getNome() + " " + p.getLogin() + " " + p.getSenha());
		}
	}

}

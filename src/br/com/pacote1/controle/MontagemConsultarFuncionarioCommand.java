package br.com.pacote1.controle;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import br.com.pacote1.entidades.Funcionario;
import br.com.pacote1.entidades.Pessoa;
import br.com.pacote1.entidades.PessoaPerfil;
import br.com.pacote1.entidades.Professor;
import br.com.pacote1.jdbc.FuncionarioDAO;
import br.com.pacote1.jdbc.PessoaDAO;
import br.com.pacote1.jdbc.PessoaPerfilDAO;
import br.com.pacote1.jdbc.ProfessorDAO;

public class MontagemConsultarFuncionarioCommand implements Command {
	
	private String proximo;
	
	public String execute(HttpServletRequest request) {
		
		proximo = "consultarFuncionario.jsp";
		
		Pessoa pessoa = new Pessoa();
		PessoaDAO pessoaDAO = new PessoaDAO();
		PessoaPerfil pessoaPerfil = new PessoaPerfil();
		PessoaPerfilDAO pessoaPerfilDAO = new PessoaPerfilDAO();
		Funcionario funcionario = new Funcionario();
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		Professor professor = new Professor();
		ProfessorDAO professorDAO = new ProfessorDAO();
		
		List<Pessoa> listPessoa = new ArrayList<Pessoa>();
		List<Pessoa> listPessoaFuncionario = new ArrayList<Pessoa>();
		
		String chave = request.getParameter("chave");
		String botaoAlterar = request.getParameter("botaoAlterar");
		String cpf = request.getParameter("cpf");
		String nome = request.getParameter("nome");
		
		if(cpf != null && !cpf.equals("")){
			funcionario = funcionarioDAO.consultar(cpf);
		}else if(nome != null && !nome.equals("")){
			listPessoa = pessoaDAO.consultar(null, nome);
			
			for(int x = 0; x<listPessoa.size(); x++){
				pessoa = listPessoa.get(x);
					
				String idPessoa = pessoa.getId();
					
				funcionario = funcionarioDAO.consultar(idPessoa);
					
				if(funcionario != null){
					listPessoaFuncionario.add(pessoa);
				}
			}
				
		}
			
		
		
		return proximo;
	}
	
}

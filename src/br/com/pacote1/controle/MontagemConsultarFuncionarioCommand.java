package br.com.pacote1.controle;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import br.com.pacote1.entidades.Funcao;
import br.com.pacote1.entidades.Funcionario;
import br.com.pacote1.entidades.PerfilFuncao;
import br.com.pacote1.entidades.Pessoa;
import br.com.pacote1.entidades.PessoaPerfil;
import br.com.pacote1.entidades.ProfessorDisciplina;
import br.com.pacote1.jdbc.FuncaoDAO;
import br.com.pacote1.jdbc.FuncionarioDAO;
import br.com.pacote1.jdbc.PerfilFuncaoDAO;
import br.com.pacote1.jdbc.PessoaDAO;
import br.com.pacote1.jdbc.PessoaPerfilDAO;
import br.com.pacote1.jdbc.ProfessorDisciplinaDAO;

public class MontagemConsultarFuncionarioCommand implements Command {
	
	private String proximo;
	
	public String execute(HttpServletRequest request) {
		
		proximo = "consultarFuncionario.jsp";
		
		ArrayList<String> colecao = new ArrayList<String>();
		
		Pessoa pessoa = new Pessoa();
		PessoaDAO pessoaDAO = new PessoaDAO();
		PessoaPerfil pessoaPerfil = new PessoaPerfil();
		PessoaPerfilDAO pessoaPerfilDAO = new PessoaPerfilDAO();
		PerfilFuncao perfilFuncao = new PerfilFuncao();
		PerfilFuncaoDAO perfilFuncaoDAO = new PerfilFuncaoDAO();
		Funcao funcao = new Funcao();
		FuncaoDAO funcaoDAO = new FuncaoDAO();
		Funcionario funcionario = null;
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		List<ProfessorDisciplina> listProfessorDisciplina = new ArrayList<ProfessorDisciplina>();
		ProfessorDisciplinaDAO professorDisciplinaDAO = new ProfessorDisciplinaDAO();
		
		List<Pessoa> listPessoa = new ArrayList<Pessoa>();
		List<Pessoa> listPessoaFuncionario = new ArrayList<Pessoa>();
		
		String chave = request.getParameter("chave");
		String botaoAlterar = request.getParameter("botaoAlterar");
		String botaoExcluir = request.getParameter("botaoExcluir");
		String cpf = request.getParameter("cpf");
		String nome = request.getParameter("nome");
		
		if(chave != null && botaoAlterar != null){
			proximo = "alteracaoFuncionario.jsp";
			
			String arrayChave[] = chave.split(";");
			
			listPessoa = pessoaDAO.consultar(arrayChave[0], null);			
			funcionario = funcionarioDAO.consultar(arrayChave[0]);
			String idFuncao = arrayChave[2];
						
			if(idFuncao.equals("2")){
				listProfessorDisciplina = professorDisciplinaDAO.consultar(arrayChave[0]);
			}
			
			request.setAttribute("listPessoa", listPessoa);
			request.setAttribute("listProfessorDisciplina", listProfessorDisciplina);
			request.setAttribute("funcionario", funcionario);
			request.setAttribute("idFuncao", idFuncao);
		
		}else if(chave != null && botaoExcluir != null){
			proximo = "excluirFuncionario.jsp";
			
			String arrayChave[] = chave.split(";");
			
			request.setAttribute("chave",  arrayChave);
			
		}else{
			
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
				
				funcionario = null;
					
			}
			
			if(funcionario != null || !listPessoaFuncionario.isEmpty()){
				
				if(funcionario != null){
					listPessoa = pessoaDAO.consultar(cpf, nome);
					pessoa = listPessoa.get(0);
					pessoaPerfil = pessoaPerfilDAO.consultar(cpf);
					perfilFuncao = perfilFuncaoDAO.consultar(pessoaPerfil.getId_perfil(), null);
					funcao = funcaoDAO.consultar(perfilFuncao.getId_funcao());
					String dadosFunc = pessoa.getId() + ";" + pessoa.getNome() + ";" + funcao.getDs_funcao() + ";" + funcao.getId_funcao();
					colecao.add(dadosFunc);
					
				}else if(!listPessoaFuncionario.isEmpty()){
					for(int f = 0; f < listPessoaFuncionario.size(); f++){
						pessoa = listPessoaFuncionario.get(f);
						
						pessoaPerfil = pessoaPerfilDAO.consultar(pessoa.getId());
						perfilFuncao = perfilFuncaoDAO.consultar(pessoaPerfil.getId_perfil(), null);
						funcao = funcaoDAO.consultar(perfilFuncao.getId_funcao());
						
						String dadosFunc = pessoa.getId() + ";" + pessoa.getNome() + ";" + funcao.getDs_funcao() + ";" + funcao.getId_funcao();
						colecao.add(dadosFunc);
						
					}
					
				}
				
			}else{
				listPessoa = pessoaDAO.consultar(null, null);
				
				for(int f = 0; f < listPessoa.size(); f++){
					pessoa = listPessoa.get(f);
					
					funcionario = funcionarioDAO.consultar(pessoa.getId());
					
					if(funcionario != null){
						pessoaPerfil = pessoaPerfilDAO.consultar(pessoa.getId());
						perfilFuncao = perfilFuncaoDAO.consultar(pessoaPerfil.getId_perfil(), null);
						funcao = funcaoDAO.consultar(perfilFuncao.getId_funcao());
						
						String dadosFunc = pessoa.getId() + ";" + pessoa.getNome() + ";" + funcao.getDs_funcao() + ";" + funcao.getId_funcao();
						colecao.add(dadosFunc);					
					}
					
				}
			}
				
			request.setAttribute("dadosFunc", colecao);
			request.setAttribute("cpf", cpf);
			request.setAttribute("nome", nome);
			
		}
		
		return proximo;
	}
	
}

package br.com.educandariopassosfirmes.servlet;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import br.com.educandariopassosfirmes.dao.FuncaoDAO;
import br.com.educandariopassosfirmes.dao.FuncionarioDAO;
import br.com.educandariopassosfirmes.dao.PerfilFuncaoDAO;
import br.com.educandariopassosfirmes.dao.PessoaDAO;
import br.com.educandariopassosfirmes.dao.PessoaPerfilDAO;
import br.com.educandariopassosfirmes.dao.ProfessorDisciplinaDAO;
import br.com.educandariopassosfirmes.entidades.Funcao;
import br.com.educandariopassosfirmes.entidades.Funcionario;
import br.com.educandariopassosfirmes.entidades.PerfilFuncao;
import br.com.educandariopassosfirmes.entidades.Pessoa;
import br.com.educandariopassosfirmes.entidades.PessoaPerfil;
import br.com.educandariopassosfirmes.entidades.ProfessorDisciplina;

public class ServletMontagemConsultarFuncionario extends Servlet {
	
	private String proximo;
	
	public String execute(HttpServletRequest request) {
		
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
		String botaoVoltar = request.getParameter("menu");
		String botaoConsultar = request.getParameter("consultar");
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
			
		}else if(botaoVoltar != null){
			proximo = "home.jsp";
			
		}else if(botaoConsultar != null){
			proximo = "consultarFuncionario.jsp";
			
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
			
		}else if(chave == null){
			proximo = "consultarFuncionario.jsp";
		}
		
		return proximo;
	}
	
}

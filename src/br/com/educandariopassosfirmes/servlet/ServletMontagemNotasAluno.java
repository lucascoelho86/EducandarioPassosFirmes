package br.com.educandariopassosfirmes.servlet;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import br.com.educandariopassosfirmes.dao.NotaDAO;
import br.com.educandariopassosfirmes.dao.PessoaDAO;
import br.com.educandariopassosfirmes.dao.TurmaAlunoDAO;
import br.com.educandariopassosfirmes.dao.TurmaDisciplinaDAO;
import br.com.educandariopassosfirmes.entidades.Nota;
import br.com.educandariopassosfirmes.entidades.Pessoa;
import br.com.educandariopassosfirmes.entidades.TurmaAluno;
import br.com.educandariopassosfirmes.entidades.TurmaDisciplina;

public class ServletMontagemNotasAluno extends Servlet {
	
	private String proximo;
	
	public String execute(HttpServletRequest request) {
		
		proximo = "cadastroNotas.jsp";
		PessoaDAO pessoaDAO = new PessoaDAO();
		List<Pessoa> listPessoa = new ArrayList<Pessoa>();
		List<Nota> listNotas = new ArrayList<Nota>();
		List<Nota> listNotasAux = new ArrayList<Nota>();
		List<Pessoa> listPessoaAluno = new ArrayList<Pessoa>();
		TurmaAlunoDAO turmaAlunoDAO = new TurmaAlunoDAO();
		List<TurmaAluno> listTurmaAluno = new ArrayList<TurmaAluno>();
		TurmaDisciplinaDAO turmaDisciplinaDAO = new TurmaDisciplinaDAO();
		List<TurmaDisciplina> listDisciplinaTurma = new ArrayList<TurmaDisciplina>();
		Nota nota = null;
		NotaDAO notaDAO = new NotaDAO();
		
		String matricula = request.getParameter("matricula");
		String nome = request.getParameter("nome");
		String turma = request.getParameter("turma");
		String disciplina = request.getParameter("disciplina");
		String unidade = request.getParameter("unidade");
		String botaoSalvar = request.getParameter("botaoSalvar");
		String botaoAlterar = request.getParameter("botaoAlterar");
		String botaoExcluir = request.getParameter("botaoExcluir");
		String botaoVoltar = request.getParameter("botaoVoltar");
		String contador = request.getParameter("contador");
				
		listDisciplinaTurma = turmaDisciplinaDAO.consultar(Integer.valueOf(turma), Integer.valueOf(disciplina));
		
		if(botaoSalvar != null && !botaoSalvar.equals("")){
			
			for(int x = 0; x < Integer.valueOf(contador); x++){
				nota = new Nota();
				nota.setIdAluno(request.getParameter("chave" + x));
				nota.setIdDisciplina(Integer.valueOf(disciplina));
				nota.setUnidade(Integer.valueOf(unidade));
				
				String teste = request.getParameter("teste" + x);
				
				if(teste != null && !teste.equals("")){
					nota.setIdTpNota(Integer.valueOf("1"));
					nota.setNota(Double.valueOf(teste));
					notaDAO.cadastrar(nota);
				}
				
				String prova = request.getParameter("prova" + x);
				
				if(prova != null && !prova.equals("")){
					nota.setIdTpNota(Integer.valueOf("2"));
					nota.setNota(Double.valueOf(prova));
					notaDAO.cadastrar(nota);
				}

				String trabalho = request.getParameter("trabalho" + x);
				
				if(trabalho != null && !trabalho.equals("")){
					nota.setIdTpNota(Integer.valueOf("3"));
					nota.setNota(Double.valueOf(trabalho));
					notaDAO.cadastrar(nota);
				}
				
			}
		}else if(botaoAlterar != null && !botaoAlterar.equals("")){
			
			for(int x = 0; x < Integer.valueOf(contador); x++){
				notaDAO.excluir(request.getParameter("chave" + x));
			}
			
			for(int x = 0; x < Integer.valueOf(contador); x++){
				nota = new Nota();
				nota.setIdAluno(request.getParameter("chave" + x));
				nota.setIdDisciplina(Integer.valueOf(disciplina));
				nota.setUnidade(Integer.valueOf(unidade));
				
				String teste = request.getParameter("teste" + x);
				
				if(teste != null && !teste.equals("")){
					nota.setIdTpNota(Integer.valueOf("1"));
					nota.setNota(Double.valueOf(teste));
					notaDAO.cadastrar(nota);
				}
				
				String prova = request.getParameter("prova" + x);
				
				if(prova != null && !prova.equals("")){
					nota.setIdTpNota(Integer.valueOf("2"));
					nota.setNota(Double.valueOf(prova));
					notaDAO.cadastrar(nota);
				}

				String trabalho = request.getParameter("trabalho" + x);
				
				if(trabalho != null && !trabalho.equals("")){
					nota.setIdTpNota(Integer.valueOf("3"));
					nota.setNota(Double.valueOf(trabalho));
					notaDAO.cadastrar(nota);
				}
				
			}
			
		}else if(botaoExcluir != null && !botaoExcluir.equals("")){
			for(int x = 0; x < Integer.valueOf(contador); x++){
				notaDAO.excluir(request.getParameter("chave" + x));
			}
			
		}else if(botaoVoltar != null && !botaoVoltar.equals("")){
			proximo = "home.jsp";
		}else{
						
			if(!listDisciplinaTurma.isEmpty()){
				listTurmaAluno = turmaAlunoDAO.consultar(Integer.valueOf(turma), matricula);
				
				if(matricula != null && !matricula.equals("") || nome != null && !nome.equals("")){
					listPessoa = pessoaDAO.consultar(matricula, nome);
					
					if(!listPessoa.isEmpty()){
						Pessoa pessoa = listPessoa.get(0);
						listNotas = notaDAO.consultar(pessoa.getId(), Integer.valueOf(unidade));
					}
				}else{
					Iterator<TurmaAluno> itTurmaAluno = listTurmaAluno.iterator();
					
					while(itTurmaAluno.hasNext()){
						TurmaAluno turmaAluno = itTurmaAluno.next();
						
						listPessoaAluno = pessoaDAO.consultar(turmaAluno.getIdAluno(), null);
						
						listNotasAux = notaDAO.consultar(turmaAluno.getIdAluno(), Integer.valueOf(unidade));
						
						if(!listNotasAux.isEmpty()){
							for(int x = 0; x < listNotasAux.size(); x++){
								nota = listNotasAux.get(x);
								
								listNotas.add(nota);
							}
						}
						
						listPessoa.add(listPessoaAluno.get(0));
						
						
					}
				}
				
			}
		}
		
		
		request.setAttribute("listPessoa", listPessoa);
		request.setAttribute("listNotas", listNotas);
		request.setAttribute("matricula", matricula);
		request.setAttribute("nome", nome);
		request.setAttribute("turma", turma);
		request.setAttribute("disciplina", disciplina);
		request.setAttribute("unidade", unidade);
		
		return proximo;
	}
	
}

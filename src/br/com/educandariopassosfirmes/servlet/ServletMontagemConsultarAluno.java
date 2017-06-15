package br.com.educandariopassosfirmes.servlet;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import br.com.educandariopassosfirmes.dao.AlunoDAO;
import br.com.educandariopassosfirmes.dao.PessoaDAO;
import br.com.educandariopassosfirmes.dao.ResponsavelAlunoDAO;
import br.com.educandariopassosfirmes.dao.TurmaAlunoDAO;
import br.com.educandariopassosfirmes.dao.TurmaDAO;
import br.com.educandariopassosfirmes.documentos.ExcRepositorio;
import br.com.educandariopassosfirmes.documentos.GerarBoletim;
import br.com.educandariopassosfirmes.entidades.Aluno;
import br.com.educandariopassosfirmes.entidades.Pessoa;
import br.com.educandariopassosfirmes.entidades.ResponsavelAluno;
import br.com.educandariopassosfirmes.entidades.Turma;
import br.com.educandariopassosfirmes.entidades.TurmaAluno;
import net.sf.jasperreports.engine.JasperPrint;

public class ServletMontagemConsultarAluno extends Servlet {
	
	private String proximo;
	
	public String execute(HttpServletRequest request){
		
		Aluno aluno = null;
		AlunoDAO alunoDAO = new AlunoDAO();
		Pessoa pessoa = new Pessoa();
		PessoaDAO pessoaDAO = new PessoaDAO();
		List<Pessoa> listPessoa = new ArrayList<Pessoa>();
		List<Pessoa> listPessoaResponsavel = new ArrayList<Pessoa>();
		List<Pessoa> listPessoaAluno = new ArrayList<Pessoa>();
		List<Pessoa> listPessoaAlunoPeloNome = new ArrayList<Pessoa>();
		TurmaAluno turmaAluno = new TurmaAluno();
		TurmaAlunoDAO turmaAlunoDAO = new TurmaAlunoDAO();
		List<TurmaAluno> listTurma = new ArrayList<TurmaAluno>();
		List<TurmaAluno> listTurmaAluno = new ArrayList<TurmaAluno>();
		Turma turma = null;
		TurmaDAO turmaDAO = new TurmaDAO();
		ResponsavelAluno responsavelAluno = null;
		List<ResponsavelAluno> listResponsavelAluno = new ArrayList<ResponsavelAluno>();
		ResponsavelAlunoDAO responsavelAlunoDAO = new ResponsavelAlunoDAO();
		
		String chave = request.getParameter("chave");
		String botaoAlterar = request.getParameter("botaoAlterar");
		String botaoExcluir = request.getParameter("botaoExcluir");
		String botaoVoltar = request.getParameter("menu");
		String botaoRelatorio = request.getParameter("botaoRelatorio");
		String botaoConsultar = request.getParameter("botaoConsultar");
		String matricula = request.getParameter("matricula");
		String nome = request.getParameter("nome");
		
		if(chave != null && botaoAlterar != null){
			proximo = "alteracaoAluno.jsp";
			
			String arrayChave[] = chave.split(";");
			
			listPessoa = pessoaDAO.consultar(arrayChave[0], null);
			
			turma = turmaDAO.consultar(Integer.valueOf(arrayChave[2]));
			
			listResponsavelAluno = responsavelAlunoDAO.consultar(arrayChave[0]);
			
			responsavelAluno = listResponsavelAluno.get(0);
			
			listPessoaResponsavel = pessoaDAO.consultar(responsavelAluno.getIdResponsavel(), null);
			
			request.setAttribute("listPessoa", listPessoa);
			request.setAttribute("listResponsavel", listPessoaResponsavel);
			request.setAttribute("turma", turma);
		
		}else if(chave != null && botaoExcluir != null){
			proximo = "excluirAluno.jsp";
			
			String arrayChave[] = chave.split(";");
			
			request.setAttribute("chave",  arrayChave);
		
		}else if(chave != null && botaoRelatorio != null){
			proximo = "consultarAluno.jsp";
			GerarBoletim rep = new GerarBoletim();
			JasperPrint relat;
			
			try {
				rep.inserir(chave);
				relat = rep.gerar(chave);
			} catch (ExcRepositorio e) {
				e.printStackTrace();
			}
			
		}else if(botaoVoltar != null){
			proximo = "home.jsp";
		
		}else if(botaoConsultar != null){
			
			proximo = "consultarAluno.jsp";
			
			if(matricula != null && !matricula.equals("")){
				aluno = alunoDAO.consultar(matricula);			
			}else if(nome != null && !nome.equals("")){
				listPessoa = pessoaDAO.consultar(null, nome);
				
				for(int x = 0; x<listPessoa.size(); x++){
					pessoa = listPessoa.get(x);
					
					String idPessoa = pessoa.getId();
					
					aluno = alunoDAO.consultar(idPessoa);
					
					if(aluno != null){
						listPessoaAluno.add(pessoa);
					}
				}
				
				aluno = null;
			}
			
			if(aluno != null || !listPessoaAluno.isEmpty()){
				
				if(aluno != null){
					listPessoa = pessoaDAO.consultar(matricula, nome);				
					listTurma = turmaAlunoDAO.consultar(null, matricula);
					request.setAttribute("listPessoa", listPessoa);
					request.setAttribute("listTurma", listTurma);
				}else if(!listPessoaAluno.isEmpty()){
					for(int alu = 0; alu < listPessoaAluno.size(); alu++){
						pessoa = listPessoaAluno.get(alu);
						
						listTurma = turmaAlunoDAO.consultar(null, pessoa.getId());
						
						if(!listTurma.isEmpty()){
							turmaAluno = listTurma.get(0);
							listTurmaAluno.add(turmaAluno);
							listPessoaAlunoPeloNome.add(pessoa);
						}					
						
					}
					request.setAttribute("listPessoa", listPessoaAlunoPeloNome);
					request.setAttribute("listTurma", listTurmaAluno);				
				}
				
			}else{
				
				listPessoa = pessoaDAO.consultar(null, null);
				
				for(int x = 0; x<listPessoa.size(); x++){
					pessoa = listPessoa.get(x);
					
					String idPessoa = pessoa.getId();
					
					aluno = alunoDAO.consultar(idPessoa);
					
					if(aluno != null){
						listPessoaAluno.add(pessoa);
					}
				}
				
				if(!listPessoaAluno.isEmpty()){
					for(int alu = 0; alu < listPessoaAluno.size(); alu++){
						pessoa = listPessoaAluno.get(alu);
						
						listTurma = turmaAlunoDAO.consultar(null, pessoa.getId());
						
						if(!listTurma.isEmpty()){
							turmaAluno = listTurma.get(0);
							listTurmaAluno.add(turmaAluno);
						}					
						
					}
					
				}
				
				request.setAttribute("listPessoa", listPessoaAluno);
				request.setAttribute("listTurma", listTurmaAluno);
				
			}
			
			request.setAttribute("matricula", matricula);
			request.setAttribute("nome", nome);		
		}else if(chave == null){
			proximo = "consultarAluno.jsp";
		}
		
		
		return proximo;
	}
	
}

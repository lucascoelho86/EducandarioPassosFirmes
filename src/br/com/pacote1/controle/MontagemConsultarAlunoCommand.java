package br.com.pacote1.controle;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import br.com.pacote1.entidades.Aluno;
import br.com.pacote1.entidades.Pessoa;
import br.com.pacote1.entidades.ResponsavelAluno;
import br.com.pacote1.entidades.Turma;
import br.com.pacote1.entidades.TurmaAluno;
import br.com.pacote1.jdbc.AlunoDAO;
import br.com.pacote1.jdbc.PessoaDAO;
import br.com.pacote1.jdbc.ResponsavelAlunoDAO;
import br.com.pacote1.jdbc.TurmaAlunoDAO;
import br.com.pacote1.jdbc.TurmaDAO;

public class MontagemConsultarAlunoCommand implements Command {
	
	private String proximo;
	
	public String execute(HttpServletRequest request) {
		
		proximo = "consultarAluno.jsp";
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
		ResponsavelAlunoDAO responsavelAlunoDAO = new ResponsavelAlunoDAO();
		
		String chave = request.getParameter("chave");
		String botaoAlterar = request.getParameter("botaoAlterar");
		String matricula = request.getParameter("matricula");
		String nome = request.getParameter("nome");
		
		if(chave != null && botaoAlterar != null){
			proximo = "alteracaoAluno.jsp";
			
			String arrayChave[] = chave.split(";");
			
			listPessoa = pessoaDAO.consultar(arrayChave[0], null);
			
			turma = turmaDAO.consultar(Integer.valueOf(arrayChave[2]));
			
			responsavelAluno = responsavelAlunoDAO.consultar(arrayChave[0]);
			
			listPessoaResponsavel = pessoaDAO.consultar(responsavelAluno.getIdResponsavel(), null);
			
			request.setAttribute("listPessoa", listPessoa);
			request.setAttribute("listResponsavel", listPessoaResponsavel);
			request.setAttribute("turma", turma);
			
		}else{
			
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
		}
		
		
		return proximo;
	}
	
}

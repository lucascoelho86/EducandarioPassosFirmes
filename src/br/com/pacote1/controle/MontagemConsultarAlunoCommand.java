package br.com.pacote1.controle;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.swing.JOptionPane;

import br.com.pacote1.documentos.ExcRepositorio;
import br.com.pacote1.documentos.GerarBoletim;
import br.com.pacote1.entidades.Aluno;
import br.com.pacote1.entidades.Pessoa;
import br.com.pacote1.entidades.ResponsavelAluno;
import br.com.pacote1.entidades.Turma;
import br.com.pacote1.entidades.TurmaAluno;
import br.com.pacote1.jdbc.AlunoDAO;
import br.com.pacote1.jdbc.Conexao;
import br.com.pacote1.jdbc.PessoaDAO;
import br.com.pacote1.jdbc.ResponsavelAlunoDAO;
import br.com.pacote1.jdbc.TurmaAlunoDAO;
import br.com.pacote1.jdbc.TurmaDAO;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

public class MontagemConsultarAlunoCommand implements Command {
	
	private String proximo;
	
	public String execute(HttpServletRequest request){
		
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
		List<ResponsavelAluno> listResponsavelAluno = new ArrayList<ResponsavelAluno>();
		ResponsavelAlunoDAO responsavelAlunoDAO = new ResponsavelAlunoDAO();
		
		String chave = request.getParameter("chave");
		String botaoAlterar = request.getParameter("botaoAlterar");
		String botaoExcluir = request.getParameter("botaoExcluir");
		String botaoRelatorio = request.getParameter("botaoRelatorio");
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
			GerarBoletim rep = new GerarBoletim();
			JasperPrint relat;
			
			try {
				rep.inserir(chave);
				relat = rep.gerar(tratarColecaoRelatorio());
			} catch (ExcRepositorio e) {
				e.printStackTrace();
			}
			
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
	
	public HashMap<String, Object> tratarColecaoRelatorio(){
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		
		hashMap.put("matricula", "20170100001");
		hashMap.put("nome", "Lucas");
		hashMap.put("turma", "Maternal");
		hashMap.put("disciplina", "Matemática");
		hashMap.put("notaTeste", Double.valueOf(10.0));
		hashMap.put("notaProva", Double.valueOf(10.0));
		hashMap.put("notaTrabalho", Double.valueOf(10.0));
		hashMap.put("notaFinal", Double.valueOf(10.0));
		
		return hashMap;
	}
	
}

package br.com.educandariopassosfirmes.servlet;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpServletRequest;

import br.com.educandariopassosfirmes.dao.PessoaDAO;
import br.com.educandariopassosfirmes.dao.TurmaAlunoDAO;
import br.com.educandariopassosfirmes.entidades.Pessoa;
import br.com.educandariopassosfirmes.entidades.TurmaAluno;

public class ServletMontagemAlteracaoAluno extends Servlet {
	
	private String proximo;
	
	public String execute(HttpServletRequest request) {
		
		proximo = "sucesso.jsp";
		Pessoa pessoa = new Pessoa();
		PessoaDAO pessoaDAO = new PessoaDAO();
		TurmaAluno turmaAluno = new TurmaAluno();
		TurmaAlunoDAO turmaAlunoDAO = new TurmaAlunoDAO();
		
		String nome = request.getParameter("nome");
		String matricula = request.getParameter("matricula");
		String dataNascimento = request.getParameter("dataNascimento");
		String naturalidade = request.getParameter("naturalidade");
		String endereco = request.getParameter("endereço");
		String numero = request.getParameter("numero");
		String bairro = request.getParameter("bairro");
		String cidade = request.getParameter("cidade");
		String estado = request.getParameter("estado");
		String telefone = request.getParameter("telefone");
		String nomeResponsavel = request.getParameter("nomeResponsavel");
		String cpfResponsavel = request.getParameter("cpfResponsavel");
		String turma = request.getParameter("turma");
		String idTurmaAtual = request.getParameter("idTurmaAtual");
		String dataNascimentoResp = request.getParameter("dataNascimentoResp");
		String naturalidadeResp = request.getParameter("naturalidadeResp");
		
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate data = LocalDate.parse(dataNascimento, formato);
		LocalDate dataResp = LocalDate.parse(dataNascimentoResp, formato);
		
		for(int x = 0; x < 2; x++){
			if(x == 0){
				pessoa.setId(matricula);
				pessoa.setNome(nome);
				pessoa.setDtNascimento(data);
				pessoa.setNaturalidade(naturalidade);
			}else{
				pessoa.setId(cpfResponsavel);
				pessoa.setNome(nomeResponsavel);
				pessoa.setDtNascimento(dataResp);
				pessoa.setNaturalidade(naturalidadeResp);
			}
			
			pessoa.setEndereco(endereco);
			pessoa.setNumero(Integer.valueOf(numero));
			pessoa.setBairro(bairro);
			pessoa.setCidade(cidade);
			pessoa.setEstado(estado);
			pessoa.setTelefone(telefone);
			
			pessoaDAO.alterar(pessoa);
			
		}
				
		if(!turma.equals("0") && !idTurmaAtual.equals(turma)){
			turmaAluno.setIdTurma(Integer.valueOf(turma));
			turmaAluno.setIdAluno(matricula);
			turmaAlunoDAO.alterar(turmaAluno);			
		}
		
		
		return proximo;
	}
	
}

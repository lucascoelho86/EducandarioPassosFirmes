package br.com.pacote1.controle;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import br.com.pacote1.entidades.Aluno;
import br.com.pacote1.entidades.Pessoa;
import br.com.pacote1.entidades.PessoaPerfil;
import br.com.pacote1.entidades.Responsavel;
import br.com.pacote1.entidades.ResponsavelAluno;
import br.com.pacote1.entidades.TurmaAluno;
import br.com.pacote1.jdbc.AlunoDAO;
import br.com.pacote1.jdbc.PessoaDAO;
import br.com.pacote1.jdbc.PessoaPerfilDAO;
import br.com.pacote1.jdbc.ResponsavelAlunoDAO;
import br.com.pacote1.jdbc.ResponsavelDAO;
import br.com.pacote1.jdbc.TurmaAlunoDAO;

public class MontagemCadastroAlunoCommand implements Command {
	
	private String proximo;
	
	public String execute(HttpServletRequest request) {
		
		proximo = "sucesso.jsp";
		String matricula = "";
		Pessoa pessoa = new Pessoa();
		PessoaDAO pessoaDAO = new PessoaDAO();
		Aluno aluno = new Aluno();
		AlunoDAO alunoDAO = new AlunoDAO();
		PessoaPerfil pessoaPerfil = new PessoaPerfil();
		PessoaPerfilDAO pessoaPerfilDAO = new PessoaPerfilDAO();
		Responsavel responsavel = new Responsavel();
		ResponsavelDAO responsavelDAO = new ResponsavelDAO();
		ResponsavelAluno responsavelAluno = new ResponsavelAluno();
		ResponsavelAlunoDAO responsavelAlunoDAO = new ResponsavelAlunoDAO();
		TurmaAluno turmaAluno = new TurmaAluno();
		TurmaAlunoDAO turmaAlunoDAO = new TurmaAlunoDAO();
		List<Pessoa> listPessoa = new ArrayList<Pessoa>();
		
		String nome = request.getParameter("nome");
		String dataNascimento = request.getParameter("dataNascimento");
		String naturalidade = request.getParameter("naturalidade");
		String endereco = request.getParameter("endereço");
		String numero = request.getParameter("numero");
		String bairro = request.getParameter("bairro");
		String cidade = request.getParameter("cidade");
		String estado = request.getParameter("estado");
		String telefone = request.getParameter("telefone");
		String perfilAluno = request.getParameter("perfilAluno");
		String perfilResp = request.getParameter("perfilResp");
		String nomeResponsavel = request.getParameter("nomeResponsavel");
		String cpfResponsavel = request.getParameter("cpfResponsavel");
		String turma = request.getParameter("turma");
		String dataNascimentoResp = request.getParameter("dataNascimentoResp");
		String naturalidadeResp = request.getParameter("naturalidadeResp");
		
		Calendar cal = GregorianCalendar.getInstance();
		
		int ano = cal.get(Calendar.YEAR);
		int mes = cal.get(Calendar.MONTH);
		
		if(mes < 7){
			matricula = ano + "01" + (preencherValorEsquerda(Integer.valueOf(encontrarMaiorSequencial(ano, alunoDAO)) + 1, "0", 5));
			
		}else{
			matricula = ano + "02" + (preencherValorEsquerda(Integer.valueOf(encontrarMaiorSequencial(ano, alunoDAO)) + 1, "0", 5));
		}
		
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate data = LocalDate.parse(dataNascimento, formato);
		LocalDate dataResp = LocalDate.parse(dataNascimentoResp, formato);
		
		for(int x = 0; x < 2; x++){
			if(x == 0){
				pessoa.setId(matricula);
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
			}else{
				
				listPessoa = pessoaDAO.consultar(cpfResponsavel, null);
				
				if(listPessoa.isEmpty()){
					pessoa.setId(cpfResponsavel);
					pessoa.setNome(nomeResponsavel);
					pessoa.setDtNascimento(dataResp);
					pessoa.setNaturalidade(naturalidadeResp);
					pessoa.setEndereco(endereco);
					pessoa.setNumero(Integer.valueOf(numero));
					pessoa.setBairro(bairro);
					pessoa.setCidade(cidade);
					pessoa.setEstado(estado);
					pessoa.setTelefone(telefone);
					
					pessoaDAO.cadastrar(pessoa);					
				}
			}
			
		}
		
		aluno.setId(matricula);		
		alunoDAO.cadastrar(aluno);
		
		pessoaPerfil.setId(matricula);
		pessoaPerfil.setId_perfil(Integer.valueOf(perfilAluno));		
		pessoaPerfilDAO.cadastrar(pessoaPerfil);
		
		if(listPessoa.isEmpty()){
			responsavel.setId(cpfResponsavel);		
			responsavelDAO.cadastrar(responsavel);
			
			pessoaPerfil = new PessoaPerfil();
			pessoaPerfil.setId(cpfResponsavel);
			pessoaPerfil.setId_perfil(Integer.valueOf(perfilResp));		
			pessoaPerfilDAO.cadastrar(pessoaPerfil);
		}
		responsavelAluno.setIdResponsavel(cpfResponsavel);
		responsavelAluno.setIdAluno(matricula);		
		responsavelAlunoDAO.cadastrar(responsavelAluno);
		
		turmaAluno.setIdTurma(Integer.valueOf(turma));
		turmaAluno.setIdAluno(matricula);		
		turmaAlunoDAO.cadastrar(turmaAluno);
		
		return proximo;
	}
	
	public String encontrarMaiorSequencial(int pAno, AlunoDAO pAlunoDAO){
		Aluno aluno = null;
		String maiorSq = "";
		
		List<Aluno> listAluno = pAlunoDAO.buscarAlunosAnoMatricula(String.valueOf(pAno));
		
		Iterator<Aluno> itAluno = listAluno.listIterator();
		
		while(itAluno.hasNext()){
			aluno = itAluno.next();
			
			maiorSq = aluno.getId().substring(6);
		}
		
		if(maiorSq.equals("")){
			maiorSq = "00000";
		}
		
		return maiorSq;
		
	}
	
	public String preencherValorEsquerda(Integer pSqMatricula, String pValorPreencher, int tamanho){
        StringBuffer sb = new StringBuffer(String.valueOf(pSqMatricula));

        for (int i=sb.length() ; i<tamanho ; i++){

        	sb.insert(0,pValorPreencher);

        }

        return sb.toString();

    }
	
}

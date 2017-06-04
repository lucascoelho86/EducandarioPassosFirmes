<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="br.com.pacote1.entidades.Pessoa"%>
<%@page import="br.com.pacote1.entidades.Turma"%>
<%@page import="java.util.List"%>
<%@page import="br.com.pacote1.controle.PessoaControle"%>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>CADASTRO</title>
</head>

<%
try {
%>

<% 
         	
	List<Pessoa> listPessoa = null;
	List<Pessoa> listPessoaResponsavel = null;
	Pessoa pessoa = new Pessoa();
	Pessoa pessoaResponsavel = new Pessoa();
	Turma turma = null;

	listPessoa = (List<Pessoa>)PessoaControle.getAtributoOpcional("listPessoa", request);
	listPessoaResponsavel = (List<Pessoa>)PessoaControle.getAtributoOpcional("listResponsavel", request);
	turma = (Turma)PessoaControle.getAtributoOpcional("turma", request);
         	
	if(listPessoa == null){
		listPessoa = new ArrayList<Pessoa>();
	}else{
		pessoa = listPessoa.get(0);
	}
	
	if(listPessoaResponsavel == null){
		listPessoaResponsavel = new ArrayList<Pessoa>();
	}else{
		pessoaResponsavel = listPessoaResponsavel.get(0);
	}
	
	if(turma == null){
		turma = new Turma();
	}
%>

<body>
	<form method="post">
		<fieldset id="fieldset_aluno">
				<legend>ALTERAR CADASTRO DO ALUNO</legend>
				<input type="hidden" name="perfil" value="4" />
				<div class="campo">
					<label>Nome</label>
					<input type="text" name="nome" size="50" value ="<%= pessoa.getNome() %>">
					<label>Data de Nascimento</label>				
					<input type="text" name="dataNascimento"  value ="<%= pessoa.getDtNascimento() %>">
				</div>
				<br>
				<div class="campo" >
					<label>Naturalidade</label>
					<input type="text" name="naturalidade"  value ="<%= pessoa.getNaturalidade() %>">
					<label>Endereço</label>				
					<input type="text" name="endereço"  value ="<%= pessoa.getEndereco() %>">
				</div>
				<br>
				<div class="campo" >
					<label>Número</label>				
					<input type="text" name="numero" value = "<%= pessoa.getNumero() %>">
					<label>Bairro</label>				
					<input type="text" name="bairro" value ="<%= pessoa.getBairro() %>">
				</div>
				<br>
				<div class="campo" >
					<label>Cidade</label>				
					<input type="text" name="cidade" value = "<%= pessoa.getCidade() %>">
					<label>Estado</label>				
					<input type="text" name="estado" value = "<%= pessoa.getEstado() %>">
				</div>
				<div class="campo" >
					<label>Telefone</label>				
					<input type="text" name="telefone" value = "<%= pessoa.getTelefone() %>">
				</div>
				<br>
				<div class="campo" >
					<label>Nome Responsável</label>				
					<input type="text" name="nomeResponsavel" value = "<%= pessoaResponsavel.getNome() %>">
				</div>
				<br>
				<div class="campo" >
					<label>CPF Responsável</label>				
					<input type="text" name="cpfResponsavel" value = "<%= pessoaResponsavel.getId() %>">
					<br>
					<label>Data de Nascimento Responsável</label>				
					<input type="text" name="dataNascimentoResp" value = "<%= pessoaResponsavel.getDtNascimento() %>">
					<br>
					<label>Naturalidade Responsável</label>
					<input type="text" name="naturalidadeResp" value = "<%= pessoaResponsavel.getNaturalidade() %>">
				</div>
				<br>
				<label>Turma Atual</label>
				<input type="text" name="turmaAtual" value="<%= turma.getDsTurma() %>" readonly>
				<br>
				<label>Alterar Turma</label>
				<select name="turma">
					<option value="0">Selecionar</option>
					<option value="1">4A</option>
				</select>
				<br>
				<div class="campo">
					<input type="submit" value="Continuar"/>
				</div>
			</fieldset>
	</form>
<%
} catch(Exception e) {

}
%>
</body>
</html>
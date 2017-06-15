<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="br.com.educandariopassosfirmes.entidades.Pessoa"%>
<%@page import="br.com.educandariopassosfirmes.entidades.Turma"%>
<%@page import="java.util.List"%>
<%@page import="br.com.educandariopassosfirmes.servlet.ServletPrincipal"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.time.LocalDate"%>
<%@ page import="java.time.format.DateTimeFormatter"%>
<%@ page import="java.util.Date"%>

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

	listPessoa = (List<Pessoa>)ServletPrincipal.getAtributoOpcional("listPessoa", request);
	listPessoaResponsavel = (List<Pessoa>)ServletPrincipal.getAtributoOpcional("listResponsavel", request);
	turma = (Turma)ServletPrincipal.getAtributoOpcional("turma", request);
         	
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
	<jsp:include page="cabecalho.jsp"/>
	<br>
	<form method="post" action="ServletPrincipal?acao=alterarAluno">
		<fieldset id="fieldset_aluno">
				<legend>ALTERAR CADASTRO DO ALUNO</legend>
				<input type="hidden" name="perfil" value="4" />
				<input type="hidden" name="matricula" value="<%=pessoa.getId()%>"/>
				<input type="hidden" name="idTurmaAtual" value="<%= turma.getIdTurma() %>"/>
				
				<TABLE id="table_filtro" class="filtro" cellpadding="0" cellspacing="5">
	                <TBODY>
	                    <TR>
	                        <TH class="campoformulario" align="left" width="15%">Nome:</TH>
	                        <TD class="campoformulario">
	                        	<input type="text" name="nome" size="50" value="<%=pessoa.getNome()%>">
	                        </TD>
	                        <TH class="campoformulario" align="right" width="15%">Dt Nascimento:</TH>
	                        <TD class="campoformulario">
	                        	<%
	                        	String data = pessoa.getDtNascimento().toString();

	                        	if(data != null && !data.equals("")){
	                        		String ano = data.substring(0, 4);
	                        		String mes = data.substring(5, 7);
	                        		String dia = data.substring(8);
	                        	%>
	                        		<input type="text" name="dataNascimento"  value ="<%= dia + "/" + mes + "/" + ano %>">
	                        	<%	
	                        	}else{
	                        	%>
	                        		<input type="text" name="dataNascimento">
	                        	<%	
	                        	}
	                        	%>
	                        </TD>
	                        <TH class="campoformulario" align="right" width="15%">Naturalidade:</TH>
	                        <TD class="campoformulario">
	                        	<input type="text" name="naturalidade"  value ="<%= pessoa.getNaturalidade() %>">
	                        </TD>
	                    </TR>
	                    <TR>
	                    	<TH class="campoformulario" align="left" width="15%">Endereço:</TH>
	                        <TD class="campoformulario">
	                        	<input type="text" name="endereço"  value ="<%= pessoa.getEndereco() %>">
	                        </TD>
	                       <TH class="campoformulario" align="right" width="15%">Número:</TH>
	                        <TD class="campoformulario">
	                        	<input type="text" name="numero" value = "<%= pessoa.getNumero() %>">
	                        </TD> 
	                        <TH class="campoformulario" align="right" width="15%">Bairro:</TH>
	                        <TD class="campoformulario">
	                        	<input type="text" name="bairro" value ="<%= pessoa.getBairro() %>">
	                        </TD>                        	
	                    </TR>	                    
	                    <TR>
	                        <TH class="campoformulario" align="left" width="15%">Cidade:</TH>
	                        <TD class="campoformulario">
	                        	<input type="text" name="cidade" value = "<%= pessoa.getCidade() %>">
	                        </TD>  
	                        <TH class="campoformulario" align="right" width="15%">Estado:</TH>
	                        <TD class="campoformulario">
	                        	<input type="text" name="estado" value = "<%= pessoa.getEstado() %>">
	                        </TD>
	                        <TH class="campoformulario" align="right" width="15%">Telefone:</TH>
	                        <TD class="campoformulario">
	                        	<input type="text" name="telefone" value = "<%= pessoa.getTelefone() %>">
	                        </TD>                 	
	                    </TR>
	                    <TR>
	                       <TH class="campoformulario" align="left" width="15%">Nome Responsável:</TH>
	                        <TD class="campoformulario">
	                        	<input type="text" name="nomeResponsavel" size="50" value = "<%= pessoaResponsavel.getNome() %>">
	                        </TD> 
	                        <TH class="campoformulario" align="right" width="15%">CPF Responsável:</TH>
	                        <TD class="campoformulario">
	                        	<input type="text" name="cpfResponsavel" value = "<%= pessoaResponsavel.getId() %>">
	                        </TD>
	                        <TH class="campoformulario" align="left" width="15%">Dt Nascimento Responsável:</TH>
	                        <TD class="campoformulario">
	                        	<%
	                        	String dataResp = pessoa.getDtNascimento().toString();

	                        	if(data != null && !data.equals("")){
	                        		String ano = dataResp.substring(0, 4);
	                        		String mes = dataResp.substring(5, 7);
	                        		String dia = dataResp.substring(8);
	                        	%>
	                        		<input type="text" name="dataNascimentoResp" value = "<%= dia + "/" + mes + "/" + ano %>">
	                        	<%	
	                        	}else{
	                        	%>
	                        		<input type="text" name="dataNascimentoResp">
	                        	<%	
	                        	}
	                        	%>
	                        </TD>
	                    </TR>
	                    <TR>
	                       <TH class="campoformulario" align="left" width="15%">Naturalidade Responsável:</TH>
	                        <TD class="campoformulario">
	                        	<input type="text" name="naturalidadeResp" value = "<%= pessoaResponsavel.getNaturalidade() %>">
	                        </TD> 
	                        <TH class="campoformulario" align="right" width="15%">Turma Atual:</TH>
	                        <TD class="campoformulario">
	                        	<input type="text" name="turmaAtual" value="<%= turma.getDsTurma() %>" readonly>
	                        </TD>
	                        <TH class="campoformulario" align="right" width="15%">Alterar Turma:</TH>
	                        <TD class="campoformulario">
	                        	<select name="turma">
									<option value="0">Selecionar</option>
									<option value="1">4A</option>
								</select>
	                        </TD>
	                    </TR>
	                </TBODY>
         		</TABLE>
				<div class="campo">
					<input type="submit" value="Continuar"/>
				</div>
				<div class="campo">
					<input type="button" value="Voltar" onClick="javascript:history.back();">
				</div>
			</fieldset>
	</form>
<%
} catch(Exception e) {

}
%>
</body>
</html>
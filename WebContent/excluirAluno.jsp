<%@page import="java.util.Iterator"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="br.com.pacote1.entidades.Pessoa"%>
<%@page import="br.com.pacote1.entidades.Funcionario"%>
<%@page import="br.com.pacote1.entidades.ProfessorDisciplina"%>
<%@page import="java.util.List"%>
<%@page import="br.com.pacote1.controle.PessoaControle"%>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>EXCLUIR</title>
</head>

<%
try {
%>

<% 
    	
	String arrayChave[] = (String[])PessoaControle.getAtributoOpcional("chave", request);
	
	String matricula = arrayChave[0];
	String nome = arrayChave[1];
	String idFuncao = arrayChave[2];
	String turma = arrayChave[3];
%>

<body>
	<form method="post" action="PessoaControle?acao=excluirAluno">
		<fieldset id="fieldset_aluno">
				<legend>EXCLUIR CADASTRO DE ALUNO</legend>
				<input type="hidden" name="idFuncao" value="<%=idFuncao%>" />
				<TABLE id="table_filtro" class="filtro" cellpadding="0" cellspacing="5">
	                <TBODY>
	                    <TR>
	                    	<TH class="campoformulario" align="right" width="10%">Matr�cula:</TH>
	                        <TD class="campoformulario">
	                        	<input type="text" name="matricula" value ="<%= matricula %>">
	                        </TD>
	                        <TH class="campoformulario" align="left" width="10%">Nome:</TH>
	                        <TD class="campoformulario">
	                        	<input type="text" name="nome" size="50" value="<%=nome%>">
	                        </TD>
	                        <TH class="campoformulario" align="right" width="10%">Turma:</TH>
	                        <TD class="campoformulario">
	                        	<input type="text" name="turma"  value ="<%= turma %>">
	                        </TD>
	                    </TR>
	                </TBODY>
         		</TABLE>
			</fieldset>
			<TABLE id="table_filtro" class="filtro" cellpadding="0" cellspacing="5">
	                <TBODY>
	                    <TR>
	                        <TH class="campoformulario" align="left" width="20%">CONFIRMAR EXCLUS�O?</TH>
	                        <TD class="campoformulario">
	                        	<input type="submit" value="Continuar"/>
	                        </TD>
	                        <TH class="campoformulario" align="right" width="20%">DESISTIR DA EXCLUS�O?</TH>
	                        <TD class="campoformulario">
	                        	<input type="button" value="Voltar" onClick="javascript:history.back();">
	                        </TD>
	                    </TR>
	                </TBODY>
         		</TABLE>
	</form>
<%
} catch(Exception e) {

}
%>
</body>
</html>
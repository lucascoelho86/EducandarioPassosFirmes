<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="br.com.educandariopassosfirmes.entidades.Turma"%>
<%@page import="br.com.educandariopassosfirmes.dao.TurmaDAO"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SUCESSO</title>
</head>
<body>
	<jsp:include page="cabecalho.jsp"/>
	<br>
	<form method="post" action="PessoaControle?acao=sucesso">
		<fieldset id="fieldset_aluno">
			<p align="center">
				<b>SOLICITAÇÃO REALIZADA COM SUCESSO!</b>
			</p>
		</fieldset>
			<TABLE id="table_filtro" class="filtro" cellpadding="0" cellspacing="5">
	                <TBODY>
	                    <TR>
	                        <TH class="campoformulario" align="right" width="20%">VOLTAR PARA O MENU INICIAL?</TH>
	                        <TD class="campoformulario">
	                        	<input type="submit" value="Menu"/>
	                        </TD>	                        
	                        <TH class="campoformulario" align="right" width="">SAIR?</TH>
	                        <TD class="campoformulario">
	                        	<input type="submit" value="Sair"/>
	                        </TD>
	                    </TR>
	                </TBODY>
         	</TABLE>
	</form>
</body>
</html>
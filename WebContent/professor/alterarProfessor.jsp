<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="br.com.educandariopassosfirmes.servlet.ServletProfessor"%>
<%@page import="br.com.educandariopassosfirmes.util.Select"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="cssProjeto.css">
<title>Alterar Professor</title>

</head>

<script type="text/javascript">

function desistir(){
	document.getElementById("<%=ServletProfessor.NM_EVENTO%>").value = "<%=ServletProfessor.NM_JSP_CONSULTAR%>";
}

function cadastrar(){
	document.getElementById("<%=ServletProfessor.NM_EVENTO%>").value = "<%=ServletProfessor.NM_EVENTO_PROCESSAR_INCLUSAO%>";		
}

</script>

<%

String descricao;
String qtMaxAlunos;

descricao = (String) request.getAttribute(ServletProfessor.NM_PARAMETRO_DS_TURMA);
qtMaxAlunos = (String) request.getAttribute(ServletProfessor.NM_PARAMETRO_QT_MAX_ALUNOS);

if(descricao == null){
	descricao = "";
}

if(qtMaxAlunos == null){
	qtMaxAlunos = "";
}

%>

<body>

<jsp:include page="cabecalho.jsp"/>

<form action="ServletProfessor" method="post">
<input type="hidden" id="<%=ServletProfessor.NM_EVENTO%>" name="<%=ServletProfessor.NM_EVENTO%>" value="">
	<h2 align="center">CADASTRAR PROFESSOR</h2>
	<table>
		<tbody>
			<tr>			
				<th width="10%" align="right"> Nome: </th>
				<td>
					<input type="text" id="<%=ServletProfessor.NM_PARAMETRO_NOME%>" name="<%=ServletProfessor.NM_PARAMETRO_NOME%>" value="<%=descricao%>" size="50">
				
				</td>			
			
				<th align="right"> Data de Nascimento: </th>
				<td>
					<input type="text" id="<%=ServletProfessor.NM_PARAMETRO_DT_NASCIMENTO%>" name="<%=ServletProfessor.NM_PARAMETRO_DT_NASCIMENTO%>" value="<%=qtMaxAlunos%>">		
				</td>
				
				<th align="right"> Naturalidade: </th>
				<td>
					<input type="text" id="<%=ServletProfessor.NM_PARAMETRO_NATURALIDADE%>" name="<%=ServletProfessor.NM_PARAMETRO_NATURALIDADE%>" value="<%=qtMaxAlunos%>">
				</td>
			</tr>
			<tr>			
				<th width="10%" align="right"> Endereço: </th>
				<td>
					<input type="text" id="<%=ServletProfessor.NM_PARAMETRO_ENDERECO%>" name="<%=ServletProfessor.NM_PARAMETRO_ENDERECO%>" value="<%=descricao%>" size="50">
				
				</td>			
			
				<th align="right"> Número: </th>
				<td>
					<input type="text" id="<%=ServletProfessor.NM_PARAMETRO_NUMERO%>" name="<%=ServletProfessor.NM_PARAMETRO_NUMERO%>" value="<%=qtMaxAlunos%>">		
				</td>
				
				<th align="right"> Bairro: </th>
				<td>
					<input type="text" id="<%=ServletProfessor.NM_PARAMETRO_BAIRRO%>" name="<%=ServletProfessor.NM_PARAMETRO_BAIRRO%>" value="<%=qtMaxAlunos%>">
				</td>
			</tr>
			<tr>			
				<th width="10%" align="right"> Cidade: </th>
				<td>
					<input type="text" id="<%=ServletProfessor.NM_PARAMETRO_CIDADE%>" name="<%=ServletProfessor.NM_PARAMETRO_CIDADE%>" value="<%=descricao%>" size="20">
				
				</td>			
			
				<th align="right"> Estado: </th>
				<td>
					<input type="text" id="<%=ServletProfessor.NM_PARAMETRO_ESTADO%>" name="<%=ServletProfessor.NM_PARAMETRO_ESTADO%>" value="<%=qtMaxAlunos%>">		
				</td>
				
				<th align="right"> Telefone: </th>
				<td>
					<input type="text" id="<%=ServletProfessor.NM_PARAMETRO_TELEFONE%>" name="<%=ServletProfessor.NM_PARAMETRO_TELEFONE%>" value="<%=qtMaxAlunos%>">
				</td>
			</tr>
			<tr>			
				<th width="10%" align="right"> Identidade: </th>
				<td>
					<input type="text" id="<%=ServletProfessor.NM_PARAMETRO_IDENTIDADE%>" name="<%=ServletProfessor.NM_PARAMETRO_IDENTIDADE%>" value="<%=descricao%>" size="20">
				
				</td>			
			
				<th align="right"> CPF: </th>
				<td>
					<input type="text" id="<%=ServletProfessor.NM_PARAMETRO_CPF%>" name="<%=ServletProfessor.NM_PARAMETRO_CPF%>" value="<%=qtMaxAlunos%>">		
				</td>
				
				<th align="right"> Formação: </th>
				<td>
					<input type="text" id="<%=ServletProfessor.NM_PARAMETRO_FORMACAO%>" name="<%=ServletProfessor.NM_PARAMETRO_FORMACAO%>" value="<%=qtMaxAlunos%>">
				</td>
			</tr>
			<tr>			
				<th width="10%" align="right"> Estado Civil: </th>
				<td>
					<input type="text" id="<%=ServletProfessor.NM_PARAMETRO_ESTADO_CIVIL%>" name="<%=ServletProfessor.NM_PARAMETRO_ESTADO_CIVIL%>" value="<%=descricao%>" size="20">
				
				</td>			
			
				<th align="right"> Quantidade Dependentes: </th>
				<td>
					<input type="text" id="<%=ServletProfessor.NM_PARAMETRO_QT_DEPENDENTE%>" name="<%=ServletProfessor.NM_PARAMETRO_QT_DEPENDENTE%>" value="<%=qtMaxAlunos%>">		
				</td>
				
				<th align="right"> Data Admissão: </th>
				<td>
					<input type="text" id="<%=ServletProfessor.NM_PARAMETRO_DT_ADMISSAO%>" name="<%=ServletProfessor.NM_PARAMETRO_DT_ADMISSAO%>" value="<%=qtMaxAlunos%>">
				</td>
			</tr>
			<tr>			
				<th width="10%" align="right"> Carga Horária: </th>
				<td>
					<input type="text" id="<%=ServletProfessor.NM_PARAMETRO_CARGA_HORARIA%>" name="<%=ServletProfessor.NM_PARAMETRO_CARGA_HORARIA%>" value="<%=descricao%>" size="10">
				
				</td>			
			
				<th align="right"> Salário: </th>
				<td>
					<input type="text" id="<%=ServletProfessor.NM_PARAMETRO_SALARIO%>" name="<%=ServletProfessor.NM_PARAMETRO_SALARIO%>" value="<%=qtMaxAlunos%>">		
				</td>
			</tr>
		</tbody>
	</table>
	<br>
	<table>
	<tr>
		<td style="position: absolute; left: 30%; top: 60%;">
			<button type="submit" id="botaoCadastrar" name="botaoCadastrar" onclick="cadastrar();">Cadastrar</button>				
		</td>
			
		<td style="position: absolute; left: 60%; top: 60%;">
			<button type="submit" id="botaoDesistir" name="botaoDesistir" onclick="desistir();">Voltar</button>				
		</td>
	</tr>
	</table>
</form>

</body>



</html>
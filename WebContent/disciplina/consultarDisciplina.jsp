<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="br.com.educandariopassosfirmes.servlet.ServletDisciplina"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="cssProjeto.css">
<title>Consultar Disciplina</title>

</head>

<script type="text/javascript">

function consultar(){
	document.getElementById("<%=ServletDisciplina.NM_EVENTO%>").value = "<%=ServletDisciplina.NM_EVENTO_CONSULTAR_TODOS%>";
}

function excluir(){
	document.getElementById("<%=ServletDisciplina.NM_EVENTO%>").value = "<%=ServletDisciplina.NM_EVENTO_EXCLUIR%>";
}

function exibirAlteracao(){
	document.getElementById("<%=ServletDisciplina.NM_EVENTO%>").value = "<%=ServletDisciplina.NM_EVENTO_EXIBIR_ALTERACAO%>";
}

function exibirInclusao(){
	document.getElementById("<%=ServletDisciplina.NM_EVENTO%>").value = "<%=ServletDisciplina.NM_EVENTO_EXIBIR_INCLUSAO%>";
}

</script>

<body>

<jsp:include page="cabecalho.jsp"/>

<form action="ServletDisciplina" method="post">
<input type="hidden" id="<%=ServletDisciplina.NM_EVENTO%>" name="<%=ServletDisciplina.NM_EVENTO%>" value="">
	<h2 align="center">CONSULTAR DISCIPLINA</h2>
	<table>
		<tbody>
			<tr>
			
				<th width="12%"> Código Disciplina: </th>
				<td>
					<input type="text" id="id_cdDisciplina" name="name_cdDisciplina" value="">
				
				</td>			
			
				<th align="right"> Descrição Disciplina: </th>
				<td>
					<input type="text" id="id_dsDisciplina" name="name_dsDisciplina" value="" size="50">
				
				</td>
				<td>
					<button type="submit" id="botaoLocalizar" name="botaoLocalizar" onclick="consultar();">Localizar</button>
				
				</td>
			
			</tr>
		</tbody>
	</table>
	<br>
	<table class="tituloRetornoDados">
	<tr>
		<TH align="center" width="1%">X</TH>
		<TH align="left" width="3%" >Código Disciplina</TH>
		<TH align="left" width="10%" >Descrição Disciplina</TH>
	</tr>
	</table>
	<table>
		<tr>
			<td>
					<button type="submit" id="botaoCadastrar" name="botaoCadastrar" onclick="exibirInclusao();">Incluir</button>
					
			</td>
		</tr>
	</table>
</form>

</body>



</html>
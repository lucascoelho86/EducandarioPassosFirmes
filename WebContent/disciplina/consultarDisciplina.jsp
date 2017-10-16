<%@page import="java.util.Iterator"%>
<%@page import="br.com.educandariopassosfirmes.entidades.Disciplina"%>
<%@page import="java.util.ArrayList"%>
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

<%

ArrayList<Disciplina>colecaoDisciplina;
Iterator<Disciplina>itDisciplina;
colecaoDisciplina = (ArrayList<Disciplina>) request.getAttribute(ServletDisciplina.NM_PARAMETRO_COLECAO_DISCIPLINA);

if(colecaoDisciplina == null){
	colecaoDisciplina = new ArrayList<Disciplina>();
	itDisciplina = colecaoDisciplina.iterator();
}else{
	itDisciplina = colecaoDisciplina.iterator();
}

%>

<body>

<jsp:include page="cabecalho.jsp"/>

<form action="ServletDisciplina" method="post">
<input type="hidden" id="<%=ServletDisciplina.NM_EVENTO%>" name="<%=ServletDisciplina.NM_EVENTO%>" value="">
	<h2 align="center">CONSULTAR DISCIPLINA</h2>
	<table>
		<tbody>
			<tr>
			
				<th width="12%"> Sigla Disciplina: </th>
				<td>
					<input type="text" id="<%=ServletDisciplina.NM_PARAMETRO_SIGLA_DISCIPLINA%>" name="<%=ServletDisciplina.NM_PARAMETRO_SIGLA_DISCIPLINA%>" value="">
				
				</td>			
			
				<th align="right"> Descrição Disciplina: </th>
				<td>
					<input type="text" id="<%=ServletDisciplina.NM_PARAMETRO_DS_DISCIPLINA%>" name="<%=ServletDisciplina.NM_PARAMETRO_DS_DISCIPLINA%>" value="" size="50">
				
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
		<TH align="left" width="3%" >Sigla Disciplina</TH>
		<TH align="left" width="10%" >Descrição Disciplina</TH>
	</tr>
	</table>
	<table class="tabeladados">
		<%
		String chave = "";
		while(itDisciplina.hasNext()){
			Disciplina disciplina = itDisciplina.next();
			chave = disciplina.getIdDisciplina() + ";" + disciplina.getSiglaDisciplina() + ";" + disciplina.getDsDisciplina();
			%>
			<tr>
				<th style="color: white"> SSS </th>
				<td>
					<input type="radio" id="rdb_consulta" name="<%=ServletDisciplina.NM_PARAMETRO_CHAVE%>" value="<%=chave%>">
				</td>
				<th style="color: white"> SSSS </th>
				<td style="width:310px; display:block; text-align: left"><%=disciplina.getSiglaDisciplina().toUpperCase() %></td>
				<td><%=disciplina.getDsDisciplina().toUpperCase() %></td>
			</tr>
			
			
			<%		
		}
	%>
	</table>
	<br>
	<table>
		<tr>
			<td style="width:600px; text-align: center">
					<button type="submit" id="botaoCadastrar" name="botaoCadastrar" onclick="exibirInclusao();">Incluir</button>
					
			</td>
			<td style="width:700px; text-align: center">
					<button type="submit" id="botaoAlterar" name="botaoAlterar" onclick="exibirAlteracao();">Alterar</button>
					
			</td>
			<td style="width:800px; text-align: center">
					<button type="submit" id="botaoExcluir" name="botaoExcluir" onclick="excluir();">Excluir</button>
					
			</td>
		</tr>
	</table>
</form>

</body>



</html>
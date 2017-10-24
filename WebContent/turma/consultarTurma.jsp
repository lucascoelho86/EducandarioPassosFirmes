<%@page import="java.util.Iterator"%>
<%@page import="br.com.educandariopassosfirmes.entidades.Turma"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="br.com.educandariopassosfirmes.servlet.ServletTurma"%>
<%@page import="br.com.educandariopassosfirmes.util.Select"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="cssProjeto.css">
<title>Consultar Turma</title>

</head>

<script type="text/javascript">

function consultar(){
	document.getElementById("<%=ServletTurma.NM_EVENTO%>").value = "<%=ServletTurma.NM_EVENTO_CONSULTAR_TODOS%>";
}

function excluir(){
	document.getElementById("<%=ServletTurma.NM_EVENTO%>").value = "<%=ServletTurma.NM_EVENTO_EXCLUIR%>";
}

function exibirAlteracao(){
	document.getElementById("<%=ServletTurma.NM_EVENTO%>").value = "<%=ServletTurma.NM_EVENTO_EXIBIR_ALTERACAO%>";
}

function exibirInclusao(){
	document.getElementById("<%=ServletTurma.NM_EVENTO%>").value = "<%=ServletTurma.NM_EVENTO_EXIBIR_INCLUSAO%>";
}

</script>

<%

ArrayList<Turma>colecaoTurma;
Iterator<Turma>itTurma;
colecaoTurma = (ArrayList<Turma>) request.getAttribute(ServletTurma.NM_PARAMETRO_COLECAO_TURMA);

if(colecaoTurma == null){
	colecaoTurma = new ArrayList<Turma>();
	itTurma = colecaoTurma.iterator();
}else{
	itTurma = colecaoTurma.iterator();
}

%>

<body>

<jsp:include page="cabecalho.jsp"/>

<form action="ServletTurma" method="post">
<input type="hidden" id="<%=ServletTurma.NM_EVENTO%>" name="<%=ServletTurma.NM_EVENTO%>" value="">
	<h2 align="center">CONSULTAR TURMA</h2>
	<table>
		<tbody>
			<tr>
			
				<th width="10%"> Descri��o Turma: </th>
				<td>
					<input type="text" id="<%=ServletTurma.NM_PARAMETRO_DS_TURMA%>" name="<%=ServletTurma.NM_PARAMETRO_DS_TURMA%>" value="" size="50">
				
				</td>			
			
				<th align="right"> Turno: </th>
				<td>
					<%
						int contador = 0;
						for(int x = 0; x < 2; x++){
							
							
							if(x == 0){%>						
								<%=Select.getInstancia().getHTML(ServletTurma.NM_PARAMETRO_SELECT_TURNO, ServletTurma.NM_PARAMETRO_SELECT_TURNO, x + 1, ServletTurma.NM_TURNO_MANHA, false, contador, false)%>
							<%}else{%>
								<%=Select.getInstancia().getHTML(ServletTurma.NM_PARAMETRO_SELECT_TURNO, ServletTurma.NM_PARAMETRO_SELECT_TURNO, x + 1, ServletTurma.NM_TURNO_TARDE, false, contador, true)%>
							<%}
							contador++;
							%>
						<%}%>				
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
		<TH align="left" width="3%" >Descri��o Turma</TH>
		<TH align="left" width="10%" >Turno</TH>
	</tr>
	</table>
	<table class="tabeladados">
		<%
		String chave = "";
		while(itTurma.hasNext()){
			Turma turma = itTurma.next();
			chave = turma.getIdTurma() + ";" + turma.getDsTurma() + ";" + turma.getTurno() + ";" + turma.getQtMaxAlunos();
			%>
			<tr>
				<th style="color: white"> SSS </th>
				<td>
				<%if(colecaoTurma.size() == 1){ %>
					<input type="radio" id="rdb_consulta" name="<%=ServletTurma.NM_PARAMETRO_CHAVE%>" value="<%=chave%>" checked>
				<%}else{%>
					<input type="radio" id="rdb_consulta" name="<%=ServletTurma.NM_PARAMETRO_CHAVE%>" value="<%=chave%>">
				<%}%>
				</td>
				<th style="color: white"> SSSS </th>
				<td style="width:310px; display:block; text-align: left"><%=turma.getDsTurma().toUpperCase() %></td>
				<td><%=turma.getTurno().toUpperCase() %></td>
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
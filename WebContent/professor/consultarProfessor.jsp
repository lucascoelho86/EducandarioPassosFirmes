<%@page import="java.util.Iterator"%>
<%@page import="br.com.educandariopassosfirmes.entidades.Turma"%>
<%@page import="br.com.educandariopassosfirmes.entidades.Disciplina"%>
<%@page import="br.com.educandariopassosfirmes.dao.DisciplinaDAO"%>
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
<title>Consultar Professor</title>

</head>

<script type="text/javascript">

function consultar(){
	document.getElementById("<%=ServletProfessor.NM_EVENTO%>").value = "<%=ServletProfessor.NM_EVENTO_CONSULTAR_TODOS%>";
}

function excluir(){
	document.getElementById("<%=ServletProfessor.NM_EVENTO%>").value = "<%=ServletProfessor.NM_EVENTO_EXCLUIR%>";
}

function exibirAlteracao(){
	document.getElementById("<%=ServletProfessor.NM_EVENTO%>").value = "<%=ServletProfessor.NM_EVENTO_EXIBIR_ALTERACAO%>";
}

function exibirInclusao(){
	document.getElementById("<%=ServletProfessor.NM_EVENTO%>").value = "<%=ServletProfessor.NM_EVENTO_EXIBIR_INCLUSAO%>";
}

</script>

<%

ArrayList<Turma>colecaoTurma;
Iterator<Turma>itTurma;
colecaoTurma = (ArrayList<Turma>) request.getAttribute(ServletProfessor.NM_PARAMETRO_COLECAO_TURMA);

if(colecaoTurma == null){
	colecaoTurma = new ArrayList<Turma>();
	itTurma = colecaoTurma.iterator();
}else{
	itTurma = colecaoTurma.iterator();
}

%>

<body>

<jsp:include page="cabecalho.jsp"/>

<form action="ServletProfessor" method="post">
<input type="hidden" id="<%=ServletProfessor.NM_EVENTO%>" name="<%=ServletProfessor.NM_EVENTO%>" value="">
	<h2 align="center">CONSULTAR PROFESSOR</h2>
	<table>
		<tbody>
			<tr>
			
				<th width="10%"> Nome Professor: </th>
				<td>
					<input type="text" id="<%=ServletProfessor.NM_PARAMETRO_DS_TURMA%>" name="<%=ServletProfessor.NM_PARAMETRO_DS_TURMA%>" value="" size="50">
				
				</td>
			
				<th align="right"> Disciplina: </th>
				<td>
					<%	DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
						ArrayList<Disciplina>colecaoDisciplina = disciplinaDAO.consultar(0, "", "");
						boolean ultimaDisciplina = false;
						for(int x = 0; x < colecaoDisciplina.size(); x++){
							Disciplina disciplina = colecaoDisciplina.get(x);
							
							if(x + 1 == colecaoDisciplina.size()){
								ultimaDisciplina = true;
							}%>	
												
							<%=Select.getInstancia().getHTML(ServletProfessor.NM_PARAMETRO_SELECT_TURNO, ServletProfessor.NM_PARAMETRO_SELECT_TURNO, x + 1, disciplina.getDsDisciplina(), false, x, ultimaDisciplina)%>
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
					<input type="radio" id="rdb_consulta" name="<%=ServletProfessor.NM_PARAMETRO_CHAVE%>" value="<%=chave%>" checked>
				<%}else{%>
					<input type="radio" id="rdb_consulta" name="<%=ServletProfessor.NM_PARAMETRO_CHAVE%>" value="<%=chave%>">
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
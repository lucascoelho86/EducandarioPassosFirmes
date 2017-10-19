<%@page import="java.util.Iterator"%>
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
<title>Cadastrar Turma</title>

</head>

<script type="text/javascript">

function desistir(){
	document.getElementById("<%=ServletTurma.NM_EVENTO%>").value = "<%=ServletTurma.NM_JSP_CONSULTAR%>";
}

function cadastrar(){
	document.getElementById("<%=ServletTurma.NM_EVENTO%>").value = "<%=ServletTurma.NM_EVENTO_PROCESSAR_INCLUSAO%>";
}

</script>

<body>

<jsp:include page="cabecalho.jsp"/>

<form action="ServletTurma" method="post">
<input type="hidden" id="<%=ServletTurma.NM_EVENTO%>" name="<%=ServletTurma.NM_EVENTO%>" value="">
	<h2 align="center">CADASTRAR TURMA</h2>
	<table>
		<tbody>
			<tr>			
				<th width="10%" align="right"> Descrição Turma: </th>
				<td>
					<input type="text" id="<%=ServletTurma.NM_PARAMETRO_DS_TURMA%>" name="<%=ServletTurma.NM_PARAMETRO_DS_TURMA%>" value="" size="50">
				
				</td>			
			
				<th align="right"> Turno: </th>
				<td>
					<%
						int contador = 0;
						for(int x = 0; x < 2; x++){
							
							
							if(x == 0){%>						
								<%=Select.getInstancia().getHTML(ServletTurma.NM_PARAMETRO_SELECT_TURNO, ServletTurma.NM_PARAMETRO_SELECT_TURNO, x, "Matutino", false, contador, false)%>
							<%}else{%>
								<%=Select.getInstancia().getHTML(ServletTurma.NM_PARAMETRO_SELECT_TURNO, ServletTurma.NM_PARAMETRO_SELECT_TURNO, x, "Vespertino", false, contador, true)%>
							<%}
							contador++;
							%>
						<%}%>				
				</td>
				
				<th align="right"> Quantidade Máxima de Alunos: </th>
				<td>
					<input type="text" id="<%=ServletTurma.NM_PARAMETRO_CAMPO_CARGA_HORARIA%>" name="<%=ServletTurma.NM_PARAMETRO_CAMPO_CARGA_HORARIA%>" value="">
				</td>
			</tr>
		</tbody>
	</table>
	<br>
	<table>
	<tr>
		<td style="position: absolute; left: 30%; top: 40%;">
			<button type="submit" id="botaoCadastrar" name="botaoCadastrar" onclick="cadastrar();">Cadastrar</button>				
		</td>
			
		<td style="position: absolute; left: 60%; top: 40%;">
			<button type="submit" id="botaoDesistir" name="botaoDesistir" onclick="desistir();">Voltar</button>				
		</td>
	</tr>
	</table>
</form>

</body>



</html>
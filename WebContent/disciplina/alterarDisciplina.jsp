<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="br.com.educandariopassosfirmes.servlet.ServletDisciplina"%>
<%@page import="br.com.educandariopassosfirmes.util.Select"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="cssProjeto.css">
<title>Alterar Disciplina</title>

</head>

<script type="text/javascript">

function desistir(){
	window.history.back();
}

function alterar(){
	document.getElementById("<%=ServletDisciplina.NM_EVENTO%>").value = "<%=ServletDisciplina.NM_EVENTO_PROCESSAR_ALTERACAO%>";
	
	var campoSigla = document.getElementById("<%=ServletDisciplina.NM_PARAMETRO_SIGLA_DISCIPLINA%>").value;
	var campoDescricao = document.getElementById("<%=ServletDisciplina.NM_PARAMETRO_DS_DISCIPLINA%>").value;
	
	if(campoSigla != "" && campoDescricao != ""){
		document.frm_principal.submit();
	}else if(campoSigla == ""){
		alert("Preencha o campo sigla!");
	}else if(campoDescricao == ""){
		alert("Preencha o campo descri��o!");
	}
}

</script>

<%

String idDisciplina = "";
String siglaDisciplina = "";
String dsDisciplina = "";

idDisciplina = (String)request.getAttribute(ServletDisciplina.NM_PARAMETRO_ID_DISCIPLINA);
siglaDisciplina = (String)request.getAttribute(ServletDisciplina.NM_PARAMETRO_SIGLA_DISCIPLINA);
dsDisciplina = (String)request.getAttribute(ServletDisciplina.NM_PARAMETRO_DS_DISCIPLINA);

%>

<body>

<jsp:include page="cabecalho.jsp"/>
<a href = "ServletMenu" style="font-size: 14px; font-family: Cooper Black; text-decoration: none; color: black;"> <img width="60px" height="60px" src="img/pe.png"/><b>MENU</b></a>
<form name="frm_principal" action="ServletDisciplina" method="post">
<input type="hidden" id="<%=ServletDisciplina.NM_EVENTO%>" name="<%=ServletDisciplina.NM_EVENTO%>" value="">
<input type="hidden" id="<%=ServletDisciplina.NM_PARAMETRO_ID_DISCIPLINA%>" name="<%=ServletDisciplina.NM_PARAMETRO_ID_DISCIPLINA%>" value="<%=idDisciplina%>">
	<h2 align="center">ALTERAR DISCIPLINA</h2>
	<table width="100%">
			<tr>
				<td>
					<table width="65%" align="center" style="background-color: #99CCFF">
						<tbody>
							<tr>			
								<th width="15%" align="right"> Sigla Disciplina: </th>
								<td>
									<input type="text" id="<%=ServletDisciplina.NM_PARAMETRO_SIGLA_DISCIPLINA%>" name="<%=ServletDisciplina.NM_PARAMETRO_SIGLA_DISCIPLINA%>" value="<%=siglaDisciplina%>" maxlength="20">
								
								</td>			
							
								<th width="20%" align="right"> Descri��o Disciplina: </th>
								<td>
									<input type="text" id="<%=ServletDisciplina.NM_PARAMETRO_DS_DISCIPLINA%>" name="<%=ServletDisciplina.NM_PARAMETRO_DS_DISCIPLINA%>" value="<%=dsDisciplina%>" size="40" maxlength="20">
								
								</td>
							</tr>
						</tbody>
					</table>
					<br>
					<table width="50%" align="center">
						<tr>
							<td align="center">
								<button type="button" id="botaoAlterar" name="botaoAlterar" onclick="alterar();">Alterar</button>				
							</td>
							<td align="center">
								<button type="button" id="botaoDesistir" name="botaoDesistir" onclick="desistir();">Voltar</button>				
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
</form>

</body>



</html>
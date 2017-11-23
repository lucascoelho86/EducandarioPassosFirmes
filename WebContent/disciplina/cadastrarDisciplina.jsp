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
<title>Cadastrar Disciplina</title>

</head>

<script type="text/javascript">

function desistir(){
	document.getElementById("<%=ServletDisciplina.NM_EVENTO%>").value = "<%=ServletDisciplina.NM_JSP_CONSULTAR%>";
	document.frm_principal.submit();
}

function cadastrar(){
	document.getElementById("<%=ServletDisciplina.NM_EVENTO%>").value = "<%=ServletDisciplina.NM_EVENTO_PROCESSAR_INCLUSAO%>";
	
	var campoSigla = document.getElementById("<%=ServletDisciplina.NM_PARAMETRO_SIGLA_DISCIPLINA%>").value;
	var campoDescricao = document.getElementById("<%=ServletDisciplina.NM_PARAMETRO_DS_DISCIPLINA%>").value;
	
	if(campoSigla != "" && campoDescricao != ""){
		document.frm_principal.submit();
	}else if(campoSigla == ""){
		alert("Preencha o campo sigla!");
	}else if(campoDescricao == ""){
		alert("Preencha o campo descrição!");
	}
	
}

</script>

<body>

<jsp:include page="cabecalho.jsp"/>
<a href = "ServletMenu" style="font-size: 14px; font-family: Cooper Black; text-decoration: none; color: black;"> <img width="60px" height="60px" src="img/pe.png"/><b>MENU</b></a>
<form name="frm_principal" action="ServletDisciplina" method="post">
<input type="hidden" id="<%=ServletDisciplina.NM_EVENTO%>" name="<%=ServletDisciplina.NM_EVENTO%>" value="">
	<h2 align="center">CADASTRAR DISCIPLINA</h2>
	<table width="100%">
			<tr>
				<td>
					<table width="65%" align="center" style="background-color: #99CCFF">
						<tbody>
							<tr>			
								<th width="15%" align="right"> Sigla Disciplina: </th>
								<td>
									<input type="text" id="<%=ServletDisciplina.NM_PARAMETRO_SIGLA_DISCIPLINA%>" name="<%=ServletDisciplina.NM_PARAMETRO_SIGLA_DISCIPLINA%>" value="">
								
								</td>			
							
								<th width="20%" align="right"> Descrição Disciplina: </th>
								<td>
									<input type="text" id="<%=ServletDisciplina.NM_PARAMETRO_DS_DISCIPLINA%>" name="<%=ServletDisciplina.NM_PARAMETRO_DS_DISCIPLINA%>" value="" size="40">
								
								</td>
							</tr>
						</tbody>
					</table>
					<br>
					<table width="50%" align="center">
						<tr>
							<td align="center">
								<button type="button" id="botaoCadastrar" name="botaoCadastrar" onclick="cadastrar();">Cadastrar</button>				
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
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="br.com.educandariopassosfirmes.servlet.ServletDisciplina"%>
<%@page import="br.com.educandariopassosfirmes.util.Select"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="cssProjeto.css">
<title>Exceção</title>

</head>
<SCRIPT language="JavaScript" type="text/javascript"
	src="js/biblioteca.js"></SCRIPT>
<script type="text/javascript">

function desistir(){
	window.history.back();
}

function excluirProgramacaoDisciplina(){
	document.getElementById("<%=ServletDisciplina.NM_EVENTO%>").value = "<%=ServletDisciplina.NM_EVENTO_EXCLUIR_PROGRAMACAO_E_DISCIPLINA%>";
	document.frm_principal.submit();
}

</script>

<%
	String idDisciplina = (String) request
			.getAttribute(ServletDisciplina.NM_PARAMETRO_ID_DISCIPLINA);
	String nome = (String) request
			.getAttribute(ServletDisciplina.NM_PARAMETRO_DS_DISCIPLINA);
	String tamanho = (String) request
			.getAttribute(ServletDisciplina.NM_PARAMETRO_TAMANHO);
	
	if(nome == null){
		nome = "";
	}
%>

<body>

	<jsp:include page="cabecalho.jsp" />
	<a href = "ServletMenu" style="font-size: 14px; font-family: Cooper Black; text-decoration: none; color: black;"> <img width="60px" height="60px" src="img/pe.png"/><b>MENU</b></a>
	<form name="frm_principal" action="ServletDisciplina" method="post">
		<input type="hidden" id="<%=ServletDisciplina.NM_EVENTO%>"
			name="<%=ServletDisciplina.NM_EVENTO%>" value="">
		<input type="hidden" id="<%=ServletDisciplina.NM_PARAMETRO_ID_DISCIPLINA%>"
			name="<%=ServletDisciplina.NM_PARAMETRO_ID_DISCIPLINA%>" value="<%=idDisciplina%>">
		<h3 align="center">EXISTE <%=Integer.valueOf(tamanho) == 1 ? tamanho + " PROGRAMAÇÃO " : tamanho + "PROGRAMAÇÕES"%>PARA DISCIPLINA (<%=nome.equals("") ? idDisciplina : nome%>)!</h3>
		<h3 align="center">DESEJA REALMENTE EXCLUIR PROGRAMAÇÃO E DISCIPLINA?</h3>
		<table width="100%">
			<tr>
				<td>
					<table width="50%" align="center">
						<tr>
							<td style="width: 600px; text-align: center">
								<button type="button" id="botaoImprimirRelacao" name="botaoImprimirRelacao"
									onclick="excluirProgramacaoDisciplina();">Sim</button>
							</td>

							<td style="width: 700px; text-align: center">
								<button type="button" id="botaoDesistir" name="botaoDesistir"
									onclick="desistir();">Não</button>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</form>

</body>



</html>
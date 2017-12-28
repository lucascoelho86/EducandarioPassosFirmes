<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="br.com.educandariopassosfirmes.servlet.ServletTurma"%>
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

function excluirProgramacaoTurma(){
	document.getElementById("<%=ServletTurma.NM_EVENTO%>").value = "<%=ServletTurma.NM_EVENTO_EXCLUIR_PROGRAMACAO_E_TURMA%>";
	document.frm_principal.submit();
}

</script>

<%
	String turma = (String) request
			.getAttribute(ServletTurma.NM_PARAMETRO_SIGLA_TURMA);
	String nomeTurma = (String) request
			.getAttribute(ServletTurma.NM_PARAMETRO_DS_TURMA);
	String tamanho = (String) request
			.getAttribute(ServletTurma.NM_PARAMETRO_TAMANHO);
	
	if(nomeTurma == null){
		nomeTurma = "";
	}
%>

<body>

	<jsp:include page="cabecalho.jsp" />
	<a href = "ServletMenu" style="font-size: 14px; font-family: Cooper Black; text-decoration: none; color: black;"> <img width="60px" height="60px" src="img/pe.png"/><b>MENU</b></a>
	<form name="frm_principal" action="ServletTurma" method="post">
		<input type="hidden" id="<%=ServletTurma.NM_EVENTO%>"
			name="<%=ServletTurma.NM_EVENTO%>" value="">
		<input type="hidden" id="<%=ServletTurma.NM_PARAMETRO_SIGLA_TURMA%>"
			name="<%=ServletTurma.NM_PARAMETRO_SIGLA_TURMA%>" value="<%=turma%>">
		<h3 align="center">EXISTE <%=Integer.valueOf(tamanho) == 1 ? tamanho + " PROGRAMAÇÃO " : tamanho + "PROGRAMAÇÕES"%>PARA TURMA (<%=nomeTurma.equals("") ? turma : nomeTurma%>)!</h3>
		<h3 align="center">DESEJA REALMENTE EXCLUIR PROGRAMAÇÃO E TURMA?</h3>
		<table width="100%">
			<tr>
				<td>
					<table width="50%" align="center">
						<tr>
							<td style="width: 600px; text-align: center">
								<button type="button" id="botaoImprimirRelacao" name="botaoImprimirRelacao"
									onclick="excluirProgramacaoTurma();">Sim</button>
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
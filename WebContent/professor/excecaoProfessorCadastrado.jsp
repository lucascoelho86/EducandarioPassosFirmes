<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="br.com.educandariopassosfirmes.servlet.ServletProfessor"%>
<%@page import="br.com.educandariopassosfirmes.util.Select"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="cssProjeto.css">
<title>Exce��o</title>

</head>
<SCRIPT language="JavaScript" type="text/javascript"
	src="js/biblioteca.js"></SCRIPT>
<script type="text/javascript">

function desistir(){
	window.history.back();
}

</script>

<%
	String cpf = (String) request
			.getAttribute(ServletProfessor.NM_PARAMETRO_CPF);
	String nome = (String) request
			.getAttribute(ServletProfessor.NM_PARAMETRO_NOME);
	
	if(nome == null){
		nome = "";
	}
%>

<body>

	<jsp:include page="cabecalho.jsp" />
	<a href = "ServletMenu" style="font-size: 14px; font-family: Cooper Black; text-decoration: none; color: black;"> <img width="60px" height="60px" src="img/pe.png"/><b>MENU</b></a>
	<form name="frm_principal" action="ServletProfessor" method="post">
		<input type="hidden" id="<%=ServletProfessor.NM_EVENTO%>"
			name="<%=ServletProfessor.NM_EVENTO%>" value="">
		<input type="hidden" id="<%=ServletProfessor.NM_PARAMETRO_CPF%>"
			name="<%=ServletProfessor.NM_PARAMETRO_CPF%>" value="<%=cpf%>">
		<h3 align="center">O CPF: <%=cpf%> J� FOI CADASTRADO PARA O PROFESSOR(A) (<%=nome%>)!</h3>
		<h3 align="center">VERIFIQUE AS INFORMA��ES!</h3>
		<table width="100%">
			<tr>
				<td>
					<table width="50%" align="center">
						<tr>
							<td style="width: 700px; text-align: center">
								<button type="button" id="botaoDesistir" name="botaoDesistir"
									onclick="desistir();">Voltar</button>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</form>

</body>



</html>
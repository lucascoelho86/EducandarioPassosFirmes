<%@page import="br.com.educandariopassosfirmes.util.BibliotecaFormatarDados"%>
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
<title>Exceção</title>

</head>
<SCRIPT language="JavaScript" type="text/javascript"
	src="js/biblioteca.js"></SCRIPT>
<script type="text/javascript">

function desistir(){
	document.getElementById("<%=ServletTurma.NM_EVENTO%>").value = "<%=ServletTurma.NM_JSP_CONSULTAR%>";
	document.frm_principal.submit();
}

function imprimirRelacao(){
	document.getElementById("<%=ServletTurma.NM_EVENTO%>").value = "<%=ServletTurma.NM_EVENTO_IMPRIMIR%>";
	document.frm_principal.submit();
}

</script>

<%
	ArrayList<String> colecaoTurma;
	Iterator<String> itTurma;
	String turma = (String) request
			.getAttribute(ServletTurma.NM_PARAMETRO_SIGLA_TURMA);
	colecaoTurma = (ArrayList<String>) request
			.getAttribute(ServletTurma.NM_PARAMETRO_COLECAO_TURMA);

	if (colecaoTurma == null) {
		colecaoTurma = new ArrayList<String>();
		itTurma = colecaoTurma.iterator();
	} else {
		itTurma = colecaoTurma.iterator();
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
		<h3 align="center">NÃO É POSSÍVEL EXCLUIR A TURMA, POIS EXISTE ALUNOS MATRICULADOS NA MESMA.</h3>
		<h3 align="center">É NECESSÁRIO MUDAR A TURMA DOS RESPECTIVOS ALUNOS</h3>
		<table width="100%">
			<tr>
				<td>
					<table width="50%" align="center">
						<tr class="cabecalhoRetornoDados">
							<TH align="center" width="10%">Matrícula</TH>
							<TH align="center">Nome</TH>
						</tr>
						<%
							String cssCorlinha;
							boolean tratamentoCSS = true;
							String chave = "";
							while (itTurma.hasNext()) {
								String matriculaNome = itTurma.next();
								
								String[] chaveAlunoTurma = matriculaNome.split(";");
								String matricula = chaveAlunoTurma[0];
								String nome = chaveAlunoTurma[1];
								
								if (tratamentoCSS) {
									cssCorlinha = "#c0c0c0";
									tratamentoCSS = false;
								} else {
									cssCorlinha = "#ffffff";
									tratamentoCSS = true;
								}
						%>
						<tr style="background-color: <%=cssCorlinha%>">
							<td align="center"><%=BibliotecaFormatarDados.formatarMatricula(matricula)%></td>
							<td align="center"><%=nome.toUpperCase()%></td>
						</tr>
						<%
							chave += BibliotecaFormatarDados.formatarMatricula(matricula) + ";" + nome.toUpperCase() + ":";
							}
						%>
					</table>
					<input type="hidden" id="<%=ServletTurma.NM_PARAMETRO_COLECAO_TURMA%>"
						name="<%=ServletTurma.NM_PARAMETRO_COLECAO_TURMA%>" value="<%=chave%>">
					<br>
					<table width="50%" align="center">
						<tr>
							<td style="width: 600px; text-align: center">
								<button type="button" id="botaoImprimirRelacao" name="botaoImprimirRelacao"
									onclick="imprimirRelacao();">Imprimir</button>
							</td>

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
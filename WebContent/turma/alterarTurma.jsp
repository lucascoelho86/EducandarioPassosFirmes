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
<title>Alterar Turma</title>

</head>

<script type="text/javascript">

function desistir(){
	document.getElementById("<%=ServletTurma.NM_EVENTO%>").value = "<%=ServletTurma.NM_JSP_CONSULTAR%>";
	document.frm_principal.submit();
}

function alterar(){
	document.getElementById("<%=ServletTurma.NM_EVENTO%>").value = "<%=ServletTurma.NM_EVENTO_PROCESSAR_ALTERACAO%>";
	var campoSigla = document.getElementById("<%=ServletTurma.NM_PARAMETRO_SIGLA_TURMA%>").value;
	var campoDescricao = document.getElementById("<%=ServletTurma.NM_PARAMETRO_DS_TURMA%>").value;
	var valorSelectTurno = document.getElementById("<%=ServletTurma.NM_PARAMETRO_SELECT_TURNO%>").value;
	
	if(campoSigla != "" && campoDescricao != "" && valorSelectTurno != 0){
		document.frm_principal.submit();
	}else if(campoSigla == ""){
		alert("Preencha o campo sigla!");
	}else if(campoDescricao == ""){
		alert("Preencha o campo descrição!");
	}else if(valorSelectTurno == 0){
		alert("Selecione um turno!");
	}
}
</script>

<%
	String idTurma = "";
	String dsTurma = "";
	String turno = "";
	String qtMaxAlunos = "";
	String sala = "";

	idTurma = (String) request
			.getAttribute(ServletTurma.NM_PARAMETRO_SIGLA_TURMA);
	dsTurma = (String) request
			.getAttribute(ServletTurma.NM_PARAMETRO_DS_TURMA);
	turno = (String) request
			.getAttribute(ServletTurma.NM_PARAMETRO_TURNO);
	qtMaxAlunos = (String) request
			.getAttribute(ServletTurma.NM_PARAMETRO_QT_MAX_ALUNOS);
	sala = (String) request
			.getAttribute(ServletTurma.NM_PARAMETRO_SALA);
%>

<body>

	<jsp:include page="cabecalho.jsp" />
	<a href = "ServletMenu" style="font-size: 14px; font-family: Cooper Black; text-decoration: none; color: black;"> <img width="60px" height="60px" src="img/pe.png"/><b>MENU</b></a>
	<form name="frm_principal" action="ServletTurma" method="post">
	<input type="hidden" id="<%=ServletTurma.NM_EVENTO%>"
			name="<%=ServletTurma.NM_EVENTO%>" value="">
		<h2 align="center">ALTERAR TURMA</h2>
		<table width="100%">
			<tr>
				<td>
					<table width="60%" align="center" style="background-color: #99CCFF">
						<tbody>
							<tr>
								<th width="15%" align="right">Sigla Turma:</th>
								<td><input type="text"
									id="<%=ServletTurma.NM_PARAMETRO_SIGLA_TURMA%>"
									name="<%=ServletTurma.NM_PARAMETRO_SIGLA_TURMA%>"
									value="<%=idTurma%>" size="10" readonly></td>
									
								<th align="right">Descrição Turma:</th>
								<td><input type="text"
									id="<%=ServletTurma.NM_PARAMETRO_DS_TURMA%>"
									name="<%=ServletTurma.NM_PARAMETRO_DS_TURMA%>"
									value="<%=dsTurma%>" size="50"></td>
							</tr>
							<tr>
								<th align="right">Turno:</th>
								<td>
									<%
										int contador = 0;
										boolean turmaSelecionada = false;
										for (int x = 0; x < 2; x++) {

											if (x + 1 == Integer.valueOf(turno)) {
												turmaSelecionada = true;
											}
											if (x == 0) {
									%> <%=Select.getInstancia().getHTML(
							ServletTurma.NM_PARAMETRO_SELECT_TURNO,
							ServletTurma.NM_PARAMETRO_SELECT_TURNO, String.valueOf(x + 1),
							ServletTurma.NM_TURNO_MANHA, turmaSelecionada,
							contador, false, "")%> <%
 	} else {
 %> <%=Select.getInstancia().getHTML(
							ServletTurma.NM_PARAMETRO_SELECT_TURNO,
							ServletTurma.NM_PARAMETRO_SELECT_TURNO, String.valueOf(x + 1),
							ServletTurma.NM_TURNO_TARDE, turmaSelecionada,
							contador, true, "")%> <%
 	}
 		contador++;
 		turmaSelecionada = false;
 %> <%
 	}
 %>
								</td>

								<th align="right">Quantidade Máxima de Alunos:</th>
								<td><input type="text"
									id="<%=ServletTurma.NM_PARAMETRO_QT_MAX_ALUNOS%>"
									name="<%=ServletTurma.NM_PARAMETRO_QT_MAX_ALUNOS%>"
									value="<%=qtMaxAlunos.equals("0") ? "" : qtMaxAlunos%>"></td>
							</tr>
							<tr>
								<th align="right">Sala:</th>
								<td><input type="text"
									id="<%=ServletTurma.NM_PARAMETRO_SALA%>"
									name="<%=ServletTurma.NM_PARAMETRO_SALA%>"
									value="<%=sala%>"></td>
							</tr>
						</tbody>
					</table> <br>
					<table width="50%" align="center">
						<tr>
							<td style="width: 600px; text-align: center">
								<button type="button" id="botaoAlterar" name="botaoAlterar"
									onclick="alterar();">Alterar</button>
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
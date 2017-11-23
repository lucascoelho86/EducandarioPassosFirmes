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
	document.frm_principal.submit();
}

function cadastrar(){
	document.getElementById("<%=ServletTurma.NM_EVENTO%>").value = "<%=ServletTurma.NM_EVENTO_PROCESSAR_INCLUSAO%>";		
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
	String descricao;
	String siglaTurma;
	String qtMaxAlunos;
	String sala;

	descricao = (String) request
			.getAttribute(ServletTurma.NM_PARAMETRO_DS_TURMA);
	siglaTurma = (String) request
			.getAttribute(ServletTurma.NM_PARAMETRO_SIGLA_TURMA);
	qtMaxAlunos = (String) request
			.getAttribute(ServletTurma.NM_PARAMETRO_QT_MAX_ALUNOS);
	sala = (String) request
			.getAttribute(ServletTurma.NM_PARAMETRO_SALA);

	if (descricao == null) {
		descricao = "";
	}

	if (siglaTurma == null) {
		siglaTurma = "";
	}

	if (qtMaxAlunos == null) {
		qtMaxAlunos = "";
	}

	if (sala == null) {
		sala = "";
	}
%>

<body>

	<jsp:include page="cabecalho.jsp" />
	<a href = "ServletMenu" style="font-size: 14px; font-family: Cooper Black; text-decoration: none; color: black;"> <img width="60px" height="60px" src="img/pe.png"/><b>MENU</b></a>
	<form name="frm_principal" action="ServletTurma" method="post">
		<input type="hidden" id="<%=ServletTurma.NM_EVENTO%>"
			name="<%=ServletTurma.NM_EVENTO%>" value="">
		<h2 align="center">CADASTRAR TURMA</h2>
		<table width="100%">
			<tr>
				<td>
					<table width="70%" align="center" style="background-color: #99CCFF">
						<tbody>
							<tr>
								<th width="13%" align="left">Sigla Turma:</th>
								<td><input type="text"
									id="<%=ServletTurma.NM_PARAMETRO_SIGLA_TURMA%>"
									name="<%=ServletTurma.NM_PARAMETRO_SIGLA_TURMA%>"
									value="<%=siglaTurma%>" size="10" maxlength="5"></td>

								<th align="left">Descrição Turma:</th>
								<td><input type="text"
									id="<%=ServletTurma.NM_PARAMETRO_DS_TURMA%>"
									name="<%=ServletTurma.NM_PARAMETRO_DS_TURMA%>"
									value="<%=descricao%>" size="50"></td>

							</tr>
							<tr>
								<th align="left">Sala:</th>
								<td><input type="text"
									id="<%=ServletTurma.NM_PARAMETRO_SALA%>"
									name="<%=ServletTurma.NM_PARAMETRO_SALA%>"
									value="<%=sala%>" size="10"></td>
									
								<th align="left">Quantidade Máxima de Alunos:</th>
								<td><input type="text"
									id="<%=ServletTurma.NM_PARAMETRO_QT_MAX_ALUNOS%>"
									name="<%=ServletTurma.NM_PARAMETRO_QT_MAX_ALUNOS%>"
									value="<%=qtMaxAlunos%>" size="5"></td>
							</tr>
							<tr>
								<th align="left">Turno:</th>
								<td>
									<%
										int contador = 0;
										for (int x = 0; x < 2; x++) {

											if (x == 0) {
									%> <%=Select.getInstancia()
							.getHTML(ServletTurma.NM_PARAMETRO_SELECT_TURNO,
									ServletTurma.NM_PARAMETRO_SELECT_TURNO,
									String.valueOf(x + 1), ServletTurma.NM_TURNO_MANHA, false,
									contador, false, "")%> <%
 	} else {
 %> <%=Select.getInstancia().getHTML(
							ServletTurma.NM_PARAMETRO_SELECT_TURNO,
							ServletTurma.NM_PARAMETRO_SELECT_TURNO, String.valueOf(x + 1),
							ServletTurma.NM_TURNO_TARDE, false, contador, true, "")%> <%
 	}
 		contador++;
 %> <%
 	}
 %>
								</td>
							</tr>
						</tbody>
					</table> <br>
					<table width="50%" align="center">
						<tr>
							<td style="width: 600px; text-align: center">
								<button type="button" id="botaoCadastrar" name="botaoCadastrar"
									onclick="cadastrar();">Cadastrar</button>
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
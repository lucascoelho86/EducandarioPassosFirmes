<%@page import="br.com.educandariopassosfirmes.entidades.Turma"%>
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
	var salaTela = document.getElementById("<%=ServletTurma.NM_PARAMETRO_SALA%>").value;
	var salasManha = document.getElementById("<%=ServletTurma.NM_PARAMETRO_COLECAO_SALAS_MANHA%>").value;
	var salasTarde = document.getElementById("<%=ServletTurma.NM_PARAMETRO_COLECAO_SALAS_TARDE%>").value;
	var continuar = true;
	
	if(valorSelectTurno == 1){
		var turno = "Manh�";
		var colecaoSalasManha = salasManha.split(";");
		for(i = 0; i < colecaoSalasManha.length; i++){
			sala = colecaoSalasManha[i];
			
			if(sala != null && sala != "" && salaTela == sala){
				var mensagem1 = "J� existe uma turma que utiliza est� sala no turno da ";
				var mensagem2 = mensagem1.concat(turno);
				var mensagem3 = mensagem2.concat("!");
				alert(mensagem3);
				
				continuar = false;
				document.getElementById("<%=ServletTurma.NM_PARAMETRO_SALA%>").focus();
			}			
		}
	}

	if(valorSelectTurno == 2){
		var turno = "Tarde";
		var colecaoSalasTarde = salasTarde.split(";");
		for(i = 0; i < colecaoSalasTarde.length; i++){
			sala = colecaoSalasTarde[i];
			
			if(sala != null && sala != "" && salaTela == sala){
				var mensagem1 = "J� existe uma turma que utiliza est� sala no turno da ";
				var mensagem2 = mensagem1.concat(turno);
				var mensagem3 = mensagem2.concat("!");
				window.confirm(mensagem3);
				
				continuar = false;
				document.getElementById("<%=ServletTurma.NM_PARAMETRO_SALA%>").focus();
			}			
		}
	}
	
	if(campoSigla != "" && campoDescricao != "" && valorSelectTurno != 0 && continuar){
		document.frm_principal.submit();
	}else if(campoSigla == ""){
		alert("Preencha o campo sigla!");
	}else if(campoDescricao == ""){
		alert("Preencha o campo descri��o!");
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
	String salasManha;
	String salasTarde;

	descricao = (String) request
			.getAttribute(ServletTurma.NM_PARAMETRO_DS_TURMA);
	siglaTurma = (String) request
			.getAttribute(ServletTurma.NM_PARAMETRO_SIGLA_TURMA);
	qtMaxAlunos = (String) request
			.getAttribute(ServletTurma.NM_PARAMETRO_QT_MAX_ALUNOS);
	sala = (String) request
			.getAttribute(ServletTurma.NM_PARAMETRO_SALA);
	salasManha = (String) request
			.getAttribute(ServletTurma.NM_PARAMETRO_COLECAO_SALAS_MANHA);
	salasTarde = (String) request
			.getAttribute(ServletTurma.NM_PARAMETRO_COLECAO_SALAS_TARDE);

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

	if (salasManha == null) {
		salasManha = "";
	}

	if (salasTarde == null) {
		salasTarde = "";
	}
%>

<body>

	<jsp:include page="cabecalho.jsp" />
	<a href = "ServletMenu" style="font-size: 14px; font-family: Cooper Black; text-decoration: none; color: black;"> <img width="60px" height="60px" src="img/pe.png"/><b>MENU</b></a>
	<form name="frm_principal" action="ServletTurma" method="post">
		<input type="hidden" id="<%=ServletTurma.NM_EVENTO%>"
			name="<%=ServletTurma.NM_EVENTO%>" value="">
		<input type="hidden" id="<%=ServletTurma.NM_PARAMETRO_COLECAO_SALAS_MANHA%>"
			name="<%=ServletTurma.NM_PARAMETRO_COLECAO_SALAS_MANHA%>" value="<%=salasManha%>">
		<input type="hidden" id="<%=ServletTurma.NM_PARAMETRO_COLECAO_SALAS_TARDE%>"
			name="<%=ServletTurma.NM_PARAMETRO_COLECAO_SALAS_MANHA%>" value="<%=salasTarde%>">
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

								<th align="left">Descri��o Turma:</th>
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
									
								<th align="left">Quantidade M�xima de Alunos:</th>
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
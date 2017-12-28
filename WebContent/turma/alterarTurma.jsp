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
	window.history.back();
}

function alterar(){
	document.getElementById("<%=ServletTurma.NM_EVENTO%>").value = "<%=ServletTurma.NM_EVENTO_PROCESSAR_ALTERACAO%>";
	
	var campoSiglaAnt = document.getElementById("<%=ServletTurma.NM_PARAMETRO_SIGLA_TURMA_ANT%>").value;
	var campoSigla = document.getElementById("<%=ServletTurma.NM_PARAMETRO_SIGLA_TURMA%>").value;
	var campoDescricao = document.getElementById("<%=ServletTurma.NM_PARAMETRO_DS_TURMA%>").value;
	var valorSelectTurno = document.getElementById("<%=ServletTurma.NM_PARAMETRO_SELECT_TURNO%>").value;
	var salaTela = document.getElementById("<%=ServletTurma.NM_PARAMETRO_SALA%>").value;
	var tamanhoColecaoTurmaManha = document.getElementById("tamanhoTurmaManha").value;
	var tamanhoColecaoTurmaTarde = document.getElementById("tamanhoTurmaTarde").value;
	
	var turmaDiferente = false;
	if(campoSigla != campoSiglaAnt){
		turmaDiferente = true;
	}
	
	var continuar = true;
	
	if(valorSelectTurno == "1"){
		
		var turno = "Manhã";
		for(x = 0; x < tamanhoColecaoTurmaManha; x++){
			if(document.getElementById("turmaManha" + x).value != ""){
				var turma = document.getElementById("turmaManha" + x).value;
				var sala = document.getElementById("salaManha" + x).value;
					
				if(turma == campoSiglaAnt && turmaDiferente){
					continue;
				}
				
				if(turma != campoSigla){
					if(sala == salaTela){
						var mensagem1 = "Já existe uma turma que utiliza está sala no turno da ";
						var mensagem2 = mensagem1.concat(turno);
						var mensagem3 = mensagem2.concat("!");
						alert(mensagem3);
								
						continuar = false;
						document.getElementById("<%=ServletTurma.NM_PARAMETRO_SALA%>").focus();
					}
				}
			}
		}
		
	}else if(valorSelectTurno == "2"){
			
		var turno = "Tarde";
		for(x = 0; x < tamanhoColecaoTurmaTarde; x++){
			if(document.getElementById("turmaTarde" + x).value != ""){
				var turma = document.getElementById("turmaTarde" + x).value;
				var sala = document.getElementById("salaTarde" + x).value;
				
				if(turma == campoSiglaAnt && turmaDiferente){
					continue;
				}
				
				if(turma != campoSigla){
					if(sala == salaTela){
						var mensagem1 = "Já existe uma turma que utiliza está sala no turno da ";
						var mensagem2 = mensagem1.concat(turno);
						var mensagem3 = mensagem2.concat("!");
						alert(mensagem3);
								
						continuar = false;
						document.getElementById("<%=ServletTurma.NM_PARAMETRO_SALA%>").focus();
					}
				}
					
			}
		}
		
	}
	
	if(campoSigla != "" && campoDescricao != "" && valorSelectTurno != 0 && continuar){
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
	String salasManha;
	String salasTarde;

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
	salasManha = (String) request
			.getAttribute(ServletTurma.NM_PARAMETRO_COLECAO_SALAS_MANHA);
	salasTarde = (String) request
			.getAttribute(ServletTurma.NM_PARAMETRO_COLECAO_SALAS_TARDE);
	
	String[] valoresTurmaManha = new String[salasManha.length()];
	valoresTurmaManha = salasManha.split(":");

	String[] valoresTurmaTarde = new String[salasTarde.length()];
	valoresTurmaTarde = salasTarde.split(":");
%>

<body>

	<jsp:include page="cabecalho.jsp" />
	<a href = "ServletMenu" style="font-size: 14px; font-family: Cooper Black; text-decoration: none; color: black;"> <img width="60px" height="60px" src="img/pe.png"/><b>MENU</b></a>
	<form name="frm_principal" action="ServletTurma" method="post">
	<input type="hidden" id="<%=ServletTurma.NM_EVENTO%>"
			name="<%=ServletTurma.NM_EVENTO%>" value="">
	<input type="hidden" id="<%="tamanhoTurmaManha"%>"
			name="<%="tamanhoTurmaManha"%>" value="<%=valoresTurmaManha.length%>">
	<input type="hidden" id="<%="tamanhoTurmaTarde"%>"
			name="<%="tamanhoTurmaTarde"%>" value="<%=valoresTurmaTarde.length%>">
<%
String[] valores = new String[valoresTurmaManha.length];
for(int x = 0; x < valoresTurmaManha.length; x++){
	String valor = valoresTurmaManha[x];
	valores = valor.split(";");
	
	String turma = valores[0];
	String salaManha = "";
	if(!turma.equals("")){
		salaManha = valores[1];
	}
%>
	<input type="hidden" id="<%="turmaManha" + x%>"
			name="<%="turmaManha" + x%>" value="<%=turma%>">
	<input type="hidden" id="<%="salaManha" + x%>"
			name="<%="salaManha" + x%>" value="<%=salaManha%>">
<%} %>
<%
String[] valores2 = new String[valoresTurmaTarde.length];
for(int x = 0; x < valoresTurmaTarde.length; x++){
	String valor = valoresTurmaTarde[x];
	valores2 = valor.split(";");
	
	String turma = valores2[0];
	String salaTarde = "";
	if(!turma.equals("")){
		salaTarde = valores2[1];
	}
%>
	<input type="hidden" id="<%="turmaTarde" + x%>"
			name="<%="turmaTarde" + x%>" value="<%=turma%>">
	<input type="hidden" id="<%="salaTarde" + x%>"
			name="<%="salaTarde" + x%>" value="<%=salaTarde%>">
<%} %>
	<input type="hidden" id="<%=ServletTurma.NM_PARAMETRO_SIGLA_TURMA_ANT%>"
			name="<%=ServletTurma.NM_PARAMETRO_SIGLA_TURMA_ANT%>" value="<%=idTurma%>">
		<h2 align="center">ALTERAR TURMA</h2>
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
									value="<%=idTurma%>" size="10" maxlength="5"></td>
									
								<th align="left">Descrição Turma:</th>
								<td><input type="text"
									id="<%=ServletTurma.NM_PARAMETRO_DS_TURMA%>"
									name="<%=ServletTurma.NM_PARAMETRO_DS_TURMA%>"
									value="<%=dsTurma%>" size="50"></td>
							</tr>
							<tr>
								<th align="left">Sala:</th>
								<td><input type="text"
									id="<%=ServletTurma.NM_PARAMETRO_SALA%>"
									name="<%=ServletTurma.NM_PARAMETRO_SALA%>"
									value="<%=sala == null ? "" : sala%>" size="10"></td>
									
								<th align="left">Quantidade Máxima de Alunos:</th>
								<td><input type="text"
									id="<%=ServletTurma.NM_PARAMETRO_QT_MAX_ALUNOS%>"
									name="<%=ServletTurma.NM_PARAMETRO_QT_MAX_ALUNOS%>"
									value="<%=qtMaxAlunos.equals("0") ? "" : qtMaxAlunos%>"></td>
							</tr>
							<tr>
								<th align="left">Turno:</th>
								<td>
									<%
										int contador = 0;
										boolean turmaSelecionada = false;
										for (int x = 0; x < 3; x++) {

											if (x == Integer.valueOf(turno)) {
												turmaSelecionada = true;
											}
											
											if (x == 0) {
												%> <%=Select.getInstancia().getHTML(
										ServletTurma.NM_PARAMETRO_SELECT_TURNO,
										ServletTurma.NM_PARAMETRO_SELECT_TURNO, String.valueOf(x),
										"", turmaSelecionada,
										contador, false, "")%> <%
											
											}else if (x == 1) {
									%> <%=Select.getInstancia().getHTML(
							ServletTurma.NM_PARAMETRO_SELECT_TURNO,
							ServletTurma.NM_PARAMETRO_SELECT_TURNO, String.valueOf(x),
							ServletTurma.NM_TURNO_MANHA, turmaSelecionada,
							contador, false, "")%> <%
 	} else {
 %> <%=Select.getInstancia().getHTML(
							ServletTurma.NM_PARAMETRO_SELECT_TURNO,
							ServletTurma.NM_PARAMETRO_SELECT_TURNO, String.valueOf(x),
							ServletTurma.NM_TURNO_TARDE, turmaSelecionada,
							contador, true, "")%> <%
 	}
 		contador++;
 		turmaSelecionada = false;
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
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
}

function cadastrar(){
	var valorSelectTurno = document.getElementById("selectTurno").value;
	
	if(valorSelectTurno != 0){
		document.getElementById("<%=ServletTurma.NM_EVENTO%>").value = "<%=ServletTurma.NM_EVENTO_PROCESSAR_INCLUSAO%>";		
	}else{
		alert("Selecione um turno!");
		document.getElementById("<%=ServletTurma.NM_EVENTO%>").value = "<%=ServletTurma.NM_EVENTO_EXIBIR_INCLUSAO%>";
		}
	}
</script>

<%
	String descricao;
	String siglaTurma;
	String qtMaxAlunos;

	descricao = (String) request
			.getAttribute(ServletTurma.NM_PARAMETRO_DS_TURMA);
	siglaTurma = (String) request
			.getAttribute(ServletTurma.NM_PARAMETRO_SIGLA_TURMA);
	qtMaxAlunos = (String) request
			.getAttribute(ServletTurma.NM_PARAMETRO_QT_MAX_ALUNOS);

	if (descricao == null) {
		descricao = "";
	}

	if (siglaTurma == null) {
		siglaTurma = "";
	}

	if (qtMaxAlunos == null) {
		qtMaxAlunos = "";
	}
%>

<body>

	<jsp:include page="cabecalho.jsp" />

	<form action="ServletTurma" method="post">
		<input type="hidden" id="<%=ServletTurma.NM_EVENTO%>"
			name="<%=ServletTurma.NM_EVENTO%>" value="">
		<h2 align="center">CADASTRAR TURMA</h2>
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
									value="<%=siglaTurma%>" size="10" maxlength="5"></td>

								<th align="right">Descrição Turma:</th>
								<td><input type="text"
									id="<%=ServletTurma.NM_PARAMETRO_DS_TURMA%>"
									name="<%=ServletTurma.NM_PARAMETRO_DS_TURMA%>"
									value="<%=descricao%>" size="50"></td>

							</tr>
							<tr>
								<th align="right">Turno:</th>
								<td>
									<%
										int contador = 0;
										for (int x = 0; x < 2; x++) {

											if (x == 0) {
									%> <%=Select.getInstancia()
							.getHTML(ServletTurma.NM_PARAMETRO_SELECT_TURNO,
									ServletTurma.NM_PARAMETRO_SELECT_TURNO,
									String.valueOf(x + 1), ServletTurma.NM_TURNO_MANHA, false,
									contador, false)%> <%
 	} else {
 %> <%=Select.getInstancia().getHTML(
							ServletTurma.NM_PARAMETRO_SELECT_TURNO,
							ServletTurma.NM_PARAMETRO_SELECT_TURNO, String.valueOf(x + 1),
							ServletTurma.NM_TURNO_TARDE, false, contador, true)%> <%
 	}
 		contador++;
 %> <%
 	}
 %>
								</td>

								<th align="right">Quantidade Máxima de Alunos:</th>
								<td><input type="text"
									id="<%=ServletTurma.NM_PARAMETRO_QT_MAX_ALUNOS%>"
									name="<%=ServletTurma.NM_PARAMETRO_QT_MAX_ALUNOS%>"
									value="<%=qtMaxAlunos%>"></td>
							</tr>
						</tbody>
					</table> <br>
					<table width="50%" align="center">
						<tr>
							<td style="width: 600px; text-align: center">
								<button type="submit" id="botaoCadastrar" name="botaoCadastrar"
									onclick="cadastrar();">Cadastrar</button>
							</td>

							<td style="width: 700px; text-align: center">
								<button type="submit" id="botaoDesistir" name="botaoDesistir"
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
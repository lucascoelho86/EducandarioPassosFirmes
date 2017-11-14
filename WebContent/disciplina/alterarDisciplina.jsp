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
<title>Alterar Disciplina</title>

</head>

<script type="text/javascript">

function desistir(){
	document.getElementById("<%=ServletDisciplina.NM_EVENTO%>").value = "<%=ServletDisciplina.NM_JSP_CONSULTAR%>";
}

function alterar(){
	document.getElementById("<%=ServletDisciplina.NM_EVENTO%>").value = "<%=ServletDisciplina.NM_EVENTO_PROCESSAR_ALTERACAO%>";
}

</script>

<%

String idDisciplina = "";
String siglaDisciplina = "";
String dsDisciplina = "";
String assuntoPrimeiraUnidade = "";
String assuntoSegundaUnidade = "";
String assuntoTerceiraUnidade = "";
String assuntoQuartaUnidade = "";
Integer cargaHoraria;

idDisciplina = (String)request.getAttribute(ServletDisciplina.NM_PARAMETRO_ID_DISCIPLINA);
siglaDisciplina = (String)request.getAttribute(ServletDisciplina.NM_PARAMETRO_SIGLA_DISCIPLINA);
dsDisciplina = (String)request.getAttribute(ServletDisciplina.NM_PARAMETRO_DS_DISCIPLINA);
assuntoPrimeiraUnidade = (String)request.getAttribute(ServletDisciplina.NM_PARAMETRO_TX_PRIMEIRA_UNIDADE);
assuntoSegundaUnidade = (String)request.getAttribute(ServletDisciplina.NM_PARAMETRO_TX_SEGUNDA_UNIDADE);
assuntoTerceiraUnidade = (String)request.getAttribute(ServletDisciplina.NM_PARAMETRO_TX_TERCEIRA_UNIDADE);
assuntoQuartaUnidade = (String)request.getAttribute(ServletDisciplina.NM_PARAMETRO_TX_QUARTA_UNIDADE);
cargaHoraria = (Integer)request.getAttribute(ServletDisciplina.NM_PARAMETRO_CAMPO_CARGA_HORARIA);

%>

<body>

<jsp:include page="cabecalho.jsp"/>
<a href = "ServletMenu" style="font-size: 14px; font-family: Cooper Black; text-decoration: none; color: black;"> <img width="60px" height="60px" src="img/pe.png"/><b>MENU</b></a>
<form action="ServletDisciplina" method="post">
<input type="hidden" id="<%=ServletDisciplina.NM_EVENTO%>" name="<%=ServletDisciplina.NM_EVENTO%>" value="">
<input type="hidden" id="<%=ServletDisciplina.NM_PARAMETRO_ID_DISCIPLINA%>" name="<%=ServletDisciplina.NM_PARAMETRO_ID_DISCIPLINA%>" value="<%=idDisciplina%>">
	<h2 align="center">ALTERAR DISCIPLINA</h2>
	<table width="100%">
			<tr>
				<td>
					<table width="65%" align="center" style="background-color: #99CCFF">
						<tbody>
							<tr>			
								<th width="12%" align="right"> Sigla Disciplina: </th>
								<td>
									<input type="text" id="<%=ServletDisciplina.NM_PARAMETRO_SIGLA_DISCIPLINA%>" name="<%=ServletDisciplina.NM_PARAMETRO_SIGLA_DISCIPLINA%>" value="<%=siglaDisciplina%>">
								
								</td>			
							
								<th align="right"> Descrição Disciplina: </th>
								<td>
									<input type="text" id="<%=ServletDisciplina.NM_PARAMETRO_DS_DISCIPLINA%>" name="<%=ServletDisciplina.NM_PARAMETRO_DS_DISCIPLINA%>" value="<%=dsDisciplina%>" size="50">
								
								</td>
							</tr>
							<tr>
								<th width="15%"  align="right"> Assuntos 1ª Unidade: </th>
								<td>
									<textarea id="<%=ServletDisciplina.NM_PARAMETRO_TX_PRIMEIRA_UNIDADE%>" name="<%=ServletDisciplina.NM_PARAMETRO_TX_PRIMEIRA_UNIDADE%>" rows="4" cols="60"><%=assuntoPrimeiraUnidade%></textarea>
								
								</td>			
							
								<th align="right"> Assuntos 2ª Unidade: </th>
								<td>
									<textarea id="<%=ServletDisciplina.NM_PARAMETRO_TX_SEGUNDA_UNIDADE%>" name="<%=ServletDisciplina.NM_PARAMETRO_TX_SEGUNDA_UNIDADE%>" rows="4" cols="60"><%=assuntoSegundaUnidade%></textarea>
								
								</td>
								
							</tr>
							<tr>
								<th width="12%"  align="right"> Assuntos 3ª Unidade: </th>
								<td>
									<textarea id="<%=ServletDisciplina.NM_PARAMETRO_TX_TERCEIRA_UNIDADE%>" name="<%=ServletDisciplina.NM_PARAMETRO_TX_TERCEIRA_UNIDADE%>" rows="4" cols="60"><%=assuntoTerceiraUnidade%></textarea>
								
								</td>			
							
								<th align="right"> Assuntos 4ª Unidade: </th>
								<td>
									<textarea id="<%=ServletDisciplina.NM_PARAMETRO_TX_QUARTA_UNIDADE%>" name="<%=ServletDisciplina.NM_PARAMETRO_TX_QUARTA_UNIDADE%>" rows="4" cols="60"><%=assuntoQuartaUnidade%></textarea>
								
								</td>
								
							</tr>
							<tr>
								<th width="12%"  align="right"> Carga Horária: </th>
								<td>
									<input type="text" id="<%=ServletDisciplina.NM_PARAMETRO_CAMPO_CARGA_HORARIA%>" name="<%=ServletDisciplina.NM_PARAMETRO_CAMPO_CARGA_HORARIA%>" value="<%=String.valueOf(cargaHoraria)%>">				
								</td>
							</tr>
						</tbody>
					</table>
					<br>
					<table width="50%" align="center">
						<tr>
							<td align="center">
								<button type="submit" id="botaoAlterar" name="botaoAlterar" onclick="alterar();">Alterar</button>				
							</td>
							<td align="center">
								<button type="submit" id="botaoDesistir" name="botaoDesistir" onclick="desistir();">Voltar</button>				
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
</form>

</body>



</html>
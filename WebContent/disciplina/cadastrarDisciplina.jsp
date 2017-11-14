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
<title>Cadastrar Disciplina</title>

</head>

<script type="text/javascript">

function desistir(){
	document.getElementById("<%=ServletDisciplina.NM_EVENTO%>").value = "<%=ServletDisciplina.NM_JSP_CONSULTAR%>";
}

function cadastrar(){
	document.getElementById("<%=ServletDisciplina.NM_EVENTO%>").value = "<%=ServletDisciplina.NM_EVENTO_PROCESSAR_INCLUSAO%>";
}

</script>

<body>

<jsp:include page="cabecalho.jsp"/>
<a href = "ServletMenu" style="font-size: 14px; font-family: Cooper Black; text-decoration: none; color: black;"> <img width="60px" height="60px" src="img/pe.png"/><b>MENU</b></a>
<form action="ServletDisciplina" method="post">
<input type="hidden" id="<%=ServletDisciplina.NM_EVENTO%>" name="<%=ServletDisciplina.NM_EVENTO%>" value="">
	<h2 align="center">CADASTRAR DISCIPLINA</h2>
	<table width="100%">
			<tr>
				<td>
					<table width="65%" align="center" style="background-color: #99CCFF">
						<tbody>
							<tr>			
								<th width="10%" align="right"> Sigla Disciplina: </th>
								<td>
									<input type="text" id="<%=ServletDisciplina.NM_PARAMETRO_SIGLA_DISCIPLINA%>" name="<%=ServletDisciplina.NM_PARAMETRO_SIGLA_DISCIPLINA%>" value="">
								
								</td>			
							
								<th width="15%" align="right"> Descri��o Disciplina: </th>
								<td>
									<input type="text" id="<%=ServletDisciplina.NM_PARAMETRO_DS_DISCIPLINA%>" name="<%=ServletDisciplina.NM_PARAMETRO_DS_DISCIPLINA%>" value="" size="50">
								
								</td>
							</tr>
							<tr>
								<th width="15%"  align="right"> Assuntos 1� Unidade: </th>
								<td>
									<textarea id="<%=ServletDisciplina.NM_PARAMETRO_TX_PRIMEIRA_UNIDADE%>" name="<%=ServletDisciplina.NM_PARAMETRO_TX_PRIMEIRA_UNIDADE%>" rows="4" cols="60"></textarea>
								
								</td>			
							
								<th align="right"> Assuntos 2� Unidade: </th>
								<td>
									<textarea id="<%=ServletDisciplina.NM_PARAMETRO_TX_SEGUNDA_UNIDADE%>" name="<%=ServletDisciplina.NM_PARAMETRO_TX_SEGUNDA_UNIDADE%>" rows="4" cols="60"></textarea>
								
								</td>
								
							</tr>
							<tr>
								<th width="12%"  align="right"> Assuntos 3� Unidade: </th>
								<td>
									<textarea id="<%=ServletDisciplina.NM_PARAMETRO_TX_TERCEIRA_UNIDADE%>" name="<%=ServletDisciplina.NM_PARAMETRO_TX_TERCEIRA_UNIDADE%>" rows="4" cols="60"></textarea>
								
								</td>			
							
								<th align="right"> Assuntos 4� Unidade: </th>
								<td>
									<textarea id="<%=ServletDisciplina.NM_PARAMETRO_TX_QUARTA_UNIDADE%>" name="<%=ServletDisciplina.NM_PARAMETRO_TX_QUARTA_UNIDADE%>" rows="4" cols="60"></textarea>
								
								</td>
								
							</tr>
							<tr>
								<th width="12%"  align="right"> Carga Hor�ria: </th>
								<td>
									<input type="text" id="<%=ServletDisciplina.NM_PARAMETRO_CAMPO_CARGA_HORARIA%>" name="<%=ServletDisciplina.NM_PARAMETRO_CAMPO_CARGA_HORARIA%>" value="">				
								</td>
							</tr>
						</tbody>
					</table>
					<br>
					<table width="50%" align="center">
						<tr>
							<td align="center">
								<button type="submit" id="botaoCadastrar" name="botaoCadastrar" onclick="cadastrar();">Cadastrar</button>				
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
<%@page import="java.util.Iterator"%>
<%@page import="br.com.educandariopassosfirmes.dao.TipoEnsinoDAO"%>
<%@page import="br.com.educandariopassosfirmes.entidades.TipoEnsino"%>
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
<title>Consultar Disciplina</title>

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

<form action="ServletDisciplina" method="post">
<input type="hidden" id="<%=ServletDisciplina.NM_EVENTO%>" name="<%=ServletDisciplina.NM_EVENTO%>" value="">
	<h2 align="center">CADASTRAR DISCIPLINA</h2>
	<table>
		<tbody>
			<tr>			
				<th width="12%" align="right"> Sigla Disciplina: </th>
				<td>
					<input type="text" id="<%=ServletDisciplina.NM_PARAMETRO_SIGLA_DISCIPLINA%>" name="<%=ServletDisciplina.NM_PARAMETRO_SIGLA_DISCIPLINA%>" value="">
				
				</td>			
			
				<th align="right"> Descrição Disciplina: </th>
				<td>
					<input type="text" id="<%=ServletDisciplina.NM_PARAMETRO_DS_DISCIPLINA%>" name="<%=ServletDisciplina.NM_PARAMETRO_DS_DISCIPLINA%>" value="" size="50">
				
				</td>
			</tr>
			<tr>
				<th width="15%"  align="right"> Assuntos 1ª Unidade: </th>
				<td>
					<textarea id="<%=ServletDisciplina.NM_PARAMETRO_TX_PRIMEIRA_UNIDADE%>" name="<%=ServletDisciplina.NM_PARAMETRO_TX_PRIMEIRA_UNIDADE%>" rows="4" cols="60"></textarea>
				
				</td>			
			
				<th align="right"> Assuntos 2ª Unidade: </th>
				<td>
					<textarea id="<%=ServletDisciplina.NM_PARAMETRO_TX_SEGUNDA_UNIDADE%>" name="<%=ServletDisciplina.NM_PARAMETRO_TX_SEGUNDA_UNIDADE%>" rows="4" cols="60"></textarea>
				
				</td>
				
			</tr>
			<tr>
				<th width="12%"  align="right"> Assuntos 3ª Unidade: </th>
				<td>
					<textarea id="<%=ServletDisciplina.NM_PARAMETRO_TX_TERCEIRA_UNIDADE%>" name="<%=ServletDisciplina.NM_PARAMETRO_TX_TERCEIRA_UNIDADE%>" rows="4" cols="60"></textarea>
				
				</td>			
			
				<th align="right"> Assuntos 4ª Unidade: </th>
				<td>
					<textarea id="<%=ServletDisciplina.NM_PARAMETRO_TX_QUARTA_UNIDADE%>" name="<%=ServletDisciplina.NM_PARAMETRO_TX_QUARTA_UNIDADE%>" rows="4" cols="60"></textarea>
				
				</td>
				
			</tr>
			<tr>
				<th width="12%"  align="right"> Carga Horária: </th>
				<td>
					<input type="text" id="<%=ServletDisciplina.NM_PARAMETRO_CAMPO_CARGA_HORARIA%>" name="<%=ServletDisciplina.NM_PARAMETRO_CAMPO_CARGA_HORARIA%>" value="">				
				</td>
				
				<th align="right"> Tipo de Ensino: </th>
				<td>
					<%
						TipoEnsinoDAO tipoEnsinoDAO = new TipoEnsinoDAO();
						ArrayList<TipoEnsino>consultaTipoEnsino = tipoEnsinoDAO.consultarTodosTipoEnsino();
						Iterator<TipoEnsino> itTipoEnsino = consultaTipoEnsino.iterator();
						int contador = 0;
						while(itTipoEnsino.hasNext()){
							TipoEnsino tipoEnsino = itTipoEnsino.next();
							
							if(itTipoEnsino.hasNext()){%>						
								<%=Select.getInstancia().getHTML(ServletDisciplina.NM_PARAMETRO_SELECT_TIPO_ENSINO, ServletDisciplina.NM_PARAMETRO_SELECT_TIPO_ENSINO, tipoEnsino.getCdTipoEnsino(), tipoEnsino.getDsTipoEnsino(), false, contador, false)%>
							<%}else{%>
								<%=Select.getInstancia().getHTML(ServletDisciplina.NM_PARAMETRO_SELECT_TIPO_ENSINO, ServletDisciplina.NM_PARAMETRO_SELECT_TIPO_ENSINO, tipoEnsino.getCdTipoEnsino(), tipoEnsino.getDsTipoEnsino(), false, contador, true)%>
							<%}
							contador++;
							%>
						<%}%>				
				</td>
			</tr>
		</tbody>
	</table>
	<br>
	<table>
	<tr>
		<td style="position: absolute; left: 30%; top: 60%;">
			<button type="submit" id="botaoCadastrar" name="botaoCadastrar" onclick="cadastrar();">Cadastrar</button>				
		</td>
			
		<td style="position: absolute; left: 60%; top: 60%;">
			<button type="submit" id="botaoDesistir" name="botaoDesistir" onclick="desistir();">Voltar</button>				
		</td>
	</tr>
	</table>
</form>

</body>



</html>
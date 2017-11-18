<%@page import="br.com.educandariopassosfirmes.dao.ProfessorDisciplinaDAO"%>
<%@page
	import="br.com.educandariopassosfirmes.entidades.ProfessorDisciplina"%>
<%@page import="br.com.educandariopassosfirmes.entidades.Disciplina"%>
<%@page import="br.com.educandariopassosfirmes.dao.DisciplinaDAO"%>
<%@page
	import="br.com.educandariopassosfirmes.servlet.ServletDisciplina"%>
<%@page import="java.util.LinkedHashMap"%>
<%@page
	import="br.com.educandariopassosfirmes.dao.ConsultaPrincipalProfessor"%>
<%@page import="br.com.educandariopassosfirmes.dao.ProfessorDAO"%>
<%@page
	import="br.com.educandariopassosfirmes.servlet.ServletProgramacao"%>
<%@page import="br.com.educandariopassosfirmes.entidades.Turma"%>
<%@page import="br.com.educandariopassosfirmes.dao.TurmaDAO"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page
	import="br.com.educandariopassosfirmes.servlet.ServletProgramacao"%>
<%@page import="br.com.educandariopassosfirmes.util.Select"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="cssProjeto.css">
<title>Cadastrar Programação</title>

</head>

<script type="text/javascript">

function desistir(){
	document.getElementById("<%=ServletProgramacao.NM_EVENTO%>").value = "<%=ServletProgramacao.NM_JSP_CONSULTAR%>";
}

function cadastrar(){
	var valorSelectTurno = document.getElementById("selectTurno").value;
	
	if(valorSelectTurno != 0){
		document.getElementById("<%=ServletProgramacao.NM_EVENTO%>").value = "<%=ServletProgramacao.NM_EVENTO_PROCESSAR_INCLUSAO%>";		
	}else{
		alert("Selecione um turno!");
		document.getElementById("<%=ServletProgramacao.NM_EVENTO%>").value = "<%=ServletProgramacao.NM_EVENTO_EXIBIR_INCLUSAO%>";
	}
}

function consultaSelectProfessor(pValue){
	var valorSelectTurno = pValue.value;

	document.getElementById("<%=ServletProgramacao.NM_EVENTO%>").value = "<%=ServletProgramacao.NM_EVENTO_PROCESSAR_CONSULTA_SELECT_PROFESSOR%>";
	document.frm_principal.submit();
}

function consultaSelectDisciplina(pValue){
	var valorSelectTurno = pValue.value;

	document.getElementById("<%=ServletProgramacao.NM_EVENTO%>").value = "<%=ServletProgramacao.NM_EVENTO_PROCESSAR_CONSULTA_SELECT_DISCIPLINA%>";
		document.frm_principal.submit();
	}
</script>

<%
	String eventoSelectProfessor = "";
	String eventoSelectDisciplina = "";
	String idProfessor = "";
	String idDisciplina = "";
	
	eventoSelectProfessor = (String)request.getAttribute(ServletProgramacao.NM_EVENTO_PROCESSAR_CONSULTA_SELECT_PROFESSOR);
	eventoSelectDisciplina = (String)request.getAttribute(ServletProgramacao.NM_EVENTO_PROCESSAR_CONSULTA_SELECT_DISCIPLINA);
	idProfessor = (String)request.getAttribute(ServletProgramacao.NM_PARAMETRO_SELECT_PROFESSOR);
	idDisciplina = (String)request.getAttribute(ServletProgramacao.NM_PARAMETRO_SELECT_DISCIPLINA);
	
	if(eventoSelectProfessor == null){
		eventoSelectProfessor = "";
	}
	
	if(eventoSelectDisciplina == null){
		eventoSelectDisciplina = "";
	}

	if(idProfessor == null){
		idProfessor = "";
	}else{
		if(idProfessor.equals("0")){
			idProfessor = "";
		}
	}

	if(idDisciplina == null){
		idDisciplina = "";
	}else{
		if(idDisciplina.equals("0")){
			idDisciplina = "";
		}
	}
%>

<body>

	<jsp:include page="cabecalho.jsp" />
	<a href="ServletMenu"
		style="font-size: 14px; font-family: Cooper Black; text-decoration: none; color: black;">
		<img width="60px" height="60px" src="img/pe.png" /><b>MENU</b>
	</a>
	<form name="frm_principal" action="ServletProgramacao" method="post">
		<input type="hidden" id="<%=ServletProgramacao.NM_EVENTO%>"
			name="<%=ServletProgramacao.NM_EVENTO%>" value="">
		<h2 align="center" style="margin-top: 0">CADASTRAR PROGRAMAÇÃO</h2>
		<table width="100%">
			<tr>
				<td>
					<table width="60%" align="center" style="background-color: #99CCFF">
						<tbody>
							<tr>
								<th align="right">Turma:</th>
								<td>
									<%
										TurmaDAO turmaDAO = new TurmaDAO();
										ArrayList<Turma> colecao = turmaDAO.consultar("", "", "");
										boolean ultimaTurma = false;
										for (int x = 0; x < colecao.size(); x++) {
											Turma turma = colecao.get(x);

											if (x + 1 == colecao.size()) {
												ultimaTurma = true;
											}
									%> <%=Select.getInstancia().getHTML(ServletProgramacao.NM_PARAMETRO_SIGLA_TURMA,
						ServletProgramacao.NM_PARAMETRO_SIGLA_TURMA, String.valueOf(turma.getIdTurma()),
						turma.getDsTurma(), false, x, ultimaTurma, "")%>
									<%
										}
									%>
								</td>
								<th align="right">Professor:</th>
								<td>
									<%
										if(!eventoSelectDisciplina.equals("")){
											ArrayList<LinkedHashMap<String, String>> colecaoProfessor = new ArrayList<LinkedHashMap<String, String>>();
											ConsultaPrincipalProfessor consulta = new ConsultaPrincipalProfessor();
	
											String javascript = "";
											if(idDisciplina.equals("")){
												javascript = "onchange='consultaSelectProfessor(this)';";
											}
											
											colecaoProfessor = consulta.consultar("", "", idDisciplina);
											boolean ultimoProfessor = false;
											for (int x = 0; x < colecaoProfessor.size(); x++) {
												LinkedHashMap<String, String> dados = colecaoProfessor.get(x);
	
												if (x + 1 == colecaoProfessor.size()) {
													ultimoProfessor = true;
												}%>
												
												<%=Select.getInstancia().getHTML(ServletProgramacao.NM_PARAMETRO_SELECT_PROFESSOR,
								ServletProgramacao.NM_PARAMETRO_SELECT_PROFESSOR, dados.get("ID_PESSOA"), dados.get("NOME"),
								false, x, ultimoProfessor, javascript)%>
									<%
											}
										}else{
										
											ArrayList<LinkedHashMap<String, String>> colecaoProfessor = new ArrayList<LinkedHashMap<String, String>>();
											ConsultaPrincipalProfessor consulta = new ConsultaPrincipalProfessor();
	
											colecaoProfessor = consulta.consultar("", "", "");
											boolean ultimoProfessor = false;
											boolean mesmoProfessor = false;
											for (int x = 0; x < colecaoProfessor.size(); x++) {
												LinkedHashMap<String, String> dados = colecaoProfessor.get(x);
	
												if (x + 1 == colecaoProfessor.size()) {
													ultimoProfessor = true;
												}
												
												if(idProfessor.equals(dados.get("ID_PESSOA"))){
													mesmoProfessor = true;
												}
	
										%> <%=Select.getInstancia().getHTML(ServletProgramacao.NM_PARAMETRO_SELECT_PROFESSOR,
								ServletProgramacao.NM_PARAMETRO_SELECT_PROFESSOR, dados.get("ID_PESSOA"), dados.get("NOME"),
								mesmoProfessor, x, ultimoProfessor, "onchange='consultaSelectProfessor(this)';")%>
										<%
											mesmoProfessor = false;
											}
										}
									%>
								</td>
								<th align="right">Disciplina:</th>
								<td>
									<%
										if(!eventoSelectProfessor.equals("")){
											ArrayList<LinkedHashMap<String, String>> colecaoProfessor2 = new ArrayList<LinkedHashMap<String, String>>();
											ConsultaPrincipalProfessor consulta2 = new ConsultaPrincipalProfessor();
											String javascript = "";
											if(idProfessor.equals("")){
												javascript = "onchange='consultaSelectDisciplina(this)';";
											}
											
											colecaoProfessor2 = consulta2.consultar(idProfessor, "", "");
											boolean ultimoProfessor2 = false;
											for (int x = 0; x < colecaoProfessor2.size(); x++) {
												LinkedHashMap<String, String> dados = colecaoProfessor2.get(x);

												if (x + 1 == colecaoProfessor2.size()) {
													ultimoProfessor2 = true;
												}

										%> <%=Select.getInstancia().getHTML(ServletProgramacao.NM_PARAMETRO_SELECT_DISCIPLINA,
								ServletProgramacao.NM_PARAMETRO_SELECT_DISCIPLINA, dados.get("ID_DISCIPLINA"), dados.get("DS_DISCIPLINA"),
								false, x, ultimoProfessor2, javascript)%>
										<%
											}
										}else{
											ArrayList<LinkedHashMap<String, String>> colecaoProfessor2 = new ArrayList<LinkedHashMap<String, String>>();
											ConsultaPrincipalProfessor consulta2 = new ConsultaPrincipalProfessor();

											colecaoProfessor2 = consulta2.consultar("", "", "");
											boolean ultimoDisciplina = false;
											boolean mesmaDisciplina = false;
											for (int x = 0; x < colecaoProfessor2.size(); x++) {
												LinkedHashMap<String, String> dados = colecaoProfessor2.get(x);

												if (x + 1 == colecaoProfessor2.size()) {
													ultimoDisciplina = true;
												}
												
											if(idDisciplina.equals(dados.get("ID_DISCIPLINA"))){
												mesmaDisciplina = true;
											}

									%> <%=Select.getInstancia().getHTML(ServletProgramacao.NM_PARAMETRO_SELECT_DISCIPLINA,
							ServletProgramacao.NM_PARAMETRO_SELECT_DISCIPLINA,
							dados.get("ID_DISCIPLINA"), dados.get("DS_DISCIPLINA"), mesmaDisciplina, x,
							ultimoDisciplina, "onchange='consultaSelectDisciplina(this)';")%>
									<%
											mesmaDisciplina = false;
											}
										}
									%>
								</td>
							</tr>
							<tr>
								<th align="right">Assuntos 1ª Unidade:</th>
								<td><textarea
										id="<%=ServletDisciplina.NM_PARAMETRO_TX_PRIMEIRA_UNIDADE%>"
										name="<%=ServletDisciplina.NM_PARAMETRO_TX_PRIMEIRA_UNIDADE%>"
										rows="4" cols="30"></textarea></td>

								<th align="right">Assuntos 2ª Unidade:</th>
								<td><textarea
										id="<%=ServletDisciplina.NM_PARAMETRO_TX_SEGUNDA_UNIDADE%>"
										name="<%=ServletDisciplina.NM_PARAMETRO_TX_SEGUNDA_UNIDADE%>"
										rows="4" cols="30"></textarea></td>
								<th align="right">Carga Horária:</th>
								<td><input type="text"
									id="<%=ServletDisciplina.NM_PARAMETRO_CAMPO_CARGA_HORARIA%>"
									name="<%=ServletDisciplina.NM_PARAMETRO_CAMPO_CARGA_HORARIA%>"
									value=""></td>
							</tr>
							<tr>
								<th align="right">Assuntos 3ª Unidade:</th>
								<td><textarea
										id="<%=ServletDisciplina.NM_PARAMETRO_TX_TERCEIRA_UNIDADE%>"
										name="<%=ServletDisciplina.NM_PARAMETRO_TX_TERCEIRA_UNIDADE%>"
										rows="4" cols="30"></textarea></td>

								<th align="right">Assuntos 4ª Unidade:</th>
								<td><textarea
										id="<%=ServletDisciplina.NM_PARAMETRO_TX_QUARTA_UNIDADE%>"
										name="<%=ServletDisciplina.NM_PARAMETRO_TX_QUARTA_UNIDADE%>"
										rows="4" cols="30"></textarea></td>

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
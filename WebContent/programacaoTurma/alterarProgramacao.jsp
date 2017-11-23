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
<title>Alterar Programação</title>

</head>

<script type="text/javascript">

function desistir(){
	document.getElementById("<%=ServletProgramacao.NM_EVENTO%>").value = "<%=ServletProgramacao.NM_JSP_CONSULTAR%>";
}

function alterar(){
	document.getElementById("<%=ServletProgramacao.NM_EVENTO%>").value = "<%=ServletProgramacao.NM_EVENTO_PROCESSAR_ALTERACAO%>";		
}

</script>

<%
	String idTurma = "";
	String idProfessor = "";
	String idDisciplina = "";
	
	String txPrimeiraUnidade = "";
	String txSegundaUnidade = "";
	String txTerceiraUnidade = "";
	String txQuartaUnidade = "";
	String cargaHoraria = "";
	
	idTurma = (String)request.getAttribute(ServletProgramacao.NM_PARAMETRO_SELECT_TURMA);
	idProfessor = (String)request.getAttribute(ServletProgramacao.NM_PARAMETRO_SELECT_PROFESSOR);
	idDisciplina = (String)request.getAttribute(ServletProgramacao.NM_PARAMETRO_SELECT_DISCIPLINA);
	
	txPrimeiraUnidade = (String)request.getAttribute(ServletProgramacao.NM_PARAMETRO_TX_PRIMEIRA_UNIDADE);
	txSegundaUnidade = (String)request.getAttribute(ServletProgramacao.NM_PARAMETRO_TX_SEGUNDA_UNIDADE);
	txTerceiraUnidade = (String)request.getAttribute(ServletProgramacao.NM_PARAMETRO_TX_TERCEIRA_UNIDADE);
	txQuartaUnidade = (String)request.getAttribute(ServletProgramacao.NM_PARAMETRO_TX_QUARTA_UNIDADE);
	cargaHoraria = (String)request.getAttribute(ServletProgramacao.NM_PARAMETRO_CAMPO_CARGA_HORARIA);
	
	if(idTurma == null){
		idTurma = "";
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
	
	if(txPrimeiraUnidade == null){
		txPrimeiraUnidade = "";
	}
	
	if(txSegundaUnidade == null){
		txSegundaUnidade = "";
	}
	
	if(txTerceiraUnidade == null){
		txTerceiraUnidade = "";
	}
	
	if(txQuartaUnidade == null){
		txQuartaUnidade = "";
	}
	
	if(cargaHoraria == null){
		cargaHoraria = "";
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
		<input type="hidden" id="<%=ServletProgramacao.NM_PARAMETRO_SELECT_TURMA%>"
			name="<%=ServletProgramacao.NM_PARAMETRO_SELECT_TURMA%>" value="<%=idTurma%>">
		<input type="hidden" id="<%=ServletProgramacao.NM_PARAMETRO_SELECT_PROFESSOR%>"
			name="<%=ServletProgramacao.NM_PARAMETRO_SELECT_PROFESSOR%>" value="<%=idProfessor%>">
		<input type="hidden" id="<%=ServletProgramacao.NM_PARAMETRO_SELECT_DISCIPLINA%>"
			name="<%=ServletProgramacao.NM_PARAMETRO_SELECT_DISCIPLINA%>" value="<%=idDisciplina%>">
		<h2 align="center" style="margin-top: 0">ALTERAR PROGRAMAÇÃO</h2>
		<table width="100%">
			<tr>
				<td>
					<table width="80%" align="center" style="background-color: #99CCFF">
						<tbody>
							<tr>
								<th align="left">Turma:</th>
								<td>
									<%
										TurmaDAO turmaDAO = new TurmaDAO();
										ArrayList<Turma> colecao = turmaDAO.consultar("", "", "");
										boolean ultimaTurma = false;
										boolean mesmaTurma = false;
										for (int x = 0; x < colecao.size(); x++) {
											Turma turma = colecao.get(x);

											if (x + 1 == colecao.size()) {
												ultimaTurma = true;
											}
											
											if(idTurma.equals(turma.getIdTurma())){
												mesmaTurma = true;
											}
									%> <%=Select.getInstancia().getHTML(ServletProgramacao.NM_PARAMETRO_SELECT_TURMA,
						ServletProgramacao.NM_PARAMETRO_SELECT_TURMA, turma.getIdTurma(),
						turma.getDsTurma(), mesmaTurma, x, ultimaTurma, "disabled")%>
									<%
										mesmaTurma = false;
										}
									%>
								</td>
								<th align="left">Professor:</th>
								<td>
									<%
										
											ArrayList<LinkedHashMap<String, String>> colecaoProfessor = new ArrayList<LinkedHashMap<String, String>>();
											ConsultaPrincipalProfessor consulta = new ConsultaPrincipalProfessor();
	
											colecaoProfessor = consulta.consultar("", "", "", false);
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
								mesmoProfessor, x, ultimoProfessor, "disabled")%>
										<%
											mesmoProfessor = false;
											}
										
									%>
								</td>
								<th align="left">Disciplina:</th>
								<td>
									<%
											ArrayList<LinkedHashMap<String, String>> colecaoProfessor2 = new ArrayList<LinkedHashMap<String, String>>();
											ConsultaPrincipalProfessor consulta2 = new ConsultaPrincipalProfessor();

											colecaoProfessor2 = consulta2.consultar("", "", "", true);
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
							ultimoDisciplina, "disabled")%>
									<%
											mesmaDisciplina = false;
											}
										
									%>
								</td>
							</tr>
							<tr>
								<th align="left">Assuntos 1ª Unidade:</th>
								<td><textarea
										id="<%=ServletDisciplina.NM_PARAMETRO_TX_PRIMEIRA_UNIDADE%>"
										name="<%=ServletDisciplina.NM_PARAMETRO_TX_PRIMEIRA_UNIDADE%>"
										rows="4" cols="30"><%=txPrimeiraUnidade%></textarea></td>

								<th align="left">Assuntos 2ª Unidade:</th>
								<td><textarea
										id="<%=ServletDisciplina.NM_PARAMETRO_TX_SEGUNDA_UNIDADE%>"
										name="<%=ServletDisciplina.NM_PARAMETRO_TX_SEGUNDA_UNIDADE%>"
										rows="4" cols="30"><%=txSegundaUnidade%></textarea></td>
								<th align="left">Carga Horária:</th>
								<td><input type="text"
									id="<%=ServletDisciplina.NM_PARAMETRO_CAMPO_CARGA_HORARIA%>"
									name="<%=ServletDisciplina.NM_PARAMETRO_CAMPO_CARGA_HORARIA%>"
									value="<%=cargaHoraria.equals("0") ? "" : cargaHoraria%>"></td>
							</tr>
							<tr>
								<th align="left">Assuntos 3ª Unidade:</th>
								<td><textarea
										id="<%=ServletDisciplina.NM_PARAMETRO_TX_TERCEIRA_UNIDADE%>"
										name="<%=ServletDisciplina.NM_PARAMETRO_TX_TERCEIRA_UNIDADE%>"
										rows="4" cols="30"><%=txTerceiraUnidade%></textarea></td>

								<th align="left">Assuntos 4ª Unidade:</th>
								<td><textarea
										id="<%=ServletDisciplina.NM_PARAMETRO_TX_QUARTA_UNIDADE%>"
										name="<%=ServletDisciplina.NM_PARAMETRO_TX_QUARTA_UNIDADE%>"
										rows="4" cols="30"><%=txQuartaUnidade%></textarea></td>

							</tr>
						</tbody>
					</table> <br>
					<table width="50%" align="center">
						<tr>
							<td style="width: 600px; text-align: center">
								<button type="submit" id="botaoAlterar" name="botaoAlterar"
									onclick="alterar();">Alterar</button>
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
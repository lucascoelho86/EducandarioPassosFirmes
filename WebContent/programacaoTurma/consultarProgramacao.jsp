<%@page import="br.com.educandariopassosfirmes.dao.ConsultaPrincipalProfessor"%>
<%@page import="java.util.LinkedHashMap"%>
<%@page import="br.com.educandariopassosfirmes.servlet.ServletProgramacao"%>
<%@page import="br.com.educandariopassosfirmes.dao.TurmaDAO"%>
<%@page import="java.util.Iterator"%>
<%@page import="br.com.educandariopassosfirmes.entidades.Turma"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="br.com.educandariopassosfirmes.servlet.ServletProgramacao"%>
<%@page import="br.com.educandariopassosfirmes.util.Select"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="cssProjeto.css">
<title>Consultar Programação</title>

</head>
<SCRIPT language="JavaScript" type="text/javascript"
	src="js/biblioteca.js"></SCRIPT>
<script type="text/javascript">

function consultar(){
	document.getElementById("<%=ServletProgramacao.NM_EVENTO%>").value = "<%=ServletProgramacao.NM_EVENTO_CONSULTAR_TODOS%>";
	document.frm_principal.submit();
}

function excluir(){
	document.getElementById("<%=ServletProgramacao.NM_EVENTO%>").value = "<%=ServletProgramacao.NM_EVENTO_EXCLUIR%>";
	
	if (isRadioButtonConsultaSelecionado("document.frm_principal.rdb_consulta")){
		document.frm_principal.submit();		
	}else{
		alert("Selecione uma opção!");
	}
}

function exibirAlteracao(){
	document.getElementById("<%=ServletProgramacao.NM_EVENTO%>").value = "<%=ServletProgramacao.NM_EVENTO_EXIBIR_ALTERACAO%>";
	
	if (isRadioButtonConsultaSelecionado("document.frm_principal.rdb_consulta")){
		document.frm_principal.submit();		
	}else{
		alert("Selecione uma turma!");
	}
}

function exibirInclusao(){
	document.getElementById("<%=ServletProgramacao.NM_EVENTO%>").value = "<%=ServletProgramacao.NM_EVENTO_EXIBIR_INCLUSAO%>";
		document.frm_principal.submit();
}

function consultaSelectProfessor(){
	document.getElementById("<%=ServletProgramacao.NM_EVENTO%>").value = "<%=ServletProgramacao.NM_EVENTO_PROCESSAR_CONSULTA_SELECT_PROFESSOR%>";
	document.frm_principal.submit();
}

function consultaSelectDisciplina(){
	document.getElementById("<%=ServletProgramacao.NM_EVENTO%>").value = "<%=ServletProgramacao.NM_EVENTO_PROCESSAR_CONSULTA_SELECT_DISCIPLINA%>";
	document.frm_principal.submit();
}
</script>

<%
	String idTurma;
	String eventoSelectDisciplina = "";
	String eventoSelectProfessor = "";
	String idProfessor = "";
	String idDisciplina = "";
	ArrayList<LinkedHashMap<String, String>> colecaoTurma;
	Iterator<LinkedHashMap<String, String>> itTurma;
	idTurma = (String) request.getAttribute(ServletProgramacao.NM_PARAMETRO_SELECT_TURMA);
	colecaoTurma = (ArrayList<LinkedHashMap<String, String>>) request
			.getAttribute(ServletProgramacao.NM_PARAMETRO_COLECAO_TURMA);
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
	
	if (colecaoTurma == null) {
		colecaoTurma = new ArrayList<LinkedHashMap<String, String>>();
		itTurma = colecaoTurma.iterator();
	} else {
		itTurma = colecaoTurma.iterator();
	}
%>

<body>

	<jsp:include page="cabecalho.jsp" />
	<a href = "ServletMenu" style="font-size: 14px; font-family: Cooper Black; text-decoration: none; color: black;"> <img width="60px" height="60px" src="img/pe.png"/><b>MENU</b></a>
	<form name="frm_principal" action="ServletProgramacao" method="post">
		<input type="hidden" id="<%=ServletProgramacao.NM_EVENTO%>"
			name="<%=ServletProgramacao.NM_EVENTO%>" value="">
		<input type="hidden" id="<%=ServletProgramacao.NM_EVENTO_TELA%>"
			name="<%=ServletProgramacao.NM_EVENTO_TELA%>" value="<%=ServletProgramacao.NM_JSP_CONSULTAR%>">
		<h2 align="center">CONSULTAR PROGRAMAÇÃO</h2>
		<table width="100%">
			<tr>
				<td>
					<table width="70%" align="center">
						<tbody>
							<tr>
								<td align="center">
								<h4 align="center" style="margin-top:0; margin-bottom: 0;">Código Turma:
								<%	TurmaDAO turmaDAO = new TurmaDAO();
									ArrayList<Turma>colecao = turmaDAO.consultar("", "", "");
									boolean ultimaTurma = false;
									boolean mesmaTurma = false;
									for(int x = 0; x < colecao.size(); x++){
										Turma turma = colecao.get(x);
										
										if(x + 1 == colecao.size()){
											ultimaTurma = true;
										}
										
										if(idTurma.equals(turma.getIdTurma())){
											mesmaTurma = true;
										}
										%>
															
										<%=Select.getInstancia().getHTML(ServletProgramacao.NM_PARAMETRO_SELECT_TURMA, ServletProgramacao.NM_PARAMETRO_SELECT_TURMA, turma.getIdTurma(), turma.getDsTurma(), mesmaTurma, x, ultimaTurma, "")%>
									<%
									mesmaTurma = false;
									}%>
									</h4>
								</td>
								<th align="right">Professor:</th>
								<td>
									<%
										if(!eventoSelectDisciplina.equals("")){
											ArrayList<LinkedHashMap<String, String>> colecaoProfessor = new ArrayList<LinkedHashMap<String, String>>();
											ConsultaPrincipalProfessor consulta = new ConsultaPrincipalProfessor();
	
											String javascript = "";
											if(idDisciplina.equals("")){
												javascript = "onchange='consultaSelectProfessor()';";
											}
											
											colecaoProfessor = consulta.consultar("", "", idDisciplina, false, false);
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
	
											colecaoProfessor = consulta.consultar("", "", "", false, false);
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
								mesmoProfessor, x, ultimoProfessor, "onchange='consultaSelectProfessor()';")%>
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
												javascript = "onchange='consultaSelectDisciplina()';";
											}
											
											colecaoProfessor2 = consulta2.consultar(idProfessor, "", "", true, true);
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

											colecaoProfessor2 = consulta2.consultar("", "", "", true, true);
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
							ultimoDisciplina, "onchange='consultaSelectDisciplina()';")%>
									<%
											mesmaDisciplina = false;
											}
										}
									%>
								</td>
								<td>
									<button type="submit" id="botaoLocalizar" name="botaoLocalizar"
										onclick="consultar();">Localizar</button>
								</td>
							</tr>
						</tbody>
					</table> <br>
					<table width="50%" align="center">
						<tr class="cabecalhoRetornoDados">
							<TH align="center" width="1%">X</TH>
							<TH align="left" width="3%">Turma</TH>
							<TH align="left" width="10%">Professor</TH>
							<TH align="left" width="3%">Disciplina</TH>
						</tr>
						<%
							String cssCorlinha;
							boolean tratamentoCSS = true;
							String chave = "";
							while (itTurma.hasNext()) {
								LinkedHashMap<String, String> turma = itTurma.next();
								chave = turma.get("ID_TURMA") + ";" + turma.get("ID_PESSOA") + ";" + turma.get("ID_DISCIPLINA");
								if (tratamentoCSS) {
									cssCorlinha = "#c0c0c0";
									tratamentoCSS = false;
								} else {
									cssCorlinha = "#ffffff";
									tratamentoCSS = true;
								}
						%>
						<tr style="background-color: <%=cssCorlinha%>">
							<td align="center">
								<%
									if (colecaoTurma.size() == 1) {
										%> <input type="radio" id="rdb_consulta"
										name="<%=ServletProgramacao.NM_PARAMETRO_CHAVE%>" value="<%=chave%>"
										checked> <%
 									} else {
										 %> <input type="radio" id="rdb_consulta"
										name="<%=ServletProgramacao.NM_PARAMETRO_CHAVE%>" value="<%=chave%>">
								<%
									}
								%>
							</td>
							<td><%=turma.get("DS_TURMA").toUpperCase()%></td>
							<td><%=turma.get("NOME").toUpperCase()%></td>
							<td><%=turma.get("DS_DISCIPLINA").toUpperCase()%></td>
						</tr>


						<%
							}
						%>
					</table> <br>
					<table width="50%" align="center">
						<tr>
							<td style="width: 600px; text-align: center">
								<button type="button" id="botaoCadastrar" name="botaoCadastrar"
									onclick="exibirInclusao();">Incluir</button>

							</td>

							<%
								if (!colecaoTurma.isEmpty()) {
							%>
							<td style="width: 700px; text-align: center">
								<button type="button" id="botaoAlterar" name="botaoAlterar"
									onclick="exibirAlteracao();">Alterar</button>

							</td>
							<td style="width: 800px; text-align: center">
								<button type="button" id="botaoExcluir" name="botaoExcluir"
									onclick="excluir();">Excluir</button>
							</td>
							<%
								} else {
							%>
							<td style="width: 700px; text-align: center">
								<button type="button" id="botaoAlterar" name="botaoAlterar"
									onclick="exibirAlteracao();" disabled>Alterar</button>

							</td>
							<td style="width: 800px; text-align: center">
								<button type="button" id="botaoExcluir" name="botaoExcluir"
									onclick="excluir();" disabled>Excluir</button>
							</td>
							<%
								}
							%>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</form>

</body>



</html>
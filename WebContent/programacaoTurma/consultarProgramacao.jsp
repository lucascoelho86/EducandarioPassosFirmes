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
		alert("Selecione uma turma!");
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
</script>

<%
	ArrayList<Turma> colecaoTurma;
	Iterator<Turma> itTurma;
	colecaoTurma = (ArrayList<Turma>) request
			.getAttribute(ServletProgramacao.NM_PARAMETRO_COLECAO_TURMA);

	if (colecaoTurma == null) {
		colecaoTurma = new ArrayList<Turma>();
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
		<h2 align="center">CONSULTAR PROGRAMAÇÃO</h2>
		<table width="100%">
			<tr>
				<td>
					<table width="50%" align="center">
						<tbody>
							<tr>
								<td align="center">
								<h4 align="center" style="margin-top:0; margin-bottom: 0;">Código Turma:
								<%	TurmaDAO turmaDAO = new TurmaDAO();
									ArrayList<Turma>colecao = turmaDAO.consultar("", "", "");
									boolean ultimaTurma = false;
									for(int x = 0; x < colecao.size(); x++){
										Turma turma = colecao.get(x);
										
										if(x + 1 == colecao.size()){
											ultimaTurma = true;
										}%>
															
										<%=Select.getInstancia().getHTML(ServletProgramacao.NM_PARAMETRO_SIGLA_TURMA, ServletProgramacao.NM_PARAMETRO_SIGLA_TURMA, String.valueOf(turma.getIdTurma()), turma.getDsTurma(), false, x, ultimaTurma, "")%>
									<%}%>
									<button type="submit" id="botaoLocalizar" name="botaoLocalizar"
										onclick="consultar();">Localizar</button>
									</h4>
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
								Turma turma = itTurma.next();
								chave = turma.getIdTurma() + ";" + turma.getDsTurma() + ";"
										+ turma.getTurno() + ";" + turma.getQtMaxAlunos();
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
							<td style="width: 310px; display: block; text-align: left"><%=turma.getDsTurma().toUpperCase()%></td>
							<td><%=turma.getTurno().toUpperCase()%></td>
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
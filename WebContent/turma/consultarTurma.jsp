<%@page import="java.util.Iterator"%>
<%@page import="br.com.educandariopassosfirmes.entidades.Turma"%>
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
<title>Consultar Turma</title>

</head>
<SCRIPT language="JavaScript" type="text/javascript"
	src="js/biblioteca.js"></SCRIPT>
<script type="text/javascript">

function consultar(){
	document.getElementById("<%=ServletTurma.NM_EVENTO%>").value = "<%=ServletTurma.NM_EVENTO_CONSULTAR_TODOS%>";
	document.frm_principal.submit();
}

function excluir(){
	document.getElementById("<%=ServletTurma.NM_EVENTO%>").value = "<%=ServletTurma.NM_EVENTO_EXCLUIR%>";
	
	if (isRadioButtonConsultaSelecionado("document.frm_principal.rdb_consulta")){
		document.frm_principal.submit();		
	}else{
		alert("Selecione uma turma!");
	}
}

function imprimirChamada(){
	document.getElementById("<%=ServletTurma.NM_EVENTO%>").value = "<%=ServletTurma.NM_EVENTO_CHAMADA%>";
	
	if (isRadioButtonConsultaSelecionado("document.frm_principal.rdb_consulta")){
		document.frm_principal.submit();		
	}else{
		alert("Selecione uma turma!");
	}
}

function exibirAlteracao(){
	document.getElementById("<%=ServletTurma.NM_EVENTO%>").value = "<%=ServletTurma.NM_EVENTO_EXIBIR_ALTERACAO%>";
	
	if (isRadioButtonConsultaSelecionado("document.frm_principal.rdb_consulta")){
		document.frm_principal.submit();		
	}else{
		alert("Selecione uma turma!");
	}
}

function exibirInclusao(){
	document.getElementById("<%=ServletTurma.NM_EVENTO%>").value = "<%=ServletTurma.NM_EVENTO_EXIBIR_INCLUSAO%>";
		document.frm_principal.submit();
}
</script>

<%
	ArrayList<Turma> colecaoTurma;
	Iterator<Turma> itTurma;
	String sigla = "";
	String descricao = "";
	String turno = "";
	colecaoTurma = (ArrayList<Turma>) request
			.getAttribute(ServletTurma.NM_PARAMETRO_COLECAO_TURMA);

	sigla = (String) request.getAttribute(ServletTurma.NM_PARAMETRO_SIGLA_TURMA);
	descricao = (String) request.getAttribute(ServletTurma.NM_PARAMETRO_DS_TURMA);
	turno = (String) request.getAttribute(ServletTurma.NM_PARAMETRO_SELECT_TURNO);
	
	if (colecaoTurma == null) {
		colecaoTurma = new ArrayList<Turma>();
		itTurma = colecaoTurma.iterator();
	} else {
		itTurma = colecaoTurma.iterator();
	}
	
	if(sigla == null){
		sigla = "";
	}
	
	if(descricao == null){
		descricao = "";
	}
	
	if(turno == null){
		turno = "";
	}
%>

<body>

	<jsp:include page="cabecalho.jsp" />
	<a href = "ServletMenu" style="font-size: 14px; font-family: Cooper Black; text-decoration: none; color: black;"> <img width="60px" height="60px" src="img/pe.png"/><b>MENU</b></a>
	<form name="frm_principal" action="ServletTurma" method="post">
		<input type="hidden" id="<%=ServletTurma.NM_EVENTO%>"
			name="<%=ServletTurma.NM_EVENTO%>" value="">
		<h2 align="center">CONSULTAR TURMA</h2>
		<table width="100%">
			<tr>
				<td>
					<table width="70%" align="center">
						<tbody>
							<tr>
								<th>Sigla Turma:</th>
								<td><input type="text"
									id="<%=ServletTurma.NM_PARAMETRO_SIGLA_TURMA%>"
									name="<%=ServletTurma.NM_PARAMETRO_SIGLA_TURMA%>" value="<%=sigla%>" maxlength="5" size="5"></td>
								
								<th>Descrição Turma:</th>
								<td><input type="text"
									id="<%=ServletTurma.NM_PARAMETRO_DS_TURMA%>"
									name="<%=ServletTurma.NM_PARAMETRO_DS_TURMA%>" value="<%=descricao%>"
									size="40"></td>

								<th align="right">Turno:</th>
								<td>
									<%
										int contador = 0;
										boolean mesmoTurno = false;
										for (int x = 0; x < 2; x++) {

											if(turno.equals(String.valueOf(x))){
												mesmoTurno = true;
											}
											
											if (x == 0) {
														%> <%=Select.getInstancia()
												.getHTML(ServletTurma.NM_PARAMETRO_SELECT_TURNO,
														ServletTurma.NM_PARAMETRO_SELECT_TURNO,
														String.valueOf(x + 1), ServletTurma.NM_TURNO_MANHA, mesmoTurno,
														contador, false, "")%> <%
 											} else {
					 							%> <%=Select.getInstancia().getHTML(
												ServletTurma.NM_PARAMETRO_SELECT_TURNO,
												ServletTurma.NM_PARAMETRO_SELECT_TURNO, String.valueOf(x + 1),
												ServletTurma.NM_TURNO_TARDE, mesmoTurno, contador, true, "")%> <%
 											}
 										contador++;
									 %> <%
									 	mesmoTurno = false;
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
							<TH align="left" width="5%">Descrição Turma</TH>
							<TH align="left" width="2%">Turno</TH>
							<TH align="left" width="1%">Sala</TH>
						</tr>
						<%
							String cssCorlinha;
							boolean tratamentoCSS = true;
							String chave = "";
							while (itTurma.hasNext()) {
								Turma turma = itTurma.next();
								chave = turma.getIdTurma();
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
										name="<%=ServletTurma.NM_PARAMETRO_CHAVE%>" value="<%=chave%>"
										checked> <%
 									} else {
										 %> <input type="radio" id="rdb_consulta"
										name="<%=ServletTurma.NM_PARAMETRO_CHAVE%>" value="<%=chave%>">
								<%
									}
								%>
							</td>
							<td style="width: 310px; display: block; text-align: left"><%=turma.getDsTurma().toUpperCase()%></td>
							<td><%=turma.getTurno().toUpperCase()%></td>
							<td><%=turma.getSala()%></td>
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
							
							<td style="width: 700px; text-align: center">
								<button type="button" id="botaoChamada" name="botaoChamada"
									onclick="imprimirChamada();">Imprimir Chamada</button>
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
							
							<td style="width: 700px; text-align: center">
								<button type="button" id="botaoChamada" name="botaoChamada"
									onclick="imprimirChamada();" disabled>Imprimir Chamada</button>
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
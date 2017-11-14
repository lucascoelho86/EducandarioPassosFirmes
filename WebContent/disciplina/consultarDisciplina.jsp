<%@page import="java.util.Iterator"%>
<%@page import="br.com.educandariopassosfirmes.entidades.Disciplina"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="br.com.educandariopassosfirmes.servlet.ServletDisciplina"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="cssProjeto.css">
<title>Consultar Disciplina</title>

</head>
<SCRIPT language="JavaScript" type="text/javascript" src="js/biblioteca.js"></SCRIPT>
<script type="text/javascript">

function consultar(){
	document.getElementById("<%=ServletDisciplina.NM_EVENTO%>").value = "<%=ServletDisciplina.NM_EVENTO_CONSULTAR_TODOS%>";
	document.frm_principal.submit();
}

function excluir(){
	document.getElementById("<%=ServletDisciplina.NM_EVENTO%>").value = "<%=ServletDisciplina.NM_EVENTO_EXCLUIR%>";
	
	if (isRadioButtonConsultaSelecionado("document.frm_principal.rdb_consulta")){
		document.frm_principal.submit();		
	}else{
		alert("Selecione uma disciplina!");
	}
}

function exibirAlteracao(){
	document.getElementById("<%=ServletDisciplina.NM_EVENTO%>").value = "<%=ServletDisciplina.NM_EVENTO_EXIBIR_ALTERACAO%>";
	
	if (isRadioButtonConsultaSelecionado("document.frm_principal.rdb_consulta")){
		document.frm_principal.submit();		
	}else{
		alert("Selecione uma disciplina!");
	}
}

function exibirInclusao(){
	document.getElementById("<%=ServletDisciplina.NM_EVENTO%>").value = "<%=ServletDisciplina.NM_EVENTO_EXIBIR_INCLUSAO%>";
	document.frm_principal.submit();
}

</script>

<%

ArrayList<Disciplina>colecaoDisciplina;
Iterator<Disciplina>itDisciplina;
colecaoDisciplina = (ArrayList<Disciplina>) request.getAttribute(ServletDisciplina.NM_PARAMETRO_COLECAO_DISCIPLINA);

if(colecaoDisciplina == null){
	colecaoDisciplina = new ArrayList<Disciplina>();
	itDisciplina = colecaoDisciplina.iterator();
}else{
	itDisciplina = colecaoDisciplina.iterator();
}

%>

<body>

<jsp:include page="cabecalho.jsp"/>
<a href = "ServletMenu" style="font-size: 14px; font-family: Cooper Black; text-decoration: none; color: black;"> <img width="60px" height="60px" src="img/pe.png"/><b>MENU</b></a>
<form name="frm_principal" action="ServletDisciplina" method="post">
<input type="hidden" id="<%=ServletDisciplina.NM_EVENTO%>" name="<%=ServletDisciplina.NM_EVENTO%>" value="">
	<h2 align="center">CONSULTAR DISCIPLINA</h2>
	<table width="100%">
		<tr>
			<td>
				<table width="50%" align="center">
					<tbody>
						<tr>
						
							<th width="15%"> Sigla Disciplina: </th>
							<td>
								<input type="text" id="<%=ServletDisciplina.NM_PARAMETRO_SIGLA_DISCIPLINA%>" name="<%=ServletDisciplina.NM_PARAMETRO_SIGLA_DISCIPLINA%>" value="">
							
							</td>			
						
							<th align="right"> Descrição Disciplina: </th>
							<td>
								<input type="text" id="<%=ServletDisciplina.NM_PARAMETRO_DS_DISCIPLINA%>" name="<%=ServletDisciplina.NM_PARAMETRO_DS_DISCIPLINA%>" value="" size="50">
							
							</td>
							<td>
								<button type="submit" id="botaoLocalizar" name="botaoLocalizar" onclick="consultar();">Localizar</button>
							
							</td>
						
						</tr>
					</tbody>
				</table>
				<br>
				<table class="tituloRetornoDados" width="50%" align="center">
				<tr class="cabecalhoRetornoDados">
					<TH align="left" width="1%">X</TH>
					<TH align="left" width="3%" >Sigla Disciplina</TH>
					<TH align="left" width="10%" >Descrição Disciplina</TH>
				</tr>
					<%
					String chave = "";
					String cssCorlinha;
					boolean tratamentoCSS = true;
					while(itDisciplina.hasNext()){
						Disciplina disciplina = itDisciplina.next();
						chave = disciplina.getIdDisciplina() + ";" + disciplina.getSiglaDisciplina() + ";" + disciplina.getDsDisciplina();
						if(tratamentoCSS){
							cssCorlinha = "#c0c0c0";
							tratamentoCSS = false;
						}else{
							cssCorlinha = "#ffffff";
							tratamentoCSS = true;
						}
						%>
						<tr style="background-color: <%=cssCorlinha%>">
							<td>
							<%if(colecaoDisciplina.size() == 1){ %>
								<input type="radio" id="rdb_consulta" name="<%=ServletDisciplina.NM_PARAMETRO_CHAVE%>" value="<%=chave%>" checked>
							<%}else{%>
								<input type="radio" id="rdb_consulta" name="<%=ServletDisciplina.NM_PARAMETRO_CHAVE%>" value="<%=chave%>">
							<%}%>
							</td>
							<td><%=disciplina.getSiglaDisciplina().toUpperCase() %></td>
							<td><%=disciplina.getDsDisciplina().toUpperCase() %></td>
						</tr>
						
						
						<%		
					}
				%>
				</table>
				<br>
				<table width="50%" align="center">
					<tr>
						<td style="width:600px; text-align: center">
								<button type="button" id="botaoCadastrar" name="botaoCadastrar" onclick="exibirInclusao();">Incluir</button>
								
						</td>
						<%if(!colecaoDisciplina.isEmpty()){%>
						<td style="width:700px; text-align: center">
								<button type="button" id="botaoAlterar" name="botaoAlterar" onclick="exibirAlteracao();">Alterar</button>
								
						</td>
						<td style="width:800px; text-align: center">
								<button type="button" id="botaoExcluir" name="botaoExcluir" onclick="excluir();">Excluir</button>
								
						</td>
						<%}else{%>
						<td style="width:700px; text-align: center">
								<button type="button" id="botaoAlterar" name="botaoAlterar" onclick="exibirAlteracao();" disabled>Alterar</button>
								
						</td>
						<td style="width:800px; text-align: center">
								<button type="button" id="botaoExcluir" name="botaoExcluir" onclick="excluir();" disabled>Excluir</button>
								
						</td>
						<%}%>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</form>

</body>



</html>
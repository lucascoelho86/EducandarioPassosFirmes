<%@page import="java.util.LinkedHashMap"%>
<%@page import="br.com.educandariopassosfirmes.entidades.Aluno"%>
<%@page import="br.com.educandariopassosfirmes.entidades.Turma"%>
<%@page import="br.com.educandariopassosfirmes.dao.TurmaDAO"%>
<%@page import="br.com.educandariopassosfirmes.util.BibliotecaFormatarDados"%>
<%@page import="java.util.Iterator"%>
<%@page import="br.com.educandariopassosfirmes.entidades.Pessoa"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="br.com.educandariopassosfirmes.servlet.ServletAluno"%>
<%@page import="br.com.educandariopassosfirmes.util.Select"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="cssProjeto.css">
<title>Consultar Professor</title>

</head>
<SCRIPT language="JavaScript" type="text/javascript" src="js/biblioteca.js"></SCRIPT>
<script type="text/javascript">

function consultar(){
	document.getElementById("<%=ServletAluno.NM_EVENTO%>").value = "<%=ServletAluno.NM_EVENTO_CONSULTAR_TODOS%>";
	document.frm_principal.submit();		
}

function excluir(){
	document.getElementById("<%=ServletAluno.NM_EVENTO%>").value = "<%=ServletAluno.NM_EVENTO_EXCLUIR%>";
	
	if (isRadioButtonConsultaSelecionado("document.frm_principal.rdb_consulta")){
		document.frm_principal.submit();		
	}else{
		alert("Selecione um professor!");
	}
}

function exibirAlteracao(){
	document.getElementById("<%=ServletAluno.NM_EVENTO%>").value = "<%=ServletAluno.NM_EVENTO_EXIBIR_ALTERACAO%>";

	if (isRadioButtonConsultaSelecionado("document.frm_principal.rdb_consulta")){
		document.frm_principal.submit();		
	}else{
		alert("Selecione um professor!");
	}
}

function exibirInclusao(){
	document.getElementById("<%=ServletAluno.NM_EVENTO%>").value = "<%=ServletAluno.NM_EVENTO_EXIBIR_INCLUSAO%>";
	document.frm_principal.submit();
}

</script>

<%

ArrayList<LinkedHashMap<String, String>>colecaoPessoa;
Iterator<LinkedHashMap<String, String>>itPessoa;
String matricula = "";
String nome = "";
String turmaTela = "";

matricula = (String)request.getAttribute(ServletAluno.NM_PARAMETRO_MATRICULA);
nome = (String)request.getAttribute(ServletAluno.NM_PARAMETRO_NOME);
turmaTela = (String)request.getAttribute(ServletAluno.NM_PARAMETRO_SELECT_TURMA);
colecaoPessoa = (ArrayList<LinkedHashMap<String, String>>) request.getAttribute(ServletAluno.NM_PARAMETRO_COLECAO_PESSOA);

if(colecaoPessoa == null){
	colecaoPessoa = new ArrayList<LinkedHashMap<String, String>>();
	itPessoa = colecaoPessoa.iterator();
}else{
	itPessoa = colecaoPessoa.iterator();
}

if(matricula == null){
	matricula = "";
}

if(nome == null){
	nome = "";
}

if(turmaTela == null){
	turmaTela = "";
}

%>

<body>

<jsp:include page="cabecalho.jsp"/>
<a href = "ServletMenu" style="font-size: 14px; font-family: Cooper Black; text-decoration: none; color: black;"> <img width="60px" height="60px" src="img/pe.png"/><b>MENU</b></a>
<form name="frm_principal" action="ServletAluno" method="post">
<input type="hidden" id="<%=ServletAluno.NM_EVENTO%>" name="<%=ServletAluno.NM_EVENTO%>" value="">
	<h2 align="center">CONSULTAR ALUNO</h2>
	<table width="100%">
		<tr>
			<td>
				<table width="50%" align="center">
					<tbody>
						<tr>
							<th width="10%" align="right"> Matrícula: </th>
							<td>
								<input type="text" id="<%=ServletAluno.NM_PARAMETRO_MATRICULA%>" name="<%=ServletAluno.NM_PARAMETRO_MATRICULA%>" value="<%=matricula%>" size="14" onkeyup="formatarMatricula(event);" onkeypress='return SomenteNumero(event)' maxlength="13">
							</td>
						
							<th width="10%" align="right"> Nome: </th>
							<td>
								<input type="text" id="<%=ServletAluno.NM_PARAMETRO_NOME%>" name="<%=ServletAluno.NM_PARAMETRO_NOME%>" value="<%=nome%>" size="50" onkeypress='return letras(event)'>
							
							</td>
						
							<th width="10%" align="right"> Turma: </th>
							<td>
								<%	TurmaDAO turmaDAO = new TurmaDAO();
									ArrayList<Turma>colecaoTurma = turmaDAO.consultar("", "", "");
									boolean ultimaTurma = false;
									boolean mesmaTurma = false;
									for(int x = 0; x < colecaoTurma.size(); x++){
										Turma turma = colecaoTurma.get(x);
										
										if(x + 1 == colecaoTurma.size()){
											ultimaTurma = true;
										}
										
										if(turmaTela.equals(turma.getIdTurma())){
											mesmaTurma = true;
										}
										%>	
															
										<%=Select.getInstancia().getHTML(ServletAluno.NM_PARAMETRO_SELECT_TURMA, ServletAluno.NM_PARAMETRO_SELECT_TURMA, turma.getIdTurma(), turma.getDsTurma(), mesmaTurma, x, ultimaTurma, "")%>
									<%
									mesmaTurma = false;
									}%>				
							</td>
							<td>
								<input type="button" id="botaoLocalizar" name="botaoLocalizar" onclick="consultar();" value="Localizar">
							
							</td>
						
						</tr>
					</tbody>
				</table>
				<br>
				<table width="50%" align="center">
				<tr class="cabecalhoRetornoDados">
					<TH align="left" width="1%">X</TH>
					<TH align="left" width="2%" >Matrícula</TH>
					<TH align="left" width="10%" >Nome</TH>
					<TH align="left" width="2%" >Turma</TH>
				</tr>
					<%
					String chave = "";
					String cssCorlinha;
					boolean tratamentoCSS = true;
					while(itPessoa.hasNext()){
						LinkedHashMap<String, String> pessoa = itPessoa.next();
						chave = pessoa.get("ID_PESSOA");
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
							<%if(colecaoPessoa.size() == 1){ %>
								<input type="radio" id="rdb_consulta" name="<%=ServletAluno.NM_PARAMETRO_CHAVE%>" value="<%=chave%>" checked>
							<%}else{%>
								<input type="radio" id="rdb_consulta" name="<%=ServletAluno.NM_PARAMETRO_CHAVE%>" value="<%=chave%>">
							<%}%>
							</td>
							<td><%=BibliotecaFormatarDados.formatarMatricula(pessoa.get("ID_PESSOA"))%></td>
							<td><%=pessoa.get("NOME").toUpperCase() %></td>
							<td><%=pessoa.get("DS_TURMA").toUpperCase() %></td>
						</tr>
						
						
						<%		
					}
				%>
				</table>
				<br>
				<table  width="50%" align="center">
					<tr>
						<td style="width:600px; text-align: center">
								<button type="button" id="botaoCadastrar" name="botaoCadastrar" onclick="exibirInclusao();">Incluir</button>
								
						</td>
						<%if(!colecaoPessoa.isEmpty()){%>
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
<%@page import="br.com.educandariopassosfirmes.util.BibliotecaFormatarDados"%>
<%@page import="java.util.Iterator"%>
<%@page import="br.com.educandariopassosfirmes.entidades.Pessoa"%>
<%@page import="br.com.educandariopassosfirmes.entidades.Disciplina"%>
<%@page import="br.com.educandariopassosfirmes.dao.DisciplinaDAO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="br.com.educandariopassosfirmes.servlet.ServletProfessor"%>
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
	document.getElementById("<%=ServletProfessor.NM_EVENTO%>").value = "<%=ServletProfessor.NM_EVENTO_CONSULTAR_TODOS%>";
	document.frm_principal.submit();		
}

function excluir(){
	document.getElementById("<%=ServletProfessor.NM_EVENTO%>").value = "<%=ServletProfessor.NM_EVENTO_EXCLUIR%>";
	
	if (isRadioButtonConsultaSelecionado("document.frm_principal.rdb_consulta")){
		document.frm_principal.submit();		
	}else{
		alert("Selecione um professor!");
	}
}

function exibirAlteracao(){
	document.getElementById("<%=ServletProfessor.NM_EVENTO%>").value = "<%=ServletProfessor.NM_EVENTO_EXIBIR_ALTERACAO%>";

	if (isRadioButtonConsultaSelecionado("document.frm_principal.rdb_consulta")){
		document.frm_principal.submit();		
	}else{
		alert("Selecione um professor!");
	}
}

function exibirInclusao(){
	document.getElementById("<%=ServletProfessor.NM_EVENTO%>").value = "<%=ServletProfessor.NM_EVENTO_EXIBIR_INCLUSAO%>";
	document.frm_principal.submit();
}

</script>

<%

ArrayList<Pessoa>colecaoPessoa;
Iterator<Pessoa>itPessoa;
colecaoPessoa = (ArrayList<Pessoa>) request.getAttribute(ServletProfessor.NM_PARAMETRO_COLECAO_PESSOA);

if(colecaoPessoa == null){
	colecaoPessoa = new ArrayList<Pessoa>();
	itPessoa = colecaoPessoa.iterator();
}else{
	itPessoa = colecaoPessoa.iterator();
}

%>

<body>

<jsp:include page="cabecalho.jsp"/>

<form name="frm_principal" action="ServletProfessor" method="post">
<input type="hidden" id="<%=ServletProfessor.NM_EVENTO%>" name="<%=ServletProfessor.NM_EVENTO%>" value="">
	<h2 align="center">CONSULTAR PROFESSOR</h2>
	<table width="100%">
		<tr>
			<td>
				<table width="50%" align="center">
					<tbody>
						<tr>
							<th width="10%" align="right"> CPF: </th>
							<td>
								<input type="text" id="<%=ServletProfessor.NM_PARAMETRO_CPF%>" name="<%=ServletProfessor.NM_PARAMETRO_CPF%>" value="" size="14" onkeyup="formatarCPF(event);" maxlength="14"
									onkeypress='return SomenteNumero(event)'>
							
							</td>
						
							<th width="10%" align="right"> Nome: </th>
							<td>
								<input type="text" id="<%=ServletProfessor.NM_PARAMETRO_NOME%>" name="<%=ServletProfessor.NM_PARAMETRO_NOME%>" value="" size="50" onkeypress='return letras(event)'>
							
							</td>
						
							<th width="10%" align="right"> Disciplina: </th>
							<td>
								<%	DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
									ArrayList<Disciplina>colecaoDisciplina = disciplinaDAO.consultar(0, "", "");
									boolean ultimaDisciplina = false;
									for(int x = 0; x < colecaoDisciplina.size(); x++){
										Disciplina disciplina = colecaoDisciplina.get(x);
										
										if(x + 1 == colecaoDisciplina.size()){
											ultimaDisciplina = true;
										}%>	
															
										<%=Select.getInstancia().getHTML(ServletProfessor.NM_PARAMETRO_SELECT_DISCIPLINA, ServletProfessor.NM_PARAMETRO_SELECT_DISCIPLINA, String.valueOf(disciplina.getDsDisciplina()), disciplina.getDsDisciplina(), false, x, ultimaDisciplina)%>
									<%}%>				
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
					<TH align="left" width="3%" >CPF</TH>
					<TH align="left" width="10%" >Nome</TH>
				</tr>
					<%
					String chave = "";
					String cssCorlinha;
					boolean tratamentoCSS = true;
					while(itPessoa.hasNext()){
						Pessoa pessoa = itPessoa.next();
						chave = pessoa.getId();
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
								<input type="radio" id="rdb_consulta" name="<%=ServletProfessor.NM_PARAMETRO_CHAVE%>" value="<%=chave%>" checked>
							<%}else{%>
								<input type="radio" id="rdb_consulta" name="<%=ServletProfessor.NM_PARAMETRO_CHAVE%>" value="<%=chave%>">
							<%}%>
							</td>
							<td><%=BibliotecaFormatarDados.formatarCPF(pessoa.getId())%></td>
							<td><%=pessoa.getNome().toUpperCase() %></td>
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
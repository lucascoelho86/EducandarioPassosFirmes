<%@page import="java.util.Locale"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="br.com.educandariopassosfirmes.entidades.ProfessorDisciplina"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="br.com.educandariopassosfirmes.entidades.Disciplina"%>
<%@page import="br.com.educandariopassosfirmes.dao.DisciplinaDAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="br.com.educandariopassosfirmes.servlet.ServletProfessor"%>
<%@page import="br.com.educandariopassosfirmes.util.Select"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="cssProjeto.css">
<title>Alterar Professor</title>

</head>
<script type="text/javascript" language="javascript" src="js/jquery-1.3.2-vsdoc2.js"></script>
<SCRIPT language="JavaScript" type="text/javascript"
	src="js/biblioteca.js"></SCRIPT>

<script type="text/javascript">

function desistir(){
	window.history.back();
}

function alterar(){
	document.getElementById("<%=ServletProfessor.NM_EVENTO%>").value = "<%=ServletProfessor.NM_EVENTO_PROCESSAR_ALTERACAO%>";
	var valorCPF = document.getElementById("<%=ServletProfessor.NM_PARAMETRO_CPF%>").value;
	var valorSelectFormacao = document.getElementById("<%=ServletProfessor.NM_PARAMETRO_FORMACAO%>").value;
	var tamanhoValorCPF = valorCPF.length;
	
	if (tamanhoValorCPF == 14 && valorSelectFormacao != 0) {
		document.frm_principal.submit();
	}else if(tamanhoValorCPF < 14){
		alert("CPF obrigatório!");
	}else if(valorSelectFormacao == 0){
		alert("Selecione uma formação!");
	}
}

function mostrarDP(){
	if(document.getElementById("idTableDisciplinas").style.visibility == "hidden"){
		document.getElementById("idTableDisciplinas").style.visibility = "visible";
	}else{
		document.getElementById("idTableDisciplinas").style.visibility = "hidden";
	}
	
	if(document.getElementById("idTableDisciplinas2").style.visibility == "hidden"){
		document.getElementById("idTableDisciplinas2").style.visibility = "visible";
	}else{
		document.getElementById("idTableDisciplinas2").style.visibility = "hidden";
	}
}
</script>
<script type="text/javascript">
		
	jQuery(document).ready(function(){

		//Muda o CSS da linha de adicionar evento, quando passa o mouse por cima
	  	jQuery("td.adicionarEvento").mouseover(function(){
	    	jQuery("td.adicionarEvento").css("background-color","#D8E1ED");
	    	jQuery("td.adicionarEvento").css("cursor","pointer");
	  	});
	  	jQuery("td.adicionarEvento").mouseout(function(){
	    	jQuery("td.adicionarEvento").css("background-color","#E9E9E4");
	  	});

		//Função de clicar na linha de adicionar evento	
		jQuery("td.adicionarEvento").click(function(){		
			mostrarDP();	
		});	
		
	});
</script>

<%
	String nome = "";
	String dtNascimento = "";
	String naturalidade = "";
	String endereco = "";
	String numero = "";
	String bairro = "";
	String cidade = "";
	String estado = "";
	String telefone = "";
	String identidade = "";
	String cpf = "";
	String formacao = "";
	String estadoCivil = "";
	String qtDependente = "";
	String dtAdmissao = "";
	String cargaHoraria = "";
	Double salario;
	ArrayList<ProfessorDisciplina> colecaoProfessorDisciplina = null;

	nome = (String) request.getAttribute(ServletProfessor.NM_PARAMETRO_NOME);
	dtNascimento = (String) request.getAttribute(ServletProfessor.NM_PARAMETRO_DT_NASCIMENTO);
	naturalidade = (String) request.getAttribute(ServletProfessor.NM_PARAMETRO_NATURALIDADE);
	endereco = (String) request.getAttribute(ServletProfessor.NM_PARAMETRO_ENDERECO);
	numero = (String) request.getAttribute(ServletProfessor.NM_PARAMETRO_NUMERO);
	bairro = (String) request.getAttribute(ServletProfessor.NM_PARAMETRO_BAIRRO);
	cidade = (String) request.getAttribute(ServletProfessor.NM_PARAMETRO_CIDADE);
	estado = (String) request.getAttribute(ServletProfessor.NM_PARAMETRO_ESTADO);
	telefone = (String) request.getAttribute(ServletProfessor.NM_PARAMETRO_TELEFONE);
	identidade = (String) request.getAttribute(ServletProfessor.NM_PARAMETRO_IDENTIDADE);
	cpf = (String) request.getAttribute(ServletProfessor.NM_PARAMETRO_CPF);
	formacao = (String) request.getAttribute(ServletProfessor.NM_PARAMETRO_FORMACAO);
	estadoCivil = (String) request.getAttribute(ServletProfessor.NM_PARAMETRO_ESTADO_CIVIL);
	qtDependente = (String) request.getAttribute(ServletProfessor.NM_PARAMETRO_QT_DEPENDENTE);
	dtAdmissao = (String) request.getAttribute(ServletProfessor.NM_PARAMETRO_DT_ADMISSAO);
	cargaHoraria = (String) request.getAttribute(ServletProfessor.NM_PARAMETRO_CARGA_HORARIA);
	salario = (Double) request.getAttribute(ServletProfessor.NM_PARAMETRO_SALARIO);
	colecaoProfessorDisciplina = (ArrayList<ProfessorDisciplina>) request.getAttribute(ServletProfessor.NM_PARAMETRO_COLECAO_PROFESSOR_DISCIPLINA);
	BigDecimal valor = new BigDecimal (String.valueOf(salario));
	NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
	String salarioFormatado = nf.format(valor);
	salarioFormatado = salarioFormatado.replace("R$", "");
	salarioFormatado = salarioFormatado.trim();
%>

<body
	onload="formatarCPFOnload(); formatarCamposDataOnload(); formatarCampoTelefoneOnload();">

	<jsp:include page="cabecalho.jsp" />
	<a href = "ServletMenu" style="font-size: 14px; font-family: Cooper Black; text-decoration: none; color: black;"> <img width="60px" height="60px" src="img/pe.png"/><b>MENU</b></a>
	<form name="frm_principal" action="ServletProfessor" method="post">
		<input type="hidden" id="<%=ServletProfessor.NM_EVENTO%>"
			name="<%=ServletProfessor.NM_EVENTO%>" value="">
		<input type="hidden" id="<%=ServletProfessor.NM_PARAMETRO_CPF_ANTERIOR%>"
			name="<%=ServletProfessor.NM_PARAMETRO_CPF_ANTERIOR%>" value="<%=cpf%>">
		<h2 align="center">ALTERAR PROFESSOR</h2>
		<table width="100%">
			<tr>
				<td>
					<table width="100%" align="center" style="background-color: #99CCFF">
						<tbody>
							<tr>
								<th width="13%" align="left">Nome:</th>
								<td><input type="text"
									id="<%=ServletProfessor.NM_PARAMETRO_NOME%>"
									name="<%=ServletProfessor.NM_PARAMETRO_NOME%>"
									value="<%=nome%>" size="40" onkeypress='return letras(event)'></td>

								<th width="15%" align="left">Data de Nascimento:</th>
								<td><input type="text"
									id="<%=ServletProfessor.NM_PARAMETRO_DT_NASCIMENTO%>"
									name="<%=ServletProfessor.NM_PARAMETRO_DT_NASCIMENTO%>"
									onkeyup="formatarCamposData(this.name, this, event)"
									value="<%=dtNascimento.equals("null") ? "" : dtNascimento%>"
									onkeypress='return SomenteNumero(event)' maxlength="10"></td>

								<th width="10%" align="left">Naturalidade:</th>
								<td><input type="text"
									id="<%=ServletProfessor.NM_PARAMETRO_NATURALIDADE%>"
									name="<%=ServletProfessor.NM_PARAMETRO_NATURALIDADE%>"
									value="<%=naturalidade%>" onkeypress='return letras(event)'></td>
							</tr>
							<tr>
								<th align="left">Endereço:</th>
								<td><input type="text"
									id="<%=ServletProfessor.NM_PARAMETRO_ENDERECO%>"
									name="<%=ServletProfessor.NM_PARAMETRO_ENDERECO%>"
									value="<%=endereco%>" size="40"></td>

								<th align="left">Número:</th>
								<td><input type="text"
									id="<%=ServletProfessor.NM_PARAMETRO_NUMERO%>"
									name="<%=ServletProfessor.NM_PARAMETRO_NUMERO%>"
									value="<%=numero.equals("0") ? "" : numero%>" onkeypress='return SomenteNumero(event)'></td>

								<th align="left">Bairro:</th>
								<td><input type="text"
									id="<%=ServletProfessor.NM_PARAMETRO_BAIRRO%>"
									name="<%=ServletProfessor.NM_PARAMETRO_BAIRRO%>"
									value="<%=bairro%>" onkeypress='return letras(event)'></td>
							</tr>
							<tr>
								<th align="left">Cidade:</th>
								<td><input type="text"
									id="<%=ServletProfessor.NM_PARAMETRO_CIDADE%>"
									name="<%=ServletProfessor.NM_PARAMETRO_CIDADE%>"
									value="<%=cidade%>" size="20" onkeypress='return letras(event)'></td>

								<th align="left">Estado:</th>
								<td><input type="text"
									id="<%=ServletProfessor.NM_PARAMETRO_ESTADO%>"
									name="<%=ServletProfessor.NM_PARAMETRO_ESTADO%>"
									value="<%=estado%>" onkeypress='return letras(event)'></td>

								<th align="left">Telefone:</th>
								<td><input type="text"
									id="<%=ServletProfessor.NM_PARAMETRO_TELEFONE%>"
									name="<%=ServletProfessor.NM_PARAMETRO_TELEFONE%>"
									value="<%=telefone%>"
									onkeyup="formatarCampoTelefone(this.name, this, event);"
									onkeypress='return SomenteNumero(event)'></td>
							</tr>
							<tr>
								<th align="left">Identidade:</th>
								<td><input type="text"
									id="<%=ServletProfessor.NM_PARAMETRO_IDENTIDADE%>"
									name="<%=ServletProfessor.NM_PARAMETRO_IDENTIDADE%>"
									value="<%=identidade%>" size="20"
									onkeypress='return SomenteNumero(event)'></td>

								<th align="left">CPF:</th>
								<td><input type="text"
									id="<%=ServletProfessor.NM_PARAMETRO_CPF%>"
									name="<%=ServletProfessor.NM_PARAMETRO_CPF%>" value="<%=cpf%>"
									onkeyup="formatarCPF(event);"
									onkeypress='return SomenteNumero(event)' maxlength="14"></td>

								<th align="left">Formação:</th>
								<td>
									<%
										int contador = 0;
									boolean formacaoSelecionada = false;
										for (int x = 0; x < 3; x++) {

											if (x + 1 == Integer.valueOf(formacao)) {
												formacaoSelecionada = true;
											}
											if (x == 0) {
									%> <%=Select.getInstancia().getHTML(ServletProfessor.NM_PARAMETRO_FORMACAO,
							ServletProfessor.NM_PARAMETRO_FORMACAO, String.valueOf(x + 1),
							ServletProfessor.NM_MAGISTERIO, formacaoSelecionada, contador, false, "")%><%
							
											}else if (x == 1) {%>
												<%=Select.getInstancia().getHTML(ServletProfessor.NM_PARAMETRO_FORMACAO,
														ServletProfessor.NM_PARAMETRO_FORMACAO, String.valueOf(x + 1),
														ServletProfessor.NM_PEDAGOGIA, formacaoSelecionada, contador, false, "")%><%
 	} else {
 %> <%=Select.getInstancia().getHTML(ServletProfessor.NM_PARAMETRO_FORMACAO,
							ServletProfessor.NM_PARAMETRO_FORMACAO, String.valueOf(x + 1),
							ServletProfessor.NM_OUTROS, formacaoSelecionada, contador, true, "")%> <%
 	}
 		contador++;
 %> <%
 formacaoSelecionada = false;
 	}
 %>
								</td>
							</tr>
							<tr>
								<th align="left">Estado Civil:</th>
								<td><input type="text"
									id="<%=ServletProfessor.NM_PARAMETRO_ESTADO_CIVIL%>"
									name="<%=ServletProfessor.NM_PARAMETRO_ESTADO_CIVIL%>"
									value="<%=estadoCivil%>" size="20"
									onkeypress='return letras(event)'></td>

								<th align="left">Quantidade Dependentes:</th>
								<td><input type="text"
									id="<%=ServletProfessor.NM_PARAMETRO_QT_DEPENDENTE%>"
									name="<%=ServletProfessor.NM_PARAMETRO_QT_DEPENDENTE%>"
									value="<%=qtDependente.equals("0") ? "" : qtDependente%>"
									onkeypress='return SomenteNumero(event)'></td>

								<th align="left">Data Admissão:</th>
								<td><input type="text"
									id="<%=ServletProfessor.NM_PARAMETRO_DT_ADMISSAO%>"
									name="<%=ServletProfessor.NM_PARAMETRO_DT_ADMISSAO%>"
									value="<%=dtAdmissao.equals("null") ? "" : dtAdmissao%>"
									onkeyup="formatarCamposData(this.name, this, event)"
									onkeypress='return SomenteNumero(event)' maxlength="10"></td>
							</tr>
							<tr>
								<th align="left">Carga Horária Semanal:</th>
								<td><input type="text"
									id="<%=ServletProfessor.NM_PARAMETRO_CARGA_HORARIA%>"
									name="<%=ServletProfessor.NM_PARAMETRO_CARGA_HORARIA%>"
									value="<%=cargaHoraria.equals("0") ? "" : cargaHoraria%>" size="10"
									onkeypress='return SomenteNumero(event)'></td>

								<th align="left">Salário:</th>
								<td><input type="text"
									id="<%=ServletProfessor.NM_PARAMETRO_SALARIO%>"
									name="<%=ServletProfessor.NM_PARAMETRO_SALARIO%>"
									value="<%=salarioFormatado%>"
									onKeydown="Formata(this,20,event,2)"
									onkeypress='return SomenteNumero(event)'></td>
							</tr>
						</tbody>
					</table>
					<br>
					<table width="50%" align="center">
						<tr>
							<td align="center">
								<button type="button" id="botaoAlterar" name="botaoAlterar"
									onclick="alterar();">Alterar</button>
							</td>

							<td align="center">
								<button type="button" id="botaoDesistir" name="botaoDesistir"
									onclick="desistir();">Voltar</button>
							</td>
						</tr>
					</table> <br>
					<table align="center">
						<tr>
							<TD class="adicionarEvento" colspan="9" align="center" style="border-color: #A5B9D7; border-style: dotted;">
								<p><b>+ ADICIONAR OU REMOVER DISCIPLINA AO PROFESSOR(A) +</b></p>
							</TD>
						</tr>
					</table>
					<br>
					<table id="idTableDisciplinas" width="90%" align="center" style="background-color: #99CCFF; visibility: hidden;">
						<tr>
							<TH align="center" width="1%">X</TH>
							<TH align="left" width="10%" >Disciplinas</TH>
						</tr>
					</table>
					<table id="idTableDisciplinas2" width="90%" align="center" style="visibility: hidden;">
						<%	DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
						ArrayList<Disciplina>colecaoDisciplina = disciplinaDAO.consultar(0, "", "");
						boolean ultimaDisciplina = false;
						String cssCorlinha = "";
						String checked = "";
						boolean tratamentoCSS = true;
						for(int x = 0; x < colecaoDisciplina.size(); x++){
							Disciplina disciplina = colecaoDisciplina.get(x);
							
							if(tratamentoCSS){
								cssCorlinha = "#c0c0c0";
								tratamentoCSS = false;
							}else{
								cssCorlinha = "#ffffff";
								tratamentoCSS = true;
							}
							
							Iterator<ProfessorDisciplina>itProfessorDisciplina = colecaoProfessorDisciplina.iterator();							
							while(itProfessorDisciplina.hasNext()){
								ProfessorDisciplina professorDisciplina = itProfessorDisciplina.next();
								
								
								if(disciplina.getIdDisciplina() == professorDisciplina.getId_disciplina()){
									
									checked = "checked";
								
								}
							}%>	
							<tr style="background-color: <%=cssCorlinha%>">
								<th align="center" width="1%">
									<input type="checkbox" id=<%=ServletProfessor.NM_PARAMETRO_ID_DISCIPLINA + x%> name="<%=ServletProfessor.NM_PARAMETRO_ID_DISCIPLINA + x%>" value="<%=disciplina.getIdDisciplina()%>" <%=checked%>>
									<input type="hidden" id=<%=ServletProfessor.NM_PARAMETRO_TAMANHO_COLECAO_DISCIPLINA%> name="<%=ServletProfessor.NM_PARAMETRO_TAMANHO_COLECAO_DISCIPLINA%>" value="<%=String.valueOf(colecaoDisciplina.size())%>">
								</th>
								<TH align="left" width="11%" ><%=disciplina.getDsDisciplina().toUpperCase()%></TH>
							</tr>
							
							
						<%
						checked = "";
						}%>
					</table>
				</td>
			</tr>
		</table>
		<br>
	</form>
</body>



</html>
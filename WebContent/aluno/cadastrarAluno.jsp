<%@page
	import="br.com.educandariopassosfirmes.util.BibliotecaFormatarDados"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="br.com.educandariopassosfirmes.entidades.Aluno"%>
<%@page import="br.com.educandariopassosfirmes.dao.AlunoDAO"%>
<%@page import="br.com.educandariopassosfirmes.util.Select"%>
<%@page import="br.com.educandariopassosfirmes.entidades.Turma"%>
<%@page import="br.com.educandariopassosfirmes.dao.TurmaDAO"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="br.com.educandariopassosfirmes.entidades.Disciplina"%>
<%@page import="br.com.educandariopassosfirmes.dao.DisciplinaDAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="br.com.educandariopassosfirmes.servlet.ServletAluno"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="cssProjeto.css">
<title>Cadastrar Aluno</title>

</head>
<script type="text/javascript" language="javascript"
	src="js/jquery-1.3.2-vsdoc2.js"></script>
<SCRIPT language="JavaScript" type="text/javascript"
	src="js/biblioteca.js"></SCRIPT>
<script type="text/javascript">

function desistir(){
	window.history.back();
}

function cadastrar(){
	document.getElementById("<%=ServletAluno.NM_EVENTO%>").value = "<%=ServletAluno.NM_EVENTO_PROCESSAR_INCLUSAO%>";
	var valorCPF = document.getElementById("<%=ServletAluno.NM_PARAMETRO_CPF%>").value;
	var tamanhoValorCPF = valorCPF.length;
	var valorSelectTurma = document.getElementById("<%=ServletAluno.NM_PARAMETRO_TURMA%>").value;
	
	if(tamanhoValorCPF == 14 && valorSelectTurma > "0"){
		document.frm_principal.submit();
	}else if(tamanhoValorCPF < 14){
		alert("CPF do responsável obrigatório!");
	}else if(valorSelectTurma == "0"){
		alert("Selecione uma turma!");
	}
	
}

function mostrarDP() {
	document.getElementById("dsNecEspecial").style.visibility = "visible";
}

function esconderDP() {
	document.getElementById("dsNecEspecial").style.visibility = "hidden";
}

function copiar() {
	document.getElementById("<%=ServletAluno.NM_PARAMETRO_CEP_RESP%>").value = document.getElementById("<%=ServletAluno.NM_PARAMETRO_CEP%>").value;
	document.getElementById("<%=ServletAluno.NM_PARAMETRO_ENDERECO_RESP%>").value = document.getElementById("<%=ServletAluno.NM_PARAMETRO_ENDERECO%>").value;
	document.getElementById("<%=ServletAluno.NM_PARAMETRO_NUMERO_RESP%>").value = document.getElementById("<%=ServletAluno.NM_PARAMETRO_NUMERO%>").value;
	document.getElementById("<%=ServletAluno.NM_PARAMETRO_BAIRRO_RESP%>").value = document.getElementById("<%=ServletAluno.NM_PARAMETRO_BAIRRO%>").value;
	document.getElementById("<%=ServletAluno.NM_PARAMETRO_CIDADE_RESP%>").value = document.getElementById("<%=ServletAluno.NM_PARAMETRO_CIDADE%>").value;
	document.getElementById("<%=ServletAluno.NM_PARAMETRO_ESTADO_RESP%>").value = document.getElementById("<%=ServletAluno.NM_PARAMETRO_ESTADO%>").value;
}

function consultarResponsavel(pCampo){
	var tamanhoCampo = pCampo.length;
	
	if(tamanhoCampo == 14){
		document.getElementById("<%=ServletAluno.NM_EVENTO%>").value = "<%=ServletAluno.NM_EVENTO_RESPONSAVEL_CADASTRADO%>";
		document.getElementById("<%=ServletAluno.NM_PARAMETRO_TELA_EVENTO%>").value = "<%=ServletAluno.NM_JSP_INCLUIR_ALUNO%>";
		document.frm_principal.submit();
	}
}
</script>
<script type="text/javascript">
	jQuery(document).ready(function() {

		//Função de clicar na linha de adicionar evento	
		jQuery("input.adicionarEvento").click(function() {
			mostrarDP();
		});

		//Função de clicar na linha de esconder evento	
		jQuery("input.esconderEvento").click(function() {
			esconderDP();
		});

	});
</script>

<%
	String nome = (String) request
			.getAttribute(ServletAluno.NM_PARAMETRO_NOME);
	String dtNascimento = (String) request
			.getAttribute(ServletAluno.NM_PARAMETRO_DT_NASCIMENTO);
	String naturalidade = (String) request
			.getAttribute(ServletAluno.NM_PARAMETRO_NATURALIDADE);
	String cep = (String) request
			.getAttribute(ServletAluno.NM_PARAMETRO_CEP);
	String endereco = (String) request
			.getAttribute(ServletAluno.NM_PARAMETRO_ENDERECO);
	String numero = (String) request
			.getAttribute(ServletAluno.NM_PARAMETRO_NUMERO);
	String bairro = (String) request
			.getAttribute(ServletAluno.NM_PARAMETRO_BAIRRO);
	String cidade = (String) request
			.getAttribute(ServletAluno.NM_PARAMETRO_CIDADE);
	String estado = (String) request
			.getAttribute(ServletAluno.NM_PARAMETRO_ESTADO);
	String certidaoNasc = (String) request
			.getAttribute(ServletAluno.NM_PARAMETRO_CERTIDAO_NASC);
	String carteiraEstudante = (String) request
			.getAttribute(ServletAluno.NM_PARAMETRO_CARTEIRA_ESTUDANTE);
	String necEspecial = (String) request
			.getAttribute(ServletAluno.NM_PARAMETRO_NECESSIDADE_ESPECIAL);
	String dsNecEspecial = (String) request
			.getAttribute(ServletAluno.NM_PARAMETRO_DS_NECESSIDADE_ESPECIAL);
	String turmaTela = (String) request
			.getAttribute(ServletAluno.NM_PARAMETRO_TURMA);
	
	if(turmaTela == null){
		turmaTela = "";
	}

	String nomeResp = (String) request
			.getAttribute(ServletAluno.NM_PARAMETRO_NOME_RESP);
	Date dtNascimentoResp = (Date) request
			.getAttribute(ServletAluno.NM_PARAMETRO_DT_NASCIMENTO_RESP);
	String naturalidadeResp = (String) request
			.getAttribute(ServletAluno.NM_PARAMETRO_NATURALIDADE_RESP);
	String cepResp = (String) request
			.getAttribute(ServletAluno.NM_PARAMETRO_CEP_RESP);
	String enderecoResp = (String) request
			.getAttribute(ServletAluno.NM_PARAMETRO_ENDERECO_RESP);
	String numeroResp = (String) request
			.getAttribute(ServletAluno.NM_PARAMETRO_NUMERO_RESP);
	String bairroResp = (String) request
			.getAttribute(ServletAluno.NM_PARAMETRO_BAIRRO_RESP);
	String cidadeResp = (String) request
			.getAttribute(ServletAluno.NM_PARAMETRO_CIDADE_RESP);
	String estadoResp = (String) request
			.getAttribute(ServletAluno.NM_PARAMETRO_ESTADO_RESP);
	String telefone = (String) request
			.getAttribute(ServletAluno.NM_PARAMETRO_TELEFONE);
	String identidade = (String) request
			.getAttribute(ServletAluno.NM_PARAMETRO_IDENTIDADE);
	String cpf = (String) request
			.getAttribute(ServletAluno.NM_PARAMETRO_CPF);

	String parentesco = (String) request
			.getAttribute(ServletAluno.NM_PARAMETRO_PARENTESCO);
	String estadoCivil = (String) request
			.getAttribute(ServletAluno.NM_PARAMETRO_ESTADO_CIVIL);
	String escolaridade = (String) request
			.getAttribute(ServletAluno.NM_PARAMETRO_ESCOLARIDADE);
	String profissao = (String) request
			.getAttribute(ServletAluno.NM_PARAMETRO_PROFISSAO);
	String salarioFormatado = (String) request
			.getAttribute(ServletAluno.NM_PARAMETRO_RENDA);
	String telaEvento = (String) request
			.getAttribute(ServletAluno.NM_PARAMETRO_TELA_EVENTO);
%>

<body onload="formatarCPFOnload(); formatarCampoTelefoneOnload(); formatarCampoCEPOnload(); formatarCamposDataAlunoOnload();">

	<jsp:include page="cabecalho.jsp" />
	<a href="ServletMenu"
		style="font-size: 14px; font-family: Cooper Black; text-decoration: none; color: black;">
		<img width="60px" height="60px" src="img/pe.png" /><b>MENU</b>
	</a>
	<form name="frm_principal" action="ServletAluno" method="post">
		<input type="hidden" id="<%=ServletAluno.NM_EVENTO%>"
			name="<%=ServletAluno.NM_EVENTO%>" value="">
		<input type="hidden" id="<%=ServletAluno.NM_PARAMETRO_TELA_EVENTO%>"
			name="<%=ServletAluno.NM_PARAMETRO_TELA_EVENTO%>" value="">
		<h2 align="center">CADASTRAR ALUNO</h2>
		<table width="100%">
			<tr>
				<td>
					<table width="100%" align="center" style="background-color: #99CCFF">
						<tbody>
							<tr>
								<th width="13%" align="left">Nome:</th>
								<td><input type="text"
									id="<%=ServletAluno.NM_PARAMETRO_NOME%>"
									name="<%=ServletAluno.NM_PARAMETRO_NOME%>"
									value="<%=nome == null ? "" : nome%>" size="40"
									onkeypress='return letras(event)'></td>

								<th width="12%" align="left">Data de Nascimento:</th>
								<td><input type="text"
									id="<%=ServletAluno.NM_PARAMETRO_DT_NASCIMENTO%>"
									name="<%=ServletAluno.NM_PARAMETRO_DT_NASCIMENTO%>"
									value="<%=dtNascimento == null ? "" : dtNascimento%>"
									onkeyup="formatarCamposData(this.name, this, event)"
									onkeypress='return SomenteNumero(event)' maxlength="10"></td>

								<th width="10%" align="left">CEP:</th>
								<td><input type="text"
									id="<%=ServletAluno.NM_PARAMETRO_CEP%>"
									name="<%=ServletAluno.NM_PARAMETRO_CEP%>"
									value="<%=cep == null ? "" : cep%>"
									onkeyup="formatarCampoCEP(event)" maxlength="9"
									onkeypress='return SomenteNumero(event)'></td>
							</tr>
							<tr>
								<th width="13%" align="left">Endereço:</th>
								<td><input type="text"
									id="<%=ServletAluno.NM_PARAMETRO_ENDERECO%>"
									name="<%=ServletAluno.NM_PARAMETRO_ENDERECO%>"
									value="<%=endereco == null ? "" : endereco%>" size="40"></td>

								<th width="12%" align="left">Número:</th>
								<td><input type="text"
									id="<%=ServletAluno.NM_PARAMETRO_NUMERO%>"
									name="<%=ServletAluno.NM_PARAMETRO_NUMERO%>"
									value="<%=numero == null ? "" : numero%>"
									onkeypress='return SomenteNumero(event)'></td>
									
								<th align="left">Bairro:</th>
								<td><input type="text"
									id="<%=ServletAluno.NM_PARAMETRO_BAIRRO%>"
									name="<%=ServletAluno.NM_PARAMETRO_BAIRRO%>"
									value="<%=bairro == null ? "" : bairro%>"
									onkeypress='return letras(event)'></td>
							</tr>
							<tr>
								<th width="13%" align="left">Cidade:</th>
								<td><input type="text"
									id="<%=ServletAluno.NM_PARAMETRO_CIDADE%>"
									name="<%=ServletAluno.NM_PARAMETRO_CIDADE%>"
									value="<%=cidade == null ? "" : cidade%>" size="20"
									onkeypress='return letras(event)'></td>

								<th width="12%" align="left">Estado:</th>
								<td><input type="text"
									id="<%=ServletAluno.NM_PARAMETRO_ESTADO%>"
									name="<%=ServletAluno.NM_PARAMETRO_ESTADO%>"
									value="<%=estado == null ? "" : estado%>"
									onkeypress='return letras(event)'></td>
									
								<th width="10%" align="left">Naturalidade:</th>
								<td><input type="text"
									id="<%=ServletAluno.NM_PARAMETRO_NATURALIDADE%>"
									name="<%=ServletAluno.NM_PARAMETRO_NATURALIDADE%>"
									value="<%=naturalidade == null ? "" : naturalidade%>"
									onkeypress='return letras(event)'></td>
							</tr>
							<tr>
								<th width="13%" align="left">Código Certidão Nas:</th>
								<td><input type="text"
									id="<%=ServletAluno.NM_PARAMETRO_CERTIDAO_NASC%>"
									name="<%=ServletAluno.NM_PARAMETRO_CERTIDAO_NASC%>"
									value="<%=certidaoNasc == null ? "" : certidaoNasc%>" size="20"></td>
									
								<th width="12%" align="left">Matrícula:</th>

								<%
									String matricula = "";
									AlunoDAO alunoDAO = new AlunoDAO();
									java.util.Date d = new Date();
									String dStr = java.text.DateFormat.getDateInstance(
											DateFormat.MEDIUM).format(d);
									String ano = dStr.substring(6, 10);
									String mes = dStr.substring(3, 5);
									String maiorMatricula = alunoDAO.consultarMaiorMatriculaNoAno(ano);

									if (maiorMatricula == null || maiorMatricula.equals("")) {
										matricula = ano + "." + mes + ".00001";
									} else {
										String primeiraParteMatricula = maiorMatricula.substring(0, 6);
										String segundaParte = maiorMatricula.substring(6, 11);
										Integer valorAtual = Integer.valueOf(segundaParte);
										Integer valorAtualMaisUm = valorAtual + 1;

										matricula = primeiraParteMatricula.substring(0, 4)
												+ "."
												+ primeiraParteMatricula.substring(4, 6)
												+ "."
												+ BibliotecaFormatarDados
														.completarNumeroComZerosEsquerda(
																String.valueOf(valorAtualMaisUm), 5);
									}
								%>


								<td><input type="text"
									id="<%=ServletAluno.NM_PARAMETRO_MATRICULA%>"
									name="<%=ServletAluno.NM_PARAMETRO_MATRICULA%>"
									value="<%=matricula%>" readonly></td>

								<th align="left">Data Matrícula:</th>
								<td><input type="text"
									id="<%=ServletAluno.NM_PARAMETRO_DT_MATRICULA%>"
									name="<%=ServletAluno.NM_PARAMETRO_DT_MATRICULA%>"
									value="<%=dStr%>" readonly></td>
							</tr>
							<tr>
								<th width="13%" align="left">N° Carteira Estudante:</th>
								<td><input type="text"
									id="<%=ServletAluno.NM_PARAMETRO_CARTEIRA_ESTUDANTE%>"
									name="<%=ServletAluno.NM_PARAMETRO_CARTEIRA_ESTUDANTE%>"
									value="<%=carteiraEstudante == null ? "" : carteiraEstudante%>"
									onkeyup="formatarCampoCarteiraEstudante(event)"
									onkeypress='return SomenteNumero(event)' maxlength="14"></td>
								
								<th width="12%" align="left">Necessidade Especial:</th>
								
								<%
									String campoSelecionadoN = "";
									String campoSelecionadoS = "";

									if (necEspecial != null	&& necEspecial.equals("S")) {
										campoSelecionadoS = "checked";
									} else if (necEspecial != null && necEspecial.equals("N")) {
										campoSelecionadoN = "checked";
									}
								%>
								
								<td><input class="esconderEvento" type="radio"
									id="semNecessidade"
									name="<%=ServletAluno.NM_PARAMETRO_NECESSIDADE_ESPECIAL%>"
									value="N"<%=campoSelecionadoN%>>Não <input class="adicionarEvento"
									type="radio" id="comNecessidade"
									name="<%=ServletAluno.NM_PARAMETRO_NECESSIDADE_ESPECIAL%>"
									value="S" <%=campoSelecionadoS%>>Sim <%
									if (!campoSelecionadoS.equals("")) {
								%> <textarea style="visibility: visible;"
										id="<%=ServletAluno.NM_PARAMETRO_DS_NECESSIDADE_ESPECIAL%>"
										name="<%=ServletAluno.NM_PARAMETRO_DS_NECESSIDADE_ESPECIAL%>"
										rows="1" cols="30"><%=dsNecEspecial%></textarea>
									<%
										} else {
									%> <textarea style="visibility: hidden;"
										id="<%=ServletAluno.NM_PARAMETRO_DS_NECESSIDADE_ESPECIAL%>"
										name="<%=ServletAluno.NM_PARAMETRO_DS_NECESSIDADE_ESPECIAL%>"
										rows="1" cols="30"></textarea> <%
 	}
 %></td>

								<th align="left">Turma:</th>
								<td>
									<%
										TurmaDAO turmaDAO = new TurmaDAO();
										ArrayList<Turma> colecaoTurma = turmaDAO.consultar("", "", "");
										boolean ultimaTurma = false;
										boolean mesmaTurma = false;
										for (int x = 0; x < colecaoTurma.size(); x++) {
											Turma turma = colecaoTurma.get(x);

											if (x + 1 == colecaoTurma.size()) {
												ultimaTurma = true;
											}
											
											if(turmaTela.equals(turma.getIdTurma())){
												mesmaTurma = true;
											}
									%> <%=Select.getInstancia().getHTML(
						ServletAluno.NM_PARAMETRO_TURMA,
						ServletAluno.NM_PARAMETRO_TURMA, turma.getIdTurma(),
						turma.getDsTurma(), mesmaTurma, x, ultimaTurma, "")%>
									<%
											mesmaTurma = false;
										}
									%>
								</td>
							</tr>
						</tbody>
					</table>
				</td>
			</tr>
		</table>
		<%if(telaEvento == null || telaEvento.equals("")){ %>
		<table align="center">
			<tr>
				<th align="right">O Aluno mora no mesmo endereço do
					responsável?</th>
				<td>
					<button type="button" id="botaoCopiar" name="botaoCopiar"
						onclick="copiar();">Copiar</button>
				</td>
			</tr>
		</table>
		<%} %>
		<h2 align="center">CADASTRAR RESPONSÁVEL</h2>
		<table width="100%" align="center">
			<tr>
				<td>
					<table width="100%" align="center" style="background-color: #99CCFF">
						<tbody>
							<tr>
								<th width="6%" align="left">CPF:</th>
								<td><input type="text"
									id="<%=ServletAluno.NM_PARAMETRO_CPF%>"
									name="<%=ServletAluno.NM_PARAMETRO_CPF%>" value="<%=cpf == null ? "" : cpf%>"
									onkeyup="formatarCPF(event); consultarResponsavel(this.value);"
									onblur="formatarCPF(event); consultarResponsavel(this.value);"
									maxlength="14" onkeypress='return SomenteNumero(event)' size="15"></td>
							
								<th width="8%" align="left">Nome:</th>
								<td><input type="text"
									id="<%=ServletAluno.NM_PARAMETRO_NOME_RESP%>"
									name="<%=ServletAluno.NM_PARAMETRO_NOME_RESP%>"
									value="<%=nomeResp == null ? "" : nomeResp%>" size="40"
									onkeypress='return letras(event)'></td>

								<th width="12%" align="left">Data de Nascimento:</th>
								<td><input type="text"
									id="<%=ServletAluno.NM_PARAMETRO_DT_NASCIMENTO_RESP%>"
									name="<%=ServletAluno.NM_PARAMETRO_DT_NASCIMENTO_RESP%>"
									value="<%=dtNascimentoResp == null ? "" : dtNascimentoResp%>"
									onkeyup="formatarCamposData(this.name, this, event)"
									onkeypress='return SomenteNumero(event)' maxlength="10"></td>

								<th align="left">Naturalidade:</th>
								<td><input type="text"
									id="<%=ServletAluno.NM_PARAMETRO_NATURALIDADE_RESP%>"
									name="<%=ServletAluno.NM_PARAMETRO_NATURALIDADE_RESP%>"
									value="<%=naturalidadeResp == null ? "" : naturalidadeResp%>"
									onkeypress='return letras(event)'></td>
							</tr>
							<tr>
								<th width="5%" align="left">CEP:</th>
								<td><input type="text"
									id="<%=ServletAluno.NM_PARAMETRO_CEP_RESP%>"
									name="<%=ServletAluno.NM_PARAMETRO_CEP_RESP%>"
									value="<%=cepResp == null ? "" : cepResp%>"
									onkeyup="formatarCampoCEP(event)" maxlength="9"
									onkeypress='return SomenteNumero(event)' size="15"></td>
							
								<th align="left">Endereço:</th>
								<td><input type="text"
									id="<%=ServletAluno.NM_PARAMETRO_ENDERECO_RESP%>"
									name="<%=ServletAluno.NM_PARAMETRO_ENDERECO_RESP%>"
									value="<%=enderecoResp == null ? "" : enderecoResp%>" size="40"></td>

								<th align="left">Número:</th>
								<td><input type="text"
									id="<%=ServletAluno.NM_PARAMETRO_NUMERO_RESP%>"
									name="<%=ServletAluno.NM_PARAMETRO_NUMERO_RESP%>"
									value="<%=numeroResp == null ? "" : numeroResp%>"
									onkeypress='return SomenteNumero(event)'></td>
									
								<th align="left">Bairro:</th>
								<td><input type="text"
									id="<%=ServletAluno.NM_PARAMETRO_BAIRRO_RESP%>"
									name="<%=ServletAluno.NM_PARAMETRO_BAIRRO_RESP%>"
									value="<%=bairroResp == null ? "" : bairroResp%>"
									onkeypress='return letras(event)'></td>
									
							</tr>
							<tr>
								<th align="left">Cidade:</th>
								<td><input type="text"
									id="<%=ServletAluno.NM_PARAMETRO_CIDADE_RESP%>"
									name="<%=ServletAluno.NM_PARAMETRO_CIDADE_RESP%>"
									value="<%=cidadeResp == null ? "" : cidadeResp%>"
									onkeypress='return letras(event)' size="15"></td>
									
								<th align="left">Estado:</th>
								<td><input type="text"
									id="<%=ServletAluno.NM_PARAMETRO_ESTADO_RESP%>"
									name="<%=ServletAluno.NM_PARAMETRO_ESTADO_RESP%>"
									value="<%=estadoResp == null ? "" : estadoResp%>"
									onkeypress='return letras(event)'></td>
									
								<th align="left">Telefone:</th>
								<td><input type="text"
									id="<%=ServletAluno.NM_PARAMETRO_TELEFONE%>"
									name="<%=ServletAluno.NM_PARAMETRO_TELEFONE%>"
									value="<%=telefone == null ? "" : telefone%>"
									onkeyup="formatarCampoTelefone(this.name, this, event);"
									onkeypress='return SomenteNumero(event)'></td>
									
								<th align="left">Identidade:</th>
								<td><input type="text"
									id="<%=ServletAluno.NM_PARAMETRO_IDENTIDADE%>"
									name="<%=ServletAluno.NM_PARAMETRO_IDENTIDADE%>"
									value="<%=identidade == null ? "" : identidade%>" size="20"
									onkeypress='return SomenteNumero(event)'></td>
							</tr>
							<tr>
								<th align="left">Parentesco:</th>
								<td><input type="text"
									id="<%=ServletAluno.NM_PARAMETRO_PARENTESCO%>"
									name="<%=ServletAluno.NM_PARAMETRO_PARENTESCO%>"
									value="<%=parentesco == null ? "" : parentesco%>"
									onkeypress='return letras(event)' maxlength="10" size="15"></td>
							
								<th align="left">Estado Civil:</th>
								<td><input type="text"
									id="<%=ServletAluno.NM_PARAMETRO_ESTADO_CIVIL%>"
									name="<%=ServletAluno.NM_PARAMETRO_ESTADO_CIVIL%>"
									value="<%=estadoCivil == null ? "" : estadoCivil%>"
									onkeypress='return letras(event)'></td>

								<th align="left">Escolaridade:</th>
								<td><input type="text"
									id="<%=ServletAluno.NM_PARAMETRO_ESCOLARIDADE%>"
									name="<%=ServletAluno.NM_PARAMETRO_ESCOLARIDADE%>"
									value="<%=escolaridade == null ? "" : escolaridade%>"
									onkeypress='return letras(event)'></td>
								<th align="left">Profissão:</th>
								<td><input type="text"
									id="<%=ServletAluno.NM_PARAMETRO_PROFISSAO%>"
									name="<%=ServletAluno.NM_PARAMETRO_PROFISSAO%>"
									value="<%=profissao == null ? "" : profissao%>"
									onkeypress='return letras(event)'></td>
							</tr>
							<tr>
								<th align="left">Renda:</th>
								<td><input type="text"
									id="<%=ServletAluno.NM_PARAMETRO_RENDA%>"
									name="<%=ServletAluno.NM_PARAMETRO_RENDA%>"
									value="<%=salarioFormatado == null ? "" : salarioFormatado%>"
									onKeydown="Formata(this,20,event,2)"
									onkeypress='return SomenteNumero(event)' size="15"></td>
							</tr>
						</tbody>
					</table>
					<table width="50%" align="center">
						<tr>
							<td align="center">
								<button type="button" id="botaoCadastrar" name="botaoCadastrar"
									onclick="cadastrar();">Cadastrar</button>
							</td>

							<td align="center">
								<button type="button" id="botaoDesistir" name="botaoDesistir"
									onclick="desistir();">Voltar</button>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<br>
	</form>
</body>
</html>
<%@page import="java.util.Locale"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="br.com.educandariopassosfirmes.entidades.Responsavel"%>
<%@page import="br.com.educandariopassosfirmes.entidades.Pessoa"%>
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
<title>Alterar Aluno</title>

</head>
<script type="text/javascript" language="javascript"
	src="js/jquery-1.3.2-vsdoc2.js"></script>
<SCRIPT language="JavaScript" type="text/javascript"
	src="js/biblioteca.js"></SCRIPT>
<script type="text/javascript">

function desistir(){
	document.getElementById("<%=ServletAluno.NM_EVENTO%>").value = "<%=ServletAluno.NM_JSP_CONSULTAR%>";
}

function alterar(){
	document.getElementById("<%=ServletAluno.NM_EVENTO%>").value = "<%=ServletAluno.NM_EVENTO_PROCESSAR_ALTERACAO%>";
	}

	function mostrarDP() {
		document.getElementById("dsNecEspecial").style.visibility = "visible";
	}

	function esconderDP() {
		document.getElementById("dsNecEspecial").style.visibility = "hidden";
	}
	
function consultarResponsavel(pCampo){
	var tamanhoCampo = pCampo.length;
		
	if(tamanhoCampo == 14){
		document.getElementById("<%=ServletAluno.NM_EVENTO%>").value = "<%=ServletAluno.NM_EVENTO_RESPONSAVEL_CADASTRADO%>";
		document.getElementById("<%=ServletAluno.NM_PARAMETRO_TELA_EVENTO%>").value = "<%=ServletAluno.NM_JSP_ALTERAR_ALUNO%>";
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
	Aluno aluno = (Aluno) request
			.getAttribute(ServletAluno.NM_PARAMETRO_ALUNO);
	Pessoa pessoaAluno = (Pessoa) request
			.getAttribute(ServletAluno.NM_PARAMETRO_PESSOA_ALUNO);
	Responsavel responsavel = (Responsavel) request
			.getAttribute(ServletAluno.NM_PARAMETRO_RESPONSAVEL);
	Pessoa pesssoaResponsavel = (Pessoa) request
			.getAttribute(ServletAluno.NM_PARAMETRO_PESSOA_RESPONSAVEL);

	double salario = responsavel.getRenda();

	BigDecimal valor = new BigDecimal(String.valueOf(salario));
	NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt",
			"BR"));
	String salarioFormatado = nf.format(valor);
	salarioFormatado = salarioFormatado.replace("R$", "");
	salarioFormatado = salarioFormatado.trim();
%>

<body
	onload="formatarCPFOnload(); formatarCamposDataAlunoOnload(); formatarCampoTelefoneOnload(); formatarMatriculaOnload(); formatarCampoCarteiraEstudanteOnload(); formatarCampoCEPOnload();">

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
		<h2 align="center">ALTERAR ALUNO</h2>
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
									value="<%=pessoaAluno.getNome()%>" size="40"
									onkeypress='return letras(event)'></td>

								<th width="12%" align="left">Data de Nascimento:</th>
								<td><input type="text"
									id="<%=ServletAluno.NM_PARAMETRO_DT_NASCIMENTO%>"
									name="<%=ServletAluno.NM_PARAMETRO_DT_NASCIMENTO%>"
									value="<%=pessoaAluno.getDtNascimento() == null ? "" : pessoaAluno
					.getDtNascimento()%>"
									onkeyup="formatarCamposData(this.name, this, event)"
									onkeypress='return SomenteNumero(event)' maxlength="10"></td>

								<th width="10%" align="left">CEP:</th>
								<td><input type="text"
									id="<%=ServletAluno.NM_PARAMETRO_CEP%>"
									name="<%=ServletAluno.NM_PARAMETRO_CEP%>"
									value="<%=pessoaAluno.getCep() == null ? "" : pessoaAluno.getCep()%>"
									onkeyup="formatarCampoCEP(event)" maxlength="9"
									onkeypress='return SomenteNumero(event)'></td>
							</tr>
							<tr>

								<th width="13%" align="left">Endereço:</th>
								<td><input type="text"
									id="<%=ServletAluno.NM_PARAMETRO_ENDERECO%>"
									name="<%=ServletAluno.NM_PARAMETRO_ENDERECO%>"
									value="<%=pessoaAluno.getEndereco()%>" size="40"></td>

								<th width="12%" align="left">Número:</th>
								<td><input type="text"
									id="<%=ServletAluno.NM_PARAMETRO_NUMERO%>"
									name="<%=ServletAluno.NM_PARAMETRO_NUMERO%>"
									value="<%=pessoaAluno.getNumero() == 0 ? "" : pessoaAluno
					.getNumero()%>"
									onkeypress='return SomenteNumero(event)'></td>
									
								<th align="left">Bairro:</th>
								<td><input type="text"
									id="<%=ServletAluno.NM_PARAMETRO_BAIRRO%>"
									name="<%=ServletAluno.NM_PARAMETRO_BAIRRO%>"
									value="<%=pessoaAluno.getBairro()%>"
									onkeypress='return letras(event)'></td>
							</tr>
							<tr>

								<th width="13%" align="left">Cidade:</th>
								<td><input type="text"
									id="<%=ServletAluno.NM_PARAMETRO_CIDADE%>"
									name="<%=ServletAluno.NM_PARAMETRO_CIDADE%>"
									value="<%=pessoaAluno.getCidade()%>" size="20"
									onkeypress='return letras(event)'></td>

								<th width="12%" align="left">Estado:</th>
								<td><input type="text"
									id="<%=ServletAluno.NM_PARAMETRO_ESTADO%>"
									name="<%=ServletAluno.NM_PARAMETRO_ESTADO%>"
									value="<%=pessoaAluno.getEstado()%>"
									onkeypress='return letras(event)'></td>
									
								<th width="10%" align="left">Naturalidade:</th>
								<td><input type="text"
									id="<%=ServletAluno.NM_PARAMETRO_NATURALIDADE%>"
									name="<%=ServletAluno.NM_PARAMETRO_NATURALIDADE%>"
									value="<%=pessoaAluno.getNaturalidade()%>"
									onkeypress='return letras(event)'></td>
							</tr>
							<tr>
								<th width="13%" align="left">Código Certidão Nas:</th>
								<td><input type="text"
									id="<%=ServletAluno.NM_PARAMETRO_CERTIDAO_NASC%>"
									name="<%=ServletAluno.NM_PARAMETRO_CERTIDAO_NASC%>"
									value="<%=aluno.getCdCertidaoNascimento()%>" size="20"></td>

								<th width="12%" align="left">Matrícula:</th>
								<td><input type="text"
									id="<%=ServletAluno.NM_PARAMETRO_MATRICULA%>"
									name="<%=ServletAluno.NM_PARAMETRO_MATRICULA%>"
									value="<%=aluno.getId()%>" readonly></td>

								<th align="left">Data Matrícula:</th>
								<td><input type="text"
									id="<%=ServletAluno.NM_PARAMETRO_DT_MATRICULA%>"
									name="<%=ServletAluno.NM_PARAMETRO_DT_MATRICULA%>"
									value="<%=aluno.getDtMatricula() == null ? "" : aluno
					.getDtMatricula()%>"
									readonly></td>
							</tr>
							<tr>
								<th align="left">N° Carteira Estudante:</th>
								<td><input type="text"
									id="<%=ServletAluno.NM_PARAMETRO_CARTEIRA_ESTUDANTE%>"
									name="<%=ServletAluno.NM_PARAMETRO_CARTEIRA_ESTUDANTE%>"
									value="<%=aluno.getCdCarteiraEstudante()%>"
									onkeyup="formatarCampoCarteiraEstudante(event)"
									onkeypress='return SomenteNumero(event)' maxlength="14"></td>

								<th align="left">Necessidade Especial:</th>

								<%
									String campoSelecionadoN = "";
									String campoSelecionadoS = "";

									if (aluno.getNecessidadeEspecial() != null
											&& aluno.getNecessidadeEspecial().equals("S")) {
										campoSelecionadoS = "checked";
									} else if (aluno.getNecessidadeEspecial() != null
											&& aluno.getNecessidadeEspecial().equals("N")) {
										campoSelecionadoN = "checked";
									}
								%>

								<td><input class="esconderEvento" type="radio"
									id="semNecessidade"
									name="<%=ServletAluno.NM_PARAMETRO_NECESSIDADE_ESPECIAL%>"
									value="N" <%=campoSelecionadoN%>>Não <input
									class="adicionarEvento" type="radio" id="comNecessidade"
									name="<%=ServletAluno.NM_PARAMETRO_NECESSIDADE_ESPECIAL%>"
									value="S" <%=campoSelecionadoS%>>Sim <%
									if (!campoSelecionadoS.equals("")) {
								%> <textarea style="visibility: visible;"
										id="<%=ServletAluno.NM_PARAMETRO_DS_NECESSIDADE_ESPECIAL%>"
										name="<%=ServletAluno.NM_PARAMETRO_DS_NECESSIDADE_ESPECIAL%>"
										rows="1" cols="30"><%=aluno.getDetalheNecessidadeEspecial()%></textarea>
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

											if (aluno.getIdTurma().equals(turma.getIdTurma())) {
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
		<h2 align="center">ALTERAR RESPONSÁVEL</h2>
		<table width="100%">
			<tr>
				<td>
					<table width="100%" align="center" style="background-color: #99CCFF">
						<tbody>
							<tr>
								<th align="left">CPF:</th>
								<td><input type="text"
									id="<%=ServletAluno.NM_PARAMETRO_CPF%>"
									name="<%=ServletAluno.NM_PARAMETRO_CPF%>"
									value="<%=pesssoaResponsavel.getId()%>"
									onkeyup="formatarCPF(event); consultarResponsavel(this.value);"
									onblur="formatarCPF(event); consultarResponsavel(this.value);"
									maxlength="14" onkeypress='return SomenteNumero(event)'size="15"></td>
									
								<th width="8%" align="left">Nome:</th>
								<td><input type="text"
									id="<%=ServletAluno.NM_PARAMETRO_NOME_RESP%>"
									name="<%=ServletAluno.NM_PARAMETRO_NOME_RESP%>"
									value="<%=pesssoaResponsavel.getNome()%>" size="50"
									onkeypress='return letras(event)'></td>

								<th width="12%" align="left">Data de Nascimento:</th>
								<td><input type="text"
									id="<%=ServletAluno.NM_PARAMETRO_DT_NASCIMENTO_RESP%>"
									name="<%=ServletAluno.NM_PARAMETRO_DT_NASCIMENTO_RESP%>"
									value="<%=pesssoaResponsavel.getDtNascimento() == null ? ""
					: pesssoaResponsavel.getDtNascimento()%>"
									onkeyup="formatarCamposData(this.name, this, event)"
									onkeypress='return SomenteNumero(event)' maxlength="10"></td>

								<th align="left">Naturalidade:</th>
								<td><input type="text"
									id="<%=ServletAluno.NM_PARAMETRO_NATURALIDADE_RESP%>"
									name="<%=ServletAluno.NM_PARAMETRO_NATURALIDADE_RESP%>"
									value="<%=pesssoaResponsavel.getNaturalidade()%>"
									onkeypress='return letras(event)'></td>
							</tr>
							<tr>
								<th width="5%" align="left">CEP:</th>
								<td><input type="text"
									id="<%=ServletAluno.NM_PARAMETRO_CEP_RESP%>"
									name="<%=ServletAluno.NM_PARAMETRO_CEP_RESP%>"
									value="<%=pesssoaResponsavel.getCep() == null ? ""
					: pesssoaResponsavel.getCep()%>"
									onkeyup="formatarCampoCEP(event)" maxlength="9"
									onkeypress='return SomenteNumero(event)' size="15"></td>
									
								<th align="left">Endereço:</th>
								<td><input type="text"
									id="<%=ServletAluno.NM_PARAMETRO_ENDERECO_RESP%>"
									name="<%=ServletAluno.NM_PARAMETRO_ENDERECO_RESP%>"
									value="<%=pesssoaResponsavel.getEndereco()%>" size="50"></td>

								<th align="left">Número:</th>
								<td><input type="text"
									id="<%=ServletAluno.NM_PARAMETRO_NUMERO_RESP%>"
									name="<%=ServletAluno.NM_PARAMETRO_NUMERO_RESP%>"
									value="<%=pesssoaResponsavel.getNumero() == 0 ? ""
					: pesssoaResponsavel.getNumero()%>"
									onkeypress='return SomenteNumero(event)'></td>
									
								<th align="left">Bairro:</th>
								<td><input type="text"
									id="<%=ServletAluno.NM_PARAMETRO_BAIRRO_RESP%>"
									name="<%=ServletAluno.NM_PARAMETRO_BAIRRO_RESP%>"
									value="<%=pesssoaResponsavel.getBairro()%>"
									onkeypress='return letras(event)'></td>
							</tr>
							<tr>
								<th align="left">Cidade:</th>
								<td><input type="text"
									id="<%=ServletAluno.NM_PARAMETRO_CIDADE_RESP%>"
									name="<%=ServletAluno.NM_PARAMETRO_CIDADE_RESP%>"
									value="<%=pesssoaResponsavel.getCidade()%>"
									onkeypress='return letras(event)' size="15"></td>
									
								<th align="left">Estado:</th>
								<td><input type="text"
									id="<%=ServletAluno.NM_PARAMETRO_ESTADO_RESP%>"
									name="<%=ServletAluno.NM_PARAMETRO_ESTADO_RESP%>"
									value="<%=pesssoaResponsavel.getEstado()%>"
									onkeypress='return letras(event)'></td>
									
								<th align="left">Telefone:</th>
								<td><input type="text"
									id="<%=ServletAluno.NM_PARAMETRO_TELEFONE%>"
									name="<%=ServletAluno.NM_PARAMETRO_TELEFONE%>"
									value="<%=pesssoaResponsavel.getTelefone()%>"
									onkeyup="formatarCampoTelefone(this.name, this, event);"
									onkeypress='return SomenteNumero(event)'></td>
									
								<th align="left">Identidade:</th>
								<td><input type="text"
									id="<%=ServletAluno.NM_PARAMETRO_IDENTIDADE%>"
									name="<%=ServletAluno.NM_PARAMETRO_IDENTIDADE%>"
									value="<%=pesssoaResponsavel.getIdentidade()%>" size="20"
									onkeypress='return SomenteNumero(event)'></td>
							</tr>
							<tr>
								<th align="left">Parentesco:</th>
								<td><input type="text"
									id="<%=ServletAluno.NM_PARAMETRO_PARENTESCO%>"
									name="<%=ServletAluno.NM_PARAMETRO_PARENTESCO%>"
									value="<%=aluno.getParentesco() == null ? "" : aluno.getParentesco()%>"
									onkeypress='return letras(event)' maxlength="10" size="15"></td>
									
								<th align="left">Estado Civil:</th>
								<td><input type="text"
									id="<%=ServletAluno.NM_PARAMETRO_ESTADO_CIVIL%>"
									name="<%=ServletAluno.NM_PARAMETRO_ESTADO_CIVIL%>"
									value="<%=responsavel.getEstadoCivil()%>"
									onkeypress='return letras(event)'></td>

								<th align="left">Escolaridade:</th>
								<td><input type="text"
									id="<%=ServletAluno.NM_PARAMETRO_ESCOLARIDADE%>"
									name="<%=ServletAluno.NM_PARAMETRO_ESCOLARIDADE%>"
									value="<%=responsavel.getEscolaridade()%>"
									onkeypress='return letras(event)'></td>
									
								<th align="left">Profissão:</th>
								<td><input type="text"
									id="<%=ServletAluno.NM_PARAMETRO_PROFISSAO%>"
									name="<%=ServletAluno.NM_PARAMETRO_PROFISSAO%>"
									value="<%=responsavel.getProfissao()%>"
									onkeypress='return letras(event)'></td>
							</tr>
							<tr>
								<th align="left">Renda:</th>
								<td><input type="text"
									id="<%=ServletAluno.NM_PARAMETRO_RENDA%>"
									name="<%=ServletAluno.NM_PARAMETRO_RENDA%>"
									value="<%=salarioFormatado%>"
									onKeydown="Formata(this,20,event,2)"
									onkeypress='return SomenteNumero(event)' size="15"></td>
							</tr>
						</tbody>
					</table>
					<br>
					<table width="50%" align="center">
						<tr>
							<td align="center">
								<button type="submit" id="botaoAlterar" name="botaoAlterar"
									onclick="alterar();">Alterar</button>
							</td>

							<td align="center">
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
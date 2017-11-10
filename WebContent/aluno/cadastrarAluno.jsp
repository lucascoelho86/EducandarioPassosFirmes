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
	document.getElementById("<%=ServletAluno.NM_EVENTO%>").value = "<%=ServletAluno.NM_JSP_CONSULTAR%>";
}

function cadastrar(){
	document.getElementById("<%=ServletAluno.NM_EVENTO%>").value = "<%=ServletAluno.NM_EVENTO_PROCESSAR_INCLUSAO%>";
	}

	function mostrarDP() {
		document.getElementById("teste").style.visibility = "visible";
	}

	function esconderDP() {
		document.getElementById("teste").style.visibility = "hidden";
	}
</script>
<script type="text/javascript">
	jQuery(document).ready(function() {

		//Fun��o de clicar na linha de adicionar evento	
		jQuery("input.adicionarEvento").click(function() {
			mostrarDP();
		});

		//Fun��o de clicar na linha de esconder evento	
		jQuery("input.esconderEvento").click(function() {
			esconderDP();
		});

	});
</script>
<body>

	<jsp:include page="cabecalho.jsp" />

	<form action="ServletAluno" method="post">
		<input type="hidden" id="<%=ServletAluno.NM_EVENTO%>"
			name="<%=ServletAluno.NM_EVENTO%>" value="">
		<h2 align="center">CADASTRAR ALUNO</h2>
		<table width="100%">
			<tr>
				<td>
					<table width="65%" align="center" style="background-color: #99CCFF">
						<tbody>
							<tr>
								<th width="20%" align="right">Nome:</th>
								<td><input type="text"
									id="<%=ServletAluno.NM_PARAMETRO_NOME%>"
									name="<%=ServletAluno.NM_PARAMETRO_NOME%>" value="" size="50"
									onkeypress='return letras(event)'></td>

								<th width="20%" align="right">Data de Nascimento:</th>
								<td><input type="text"
									id="<%=ServletAluno.NM_PARAMETRO_DT_NASCIMENTO%>"
									name="<%=ServletAluno.NM_PARAMETRO_DT_NASCIMENTO%>" value=""
									onkeyup="formatarCamposData(this.name, this, event)"
									onkeypress='return SomenteNumero(event)' maxlength="10"></td>

								<th width="10%" align="right">Naturalidade:</th>
								<td><input type="text"
									id="<%=ServletAluno.NM_PARAMETRO_NATURALIDADE%>"
									name="<%=ServletAluno.NM_PARAMETRO_NATURALIDADE%>" value=""
									onkeypress='return letras(event)'></td>
							</tr>
							<tr>
								<th align="right">Endere�o:</th>
								<td><input type="text"
									id="<%=ServletAluno.NM_PARAMETRO_ENDERECO%>"
									name="<%=ServletAluno.NM_PARAMETRO_ENDERECO%>" value=""
									size="50"></td>

								<th align="right">N�mero:</th>
								<td><input type="text"
									id="<%=ServletAluno.NM_PARAMETRO_NUMERO%>"
									name="<%=ServletAluno.NM_PARAMETRO_NUMERO%>" value=""
									onkeypress='return SomenteNumero(event)'></td>

								<th align="right">Bairro:</th>
								<td><input type="text"
									id="<%=ServletAluno.NM_PARAMETRO_BAIRRO%>"
									name="<%=ServletAluno.NM_PARAMETRO_BAIRRO%>" value=""
									onkeypress='return letras(event)'></td>
							</tr>
							<tr>
								<th width="10%" align="right">Cidade:</th>
								<td><input type="text"
									id="<%=ServletAluno.NM_PARAMETRO_CIDADE%>"
									name="<%=ServletAluno.NM_PARAMETRO_CIDADE%>" value="" size="20"
									onkeypress='return letras(event)'></td>

								<th align="right">Estado:</th>
								<td><input type="text"
									id="<%=ServletAluno.NM_PARAMETRO_ESTADO%>"
									name="<%=ServletAluno.NM_PARAMETRO_ESTADO%>" value=""
									onkeypress='return letras(event)'></td>

								<th align="right">C�digo Certid�o Nas:</th>
								<td><input type="text"
									id="<%=ServletAluno.NM_PARAMETRO_IDENTIDADE%>"
									name="<%=ServletAluno.NM_PARAMETRO_IDENTIDADE%>" value=""
									size="20"></td>
							</tr>
							<tr>
								<th width="10%" align="right">Matr�cula:</th>
								<td><input type="text"
									id="<%=ServletAluno.NM_PARAMETRO_CPF%>"
									name="<%=ServletAluno.NM_PARAMETRO_CPF%>" value=""
									onkeyup="formatarCPF(event);" maxlength="14"
									onkeypress='return SomenteNumero(event)'></td>

								<th align="right">Data Matr�cula:</th>
								<td><input type="text"
									id="<%=ServletAluno.NM_PARAMETRO_DT_ADMISSAO%>"
									name="<%=ServletAluno.NM_PARAMETRO_DT_ADMISSAO%>" value=""
									onkeyup="formatarCamposData(this.name, this, event)"
									onkeypress='return SomenteNumero(event)' maxlength="10"></td>
									
								<th width="20%" align="right">N� Carteira Estudante:</th>
								<td><input type="text"
									id="<%=ServletAluno.NM_PARAMETRO_QT_DEPENDENTE%>"
									name="<%=ServletAluno.NM_PARAMETRO_QT_DEPENDENTE%>" value=""
									onkeypress='return SomenteNumero(event)'></td>
							</tr>
							<tr>
								<th align="right">Necessidade Especial:</th>
								<td><input class="esconderEvento" type="radio"
									id="semNecessidade"
									name="<%=ServletAluno.NM_PARAMETRO_NECESSIDADE_ESPECIAL%>" value="N">N�o
									<input class="adicionarEvento" type="radio" id="comNecessidade"
									name="<%=ServletAluno.NM_PARAMETRO_NECESSIDADE_ESPECIAL%>" value="S">Sim
									<textarea style="visibility: hidden;" id="teste" name="teste"
										rows="1" cols="36"></textarea></td>
							</tr>
						</tbody>
					</table> <br>
					<h2 align="center">CADASTRAR RESPONS�VEL</h2>
					<table width="100%">
						<tr>
							<td>
								<table width="65%" align="center" style="background-color: #99CCFF">
									<tbody>
										<tr>
											<th width="10%" align="right">Nome:</th>
											<td><input type="text"
												id="<%=ServletAluno.NM_PARAMETRO_NOME%>"
												name="<%=ServletAluno.NM_PARAMETRO_NOME%>" value="" size="50"
												onkeypress='return letras(event)'></td>
			
											<th align="right">Data de Nascimento:</th>
											<td><input type="text"
												id="<%=ServletAluno.NM_PARAMETRO_DT_NASCIMENTO%>"
												name="<%=ServletAluno.NM_PARAMETRO_DT_NASCIMENTO%>" value=""
												onkeyup="formatarCamposData(this.name, this, event)"
												onkeypress='return SomenteNumero(event)' maxlength="10"></td>
			
											<th width="10%" align="right">Naturalidade:</th>
											<td><input type="text"
												id="<%=ServletAluno.NM_PARAMETRO_NATURALIDADE%>"
												name="<%=ServletAluno.NM_PARAMETRO_NATURALIDADE%>" value=""
												onkeypress='return letras(event)'></td>
										</tr>
										<tr>
											<th width="10%" align="right">Endere�o:</th>
											<td><input type="text"
												id="<%=ServletAluno.NM_PARAMETRO_ENDERECO%>"
												name="<%=ServletAluno.NM_PARAMETRO_ENDERECO%>" value=""
												size="50"></td>
			
											<th align="right">N�mero:</th>
											<td><input type="text"
												id="<%=ServletAluno.NM_PARAMETRO_NUMERO%>"
												name="<%=ServletAluno.NM_PARAMETRO_NUMERO%>" value=""
												onkeypress='return SomenteNumero(event)'></td>
			
											<th align="right">Bairro:</th>
											<td><input type="text"
												id="<%=ServletAluno.NM_PARAMETRO_BAIRRO%>"
												name="<%=ServletAluno.NM_PARAMETRO_BAIRRO%>" value=""
												onkeypress='return letras(event)'></td>
										</tr>
										<tr>
											<th width="10%" align="right">Cidade:</th>
											<td><input type="text"
												id="<%=ServletAluno.NM_PARAMETRO_CIDADE%>"
												name="<%=ServletAluno.NM_PARAMETRO_CIDADE%>" value="" size="20"
												onkeypress='return letras(event)'></td>
			
											<th align="right">Estado:</th>
											<td><input type="text"
												id="<%=ServletAluno.NM_PARAMETRO_ESTADO%>"
												name="<%=ServletAluno.NM_PARAMETRO_ESTADO%>" value=""
												onkeypress='return letras(event)'></td>
			
											<th align="right">Telefone:</th>
											<td><input type="text"
												id="<%=ServletAluno.NM_PARAMETRO_TELEFONE%>"
												name="<%=ServletAluno.NM_PARAMETRO_TELEFONE%>" value=""
												onkeyup="formatarCampoTelefone(this.name, this, event);"
												onkeypress='return SomenteNumero(event)'></td>
										</tr>
										<tr>
											<th width="10%" align="right">Identidade:</th>
											<td><input type="text"
												id="<%=ServletAluno.NM_PARAMETRO_IDENTIDADE%>"
												name="<%=ServletAluno.NM_PARAMETRO_IDENTIDADE%>" value=""
												size="20" onkeypress='return SomenteNumero(event)'></td>
			
											<th align="right">CPF:</th>
											<td><input type="text"
												id="<%=ServletAluno.NM_PARAMETRO_CPF%>"
												name="<%=ServletAluno.NM_PARAMETRO_CPF%>" value=""
												onkeyup="formatarCPF(event);" maxlength="14"
												onkeypress='return SomenteNumero(event)'></td>
			
											<th align="right">Parentesco:</th>
											<td><input type="text"
												id="<%=ServletAluno.NM_PARAMETRO_PARENTESCO%>"
												name="<%=ServletAluno.NM_PARAMETRO_PARENTESCO%>" value=""
												onkeyup="formatarCamposData(this.name, this, event)"
												onkeypress='return SomenteNumero(event)' maxlength="10"></td>
										</tr>
										<tr>
											<th align="right">Estado Civil:</th>
											<td><input type="text"
												id="<%=ServletAluno.NM_PARAMETRO_ESTADO_CIVIL%>"
												name="<%=ServletAluno.NM_PARAMETRO_ESTADO_CIVIL%>" value=""
												onkeypress='return SomenteNumero(event)'></td>
												
											<th align="right">Escolaridade:</th>
											<td><input type="text"
												id="<%=ServletAluno.NM_PARAMETRO_ESCOLARIDADE%>"
												name="<%=ServletAluno.NM_PARAMETRO_ESCOLARIDADE%>" value=""
												onkeypress='return SomenteNumero(event)'></td>
												
											<th align="right">Profiss�o:</th>
											<td><input type="text"
												id="<%=ServletAluno.NM_PARAMETRO_PROFISSAO%>"
												name="<%=ServletAluno.NM_PARAMETRO_PROFISSAO%>" value=""
												onkeypress='return SomenteNumero(event)'></td>
										</tr>
										<tr>
											<th align="right">Renda:</th>
											<td><input type="text"
												id="<%=ServletAluno.NM_PARAMETRO_RENDA%>"
												name="<%=ServletAluno.NM_PARAMETRO_RENDA%>" value=""
												onkeypress='return SomenteNumero(event)'></td>
										</tr>
									</tbody>
					</table> <br>
					<table id="idTableDisciplinas" width="65%" align="center"
						style="background-color: #99CCFF; visibility: hidden;">
						<tr>
							<TH align="center" width="1%">X</TH>
							<TH align="left" width="10%">Disciplinas</TH>
						</tr>
					</table>
					<table id="idTableDisciplinas2" width="65%" align="center"
						style="visibility: hidden;">
						<%
							DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
							ArrayList<Disciplina> colecaoDisciplina = disciplinaDAO.consultar(
									0, "", "");
							boolean ultimaDisciplina = false;
							String cssCorlinha = "";
							String checked = "";
							boolean tratamentoCSS = true;
							for (int x = 0; x < colecaoDisciplina.size(); x++) {
								Disciplina disciplina = colecaoDisciplina.get(x);

								if (tratamentoCSS) {
									cssCorlinha = "#c0c0c0";
									tratamentoCSS = false;
								} else {
									cssCorlinha = "#ffffff";
									tratamentoCSS = true;
								}
						%>

						<tr style="background-color: <%=cssCorlinha%>">
							<th align="center" width="1%"><input type="checkbox"
								id=<%=ServletAluno.NM_PARAMETRO_ID_DISCIPLINA + x%>
								name="<%=ServletAluno.NM_PARAMETRO_ID_DISCIPLINA + x%>"
								value="<%=disciplina.getIdDisciplina()%>"> <input
								type="hidden"
								id=<%=ServletAluno.NM_PARAMETRO_TAMANHO_COLECAO_DISCIPLINA%>
								name="<%=ServletAluno.NM_PARAMETRO_TAMANHO_COLECAO_DISCIPLINA%>"
								value="<%=String.valueOf(colecaoDisciplina.size())%>"></th>
							<TH align="left" width="11%"><%=disciplina.getDsDisciplina().toUpperCase()%></TH>
						</tr>
						<%
							}
						%>
					</table> <br>
					<table width="50%" align="center">
						<tr>
							<td align="center">
								<button type="submit" id="botaoCadastrar" name="botaoCadastrar"
									onclick="cadastrar();">Cadastrar</button>
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
		<br>
	</form>

</body>



</html>
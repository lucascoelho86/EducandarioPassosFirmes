<%@page import="br.com.educandariopassosfirmes.util.BibliotecaFormatarDados"%>
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
								<th align="right">Endereço:</th>
								<td><input type="text"
									id="<%=ServletAluno.NM_PARAMETRO_ENDERECO%>"
									name="<%=ServletAluno.NM_PARAMETRO_ENDERECO%>" value=""
									size="50"></td>

								<th align="right">Número:</th>
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

								<th align="right">Código Certidão Nas:</th>
								<td><input type="text"
									id="<%=ServletAluno.NM_PARAMETRO_CERTIDAO_NASC%>"
									name="<%=ServletAluno.NM_PARAMETRO_CERTIDAO_NASC%>" value=""
									size="20"></td>
							</tr>
							<tr>
								<th width="10%" align="right">Matrícula:</th>
								
								<%	String matricula = "";
									AlunoDAO alunoDAO = new AlunoDAO();
									java.util.Date d = new Date();
									String dStr = java.text.DateFormat.getDateInstance(DateFormat.MEDIUM).format(d);
									String ano = dStr.substring(6, 10);
									String mes = dStr.substring(3, 5);
									String maiorMatricula = alunoDAO.consultarMaiorMatriculaNoAno(ano);
									
									if(maiorMatricula == null || maiorMatricula.equals("")){
										matricula = ano + "." + mes + ".00001";
									}else{
										String primeiraParteMatricula = maiorMatricula.substring(0, 6);
										String segundaParte = maiorMatricula.substring(6, 11);
										Integer valorAtual = Integer.valueOf(segundaParte);
										Integer valorAtualMaisUm = valorAtual + 1;
										
										matricula = primeiraParteMatricula.substring(0, 4) + "." + primeiraParteMatricula.substring(4, 6) + "."
										+ BibliotecaFormatarDados.completarNumeroComZerosEsquerda(String.valueOf(valorAtualMaisUm), 5);
									}
									%>	
																			
								
								<td><input type="text"
									id="<%=ServletAluno.NM_PARAMETRO_MATRICULA%>"
									name="<%=ServletAluno.NM_PARAMETRO_MATRICULA%>" value="<%=matricula%>" readonly></td>

								<th align="right">Data Matrícula:</th>
								<td><input type="text"
									id="<%=ServletAluno.NM_PARAMETRO_DT_MATRICULA%>"
									name="<%=ServletAluno.NM_PARAMETRO_DT_MATRICULA%>" value="<%=dStr%>"
									readonly></td>
									
								<th width="20%" align="right">N° Carteira Estudante:</th>
								<td><input type="text"
									id="<%=ServletAluno.NM_PARAMETRO_CARTEIRA_ESTUDANTE%>"
									name="<%=ServletAluno.NM_PARAMETRO_CARTEIRA_ESTUDANTE%>" value=""
									onkeyup="formatarCampoCarteiraEstudante(event)" onkeypress='return SomenteNumero(event)'
									maxlength="14"></td>
							</tr>
							<tr>
								<th align="right">Necessidade Especial:</th>
								<td><input class="esconderEvento" type="radio"
									id="semNecessidade"
									name="<%=ServletAluno.NM_PARAMETRO_NECESSIDADE_ESPECIAL%>" value="N">Não
									<input class="adicionarEvento" type="radio" id="comNecessidade"
									name="<%=ServletAluno.NM_PARAMETRO_NECESSIDADE_ESPECIAL%>" value="S">Sim
									<textarea style="visibility: hidden;" id="teste" name="teste"
										rows="1" cols="36"></textarea></td>
										
								<th align="right">Turma:</th>
								<td>
									<%	TurmaDAO turmaDAO = new TurmaDAO();
										ArrayList<Turma>colecaoTurma = turmaDAO.consultar("", "");
										boolean ultimaTurma = false;
										for(int x = 0; x < colecaoTurma.size(); x++){
											Turma turma = colecaoTurma.get(x);
														
											if(x + 1 == colecaoTurma.size()){
												ultimaTurma = true;
											}%>	
																			
											<%=Select.getInstancia().getHTML(ServletAluno.NM_PARAMETRO_TURMA, ServletAluno.NM_PARAMETRO_TURMA, turma.getIdTurma(), turma.getDsTurma(), false, x, ultimaTurma)%>
									<%}%>				
								</td>
							</tr>
						</tbody>
					</table> <br>
					<h2 align="center">CADASTRAR RESPONSÁVEL</h2>
					<table width="100%">
						<tr>
							<td>
								<table width="65%" align="center" style="background-color: #99CCFF">
									<tbody>
										<tr>
											<th width="10%" align="right">Nome:</th>
											<td><input type="text"
												id="<%=ServletAluno.NM_PARAMETRO_NOME_RESP%>"
												name="<%=ServletAluno.NM_PARAMETRO_NOME_RESP%>" value="" size="50"
												onkeypress='return letras(event)'></td>
			
											<th align="right">Data de Nascimento:</th>
											<td><input type="text"
												id="<%=ServletAluno.NM_PARAMETRO_DT_NASCIMENTO_RESP%>"
												name="<%=ServletAluno.NM_PARAMETRO_DT_NASCIMENTO_RESP%>" value=""
												onkeyup="formatarCamposData(this.name, this, event)"
												onkeypress='return SomenteNumero(event)' maxlength="10"></td>
			
											<th width="10%" align="right">Naturalidade:</th>
											<td><input type="text"
												id="<%=ServletAluno.NM_PARAMETRO_NATURALIDADE_RESP%>"
												name="<%=ServletAluno.NM_PARAMETRO_NATURALIDADE_RESP%>" value=""
												onkeypress='return letras(event)'></td>
										</tr>
										<tr>
											<th width="10%" align="right">Endereço:</th>
											<td><input type="text"
												id="<%=ServletAluno.NM_PARAMETRO_ENDERECO_RESP%>"
												name="<%=ServletAluno.NM_PARAMETRO_ENDERECO_RESP%>" value=""
												size="50"></td>
			
											<th align="right">Número:</th>
											<td><input type="text"
												id="<%=ServletAluno.NM_PARAMETRO_NUMERO_RESP%>"
												name="<%=ServletAluno.NM_PARAMETRO_NUMERO_RESP%>" value=""
												onkeypress='return SomenteNumero(event)'></td>
			
											<th align="right">Bairro:</th>
											<td><input type="text"
												id="<%=ServletAluno.NM_PARAMETRO_BAIRRO_RESP%>"
												name="<%=ServletAluno.NM_PARAMETRO_BAIRRO_RESP%>" value=""
												onkeypress='return letras(event)'></td>
										</tr>
										<tr>
											<th width="10%" align="right">Cidade:</th>
											<td><input type="text"
												id="<%=ServletAluno.NM_PARAMETRO_CIDADE_RESP%>"
												name="<%=ServletAluno.NM_PARAMETRO_CIDADE_RESP%>" value="" size="20"
												onkeypress='return letras(event)'></td>
			
											<th align="right">Estado:</th>
											<td><input type="text"
												id="<%=ServletAluno.NM_PARAMETRO_ESTADO_RESP%>"
												name="<%=ServletAluno.NM_PARAMETRO_ESTADO_RESP%>" value=""
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
												onkeypress='return letras(event)' maxlength="10"></td>
										</tr>
										<tr>
											<th align="right">Estado Civil:</th>
											<td><input type="text"
												id="<%=ServletAluno.NM_PARAMETRO_ESTADO_CIVIL%>"
												name="<%=ServletAluno.NM_PARAMETRO_ESTADO_CIVIL%>" value=""
												onkeypress='return letras(event)'></td>
												
											<th align="right">Escolaridade:</th>
											<td><input type="text"
												id="<%=ServletAluno.NM_PARAMETRO_ESCOLARIDADE%>"
												name="<%=ServletAluno.NM_PARAMETRO_ESCOLARIDADE%>" value=""
												onkeypress='return letras(event)'></td>
												
											<th align="right">Profissão:</th>
											<td><input type="text"
												id="<%=ServletAluno.NM_PARAMETRO_PROFISSAO%>"
												name="<%=ServletAluno.NM_PARAMETRO_PROFISSAO%>" value=""
												onkeypress='return letras(event)'></td>
										</tr>
										<tr>
											<th align="right">Renda:</th>
											<td><input type="text"
												id="<%=ServletAluno.NM_PARAMETRO_RENDA%>"
												name="<%=ServletAluno.NM_PARAMETRO_RENDA%>" value=""
												onKeydown="Formata(this,20,event,2)"
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
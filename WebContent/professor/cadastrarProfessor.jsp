<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="br.com.educandariopassosfirmes.entidades.Disciplina"%>
<%@page import="br.com.educandariopassosfirmes.dao.DisciplinaDAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="br.com.educandariopassosfirmes.servlet.ServletProfessor"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="cssProjeto.css">
<title>Cadastrar Professor</title>

</head>
<script type="text/javascript" language="javascript" src="js/jquery-1.3.2-vsdoc2.js"></script>
<SCRIPT language="JavaScript" type="text/javascript"
	src="js/biblioteca.js"></SCRIPT>
<script type="text/javascript">

function desistir(){
	document.getElementById("<%=ServletProfessor.NM_EVENTO%>").value = "<%=ServletProfessor.NM_JSP_CONSULTAR%>";
}

function cadastrar(){
	document.getElementById("<%=ServletProfessor.NM_EVENTO%>").value = "<%=ServletProfessor.NM_EVENTO_PROCESSAR_INCLUSAO%>";
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
<body>

	<jsp:include page="cabecalho.jsp" />

	<form action="ServletProfessor" method="post">
		<input type="hidden" id="<%=ServletProfessor.NM_EVENTO%>"
			name="<%=ServletProfessor.NM_EVENTO%>" value="">
		<h2 align="center">CADASTRAR PROFESSOR</h2>
		<table width="100%">
			<tr>
				<td>
					<table width="90%" align="center" style="background-color: #99CCFF">
						<tbody>
							<tr>
								<th width="10%" align="right">Nome:</th>
								<td><input type="text"
									id="<%=ServletProfessor.NM_PARAMETRO_NOME%>"
									name="<%=ServletProfessor.NM_PARAMETRO_NOME%>" value=""
									size="50" onkeypress='return letras(event)'></td>

								<th align="right">Data de Nascimento:</th>
								<td><input type="text"
									id="<%=ServletProfessor.NM_PARAMETRO_DT_NASCIMENTO%>"
									name="<%=ServletProfessor.NM_PARAMETRO_DT_NASCIMENTO%>"
									value="" onkeyup="formatarCamposData(this.name, this, event)"
									onkeypress='return SomenteNumero(event)' maxlength="10"></td>

								<th align="right">Naturalidade:</th>
								<td><input type="text"
									id="<%=ServletProfessor.NM_PARAMETRO_NATURALIDADE%>"
									name="<%=ServletProfessor.NM_PARAMETRO_NATURALIDADE%>" value=""
									onkeypress='return letras(event)'></td>
							</tr>
							<tr>
								<th width="10%" align="right">Endereço:</th>
								<td><input type="text"
									id="<%=ServletProfessor.NM_PARAMETRO_ENDERECO%>"
									name="<%=ServletProfessor.NM_PARAMETRO_ENDERECO%>" value=""
									size="50"></td>

								<th align="right">Número:</th>
								<td><input type="text"
									id="<%=ServletProfessor.NM_PARAMETRO_NUMERO%>"
									name="<%=ServletProfessor.NM_PARAMETRO_NUMERO%>" value=""
									onkeypress='return SomenteNumero(event)'></td>

								<th align="right">Bairro:</th>
								<td><input type="text"
									id="<%=ServletProfessor.NM_PARAMETRO_BAIRRO%>"
									name="<%=ServletProfessor.NM_PARAMETRO_BAIRRO%>" value=""
									onkeypress='return letras(event)'></td>
							</tr>
							<tr>
								<th width="10%" align="right">Cidade:</th>
								<td><input type="text"
									id="<%=ServletProfessor.NM_PARAMETRO_CIDADE%>"
									name="<%=ServletProfessor.NM_PARAMETRO_CIDADE%>" value=""
									size="20" onkeypress='return letras(event)'></td>

								<th align="right">Estado:</th>
								<td><input type="text"
									id="<%=ServletProfessor.NM_PARAMETRO_ESTADO%>"
									name="<%=ServletProfessor.NM_PARAMETRO_ESTADO%>" value=""
									onkeypress='return letras(event)'></td>

								<th align="right">Telefone:</th>
								<td><input type="text"
									id="<%=ServletProfessor.NM_PARAMETRO_TELEFONE%>"
									name="<%=ServletProfessor.NM_PARAMETRO_TELEFONE%>" value=""
									onkeyup="formatarCampoTelefone(this.name, this, event);"
									onkeypress='return SomenteNumero(event)'></td>
							</tr>
							<tr>
								<th width="10%" align="right">Identidade:</th>
								<td><input type="text"
									id="<%=ServletProfessor.NM_PARAMETRO_IDENTIDADE%>"
									name="<%=ServletProfessor.NM_PARAMETRO_IDENTIDADE%>" value=""
									size="20" onkeypress='return SomenteNumero(event)'></td>

								<th align="right">CPF:</th>
								<td><input type="text"
									id="<%=ServletProfessor.NM_PARAMETRO_CPF%>"
									name="<%=ServletProfessor.NM_PARAMETRO_CPF%>" value=""
									onkeyup="formatarCPF(event);" maxlength="14"
									onkeypress='return SomenteNumero(event)'></td>

								<th align="right">Formação:</th>
								<td><input type="text"
									id="<%=ServletProfessor.NM_PARAMETRO_FORMACAO%>"
									name="<%=ServletProfessor.NM_PARAMETRO_FORMACAO%>" value=""
									onkeypress='return letras(event)'></td>
							</tr>
							<tr>
								<th width="10%" align="right">Estado Civil:</th>
								<td><input type="text"
									id="<%=ServletProfessor.NM_PARAMETRO_ESTADO_CIVIL%>"
									name="<%=ServletProfessor.NM_PARAMETRO_ESTADO_CIVIL%>" value=""
									size="20" onkeypress='return letras(event)'></td>

								<th align="right">Quantidade Dependentes:</th>
								<td><input type="text"
									id="<%=ServletProfessor.NM_PARAMETRO_QT_DEPENDENTE%>"
									name="<%=ServletProfessor.NM_PARAMETRO_QT_DEPENDENTE%>"
									value="" onkeypress='return SomenteNumero(event)'></td>

								<th align="right">Data Admissão:</th>
								<td><input type="text"
									id="<%=ServletProfessor.NM_PARAMETRO_DT_ADMISSAO%>"
									name="<%=ServletProfessor.NM_PARAMETRO_DT_ADMISSAO%>" value=""
									onkeyup="formatarCamposData(this.name, this, event)"
									onkeypress='return SomenteNumero(event)' maxlength="10"></td>
							</tr>
							<tr>
								<th width="10%" align="right">Carga Horária:</th>
								<td><input type="text"
									id="<%=ServletProfessor.NM_PARAMETRO_CARGA_HORARIA%>"
									name="<%=ServletProfessor.NM_PARAMETRO_CARGA_HORARIA%>"
									value="" size="10" onkeypress='return SomenteNumero(event)'></td>

								<th align="right">Salário:</th>
								<td><input type="text"
									id="<%=ServletProfessor.NM_PARAMETRO_SALARIO%>"
									name="<%=ServletProfessor.NM_PARAMETRO_SALARIO%>" value=""
									onKeydown="Formata(this,20,event,2)"
									onkeypress='return SomenteNumero(event)'></td>
							</tr>
						</tbody>
					</table>
					<br>
					<table align="center">
						<tr>
							<TD class="adicionarEvento" colspan="9" align="center" style="border-color: #A5B9D7; border-style: dotted;">
								<p><b>+ ADICIONAR DISCIPLINA AO PROFESSOR(A) +</b></p>
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
							}%>
							
							<tr style="background-color: <%=cssCorlinha%>">
								<th align="center" width="1%">
									<input type="checkbox" id=<%=ServletProfessor.NM_PARAMETRO_ID_DISCIPLINA + x%> name="<%=ServletProfessor.NM_PARAMETRO_ID_DISCIPLINA + x%>" value="<%=disciplina.getIdDisciplina()%>">
									<input type="hidden" id=<%=ServletProfessor.NM_PARAMETRO_TAMANHO_COLECAO_DISCIPLINA%> name="<%=ServletProfessor.NM_PARAMETRO_TAMANHO_COLECAO_DISCIPLINA%>" value="<%=String.valueOf(colecaoDisciplina.size())%>">
								</th>
								<TH align="left" width="11%" ><%=disciplina.getDsDisciplina().toUpperCase()%></TH>
							</tr>
						<%}%>
					</table>
					<br>
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
<%@page import="br.com.educandariopassosfirmes.servlet.ServletDisciplina"%>
<%@page import="java.util.LinkedHashMap"%>
<%@page import="br.com.educandariopassosfirmes.dao.ConsultaPrincipalProfessor"%>
<%@page import="br.com.educandariopassosfirmes.dao.ProfessorDAO"%>
<%@page import="br.com.educandariopassosfirmes.servlet.ServletProgramacao"%>
<%@page import="br.com.educandariopassosfirmes.entidades.Turma"%>
<%@page import="br.com.educandariopassosfirmes.dao.TurmaDAO"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="br.com.educandariopassosfirmes.servlet.ServletProgramacao"%>
<%@page import="br.com.educandariopassosfirmes.util.Select"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="cssProjeto.css">
<title>Cadastrar Programação</title>

</head>

<script type="text/javascript">

function desistir(){
	document.getElementById("<%=ServletProgramacao.NM_EVENTO%>").value = "<%=ServletProgramacao.NM_JSP_CONSULTAR%>";
}

function cadastrar(){
	var valorSelectTurno = document.getElementById("selectTurno").value;
	
	if(valorSelectTurno != 0){
		document.getElementById("<%=ServletProgramacao.NM_EVENTO%>").value = "<%=ServletProgramacao.NM_EVENTO_PROCESSAR_INCLUSAO%>";		
	}else{
		alert("Selecione um turno!");
		document.getElementById("<%=ServletProgramacao.NM_EVENTO%>").value = "<%=ServletProgramacao.NM_EVENTO_EXIBIR_INCLUSAO%>";
		}
	}
</script>

<%
	String descricao;
	String siglaTurma;
	String qtMaxAlunos;

	descricao = (String) request
			.getAttribute(ServletProgramacao.NM_PARAMETRO_DS_TURMA);
	siglaTurma = (String) request
			.getAttribute(ServletProgramacao.NM_PARAMETRO_SIGLA_TURMA);
	qtMaxAlunos = (String) request
			.getAttribute(ServletProgramacao.NM_PARAMETRO_QT_MAX_ALUNOS);

	if (descricao == null) {
		descricao = "";
	}

	if (siglaTurma == null) {
		siglaTurma = "";
	}

	if (qtMaxAlunos == null) {
		qtMaxAlunos = "";
	}
%>

<body>

	<jsp:include page="cabecalho.jsp" />
	<a href = "ServletMenu" style="font-size: 14px; font-family: Cooper Black; text-decoration: none; color: black;"> <img width="60px" height="60px" src="img/pe.png"/><b>MENU</b></a>
	<form action="ServletProgramacao" method="post">
		<input type="hidden" id="<%=ServletProgramacao.NM_EVENTO%>"
			name="<%=ServletProgramacao.NM_EVENTO%>" value="">
		<h2 align="center" style="margin-top: 0">CADASTRAR PROGRAMAÇÃO</h2>
		<table width="100%">
			<tr>
				<td>
					<table width="60%" align="center" style="background-color: #99CCFF">
						<tbody>
							<tr>
								<th align="right">Turma:</th>
								<td><%	TurmaDAO turmaDAO = new TurmaDAO();
									ArrayList<Turma>colecao = turmaDAO.consultar("", "", "");
									boolean ultimaTurma = false;
									for(int x = 0; x < colecao.size(); x++){
										Turma turma = colecao.get(x);
										
										if(x + 1 == colecao.size()){
											ultimaTurma = true;
										}%>
															
										<%=Select.getInstancia().getHTML(ServletProgramacao.NM_PARAMETRO_SIGLA_TURMA, ServletProgramacao.NM_PARAMETRO_SIGLA_TURMA, String.valueOf(turma.getIdTurma()), turma.getDsTurma(), false, x, ultimaTurma)%>
									<%}%></td>
								<th align="right">Professor:</th>
								<td><%
									ArrayList<LinkedHashMap<String, String>>colecaoProfessor = new ArrayList<LinkedHashMap<String, String>>();
									ConsultaPrincipalProfessor consulta = new ConsultaPrincipalProfessor();
								
									colecaoProfessor = consulta.consultar("", "", "");
									boolean ultimoProfessor = false;
									for(int x = 0; x < colecaoProfessor.size(); x++){
										LinkedHashMap<String, String> dados = colecaoProfessor.get(x);
										
										if(x + 1 == colecao.size()){
											ultimoProfessor = true;
										}%>
															
										<%=Select.getInstancia().getHTML("", "", dados.get("ID_PESSOA"), dados.get("NOME"), false, x, ultimoProfessor)%>
									<%}%></td>
								<th align="right">Disciplina:</th>
								<td><%
									ArrayList<LinkedHashMap<String, String>>colecaoProfessor2 = new ArrayList<LinkedHashMap<String, String>>();
									ConsultaPrincipalProfessor consulta2 = new ConsultaPrincipalProfessor();
								
									colecaoProfessor = consulta.consultar("", "", "");
									boolean ultimoProfessor2 = false;
									for(int x = 0; x < colecaoProfessor.size(); x++){
										LinkedHashMap<String, String> dados = colecaoProfessor.get(x);
										
										if(x + 1 == colecao.size()){
											ultimoProfessor = true;
										}%>
															
										<%=Select.getInstancia().getHTML("", "", dados.get("ID_PESSOA"), dados.get("NOME"), false, x, ultimoProfessor)%>
									<%}%></td>
							</tr>
							<tr>
								<th align="right"> Assuntos 1ª Unidade: </th>
								<td>
									<textarea id="<%=ServletDisciplina.NM_PARAMETRO_TX_PRIMEIRA_UNIDADE%>" name="<%=ServletDisciplina.NM_PARAMETRO_TX_PRIMEIRA_UNIDADE%>" rows="4" cols="30"></textarea>
								
								</td>			
							
								<th align="right"> Assuntos 2ª Unidade: </th>
								<td>
									<textarea id="<%=ServletDisciplina.NM_PARAMETRO_TX_SEGUNDA_UNIDADE%>" name="<%=ServletDisciplina.NM_PARAMETRO_TX_SEGUNDA_UNIDADE%>" rows="4" cols="30"></textarea>
								
								</td>
								<th align="right"> Carga Horária: </th>
								<td>
									<input type="text" id="<%=ServletDisciplina.NM_PARAMETRO_CAMPO_CARGA_HORARIA%>" name="<%=ServletDisciplina.NM_PARAMETRO_CAMPO_CARGA_HORARIA%>" value="">				
								</td>
							</tr>
							<tr>
								<th align="right"> Assuntos 3ª Unidade: </th>
								<td>
									<textarea id="<%=ServletDisciplina.NM_PARAMETRO_TX_TERCEIRA_UNIDADE%>" name="<%=ServletDisciplina.NM_PARAMETRO_TX_TERCEIRA_UNIDADE%>" rows="4" cols="30"></textarea>
								
								</td>			
							
								<th align="right"> Assuntos 4ª Unidade: </th>
								<td>
									<textarea id="<%=ServletDisciplina.NM_PARAMETRO_TX_QUARTA_UNIDADE%>" name="<%=ServletDisciplina.NM_PARAMETRO_TX_QUARTA_UNIDADE%>" rows="4" cols="30"></textarea>
								
								</td>
								
							</tr>
						</tbody>
					</table> <br>
					<table width="50%" align="center">
						<tr>
							<td style="width: 600px; text-align: center">
								<button type="submit" id="botaoCadastrar" name="botaoCadastrar"
									onclick="cadastrar();">Cadastrar</button>
							</td>

							<td style="width: 700px; text-align: center">
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
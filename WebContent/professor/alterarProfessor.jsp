<%@page import="java.util.Iterator"%>
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
<title>Alterar Professor</title>

</head>

<script type="text/javascript">

function desistir(){
	document.getElementById("<%=ServletProfessor.NM_EVENTO%>").value = "<%=ServletProfessor.NM_JSP_CONSULTAR%>";
}

function alterar(){
	document.getElementById("<%=ServletProfessor.NM_EVENTO%>").value = "<%=ServletProfessor.NM_EVENTO_PROCESSAR_ALTERACAO%>";
}

function formatarCPF(event){
	var valor = document.getElementById("<%=ServletProfessor.NM_PARAMETRO_CPF%>").value;
	var tamanhoValor = valor.length;
	var tecla = event.keyCode;
	
	if(tecla != 8 && tecla != 46){
		if(tamanhoValor == 3 || tamanhoValor == 7){
			valor = valor.concat(".");
		}else if(tamanhoValor == 11){
			valor = valor.concat("-");
		}
	}
	document.getElementById("<%=ServletProfessor.NM_PARAMETRO_CPF%>").value = valor;
}

function formatarCamposData(){
	var valorDtNasc = document.getElementById("<%=ServletProfessor.NM_PARAMETRO_DT_NASCIMENTO%>").value;
	var valorDtAdm = document.getElementById("<%=ServletProfessor.NM_PARAMETRO_DT_ADMISSAO%>").value;
	var resDtNasc = valorDtNasc.split("-");
	var resDtAdm = valorDtAdm.split("-");
	var anoDtNasc = resDtNasc[0];
	var mesDtNasc = resDtNasc[1];
	var diaDtNasc = resDtNasc[2];
	var anoDtAdm = resDtAdm[0];
	var mesDtAdm = resDtAdm[1];
	var diaDtAdm = resDtAdm[2];
	
	valorDtNasc = diaDtNasc.concat("/").concat(mesDtNasc).concat("/").concat(anoDtNasc);
	valorDtAdm = diaDtAdm.concat("/").concat(mesDtAdm).concat("/").concat(anoDtAdm);
	document.getElementById("<%=ServletProfessor.NM_PARAMETRO_DT_NASCIMENTO%>").value = valorDtNasc;
	document.getElementById("<%=ServletProfessor.NM_PARAMETRO_DT_ADMISSAO%>").value = valorDtAdm;
}

function formatarCPFOnload(){
	var valor = document.getElementById("<%=ServletProfessor.NM_PARAMETRO_CPF%>").value;
	var tamanhoValor = valor.length;
	
	if(tamanhoValor == 11){
		var primeiraParteCPF = valor.substring(0, 3);
		var segundaParteCPF = valor.substring(3, 6);
		var terceiraParteCPF = valor.substring(6, 9);
		var quartaParteCPF = valor.substring(9, 11);
		
		valor = primeiraParteCPF.concat(".").concat(segundaParteCPF).concat(".").concat(terceiraParteCPF).concat("-").concat(quartaParteCPF);
	}
	
	document.getElementById("<%=ServletProfessor.NM_PARAMETRO_CPF%>").value = valor;
}
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
String salario = "";


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
salario = (String) request.getAttribute(ServletProfessor.NM_PARAMETRO_SALARIO);

%>

<body onload="formatarCPFOnload(); formatarCamposData();">

<jsp:include page="cabecalho.jsp"/>

<form action="ServletProfessor" method="post">
<input type="hidden" id="<%=ServletProfessor.NM_EVENTO%>" name="<%=ServletProfessor.NM_EVENTO%>" value="">
	<h2 align="center">ALTERAR PROFESSOR</h2>
	<table>
		<tbody>
			<tr>			
				<th width="10%" align="right"> Nome: </th>
				<td>
					<input type="text" id="<%=ServletProfessor.NM_PARAMETRO_NOME%>" name="<%=ServletProfessor.NM_PARAMETRO_NOME%>" value="<%=nome%>" size="50">
				
				</td>			
			
				<th align="right"> Data de Nascimento: </th>
				<td>
					<input type="text" id="<%=ServletProfessor.NM_PARAMETRO_DT_NASCIMENTO%>" name="<%=ServletProfessor.NM_PARAMETRO_DT_NASCIMENTO%>" value="<%=dtNascimento%>">		
				</td>
				
				<th align="right"> Naturalidade: </th>
				<td>
					<input type="text" id="<%=ServletProfessor.NM_PARAMETRO_NATURALIDADE%>" name="<%=ServletProfessor.NM_PARAMETRO_NATURALIDADE%>" value="<%=naturalidade%>">
				</td>
			</tr>
			<tr>			
				<th width="10%" align="right"> Endere�o: </th>
				<td>
					<input type="text" id="<%=ServletProfessor.NM_PARAMETRO_ENDERECO%>" name="<%=ServletProfessor.NM_PARAMETRO_ENDERECO%>" value="<%=endereco%>" size="50">
				
				</td>			
			
				<th align="right"> N�mero: </th>
				<td>
					<input type="text" id="<%=ServletProfessor.NM_PARAMETRO_NUMERO%>" name="<%=ServletProfessor.NM_PARAMETRO_NUMERO%>" value="<%=numero%>">		
				</td>
				
				<th align="right"> Bairro: </th>
				<td>
					<input type="text" id="<%=ServletProfessor.NM_PARAMETRO_BAIRRO%>" name="<%=ServletProfessor.NM_PARAMETRO_BAIRRO%>" value="<%=bairro%>">
				</td>
			</tr>
			<tr>			
				<th width="10%" align="right"> Cidade: </th>
				<td>
					<input type="text" id="<%=ServletProfessor.NM_PARAMETRO_CIDADE%>" name="<%=ServletProfessor.NM_PARAMETRO_CIDADE%>" value="<%=cidade%>" size="20">
				
				</td>			
			
				<th align="right"> Estado: </th>
				<td>
					<input type="text" id="<%=ServletProfessor.NM_PARAMETRO_ESTADO%>" name="<%=ServletProfessor.NM_PARAMETRO_ESTADO%>" value="<%=estado%>">		
				</td>
				
				<th align="right"> Telefone: </th>
				<td>
					<input type="text" id="<%=ServletProfessor.NM_PARAMETRO_TELEFONE%>" name="<%=ServletProfessor.NM_PARAMETRO_TELEFONE%>" value="<%=telefone%>">
				</td>
			</tr>
			<tr>			
				<th width="10%" align="right"> Identidade: </th>
				<td>
					<input type="text" id="<%=ServletProfessor.NM_PARAMETRO_IDENTIDADE%>" name="<%=ServletProfessor.NM_PARAMETRO_IDENTIDADE%>" value="<%=identidade%>" size="20">
				
				</td>			
			
				<th align="right"> CPF: </th>
				<td>
					<input type="text" id="<%=ServletProfessor.NM_PARAMETRO_CPF%>" name="<%=ServletProfessor.NM_PARAMETRO_CPF%>" value="<%=cpf%>" onkeyup="formatarCPF(event);" maxlength="14" readonly="readonly">		
				</td>
				
				<th align="right"> Forma��o: </th>
				<td>
					<input type="text" id="<%=ServletProfessor.NM_PARAMETRO_FORMACAO%>" name="<%=ServletProfessor.NM_PARAMETRO_FORMACAO%>" value="<%=formacao%>">
				</td>
			</tr>
			<tr>			
				<th width="10%" align="right"> Estado Civil: </th>
				<td>
					<input type="text" id="<%=ServletProfessor.NM_PARAMETRO_ESTADO_CIVIL%>" name="<%=ServletProfessor.NM_PARAMETRO_ESTADO_CIVIL%>" value="<%=estadoCivil%>" size="20">
				
				</td>			
			
				<th align="right"> Quantidade Dependentes: </th>
				<td>
					<input type="text" id="<%=ServletProfessor.NM_PARAMETRO_QT_DEPENDENTE%>" name="<%=ServletProfessor.NM_PARAMETRO_QT_DEPENDENTE%>" value="<%=qtDependente%>">		
				</td>
				
				<th align="right"> Data Admiss�o: </th>
				<td>
					<input type="text" id="<%=ServletProfessor.NM_PARAMETRO_DT_ADMISSAO%>" name="<%=ServletProfessor.NM_PARAMETRO_DT_ADMISSAO%>" value="<%=dtAdmissao%>">
				</td>
			</tr>
			<tr>			
				<th width="10%" align="right"> Carga Hor�ria: </th>
				<td>
					<input type="text" id="<%=ServletProfessor.NM_PARAMETRO_CARGA_HORARIA%>" name="<%=ServletProfessor.NM_PARAMETRO_CARGA_HORARIA%>" value="<%=cargaHoraria%>" size="10">
				
				</td>			
			
				<th align="right"> Sal�rio: </th>
				<td>
					<input type="text" id="<%=ServletProfessor.NM_PARAMETRO_SALARIO%>" name="<%=ServletProfessor.NM_PARAMETRO_SALARIO%>" value="<%=salario%>">		
				</td>
			</tr>
		</tbody>
	</table>
	<br>
	<table>
	<tr>
		<td style="position: absolute; left: 30%; top: 60%;">
			<button type="submit" id="botaoAlterar" name="botaoAlterar" onclick="alterar();">Alterar</button>				
		</td>
			
		<td style="position: absolute; left: 60%; top: 60%;">
			<button type="submit" id="botaoDesistir" name="botaoDesistir" onclick="desistir();">Voltar</button>				
		</td>
	</tr>
	</table>
</form>

</body>



</html>
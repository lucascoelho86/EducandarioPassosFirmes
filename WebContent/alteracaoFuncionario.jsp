<%@page import="java.util.Iterator"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="br.com.educandariopassosfirmes.entidades.Pessoa"%>
<%@page import="br.com.educandariopassosfirmes.entidades.Funcionario"%>
<%@page import="br.com.educandariopassosfirmes.entidades.ProfessorDisciplina"%>
<%@page import="java.util.List"%>
<%@page import="br.com.educandariopassosfirmes.servlet.ServletPrincipal"%>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>CADASTRO</title>
</head>

<%
try {
%>

<% 
         	
	List<Pessoa> listPessoa = null;
	List<ProfessorDisciplina> listProfessorDisciplina = null;
	ProfessorDisciplina professorDisciplina = null;
	Pessoa pessoa = new Pessoa();
	Funcionario funcionario = null;
	String idFuncao = "";

	listPessoa = (List<Pessoa>)ServletPrincipal.getAtributoOpcional("listPessoa", request);
	listProfessorDisciplina = (List<ProfessorDisciplina>)ServletPrincipal.getAtributoOpcional("listProfessorDisciplina", request);
	funcionario = (Funcionario)ServletPrincipal.getAtributoOpcional("funcionario", request);
	idFuncao = (String)ServletPrincipal.getAtributoOpcional("idFuncao", request);
         	
	if(listPessoa == null){
		listPessoa = new ArrayList<Pessoa>();
	}else{
		pessoa = listPessoa.get(0);
	}
	
	if(listProfessorDisciplina == null){
		listProfessorDisciplina = new ArrayList<ProfessorDisciplina>();
	}
	
	if(funcionario == null){
		funcionario = new Funcionario();
	}
%>

<body>
	<jsp:include page="cabecalho.jsp"/>
	<br>
	<form method="post" action="ServletPrincipal?acao=alterarFuncionario">
		<fieldset id="fieldset_aluno">
				<legend>ALTERAR CADASTRO DE FUNCIONÁRIO</legend>
				
				<TABLE id="table_filtro" class="filtro" cellpadding="0" cellspacing="5">
	                <TBODY>
	                    <TR>
	                        <TH class="campoformulario" align="left" width="10%">Nome:</TH>
	                        <TD class="campoformulario">
	                        	<input type="text" name="nome" size="50" value="<%=pessoa.getNome()%>">
	                        </TD>
	                        <TH class="campoformulario" align="right" width="10%">CPF:</TH>
	                        <TD class="campoformulario">
	                        	<input type="text" name="cpf" value ="<%= pessoa.getId() %>">
	                        </TD>
	                        <TH class="campoformulario" align="right" width="10%">Naturalidade:</TH>
	                        <TD class="campoformulario">
	                        	<input type="text" name="naturalidade"  value ="<%= pessoa.getNaturalidade() %>">
	                        </TD>
	                    </TR>
	                    <TR>
	                    	<TH class="campoformulario" align="left" width="10%">Dt Nascimento:</TH>
	                        <TD class="campoformulario">
	                        	<%
	                        	String data = pessoa.getDtNascimento().toString();

	                        	if(data != null && !data.equals("")){
	                        		String ano = data.substring(0, 4);
	                        		String mes = data.substring(5, 7);
	                        		String dia = data.substring(8);
	                        	%>
	                        		<input type="text" name="dataNascimento"  value ="<%= dia + "/" + mes + "/" + ano %>">
	                        	<%	
	                        	}else{
	                        	%>
	                        		<input type="text" name="dataNascimento">
	                        	<%	
	                        	}
	                        	%>
	                        </TD>
	                    	<TH class="campoformulario" align="right" width="10%">Endereço:</TH>
	                        <TD class="campoformulario">
	                        	<input type="text" name="endereço"  value ="<%= pessoa.getEndereco() %>">
	                        </TD>
	                       <TH class="campoformulario" align="right" width="15%">Número:</TH>
	                        <TD class="campoformulario">
	                        	<input type="text" name="numero" value = "<%= pessoa.getNumero() %>">
	                        </TD> 
	                    </TR>	                    
	                    <TR>
	                    	<TH class="campoformulario" align="left" width="10%">Bairro:</TH>
	                        <TD class="campoformulario">
	                        	<input type="text" name="bairro" value ="<%= pessoa.getBairro() %>">
	                        </TD>
	                        <TH class="campoformulario" align="right" width="10%">Cidade:</TH>
	                        <TD class="campoformulario">
	                        	<input type="text" name="cidade" value = "<%= pessoa.getCidade() %>">
	                        </TD>  
	                        <TH class="campoformulario" align="right" width="15%">Estado:</TH>
	                        <TD class="campoformulario">
	                        	<input type="text" name="estado" value = "<%= pessoa.getEstado() %>">
	                        </TD>
	                    </TR>
	                    <TR>
	                    <TH class="campoformulario" align="left" width="10%">Telefone:</TH>
	                        <TD class="campoformulario">
	                        	<input type="text" name="telefone" value = "<%= pessoa.getTelefone() %>">
	                        </TD>
	                        <TH class="campoformulario" align="right" width="10%">Cadastrar Senha:</TH>
	                        <TD class="campoformulario">
	                        	<input type="text" name="senha" value = "<%= funcionario.getSenha() %>">
	                        </TD>                      	
	                    </TR>
	                </TBODY>
         		</TABLE>
         		<br>
				<b><label>Função</label></b>
				<br>
				<select name="funcao">
				
					<%
					
					for(int x = 0; x < 4; x++){
						if(x == 0){
							if(x == Integer.valueOf(idFuncao)){
						%>
						<option value="<%= x %>" selected>Selecionar</option>
						<%}else{%>
						<option value="<%= x %>">Selecionar</option>
						<%}						
						}
						%>
						
						<%if(x == 1){
							if(x == Integer.valueOf(idFuncao)){
						%>
						<option value="<%= x %>" selected>Administrativo</option>
						<%}else{%>
						<option value="<%= x %>">Administrativo</option>
						<%}						
						}
						%>
						
						<%if(x == 2){
							if(x == Integer.valueOf(idFuncao)){
						%>
						<option value="<%= x %>" selected>Professor</option>
						<%}else{%>
						<option value="<%= x %>">Professor</option>
						<%}						
						}
						%>
						
						<%if(x == 3){
							if(x == Integer.valueOf(idFuncao)){
						%>
						<option value="<%= x %>" selected>Coordenador</option>
						<%}else{%>
						<option value="<%= x %>">Coordenador</option>
						<%}						
						}
						%>
						
						<%if(x == 4){
							if(x == Integer.valueOf(idFuncao)){
						%>
						<option value="<%= x %>" selected>Coordenador/Professor</option>
						<%}else{%>
						<option value="<%= x %>">Coordenador/Professor</option>
						<%}						
						}
						%>
						
					<%
					}
					
					%>
				</select>
				<br>
				<br>
				<b><label>* Se o funcionário tiver a função de professor selecionar a materia que deverá lecionar</label></b>
				<div class="campo" >
				
					<%
					boolean matPort = false;
					boolean MatMate = false;
					boolean MatCien = false;
					boolean MatGeo = false;
					boolean MatIng = false;
					boolean MatHist = false;
					boolean MatNatSoc = false;
					for(int i = 0; i<listProfessorDisciplina.size(); i++){
						professorDisciplina = listProfessorDisciplina.get(i);
						if(professorDisciplina.getId_disciplina() == 2){
							matPort = true;
						}else if(professorDisciplina.getId_disciplina() == 3){
							MatMate = true;
						}else if(professorDisciplina.getId_disciplina() == 4){
							MatCien = true;
						}else if(professorDisciplina.getId_disciplina() == 5){
							MatGeo = true;
						}else if(professorDisciplina.getId_disciplina() == 6){
							MatIng = true;
						}else if(professorDisciplina.getId_disciplina() == 7){
							MatHist = true;
						}else if(professorDisciplina.getId_disciplina() == 1){
							MatNatSoc = true;
						}
					}					
					%>					
					<%
					if(matPort){
					%>
						<input type="checkbox" name="MatPort" value = "2" checked="checked">
					<%
					}else{
					%>
						<input type="checkbox" name="MatPort" value = "2">
					<%
					}
					%>					
					<b><label>Português</label></b>
					<%
					if(MatMate){
					%>
						<input type="checkbox" name="MatMate" value = "3" checked="checked">
					<%
					}else{
					%>
						<input type="checkbox" name="MatMate" value = "3">
					<%
					}
					%>				
					<b><label>Matemática</label></b>	
					<%
					if(MatCien){
					%>
						<input type="checkbox" name="MatCien" value = "4" checked="checked">
					<%
					}else{
					%>
						<input type="checkbox" name="MatCien" value = "4">
					<%
					}
					%>					
					<b><label>Ciências</label></b>
					<%
					if(MatGeo){
					%>
						<input type="checkbox" name="MatGeo" value = "5" checked="checked">
					<%
					}else{
					%>
						<input type="checkbox" name="MatGeo" value = "5">
					<%
					}
					%>					
					<b><label>Geografia</label></b>
					<%
					if(MatIng){
					%>
						<input type="checkbox" name="MatIng" value = "6" checked="checked">
					<%
					}else{
					%>
						<input type="checkbox" name="MatIng" value = "6">
					<%
					}
					%>					
					<b><label>Inglês</label></b>
					<%
					if(MatHist){
					%>
						<input type="checkbox" name="MatHist" value = "7" checked="checked">
					<%
					}else{
					%>
						<input type="checkbox" name="MatHist" value = "7">
					<%
					}
					%>					
					<b><label>História</label></b>
					<%
					if(MatNatSoc){
					%>
						<input type="checkbox" name="MatNatSoc" value = "1" checked="checked">
					<%
					}else{
					%>
						<input type="checkbox" name="MatNatSoc" value = "1">
					<%
					}
					%>					
					<b><label>Natureza e Sociedade</label></b>					
				</div>
				<div class="campo">
					<input type="submit" value="Continuar"/>
				</div>
				<div class="campo">
					<input type="button" value="Voltar" onClick="javascript:history.back();">
				</div>
			</fieldset>
	</form>
<%
} catch(Exception e) {

}
%>
</body>
</html>
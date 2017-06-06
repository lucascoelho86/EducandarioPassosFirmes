<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="br.com.pacote1.entidades.Pessoa"%>
<%@page import="br.com.pacote1.entidades.Turma"%>
<%@page import="java.util.List"%>
<%@page import="br.com.pacote1.controle.PessoaControle"%>
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
	List<Pessoa> listPessoaResponsavel = null;
	Pessoa pessoa = new Pessoa();
	Pessoa pessoaResponsavel = new Pessoa();
	Turma turma = null;

	listPessoa = (List<Pessoa>)PessoaControle.getAtributoOpcional("listPessoa", request);
	listPessoaResponsavel = (List<Pessoa>)PessoaControle.getAtributoOpcional("listResponsavel", request);
	turma = (Turma)PessoaControle.getAtributoOpcional("turma", request);
         	
	if(listPessoa == null){
		listPessoa = new ArrayList<Pessoa>();
	}else{
		pessoa = listPessoa.get(0);
	}
	
	if(listPessoaResponsavel == null){
		listPessoaResponsavel = new ArrayList<Pessoa>();
	}else{
		pessoaResponsavel = listPessoaResponsavel.get(0);
	}
	
	if(turma == null){
		turma = new Turma();
	}
%>

<body>
	<form method="post" action="PessoaControle?acao=alterarFuncionario">
		<fieldset id="fieldset_aluno">
				<legend>ALTERAR CADASTRO DE FUNCIONÁRIO</legend>
				<input type="hidden" name="perfil" value="4" />
				<input type="hidden" name="matricula" value="<%=pessoa.getId()%>"/>
				<input type="hidden" name="idTurmaAtual" value="<%= turma.getIdTurma() %>"/>
				
				<TABLE id="table_filtro" class="filtro" cellpadding="0" cellspacing="5">
	                <TBODY>
	                    <TR>
	                        <TH class="campoformulario" align="left" width="15%">Nome:</TH>
	                        <TD class="campoformulario">
	                        	<input type="text" name="nome" size="50" value="<%=pessoa.getNome()%>">
	                        </TD>
	                        <TH class="campoformulario" align="right" width="15%">CPF:</TH>
	                        <TD class="campoformulario">
	                        	<input type="text" name="cpf" value ="<%= pessoa.getId() %>">
	                        </TD>
	                        <TH class="campoformulario" align="right" width="15%">Naturalidade:</TH>
	                        <TD class="campoformulario">
	                        	<input type="text" name="naturalidade"  value ="<%= pessoa.getNaturalidade() %>">
	                        </TD>
	                    </TR>
	                    <TR>
	                    	<TH class="campoformulario" align="right" width="15%">Dt Nascimento:</TH>
	                        <TD class="campoformulario">
	                        	<input type="text" name="dataNascimento"  value ="<%= pessoa.getDtNascimento() %>">
	                        </TD>
	                    	<TH class="campoformulario" align="left" width="15%">Endereço:</TH>
	                        <TD class="campoformulario">
	                        	<input type="text" name="endereço"  value ="<%= pessoa.getEndereco() %>">
	                        </TD>
	                       <TH class="campoformulario" align="right" width="15%">Número:</TH>
	                        <TD class="campoformulario">
	                        	<input type="text" name="numero" value = "<%= pessoa.getNumero() %>">
	                        </TD> 
	                    </TR>	                    
	                    <TR>
	                    	<TH class="campoformulario" align="right" width="15%">Bairro:</TH>
	                        <TD class="campoformulario">
	                        	<input type="text" name="bairro" value ="<%= pessoa.getBairro() %>">
	                        </TD>
	                        <TH class="campoformulario" align="left" width="15%">Cidade:</TH>
	                        <TD class="campoformulario">
	                        	<input type="text" name="cidade" value = "<%= pessoa.getCidade() %>">
	                        </TD>  
	                        <TH class="campoformulario" align="right" width="15%">Estado:</TH>
	                        <TD class="campoformulario">
	                        	<input type="text" name="estado" value = "<%= pessoa.getEstado() %>">
	                        </TD>
	                    </TR>
	                    <TR>
	                    <TH class="campoformulario" align="right" width="15%">Telefone:</TH>
	                        <TD class="campoformulario">
	                        	<input type="text" name="telefone" value = "<%= pessoa.getTelefone() %>">
	                        </TD>
	                        <TH class="campoformulario" align="right" width="30%">Cadastrar Senha:</TH>
	                        <TD class="campoformulario">
	                        	<input type="text" name="senha">
	                        </TD>                      	
	                    </TR>
	                </TBODY>
         		</TABLE>
				<div class="campo">
					<input type="submit" value="Continuar"/>
				</div>
			</fieldset>
	</form>
<%
} catch(Exception e) {

}
%>
</body>
</html>
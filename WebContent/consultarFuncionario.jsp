<%@page import="java.util.Iterator"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="br.com.pacote1.entidades.TurmaAluno"%>
<%@page import="java.util.ArrayList"%>
<%@page import="br.com.pacote1.controle.PessoaControle"%>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>CONSULTAR</title>
</head>

<%
try {
%>

<% 
         	
	ArrayList<String> colecao = null;
	String cpfTela = "";
	String nomeTela = "";

	colecao = (ArrayList<String>)PessoaControle.getAtributoOpcional("dadosFunc", request);
	cpfTela = (String)PessoaControle.getAtributoOpcional("cpf", request);
	nomeTela = (String)PessoaControle.getAtributoOpcional("nome", request);
         	
	if(colecao == null){
		colecao = new ArrayList<String>();
	}
	
	if(cpfTela == null){
		cpfTela = "";
	}
	
	if(nomeTela == null){
		nomeTela = "";
	}
%>

<body>
	<jsp:include page="cabecalho.jsp"/>
	<br>
	<form method="post" action="PessoaControle?acao=consultarFuncionario">
		<fieldset id="fieldset_aluno">
				<legend>CONSULTAR FUNCIONÁRIO</legend>
				<TABLE id="table_filtro" class="filtro" cellpadding="0" cellspacing="5">
	                <TBODY>
	                    <TR>
	                        <TH class="campoformulario" align="right" width="15%">CPF:</TH>
	                        <TD class="campoformulario">
	                        	<input type="text" name="cpf" value = <%= cpfTela %>>
	                        </TD>
	                        <TH class="campoformulario" align="right" width="15%">Nome:</TH>
	                        <TD class="campoformulario">
	                        	<input type="text" name="nome" size="50" value = <%= nomeTela %>>
	                        </TD>
	                        <TH class="campoformulario" width="15%"></TH>
	                        <TD class="campoformulario">
	                        	<input type="submit" value="Continuar"/>
	                        </TD>
	                    </TR>
	                </TBODY>
         		</TABLE>
				<br>
			</fieldset>
			<br>
			<fieldset id="fieldset_aluno">
				<TABLE id="table_filtro" class="filtro" cellpadding="0" cellspacing="0">
		                <TBODY>
		                    <TR>
	                        <TH class="headertabeladados" width="2%">&nbsp;&nbsp;</TH>
	                        <TH class="headertabeladados" align="left" width="5%" >CPF</TH>
	                        <TH class="headertabeladados" align="left" width="10%" >Nome do Funcionário</TH>
	                        <TH class="headertabeladados" align="left" width="5%" >Função</TH>
	                    </TR>
	                    <%
								Iterator<String> it = colecao.iterator();
		                		String cpf = "";
		                		String nome = "";
		                		String funcao = "";
		                		String idFuncao = "";
		                		String chavePrimaria = "";
		                
		                		while(it.hasNext()){
		                			String dados = it.next();
		                			
		                			String dadosFuncionario[] = dados.split(";");
		                			
		                			cpf = dadosFuncionario[0];
		                			nome = dadosFuncionario[1];
		                			funcao = dadosFuncionario[2];
		                			idFuncao = dadosFuncionario[3];
		                			
		                			chavePrimaria = cpf + ";" + nome + ";" + idFuncao + ";" + funcao;
		                			%>
		                			<TR>
										<TD class="tabeladados">
											<INPUT type="radio" id="rdb_consulta" name="chave" value="<%=chavePrimaria%>" checked>
										</TD>
										<TD class="tabeladados"><%= cpf %></TD>
										<TD class="tabeladados"><%= nome %></TD>
										<TD class="tabeladados"><%= funcao %></TD>
								
	                    			</TR>
		                		<%
		                		}
								%>								
		                </TBODY>
	         	</TABLE>
         	</fieldset>
         	<br>
         	<TABLE id="table_barraacoes" class="barraacoes" cellpadding="0" cellspacing="0">
                <TBODY>
                    <TR>
                    	<TH class="botaofuncao" align="left" width="45%"></TH>
                        <TD class="botaofuncao">
							      <input type="submit" value="Alterar" name="botaoAlterar"/>
                    	</TD>
                    	<TH class="botaofuncao" align="left" width="40%"></TH>
                    	<TD class="botaofuncao">
							      <input type="submit" value="Excluir" name="botaoExcluir"/>
                    	</TD>
                    	<TH class="botaofuncao" align="left" width="40%"></TH>
                    	<TD class="botaofuncao">
							      <input type="button" value="Voltar" onClick="javascript:history.back();">
                    	</TD>
                    </TR>
                </TBODY>
            </TABLE>
	</form>
	
	<%
} catch(Exception e) {

}
%>
	
</body>
</html>
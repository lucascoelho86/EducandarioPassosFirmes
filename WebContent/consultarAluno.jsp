<%@page import="java.util.Iterator"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="br.com.pacote1.entidades.Pessoa"%>
<%@page import="br.com.pacote1.entidades.TurmaAluno"%>
<%@page import="java.util.List"%>
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
         	
	List<Pessoa> listPessoa = null;
	List<TurmaAluno> listTurma = null;
	String matriculaTela = "";
	String nomeTela = "";

	listPessoa = (List<Pessoa>)PessoaControle.getAtributoOpcional("listPessoa", request);
	listTurma = (List<TurmaAluno>)PessoaControle.getAtributoOpcional("listTurma", request);
	matriculaTela = (String)PessoaControle.getAtributoOpcional("matricula", request);
	nomeTela = (String)PessoaControle.getAtributoOpcional("nome", request);
         	
	if(listPessoa == null){
		listPessoa = new ArrayList<Pessoa>();
	}
	
	if(listTurma == null){
		listTurma = new ArrayList<TurmaAluno>();
	}
	
	if(matriculaTela == null){
		matriculaTela = "";
	}
	
	if(nomeTela == null){
		nomeTela = "";
	}
%>

<body>
	<form method="post" action="PessoaControle?acao=consultarAluno">
		<fieldset id="fieldset_aluno">
				<legend>CONSULTAR ALUNO</legend>
				<TABLE id="table_filtro" class="filtro" cellpadding="0" cellspacing="5">
	                <TBODY>
	                    <TR>
	                        <TH class="campoformulario" align="right" width="15%">Matrícula:</TH>
	                        <TD class="campoformulario">
	                        	<input type="text" name="matricula" value = <%= matriculaTela %>>
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
	                        <TH class="headertabeladados" align="left" width="" >Matrícula</TH>
	                        <TH class="headertabeladados" align="left" width="" >Nome do Aluno</TH>
	                        <TH class="headertabeladados" align="left" width="" >Turma</TH>
	                    </TR>
	                    <%
								Iterator<Pessoa> it = listPessoa.iterator();
		                		Pessoa pessoa = null;
		                		String matricula = "";
		                		String nome = "";
		                		String chavePrimaria = "";
		                
		                		while(it.hasNext()){
		                			pessoa = it.next();
		                			matricula = pessoa.getId();
		                			nome = pessoa.getNome();
		                			
		                			Iterator<TurmaAluno> itTurmaAluno = listTurma.iterator();
		                			TurmaAluno turmaAluno = null;
		                			int cdTurma = 0;
		                			
		                			while(itTurmaAluno.hasNext()){
		                				turmaAluno = itTurmaAluno.next();
		                				
		                				if(matricula.equals(turmaAluno.getIdAluno())){
		                					cdTurma = turmaAluno.getIdTurma();
		                					break;
		                				}
		                			}
		                			
		                			chavePrimaria = matricula + ";" + nome + ";" + cdTurma;
		                			%>
		                			<TR>
										<TD class="tabeladados">
											<INPUT type="radio" id="rdb_consulta" name="chave" value="<%=chavePrimaria%>" checked>
										</TD>
										<TD class="tabeladados"><%= matricula %></TD>
										<TD class="tabeladados"><%= nome %></TD>
										<TD class="tabeladados"><%= String.valueOf(cdTurma) %></TD>
								
	                    			</TR>
		                		<%
		                		}
								%>
								
								<TR>
            <TD class="conteinerbarraacoes">
            <TABLE id="table_barraacoes" class="barraacoes" cellpadding="0" cellspacing="0">
                <TBODY>
                    <TR>
                        <TD class="botaofuncao">
							      <input type="submit" value="Alterar" name="botaoAlterar"/>
                    	</TD>
                    </TR>
                </TBODY>
            </TABLE>
            </TD>
        </TR>
		                </TBODY>
	         	</TABLE>
         	</fieldset>
	</form>
	
	<%
} catch(Exception e) {

}
%>
	
</body>
</html>
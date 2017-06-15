<%@page import="java.util.Iterator"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="br.com.educandariopassosfirmes.entidades.Pessoa"%>
<%@page import="br.com.educandariopassosfirmes.entidades.TurmaAluno"%>
<%@page import="br.com.educandariopassosfirmes.entidades.Turma"%>
<%@page import="br.com.educandariopassosfirmes.dao.TurmaDAO"%>
<%@page import="java.util.List"%>
<%@page import="br.com.educandariopassosfirmes.servlet.ServletPrincipal"%>
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

	listPessoa = (List<Pessoa>)ServletPrincipal.getAtributoOpcional("listPessoa", request);
	listTurma = (List<TurmaAluno>)ServletPrincipal.getAtributoOpcional("listTurma", request);
	matriculaTela = (String)ServletPrincipal.getAtributoOpcional("matricula", request);
	nomeTela = (String)ServletPrincipal.getAtributoOpcional("nome", request);
         	
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
	<jsp:include page="cabecalho.jsp"/>
	<br>
	<form method="post" action="ServletPrincipal?acao=consultarAluno">
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
	                        	<input type="submit" name="botaoConsultar" value="Continuar"/>
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
		                        <TH class="headertabeladados" align="left" width="5%" >Matrícula</TH>
		                        <TH class="headertabeladados" align="left" width="10%" >Nome do Aluno</TH>
		                        <TH class="headertabeladados" align="left" width="5%" >Turma</TH>
	                    	</TR>
	                    <%
								Iterator<Pessoa> it = listPessoa.iterator();
		                		Pessoa pessoa = null;
		                		String matricula = "";
		                		String nome = "";
		                		String chavePrimaria = "";
		                		Turma turma = new Turma();
		                		TurmaDAO turmaDAO = new TurmaDAO();
		                
		                		while(it.hasNext()){
		                			pessoa = it.next();
		                			matricula = pessoa.getId();
		                			nome = pessoa.getNome();
		                			
		                			Iterator<TurmaAluno> itTurmaAluno = listTurma.iterator();
		                			TurmaAluno turmaAluno = null;
		                			int cdTurma = 0;
		                			String dsTurma = "";
		                			
		                			while(itTurmaAluno.hasNext()){
		                				turmaAluno = itTurmaAluno.next();
		                				
		                				if(matricula.equals(turmaAluno.getIdAluno())){
		                					cdTurma = turmaAluno.getIdTurma();
		                					turma = turmaDAO.consultar(cdTurma);
		                					dsTurma = turma.getDsTurma();
		                					break;
		                				}
		                			}
		                			
		                			chavePrimaria = matricula + ";" + nome + ";" + cdTurma + ";" + dsTurma;
		                			%>
		                			<TR>
										<TD class="tabeladados">
											<INPUT type="radio" id="rdb_consulta" name="chave" value="<%=chavePrimaria%>" checked>
										</TD>
										<TD class="tabeladados"><%= matricula %></TD>
										<TD class="tabeladados"><%= nome %></TD>
										<TD class="tabeladados"><%= dsTurma %></TD>
								
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
                    	<TH class="botaofuncao" align="left" width="20%"></TH>
                        <TD class="botaofuncao">
							      <input type="submit" value="Boletim" name="botaoRelatorio"/>
                    	</TD>
                    	<TH class="botaofuncao" align="left" width="20%"></TH>
                        <TD class="botaofuncao">
							      <input type="submit" value="Alterar" name="botaoAlterar"/>
                    	</TD>
                    	<TH class="botaofuncao" align="left" width="40%"></TH>
                    	<TD class="botaofuncao">
							      <input type="submit" value="Excluir" name="botaoExcluir"/>
                    	</TD>
                    	<TH class="botaofuncao" align="left" width="30%"></TH>
                    	<TD class="botaofuncao">
							      <input type="submit" name="menu" value="Voltar">
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
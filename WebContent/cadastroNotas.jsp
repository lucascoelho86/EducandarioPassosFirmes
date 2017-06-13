<%@page import="java.util.Iterator"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="br.com.pacote1.entidades.Pessoa"%>
<%@page import="br.com.pacote1.entidades.TurmaAluno"%>
<%@page import="br.com.pacote1.entidades.Turma"%>
<%@page import="br.com.pacote1.entidades.Nota"%>
<%@page import="br.com.pacote1.jdbc.TurmaDAO"%>
<%@page import="br.com.pacote1.entidades.Disciplina"%>
<%@page import="br.com.pacote1.jdbc.DisciplinaDAO"%>
<%@page import="java.util.List"%>
<%@page import="br.com.pacote1.controle.PessoaControle"%>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>NOTAS</title>
</head>

<%
try {
%>

<% 
         	
	List<Pessoa> listPessoa = null;
	List<Nota> listNotas = null;
	String matriculaTela = "";
	String nomeTela = "";
	String turmaTela = "";
	String disciplinaTela = "";
	String unidadeTela = "";

	listPessoa = (List<Pessoa>)PessoaControle.getAtributoOpcional("listPessoa", request);
	listNotas = (List<Nota>)PessoaControle.getAtributoOpcional("listNotas", request);
	matriculaTela = (String)PessoaControle.getAtributoOpcional("matricula", request);
	nomeTela = (String)PessoaControle.getAtributoOpcional("nome", request);
	turmaTela = (String)PessoaControle.getAtributoOpcional("turma", request);
	disciplinaTela = (String)PessoaControle.getAtributoOpcional("disciplina", request);
	unidadeTela = (String)PessoaControle.getAtributoOpcional("unidade", request);
         	
	if(listPessoa == null){
		listPessoa = new ArrayList<Pessoa>();
	}
	
	if(matriculaTela == null){
		matriculaTela = "";
	}
	
	if(nomeTela == null){
		nomeTela = "";
	}
	
	if(turmaTela == null){
		turmaTela = "";
	}
	
	if(disciplinaTela == null){
		disciplinaTela = "";
	}
	
	if(unidadeTela == null){
		unidadeTela = "";
	}
%>

<body>
	<jsp:include page="cabecalho.jsp"/>
	<br>
	<form method="post" action="PessoaControle?acao=cadastrarNotas">
		<fieldset id="fieldset_aluno">
				<legend>NOTAS</legend>
				<TABLE id="table_filtro" class="filtro" cellpadding="0" cellspacing="0">
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
	                        <TH class="campoformulario" align="right" width="15%">Turma:</TH>
	                        <TD class="campoformulario">
	                        	<select name="turma">
	                        		
	                        		<%
	                        			Turma turma = new Turma();
	                        			TurmaDAO turmaDAO = new TurmaDAO();
	                        			String dsTurma = "";
	                        			
	                        			for(int x = 0; x <= 9; x++){
	                        				
	                        				if(x > 0){
	                        					turma = turmaDAO.consultar(x);
	                        					dsTurma = turma.getDsTurma();
	                        					
	                        					if(turmaTela.equals(String.valueOf(turma.getIdTurma()))){
	                        		%>
	                        						<option value="<%= String.valueOf(turma.getIdTurma()) %>" selected><%= dsTurma %></option>
	                        		<%
	                        					}else{
	                        		%>	
	                        						<option value="<%= String.valueOf(turma.getIdTurma()) %>"><%= dsTurma %></option>
	                        		<%			
	                        					}
	                        				}else{	                        					
	                        		%>
	                        					<option value="<%= x %>">Selecionar</option>
	                        		<%
	                        				}
	                        			}
	                        		%>
								</select>
	                        </TD>
	                        <TH class="campoformulario" align="right" width="15%">Disciplina:</TH>
	                        <TD class="campoformulario">
	                        	<select name="disciplina">
	                        		
	                        		<%
	                        			Disciplina disciplina = new Disciplina();
	                        			DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
	                        			String dsDisciplina = "";
	                        			
	                        			for(int x = 0; x <= 7; x++){
	                        				
	                        				if(x > 0){
	                        					disciplina = disciplinaDAO.consultar(x);
	                        					dsDisciplina = disciplina.getDsDisciplina();
	                        					
	                        					if(disciplinaTela.equals(String.valueOf(disciplina.getIdDisciplina()))){
	                        		%>
	                        						<option value="<%= String.valueOf(disciplina.getIdDisciplina()) %>" selected><%= dsDisciplina %></option>
	                        		<%
	                        					}else{
	                        		%>			
	                        						<option value="<%= String.valueOf(disciplina.getIdDisciplina()) %>"><%= dsDisciplina %></option>
	                        		<%			
	                        					}
	                        				}else{	                        					
	                        		%>
	                        					<option value="<%= x %>">Selecionar</option>
	                        		<%
	                        				}
	                        			}
	                        		%>
								</select>
	                        </TD>
	                        
	                        <TH class="campoformulario" align="right" width="15%">Unidade:</TH>
	                        <TD class="campoformulario">
	                        	<select name="unidade">
	                        		<%
	                        			if(unidadeTela.equals("") || unidadeTela.equals("0")){
	                        		%>
	                        				<option value="0" selected>Selecionar</option>
	                        				<option value="1">1ª Unidade</option>
	                        				<option value="2">2ª Unidade</option>
	                        				<option value="3">3ª Unidade</option>
	                        				<option value="4">4ª Unidade</option>
	                        		<%
	                        			}else if(unidadeTela.equals("1")){
	                        		%>
	                        				<option value="0">Selecionar</option>
	                        				<option value="1" selected>1ª Unidade</option>
	                        				<option value="2">2ª Unidade</option>
	                        				<option value="3">3ª Unidade</option>
	                        				<option value="4">4ª Unidade</option>
	                        		<%
	                        			}else if(unidadeTela.equals("2")){
	                        		%>
	                        				<option value="0">Selecionar</option>
	                        				<option value="1">1ª Unidade</option>
	                        				<option value="2" selected>2ª Unidade</option>
	                        				<option value="3">3ª Unidade</option>
	                        				<option value="4">4ª Unidade</option>
	                        		<%
	                        			}else if(unidadeTela.equals("3")){
	                        		%>
	                        				<option value="0">Selecionar</option>
	                        				<option value="1">1ª Unidade</option>
	                        				<option value="2">2ª Unidade</option>
	                        				<option value="3" selected>3ª Unidade</option>
	                        				<option value="4">4ª Unidade</option>
	                        		<%
	                        			}else if(unidadeTela.equals("4")){
	                        		%>
	                        				<option value="0">Selecionar</option>
	                        				<option value="1">1ª Unidade</option>
	                        				<option value="2">2ª Unidade</option>
	                        				<option value="3">3ª Unidade</option>
	                        				<option value="4" selected>4ª Unidade</option>
	                        		<%
	                        			}
	                        		%>
								</select>
	                         </TD>
	                    </TR>
	                </TBODY>
         		</TABLE>
         		<br>
         		<TABLE id="table_filtro" class="filtro" cellpadding="0" cellspacing="0">
	                <TBODY>
	                    <TR>
	                    	<TH class="campoformulario" width="15%"></TH>
	                        <TD class="campoformulario">
	                        	<input type="submit" value="Continuar"/>
	                        </TD>
	                    </TR>
	                </TBODY>
         		</TABLE>
			</fieldset>
			<br>
			<fieldset id="fieldset_aluno">
				<TABLE id="table_filtro" class="filtro" cellpadding="0" cellspacing="0">
		                <TBODY>
		                    <TR>
		                        <TH class="headertabeladados" align="left" width="5%" >Matrícula</TH>
		                        <TH class="headertabeladados" align="left" width="10%" >Nome do Aluno</TH>
		                        <TH class="headertabeladados" align="left" width="5%" >Teste</TH>
		                        <TH class="headertabeladados" align="left" width="5%" >Prova</TH>
		                        <TH class="headertabeladados" align="left" width="5%" >Trabalho</TH>
	                    	</TR>
	                    	<%
								Iterator<Pessoa> it = listPessoa.iterator();
		                		Pessoa pessoa = null;
		                		String matricula = "";
		                		String nome = "";
		                		String chavePrimaria = "";
		                		String valoresNotas[];
		                		ArrayList<String> colecaoNotas = new ArrayList<String>();
		                		String divisor = "*";
		                		int contador = 0;
		                
		                		while(it.hasNext()){
		                			pessoa = it.next();
		                			matricula = pessoa.getId();
		                			nome = pessoa.getNome();
		                			
		                			chavePrimaria = matricula;
		                			
		                			Iterator<Nota> itNota = listNotas.iterator();
		                			String notaTeste = "";
			                		String notaProva = "";
			                		String notaTrabalho = "";
		                			while(itNota.hasNext()){
		                				Nota nota = itNota.next();
		                				
		                				if(nota.getIdAluno().equals(matricula)){
		                				
			                				if(nota.getIdTpNota() == 1){
			                					notaTeste = String.valueOf(nota.getNota());
			                				}else if(nota.getIdTpNota() == 2){
			                					notaProva = String.valueOf(nota.getNota());
			                				}else if(nota.getIdTpNota() == 3){
			                					notaTrabalho = String.valueOf(nota.getNota());
			                				}
		                				}
		                			}
		                			%>
		                			<TR>
										<TD class="tabeladados"><%= matricula %></TD>
										<TD class="tabeladados"><%= nome %></TD>										
										<TD class="tabeladados">
											<INPUT type="text" name="teste<%=contador%>" value="<%=notaTeste%>">
										</TD>
										<TD class="tabeladados">
											<INPUT type="text" name="prova<%=contador%>" value="<%=notaProva%>">
										</TD>
										<TD class="tabeladados">
											<INPUT type="text" name="trabalho<%=contador%>" value="<%=notaTrabalho%>">
											<INPUT type="hidden" name="chave<%=contador%>" value="<%=chavePrimaria%>">
										</TD>
	                    			</TR>
		                		<%
		                		contador++;
		                		}		                		
								%>
		                </TBODY>
	         	</TABLE>
	         	<INPUT type="hidden" name="contador" value="<%=contador + ""%>">
         	</fieldset>
         	<br>
         	<TABLE id="table_barraacoes" class="barraacoes" cellpadding="0" cellspacing="0">
                <TBODY>
                    <TR>
                    	<TH class="botaofuncao" align="left" width="10%"></TH>
                        <TD class="botaofuncao">
							      <input type="submit" value="Salvar" name="botaoSalvar"/>
                    	</TD>
                    	<TH class="botaofuncao" align="left" width="20%"></TH>
                        <TD class="botaofuncao">
							      <input type="submit" value="Alterar" name="botaoAlterar"/>
                    	</TD>
                    	<TH class="botaofuncao" align="left" width="30%"></TH>
                    	<TD class="botaofuncao">
							      <input type="submit" value="Excluir" name="botaoExcluir"/>
                    	</TD>
                    	<TH class="botaofuncao" align="left" width="30%"></TH>
                    	<TD class="botaofuncao">
							      <input type="submit" value="Voltar" name="botaoVoltar"/>
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
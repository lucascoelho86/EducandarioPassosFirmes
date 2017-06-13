<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="br.com.pacote1.entidades.Turma"%>
<%@page import="br.com.pacote1.jdbc.TurmaDAO"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>CADASTRO</title>
</head>
<body>
	<jsp:include page="cabecalho.jsp"/>
	<br>
	<form method="post" action="PessoaControle?acao=cadastroAluno">
		<fieldset id="fieldset_aluno">
				<legend>CADASTRO DE ALUNO</legend>
				<input type="hidden" name="perfilAluno" value="4" />
				<input type="hidden" name="perfilResp" value="5" />
				
				<TABLE id="table_filtro" class="filtro" cellpadding="0" cellspacing="5">
	                <TBODY>
	                    <TR>
	                        <TH class="campoformulario" align="left" width="15%">Nome:</TH>
	                        <TD class="campoformulario">
	                        	<input type="text" name="nome" size="50">
	                        </TD>
	                        <TH class="campoformulario" align="right" width="15%">Dt Nascimento:</TH>
	                        <TD class="campoformulario">
	                        	<input type="text" name="dataNascimento">
	                        </TD>
	                        <TH class="campoformulario" align="right" width="15%">Naturalidade:</TH>
	                        <TD class="campoformulario">
	                        	<input type="text" name="naturalidade">
	                        </TD>
	                    </TR>
	                    <TR>
	                    	<TH class="campoformulario" align="left" width="15%">Endere�o:</TH>
	                        <TD class="campoformulario">
	                        	<input type="text" name="endere�o">
	                        </TD>
	                       <TH class="campoformulario" align="right" width="15%">N�mero:</TH>
	                        <TD class="campoformulario">
	                        	<input type="text" name="numero">
	                        </TD> 
	                        <TH class="campoformulario" align="right" width="15%">Bairro:</TH>
	                        <TD class="campoformulario">
	                        	<input type="text" name="bairro">
	                        </TD>                        	
	                    </TR>	                    
	                    <TR>
	                        <TH class="campoformulario" align="left" width="15%">Cidade:</TH>
	                        <TD class="campoformulario">
	                        	<input type="text" name="cidade">
	                        </TD>  
	                        <TH class="campoformulario" align="right" width="15%">Estado:</TH>
	                        <TD class="campoformulario">
	                        	<input type="text" name="estado">
	                        </TD>
	                        <TH class="campoformulario" align="right" width="15%">Telefone:</TH>
	                        <TD class="campoformulario">
	                        	<input type="text" name="telefone">
	                        </TD>                 	
	                    </TR>
	                    <TR>
	                       <TH class="campoformulario" align="left" width="15%">Nome Respons�vel:</TH>
	                        <TD class="campoformulario">
	                        	<input type="text" name="nomeResponsavel" size="50">
	                        </TD> 
	                        <TH class="campoformulario" align="right" width="15%">CPF Respons�vel:</TH>
	                        <TD class="campoformulario">
	                        	<input type="text" name="cpfResponsavel">
	                        </TD>
	                        <TH class="campoformulario" align="right" width="15%">Dt Nascimento Resp:</TH>
	                        <TD class="campoformulario">
	                        	<input type="text" name="dataNascimentoResp">
	                        </TD>
	                    </TR>
	                    <TR>
	                       <TH class="campoformulario" align="left" width="15%">Naturalidade Respons�vel:</TH>
	                        <TD class="campoformulario">
	                        	<input type="text" name="naturalidadeResp">
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
	                        		%>
	                        					<option value="<%= String.valueOf(turma.getIdTurma()) %>"><%= dsTurma %></option>
	                        		<%
	                        				}else{	                        					
	                        		%>
	                        					<option value="<%= x %>">Selecionar</option>
	                        		<%
	                        				}
	                        			}
	                        		%>
								</select>
	                        </TD>
	                    </TR>
	                </TBODY>
         		</TABLE>
			</fieldset>
			<TABLE id="table_filtro" class="filtro" cellpadding="0" cellspacing="5">
	                <TBODY>
	                    <TR>
	                        <TH class="campoformulario" align="left" width="20%">CONFIRMAR INCLUS�O?</TH>
	                        <TD class="campoformulario">
	                        	<input type="submit" value="Continuar"/>
	                        </TD>
	                        <TH class="campoformulario" align="right" width="20%">DESISTIR DA INCLUS�O?</TH>
	                        <TD class="campoformulario">
	                        	<input type="button" value="Voltar" onClick="javascript:history.back();">
	                        </TD>
	                    </TR>
	                </TBODY>
         	</TABLE>
	</form>
</body>
</html>
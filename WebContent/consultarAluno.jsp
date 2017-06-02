<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>CONSULTAR</title>
</head>
<body>
	<form method="post" action="PessoaControle?acao=consultarAluno">
		<fieldset id="fieldset_aluno">
				<legend>CONSULTAR ALUNO</legend>
				<TABLE id="table_filtro" class="filtro" cellpadding="0" cellspacing="5">
	                <TBODY>
	                    <TR>
	                        <TH class="campoformulario" align="right" width="15%">Matrícula:</TH>
	                        <TD class="campoformulario">
	                        	<input type="text" name="matricula">
	                        </TD>
	                        <TH class="campoformulario" align="right" width="15%">Nome:</TH>
	                        <TD class="campoformulario">
	                        	<input type="text" name="nome" size="50">
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
				<TABLE id="table_filtro" class="filtro" cellpadding="0" cellspacing="5">
		                <TBODY>
		                    <TR>
	                        <TH class="headertabeladados" width="3%">&nbsp;&nbsp;X</TH>
	                        <TH class="headertabeladados" width="3%" >Matrícula</TH>
	                        <TH class="headertabeladados" width="22%" >Nome do Aluno</TH>
	                        <TH class="headertabeladados" width="" >Turma</TH>
	                    </TR>
		                </TBODY>
	         	</TABLE>
         	</fieldset>
	</form>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>HOME</title>
</head>
<body>
	<form method="post" action="PessoaControle?acao=cadastro">
		<fieldset id="fieldset_aluno">
				<legend>ALUNO</legend>
				<div class="campo" >
					<label>Cadastrar</label>				
					<input type="checkbox" name="CadastrarAluno">
					<label>Consultar</label>				
					<input type="checkbox" name="ConsultarAluno">
				</div>
			</fieldset>
			<fieldset id="fieldset_funcionario">
				<legend>FUNCION�RIO</legend>
				<div class="campo" >
					<label>Cadastrar</label>				
					<input type="checkbox" name="CadastrarFunc">
					<label>Consultar</label>				
					<input type="checkbox" name="ConsultarFunc">
				</div>
		</fieldset>
		<br>
		<div class="campo">
			<input type="submit" value="Continuar"/>
		</div>
	</form>
</body>
</html>
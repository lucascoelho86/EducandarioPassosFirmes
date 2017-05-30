<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
</head>
<body>
	<form method="post" action="PessoaControle?acao=login">
		
		<fieldset id="fieldset_login">		
			<legend>Login do Sistema</legend>
			<div class="campo">
				<div class="label">
					<label for="login">CPF</label>
				</div>
				<input type="text" id="login" name="login" maxlength="15"/>
			</div>
			
			<div class="campo">
				<div class="label">
					<label for="senha">Senha</label>
				</div>
				<input type="password" id="senha" name="senha" maxlength="15"/>
			</div>
			
			<div class="campo">
				<input type="submit" value="Logar"/>
			</div>
		</fieldset>
	</form>
</body>
</html>
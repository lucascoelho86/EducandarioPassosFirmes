<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>CADASTRO</title>
</head>
<body>
	<form method="post" action="PessoaControle?acao=cadastroAluno">
		<fieldset id="fieldset_aluno">
				<legend>CADASTRO DE ALUNO</legend>
				<input type="hidden" name="perfil" value="4" />
				<div class="campo">
					<label>Nome</label>
					<input type="text" name="nome" size="50">
					<label>Data de Nascimento</label>				
					<input type="text" name="dataNascimento">
				</div>
				<br>
				<div class="campo" >
					<label>Naturalidade</label>
					<input type="text" name="naturalidade">
					<label>Endere�o</label>				
					<input type="text" name="endere�o">
				</div>
				<br>
				<div class="campo" >
					<label>N�mero</label>				
					<input type="text" name="numero">
					<label>Bairro</label>				
					<input type="text" name="bairro">
				</div>
				<br>
				<div class="campo" >
					<label>Cidade</label>				
					<input type="text" name="cidade">
					<label>Estado</label>				
					<input type="text" name="estado">
				</div>
				<div class="campo" >
					<label>Telefone</label>				
					<input type="text" name="telefone">
				</div>
				<br>
				<div class="campo" >
					<label>Nome Respons�vel</label>				
					<input type="text" name="nomeResponsavel">
				</div>
				<br>
				<div class="campo" >
					<label>CPF Respons�vel</label>				
					<input type="text" name="cpfResponsavel">
					<br>
					<label>Data de Nascimento Respons�vel</label>				
					<input type="text" name="dataNascimentoResp">
					<br>
					<label>Naturalidade Respons�vel</label>
					<input type="text" name="naturalidadeResp">
				</div>
				<br>
				<label>Turma</label>
				<select name="turma">
					<option value="0">Selecionar</option>
					<option value="1">4A</option>
				</select>
				<br>
				<div class="campo">
					<input type="submit" value="Continuar"/>
				</div>
			</fieldset>
	</form>
</body>
</html>
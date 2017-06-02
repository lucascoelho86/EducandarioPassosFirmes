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
					<label>Endereço</label>				
					<input type="text" name="endereço">
				</div>
				<br>
				<div class="campo" >
					<label>Número</label>				
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
					<label>Nome Responsável</label>				
					<input type="text" name="nomeResponsavel">
				</div>
				<br>
				<div class="campo" >
					<label>CPF Responsável</label>				
					<input type="text" name="cpfResponsavel">
					<br>
					<label>Data de Nascimento Responsável</label>				
					<input type="text" name="dataNascimentoResp">
					<br>
					<label>Naturalidade Responsável</label>
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
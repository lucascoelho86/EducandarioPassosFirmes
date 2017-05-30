<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>HOME</title>
</head>
<body>
	<form method="post" action="PessoaControle?acao=cadastroFuncionario">
	
		<fieldset id="fieldset_funcionario">
				<legend>CADASTRO DE FUNCIONÁRIO</legend>
				
				<TABLE id="table_filtro" class="filtro" cellpadding="0" cellspacing="5">
	                <TBODY>
	                    <TR>
	                        <TH class="campoformulario" align="left" width="15%">Nome:</TH>
	                        <TD class="campoformulario">
	                        	<input type="text" name="nome" size="50">
	                        </TD>
	                        <TH class="campoformulario" align="right" width="15%">CPF:</TH>
	                        <TD class="campoformulario">
	                        	<input type="text" name="cpf">
	                        </TD>
	                        <TH class="campoformulario" align="right" width="15%">Naturalidade:</TH>
	                        <TD class="campoformulario">
	                        	<input type="text" name="naturalidade">
	                        </TD>
	                    </TR>
	                    <TR>
	                    	<TH class="campoformulario" align="left" width="20%">Dt Nascimento:</TH>
	                        <TD class="campoformulario">
	                        	<input type="text" name="dataNascimento">
	                        </TD>
	                       <TH class="campoformulario" align="right" width="15%">Endereço:</TH>
	                        <TD class="campoformulario">
	                        	<input type="text" name="endereço" size="50">
	                        </TD> 
	                        <TH class="campoformulario" align="right" width="15%">Número:</TH>
	                        <TD class="campoformulario">
	                        	<input type="text" name="numero">
	                        </TD>                        	
	                    </TR>	                    
	                    <TR>
	                       <TH class="campoformulario" align="left" width="15%">Bairro:</TH>
	                        <TD class="campoformulario">
	                        	<input type="text" name="bairro">
	                        </TD>  
	                        <TH class="campoformulario" align="right" width="15%">Cidade:</TH>
	                        <TD class="campoformulario">
	                        	<input type="text" name="cidade">
	                        </TD>  
	                        <TH class="campoformulario" align="right" width="15%">Estado:</TH>
	                        <TD class="campoformulario">
	                        	<input type="text" name="estado">
	                        </TD>                   	
	                    </TR>
	                    <TR>
	                       <TH class="campoformulario" align="left" width="15%">Telefone:</TH>
	                        <TD class="campoformulario">
	                        	<input type="text" name="telefone">
	                        </TD> 
	                        <TH class="campoformulario" align="right" width="30%">Cadastrar Senha:</TH>
	                        <TD class="campoformulario">
	                        	<input type="text" name="senha">
	                        </TD>                      	
	                    </TR>
	                </TBODY>
         		</TABLE>
         		<br>
				<b><label>Função</label></b>
				<br>
				<select name="perfil">
					<option value="0">Selecionar</option>
					<option value="1">Administrativo</option>
					<option value="2">Professor</option>
					<option value="3">Coordenador</option>
					<option value="4">Coordenador/Professor</option>
				</select>
				<br>
				<br>
				<div class="campo">
					<input type="submit" value="Continuar"/>
				</div>
			</fieldset>
	</form>
</body>
</html>
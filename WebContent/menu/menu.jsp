<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="cssProjeto.css">
<title>Menu</title>

</head>

<body>

<jsp:include page="cabecalho.jsp"/>

<form name="frm_principal" action="ServletMenu" method="post">
	<h2 align="center">MENU</h2>
	<table>
  		<tr>
    		<td width="10%"><a href = "ServletAluno"> <img width="250px" height="250px" src="img/peAluno.png"/></a></td>
    		<td width="10%"><a href = "ServletProfessor"> <img width="250px" height="250px" src="img/peProfessor.png" align="right"/></a></td>
    		<td width="10%"><a href = "ServletDisciplina"> <img width="250px" height="250px" src="img/peDisciplina.png" align="right"/></a></td>
    		<td width="10%"><a href = "ServletTurma"> <img width="250px" height="250px" src="img/peTurma.png"/></a></td>
    		<td width="10%"><a href = "ServletProgramacao"> <img width="250px" height="250px" src="img/peProgramacao.png"/></a></td>
  		</tr>
	</table>
</form>
</body>



</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cadastro</title>

</head>
<body>



<form name="form1" method="get" action="/cadastrar">
<table>
	<tr>
		<td><font color="red" size="14"><%= request.getSession().getAttribute("mensagem")!= null ?request.getSession().getAttribute("mensagem"):""  %></font>
	</tr>
	<tr>
		<td>Digite os dados do livro</td>
	</tr>

	<tr>
		<td>Título</td>
		<td><input type="text" name="titulo"></td>
	</tr>
	
	<tr>
		<td>Editora</td>
		<td><input type="text" name="editora"></td>
	</tr>
	<tr>
		<td><button type="submit" >Enviar</button></td>
		<td><input type="reset" value="Limpar"></td>
	</tr>

</table>
</form>

<% request.getSession().removeAttribute("mensagem"); %>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>AJOUT</h1>
<form method="post" action="<%= request.getContextPath()%>/ServletAjoutRepas">
	<p>
		<label for="date">Date : </label>
		<input type="date" id="date" name="date">
	</p>
	<p>
		<label for="time">Time : </label>
		<input type="time" id="time" name="time">
	</p>
	<p>
		<label for="aliments">Aliments : </label>
		<textarea id="aliments" name="aliments"></textarea>
	</p>
	<input type="submit" value="Valider">
	<input  type="button"
			value="Annuler"
			onclick="window.location='<%= request.getContextPath()%>/accueil'">
</form>
</body>
</html>
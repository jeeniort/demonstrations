<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ajout de repas</title>
</head>
<body>
<h1>Ajout de repas</h1><hr/>
<%
	List<String> erreurs = (List<String>)request.getAttribute("erreurs");
	if(erreurs != null && erreurs.size() > 0) {
		out.print("<ul>");
		for (String err : erreurs) {
			out.print("<li>"+err+"</li>");
		}
		out.print("</ul>");
	}
%>
<form method="post" action="<%= request.getContextPath() %>/ServletAjoutRepas">
<p>
	<label for="date">Date : </label>
	<input id="date" name="date" type="date">
</p>
<p>
	<label for="time">Heure : </label>
	<input id="time" name="time" type="time">
</p>
<p>
	<label for="repas">Repas : </label>
	<textarea id="repas" name="repas"></textarea>
</p>
	
	<input type="submit" value="Valider">
	<input type="button" value="Annuler" onclick="window.location = '<%= request.getContextPath() %>/Accueil'">
</form>
</body>
</html>
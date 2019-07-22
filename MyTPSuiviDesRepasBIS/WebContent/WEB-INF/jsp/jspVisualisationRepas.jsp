<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="gestiondesrepas.bo.Repas" %>
<%@ page import="gestiondesrepas.bo.Aliment" %>
<%@page import="java.util.List"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%!
	DateTimeFormatter myFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Visualisation des repas</title>
</head>
<body>
<h1>Historique</h1>
<table border="1">
	<thead>
	<tr>
		<th>Date</th>
		<th>Heure</th>
		<th>Détails</th>
	</tr>
	</thead>
	<tbody>
		<%
		for (Repas repas : (List<Repas>)(request.getAttribute("repas"))) {
			out.print("<tr>");
			out.print("<td>" + repas.getLocalDate().format(myFormatter) + "</td>");
			out.print("<td>" + repas.getLocalTime() + "</td>");
			out.print("<td><ul>");
			for (Aliment aliment : repas.getAliments()) {
				out.print("<li>" + aliment.getNom() + "</li>");
			}
			out.print("</ul></td>");
			out.print("</tr>");
		}
		%>
	</tbody>
</table>

<input type="button" value="Ajouter un nouveau repas" onclick="window.location = '<%= request.getContextPath() %>/ServletAjoutRepas'">
<input type="button" value="Retour à l'accueil" onclick="window.location = '<%= request.getContextPath() %>/Accueil'">
</body>
</html>
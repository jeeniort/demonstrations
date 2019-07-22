<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="gestiondesrepas.bo.Repas" %>
<%@ page import="gestiondesrepas.bo.Aliment" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>HISTORIQUE</h1>

<table border="1">
	<thead>
		<tr>
		<td>Date</td>
		<td>Heure</td>
		<td>Détails</td>
		</tr>
	</thead>
	<tbody>
		<% for(Repas repas : (List<Repas>)request.getAttribute("repas")) { %>
			<tr>
				<td><%= repas.getDate() %></td>
				<td><%= repas.getTime() %></td>
				<td>
					<% for(Aliment aliment : repas.getAliments()) { %>
						<p> <%= aliment.getNom() %></p>
					<% } %>
				</td>
			</tr>
		<% } %>
	</tbody>
</table>

<input  type="button"
			value="Ajouter un nouveau repas"
			onclick="window.location='<%= request.getContextPath()%>/ServletAjoutRepas'">
<input  type="button"
			value="Retour à l'accueil"
			onclick="window.location='<%= request.getContextPath()%>/accueil'">
</body>
</html>
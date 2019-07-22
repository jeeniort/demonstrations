<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Application de gestion des repas</title>
</head>
<body>
<form method="get" action="<%= request.getContextPath() %>/ServletAjoutRepas">
	<input type="submit" value="Ajouter un nouveau repas" />
</form>
<form method="get" action="<%= request.getContextPath() %>/ServletVisualisationRepas">
	<input type="submit" value="Visualiser les repas" />
</form>
</body>
</html>
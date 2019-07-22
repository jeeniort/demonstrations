<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1><%= request.getAttribute("result") %></h1>
<img src="ressources/img/<%= request.getAttribute("pathUtilisateur") %>" />
<img src="ressources/img/<%= request.getAttribute("pathIA") %>" />
<p>Vous aviez choisi : <%= request.getAttribute("choixUtilisateur") %></p>
<p>L'ordinateur a choisi : <%= request.getAttribute("choixIA") %></p>
<p><a href="/MyChifoumiAmeliore/jouer">Rejouer</a></p>
</body>
</html>
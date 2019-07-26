<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body onload="list();">
<h1>Prise de notes</h1>
	<div id="notes"></div>
	<div>
		<textarea id="taCreation" placeholder="Créez une nouvelle note..."></textarea>
		<input type="button" value="Ajouter" onclick="ajouter()">
	</div>
<script type="text/javascript" src="js/rest.js"></script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<img src="ressources/img/wannaplay.jpg" alt="Wanna play a game ?" height="200px"/>
<form action="/MyChifoumiAmeliore/ServletTraitantChifoumi" method="post">
	<input type="submit" value="pierre" name="choix" >
	<input type="submit" value="feuille" name="choix">
	<input type="submit" value="ciseaux" name="choix">
</form>
</body>
</html>
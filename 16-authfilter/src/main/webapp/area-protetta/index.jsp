<!DOCTYPE html>
<%@page import="it.formarete.model.User"%>
<html>
<head>
<title>Area protetta</title>
<meta charset="UTF-8">
</head>
<body>
	<h1>Sei dentro <%= ((User) request.getAttribute("user")).getUsername() %>!</h1>
	<jsp:useBean id="user" scope="request" class="it.formarete.model.User" />
	<p>Hello <jsp:getProperty name="user" property="username" />, benvenuto nell'area protetta.</p>
	<p>Te lo ripeto? Hello ${user.username}!</p>
	<p><a href="gallery.jsp">vai alla photogallery</a></p>
</body>
</html>

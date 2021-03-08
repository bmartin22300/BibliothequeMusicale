<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<%@include file="../head.jsp" %>	 
</head>
<body>
	<%@include file="../header.jsp" %>
	
	<% String email = (String)request.getAttribute("email"); %>
	
	<div class="cardProfile">
	  <img src="ExternalRessource/User/profile.jpg" alt="John" style="width:100%">
	  <h1><%= email %></h1>
	  <a href="#"><i class="fa fa-dribbble"></i></a>
	  <a href="#"><i class="fa fa-twitter"></i></a>
	  <a href="#"><i class="fa fa-linkedin"></i></a>
	  <a href="#"><i class="fa fa-facebook"></i></a>
	  <p><a href="ModficationProfilAdministrateur" class="button">Modifier</a></p>
	</div>
	
</body>
</html>
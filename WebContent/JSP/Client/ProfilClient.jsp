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
	<% String civilité = (String)request.getAttribute("civilité"); %>
	<% String nom = (String)request.getAttribute("nom"); %>
	<% String prénom = (String)request.getAttribute("prénom"); %>
	<% String adresse = (String)request.getAttribute("adresse"); %>
	<% String dateDeNaissance = (String)request.getAttribute("dateDeNaissance"); %>
	<% int nbEcoutes = (int)request.getAttribute("nbEcoutes"); %>
	
	<div class="cardProfile">
	  <img src="ExternalRessource/User/profile.jpg" alt="John" style="width:100%">
	  <h1><%= pseudonyme %></h1>
	  <p class="title"><%= email %></p>
	  <p><%= prénom %> <%= nom %></p>
	  <p><%= adresse %></p>
	  <p><%= dateDeNaissance %></p>
	  <p>Nombre d'écoutes : <%= nbEcoutes %></p>
	  <a href="#"><i class="fa fa-dribbble"></i></a>
	  <a href="#"><i class="fa fa-twitter"></i></a>
	  <a href="#"><i class="fa fa-linkedin"></i></a>
	  <a href="#"><i class="fa fa-facebook"></i></a>
	  <p><a href="ModficationProfilClient" class="button">Modifier</a></p>
	</div>
	
</body>
</html>
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
	<% String civilit� = (String)request.getAttribute("civilit�"); %>
	<% String nom = (String)request.getAttribute("nom"); %>
	<% String pr�nom = (String)request.getAttribute("pr�nom"); %>
	<% String adresse = (String)request.getAttribute("adresse"); %>
	<!-- String dateDeNaissance = request.getAttribute("dateDeNaissance").toString(); -->
	<%java.text.DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd"); %>
	<% String dateDeNaissance = df.format(new java.util.Date()); %>
	<% int nbEcoutes = (int)request.getAttribute("nbEcoutes"); %>
	
	<div class="cardProfile">
	  <img src="ExternalRessource/User/profile.jpg" alt="John" style="width:100%">
	  <h1><%= email %></h1>
	  <% if(pr�nom!=null){ %>
	  	<p class="title"><%= pr�nom %> <%= nom %></p>
	  <% } %>
	   <% if(adresse!=null){ %>
	  	<p><%= adresse %></p>
	   <% } %>
	   <% if(dateDeNaissance!=null){ %>
	   	<p><%= dateDeNaissance %></p>
	   <% } %>
	  <p>Nombre d'�coutes : <%= nbEcoutes %></p>
	  <a href="#"><i class="fa fa-dribbble"></i></a>
	  <a href="#"><i class="fa fa-twitter"></i></a>
	  <a href="#"><i class="fa fa-linkedin"></i></a>
	  <a href="#"><i class="fa fa-facebook"></i></a>
	  <p><a href="ModificationProfilClient" class="button">Modifier</a></p>
	</div>
	
</body>
</html>
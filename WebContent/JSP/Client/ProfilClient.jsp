<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<%@include file="../head.jsp" %>	 
</head>
<body>
	<%@include file="../header.jsp" %>
	<%@ page import="java.util.Date" %>
	
	<% String email = (String)request.getAttribute("email"); %>
	<% String civilite = (String)request.getAttribute("civilite"); %>
	<% String nom = (String)request.getAttribute("nom"); %>
	<% String prenom = (String)request.getAttribute("prenom"); %>
	<% String adresse = (String)request.getAttribute("adresse"); %>
	<% Date dateDeNaissance = (Date)request.getAttribute("dateDeNaissance"); %>
	<% if(dateDeNaissance!=null){ %>
		<% dateDeNaissance.toString(); %>
	<%} %>
	<% int nbEcoutes = (int)request.getAttribute("nbEcoutes"); %>
	
	<div class="cardProfile">
	  <img src="ExternalRessource/User/profile.jpg" alt="John" style="width:100%">
	  <h1><%= email %></h1>
	  <% if(prenom!=null){ %>
	  	<p class="title"><%= prenom %> <%= nom %></p>
	  <% } %>
	   <% if(adresse!=null){ %>
	  	<p><%= adresse %></p>
	   <% } %>
	   <% if(dateDeNaissance!=null){ %>
	   	<p><%= dateDeNaissance %></p>
	   <% } %>
	  <p>Nombre d'ecoutes : <%= nbEcoutes %></p>
	  <p><a href="ModificationProfilClient" class="button">Modifier</a></p>
	</div>
	
</body>
</html>
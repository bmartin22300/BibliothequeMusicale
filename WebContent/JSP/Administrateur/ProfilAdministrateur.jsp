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
	<% Boolean isAdministrateurClient = (Boolean)request.getAttribute("isAdministrateurClient"); %>
	<% Boolean isAdministrateurMusical = (Boolean)request.getAttribute("isAdministrateurMusical"); %>
	
	<div class="cardProfile">
	  <img src="ExternalRessource/User/profile.jpg" alt="John" style="width:100%">
	  <h1><%= email %></h1>
	   <% if(isAdministrateurClient != null && isAdministrateurClient==true){ %>
	   	<p>Ce profil est un administrateur client</p>
	   <% } %>
	   <% if(isAdministrateurMusical!=null  && isAdministrateurMusical==true){ %>
	   	<p>Ce profil est un administrateur musical</p>
	   <% } %>
	  <p><a href="ModificationProfilAdministrateur" class="button">Modifier</a></p>
	</div>
	
</body>
</html>
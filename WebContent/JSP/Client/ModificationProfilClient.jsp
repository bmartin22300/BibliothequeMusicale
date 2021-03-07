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
	<% String password = (String)request.getAttribute("password"); %>
	<% String civilité = (String)request.getAttribute("civilité"); %>
	<% String nom = (String)request.getAttribute("nom"); %>
	<% String prénom = (String)request.getAttribute("prénom"); %>
	<% String adresse = (String)request.getAttribute("adresse"); %>
	<% String dateDeNaissance = (String)request.getAttribute("dateDeNaissance"); %>
	<% int nbEcoutes = (int)request.getAttribute("nbEcoutes"); %>
	
	<form method="POST">
		<div class="cardProfile">
			<input name="action" type="hidden" value="ModificationProfilClient">
			
			<h1>Modifier mes informations</h1>
			
			<img src="ExternalRessource/User/profile.jpg" alt="John" style="width:100%">
        
	        <p>
		        <label><b>Mail</b></label>
		        <input type="text" value=<%= email %> name="mail" required>
	        </p>
	        
			<p>
		        <label><b>Mot de passe</b></label>
		        <input type="password" value=<%= password %> name="password" required>
			</p>
			
			<p class="title">
		        <label><b>Prénom</b></label>
		        <input type="prénom" placeholder=
		         	<% if(prénom==null){ %>
				  		"Entrez le prénom"
				 	<% } else {%>
				 		%= prénom %>
				 	<% } %>
		        name="prénom">
			</p>
			
			<p class="title">
		        <label><b>Nom</b></label>
		        <input type="nom" placeholder=
		         	<% if(nom==null){ %>
				  		"Entrez le nom"
				 	<% } else {%>
				 		%= nom %>
				 	<% } %>
		        name="nom">
			</p>
			
			<p>
		        <label><b>Adresse</b></label>
		        <input type="adresse" placeholder=
		         	<% if(prénom==null){ %>
				  		"Entrez l'adresse"
				 	<% } else {%>
				 		%= adresse %>
				 	<% } %>
		        name="adresse">
			</p>
			
			<p>
		        <label><b>Date de naissance</b></label>
		        <input type="password" placeholder=
		         	<% if(dateDeNaissance==null){ %>
				  		"yyyy-mm-dd"	
				 	<% } else {%>
				 		%= dateDeNaissance %>
				 	<% } %>
		        name="dateDeNaissance">
			</p>
			
			<p>
	        	<input type="submit" id='submit' value='Enregistrer' >
			</p>
		  
		  <a href="#"><i class="fa fa-dribbble"></i></a>
		  <a href="#"><i class="fa fa-twitter"></i></a>
		  <a href="#"><i class="fa fa-linkedin"></i></a>
		  <a href="#"><i class="fa fa-facebook"></i></a>
		  <p><a href="ProfilClient" class="button">Retour</a></p>
		</div>
	</form>
	
	
</body>
</html>
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
	<!-- String dateDeNaissance = request.getAttribute("dateDeNaissance").toString(); -->
	<%java.text.DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd"); %>
	<% String dateDeNaissance = df.format(new java.util.Date()); %>
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
		        <label><b>Civilité</b></label>
			       <div class="profileModificationCheckbox">
					  <input type="checkbox" id="scales" name="M" checked>
					  <label for="scales">M</label>
					</div>
					
					<div class="profileModificationCheckbox">
					  <input type="checkbox" id="horns" name="Mme">
					  <label for="horns">Mme</label>
					</div>
					
					<div class="profileModificationCheckbox">
					  <input type="checkbox" id="horns" name="Autre">
					  <label for="horns">Autre</label>
					</div>
			</p>
			
			<p>
				  <label for="cars">Genre favori</label>
				  <select id="cars" name="styleMusiquePrefere">
				    <option value="RAP">RAP</option>
				    <option value="ROCK">ROCK</option>
				    <option value="JAZZ">JAZZ</option>
				    <option value="POP">POP</option>
				    <option value="VARIETE">VARIETE</option>
				    <option value="CLASSIQUE">CLASSIQUE</option>
				    <option value="TECHNO">TECHNO</option>
				  </select>
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
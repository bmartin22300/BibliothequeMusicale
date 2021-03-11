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
	<% String civilite = (String)request.getAttribute("civilite"); %>
	<% String nom = (String)request.getAttribute("nom"); %>
	<% String prenom = (String)request.getAttribute("prenom"); %>
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
		        <input type="text" value=<%= email %> name="mail">
	        </p>
	        
			<p>
		        <label><b>Mot de passe</b></label>
		        <input type="password" value=<%= password %> name="password">
			</p>
			
			<p class="title">
		        <label><b>Prenom</b></label>
		        <input type="prenom" 
		        	<% boolean noPrenom= (prenom==null || prenom.equals("")); %>
		         	<% if(noPrenom){ %>
		         		placeholder="Entrez le prenom"
				 	<% } else {%>
				 		value= <%= prenom	%>
				 	<% } %>
		        name="prenom">
			</p>

			<p class="title">
		        <label><b>Nom</b></label>
		        <input type="nom" 
		        	<% boolean noNom = (nom==null || nom.equals("")); %>
		         	<% if(noNom){ %>
		         		placeholder="Entrez le nom"
				 	<% } else {%>
				 		value= <%= nom	%>
				 	<% } %>
		        name="nom">
			</p>
			
			<p>
		        <label><b>Adresse</b></label>
		        <input type="adresse" 
		        	<% boolean noAdresse = (adresse==null || adresse.equals("")); %>
		         	<% if(noAdresse){ %>
		         		placeholder="Entrez l'adresse"
				 	<% } else {%>
				 		value= <%= adresse	%>
				 	<% } %>
		        name="adresse">
			</p>
			
			<p>
		        <label><b>Date de naissance</b></label>
		        <input type="dateDeNaissance" 
		        	<% boolean noDate = (dateDeNaissance==null || dateDeNaissance.equals("")); %>
		         	<% if(noDate){ %>
		         		placeholder="YYYY-MM-DD"
				 	<% } else {%>
				 		value= <%= dateDeNaissance %>
				 	<% } %>
		        name="dateDeNaissance">
			</p>

			<p>
		        <label><b>Civilite</b></label>
					<div>
					  <input type="radio" id="M" name="civilite" value="M"
					  <% if(civilite=="M"){ %>
		         		checked
				 	  <% } %>>
					  <label for="huey">M</label>
					</div>
					
					<div>
					  <input type="radio" id="Mme" name="civilite" value="Mme"
					  <% if(civilite=="Mme"){ %>
		         		checked
				 	  <% } %>>
					  <label for="dewey">Mme</label>
					</div>
					
					<div>
					  <input type="radio" id="Autre" name="civilite" value="Autre"
					  <% if(civilite=="Autre"){ %>
		         		checked
				 	  <% } %>>
					  <label for="louie">Autre</label>
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
			
		  <p><a href="ProfilClient" class="button">Retour</a></p>
		</div>
	</form>
	
	
</body>
</html>
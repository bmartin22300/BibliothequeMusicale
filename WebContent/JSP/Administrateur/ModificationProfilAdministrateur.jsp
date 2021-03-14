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
	
	<form method="POST">
		<div class="cardProfile">
			<input name="action" type="hidden" value="ModificationProfilAdministrateur">
			
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
			
			<p>
	        	<input type="submit" id='submit' value='Enregistrer' >
			</p>
		  
		  <a href="#"><i class="fa fa-dribbble"></i></a>
		  <a href="#"><i class="fa fa-twitter"></i></a>
		  <a href="#"><i class="fa fa-linkedin"></i></a>
		  <a href="#"><i class="fa fa-facebook"></i></a>
		  <p><a href="ProfilAdministrateur" class="button">Retour</a></p>
		</div>
	</form>
	
	
</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<%@include file="../head.jsp" %>	 
</head>
<body>
	<%@include file="../header.jsp" %>
	<br>
	
	<div class="mainContent">
		<!-- formulaire de connexion -->
		<div class="connexion">
			<form method="POST">
				<input name="action" type="hidden" value="AuthentificationClient">
			
		        <h1>Connexion client</h1>
		        
		        <label><b>Mail</b></label>
		        <input type="text" placeholder="Entrer le mail" name="mail" required><br>
		
		        <label><b>Mot de passe</b></label>
		        <input type="password" placeholder="Entrer le mot de passe" name="password" required><br>
		
		        <input type="submit" class="button" id='submit' value='Connexion' >
		    </form>
		    
		    <!-- erreur de login -->
		    <% boolean isErrorLogin = (boolean)request.getAttribute("isErrorLogin"); %>
		    <% if(isErrorLogin==true){ %>
		    	<p>Erreur : pseudonyme ou mot de passe incorrect</p>
		    	<br>
		    <% } %>
		    
		    <!-- tentative de manipulation d'url pour contourné la connexion -->
		    <% boolean notLogged = (boolean)request.getAttribute("notLogged"); %>
		    <% if(notLogged==true){ %>
		    	<p>Attention : merci de ne pas jouer avec l'url sinon j'appelle Adopi !</p>
		    	<br>
		    <% } %>
		    
		    <!-- inscription client -->
		    
		    <br><br>
		    <a href="InscriptionClient" class="button">S'inscrire</a>			    
		    
		    <!-- connexion administrateur -->
		    <a href="AuthentificationAdministrateur" class="button">Connexion administrateur</a>
	    </div>
    </div>
    
    <%@include file="../footer.jsp" %>
</body>
</html>
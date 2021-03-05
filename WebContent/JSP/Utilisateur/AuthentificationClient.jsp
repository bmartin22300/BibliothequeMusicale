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
	
	<!-- formulaire de connexion -->
	<form action="AuthentificationClient" method="POST">
        <h1>Connexion</h1>
        
        <label><b>Pseudonyme</b></label>
        <input type="text" placeholder="Entrer le pseudonyme" name="username" required>

        <label><b>Mot de passe</b></label>
        <input type="password" placeholder="Entrer le mot de passe" name="password" required>

        <input type="submit" id='submit' value='LOGIN' >
    </form>
    
    <!-- erreur de login -->
    <% boolean isErrorLogin = (boolean)request.getAttribute("isErrorLogin"); %>
    <% if(isErrorLogin==true){ %>
    	<a>Erreur : pseudonyme ou mot de passe incorrect</a>
    	<br>
    <% } %>
    
    <!-- tentative de manipulation d'url pour contourné la connexion -->
    <% boolean notLogged = (boolean)request.getAttribute("notLogged"); %>
    <% if(notLogged==true){ %>
    	<a>Attention : merci de ne pas jouer avec l'url sinon j'appelle Adopi !</a>
    	<br>
    <% } %>
    
    <!-- connexion administrateur -->
    <a href="AuthentificationAdministrateur">Connexion administrateur</a>	
</body>
</html>
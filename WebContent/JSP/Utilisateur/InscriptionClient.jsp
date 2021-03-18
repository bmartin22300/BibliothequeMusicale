<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<%@include file="../head.jsp" %>	 
</head>
<body>
	<div class="mainContent">
		<%@include file="../header.jsp" %>
		<br>
		
		<!-- formulaire de connexion -->
		<div class="connexion">
			<form method="POST">
				<input name="action" type="hidden" value="InscriptionClient">
				
		        <h1>Inscription</h1>
		        
		        <label><b>Mail</b></label>
		        <input type="text" placeholder="Entrer le mail" name="mail" required><br>
		
		        <label><b>Mot de passe</b></label>
		        <input type="password" placeholder="Entrer le mot de passe" name="password" required><br>
		
		        <input type="submit" id='submit' value='LOGIN' >
		    </form>
	    
		    <!-- erreur de login -->
		    <% boolean isErrorLogin = (boolean)request.getAttribute("isErrorLogin"); %>
		    <% if(isErrorLogin==true){ %>
		    	<p>Erreur : mail deja utilise</p>
		    	<br>
		    <% } %>
		    
		    
		    <!-- connexion client -->
		    <a href="AuthentificationClient">Se connecter</a>
	   </div>
	</div>
   
   <%@include file="../footer.jsp" %>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<%@include file="../head.jsp" %>	 
</head>
<body onload="">
	<%@include file="../header.jsp" %>
	<%@ page import="java.util.List" %>
	<%@ page import="Object.TitreMusical" %>
	
	<div class="mainContent">
	    <!-- formulaires -->
	    <div class="cardProfile">
			<form method="POST">
				<input name="action" type="hidden" value="CreerPlaylist">
		        <h1>Nouvelle playlist :</h1>    
		    	<!-- insertion de donnee -->
		    	
		    	<!-- Playlist -->
				<div id="formPlaylist">
					<h1>
						<label><b>Titre</b></label>
						<input id="titre" type="text" placeholder="Nom de la playlist" name="nomPlaylist">
					</h1>

					<!--  Ajout titre -->
					<h1>
						<label><b>Titre</b></label>

					</h1>
					
					
			        <p style="padding-bottom:20px;"><input type="submit" id='submit' value='Creer'></p>
				</div>
		    
		    
		    	
		    </form>	
	    </div>
    </div>
		
	<%@include file="../footer.jsp" %>
</body>
</html>
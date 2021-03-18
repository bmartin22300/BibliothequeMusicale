<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<%@include file="../head.jsp" %>	 
</head>
<body>
	<%@include file="../header.jsp" %>
	<%@ page import="java.util.List" %>
	<%@ page import="Object.Album" %>
	
	<% Album a = (Album)request.getSession().getAttribute("a"); %>
	
	<div class="mainContent">
	    <!-- formulaires -->
	    <div class="cardProfile">
			<form method="POST">
			
				<input name="action" type="hidden" value="ModificationAlbumFin">
				<input name="id" type="hidden" value=<%= a.getIdCatalogue() %>>
				
		        <h1>Modification de l'album :</h1>
		        
		    	<!-- insertion de donnee -->
		    
				<div>
					<h1>
						<label><b>Nom</b></label>
						<input id="titre" type="text" name="Titre"
						<% if(a.getNom()==null){out.print("placeholder=\"Entrer le nom\"");}else{out.print("value=\""+a.getNom()+"\"");}%>>
					</h1>
					
			        <p>
			        	<label><b>Annee de sortie</b></label>
			        	<input id="titre" type="text" name="Annee de creation"
			        	<% if(a.getDateSortie()==0){out.print("placeholder=\"Entrer l'annee de sortie\"");}else{out.print("value=\""+a.getDateSortie()+"\"");}%>>
			        </p> 
			
			        <p style="padding-bottom:20px;"><input class="button" type="submit" id='submit' value='Suivant'></p>
				</div>    
			</form>
		</div>
	</div>
			
	<%@include file="../footer.jsp" %>
</body>
</html>
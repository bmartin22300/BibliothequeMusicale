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
	<%@ page import="Object.TitreMusical" %>
	<%@ page import="Object.Album" %>
	
	<% Album a = (Album)request.getSession().getAttribute("a"); %>
	<% request.getSession().setAttribute("album",a); %>
	<% List<TitreMusical> titresMusicaux = (List<TitreMusical>)request.getSession().getAttribute("titresMusicaux"); %>
	<% List<TitreMusical> titresMusicauxAssocies = (List<TitreMusical>)request.getSession().getAttribute("titresMusicauxAssocies"); %>
	
	<div class="mainContent">
	    <!-- formulaires -->
	    <div class="cardProfile">
			<form method="POST">
			
				<input name="action" type="hidden" value="ModificationAlbumTitresAjout">
				<input name="id" type="hidden" value=<%= a.getIdCatalogue() %>>
				
		        <h1>Modification des titres associes a l'album :</h1>
			        
				 <!-- choix du type d'�l�ment -->
		        <div style="margin:10px;">
					<!-- select box -->
					<p>
					  <label for="cars">Titres musicaux</label>
					  <% if(titresMusicaux!=null){ %>
						  	<select name="interpreteSelectBox"> 
						  		<% for(TitreMusical titreMusical : titresMusicaux) {%>
							  		<option value=<%= titreMusical.getIdCatalogue() %>><%= titreMusical.getTitre() %></option>
							    <% } %>
						  	</select>
					  <%} %>
					  <input type="submit" id='submit' value='Ajouter'>
					</p>				
				</div>   
		    </form>
		    
		    <!-- liste des interpretes associe au titre -->
		    <ul>
		    	<% if(titresMusicauxAssocies!=null) {%>
		    		<% for(TitreMusical titreMusicalAssocie : titresMusicauxAssocies) {%>
				  		<li><%= titreMusicalAssocie.getTitre() %></li>
				    <% } %>
		    	<%} %>
		    </ul>
		    
		    <!-- fin d'ajout -->
		    <form method="POST">
				<input name="action" type="hidden" value="ModificationAlbumTitresFin">
				<input type="submit" id='submit' value='Terminer'>
			</form>
			
		</div>
	</div>
			
	<%@include file="../footer.jsp" %>
</body>
</html>
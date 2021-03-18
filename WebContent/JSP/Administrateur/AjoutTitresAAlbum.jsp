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
	<%@ page import="Object.TitreMusical" %>
	
	<% List<TitreMusical> titresMusicaux = (List<TitreMusical>)request.getAttribute("titresMusicaux"); %>
	<% List<TitreMusical> titresMusicauxAssocies = (List<TitreMusical>)request.getAttribute("titresMusicauxAssocies"); %>
	<% Album album = (Album)request.getAttribute("album"); %>
	<% request.getSession().setAttribute("album",album); %>
	
	<div class="mainContent">
		<!-- formulaire -->
		<form method="POST">
		
		<input name="action" type="hidden" value="AjoutTitresAAlbum">
		
		<h1>Ajouter un titre musical</h1>
			 <!-- choix du type d'�l�ment -->
	        <div style="margin:10px;">
				<!-- select box -->
				<p>
				  <label for="cars">Titre musical</label>
				  <% if(titresMusicaux!=null){ %>
					  	<select name="titresMusicauxSelectBox"> 
					  		<% for(TitreMusical titreMusical : titresMusicaux) {%>
						  		<option value=<%= titreMusical.getIdCatalogue() %>><%= titreMusical.getTitre() %></option>
						    <% } %>
					  	</select>
				  <%} %>
				  <input type="submit" id='submit' value='Ajouter'>
				</p>				
			</div>   
	    </form>
	    
	    <!-- liste des titresMusicaux associe a l'album -->
	    <ul>
	    	<% if(titresMusicauxAssocies!=null) {%>
	    		<% for(TitreMusical titreMusicalAssocie : titresMusicauxAssocies) {%>
			  		<li><%= titreMusicalAssocie.getTitre() %></li>
			    <% } %>
	    	<%} %>
	    </ul>
	    
	    <!-- fin d'ajout -->
	    <form method="POST">
			<input name="action" type="hidden" value="FinAjoutTitresMusicauxAAlbum">
			<input type="submit" id='submit' value='Terminer'>
		</form>
    </div>
    
    <%@include file="../footer.jsp" %>
</body>
</html>
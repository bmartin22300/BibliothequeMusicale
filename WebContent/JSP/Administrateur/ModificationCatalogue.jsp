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
	
	<!-- formulaire de connexion -->
	<form method="POST">
		<input name="action" type="hidden" value="RechercheTitre">
	
        <h1>Element a rechercher</h1>
        <div style="margin:10px;">
			<!-- select box -->
			<select name="pets" name="TypeElement">
			    <option value="Titres musicaux">Titres musicaux</option>
			    <option value="Interpretes">Interpretes</option>
			    <option value="Albums">Albums</option>
			</select>
		</div>
        
        <label><b>Nom</b></label>
        <input id="titre" type="text" placeholder="Entrer le titre" name="titre">

        <input type="submit" id='submit' value='Rechercher'>
    </form>
    
    <% List<TitreMusical> titresMusicaux = (List<TitreMusical>)request.getAttribute("titresMusicaux"); %>
    <!-- resultat de la requete -->
    <div style="margin:10px;">
    	<table>
		    <thead>
		        <tr>
		            <th>Titre</th>
		            <th>Interpretes</th>
		            <th>Album</th>
		            <th>Genre</th>
		            <th>Annee de creation</th>
		            <th>Durée</th>
		        </tr>
		    </thead>
		    <tbody>
	        	<% if (titresMusicaux!=null) {%>
	        		<% for( TitreMusical elem : titresMusicaux ) {%>
						<tr id="tr">
			            	<td><% out.print(elem.getTitre()); %></td>
			            	<td><% out.print(elem.getInterpretes()); %></td>
			            	<td><% out.print(elem.getAlbum()); %></td>
			            	<td><% out.print(elem.getGenre()); %></td>
			            	<td><% out.print(elem.getAnneeCreation()); %></td>
			            	<td><% out.print(elem.getDuree()); %></td>
		            	</tr>
		            <% } %>
	        	<% } %>
		    </tbody>
		</table>
    </div>
    
</body>
</html>
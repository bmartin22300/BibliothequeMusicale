<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<%@include file="../head.jsp" %>	 
</head>
<body onload="changerTypeElementsAffichesModification()">
	<%@include file="../header.jsp" %>
	<%@ page import="java.util.List" %>
	<%@ page import="Object.TitreMusical" %>
	<%@ page import="Object.Interprete" %>
	<%@ page import="Object.Album" %>
	
	<!-- formulaire -->
	<form method="POST">
	
	<input name="action" type="hidden" value="RechercheTitre">
	
	<h1>Element a rechercher</h1>
	
		 <!-- choix du type d'élément -->
        <div style="margin:10px;">
			<!-- select box -->
			<p>
				<% String TypeElement = (String)request.getAttribute("TypeElement"); %>
				<% if (TypeElement!=null) {%>
					<% if(TypeElement=="Titres musicaux") {%>
					<%} %>
				<%} %>
				
			  <label for="cars">Type d'element</label>
			  <select id="TypeElement" onChange="changerTypeElementsAffichesModification()" name="TypeElement">
			    <option value="Titres musicaux">Titres musicaux</option>
			    <option value="Interpretes">Interpretes</option>
			    <option value="Albums">Albums</option>
			  </select>
			</p>				
		</div>   
		
        <label><b>Nom</b></label>
        <input id="titre" type="text" placeholder="Entrer le nom" name="titre">

        <input type="submit" id='submit' value='Rechercher'>
    </form>
    
    <% List<TitreMusical> titresMusicaux = (List<TitreMusical>)request.getAttribute("titresMusicaux"); %>
    <% List<Interprete> interpretes = (List<Interprete>)request.getAttribute("interpretes"); %>
    <% List<Album> albums = (List<Album>)request.getAttribute("albums"); %>
    
    <!-- resultat de la requete -->
    <div style="margin:10px;">
    
    <!-- Titres musicaux -->
	<div id="tableTitresMusicaux">
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
			
		<!-- Interpretes -->
		<div id="tableInterpretes">
			<table>
			    <thead>
			        <tr>
			            <th>Pseudo</th>
			            <th>Prenom</th>
			            <th>Nom</th>
			            <th>Date de naissance</th>
			        </tr>
			    </thead>
			    <tbody>
		        	<% if (interpretes!=null) {%>
		        		<% for( Interprete elem : interpretes ) {%>
							<tr id="tr">
				            	<td><% out.print(elem.getPseudonyme()); %></td>
				            	<td><% out.print(elem.getPrenom()); %></td>
				            	<td><% out.print(elem.getNom()); %></td>
				            	<td><% out.print(elem.getDateNaissance()); %></td>
			            	</tr>
			            <% } %>
		        	<% } %>
			    </tbody>
		    </table>
		</div>
			
		<!-- Albums -->
		<div id="tableAlbums">
			<table>
			    <thead>
			        <tr>
			            
			        </tr>
			    </thead>
			    <tbody>
		        	<% if (albums!=null) {%>
		        		<% for(Album elem : albums) {%>
							<tr id="tr">
				            	
			            	</tr>
			            <% } %>
		        	<% } %>
			    </tbody>
		    </table>
	    </div>
	    
    </div>
    
</body>
</html>
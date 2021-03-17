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
			  <label for="cars">Type d'element</label>
			  <select id="TypeElement" onChange="changerTypeElementsAffichesModification()" name="TypeElement">
			    <option value="Titres musicaux"
			    	<% if ((TypeElement!=null) && TypeElement.equals("Titres musicaux")) {%>
			    		selected="selected"
			    	<%} %>
			    >Titres musicaux</option>
			    <option value="Interpretes" 
			    	<% if ((TypeElement!=null) && TypeElement.equals("Interpretes")) {%>
			    		selected="selected"
			    	<%} %>
			    >Interpretes</option>
			    <option value="Albums"
			    	<% if ((TypeElement!=null) && TypeElement.equals("Albums")) {%>
			    		selected="selected"
			    	<%} %>
			    >Albums</option>
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
			            <th></th>
			            <th></th>
			        </tr>
			    </thead>
			    <tbody>
		        	<% if (titresMusicaux!=null) {%>
		        		<% for( TitreMusical elem : titresMusicaux ) {%>
							<tr id="tr">
				            	<td><% out.print(elem.getTitre()); %></td>
				            	<td><ul>
				            		<% for(Interprete i : elem.getInterpretes()){ %>
   										<li><% out.print(i.getPseudonyme()); %></li>
				            		<%} %>
				            	</ul></td>
				            	<td><% out.print(elem.getAlbum()); %></td>
				            	<td><% out.print(elem.getGenre()); %></td>
				            	<td><% out.print(elem.getAnneeCreation()); %></td>
				            	<td><% out.print(elem.getDuree()); %></td>
				            	<td>
				            		<form method="POST">
				            			<input name="action" type="hidden" value="ModificationTitre">
										<input name="idCatalogue" type="hidden" value=<%elem.getIdCatalogue();%>>
										<input type="submit" id='submit' value='Modifier'>
									</form>
				            	</td>
				            	<td>
				            		<form method="POST">
				            			<input name="action" type="hidden" value="SuppressionTitre">
				            			<input name="idString" type="hidden" value=<% out.print(elem.getIdCatalogue());%>>
										<input name="titreString" type="hidden" value=<% out.print(elem.getTitre());%>>
										<input type="submit" id='submit' value='Supprimer'>
									</form>
				            	</td>
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
			            <th>Modifier</th>
			            <th>Supprimer</th>
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
				            	<td>
				            		<form method="POST">
				            			<input name="action" type="hidden" value="SuppressionTitre">
				            			<input name="idString" type="hidden" value="">
										<input name="titreString" type="hidden" value="">
										<input type="submit" id='submit' value='Modifier'>
									</form>
				            	</td>
				            	<td>
				            		<form method="POST">
				            			<input name="action" type="hidden" value="SuppressionInterprete">
				            			<input name="idString" type="hidden" value=<% out.print(elem.getId());%>>
										<input name="titreString" type="hidden" value=<% out.print(elem.getPseudonyme());%>>
										<input type="submit" id='submit' value='Supprimer'>
									</form>
				            	</td>
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
			            <th>Nom</th>
			            <th>Titres musicaux</th>
			            <th>Annee de sortie</th>
			            <th>Duree</th>
			            <th>Modifier</th>
			            <th>Supprimer</th>
			        </tr>
			    </thead>
			    <tbody>
		        	<% if (albums!=null) {%>
		        		<% for(Album elem : albums) {%>
							<tr id="tr">
				            	<td><% out.print(elem.getNom()); %></td>
				            	<td><ul>
				            		<% for(TitreMusical t : elem.getTitres()){ %>
   										<li><% out.print(t.getTitre()); %></li>
				            		<%} %>
				            	</ul></td>
				            	<td><% out.print(elem.getDateSortie()); %></td>
				            	<td><% out.print(elem.getDuree()); %></td>
				            	<td>
				            		<form method="POST">
				            			<input name="action" type="hidden" value="ModificationAlbum">
										<input name="idCatalogue" type="hidden" value=<%elem.getIdCatalogue();%>>
										<input type="submit" id='submit' value='Modifier'>
									</form>
				            	</td>
				            	<td>
				            		<form method="POST">
				            			<input name="action" type="hidden" value="SuppressionAlbum">
				            			<input name="idString" type="hidden" value=<% out.print(elem.getIdCatalogue());%>>
										<input name="nomString" type="hidden" value=<% out.print(elem.getNom());%>>
										<input type="submit" id='submit' value='Supprimer'>
									</form>
				            	</td>
			            	</tr>
			            <% } %>
		        	<% } %>
			    </tbody>
		    </table>
	    </div>
	    
    </div>
    
</body>
</html>
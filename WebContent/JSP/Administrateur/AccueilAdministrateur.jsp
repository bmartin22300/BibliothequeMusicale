<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<%@include file="../head.jsp" %>	 
</head>
<body onload="changerTypeElementsAffichesRecherche()">
	<%@include file="../header.jsp" %>
	<%@ page import="java.util.List" %>
	<%@ page import="Object.TitreMusical" %>
	<%@ page import="Object.Interprete" %>
	<%@ page import="Object.Album" %>
	
	<!--  ExternalRessource/Catalogue -->
	<div style="margin:10px;">
		<!-- select box -->
		<label for="pet-select">Element a rechercher</label>
		<select name="pets" id="TypeElement" onChange="changerTypeElementsAffichesRecherche()">
	    	<option value="Titres musicaux">Titres musicaux</option>
		    <option value="Interpretes">Interpretes</option>
		    <option value="Albums">Albums</option>
		</select>
	</div>
	
	<% List<TitreMusical> titresMusicaux = (List<TitreMusical>)request.getAttribute("titresMusicaux"); %>
	<% List<Interprete> interpretes = (List<Interprete>)request.getAttribute("interpretes"); %>
	<% List<Album> albums = (List<Album>)request.getAttribute("albums"); %>
	
	<!--  Catalogue -->
	
	<!--  titresMusicaux -->
	<div class="wrapper" id="catalogueTitresMusicaux" style="display:grid;">	
		<% if (titresMusicaux!=null) {%>
       		<% for( TitreMusical elem : titresMusicaux ) {%>
				<div class="card">	
					<img src="ExternalRessource/Catalogue/Image/titreMusical.png" alt="Denim Jeans" style="width:100%">
					<audio id="player" controls preload tabindex="0">
						<source type="audio/mp3"/>
						Your browser does not support HTML5 audio.
					</audio>
				  <%if(elem.getTitre()!=null){ %><h1><%= elem.getTitre() %></h1><%} %>
				  <%if(elem.getInterpretes()!=null){ %><p><%= elem.getInterpretes() %></p><%} %>
				  <%if(elem.getGenre()!=null){ %><p class="price"><%= elem.getGenre() %></p><%} %>
				  <%if(elem.getAlbum()!=null){ %><p><button><%= elem.getAlbum() %></button></p><%} %>
				</div>
			<% } %>
		<% } %>
	</div>
	
	<!--  interpretes -->
	<div class="wrapper" id="catalogueInterpretes">	
		<% if (interpretes!=null) {%>
       		<% for( Interprete elem : interpretes ) {%>
				<div class="card">	
					<!-- ExternalRessource/Catalogue element -->
					<img src="ExternalRessource/Catalogue/Image/interprete3.png" alt="Denim Jeans" style="width:100%">
				  <%if(elem.getPseudonyme()!=null){ %><h1><%= elem.getPseudonyme() %></h1><%} %>
				  <%if(elem.getPrenom()!=null){ %><p class="price"><%= elem.getPrenom() %></p><%} %>
				  <%if(elem.getNom()!=null){ %><p class="price"><%= elem.getNom() %></p><%} %>
				  <%if(elem.getDateNaissance()!=null){ %><p><button><%= elem.getDateNaissance() %></button></p><%} %>
				</div>
			<% } %>
		<% } %>
	</div>
	
	<!--  albums -->
	<div id="catalogueAlbums">	
		<%= albums %>
	</div>
	
</body>
</html>
	
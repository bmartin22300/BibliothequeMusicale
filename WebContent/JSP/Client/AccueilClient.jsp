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
	
	
	<div class="mainContent">
		<!--  ExternalRessource/Catalogue -->
		
		<!-- formulaire -->
		<div  class = "rechercheTextBox">
			<form method="POST">
			
			<input name="action" type="hidden" value="RechercheAccueil">
				
				 <!-- choix du type d'élément -->
				<div style="margin:10px;">
					<!-- select box -->
					<p>
					<% String TypeElement = (String)request.getAttribute("TypeElement"); %>
						<label for="cars">Type d'element</label>
					  <select id="TypeElement" onChange="changerTypeElementsAffichesRecherche()" name="TypeElement">
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
		        <input id="recherche" type="text" placeholder="Entrer le nom" name="recherche">
		        <input type="submit" id='submit' value='Rechercher'>
		    </form>
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
						<form method="POST" id="formIncremeteAudio<% out.print(elem.getIdCatalogue()); %>" name="formIncremeteAudio">
							<input name="action" type="hidden" value="incrementeAudio">
							<input type="file" accept="audio/*" capture  src="ExternalRessource/Catalogue/Musique/magma_isha.mp3">
							<input value=<% out.print(elem.getIdCatalogue()); %> name="idTitreMusical" type="hidden">
							<audio id="player" controls preload tabindex="0" src="ExternalRessource/Catalogue/Musique/magma_isha.mp3" onPlay="audioIncremente(<% out.print(elem.getIdCatalogue()); %>)">
								<source type="audio/mp3"/>
								Your browser does not support HTML5 audio.
							</audio>
						</form>
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
						<img src="ExternalRessource/Catalogue/Image/interprete.png" alt="Denim Jeans" style="width:100%">
					  <%if(elem.getPseudonyme()!=null){ %><h1><%= elem.getPseudonyme() %></h1><%} %>
					  <%if(elem.getPrenom()!=null){ %><p class="price"><%= elem.getPrenom() %></p><%} %>
					  <%if(elem.getNom()!=null){ %><p class="price"><%= elem.getNom() %></p><%} %>
					  <%if(elem.getDateNaissance()!=null){ %><p><button><%= elem.getDateNaissance() %></button></p><%} %>
					</div>
				<% } %>
			<% } %>
		</div>
		
		<!--  albums -->
		<div class="wrapper" id="catalogueAlbums">	
			<% if (albums!=null) {%>
	       		<% for( Album elem : albums ) {%>
					<div class="card">	
						<!-- ExternalRessource/Catalogue element -->
						<img src="ExternalRessource/Catalogue/Image/album.png" alt="Denim Jeans" style="width:100%">
					  <%if(elem.getNom()!=null){ %><h1><%= elem.getNom() %></h1><%} %>
					  <%if(elem.getDateSortie()!=0){ %><p class="price"><%= elem.getDateSortie() %></p><%} %>
					  <%if(elem.getDuree()!=0){ %><p class="price"><%= elem.getDuree() %></p><%} %>
					  <%if(elem.getDateSortie()!=0){ %><p><button><%= elem.getDateSortie() %></button></p><%} %>
					</div>
				<% } %>
			<% } %>
		</div>
	</div>
	
	<%@include file="../footer.jsp" %>
</body>
</html>

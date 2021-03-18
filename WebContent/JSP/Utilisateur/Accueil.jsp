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
	<%@ page import="Object.Interprete" %>
	<%@ page import="Object.Album" %>
	
	<% List<TitreMusical> titresMusicaux = (List<TitreMusical>)request.getAttribute("titresMusicaux"); %>
	
	<!--  Catalogue -->
	<div class="mainContent">
		<!--  titresMusicaux -->
		<div class="wrapper" id="catalogueTitresMusicaux" style="display:grid;">	
			<% if (titresMusicaux!=null) {%>
	       		<% for( TitreMusical elem : titresMusicaux ) {%>
					<div class="card">	
						<img src="ExternalRessource/Catalogue/Image/titreMusical.png" alt="Denim Jeans" style="width:100%">
						<form method="POST" id="formIncremeteAudio<% out.print(elem.getIdCatalogue()); %>" name="formIncremeteAudio">
							<input name="action" type="hidden" value="incrementeAudio">
							<input value=<% out.print(elem.getIdCatalogue()); %> name="idTitreMusical" type="hidden">
							<audio id="player" controls preload tabindex="0" src="ExternalRessource/Catalogue/Musique/Jasmin.mp3" onPlay="audioIncremente(<% out.print(elem.getIdCatalogue()); %>)">
								<source type="audio/mp3"/>
								Your browser does not support HTML5 audio.
							</audio>
						</form>
					  <%if(elem.getTitre()!=null){ %><h1><%= elem.getTitre() %></h1><%} %>
					  <%if(elem.getInterpretes()!=null){ %>
						  <ul>
							  <p><% for(Interprete i :elem.getInterpretes()){ %>
								  	<li><%= i.getPseudonyme() %></li>
								  <% }%>
							  </p>
						  </ul>
					  <%} %>
					  <%if(elem.getGenre()!=null){ %><p class="price"><%= elem.getGenre() %></p><%} %>
					  <%if(elem.getAlbum()!=null){ %>
					  		<% if(elem.getAlbum().getNom()!=null){%>
						  		<p><button>
						  		<%out.print(elem.getAlbum().getNom()); %>
						  		</button></p>
					  		<%}%>
					  <%} %>
					</div>
				<% } %>
			<% } %>
		</div>
	</div>
	
	<%@include file="../footer.jsp" %>
</body>
</html>
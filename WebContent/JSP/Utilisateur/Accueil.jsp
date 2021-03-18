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
	
	<%@include file="../footer.jsp" %>
</body>
</html>
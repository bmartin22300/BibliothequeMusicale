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
	<%@ page import="Object.Playlist" %>
	
	<div class="mainContent">
	    
	    <% List<Playlist> playlists = (List<Playlist>)request.getAttribute("playlists"); %>
	    
	    <!-- resultat de la requete -->
	    <div style="margin:10px;">
	    
			<!-- Playlists -->
			<div id="tablePlaylists">
				<table>
				    <thead>
				        <tr>
				            <th>Nom</th>
				            <th>Titres musicaux</th>
				        </tr>
				    </thead>
				    <tbody>
			        	<% if (playlists!=null) {%>
			        		<% for(Playlist elem : playlists) {%>
								<tr id="tr">
					            	<td><% out.print(elem.getNomPlaylist()); %></td>
					            	<td><ul>
					            		<% for(TitreMusical t : elem.getTitresMusicaux()){ %>
	   										<li><% out.print(t.getTitre()); %></li>
					            		<%} %>
					            	</ul></td>

					            	<td>
					            		<form method="POST">
					            			<input name="action" type="hidden" value="SuppressionPlaylist">
					            			<input name="idString" type="hidden" value=<% out.print(elem.getIdPlaylist());%>>
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
    </div>
	<%@include file="../footer.jsp" %>
</body>
</html>
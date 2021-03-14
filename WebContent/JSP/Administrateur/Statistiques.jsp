<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<%@include file="../head.jsp" %>	 
</head>
<body onload="changerTypeElementsStatistiques()">
	<%@include file="../header.jsp" %>
	<%@ page import="java.util.List" %>
	<%@ page import="Object.Client" %>
	<%@ page import="Object.TitreMusical" %>
	<% List<Client> clients = (List<Client>)request.getAttribute("clients"); %>
	<% List<TitreMusical> titres = (List<TitreMusical>)request.getAttribute("titres"); %>
	<!-- formulaire -->
	<form method="POST">
	
	<input name="action" type="hidden" value="RechercheTitre">
	
	<h1>Statistiques</h1>
		 <!-- choix du type d'élément -->
        <div style="margin:10px;">
			<!-- select box -->
			<p>
			  <label for="cars">Type d'element</label>
			  <select id="TypeElement" onChange="changerTypeElementsStatistiques()" name="TypeElement">
			    <option value="Clients">Clients </option>
			    <option value="VuesTitres">Vues titres </option>
			  </select>
			</p>				
		</div>   
    </form>
    <!-- Clients -->
    <div id="divClient">
    	<table>
		    <thead>
		        <tr>
		            <th>Mail</th>
		            <th>Nom</th>
		            <th>Prenom</th>
		            <th>Nb Ecoutes</th>
		        </tr>
		    </thead>
		    <tbody>
	        	<% if (clients!=null) {%>
	        		<% for( Client elem : clients ) {%>
						<tr id="tr">
			            	<td><% out.print(elem.getMail()); %></td>
			            	<td><% out.print(elem.getNom()); %></td>
			            	<td><% out.print(elem.getPrenom()); %></td>
			            	<td><% out.print(elem.getNbEcoute()); %></td>
		            	</tr>
		            <% } %>
	        	<% } %>
		    </tbody>
	    </table>
    </div>
    <!-- Vues Titres -->
    <div id="divVueTitre">
    	<table>
		    <thead>
		        <tr>
		            <th>Titre</th>
		            <th>Annee sortie</th>
		            <th>Duree</th>
		            <th>Nb Ecoutes totale</th>
		            <th>Nb Ecoutes mois</th>
		            <th>Genre</th>
		        </tr>
		    </thead>
		    <tbody>
	        	<% if (titres!=null) {%>
	        		<% for( TitreMusical elem : titres ) {%>
						<tr id="tr">
			            	<td><% out.print(elem.getTitre()); %></td>
			            	<td><% out.print(elem.getAnneeCreation()); %></td>
			            	<td><% out.print(elem.getDuree()); %></td>
			            	<td><% out.print(elem.getNbEcoute()); %></td>
			            	<td><% out.print(elem.getNbEcouteMois()); %></td>
			            	<td><% out.print(elem.getGenre()); %></td>
		            	</tr>
		            <% } %>
	        	<% } %>
		    </tbody>
	    </table>
    </div>
</body>
</html>
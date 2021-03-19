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
	<%@ page import="Object.Client" %>
	
	<div class="mainContent">
		<!-- formulaire -->
		<div  class = "rechercheTextBox">
			<form method="POST">
			
				<input name="action" type="hidden" value="ModificationProfil">
		
				 <!-- choix du type d'élément -->
				<% String TypeElement = (String)request.getAttribute("TypeElement"); %>
		
		        <label><b>Nom</b></label>
		        <input id="recherche" type="text" placeholder="Entrer le nom" name="recherche">
		
		        <input type="submit" id='submit' value='Rechercher'>
		    </form>
		</div>
	    
	    <% List<Client> clients = (List<Client>)request.getAttribute("clients"); %>
	    
	    <!-- resultat de la requete -->
	    <div style="margin:10px;">
	    
	    <!-- Clients -->
		<div id="tableClients">
	    	<table>
	    		<thead>
			        <tr>
			            <th>Mail</th>
			            <th>Nom</th>
			            <th>Prenom</th>
			            <th>Civilite</th>
	       				<th>Date de naissance</th>
	       				<th>Adresse</th>
	       				<th>Genre</th>
			        </tr>
			    </thead>
			    <tbody>
		        	<% if (clients!=null) {%>
		        		<% for( Client elem : clients ) {%>
							<tr id="tr">
				            	<td><% out.print(elem.getMail()); %></td>
				            	<td><% if(elem.getNom()!=null){out.print(elem.getNom());} %></td>
				            	<td><% if(elem.getPrenom()!=null){out.print(elem.getPrenom());} %></td>
				            	<td><% out.print(elem.getCivilite()); %></td>
				            	<td><% if(elem.getDateNaissance()!=null){out.print(elem.getDateNaissance());} %></td>
				            	<td><% if(elem.getAdresseFacturation()!=null){out.print(elem.getAdresseFacturation());} %></td>
				            	<td><% out.print(elem.getStyleMusiquePrefere()); %></td>
				            	<td>
				            		<form method="POST">
				            			<input name="action" type="hidden" value="ModificationClient">
										<input name="idClient" type="hidden" value=<%elem.getId();%>>
										<input type="submit" id='submit' value='Modifier'>
									</form>
				            	</td>
				            	<td>
				            		<form method="POST">
				            			<input name="action" type="hidden" value="SuppressionClient">
				            			<input name="idString" type="hidden" value=<% out.print(elem.getId());%>>
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
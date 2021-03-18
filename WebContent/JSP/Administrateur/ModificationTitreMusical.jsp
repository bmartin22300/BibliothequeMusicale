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
	
	<% TitreMusical t = (TitreMusical)request.getSession().getAttribute("t"); %>
	
	<div class="mainContent">
	    <!-- formulaires -->
	    <div class="cardProfile">
			<form method="POST">
			
				<input name="action" type="hidden" value="ModificationTitreMusicalFin">
				<input name="id" type="hidden" value=<%= t.getIdCatalogue() %>>
				
		        <h1>Modification du titre musical :</h1>
		        
		    	<!-- insertion de donnee -->
		    
				<div>
					<h1>
						<label><b>Titre</b></label>
						<input id="titre" type="text" name="Titre"
						<% if(t.getTitre()==null){out.print("placeholder=\"Entrer le titre\"");}else{out.print("value=\""+t.getTitre()+"\"");}%>>
					</h1>
			        
			         <p>
					  <label for="cars">Genre</label>
					  <select id="cars" name="Genre">
					    <option value="RAP" <% if(t.getGenre()!=null && t.getGenre().toString()=="RAP"){out.print("selected");} %>>RAP</option>
					    <option value="ROCK" <% if(t.getGenre()!=null && t.getGenre().toString()=="ROCK"){out.print("selected");} %>>ROCK</option>TODO VERIF
					    <option value="JAZZ" <% if(t.getGenre()!=null && t.getGenre().toString()=="JAZZ"){out.print("selected");} %>>JAZZ</option>
					    <option value="POP" <% if(t.getGenre()!=null && t.getGenre().toString()=="POP"){out.print("selected");} %>>POP</option>
					    <option value="VARIETE" <% if(t.getGenre()!=null && t.getGenre().toString()=="VARIETE"){out.print("selected");} %>>VARIETE</option>
					    <option value="CLASSIQUE" <% if(t.getGenre()!=null && t.getGenre().toString()=="CLASSIQUE"){out.print("selected");} %>>CLASSIQUE</option>
					    <option value="TECHNO" <% if(t.getGenre()!=null && t.getGenre().toString()=="TECHNO"){out.print("selected");} %>>TECHNO</option>
					  </select>
					</p>
			        
			        <p>
			        	<label><b>Annee de creation</b></label>
			        	<input id="titre" type="text" name="Annee de creation"
			        	<% if(t.getAnneeCreation()==0){out.print("placeholder=\"YYYY-MM-DD\"");}else{out.print("value=\""+t.getAnneeCreation()+"\"");}%>>
			        </p>
			        		        
			        <p>
			       		<label><b>Duree</b></label>
			       		<input id="titre" type="text" name="Duree"
			       		<% if(t.getDuree()==0){out.print("placeholder=\"Entrer la duree\"");}else{out.print("value=\""+t.getDuree()+"\"");}%>>
			        </p>
			
			        <p style="padding-bottom:20px;"><input class="button" type="submit" id='submit' value='Suivant'></p>
				</div>    
			</form>
		</div>
	</div>
			
	<%@include file="../footer.jsp" %>
</body>
</html>
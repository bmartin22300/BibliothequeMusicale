<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<%@include file="../head.jsp" %>	 
</head>
<body onload="changerTypeElementsAffichesAjout()">
	<%@include file="../header.jsp" %>
	<%@ page import="java.util.List" %>
	<%@ page import="Object.TitreMusical" %>
	
    <!-- formulaires -->
    <div class="cardProfile">
		<form method="POST">
			<input name="action" type="hidden" value="AjouterElement">
	        <h1>Element a ajouter :</h1>
	        
	        <!-- choix du type d'�l�ment -->
	        <div style="margin:10px;">
				<!-- select box -->
				<p>
				  <label for="cars">Type d'�l�ment</label>
				  <select id="TypeElement" onChange="changerTypeElementsAffichesAjout()" name="TypeElement">
				    <option value="Titre musical">Titre musical</option>
				    <option value="Interprete">Interprete</option>
				    <option value="Album">Album</option>
				  </select>
				</p>				
			</div>       
    	
	    	<!-- insertion de donnee -->
	    	
	    	<!-- Titre musical -->
			<div id="formTitreMusical">
				<h1>
					<label><b>Titre</b></label>
					<input id="titre" type="text" placeholder="Entrer le titre" name="Titre">
				</h1>
		        
		        <p>
		        	<label><b>Genre</b></label>
		        	<input id="titre" type="text" placeholder="Entrer le genre" name="Genre">
		        </p>
		        
		        <p>
		        	<label><b>Annee de creation</b></label>
		        	<input id="titre" type="text" placeholder="YYYY-MM-DD" name="Annee de creation">
		        </p>
		        
		        <p>
		       		<label><b>Duree</b></label>
		       		<input id="titre" type="text" placeholder="300" name="Duree">
		        </p>
		
		        <p style="padding-bottom:20px;"><input type="submit" id='submit' value='Ajouter'></p>
			</div>
	    
	    
	    	<!-- Interprete -->
			<div id="formInterprete">
				<h1>
					<label><b>Pseudo</b></label>
					<input id="titre" type="text" placeholder="Entrer le pseudo" name="Pseudo">
				</h1>
				
				<p>
					<label><b>Prenom</b></label>
					<input id="titre" type="text" placeholder="Entrer le prenom" name="Prenom">
				</p>
				
				<p>
					<label><b>Nom</b></label>
					<input id="titre" type="text" placeholder="Entrer le nom" name="Nom">
				</p>
				
				<p>
					<label><b>Date de naissance</b></label>
					<input id="titre" type="text" placeholder="YYYY-MM-DD" name="Date de naissance">
				</p>
		        
		        <p style="padding-bottom:20px;"><input type="submit" id='submit' value='Ajouter'></p>
			</div>
	    
	    	<!-- Album -->
			<div id="formAlbum">
				<h1>
					<label><b>Titre</b></label>
					<input id="titre" type="text" placeholder="Entrer le titre" name="Titre">
				</h1>
		        
		        <p style="padding-bottom:20px;"><input type="submit" id='submit' value='Ajouter'></p>
			</div>
	    </form>	
    </div>
		
</body>
</html>
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
	<%@ page import="Object.Interprete" %>
	
	<div class="mainContent">
	    <!-- formulaires -->
	    <div class="cardProfile">
			<form method="POST">
			
				<input name="action" type="hidden" value="ModificationInterprete">
				
		        <h1>Modification de l'interprete :</h1>
		        
		    	<!-- insertion de donnee -->
		    
				<div>
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
			
			        <p style="padding-bottom:20px;"><input type="submit" id='submit' value='Suivant'></p>
				</div>    
			</form>
		</div>
	</div>
			
	<%@include file="../footer.jsp" %>
</body>
</html>
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
	
	<% Interprete i = (Interprete)request.getSession().getAttribute("i"); %>
	
	<div class="mainContent">
	    <!-- formulaires -->
	    <div class="cardProfile">
			<form method="POST">
			
				<input name="action" type="hidden" value="ModificationInterpreteFin">
				<input name="id" type="hidden" value=<%= i.getId() %>>
				
		        <h1>Modification de l'interprete :</h1>
		        
		    	<!-- insertion de donnee -->
		    
				<div>
					<h1>
						<label><b>Pseudo</b></label>
						<input id="titre" type="text" value=<%= i.getPseudonyme() %> name="Pseudo">
					</h1>
			        
			        <p>
			        	<label><b>Prenom</b></label>
			        	<input id="titre" type="text" name="Prenom"
			        	<% if(i.getPrenom()==null){out.print("placeholder=\"Entrer le prenom\"");}else{out.print("value="+i.getPrenom());}%>>
			        </p>
			        
			        <p>
			        	<label><b>Nom</b></label>
			        	<input id="titre" type="text" name="Nom"
			        	<% if(i.getNom()==null){out.print("placeholder=\"Entrer le nom\"");}else{out.print("value="+i.getNom());}%>>
			        </p>
			        		        
			        <p>
			       		<label><b>Date de naissance</b></label>
			       		<input id="titre" type="text" name="Date de naissance"
			       		<% if(i.getDateNaissance()==null){out.print("placeholder=\"YYYY-MM-DD\"");}else{out.print("value="+i.getDateNaissance());}%>>
			        </p>
			
			        <p style="padding-bottom:20px;"><input type="submit" id='submit' value='Suivant'></p>
				</div>    
			</form>
		</div>
	</div>
			
	<%@include file="../footer.jsp" %>
</body>
</html>
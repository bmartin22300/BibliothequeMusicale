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
	
	<% TitreMusical t = (TitreMusical)request.getSession().getAttribute("t"); %>
	<% request.getSession().setAttribute("titre",t); %>
	<% List<Interprete> interpretes = (List<Interprete>)request.getSession().getAttribute("interpretes"); %>
	<% List<Interprete> interpretesAssocies = (List<Interprete>)request.getSession().getAttribute("interpretesAssocies"); %>
	
	<div class="mainContent">
	    <!-- formulaires -->
	    <div class="cardProfile">
			<form method="POST">
			
				<input name="action" type="hidden" value="ModificationTitreMusicalInterpretesAjout">
				<input name="id" type="hidden" value=<%= t.getIdCatalogue() %>>
				
		        <h1>Modification des interpretes associes au titre musical :</h1>
			        
				 <!-- choix du type d'�l�ment -->
		        <div style="margin:10px;">
					<!-- select box -->
					<p>
					  <label for="cars">Interprete</label>
					  <% if(interpretes!=null){ %>
						  	<select name="interpreteSelectBox"> 
						  		<% for(Interprete interprete : interpretes) {%>
							  		<option value=<%= interprete.getId() %>><%= interprete.getPseudonyme() %></option>
							    <% } %>
						  	</select>
					  <%} %>
					  <input type="submit" id='submit' value='Ajouter'>
					</p>				
				</div>   
		    </form>
		    
		    <!-- liste des interpretes associe au titre -->
		    <ul>
		    	<% if(interpretesAssocies!=null) {%>
		    		<% for(Interprete interpreteAssocie : interpretesAssocies) {%>
				  		<li><%= interpreteAssocie.getPseudonyme() %></li>
				    <% } %>
		    	<%} %>
		    </ul>
		    
		    <!-- fin d'ajout -->
		    <form method="POST">
				<input name="action" type="hidden" value="ModificationTitreMusicalInterpretesFin">
				<input type="submit" id='submit' value='Terminer'>
			</form>
			
		</div>
	</div>
			
	<%@include file="../footer.jsp" %>
</body>
</html>
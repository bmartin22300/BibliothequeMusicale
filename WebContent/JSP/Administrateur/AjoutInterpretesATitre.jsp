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
	<%@ page import="Object.TitreMusical" %>
	
	<% List<Interprete> interpretes = (List<Interprete>)request.getAttribute("interpretes"); %>
	<% List<Interprete> interpretesAssocies = (List<Interprete>)request.getAttribute("interpretesAssocies"); %>
	<% TitreMusical titre = (TitreMusical)request.getAttribute("titre"); %>
	<% request.getSession().setAttribute("titre",titre); %>
	
	<!-- formulaire -->
	<form method="POST">
	
	<input name="action" type="hidden" value="AjoutInterpretesATitre">
	
	<h1>Ajouter un interprete</h1>
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
		<input name="action" type="hidden" value="FinAjoutInterpretesATitre">
		<input type="submit" id='submit' value='Terminer'>
	</form>
    
</body>
</html>
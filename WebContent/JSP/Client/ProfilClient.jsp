<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<%@include file="../head.jsp" %>	 
</head>
<body>
	<%@include file="../header.jsp" %>
	<%@page import="java.util.List,Object.Client"%>
	<% 
	String parametre = request.getParameter( "auteur" );
    out.println( parametre );
            %>
	
</body>
</html>
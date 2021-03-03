<% boolean isClient = (boolean)request.getAttribute("isClient"); %>
<% boolean isAdministrateur = (boolean)request.getAttribute("isAdministrateur"); %>

<!-- navbar -->
<div class="topnav">
	<!-- client -->
	<% if(isClient==true){ %>
		<a class="active" href="Client/AccueilClient.jsp">Accueil</a>
		<a href="Client/GestionPlaylist.jsp">Gérer mes playlists</a>
		<a href="Client/ProfilClient.jsp">Modifier mon profil</a>
		<a href="Utilisateur/Accueil.jsp">Déconnexion</a>	
	<% }else{ %>
		<!-- administrateur -->
		<% if(isAdministrateur==true){ %>
			<a class="active" href="Administrateur/AccueilAdministrateur.jsp">Accueil</a>
			<a href="Administrateur/ModificationCatalogue.jsp">Modifier le catalogue</a>
			<a href="Administrateur/ModificationProfil.jsp">Modifier un profil client</a>
			<a href="Administrateur/Statistiques.jsp">Consulter les statistiques</a>
			<a href="Administrateur/ProfilAdministrateur.jsp">Modifier mon profil</a>
			<a href="Utilisateur/Accueil.jsp">Déconnexion</a>	
		<!-- utilisateur -->
		<% }else{ %>
			<a class="active" href="Utilisateur/Accueil.jsp">Accueil</a>
			<a href="Utilisateur/AuthentificationClient.jsp">Connexion</a>
		<%} %>
	<%} %>
</div>


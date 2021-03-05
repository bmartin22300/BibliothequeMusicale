<% boolean isClient = (boolean)request.getAttribute("isClient"); %>
<% boolean isAdministrateur = (boolean)request.getAttribute("isAdministrateur"); %>
<% String pseudonyme = (String)request.getAttribute("pseudonyme"); %>

<!-- navbar -->
<div class="topnav">
	<!-- client -->
	<% if(isClient==true){ %>	
		<a class="active" href=AccueilClient>Accueil</a>
		<a href=GestionPlaylist>Gérer mes playlists</a>
		<a href=ProfilClient>Modifier mon profil</a>
		<a href=Accueil>Déconnexion</a>	
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
			<a class="active" href=Accueil>Accueil</a>
			<a href=AuthentificationClient>Connexion</a>
		<%} %>
	<%} %>
</div>


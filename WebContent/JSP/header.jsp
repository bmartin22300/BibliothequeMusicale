<% boolean isClient = (boolean)request.getAttribute("isClient"); %>
<% boolean isAdministrateur = (boolean)request.getAttribute("isAdministrateur"); %>

<!-- navbar -->
<div class="topnav">
	<!-- client -->
	<% if(isClient==true){ %>	
		<a class="active" href=AccueilClient>Accueil</a>
		<a href=GestionPlaylist>G�rer mes playlists</a>
		<a href=ProfilClient>Modifier mon profil</a>
		<a href=Accueil>D�connexion</a>	
		<a href=Sandbox>Sandbox</a>
	<% }else{ %>
		<!-- administrateur -->
		<% if(isAdministrateur==true){ %>
			<% boolean isAdministrateurMusical = (boolean)request.getAttribute("isAdministrateurMusical"); %>
			<a class="active" href=AccueilAdministrateur>Accueil</a>
			<% if(isAdministrateurMusical==true){ %>
				<a href=ModificationCatalogue>Modifier le catalogue</a>
			<% } else { %>
				<a href=ModificationProfil>Modifier un profil client</a>
			<% } %>			
			<a href=Statistiques>Consulter les statistiques</a>
			<a href=ProfilAdministrateur>Modifier mon profil</a>
			<a href=Accueil>D�connexion</a>	
		<!-- utilisateur -->
		<% }else{ %>
			<a class="active" href=Accueil>Accueil</a>
			<a href=AuthentificationClient>Connexion</a>
		<%} %>
	<%} %>
</div>


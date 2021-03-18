<% boolean isClient = (boolean)request.getAttribute("isClient"); %>
<% boolean isAdministrateur = (boolean)request.getAttribute("isAdministrateur"); %>
<% String navBarActive = (String)request.getAttribute("nav-bar-active"); %>

<!-- navbar -->
<div class="topnav">
	<!-- client -->
	<% if(isClient==true){ %>	
		<div id="myDIV">
			<a class="aref <% if(navBarActive!=null){if(navBarActive.equals("AccueilClient")){out.print("active");}}%>" href=AccueilClient>Accueil</a>
			<a class="aref <% if(navBarActive!=null){if(navBarActive.equals("GestionPlaylist")){out.print("active");}}%>" href=GestionPlaylist>G�rer mes playlists</a>
			<a class="aref" href=AjoutPlaylist>Cr�er une playlist</a>
			<a class="aref" href=ProfilClient>Modifier mon profil</a>
			<a class="aref" href=Accueil>D�connexion</a>	
			<a class="aref" href=Sandbox>Sandbox</a>
		</div>
	<% }else{ %>
		<!-- administrateur -->
		<% if(isAdministrateur==true){ %>
			<div id="myDIV">
				<% boolean isAdministrateurMusical = (boolean)request.getAttribute("isAdministrateurMusical"); %>
				<a class="aref  active"  href=AccueilAdministrateur>Accueil</a>
				<% if(isAdministrateurMusical==true){ %>
					<a class="aref" href=ModificationCatalogue>Modifier le catalogue</a>
					<a class="aref" href=AjoutCatalogue>Ajouter un �l�ment au catalogue</a>
				<% } else { %>
					<a class="aref" href=ModificationProfil>Modifier un profil client</a>
				<% } %>			
				<a class="aref" href=Statistiques>Consulter les statistiques</a>
				<a class="aref" href=ProfilAdministrateur>Modifier mon profil</a>
				<a class="aref" href=Accueil>D�connexion</a>
			</div>	
		<!-- utilisateur -->
		<% }else{ %>
			<div id="myDIV">
				<a class="aref  active" href=Accueil>Accueil</a>
				<a class="aref" href=AuthentificationClient>Connexion</a>
			</div>
		<%} %>
	<%} %>
</div>

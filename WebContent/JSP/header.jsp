<% boolean isClient = (boolean)request.getAttribute("isClient"); %>
<% boolean isAdministrateur = (boolean)request.getAttribute("isAdministrateur"); %>
<% String navBarActive = (String)request.getAttribute("nav-bar-active"); %>

<!-- navbar -->
<div class="topnav">
	<!-- client -->
	<% if(isClient==true){ %>	
		<div id="myDIV">
			<a class="aref <% if(navBarActive!=null){if(navBarActive.equals("AccueilClient")){out.print("active");}}else{out.print("active");}%>" href=AccueilClient>Accueil</a>
			<a class="aref <% if(navBarActive!=null){if(navBarActive.equals("GestionPlaylist")){out.print("active");}}%>" href=GestionPlaylist>Gérer mes playlists</a>
			<a class="aref <% if(navBarActive!=null){if(navBarActive.equals("AjoutPlaylist")){out.print("active");}}%>" href=AjoutPlaylist>Créer une playlist</a>
			<a class="aref <% if(navBarActive!=null){if(navBarActive.equals("ProfilClient")){out.print("active");}}%>" href=ProfilClient>Modifier mon profil</a>
			<a class="aref <% if(navBarActive!=null){if(navBarActive.equals("Accueil")){out.print("active");}}%>" href=Accueil>Déconnexion</a>	
			<a class="aref <% if(navBarActive!=null){if(navBarActive.equals("Sandbox")){out.print("active");}}%>" href=Sandbox>Sandbox</a>
		</div>
	<% }else{ %>
		<!-- administrateur -->
		<% if(isAdministrateur==true){ %>
			<div id="myDIV">
				<% boolean isAdministrateurMusical = (boolean)request.getAttribute("isAdministrateurMusical"); %>
				<a class="aref <% if(navBarActive!=null){if(navBarActive.equals("AccueilAdministrateur")){out.print("active");}}else{out.print("active");}%>" href=AccueilAdministrateur>Accueil</a>
				<% if(isAdministrateurMusical==true){ %>
					<a class="aref <% if(navBarActive!=null){if(navBarActive.equals("ModificationCatalogue")){out.print("active");}}%>" href=ModificationCatalogue>Modifier le catalogue</a>
					<a class="aref <% if(navBarActive!=null){if(navBarActive.equals("AjoutCatalogue")){out.print("active");}}%>" href=AjoutCatalogue>Ajouter un élément au catalogue</a>
				<% } else { %>
					<a class="aref <% if(navBarActive!=null){if(navBarActive.equals("ModificationProfil")){out.print("active");}}%>" href=ModificationProfil>Modifier un profil client</a>
				<% } %>			
				<a class="aref <% if(navBarActive!=null){if(navBarActive.equals("Statistiques")){out.print("active");}}%>" href=Statistiques>Consulter les statistiques</a>
				<a class="aref <% if(navBarActive!=null){if(navBarActive.equals("ProfilAdministrateur")){out.print("active");}}%>" href=ProfilAdministrateur>Modifier mon profil</a>
				<a class="aref <% if(navBarActive!=null){if(navBarActive.equals("Accueil")){out.print("active");}}%>" href=Accueil>Déconnexion</a>
			</div>	
		<!-- utilisateur -->
		<% }else{ %>
			<div id="myDIV">
				<a class="aref <% if(navBarActive!=null){if(navBarActive.equals("Accueil")){out.print("active");}}else{out.print("active");}%>" href=Accueil>Accueil</a>
				<a class="aref <% if(navBarActive!=null){if(navBarActive.equals("Connexion")){out.print("active");}}%>" href=AuthentificationClient>Connexion</a>
			</div>
		<%} %>
	<%} %>
</div>

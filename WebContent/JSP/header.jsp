<% boolean isClient = (boolean)request.getAttribute("isClient"); %>
<% boolean isAdministrateur = (boolean)request.getAttribute("isAdministrateur"); %>

<!-- navbar -->
<div class="topnav">
	<!-- client -->
	<% if(isClient==true){ %>	
		<div id="myDIV">
			<a class="aref active" href=AccueilClient>Accueil</a>
			<a class="aref" href=GestionPlaylist>Gérer mes playlists</a>
			<a class="aref" href=ProfilClient>Modifier mon profil</a>
			<a class="aref" href=Accueil>Déconnexion</a>	
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
					<a class="aref" href=AjoutCatalogue>Ajouter un élément au catalogue</a>
				<% } else { %>
					<a class="aref" href=ModificationProfil>Modifier un profil client</a>
				<% } %>			
				<a class="aref" href=Statistiques>Consulter les statistiques</a>
				<a class="aref" href=ProfilAdministrateur>Modifier mon profil</a>
				<a class="aref" href=Accueil>Déconnexion</a>
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

<script>
	//Get the container element
	var aRefContainer = document.getElementById("myDIV");
	
	// Get all buttons with class="aref" inside the container
	var aRefs = aRefContainer.getElementsByClassName("aref");
	
	//var pathname = window.location.pathname,
	//pages = ['InscriptionClient', 'AuthentificationClient', 'AuthentificationAdministrateur','Accueil'];
	
	//aRefs.each(function(i) {
	// if (pathname.includes(pages[i])) this.addClass('active');
	// else if (this.className.includes('active')) this.removeClass('active');
	//});
	
	for (var i = 0; i < aRefs.length; i++) {
		aRefs[i].addEventListener("click", function() {
		    var current = document.getElementsByClassName("active");
		    current[0].className = current[0].className.replace(" active", "");
		    this.className += " active";
 		});
	}
</script>
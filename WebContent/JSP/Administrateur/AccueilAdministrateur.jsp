<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<%@include file="../head.jsp" %>	 
</head>
<body onload="changerTypeElementsAffiches()">
	<%@include file="../header.jsp" %>
	<%@ page import="java.util.List" %>
	<%@ page import="Object.TitreMusical" %>
	
	<!--  ExternalRessource/Catalogue -->
	<div style="margin:10px;">
		<!-- select box -->
		<label for="pet-select">Element a rechercher</label>
		<select name="pets" id="TypeElement" onChange="changerTypeElementsAffichesRecherche()">
	    	<option value="Titres musicaux">Titres musicaux</option>
		    <option value="Interpretes">Interpretes</option>
		    <option value="Albums">Albums</option>
		</select>
	</div>
	
	<% List<TitreMusical> titresMusicaux = (List<TitreMusical>)request.getAttribute("titresMusicaux"); %>
	<% int interpretes = (int)request.getAttribute("interpretes"); %>
	<% int albums = (int)request.getAttribute("albums"); %>
	
		
	<% if (titresMusicaux!=null) {%>
		<div class="wrapper" id="catalogueTitresMusicaux" style="display:grid;">
       		<% for( TitreMusical elem : titresMusicaux ) {%>
				<div class="card catalogueTitreMusical">	
					<!-- ExternalRessource/Catalogue element -->
					<img src="ExternalRessource/Catalogue/Image/TitreMusical/titreMusical.png" alt="Denim Jeans" style="width:100%">
					<audio id="player" controls preload tabindex="0">
						<source type="audio/mp3"/>
						Your browser does not support HTML5 audio.
					</audio>
				  <h1>Ce monde est cruel</h1>
				  <p class="price">Vald</p>
				  <p><button>Ce monde est cruel</button></p>
					<!-- connexion client -->
				    <p><a href="ModificationTitre?titre=cmec">Modifier</a></p>
					<!-- connexion client -->
				    <p><a href="SupprimerTitre">Supprimer</a></p>
				</div>
			<% } %>
		</div>
	<% } %>
	
	<div id="catalogueInterpretes">	
		<%= interpretes %>
	</div>
	<div id="catalogueAlbums">	
		<%= albums %>
	</div>
	
	<div class="wrapper">	
		<!-- ExternalRessource/Catalogue element -->
		<div class="card">
			<img src="ExternalRessource/Catalogue/Image/TitreMusical/ce_monde_est_cruel.jpg" alt="Denim Jeans" style="width:100%">
			<audio id="player" controls preload tabindex="0">
				<source type="audio/mp3" src="ExternalRessource/Catalogue/Musique/magma_isha.mp3"/>
				Your browser does not support HTML5 audio.
			</audio>
		  <h1>Ce monde est cruel</h1>
		  <p class="price">Vald</p>
		  <p><button>Ce monde est cruel</button></p>
			<!-- connexion client -->
		    <p><a href="ModificationTitre?titre=cmec">Modifier</a></p>
			<!-- connexion client -->
		    <p><a href="SupprimerTitre">Supprimer</a></p>
		</div>
		<div class="card">
			<img src="ExternalRessource/Catalogue/Image/TitreMusical/astroworl.jpg" alt="Denim Jeans" style="width:100%;">
			<audio id="player" controls preload tabindex="0">
				<source type="audio/mp3" src="ExternalRessource/Catalogue/Musique/magma_isha.mp3"/>
				Your browser does not support HTML5 audio.
			</audio>
		  <h1>Stargazing</h1>
		  <p class="price">Travis scott</p>
		  <p><button>Astroworld</button></p>
		</div>
		<div class="card">
			<img src="ExternalRessource/Catalogue/Image/TitreMusical/post_malone.jpeg" alt="Denim Jeans" style="width:100%">
			<audio id="player" controls preload tabindex="0">
				<source type="audio/mp3" src="ExternalRessource/Catalogue/Musique/magma_isha.mp3"/>
				Your browser does not support HTML5 audio.
			</audio>
		  <h1>Flex</h1>
		  <p class="price">Post malone</p>
		  <p><button>Beer pong and bentleys</button></p>
		</div>
		<div class="card">
			<img src="ExternalRessource/Catalogue/Image/TitreMusical/isha.jpg" alt="Denim Jeans" style="width:100%">
			<audio id="player" controls preload tabindex="0">
				<source type="audio/mp3" src="ExternalRessource/Catalogue/Musique/magma_isha.mp3"/>
				Your browser does not support HTML5 audio.
			</audio>
		  <h1>Durag</h1>
		  <p class="price">Isha</p>
		  <p><button>La vie augmente, Vol 3</button></p>
		</div>
		<div class="card">
		  <img src="ExternalRessource/Catalogue/Image/TitreMusical/isha.jpg" alt="Denim Jeans" style="width:100%">
		  <audio id="player" controls preload tabindex="0">
				<source type="audio/mp3" src="ExternalRessource/Catalogue/Musique/magma_isha.mp3"/>
				Your browser does not support HTML5 audio.
			</audio>
		  <h1>Bad boy</h1>
		  <p class="price">Isha</p>
		  <p><button>La vie augmente, Vol 3</button></p>
		</div>
		<div class="card">
		  <img src="ExternalRessource/Catalogue/Image/TitreMusical/isha.jpg" alt="Denim Jeans" style="width:100%">
		  <audio id="player" controls preload tabindex="0">
				<source type="audio/mp3" src="ExternalRessource/Catalogue/Musique/magma_isha.mp3"/>
				Your browser does not support HTML5 audio.
			</audio>
		  <h1>Tradition</h1>
		  <p class="price">Isha</p>
		  <p><button>La vie augmente, Vol 3</button></p>
		</div>
		<div class="card">
		  <img src="ExternalRessource/Catalogue/Image/TitreMusical/isha.jpg" alt="Denim Jeans" style="width:100%">
		  <audio id="player" controls preload tabindex="0">
				<source type="audio/mp3" src="ExternalRessource/Catalogue/Musique/magma_isha.mp3"/>
				Your browser does not support HTML5 audio.
			</audio>
		  <h1>Les magiciens</h1>
		  <p class="price">Isha</p>
		  <p><button>La vie augmente, Vol 3</button></p>
		</div>
		<div class="card">
		  <img src="ExternalRessource/Catalogue/Image/TitreMusical/isha.jpg" alt="Denim Jeans" style="width:100%">
		  <audio id="player" controls preload tabindex="0">
				<source type="audio/mp3" src="ExternalRessource/Catalogue/Musique/magma_isha.mp3"/>
				Your browser does not support HTML5 audio.
			</audio>
		  <h1>Magma</h1>
		  <p class="price">Isha</p>
		  <p><button>La vie augmente, Vol 3</button></p>
		</div>
		<div class="card">
		  <img src="ExternalRessource/Catalogue/Image/TitreMusical/isha.jpg" alt="Denim Jeans" style="width:100%">
		  <audio id="player" controls preload tabindex="0">
				<source type="audio/mp3" src="ExternalRessource/Catalogue/Musique/magma_isha.mp3"/>
				Your browser does not support HTML5 audio.
			</audio>
		  <h1>Chaud devant</h1>
		  <p class="price">Isha</p>
		  <p><button>La vie augmente, Vol 3</button></p>
		</div>
		<div class="card">
		  <img src="ExternalRessource/Catalogue/Image/TitreMusical/isha.jpg" alt="Denim Jeans" style="width:100%">
		  <audio id="player" controls preload tabindex="0">
				<source type="audio/mp3" src="ExternalRessource/Catalogue/Musique/magma_isha.mp3"/>
				Your browser does not support HTML5 audio.
			</audio>
		  <h1>Idole</h1>
		  <p class="price">Isha</p>
		  <p><button>La vie augmente, Vol 3</button></p>
		</div>
	</div>
	
</body>
</html>
	
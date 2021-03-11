function init(){
}

function changerTypeElementsAffichesModification(){	
	TypeElement = document.getElementById("TypeElement").value;
	
	switch (TypeElement) {
	  case 'Titres musicaux':
	 	document.getElementById("catalogueInterpretes").style.display = "none";
	 	document.getElementById("catalogueAlbums").style.display = "none";
	    document.getElementById("catalogueTitresMusicaux").style.display = "grid";
	    break;
	  case 'Interpretes':
	   	document.getElementById("catalogueTitresMusicaux").style.display = "none";	
	   	document.getElementById("catalogueAlbums").style.display = "none";
	   	document.getElementById("catalogueInterpretes").style.display = "grid";
	  	break;
	  case 'Albums':
	    document.getElementById("catalogueInterpretes").style.display = "none";
	    document.getElementById("catalogueTitresMusicaux").style.display = "none";
	    document.getElementById("catalogueAlbums").style.display = "grid";
	    break;
	  default:
	     console.log("default case");
	}
}

function changerTypeElementsAffichesAjout(){	
	TypeElement = document.getElementById("TypeElement").value;
	
	switch (TypeElement) {
	  case 'Titre musical':
	 	document.getElementById("formTitreMusical").style.display = "grid";
	 	document.getElementById("formInterprete").style.display = "none";
	    document.getElementById("formAlbum").style.display = "none";
	    break;
	  case 'Interprete':
	   	document.getElementById("formTitreMusical").style.display = "none";
	 	document.getElementById("formInterprete").style.display = "grid";
	    document.getElementById("formAlbum").style.display = "none";
	  	break;
	  case 'Album':
	    document.getElementById("formTitreMusical").style.display = "none";
	 	document.getElementById("formInterprete").style.display = "none";
	    document.getElementById("formAlbum").style.display = "grid";
	    break;
	  default:
	     console.log("default case");
	}
}
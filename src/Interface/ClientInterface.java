package Interface;

import java.util.Date;
import java.util.List;

import Object.Album;
import Object.Client;
import Object.ElementCatalogue;
import Object.Genre;
import Object.Interprete;
import Object.Playlist;
import Object.TitreMusical;

public interface ClientInterface {

	// VALIDE
	
	// TESTE
	public Client modifierInformations(String mail, String password, String civilite, String nom, String prenom, Date dateNaissance, String adresseFacturation, Genre styleMusiquePrefere);
	
	public boolean regarder(ElementCatalogue elementCatalogue);
	
	// Playlist
	public Playlist creerPlaylist(String nomPlaylist, List<TitreMusical> titresMusicaux);
	public boolean changerNomPlaylist(Playlist playlist, String nom);
	public boolean supprimerPlaylist(Playlist playlist);
	
	public boolean ajouterTitrePlaylist(TitreMusical titreMusical, Playlist playlist);
	public boolean retirerTitrePlaylist(TitreMusical titreMusical, Playlist playlist);
	
	
	//A VERIFIER
	
	//TODO
	
	//PARTIE PLAYLIST / CATALOGUE
	
	// Parcourir Catalogue -- Comme Admin **
	public List<ElementCatalogue> rechercherParNomCatalogue(String recherche);
	public List<ElementCatalogue> rechercherParInterpreteCatalogue(String recherche);
	public List<ElementCatalogue> rechercherParGenreCatalogue(Genre genre);
	public List<ElementCatalogue> rechercherParDateSortieCatalogue(Date date);
	public List<ElementCatalogue> parcourirCatalogueCatalogue();
	
	// Parcourir TitreMusical
	public List<TitreMusical> rechercherParNomTitre(String recherche);
	public List<TitreMusical> rechercherParInterpreteTitre(String recherche);
	public List<TitreMusical> rechercherParGenreTitre(Genre genre);
	public List<TitreMusical> rechercherParDateSortieTitre(Date date);
	public List<TitreMusical> parcourirCatalogueTitre();
	
	// Parcourir Album
	public List<Album> rechercherParNomAlbum(String recherche);
	public List<Album> rechercherParInterpreteAlbum(String recherche);
	public List<Album> rechercherParGenreAlbum(Genre genre);
	public List<Album> rechercherParDateSortieAlbum(Date date);
	public List<Album> parcourirCatalogueAlbum();
	
	// Parcourir Interpretes
	public List<Interprete> rechercherParPseudoInterprete(String recherche);
	public List<Interprete> rechercherParPrenomInterprete(String recherche);
	public List<Interprete> rechercherParNomInterprete(String recherche);
	//***************************************	
}

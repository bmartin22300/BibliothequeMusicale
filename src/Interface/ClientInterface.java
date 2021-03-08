package Interface;

import java.sql.Date;
import java.util.List;

import Object.Album;
import Object.Client;
import Object.ElementCatalogue;
import Object.Genre;
import Object.Interprete;
import Object.Playlist;
import Object.TitreMusical;

public interface ClientInterface {

	// Valide
	
	
	//A verifier - Tester
	public Client modifierInformations(String password, String civilite, String nom, String prenom, Date dateNaissance, String adresseFacturation, Genre styleMusiquePrefere); // null dans les champs où on veut pas donner ça devrait être fine
	
	//TODO
	
	//PARTIE PLAYLIST / CATALOGUE
	//public void deconnexion();?=> Supprime l'objet client?
	public void regarderElementCatalogue(ElementCatalogue elementCatalogue);
	
	
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
	
	
	public boolean creerPlaylist(String nomPlaylist, List<ElementCatalogue> elementsCatalogue); // Se récupère dans Client.playlists, mais on peut mettre en retour aussi
	//Optionnel ***
	public boolean supprimerPlaylist(Playlist playlist);
	public boolean changerNomPlaylist(Playlist playlist, String nom);
	//*************
	
	public boolean modifierPlaylist(Playlist playlist, List<ElementCatalogue> elementsCatalogue); // Vide playlist puis rempli playlist avec nouveaux titres
	public boolean retirerDePlaylist(ElementCatalogue elementCatalogue, Playlist playlist);
	public boolean ajouterAPlaylist(ElementCatalogue elementCatalogue, Playlist playlist);
	
	
}

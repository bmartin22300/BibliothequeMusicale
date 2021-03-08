package Interface;

import java.sql.Date;
import java.util.List;

import Object.ElementCatalogue;
import Object.Genre;
import Object.Playlist;

public interface ClientInterface {

	//A verifier - Tester
	public boolean modifierInformations(String password, String civilite, String nom, String prenom, Date dateNaissance, String adresseFacturation, Genre styleMusiquePrefere); // null dans les champs où on veut pas donner ça devrait être fine

	
	//TODO
	
	//PARTIE PLAYLIST / CATALOGUE
	//public void deconnexion();?=> Supprime l'objet client?
	public void regarderElementCatalogue(ElementCatalogue elementCatalogue);
	
	public List<ElementCatalogue> rechercherParNom(String nom);
	public List<ElementCatalogue> rechercherParInterprete(String interprete);
	public List<ElementCatalogue> rechercherParGenre(Genre genre);
	public List<ElementCatalogue> rechercherParDateSortie(Date date);
	public List<ElementCatalogue> parcourirCatalogue();
	
	public boolean creerPlaylist(String nomPlaylist, List<ElementCatalogue> elementsCatalogue); // Se récupère dans Client.playlists, mais on peut mettre en retour aussi
	//Optionnel ***
	public boolean supprimerPlaylist(Playlist playlist);
	public boolean changerNomPlaylist(Playlist playlist, String nom);
	//*************
	
	public boolean modifierPlaylist(Playlist playlist, List<ElementCatalogue> elementsCatalogue); // Vide playlist puis rempli playlist avec nouveaux titres
	public boolean retirerDePlaylist(ElementCatalogue elementCatalogue, Playlist playlist);
	public boolean ajouterAPlaylist(ElementCatalogue elementCatalogue, Playlist playlist);
	
	
}

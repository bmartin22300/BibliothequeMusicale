package Interface;

import java.sql.Date;
import java.util.List;

import Object.ElementCatalogue;
import Object.Genre;
import Object.Playlist;

public interface ClientInterface {

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
	public boolean supprimerPlaylist(Playlist playlist);
	public boolean modifierPlaylist(Playlist playlist, List<ElementCatalogue> elementsCatalogue); // Vide playlist puis rempli playlist avec nouveaux titres
	
	public boolean changerNomPlaylist(Playlist playlist, String nom);
	
	public boolean retirerDePlaylist(ElementCatalogue elementCatalogue, Playlist playlist);
	public boolean ajouterAPlaylist(ElementCatalogue elementCatalogue, Playlist playlist);
	
	//PARTIE INFOS PERSO -- Ou on fait comme dans ProfilGestionnaireClient, besoin du retour en mp quand tu liras ça
	public boolean modifierInformationsPerso(String password, String civilite, String nom, String prenom, Date dateNaissance, String adresseFacturation, Genre styleMusiquePrefere); // null dans les champs où on veut pas donner ça devrait être fine
	
	

}

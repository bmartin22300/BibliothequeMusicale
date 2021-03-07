package Interface;

import java.sql.Date;
import java.time.Year;
import java.util.List;

import Object.Client;
import Object.ElementCatalogue;
import Object.Genre;
import Object.Interprete;
import Object.Podcast;
import Object.Radio;
import Object.TitreMusical;

public interface ProfilGestionnaireMusicalInterface extends AdministrateurInterface {
	
	public Interprete creerInterprete(String pseudo);
	
	public Interprete creerInterprete(String pseudo, String prenom, String nom, Date dateNaissance);
	
	public TitreMusical creerTitre(String titre, Year anneeCreation, List<Interprete> interpretes, int duree, Genre genre);
	
	// A améliorer
	public void ajoutDiscographie(int idTitre, String pseudoInterprete);
	
	public void creerAlbum(String nom, String anneeSortie);
	
	public void ajoutTitreAlbum(int idTitre, int idAlbum);
	
	
	//TODO
	//Comme dans Client
	public List<ElementCatalogue> rechercherParNom(String nom);
	public List<ElementCatalogue> rechercherParInterprete(String interprete);
	public List<ElementCatalogue> rechercherParGenre(Genre genre);
	public List<ElementCatalogue> rechercherParDateSortie(Date date);
	public List<ElementCatalogue> parcourirCatalogue();
	//*****************
	
	public boolean supprimerElementCatalogue(ElementCatalogue elementCatalogue);
	
	public Interprete obtenirInterprete(String pseudo);
	public boolean modifierInterprete(String pseudo, String prenom, String nom, Date dateNaissance); //Pseudo ne change pas !
	public boolean supprimerInterprete(Interprete interprete);	
	
	public Radio creerRadio(String nom, Genre genreMusical);
	public Podcast creerPodcast(String titre, int duree, String PseudoAuteur, String categorie);
	
	public boolean recommander(ElementCatalogue elementCatalogue);
	public List<TitreMusical> topTitresEcoutes();
	public List<Client> topUtilisateursEcoutes();
}

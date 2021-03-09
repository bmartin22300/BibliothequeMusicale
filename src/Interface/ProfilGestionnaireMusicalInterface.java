package Interface;

import java.sql.Date;
import java.time.Year;
import java.util.List;

import Object.Album;
import Object.ElementCatalogue;
import Object.Genre;
import Object.Interprete;
import Object.Podcast;
import Object.Radio;
import Object.TitreMusical;

public interface ProfilGestionnaireMusicalInterface extends AdministrateurInterface {

	// Valide	
	
	// Teste
	
	
	//A verifier 
	// Interprete
	public Interprete creerInterprete(String pseudo);
	public Interprete creerInterprete(String pseudo, String prenom, String nom, Date dateNaissance);
	public boolean modifierInterprete(Interprete interprete, String prenom, String nom, Date dateNaissance);
	public boolean supprimerInterprete(Interprete interprete);
	
	// TitreMusical
	public TitreMusical creerTitre(String titre, Year anneeCreation, List<Interprete> interpretes, int duree, Genre genre);
	public boolean modifierTitre(TitreMusical titreMusical, String titre, Year anneeCreation, int duree, Genre genre);
	public boolean supprimerTitre(TitreMusical titreMusical);
	
	public boolean ajouterDiscographie(TitreMusical titre, Interprete interprete);
	public boolean retirerDiscographie(TitreMusical titre, Interprete interprete);
	
	// Album
	public Album creerAlbum(String nom, Year anneeSortie, List<TitreMusical> titres, List<Interprete> interpretes);
	public boolean modifierAlbum(Album album, String nom, Year anneeSortie);
	public boolean supprimerAlbum(Album album);
	
	public boolean ajouterDiscographie(Album album, Interprete interprete);
	public boolean retirerDiscographie(Album album, Interprete interprete);

	public boolean ajoutTitreAlbum(TitreMusical titre, Album album);
	public boolean suppressionTitreAlbum(TitreMusical titre, Album album);
	
	
	//TODO
	
	
	// Radio
	public Radio creerRadio(String nom, Genre genreMusical);
	public boolean modifierRadio(Radio radio, String nom, Genre genreMusical);
	public boolean supprimerRadio(Radio radio);
	
	// Podcast
	public Podcast creerPodcast(String titre, int duree, String PseudoAuteur, String categorie);
	public boolean modifierPodcast(String titre, int duree, String PseudoAuteur, String categorie);
	public boolean supprimerPodcast(Podcast podcast);

	
	public boolean recommander(ElementCatalogue elementCatalogue);

}

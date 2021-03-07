package Interface;

import java.sql.Date;
import java.time.Year;
import java.util.List;

import Object.Genre;
import Object.Interprete;
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
	public void creerPlaylist(String nom, String mail);
	
	public void ajoutElementCataloguePlaylist(int idCatalogue, int idPlaylist);
	

}

package Interface;

import java.sql.Date;
import java.util.List;

import Object.Administrateur;
import Object.Album;
import Object.Client;
import Object.ElementCatalogue;
import Object.Genre;
import Object.Interprete;
import Object.TitreMusical;

public interface AdministrateurInterface {

	// VALIDE
	
	// TESTE
	public Administrateur creerAdmin(String mail, String password);
	
	// Parcourir Interpretes
	public List<Interprete> rechercherParPseudoInterprete(String recherche);
	public List<Interprete> rechercherParPrenomInterprete(String recherche);
	public List<Interprete> rechercherParNomInterprete(String recherche);
	public Interprete getInterprete(int id);
	
	// Parcourir TitreMusical
	public List<TitreMusical> rechercherParNomTitre(String recherche);
	public TitreMusical getTitreMusical(int id);
	
	// Parcourir Album
	public List<Album> rechercherParNomAlbum(String recherche);
	public Album getAlbum(int id);
	
	// Parcourir Clients
	public List<Client> rechercherParMailClient(String recherche);
	public Client getClient(int id);
	
	// Regarder
	public boolean regarder(ElementCatalogue elementCatalogue);
	
	// Statistiques
	public List<TitreMusical> topTitresEcoutes();
	public List<Client> topUtilisateursEcoutes();
	public List<TitreMusical> recommandationsDuMoment();
	
	//A VERIFIER
	
	// Modification des donn�es de l'administrateur sur la BD
	public Administrateur modifierInformations(String mail, String password);
	
	// Parcourir TitreMusical
	public List<TitreMusical> rechercherParGenreTitre(Genre genre);
	public List<TitreMusical> rechercherParDateSortieTitre(Date date);
	public List<TitreMusical> parcourirCatalogueTitre();

	
	//TODO
	
	// Parcourir Clients
	public List<Client> rechercherParNomClient(String recherche);
	public List<Client> rechercherParPrenomClient(String recherche);
	// Autres crit�res de recherche useless? (GenrePr�f�r�, adresse etc)
	
	// Parcourir Album
	public List<Album> rechercherParInterpreteAlbum(String recherche);
	public List<Album> rechercherParGenreAlbum(Genre genre);
	public List<Album> rechercherParDateSortieAlbum(Date date);
	public List<Album> parcourirCatalogueAlbum();
}

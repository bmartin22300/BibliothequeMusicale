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
	
	// Regarder
	public boolean regarder(ElementCatalogue elementCatalogue);
	
	
	//A VERIFIER
	
	// Parcourir TitreMusical
	public List<TitreMusical> rechercherParGenreTitre(Genre genre);
	public List<TitreMusical> rechercherParDateSortieTitre(Date date);
	public List<TitreMusical> parcourirCatalogueTitre();

	
	//TODO
	
	// Statistiques
	public List<TitreMusical> topTitresEcoutes();
	public List<Client> topUtilisateursEcoutes();
	
	// Parcourir Clients
	public List<Client> rechercherParMailClient(String recherche);
	public List<Client> rechercherParNomClient(String recherche);
	public List<Client> rechercherParPrenomClient(String recherche);
	// Autres critères de recherche useless? (GenrePréféré, adresse etc)
	
	// Parcourir Catalogue
	public List<ElementCatalogue> rechercherParNomCatalogue(String recherche);
	public List<ElementCatalogue> rechercherParInterpreteCatalogue(String recherche);
	public List<ElementCatalogue> rechercherParGenreCatalogue(Genre genre);
	public List<ElementCatalogue> rechercherParDateSortieCatalogue(Date date);
	public List<ElementCatalogue> parcourirCatalogueCatalogue();
	
	// Parcourir Album
	public List<Album> rechercherParInterpreteAlbum(String recherche);
	public List<Album> rechercherParGenreAlbum(Genre genre);
	public List<Album> rechercherParDateSortieAlbum(Date date);
	public List<Album> parcourirCatalogueAlbum();
}

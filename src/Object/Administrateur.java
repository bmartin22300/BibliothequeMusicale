package Object;

import java.sql.Date;
import java.util.List;

import Interface.AdministrateurInterface;

public class Administrateur implements AdministrateurInterface {
	private String mail;
	private String password;

	// Constructeur
	public Administrateur(String mail, String password) {
		super();
		this.mail = mail;
		this.password = password;
	}
	
	// Getters et Setters
	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public Administrateur creerAdmin(String mail, String password) {
		// TODO Auto-generated method stub
		return null;
	}
	
	// Méthodes de classe
	public abstract Administrateur authentification(String mail, String password);
	
	public abstract Administrateur creerAdmin(String mail, String password);

	@Override
	public List<TitreMusical> topTitresEcoutes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Client> topUtilisateursEcoutes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Client> rechercherParMailClient(String recherche) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Client> rechercherParNomClient(String recherche) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Client> rechercherParPrenomClient(String recherche) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ElementCatalogue> rechercherParNomCatalogue(String recherche) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ElementCatalogue> rechercherParInterpreteCatalogue(String recherche) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ElementCatalogue> rechercherParGenreCatalogue(Genre genre) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ElementCatalogue> rechercherParDateSortieCatalogue(Date date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ElementCatalogue> parcourirCatalogueCatalogue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TitreMusical> rechercherParNomTitre(String recherche) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TitreMusical> rechercherParInterpreteTitre(String recherche) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TitreMusical> rechercherParGenreTitre(Genre genre) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TitreMusical> rechercherParDateSortieTitre(Date date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TitreMusical> parcourirCatalogueTitre() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Album> rechercherParNomAlbum(String recherche) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Album> rechercherParInterpreteAlbum(String recherche) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Album> rechercherParGenreAlbum(Genre genre) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Album> rechercherParDateSortieAlbum(Date date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Album> parcourirCatalogueAlbum() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Interprete> rechercherParPseudoInterprete(String recherche) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Interprete> rechercherParPrenomInterprete(String recherche) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Interprete> rechercherParNomInterprete(String recherche) {
		// TODO Auto-generated method stub
		return null;
	}
}

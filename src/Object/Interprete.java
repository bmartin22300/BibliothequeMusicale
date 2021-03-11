package Object;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Interprete {

	private int id;
	private String pseudonyme;
	private String prenom;
	private String nom;
	private Date dateNaissance;
	private List<Album> albums;
	private List<TitreMusical> titres;

	// Constructeur
	public Interprete(String pseudonyme, String prenom, String nom, Date dateNaissance) {
		super();
		this.pseudonyme = pseudonyme;
		this.prenom = prenom;
		this.nom = nom;
		this.dateNaissance = dateNaissance;
		this.albums = new ArrayList<Album>();
		this.titres = new ArrayList<TitreMusical>();
	}
	
	public Interprete(String pseudo) {
		this.pseudonyme=pseudo;
	}

	// Getters et Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getPseudonyme() {
		return pseudonyme;
	}

	public void setPseudonyme(String pseudonyme) {
		this.pseudonyme = pseudonyme;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Date getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}
	
	public List<Album> getAlbums() {
		return albums;
	}


	public void setAlbums(List<Album> albums) {
		this.albums = albums;
	}


	public List<TitreMusical> getTitres() {
		return titres;
	}


	public void setTitres(List<TitreMusical> titres) {
		this.titres = titres;
	}
	
	public void addTitre(TitreMusical titre) {
		this.titres.add(titre);
	}
	
	@Override
	public String toString() {
		return "Interprete [pseudonyme=" + pseudonyme + ", prenom=" + prenom + ", nom=" + nom + ", dateNaissance="
				+ dateNaissance + "]";
	}
}

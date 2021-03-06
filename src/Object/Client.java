package Object;

import java.util.Date;
import java.util.List;

public class Client {
	private String mail;
	private String password;
	private String civilite;
	private String nom;
	private String prenom;
	private Date dateNaissance;
	private String adresseFacturation;
	private int nbEcoute;
	private List<Playlist> playlists;
	private Genre styleMusiquePrefere;
	
	// Constructeur
	public Client(String mail, String password, String civilite, String nom, String prenom, Date dateNaissance,
			String adresseFacturation, int nbEcoute, List<Playlist> playlists, Genre styleMusiquePrefere) {
		super();
		this.mail = mail;
		this.password = password;
		this.civilite = civilite;
		this.nom = nom;
		this.prenom = prenom;
		this.dateNaissance = dateNaissance;
		this.adresseFacturation = adresseFacturation;
		this.nbEcoute = nbEcoute;
		this.playlists = playlists;
		this.styleMusiquePrefere = styleMusiquePrefere;
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

	public String getCivilite() {
		return civilite;
	}

	public void setCivilite(String civilite) {
		this.civilite = civilite;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public Date getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public String getAdresseFacturation() {
		return adresseFacturation;
	}

	public void setAdresseFacturation(String adresseFacturation) {
		this.adresseFacturation = adresseFacturation;
	}

	public int getNbEcoute() {
		return nbEcoute;
	}

	public void setNbEcoute(int nbEcoute) {
		this.nbEcoute = nbEcoute;
	}

	public List<Playlist> getPlaylists() {
		return playlists;
	}

	public void setPlaylists(List<Playlist> playlists) {
		this.playlists = playlists;
	}

	public Genre getStyleMusiquePrefere() {
		return styleMusiquePrefere;
	}

	public void setStyleMusiquePrefere(Genre styleMusiquePrefere) {
		this.styleMusiquePrefere = styleMusiquePrefere;
	}
	
	// Methodes de classe
	/*
	public boolean authentification(String email, String motDePasse) {
		return true;
	}
	
	public void playOrPause(ElementCatalogue elementCatalogue) {
		
	}
	
	public void ajouterAUnePlaylist(ElementCatalogue elementCatalogue, Playlist playlist) {
		
	}
	
	public void modifierProfil() {
		
	}
	
	public void rechercher(String nomElementCatalogue) {
		
	}
	
	public void cr�erPlaylist(String nomPlaylist) {
		
	}
	
	public void modifierPlaylist(Playlist playlist) {
		
	}
	
	public void supprimerPlaylist(Playlist playlist) {
		
	}
	
	public void chercherPlaylist(String nomPlaylist) {
		
	}
	
	public void selection(ElementCatalogue nomElementCatalogue) {
		
	}*/

}

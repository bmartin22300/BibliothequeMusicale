package Object;

import java.sql.Date;
import java.util.List;

import Interface.ClientInterface;

public class Client implements ClientInterface {
	private String mail;
	private String password;
	private String civilite;
	private String nom;
	private String prenom;
	private Date dateNaissance;
	private String adresseFacturation;
	private int nbEcoute;
	private Genre styleMusiquePrefere;
	private List<Playlist> playlists;
	
	
	// Constructeurs
	public Client(String mail, String password, String civilite, String nom, String prenom, Date dateNaissance,
			String adresseFacturation, Genre styleMusiquePrefere) {
		super();
		this.mail = mail;
		this.password = password;
		this.civilite = civilite;
		this.nom = nom;
		this.prenom = prenom;
		this.dateNaissance = dateNaissance;
		this.adresseFacturation = adresseFacturation;
		this.nbEcoute = 0;
		this.styleMusiquePrefere = styleMusiquePrefere;
	}

	public Client(String mail, String password) {
		super();
		this.mail = mail;
		this.password = password;
	}

	public Client(String mail, String password, String civilite, String nom, String prenom, Date dateNaissance,
			String adresseFacturation, int nbEcoute, Genre styleMusiquePrefere, List<Playlist> playlists) {
		super();
		this.mail = mail;
		this.password = password;
		this.civilite = civilite;
		this.nom = nom;
		this.prenom = prenom;
		this.dateNaissance = dateNaissance;
		this.adresseFacturation = adresseFacturation;
		this.nbEcoute = nbEcoute;
		this.styleMusiquePrefere = styleMusiquePrefere;
		this.playlists = playlists;
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

	@Override
	public void regarderElementCatalogue(ElementCatalogue elementCatalogue) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ElementCatalogue> rechercherParNom(String nom) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ElementCatalogue> rechercherParInterprete(String interprete) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ElementCatalogue> rechercherParGenre(Genre genre) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ElementCatalogue> rechercherParDateSortie(Date date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ElementCatalogue> parcourirCatalogue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean creerPlaylist(String nomPlaylist, List<ElementCatalogue> elementsCatalogue) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean supprimerPlaylist(Playlist playlist) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean modifierPlaylist(Playlist playlist, List<ElementCatalogue> elementsCatalogue) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean changerNomPlaylist(Playlist playlist, String nom) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retirerDePlaylist(ElementCatalogue elementCatalogue, Playlist playlist) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean ajouterAPlaylist(ElementCatalogue elementCatalogue, Playlist playlist) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean modifierInformationsPerso(String password, String civilite, String nom, String prenom,
			Date dateNaissance, String adresseFacturation, Genre styleMusiquePrefere) {
		// TODO Auto-generated method stub
		return false;
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

package Object;

import java.util.Date;
import java.util.List;

public class Client {
	private String civilit�;
	private String nom;
	private String pr�nom;
	private String email;
	private String mdp;
	private String adresse;
	private Date dateNaissance;
	private int nbEcoute;
	private List<Playlist> playlists;
	private List<Genre> genres;
	
	public Client(String civilit�, String nom, String pr�nom, String email, String mdp, String adresse,
			Date dateNaissance, int nbEcoute, List<Playlist> playlists, List<Genre> genres) {
		super();
		this.civilit� = civilit�;
		this.nom = nom;
		this.pr�nom = pr�nom;
		this.email = email;
		this.mdp = mdp;
		this.adresse = adresse;
		this.dateNaissance = dateNaissance;
		this.nbEcoute = nbEcoute;
		this.playlists = playlists;
		this.genres = genres;
	}
	
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
		
	}

	public String getCivilit�() {
		return civilit�;
	}

	public void setCivilit�(String civilit�) {
		this.civilit� = civilit�;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPr�nom() {
		return pr�nom;
	}

	public void setPr�nom(String pr�nom) {
		this.pr�nom = pr�nom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public Date getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
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

	public List<Genre> getGenres() {
		return genres;
	}

	public void setGenres(List<Genre> genres) {
		this.genres = genres;
	}
}

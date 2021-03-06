package Object;

import java.util.ArrayList;
import java.util.List;

public abstract class ElementCatalogue {
	private int idCatalogue;
	private int nbEcoute;
	private int nbEcouteMois;
	private boolean recommande;
	private List<Playlist> playlists;

	// Constructeur
	public ElementCatalogue(int idCatalogue) {
		super();
		this.idCatalogue = idCatalogue;
		this.recommande = false;
		this.nbEcoute = 0;
		this.nbEcouteMois = 0;
		this.playlists = new ArrayList<Playlist>();
	}

	// Getters et Setters	
	public int getNbEcouteMois() {
		return nbEcouteMois;
	}

	public void setNbEcouteMois(int nbEcouteMois) {
		this.nbEcouteMois = nbEcouteMois;
	}

	public boolean isRecommande() {
		return recommande;
	}

	public void setRecommande(boolean recommande) {
		this.recommande = recommande;
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

	public int getIdCatalogue() {
		return idCatalogue;
	}

	public void setIdCatalogue(int idCatalogue) {
		this.idCatalogue = idCatalogue;
	}
	
	public void regarder() {
		this.nbEcoute+=1;
		this.nbEcouteMois+=1;
	}
	
}

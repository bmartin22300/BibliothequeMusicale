package Object;

import java.util.List;

public abstract class ElementCatalogue {
	private int idCatalogue;
	private boolean recommande;
	private int nbEcoute;
	private List<Playlist> playlists;

	// Constructeur
	public ElementCatalogue(boolean recommande, int nbEcoute) {
		super();
		this.recommande = recommande;
		this.nbEcoute = nbEcoute;
	}

	// Getters et Setters
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
	
	
	
/*
	public void modifierElement() {
		
	}

	public boolean isRecommand�() {
		return recommand�;
	}*/
	
}

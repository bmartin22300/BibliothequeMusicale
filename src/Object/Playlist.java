package Object;

import java.util.List;

public class Playlist {
	private int idPlaylist;
	private String nomPlaylist;
	private Client createur;
	private List<ElementCatalogue> elementsCatalogue;
	
	// Constructeurs
	public Playlist(int idPlaylist, String nomPlaylist, Client createur, List<ElementCatalogue> elementsCatalogue) {
		super();
		this.idPlaylist = idPlaylist;
		this.nomPlaylist = nomPlaylist;
		this.createur = createur;
		this.elementsCatalogue = elementsCatalogue;
	}
	
	// Getters et Setters

	public int getIdPlaylist() {
		return idPlaylist;
	}

	public void setIdPlaylist(int idPlaylist) {
		this.idPlaylist = idPlaylist;
	}

	public String getNomPlaylist() {
		return nomPlaylist;
	}

	public void setNomPlaylist(String nomPlaylist) {
		this.nomPlaylist = nomPlaylist;
	}

	public Client getCreateur() {
		return createur;
	}

	public void setCreateur(Client createur) {
		this.createur = createur;
	}

	public List<ElementCatalogue> getElementsCatalogue() {
		return elementsCatalogue;
	}

	public void setElementsCatalogue(List<ElementCatalogue> elementsCatalogue) {
		this.elementsCatalogue = elementsCatalogue;
	}


	
}

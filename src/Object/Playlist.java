package Object;

import java.util.List;

public class Playlist {
	private String nomPlaylist;
	private List<ElementCatalogue> elementsCatalogue;
	
	// Constructeurs
	public Playlist(String nomPlaylist, List<ElementCatalogue> elementsCatalogue, List<ElementCatalogue> get) {
		super();
		this.nomPlaylist = nomPlaylist;
		this.elementsCatalogue = elementsCatalogue;
	}

	// Getters et Setters
	public String getNomPlaylist() {
		return nomPlaylist;
	}

	public void setNomPlaylist(String nomPlaylist) {
		this.nomPlaylist = nomPlaylist;
	}

	public List<ElementCatalogue> getElementsCatalogue() {
		return elementsCatalogue;
	}

	public void setElementsCatalogue(List<ElementCatalogue> elementsCatalogue) {
		this.elementsCatalogue = elementsCatalogue;
	}
	
}

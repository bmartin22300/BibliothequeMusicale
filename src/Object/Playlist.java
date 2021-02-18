package Object;

import java.util.List;

public class Playlist extends ElementCatalogue{
	private String nom;
	private List<ElementCatalogue> éléments;
	
	public Playlist(boolean recommandé, String nom, List<ElementCatalogue> éléments) {
		super(recommandé);
		this.nom = nom;
		this.éléments = éléments;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public List<ElementCatalogue> getÉléments() {
		return éléments;
	}

	public void setÉléments(List<ElementCatalogue> éléments) {
		this.éléments = éléments;
	}
}

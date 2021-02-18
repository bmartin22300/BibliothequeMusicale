package Object;

import java.util.List;

public class Playlist extends ElementCatalogue{
	private String nom;
	private List<ElementCatalogue> �l�ments;
	
	public Playlist(boolean recommand�, String nom, List<ElementCatalogue> �l�ments) {
		super(recommand�);
		this.nom = nom;
		this.�l�ments = �l�ments;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public List<ElementCatalogue> get�l�ments() {
		return �l�ments;
	}

	public void set�l�ments(List<ElementCatalogue> �l�ments) {
		this.�l�ments = �l�ments;
	}
}

package Object;

import java.util.List;

public class Radio extends ElementCatalogue{
	private String nom;
	private List<Genre> genres;
	
	public Radio(boolean recommandé, String nom, List<Genre> genres) {
		super(recommandé);
		this.nom=nom;
		this.genres=genres;
	}
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public List<Genre> getGenres() {
		return genres;
	}
	public void setGenres(List<Genre> genres) {
		this.genres = genres;
	}
}

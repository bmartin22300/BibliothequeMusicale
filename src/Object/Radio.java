package Object;

import java.util.List;

public class Radio extends ElementCatalogue{
	private String nom;
	private Genre genre;
	
	// Constructeur
	public Radio(boolean recommande, int nbEcoute, String nom, Genre genre) {
		super(recommande, nbEcoute);
		this.nom = nom;
		this.genre = genre;
	}
	
	// Getters et Setters
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}
	
	
}

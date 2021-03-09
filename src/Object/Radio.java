package Object;

public class Radio extends ElementCatalogue{
	private String nom;
	private Genre genre;
	
	// Constructeur
	public Radio(int idCatalogue, String nom, Genre genre) {
		super(idCatalogue);
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

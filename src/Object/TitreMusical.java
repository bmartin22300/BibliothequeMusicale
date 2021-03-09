package Object;

import java.time.Year;
import java.util.List;

public class TitreMusical extends ElementCatalogue{
	private String titre;
	private Year anneeCreation;
	private int duree;
	private Genre genre;
	private Album album;
	private List<Interprete> interpretes;
	
	// Constructeur
	public TitreMusical(int idCatalogue, String titre, Year anneeCreation, int duree, Genre genre,
			List<Interprete> interpretes) {
		super(idCatalogue);
		this.titre = titre;
		this.anneeCreation = anneeCreation;
		this.duree = duree;
		this.genre = genre;
		this.interpretes = interpretes;
	}

	public Album getAlbum() {
		return album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}

	// Getters et Setters
	public String getTitre() {
		return titre;
	}
	
	public void setTitre(String titre) {
		this.titre = titre;
	}

	public List<Interprete> getInterprete() {
		return interpretes;
	}

	public void setInterprete(List<Interprete> interpretes) {
		this.interpretes = interpretes;
	}


	public List<Interprete> getInterpretes() {
		return interpretes;
	}

	public void setInterpretes(List<Interprete> interpretes) {
		this.interpretes = interpretes;
	}

	public Year getAnneeCreation() {
		return anneeCreation;
	}

	public void setAnneeCreation(Year anneeCreation) {
		this.anneeCreation = anneeCreation;
	}

	public int getDuree() {
		return duree;
	}

	public void setDuree(int duree) {
		this.duree = duree;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

}

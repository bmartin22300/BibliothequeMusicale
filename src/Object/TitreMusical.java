package Object;

import java.sql.Date;
import java.time.Year;
import java.util.List;

public class TitreMusical extends ElementCatalogue{
	private String titre;
	private List<Interprete> interpretes;
	private Year anneeCreation;
	private int duree;
	private Genre genre;
	
	// Constructeur
	public TitreMusical(int idCatalogue, String titre, List<Interprete> interpretes, Year anneeCreation, int duree,
			Genre genre) {
		super(idCatalogue);
		this.titre = titre;
		this.interpretes = interpretes;
		this.anneeCreation = anneeCreation;
		this.duree = duree;
		this.genre = genre;
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

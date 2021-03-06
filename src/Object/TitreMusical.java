package Object;

import java.sql.Date;
import java.util.List;

public class TitreMusical extends ElementCatalogue{
	private String titre;
	private List<Interprete> interpretes;
	private Date dateCreation;
	private int duree;
	private Genre genre;
	
	// Constructeur
	public TitreMusical(boolean recommande, int nbEcoute, String titre, List<Interprete> interpretes, Date dateCreation,
			int duree, Genre genre) {
		super(recommande, nbEcoute);
		this.titre = titre;
		this.interpretes = interpretes;
		this.dateCreation = dateCreation;
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

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
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

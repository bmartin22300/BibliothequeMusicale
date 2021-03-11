package Object;

import java.util.List;

public class TitreMusical extends ElementCatalogue{
	private String titre;
	private int anneeCreation;
	private int duree;
	private Genre genre;
	private Album album;
	private List<Interprete> interpretes;
	
	// Constructeur
	public TitreMusical(int idCatalogue, String titre, int anneeCreation, int duree, Genre genre,
			List<Interprete> interpretes) {
		super(idCatalogue);
		this.titre = titre;
		this.anneeCreation = anneeCreation;
		this.duree = duree;
		this.genre = genre;
		this.interpretes = interpretes;
	}

	public TitreMusical(int idCatalogue, String titre, int anneeCreation, int duree, Genre genre, Album album,
			List<Interprete> interpretes) {
		super(idCatalogue);
		this.titre = titre;
		this.anneeCreation = anneeCreation;
		this.duree = duree;
		this.genre = genre;
		this.album = album;
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

	public int getAnneeCreation() {
		return anneeCreation;
	}

	public void setAnneeCreation(int anneeCreation) {
		this.anneeCreation = anneeCreation;
	}

	public int getDuree() {
		return duree;
	}

	public void setDuree(int duree) {
		if(this.album!=null) {
			this.album.setDuree(this.album.getDuree()+duree-this.duree);
		}
		this.duree = duree;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}
	

	@Override
	public String toString() {
		return "TitreMusical [titre=" + titre + ", anneeCreation=" + anneeCreation + ", duree=" + duree + ", genre="
				+ genre + "]";
	}

}

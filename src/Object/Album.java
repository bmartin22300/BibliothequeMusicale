package Object;

import java.sql.Date;
import java.util.List;

public class Album extends ElementCatalogue{
	private String nom;
	private List<Interprete> interpretes;
	private Date dateSortie;
	private int duree;
	private List<TitreMusical> titres;
	
	// Constructeur
	public Album(int idCatalogue, String nom, List<Interprete> interpretes, Date dateSortie, int duree,
			List<TitreMusical> titres) {
		super(idCatalogue);
		this.nom = nom;
		this.interpretes = interpretes;
		this.dateSortie = dateSortie;
		this.duree = duree;
		this.titres = titres;
	}
	
	// Getters et Setters
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public List<Interprete> getInterprete() {
		return interpretes;
	}

	public void setInterprete(List<Interprete> interpretes) {
		this.interpretes = interpretes;
	}

	public Date getDateSortie() {
		return dateSortie;
	}

	public void setDateSortie(Date dateSortie) {
		this.dateSortie = dateSortie;
	}

	public int getDuree() {
		return duree;
	}

	public void setDuree(int duree) {
		this.duree = duree;
	}

	public List<TitreMusical> getTitres() {
		return titres;
	}

	public void setTitres(List<TitreMusical> titres) {
		this.titres = titres;
	}
	
	// Méthodes de classe
	/*
	public void ajouterTitre(ElementCatalogue elementMusical) {
		
	}
	
	public void supprimerTitre(ElementCatalogue elementMusical) {
		
	}*/

	
}

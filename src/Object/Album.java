package Object;

import java.util.List;

public class Album extends ElementCatalogue{

	private String nom;
	private int anneeSortie;
	private int duree;
	private List<Interprete> interpretes;
	private List<TitreMusical> titres;
	
	// Constructeur
	public Album(int idCatalogue, String nom, int duree, int anneeSortie, List<Interprete> interpretes,
			List<TitreMusical> titres) {
		super(idCatalogue);
		this.nom = nom;
		this.anneeSortie = anneeSortie;
		this.duree = duree;
		this.interpretes = interpretes;
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

	public int getDateSortie() {
		return anneeSortie;
	}

	public void setDateSortie(int anneeSortie) {
		this.anneeSortie = anneeSortie;
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

	@Override
	public String toString() {
		return "Album [nom=" + nom + ", anneeSortie=" + anneeSortie + ", duree=" + duree + ", interpretes="
				+ interpretes + ", titres=" + titres + "]";
	}
}

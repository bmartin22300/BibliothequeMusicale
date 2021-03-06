package Object;

import java.util.ArrayList;
import java.util.List;

public class Album extends ElementCatalogue{

	private String nom;
	private int anneeSortie;
	private int duree;
	private List<TitreMusical> titres;
	
	// Constructeur
	public Album(int idCatalogue, String nom, int duree, int anneeSortie,
			List<TitreMusical> titres) {
		super(idCatalogue);
		this.nom = nom;
		this.anneeSortie = anneeSortie;
		this.duree = duree;
		if(titres == null) {
			titres = new ArrayList<TitreMusical>();
		}else {
			this.titres = titres;
		}
	}
	
	public Album(int idCatalogue) {
		super(idCatalogue);
	}


	
	// Getters et Setters
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
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
	
	public void ajouterTitre(TitreMusical titre) {
		if(this.titres==null) {
			this.titres = new ArrayList<TitreMusical>();
			this.titres.add(titre);
		}
		else {
			this.titres.add(titre);
		}
	}
	
	public void retirerTitre(TitreMusical titre) {
		if(this.titres!=null) {
			this.titres.remove(titre);
		}
	}

	@Override
	public String toString() {
		return "Album [nom=" + nom + ", anneeSortie=" + anneeSortie + ", duree=" + duree + ", interpretes="
				+ ", titres=" + titres + "]";
	}
}

package Object;

import java.util.List;

public class Album extends ElementCatalogue{
	private String nom;
	private String interprète;
	private int durée;
	private int annéeCréation;
	private List<TitreMusical> titres;
	
	public Album(boolean recommandé, String nom, String interprète, int durée, int annéeCréation,
			List<TitreMusical> titres) {
		super(recommandé);
		this.nom = nom;
		this.interprète = interprète;
		this.durée = durée;
		this.annéeCréation = annéeCréation;
		this.titres = titres;
	}
	
	public void ajouterTitre(ElementCatalogue elementMusical) {
		
	}
	
	public void supprimerTitre(ElementCatalogue elementMusical) {
		
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getInterprète() {
		return interprète;
	}

	public void setInterprète(String interprète) {
		this.interprète = interprète;
	}

	public int getDurée() {
		return durée;
	}

	public void setDurée(int durée) {
		this.durée = durée;
	}

	public int getAnnéeCréation() {
		return annéeCréation;
	}

	public void setAnnéeCréation(int annéeCréation) {
		this.annéeCréation = annéeCréation;
	}

	public List<TitreMusical> getTitres() {
		return titres;
	}

	public void setTitres(List<TitreMusical> titres) {
		this.titres = titres;
	}
}

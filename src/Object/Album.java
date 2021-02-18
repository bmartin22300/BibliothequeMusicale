package Object;

import java.util.List;

public class Album extends ElementCatalogue{
	private String nom;
	private String interpr�te;
	private int dur�e;
	private int ann�eCr�ation;
	private List<TitreMusical> titres;
	
	public Album(boolean recommand�, String nom, String interpr�te, int dur�e, int ann�eCr�ation,
			List<TitreMusical> titres) {
		super(recommand�);
		this.nom = nom;
		this.interpr�te = interpr�te;
		this.dur�e = dur�e;
		this.ann�eCr�ation = ann�eCr�ation;
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

	public String getInterpr�te() {
		return interpr�te;
	}

	public void setInterpr�te(String interpr�te) {
		this.interpr�te = interpr�te;
	}

	public int getDur�e() {
		return dur�e;
	}

	public void setDur�e(int dur�e) {
		this.dur�e = dur�e;
	}

	public int getAnn�eCr�ation() {
		return ann�eCr�ation;
	}

	public void setAnn�eCr�ation(int ann�eCr�ation) {
		this.ann�eCr�ation = ann�eCr�ation;
	}

	public List<TitreMusical> getTitres() {
		return titres;
	}

	public void setTitres(List<TitreMusical> titres) {
		this.titres = titres;
	}
}

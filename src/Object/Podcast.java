package Object;

public class Podcast extends ElementCatalogue {
	private String titre;
	private String duree;
	private String pseudoAuteur;
	private String categorie;
	
	// Constructeur
	public Podcast(int idCatalogue, boolean recommande, String titre, String duree, String pseudoAuteur,
			String categorie) {
		super(idCatalogue);
		this.titre = titre;
		this.duree = duree;
		this.pseudoAuteur = pseudoAuteur;
		this.categorie = categorie;
	}

	// Getters et Setters
	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getDuree() {
		return duree;
	}

	public void setDuree(String duree) {
		this.duree = duree;
	}

	public String getPseudoAuteur() {
		return pseudoAuteur;
	}

	public void setPseudoAuteur(String pseudoAuteur) {
		this.pseudoAuteur = pseudoAuteur;
	}

	public String getCategorie() {
		return categorie;
	}

	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}	
}

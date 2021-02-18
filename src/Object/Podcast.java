package Object;

public class Podcast extends ElementCatalogue {
	private String titre;
	private String interprète;
	private String catégorie;
	private int durée;

	public Podcast(boolean recommandé, String titre, String interprète, String catégorie, int durée) {
		super(recommandé);
		this.titre = titre;
		this.interprète = interprète;
		this.catégorie = catégorie;
		this.durée = durée;
	}



	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getInterprète() {
		return interprète;
	}

	public void setInterprète(String interprète) {
		this.interprète = interprète;
	}

	public String getCatégorie() {
		return catégorie;
	}

	public void setCatégorie(String catégorie) {
		this.catégorie = catégorie;
	}

	public int getDurée() {
		return durée;
	}

	public void setDurée(int durée) {
		this.durée = durée;
	}
}

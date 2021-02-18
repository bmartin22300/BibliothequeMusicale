package Object;

public class TitreMusical extends ElementCatalogue{
	private String titre;
	private String interprète;
	private int durée;
	private int annéeCréation;
	private int nbEcouteMensuel;
	
	public TitreMusical(boolean recommandé, String titre, String interprète, int durée, int annéeCréation,
			int nbEcouteMensuel) {
		super(recommandé);
		this.titre = titre;
		this.interprète = interprète;
		this.durée = durée;
		this.annéeCréation = annéeCréation;
		this.nbEcouteMensuel = nbEcouteMensuel;
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

	public int getNbEcouteMensuel() {
		return nbEcouteMensuel;
	}

	public void setNbEcouteMensuel(int nbEcouteMensuel) {
		this.nbEcouteMensuel = nbEcouteMensuel;
	}
	
	
}

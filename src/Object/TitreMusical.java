package Object;

public class TitreMusical extends ElementCatalogue{
	private String titre;
	private String interpr�te;
	private int dur�e;
	private int ann�eCr�ation;
	private int nbEcouteMensuel;
	
	public TitreMusical(boolean recommand�, String titre, String interpr�te, int dur�e, int ann�eCr�ation,
			int nbEcouteMensuel) {
		super(recommand�);
		this.titre = titre;
		this.interpr�te = interpr�te;
		this.dur�e = dur�e;
		this.ann�eCr�ation = ann�eCr�ation;
		this.nbEcouteMensuel = nbEcouteMensuel;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
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

	public int getNbEcouteMensuel() {
		return nbEcouteMensuel;
	}

	public void setNbEcouteMensuel(int nbEcouteMensuel) {
		this.nbEcouteMensuel = nbEcouteMensuel;
	}
	
	
}

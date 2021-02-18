package Object;

public class Podcast extends ElementCatalogue {
	private String titre;
	private String interpr�te;
	private String cat�gorie;
	private int dur�e;

	public Podcast(boolean recommand�, String titre, String interpr�te, String cat�gorie, int dur�e) {
		super(recommand�);
		this.titre = titre;
		this.interpr�te = interpr�te;
		this.cat�gorie = cat�gorie;
		this.dur�e = dur�e;
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

	public String getCat�gorie() {
		return cat�gorie;
	}

	public void setCat�gorie(String cat�gorie) {
		this.cat�gorie = cat�gorie;
	}

	public int getDur�e() {
		return dur�e;
	}

	public void setDur�e(int dur�e) {
		this.dur�e = dur�e;
	}
}

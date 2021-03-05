package Object;

public class Authentification {
	private String pseudonyme;
	private String motDePasse;
	private boolean isClient;
	private boolean isAdministrateur;
	
	public Authentification() {
		super();
		this.pseudonyme = "a";
		this.motDePasse = "b";
		this.isClient = false;
		this.isAdministrateur = false;
	}
	
	public Authentification(String pseudonyme, String motDePasse, boolean isClient, boolean isAdministrateur) {
		super();
		this.pseudonyme = pseudonyme;
		this.motDePasse = motDePasse;
		this.isClient = isClient;
		this.isAdministrateur = isAdministrateur;
	}
	
	public String getPseudonyme() {
		return pseudonyme;
	}
	public void setPseudonyme(String pseudonyme) {
		this.pseudonyme = pseudonyme;
	}
	public String getMotDePasse() {
		return motDePasse;
	}
	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}
	public boolean isClient() {
		return isClient;
	}
	public void setClient(boolean isClient) {
		this.isClient = isClient;
	}
	public boolean isAdministrateur() {
		return isAdministrateur;
	}
	public void setAdministrateur(boolean isAdministrateur) {
		this.isAdministrateur = isAdministrateur;
	}
	
}

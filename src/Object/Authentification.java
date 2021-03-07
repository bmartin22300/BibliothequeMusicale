package Object;

public class Authentification {
	private String mail;
	private String motDePasse;
	private boolean isClient;
	private boolean isAdministrateur;
	
	public Authentification() {
		super();
		this.mail = "a";
		this.motDePasse = "b";
		this.isClient = false;
		this.isAdministrateur = false;
	}
	
	public Authentification(String mail, String motDePasse, boolean isClient, boolean isAdministrateur) {
		super();
		this.mail = mail;
		this.motDePasse = motDePasse;
		this.isClient = isClient;
		this.isAdministrateur = isAdministrateur;
	}
	
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
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

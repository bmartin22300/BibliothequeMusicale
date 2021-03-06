package Object;

public class Administrateur {
	private String mail;
	private String password;
	private boolean profilGestionClient;
	private boolean profilGestionMusique;

	// Constructeur
	public Administrateur(String mail, String password, boolean profilGestionClient, boolean profilGestionMusique) {
		super();
		this.mail = mail;
		this.password = password;
		this.profilGestionClient = profilGestionClient;
		this.profilGestionMusique = profilGestionMusique;
	}
	
	// Getters et Setters
	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isProfilGestionClient() {
		return profilGestionClient;
	}

	public void setProfilGestionClient(boolean profilGestionClient) {
		this.profilGestionClient = profilGestionClient;
	}

	public boolean isProfilGestionMusique() {
		return profilGestionMusique;
	}

	public void setProfilGestionMusique(boolean profilGestionMusique) {
		this.profilGestionMusique = profilGestionMusique;
	}	
	
	
	// Méthodes de classe
	
	
	/*
	public void consulterStatistiques() {
		
	}
	
	public boolean authentification(String email, String motDePasse) {
		return true;
	}
	
	public void rechercherElement(String nomElementCatalogue) {
		
	}
	
	public void rechercherClient(String nom, String prenom) {
		
	}
	
	public void modifierProfilClient() {
		
	}
	
	public Client selectionerClient(Client client) {
		return client;
	}
	
	public ElementCatalogue selectionerElementMusical(ElementCatalogue elementCatalogue) {
		return elementCatalogue;
	}*/
	

}

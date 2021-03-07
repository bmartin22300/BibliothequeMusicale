package Object;

import Interface.AdministrateurInterface;

public abstract class Administrateur implements AdministrateurInterface {
	private String mail;
	private String password;

	// Constructeur
	public Administrateur(String mail, String password) {
		super();
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
	
	// Méthodes de classe
	public abstract boolean authentification(String mail, String password);
	
	public abstract Administrateur creerAdmin(String mail, String password);
	
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

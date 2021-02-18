package Object;

public class Administrateur {
	private String email;
	private String mdp;
	
	public Administrateur(String email, String mdp) {
		super();
		this.email = email;
		this.mdp = mdp;
	}
	
	public void consulterStatistiques() {
		
	}
	
	public boolean authentification(String email, String motDePasse) {
		return true;
	}
	
	public void rechercherElement(String nomElementCatalogue) {
		
	}
	
	public void rechercherClient(String nom, String prénom) {
		
	}
	
	public void modifierProfilClient() {
		
	}
	
	public Client selectionerClient(Client client) {
		return client;
	}
	
	public ElementCatalogue selectionerElementMusical(ElementCatalogue elementCatalogue) {
		return elementCatalogue;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMdp() {
		return mdp;
	}
	public void setMdp(String mdp) {
		this.mdp = mdp;
	}
}

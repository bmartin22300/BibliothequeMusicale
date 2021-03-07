package Interface;

import Object.Client;
import Object.ElementCatalogue;

public interface UtilisateurInterface {
	
	public Client creerCompte(String mail, String password, String civilite, String nom, String prenom, String dateNaissance, String adresseFacturation, String styleMusiquePrefere);

	public Client creerCompte(String mail, String password);

	public Client authentification(String mail, String password);
	
	//TODO
	public void regarderElementCatalogue(ElementCatalogue elementCatalogue);
	
}

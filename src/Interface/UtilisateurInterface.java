package Interface;

import Object.ElementCatalogue;

public interface UtilisateurInterface {
	
	public void creerCompte(String mail, String password, String civilite, String nom, String prenom, String dateNaissance, String adresseFacturation, String styleMusiquePrefere);

	public void creerCompte(String mail, String password);

	public boolean authentification(String mail, String password);
	
	//TODO
	public void regarderElementCatalogue(ElementCatalogue elementCatalogue);
	
}

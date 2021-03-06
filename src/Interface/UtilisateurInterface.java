package Interface;

import java.util.Date;

import Object.Genre;

public interface UtilisateurInterface {
	
	public void creerCompte(String mail, String password, String civilite, String nom, String prenom, String dateNaissance, String adresseFacturation, String styleMusiquePrefere);
	
}

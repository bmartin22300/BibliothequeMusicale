package Interface;

import java.sql.Date;

import Object.Client;
import Object.Genre;

public interface ProfilGestionnaireClientInterface extends AdministrateurInterface {
	
	// Valide
	
	//A verifier - Tester
	
	//TODO
	
	public boolean modifierInformationsClient(Client client, String password, String civilite, String nom, String prenom, Date dateNaissance, String adresseFacturation, Genre styleMusiquePrefere);

}

package Interface;

import java.sql.Date;

import Object.Client;
import Object.Genre;
import Object.Interprete;

public interface ProfilGestionnaireClientInterface extends AdministrateurInterface {

	public boolean supprimerClient(Client client);
	
	public boolean modifierInformationsClient(Client client, String mail, String password, String civilite, String nom, String prenom, Date dateNaissance, String adresseFacturation, Genre styleMusiquePrefere);

}

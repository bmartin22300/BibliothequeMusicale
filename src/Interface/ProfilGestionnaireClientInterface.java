package Interface;

import java.sql.Date;

import Object.Client;
import Object.Genre;

public interface ProfilGestionnaireClientInterface extends AdministrateurInterface {
	
	//A verifier
	
	//TODO
	
	//Modifications utilisateur
	public boolean modifierNomClient(Client client, String nom);
	public boolean modifierPrenomClient(Client client, String prenom);
	public boolean modifierPasswordClient(Client client, String password);
	public boolean modifierCiviliteClient(Client client, String civilite);
	public boolean modifierDateNaissanceClient(Client client, Date dateNaissance);
	public boolean modifierAdresseClient(Client client, String adresse);
	public boolean modifierStyleClient(Client client, Genre style);

}

package Interface;

import java.sql.Date;
import java.util.List;

import Object.Administrateur;
import Object.Client;
import Object.ElementCatalogue;
import Object.Genre;

public interface UtilisateurInterface {

	// VALIDE
	
	// TESTE
	public Client creerCompte(String mail, String password, String civilite, String nom, String prenom, Date dateNaissance, String adresseFacturation, Genre styleMusiquePrefere);

	public Client creerCompte(String mail, String password);

	public Client authentification(String mail, String password);
	
	//A VERIFIER

	public Administrateur authentificationAdmin(String mail, String password);
	
	//TODO
	
	public void regarderElementCatalogue(ElementCatalogue elementCatalogue);
	
	public List<ElementCatalogue> morceauxPopulaires();
	
	public List<ElementCatalogue> recommandationsDuMoment();
	
}

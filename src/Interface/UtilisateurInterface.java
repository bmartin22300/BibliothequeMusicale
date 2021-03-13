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
	
	public Administrateur authentificationAdmin(String mail, String password);
	
	public boolean regarder(ElementCatalogue elementCatalogue);
	
	//A VERIFIER	

	
	//TODO
	

	
	public List<ElementCatalogue> morceauxPopulaires();
	
	public List<ElementCatalogue> recommandationsDuMoment();
	
}

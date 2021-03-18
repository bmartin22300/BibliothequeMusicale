package Interface;

import java.sql.Date;
import java.util.List;

import Object.Administrateur;
import Object.Client;
import Object.ElementCatalogue;
import Object.Genre;
import Object.TitreMusical;

public interface UtilisateurInterface {

	// VALIDE
	
	// TESTE
	public Client creerCompte(String mail, String password, String civilite, String nom, String prenom, Date dateNaissance, String adresseFacturation, Genre styleMusiquePrefere);

	public Client creerCompte(String mail, String password);

	public Client authentification(String mail, String password);
	
	public Administrateur authentificationAdmin(String mail, String password);
	
	public boolean regarder(ElementCatalogue elementCatalogue);
	
	public List<TitreMusical> topTitresEcoutes();
	
	public List<TitreMusical> recommandationsDuMoment();
	
	TitreMusical getTitreMusical(int idTitreMusical);
	//A VERIFIER	

	

	
	//TODO
	

	

	

	
}

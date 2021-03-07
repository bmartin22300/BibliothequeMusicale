package Object;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Interface.UtilisateurInterface;

public class Utilisateur implements UtilisateurInterface {
	
	
	
	/*public void ecouterMorceau(TitreMusical titreMusical) {
		
	}*/

	public Utilisateur() {
		super();
	}
	
	
	
	@Override
	public boolean authentification(String mail, String password) {
		// Récupérer une connexion de type java.sql.Connection
		Connection connexion = DBManager.getInstance().getConnection();
		
		try {
			// Exécuter la requête SQL et récupérer un java.sql.ResultSet
			String request = "CALL authentification_client(?, ?);";
			
			// Prepared statement 
			PreparedStatement preparedQuery = connexion.prepareStatement(request);
			preparedQuery.setString(1, mail);
			preparedQuery.setString(2, password);
			
			// Retour
			ResultSet rs = preparedQuery.executeQuery();
			
			// Vrai si les identifiants correspondent à un compte
			return rs.next();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;		
	}
	
	@Override
	public void creerCompte(String mail, String password) {
	
		//Statement statement=null;
		
		// Récupérer une connexion de type java.sql.Connection
		Connection connexion = DBManager.getInstance().getConnection();
		
		try {
			/*// Créer un java.sql.Statement depuis cette connexion
			statement = connexion.createStatement();
			
			// Exécuter la requête SQL et récupérer un java.sql.ResultSet
			String request = "call nouveau_client('"+ mail +"','"+ password+"');";
			statement.executeQuery(request);*/
			
			// Exécuter la requête SQL et récupérer un java.sql.ResultSet
			String request = "CALL nouveau_client(?, ?);";
			
			// Prepared statement 
			PreparedStatement preparedQuery = connexion.prepareStatement(request);
			preparedQuery.setString(1, mail);
			preparedQuery.setString(2, password);
			
			// Execution
			preparedQuery.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	@Override
	public void creerCompte(String mail, String password, String civilite, String nom, String prenom,
		String dateNaissance, String adresseFacturation, String styleMusiquePrefere) {
		
		// Récupérer une connexion de type java.sql.Connection
		Connection connexion = DBManager.getInstance().getConnection();
		
		try {
			
			// Exécuter la requête SQL et récupérer un java.sql.ResultSet
			String request = "CALL nouveau_client_complet(?, ?, ?, ?, ?, ?, ?, ?);";
			
			// Prepared statement 
			PreparedStatement preparedQuery = connexion.prepareStatement(request);
			preparedQuery.setString(1, mail);
			preparedQuery.setString(2, password);
			preparedQuery.setString(3, civilite);
			preparedQuery.setString(4, nom);
			preparedQuery.setString(5, prenom);
			preparedQuery.setString(6, dateNaissance);
			preparedQuery.setString(7, adresseFacturation);
			preparedQuery.setString(8, styleMusiquePrefere);
			
			// Execution
			preparedQuery.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void regarderElementCatalogue(ElementCatalogue elementCatalogue) {
	
		//TODO : pour utiliser "CALL regarder(idCatalogue_ INT)", besoin de connaître l'identifiant de l'élément du catalogue, ajout attribut que je donne à la création?
		
	}
}

package Object;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Interface.UtilisateurInterface;

public class Utilisateur implements UtilisateurInterface {
	
	
	
	/*public void ecouterMorceau(TitreMusical titreMusical) {
		
	}*/

	public Utilisateur() {
		super();
	}
	
	
	
	@Override
	public boolean authentification(String mail, String password) {
	
		Statement statement=null;
		
		// Récupérer une connexion de type java.sql.Connection
		Connection connexion = DBManager.getInstance().getConnection();
		
		try {
			// Créer un java.sql.Statement depuis cette connexion
			statement = connexion.createStatement();
			
			// Exécuter la requête SQL et récupérer un java.sql.ResultSet
			String request = "CALL authentification_client('"+mail+"', '"+password+"');";
			ResultSet rs=statement.executeQuery(request);
			return rs.next();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;		
	}
	
	@Override
	public void creerCompte(String mail, String password) {
	
		Statement statement=null;
		
		// Récupérer une connexion de type java.sql.Connection
		Connection connexion = DBManager.getInstance().getConnection();
		
		try {
			// Créer un java.sql.Statement depuis cette connexion
			statement = connexion.createStatement();
			
			// Exécuter la requête SQL et récupérer un java.sql.ResultSet
			String request = "call nouveau_client('"+ mail +"','"+ password+"');";
			statement.executeQuery(request);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	@Override
	public void creerCompte(String mail, String password, String civilite, String nom, String prenom,
		String dateNaissance, String adresseFacturation, String styleMusiquePrefere) {
	
		Statement statement=null;
		
		// Récupérer une connexion de type java.sql.Connection
		Connection connexion = DBManager.getInstance().getConnection();
		
		try {
			// Créer un java.sql.Statement depuis cette connexion
			statement = connexion.createStatement();
			
			// Exécuter la requête SQL et récupérer un java.sql.ResultSet
			String request = "call nouveau_client('"+ mail +"','"+ password +"','"+ civilite +"','"+ nom +"','"+ prenom +"','"+ dateNaissance +"','"+ adresseFacturation +"','"+ styleMusiquePrefere +"');";
			statement.executeQuery(request);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
}

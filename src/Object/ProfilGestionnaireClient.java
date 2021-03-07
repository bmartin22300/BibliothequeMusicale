package Object;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Interface.ProfilGestionnaireClientInterface;

public class ProfilGestionnaireClient extends Administrateur implements ProfilGestionnaireClientInterface{

	public ProfilGestionnaireClient(String mail, String password) {
		super(mail,password);
	}

	@Override
	public boolean authentification(String mail, String password) {
		// Récupérer une connexion de type java.sql.Connection
		Connection connexion = DBManager.getInstance().getConnection();
		
		try {
			// Exécuter la requête SQL et récupérer un java.sql.ResultSet
			String request = "CALL authentification_adminClient(?, ?);";
			
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
	public Administrateur creerAdmin(String mail, String password) {
		
		// Récupérer une connexion de type java.sql.Connection
		Connection connexion = DBManager.getInstance().getConnection();
		
		try {
			
			// Exécuter la requête SQL et récupérer un java.sql.ResultSet
			String request = "CALL nouveau_admin(?, ?, ?, ?);";
			
			// Prepared statement 
			PreparedStatement preparedQuery = connexion.prepareStatement(request);
			preparedQuery.setString(1, mail);
			preparedQuery.setString(2, password);
			preparedQuery.setBoolean(3, true); // Profil gestion Client
			preparedQuery.setBoolean(4, false); // Profil gestion Musique
			
			// Execution
			if(preparedQuery.executeUpdate()>0) {
				return new ProfilGestionnaireClient(mail, password);
			}
			else{
				return null;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;		
	}

	@Override
	public boolean modifierNomClient(Client client, String nom) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean modifierPrenomClient(Client client, String prenom) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean modifierPasswordClient(Client client, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean modifierCiviliteClient(Client client, String civilite) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean modifierDateNaissanceClient(Client client, Date dateNaissance) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean modifierAdresseClient(Client client, String adresse) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean modifierStyleClient(Client client, Genre style) {
		// TODO Auto-generated method stub
		return false;
	}
}

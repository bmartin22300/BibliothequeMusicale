package Object;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Interface.ProfilGestionnaireClientInterface;

public class ProfilGestionnaireClient extends Administrateur implements ProfilGestionnaireClientInterface{

	public ProfilGestionnaireClient(int id, String mail, String password) {
		super(id, mail,password);
	}
	
	
	/*
	 * Fonction authentification vérifie l'existence du couple mail, password ayant les droits de GestionnaireClient
	 * Renvoie l'objet ProfilGestionnaireClient correspondant s'il est trouvé, null sinon
	 */
	@Override
	public Administrateur authentification(String mail, String password) {
		// RÃ©cupÃ©rer une connexion de type java.sql.Connection
		Connection connexion = DBManager.getInstance().getConnection();
		
		try {
			// ExÃ©cuter la requÃªte SQL et rÃ©cupÃ©rer un java.sql.ResultSet
			String request = "CALL authentification_adminClient(?, ?);";
			
			// Prepared statement 
			PreparedStatement preparedQuery = connexion.prepareStatement(request);
			preparedQuery.setString(1, mail);
			preparedQuery.setString(2, password);
			
			// Retour
			ResultSet rs = preparedQuery.executeQuery();
			
			// Vrai si les identifiants correspondent Ã  un compte
			if(rs.next()) {
				return new ProfilGestionnaireClient(id, mail, password);
			};
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/*
	 * Fonction creerAdmin ajoute un administrateur ayant les droits de GestionnaireClient
	 * Renvoie l'objet ProfilGestionnaireClient correspondant si l'insertion à la BDD réussit, null sinon
	 */
	@Override
	public Administrateur creerAdmin(String mail, String password) {
		// On recupere une connexion de type java.sql.Connection
		Connection connexion = DBManager.getInstance().getConnection();
		
		try {
			
			String requestVerification = "CALL existe_adminClient(?);";
			PreparedStatement preparedVerification = connexion.prepareStatement(requestVerification);
			preparedVerification.setString(1, mail);
			ResultSet rsVerif = preparedVerification.executeQuery();
			if(rsVerif.next()) { // Le compte existe deja
				return null;
			}
			else { // On cree je compte
				
				
				// On execute la requete SQL et on recupere un java.sql.ResultSet
				String request = "SELECT nouveau_admin(?, ?, ?, ?);";
				
				// Prepared statement 
				PreparedStatement preparedQuery = connexion.prepareStatement(request);
				preparedQuery.setString(1, mail);
				preparedQuery.setString(2, password);
				preparedQuery.setBoolean(3, true); // Profil gestion Client
				preparedQuery.setBoolean(4, false); // Profil gestion Musique
				
				ResultSet rs = preparedQuery.executeQuery();

				if(rs.next()) // Creation de l'Admin
	            {
	                int last_inserted_id = rs.getInt(1); // Id de l'Admin cree
	                
	                return new ProfilGestionnaireClient(last_inserted_id, mail, password);
	            }
				else {
					return null;
				}
			}
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public boolean modifierInformationsClient(Client client, String password, String civilite, String nom,
			String prenom, Date dateNaissance, String adresseFacturation, Genre styleMusiquePrefere) {
		// TODO Auto-generated method stub
		return false;
	}
}

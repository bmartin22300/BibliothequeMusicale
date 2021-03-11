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
	 * Fonction authentification v�rifie l'existence du couple mail, password ayant les droits de GestionnaireClient
	 * Renvoie l'objet ProfilGestionnaireClient correspondant s'il est trouv�, null sinon
	 */
	@Override
	public Administrateur authentification(String mail, String password) {
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
			if(rs.next()) {
				return new ProfilGestionnaireClient((int) (Math.random()*1000), mail, password);
			};
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/*
	 * Fonction creerAdmin ajoute un administrateur ayant les droits de GestionnaireClient
	 * Renvoie l'objet ProfilGestionnaireClient correspondant si l'insertion � la BDD r�ussit, null sinon
	 */
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
				return new ProfilGestionnaireClient((int) (Math.random()*1000),mail, password);
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
	public boolean modifierInformationsClient(Client client, String password, String civilite, String nom,
			String prenom, Date dateNaissance, String adresseFacturation, Genre styleMusiquePrefere) {
		// TODO Auto-generated method stub
		return false;
	}
}

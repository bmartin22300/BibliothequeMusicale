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
	 * Fonction creerAdmin ajoute un administrateur ayant les droits de GestionnaireClient
	 * Renvoie l'objet ProfilGestionnaireClient correspondant si l'insertion ï¿½ la BDD rï¿½ussit, null sinon
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

				if(rs.next()) // Creation du Client
	            {
	                int last_inserted_id = rs.getInt(1); // Id du Client cree
	                
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
	
	/*
	 * Fonction modifierInformationsClient, met à jour les informations du client dans la BD puis dans l'objet
	 * Renvoie true si la modification a lieu, false sinon
	 */
	@Override
	public boolean modifierInformationsClient(Client client,String mail, String password, String civilite, String nom, String prenom,
		Date dateNaissance, String adresseFacturation, Genre styleMusiquePrefere){
		// Recuperer la connexion
		Connection connexion = DBManager.getInstance().getConnection();
		
		try {
			
			// Maj BDD
			String request = "CALL modifier_client(?, ?, ?, ?, ?, ?, ?, ?, ?);";
			
			// Prepared statement 
			PreparedStatement preparedQuery = connexion.prepareStatement(request);
			preparedQuery.setInt(1, client.getId());
			preparedQuery.setString(2, mail);
			preparedQuery.setString(3, password);
			preparedQuery.setString(4, civilite);
			preparedQuery.setString(5, nom);
			preparedQuery.setString(6, prenom);
			preparedQuery.setDate(7, (java.sql.Date) dateNaissance);
			preparedQuery.setString(8, adresseFacturation);
			preparedQuery.setString(9, styleMusiquePrefere.toString());
			
			// Execution
			if(preparedQuery.executeUpdate()>0) { // Succes de la modification
				// Maj Objet
				client.setPassword(mail);
				client.setPassword(password);
				client.setCivilite(civilite);
				client.setNom(nom);
				client.setPrenom(prenom);
				client.setDateNaissance(dateNaissance);
				client.setAdresseFacturation(adresseFacturation);
				client.setStyleMusiquePrefere(styleMusiquePrefere);
				
				return true;
			}
			else{ // La mise a jour echoue
				return false;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}

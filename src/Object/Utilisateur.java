package Object;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import Interface.UtilisateurInterface;

public class Utilisateur implements UtilisateurInterface {
	
	
	
	/*public void ecouterMorceau(TitreMusical titreMusical) {
		
	}*/

	public Utilisateur() {
		super();
	}
	
	
	/*
	 * Fonction authentification v�rifie l'existence du couple mail, password dans la table Client
	 * Renvoie l'objet Client correspondant s'il est trouv�, null sinon
	 */
	@Override
	public Client authentification(String mail, String password) {
		// On recupere une connexion de type java.sql.Connection
		Connection connexion = DBManager.getInstance().getConnection();
		
		try {
			// On execute la requete et on recupere un java.sql.ResultSet
			String request = "CALL authentification_client(?, ?);";
			
			// Prepared statement 
			PreparedStatement preparedQuery = connexion.prepareStatement(request);
			preparedQuery.setString(1, mail);
			preparedQuery.setString(2, password);
			
			// Retour
			ResultSet rs = preparedQuery.executeQuery();
			
			// Execution
			if(rs.next()) {// Vrai si les identifiants correspondent � un compte
				
				List<Playlist> playlists = null; //TODO : r�cup�rer les playlists du client � l'authentification
				
				// Cr�ation du Genre
				Genre genre;
				if(rs.getString("nomGenre")==null) {
					genre = Genre.valueOf("INCONNU");
				}
				else {
					genre = Genre.valueOf(rs.getString("nomGenre"));
				}
				
				return new Client(rs.getInt("idClient"),rs.getString("mail"), rs.getString("password"), rs.getString("civilite"), rs.getString("nom"), 
						rs.getString("prenom"),rs.getDate("dateNaissance"), rs.getString("adresseFacturation"),rs.getInt("nbEcoute"), genre , playlists);
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
	
	
	/*
	 * Fonction authentification v�rifie l'existence du couple mail, password ayant des droits Administrateur
	 * Renvoie l'objet Administrateur correspondant s'il est trouv�, null sinon
	 */
	@Override
	public Administrateur authentificationAdmin(String mail, String password) {
		// Recuperer une connexion de type java.sql.Connection
		Connection connexion = DBManager.getInstance().getConnection();
		
		try {
			// Executer la requete SQL et recuperer un java.sql.ResultSet
			String request = "CALL authentification_admin(?, ?);";
			
			// Prepared statement 
			PreparedStatement preparedQuery = connexion.prepareStatement(request);
			preparedQuery.setString(1, mail);
			preparedQuery.setString(2, password);
			
			// Retour
			ResultSet rs = preparedQuery.executeQuery();
			
			// Vrai si les identifiants correspondent a� un compte
			if(rs.next()) {
				int id = rs.getInt("idAdmin"); // Id de l'administrateur trouve
				String mailRS = rs.getString("mail"); // mail de l'administrateur trouve
				String passwordRS = rs.getString("password"); // password de l'administrateur trouve
				boolean profilGestionClientRS = rs.getBoolean("profilGestionClient"); // true si AdminClient
				boolean profilGestionMusiqueRS = rs.getBoolean("profilGestionMusique"); // true si AdminMusique
				
				if(profilGestionClientRS) {
					return new ProfilGestionnaireClient(id, mailRS, passwordRS);
				}
				else if(profilGestionMusiqueRS) {
					return new ProfilGestionnaireMusical(id, mailRS, passwordRS);
				}
				else {
					return null;
				}
				
			};
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * Fonction creerCompte, creation d'un nouveau Client dans la BDD
	 * Renvoie l'objet Client si succ�s, null sinon
	 */
	@Override
	public Client creerCompte(String mail, String password) {
		// Récupérer une connexion de type java.sql.Connection
		Connection connexion = DBManager.getInstance().getConnection();
		
		try {
			
			String requestVerification = "CALL existe_client(?);";
			PreparedStatement preparedVerification = connexion.prepareStatement(requestVerification);
			preparedVerification.setString(1, mail);
			ResultSet rsVerif = preparedVerification.executeQuery();
			if(rsVerif.next()) { // Le compte existe deja
				return null;
			}
			else { // On cree je compte
				// Executer la requete SQL et recuperer un java.sql.ResultSet
				String request = "SELECT nouveau_client(?, ?);";
				
				// Prepared statement 
				PreparedStatement preparedQuery = connexion.prepareStatement(request);
				preparedQuery.setString(1, mail);
				preparedQuery.setString(2, password);
				
				ResultSet rs = preparedQuery.executeQuery();
				
	            if(rs.next()) // Creation du Client
	            {
	                int last_inserted_id = rs.getInt(1); // Id du Client cree
	                
	                return new Client(last_inserted_id, mail, password);
	            }
				else{
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
	 * Fonction creerCompte, creation d'un nouveau Client dans la BDD
	 * Renvoie l'objet Client si succ�s, null sinon
	 */
	@Override
	public Client creerCompte(String mail, String password, String civilite, String nom, String prenom,
		Date dateNaissance, String adresseFacturation, Genre styleMusiquePrefere) {
		
		// Recuperer une connexion de type java.sql.Connection
		Connection connexion = DBManager.getInstance().getConnection();
		
		try {
			
			String requestVerification = "CALL existe_client(?);";
			PreparedStatement preparedVerification = connexion.prepareStatement(requestVerification);
			preparedVerification.setString(1, mail);
			ResultSet rsVerif = preparedVerification.executeQuery();
			if(rsVerif.next()) { // Le compte existe deja
				return null;
			}
			else { // On cree je compte
				// Executer la requete SQL et recuperer un java.sql.ResultSet
				String request = "SELECT nouveau_client_complet(?, ?, ?, ?, ?, ?, ?, ?);";
				
				// Prepared statement 
				PreparedStatement preparedQuery = connexion.prepareStatement(request);
				preparedQuery.setString(1, mail);
				preparedQuery.setString(2, password);
				preparedQuery.setString(3, civilite);
				preparedQuery.setString(4, nom);
				preparedQuery.setString(5, prenom);
				preparedQuery.setString(6, dateNaissance.toString());
				preparedQuery.setString(7, adresseFacturation);
				preparedQuery.setString(8, styleMusiquePrefere.toString());
				
				ResultSet rs = preparedQuery.executeQuery();
				
	            if(rs.next()) // Creation du Client
	            {
	                int last_inserted_id = rs.getInt(1); // Id du Client cree
					return new Client(last_inserted_id, mail, password, civilite, nom, prenom, dateNaissance, adresseFacturation, styleMusiquePrefere);
				}
				else{
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
	 * Fonction regarder ajoute 1 au nombres de vues de l'Element
	 * et false en cas d'erreur
	 */
	@Override
	public boolean regarder(ElementCatalogue elementCatalogue) {
		// On recupere une connexion de type java.sql.Connection
		Connection connexion = DBManager.getInstance().getConnection();
		
		try {
			
			// On execute la requete SQL et recupere un java.sql.ResultSet
			String request = "CALL regarder(?, ?);";
			
			// Prepared statement 
			PreparedStatement preparedQuery = connexion.prepareStatement(request);
			preparedQuery.setInt(1, 1);
			preparedQuery.setInt(2, elementCatalogue.getIdCatalogue());

            if(preparedQuery.executeUpdate()>0) // Recommandation effectuee
            {
            	elementCatalogue.regarder();
                return true;
			}
			else{
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}



	@Override
	public List<ElementCatalogue> morceauxPopulaires() {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public List<ElementCatalogue> recommandationsDuMoment() {
		// TODO Auto-generated method stub
		return null;
	}
}

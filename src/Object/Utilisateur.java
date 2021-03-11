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
	 * Fonction authentification vÈrifie l'existence du couple mail, password dans la table Client
	 * Renvoie l'objet Client correspondant s'il est trouvÈ, null sinon
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
			if(rs.next()) {// Vrai si les identifiants correspondent ‡ un compte
				
				List<Playlist> playlists = null; //TODO : rÈcupÈrer les playlists du client ‡ l'authentification
				
				// CrÈation du Genre
				Genre genre;
				if(rs.getString("nomGenre")==null) {
					genre = Genre.valueOf("INCONNU");
				}
				else {
					genre = Genre.valueOf(rs.getString("nomGenre"));
				}
				
				return new Client(rs.getString("mail"), rs.getString("password"), rs.getString("civilite"), rs.getString("nom"), 
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
	 * Fonction creerCompte, creation d'un nouveau Client dans la BDD
	 * Renvoie l'objet Client si succËs, null sinon
	 */
	@Override
	public Client creerCompte(String mail, String password) {
		
		// R√©cup√©rer une connexion de type java.sql.Connection
		Connection connexion = DBManager.getInstance().getConnection();
		
		try {
			// Ex√©cuter la requ√™te SQL et r√©cup√©rer un java.sql.ResultSet
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
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;		
	}
	
	/*
	 * Fonction creerCompte, creation d'un nouveau Client dans la BDD
	 * Renvoie l'objet Client si succËs, null sinon
	 */
	@Override
	public Client creerCompte(String mail, String password, String civilite, String nom, String prenom,
		Date dateNaissance, String adresseFacturation, Genre styleMusiquePrefere) {
		
		// R√©cup√©rer une connexion de type java.sql.Connection
		Connection connexion = DBManager.getInstance().getConnection();
		
		try {
			
			// Ex√©cuter la requ√™te SQL et r√©cup√©rer un java.sql.ResultSet
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
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void regarderElementCatalogue(ElementCatalogue elementCatalogue) {
	
		//TODO : pour utiliser "CALL regarder(idCatalogue_ INT)", besoin de conna√Ætre l'identifiant de l'√©l√©ment du catalogue, ajout attribut que je donne √† la cr√©ation?
		
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

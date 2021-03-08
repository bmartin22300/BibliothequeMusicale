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
	
	
	
	@Override
	public Client authentification(String mail, String password) {
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
			
			// Execution
			if(rs.next()) {// Vrai si les identifiants correspondent � un compte
				
				List<Playlist> playlists = null; //TODO : r�cup�rer les playlists du client � l'authentification
				
				// Cr�ation du Genre
				Genre genre;
				if(rs.getString("nomGenre") == null) {
					genre = null;
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
	
	@Override
	public Client creerCompte(String mail, String password) {
		
		// Récupérer une connexion de type java.sql.Connection
		Connection connexion = DBManager.getInstance().getConnection();
		
		try {
			// Exécuter la requête SQL et récupérer un java.sql.ResultSet
			String request = "CALL nouveau_client(?, ?);";
			
			// Prepared statement 
			PreparedStatement preparedQuery = connexion.prepareStatement(request);
			preparedQuery.setString(1, mail);
			preparedQuery.setString(2, password);
			
			// Execution
			if(preparedQuery.executeUpdate()>0) {
				return new Client(mail, password);
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
	public Client creerCompte(String mail, String password, String civilite, String nom, String prenom,
		Date dateNaissance, String adresseFacturation, Genre styleMusiquePrefere) {
		
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
			preparedQuery.setString(6, dateNaissance.toString());
			preparedQuery.setString(7, adresseFacturation);
			preparedQuery.setString(8, styleMusiquePrefere.toString());
			
			// Execution
			if(preparedQuery.executeUpdate()>0) {
				return new Client(mail, password, civilite, nom, prenom, dateNaissance, adresseFacturation, styleMusiquePrefere);
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
	
		//TODO : pour utiliser "CALL regarder(idCatalogue_ INT)", besoin de connaître l'identifiant de l'élément du catalogue, ajout attribut que je donne à la création?
		
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

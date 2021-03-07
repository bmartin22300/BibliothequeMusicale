package Object;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Year;
import java.util.List;

import Interface.ProfilGestionnaireMusicalInterface;

public class ProfilGestionnaireMusical extends Administrateur implements ProfilGestionnaireMusicalInterface{

	public ProfilGestionnaireMusical(String mail, String password) {
		super(mail,password);
	}

	// Methodes heritees d'Administrateur
	@Override
	public boolean authentification(String mail, String password) {
		// Récupérer une connexion de type java.sql.Connection
		Connection connexion = DBManager.getInstance().getConnection();
		
		try {
			// Exécuter la requête SQL et récupérer un java.sql.ResultSet
			String request = "CALL authentification_adminMusique(?, ?);";
			
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
			preparedQuery.setBoolean(3, false); // Profil gestion Client
			preparedQuery.setBoolean(4, true); // Profil gestion Musique
			
			// Execution
			if(preparedQuery.executeUpdate()>0) {
				return new ProfilGestionnaireMusical(mail, password);
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

	// Methodes specifiques a la classe
	@Override
	public TitreMusical creerTitre(String titre, Year anneeCreation, List<Interprete> interpretes, int duree, Genre genre) {
		// Récupérer une connexion de type java.sql.Connection
		Connection connexion = DBManager.getInstance().getConnection();
		
		try {
			
			// Exécuter la requête SQL et récupérer un java.sql.ResultSet
			String request = "CALL nouveau_titre(?, ?, ?);";
			
			// Prepared statement 
			PreparedStatement preparedQuery = connexion.prepareStatement(request);
			preparedQuery.setString(1, titre);
			preparedQuery.setString(2, anneeCreation.toString());
			preparedQuery.setString(3, genre.toString()); //Marche probablement pas
			
			preparedQuery.executeUpdate();
			
			ResultSet rs = preparedQuery.getGeneratedKeys();
            if(rs.next()) // Ajout des interpretes
            {
                int last_inserted_id = rs.getInt(1);
                
				for(Interprete interprete : interpretes){
					ajoutDiscographie(last_inserted_id, interprete.getPseudonyme());
				}
				
				return new TitreMusical(last_inserted_id, titre, interpretes, anneeCreation, duree, genre);
                
            }
            else {
            	return null;
            }		
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Interprete creerInterprete(String pseudo) {
		// Récupérer une connexion de type java.sql.Connection
		Connection connexion = DBManager.getInstance().getConnection();
		
		try {
			
			// Exécuter la requête SQL et récupérer un java.sql.ResultSet
			String request = "CALL nouveau_interprete(?);";
			
			// Prepared statement 
			PreparedStatement preparedQuery = connexion.prepareStatement(request);
			preparedQuery.setString(1, pseudo);
			
			// Execution
			if(preparedQuery.executeUpdate()>0) {
				return new Interprete(pseudo);
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
	public Interprete creerInterprete(String pseudo, String prenom, String nom, Date dateNaissance) {
		// Récupérer une connexion de type java.sql.Connection
		Connection connexion = DBManager.getInstance().getConnection();
		
		try {
			
			// Exécuter la requête SQL et récupérer un java.sql.ResultSet
			String request = "CALL nouveau_interprete_complet(?, ?, ?, ?);";
			
			// Prepared statement 
			PreparedStatement preparedQuery = connexion.prepareStatement(request);
			preparedQuery.setString(1, pseudo);
			preparedQuery.setString(2, prenom);
			preparedQuery.setString(3, nom);
			preparedQuery.setString(4, dateNaissance.toString());
			
			// Execution
			if(preparedQuery.executeUpdate()>0) {
				return new Interprete(pseudo, prenom, nom, dateNaissance);
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
	public void ajoutDiscographie(int idTitre, String pseudoInterprete) {
		// Récupérer une connexion de type java.sql.Connection
		Connection connexion = DBManager.getInstance().getConnection();
		
		try {
			
			// Exécuter la requête SQL et récupérer un java.sql.ResultSet
			String request = "CALL association_titre_interprete(?, ?);";
			
			// Prepared statement 
			PreparedStatement preparedQuery = connexion.prepareStatement(request);
			preparedQuery.setInt(1, idTitre);
			preparedQuery.setString(2, pseudoInterprete);

			// Execution
			preparedQuery.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	@Override
	public void creerAlbum(String nom, String anneeSortie) {
		// Récupérer une connexion de type java.sql.Connection
		Connection connexion = DBManager.getInstance().getConnection();
		
		try {
			
			// Exécuter la requête SQL et récupérer un java.sql.ResultSet
			String request = "CALL nouveau_album(?, ?);";
			
			// Prepared statement 
			PreparedStatement preparedQuery = connexion.prepareStatement(request);
			preparedQuery.setString(1, nom);
			preparedQuery.setString(2, anneeSortie);
			
			// Execution
			preparedQuery.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void ajoutTitreAlbum(int idTitre, int idAlbum) {
		// Récupérer une connexion de type java.sql.Connection
		Connection connexion = DBManager.getInstance().getConnection();
		
		try {
			
			// Exécuter la requête SQL et récupérer un java.sql.ResultSet
			String request = "CALL ajout_titre_album(?, ?);";
			
			// Prepared statement 
			PreparedStatement preparedQuery = connexion.prepareStatement(request);
			preparedQuery.setInt(1, idTitre);
			preparedQuery.setInt(2, idAlbum);

			// Execution
			preparedQuery.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	
	
	//TODO -- En travaux
	@Override
	public void creerPlaylist(String nom, String mail) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ajoutElementCataloguePlaylist(int idCatalogue, int idPlaylist) {
		// TODO Auto-generated method stub
		
	}
}

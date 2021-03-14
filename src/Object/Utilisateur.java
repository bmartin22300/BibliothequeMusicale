package Object;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Interface.UtilisateurInterface;

public class Utilisateur implements UtilisateurInterface {
	
	
	
	/*public void ecouterMorceau(TitreMusical titreMusical) {
		
	}*/

	public Utilisateur() {
		super();
	}
	
	
	/*
	 * Fonction authentification vérifie l'existence du couple mail, password dans la table Client
	 * Renvoie l'objet Client correspondant s'il est trouvé, null sinon
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
			if(rs.next()) {// Vrai si les identifiants correspondent à un compte
				
				List<Playlist> playlists = null; //TODO : récupérer les playlists du client à l'authentification
				
				// Création du Genre
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
	 * Fonction authentification vérifie l'existence du couple mail, password ayant des droits Administrateur
	 * Renvoie l'objet Administrateur correspondant s'il est trouvé, null sinon
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
			
			// Vrai si les identifiants correspondent a  un compte
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
	 * Renvoie l'objet Client si succès, null sinon
	 */
	@Override
	public Client creerCompte(String mail, String password) {
		// RÃ©cupÃ©rer une connexion de type java.sql.Connection
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
	 * Renvoie l'objet Client si succès, null sinon
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



	/*
	 * Fonction topTitresEcoutes renvoie la List<TitreMusical> correspondant aux 10 titres les plus ecoutes
	 */
	@Override
	public List<TitreMusical> topTitresEcoutes() {
			// On recupere une connexion de type java.sql.Connection
			Connection connexion = DBManager.getInstance().getConnection();
			
			try {				// On execute la requete SQL et on recupere un java.sql.ResultSet
				String request = "SELECT * FROM vue_morceaux_populaires;";
				
				// Prepared statement 
				PreparedStatement preparedQuery = connexion.prepareStatement(request);
				
				// Retour
				ResultSet rs = preparedQuery.executeQuery();
				
				// Creation de la liste
				List<TitreMusical> titres = new ArrayList<TitreMusical>();
				// Vrai tant qu'il reste des lignes
				while(rs.next()) {
					// Creation du titre
					int idTitre = rs.getInt("idCatalogue");
					String titreTitre = rs.getString("titre");
					int dateCreationTitre = rs.getInt("dateCreation"); 
					int dureeTitre = rs.getInt("duree");
					String stringGenre = rs.getString("nomGenre");
					Genre nomGenre;
					if(stringGenre==null) {
						nomGenre = Genre.INCONNU;
					}
					else {
						nomGenre = Genre.valueOf(rs.getString("nomGenre").toUpperCase());
					}
					int nbEcoute = rs.getInt("nbEcoute");
					int nbEcouteMois = rs.getInt("nbEcouteMois");
					Album albumTitre;
					if((rs.getInt("Album_idCatalogue"))==0) {
						albumTitre=null;
					}else {
						albumTitre = new Album(rs.getInt("Album_idCatalogue"));
					}
					
					// On recherche les titres de l'interprete
					String requestInterpretes = "CALL rechercherParIdCatalogueInterpretes(?);";
					
					// Prepared statement 
					PreparedStatement preparedQueryInterpretes = connexion.prepareStatement(requestInterpretes);
					preparedQueryInterpretes.setInt(1, idTitre);
					
					// Retour
					ResultSet rsInterpretes = preparedQueryInterpretes.executeQuery();
					//Creation de la liste des interpretes
					List<Interprete> interpretes = new ArrayList<Interprete>();
					
					while(rsInterpretes.next()) {
						// Creation de l'interprete
						int idInterprete = rsInterpretes.getInt("idInterprete");
						String pseudo = rsInterpretes.getString("pseudonyme");
						String prenom = rsInterpretes.getString("prenom"); 
						String nom = rsInterpretes.getString("nom");
						Date dateNaissance = rsInterpretes.getDate("dateNaissance");
						
						interpretes.add(new Interprete(idInterprete, pseudo, prenom, nom, dateNaissance));
					}
					
					// Ajout a la liste retournee
					TitreMusical titreTemp = new TitreMusical(idTitre, titreTitre, dateCreationTitre, dureeTitre, nomGenre, albumTitre, null);
					titreTemp.setNbEcoute(nbEcoute);
					titreTemp.setNbEcouteMois(nbEcouteMois);
					titres.add(titreTemp);						
				}
				return titres;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
	}


	/*
	 * Fonction recommandationsDuMoment renvoie la liste des morceaux recommandés par un administrateur
	 */
	@Override
	public List<TitreMusical> recommandationsDuMoment(){
		// On recupere une connexion de type java.sql.Connection
		Connection connexion = DBManager.getInstance().getConnection();
		
		try {				// On execute la requete SQL et on recupere un java.sql.ResultSet
			String request = "SELECT * FROM vue_recommandations;";
			
			// Prepared statement 
			PreparedStatement preparedQuery = connexion.prepareStatement(request);
			
			// Retour
			ResultSet rs = preparedQuery.executeQuery();
			
			// Creation de la liste
			List<TitreMusical> titres = new ArrayList<TitreMusical>();
			// Vrai tant qu'il reste des lignes
			while(rs.next()) {
				// Creation du titre
				int idTitre = rs.getInt("idCatalogue");
				String titreTitre = rs.getString("titre");
				int dateCreationTitre = rs.getInt("dateCreation"); 
				int dureeTitre = rs.getInt("duree");
				String stringGenre = rs.getString("nomGenre");
				Genre nomGenre;
				if(stringGenre==null) {
					nomGenre = Genre.INCONNU;
				}
				else {
					nomGenre = Genre.valueOf(rs.getString("nomGenre").toUpperCase());
				}
				int nbEcoute = rs.getInt("nbEcoute");
				int nbEcouteMois = rs.getInt("nbEcouteMois");
				Album albumTitre;
				if((rs.getInt("Album_idCatalogue"))==0) {
					albumTitre=null;
				}else {
					albumTitre = new Album(rs.getInt("Album_idCatalogue"));
				}
				
				// On recherche les titres de l'interprete
				String requestInterpretes = "CALL rechercherParIdCatalogueInterpretes(?);";
				
				// Prepared statement 
				PreparedStatement preparedQueryInterpretes = connexion.prepareStatement(requestInterpretes);
				preparedQueryInterpretes.setInt(1, idTitre);
				
				// Retour
				ResultSet rsInterpretes = preparedQueryInterpretes.executeQuery();
				//Creation de la liste des interpretes
				List<Interprete> interpretes = new ArrayList<Interprete>();
				
				while(rsInterpretes.next()) {
					// Creation de l'interprete
					int idInterprete = rsInterpretes.getInt("idInterprete");
					String pseudo = rsInterpretes.getString("pseudonyme");
					String prenom = rsInterpretes.getString("prenom"); 
					String nom = rsInterpretes.getString("nom");
					Date dateNaissance = rsInterpretes.getDate("dateNaissance");
					
					interpretes.add(new Interprete(idInterprete, pseudo, prenom, nom, dateNaissance));
				}
				
				// Ajout a la liste retournee
				TitreMusical titreTemp = new TitreMusical(idTitre, titreTitre, dateCreationTitre, dureeTitre, nomGenre, albumTitre, null);
				titreTemp.setNbEcoute(nbEcoute);
				titreTemp.setNbEcouteMois(nbEcouteMois);
				titres.add(titreTemp);						
			}
			return titres;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}

package Object;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Interface.AdministrateurInterface;

public abstract class Administrateur implements AdministrateurInterface {

	private int id;
	private String mail;
	private String password;

	// Constructeur
	public Administrateur(int id, String mail, String password) {
		super();
		this.id = id;
		this.mail = mail;
		this.password = password;
	}
	
	// Getters et Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}	
	
	// Methodes de classe	
	public abstract Administrateur creerAdmin(String mail, String password);

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

	/*
	 * Fonction topUtilisateursEcoutes renvoie la List<Client> correspondant aux 10 utilisateurs ayant realise le plus d'ecoutes
	 */
	@Override
	public List<Client> topUtilisateursEcoutes() {
		
		// On recupere une connexion de type java.sql.Connection
		Connection connexion = DBManager.getInstance().getConnection();
		
		try {
			// On execute la requete SQL et on recupere un java.sql.ResultSet
			String request = "SELECT * FROM vue_top_utilisateurs_ecoutes;";
			
			// Prepared statement 
			PreparedStatement preparedQuery = connexion.prepareStatement(request);
			
			// Retour
			ResultSet rs = preparedQuery.executeQuery();
			
			// Creation de la liste
			List<Client> clients = new ArrayList<Client>();
			// Vrai tant qu'il reste des lignes
			while(rs.next()) {
				// Creation du Client
				int idClient = rs.getInt("idClient");
				String mailClient = rs.getString("mail");
				String passwordClient = null;
				String civiliteClient = null; 
				String nomClient = rs.getString("nom"); 
				String prenomClient = rs.getString("prenom");
				Date dateNaissanceClient = null;
				String adresseFacturationClient = null;
				int nbEcouteClient = rs.getInt("nbEcoute");
				String stringGenre = null;
				Genre nomGenreClient;
				if(stringGenre==null) {
					nomGenreClient = Genre.INCONNU;
				}
				else {
					nomGenreClient = Genre.valueOf(rs.getString("nomGenre").toUpperCase());
				}
				
				// On recherche les playlists du Client
				String requestPlaylists = "CALL rechercherParIdClientPlaylists(?);";
				
				// Prepared statement 
				PreparedStatement preparedQueryPlaylists = connexion.prepareStatement(requestPlaylists);
				preparedQueryPlaylists.setInt(1, idClient);
				
				// Retour
				ResultSet rsPlaylists = preparedQueryPlaylists.executeQuery();
				//Creation de la liste des playlists
				List<Playlist> playlists = new ArrayList<Playlist>();
				
				while(rsPlaylists.next()) {
					// Creation de la playlist
					int idPlaylistPlaylist = rsPlaylists.getInt("idPlaylist");
					String nomPlaylistPlaylist = rsPlaylists.getString("nomPlaylist");
					//int idClientPlaylist = rsPlaylists.getInt("idClient"); 
					
					playlists.add(new Playlist(idPlaylistPlaylist, nomPlaylistPlaylist, null, null));
				}
				
				// Ajout a la liste retournee
				clients.add(new Client(idClient, mailClient, passwordClient, civiliteClient, nomClient, prenomClient, dateNaissanceClient, adresseFacturationClient, nbEcouteClient, nomGenreClient, playlists));
			}
			return clients;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	// Interprete
	/*
	 * Fonction rechercherParPseudoInterprete renvoie la List<Interprete> correspondant a la recherche en parametre
	 */
	@Override
	public List<Interprete> rechercherParPseudoInterprete(String recherche) {
		// On recupere une connexion de type java.sql.Connection
		Connection connexion = DBManager.getInstance().getConnection();
		
		try {
			// On execute la requete SQL et on recupere un java.sql.ResultSet
			String request = "CALL rechercherParPseudoInterprete(?);";
			
			// Prepared statement 
			PreparedStatement preparedQuery = connexion.prepareStatement(request);
			preparedQuery.setString(1, recherche);
			
			// Retour
			ResultSet rs = preparedQuery.executeQuery();
			
			// Creation de la liste
			List<Interprete> interpretes = new ArrayList<Interprete>();
			// Vrai tant qu'il reste des lignes
			while(rs.next()) {
				// Creation de l'interprete
				int idInterprete = rs.getInt("idInterprete");
				String pseudo = rs.getString("pseudonyme");
				String prenom = rs.getString("prenom"); 
				String nom = rs.getString("nom");
				Date dateNaissance = rs.getDate("dateNaissance");
				
				// On recherche les titres de l'interprete
				String requestTitres = "CALL rechercherParIdInterpreteTitres(?);";
				
				// Prepared statement 
				PreparedStatement preparedQueryTitres = connexion.prepareStatement(requestTitres);
				preparedQueryTitres.setInt(1, idInterprete);
				
				// Retour
				ResultSet rsTitres = preparedQueryTitres.executeQuery();
				//Creation de la liste des titres
				List<TitreMusical> titres = new ArrayList<TitreMusical>();
				
				while(rsTitres.next()) {
					// Creation du titre
					int idTitre = rsTitres.getInt("idCatalogue");
					String titreTitre = rsTitres.getString("titre");
					int dateCreationTitre = rsTitres.getInt("dateCreation"); 
					int dureeTitre = rsTitres.getInt("duree");
					String stringGenre = rsTitres.getString("nomGenre");
					Genre nomGenre;
					if(stringGenre==null) {
						nomGenre = Genre.INCONNU;
					}
					else {
						nomGenre = Genre.valueOf(rsTitres.getString("nomGenre").toUpperCase());
					}
					Album albumTitre;
					if((rsTitres.getInt("Album_idCatalogue"))==0) {
						albumTitre=null;
					}else {
						albumTitre = new Album(rsTitres.getInt("Album_idCatalogue"));
					}
					
					titres.add(new TitreMusical(idTitre, titreTitre, dateCreationTitre, dureeTitre, nomGenre, albumTitre, null));
				}
				
				// Ajout a la liste retournee
				interpretes.add(new Interprete(idInterprete, pseudo, prenom, nom, dateNaissance, titres));
			}
			
			return interpretes;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * Fonction rechercherParPrenomInterprete renvoie la List<Interprete> correspondant a la recherche en parametre
	 */
	@Override
	public List<Interprete> rechercherParPrenomInterprete(String recherche) {
		// On recupere une connexion de type java.sql.Connection
		Connection connexion = DBManager.getInstance().getConnection();
		
		try {
			// On execute la requete SQL et on recupere un java.sql.ResultSet
			String request = "CALL rechercherParPrenomInterprete(?);";
			
			// Prepared statement 
			PreparedStatement preparedQuery = connexion.prepareStatement(request);
			preparedQuery.setString(1, recherche);
			
			// Retour
			ResultSet rs = preparedQuery.executeQuery();
			
			// Creation de la liste
			List<Interprete> interpretes = new ArrayList<Interprete>();
			// Vrai tant qu'il reste des lignes
			while(rs.next()) {
				// Creation de l'interprete
				int idInterprete = rs.getInt("idInterprete");
				String pseudo = rs.getString("pseudonyme");
				String prenom = rs.getString("prenom"); 
				String nom = rs.getString("nom");
				Date dateNaissance = rs.getDate("dateNaissance");
				
				// On recherche les titres de l'interprete
				String requestTitres = "CALL rechercherParIdInterpreteTitres(?);";
				
				// Prepared statement 
				PreparedStatement preparedQueryTitres = connexion.prepareStatement(requestTitres);
				preparedQueryTitres.setInt(1, idInterprete);
				
				// Retour
				ResultSet rsTitres = preparedQueryTitres.executeQuery();
				//Creation de la liste des titres
				List<TitreMusical> titres = new ArrayList<TitreMusical>();
				
				while(rsTitres.next()) {
					// Creation du titre
					int idTitre = rsTitres.getInt("idCatalogue");
					String titreTitre = rsTitres.getString("titre");
					int dateCreationTitre = rsTitres.getInt("dateCreation"); 
					int dureeTitre = rsTitres.getInt("duree");
					String stringGenre = rsTitres.getString("nomGenre");
					Genre nomGenre;
					if(stringGenre==null) {
						nomGenre = Genre.INCONNU;
					}
					else {
						nomGenre = Genre.valueOf(rsTitres.getString("nomGenre").toUpperCase());
					}
					Album albumTitre = new Album(rsTitres.getInt("Album_idCatalogue"));
					
					titres.add(new TitreMusical(idTitre, titreTitre, dateCreationTitre, dureeTitre, nomGenre, albumTitre, null));
				}
				
				// Ajout a la liste retournee
				interpretes.add(new Interprete(idInterprete, pseudo, prenom, nom, dateNaissance));
			}
			
			return interpretes;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * Fonction rechercherParNomInterprete renvoie la List<Interprete> correspondant a la recherche en parametre
	 */
	@Override
	public List<Interprete> rechercherParNomInterprete(String recherche) {
		// On recupere une connexion de type java.sql.Connection
		Connection connexion = DBManager.getInstance().getConnection();
		
		try {
			// On execute la requete SQL et on recupere un java.sql.ResultSet
			String request = "CALL rechercherParNomInterprete(?);";
			
			// Prepared statement 
			PreparedStatement preparedQuery = connexion.prepareStatement(request);
			preparedQuery.setString(1, recherche);
			
			// Retour
			ResultSet rs = preparedQuery.executeQuery();
			
			// Creation de la liste
			List<Interprete> interpretes = new ArrayList<Interprete>();
			// Vrai tant qu'il reste des lignes
			while(rs.next()) {
				// Creation de l'interprete
				int idInterprete = rs.getInt("idInterprete");
				String pseudo = rs.getString("pseudonyme");
				String prenom = rs.getString("prenom"); 
				String nom = rs.getString("nom");
				Date dateNaissance = rs.getDate("dateNaissance");
				
				// On recherche les titres de l'interprete
				String requestTitres = "CALL rechercherParIdInterpreteTitres(?);";
				
				// Prepared statement 
				PreparedStatement preparedQueryTitres = connexion.prepareStatement(requestTitres);
				preparedQueryTitres.setInt(1, idInterprete);
				
				// Retour
				ResultSet rsTitres = preparedQueryTitres.executeQuery();
				//Creation de la liste des titres
				List<TitreMusical> titres = new ArrayList<TitreMusical>();
				
				while(rsTitres.next()) {
					// Creation du titre
					int idTitre = rsTitres.getInt("idCatalogue");
					String titreTitre = rsTitres.getString("titre");
					int dateCreationTitre = rsTitres.getInt("dateCreation"); 
					int dureeTitre = rsTitres.getInt("duree");
					String stringGenre = rsTitres.getString("nomGenre");
					Genre nomGenre;
					if(stringGenre==null) {
						nomGenre = Genre.INCONNU;
					}
					else {
						nomGenre = Genre.valueOf(rsTitres.getString("nomGenre").toUpperCase());
					}
					Album albumTitre;
					if((rsTitres.getInt("Album_idCatalogue"))==0) {
						albumTitre=null;
					}else {
						albumTitre = new Album(rsTitres.getInt("Album_idCatalogue"));
					}
					
					titres.add(new TitreMusical(idTitre, titreTitre, dateCreationTitre, dureeTitre, nomGenre, albumTitre, null));
				}
				
				// Ajout a la liste retournee
				interpretes.add(new Interprete(idInterprete, pseudo, prenom, nom, dateNaissance));
			}
			return interpretes;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/*
	 * Fonction getInterprete renvoie l'Interprete associé à l'id en parametre
	 */
	@Override
	public Interprete getInterprete(int id) {
		// On recupere une connexion de type java.sql.Connection
		Connection connexion = DBManager.getInstance().getConnection();
		
		try {
			// On execute la requete SQL et on recupere un java.sql.ResultSet
			String request = "CALL getInterprete(?);";
			
			// Prepared statement 
			PreparedStatement preparedQuery = connexion.prepareStatement(request);
			preparedQuery.setInt(1, id);
			
			// Retour
			ResultSet rs = preparedQuery.executeQuery();
			
			// Vrai si on a un resultat
			if(rs.next()) {
				// Creation de l'interprete
				int idInterprete = rs.getInt("idInterprete");
				String pseudo = rs.getString("pseudonyme");
				String prenom = rs.getString("prenom"); 
				String nom = rs.getString("nom");
				Date dateNaissance = rs.getDate("dateNaissance");
				
				// On recherche les titres de l'interprete
				String requestTitres = "CALL rechercherParIdInterpreteTitres(?);";
				
				// Prepared statement 
				PreparedStatement preparedQueryTitres = connexion.prepareStatement(requestTitres);
				preparedQueryTitres.setInt(1, idInterprete);
				
				// Retour
				ResultSet rsTitres = preparedQueryTitres.executeQuery();
				//Creation de la liste des titres
				List<TitreMusical> titres = new ArrayList<TitreMusical>();
				
				while(rsTitres.next()) {
					// Creation du titre
					int idTitre = rsTitres.getInt("idCatalogue");
					String titreTitre = rsTitres.getString("titre");
					int dateCreationTitre = rsTitres.getInt("dateCreation"); 
					int dureeTitre = rsTitres.getInt("duree");
					String stringGenre = rsTitres.getString("nomGenre");
					Genre nomGenre;
					if(stringGenre==null) {
						nomGenre = Genre.INCONNU;
					}
					else {
						nomGenre = Genre.valueOf(rsTitres.getString("nomGenre").toUpperCase());
					}
					Album albumTitre;
					if((rsTitres.getInt("Album_idCatalogue"))==0) {
						albumTitre=null;
					}else {
						albumTitre = new Album(rsTitres.getInt("Album_idCatalogue"));
					}
					
					titres.add(new TitreMusical(idTitre, titreTitre, dateCreationTitre, dureeTitre, nomGenre, albumTitre, null));
				}
				
				// On retourne l'Interprete
				return new Interprete(idInterprete, pseudo, prenom, nom, dateNaissance);
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
	
	// TitreMusical
	/*
	 * Fonction rechercherParNomTitre renvoie la List<TitreMusical> correspondant a la recherche en parametre
	 */
	@Override
	public List<TitreMusical> rechercherParNomTitre(String recherche) {
		// On recupere une connexion de type java.sql.Connection
				Connection connexion = DBManager.getInstance().getConnection();
				
				try {
					// On execute la requete SQL et on recupere un java.sql.ResultSet
					String request = "CALL rechercherParTitreTitre(?);";
					
					// Prepared statement 
					PreparedStatement preparedQuery = connexion.prepareStatement(request);
					preparedQuery.setString(1, recherche);
					
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
						titres.add(new TitreMusical(idTitre, titreTitre, dateCreationTitre, dureeTitre, nomGenre, albumTitre, interpretes));						
					}
					return titres;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return null;
	}

	/*
	 * Fonction getTitreMusical renvoie le TitreMusical associé à l'id en parametre
	 */
	@Override
	public TitreMusical getTitreMusical(int idTitreMusical) {
		// On recupere une connexion de type java.sql.Connection
		Connection connexion = DBManager.getInstance().getConnection();
		
		try {
			// On execute la requete SQL et on recupere un java.sql.ResultSet
			String request = "CALL getTitreMusical(?);";
			
			// Prepared statement 
			PreparedStatement preparedQuery = connexion.prepareStatement(request);
			preparedQuery.setInt(1, idTitreMusical);
			
			// Retour
			ResultSet rs = preparedQuery.executeQuery();
			
			// Vrai si on a un resultat
			if(rs.next()) {					
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
				
				// On retourne le titre
				return new TitreMusical(idTitre, titreTitre, dateCreationTitre, dureeTitre, nomGenre, albumTitre, null);						
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
	public List<TitreMusical> rechercherParGenreTitre(Genre genre) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TitreMusical> rechercherParDateSortieTitre(Date date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TitreMusical> parcourirCatalogueTitre() {
		// TODO Auto-generated method stub
		return null;
	}

	// Client
	/*
	 * Fonction rechercherParMailClient renvoie la List<Client> correspondant a la recherche en parametre
	 */
	@Override
	public List<Client> rechercherParMailClient(String recherche) {
		// On recupere une connexion de type java.sql.Connection
		Connection connexion = DBManager.getInstance().getConnection();
		
		try {
			// On execute la requete SQL et on recupere un java.sql.ResultSet
			String request = "CALL rechercherParMailClient(?);";
			
			// Prepared statement 
			PreparedStatement preparedQuery = connexion.prepareStatement(request);
			preparedQuery.setString(1, recherche);
			
			// Retour
			ResultSet rs = preparedQuery.executeQuery();
			
			// Creation de la liste
			List<Client> clients = new ArrayList<Client>();
			// Vrai tant qu'il reste des lignes
			while(rs.next()) {
				// Creation du Client
				int idClient = rs.getInt("idClient");
				String mailClient = rs.getString("mail");
				String passwordClient = rs.getString("password");
				String civiliteClient = rs.getString("civilite"); 
				String nomClient = rs.getString("nom"); 
				String prenomClient = rs.getString("prenom");
				Date dateNaissanceClient = rs.getDate("dateNaissance");
				String adresseFacturationClient = rs.getString("adresseFacturation");
				int nbEcouteClient = rs.getInt("nbEcoute");
				String stringGenre = rs.getString("nomGenre");
				Genre nomGenreClient;
				if(stringGenre==null) {
					nomGenreClient = Genre.INCONNU;
				}
				else {
					nomGenreClient = Genre.valueOf(rs.getString("nomGenre").toUpperCase());
				}
				
				// On recherche les playlists du Client
				String requestPlaylists = "CALL rechercherParIdClientPlaylists(?);";
				
				// Prepared statement 
				PreparedStatement preparedQueryPlaylists = connexion.prepareStatement(requestPlaylists);
				preparedQueryPlaylists.setInt(1, idClient);
				
				// Retour
				ResultSet rsPlaylists = preparedQueryPlaylists.executeQuery();
				//Creation de la liste des playlists
				List<Playlist> playlists = new ArrayList<Playlist>();
				
				while(rsPlaylists.next()) {
					// Creation de la playlist
					int idPlaylistPlaylist = rsPlaylists.getInt("idPlaylist");
					String nomPlaylistPlaylist = rsPlaylists.getString("nomPlaylist");
					//int idClientPlaylist = rsPlaylists.getInt("idClient"); 
					
					playlists.add(new Playlist(idPlaylistPlaylist, nomPlaylistPlaylist, null, null));
				}
				
				// Ajout a la liste retournee
				clients.add(new Client(idClient, mailClient, passwordClient, civiliteClient, nomClient, prenomClient, dateNaissanceClient, adresseFacturationClient, nbEcouteClient, nomGenreClient, playlists));
			}
			return clients;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/*
	 * Fonction getClient renvoie le Client associé à l'id en parametre
	 */
	@Override
	public Client getClient(int id) {
		// On recupere une connexion de type java.sql.Connection
		Connection connexion = DBManager.getInstance().getConnection();
		
		try {
			// On execute la requete SQL et on recupere un java.sql.ResultSet
			String request = "CALL getClient(?);";
			
			// Prepared statement 
			PreparedStatement preparedQuery = connexion.prepareStatement(request);
			preparedQuery.setInt(1, id);
			
			// Retour
			ResultSet rs = preparedQuery.executeQuery();
			
			// Vrai si on a un resultat
			if(rs.next()) {					
				// Creation du Client
				int idClient = rs.getInt("idClient");
				String mailClient = rs.getString("mail");
				String passwordClient = rs.getString("password");
				String civiliteClient = rs.getString("civilite"); 
				String nomClient = rs.getString("nom"); 
				String prenomClient = rs.getString("prenom");
				Date dateNaissanceClient = rs.getDate("dateNaissance");
				String adresseFacturationClient = rs.getString("adresseFacturation");
				int nbEcouteClient = rs.getInt("nbEcoute");
				String stringGenre = rs.getString("nomGenre");
				Genre nomGenreClient;
				if(stringGenre==null) {
					nomGenreClient = Genre.INCONNU;
				}
				else {
					nomGenreClient = Genre.valueOf(rs.getString("nomGenre").toUpperCase());
				}
				
				// On recherche les playlists du Client
				String requestPlaylists = "CALL rechercherParIdClientPlaylists(?);";
				
				// Prepared statement 
				PreparedStatement preparedQueryPlaylists = connexion.prepareStatement(requestPlaylists);
				preparedQueryPlaylists.setInt(1, idClient);
				
				// Retour
				ResultSet rsPlaylists = preparedQueryPlaylists.executeQuery();
				//Creation de la liste des playlists
				List<Playlist> playlists = new ArrayList<Playlist>();
				
				while(rsPlaylists.next()) {
					// Creation de la playlist
					int idPlaylistPlaylist = rsPlaylists.getInt("idPlaylist");
					String nomPlaylistPlaylist = rsPlaylists.getString("nomPlaylist");
					//int idClientPlaylist = rsPlaylists.getInt("idClient"); 
					
					playlists.add(new Playlist(idPlaylistPlaylist, nomPlaylistPlaylist, null, null));
				}
				
				// On retourne le Client
				return new Client(idClient, mailClient, passwordClient, civiliteClient, nomClient, prenomClient, dateNaissanceClient, adresseFacturationClient, nbEcouteClient, nomGenreClient, playlists);			
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
	public List<Client> rechercherParNomClient(String recherche) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Client> rechercherParPrenomClient(String recherche) {
		// TODO Auto-generated method stub
		return null;
	}

	// Album
	/*
	 * Fonction rechercherParNomAlbum renvoie la List<Album> correspondant a la recherche en parametre
	 */
	@Override
	public List<Album> rechercherParNomAlbum(String recherche) {
		// On recupere une connexion de type java.sql.Connection
		Connection connexion = DBManager.getInstance().getConnection();
		
		try {
			// On execute la requete SQL et on recupere un java.sql.ResultSet
			String request = "CALL rechercherParNomAlbum(?);";
			
			// Prepared statement 
			PreparedStatement preparedQuery = connexion.prepareStatement(request);
			preparedQuery.setString(1, recherche);
			
			// Retour
			ResultSet rs = preparedQuery.executeQuery();
			
			// Creation de la liste
			List<Album> albums = new ArrayList<Album>();
			// Vrai tant qu'il reste des lignes
			while(rs.next()) {
				// Creation du titre
				int idAlbum = rs.getInt("idCatalogue");
				String nomAlbum = rs.getString("nom");
				int dateSortieAlbum = rs.getInt("dateSortie"); 
				int dureeAlbum = rs.getInt("duree");
				
				// On recherche les titres de l'album
				String requestTitres = "CALL rechercherParIdAlbumTitres(?);";
				
				// Prepared statement 
				PreparedStatement preparedQueryTitres = connexion.prepareStatement(requestTitres);
				preparedQueryTitres.setInt(1, idAlbum);
				
				// Retour
				ResultSet rsTitres = preparedQueryTitres.executeQuery();
				//Creation de la liste des interpretes
				List<TitreMusical> titres = new ArrayList<TitreMusical>();
				
				while(rsTitres.next()) {
					// Creation de l'interprete
					int idTitre = rsTitres.getInt("idCatalogue");
					String titreTitre = rsTitres.getString("titre");
					int dateCreationTitre = rsTitres.getInt("dateCreation"); 
					int dureeTitre = rsTitres.getInt("duree");
					String stringGenre = rsTitres.getString("nomGenre");
					Genre nomGenreTitre;
					if(stringGenre==null) {
						nomGenreTitre = Genre.INCONNU;
					}
					else {
						nomGenreTitre = Genre.valueOf(rsTitres.getString("nomGenre").toUpperCase());
					}
					Album albumTitre;
					if((rsTitres.getInt("Album_idCatalogue"))==0) {
						albumTitre=null;
					}else {
						albumTitre = new Album(rsTitres.getInt("Album_idCatalogue"));
					}
					
					titres.add(new TitreMusical(idTitre, titreTitre, dateCreationTitre, dureeTitre, nomGenreTitre, albumTitre, null));
				}
				
				// Ajout a la liste retournee
				albums.add(new Album(idAlbum, nomAlbum, dureeAlbum, dateSortieAlbum, titres));						
			}
			return albums;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Album> rechercherParInterpreteAlbum(String recherche) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Album> rechercherParGenreAlbum(Genre genre) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Album> rechercherParDateSortieAlbum(Date date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Album> parcourirCatalogueAlbum() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/*
	 * Fonction getAlbum renvoie l'Album associé à l'id en parametre
	 */
	@Override
	public Album getAlbum(int id) {
		// On recupere une connexion de type java.sql.Connection
		Connection connexion = DBManager.getInstance().getConnection();
		
		try {
			// On execute la requete SQL et on recupere un java.sql.ResultSet
			String request = "CALL getAlbum(?);";
			
			// Prepared statement 
			PreparedStatement preparedQuery = connexion.prepareStatement(request);
			preparedQuery.setInt(1, id);
			
			// Retour
			ResultSet rs = preparedQuery.executeQuery();
			
			// Vrai s'il y a un resultat
			if(rs.next()) {
				// Creation de l'Album
				int idAlbum = rs.getInt("idCatalogue");
				String nomAlbum = rs.getString("nom");
				int dateSortieAlbum = rs.getInt("dateSortie"); 
				int dureeAlbum = rs.getInt("duree");
				
				// On recherche les titres de l'album
				String requestTitres = "CALL rechercherParIdAlbumTitres(?);";
				
				// Prepared statement 
				PreparedStatement preparedQueryTitres = connexion.prepareStatement(requestTitres);
				preparedQueryTitres.setInt(1, idAlbum);
				
				// Retour
				ResultSet rsTitres = preparedQueryTitres.executeQuery();
				//Creation de la liste de Titres
				List<TitreMusical> titres = new ArrayList<TitreMusical>();
				
				while(rsTitres.next()) {
					// Creation du Titre
					int idTitre = rsTitres.getInt("idCatalogue");
					String titreTitre = rsTitres.getString("titre");
					int dateCreationTitre = rsTitres.getInt("dateCreation"); 
					int dureeTitre = rsTitres.getInt("duree");
					String stringGenre = rsTitres.getString("nomGenre");
					Genre nomGenreTitre;
					if(stringGenre==null) {
						nomGenreTitre = Genre.INCONNU;
					}
					else {
						nomGenreTitre = Genre.valueOf(rsTitres.getString("nomGenre").toUpperCase());
					}
					Album albumTitre;
					if((rsTitres.getInt("Album_idCatalogue"))==0) {
						albumTitre=null;
					}else {
						albumTitre = new Album(rsTitres.getInt("Album_idCatalogue"));
					}
					
					titres.add(new TitreMusical(idTitre, titreTitre, dateCreationTitre, dureeTitre, nomGenreTitre, albumTitre, null));
				}
				
				// On retourne l'Album
				return new Album(idAlbum, nomAlbum, dureeAlbum, dateSortieAlbum, titres);						
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
	public String toString() {
		return "Administrateur [mail=" + mail + ", password=" + password + "]";
	}

}

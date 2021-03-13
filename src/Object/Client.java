package Object;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Interface.ClientInterface;

public class Client implements ClientInterface {

	private int id;
	private String mail;
	private String password;
	private String civilite;
	private String nom;
	private String prenom;
	private Date dateNaissance;
	private String adresseFacturation;
	private int nbEcoute;
	private Genre styleMusiquePrefere;
	private List<Playlist> playlists;
	
	// Constructeurs
	public Client(int id, String mail, String password, String civilite, String nom, String prenom, Date dateNaissance,
			String adresseFacturation, Genre styleMusiquePrefere) {
		super();
		this.id = id;
		this.mail = mail;
		this.password = password;
		this.civilite = civilite;
		this.nom = nom;
		this.prenom = prenom;
		this.dateNaissance = dateNaissance;
		this.adresseFacturation = adresseFacturation;
		this.nbEcoute = 0;
		this.styleMusiquePrefere = styleMusiquePrefere;
		this.playlists = new ArrayList<Playlist>();
	}

	public Client(int id, String mail, String password) {
		super();
		this.id = id;
		this.mail = mail;
		this.password = password;
		this.playlists = new ArrayList<Playlist>();
	}

	public Client(int id, String mail, String password, String civilite, String nom, String prenom, Date dateNaissance,
			String adresseFacturation, int nbEcoute, Genre styleMusiquePrefere, List<Playlist> playlists) {
		super();
		this.id=id;
		this.mail = mail;
		this.password = password;
		this.civilite = civilite;
		this.nom = nom;
		this.prenom = prenom;
		this.dateNaissance = dateNaissance;
		this.adresseFacturation = adresseFacturation;
		this.nbEcoute = nbEcoute;
		this.styleMusiquePrefere = styleMusiquePrefere;
		this.playlists = playlists;
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

	public String getCivilite() {
		return civilite;
	}

	public void setCivilite(String civilite) {
		this.civilite = civilite;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public Date getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public String getAdresseFacturation() {
		return adresseFacturation;
	}

	public void setAdresseFacturation(String adresseFacturation) {
		this.adresseFacturation = adresseFacturation;
	}

	public int getNbEcoute() {
		return nbEcoute;
	}

	public void setNbEcoute(int nbEcoute) {
		this.nbEcoute = nbEcoute;
	}

	public void setPlaylists(List<Playlist> playlists) {
		this.playlists = playlists;
	}

	public Genre getStyleMusiquePrefere() {
		return styleMusiquePrefere;
	}

	public void setStyleMusiquePrefere(Genre styleMusiquePrefere) {
		this.styleMusiquePrefere = styleMusiquePrefere;
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
			preparedQuery.setInt(1, this.getId());
			preparedQuery.setInt(2, elementCatalogue.getIdCatalogue());

            if(preparedQuery.executeUpdate()>0) // Recommandation effectuee
            {
            	this.setNbEcoute(this.getNbEcoute()+1);
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

	// Playlist
	/*
	 * Fonction creerPlaylist, creation d'une nouvelle Playlist dans la BDD
	 * Renvoie l'objet Playlist si succes, null sinon
	 */
	@Override
	public Playlist creerPlaylist(String nomPlaylist, List<TitreMusical> titresMusicaux) {
		// On recupere une connexion de type java.sql.Connection
		Connection connexion = DBManager.getInstance().getConnection();
		
		try {
			
			// On execute la requete SQL et recupere un java.sql.ResultSet
			String request = "SELECT nouvelle_playlist(?, ?);";
			
			// Prepared statement 
			PreparedStatement preparedQuery = connexion.prepareStatement(request);
			preparedQuery.setInt(1, this.getId());
			preparedQuery.setString(2, nomPlaylist);
			
			// Execution
			ResultSet rs = preparedQuery.executeQuery();
			
	        if(rs.next()) // Ajout des elementsCatalogue
	        {
	            int last_inserted_id = rs.getInt(1); // Id de la playlist creee
                
                // Association elementsCatalogue
                String requestAssociation = "CALL association_playlist_elementsPlaylist(?, ?);";
                
                // Prepared statement 
    			PreparedStatement preparedQueryAssociation = connexion.prepareStatement(requestAssociation);
    			
    			preparedQueryAssociation.setInt(2, last_inserted_id); // Id de la playlist
    			
    			if(titresMusicaux!=null) {
    				for(ElementCatalogue titreMusical : titresMusicaux){ // On associe chaque elementCatalogue a la playlist
    					
    					preparedQueryAssociation.setInt(1, titreMusical.getIdCatalogue());

    					preparedQueryAssociation.executeUpdate();
    				}
    			}
    			Playlist playlist = new Playlist(last_inserted_id, nomPlaylist, this, titresMusicaux);
    			if(this.playlists!=null) {
    				this.playlists.add(playlist);
    			}
    			else {
    				this.playlists = new ArrayList<Playlist>();
    				this.playlists.add(playlist);
    			}
    			return playlist;				
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
	 * Fonction supprimerPlaylist, retire la Playlist de la BD
	 * Renvoie true si la suppression a lieu, false sinon
	 */
	@Override
	public boolean supprimerPlaylist(Playlist playlist) {
		// Recuperer la connexion
		Connection connexion = DBManager.getInstance().getConnection();
		
		try {
			
			// Maj BDD
			String request = "CALL supprimer_playlist(?);";
			
			// Prepared statement 
			PreparedStatement preparedQuery = connexion.prepareStatement(request);
			preparedQuery.setInt(1, playlist.getIdPlaylist());
			
			// Execution
			if(preparedQuery.executeUpdate()>0) { // Succes de la suppression
				return true;
			}
			else{ // La suppression echoue
				return false;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	/*
	 * Fonction changerNomPlaylist, met a jour les informations de la Playlist dans la BD puis dans l'objet
	 * Renvoie true si la modification a lieu, false sinon
	 */
	@Override
	public boolean changerNomPlaylist(Playlist playlist, String nom) {
		// Recuperer la connexion
		Connection connexion = DBManager.getInstance().getConnection();
		
		try {
			
			// Maj BDD
			String request = "CALL modifier_playlist(?, ?);";
			
			// Prepared statement 
			PreparedStatement preparedQuery = connexion.prepareStatement(request);
			preparedQuery.setInt(1, playlist.getIdPlaylist());
			preparedQuery.setString(2, nom);
			
			// Execution
			if(preparedQuery.executeUpdate()>0) { // Succes de la modification
				
				// Maj Objet
				playlist.setNomPlaylist(nom);				
				return true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	/*
	 * Fonction ajouterTitrePlaylist, ajoute le titre a la Playlist dans la BD
	 * Renvoie true et associe l'instance titreMusical a l'instance playlist si l'ajout a lieu, false sinon
	 */
	@Override
	public boolean ajouterTitrePlaylist(TitreMusical titreMusical, Playlist playlist) {
		// On recupere une connexion de type java.sql.Connection
		Connection connexion = DBManager.getInstance().getConnection();
		
		try {
			
			// On execute la requete SQL et on recupere un java.sql.ResultSet
			String request = "CALL association_playlist_elementsPlaylist(?, ?);";
			
			// Prepared statement 
			PreparedStatement preparedQuery = connexion.prepareStatement(request);
			preparedQuery.setInt(1, titreMusical.getIdCatalogue());
			preparedQuery.setInt(2, playlist.getIdPlaylist());

			// Execution
			if(preparedQuery.executeUpdate()>0) {
				titreMusical.ajouterPlaylist(playlist); // On ajoute la Playlist
				playlist.ajouterTitre(titreMusical); // On ajoute le titre
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;	
	}
	
	/*
	 * Fonction retirerTitrePlaylist, retire le titre de la Playlist dans la BD
	 * Renvoie true et dissocie l'instance titreMusical d l'instance playlist si la suppression a lieu, false sinon
	 */
	@Override
	public boolean retirerTitrePlaylist(TitreMusical titreMusical, Playlist playlist) {
		// On recupere une connexion de type java.sql.Connection
		Connection connexion = DBManager.getInstance().getConnection();
		
		try {
			
			// On execute la requete SQL et on recupere un java.sql.ResultSet
			String request = "CALL dissociation_titre_playlist(?, ?);";
			
			// Prepared statement 
			PreparedStatement preparedQuery = connexion.prepareStatement(request);
			preparedQuery.setInt(1, titreMusical.getIdCatalogue());
			preparedQuery.setInt(2, playlist.getIdPlaylist());

			// Execution
			if(preparedQuery.executeUpdate()>0) {
				playlist.retirerTitre(titreMusical); // On supprime le titre
				titreMusical.retirerPlaylist(playlist); // On retire la Playlist
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;	
	}


	/*
	 * Fonction modifierInformations, met à jour les informations du client dans la BD puis dans l'objet
	 * Renvoie true si la modification a lieu, false sinon
	 */
	@Override
	public Client modifierInformations(String mail, String password, String civilite, String nom, String prenom,
			Date dateNaissance, String adresseFacturation, Genre styleMusiquePrefere) {
		// Recuperer la connexion
		Connection connexion = DBManager.getInstance().getConnection();
		
		try {
			
			// Maj BDD
			String request = "CALL modifier_client(?, ?, ?, ?, ?, ?, ?, ?, ?);";
			
			// Prepared statement 
			PreparedStatement preparedQuery = connexion.prepareStatement(request);
			preparedQuery.setInt(1, this.getId());
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
				this.setPassword(mail);
				this.setPassword(password);
				this.setCivilite(civilite);
				this.setNom(nom);
				this.setPrenom(prenom);
				this.setDateNaissance(dateNaissance);
				this.setAdresseFacturation(adresseFacturation);
				this.setStyleMusiquePrefere(styleMusiquePrefere);
				
				return this;
			}
			else{ // La mise a jour echoue
				return null;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/*
	 * Fonction getPlaylists renvoie la liste des Playlists du Client
	 */
	@Override
	public List<Playlist> getPlaylists(){
		// Recuperer la connexion
		Connection connexion = DBManager.getInstance().getConnection();
		
		try {
			
			// Maj BDD
			String request = "CALL getPlaylists(?);";
			
			// Prepared statement 
			PreparedStatement preparedQuery = connexion.prepareStatement(request);
			preparedQuery.setInt(1, this.getId());
			
			// Retour
			ResultSet rs = preparedQuery.executeQuery();
			
			List<Playlist> playlists = new ArrayList<Playlist>();
			// Vrai tant qu'il y a un resultat
			while(rs.next()) {
				// Creation de la Playlist
				int idPlaylist = rs.getInt("idPlaylist");
				String nomPlaylist = rs.getString("nomPlaylist");
				
				// On recherche les titres de la Playlist
				String requestTitres = "CALL rechercherParIdPlaylistTitres(?);";
				
				// Prepared statement 
				PreparedStatement preparedQueryTitres = connexion.prepareStatement(requestTitres);
				preparedQueryTitres.setInt(1, idPlaylist);
				
				// Retour
				ResultSet rsTitres = preparedQueryTitres.executeQuery();
				//Creation de la liste des Titres
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
				
				// Ajout a la liste retournee
				playlists.add(new Playlist(idPlaylist, nomPlaylist, this, titres));		
			}
			
			return playlists;
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
							titres.add(new TitreMusical(idTitre, titreTitre, dateCreationTitre, dureeTitre, nomGenre, albumTitre, null));						
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
	public List<TitreMusical> rechercherParInterpreteTitre(String recherche) {
		// TODO Auto-generated method stub
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
	
	@Override
	public String toString() {
		return "Client [mail=" + mail + ", civilite=" + civilite + ", nom=" + nom
				+ ", prenom=" + prenom + ", dateNaissance=" + dateNaissance + ", adresseFacturation="
				+ adresseFacturation + ", styleMusiquePrefere=" + styleMusiquePrefere + "]";
	}

}

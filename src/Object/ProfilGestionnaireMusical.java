package Object;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Interface.ProfilGestionnaireMusicalInterface;

public class ProfilGestionnaireMusical extends Administrateur implements ProfilGestionnaireMusicalInterface{


	public ProfilGestionnaireMusical(int id, String mail, String password) {
		super(id, mail,password);
	}
	
	public ProfilGestionnaireMusical(String mail, String password) {
		super((int) (Math.random()*1000),mail,password);
	}

	
	/*
	 * Fonction creerAdmin ajoute un administrateur ayant les droits de GestionnaireMusical
	 * Renvoie l'objet ProfilGestionnaireMusical correspondant si l'insertion � la BDD reussit, null sinon
	 */
	@Override
	public Administrateur creerAdmin(String mail, String password) {
		// On recupere une connexion de type java.sql.Connection
		Connection connexion = DBManager.getInstance().getConnection();
		
		try {
			
			String requestVerification = "CALL existe_adminMusique(?);";
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
				preparedQuery.setBoolean(3, false); // Profil gestion Client
				preparedQuery.setBoolean(4, true); // Profil gestion Musique
				
				ResultSet rs = preparedQuery.executeQuery();

				if(rs.next()) // Creation du Client
	            {
	                int last_inserted_id = rs.getInt(1); // Id du Client cree
	                
	                return new ProfilGestionnaireMusical(last_inserted_id, mail, password);
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

	// Methodes specifiques a la classe
	
	// Interprete
	/*
	 * Fonction creerInterprete, creation d'un nouveau Interprete dans la BDD
	 * Renvoie l'objet Interprete si succ�s, null sinon
	 */
	@Override
	public Interprete creerInterprete(String pseudo) {
		// On recupere une connexion de type java.sql.Connection
		Connection connexion = DBManager.getInstance().getConnection();
		
		try {
			
			// On execute la requete SQL et recupere un java.sql.ResultSet
			String request = "SELECT nouveau_interprete(?);";
			
			// Prepared statement 
			PreparedStatement preparedQuery = connexion.prepareStatement(request);
			preparedQuery.setString(1, pseudo);
			
			// Execution
			ResultSet rs = preparedQuery.executeQuery();
			
            if(rs.next()) // Ajout des interpretes
            {
                int last_inserted_id = rs.getInt(1); // Id de l'interprete cree
                return new Interprete(last_inserted_id, pseudo);
				
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
	 * Fonction creerInterprete, creation d'un nouveau Interprete dans la BDD
	 * Renvoie l'objet Interprete si succ�s, null sinon
	 */
	@Override
	public Interprete creerInterprete(String pseudo, String prenom, String nom, Date dateNaissance) {
		// On recupere une connexion de type java.sql.Connection
		Connection connexion = DBManager.getInstance().getConnection();
		
		try {
			
			// On execute la requete SQL et recupere un java.sql.ResultSet
			String request = "SELECT nouveau_interprete_complet(?, ?, ?, ?);";
			
			// Prepared statement 
			PreparedStatement preparedQuery = connexion.prepareStatement(request);
			preparedQuery.setString(1, pseudo);
			preparedQuery.setString(2, prenom);
			preparedQuery.setString(3, nom);
			preparedQuery.setDate(4, (java.sql.Date) dateNaissance);
			
			// Execution
			ResultSet rs = preparedQuery.executeQuery();
			
            if(rs.next()) // Ajout des interpretes
            {
                int last_inserted_id = rs.getInt(1); // Id de l'interprete cree
				return new Interprete(last_inserted_id, pseudo, prenom, nom, dateNaissance);
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
	 * Fonction modifierInterprete, met � jour les informations de l'interprete dans la BD puis dans l'objet
	 * Renvoie true si la modification a lieu, false sinon
	 */
	@Override
	public boolean modifierInterprete(Interprete interprete, String pseudonyme, String prenom, String nom, Date dateNaissance) {
		// Recuperer la connexion
		Connection connexion = DBManager.getInstance().getConnection();
		
		try {
			
			// Maj BDD
			String request = "CALL modifier_interprete(?, ?, ?, ?, ?);";
			
			// Prepared statement 
			PreparedStatement preparedQuery = connexion.prepareStatement(request);
			preparedQuery.setInt(1, interprete.getId());
			preparedQuery.setString(2, pseudonyme);
			preparedQuery.setString(3, prenom);
			preparedQuery.setString(4, nom);
			preparedQuery.setDate(5, dateNaissance);
			
			// Execution
			if(preparedQuery.executeUpdate()>0) { // Succes de la modification
				
				// Maj Objet
				interprete.setPseudonyme(pseudonyme);
				interprete.setPrenom(prenom);
				interprete.setNom(nom);
				interprete.setDateNaissance(dateNaissance);
				
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

	/*
	 * Fonction supprimerInterprete, retire l'interprete de la BD
	 * Renvoie true si la suppression a lieu, false sinon
	 */
	@Override
	public boolean supprimerInterprete(Interprete interprete) {
		// Recuperer la connexion
		Connection connexion = DBManager.getInstance().getConnection();
		
		try {
			
			// Maj BDD
			String request = "CALL supprimer_interprete(?);";
			
			// Prepared statement 
			PreparedStatement preparedQuery = connexion.prepareStatement(request);
			preparedQuery.setInt(1, interprete.getId());
			
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
	
	
	// TitreMusical
	/*
	 * Fonction creerTitreMusical, creation d'un nouveau TitreMusical dans la BDD
	 * Renvoie l'objet TitreMusical si succ�s, null sinon
	 */
	@Override
	public TitreMusical creerTitre(String titre, int anneeCreation, List<Interprete> interpretes, int duree, Genre genre) {
		// On recupere une connexion de type java.sql.Connection
		Connection connexion = DBManager.getInstance().getConnection();
		
		try {
			
			// On execute la requete SQL et on recupere un java.sql.ResultSet
			String request = "SELECT nouveau_titre(?, ?, ?, ?);";
			
			// Prepared statement ajout titre
			PreparedStatement preparedQuery = connexion.prepareStatement(request, Statement.RETURN_GENERATED_KEYS);
			preparedQuery.setString(1, titre);
			preparedQuery.setInt(2, anneeCreation);
			preparedQuery.setInt(3, duree);
			preparedQuery.setString(4, genre.toString()); // Le genre doit exister dans la BDD
			
			ResultSet rs = preparedQuery.executeQuery();
			
            if(rs.next()) // Ajout des interpretes
            {
                int last_inserted_id = rs.getInt(1); // Id du TitreMusical cree
                
                String requestInterprete = "CALL association_titre_interprete(?, ?);";
                
                // Prepared statement association interpretes
    			PreparedStatement preparedQueryInterprete = connexion.prepareStatement(requestInterprete);
    			preparedQueryInterprete.setInt(1, last_inserted_id);
    			
    			TitreMusical nouveauTitre = new TitreMusical(last_inserted_id, titre, anneeCreation, duree, genre, interpretes); // Creation du titre
    			if(interpretes!=null) {
    				for(Interprete interprete : interpretes){ // On associe chaque interprete au titre
    					
    					interprete.addTitre(nouveauTitre);
    					
    					preparedQueryInterprete.setInt(2, interprete.getId());

    					preparedQueryInterprete.executeUpdate();
    				}
    			}
				
				
				return nouveauTitre;
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
	 * Fonction modifierTitre, met � jour les informations du Titre dans la BD puis dans l'objet
	 * Renvoie true si la modification a lieu, false sinon
	 */
	@Override
	public boolean modifierTitre(TitreMusical titreMusical, String titre, int anneeCreation, int duree, Genre genre) {
		// Recuperer la connexion
		Connection connexion = DBManager.getInstance().getConnection();
		
		try {
			
			// Maj BDD
			String request = "CALL modifier_titre(?, ?, ?, ?, ?);";
			
			// Prepared statement 
			PreparedStatement preparedQuery = connexion.prepareStatement(request);
			preparedQuery.setInt(1, titreMusical.getIdCatalogue());
			preparedQuery.setString(2, titre);
			preparedQuery.setInt(3, anneeCreation);
			preparedQuery.setInt(4, duree);
			preparedQuery.setString(5, genre.toString());
			
			
			// Execution
			if(preparedQuery.executeUpdate()>0) { // Succes de la modification
				
				// Maj Objet
				titreMusical.setTitre(titre);
				titreMusical.setAnneeCreation(anneeCreation);
				titreMusical.setDuree(duree);
				titreMusical.setGenre(genre);
				
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

	/*
	 * Fonction supprimerTitre, retire le Titre de la BD
	 * Renvoie true si la suppression a lieu, false sinon
	 */
	@Override
	public boolean supprimerTitre(TitreMusical titreMusical) {
		// Recuperer la connexion
		Connection connexion = DBManager.getInstance().getConnection();
		
		try {
			
			// Maj BDD
			String request = "CALL supprimer_titre(?);";
			
			// Prepared statement 
			PreparedStatement preparedQuery = connexion.prepareStatement(request);
			preparedQuery.setInt(1, titreMusical.getIdCatalogue());
			
			// Execution
			if(preparedQuery.executeUpdate()>0) { // Succes de la suppression
				for(Interprete interprete : titreMusical.getInterpretes()) {
					interprete.getTitres().remove(titreMusical);
				}
				if(titreMusical.getAlbum()!=null) {
					if(titreMusical.getAlbum().getTitres()!=null) {
						titreMusical.getAlbum().getTitres().remove(titreMusical);
					}
				}
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
	 * Fonction ajouterDiscographie, associe l'interprete au titre dans la BD
	 * Renvoie true et associe l'instance titre a l'instance interprete si l'ajout a lieu, false sinon
	 */
	@Override
	public boolean ajouterDiscographie(TitreMusical titre, Interprete interprete) {
		// On recupere une connexion de type java.sql.Connection
		Connection connexion = DBManager.getInstance().getConnection();
		
		try {
			
			// On execute la requete SQL et on recupere un java.sql.ResultSet
			String request = "CALL association_titre_interprete(?, ?);";
			
			// Prepared statement 
			PreparedStatement preparedQuery = connexion.prepareStatement(request);
			preparedQuery.setInt(1, titre.getIdCatalogue());
			preparedQuery.setInt(2, interprete.getId());

			// Execution
			if(preparedQuery.executeUpdate()>0) {
				if(titre.getInterpretes()==null) {
					titre.setInterpretes(new ArrayList<Interprete>());
				}
				titre.getInterpretes().add(interprete); // On ajoute l'interprete
				if(interprete.getTitres()==null) {
					interprete.setTitres(new ArrayList<TitreMusical>());
				}
				interprete.getTitres().add(titre); // On ajoute le titre			
				
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;		
	}
	
	/*
	 * Fonction retirerDiscographie, dissocie l'interprete du titre dans la BD
	 * Renvoie true et dissocie l'instance titre de l'instance interprete si la suppression a lieu, false sinon
	 */
	@Override
	public boolean retirerDiscographie(TitreMusical titre, Interprete interprete) {
		// On recupere une connexion de type java.sql.Connection
		Connection connexion = DBManager.getInstance().getConnection();
		
		try {
			
			// On execute la requete SQL et on recupere un java.sql.ResultSet
			String request = "CALL dissociation_titre_interprete(?, ?);";
			
			// Prepared statement 
			PreparedStatement preparedQuery = connexion.prepareStatement(request);
			preparedQuery.setInt(1, titre.getIdCatalogue());
			preparedQuery.setInt(2, interprete.getId());

			// Execution
			if(preparedQuery.executeUpdate()>0) {
				titre.getInterprete().remove(interprete); // On ajoute l'interprete
				interprete.getTitres().remove(titre); // On ajoute le titre
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;	
	}
	
	// Album
	/*
	 * Fonction creerAlbum, creation d'un nouveau Album dans la BDD
	 * Renvoie l'objet Album si succes, null sinon
	 */
	@Override
	public Album creerAlbum(String nom, int anneeSortie, List<TitreMusical> titres) {
		
		// On recupere une connexion de type java.sql.Connection
		Connection connexion = DBManager.getInstance().getConnection();
		
		try {
			
			// On execute la requete SQL et on recupere un java.sql.ResultSet
			String request = "SELECT nouveau_album(?, ?);";
			
			// Prepared statement ajout Album
			PreparedStatement preparedQuery = connexion.prepareStatement(request);
			preparedQuery.setString(1, nom);
			preparedQuery.setInt(2, anneeSortie);
			
			ResultSet rs = preparedQuery.executeQuery();
			
            if(rs.next()) // Ajout des Titres
            {
                int last_inserted_id = rs.getInt(1); // Id de l'album cree
                
                // Association titres
                int dureeAlbum = 0;
                String requestTitre = "CALL ajout_titre_album(?, ?);";
                
                // Prepared statement 
    			PreparedStatement preparedQueryTitres = connexion.prepareStatement(requestTitre);
    			
    			preparedQueryTitres.setInt(2, last_inserted_id); // Id de l'album
    			
    			if(titres!=null) {
    				for(TitreMusical titre : titres){ // On associe chaque interprete au titre
    					
    					preparedQueryTitres.setInt(1, titre.getIdCatalogue());

    					preparedQueryTitres.executeUpdate();
    					
    					dureeAlbum+=titre.getDuree();
    				}
    			}
								
    			// On execute la requete SQL et on recupere un java.sql.ResultSet
    			String requestMajDuree = "CALL majDureeAlbum(?, ?);";
    			
    			// Prepared statement ajout Album
    			PreparedStatement preparedMajDuree = connexion.prepareStatement(requestMajDuree);
    			preparedMajDuree.setInt(1, last_inserted_id);
    			preparedMajDuree.setInt(2, dureeAlbum);
    			
    			preparedMajDuree.executeUpdate();
				return new Album(last_inserted_id, nom, dureeAlbum, anneeSortie, titres);
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
	 * Fonction modifierAlbum, met � jour les informations de l'Album dans la BD puis dans l'objet
	 * Renvoie true si la modification a lieu, false sinon
	 */
	@Override
	public boolean modifierAlbum(Album album, String nom, int anneeSortie) {					
		// Recuperer la connexion
		Connection connexion = DBManager.getInstance().getConnection();
		
		try {
			
			// Maj BDD
			String request = "CALL modifier_album(?, ?, ?);";
			
			// Prepared statement 
			PreparedStatement preparedQuery = connexion.prepareStatement(request);
			preparedQuery.setInt(1, album.getIdCatalogue());
			preparedQuery.setString(2, nom);
			preparedQuery.setInt(3, anneeSortie); 		
			
			// Execution
			if(preparedQuery.executeUpdate()>0) { // Succes de la modification
				
				// Maj Objet
				album.setNom(nom);
				album.setDateSortie(anneeSortie);
				
				return true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	/*
	 * Fonction supprimerAlbum, retire l'Album de la BD
	 * Renvoie true si la suppression a lieu, false sinon
	 */
	@Override
	public boolean supprimerAlbum(Album album) {
		// Recuperer la connexion
		Connection connexion = DBManager.getInstance().getConnection();
		
		try {
			
			// Maj BDD
			String request = "CALL supprimer_album(?);";
			
			// Prepared statement 
			PreparedStatement preparedQuery = connexion.prepareStatement(request);
			preparedQuery.setInt(1, album.getIdCatalogue());
			
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
	 * Fonction ajoutTitreAlbum, ajoute le titre � l'album dans la BD
	 * Renvoie true et associe l'instance album a l'instance titre si l'ajout a lieu, false sinon
	 */
	@Override
	public boolean ajoutTitreAlbum(TitreMusical titre, Album album) {
		// On recupere une connexion de type java.sql.Connection
		Connection connexion = DBManager.getInstance().getConnection();
		
		try {
			
			// On execute la requete SQL et on recupere un java.sql.ResultSet
			String request = "CALL ajout_titre_album(?, ?);";
			
			// Prepared statement 
			PreparedStatement preparedQuery = connexion.prepareStatement(request);
			preparedQuery.setInt(1, titre.getIdCatalogue());
			preparedQuery.setInt(2, album.getIdCatalogue());

			// Execution
			if(preparedQuery.executeUpdate()>0) {
				album.ajouterTitre(titre);  // On ajoute le titre
				titre.setAlbum(album); // On ajoute l'album
				album.setDuree(album.getDuree()+titre.getDuree()); // On ajoute la duree du titre a l'album
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;	
	}
	
	/*
	 * Fonction suppressionTitreAlbum, supprime le titre de l'album dans la BD
	 * Renvoie true et dissocie l'instance album de l'instance titre si l'ajout a lieu, false sinon
	 */
	@Override
	public boolean suppressionTitreAlbum(TitreMusical titre, Album album) {
		// On recupere une connexion de type java.sql.Connection
		Connection connexion = DBManager.getInstance().getConnection();
		
		try {
			
			// On execute la requete SQL et on recupere un java.sql.ResultSet
			String request = "CALL dissociation_titre_album(?, ?);";
			
			// Prepared statement 
			PreparedStatement preparedQuery = connexion.prepareStatement(request);
			preparedQuery.setInt(1, titre.getIdCatalogue());
			preparedQuery.setInt(2, album.getIdCatalogue());

			// Execution
			if(preparedQuery.executeUpdate()>0) {
				album.retirerTitre(titre); // On supprime le titre
				titre.setAlbum(null); // On retire l'album
				album.setDuree(album.getDuree()-titre.getDuree()); // On retire la duree du titre a l'album
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;	
	}
	//TODO -- En travaux



	@Override
	public Radio creerRadio(String nom, Genre genreMusical) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Podcast creerPodcast(String titre, int duree, String PseudoAuteur, String categorie) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * Fonction recommander renvoie true si l'elementCatalogue est mis en recommande,
	 * et false en cas d'echec
	 */
	@Override
	public boolean recommander(ElementCatalogue elementCatalogue) {
		// On recupere une connexion de type java.sql.Connection
		Connection connexion = DBManager.getInstance().getConnection();
		
		try {
			
			// On execute la requete SQL et recupere un java.sql.ResultSet
			String request = "CALL recommander(?);";
			
			// Prepared statement 
			PreparedStatement preparedQuery = connexion.prepareStatement(request);
			preparedQuery.setInt(1, elementCatalogue.getIdCatalogue());

            if(preparedQuery.executeUpdate()>0) // Recommandation effectuee
            {
            	elementCatalogue.setRecommande(true);
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
	 * Fonction recommander_annuler renvoie true si l'elementCatalogue est bien retire des recommandes,
	 * et false en cas d'echec
	 */
	@Override
	public boolean recommander_annuler(ElementCatalogue elementCatalogue) {
		// On recupere une connexion de type java.sql.Connection
		Connection connexion = DBManager.getInstance().getConnection();
		
		try {
			
			// On execute la requete SQL et recupere un java.sql.ResultSet
			String request = "CALL recommander_annuler(?);";
			
			// Prepared statement 
			PreparedStatement preparedQuery = connexion.prepareStatement(request);
			preparedQuery.setInt(1, elementCatalogue.getIdCatalogue());

            if(preparedQuery.executeUpdate()>0) // Recommandation effectuee
            {
            	elementCatalogue.setRecommande(false);
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
	public boolean modifierRadio(Radio radio, String nom, Genre genreMusical) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean supprimerRadio(Radio radio) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean modifierPodcast(String titre, int duree, String PseudoAuteur, String categorie) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean supprimerPodcast(Podcast podcast) {
		// TODO Auto-generated method stub
		return false;
	}
}

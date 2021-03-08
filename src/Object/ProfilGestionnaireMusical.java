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
	/*
	 * Fonction authentification v�rifie l'existence du couple mail, password ayant les droits de GestionnaireMusical
	 * Renvoie l'objet ProfilGestionnaireMusical correspondant s'il est trouv�, null sinon
	 */
	@Override
	public Administrateur authentification(String mail, String password) {
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
			if(rs.next()) {
				return new ProfilGestionnaireMusical(mail, password);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * Fonction creerAdmin ajoute un administrateur ayant les droits de GestionnaireMusical
	 * Renvoie l'objet ProfilGestionnaireMusical correspondant si l'insertion � la BDD reussit, null sinon
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
	
	/*
	 * Fonction modifierInterprete, met � jour les informations de l'interprete dans la BD puis dans l'objet
	 * Renvoie true si la modification a lieu, false sinon
	 */
	@Override
	public boolean modifierInterprete(Interprete interprete, String prenom, String nom, Date dateNaissance) {
		// Recuperer la connexion
		Connection connexion = DBManager.getInstance().getConnection();
		
		try {
			
			// Maj BDD
			String request = "CALL modifier_interprete(?, ?, ?, ?);";
			
			// Prepared statement 
			PreparedStatement preparedQuery = connexion.prepareStatement(request);
			preparedQuery.setString(1, interprete.getPseudonyme());
			preparedQuery.setString(2, prenom);
			preparedQuery.setString(3, nom);
			preparedQuery.setDate(4, dateNaissance);
			
			// Execution
			if(preparedQuery.executeUpdate()>0) { // Succes de la modification
				
				// Maj Objet
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
					preparedQuery.setString(1, interprete.getPseudonyme());
					
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
	public TitreMusical creerTitre(String titre, Year anneeCreation, List<Interprete> interpretes, int duree, Genre genre) {
		// On recupere une connexion de type java.sql.Connection
		Connection connexion = DBManager.getInstance().getConnection();
		
		try {
			
			// On execute la requete SQL et on recupere un java.sql.ResultSet
			String request = "CALL nouveau_titre(?, ?, ? ?);";
			
			// Prepared statement ajout titre
			PreparedStatement preparedQuery = connexion.prepareStatement(request);
			preparedQuery.setString(1, titre);
			preparedQuery.setString(2, anneeCreation.toString());
			preparedQuery.setInt(3, duree);
			preparedQuery.setString(4, genre.toString()); // Le genre doit exister dans la BDD
			
			preparedQuery.executeUpdate();
			
			ResultSet rs = preparedQuery.getGeneratedKeys();
            if(rs.next()) // Ajout des interpretes
            {
                int last_inserted_id = rs.getInt(1); // Id du TitreMusical cree
                
                String requestInterprete = "CALL association_titre_interprete(?, ?);";
                
                // Prepared statement association interpretes
    			PreparedStatement preparedQueryInterprete = connexion.prepareStatement(requestInterprete);
    			preparedQueryInterprete.setInt(1, last_inserted_id);
    			
				for(Interprete interprete : interpretes){ // On associe chaque interprete au titre
					
					preparedQueryInterprete.setString(2, interprete.getPseudonyme());

					preparedQueryInterprete.executeUpdate();
				}
				
				return new TitreMusical(last_inserted_id, titre, anneeCreation, duree, genre, interpretes);
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


	/*@Override
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
	}*/

	@Override
	public Album creerAlbum(String nom, String anneeSortie, List<TitreMusical> titres) {
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
		return null;
	}

	@Override
	public boolean ajoutTitreAlbum(TitreMusical titre, Album album) {
		// Récupérer une connexion de type java.sql.Connection
		Connection connexion = DBManager.getInstance().getConnection();
		
		try {
			
			// Exécuter la requête SQL et récupérer un java.sql.ResultSet
			String request = "CALL ajout_titre_album(?, ?);";
			
			// Prepared statement 
			PreparedStatement preparedQuery = connexion.prepareStatement(request);
			preparedQuery.setInt(1, titre.getIdCatalogue());
			preparedQuery.setInt(2, album.getIdCatalogue());

			// Execution
			preparedQuery.executeUpdate();
			
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

	@Override
	public boolean recommander(ElementCatalogue elementCatalogue) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<TitreMusical> topTitresEcoutes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Client> topUtilisateursEcoutes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean modifierTitre(TitreMusical titreMusical, String titre, Year anneeCreation,
			List<Interprete> interpretes, int duree, Genre genre) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean supprimerTitre(TitreMusical titreMusical) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void ajoutDiscographie(TitreMusical titre, Interprete interprete) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean suppressionTitreAlbum(TitreMusical titre, Album album) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void ajoutAlbumInterprete(Album album, Interprete interprete) {
		// TODO Auto-generated method stub
		
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

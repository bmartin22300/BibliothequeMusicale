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
					Genre nomGenre = Genre.valueOf(rsTitres.getString("nomGenre").toUpperCase());
					Album albumTitre;
					if((rs.getInt("Album_idCatalogue"))==0) {
						albumTitre=null;
					}else {
						albumTitre = new Album(rs.getInt("Album_idCatalogue"));
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
					Genre nomGenre = Genre.valueOf(rsTitres.getString("nomGenre").toUpperCase());
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
					Genre nomGenre = Genre.valueOf(rsTitres.getString("nomGenre").toUpperCase());
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
						Genre nomGenre = Genre.valueOf(rs.getString("nomGenre").toUpperCase());
						Album albumTitre = new Album(rs.getInt("Album_idCatalogue"));
						
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

	// TitreMusical
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

	@Override
	public List<Client> rechercherParMailClient(String recherche) {
		// TODO Auto-generated method stub
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

	@Override
	public List<ElementCatalogue> rechercherParNomCatalogue(String recherche) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ElementCatalogue> rechercherParInterpreteCatalogue(String recherche) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ElementCatalogue> rechercherParGenreCatalogue(Genre genre) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ElementCatalogue> rechercherParDateSortieCatalogue(Date date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ElementCatalogue> parcourirCatalogueCatalogue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Album> rechercherParNomAlbum(String recherche) {
		// TODO Auto-generated method stub
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
	
	@Override
	public String toString() {
		return "Administrateur [mail=" + mail + ", password=" + password + "]";
	}

}

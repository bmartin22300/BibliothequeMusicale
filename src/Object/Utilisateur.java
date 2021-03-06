package Object;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import Database.DBManager;
import Interface.UtilisateurInterface;

public class Utilisateur implements UtilisateurInterface {
	
	/*public void ecouterMorceau(TitreMusical titreMusical) {
		
	}*/

	@Override
	public void creerCompte(String mail, String password, String civilite, String nom, String prenom,
		String dateNaissance, String adresseFacturation, String styleMusiquePrefere) {
	
		Statement statement=null;
		
		// Récupérer une connexion de type java.sql.Connection
		Connection connexion = DBManager.getInstance().getConnection();
		
		try {
			// Créer un java.sql.Statement depuis cette connexion
			statement = connexion.createStatement();
			
			// Exécuter la requête SQL et récupérer un java.sql.ResultSet
			String request = "call nouveau_client('"+ mail +"','"+ password +"','"+ civilite +"','"+ nom +"','"+ prenom +"','"+ dateNaissance +"','"+ adresseFacturation +"','"+ styleMusiquePrefere +"');";
			statement.executeQuery(request);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
}

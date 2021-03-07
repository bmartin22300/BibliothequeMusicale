package Interface;

import Object.Administrateur;

public interface AdministrateurInterface {

	public boolean authentification(String mail, String password);
	
	public Administrateur creerAdmin(String mail, String password);
}

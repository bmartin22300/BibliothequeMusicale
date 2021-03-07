package Interface;

public interface AdministrateurInterface {

	public boolean authentification(String mail, String password);
	
	public void creerAdmin(String mail, String password);
}

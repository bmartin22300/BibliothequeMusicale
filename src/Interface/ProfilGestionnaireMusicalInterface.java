package Interface;

public interface ProfilGestionnaireMusicalInterface extends AdministrateurInterface {

	public void creerTitre(String titre, String anneeCreation, String genre);
	//public void creerTitre(String titre, String anneeCreation, Genre genre);?
	
	public void creerInterprete(String pseudo);
	
	public void creerInterprete(String pseudo, String prenom, String nom, String dateNaissance);
	
	public void ajoutDiscographie(int idTitre, String pseudoInterprete);
	//public void ajoutDiscographie(TitreMusical titre, Interprete interprete);?
	//--> public void ajoutDiscographie(TitreMusical idTitre, List<Interprete> interpretes);
	
	public void creerAlbum(String nom, String anneeSortie);
	
	public void ajoutTitreAlbum(int idTitre, int idAlbum);
	
	
	//TODO
	public void creerPlaylist(String nom, String mail);
	
	public void ajoutElementCataloguePlaylist(int idCatalogue, int idPlaylist);
	

}

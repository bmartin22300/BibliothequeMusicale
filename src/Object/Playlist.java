package Object;

import java.util.ArrayList;
import java.util.List;

public class Playlist {
	private int idPlaylist;
	private String nomPlaylist;
	private Client createur;
	private List<TitreMusical> titresMusicaux;
	
	// Constructeurs
	public Playlist(int idPlaylist, String nomPlaylist, Client createur, List<TitreMusical> titresMusicaux) {
		super();
		this.idPlaylist = idPlaylist;
		this.nomPlaylist = nomPlaylist;
		this.createur = createur;
		this.titresMusicaux = titresMusicaux;
	}
	
	// Getters et Setters
	public int getIdPlaylist() {
		return idPlaylist;
	}

	public void setIdPlaylist(int idPlaylist) {
		this.idPlaylist = idPlaylist;
	}

	public String getNomPlaylist() {
		return nomPlaylist;
	}

	public void setNomPlaylist(String nomPlaylist) {
		this.nomPlaylist = nomPlaylist;
	}

	public Client getCreateur() {
		return createur;
	}

	public void setCreateur(Client createur) {
		this.createur = createur;
	}

	public List<TitreMusical> getTitresMusicaux() {
		return titresMusicaux;
	}

	public void setTitresMusicaux(List<TitreMusical> titresMusicaux) {
		this.titresMusicaux = titresMusicaux;
	}
	
	public void ajouterTitre(TitreMusical titre) {
		if(this.titresMusicaux!=null) {
			this.titresMusicaux.add(titre);
		}
		else {
			this.titresMusicaux = new ArrayList<TitreMusical>();
			this.titresMusicaux.add(titre);
		}
	}

	public void retirerTitre(TitreMusical titre) {
		if(this.titresMusicaux!=null) {
			this.titresMusicaux.remove(titre);
		}
	}

	@Override
	public String toString() {
		return "Playlist [idPlaylist=" + idPlaylist + ", nomPlaylist=" + nomPlaylist + ", createur=" + createur
				+ ", titresMusicaux=" + titresMusicaux + "]";
	}
	
}

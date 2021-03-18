package Servlet;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Object.Album;
import Object.Client;
import Object.Genre;
import Object.Interprete;
import Object.Playlist;
import Object.ProfilGestionnaireClient;
import Object.ProfilGestionnaireMusical;
import Object.TitreMusical;
import Object.Utilisateur;

public class ClientServlet extends HttpServlet {//clientServlet
	
	public String vue;
	Client client;
	Utilisateur utilisateur=new Utilisateur();
	private String mail;
	private String motDePasse;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String servletPath = request.getServletPath();//recuperation URL
        
        //affectation param�tres � la vue
        request.setAttribute("isAdministrateur", false);
		
        //section utilisateur non connecte
		if(servletPath.equals("/AuthentificationClient")) {
			request.setAttribute("nav-bar-active", "Connexion");
			//affectation param�tres � la vue
			request.setAttribute("isClient", false);
			request.setAttribute("isErrorLogin", false);
			request.setAttribute("notLogged", false);
			
			//affection vue
			vue = "/JSP/Utilisateur/AuthentificationClient.jsp";
		}else if(servletPath.equals("/InscriptionClient")){
			request.setAttribute("nav-bar-active", "Connexion");
			//affectation param�tres � la vue
			request.setAttribute("isClient", false);
			request.setAttribute("isErrorLogin", false);
			
			//affection vue
			vue = "/JSP/Utilisateur/InscriptionClient.jsp";
		}else if(client==null){ //verification que le client est connecte 
			//section client
			request.setAttribute("nav-bar-active", "Connexion");
		
			//affectation param�tres � la vue
			request.setAttribute("isClient", false);
    		request.setAttribute("isErrorLogin", false);
    		request.setAttribute("notLogged", true);
    		
    		//affection vue
    		vue = "/JSP/Utilisateur/AuthentificationClient.jsp";
    	}else {
    		//affectation param�tres � la vue
    		
    		// requeteBDD
			List<TitreMusical> titresMusicaux = client.rechercherParNomTitre("");
			List<Interprete> interpretes = client.rechercherParPseudoInterprete("");
			List<Album> albums = client.rechercherParNomAlbum("");

			// envoie de parametres a la vue
			request.setAttribute("titresMusicaux", titresMusicaux);
			request.setAttribute("interpretes", interpretes);
			request.setAttribute("albums", albums);
    		request.setAttribute("isClient", true);
    		request.setAttribute("password", client.getPassword());
            request.setAttribute("civilite", client.getCivilite());
    		request.setAttribute("nom", client.getNom());
    		request.setAttribute("prenom", client.getPrenom());
    		request.setAttribute("email", client.getMail());
    		request.setAttribute("adresse", client.getAdresseFacturation());
    		request.setAttribute("nbEcoutes", client.getNbEcoute());
    		request.setAttribute("dateDeNaissance", client.getDateNaissance());
    		request.setAttribute("genre", client.getStyleMusiquePrefere());
	  
    		//affection vue
    		if(servletPath.equals("/GestionPlaylist")) {
    			request.setAttribute("nav-bar-active", "GestionPlaylist");

				request.setAttribute("playlists", client.getPlaylists());

				// attribution vue
            	vue = "/JSP/Client/GestionPlaylist.jsp";
            }else if(servletPath.equals("/AjoutPlaylist")) {
            	request.setAttribute("nav-bar-active", "AjoutPlaylist");
            	vue = "/JSP/Client/AjoutPlaylist.jsp";
            }else if(servletPath.equals("/ProfilClient")){
            	request.setAttribute("nav-bar-active", "ProfilClient");
            	vue = "/JSP/Client/ProfilClient.jsp";
            }else if(servletPath.equals("/AccueilClient")){
            	request.setAttribute("nav-bar-active", "AccueilClient");
            	vue = "/JSP/Client/AccueilClient.jsp";
            }else if(servletPath.equals("/ModificationProfilClient")){
            	request.setAttribute("nav-bar-active", "ModificationProfilClient");
                vue = "/JSP/Client/ModificationProfilClient.jsp";
	        }
    	}	         
        //affichage vue	
        RequestDispatcher rd = getServletContext().getRequestDispatcher(vue);
        try {
              rd.forward(request, response);
        } catch (ServletException e) {
              e.printStackTrace();
        } catch (IOException e) {
              e.printStackTrace();
        }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");// de quel form je viens ?

		//affectation param�tres � la vue
		request.setAttribute("isAdministrateur", false);

		// recuperation des parametres du form
		String mail = request.getParameter("mail");
		String motDePasse = request.getParameter("password");
		
		if(action.equals("AuthentificationClient")) {
		//requ�te � la BDD
		client = utilisateur.authentification(mail, motDePasse);
		
		if(client!=null) {
			request.setAttribute("nav-bar-active", "AccueilClient");
			//affectation param�tres � la vue
			request.setAttribute("isClient", true);
			
			// requeteBDD
			List<TitreMusical> titresMusicaux = client.rechercherParNomTitre("");
			List<Interprete> interpretes = client.rechercherParPseudoInterprete("");
			List<Album> albums = client.rechercherParNomAlbum("");

			// envoie de parametres a la vue
			request.setAttribute("titresMusicaux", titresMusicaux);
			request.setAttribute("interpretes", interpretes);
			request.setAttribute("albums", albums);
			request.setAttribute("isClient", true);

			//choix de la vue
			vue = "/JSP/Client/AccueilClient.jsp";
		}else {//echec
			request.setAttribute("nav-bar-active", "Connexion");
			//affectation param�tres � la vue
			request.setAttribute("isClient", false);
			request.setAttribute("isErrorLogin", true);
			request.setAttribute("notLogged", false);

			//choix de la vue
			vue = "/JSP/Utilisateur/AuthentificationClient.jsp";
		}
		} else {
			///////////////////////////////////////////////////////partie CLIENT///////////////////////////////////////////////////////
			List<TitreMusical> titresMusicaux;
			List<Interprete> interpretes;
			List<Album> albums;
			if (client == null) {// verification que le client est connecte
				// affectation param�tres � la vue
				request.setAttribute("isClient", false);
				request.setAttribute("isErrorLogin", false);
				request.setAttribute("notLogged", true);

				// affection vue
				vue = "/JSP/Utilisateur/AuthentificationClient.jsp";
			}else {
				// requeteBDD
				titresMusicaux = client.rechercherParNomTitre("");
				interpretes = client.rechercherParPseudoInterprete("");
				albums = client.rechercherParNomAlbum("");

				// envoie de parametres a la vue
				request.setAttribute("titresMusicaux", titresMusicaux);
				request.setAttribute("interpretes", interpretes);
				request.setAttribute("albums", albums);
				request.setAttribute("isClient", true);
			}			
		
			if(action.equals("ModificationProfilClient")) {
				//recuperation param�tres
				String prenom = request.getParameter("prenom");	
				String nom = request.getParameter("nom");
				String adresse = request.getParameter("adresse");
				String dateDeNaissanceString = request.getParameter("dateDeNaissance");
				String civilite="";
				if(request.getParameter("civilite").equals("M")) {
					civilite="M";
				}else {
					if(request.getParameter("civilite").equals("Mme")) {
						civilite="Mme";
					}else {
						if(request.getParameter("civilite").equals("Autre")) {
							civilite="Autre";
						}
					}
				}
				Date dateDeNaissance = null;
				if(dateDeNaissanceString!=null && dateDeNaissanceString!="") {
					dateDeNaissance = Date.valueOf(dateDeNaissanceString);
				}  
				String styleMusiquePrefereString = request.getParameter("styleMusiquePrefere");
				Genre styleMusiquePrefere = Genre.valueOf(styleMusiquePrefereString);
				
				//mise � jour BDD
				//TODO : ajouter un supprimerClient pour pouvoir modifier le mail
	    		Client clientModifie=client.modifierInformations(mail, motDePasse, civilite, nom, prenom, dateDeNaissance, adresse, styleMusiquePrefere);
	    		
	    		//affectation param�tres � la vue
				request.setAttribute("isClient", true);
				//le client est inaccessible depuis doPost ?!
	            request.setAttribute("civilite", client.getCivilite());
	    		request.setAttribute("nom", client.getNom());
	    		request.setAttribute("prenom", client.getPrenom());
	    		request.setAttribute("email", client.getMail());
	    		request.setAttribute("adresse", client.getAdresseFacturation());
	    		request.setAttribute("nbEcoutes", client.getNbEcoute());
	    		request.setAttribute("dateDeNaissance", client.getDateNaissance());
	    		/*
	    		request.setAttribute("nom", "");
	    		request.setAttribute("prenom", "");
	    		request.setAttribute("email", "");
	    		request.setAttribute("adresse", "");
	    		request.setAttribute("nbEcoutes", 0);
	    		request.setAttribute("dateDeNaissance", new Date(System.currentTimeMillis()));*/
	    		
	    		if(clientModifie!=null) {
	    			this.client=clientModifie;
	    		}
	    		
	    		//choix de la vue
				vue = "/JSP/Client/ProfilClient.jsp";//todo fix refresh page n�cessaire
			}else if(action.equals("InscriptionClient")) {
				//requ�te � la BDD
				client = utilisateur.creerCompte(mail, motDePasse);
				
				if(client!=null) {
					//affectation param�tres � la vue
					request.setAttribute("isClient", true);
					
					//choix de la vue
					vue = "/JSP/Client/AccueilClient.jsp";
				}else {//echec
					//affectation param�tres � la vue
					request.setAttribute("isClient", false);
					request.setAttribute("isErrorLogin", true);
					request.setAttribute("notLogged", false);
					
					//choix de la vue
					vue = "/JSP/Utilisateur/InscriptionClient.jsp";
				}
			}else if(action.equals("RechercheAccueil")) {
				// On recupere les parametres de la vue
				String typeElement = request.getParameter("TypeElement");
				String recherche = request.getParameter("recherche");
				
				if(typeElement.equals("Titres musicaux")) {
					titresMusicaux=client.rechercherParNomTitre(recherche);
					request.setAttribute("titresMusicaux", titresMusicaux);
				}else if(typeElement.equals("Interpretes")) {
					interpretes = client.rechercherParPseudoInterprete(recherche);
					request.setAttribute("interpretes", interpretes);
				}else if(typeElement.equals("Albums")) {
					albums = client.rechercherParNomAlbum(recherche);
					request.setAttribute("albums", albums);
				}
				
				// envoi param vue
				request.setAttribute("TypeElement", typeElement);
	
				// attribution vue
				vue = "/JSP/Client/AccueilClient.jsp"; 
			}else if(action.equals("CreerPlaylist")) {
				request.setAttribute("nav-bar-active", "AjoutPlaylist");
				//rï¿½cup param vue
				String nom = request.getParameter("nomPlaylist");

				// requete BDD
				titresMusicaux = client.rechercherParNomTitre("");
				Playlist playlist = client.creerPlaylist(nom, null);
				
				//envoi param vue
				request.setAttribute("playlist", playlist);
				request.setAttribute("titresMusicaux", titresMusicaux);

				//attribution vue
				vue = "/JSP/Client/AjoutTitresAPlaylist.jsp"; 
			}else if(action.equals("FinAjoutTitresMusicauxAPlaylist")) {
				request.setAttribute("nav-bar-active", "AjoutPlaylist");
				//affectation vue
				vue = "/JSP/Client/AjoutPlaylist.jsp"; 
			}else if(action.equals("AjouterTitresAPlaylist")) {
				request.setAttribute("nav-bar-active", "AjoutPlaylist");
				//rï¿½cup param vue
				String titresMusicauxSelectBox = request.getParameter("titresMusicauxSelectBox");
				Playlist playlist = (Playlist) request.getSession().getAttribute("playlist");
				String titreMusicalString = request.getParameter("titreMusical");
				
				int id = Integer.parseInt(titresMusicauxSelectBox);

				TitreMusical titreMusical = null;
				//todo ajouter mï¿½thode rechercherInterpreteParId
				for(TitreMusical t : client.rechercherParNomTitre("")){
					if(t.getIdCatalogue()==id) {
						titreMusical=t;
					}
				}
				
				//envoi param vue
				request.setAttribute("playlist", playlist);

				//requete BDD
				client.ajouterTitrePlaylist(titreMusical, playlist);
				request.setAttribute("titresMusicauxAssocies", playlist.getTitresMusicaux());
				//affectation vue
				vue = "/JSP/Client/AjoutTitresAPlaylist.jsp"; 
			}else if(action.equals("SuppressionPlaylist")) {
				// rï¿½cup param vue
				String idString = request.getParameter("idString");
				int id = Integer.parseInt(idString);
				
				Playlist playlist = null;
				for(Playlist elem : client.getPlaylists()) {
					if(elem.getIdPlaylist()==id){
						playlist = elem;
					}
				}
				if(playlist!=null) {
					client.supprimerPlaylist(playlist);
				}

				// envoie de parametres a la vue
				request.setAttribute("playlists", client.getPlaylists());
			}
		}
		//affichage de la vue
		RequestDispatcher rd = getServletContext().getRequestDispatcher(vue);
		try {
              rd.forward(request, response);
        } catch (ServletException e) {
              e.printStackTrace();
        } catch (IOException e) {
              e.printStackTrace();
        }
	}
}

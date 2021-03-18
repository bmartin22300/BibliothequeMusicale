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
        
        //affectation paramï¿½tres ï¿½ la vue
        request.setAttribute("isAdministrateur", false);
		
        //section utilisateur non connecte
		if(servletPath.equals("/AuthentificationClient")) {
			//affectation paramï¿½tres ï¿½ la vue
			request.setAttribute("isClient", false);
			request.setAttribute("isErrorLogin", false);
			request.setAttribute("notLogged", false);
			
			//affection vue
			vue = "/JSP/Utilisateur/AuthentificationClient.jsp";
		}else if(servletPath.equals("/InscriptionClient")){
			//affectation paramï¿½tres ï¿½ la vue
			request.setAttribute("isClient", false);
			request.setAttribute("isErrorLogin", false);
			
			//affection vue
			vue = "/JSP/Utilisateur/InscriptionClient.jsp";
		}else if(client==null){ //verification que le client est connecte 
			//section client
		
			//affectation paramï¿½tres ï¿½ la vue
			request.setAttribute("isClient", false);
    		request.setAttribute("isErrorLogin", false);
    		request.setAttribute("notLogged", true);
    		
    		//affection vue
    		vue = "/JSP/Utilisateur/AuthentificationClient.jsp";
    	}else {
    		//affectation paramï¿½tres ï¿½ la vue
    		
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
            	vue = "/JSP/Client/GestionPlaylist.jsp";
            }else if(servletPath.equals("/ProfilClient")){
            	vue = "/JSP/Client/ProfilClient.jsp";
            }else if(servletPath.equals("/AccueilClient")){
            	
            	vue = "/JSP/Client/AccueilClient.jsp";
            }else if(servletPath.equals("/ModificationProfilClient")){
                vue = "/JSP/Client/ModificationProfilClient.jsp";
	        }else if(servletPath.equals("/Sandbox")) {	 
    			vue = "/JSP/Client/Sandbox.jsp";
    			// Main d'Erwan
    			//DATA
    			String mailSandbox = "sandbox";
    			String passwordSandbox = "sandbox"; 
    			String civiliteSandbox = "M";
    			String nomSandbox = "Sandy";
    			String prenomSandbox = "Boxy";
    			String dateNaissanceStringSandbox = "1997-07-08"; Date dateNaissanceSandbox = Date.valueOf(dateNaissanceStringSandbox); //Conversion Date
    			String adresseSandbox = "19 rue des echalottes, Paris, France";
    			String styleMusiqueStringSandbox = "CLASSIQUE"; Genre styleMusiqueSandbox = Genre.valueOf(styleMusiqueStringSandbox); //Conversion Genre
    			String mailAdminMusiqueSandbox = "adminMusique";
    			String passwordAdminMusiqueSandbox = "adminMusique";
    			
    			// USER
    			System.out.println("USER");
    			Utilisateur userSandbox=new Utilisateur();
    			Client clientSandbox = userSandbox.creerCompte(mailSandbox, passwordSandbox);
    			System.out.println(clientSandbox);
    			
    			//Ne fait rien, existe dejï¿½
    			clientSandbox = userSandbox.creerCompte(mailSandbox, passwordSandbox, civiliteSandbox, nomSandbox, prenomSandbox, dateNaissanceSandbox, adresseSandbox, styleMusiqueSandbox);
    			System.out.println(clientSandbox);
    			
    			//Authentifie le client existant
    			clientSandbox = userSandbox.authentification(mailSandbox, passwordSandbox);
    			System.out.println(clientSandbox);
    			
    			// CLIENT
    			System.out.println("CLIENT");
    			clientSandbox.modifierInformations(mailSandbox, passwordSandbox, civiliteSandbox, nomSandbox, prenomSandbox, dateNaissanceSandbox, adresseSandbox, styleMusiqueSandbox);
    			System.out.println(clientSandbox);
    			clientSandbox.modifierInformations(mailSandbox, passwordSandbox, null, null, null, null, null, styleMusiqueSandbox);
    			System.out.println(clientSandbox);
    			
    			// ADMIN
    			System.out.println("ADMIN");
    			ProfilGestionnaireMusical adminSandbox = new ProfilGestionnaireMusical((int) (Math.random()*1000), mailAdminMusiqueSandbox,passwordAdminMusiqueSandbox);
    			System.out.println(adminSandbox.creerAdmin(mailAdminMusiqueSandbox, passwordAdminMusiqueSandbox));
    			System.out.println(userSandbox.authentification(mailAdminMusiqueSandbox, passwordAdminMusiqueSandbox));
    				
    				//Interprete
	    			List<Interprete> interpretesSandbox = new ArrayList<Interprete>();
    				System.out.println("Interprete");
    				interpretesSandbox.add(adminSandbox.creerInterprete("Noir Desir"));
    				interpretesSandbox.add(adminSandbox.creerInterprete("La fatigue", "La", "Fatigue", Date.valueOf("1980-09-17")));
    				System.out.println(interpretesSandbox);
    				adminSandbox.modifierInterprete(interpretesSandbox.get(0), "Noir Désir","Groupe", "Groupe", Date.valueOf("2001-01-01")); // Modification de "Noir Desir"
    				System.out.println(interpretesSandbox);
    				if(adminSandbox.supprimerInterprete(interpretesSandbox.get(0))){// Suppression de "Noir Desir")
    					interpretesSandbox.remove(0);
    				}
    				System.out.println(interpretesSandbox.get(0));
    				
    				// Recherche Interprete
    				System.out.println(adminSandbox.rechercherParPseudoInterprete("i"));
    				System.out.println(adminSandbox.rechercherParPrenomInterprete(""));
    				System.out.println(adminSandbox.rechercherParNomInterprete("Fatigue"));
    				//TitreMusical
	    			List<TitreMusical> titresSandbox = new ArrayList<TitreMusical>();
    				System.out.println("TitreMusical");
    				titresSandbox.add(adminSandbox.creerTitre("Il etait une fois", 1980, interpretesSandbox, 360, Genre.valueOf("TECHNO")));
    				titresSandbox.add(adminSandbox.creerTitre("Une liste de titres", 1981, interpretesSandbox, 200, Genre.valueOf("TECHNO")));
    				titresSandbox.add(adminSandbox.creerTitre("Qui n'avait aucun sens", 1982, interpretesSandbox, 503, Genre.valueOf("TECHNO")));
    				titresSandbox.add(adminSandbox.creerTitre("Mais au moins c'est des titres", 1983, interpretesSandbox, 345, Genre.valueOf("TECHNO")));
    				System.out.println(titresSandbox);
    				
    				adminSandbox.modifierTitre(titresSandbox.get(0), "Nouveau titre", 1500, 10, Genre.valueOf("VARIETE"));
    				System.out.println(titresSandbox);
    				if(adminSandbox.supprimerTitre(titresSandbox.get(0))){ // Suppression de "Nouveau titre")
    					titresSandbox.remove(0);
    				}
    				System.out.println(titresSandbox);
    				System.out.println("Recherche : "+adminSandbox.rechercherParNomTitre(""));
    				System.out.println(adminSandbox.rechercherParPseudoInterprete(""));
    				
    				// Ajouter/Supprimer discographie
    				Interprete interSandbox = adminSandbox.creerInterprete("Nouveau Random");
    				System.out.println("Avant : " + titresSandbox);
    				System.out.println(interSandbox);
    				adminSandbox.ajouterDiscographie(titresSandbox.get(0), interSandbox);
    				System.out.println("Après : " + titresSandbox);
    				System.out.println(interSandbox);
    				adminSandbox.retirerDiscographie(titresSandbox.get(0), interSandbox);
    				System.out.println("Aprèsv2 : " + titresSandbox);
    				System.out.println(interSandbox);
    			
    				// Album
    				System.out.println("ALBUM");
    				Album albumSandbox = adminSandbox.creerAlbum("Mon Album", 2000, titresSandbox);
    				System.out.println(albumSandbox);
    				Album albumSandboxVide = adminSandbox.creerAlbum("Mon Album 2.0", 2050, null);
    				System.out.println(albumSandboxVide);
    				adminSandbox.modifierAlbum(albumSandbox, "Nouveau titre", 1999);
    				System.out.println(albumSandbox);
    				//adminSandbox.supprimerAlbum(albumSandboxVide);
    				System.out.println(albumSandboxVide);
    				adminSandbox.ajoutTitreAlbum(titresSandbox.get(0),albumSandboxVide);
    				System.out.println(titresSandbox.get(0));
    				System.out.println(albumSandboxVide);
    				adminSandbox.suppressionTitreAlbum(titresSandbox.get(0), albumSandboxVide);
    				System.out.println(albumSandboxVide);
    				
    				System.out.println(adminSandbox.rechercherParNomAlbum(""));
    				
    				// GETTERS
    				System.out.println("GETTERS");
    				System.out.println(adminSandbox.getInterprete(2));
    				System.out.println(adminSandbox.getTitreMusical(2));
    				System.out.println(adminSandbox.getAlbum(5));
    				
    				// RECOMMANDATIONS
    				System.out.println("RECOMMANDATIONS");
    				adminSandbox.recommander(titresSandbox.get(1));
    				adminSandbox.recommander(titresSandbox.get(2));
    				adminSandbox.recommander_annuler(titresSandbox.get(2));
    				
	    			// AUTHENTIFICATION ADMIN
    				System.out.println("AUTHENTIFICATION ADMIN");
    				System.out.println(userSandbox.authentificationAdmin(mailAdminMusiqueSandbox, passwordAdminMusiqueSandbox));
    				System.out.println(userSandbox.authentificationAdmin("AZERTYFAUX", passwordAdminMusiqueSandbox));
    				
    				// REGARDER
    				adminSandbox.regarder(titresSandbox.get(1));
    				adminSandbox.regarder(titresSandbox.get(1));
    				adminSandbox.regarder(albumSandboxVide);
    				userSandbox.regarder(titresSandbox.get(1));
    				clientSandbox.regarder(titresSandbox.get(1));
    				
    				// PLAYLIST
    				System.out.println("PLAYLIST");
    				Playlist playlistSandbox = clientSandbox.creerPlaylist("Pour jouer", titresSandbox);
    				System.out.println(clientSandbox.creerPlaylist("Ma Playlist", titresSandbox));
    				System.out.println(clientSandbox.creerPlaylist("Ma Playlist2.0", titresSandbox));
    				System.out.println(clientSandbox.supprimerPlaylist(clientSandbox.getPlaylists().get(1)));
    				System.out.println(clientSandbox.getPlaylists());
    				System.out.println(playlistSandbox);
    				clientSandbox.changerNomPlaylist(playlistSandbox, "Pour jouer mais avec un nouveau nom");
    				System.out.println(playlistSandbox);
    				clientSandbox.ajouterTitrePlaylist(adminSandbox.creerTitre("Un autre titre pour la forme", 2000, null, 20, Genre.CLASSIQUE), playlistSandbox);
    				System.out.println(playlistSandbox);
    				clientSandbox.retirerTitrePlaylist(playlistSandbox.getTitresMusicaux().get(0), playlistSandbox);
    				System.out.println(playlistSandbox);
    				
    				// Recherche Client
    				System.out.println("RECHERCHE CLIENT");
    				System.out.println(adminSandbox.rechercherParMailClient(""));
    				System.out.println(adminSandbox.getClient(1));
    				
    				// Modifier Information Client
    				System.out.println("MODIFIER CLIENT DEPUIS ADMIN");
    				ProfilGestionnaireClient adminSandboxClient = new ProfilGestionnaireClient((int) (Math.random()*1000), "MailAdminClient","PasswordAdminClient");
    				System.out.println(clientSandbox);
    				adminSandboxClient.modifierInformationsClient(clientSandbox, "Un nouveau mail pour la forme", "Un nouveau pass pour la forme", "Mister", "Le", "Plus", dateNaissanceSandbox, "Adressé", styleMusiqueSandbox);
    				System.out.println(clientSandbox);
    				
    				// Stats
    				System.out.println("STATS");
    				System.out.println(adminSandbox.topTitresEcoutes());
    				System.out.println(clientSandbox.topTitresEcoutes());
    				System.out.println(userSandbox.topTitresEcoutes());
    				
    				System.out.println(adminSandbox.recommandationsDuMoment());
    				System.out.println(clientSandbox.recommandationsDuMoment());
    				System.out.println(userSandbox.recommandationsDuMoment());
    				
    				System.out.println(adminSandbox.topUtilisateursEcoutes());
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

		//affectation paramï¿½tres ï¿½ la vue
		request.setAttribute("isAdministrateur", false);

		// recuperation des parametres du form
		String mail = request.getParameter("mail");
		String motDePasse = request.getParameter("password");
		
		if(action.equals("AuthentificationClient")) {
		//requï¿½te ï¿½ la BDD
		client = utilisateur.authentification(mail, motDePasse);
		
		if(client!=null) {
			//affectation paramï¿½tres ï¿½ la vue
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
			//affectation paramï¿½tres ï¿½ la vue
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
				// affectation paramï¿½tres ï¿½ la vue
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
				//recuperation paramï¿½tres
				String prenom = request.getParameter("prenom");	
				String nom = request.getParameter("nom");
				String adresse = request.getParameter("adresse");
				String dateDeNaissanceString = request.getParameter("dateDeNaissance");
				System.out.println(request.getParameter("civilite"));
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
				
				//mise ï¿½ jour BDD
				//TODO : ajouter un supprimerClient pour pouvoir modifier le mail
	    		Client clientModifie=client.modifierInformations(mail, motDePasse, civilite, nom, prenom, dateDeNaissance, adresse, styleMusiquePrefere);
	    		
	    		//affectation paramï¿½tres ï¿½ la vue
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
				vue = "/JSP/Client/ProfilClient.jsp";//todo fix refresh page nï¿½cessaire
			}else if(action.equals("InscriptionClient")) {
				//requï¿½te ï¿½ la BDD
				client = utilisateur.creerCompte(mail, motDePasse);
				
				if(client!=null) {
					//affectation paramï¿½tres ï¿½ la vue
					request.setAttribute("isClient", true);
					
					//choix de la vue
					vue = "/JSP/Client/AccueilClient.jsp";
				}else {//echec
					//affectation paramï¿½tres ï¿½ la vue
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

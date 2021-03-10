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

import Object.Client;
import Object.Genre;
import Object.Interprete;
import Object.ProfilGestionnaireMusical;
import Object.TitreMusical;
import Object.Utilisateur;

public class ClientServlet extends HttpServlet {//clientServlet
	
	public String vue;
	Client client;
	Utilisateur utilisateur=new Utilisateur();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String servletPath = request.getServletPath();//récupération URL
        
        //affectation paramètres à la vue
        request.setAttribute("isAdministrateur", false);
		
        //section utilisateur non connecté
		if(servletPath.equals("/AuthentificationClient")) {
			//affectation paramètres à la vue
			request.setAttribute("isClient", false);
			request.setAttribute("isErrorLogin", false);
			request.setAttribute("notLogged", false);
			
			//affection vue
			vue = "/JSP/Utilisateur/AuthentificationClient.jsp";
		}else if(servletPath.equals("/InscriptionClient")){
			//affectation paramètres à la vue
			request.setAttribute("isClient", false);
			//affection vue
			vue = "/JSP/Utilisateur/InscriptionClient.jsp";
		}else if(client==null){ //verification que le client est connecté 
			//section client
		
			//affectation paramètres à la vue
			request.setAttribute("isClient", false);
    		request.setAttribute("isErrorLogin", false);
    		request.setAttribute("notLogged", true);
    		
    		//affection vue
    		vue = "/JSP/Utilisateur/AuthentificationClient.jsp";
    	}else {
    		//affectation paramètres à la vue
    		request.setAttribute("isClient", true);
    		request.setAttribute("password", client.getPassword());
            request.setAttribute("civilité", client.getCivilite());
    		request.setAttribute("nom", client.getNom());
    		request.setAttribute("prénom", client.getPrenom());
    		request.setAttribute("email", client.getMail());
    		request.setAttribute("adresse", client.getAdresseFacturation());
    		request.setAttribute("nbEcoutes", client.getNbEcoute());
    		request.setAttribute("dateDeNaissance", client.getDateNaissance());
	  
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
    			
    			//Ne fait rien, existe déjà
    			clientSandbox = userSandbox.creerCompte(mailSandbox, passwordSandbox, civiliteSandbox, nomSandbox, prenomSandbox, dateNaissanceSandbox, adresseSandbox, styleMusiqueSandbox);
    			System.out.println(clientSandbox);
    			
    			//Authentifie le client existant
    			clientSandbox = userSandbox.authentification(mailSandbox, passwordSandbox);
    			System.out.println(clientSandbox);
    			
    			// CLIENT
    			System.out.println("CLIENT");
    			clientSandbox.modifierInformations( passwordSandbox, civiliteSandbox, nomSandbox, prenomSandbox, dateNaissanceSandbox, adresseSandbox, styleMusiqueSandbox);
    			System.out.println(clientSandbox);
    			clientSandbox.modifierInformations( passwordSandbox, null, null, null, null, null, styleMusiqueSandbox);
    			System.out.println(clientSandbox);
    			
    			// ADMIN
    			System.out.println("ADMIN");
    			ProfilGestionnaireMusical adminSandbox = new ProfilGestionnaireMusical(mailAdminMusiqueSandbox,passwordAdminMusiqueSandbox);
    			System.out.println(adminSandbox.creerAdmin(mailAdminMusiqueSandbox, passwordAdminMusiqueSandbox));
    			System.out.println(adminSandbox.authentification(mailAdminMusiqueSandbox, passwordAdminMusiqueSandbox));
    				
    				//Interprete
	    			List<Interprete> interpretesSandbox = new ArrayList<Interprete>();
    				System.out.println("Interprete");
    				interpretesSandbox.add(adminSandbox.creerInterprete("Noir Désir"));
    				interpretesSandbox.add(adminSandbox.creerInterprete("La fatigue", "La", "Fatigue", Date.valueOf("1980-09-17")));
    				System.out.println(interpretesSandbox);
    				adminSandbox.modifierInterprete(interpretesSandbox.get(0), "Groupe", "Groupe", Date.valueOf("2001-01-01")); // Modification de "Noir Désir"
    				System.out.println(interpretesSandbox);
    				if(adminSandbox.supprimerInterprete(interpretesSandbox.get(0))){// Suppression de "Noir Désir")
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
    				titresSandbox.add(adminSandbox.creerTitre("Il était une fois", 1980, interpretesSandbox, 360, Genre.valueOf("TECHNO")));
    				titresSandbox.add(adminSandbox.creerTitre("Une liste de titres", 1981, interpretesSandbox, 200, Genre.valueOf("TECHNO")));
    				titresSandbox.add(adminSandbox.creerTitre("Qui n'avait aucun sens", 1982, interpretesSandbox, 503, Genre.valueOf("TECHNO")));
    				titresSandbox.add(adminSandbox.creerTitre("Mais au moins c'est des titres", 1983, interpretesSandbox, 345, Genre.valueOf("TECHNO")));
    				System.out.println(titresSandbox);
    				
    				adminSandbox.modifierTitre(titresSandbox.get(0), "Nouveau titre", 1500, 10, Genre.valueOf("VARIETE"));
    				System.out.println(titresSandbox);
    				if(adminSandbox.supprimerTitre(titresSandbox.get(0))){// Suppression de "Nouveau titre")
    					titresSandbox.remove(0);
    				}
    				System.out.println(titresSandbox);
    			
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
		String action = request.getParameter("action");//de quel form je viens ?
		
		//affectation paramètres à la vue
		request.setAttribute("isAdministrateur", false);
		
		//récupération des paramètres du form
		String mail = request.getParameter("mail");
		String motDePasse = request.getParameter("password");
		
		if(action.equals("ModificationProfilClient")) {
			//récupération paramètres
			String prénom = request.getParameter("prénom");
			String nom = request.getParameter("nom");
			String adresse = request.getParameter("adresse");
			String dateDeNaissanceString = request.getParameter("dateDeNaissance");
			String civilité="";
			if(request.getParameter("M").equals("on")) {
				civilité="M";
			}else {
				if(request.getParameter("Mme").equals("on")) {
					civilité="Mme";
				}else {
					if(request.getParameter("Autre").equals("on")) {
						civilité="Autre";
					}
				}
			}
			Date dateDeNaissance = null;
			if(dateDeNaissanceString!=null && dateDeNaissanceString!="") {
				dateDeNaissance = Date.valueOf(dateDeNaissanceString);
			}  
			String styleMusiquePrefereString = request.getParameter("styleMusiquePrefere");
			Genre styleMusiquePrefere = Genre.valueOf("TECHNO");
			
			//affectation paramètres à la vue
			request.setAttribute("isClient", true);
			/*
            request.setAttribute("civilité", client.getCivilite());
    		request.setAttribute("nom", client.getNom());
    		request.setAttribute("prénom", client.getPrenom());
    		request.setAttribute("email", client.getMail());
    		request.setAttribute("adresse", client.getAdresseFacturation());
    		request.setAttribute("nbEcoutes", client.getNbEcoute());
    		request.setAttribute("dateDeNaissance", client.getDateNaissance());*/
    		request.setAttribute("nom", "");
    		request.setAttribute("prénom", "");
    		request.setAttribute("email", "");
    		request.setAttribute("adresse", "");
    		request.setAttribute("nbEcoutes", 0);
    		request.setAttribute("dateDeNaissance", "");
			
			//mise à jour BDD
			//TODO : ajouter un supprimerClient pour pouvoir modifier le mail
    		Client clientModifié=client.modifierInformations(motDePasse, civilité, nom, prénom, dateDeNaissance, adresse, styleMusiquePrefere);
    		if(clientModifié!=null) {
    			this.client=clientModifié;
    		}
    		
    		
    		//choix de la vue
			vue = "/JSP/Client/ProfilClient.jsp";
		}else {
			if(action.equals("AuthentificationClient")) {
				//requête à la BDD
				client = utilisateur.authentification(mail, motDePasse);
				
				if(client!=null) {
					//affectation paramètres à la vue
					request.setAttribute("isClient", true);
					
					//choix de la vue
					vue = "/JSP/Client/AccueilClient.jsp";
				}else {//échec
					//affectation paramètres à la vue
					request.setAttribute("isClient", false);
					request.setAttribute("isErrorLogin", true);
					request.setAttribute("notLogged", false);
					
					//choix de la vue
					vue = "/JSP/Utilisateur/AuthentificationClient.jsp";
				}
			}else {
				if(action.equals("InscriptionClient")) {
					//requête à la BDD
					client = utilisateur.creerCompte(mail, motDePasse);
					
					if(client!=null) {
						//affectation paramètres à la vue
						request.setAttribute("isClient", true);
						
						//choix de la vue
						vue = "/JSP/Client/AccueilClient.jsp";
					}else {//échec
						//affectation paramètres à la vue
						request.setAttribute("isClient", false);
						request.setAttribute("isErrorLogin", false);
						request.setAttribute("notLogged", false);
						
						//choix de la vue
						vue = "/JSP/Utilisateur/AuthentificationClient.jsp";
					}
				}
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
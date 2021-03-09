package Servlet;

import java.io.IOException;
import java.sql.Date;
import java.time.Year;
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
        String servletPath = request.getServletPath();//r�cup�ration URL
        
        //affectation param�tres � la vue
        request.setAttribute("isAdministrateur", false);
		
        //section utilisateur non connect�
		if(servletPath.equals("/AuthentificationClient")) {
			//affectation param�tres � la vue
			request.setAttribute("isClient", false);
			request.setAttribute("isErrorLogin", false);
			request.setAttribute("notLogged", false);
			
			//affection vue
			vue = "/JSP/Utilisateur/AuthentificationClient.jsp";
		}else if(servletPath.equals("/InscriptionClient")){
			//affectation param�tres � la vue
			request.setAttribute("isClient", false);
			//affection vue
			vue = "/JSP/Utilisateur/InscriptionClient.jsp";
		}else if(client==null){ //verification que le client est connect� 
			//section client
		
			//affectation param�tres � la vue
			request.setAttribute("isClient", false);
    		request.setAttribute("isErrorLogin", false);
    		request.setAttribute("notLogged", true);
    		
    		//affection vue
    		vue = "/JSP/Utilisateur/AuthentificationClient.jsp";
    	}else {
    		//affectation param�tres � la vue
    		request.setAttribute("isClient", true);
    		request.setAttribute("password", client.getPassword());
            request.setAttribute("civilit�", client.getCivilite());
    		request.setAttribute("nom", client.getNom());
    		request.setAttribute("pr�nom", client.getPrenom());
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
    			
    			//Ne fait rien, existe d�j�
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
    				interpretesSandbox.add(adminSandbox.creerInterprete("Noir D�sir"));
    				interpretesSandbox.add(adminSandbox.creerInterprete("La fatigue", "La", "Fatigue", Date.valueOf("1980-09-17")));
    				System.out.println(interpretesSandbox);
    				adminSandbox.modifierInterprete(interpretesSandbox.get(0), "Groupe", "Groupe", Date.valueOf("2001-01-01")); // Modification de "Noir D�sir"
    				System.out.println(interpretesSandbox);
    				if(adminSandbox.supprimerInterprete(interpretesSandbox.get(0))){// Suppression de "Noir D�sir")
    					interpretesSandbox.remove(0);
    				}
    				System.out.println(interpretesSandbox.get(0));
    				
    				//TitreMusical
	    			List<TitreMusical> titresSandbox = new ArrayList<TitreMusical>();
    				System.out.println("TitreMusical");
    				//A faire : Convertir le type "Year" en int partout parce que voil� quoi...
    				//titresSandbox.add(adminSandbox.creerTitre("Il �tait une fois", "1980", interpretesSandbox, 360, Genre.JAZZ));
    		
    			
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
		
		//affectation param�tres � la vue
		request.setAttribute("isAdministrateur", false);
		
		//r�cup�ration des param�tres du form
		String mail = request.getParameter("mail");
		String motDePasse = request.getParameter("password");
		
		if(action.equals("ModificationProfilClient")) {
			//r�cup�ration param�tres
			String pr�nom = request.getParameter("pr�nom");
			String nom = request.getParameter("nom");
			String adresse = request.getParameter("adresse");
			String dateDeNaissanceString = request.getParameter("dateDeNaissance");
			String civilit�="";
			if(request.getParameter("M").equals("on")) {
				civilit�="M";
			}else {
				if(request.getParameter("Mme").equals("on")) {
					civilit�="Mme";
				}else {
					if(request.getParameter("Autre").equals("on")) {
						civilit�="Autre";
					}
				}
			}
			Date dateDeNaissance = null;
			if(dateDeNaissanceString!=null && dateDeNaissanceString!="") {
				dateDeNaissance = Date.valueOf(dateDeNaissanceString);
			}  
			String styleMusiquePrefereString = request.getParameter("styleMusiquePrefere");
			Genre styleMusiquePrefere = Genre.valueOf("TECHNO");
			
			//affectation param�tres � la vue
			request.setAttribute("isClient", true);
			/*
            request.setAttribute("civilit�", client.getCivilite());
    		request.setAttribute("nom", client.getNom());
    		request.setAttribute("pr�nom", client.getPrenom());
    		request.setAttribute("email", client.getMail());
    		request.setAttribute("adresse", client.getAdresseFacturation());
    		request.setAttribute("nbEcoutes", client.getNbEcoute());
    		request.setAttribute("dateDeNaissance", client.getDateNaissance());*/
    		request.setAttribute("nom", "");
    		request.setAttribute("pr�nom", "");
    		request.setAttribute("email", "");
    		request.setAttribute("adresse", "");
    		request.setAttribute("nbEcoutes", 0);
    		request.setAttribute("dateDeNaissance", "");
			
			//mise � jour BDD
			//TODO : ajouter un supprimerClient pour pouvoir modifier le mail
    		Client clientModifi�=client.modifierInformations(motDePasse, civilit�, nom, pr�nom, dateDeNaissance, adresse, styleMusiquePrefere);
    		if(clientModifi�!=null) {
    			this.client=clientModifi�;
    		}
    		
    		
    		//choix de la vue
			vue = "/JSP/Client/ProfilClient.jsp";
		}else {
			if(action.equals("AuthentificationClient")) {
				//requ�te � la BDD
				client = utilisateur.authentification(mail, motDePasse);
				
				if(client!=null) {
					//affectation param�tres � la vue
					request.setAttribute("isClient", true);
					
					//choix de la vue
					vue = "/JSP/Client/AccueilClient.jsp";
				}else {//�chec
					//affectation param�tres � la vue
					request.setAttribute("isClient", false);
					request.setAttribute("isErrorLogin", true);
					request.setAttribute("notLogged", false);
					
					//choix de la vue
					vue = "/JSP/Utilisateur/AuthentificationClient.jsp";
				}
			}else {
				if(action.equals("InscriptionClient")) {
					//requ�te � la BDD
					client = utilisateur.creerCompte(mail, motDePasse);
					
					if(client!=null) {
						//affectation param�tres � la vue
						request.setAttribute("isClient", true);
						
						//choix de la vue
						vue = "/JSP/Client/AccueilClient.jsp";
					}else {//�chec
						//affectation param�tres � la vue
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
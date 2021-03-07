package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Object.Authentification;
import Object.Client;
import Object.Utilisateur;

public class ClientServlet extends HttpServlet {//clientServlet
	
	public String vue = "/JSP/Utilisateur/AuthentificationClient.jsp";
	Authentification authentification = new Authentification();
	Utilisateur u = new Utilisateur();
	Client client;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String servletPath = request.getServletPath();//récupération URL
        
        //affectation paramètres
        request.setAttribute("isClient", authentification.isClient());
		request.setAttribute("isAdministrateur", authentification.isAdministrateur());
		request.setAttribute("mail", authentification.getMail());
		request.setAttribute("motDePasse", authentification.getMotDePasse());
		
		//affection vue
        if(servletPath.equals("/AuthentificationClient")) {
        	authentification.setClient(false);//remise à faux lors de la déconnexion
    		authentification.setAdministrateur(false);//remise à faux lors de la déconnexion
    		request.setAttribute("isClient", authentification.isClient());
    		request.setAttribute("isAdministrateur", authentification.isAdministrateur());
        	request.setAttribute("isErrorLogin", false);
        	request.setAttribute("notLogged", false);
        	vue = "/JSP/Utilisateur/AuthentificationClient.jsp";
        }else {
        	if(servletPath.equals("/InscriptionClient")) {
        		vue = "/JSP/Utilisateur/InscriptionClient.jsp";
        	}else {
        		//section authentifié
        		if(client==null) {//verification que le client est connecté / authentification.isClient()==false / TODO vérifier que ça marche après déconnexion, supprimer authentification
            		request.setAttribute("isErrorLogin", false);
            		request.setAttribute("notLogged", true);
            		vue = "/JSP/Utilisateur/AuthentificationClient.jsp";
            	}else {
            		//affectation paramètres clients
            		request.setAttribute("password", client.getPassword());
                    request.setAttribute("civilité", client.getCivilite());
            		request.setAttribute("nom", client.getNom());
            		request.setAttribute("prénom", client.getPrenom());
            		request.setAttribute("email", client.getMail());
            		request.setAttribute("adresse", client.getAdresseFacturation());
            		request.setAttribute("nbEcoutes", client.getNbEcoute());
            		request.setAttribute("dateDeNaissance", client.getDateNaissance());
          
            		if(servletPath.equals("/GestionPlaylist")) {
                    	vue = "/JSP/Client/GestionPlaylist.jsp";
                    }else {
                    	if(servletPath.equals("/ProfilClient")) {
                        	vue = "/JSP/Client/ProfilClient.jsp";
                        }else {
                        	if(servletPath.equals("/AccueilClient")) {
                            	vue = "/JSP/Client/AccueilClient.jsp";
                            }else {
                            	if(servletPath.equals("/ModficationProfilClient")) {                            		
                            		vue = "/JSP/Client/ModificationProfilClient.jsp";
                            	}
                            }
                        }
                    }
            	}
        	}
        }
        
        //affichage jsp	
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
		
		//récupération des paramètres du form
		String mail = request.getParameter("mail");
		String motDePasse = request.getParameter("password");
		
		//attribution des paramètres à l'authentificateur
		authentification.setMail(mail);
		authentification.setMotDePasse(motDePasse);
		
		if(action.equals("ProfilClient")) {
			//récupération paramètres
			String prénom = request.getParameter("prénom");
			String nom = request.getParameter("nom");
			String adresse = request.getParameter("adresse");
			String dateDeNaissance = request.getParameter("dateDeNaissance");
			
			//mise à jour BDD
			//TODO implémenter la procédure SQL
			
    		//choix de la vue
			vue = "/JSP/Client/AccueilClient.jsp";
		}else {
			if(action.equals("AuthentificationClient")) {
				client = u.authentification(mail, motDePasse);
				if(client!=null) {
					request.setAttribute("isClient", true);
					request.setAttribute("isAdministrateur", false);
					authentification.setClient(true);
					
					//choix de la vue
					vue = "/JSP/Client/AccueilClient.jsp";
				}else {
					request.setAttribute("isClient", false);
					request.setAttribute("isAdministrateur", false);
					request.setAttribute("isErrorLogin", true);
					request.setAttribute("notLogged", false);
					
					//choix de la vue
					vue = "/JSP/Utilisateur/AuthentificationClient.jsp";
				}
			}else {
				if(action.equals("InscriptionClient")) {
					client = u.creerCompte(mail, motDePasse);
					if(client!=null) {
						request.setAttribute("isClient", true);
						request.setAttribute("isAdministrateur", false);
						authentification.setClient(true);
						
						//choix de la vue
						vue = "/JSP/Client/AccueilClient.jsp";
					}else {
						request.setAttribute("isClient", false);
						request.setAttribute("isAdministrateur", false);
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
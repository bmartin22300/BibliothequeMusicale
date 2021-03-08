package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Object.Administrateur;
import Object.Utilisateur;

public class AdministrateurServlet extends HttpServlet {
	
	public String vue;
	Administrateur administrateur;
	Utilisateur utilisateur=new Utilisateur();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String servletPath = request.getServletPath();//récupération URL
        
        //affectation paramètres à la vue
        request.setAttribute("isClient", false);
		
        //section administrateur non connecté
		if(servletPath.equals("/AuthentificationAdministrateur")) {	
			//affectation paramètres à la vue
			request.setAttribute("isAdministrateur", false);
			request.setAttribute("isErrorLogin", false);
			request.setAttribute("notLogged", false);
			
			//affection vue
			vue = "/JSP/Utilisateur/AuthentificationAdministrateur.jsp";
		}else {
			if(servletPath.equals("/InscriptionAdministrateur")) {
				//affectation paramètres à la vue
				request.setAttribute("isAdministrateur", false);
				//affection vue
				vue = "/JSP/Utilisateur/InscriptionAdministrateur.jsp";
			}else {
				//section Administrateur
				/*
				if(administrateur==null) {//verification que l'Administrateur est connecté 
					//affectation paramètres à la vue
					request.setAttribute("isAdministrateur", false);
		    		request.setAttribute("isErrorLogin", false);
		    		request.setAttribute("notLogged", true);
		    		
		    		//affection vue
		    		vue = "/JSP/Utilisateur/AuthentificationAdministrateur.jsp";
		    	}else {*/
		    		//affectation paramètres à la vue
		    		request.setAttribute("isAdministrateur", true);
		    		//request.setAttribute("password", administrateur.getPassword());
		    		//request.setAttribute("email", administrateur.getMail());
		  
		    		//affection vue
		    		if(servletPath.equals("/AccueilAdministrateur")) {
		            	vue = "/JSP/Administrateur/AccueilAdministrateur.jsp";
		            }else {
		            	if(servletPath.equals("/ProfilAdministrateur")) {
		            		//affectation paramètres à la vue
		        			request.setAttribute("email", "yukvb@gmail.com");
		        			
		                	vue = "/JSP/Administrateur/ProfilAdministrateur.jsp";
		                }else {
		                	if(servletPath.equals("/Statistiques")) {
		                    	vue = "/JSP/Administrateur/Statistiques.jsp";
		                    }else {
		                    	if(servletPath.equals("/ModificationProfil")) {                            		
		                    		vue = "/JSP/Administrateur/ModificationProfil.jsp";
		                    	}else {
		                    		if(servletPath.equals("/ModificationCatalogue")) {
		                    			vue = "/JSP/Administrateur/ModificationCatalogue.jsp";
		                    		}
		                    	}
		                    }
		                }
		            }
		    	//}
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
		request.setAttribute("isClient", false);
		
		//récupération des paramètres du form
		String mail = request.getParameter("mail");
		String motDePasse = request.getParameter("password");
		
		if(action.equals("ModificationProfilAdministrateur")) {
			//récupération paramètres
			
			//affectation paramètres à la vue
			request.setAttribute("isAdministrateur", true);
			
			//mise à jour BDD
			//TODO implémenter la procédure SQL
			
    		//choix de la vue
			vue = "/JSP/Administrateur/ProfilAdministrateur.jsp";
		}else {
			if(action.equals("AuthentificationAdministrateur")) {
				//TODO authentification admin
				
				administrateur = utilisateur.authentificationAdministrateur(mail, motDePasse);
				//affectation paramètres à la vue
				request.setAttribute("isAdministrateur", true);
				vue = "/JSP/Administrateur/AccueilAdministrateur.jsp";
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
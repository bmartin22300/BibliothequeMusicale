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

public class ClientServlet extends HttpServlet {//clientServlet
	
	public String vue = "/JSP/Utilisateur/AuthentificationClient.jsp";
	Authentification authentification = new Authentification();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String servletPath = request.getServletPath();//récupération URL
        
        //affectation paramètres
        request.setAttribute("isClient", authentification.isClient());
		request.setAttribute("isAdministrateur", authentification.isAdministrateur());
		request.setAttribute("pseudonyme", authentification.getPseudonyme());
		request.setAttribute("motDePasse", authentification.getMotDePasse());
		
		//affection vue
        if(servletPath.equals("/AuthentificationClient")) {
        	request.setAttribute("isErrorLogin", false);
        	request.setAttribute("notLogged", false);
        	vue = "/JSP/Utilisateur/AuthentificationClient.jsp";
        }else {
        	if(authentification.isClient()==false) {
        		request.setAttribute("isErrorLogin", false);
        		request.setAttribute("notLogged", true);
        		vue = "/JSP/Utilisateur/AuthentificationClient.jsp";
        	}else {
        		if(servletPath.equals("/GestionPlaylist")) {
                	vue = "/JSP/Client/GestionPlaylist.jsp";
                }else {
                	if(servletPath.equals("/ProfilClient")) {
                    	vue = "/JSP/Client/ProfilClient.jsp";
                    }else {
                    	if(servletPath.equals("/AccueilClient")) {
                        	vue = "/JSP/Client/AccueilClient.jsp";
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
		String pseudonyme = request.getParameter("username");
		String motDePasse = request.getParameter("password");
		authentification.setPseudonyme(pseudonyme);
		authentification.setMotDePasse(motDePasse);
		if(pseudonyme.equals("test") && motDePasse.equals("test")) {
			request.setAttribute("isClient", true);
			request.setAttribute("isAdministrateur", false);
			authentification.setClient(true);
			vue = "/JSP/Client/AccueilClient.jsp";
		}else {
			request.setAttribute("isClient", false);
			request.setAttribute("isAdministrateur", false);
			request.setAttribute("isErrorLogin", true);
			request.setAttribute("notLogged", false);
			vue = "/JSP/Utilisateur/AuthentificationClient.jsp";
		}
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
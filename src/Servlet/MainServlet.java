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

import Object.Authentification;
import Object.Client;

public class MainServlet extends HttpServlet {//utilisateurServlet
	
	public String vue = "/JSP/Utilisateur/Accueil.jsp";
	Authentification authentification = new Authentification();//TODO : ne peut-on pas s'en débarasser ? // pratique pour sauvegarder le pseudo... entre les pages
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			doProcess(request,response);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			doProcess(request,response);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws NumberFormatException, SQLException {
		authentification.setClient(false);//remise à faux lors de la déconnexion
		authentification.setAdministrateur(false);//remise à faux lors de la déconnexion
		request.setAttribute("isClient", authentification.isClient());
		request.setAttribute("isAdministrateur", authentification.isAdministrateur());
		request.setAttribute("mail", authentification.getMail());
		request.setAttribute("motDePasse", authentification.getMotDePasse());
		vue="/JSP/Utilisateur/Accueil.jsp";
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
package Servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Object.Album;
import Object.Interprete;
import Object.TitreMusical;
import Object.Utilisateur;

public class UtilisateurServlet extends HttpServlet {//utilisateurServlet
	
	public String vue;
	Utilisateur utilisateur=new Utilisateur();
	
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
		// recuperation des parametres du form
		String action = request.getParameter("action");
		
		if(action.equals("incrementeAudio")) {
			String idTitreMusical = request.getParameter("idTitreMusical");
			int id = Integer.parseInt(idTitreMusical);
			TitreMusical t = utilisateur.getTitreMusical(id);
			utilisateur.regarder(t);
		}
		try {
			doProcess(request,response);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws NumberFormatException, SQLException {
		//affectation des paramètres à la vue
		request.setAttribute("isClient", false);
		request.setAttribute("isAdministrateur", false);
		
		// requeteBDD
		List<TitreMusical> titresMusicaux = utilisateur.topTitresEcoutes();

		// envoie de parametres a la vue
		request.setAttribute("titresMusicaux", titresMusicaux);
		
		//attribution de la vue
		vue="/JSP/Utilisateur/Accueil.jsp";
		
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
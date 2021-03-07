package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Object.Client;
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
		}else {
			if(servletPath.equals("/InscriptionClient")) {
				//affectation param�tres � la vue
				request.setAttribute("isClient", false);
				//affection vue
				vue = "/JSP/Utilisateur/InscriptionClient.jsp";
			}else {
				//section client
				if(client==null) {//verification que le client est connect� 
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
			String dateDeNaissance = request.getParameter("dateDeNaissance");
			
			//affectation param�tres � la vue
			request.setAttribute("isClient", true);
            request.setAttribute("civilit�", client.getCivilite());
    		request.setAttribute("nom", client.getNom());
    		request.setAttribute("pr�nom", client.getPrenom());
    		request.setAttribute("email", client.getMail());
    		request.setAttribute("adresse", client.getAdresseFacturation());
    		request.setAttribute("nbEcoutes", client.getNbEcoute());
    		request.setAttribute("dateDeNaissance", client.getDateNaissance());
			
			//mise � jour BDD
			//TODO impl�menter la proc�dure SQL
			
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
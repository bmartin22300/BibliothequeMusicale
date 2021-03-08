package Servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Object.Client;
import Object.Genre;
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
		}else {
			if(servletPath.equals("/InscriptionClient")) {
				//affectation paramètres à la vue
				request.setAttribute("isClient", false);
				//affection vue
				vue = "/JSP/Utilisateur/InscriptionClient.jsp";
			}else {
				//section client
				if(client==null) {//verification que le client est connecté 
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
		            }else {
		            	if(servletPath.equals("/ProfilClient")) {
		                	vue = "/JSP/Client/ProfilClient.jsp";
		                }else {
		                	if(servletPath.equals("/AccueilClient")) {
		                    	vue = "/JSP/Client/AccueilClient.jsp";
		                    }else {
		                    	if(servletPath.equals("/ModificationProfilClient")) {
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
				//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
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
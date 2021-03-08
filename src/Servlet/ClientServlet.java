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
				//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
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
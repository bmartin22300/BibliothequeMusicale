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

import Object.Administrateur;
import Object.ElementCatalogue;
import Object.Genre;
import Object.Interprete;
import Object.ProfilGestionnaireClient;
import Object.ProfilGestionnaireMusical;
import Object.TitreMusical;
import Object.Utilisateur;

public class AdministrateurServlet extends HttpServlet {
	
	public String vue;
	Administrateur administrateur;
	Utilisateur utilisateur=new Utilisateur();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String servletPath = request.getServletPath();//r�cup�ration URL
		
        //affectation param�tres � la vue
        request.setAttribute("isClient", false);
		
        //section administrateur non connect�
		if(servletPath.equals("/AuthentificationAdministrateur")) {	
			//affectation param�tres � la vue
			request.setAttribute("isAdministrateur", false);
			request.setAttribute("isErrorLogin", false);
			request.setAttribute("notLogged", false);
			
			//affection vue
			vue = "/JSP/Utilisateur/AuthentificationAdministrateur.jsp";
		}else {
			if(servletPath.equals("/InscriptionAdministrateur")) {
				//affectation param�tres � la vue
				request.setAttribute("isAdministrateur", false);
				//affection vue
				vue = "/JSP/Utilisateur/InscriptionAdministrateur.jsp";
			}else {
				//section Administrateur
				if(administrateur==null) {//verification que l'Administrateur est connect� 
					//affectation param�tres � la vue
					request.setAttribute("isAdministrateur", false);
		    		request.setAttribute("isErrorLogin", false);
		    		request.setAttribute("notLogged", true);
		    		
		    		//affection vue
		    		vue = "/JSP/Utilisateur/AuthentificationAdministrateur.jsp";
		    	}else {
		    		//affectation param�tres � la vue
		    		request.setAttribute("isAdministrateur", true);
		    		request.setAttribute("isAdministrateurMusical", administrateur instanceof ProfilGestionnaireMusical);
		    		request.setAttribute("password", administrateur.getPassword());
		    		request.setAttribute("email", administrateur.getMail());
		  
		    		//affection vue
		    		if(servletPath.equals("/AccueilAdministrateur")) {
		    			//affectation param�tres � la vue
		    			List<TitreMusical> titresMusicaux = new ArrayList<TitreMusical>();
						List<Interprete> interpretes = new ArrayList<Interprete>();
						TitreMusical titreMusical = new TitreMusical(0, "Ma musique 1", 2000, 210, Genre.RAP, interpretes);
						TitreMusical titreMusical2 = new TitreMusical(1, "Ma musique 2", 1999, 210, Genre.RAP, interpretes);
						TitreMusical titreMusical3 = new TitreMusical(1, "Ma musique 3", 1999, 210, Genre.RAP, interpretes);
						TitreMusical titreMusical4 = new TitreMusical(1, "Ma musique 4", 1999, 210, Genre.RAP, interpretes);
						titresMusicaux.add(titreMusical);
						titresMusicaux.add(titreMusical2);
						titresMusicaux.add(titreMusical3);
						titresMusicaux.add(titreMusical4);
						
						//affectation param�tres � la vue
			    		request.setAttribute("titresMusicaux", titresMusicaux);
			    		request.setAttribute("interpretes", 2);
			    		request.setAttribute("albums", 3);
			    		
		            	vue = "/JSP/Administrateur/AccueilAdministrateur.jsp";
		            }else {
		            	if(servletPath.equals("/ProfilAdministrateur")) {
		                	vue = "/JSP/Administrateur/ProfilAdministrateur.jsp";
		                }else {
		                	if(servletPath.equals("/Statistiques")) {
		                    	vue = "/JSP/Administrateur/Statistiques.jsp";
		                    }else {
		                    	if(servletPath.equals("/ModificationProfil")) {  
		                    		vue = "/JSP/Administrateur/ModificationProfil.jsp";
		                    	}else {
		                    		if(servletPath.equals("/ModificationCatalogue")) {
		                    			request.setAttribute("titresMusicaux",null);
		                    			vue = "/JSP/Administrateur/ModificationCatalogue.jsp";
		                    		}else {
		                    			if(servletPath.equals("/ModificationTitre")) {
		                    				//section Administrateur musical
		                    				//todo v�rif identit�
		                    				
		                    				//r�cup�ration de param�tre de la vue
		                    				String titre = request.getParameter("titre");
		                    				
		                    				//envoie de parametres a la vue
		                    				request.setAttribute("titre", titre);
		                    				
			                    			vue = "/JSP/Administrateur/ModificationCatalogue.jsp";
			                    		}else {
			                    			if(servletPath.equals("/AjoutCatalogue")) {
			                    				//r�cup�ration de param�tre de la vue
			                    				String TypeElement = request.getParameter("TypeElement");
			                    				
			                    				vue = "/JSP/Administrateur/AjoutCatalogue.jsp";
			                    			}
			                    		}
		                    		}
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
		request.setAttribute("isClient", false);
		
		//r�cup�ration des param�tres du form
		String mail = request.getParameter("mail");
		String motDePasse = request.getParameter("password");
		String typeAdmin = request.getParameter("typeAdmin");
		
		if(action.equals("ModificationProfilAdministrateur")) {
			//r�cup�ration param�tres
			
			//affectation param�tres � la vue
			request.setAttribute("isAdministrateur", true);
			
			//mise � jour BDD
			//TODO impl�menter la proc�dure SQL
			
    		//choix de la vue
			vue = "/JSP/Administrateur/ProfilAdministrateur.jsp";
		}else {
			if(action.equals("AuthentificationAdministrateur")) {
				//requ�te � la BDD 
				administrateur = utilisateur.authentificationAdmin(mail, motDePasse);
				
				if(administrateur!=null) {
					List<TitreMusical> titresMusicaux = new ArrayList<TitreMusical>();
					List<Interprete> interpretes = new ArrayList<Interprete>();
					TitreMusical titreMusical = new TitreMusical(0, "Ma musique 1", 2000, 210, Genre.RAP, interpretes);
					TitreMusical titreMusical2 = new TitreMusical(1, "Ma musique 2", 1999, 210, Genre.RAP, interpretes);
					TitreMusical titreMusical3 = new TitreMusical(1, "Ma musique 3", 1999, 210, Genre.RAP, interpretes);
					TitreMusical titreMusical4 = new TitreMusical(1, "Ma musique 4", 1999, 210, Genre.RAP, interpretes);
					titresMusicaux.add(titreMusical);
					titresMusicaux.add(titreMusical2);
					titresMusicaux.add(titreMusical3);
					titresMusicaux.add(titreMusical4);
					
					//affectation param�tres � la vue
					request.setAttribute("isAdministrateur", true);
					request.setAttribute("isAdministrateurMusical", administrateur instanceof ProfilGestionnaireMusical);
		    		request.setAttribute("titresMusicaux", titresMusicaux);
		    		//request.setAttribute("interpretes", administrateur.rechercherParInterprete());
		    		request.setAttribute("interpretes", 3);
		    		System.out.println(administrateur.rechercherParPseudoInterprete(""));
		    		request.setAttribute("albums", 3);
					
					//choix de la vue
					vue = "/JSP/Administrateur/AccueilAdministrateur.jsp";
				}else {//echec
					//affectation param�tres � la vue
					request.setAttribute("isAdministrateur", false);
					request.setAttribute("isErrorLogin", true);
					request.setAttribute("notLogged", false);
					
					//choix de la vue
					vue = "/JSP/Utilisateur/AuthentificationAdministrateur.jsp";
				}
			}else {
				//partie administrateur musical connecte
				//affectation param�tres � la vue
				request.setAttribute("isAdministrateur", true);
				request.setAttribute("isAdministrateurMusical", true);
				
				//todo verifier connexion admin
				
				if(action.equals("RechercheTitre")) {
					//r�cup param vue
					String typeElement = request.getParameter("TypeElement");
					String titre = request.getParameter("titre");
					
					//todo impl�menter requ�te SQL
					List<TitreMusical> titresMusicaux = new ArrayList<TitreMusical>();
					//List<Interprete> interpretes = new ArrayList<Interprete>();
					TitreMusical titreMusical = new TitreMusical(0, "Ma musique 1", 2000, 210, Genre.RAP, null);
					TitreMusical titreMusical2 = new TitreMusical(1, "Ma musique 2", 1999, 210, Genre.RAP, null);
					titresMusicaux.add(titreMusical);
					titresMusicaux.add(titreMusical2);
					
					List<Interprete> interpretes = administrateur.rechercherParPseudoInterprete(titre);
					
					//envoi param vue
					request.setAttribute("titresMusicaux", titresMusicaux);
					request.setAttribute("interpretes", interpretes);
					request.setAttribute("albums", null);
					request.setAttribute("TypeElement", typeElement);
					request.setAttribute("isAdministrateur", true);
					request.setAttribute("isAdministrateurMusical", true);
					
					//attribution vue
					vue = "/JSP/Administrateur/ModificationCatalogue.jsp";
					/*
					if(typeElement.equals("Titres musicaux")){
						
					}else {
						if(typeElement.equals("Interpretes")){
							
						}else {
							if(typeElement.equals("Albums")){
								
							}
						}
					}*/
				}else {
					if(action.equals("AjouterElement")) {
						//r�cup param vue
						String typeElement = request.getParameter("TypeElement");
						
						if(typeElement.equals("Titre musical")) {
							
						}else {
							if(typeElement.equals("Interprete")) {
								//r�cup param vue
								String Pseudo = request.getParameter("Pseudo");
								String Prenom = request.getParameter("Prenom");
								String Nom = request.getParameter("Nom");
								String DateNaissanceString = request.getParameter("Date de naissance");
								System.out.println(DateNaissanceString);
								Date dateNaissance=null;
								if(DateNaissanceString!="") {
									dateNaissance = Date.valueOf(DateNaissanceString); //Conversion Date
								}
								System.out.println("ntm");
								System.out.println(dateNaissance);
								
								//requete BDD
								Interprete interprete = ((ProfilGestionnaireMusical) administrateur).creerInterprete(Pseudo,Nom,Prenom,dateNaissance);
								System.out.println(interprete);
								
								//attribution vue
								vue = "/JSP/Administrateur/AjoutCatalogue.jsp";
							}else {
								if(typeElement.equals("Album")) {
									
								}
							}
						}
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
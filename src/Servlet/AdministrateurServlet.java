package Servlet;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Object.Administrateur;
import Object.Album;
import Object.Client;
import Object.Genre;
import Object.Interprete;
import Object.ProfilGestionnaireClient;
import Object.ProfilGestionnaireMusical;
import Object.TitreMusical;
import Object.Utilisateur;

public class AdministrateurServlet extends HttpServlet {

	public String vue;
	Administrateur administrateur;
	Utilisateur utilisateur = new Utilisateur();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String servletPath = request.getServletPath();// recuperation URL

		// affectation parametres e la vue
		request.setAttribute("isClient", false);

		// section administrateur non connecte
		if (servletPath.equals("/AuthentificationAdministrateur")) {
			request.setAttribute("nav-bar-active", "Connexion");
			// affectation parametres e la vue
			request.setAttribute("isAdministrateur", false);
			request.setAttribute("isErrorLogin", false);
			request.setAttribute("notLogged", false);

			// affection vue
			vue = "/JSP/Utilisateur/AuthentificationAdministrateur.jsp";
		} else if (servletPath.equals("/InscriptionAdministrateur")) {
			request.setAttribute("nav-bar-active", "Connexion");
			// affectation parametres e la vue
			request.setAttribute("isAdministrateur", false);
			// affection vue
			vue = "/JSP/Utilisateur/InscriptionAdministrateur.jsp";
		}
		/////////////////////////////////////////////////////// section Administrateur //////////////////////////// ////////////////////////////
		else if (administrateur == null) {// verification que l'Administrateur est connecte
			request.setAttribute("nav-bar-active", "Connexion");
			// affectation parametres e la vue
			request.setAttribute("isAdministrateur", false);
			request.setAttribute("isErrorLogin", false);
			request.setAttribute("notLogged", true);

			// affection vue
			vue = "/JSP/Utilisateur/AuthentificationAdministrateur.jsp";
		} else {
			// affectation parametres e la vue
			request.setAttribute("isAdministrateur", true);
			request.setAttribute("isAdministrateurMusical", administrateur instanceof ProfilGestionnaireMusical);
			request.setAttribute("isAdministrateurClient", administrateur instanceof ProfilGestionnaireClient);
			request.setAttribute("password", administrateur.getPassword());
			request.setAttribute("email", administrateur.getMail());

			// requeteBDD
			List<TitreMusical> titresMusicaux = administrateur.rechercherParNomTitre("");
			List<Interprete> interpretes = administrateur.rechercherParPseudoInterprete("");
			List<Album> albums = administrateur.rechercherParNomAlbum("");

			// envoie de parametres a la vue
			request.setAttribute("titresMusicaux", titresMusicaux);
			request.setAttribute("interpretes", interpretes);
			request.setAttribute("albums", albums);

			if (servletPath.equals("/AccueilAdministrateur")) {
				request.setAttribute("nav-bar-active", "AccueilAdministrateur");
				// requeteBDD
				titresMusicaux = administrateur.rechercherParNomTitre("");
				interpretes = administrateur.rechercherParPseudoInterprete("");
				albums = administrateur.rechercherParNomAlbum("");

				// envoie de parametres a la vue
				request.setAttribute("titresMusicaux", titresMusicaux);
				request.setAttribute("interpretes", interpretes);
				request.setAttribute("albums", albums);

				vue = "/JSP/Administrateur/AccueilAdministrateur.jsp";
			} else if (servletPath.equals("/ProfilAdministrateur")) {
				request.setAttribute("nav-bar-active", "ProfilAdministrateur");
				vue = "/JSP/Administrateur/ProfilAdministrateur.jsp";
			} else if (servletPath.equals("/Statistiques")) {
				request.setAttribute("nav-bar-active", "Statistiques");
				List<Client> clients = administrateur.topUtilisateursEcoutes();
				request.setAttribute("clients", clients);

				List<TitreMusical> titres = administrateur.topTitresEcoutes();
				request.setAttribute("titres", titres);
				vue = "/JSP/Administrateur/Statistiques.jsp";
			} else if (servletPath.equals("/ModificationProfil")) {
				request.setAttribute("nav-bar-active", "ModificationProfil");
				List<Client> clients = administrateur.rechercherParMailClient("");
				request.setAttribute("clients", clients);
				vue = "/JSP/Administrateur/ModificationProfil.jsp";
			} else if (servletPath.equals("/ModificationCatalogue")) {
				request.setAttribute("nav-bar-active", "ModificationCatalogue");
				vue = "/JSP/Administrateur/ModificationCatalogue.jsp";
			} else if (servletPath.equals("/AjoutCatalogue")) {
				request.setAttribute("nav-bar-active", "AjoutCatalogue");
				// recuperation de parametre de la vue

				vue = "/JSP/Administrateur/AjoutCatalogue.jsp";
			}else if(servletPath.equals("/ModificationProfilAdministrateur")){
				request.setAttribute("nav-bar-active", "ProfilAdministrateur");
                vue = "/JSP/Administrateur/ModificationProfilAdministrateur.jsp";
	        }
		}

		// affichage vue
		RequestDispatcher rd = getServletContext().getRequestDispatcher(vue);
		try {
			rd.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");// de quel form je viens ?

		// affectation parametres e la vue
		request.setAttribute("isClient", false);

		// recuperation des parametres du form
		String mail = request.getParameter("mail");
		String motDePasse = request.getParameter("password");
		String typeAdmin = request.getParameter("typeAdmin");

		if (action.equals("AuthentificationAdministrateur")) {
			// requete e la BDD
			administrateur = utilisateur.authentificationAdmin(mail, motDePasse);

			if (administrateur != null) {
				request.setAttribute("nav-bar-active", "AccueilAdministrateur");
				// requeteBDD
				List<TitreMusical> titresMusicaux = administrateur.rechercherParNomTitre("");
				List<Interprete> interpretes = administrateur.rechercherParPseudoInterprete("");
				List<Album> albums = administrateur.rechercherParNomAlbum("");
				List<Client> clients = administrateur.rechercherParMailClient("");

				// envoie de parametres a la vue
				request.setAttribute("titresMusicaux", titresMusicaux);
				request.setAttribute("interpretes", interpretes);
				request.setAttribute("albums", albums);
				request.setAttribute("isAdministrateur", true);
				request.setAttribute("isAdministrateurMusical",
						administrateur instanceof ProfilGestionnaireMusical);

				// choix de la vue
				vue = "/JSP/Administrateur/AccueilAdministrateur.jsp";
			} else {// echec
				request.setAttribute("nav-bar-active", "Connexion");
				// affectation parametres e la vue
				request.setAttribute("isAdministrateur", false);
				request.setAttribute("isErrorLogin", true);
				request.setAttribute("notLogged", false);

				// choix de la vue
				vue = "/JSP/Utilisateur/AuthentificationAdministrateur.jsp";
			}
		} else {
			///////////////////////////////////////////////////////partie ADMIN///////////////////////////////////////////////////////
			if (administrateur == null) {// verification que l'Administrateur est connecte
				request.setAttribute("nav-bar-active", "Connexion");
				// affectation parametres e la vue
				request.setAttribute("isAdministrateur", false);
				request.setAttribute("isErrorLogin", false);
				request.setAttribute("notLogged", true);

				// affection vue
				vue = "/JSP/Utilisateur/AuthentificationAdministrateur.jsp";
			}
			///////////////////////////////////////////////////////PARTIE COMMUNE///////////////////////////////////////////////////////
			// requeteBDD
			List<TitreMusical> titresMusicaux = administrateur.rechercherParNomTitre("");
			List<Interprete> interpretes = administrateur.rechercherParPseudoInterprete("");
			List<Album> albums = administrateur.rechercherParNomAlbum("");
			List<Client> clients = administrateur.rechercherParMailClient("");

			// envoie de parametres a la vue
			request.setAttribute("titresMusicaux", titresMusicaux);
			request.setAttribute("interpretes", interpretes);
			request.setAttribute("albums", albums);

			// affectation parametres e la vue
			request.setAttribute("isAdministrateur", true);
			request.setAttribute("isAdministrateurClient", false);
			request.setAttribute("isAdministrateurMusical", false);

			if (action.equals("ModificationProfilAdministrateur")) {
				request.setAttribute("nav-bar-active", "ProfilAdministrateur");
				// affectation parametres e la vue
				request.setAttribute("password", "");
				request.setAttribute("email", mail);

				// mise e jour BDD
				Administrateur administrateurModifie = administrateur.modifierInformations(mail, motDePasse);
				if (administrateurModifie != null) {
					this.administrateur = administrateurModifie;
				}

				// choix de la vue
				vue = "/JSP/Administrateur/ProfilAdministrateur.jsp";
			}else if(action.equals("incrementeAudio")) {
				String idTitreMusical = request.getParameter("idTitreMusical");
				int id = Integer.parseInt(idTitreMusical);
				TitreMusical t = administrateur.getTitreMusical(id);
				administrateur.regarder(t);
			}else if(action.equals("RechercheAccueil")) {
				request.setAttribute("nav-bar-active", "AccueilAdministrateur");
				// On recupere les parametres de la vue
				String typeElement = request.getParameter("TypeElement");
				String recherche = request.getParameter("recherche");

				if(typeElement.equals("Titres musicaux")) {
					titresMusicaux=administrateur.rechercherParNomTitre(recherche);
					request.setAttribute("titresMusicaux", titresMusicaux);
				}else if(typeElement.equals("Interpretes")) {
					interpretes = administrateur.rechercherParPseudoInterprete(recherche);
					request.setAttribute("interpretes", interpretes);
				}else if(typeElement.equals("Albums")) {
					albums = administrateur.rechercherParNomAlbum(recherche);
					request.setAttribute("albums", albums);
				}

				// envoi param vue
				request.setAttribute("TypeElement", typeElement);

				// attribution vue
				vue = "/JSP/Administrateur/AccueilAdministrateur.jsp"; 
			}
			
			///////////////////////////////////////////////////////PARTIE GESTIONNAIRE MUSICAL///////////////////////////////////////////////////////
			if(administrateur instanceof ProfilGestionnaireMusical) {
				request.setAttribute("isAdministrateurClient", false);
				request.setAttribute("isAdministrateurMusical", true);
				
				if (action.equals("RechercheTitre")) {
					request.setAttribute("nav-bar-active", "ModificationCatalogue");
					// recup param vue
					String typeElement = request.getParameter("TypeElement");
					String recherche = request.getParameter("recherche");

					if(typeElement.equals("Titres musicaux")) {
						titresMusicaux=administrateur.rechercherParNomTitre(recherche);
						request.setAttribute("titresMusicaux", titresMusicaux);
					}else if(typeElement.equals("Interpretes")) {
						interpretes = administrateur.rechercherParPseudoInterprete(recherche);
						request.setAttribute("interpretes", interpretes);
					}else if(typeElement.equals("Albums")) {
						albums = administrateur.rechercherParNomAlbum(recherche);
						request.setAttribute("albums", albums);
					}

					// envoi param vue
					request.setAttribute("TypeElement", typeElement);

					// attribution vue
					vue = "/JSP/Administrateur/ModificationCatalogue.jsp";

				} else if (action.equals("AjouterElement")) {
					request.setAttribute("nav-bar-active", "AjoutCatalogue");
					// recup param vue
					String typeElement = request.getParameter("TypeElement");

					if (typeElement.equals("Titre musical")) {
						// recup param vue
						String Titre = request.getParameter("Titre");
						String GenreString = request.getParameter("Genre");
						Genre styleMusique = Genre.valueOf(GenreString);
						String anneCreationString = request.getParameter("Annee de creation");
						int AnneeCreation = 0;
						if (!anneCreationString.equals("")) {
							AnneeCreation = Integer.parseInt(request.getParameter("Annee de creation"));
						}
						String DureeString = request.getParameter("Duree");
						int Duree = 0;
						if (!DureeString.equals("")) {
							Duree = Integer.parseInt(request.getParameter("Duree"));
						}

						//requete BDD
						TitreMusical titre = ((ProfilGestionnaireMusical) administrateur).creerTitre(Titre, AnneeCreation, null, Duree, styleMusique);

						//envoi param vue
						request.setAttribute("titre", titre);
						request.setAttribute("interpretes", interpretes);

						//attribution vue
						vue = "/JSP/Administrateur/AjoutInterpretesATitre.jsp";
					}else if(typeElement.equals("Interprete")) {
						//recup param vue
						String Pseudo = request.getParameter("Pseudo");
						String Prenom = request.getParameter("Prenom");
						String Nom = request.getParameter("Nom");
						String DateNaissanceString = request.getParameter("Date de naissance");
						Date dateNaissance = null;
						if (DateNaissanceString != "") {
							dateNaissance = Date.valueOf(DateNaissanceString); // Conversion Date
						}

						// requete BDD
						Interprete interprete = ((ProfilGestionnaireMusical) administrateur)
								.creerInterprete(Pseudo, Nom, Prenom, dateNaissance);

						//attribution vue
						vue = "/JSP/Administrateur/AjoutCatalogue.jsp"; 
					}else if(typeElement.equals("Album")) {
						//recup param vue
						String Nom = request.getParameter("Nom2");
						String AnneeSortieString = request.getParameter("Annee de sortie");
						String DureeString = request.getParameter("Duree");

						//cast String to int
						int AnneeSortie=0;
						if(!AnneeSortieString.equals("")) {
							AnneeSortie=Integer.parseInt(AnneeSortieString);
						}
						int Duree=0;
						if(!DureeString.equals("")) {
							Duree=Integer.parseInt(DureeString);
						}

						// requete BDD
						//todo ajouter la duree dans la procedure sql
						Album album = ((ProfilGestionnaireMusical) administrateur).creerAlbum(Nom, AnneeSortie, null);

						//envoi param vue
						request.setAttribute("album", album);
						request.setAttribute("titresMusicaux", titresMusicaux);

						//attribution vue
						vue = "/JSP/Administrateur/AjoutTitresAAlbum.jsp"; 
					}
				} else if (action.equals("SuppressionTitre")) {
					// recup param vue
					String idString = request.getParameter("idString");
					int id = Integer.parseInt(idString);
					TitreMusical titre = administrateur.getTitreMusical(id);
					((ProfilGestionnaireMusical) administrateur).supprimerTitre(titre);

					// requeteBDD
					titresMusicaux = administrateur.rechercherParNomTitre("");

					// envoie de parametres a la vue
					request.setAttribute("titresMusicaux", titresMusicaux);
					request.setAttribute("TypeElement", "Titres musicaux");
				}else if(action.equals("SuppressionInterprete")) {
					//recup param vue
					String idString = request.getParameter("idString");
					int id = Integer.parseInt(idString);
					Interprete i = administrateur.getInterprete(id);
					((ProfilGestionnaireMusical) administrateur).supprimerInterprete(i);

					// requeteBDD
					interpretes = administrateur.rechercherParPseudoInterprete("");

					// envoie de parametres a la vue
					request.setAttribute("interpretes", interpretes);
					request.setAttribute("TypeElement", "Interpretes");
				}else if(action.equals("SuppressionAlbum")) {
					// recup param vue
					String idString = request.getParameter("idString");
					int id = Integer.parseInt(idString);
					Album album = administrateur.getAlbum(id);
					((ProfilGestionnaireMusical) administrateur).supprimerAlbum(album);

					// requeteBDD
					albums = administrateur.rechercherParNomAlbum("");

					// envoie de parametres a la vue
					request.setAttribute("albums", albums);
					request.setAttribute("TypeElement", "Albums");
				}else if(action.equals("AjoutInterpretesATitre")) {
					request.setAttribute("nav-bar-active", "AjoutCatalogue");
					//recup param vue
					String interpreteSelectBox = request.getParameter("interpreteSelectBox");
					TitreMusical titre = (TitreMusical) request.getSession().getAttribute("titre");

					int id = Integer.parseInt(interpreteSelectBox);

					Interprete interprete = null;
					//todo ajouter methode rechercherInterpreteParId
					for(Interprete i : interpretes){
						if(i.getId()==id) {
							interprete=i;
						}
					}

					//envoi param vue
					request.setAttribute("titre", titre);

					//requete BDD
					((ProfilGestionnaireMusical) administrateur).ajouterDiscographie(titre, interprete);
					request.setAttribute("interpretesAssocies", titre.getInterpretes());

					//affectation vue
					vue = "/JSP/Administrateur/AjoutInterpretesATitre.jsp"; 
				}else if(action.equals("AjoutTitresAAlbum")) {
					request.setAttribute("nav-bar-active", "AjoutCatalogue");
					//recup param vue
					String titresMusicauxSelectBox = request.getParameter("titresMusicauxSelectBox");
					Album album = (Album) request.getSession().getAttribute("album");

					int id = Integer.parseInt(titresMusicauxSelectBox);

					TitreMusical titreMusical = null;
					//todo ajouter methode rechercherInterpreteParId
					for(TitreMusical t : titresMusicaux){
						if(t.getIdCatalogue()==id) {
							titreMusical=t;
						}
					}

					//envoi param vue
					request.setAttribute("album", album);

					//requete BDD
					((ProfilGestionnaireMusical) administrateur).ajoutTitreAlbum(titreMusical, album);
					request.setAttribute("titresMusicauxAssocies", album.getTitres());

					//affectation vue
					vue = "/JSP/Administrateur/AjoutTitresAAlbum.jsp"; 
				}else if(action.equals("FinAjoutInterpretesATitre")) {
					request.setAttribute("nav-bar-active", "AjoutCatalogue");
					//affectation vue
					vue = "/JSP/Administrateur/AjoutCatalogue.jsp"; 
				}else if(action.equals("FinAjoutTitresMusicauxAAlbum")) {
					request.setAttribute("nav-bar-active", "AjoutCatalogue");
					//affectation vue
					vue = "/JSP/Administrateur/AjoutCatalogue.jsp"; 
				}		
								
				//modififcation des elements du catalogue
				
				//interprete
				
				else if(action.equals("ModificationInterprete")) {
					request.setAttribute("nav-bar-active", "ModificationCatalogue");
					//recup param vue
					String idString = request.getParameter("idString");

					//cast int
					int id=Integer.parseInt(idString);
					
					//requete BDD
					Interprete i = administrateur.getInterprete(id);
					
					//envoi param vue
					request.getSession().setAttribute("i", i);

					//affectation vue
					vue = "/JSP/Administrateur/ModificationInterprete.jsp";
				}else if(action.equals("ModificationInterpreteFin")) {
					//recup param vue
					String Pseudo = request.getParameter("Pseudo");
					String Prenom = request.getParameter("Prenom");
					String Nom = request.getParameter("Nom");
					String DateNaissanceString = request.getParameter("Date de naissance");
					String idString = request.getParameter("id");
					
					//cast 
					int id=Integer.parseInt(idString); 
					Date dateNaissance = null;
					if (DateNaissanceString != "") {
						dateNaissance = Date.valueOf(DateNaissanceString); // Conversion Date
					}
					
					//requete BDD
					Interprete i = administrateur.getInterprete(id);
					((ProfilGestionnaireMusical) administrateur).modifierInterprete(i, Pseudo, Prenom, Nom, dateNaissance);
					interpretes=administrateur.rechercherParPseudoInterprete("");
					
					//envoi param vue
					request.setAttribute("TypeElement","Interpretes");
					request.setAttribute("interpretes",interpretes);
					
					//affectation vue
					vue = "/JSP/Administrateur/ModificationCatalogue.jsp";
				}
				
				//titre musical
				
				else if(action.equals("ModificationTitreMusical")) {
					//recup param vue
					String idString = request.getParameter("idString");

					//cast int
					int id=Integer.parseInt(idString);
					
					//requete BDD
					TitreMusical t = administrateur.getTitreMusical(id);
					
					//envoi param vue
					request.getSession().setAttribute("t", t);
					request.setAttribute("nav-bar-active", "ModificationCatalogue");

					//affectation vue
					vue = "/JSP/Administrateur/ModificationTitreMusical.jsp";
				}else if(action.equals("ModificationTitreMusicalFin")) {
					//recup param vue
					String Titre = request.getParameter("Titre");
					String GenreString = request.getParameter("Genre");
					String AnneeCreationString = request.getParameter("Annee de creation");
					String DureeString = request.getParameter("Duree");
					String idString = request.getParameter("id");
					
					//cast 
					int id=Integer.parseInt(idString); 
					Genre genre = null;
					if (GenreString != "") {
						genre = Genre.valueOf(GenreString); // Conversion genre
					}
					int AnneeCreation = 0;
					if (AnneeCreationString != "") {
						AnneeCreation = Integer.parseInt(AnneeCreationString);  
					}
					int Duree = 0;
					if (DureeString != "") {
						Duree = Integer.parseInt(DureeString);  
					}
					
					//requete BDD
					TitreMusical t = administrateur.getTitreMusical(id);
					((ProfilGestionnaireMusical) administrateur).modifierTitre(t, Titre, AnneeCreation, Duree, genre);
					titresMusicaux=administrateur.rechercherParNomTitre("");
					
					//envoi param vue
					request.getSession().setAttribute("titresMusicaux",titresMusicaux);
					request.getSession().setAttribute("interpretes",interpretes);
					request.getSession().setAttribute("interpretesAssocies",t.getInterprete());
					request.getSession().setAttribute("t",t);
					
					//affectation vue
					vue = "/JSP/Administrateur/ModificationTitreMusicalInterpretes.jsp";
				}else if(action.equals("ModificationTitreMusicalInterpretesAjout")) {
					request.setAttribute("nav-bar-active", "AjoutCatalogue");
					//recup param vue
					String interpreteSelectBox = request.getParameter("interpreteSelectBox");
					TitreMusical titre = (TitreMusical) request.getSession().getAttribute("titre");
					
					int id = Integer.parseInt(interpreteSelectBox);

					Interprete i = administrateur.getInterprete(id);

					//envoi param vue
					request.setAttribute("titre", titre);

					//requete BDD
					((ProfilGestionnaireMusical) administrateur).ajouterDiscographie(titre, i);
					request.getSession().setAttribute("interpretesAssocies", titre.getInterpretes());

					//affectation vue
					vue = "/JSP/Administrateur/ModificationTitreMusicalInterpretes.jsp"; 
				}else if(action.equals("ModificationTitreMusicalInterpretesFin")){
					//envoi param vue
					request.setAttribute("TypeElement","Titres musicaux");
					
					//affectation vue
					vue = "/JSP/Administrateur/ModificationCatalogue.jsp"; 
				}
				
				//albums
				
				else if(action.equals("ModificationAlbum")) {
					//recup param vue
					String idString = request.getParameter("idString");

					//cast int
					int id=Integer.parseInt(idString);
					
					//requete BDD
					Album a = administrateur.getAlbum(id);
					
					//envoi param vue
					request.getSession().setAttribute("a", a);
					request.setAttribute("nav-bar-active", "ModificationCatalogue");

					//affectation vue
					vue = "/JSP/Administrateur/ModificationAlbum.jsp";
				}else if(action.equals("ModificationAlbumFin")) {
					//recup param vue
					String Titre = request.getParameter("Titre");
					String AnneeCreationString = request.getParameter("Annee de creation");
					String idString = request.getParameter("id");
					
					//cast 
					int id=Integer.parseInt(idString); 
					int AnneeCreation = 0;
					if (AnneeCreationString != "") {
						AnneeCreation = Integer.parseInt(AnneeCreationString);  
					}
					
					//requete BDD
					Album a = administrateur.getAlbum(id);
					((ProfilGestionnaireMusical) administrateur).modifierAlbum(a, Titre, AnneeCreation);
					albums=administrateur.rechercherParNomAlbum("");
					
					//envoi param vue
					request.getSession().setAttribute("albums",albums);
					request.getSession().setAttribute("titresMusicaux",titresMusicaux);
					request.getSession().setAttribute("titresMusicauxAssocies", a.getTitres());
					request.getSession().setAttribute("a",a);
					
					//affectation vue
					vue = "/JSP/Administrateur/ModificationAlbumTitres.jsp";
				}else if(action.equals("ModificationAlbumTitresAjout")) {
					request.setAttribute("nav-bar-active", "AjoutCatalogue");
					//recup param vue
					String interpreteSelectBox = request.getParameter("interpreteSelectBox");
					Album album = (Album) request.getSession().getAttribute("album");
					
					int id = Integer.parseInt(interpreteSelectBox);

					TitreMusical t = administrateur.getTitreMusical(id);

					//envoi param vue
					request.setAttribute("Album", album);

					//requete BDD
					((ProfilGestionnaireMusical) administrateur).ajoutTitreAlbum(t, album);
					request.getSession().setAttribute("titresMusicauxAssocies", album.getTitres());

					//affectation vue
					vue = "/JSP/Administrateur/ModificationAlbumTitres.jsp"; 
				}else if(action.equals("ModificationAlbumTitresFin")){
					//envoi param vue
					request.setAttribute("TypeElement","Albums");
					
					//affectation vue
					vue = "/JSP/Administrateur/ModificationCatalogue.jsp"; 
				}
			}
			///////////////////////////////////////////////////////PARTIE GESTIONNAIRE CLIENT///////////////////////////////////////////////////////
			else if(administrateur instanceof ProfilGestionnaireClient) {
				request.setAttribute("isAdministrateurClient", true);
				request.setAttribute("isAdministrateurMusical", false);
				
				// Lecture seule
				if(action.equals("ModificationProfil")) {
					request.setAttribute("nav-bar-active", "ModificationProfil");
					//recuperer parametre vue
					String typeElement = request.getParameter("TypeElement");
					String recherche = request.getParameter("recherche");

					clients=administrateur.rechercherParMailClient(recherche);
					request.setAttribute("clients", clients);
					
					// envoi param vue
					request.setAttribute("TypeElement", typeElement);

					//affectation vue
					vue = "/JSP/Administrateur/ModificationProfil.jsp";
				}else if(action.equals("SuppressionClient")) {
					request.setAttribute("nav-bar-active", "ModificationProfil");
					// recup param vue
					String idString = request.getParameter("idString");
					int id = Integer.parseInt(idString);
					Client client = administrateur.getClient(id);
					((ProfilGestionnaireClient) administrateur).supprimerClient(client);

					// requeteBDD
					clients = administrateur.rechercherParMailClient("");

					// envoie de parametres a la vue
					request.setAttribute("clients", clients);
					vue = "/JSP/Administrateur/ModificationProfil.jsp";
				}
			}
		}
		// affichage de la vue
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
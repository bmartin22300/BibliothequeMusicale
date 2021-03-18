package Servlet;

import java.io.IOException;
import java.sql.Date;
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
		String servletPath = request.getServletPath();// rï¿½cupï¿½ration URL

		// affectation paramï¿½tres ï¿½ la vue
		request.setAttribute("isClient", false);

		// section administrateur non connectï¿½
		if (servletPath.equals("/AuthentificationAdministrateur")) {
			// affectation paramï¿½tres ï¿½ la vue
			request.setAttribute("isAdministrateur", false);
			request.setAttribute("isErrorLogin", false);
			request.setAttribute("notLogged", false);

			// affection vue
			vue = "/JSP/Utilisateur/AuthentificationAdministrateur.jsp";
		} else if (servletPath.equals("/InscriptionAdministrateur")) {
			// affectation paramï¿½tres ï¿½ la vue
			request.setAttribute("isAdministrateur", false);
			// affection vue
			vue = "/JSP/Utilisateur/InscriptionAdministrateur.jsp";
		}
		/////////////////////////////////////////////////////// section Administrateur //////////////////////////// ////////////////////////////
		else if (administrateur == null) {// verification que l'Administrateur est connectï¿½
			// affectation paramï¿½tres ï¿½ la vue
			request.setAttribute("isAdministrateur", false);
			request.setAttribute("isErrorLogin", false);
			request.setAttribute("notLogged", true);

			// affection vue
			vue = "/JSP/Utilisateur/AuthentificationAdministrateur.jsp";
		} else {
			// affectation paramï¿½tres ï¿½ la vue
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
				vue = "/JSP/Administrateur/ProfilAdministrateur.jsp";
			} else if (servletPath.equals("/Statistiques")) {
				List<Client> clients = administrateur.topUtilisateursEcoutes();
				request.setAttribute("clients", clients);

				List<TitreMusical> titres = administrateur.topTitresEcoutes();
				request.setAttribute("titres", titres);
				vue = "/JSP/Administrateur/Statistiques.jsp";
			} else if (servletPath.equals("/ModificationProfil")) {
				List<Client> clients = administrateur.rechercherParMailClient("");
				request.setAttribute("clients", clients);
				vue = "/JSP/Administrateur/ModificationProfil.jsp";
			} else if (servletPath.equals("/ModificationCatalogue")) {
				vue = "/JSP/Administrateur/ModificationCatalogue.jsp";
			} else if (servletPath.equals("/ModificationTitre")) {
				// section Administrateur musical
				// todo vï¿½rif identitï¿½

				// rï¿½cupï¿½ration de paramï¿½tre de la vue
				String titre = request.getParameter("titre");

				// envoie de parametres a la vue
				request.setAttribute("titre", titre);

				vue = "/JSP/Administrateur/ModificationCatalogue.jsp";
			} else if (servletPath.equals("/AjoutCatalogue")) {
				// rï¿½cupï¿½ration de paramï¿½tre de la vue
				String TypeElement = request.getParameter("TypeElement");

				vue = "/JSP/Administrateur/AjoutCatalogue.jsp";
			}else if(servletPath.equals("/ModificationProfilAdministrateur")){
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

		// affectation paramï¿½tres ï¿½ la vue
		request.setAttribute("isClient", false);

		// rï¿½cupï¿½ration des paramï¿½tres du form
		String mail = request.getParameter("mail");
		String motDePasse = request.getParameter("password");
		String typeAdmin = request.getParameter("typeAdmin");

		if (action.equals("AuthentificationAdministrateur")) {
			// requï¿½te ï¿½ la BDD
			administrateur = utilisateur.authentificationAdmin(mail, motDePasse);

			if (administrateur != null) {
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
				// affectation paramï¿½tres ï¿½ la vue
				request.setAttribute("isAdministrateur", false);
				request.setAttribute("isErrorLogin", true);
				request.setAttribute("notLogged", false);

				// choix de la vue
				vue = "/JSP/Utilisateur/AuthentificationAdministrateur.jsp";
			}
		} else {
			///////////////////////////////////////////////////////partie ADMIN///////////////////////////////////////////////////////
			if (administrateur == null) {// verification que l'Administrateur est connectï¿½
				// affectation paramï¿½tres ï¿½ la vue
				request.setAttribute("isAdministrateur", false);
				request.setAttribute("isErrorLogin", false);
				request.setAttribute("notLogged", true);

				// affection vue
				vue = "/JSP/Utilisateur/AuthentificationAdministrateur.jsp";
			}
			// PARTIE COMMUNE
			// requeteBDD
			List<TitreMusical> titresMusicaux = administrateur.rechercherParNomTitre("");
			List<Interprete> interpretes = administrateur.rechercherParPseudoInterprete("");
			List<Album> albums = administrateur.rechercherParNomAlbum("");
			List<Client> clients = administrateur.rechercherParMailClient("");

			// envoie de parametres a la vue
			request.setAttribute("titresMusicaux", titresMusicaux);
			request.setAttribute("interpretes", interpretes);
			request.setAttribute("albums", albums);

			// affectation paramï¿½tres ï¿½ la vue
			request.setAttribute("isAdministrateur", true);
			request.setAttribute("isAdministrateurClient", false);
			request.setAttribute("isAdministrateurMusical", false);

			if (action.equals("ModificationProfilAdministrateur")) {
				// affectation paramï¿½tres ï¿½ la vue
				request.setAttribute("password", "");
				request.setAttribute("email", mail);

				// mise ï¿½ jour BDD
				Administrateur administrateurModifie = administrateur.modifierInformations(mail, motDePasse);
				if (administrateurModifie != null) {
					this.administrateur = administrateurModifie;
				}

				// choix de la vue
				vue = "/JSP/Administrateur/ProfilAdministrateur.jsp";
			}else if(action.equals("incrementeAudio")) {
				String idTitreMusical = request.getParameter("idTitreMusical");
				int id = Integer.parseInt(idTitreMusical);
				for(TitreMusical t : titresMusicaux) {
					if(t.getIdCatalogue()==id) {
						administrateur.regarder(t);
					}
				}
			}else if(action.equals("RechercheAccueil")) {
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
			
			//PARTIE GESTIONNAIRE MUSICAL
			if(administrateur instanceof ProfilGestionnaireMusical) {
				request.setAttribute("isAdministrateurClient", false);
				request.setAttribute("isAdministrateurMusical", true);
				
				if (action.equals("RechercheTitre")) {
					// rï¿½cup param vue
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
					// rï¿½cup param vue
					String typeElement = request.getParameter("TypeElement");

					if (typeElement.equals("Titre musical")) {
						// rï¿½cup param vue
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
						//rï¿½cup param vue
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
						//rï¿½cup param vue
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
					// rï¿½cup param vue
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
					//rï¿½cup param vue
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
					// rï¿½cup param vue
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
					//rï¿½cup param vue
					String interpreteSelectBox = request.getParameter("interpreteSelectBox");
					TitreMusical titre = (TitreMusical) request.getSession().getAttribute("titre");

					int id = Integer.parseInt(interpreteSelectBox);

					Interprete interprete = null;
					//todo ajouter mï¿½thode rechercherInterpreteParId
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
					//rï¿½cup param vue
					String titresMusicauxSelectBox = request.getParameter("titresMusicauxSelectBox");
					Album album = (Album) request.getSession().getAttribute("album");

					int id = Integer.parseInt(titresMusicauxSelectBox);

					TitreMusical titreMusical = null;
					//todo ajouter mï¿½thode rechercherInterpreteParId
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
					//affectation vue
					vue = "/JSP/Administrateur/AjoutCatalogue.jsp"; 
				}else if(action.equals("FinAjoutTitresMusicauxAAlbum")) {
					//affectation vue
					vue = "/JSP/Administrateur/AjoutCatalogue.jsp"; 
				}else if(action.equals("ModificationProfilAdministrateur")){
					vue = "/JSP/Administrateur/ModificationProfilAdministrateur.jsp";
				}else if(action.equals("ModificationInterprete")) {
					//r�cup param vue
					String idString = request.getParameter("idString");

					//cast int
					int id=Integer.parseInt(idString);

					//envoi param vue
					request.setAttribute("id", id);

					//affectation vue
					vue = "/JSP/Administrateur/ModificationInterprete.jsp";
				}else if(action.equals("ModificationInterprete")) {
					//r�cup param vue
					String idString = request.getParameter("idString");

					//cast int
					int id=Integer.parseInt(idString);

					//envoi param vue
					request.setAttribute("id", id);

					//affectation vue
					vue = "/JSP/Administrateur/ModificationInterprete.jsp";
				}
			}
			// PARTIE GESTIONNAIRE CLIENT
			else if(administrateur instanceof ProfilGestionnaireClient) {
				request.setAttribute("isAdministrateurClient", true);
				request.setAttribute("isAdministrateurMusical", false);
				
				// Lecture seule
				if (action.equals("RechercheTitre")) {
					// rï¿½cup param vue
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
				}else if(action.equals("ModificationProfil")) {
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
					// rï¿½cup param vue
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
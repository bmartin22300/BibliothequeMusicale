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
		String servletPath = request.getServletPath();// r�cup�ration URL

		// affectation param�tres � la vue
		request.setAttribute("isClient", false);

		// section administrateur non connect�
		if (servletPath.equals("/AuthentificationAdministrateur")) {
			// affectation param�tres � la vue
			request.setAttribute("isAdministrateur", false);
			request.setAttribute("isErrorLogin", false);
			request.setAttribute("notLogged", false);

			// affection vue
			vue = "/JSP/Utilisateur/AuthentificationAdministrateur.jsp";
		} else if (servletPath.equals("/InscriptionAdministrateur")) {
			// affectation param�tres � la vue
			request.setAttribute("isAdministrateur", false);
			// affection vue
			vue = "/JSP/Utilisateur/InscriptionAdministrateur.jsp";
		}
		// section Administrateur
		else if (administrateur == null) {// verification que l'Administrateur est connect�
			// affectation param�tres � la vue
			request.setAttribute("isAdministrateur", false);
			request.setAttribute("isErrorLogin", false);
			request.setAttribute("notLogged", true);

			// affection vue
			vue = "/JSP/Utilisateur/AuthentificationAdministrateur.jsp";
		} else {
			// affectation param�tres � la vue
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
				vue = "/JSP/Administrateur/ModificationProfil.jsp";
			} else if (servletPath.equals("/ModificationCatalogue")) {
				vue = "/JSP/Administrateur/ModificationCatalogue.jsp";
			} else if (servletPath.equals("/ModificationTitre")) {
				// section Administrateur musical
				// todo v�rif identit�

				// r�cup�ration de param�tre de la vue
				String titre = request.getParameter("titre");

				// envoie de parametres a la vue
				request.setAttribute("titre", titre);

				vue = "/JSP/Administrateur/ModificationCatalogue.jsp";
			} else if (servletPath.equals("/AjoutCatalogue")) {
				// r�cup�ration de param�tre de la vue
				String TypeElement = request.getParameter("TypeElement");

				vue = "/JSP/Administrateur/AjoutCatalogue.jsp";
			} else if(servletPath.equals("/ModificationProfilAdministrateur")){
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

		// affectation param�tres � la vue
		request.setAttribute("isClient", false);

		// r�cup�ration des param�tres du form
		String mail = request.getParameter("mail");
		String motDePasse = request.getParameter("password");
		String typeAdmin = request.getParameter("typeAdmin");

		if (action.equals("ModificationProfilAdministrateur")) {
			// r�cup�ration param�tres
			request.setAttribute("password", administrateur.getPassword());
			request.setAttribute("email", administrateur.getMail());

			// affectation param�tres � la vue
			request.setAttribute("isAdministrateur", true);

			// mise � jour BDD
			Administrateur administrateurModifie = administrateur.modifierInformations(mail, motDePasse);
			if (administrateurModifie != null) {
				this.administrateur = administrateurModifie;
			}

			// choix de la vue
			vue = "/JSP/Administrateur/ProfilAdministrateur.jsp";
		} else {
			if (action.equals("AuthentificationAdministrateur")) {
				// requ�te � la BDD
				administrateur = utilisateur.authentificationAdmin(mail, motDePasse);

				if (administrateur != null) {
					// requeteBDD
					List<TitreMusical> titresMusicaux = administrateur.rechercherParNomTitre("");
					List<Interprete> interpretes = administrateur.rechercherParPseudoInterprete("");
					List<Album> albums = administrateur.rechercherParNomAlbum("");

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
						// affectation param�tres � la vue
					request.setAttribute("isAdministrateur", false);
					request.setAttribute("isErrorLogin", true);
					request.setAttribute("notLogged", false);

					// choix de la vue
					vue = "/JSP/Utilisateur/AuthentificationAdministrateur.jsp";
				}
			} else {
				// requeteBDD
				List<TitreMusical> titresMusicaux = administrateur.rechercherParNomTitre("");
				List<Interprete> interpretes = administrateur.rechercherParPseudoInterprete("");
				List<Album> albums = administrateur.rechercherParNomAlbum("");

				// envoie de parametres a la vue
				request.setAttribute("titresMusicaux", titresMusicaux);
				request.setAttribute("interpretes", interpretes);
				request.setAttribute("albums", albums);

				// partie administrateur musical connecte
				// affectation param�tres � la vue
				request.setAttribute("isAdministrateur", true);
				request.setAttribute("isAdministrateurMusical", true);

				// todo verifier connexion admin

				if (action.equals("RechercheTitre")) {
					// r�cup param vue
					String typeElement = request.getParameter("TypeElement");
					String titre = request.getParameter("titre");

					// envoi param vue
					request.setAttribute("titresMusicaux", titresMusicaux);
					request.setAttribute("interpretes", interpretes);
					request.setAttribute("albums", null);
					request.setAttribute("TypeElement", typeElement);
					request.setAttribute("isAdministrateur", true);
					request.setAttribute("isAdministrateurMusical", true);

					// attribution vue
					vue = "/JSP/Administrateur/ModificationCatalogue.jsp";
					/*
					 * if(typeElement.equals("Titres musicaux")){
					 * 
					 * }else { if(typeElement.equals("Interpretes")){
					 * 
					 * }else { if(typeElement.equals("Albums")){
					 * 
					 * } } }
					 */
				} else {
					if (action.equals("AjouterElement")) {
						// r�cup param vue
						String typeElement = request.getParameter("TypeElement");

						if (typeElement.equals("Titre musical")) {
							// r�cup param vue
							String Titre = request.getParameter("Titre");
							String GenreString = request.getParameter("Genre");
							System.out.println(GenreString);
							Genre styleMusique = Genre.valueOf(GenreString);
							System.out.println(styleMusique);
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

							// requete BDD
							TitreMusical interprete = ((ProfilGestionnaireMusical) administrateur).creerTitre(Titre,
									AnneeCreation, null, Duree, styleMusique);
							System.out.println(interprete);

							// attribution vue
							vue = "/JSP/Administrateur/AjoutCatalogue.jsp";
						} else {
							if (typeElement.equals("Interprete")) {
								// r�cup param vue
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
								System.out.println(interprete);

								// attribution vue
								vue = "/JSP/Administrateur/AjoutCatalogue.jsp";
							} else {
								if (typeElement.equals("Album")) {

								}
							}
						}
					} else {
						if (action.equals("SuppressionTitre")) {
							// r�cup param vue
							String titreString = request.getParameter("titreString");
							String idString = request.getParameter("idString");
							int id = Integer.parseInt(idString);
							List<TitreMusical> titres = administrateur.rechercherParNomTitre(titreString);
							TitreMusical titre = null;
							for (TitreMusical t : titres) {
								if (t.getIdCatalogue() == id) {
									titre = t;
								}
							}
							((ProfilGestionnaireMusical) administrateur).supprimerTitre(titre);
						} else {
							if (action.equals("SuppressionInterprete")) {
								// r�cup param vue
								String titreString = request.getParameter("titreString");
								String idString = request.getParameter("idString");
								int id = Integer.parseInt(idString);
								List<Interprete> titres = administrateur.rechercherParPseudoInterprete(titreString);
								Interprete titre = null;
								for (Interprete t : titres) {
									if (t.getId() == id) {
										titre = t;
									}
								}
								((ProfilGestionnaireMusical) administrateur).supprimerInterprete(titre);
							}
						}
					}
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
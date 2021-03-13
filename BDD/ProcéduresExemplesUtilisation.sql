USE info_team08_schema;

-- Création compte administrateur
SELECT nouveau_admin('adminclient@gmail.com', 'admin', true, false); 
SELECT nouveau_admin('adminmusique@gmail.com', 'admin', false, true); 
CALL existe_adminClient('adminclient@gmail.com');
CALL existe_adminClient('adminmusique@gmail.com');
CALL existe_adminMusique('adminclient@gmail.com');
CALL existe_adminMusique('adminmusique@gmail.com');

SELECT * FROM Administrateur;

-- Création compte client
SELECT nouveau_client_complet('client@gmail.com', 'client', 'Homme', 'clientNom', 'clientPrenom', '1999-01-23', '18 rue des cerisiers, 92120, Montrouge', 'Variete');
SELECT nouveau_client_complet('natalieriviera@free.fr', 'ordinateur', 'Femme', 'Riviera', 'Natalie', '1950-2-10', '2 place de la rouille, 22300, Lannion', 'Rock');
SELECT nouveau_client('jaimelavie@free.fr', 'maispastouslesjours');
CALL existe_client('jaimelavie@free.fr');

-- TODO : ajouter un supprimerClient pour pouvoir modifier le mail
CALL modifier_client(3, 'jaimelavie@free.fr', 'maispastouslesjours', 'Femme', 'Lavie', 'James', '1980-6-17', '5 rue du desespoir, 650, Nowhere', 'TECHNO');

CALL authentification_client('natalieriviera@free.fr', 'ordinateur');
SELECT * FROM Client;

-- Création Interprète
SELECT nouveau_interprete_complet('Edith Piaf', 'Edith', 'Giovanna Gassion', '1915-12-19');
SELECT nouveau_interprete('Noir Désir');
CALL modifier_interprete(2, 'Noir Désir', 'Groupe', 'Groupe', '1980-01-01');
SELECT nouveau_interprete_complet('Edith PiafeeTYPO', 'Edith', 'Giovanna Gassion', '1915-12-19');
CALL supprimer_interprete(3);

SELECT * FROM Interprete;
SELECT * FROM TitreMusical;

-- Création TitreMusical
SELECT nouveau_titre('La Vie en rose', '1946', 185, 'Variete');
-- SELECT retrouver_titre('La Vie en rose');

SELECT nouveau_titre('La Foule', '1957', 203, 'Variete');
SELECT retrouver_titre('La Foule');
CALL association_titre_interprete(2, 1);

SELECT nouveau_titre('Non, je ne regrette rien', '1960', 142, 'Variete');
SELECT retrouver_titre('Non, je ne regrette rien');
CALL association_titre_interprete(3, 1);

SELECT nouveau_titre("L'enfant roi", 2001, 363, 'Rock');CALL association_titre_interprete(4, 2);
SELECT nouveau_titre("Le grand incendie", 2001, 276, 'Rock');CALL association_titre_interprete(5, 2);
SELECT nouveau_titre("Le vent nous portera", 2001, 288, 'Rock');CALL association_titre_interprete(6, 2);
SELECT nouveau_titre("Des armes", 2001, 168, 'Rock');CALL association_titre_interprete(7, 2);
SELECT nouveau_titre("L'appartement", 2001, 251, 'Rock');CALL association_titre_interprete(8, 2);
SELECT nouveau_titre("Des visages des figures", 2001, 314, 'Rock');CALL association_titre_interprete(9, 2);
SELECT nouveau_titre("Son style 1", 2001, 126, 'Rock');CALL association_titre_interprete(10, 2);
SELECT nouveau_titre("Son style 2", 2001, 151, 'Rock');CALL association_titre_interprete(11, 2);
SELECT nouveau_titre("À l'envers à l'endroit", 2001, 247, 'Rock');CALL association_titre_interprete(12, 2);
SELECT nouveau_titre("Lost", 2001, 203, 'Rock');CALL association_titre_interprete(13, 2);
SELECT nouveau_titre("Bouquet de nerfs", 2001, 313, 'Rock');CALL association_titre_interprete(14, 2);
SELECT nouveau_titre("L'Europe", 2001, 1424, 'Rock');CALL association_titre_interprete(15, 2);

-- Création Album
SELECT nouveau_album('Des Visages des Figures','2001'); -- idAlbum = 16
CALL ajout_titre_album(4,16); -- Ajout de titre 4 à l'album 16
CALL ajout_titre_album(5,16);
CALL ajout_titre_album(6,16);
CALL ajout_titre_album(7,16);
CALL ajout_titre_album(8,16);
CALL ajout_titre_album(9,16);
CALL ajout_titre_album(10,16);
CALL ajout_titre_album(11,16);
CALL ajout_titre_album(12,16);
CALL ajout_titre_album(13,16);
CALL ajout_titre_album(14,16);
CALL ajout_titre_album(15,16);

SELECT nouveau_titre("Mauvais", '1901', 135, 'Variete');
CALL modifier_titre(17, "PasSiMauvais", '1960', 190, 'Rock');
-- CALL supprimer_titre(17);
CALL association_titre_interprete(17, 2);
CALL dissociation_titre_interprete(17, 2);
CALL modifier_album(16, 'Des Visages des ures','2101');
-- CALL supprimer_album(16);
CALL dissociation_titre_album(14, 16);

CALL reset_Vues_Catalogue();
SELECT * FROM ElementCatalogue;
SELECT * FROM TitreMusical;
SELECT * FROM Discographie;
SELECT * FROM Album;

-- On regarde des éléments du catalogue
CALL regarder(1, 2);CALL regarder(1, 2);
CALL regarder(2, 3);
CALL regarder(5, 3);CALL regarder(5, 3);CALL regarder(5, 3);CALL regarder(5, 3);
CALL regarder(10, 3);CALL regarder(10, 3);CALL regarder(10, 3);

-- On recommande un élément du catalogue
CALL recommander(6);

SELECT * FROM ElementCatalogue;
SELECT * FROM Interprete;
SELECT * FROM ElementCatalogue;
SELECT * FROM TitreMusical;
SELECT * FROM Discographie;


CALL rechercherParPseudoInterprete('i');
CALL rechercherParPrenomInterprete('i');
CALL rechercherParNomInterprete('i');

SELECT nouveau_titre("Mais au moins c'est des titres", 1983, 345, 'TECHNO');
Select @@global.event_scheduler;

CALL rechercherParIdInterpreteTitres(2);
CALL rechercherParIdCatalogueInterpretes(2);
CALL rechercherParTitreTitre("");

CALL getClient(1);
CALL getInterprete(1);
CALL getAlbum(16);
CALL getTitreMusical(1);

SELECT * FROM vue_recommandations;
SELECT * FROM ElementCatalogue;
SELECT * FROM Album;
SELECT * FROM TitreMusical;
SELECT * FROM vue_morceaux_populaires;

CALL regarder(3,2);

SELECT nouvelle_playlist(2, "Une playlist");
CALL association_playlist_elementsPlaylist(1, 1);
CALL association_playlist_elementsPlaylist(2, 1);
CALL modifier_playlist(1, "Cette Playlist");
-- CALL supprimer_playlist(2);
-- CALL dissociation_titre_playlist(1, 1);
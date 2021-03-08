USE info_team08_schema;

-- Création compte administrateur
CALL nouveau_admin('adminclient@gmail.com', 'admin', true, false); 
CALL nouveau_admin('adminmusique@gmail.com', 'admin', false, true); 

SELECT * FROM Administrateur;

-- Création compte client
CALL nouveau_client_complet('client@gmail.com', 'client', 'Homme', 'clientNom', 'clientPrenom', '1999-01-23', '18 rue des cerisiers, 92120, Montrouge', 'Variete');
CALL nouveau_client_complet('natalieriviera@free.fr', 'ordinateur', 'Femme', 'Riviera', 'Natalie', '1950-2-10', '2 place de la rouille, 22300, Lannion', 'Rock');
CALL nouveau_client('jaimelavie@free.fr', 'maispastouslesjours');

CALL modifier_client('jaimelavie@free.fr', 'maispastouslesjours', 'Femme', 'Lavie', 'James', '1980-6-17', '5 rue du desespoir, 650, Nowhere', 'Techno');

CALL authentification_client('natalieriviera@free.fr', 'ordinateur');
SELECT * FROM Client;

-- Création Interprète
CALL nouveau_interprete_complet('Edith Piaf', 'Edith', 'Giovanna Gassion', '1915-12-19');
CALL nouveau_interprete('Noir Désir');
CALL modifier_interprete('Noir Désir', 'Groupe', 'Groupe', '1980-01-01');
CALL nouveau_interprete_complet('Edith PiafeeTYPO', 'Edith', 'Giovanna Gassion', '1915-12-19');
-- CALL supprimer_interprete('Edith PiafeeTYPO');

SELECT * FROM Interprete;

-- Création TitreMusical
CALL nouveau_titre('La Vie en rose', '1946', 185, 'Variete');
SELECT retrouver_titre('La Vie en rose');
CALL association_titre_interprete(1, 'Edith Piaf');

CALL nouveau_titre('La Foule', '1957', 203, 'Variete');
SELECT retrouver_titre('La Foule');
CALL association_titre_interprete(2, 'Edith Piaf');

CALL nouveau_titre('Non, je ne regrette rien', '1960', 142, 'Variete');
SELECT retrouver_titre('Non, je ne regrette rien');
CALL association_titre_interprete(3, 'Edith Piaf');

CALL nouveau_titre("L'enfant roi", '2001', 363, 'Rock');CALL association_titre_interprete(4, 'Noir Désir');
CALL nouveau_titre("Le grand incendie", '2001', 276, 'Rock');CALL association_titre_interprete(5, 'Noir Désir');
CALL nouveau_titre("Le vent nous portera", '2001', 288, 'Rock');CALL association_titre_interprete(6, 'Noir Désir');
CALL nouveau_titre("Des armes", '2001', 168, 'Rock');CALL association_titre_interprete(7, 'Noir Désir');
CALL nouveau_titre("L'appartement", '2001', 251, 'Rock');CALL association_titre_interprete(8, 'Noir Désir');
CALL nouveau_titre("Des visages des figures", '2001', 314, 'Rock');CALL association_titre_interprete(9, 'Noir Désir');
CALL nouveau_titre("Son style 1", '2001', 126, 'Rock');CALL association_titre_interprete(10, 'Noir Désir');
CALL nouveau_titre("Son style 2", '2001', 151, 'Rock');CALL association_titre_interprete(11, 'Noir Désir');
CALL nouveau_titre("À l'envers à l'endroit", '2001', 247, 'Rock');CALL association_titre_interprete(12, 'Noir Désir');
CALL nouveau_titre("Lost", '2001', 203, 'Rock');CALL association_titre_interprete(13, 'Noir Désir');
CALL nouveau_titre("Bouquet de nerfs", '2001', 313, 'Rock');CALL association_titre_interprete(14, 'Noir Désir');
CALL nouveau_titre("L'Europe", '2001', 1424, 'Rock');CALL association_titre_interprete(15, 'Noir Désir');

-- Création Album
CALL nouveau_album('Des Visages des Figures','2001'); -- idAlbum = 16
CALL association_album_interprete(16, 'Noir Désir'); -- Remplissage table AlbumInterprete
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

CALL nouveau_titre("Mauvais", '1901', 135, 'Variete');
-- CALL modifier_titre(17, "PasSiMauvais", '1960', 190, 'Rock');
-- CALL supprimer_titre(17);
-- CALL association_titre_interprete(17, 'Noir Désir');
-- CALL dissociation_titre_interprete(17, 'Noir Désir');

CALL reset_Vues_Catalogue();
SELECT * FROM ElementCatalogue;
SELECT * FROM TitreMusical;
SELECT * FROM Discographie;

-- On regarde des éléments du catalogue
CALL regarder(1);CALL regarder(1);
CALL regarder(2);
CALL regarder(5);CALL regarder(5);CALL regarder(5);CALL regarder(5);
CALL regarder(10);CALL regarder(10);CALL regarder(10);

-- On recommande un élément du catalogue
CALL recommander(6);

SELECT * FROM ElementCatalogue;
SELECT * FROM Interprete;
SELECT * FROM AlbumInterprete;
SELECT * FROM ElementCatalogue;
SELECT * FROM TitreMusical;
SELECT * FROM Discographie;
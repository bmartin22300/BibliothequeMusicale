USE info_team08_schema;

CALL nouveau_admin('adminclient@gmail.com', 'admin', true, false); 
CALL nouveau_admin('adminmusique@gmail.com', 'admin', false, true); 

SELECT * FROM Administrateur;

CALL nouveau_client_complet('client@gmail.com', 'client', 'Homme', 'clientNom', 'clientPrenom', '1999-01-23', '18 rue des cerisiers, 92120, Montrouge', 'Variete');
CALL nouveau_client_complet('natalieriviera@free.fr', 'ordinateur', 'Femme', 'Riviera', 'Natalie', '1950-2-10', '2 place de la rouille, 22300, Lannion', 'Rock');
CALL nouveau_client('jaimelavie@free.fr', 'maispastouslesjours');

SELECT * FROM Client;

call nouveau_interprete('Edith Piaf', 'Edith', 'Giovanna Gassion', '1915-12-19');

SELECT * FROM Interprete;

call nouveau_titre('La Vie en rose', '1946', 'Variete');
CALL association_titre_interprete('La Vie en rose', 'Edith Piaf');

call nouveau_titre('La Foule', '1957', 'Variete');
CALL association_titre_interprete('La Foule', 'Edith Piaf');

call nouveau_titre('Non, je ne regrette rien', '1960', 'Variete');
CALL association_titre_interprete('Non, je ne regrette rien', 'Edith Piaf');

SELECT * FROM ElementCatalogue;
SELECT * FROM TitreMusical;
SELECT * FROM Discographie;
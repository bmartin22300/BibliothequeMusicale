-- MySQL Script generated by MySQL Workbench
-- Tue Mar  9 13:56:31 2021
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema info_team08_schema
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `info_team08_schema` ;

-- -----------------------------------------------------
-- Schema info_team08_schema
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `info_team08_schema` DEFAULT CHARACTER SET utf8 ;
USE `info_team08_schema` ;

-- -----------------------------------------------------
-- Table `info_team08_schema`.`Genre`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `info_team08_schema`.`Genre` ;

CREATE TABLE IF NOT EXISTS `info_team08_schema`.`Genre` (
  `nomGenre` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`nomGenre`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `info_team08_schema`.`Client`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `info_team08_schema`.`Client` ;

CREATE TABLE IF NOT EXISTS `info_team08_schema`.`Client` (
  `mail` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `civilite` VARCHAR(45) NULL,
  `nom` VARCHAR(45) NULL,
  `prenom` VARCHAR(45) NULL,
  `dateNaissance` DATE NULL,
  `adresseFacturation` VARCHAR(45) NULL,
  `nbEcoute` INT NULL,
  `nomGenre` VARCHAR(45) NULL,
  PRIMARY KEY (`mail`),
  INDEX `fk_Client_Genre1_idx` (`nomGenre` ASC),
  CONSTRAINT `fk_Client_Genre1`
    FOREIGN KEY (`nomGenre`)
    REFERENCES `info_team08_schema`.`Genre` (`nomGenre`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `info_team08_schema`.`ElementCatalogue`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `info_team08_schema`.`ElementCatalogue` ;

CREATE TABLE IF NOT EXISTS `info_team08_schema`.`ElementCatalogue` (
  `idCatalogue` INT NOT NULL AUTO_INCREMENT,
  `nbEcoute` INT NULL,
  `nbEcouteMois` INT NULL,
  `recommande` TINYINT NULL,
  PRIMARY KEY (`idCatalogue`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `info_team08_schema`.`Album`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `info_team08_schema`.`Album` ;

CREATE TABLE IF NOT EXISTS `info_team08_schema`.`Album` (
  `idCatalogue` INT NOT NULL,
  `nom` VARCHAR(45) NOT NULL,
  `dateSortie` INT NOT NULL,
  `duree` INT NULL,
  PRIMARY KEY (`idCatalogue`),
  CONSTRAINT `fk_Album_Catalogue1`
    FOREIGN KEY (`idCatalogue`)
    REFERENCES `info_team08_schema`.`ElementCatalogue` (`idCatalogue`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `info_team08_schema`.`TitreMusical`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `info_team08_schema`.`TitreMusical` ;

CREATE TABLE IF NOT EXISTS `info_team08_schema`.`TitreMusical` (
  `idCatalogue` INT NOT NULL AUTO_INCREMENT,
  `titre` VARCHAR(45) NOT NULL,
  `dateCreation` INT NULL,
  `duree` INT NULL,
  `nomGenre` VARCHAR(45) NULL,
  `Album_idCatalogue` INT NULL,
  PRIMARY KEY (`idCatalogue`),
  INDEX `fk_TitreMusical_Genre1_idx` (`nomGenre` ASC),
  INDEX `fk_TitreMusical_Album1_idx` (`Album_idCatalogue` ASC),
  CONSTRAINT `fk_TitreMusical_Catalogue1`
    FOREIGN KEY (`idCatalogue`)
    REFERENCES `info_team08_schema`.`ElementCatalogue` (`idCatalogue`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_TitreMusical_Genre1`
    FOREIGN KEY (`nomGenre`)
    REFERENCES `info_team08_schema`.`Genre` (`nomGenre`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_TitreMusical_Album1`
    FOREIGN KEY (`Album_idCatalogue`)
    REFERENCES `info_team08_schema`.`Album` (`idCatalogue`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `info_team08_schema`.`Podcast`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `info_team08_schema`.`Podcast` ;

CREATE TABLE IF NOT EXISTS `info_team08_schema`.`Podcast` (
  `idCatalogue` INT NOT NULL,
  `titre` VARCHAR(45) NOT NULL,
  `duree` INT NULL,
  `pseudoAuteur` VARCHAR(45) NULL,
  `categorie` VARCHAR(45) NULL,
  PRIMARY KEY (`idCatalogue`),
  CONSTRAINT `fk_Podcast_Catalogue1`
    FOREIGN KEY (`idCatalogue`)
    REFERENCES `info_team08_schema`.`ElementCatalogue` (`idCatalogue`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `info_team08_schema`.`Radio`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `info_team08_schema`.`Radio` ;

CREATE TABLE IF NOT EXISTS `info_team08_schema`.`Radio` (
  `idCatalogue` INT NOT NULL,
  `nom` VARCHAR(45) NOT NULL,
  `nomGenre` VARCHAR(45) NULL,
  PRIMARY KEY (`idCatalogue`),
  INDEX `fk_Radio_Genre1_idx` (`nomGenre` ASC),
  CONSTRAINT `fk_Radio_Catalogue1`
    FOREIGN KEY (`idCatalogue`)
    REFERENCES `info_team08_schema`.`ElementCatalogue` (`idCatalogue`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Radio_Genre1`
    FOREIGN KEY (`nomGenre`)
    REFERENCES `info_team08_schema`.`Genre` (`nomGenre`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `info_team08_schema`.`Interprete`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `info_team08_schema`.`Interprete` ;

CREATE TABLE IF NOT EXISTS `info_team08_schema`.`Interprete` (
  `pseudonyme` VARCHAR(45) NOT NULL,
  `prenom` VARCHAR(45) NULL,
  `nom` VARCHAR(45) NULL,
  `dateNaissance` DATE NULL,
  PRIMARY KEY (`pseudonyme`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `info_team08_schema`.`Administrateur`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `info_team08_schema`.`Administrateur` ;

CREATE TABLE IF NOT EXISTS `info_team08_schema`.`Administrateur` (
  `mail` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `profilGestionClient` TINYINT NULL,
  `profilGestionMusique` TINYINT NULL,
  PRIMARY KEY (`mail`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `info_team08_schema`.`Playlist`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `info_team08_schema`.`Playlist` ;

CREATE TABLE IF NOT EXISTS `info_team08_schema`.`Playlist` (
  `idPlaylist` INT NOT NULL AUTO_INCREMENT,
  `nomPlaylist` VARCHAR(45) NOT NULL,
  `mail` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idPlaylist`),
  INDEX `fk_Playlist_Client1_idx` (`mail` ASC),
  CONSTRAINT `fk_Playlist_Client1`
    FOREIGN KEY (`mail`)
    REFERENCES `info_team08_schema`.`Client` (`mail`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `info_team08_schema`.`ElementsPlaylist`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `info_team08_schema`.`ElementsPlaylist` ;

CREATE TABLE IF NOT EXISTS `info_team08_schema`.`ElementsPlaylist` (
  `Playlist_idPlaylist` INT NOT NULL,
  `Catalogue_idCatalogue` INT NOT NULL,
  PRIMARY KEY (`Playlist_idPlaylist`, `Catalogue_idCatalogue`),
  INDEX `fk_Playlist_has_Catalogue_Catalogue1_idx` (`Catalogue_idCatalogue` ASC),
  INDEX `fk_Playlist_has_Catalogue_Playlist1_idx` (`Playlist_idPlaylist` ASC),
  CONSTRAINT `fk_Playlist_has_Catalogue_Playlist1`
    FOREIGN KEY (`Playlist_idPlaylist`)
    REFERENCES `info_team08_schema`.`Playlist` (`idPlaylist`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Playlist_has_Catalogue_Catalogue1`
    FOREIGN KEY (`Catalogue_idCatalogue`)
    REFERENCES `info_team08_schema`.`ElementCatalogue` (`idCatalogue`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `info_team08_schema`.`Discographie`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `info_team08_schema`.`Discographie` ;

CREATE TABLE IF NOT EXISTS `info_team08_schema`.`Discographie` (
  `TitreMusical_Catalogue_idCatalogue` INT NOT NULL,
  `Interprete_pseudonyme` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`TitreMusical_Catalogue_idCatalogue`, `Interprete_pseudonyme`),
  INDEX `fk_Interprete_has_TitreMusical_TitreMusical1_idx` (`TitreMusical_Catalogue_idCatalogue` ASC),
  INDEX `fk_Discographie_Interprete1_idx` (`Interprete_pseudonyme` ASC),
  CONSTRAINT `fk_Interprete_has_TitreMusical_TitreMusical1`
    FOREIGN KEY (`TitreMusical_Catalogue_idCatalogue`)
    REFERENCES `info_team08_schema`.`TitreMusical` (`idCatalogue`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Discographie_Interprete1`
    FOREIGN KEY (`Interprete_pseudonyme`)
    REFERENCES `info_team08_schema`.`Interprete` (`pseudonyme`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `info_team08_schema`.`AlbumInterprete`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `info_team08_schema`.`AlbumInterprete` ;

CREATE TABLE IF NOT EXISTS `info_team08_schema`.`AlbumInterprete` (
  `Album_Catalogue_idCatalogue` INT NOT NULL,
  `Interprete_pseudonyme` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`Album_Catalogue_idCatalogue`, `Interprete_pseudonyme`),
  INDEX `fk_Album_has_Interprete_Album1_idx` (`Album_Catalogue_idCatalogue` ASC),
  INDEX `fk_AlbumInterprete_Interprete1_idx` (`Interprete_pseudonyme` ASC),
  CONSTRAINT `fk_Album_has_Interprete_Album1`
    FOREIGN KEY (`Album_Catalogue_idCatalogue`)
    REFERENCES `info_team08_schema`.`Album` (`idCatalogue`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_AlbumInterprete_Interprete1`
    FOREIGN KEY (`Interprete_pseudonyme`)
    REFERENCES `info_team08_schema`.`Interprete` (`pseudonyme`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;

USE `info_team08_schema` ;

-- -----------------------------------------------------
-- Placeholder table for view `info_team08_schema`.`vue_recommandations`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `info_team08_schema`.`vue_recommandations` (`titre` INT, `dateCreation` INT, `duree` INT, `nomGenre` INT, `recommande` INT);

-- -----------------------------------------------------
-- Placeholder table for view `info_team08_schema`.`vue_morceaux_populaires`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `info_team08_schema`.`vue_morceaux_populaires` (`titre` INT, `dateCreation` INT, `duree` INT, `nomGenre` INT, `nbEcoute` INT, `nbEcouteMois` INT);

-- -----------------------------------------------------
-- Placeholder table for view `info_team08_schema`.`vue_top_utilisateurs_ecoutes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `info_team08_schema`.`vue_top_utilisateurs_ecoutes` (`mail` INT, `nom` INT, `prenom` INT, `nbEcoute` INT);

-- -----------------------------------------------------
-- procedure nouveau_admin
-- -----------------------------------------------------

USE `info_team08_schema`;
DROP procedure IF EXISTS `info_team08_schema`.`nouveau_admin`;

DELIMITER $$
USE `info_team08_schema`$$
CREATE PROCEDURE `nouveau_admin` (mail VARCHAR(45), password VARCHAR(45), gestionClient BOOL, gestionMusique BOOL)
BEGIN
	INSERT INTO `info_team08_schema`.`Administrateur` (`mail`, `password`, `profilGestionClient`, `profilGestionMusique`) VALUES (mail, password, gestionClient, gestionMusique) ON DUPLICATE KEY UPDATE mail = mail;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure nouveau_client_complet
-- -----------------------------------------------------

USE `info_team08_schema`;
DROP procedure IF EXISTS `info_team08_schema`.`nouveau_client_complet`;

DELIMITER $$
USE `info_team08_schema`$$
CREATE PROCEDURE `nouveau_client_complet` (mail VARCHAR(45), password VARCHAR(45), civilite VARCHAR(45), nom VARCHAR(45), prenom VARCHAR(45), dateNaissance DATE, adresseFacturation VARCHAR(45), styleMusique VARCHAR(45))
BEGIN
	INSERT INTO `info_team08_schema`.`Client` (`mail`, `password`, `civilite`, `nom`, `prenom`, `dateNaissance`, `adresseFacturation`, `nomGenre`) 
				VALUES (mail, password, civilite, nom, prenom, dateNaissance, adresseFacturation, styleMusique) ON DUPLICATE KEY UPDATE mail = mail;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure nouveau_interprete_complet
-- -----------------------------------------------------

USE `info_team08_schema`;
DROP procedure IF EXISTS `info_team08_schema`.`nouveau_interprete_complet`;

DELIMITER $$
USE `info_team08_schema`$$
CREATE PROCEDURE `nouveau_interprete_complet` (pseudo_ VARCHAR(45), prenom_ VARCHAR(45), nom_ VARCHAR(45), dateNaissance_ VARCHAR(45))
BEGIN
	INSERT INTO `info_team08_schema`.`Interprete` (`pseudonyme`, `prenom`, `nom`, `dateNaissance`) VALUES (pseudo_, prenom_, nom_, dateNaissance_)
			ON DUPLICATE KEY UPDATE pseudonyme = pseudo_, prenom=prenom_, nom=nom_, dateNaissance=dateNaissance_;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure nouveau_client
-- -----------------------------------------------------

USE `info_team08_schema`;
DROP procedure IF EXISTS `info_team08_schema`.`nouveau_client`;

DELIMITER $$
USE `info_team08_schema`$$
CREATE PROCEDURE `nouveau_client` (mail VARCHAR(45), password VARCHAR(45))
BEGIN
	INSERT INTO `info_team08_schema`.`Client` (`mail`, `password`) 
			VALUES (mail, password) ON DUPLICATE KEY UPDATE mail=mail;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- function nouveau_titre
-- -----------------------------------------------------

USE `info_team08_schema`;
DROP function IF EXISTS `info_team08_schema`.`nouveau_titre`;

DELIMITER $$
USE `info_team08_schema`$$
CREATE FUNCTION `nouveau_titre` (titre_ VARCHAR(45), dateCreation_ INT, duree_ INT,  genre_ VARCHAR(45)) RETURNS INT
READS SQL DATA
DETERMINISTIC
BEGIN
	Declare idCatalogue_ INT;
    INSERT INTO `info_team08_schema`.`ElementCatalogue` (`nbEcoute`, `nbEcouteMois`, `recommande`) VALUES (0, 0, false); /* Creation du titre dans le catalogue */
    SELECT LAST_INSERT_ID() INTO idCatalogue_; /* On retrouve l'idCatalogue_ */
    INSERT INTO `info_team08_schema`.`TitreMusical` (`idCatalogue`, `titre`, `dateCreation`, `duree`, `nomGenre`) /* Creation du titre dans TitreMusical */
											VALUES (idCatalogue_, titre_, dateCreation_, duree_, genre_);
	RETURN idCatalogue_;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure association_titre_interprete
-- -----------------------------------------------------

USE `info_team08_schema`;
DROP procedure IF EXISTS `info_team08_schema`.`association_titre_interprete`;

DELIMITER $$
USE `info_team08_schema`$$
CREATE PROCEDURE `association_titre_interprete` (idCatalogue_ INT, pseudo_ VARCHAR(45))
BEGIN
	DECLARE idCatalogue_verifie_ INT;
    DECLARE pseudo_verifie_ VARCHAR(45);
    SELECT idCatalogue INTO idCatalogue_verifie_ FROM TitreMusical WHERE idCatalogue=idCatalogue_;
    SELECT pseudonyme INTO pseudo_verifie_ FROM Interprete WHERE pseudonyme=pseudo_;
	INSERT INTO `info_team08_schema`.`Discographie` (`TitreMusical_Catalogue_idCatalogue`, `Interprete_pseudonyme`) VALUES (idCatalogue_verifie_, pseudo_verifie_);
END$$

DELIMITER ;

-- -----------------------------------------------------
-- function retrouver_titre
-- -----------------------------------------------------

USE `info_team08_schema`;
DROP function IF EXISTS `info_team08_schema`.`retrouver_titre`;

DELIMITER $$
USE `info_team08_schema`$$
CREATE FUNCTION `retrouver_titre` (titre_ VARCHAR(45)) RETURNS INT
READS SQL DATA
DETERMINISTIC
BEGIN
	RETURN (SELECT idCatalogue FROM TitreMusical WHERE titre=titre_);
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure nouveau_album
-- -----------------------------------------------------

USE `info_team08_schema`;
DROP procedure IF EXISTS `info_team08_schema`.`nouveau_album`;

DELIMITER $$
USE `info_team08_schema`$$
CREATE PROCEDURE `nouveau_album` (nom_ VARCHAR(45), annee_sortie_ INT)
BEGIN
	DECLARE idCatalogue_ INT;
    INSERT INTO `info_team08_schema`.`ElementCatalogue` (`nbEcoute`, `nbEcouteMois`, `recommande`) VALUES (0, 0, false); /* Creation du titre dans le catalogue */
    SELECT LAST_INSERT_ID() INTO idCatalogue_; /* On retrouve l'idCatalogue_ */
    INSERT INTO `info_team08_schema`.`Album` (`idCatalogue`, `nom`, `dateSortie`, `duree`) VALUES (idCatalogue_, nom_, annee_sortie_, 0);
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure nouveau_interprete
-- -----------------------------------------------------

USE `info_team08_schema`;
DROP procedure IF EXISTS `info_team08_schema`.`nouveau_interprete`;

DELIMITER $$
USE `info_team08_schema`$$
CREATE PROCEDURE `nouveau_interprete` (pseudo_ VARCHAR(45))
BEGIN
	INSERT INTO `info_team08_schema`.`Interprete` (`pseudonyme`) VALUES (pseudo_) ON DUPLICATE KEY UPDATE pseudonyme = pseudo_;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure association_album_interprete
-- -----------------------------------------------------

USE `info_team08_schema`;
DROP procedure IF EXISTS `info_team08_schema`.`association_album_interprete`;

DELIMITER $$
USE `info_team08_schema`$$
CREATE PROCEDURE `association_album_interprete` (idCatalogue_ INT, pseudo_ VARCHAR(45))
BEGIN
	DECLARE idCatalogue_verifie_ INT;
    DECLARE pseudo_verifie_ VARCHAR(45);
    SELECT idCatalogue INTO idCatalogue_verifie_ FROM Album WHERE idCatalogue=idCatalogue_;
    SELECT pseudonyme INTO pseudo_verifie_ FROM Interprete WHERE pseudonyme=pseudo_;
	INSERT INTO `info_team08_schema`.`AlbumInterprete` (`Album_Catalogue_idCatalogue`, `Interprete_pseudonyme`) VALUES (idCatalogue_verifie_, pseudo_verifie_);
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure ajout_titre_album
-- -----------------------------------------------------

USE `info_team08_schema`;
DROP procedure IF EXISTS `info_team08_schema`.`ajout_titre_album`;

DELIMITER $$
USE `info_team08_schema`$$
CREATE PROCEDURE `ajout_titre_album` (idCatalogueTitre_ INT, idCatalogueAlbum_ INT)
BEGIN
	-- Extraction des durees
	DECLARE dureeTitre INT;
    DECLARE dureeAlbum INT;    
    SELECT duree INTO dureeTitre FROM TitreMusical WHERE idCatalogue=idCatalogueTitre_;
    SELECT duree INTO dureeAlbum FROM Album WHERE idCatalogue=idCatalogueAlbum_;
    
	UPDATE TitreMusical SET Album_idCatalogue=idCatalogueAlbum_ WHERE idCatalogue=idCatalogueTitre_;
    
    UPDATE Album SET duree=(dureeAlbum+dureeTitre) WHERE idCatalogue=idCatalogueAlbum_;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure reset_Vues_Catalogue
-- -----------------------------------------------------

USE `info_team08_schema`;
DROP procedure IF EXISTS `info_team08_schema`.`reset_Vues_Catalogue`;

DELIMITER $$
USE `info_team08_schema`$$
CREATE PROCEDURE `reset_Vues_Catalogue` ()
BEGIN
	UPDATE ElementCatalogue SET nbEcouteMois=0 WHERE idCatalogue != 0;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure regarder
-- -----------------------------------------------------

USE `info_team08_schema`;
DROP procedure IF EXISTS `info_team08_schema`.`regarder`;

DELIMITER $$
USE `info_team08_schema`$$
CREATE PROCEDURE `regarder` (idCatalogue_ INT)
BEGIN
	UPDATE ElementCatalogue SET nbEcoute = nbEcoute+1, nbEcouteMois = nbEcouteMois+1 WHERE idCatalogue = idCatalogue_;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure recommander
-- -----------------------------------------------------

USE `info_team08_schema`;
DROP procedure IF EXISTS `info_team08_schema`.`recommander`;

DELIMITER $$
USE `info_team08_schema`$$
CREATE PROCEDURE `recommander` (idCatalogue_ INT)
BEGIN
	UPDATE ElementCatalogue SET recommande = true WHERE idCatalogue = idCatalogue_;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure authentification_client
-- -----------------------------------------------------

USE `info_team08_schema`;
DROP procedure IF EXISTS `info_team08_schema`.`authentification_client`;

DELIMITER $$
USE `info_team08_schema`$$
CREATE PROCEDURE `authentification_client` (mail_ VARCHAR(45), password_ VARCHAR(45))
BEGIN
	SELECT * FROM Client WHERE mail=mail_ AND password=password_;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure authentification_adminClient
-- -----------------------------------------------------

USE `info_team08_schema`;
DROP procedure IF EXISTS `info_team08_schema`.`authentification_adminClient`;

DELIMITER $$
USE `info_team08_schema`$$
CREATE PROCEDURE `authentification_adminClient` (mail_ VARCHAR(45), password_ VARCHAR(45))
BEGIN
	SELECT * FROM Administrateur WHERE mail=mail_ AND password=password_ AND profilGestionClient=true;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure authentification_adminMusique
-- -----------------------------------------------------

USE `info_team08_schema`;
DROP procedure IF EXISTS `info_team08_schema`.`authentification_adminMusique`;

DELIMITER $$
USE `info_team08_schema`$$
CREATE PROCEDURE `authentification_adminMusique` (mail_ VARCHAR(45), password_ VARCHAR(45))
BEGIN
	SELECT * FROM Administrateur WHERE mail=mail_ AND password=password_ AND profilGestionMusique=true;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure modifier_client
-- -----------------------------------------------------

USE `info_team08_schema`;
DROP procedure IF EXISTS `info_team08_schema`.`modifier_client`;

DELIMITER $$
USE `info_team08_schema`$$
CREATE PROCEDURE `modifier_client` (mail_ VARCHAR(45), password_ VARCHAR(45), civilite_ VARCHAR(45), nom_ VARCHAR(45), prenom_ VARCHAR(45), dateNaissance_ DATE, adresse_ VARCHAR(45), styleMusique_ VARCHAR(45))
BEGIN
	UPDATE Client 
    SET password=password, civilite=civilite_, nom=nom_, prenom=prenom_, dateNaissance=dateNaissance_, adresseFacturation=adresse_, nomGenre=styleMusique_
    WHERE mail=mail_;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure modifier_interprete
-- -----------------------------------------------------

USE `info_team08_schema`;
DROP procedure IF EXISTS `info_team08_schema`.`modifier_interprete`;

DELIMITER $$
USE `info_team08_schema`$$
CREATE PROCEDURE `modifier_interprete` (pseudonyme_ VARCHAR(45), prenom_ VARCHAR(45), nom_ VARCHAR(45), dateNaissance_ DATE)
BEGIN
	UPDATE Interprete 
    SET prenom=prenom_, nom=nom_, prenom=prenom_, dateNaissance=dateNaissance_
    WHERE pseudonyme=pseudonyme_;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure supprimer_interprete
-- -----------------------------------------------------

USE `info_team08_schema`;
DROP procedure IF EXISTS `info_team08_schema`.`supprimer_interprete`;

DELIMITER $$
USE `info_team08_schema`$$
CREATE PROCEDURE `supprimer_interprete` (pseudonyme_ VARCHAR(45))
BEGIN
	DELETE FROM Interprete 
	WHERE pseudonyme = pseudonyme_;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure modifier_titre
-- -----------------------------------------------------

USE `info_team08_schema`;
DROP procedure IF EXISTS `info_team08_schema`.`modifier_titre`;

DELIMITER $$
USE `info_team08_schema`$$
CREATE PROCEDURE `modifier_titre` (idCatalogue_ INT, titre_ VARCHAR(45), dateCreation_ INT, duree_ INT, nomGenre_ VARCHAR(45))
BEGIN
	UPDATE TitreMusical
    SET titre=titre_, dateCreation=dateCreation_, duree=duree_, nomGenre=nomGenre_
    WHERE idCatalogue=idCatalogue_;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure dissociation_titre_interprete
-- -----------------------------------------------------

USE `info_team08_schema`;
DROP procedure IF EXISTS `info_team08_schema`.`dissociation_titre_interprete`;

DELIMITER $$
USE `info_team08_schema`$$
CREATE PROCEDURE `dissociation_titre_interprete` (idCatalogue_ INT, pseudonyme_ VARCHAR(45))
BEGIN
	DELETE FROM Discographie 
	WHERE TitreMusical_Catalogue_idCatalogue = idCatalogue_
    AND Interprete_pseudonyme = pseudonyme_;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure supprimer_titre
-- -----------------------------------------------------

USE `info_team08_schema`;
DROP procedure IF EXISTS `info_team08_schema`.`supprimer_titre`;

DELIMITER $$
USE `info_team08_schema`$$
CREATE PROCEDURE `supprimer_titre` (idCatalogue_ INT)
BEGIN
	DELETE FROM ElementCatalogue 
	WHERE idCatalogue = idCatalogue_;
	DELETE FROM TitreMusical 
	WHERE idCatalogue = idCatalogue_;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure modifier_album
-- -----------------------------------------------------

USE `info_team08_schema`;
DROP procedure IF EXISTS `info_team08_schema`.`modifier_album`;

DELIMITER $$
USE `info_team08_schema`$$
CREATE PROCEDURE `modifier_album` (idCatalogue_ INT, nom_ VARCHAR(45), dateSortie_ INT)
BEGIN
	UPDATE Album
    SET nom=nom_, dateSortie=dateSortie_
    WHERE idCatalogue=idCatalogue_;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure supprimer_album
-- -----------------------------------------------------

USE `info_team08_schema`;
DROP procedure IF EXISTS `info_team08_schema`.`supprimer_album`;

DELIMITER $$
USE `info_team08_schema`$$
CREATE PROCEDURE `supprimer_album` (idCatalogue_ INT)
BEGIN
	UPDATE TitreMusical
    SET Album_idCatalogue = null
    WHERE Album_idCatalogue = idCatalogue_;
    
	DELETE FROM ElementCatalogue 
	WHERE idCatalogue = idCatalogue_;
    
	DELETE FROM Album 
	WHERE idCatalogue = idCatalogue_;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure dissociation_album_interprete
-- -----------------------------------------------------

USE `info_team08_schema`;
DROP procedure IF EXISTS `info_team08_schema`.`dissociation_album_interprete`;

DELIMITER $$
USE `info_team08_schema`$$
CREATE PROCEDURE `dissociation_album_interprete` (idCatalogue_ INT, pseudonyme_ VARCHAR(45))
BEGIN
	DELETE FROM AlbumInterprete 
	WHERE Album_Catalogue_idCatalogue = idCatalogue_
    AND Interprete_pseudonyme = pseudonyme_;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure dissociation_titre_album
-- -----------------------------------------------------

USE `info_team08_schema`;
DROP procedure IF EXISTS `info_team08_schema`.`dissociation_titre_album`;

DELIMITER $$
USE `info_team08_schema`$$
CREATE PROCEDURE `dissociation_titre_album` (idCatalogue_Titre_ INT, idCatalogue_Album_ INT)
BEGIN
	-- On retire l'album du titre
	DECLARE dureeTitre INT;
    DECLARE dureeAlbum INT;    
    SELECT duree INTO dureeTitre FROM TitreMusical WHERE idCatalogue=idCatalogue_Titre_;
    SELECT duree INTO dureeAlbum FROM Album WHERE idCatalogue=idCatalogue_Album_;
    
	UPDATE TitreMusical SET  Album_idCatalogue = null
	WHERE idCatalogue = idCatalogue_Titre_
    AND Album_idCatalogue = idCatalogue_Album_;
    
    UPDATE Album SET  duree = (dureeAlbum-dureeTitre) WHERE idCatalogue = idCatalogue_Album_;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure rechercherParPseudoInterprete
-- -----------------------------------------------------

USE `info_team08_schema`;
DROP procedure IF EXISTS `info_team08_schema`.`rechercherParPseudoInterprete`;

DELIMITER $$
USE `info_team08_schema`$$
CREATE PROCEDURE `rechercherParPseudoInterprete` (recherche_ VARCHAR(45))
BEGIN
	SELECT * FROM Interprete WHERE pseudonyme LIKE concat('%', recherche_, '%');
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure rechercherParPrenomInterprete
-- -----------------------------------------------------

USE `info_team08_schema`;
DROP procedure IF EXISTS `info_team08_schema`.`rechercherParPrenomInterprete`;

DELIMITER $$
USE `info_team08_schema`$$
CREATE PROCEDURE `rechercherParPrenomInterprete` (recherche_ VARCHAR(45))
BEGIN
	SELECT * FROM Interprete WHERE prenom LIKE concat('%', recherche_, '%');
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure rechercherParNomInterprete
-- -----------------------------------------------------

USE `info_team08_schema`;
DROP procedure IF EXISTS `info_team08_schema`.`rechercherParNomInterprete`;

DELIMITER $$
USE `info_team08_schema`$$
CREATE PROCEDURE `rechercherParNomInterprete` (recherche_ VARCHAR(45))
BEGIN
	SELECT * FROM Interprete WHERE nom LIKE concat('%', recherche_, '%');
END$$

DELIMITER ;

-- -----------------------------------------------------
-- View `info_team08_schema`.`vue_recommandations`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `info_team08_schema`.`vue_recommandations`;
DROP VIEW IF EXISTS `info_team08_schema`.`vue_recommandations` ;
USE `info_team08_schema`;
CREATE OR REPLACE VIEW `vue_recommandations` AS
SELECT titre, dateCreation, duree, nomGenre, recommande
FROM info_team08_schema.ElementCatalogue as EC, info_team08_schema.TitreMusical as TM
WHERE EC.recommande = 1;

-- -----------------------------------------------------
-- View `info_team08_schema`.`vue_morceaux_populaires`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `info_team08_schema`.`vue_morceaux_populaires`;
DROP VIEW IF EXISTS `info_team08_schema`.`vue_morceaux_populaires` ;
USE `info_team08_schema`;
CREATE OR REPLACE VIEW `vue_morceaux_populaires` AS
SELECT titre, dateCreation, duree, nomGenre, nbEcoute, nbEcouteMois
FROM info_team08_schema.ElementCatalogue as EC, info_team08_schema.TitreMusical as TM
WHERE EC.idCatalogue = TM.idCatalogue AND EC.nbEcoute !=0
ORDER BY nbEcouteMois DESC
LIMIT 10;

-- -----------------------------------------------------
-- View `info_team08_schema`.`vue_top_utilisateurs_ecoutes`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `info_team08_schema`.`vue_top_utilisateurs_ecoutes`;
DROP VIEW IF EXISTS `info_team08_schema`.`vue_top_utilisateurs_ecoutes` ;
USE `info_team08_schema`;
CREATE OR REPLACE VIEW `vue_top_utilisateurs_ecoutes` AS
SELECT mail, nom, prenom, nbEcoute
FROM info_team08_schema.Client as C
WHERE C.nbEcoute != 0
ORDER BY nbEcoute DESC
LIMIT 10;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `info_team08_schema`.`Genre`
-- -----------------------------------------------------
START TRANSACTION;
USE `info_team08_schema`;
INSERT INTO `info_team08_schema`.`Genre` (`nomGenre`) VALUES ('TECHNO');
INSERT INTO `info_team08_schema`.`Genre` (`nomGenre`) VALUES ('CLASSIQUE');
INSERT INTO `info_team08_schema`.`Genre` (`nomGenre`) VALUES ('VARIETE');
INSERT INTO `info_team08_schema`.`Genre` (`nomGenre`) VALUES ('ELECTRO');
INSERT INTO `info_team08_schema`.`Genre` (`nomGenre`) VALUES ('POP');
INSERT INTO `info_team08_schema`.`Genre` (`nomGenre`) VALUES ('JAZZ');
INSERT INTO `info_team08_schema`.`Genre` (`nomGenre`) VALUES ('ROCK');
INSERT INTO `info_team08_schema`.`Genre` (`nomGenre`) VALUES ('RAP');

COMMIT;


-- -----------------------------------------------------
-- Data for table `info_team08_schema`.`Administrateur`
-- -----------------------------------------------------
START TRANSACTION;
USE `info_team08_schema`;
INSERT INTO `info_team08_schema`.`Administrateur` (`mail`, `password`, `profilGestionClient`, `profilGestionMusique`) VALUES ('adminClient', 'adminClient', 1, 0);
INSERT INTO `info_team08_schema`.`Administrateur` (`mail`, `password`, `profilGestionClient`, `profilGestionMusique`) VALUES ('adminMusique', 'adminMusique', 0, 1);

COMMIT;


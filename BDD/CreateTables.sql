-- MySQL Script generated by MySQL Workbench
-- Sat Mar  6 19:50:47 2021
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
-- Table `info_team08_schema`.`Playlist`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `info_team08_schema`.`Playlist` ;

CREATE TABLE IF NOT EXISTS `info_team08_schema`.`Playlist` (
  `idPlaylist` INT NOT NULL AUTO_INCREMENT,
  `nomPlaylist` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idPlaylist`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `info_team08_schema`.`Genre`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `info_team08_schema`.`Genre` ;

CREATE TABLE IF NOT EXISTS `info_team08_schema`.`Genre` (
  `idGenre` INT NOT NULL,
  `nomGenre` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idGenre`))
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
  `idPlaylist` INT NULL,
  `nbEcoute` INT NULL,
  `idGenre` INT NULL,
  PRIMARY KEY (`mail`, `password`),
  INDEX `fk_Client_Playlist1_idx` (`idPlaylist` ASC),
  INDEX `fk_Client_Genre1_idx` (`idGenre` ASC),
  CONSTRAINT `fk_Client_Playlist1`
    FOREIGN KEY (`idPlaylist`)
    REFERENCES `info_team08_schema`.`Playlist` (`idPlaylist`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Client_Genre1`
    FOREIGN KEY (`idGenre`)
    REFERENCES `info_team08_schema`.`Genre` (`idGenre`)
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
  `dateSortie` YEAR NOT NULL,
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
  `dateCreation` YEAR NULL,
  `duree` INT NULL,
  `idGenre` INT NULL,
  `Album_idCatalogue` INT NULL,
  PRIMARY KEY (`idCatalogue`),
  INDEX `fk_TitreMusical_Genre1_idx` (`idGenre` ASC),
  INDEX `fk_TitreMusical_Album1_idx` (`Album_idCatalogue` ASC),
  CONSTRAINT `fk_TitreMusical_Catalogue1`
    FOREIGN KEY (`idCatalogue`)
    REFERENCES `info_team08_schema`.`ElementCatalogue` (`idCatalogue`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_TitreMusical_Genre1`
    FOREIGN KEY (`idGenre`)
    REFERENCES `info_team08_schema`.`Genre` (`idGenre`)
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
  `duree` INT NOT NULL,
  `pseudoAuteur` VARCHAR(45) NOT NULL,
  `categorie` VARCHAR(45) NOT NULL,
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
  `genre` INT NOT NULL,
  PRIMARY KEY (`idCatalogue`),
  INDEX `fk_Radio_Genre1_idx` (`genre` ASC),
  CONSTRAINT `fk_Radio_Catalogue1`
    FOREIGN KEY (`idCatalogue`)
    REFERENCES `info_team08_schema`.`ElementCatalogue` (`idCatalogue`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Radio_Genre1`
    FOREIGN KEY (`genre`)
    REFERENCES `info_team08_schema`.`Genre` (`idGenre`)
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
  PRIMARY KEY (`mail`, `password`))
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
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
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
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

USE `info_team08_schema` ;

-- -----------------------------------------------------
-- procedure nouveau_admin
-- -----------------------------------------------------

USE `info_team08_schema`;
DROP procedure IF EXISTS `info_team08_schema`.`nouveau_admin`;

DELIMITER $$
USE `info_team08_schema`$$
CREATE PROCEDURE `nouveau_admin` (mail VARCHAR(45), password VARCHAR(45), gestionClient BOOL, gestionMusique BOOL)
BEGIN
	INSERT INTO `info_team08_schema`.`Administrateur` (`mail`, `password`, `profilGestionClient`, `profilGestionMusique`) VALUES (mail, password, gestionClient, gestionMusique);
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
	DECLARE idGenre_ INT;
    SELECT idGenre INTO idGenre_ FROM Genre WHERE nomGenre LIKE concat('%',styleMusique,'%');
	INSERT INTO `info_team08_schema`.`Client` (`mail`, `password`, `civilite`, `nom`, `prenom`, `dateNaissance`, `adresseFacturation`, `idGenre`) 
										VALUES (mail, password, civilite, nom, prenom, dateNaissance, adresseFacturation, idGenre_);
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
	INSERT INTO `info_team08_schema`.`Interprete` (`pseudonyme`, `prenom`, `nom`, `dateNaissance`) VALUES (pseudo_, prenom_, nom_, dateNaissance_);
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
										VALUES (mail, password);
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure nouveau_titre
-- -----------------------------------------------------

USE `info_team08_schema`;
DROP procedure IF EXISTS `info_team08_schema`.`nouveau_titre`;

DELIMITER $$
USE `info_team08_schema`$$
CREATE PROCEDURE `nouveau_titre` (titre_ VARCHAR(45), dateCreation_ YEAR, genre VARCHAR(45))
BEGIN
	DECLARE idCatalogue_, idGenre_ INT;
    SELECT idGenre INTO idGenre_ FROM Genre WHERE nomGenre LIKE concat('%', genre,'%'); /* On retrouve l'idGenre_ */
	INSERT INTO `info_team08_schema`.`ElementCatalogue` () VALUES (); /* Creation du titre dans le catalogue */
    SELECT LAST_INSERT_ID() INTO idCatalogue_; /* On retrouve l'idCatalogue_ */
    INSERT INTO `info_team08_schema`.`TitreMusical` (`idCatalogue`, `titre`, `dateCreation`, `idGenre`) /* Creation du titre dans TitreMusical */
											VALUES (idCatalogue_, titre_, dateCreation_, idGenre_);
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
CREATE PROCEDURE `nouveau_album` (nom_ VARCHAR(45), annee_sortie_ YEAR)
BEGIN
	DECLARE idCatalogue_ INT;
	INSERT INTO `info_team08_schema`.`ElementCatalogue` () VALUES (); /* Creation du titre dans le catalogue */
    SELECT LAST_INSERT_ID() INTO idCatalogue_; /* On retrouve l'idCatalogue_ */
	INSERT INTO `info_team08_schema`.`Album` (`idCatalogue`, `nom`, `dateSortie`) VALUES (idCatalogue_, nom_, annee_sortie_);
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
	INSERT INTO `info_team08_schema`.`Interprete` (`pseudonyme`) VALUES (pseudo_);
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
	DECLARE idCatalogueTitre_verifie_ INT;
    DECLARE idCatalogueAlbum_verifie_ INT;
    SELECT idCatalogue INTO idCatalogueTitre_verifie_ FROM TitreMusical WHERE idCatalogue=idCatalogueTitre_;
    SELECT idCatalogue INTO idCatalogueAlbum_verifie_ FROM Album WHERE idCatalogue=idCatalogueAlbum_;
	UPDATE TitreMusical SET Album_idCatalogue=idCatalogueAlbum_verifie_ WHERE idCatalogue=idCatalogueTitre_verifie_;
END$$

DELIMITER ;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `info_team08_schema`.`Genre`
-- -----------------------------------------------------
START TRANSACTION;
USE `info_team08_schema`;
INSERT INTO `info_team08_schema`.`Genre` (`idGenre`, `nomGenre`) VALUES (1, 'House');
INSERT INTO `info_team08_schema`.`Genre` (`idGenre`, `nomGenre`) VALUES (2, 'Pop');
INSERT INTO `info_team08_schema`.`Genre` (`idGenre`, `nomGenre`) VALUES (3, 'Metal');
INSERT INTO `info_team08_schema`.`Genre` (`idGenre`, `nomGenre`) VALUES (4, 'Classique');
INSERT INTO `info_team08_schema`.`Genre` (`idGenre`, `nomGenre`) VALUES (5, 'Jazz');
INSERT INTO `info_team08_schema`.`Genre` (`idGenre`, `nomGenre`) VALUES (6, 'Variete');
INSERT INTO `info_team08_schema`.`Genre` (`idGenre`, `nomGenre`) VALUES (7, 'Rap');
INSERT INTO `info_team08_schema`.`Genre` (`idGenre`, `nomGenre`) VALUES (8, 'Rock');

COMMIT;


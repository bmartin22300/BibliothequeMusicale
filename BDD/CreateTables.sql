-- MySQL Script generated by MySQL Workbench
-- Thu Mar  4 23:08:13 2021
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
-- Table `info_team08_schema`.`Client`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `info_team08_schema`.`Client` ;

CREATE TABLE IF NOT EXISTS `info_team08_schema`.`Client` (
  `mail` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `civilite` VARCHAR(45) NOT NULL,
  `nom` VARCHAR(45) NOT NULL,
  `prenom` VARCHAR(45) NOT NULL,
  `dateNaissance` DATE NOT NULL,
  `adresseFacturation` VARCHAR(45) NOT NULL,
  `styleMusiquePrefere` VARCHAR(45) NULL,
  `Playlist_idPlaylist` INT NOT NULL,
  PRIMARY KEY (`mail`, `password`, `Playlist_idPlaylist`),
  INDEX `fk_Client_Playlist1_idx` (`Playlist_idPlaylist` ASC) VISIBLE,
  CONSTRAINT `fk_Client_Playlist1`
    FOREIGN KEY (`Playlist_idPlaylist`)
    REFERENCES `info_team08_schema`.`Playlist` (`idPlaylist`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `info_team08_schema`.`Catalogue`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `info_team08_schema`.`Catalogue` ;

CREATE TABLE IF NOT EXISTS `info_team08_schema`.`Catalogue` (
  `idCatalogue` INT NOT NULL,
  PRIMARY KEY (`idCatalogue`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `info_team08_schema`.`TitreMusical`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `info_team08_schema`.`TitreMusical` ;

CREATE TABLE IF NOT EXISTS `info_team08_schema`.`TitreMusical` (
  `idTitreMusical` INT NOT NULL AUTO_INCREMENT,
  `titre` VARCHAR(45) NOT NULL,
  `idInterprete` INT NOT NULL,
  `dateCreation` DATE NOT NULL,
  `duree` INT NOT NULL,
  `genre` INT NOT NULL,
  `Catalogue_idCatalogue` INT NOT NULL,
  PRIMARY KEY (`idTitreMusical`, `Catalogue_idCatalogue`),
  INDEX `fk_TitreMusical_Catalogue1_idx` (`Catalogue_idCatalogue` ASC) VISIBLE,
  CONSTRAINT `fk_TitreMusical_Catalogue1`
    FOREIGN KEY (`Catalogue_idCatalogue`)
    REFERENCES `info_team08_schema`.`Catalogue` (`idCatalogue`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `info_team08_schema`.`Album`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `info_team08_schema`.`Album` ;

CREATE TABLE IF NOT EXISTS `info_team08_schema`.`Album` (
  `idAlbum` INT NOT NULL AUTO_INCREMENT,
  `nom` VARCHAR(45) NOT NULL,
  `idInterprete` INT NOT NULL,
  `dateSortie` DATE NOT NULL,
  `duree` INT NOT NULL,
  `TitreMusical_idTitreMusical` INT NOT NULL,
  `Catalogue_idCatalogue` INT NOT NULL,
  PRIMARY KEY (`idAlbum`, `Catalogue_idCatalogue`),
  INDEX `fk_Album_TitreMusical1_idx` (`TitreMusical_idTitreMusical` ASC) VISIBLE,
  INDEX `fk_Album_Catalogue1_idx` (`Catalogue_idCatalogue` ASC) VISIBLE,
  CONSTRAINT `fk_Album_TitreMusical1`
    FOREIGN KEY (`TitreMusical_idTitreMusical`)
    REFERENCES `info_team08_schema`.`TitreMusical` (`idTitreMusical`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Album_Catalogue1`
    FOREIGN KEY (`Catalogue_idCatalogue`)
    REFERENCES `info_team08_schema`.`Catalogue` (`idCatalogue`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `info_team08_schema`.`Podcast`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `info_team08_schema`.`Podcast` ;

CREATE TABLE IF NOT EXISTS `info_team08_schema`.`Podcast` (
  `idPodcast` INT NOT NULL AUTO_INCREMENT,
  `titre` VARCHAR(45) NOT NULL,
  `duree` INT NOT NULL,
  `auteur` INT NOT NULL,
  `categorie` INT NOT NULL,
  `Catalogue_idCatalogue` INT NOT NULL,
  PRIMARY KEY (`idPodcast`, `Catalogue_idCatalogue`),
  INDEX `fk_Podcast_Catalogue1_idx` (`Catalogue_idCatalogue` ASC) VISIBLE,
  CONSTRAINT `fk_Podcast_Catalogue1`
    FOREIGN KEY (`Catalogue_idCatalogue`)
    REFERENCES `info_team08_schema`.`Catalogue` (`idCatalogue`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `info_team08_schema`.`Radio`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `info_team08_schema`.`Radio` ;

CREATE TABLE IF NOT EXISTS `info_team08_schema`.`Radio` (
  `idRadio` INT NOT NULL AUTO_INCREMENT,
  `nom` VARCHAR(45) NOT NULL,
  `genre` INT NOT NULL,
  `Catalogue_idCatalogue` INT NOT NULL,
  PRIMARY KEY (`idRadio`, `Catalogue_idCatalogue`),
  INDEX `fk_Radio_Catalogue1_idx` (`Catalogue_idCatalogue` ASC) VISIBLE,
  CONSTRAINT `fk_Radio_Catalogue1`
    FOREIGN KEY (`Catalogue_idCatalogue`)
    REFERENCES `info_team08_schema`.`Catalogue` (`idCatalogue`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `info_team08_schema`.`Interprete`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `info_team08_schema`.`Interprete` ;

CREATE TABLE IF NOT EXISTS `info_team08_schema`.`Interprete` (
  `idInterprete` INT NOT NULL AUTO_INCREMENT,
  `pseudonyme` VARCHAR(45) NOT NULL,
  `prenom` VARCHAR(45) NULL,
  `nom` VARCHAR(45) NULL,
  `dateNaissance` DATE NULL,
  `Album_idAlbum` INT NOT NULL,
  PRIMARY KEY (`idInterprete`),
  INDEX `fk_Interprete_Album_idx` (`Album_idAlbum` ASC) VISIBLE,
  CONSTRAINT `fk_Interprete_Album`
    FOREIGN KEY (`Album_idAlbum`)
    REFERENCES `info_team08_schema`.`Album` (`idAlbum`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
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
  INDEX `fk_Playlist_has_Catalogue_Catalogue1_idx` (`Catalogue_idCatalogue` ASC) VISIBLE,
  INDEX `fk_Playlist_has_Catalogue_Playlist1_idx` (`Playlist_idPlaylist` ASC) VISIBLE,
  CONSTRAINT `fk_Playlist_has_Catalogue_Playlist1`
    FOREIGN KEY (`Playlist_idPlaylist`)
    REFERENCES `info_team08_schema`.`Playlist` (`idPlaylist`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Playlist_has_Catalogue_Catalogue1`
    FOREIGN KEY (`Catalogue_idCatalogue`)
    REFERENCES `info_team08_schema`.`Catalogue` (`idCatalogue`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

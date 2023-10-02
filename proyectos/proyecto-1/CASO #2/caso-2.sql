-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema ProyectoCaso2
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema ProyectoCaso2
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `ProyectoCaso2` DEFAULT CHARACTER SET utf8 ;
USE `ProyectoCaso2` ;

-- -----------------------------------------------------
-- Table `ProyectoCaso2`.`Persona`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ProyectoCaso2`.`Persona` (
  `cedula` VARCHAR(45) NOT NULL,
  `tipo` VARCHAR(45) NULL,
  `nombre` VARCHAR(45) NULL,
  `fechaNacimiento` DATE NULL,
  PRIMARY KEY (`cedula`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ProyectoCaso2`.`Cuenta`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ProyectoCaso2`.`Cuenta` (
  `numero` INT NOT NULL AUTO_INCREMENT,
  `monto` DECIMAL NULL,
  `fechaVenc` DATE NULL,
  `CedulaPersona` VARCHAR(45) NULL,
  PRIMARY KEY (`numero`),
  INDEX `IDPersona_idx` (`CedulaPersona` ASC) VISIBLE,
  CONSTRAINT `IDPersona`
    FOREIGN KEY (`CedulaPersona`)
    REFERENCES `ProyectoCaso2`.`Persona` (`cedula`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ProyectoCaso2`.`Abono`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ProyectoCaso2`.`Abono` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `cuota` INT NULL,
  `fecha` DATE NULL,
  `monto` DECIMAL NULL,
  `IDCuenta` INT NULL,
  PRIMARY KEY (`ID`),
  INDEX `numCuenta_idx` (`IDCuenta` ASC) VISIBLE,
  CONSTRAINT `numCuenta`
    FOREIGN KEY (`IDCuenta`)
    REFERENCES `ProyectoCaso2`.`Cuenta` (`numero`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ProyectoCaso2`.`FormaPago`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ProyectoCaso2`.`FormaPago` (
  `codigo` INT NOT NULL AUTO_INCREMENT,
  `Nombre` VARCHAR(45) NULL,
  `IDAbono` INT NULL,
  PRIMARY KEY (`codigo`),
  INDEX `IDAbono_idx` (`IDAbono` ASC) VISIBLE,
  CONSTRAINT `IDAbono`
    FOREIGN KEY (`IDAbono`)
    REFERENCES `ProyectoCaso2`.`Abono` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema Proyecto
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema Proyecto
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `Proyecto` DEFAULT CHARACTER SET utf8 ;
USE `Proyecto` ;

-- -----------------------------------------------------
-- Table `Proyecto`.`Sucursal`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Proyecto`.`Sucursal` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `numPatronal` VARCHAR(45) NULL,
  `nombre` VARCHAR(45) NULL,
  `direccion` VARCHAR(45) NULL,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Proyecto`.`Tienda`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Proyecto`.`Tienda` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `IDSucursal` INT NULL,
  `direccion` VARCHAR(45) NULL,
  `nombre` VARCHAR(45) NULL,
  PRIMARY KEY (`ID`),
  INDEX `IDSucursal_idx` (`IDSucursal` ASC) VISIBLE,
  CONSTRAINT `IDSucursal`
    FOREIGN KEY (`IDSucursal`)
    REFERENCES `Proyecto`.`Sucursal` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Proyecto`.`Articulo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Proyecto`.`Articulo` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NULL,
  `codigo` VARCHAR(45) NULL,
  `desc` VARCHAR(45) NULL,
  `tipo` VARCHAR(45) NULL,
  `stock` INT NULL,
  `precioVenta` DECIMAL NULL,
  `precioUnitario` DECIMAL NULL,
  `dateEndSell` DATE NULL,
  `IDTienda` INT NULL,
  PRIMARY KEY (`ID`),
  INDEX `IDTienda_idx` (`IDTienda` ASC) VISIBLE,
  CONSTRAINT `IDTienda`
    FOREIGN KEY (`IDTienda`)
    REFERENCES `Proyecto`.`Tienda` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Proyecto`.`Proveedor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Proyecto`.`Proveedor` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NULL,
  `cedula` VARCHAR(45) NULL,
  `tipo` VARCHAR(45) NULL,
  `telefono` VARCHAR(45) NULL,
  `direccion` VARCHAR(45) NULL,
  `contacto` VARCHAR(45) NULL,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Proyecto`.`Orden`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Proyecto`.`Orden` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `numOrden` INT NULL,
  `fechaOrden` DATE NULL,
  `usuarioComprador` VARCHAR(45) NULL,
  `usuarioAproboOrden` VARCHAR(45) NULL,
  `montoTotal` DECIMAL NULL,
  `IDTienda` INT NULL,
  `IDProveedor` INT NULL,
  PRIMARY KEY (`ID`),
  INDEX `IDTienda_idx` (`IDTienda` ASC) VISIBLE,
  INDEX `IDProveedor_idx` (`IDProveedor` ASC) VISIBLE,
  CONSTRAINT `IDTiendaa`
    FOREIGN KEY (`IDTienda`)
    REFERENCES `Proyecto`.`Tienda` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `IDProveedor`
    FOREIGN KEY (`IDProveedor`)
    REFERENCES `Proyecto`.`Proveedor` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Proyecto`.`Detalle`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Proyecto`.`Detalle` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `IDOrden` INT NULL,
  `IDArticulo` INT NULL,
  `cantidad` INT NULL,
  `precio` DECIMAL NULL,
  PRIMARY KEY (`ID`),
  INDEX `IDArticulo_idx` (`IDArticulo` ASC) VISIBLE,
  INDEX `IDOrden_idx` (`IDOrden` ASC) VISIBLE,
  CONSTRAINT `IDArticulo`
    FOREIGN KEY (`IDArticulo`)
    REFERENCES `Proyecto`.`Articulo` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `IDOrden`
    FOREIGN KEY (`IDOrden`)
    REFERENCES `Proyecto`.`Orden` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Proyecto`.`Empleado`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Proyecto`.`Empleado` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NULL,
  `cedula` VARCHAR(45) NULL,
  `visitaDoctor` DATE NULL,
  `IDSucursal` INT NULL,
  `IDTienda` INT NULL,
  PRIMARY KEY (`ID`),
  INDEX `IDSucursal_idx` (`IDSucursal` ASC) VISIBLE,
  INDEX `IDTienda_idx` (`IDTienda` ASC) VISIBLE,
  CONSTRAINT `IDSucursall`
    FOREIGN KEY (`IDSucursal`)
    REFERENCES `Proyecto`.`Sucursal` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `IDTiendaaa`
    FOREIGN KEY (`IDTienda`)
    REFERENCES `Proyecto`.`Tienda` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

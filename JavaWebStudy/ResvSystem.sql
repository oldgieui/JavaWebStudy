SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `ResvSystem` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `ResvSystem` ;

-- -----------------------------------------------------
-- Table `ResvSystem`.`USER`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ResvSystem`.`USER` ;

CREATE TABLE IF NOT EXISTS `ResvSystem`.`USER` (
  `USERID` VARCHAR(12) NOT NULL,
  `PASSWORD` VARCHAR(50) NOT NULL,
  `USERNAME` VARCHAR(45) NOT NULL,
  `PHONE` VARCHAR(11) NOT NULL,
  `USERTYPE` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`USERID`),
  UNIQUE INDEX `USERID_UNIQUE` (`USERID` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ResvSystem`.`PLACE`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ResvSystem`.`PLACE` ;

CREATE TABLE IF NOT EXISTS `ResvSystem`.`PLACE` (
  `PLACEID` INT NOT NULL AUTO_INCREMENT,
  `PLACENAME` VARCHAR(12) NOT NULL,
  `PLACETYPE` VARCHAR(7) NOT NULL,
  PRIMARY KEY (`PLACEID`),
  UNIQUE INDEX `PLACEID_UNIQUE` (`PLACEID` ASC),
  UNIQUE INDEX `PLACENAME_UNIQUE` (`PLACENAME` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ResvSystem`.`RESERVATION`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ResvSystem`.`RESERVATION` ;

CREATE TABLE IF NOT EXISTS `ResvSystem`.`RESERVATION` (
  `RESVID` INT NOT NULL AUTO_INCREMENT,
  `USER_USERID` VARCHAR(12) NOT NULL,
  `PLACE_PLACEID` INT NOT NULL,
  `PURPOSE` TEXT NOT NULL,
  `DATE` DATE NOT NULL,
  `STARTTIME` TIME NOT NULL,
  `ENDTIME` TIME NOT NULL,
  `SUBMITTIME` TIMESTAMP NOT NULL,
  PRIMARY KEY (`RESVID`, `USER_USERID`, `PLACE_PLACEID`),
  INDEX `fk_RESERVATION_USER1_idx` (`USER_USERID` ASC),
  INDEX `fk_RESERVATION_PLACE1_idx` (`PLACE_PLACEID` ASC),
  CONSTRAINT `fk_RESERVATION_USER1`
    FOREIGN KEY (`USER_USERID`)
    REFERENCES `ResvSystem`.`USER` (`USERID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_RESERVATION_PLACE1`
    FOREIGN KEY (`PLACE_PLACEID`)
    REFERENCES `ResvSystem`.`PLACE` (`PLACEID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ResvSystem`.`SCHEDULE`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ResvSystem`.`SCHEDULE` ;

CREATE TABLE IF NOT EXISTS `ResvSystem`.`SCHEDULE` (
  `SID` INT NOT NULL AUTO_INCREMENT,
  `SNAME` VARCHAR(45) NOT NULL,
  `USER_USERID` VARCHAR(12) NOT NULL,
  `PLACE_PLACEID` INT NOT NULL,
  `DAY` VARCHAR(3) NOT NULL,
  `STARTDATE` DATE NOT NULL,
  `ENDDATE` DATE NOT NULL,
  `STARTTIME` TIME NOT NULL,
  `ENDTIME` TIME NOT NULL,
  PRIMARY KEY (`SID`, `USER_USERID`, `PLACE_PLACEID`),
  INDEX `fk_SCHEDULE_USER1_idx` (`USER_USERID` ASC),
  INDEX `fk_SCHEDULE_PLACE1_idx` (`PLACE_PLACEID` ASC),
  CONSTRAINT `fk_SCHEDULE_USER1`
    FOREIGN KEY (`USER_USERID`)
    REFERENCES `ResvSystem`.`USER` (`USERID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_SCHEDULE_PLACE1`
    FOREIGN KEY (`PLACE_PLACEID`)
    REFERENCES `ResvSystem`.`PLACE` (`PLACEID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ResvSystem`.`HISTORY`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ResvSystem`.`HISTORY` ;

CREATE TABLE IF NOT EXISTS `ResvSystem`.`HISTORY` (
  `RESVID` INT NOT NULL AUTO_INCREMENT,
  `USER_USERID` VARCHAR(12) NOT NULL,
  `PLACE_PLACEID` INT NOT NULL,
  `PURPOSE` TEXT NOT NULL,
  `DATE` DATE NOT NULL,
  `STARTTIME` TIME NOT NULL,
  `ENDTIME` TIME NOT NULL,
  `SUBMITTIME` TIMESTAMP NOT NULL,
  PRIMARY KEY (`RESVID`, `USER_USERID`, `PLACE_PLACEID`),
  INDEX `fk_HISTORY_USER1_idx` (`USER_USERID` ASC),
  INDEX `fk_HISTORY_PLACE1_idx` (`PLACE_PLACEID` ASC),
  CONSTRAINT `fk_HISTORY_USER1`
    FOREIGN KEY (`USER_USERID`)
    REFERENCES `ResvSystem`.`USER` (`USERID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_HISTORY_PLACE1`
    FOREIGN KEY (`PLACE_PLACEID`)
    REFERENCES `ResvSystem`.`PLACE` (`PLACEID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ResvSystem`.`STATISTICS`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ResvSystem`.`STATISTICS` ;

CREATE TABLE IF NOT EXISTS `ResvSystem`.`STATISTICS` (
  `PID` INT NOT NULL,
  `PLACE_PLACEID` INT NOT NULL,
  `USER_USERID` VARCHAR(12) NOT NULL,
  `DAY` VARCHAR(3) NOT NULL,
  `STARTTIME` TIME NOT NULL,
  `TOTALUSED` TIME NOT NULL,
  INDEX `fk_STATISTICS_HISTORY2_idx` (`STARTTIME` ASC),
  PRIMARY KEY (`PLACE_PLACEID`, `USER_USERID`),
  INDEX `fk_STATISTICS_USER1_idx` (`USER_USERID` ASC),
  CONSTRAINT `fk_STATISTICS_HISTORY2`
    FOREIGN KEY (`STARTTIME`)
    REFERENCES `ResvSystem`.`HISTORY` (`STARTTIME`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_STATISTICS_PLACE1`
    FOREIGN KEY (`PLACE_PLACEID`)
    REFERENCES `ResvSystem`.`PLACE` (`PLACEID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_STATISTICS_USER1`
    FOREIGN KEY (`USER_USERID`)
    REFERENCES `ResvSystem`.`USER` (`USERID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ResvSystem`.`WEBBOARD`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ResvSystem`.`WEBBOARD` ;

CREATE TABLE IF NOT EXISTS `ResvSystem`.`WEBBOARD` (
  `ARTICLEID` INT NOT NULL AUTO_INCREMENT,
  `USER_USERID` VARCHAR(12) NOT NULL,
  `CONTENT` BLOB NOT NULL,
  PRIMARY KEY (`ARTICLEID`, `USER_USERID`),
  INDEX `fk_WEBBOARD_USER1_idx` (`USER_USERID` ASC),
  CONSTRAINT `fk_WEBBOARD_USER1`
    FOREIGN KEY (`USER_USERID`)
    REFERENCES `ResvSystem`.`USER` (`USERID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
-- ------------------------
-- CREATE DATABASE 
-- ------------------------

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `RESVSYSTEM` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `RESVSYSTEM` ;

-- -----------------------------------------------------
-- Table `RESVSYSTEM`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `RESVSYSTEM`.`user` ;

CREATE TABLE IF NOT EXISTS `RESVSYSTEM`.`user` (
  `USERID` VARCHAR(12) NOT NULL,
  `PASSWORD` VARCHAR(50) NOT NULL,
  `USERNAME` VARCHAR(45) NOT NULL,
  `EMAIL` VARCHAR(45) NOT NULL,
  `PHONE` VARCHAR(17) NOT NULL,
  `USERTYPE` VARCHAR(10) NOT NULL,
  UNIQUE INDEX `USERID_UNIQUE` (`USERID` ASC),
  UNIQUE INDEX `EMAIL_UNIQUE` (`EMAIL` ASC),
  PRIMARY KEY (`USERID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `RESVSYSTEM`.`place`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `RESVSYSTEM`.`place` ;

CREATE TABLE IF NOT EXISTS `RESVSYSTEM`.`place` (
  `PLACENAME` VARCHAR(12) NOT NULL,
  UNIQUE INDEX `PLACENAME_UNIQUE` (`PLACENAME` ASC),
  PRIMARY KEY (`PLACENAME`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `RESVSYSTEM`.`statistics`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `RESVSYSTEM`.`statistics` ;

CREATE TABLE IF NOT EXISTS `RESVSYSTEM`.`statistics` (
  `SID` INT NOT NULL AUTO_INCREMENT,
  `PLACENAME` VARCHAR(12) NOT NULL,
  `DAY` VARCHAR(3) NOT NULL,
  `STARTTIME` TIME NOT NULL,
  `TOTALUSED` TIME NOT NULL,
  `USERTYPE` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`SID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `RESVSYSTEM`.`history`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `RESVSYSTEM`.`history` ;

CREATE TABLE `history` (
  `historyid` int(10) unsigned NOT NULL,
  `USERID` varchar(12) NOT NULL,
  `PLACENAME` varchar(12) NOT NULL,
  `PURPOSE` varchar(100) NOT NULL,
  `DATE` date NOT NULL,
  `STARTTIME` time NOT NULL,
  `ENDTIME` time NOT NULL,
  `SUBMITTIME` datetime NOT NULL,
  KEY `Index_history_1` (`DATE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 |
+---------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
1 row in set (0.00 sec)

-- -----------------------------------------------------
-- Table `RESVSYSTEM`.`webboard`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `RESVSYSTEM`.`webboard` ;

CREATE TABLE IF NOT EXISTS `RESVSYSTEM`.`webboard` (
  `ARTICLEID` INT NOT NULL AUTO_INCREMENT,
  `USERID` VARCHAR(12) NOT NULL,
  `TITLE` VARCHAR(50) NOT NULL,
  `CONTENT` BLOB NOT NULL,
  `SUBMITTIME` DATETIME NOT NULL,
  `MODIFIEDTIME` TIMESTAMP NOT NULL,
  PRIMARY KEY (`ARTICLEID`),
  INDEX `fk_WEBBOARD_user1_idx` (`USERID` ASC),
  CONSTRAINT `fk_WEBBOARD_user1`
    FOREIGN KEY (`USERID`)
    REFERENCES `RESVSYSTEM`.`user` (`USERID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `RESVSYSTEM`.`reservation`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `RESVSYSTEM`.`reservation` ;

CREATE TABLE IF NOT EXISTS `RESVSYSTEM`.`reservation` (
  `RID` INT NOT NULL AUTO_INCREMENT,
  `USERID` VARCHAR(12) NOT NULL,
  `PLACENAME` VARCHAR(12) NOT NULL,
  `PURPOSE` VARCHAR(100) NOT NULL,
  `DATE` DATE NOT NULL,
  `STARTTIME` TIME NOT NULL,
  `ENDTIME` TIME NOT NULL,
  `SUBMITTIME` TIMESTAMP NOT NULL,
  PRIMARY KEY (`RID`),
  INDEX `fk_USER_has_PLACE_PLACE1_idx` (`PLACENAME` ASC),
  INDEX `fk_USER_has_PLACE_USER1_idx` (`USERID` ASC),
  CONSTRAINT `fk_RESERVATION_USERID`
    FOREIGN KEY (`USERID`)
    REFERENCES `RESVSYSTEM`.`user` (`USERID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_RESERVATION_PLACENAME`
    FOREIGN KEY (`PLACENAME`)
    REFERENCES `RESVSYSTEM`.`place` (`PLACENAME`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `RESVSYSTEM`.`schedule`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `RESVSYSTEM`.`schedule` ;

CREATE TABLE IF NOT EXISTS `RESVSYSTEM`.`schedule` (
  `SID` INT NOT NULL AUTO_INCREMENT,
  `USERID` VARCHAR(12) NOT NULL,
  `PLACENAME` VARCHAR(12) NOT NULL,
  `PURPOSE` VARCHAR(100) NOT NULL,
  `DAY` VARCHAR(3) NOT NULL,
  `STARTDATE` DATE NOT NULL,
  `ENDDATE` DATE NOT NULL,
  `STARTTIME` TIME NOT NULL,
  `ENDTIME` TIME NOT NULL,
  PRIMARY KEY (`SID`),
  INDEX `fk_user_has_place_place1_idx` (`PLACENAME` ASC),
  INDEX `fk_user_has_place_user1_idx` (`USERID` ASC),
  CONSTRAINT `fk_SCHEDULE_USERID`
    FOREIGN KEY (`USERID`)
    REFERENCES `RESVSYSTEM`.`user` (`USERID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_SCHEDULE_PLACENAME`
    FOREIGN KEY (`PLACENAME`)
    REFERENCES `RESVSYSTEM`.`place` (`PLACENAME`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- ---------------------
-- CREATE DATABASE END
-- ---------------------



-- ---------------------------------------------------------------------------------
-- WITHOUT ANY INDEX
explain SELECT RESV.*,U.USERNAME FROM RESERVATION RESV INNER JOIN USER U WHERE PLACENAME = "prompt2-4" AND U.USERID = RESV.USERID;
+----+-------------+-------+--------+----------------------------------------------------------+------------------------------+---------+------------------------+------+-----------------------+
| id | select_type | table | type   | possible_keys                                            | key                          | key_len | ref                    | rows | Extra                 |
+----+-------------+-------+--------+----------------------------------------------------------+------------------------------+---------+------------------------+------+-----------------------+
|  1 | SIMPLE      | RESV  | ref    | fk_USER_has_PLACE_PLACE1_idx,fk_USER_has_PLACE_USER1_idx | fk_USER_has_PLACE_PLACE1_idx | 38      | const                  |    1 | Using index condition |
|  1 | SIMPLE      | U     | eq_ref | PRIMARY,USERID_UNIQUE                                    | PRIMARY                      | 38      | resvsystem.RESV.USERID |    1 | NULL                  |
+----+-------------+-------+--------+----------------------------------------------------------+------------------------------+---------+------------------------+------+-----------------------+

EXPLAIN SELECT RESV.*,U.USERNAME FROM RESERVATION RESV INNER JOIN USER U WHERE RESV.USERID = U.USERID;
+----+-------------+-------+------+-----------------------------+-----------------------------+---------+---------------------+------+-------+
| id | select_type | table | type | possible_keys               | key                         | key_len | ref                 | rows | Extra |
+----+-------------+-------+------+-----------------------------+-----------------------------+---------+---------------------+------+-------+
|  1 | SIMPLE      | U     | ALL  | PRIMARY,USERID_UNIQUE       | NULL                        | NULL    | NULL                |    5 | NULL  |
|  1 | SIMPLE      | RESV  | ref  | fk_USER_has_PLACE_USER1_idx | fk_USER_has_PLACE_USER1_idx | 38      | resvsystem.U.USERID |    1 | NULL  |
+----+-------------+-------+------+-----------------------------+-----------------------------+---------+---------------------+------+-------+
-- ---------------------------------------------------------------------------------
CREATE INDEX IDX_USERNAME ON USER(USERNAME);
-- ---------------------------------------------------------------------------------

-- ---------------------------------------------------------------------------------
-- WITH INDEX ON USERNAME
explain SELECT RESV.*,U.USERNAME FROM RESERVATION RESV INNER JOIN USER U WHERE PLACENAME = "prompt2-4" AND U.USERID = RESV.USERID;
+----+-------------+-------+--------+----------------------------------------------------------+------------------------------+---------+------------------------+------+-----------------------+
| id | select_type | table | type   | possible_keys                                            | key                          | key_len | ref                    | rows | Extra                 |
+----+-------------+-------+--------+----------------------------------------------------------+------------------------------+---------+------------------------+------+-----------------------+
|  1 | SIMPLE      | RESV  | ref    | fk_USER_has_PLACE_PLACE1_idx,fk_USER_has_PLACE_USER1_idx | fk_USER_has_PLACE_PLACE1_idx | 38      | const                  |    1 | Using index condition |
|  1 | SIMPLE      | U     | eq_ref | PRIMARY,USERID_UNIQUE                                    | PRIMARY                      | 38      | resvsystem.RESV.USERID |    1 | NULL                  |
+----+-------------+-------+--------+----------------------------------------------------------+------------------------------+---------+------------------------+------+-----------------------+

EXPLAIN SELECT RESV.*,U.USERNAME FROM RESERVATION RESV INNER JOIN USER U WHERE RESV.USERID = U.USERID;
+----+-------------+-------+-------+-----------------------------+-----------------------------+---------+---------------------+------+-------------+
| id | select_type | table | type  | possible_keys               | key                         | key_len | ref                 | rows | Extra       |
+----+-------------+-------+-------+-----------------------------+-----------------------------+---------+---------------------+------+-------------+
|  1 | SIMPLE      | U     | index | PRIMARY,USERID_UNIQUE       | IDX_USERNAME                | 137     | NULL                |    5 | Using index |
|  1 | SIMPLE      | RESV  | ref   | fk_USER_has_PLACE_USER1_idx | fk_USER_has_PLACE_USER1_idx | 38      | resvsystem.U.USERID |    1 | NULL        |
+----+-------------+-------+-------+-----------------------------+-----------------------------+---------+---------------------+------+-------------+
-- ---------------------------------------------------------------------------------
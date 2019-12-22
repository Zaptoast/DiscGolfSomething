-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema dgdb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `dgdb` ;

-- -----------------------------------------------------
-- Schema dgdb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `dgdb` DEFAULT CHARACTER SET utf8 ;
USE `dgdb` ;

-- -----------------------------------------------------
-- Table `address`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `address` ;

CREATE TABLE IF NOT EXISTS `address` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `city` VARCHAR(100) NOT NULL,
  `state` VARCHAR(100) NOT NULL,
  `street` VARCHAR(400) NOT NULL,
  `zip` VARCHAR(5) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `course`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `course` ;

CREATE TABLE IF NOT EXISTS `course` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(400) NOT NULL,
  `length` INT NOT NULL,
  `description` TEXT NULL,
  `address_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_course_address1_idx` (`address_id` ASC),
  CONSTRAINT `fk_course_address1`
    FOREIGN KEY (`address_id`)
    REFERENCES `address` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user` ;

CREATE TABLE IF NOT EXISTS `user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `username` VARCHAR(45) NULL,
  `password` VARCHAR(45) NULL,
  `address_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_user_address1_idx` (`address_id` ASC),
  CONSTRAINT `fk_user_address1`
    FOREIGN KEY (`address_id`)
    REFERENCES `address` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `comment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `comment` ;

CREATE TABLE IF NOT EXISTS `comment` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `course_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  `content` TEXT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_comment_course_idx` (`course_id` ASC),
  INDEX `fk_comment_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_comment_course`
    FOREIGN KEY (`course_id`)
    REFERENCES `course` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_comment_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `rating`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rating` ;

CREATE TABLE IF NOT EXISTS `rating` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `course_id` INT NOT NULL,
  `value` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_rating_course1_idx` (`course_id` ASC),
  CONSTRAINT `fk_rating_course1`
    FOREIGN KEY (`course_id`)
    REFERENCES `course` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ammenity`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ammenity` ;

CREATE TABLE IF NOT EXISTS `ammenity` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(400) NOT NULL,
  `description` TEXT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `course_ammenities`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `course_ammenities` ;

CREATE TABLE IF NOT EXISTS `course_ammenities` (
  `course_id` INT NOT NULL,
  `ammenities_id` INT NOT NULL,
  PRIMARY KEY (`course_id`, `ammenities_id`),
  INDEX `fk_course_has_ammenities_ammenities1_idx` (`ammenities_id` ASC),
  INDEX `fk_course_has_ammenities_course1_idx` (`course_id` ASC),
  CONSTRAINT `fk_course_has_ammenities_course1`
    FOREIGN KEY (`course_id`)
    REFERENCES `course` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_course_has_ammenities_ammenities1`
    FOREIGN KEY (`ammenities_id`)
    REFERENCES `ammenity` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SET SQL_MODE = '';
DROP USER IF EXISTS dgdb@localhost;
SET SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
CREATE USER 'dgdb'@'localhost' IDENTIFIED BY 'dgdb';

GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE * TO 'dgdb'@'localhost';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `address`
-- -----------------------------------------------------
START TRANSACTION;
USE `dgdb`;
INSERT INTO `address` (`id`, `city`, `state`, `street`, `zip`) VALUES (1, 'Greenwood Village', 'Colorado', '9301 E. Union Ave.', '80111');
INSERT INTO `address` (`id`, `city`, `state`, `street`, `zip`) VALUES (2, 'Aurora', 'Colorado', '10955 E. Exposition Ave.', '80012');
INSERT INTO `address` (`id`, `city`, `state`, `street`, `zip`) VALUES (3, 'Aurora', 'Colorado', '21324 E Oxford Ave', '80013');
INSERT INTO `address` (`id`, `city`, `state`, `street`, `zip`) VALUES (4, 'Aurora', 'Colorado', '2801 Tower Rd.', '80011');
INSERT INTO `address` (`id`, `city`, `state`, `street`, `zip`) VALUES (5, 'Aurora', 'Colorado', '16300 E Centretech Pkwy.', '80011');

COMMIT;


-- -----------------------------------------------------
-- Data for table `course`
-- -----------------------------------------------------
START TRANSACTION;
USE `dgdb`;
INSERT INTO `course` (`id`, `name`, `length`, `description`, `address_id`) VALUES (1, 'Village Greens Park', 18, 'Set in a natural area of Village Greens Park. Mostly wide open with very few trees and some elevation change. Small creek runs through a few of the holes on the back nine.', 1);
INSERT INTO `course` (`id`, `name`, `length`, `description`, `address_id`) VALUES (2, 'Exposition Park', 18, 'Beautiful, flat park with lots of water hazards from 2 ponds and small stream. Nicely manicured grass and spacious cement tee pads. Walkways OB. Bug spray helpful in summer. Prone to flooding - keep in mind after rain and snowfall.', 2);
INSERT INTO `course` (`id`, `name`, `length`, `description`, `address_id`) VALUES (3, 'Tall Grass', 9, 'The course plays around the outside of the entire park area. If you play the sidewalks as strict OB the course is more challenging.', 3);
INSERT INTO `course` (`id`, `name`, `length`, `description`, `address_id`) VALUES (4, 'The Dock', 18, 'Nautical themed course on 10 acres built with vintage dock inspired tee boxes. Alt. tees can play as holes 10-18. Tight OBs and mandos. Temp. score cards available at hole #1.', 4);
INSERT INTO `course` (`id`, `name`, `length`, `description`, `address_id`) VALUES (5, 'Centre Hills Park', 18, 'The former par-3 Centre Hills Golf Course has been converted into a disc golf course by the City of Aurora. The course plays in a figure 8 shape down a long, narrow stretch of land with three ponds and a creek in the middle. The terrain is quite flat and mostly wide open, with the exception of three or four holes where trees come into play.', 5);

COMMIT;


-- -----------------------------------------------------
-- Data for table `user`
-- -----------------------------------------------------
START TRANSACTION;
USE `dgdb`;
INSERT INTO `user` (`id`, `name`, `username`, `password`, `address_id`) VALUES (1, 'Jake Kisor', 'jkisor', 'pass', 2);

COMMIT;


-- -----------------------------------------------------
-- Data for table `comment`
-- -----------------------------------------------------
START TRANSACTION;
USE `dgdb`;
INSERT INTO `comment` (`id`, `course_id`, `user_id`, `content`) VALUES (1, 1, 1, 'This course rules');

COMMIT;


-- -----------------------------------------------------
-- Data for table `rating`
-- -----------------------------------------------------
START TRANSACTION;
USE `dgdb`;
INSERT INTO `rating` (`id`, `course_id`, `value`) VALUES (1, 1, 9);
INSERT INTO `rating` (`id`, `course_id`, `value`) VALUES (2, 1, 8);
INSERT INTO `rating` (`id`, `course_id`, `value`) VALUES (3, 1, 7);
INSERT INTO `rating` (`id`, `course_id`, `value`) VALUES (4, 1, 8);
INSERT INTO `rating` (`id`, `course_id`, `value`) VALUES (5, 1, 7);
INSERT INTO `rating` (`id`, `course_id`, `value`) VALUES (6, 2, 5);
INSERT INTO `rating` (`id`, `course_id`, `value`) VALUES (7, 3, 6);
INSERT INTO `rating` (`id`, `course_id`, `value`) VALUES (8, 3, 8);
INSERT INTO `rating` (`id`, `course_id`, `value`) VALUES (9, 3, 8);
INSERT INTO `rating` (`id`, `course_id`, `value`) VALUES (10, 4, 7);
INSERT INTO `rating` (`id`, `course_id`, `value`) VALUES (11, 4, 6);
INSERT INTO `rating` (`id`, `course_id`, `value`) VALUES (12, 4, 7);
INSERT INTO `rating` (`id`, `course_id`, `value`) VALUES (13, 5, 4);
INSERT INTO `rating` (`id`, `course_id`, `value`) VALUES (14, 5, 6);
INSERT INTO `rating` (`id`, `course_id`, `value`) VALUES (15, 3, 8);
INSERT INTO `rating` (`id`, `course_id`, `value`) VALUES (16, 2, 4);
INSERT INTO `rating` (`id`, `course_id`, `value`) VALUES (17, 4, 9);

COMMIT;


-- -----------------------------------------------------
-- Data for table `ammenity`
-- -----------------------------------------------------
START TRANSACTION;
USE `dgdb`;
INSERT INTO `ammenity` (`id`, `name`, `description`) VALUES (1, 'Bathroom', 'Course has a bathroom with running water.');
INSERT INTO `ammenity` (`id`, `name`, `description`) VALUES (2, 'Port-a-Potty', 'Course  has Port-a-Pottys on site');
INSERT INTO `ammenity` (`id`, `name`, `description`) VALUES (3, 'Fountain', 'Course has a water fountain');
INSERT INTO `ammenity` (`id`, `name`, `description`) VALUES (4, 'Practice hole', 'Course has a warmup area with at least one practice hole');

COMMIT;


-- -----------------------------------------------------
-- Data for table `course_ammenities`
-- -----------------------------------------------------
START TRANSACTION;
USE `dgdb`;
INSERT INTO `course_ammenities` (`course_id`, `ammenities_id`) VALUES (1, 1);
INSERT INTO `course_ammenities` (`course_id`, `ammenities_id`) VALUES (1, 3);

COMMIT;


-- MySQL dump 10.13  Distrib 5.7.17, for macos10.12 (x86_64)
--
-- Host: localhost    Database: applikaasie
-- ------------------------------------------------------
-- Server version	5.7.19

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `adres`
--

DROP TABLE IF EXISTS `adres`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `adres` (
  `adres_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `klant_id` int(10) unsigned NOT NULL,
  `straatnaam` varchar(45) NOT NULL,
  `huisnummer` varchar(45) NOT NULL,
  `toevoegingsnummer` varchar(45) DEFAULT NULL,
  `postcode` varchar(45) NOT NULL,
  `woonplaats` varchar(45) NOT NULL,
  PRIMARY KEY (`adres_id`),
  KEY `klant_id_idx` (`klant_id`),
  CONSTRAINT `klant_id_2` FOREIGN KEY (`klant_id`) REFERENCES `klant` (`klant_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `adres`
--

LOCK TABLES `adres` WRITE;
/*!40000 ALTER TABLE `adres` DISABLE KEYS */;
INSERT INTO `adres` VALUES (13,13,'brinki','74','1','5690YU','Amsterdam'),(14,14,'brinki','74','1','5890YU','Amsterdam'),(15,15,'Osti','56','2','6789SD','Haarlem'),(16,16,'ergensstraat','6','8','3421FG','Tokio');
/*!40000 ALTER TABLE `adres` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bestelling_details`
--

DROP TABLE IF EXISTS `bestelling_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bestelling_details` (
  `bestelling_details_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `bestelling_id` int(10) unsigned NOT NULL,
  `kaas_id` int(10) unsigned NOT NULL,
  `hoeveelheid_in_kg` decimal(10,0) unsigned NOT NULL,
  `totaal_prijs` decimal(10,0) unsigned NOT NULL,
  PRIMARY KEY (`bestelling_details_id`),
  KEY `kaas_id_idx` (`kaas_id`),
  KEY `bestelling_id_idx` (`bestelling_id`),
  CONSTRAINT `bestelling_id` FOREIGN KEY (`bestelling_id`) REFERENCES `bestelling_totaal` (`bestelling_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `kaas_id` FOREIGN KEY (`kaas_id`) REFERENCES `kaas` (`kaas_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bestelling_details`
--

LOCK TABLES `bestelling_details` WRITE;
/*!40000 ALTER TABLE `bestelling_details` DISABLE KEYS */;
INSERT INTO `bestelling_details` VALUES (19,18,8,2,16),(20,18,10,3,30),(21,19,10,2,20),(22,20,10,6,60),(23,21,10,3,30),(24,21,11,5,30),(25,22,11,3,18),(26,22,8,6,48);
/*!40000 ALTER TABLE `bestelling_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bestelling_totaal`
--

DROP TABLE IF EXISTS `bestelling_totaal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bestelling_totaal` (
  `bestelling_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `klant_id` int(10) unsigned NOT NULL,
  `totaal_prijs` decimal(10,0) unsigned NOT NULL,
  `bestelling_datum` date NOT NULL,
  `status` varchar(45) NOT NULL,
  PRIMARY KEY (`bestelling_id`),
  KEY `klant_id_idx` (`klant_id`),
  CONSTRAINT `klant_id` FOREIGN KEY (`klant_id`) REFERENCES `klant` (`klant_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bestelling_totaal`
--

LOCK TABLES `bestelling_totaal` WRITE;
/*!40000 ALTER TABLE `bestelling_totaal` DISABLE KEYS */;
INSERT INTO `bestelling_totaal` VALUES (18,16,46,'2018-03-18','GESLOTEN'),(19,13,20,'2018-03-18','OPEN'),(20,14,60,'2018-03-18','OPEN'),(21,16,60,'2018-03-18','OPEN'),(22,13,66,'2018-03-18','OPEN');
/*!40000 ALTER TABLE `bestelling_totaal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kaas`
--

DROP TABLE IF EXISTS `kaas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `kaas` (
  `kaas_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `prijs_in_kg` double unsigned NOT NULL,
  `vooraad_in_kg` double unsigned NOT NULL,
  `kaas_naam` varchar(45) NOT NULL,
  PRIMARY KEY (`kaas_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kaas`
--

LOCK TABLES `kaas` WRITE;
/*!40000 ALTER TABLE `kaas` DISABLE KEYS */;
INSERT INTO `kaas` VALUES (8,8,50,'Jong Belegen'),(10,10,123,'Machengo'),(11,6,17,'Brie');
/*!40000 ALTER TABLE `kaas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `klant`
--

DROP TABLE IF EXISTS `klant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `klant` (
  `klant_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `voornaam` varchar(45) NOT NULL,
  `achternaam` varchar(45) NOT NULL,
  PRIMARY KEY (`klant_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `klant`
--

LOCK TABLES `klant` WRITE;
/*!40000 ALTER TABLE `klant` DISABLE KEYS */;
INSERT INTO `klant` VALUES (13,'Marko','Medjedovic'),(14,'Luli','Pessoa'),(15,'Ani','Cosca'),(16,'Keni','Uzi');
/*!40000 ALTER TABLE `klant` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-03-25 20:49:40

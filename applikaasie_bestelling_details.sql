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
  CONSTRAINT `bestelling_id` FOREIGN KEY (`bestelling_id`) REFERENCES `bestelling_totaal` (`bestelling_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `kaas_id` FOREIGN KEY (`kaas_id`) REFERENCES `kaas` (`kaas_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bestelling_details`
--

LOCK TABLES `bestelling_details` WRITE;
/*!40000 ALTER TABLE `bestelling_details` DISABLE KEYS */;
INSERT INTO `bestelling_details` VALUES (1,6,2,30,60),(2,6,1,50,125),(3,7,2,30,60),(4,7,1,50,125);
/*!40000 ALTER TABLE `bestelling_details` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-02-21 17:05:07

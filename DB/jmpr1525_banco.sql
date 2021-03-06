CREATE DATABASE  IF NOT EXISTS `jmpr1525_banco` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `jmpr1525_banco`;
-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: jmpr1525_banco
-- ------------------------------------------------------
-- Server version	5.7.14

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
-- Table structure for table `agencias`
--

DROP TABLE IF EXISTS `agencias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `agencias` (
  `agencia_id` int(11) NOT NULL,
  `nome` varchar(45) NOT NULL,
  `morada` varchar(255) NOT NULL,
  `telefone` varchar(45) NOT NULL,
  `ultimaconta` int(11) NOT NULL,
  PRIMARY KEY (`agencia_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `agencias`
--

LOCK TABLES `agencias` WRITE;
/*!40000 ALTER TABLE `agencias` DISABLE KEYS */;
INSERT INTO `agencias` VALUES (1,'GONDOMAR-CAMARA','Largo da Camara Nº 21, 4420-109 Gondomar','224810290',4),(2,'RIO TINTO-IGREJA','Praça da igreja, 321, 4435-271 Rio Tinto','224001145',2),(3,'PORTO-ALIADOS','Avenida dos aliados nº 45, 4000-440 Porto','223395517',1);
/*!40000 ALTER TABLE `agencias` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cartoes`
--

DROP TABLE IF EXISTS `cartoes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cartoes` (
  `cartao_id` int(11) NOT NULL AUTO_INCREMENT,
  `agencia_id` int(11) NOT NULL,
  `numero_conta` int(11) NOT NULL,
  `tipo` char(1) NOT NULL DEFAULT 'D',
  `data_criacao` date NOT NULL,
  `descricao` varchar(45) NOT NULL,
  `nome_no_cartao` varchar(45) NOT NULL,
  `data_validade` date NOT NULL,
  `plafond_mensal` double DEFAULT NULL,
  `plafond_disponivel` double DEFAULT NULL,
  `dias_prazo_pagamento` int(11) DEFAULT NULL,
  `dia_inicio_extrato` int(11) DEFAULT NULL,
  PRIMARY KEY (`cartao_id`),
  KEY `idx_contaagencia` (`agencia_id`,`numero_conta`),
  CONSTRAINT `fk_conta` FOREIGN KEY (`agencia_id`, `numero_conta`) REFERENCES `contas` (`agencia_id`, `numero_conta`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cartoes`
--

LOCK TABLES `cartoes` WRITE;
/*!40000 ALTER TABLE `cartoes` DISABLE KEYS */;
INSERT INTO `cartoes` VALUES (1,1,1,'D','2018-05-01','CARTÃO DÉBITO','FREDERICO PONTES','2020-05-31',NULL,NULL,NULL,NULL),(2,2,1,'D','2018-05-01','CARTÃO DÉBITO','FILOMENIA SILVA','2020-05-31',NULL,NULL,NULL,NULL),(3,2,2,'D','2018-05-01','CARTÃO DÉBITO','SARA TORRES','2020-05-31',NULL,NULL,NULL,NULL),(6,1,1,'C','2018-05-01','CARTÃO CRÉDITO','FREDERICO PONTES','2020-05-31',1500,1500,10,15);
/*!40000 ALTER TABLE `cartoes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clientes`
--

DROP TABLE IF EXISTS `clientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `clientes` (
  `agencia_id` int(11) NOT NULL,
  `numero_cliente` int(11) NOT NULL,
  `tipo` char(1) NOT NULL DEFAULT 'N',
  `nome` varchar(45) NOT NULL,
  `cartao_cidadao` varchar(45) NOT NULL,
  `morada` varchar(255) NOT NULL,
  `telefone` varchar(45) NOT NULL,
  `email` varchar(255) NOT NULL,
  `data_criacao` date NOT NULL,
  `data_nascimento` date DEFAULT NULL,
  `profissao` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`agencia_id`,`numero_cliente`),
  CONSTRAINT `fk_agenciacliente` FOREIGN KEY (`agencia_id`) REFERENCES `agencias` (`agencia_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clientes`
--

LOCK TABLES `clientes` WRITE;
/*!40000 ALTER TABLE `clientes` DISABLE KEYS */;
INSERT INTO `clientes` VALUES (1,1,'N','Frederico Manuel de Teixeira Pontes','10203388','Rua 1º de Maio nº 15, 4420-340 Fanzeres','224861101','fred2sapo.pt','2018-05-01','1996-10-23','Empresário'),(2,1,'N','Filomenia Amaro da Silva','11558877','Rua do Amparo nº 1209, 4435-510 Rio Tinto','224555888','filo@sapo.pt','2018-05-01','1994-03-10','Professora'),(2,2,'N','Sara Luisa Gomes Pereira Torres','21454400','Travessa do Marques nº 70, 4435-150 Rio Tinto','224111447','saraluisa@sapo.pt','2018-05-01','1986-05-30','EngªQuimica');
/*!40000 ALTER TABLE `clientes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contas`
--

DROP TABLE IF EXISTS `contas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contas` (
  `agencia_id` int(11) NOT NULL,
  `numero_conta` int(11) NOT NULL,
  `numero_cliente` int(11) NOT NULL,
  `tipo` varchar(45) NOT NULL,
  `descricao` varchar(45) NOT NULL,
  `data_criacao` date NOT NULL,
  `saldo` double NOT NULL,
  `taxa_remuneracao` double DEFAULT NULL,
  `periocidade_juros` int(11) DEFAULT NULL COMMENT 'em dias',
  `prazo_anos` int(11) DEFAULT NULL,
  `custo` double DEFAULT NULL,
  `ultimomovimento` int(11) NOT NULL,
  PRIMARY KEY (`agencia_id`,`numero_conta`),
  KEY `idx_cliente` (`agencia_id`,`numero_cliente`),
  KEY `idx_agencia` (`agencia_id`),
  CONSTRAINT `fk_agenciaconta` FOREIGN KEY (`agencia_id`) REFERENCES `agencias` (`agencia_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_clienteconta` FOREIGN KEY (`agencia_id`, `numero_cliente`) REFERENCES `clientes` (`agencia_id`, `numero_cliente`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contas`
--

LOCK TABLES `contas` WRITE;
/*!40000 ALTER TABLE `contas` DISABLE KEYS */;
INSERT INTO `contas` VALUES (1,1,1,'ORDEM','À Ordem Base','2018-05-01',240,NULL,NULL,NULL,NULL,5),(1,3,1,'APRAZO','Net3Anos','2018-05-01',1000,0.1,365,3,NULL,3),(1,4,1,'POUPANCA','Poupa+','2018-05-01',0,0.05,365,NULL,NULL,0),(2,1,1,'ORDEM','À Ordem Base','2018-05-01',3050,NULL,NULL,NULL,NULL,6),(2,2,2,'ORDEM','À Ordem Base','2018-05-01',0,NULL,NULL,NULL,NULL,0);
/*!40000 ALTER TABLE `contas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movimentos`
--

DROP TABLE IF EXISTS `movimentos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `movimentos` (
  `agencia_id` int(11) NOT NULL,
  `numero_conta` int(11) NOT NULL,
  `numero_movimento` int(11) NOT NULL,
  `cartao_id` int(11) DEFAULT NULL,
  `data_movimento` datetime NOT NULL,
  `tipo` char(1) NOT NULL,
  `tipo_descr` varchar(45) NOT NULL,
  `valor` double NOT NULL,
  `descricao` varchar(45) DEFAULT NULL,
  `ref_agencia_id` int(11) DEFAULT NULL,
  `ref_numero_conta` int(11) DEFAULT NULL,
  `ref_numero_movimento` int(11) DEFAULT NULL,
  PRIMARY KEY (`agencia_id`,`numero_conta`,`numero_movimento`),
  KEY `fk_cartao_idx` (`cartao_id`),
  KEY `fk_contaorigem_idx` (`agencia_id`,`numero_conta`),
  CONSTRAINT `fk_cartao` FOREIGN KEY (`cartao_id`) REFERENCES `cartoes` (`cartao_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_contaorigem` FOREIGN KEY (`agencia_id`, `numero_conta`) REFERENCES `contas` (`agencia_id`, `numero_conta`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movimentos`
--

LOCK TABLES `movimentos` WRITE;
/*!40000 ALTER TABLE `movimentos` DISABLE KEYS */;
INSERT INTO `movimentos` VALUES (1,1,1,1,'2018-05-01 18:35:22','C','DEP',1000,'S. Inicial',NULL,NULL,-1),(1,1,2,1,'2018-05-01 18:35:59','C','DEP',450,'Coisas,Lda',NULL,NULL,-1),(1,1,3,1,'2018-05-01 18:42:21','D','TRFOUT',1000,'ABERTURA',1,3,1),(1,1,4,1,'2018-05-01 18:59:33','D','TRFOUT',150,'Frederico',2,1,1),(1,1,5,1,'2018-05-01 19:11:11','D','LEV',60,'Portagens',NULL,NULL,-1),(1,3,1,1,'2018-05-01 18:42:21','C','TRFIN',1000,'ABERTURA',1,1,3),(2,1,1,NULL,'2018-05-01 18:59:33','C','TRFIN',150,'Frederico',1,1,4),(2,1,5,2,'2018-05-01 19:01:08','C','DEP',3000,'PrémioMês',NULL,NULL,-1),(2,1,6,2,'2018-05-01 19:02:54','D','LEV',100,'pagam. EDP',NULL,NULL,-1);
/*!40000 ALTER TABLE `movimentos` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-05-01 19:14:16

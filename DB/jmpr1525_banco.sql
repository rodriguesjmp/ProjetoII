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
  `conta_id` int(11) NOT NULL,
  `descricao` varchar(45) DEFAULT NULL,
  `tipo` char(1) NOT NULL DEFAULT 'D',
  `plafond_mensal` double DEFAULT NULL,
  `plafond_disponivel` double DEFAULT NULL,
  `data_criacao` date DEFAULT NULL,
  `data_limte_pagamento` date DEFAULT NULL,
  `periodo_extrato` date DEFAULT NULL,
  PRIMARY KEY (`cartao_id`),
  KEY `idx_contaagencia` (`agencia_id`,`conta_id`),
  CONSTRAINT `fk_conta` FOREIGN KEY (`agencia_id`, `conta_id`) REFERENCES `contas` (`agencia_id`, `conta_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cartoes`
--

LOCK TABLES `cartoes` WRITE;
/*!40000 ALTER TABLE `cartoes` DISABLE KEYS */;
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
  `cliente_id` int(11) NOT NULL,
  `tipo` char(1) NOT NULL DEFAULT 'N',
  `nome` varchar(45) NOT NULL,
  `cartao_cidadao` varchar(45) NOT NULL,
  `morada` varchar(255) NOT NULL,
  `telefone` varchar(45) NOT NULL,
  `email` varchar(255) NOT NULL,
  `profissao` varchar(45) NOT NULL,
  PRIMARY KEY (`agencia_id`,`cliente_id`),
  CONSTRAINT `fk_agenciacliente` FOREIGN KEY (`agencia_id`) REFERENCES `agencias` (`agencia_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clientes`
--

LOCK TABLES `clientes` WRITE;
/*!40000 ALTER TABLE `clientes` DISABLE KEYS */;
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
  `conta_id` int(11) NOT NULL,
  `cliente_id` int(11) NOT NULL,
  `tipo` varchar(45) NOT NULL DEFAULT '"ord"',
  `descricao` varchar(45) NOT NULL,
  `data_criacao` date NOT NULL,
  `taxa_remuneracao` double DEFAULT NULL,
  `preriocidade_juros` int(11) DEFAULT NULL,
  `prazo_dias` int(11) DEFAULT NULL,
  `custo` double DEFAULT NULL,
  `ultimomovimento` int(11) NOT NULL,
  PRIMARY KEY (`agencia_id`,`conta_id`),
  KEY `idx_cliente` (`agencia_id`,`cliente_id`),
  KEY `idx_agencia` (`agencia_id`),
  CONSTRAINT `fk_agenciaconta` FOREIGN KEY (`agencia_id`) REFERENCES `agencias` (`agencia_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_clienteconta` FOREIGN KEY (`agencia_id`, `cliente_id`) REFERENCES `clientes` (`agencia_id`, `cliente_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contas`
--

LOCK TABLES `contas` WRITE;
/*!40000 ALTER TABLE `contas` DISABLE KEYS */;
/*!40000 ALTER TABLE `contas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movimentos`
--

DROP TABLE IF EXISTS `movimentos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `movimentos` (
  `movimento_id` int(11) NOT NULL,
  `origem_agencia_id` int(11) DEFAULT NULL,
  `origem_conta_id` int(11) DEFAULT NULL,
  `cartao_id` int(11) DEFAULT NULL,
  `data_movimento` datetime DEFAULT NULL,
  `valor` double DEFAULT NULL,
  `tipo` varchar(45) DEFAULT NULL,
  `destino_agencia_id` int(11) DEFAULT NULL,
  `destino_conta_id` int(11) DEFAULT NULL,
  `descricao` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`movimento_id`),
  KEY `fk_cartao_idx` (`cartao_id`),
  KEY `fk_contaorigem_idx` (`origem_agencia_id`,`origem_conta_id`),
  CONSTRAINT `fk_cartao` FOREIGN KEY (`cartao_id`) REFERENCES `cartoes` (`cartao_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_contaorigem` FOREIGN KEY (`origem_agencia_id`, `origem_conta_id`) REFERENCES `contas` (`agencia_id`, `conta_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movimentos`
--

LOCK TABLES `movimentos` WRITE;
/*!40000 ALTER TABLE `movimentos` DISABLE KEYS */;
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

-- Dump completed on 2018-04-18 16:24:07

-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: localhost    Database: mercado
-- ------------------------------------------------------
-- Server version	8.0.33

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `categoria`
--

DROP TABLE IF EXISTS `categoria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categoria` (
  `idcategoria` int NOT NULL AUTO_INCREMENT,
  `nombre_categoria` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idcategoria`),
  UNIQUE KEY `idcategoria_UNIQUE` (`idcategoria`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categoria`
--

LOCK TABLES `categoria` WRITE;
/*!40000 ALTER TABLE `categoria` DISABLE KEYS */;
INSERT INTO `categoria` VALUES (1,'ALIMENTOS'),(2,'TECNOLOGÍA'),(3,'ELECTRODOMÉSTICOS'),(4,'COSMÉTICOS'),(5,'DEPORTES'),(6,'MODA'),(7,'JUGUETERÍA'),(8,'HOGAR'),(9,'MASCOTAS');
/*!40000 ALTER TABLE `categoria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cliente` (
  `idCliente` int NOT NULL AUTO_INCREMENT,
  `nro_identificacion` varchar(30) DEFAULT NULL,
  `nombre_Completo` varchar(45) DEFAULT NULL,
  `direccion` varchar(100) DEFAULT NULL,
  `id_pais` int DEFAULT NULL,
  `edad` int DEFAULT NULL,
  `genero` int DEFAULT NULL,
  PRIMARY KEY (`idCliente`),
  UNIQUE KEY `idCliente_UNIQUE` (`idCliente`),
  KEY `id_pais_idx` (`id_pais`),
  CONSTRAINT `id_pais` FOREIGN KEY (`id_pais`) REFERENCES `pais` (`idpais`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES (1,'1389494','Lupe Mendez','Av 34 nro 50-90 barrio tres q',1,60,0),(2,'679947','Alana Rodriguez','usme crra23 nro 90-90',2,25,0),(3,'10930445','Zoe Hernandez','Chia, av45 casa 34 mz 45',3,45,0),(4,'095067','Andres Perez','los quindos mz f casa 34',4,61,1),(5,'7857856','Juan Linares','Oro negro torre b apto 90',5,74,1),(6,'10295866','Stefano Benitez','Torre verde bloque 7 casa 45',1,61,1),(7,'1010144365','Luka Modric','Avenida siempre viva',2,43,1);
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detallefactura`
--

DROP TABLE IF EXISTS `detallefactura`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `detallefactura` (
  `id_factura` int DEFAULT NULL,
  `id_producto` int DEFAULT NULL,
  `cantidad` int DEFAULT NULL,
  KEY `id_factura_idx` (`id_factura`),
  KEY `id_producto_idx` (`id_producto`),
  CONSTRAINT `id_factura` FOREIGN KEY (`id_factura`) REFERENCES `factura` (`idfactura`),
  CONSTRAINT `id_producto` FOREIGN KEY (`id_producto`) REFERENCES `producto` (`idproducto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detallefactura`
--

LOCK TABLES `detallefactura` WRITE;
/*!40000 ALTER TABLE `detallefactura` DISABLE KEYS */;
INSERT INTO `detallefactura` VALUES (1,8,1),(1,9,1),(1,10,1),(1,24,1),(1,30,1),(1,31,1),(1,32,1),(1,33,1),(1,37,1),(1,39,1),(2,23,1),(2,24,1),(2,28,1),(2,29,1),(2,30,1),(2,31,1),(2,32,1),(2,33,1),(2,36,1),(2,38,1),(3,26,1),(3,27,1),(3,30,1),(3,21,2),(4,30,1),(5,31,1),(5,26,1),(6,35,1),(6,38,1),(7,45,1),(7,51,1),(7,16,1),(8,49,1),(8,52,1),(9,64,1),(9,70,1),(9,68,1),(10,55,1),(11,1,1),(11,45,1),(11,52,1),(12,60,1),(13,67,1),(14,41,2),(15,41,2),(16,42,1),(17,49,1),(17,54,1),(18,69,1),(19,69,1),(19,2,1);
/*!40000 ALTER TABLE `detallefactura` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `entrega`
--

DROP TABLE IF EXISTS `entrega`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `entrega` (
  `identrega` int NOT NULL AUTO_INCREMENT,
  `id_cliente` int DEFAULT NULL,
  `fecha_entrega` datetime DEFAULT NULL,
  `id_premio` int DEFAULT NULL,
  PRIMARY KEY (`identrega`),
  UNIQUE KEY `iddetalleClientePremio_UNIQUE` (`identrega`),
  KEY `id_cliente_idx` (`id_cliente`),
  KEY `id_premio_idx` (`id_premio`),
  CONSTRAINT `id_cliente` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`idCliente`),
  CONSTRAINT `id_premio` FOREIGN KEY (`id_premio`) REFERENCES `premio` (`idpremio`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `entrega`
--

LOCK TABLES `entrega` WRITE;
/*!40000 ALTER TABLE `entrega` DISABLE KEYS */;
/*!40000 ALTER TABLE `entrega` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `factura`
--

DROP TABLE IF EXISTS `factura`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `factura` (
  `idfactura` int NOT NULL AUTO_INCREMENT,
  `fecha_factura` datetime DEFAULT NULL,
  `total_factura` decimal(17,2) DEFAULT NULL,
  `id_cliente` int DEFAULT NULL,
  PRIMARY KEY (`idfactura`),
  UNIQUE KEY `idfactura_UNIQUE` (`idfactura`),
  KEY `id_cliente_idx` (`id_cliente`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `factura`
--

LOCK TABLES `factura` WRITE;
/*!40000 ALTER TABLE `factura` DISABLE KEYS */;
INSERT INTO `factura` VALUES (1,'2024-04-07 14:18:02',9616653.00,1),(2,'2024-04-07 14:23:57',23769102.00,2),(3,'2024-04-18 14:57:16',700000.00,4),(4,'2024-04-15 15:14:12',600890.00,4),(5,'2024-04-15 15:15:05',477000.00,6),(6,'2024-04-15 15:16:22',962000.00,1),(7,'2024-04-15 15:17:56',682712.00,5),(8,'2024-04-12 15:19:13',194280.00,2),(9,'2024-04-15 15:20:08',1024000.00,3),(10,'2024-04-17 15:27:55',136000.00,7),(11,'2024-04-17 15:31:54',682042.00,4),(12,'2024-04-17 15:33:14',170000.00,1),(13,'2024-04-17 15:34:04',161900.00,6),(14,'2024-04-14 15:34:54',1140000.00,2),(15,'2024-04-22 15:35:44',1140000.00,7),(16,'2024-04-27 15:36:45',1300200.00,6),(17,'2024-04-19 15:37:40',247500.00,5),(18,'2024-04-13 15:38:35',1680000.00,5),(19,'2024-04-13 15:39:20',1693590.00,4);
/*!40000 ALTER TABLE `factura` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pais`
--

DROP TABLE IF EXISTS `pais`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pais` (
  `idpais` int NOT NULL AUTO_INCREMENT,
  `nombre_pais` varchar(45) DEFAULT NULL,
  `tipo` int DEFAULT NULL,
  PRIMARY KEY (`idpais`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pais`
--

LOCK TABLES `pais` WRITE;
/*!40000 ALTER TABLE `pais` DISABLE KEYS */;
INSERT INTO `pais` VALUES (1,'COLOMBIA',0),(2,'ECUADOR',2),(3,'PANAMA',1),(4,'MEXICO',2),(5,'GUATEMALA',1);
/*!40000 ALTER TABLE `pais` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `premio`
--

DROP TABLE IF EXISTS `premio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `premio` (
  `idpremio` int NOT NULL AUTO_INCREMENT,
  `estado` int DEFAULT NULL,
  `idcliente` int DEFAULT NULL,
  PRIMARY KEY (`idpremio`),
  UNIQUE KEY `idpremio_UNIQUE` (`idpremio`),
  KEY `idcliente_idx` (`idcliente`),
  CONSTRAINT `idcliente` FOREIGN KEY (`idcliente`) REFERENCES `cliente` (`idCliente`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `premio`
--

LOCK TABLES `premio` WRITE;
/*!40000 ALTER TABLE `premio` DISABLE KEYS */;
/*!40000 ALTER TABLE `premio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `producto`
--

DROP TABLE IF EXISTS `producto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `producto` (
  `idproducto` int NOT NULL,
  `nombre_producto` varchar(100) DEFAULT NULL,
  `precio` decimal(15,2) DEFAULT NULL,
  `id_categoria` int DEFAULT NULL,
  PRIMARY KEY (`idproducto`),
  KEY `id_categoria_idx` (`id_categoria`),
  CONSTRAINT `id_categoria` FOREIGN KEY (`id_categoria`) REFERENCES `categoria` (`idcategoria`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `producto`
--

LOCK TABLES `producto` WRITE;
/*!40000 ALTER TABLE `producto` DISABLE KEYS */;
INSERT INTO `producto` VALUES (1,'Cafe Molido',12950.00,1),(2,'Huevo Rojo A X 30 SMN 30 und',13590.00,1),(3,'Aceite Vegetal FRESCAMPO 3000 ml',19480.00,1),(4,'Arroz Blanco Arroba ',49500.00,1),(5,'Coffee Time Lovers 500 gr',39900.00,1),(6,'Queso Holandes Gouda 450gr',34950.00,1),(7,'Chorizo Antioqueño PORCHI 1000 gr',31400.00,1),(8,'Pechuga Para Hornear Pavos Del Campo 3-4 kg xund ',283000.00,1),(9,'Pierna De Cordero Deshuesada Simunovic x1,4kg',176372.00,1),(10,'Bife De Chata Res Ranchera x1200g ',77880.00,1),(11,'Aceite Puroil vegetal x3000ml ',22890.00,1),(12,'Chocolate Corona resellable x500g ',11000.00,1),(13,'Avena Quaker hojuela original gratis más contenido x1100g ',16890.00,1),(14,'Café Nescafé Tradición instantáneo x170g ',16780.00,1),(15,'Atún En Agua Van Camps x 160g x 4und ',27800.00,1),(16,'Lenteja Maritza premium x500g ',4000.00,1),(17,'Café Juan Valdez Colina molido x454g ',30000.00,1),(18,'Salsa Bachaca surtido x4und x1000g ',46000.00,1),(19,'Aceite Sublime oliva extra virgen Español x500ml ',44500.00,1),(20,'Cereal Fitness 25% menos azúcar x570g',10000.00,1),(21,'Soporte Para Portátil En Aluminio Plegable Ergonómica',50000.00,2),(22,'Adaptador Usb Macho A Usb Tipo C',12000.00,2),(23,'Televisor HISENSE 58 Pulgadas LED Uhd4K Smart TV 58A6K',1499000.00,2),(24,'Televisor HYUNDAI 43 Pulgadas LED Fhd Smart TV HYLED4322GiM',916000.00,2),(25,'Oximetro De Pulso Frecuencia Cardiaca Pulsioximetro',27890.00,2),(26,'Adaptador Corriente Universal Viajero Toma 2 Puerto Usb',27000.00,2),(27,'Aspiradora Recogedor Pelo Manual Para Mascota Sin Maltrato',58000.00,2),(28,'Computador ASUS Vivobook 15 Intel Core i5 1235U RAM 8 GB 1 TB',2900000.00,2),(29,'Computador Portatil HP AMD Ryzen 5 5500U RAM 8 GB 512 GB',1800000.00,2),(30,'Impresora Hp Ink Tank 415',600890.00,2),(31,'Celular SAMSUNG Galaxy A04S 128 GB 4 GB RAM Negro',450000.00,2),(32,'Celular MOTOROLA G72 128 GB 6 GB RAM Negro',417000.00,2),(33,'Galaxy Watch 4 40mm Black SAMSUNG',749000.00,2),(34,'iPhone 15 128GB Rosado',3890000.00,2),(35,'Airpods Pro Audifonos Inalambricos Para Iphone',62000.00,2),(36,'Consola Ps5 Slim 1 TB Blanca + Spiderman 2',2400000.00,2),(37,'Reloj Inteligente Y68 Smartwatch D20 + Audífonos Airdots Pro 3 Táctiles',78900.00,2),(38,'Parlante KALLEY BSK KBSK8',900000.00,2),(39,'Reproductor Express Reempacado ROKU ROK3930EU',100000.00,2),(40,'Camara De Seguridad Robotica Ip Full Hd Wifi Vigilancia 3 Antenas',89000.00,2),(41,'Tablet LENOVO 10.1 pulgadas Wifi 64 GB 4 GB RAM Gris Hierro',570000.00,2),(42,'Impresora multifuncional HP Smart Tank WiFi 750',1300200.00,2),(43,'Lavadora WHIRLPOOL Carga Superior 19 kg (42 lb) WW19LTAHLA',2059900.00,3),(44,'NEVERA HACEB Congelador Superior 243 LTS 9002741',1585900.00,3),(45,'Estufa Romero 50T Gn Ne HACEB 9002397',642312.00,3),(46,'AIRE INVERTER 12.000 BTU 110v MABE MMI12CABWC',1699900.00,3),(47,'Ventilador Tropical Plus Negro SAMURAI VE2312I0',199999.00,3),(48,'Ampolleta Capilar Con Feromonas Rose Potion Lm X 25Ml',25500.00,4),(49,'Shampoo Cabello Alisados Y Extensiones Con Iones Negativos',167500.00,4),(50,'Shampoo Hidratante Y Nutritivo De Uva Lm Para Cabellos Alisados X',500000.00,4),(51,'Cover Dreamy Corrector De Ojeras L102',36400.00,4),(52,'Labial En Barra 03 Pretty Pop',26780.00,4),(53,'Soporte De Entrenamiento Rodillo Tranz X Bicicleta',410000.00,5),(54,'Botella blanda para running de 500ml tipo soft flask flexible kalenji azul',80000.00,5),(55,'Cinturon de hidratación de running kiprun portabotellas 250ml + dorsal negro',136000.00,5),(56,'Kit para masajes musculares de running aptonia discovery100 negro',210000.00,5),(57,'Toalla de microfibra talla l 80 x 130 cm para natación nabaiji rosa',38000.00,5),(58,'Chaqueta De Jean Para Hombre',88900.00,6),(59,'Chaqueta O Gabán dama',80000.00,6),(60,'Falda de encaje larga Ariela ',170000.00,6),(61,'Vestido largo Azucena de Engracia',123000.00,6),(62,'Buso Deportivo Beisbolera ',76000.00,6),(63,'Computador Didáctico Aprendizaje Juguetes Jugueteria',139000.00,7),(64,'Juguetes Carros De Dinosaurios X 8',90000.00,7),(65,'Bolitron Juguetes Antiestres',20000.00,7),(66,'Piano Transportes Juguetes Bebes',62700.00,7),(67,'Espejo Napoli',161900.00,8),(68,'Cortinas tercipelo plumas',245000.00,8),(69,'Combo Para Habitación Kammel',1680000.00,8),(70,'Mueble Organizador Elegance Da',689000.00,8),(71,'Repisa Esquinera',55000.00,8);
/*!40000 ALTER TABLE `producto` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-04-14 21:54:38

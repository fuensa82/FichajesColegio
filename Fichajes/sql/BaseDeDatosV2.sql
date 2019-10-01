-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         10.4.8-MariaDB - mariadb.org binary distribution
-- SO del servidor:              Win64
-- HeidiSQL Versión:             10.2.0.5599
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Volcando estructura de base de datos para colsan
CREATE DATABASE IF NOT EXISTS `colsan` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `colsan`;

-- Volcando estructura para tabla colsan.fichajes
DROP TABLE IF EXISTS `fichajes`;
CREATE TABLE IF NOT EXISTS `fichajes` (
  `idFichaje` int(11) NOT NULL AUTO_INCREMENT,
  `currentTime` bigint(20) NOT NULL,
  `fecha` date NOT NULL,
  `hora` time NOT NULL,
  `idProfesor` int(11) NOT NULL,
  `terminal` int(11) DEFAULT 1,
  `dentro` binary(50) DEFAULT NULL,
  PRIMARY KEY (`idFichaje`)
) ENGINE=InnoDB AUTO_INCREMENT=159 DEFAULT CHARSET=utf8 COMMENT='Aquí se guardaran los fichajes de todos los profesores, entradas y salidas con sus horas correspondientes';

-- Volcando datos para la tabla colsan.fichajes: ~3 rows (aproximadamente)
/*!40000 ALTER TABLE `fichajes` DISABLE KEYS */;
INSERT IGNORE INTO `fichajes` (`idFichaje`, `currentTime`, `fecha`, `hora`, `idProfesor`, `terminal`, `dentro`) VALUES
	(156, 1569496839209, '2019-09-26', '13:20:39', 1, 2, _binary 0x7472756500000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000),
	(157, 1569496853161, '2019-09-26', '13:20:53', 1, 2, _binary 0x66616C7365000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000),
	(158, 1569496858120, '2019-09-26', '13:20:58', 1, 2, _binary 0x7472756500000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000);
/*!40000 ALTER TABLE `fichajes` ENABLE KEYS */;

-- Volcando estructura para tabla colsan.profesores
DROP TABLE IF EXISTS `profesores`;
CREATE TABLE IF NOT EXISTS `profesores` (
  `idProfesor` int(11) NOT NULL AUTO_INCREMENT,
  `nombreCorto` varchar(50) DEFAULT NULL,
  `nombre` varchar(50) DEFAULT NULL,
  `apellidos` varchar(100) DEFAULT NULL,
  `idTarjeta` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`idProfesor`),
  UNIQUE KEY `idTarjeta` (`idTarjeta`),
  UNIQUE KEY `nombreCorto` (`nombreCorto`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='Datos de los profesores';

-- Volcando datos para la tabla colsan.profesores: ~2 rows (aproximadamente)
/*!40000 ALTER TABLE `profesores` DISABLE KEYS */;
INSERT IGNORE INTO `profesores` (`idProfesor`, `nombreCorto`, `nombre`, `apellidos`, `idTarjeta`) VALUES
	(1, 'VictorP', 'Victor', 'Palomo Silva', 1),
	(2, 'Clari', 'Clara', 'Garcia-Verdugo', 2),
	(3, 'MercheP', 'Merche', 'Palomo Silva', 33);
/*!40000 ALTER TABLE `profesores` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;

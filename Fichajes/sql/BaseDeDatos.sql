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

-- Volcando estructura para tabla colsan.registros
CREATE TABLE IF NOT EXISTS `registros` (
  `idRegistro` int(11) NOT NULL AUTO_INCREMENT,
  `currentTime` bigint(20) NOT NULL,
  `fecha` date NOT NULL,
  `hora` time NOT NULL,
  `idPersonal` int(11) NOT NULL,
  `terminal` int(11) DEFAULT 1,
  PRIMARY KEY (`idRegistro`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='Aquí se guardaran los fichajes de todos los profesores, entradas y salidas con sus horas correspondientes';

-- Volcando datos para la tabla colsan.registros: ~6 rows (aproximadamente)
/*!40000 ALTER TABLE `registros` DISABLE KEYS */;
INSERT IGNORE INTO `registros` (`idRegistro`, `currentTime`, `fecha`, `hora`, `idPersonal`, `terminal`) VALUES
	(1, 2, '2019-09-20', '00:00:01', 123332, 2),
	(2, 2, '2019-09-20', '00:00:01', 3, 2),
	(3, 2, '2019-09-20', '00:00:01', 123123123, 2),
	(4, 2, '2019-09-20', '00:00:01', 99, 2),
	(5, 2, '2019-09-20', '00:00:01', 23423423, 2),
	(6, 2, '2019-09-20', '00:00:01', 1221, 2);
/*!40000 ALTER TABLE `registros` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;

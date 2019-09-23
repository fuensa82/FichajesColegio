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
CREATE TABLE IF NOT EXISTS `fichajes` (
  `idFichaje` int(11) NOT NULL AUTO_INCREMENT,
  `currentTime` bigint(20) NOT NULL,
  `fecha` date NOT NULL,
  `hora` time NOT NULL,
  `idProfesor` int(11) NOT NULL,
  `terminal` int(11) DEFAULT 1,
  PRIMARY KEY (`idFichaje`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8 COMMENT='Aquí se guardaran los fichajes de todos los profesores, entradas y salidas con sus horas correspondientes';

-- Volcando datos para la tabla colsan.fichajes: ~6 rows (aproximadamente)
/*!40000 ALTER TABLE `fichajes` DISABLE KEYS */;
INSERT IGNORE INTO `fichajes` (`idFichaje`, `currentTime`, `fecha`, `hora`, `idProfesor`, `terminal`) VALUES
	(1, 2, '2019-09-20', '00:00:01', 123332, 2),
	(2, 2, '2019-09-20', '00:00:01', 3, 2),
	(3, 2, '2019-09-20', '00:00:01', 123123123, 2),
	(4, 2, '2019-09-20', '00:00:01', 99, 2),
	(5, 2, '2019-09-20', '00:00:01', 23423423, 2),
	(6, 2, '2019-09-20', '00:00:01', 1221, 2),
	(7, 1569236661718, '2019-09-23', '13:04:21', 1, 2),
	(8, 1569236667082, '2019-09-23', '13:04:27', 2, 2),
	(9, 1569236671532, '2019-09-23', '13:04:31', 3, 2),
	(10, 1569236672781, '2019-09-23', '13:04:32', 1, 2),
	(11, 1569236673552, '2019-09-23', '13:04:33', 2, 2),
	(12, 1569236674364, '2019-09-23', '13:04:34', 1, 2),
	(13, 1569236675411, '2019-09-23', '13:04:35', 2, 2),
	(14, 1569236676067, '2019-09-23', '13:04:36', 1, 2),
	(15, 1569236676708, '2019-09-23', '13:04:36', 3, 2),
	(16, 1569236677402, '2019-09-23', '13:04:37', 2, 2),
	(17, 1569236678068, '2019-09-23', '13:04:38', 3, 2),
	(18, 1569236678740, '2019-09-23', '13:04:38', 1, 2),
	(19, 1569236679782, '2019-09-23', '13:04:39', 2, 2),
	(20, 1569236832637, '2019-09-23', '13:07:12', 1, 2),
	(21, 1569236834958, '2019-09-23', '13:07:14', 2, 2),
	(22, 1569236836562, '2019-09-23', '13:07:16', 3, 2),
	(23, 1569236840724, '2019-09-23', '13:07:20', 1, 2),
	(24, 1569236844875, '2019-09-23', '13:07:24', 1, 2);
/*!40000 ALTER TABLE `fichajes` ENABLE KEYS */;

-- Volcando estructura para tabla colsan.profesores
CREATE TABLE IF NOT EXISTS `profesores` (
  `idProfesor` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) DEFAULT NULL,
  `apellidos` varchar(100) DEFAULT NULL,
  `idTarjeta` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`idProfesor`),
  UNIQUE KEY `idTarjeta` (`idTarjeta`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='Datos de los profesores';

-- Volcando datos para la tabla colsan.profesores: ~3 rows (aproximadamente)
/*!40000 ALTER TABLE `profesores` DISABLE KEYS */;
INSERT IGNORE INTO `profesores` (`idProfesor`, `nombre`, `apellidos`, `idTarjeta`) VALUES
	(1, 'Victor', 'Palomo Silva', 1),
	(2, 'Clara', 'Garcia-Verdugo', 2),
	(3, 'Merche', 'Palomo Silva', 3);
/*!40000 ALTER TABLE `profesores` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;

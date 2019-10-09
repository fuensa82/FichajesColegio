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
DROP DATABASE IF EXISTS `colsan`;
CREATE DATABASE IF NOT EXISTS `colsan` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `colsan`;

-- Volcando estructura para tabla colsan.cursos
DROP TABLE IF EXISTS `cursos`;
CREATE TABLE IF NOT EXISTS `cursos` (
  `curso` varchar(9) NOT NULL,
  PRIMARY KEY (`curso`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Lista de cursos que se van creando';

-- Volcando datos para la tabla colsan.cursos: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `cursos` DISABLE KEYS */;
/*!40000 ALTER TABLE `cursos` ENABLE KEYS */;

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
  `curso` varchar(9) NOT NULL,
  PRIMARY KEY (`idFichaje`)
) ENGINE=InnoDB AUTO_INCREMENT=186 DEFAULT CHARSET=utf8 COMMENT='Aquí se guardaran los fichajes de todos los profesores, entradas y salidas con sus horas correspondientes';

-- Volcando datos para la tabla colsan.fichajes: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `fichajes` DISABLE KEYS */;
/*!40000 ALTER TABLE `fichajes` ENABLE KEYS */;

-- Volcando estructura para tabla colsan.horarios
DROP TABLE IF EXISTS `horarios`;
CREATE TABLE IF NOT EXISTS `horarios` (
  `idFicha` int(11) NOT NULL AUTO_INCREMENT,
  `horaIni` time NOT NULL DEFAULT '00:00:00',
  `horaFin` time NOT NULL DEFAULT '00:00:00',
  `idProfesor` int(11) NOT NULL DEFAULT 0,
  `dia` char(1) NOT NULL DEFAULT '0' COMMENT 'L, M, X, J, V, S, D',
  `tipoHora` char(2) DEFAULT NULL COMMENT 'L,NL,C',
  `curso` varchar(9) DEFAULT NULL,
  PRIMARY KEY (`idFicha`)
) ENGINE=InnoDB AUTO_INCREMENT=8813 DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla colsan.horarios: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `horarios` DISABLE KEYS */;
/*!40000 ALTER TABLE `horarios` ENABLE KEYS */;

-- Volcando estructura para tabla colsan.horasextra
DROP TABLE IF EXISTS `horasextra`;
CREATE TABLE IF NOT EXISTS `horasextra` (
  `idHoraExtra` int(11) NOT NULL AUTO_INCREMENT,
  `idProfesor` int(11) NOT NULL DEFAULT 0,
  `fecha` date NOT NULL DEFAULT '0000-00-00',
  `horaIni` time NOT NULL,
  `horaFin` time NOT NULL,
  `motivo` varchar(300) NOT NULL DEFAULT '',
  `fechaAlta` datetime NOT NULL DEFAULT current_timestamp(),
  `tipoHora` varchar(2) NOT NULL,
  `curso` varchar(9) NOT NULL,
  PRIMARY KEY (`idHoraExtra`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='Aquí se guardaran horas que hacen los profesores sin ser de sus horarios. Cursos, cosas extra, ... en definitiva lo que no esté en el horario lectivo ni en el complementario';

-- Volcando datos para la tabla colsan.horasextra: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `horasextra` DISABLE KEYS */;
/*!40000 ALTER TABLE `horasextra` ENABLE KEYS */;

-- Volcando estructura para tabla colsan.profesores
DROP TABLE IF EXISTS `profesores`;
CREATE TABLE IF NOT EXISTS `profesores` (
  `idProfesor` int(11) NOT NULL AUTO_INCREMENT,
  `nombreCorto` varchar(50) DEFAULT NULL,
  `nombre` varchar(50) DEFAULT NULL,
  `apellidos` varchar(100) NOT NULL DEFAULT '',
  `idTarjeta` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`idProfesor`),
  UNIQUE KEY `idTarjeta` (`idTarjeta`),
  UNIQUE KEY `nombreCorto` (`nombreCorto`)
) ENGINE=InnoDB AUTO_INCREMENT=283 DEFAULT CHARSET=utf8 COMMENT='Datos de los profesores';

-- Volcando datos para la tabla colsan.profesores: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `profesores` DISABLE KEYS */;
/*!40000 ALTER TABLE `profesores` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;

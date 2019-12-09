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

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla colsan.detallesinformes
DROP TABLE IF EXISTS `detallesinformes`;
CREATE TABLE IF NOT EXISTS `detallesinformes` (
  `idDetalleInforme` bigint(20) NOT NULL AUTO_INCREMENT,
  `idProfesor` int(11) NOT NULL DEFAULT 0,
  `totalHoras` int(11) NOT NULL DEFAULT 0,
  `horaIni` time NOT NULL DEFAULT '00:00:00',
  `horaFin` time NOT NULL DEFAULT '00:00:00',
  `fecha` date NOT NULL DEFAULT '0000-00-00' COMMENT 'Fecha del fichaje',
  `tipoHora` varchar(2) NOT NULL,
  `fechaCalculo` datetime DEFAULT current_timestamp() COMMENT 'Fecha en que se realiza el calculo',
  `totalHorasString` varchar(50) DEFAULT NULL,
  `llamada` varchar(150) DEFAULT NULL,
  `mes` int(11) DEFAULT NULL COMMENT 'Mes al que pertenece el calculo de horas',
  PRIMARY KEY (`idDetalleInforme`)
) ENGINE=InnoDB AUTO_INCREMENT=53029 DEFAULT CHARSET=utf8;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla colsan.eventoprofesor
DROP TABLE IF EXISTS `eventoprofesor`;
CREATE TABLE IF NOT EXISTS `eventoprofesor` (
  `idEvento` int(11) NOT NULL,
  `idProfesor` int(11) NOT NULL,
  PRIMARY KEY (`idEvento`,`idProfesor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Profesores a los que aplica un determinado evento';

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla colsan.eventos
DROP TABLE IF EXISTS `eventos`;
CREATE TABLE IF NOT EXISTS `eventos` (
  `idEvento` int(11) NOT NULL AUTO_INCREMENT,
  `fecha` date NOT NULL,
  `horaIni` time DEFAULT NULL,
  `horaFin` time DEFAULT NULL,
  `diaCompleto` binary(5) DEFAULT NULL,
  `descripcion` varchar(200) NOT NULL,
  `curso` varchar(9) NOT NULL,
  PRIMARY KEY (`idEvento`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='Eventos especiales del colegio';

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla colsan.fichajes
DROP TABLE IF EXISTS `fichajes`;
CREATE TABLE IF NOT EXISTS `fichajes` (
  `idFichaje` int(11) NOT NULL AUTO_INCREMENT,
  `currentTime` bigint(20) NOT NULL,
  `fecha` date NOT NULL,
  `hora` time NOT NULL,
  `idProfesor` int(11) NOT NULL,
  `terminal` varchar(50) DEFAULT NULL,
  `dentro` binary(50) DEFAULT NULL,
  `curso` varchar(9) NOT NULL,
  `motivo` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`idFichaje`)
) ENGINE=InnoDB AUTO_INCREMENT=5875 DEFAULT CHARSET=utf8 COMMENT='Aquí se guardaran los fichajes de todos los profesores, entradas y salidas con sus horas correspondientes';

-- La exportación de datos fue deseleccionada.

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
) ENGINE=InnoDB AUTO_INCREMENT=9751 DEFAULT CHARSET=utf8;

-- La exportación de datos fue deseleccionada.

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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='Aquí se guardaran horas que hacen los profesores sin ser de sus horarios. Cursos, cosas extra, ... en definitiva lo que no esté en el horario lectivo ni en el complementario';

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla colsan.informes
DROP TABLE IF EXISTS `informes`;
CREATE TABLE IF NOT EXISTS `informes` (
  `idInforme` int(11) NOT NULL AUTO_INCREMENT,
  `fechaGeneracion` datetime NOT NULL DEFAULT current_timestamp(),
  `mes` int(11) NOT NULL,
  `idProfesor` int(11) NOT NULL,
  `observaciones` varchar(250) DEFAULT NULL,
  `curso` varchar(9) NOT NULL DEFAULT '',
  `horasL` int(11) DEFAULT NULL COMMENT 'Segundos lectivos del mes',
  `horasNL` int(11) DEFAULT NULL COMMENT 'Segundos no lectivos del mes',
  `horasC` int(11) DEFAULT NULL COMMENT 'Segundos complementarios del mes',
  PRIMARY KEY (`idInforme`)
) ENGINE=InnoDB AUTO_INCREMENT=7302 DEFAULT CHARSET=utf8 COMMENT='Contendrá todos los datos de los informes que se han ido generando de cada profesor';

-- La exportación de datos fue deseleccionada.

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
) ENGINE=InnoDB AUTO_INCREMENT=319 DEFAULT CHARSET=utf8 COMMENT='Datos de los profesores';

-- La exportación de datos fue deseleccionada.

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;

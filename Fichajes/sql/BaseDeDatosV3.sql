-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         10.5.6-MariaDB - mariadb.org binary distribution
-- SO del servidor:              Win64
-- HeidiSQL Versión:             11.0.0.5919
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Volcando estructura de base de datos para fichajes
CREATE DATABASE IF NOT EXISTS `fichajes` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `fichajes`;

-- Volcando estructura para tabla fichajes.cursos
CREATE TABLE IF NOT EXISTS `cursos` (
  `curso` varchar(9) NOT NULL,
  PRIMARY KEY (`curso`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Lista de cursos que se van creando';

-- Volcando datos para la tabla fichajes.cursos: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `cursos` DISABLE KEYS */;
INSERT IGNORE INTO `cursos` (`curso`) VALUES
	('2021');
/*!40000 ALTER TABLE `cursos` ENABLE KEYS */;

-- Volcando estructura para tabla fichajes.detallesinformes
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
) ENGINE=InnoDB AUTO_INCREMENT=57088 DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla fichajes.detallesinformes: ~10 rows (aproximadamente)
/*!40000 ALTER TABLE `detallesinformes` DISABLE KEYS */;
INSERT IGNORE INTO `detallesinformes` (`idDetalleInforme`, `idProfesor`, `totalHoras`, `horaIni`, `horaFin`, `fecha`, `tipoHora`, `fechaCalculo`, `totalHorasString`, `llamada`, `mes`) VALUES
	(57078, 382, 600, '14:50:00', '15:00:00', '2021-03-17', 'T', '2021-03-18 11:19:45', '0:10:00', 'Caso 3 - contabilizaHorasLectivasOCmplementarias', 3),
	(57079, 382, 1, '14:11:58', '14:11:59', '2021-03-17', 'T', '2021-03-18 11:19:45', '0:00:01', ' Ultimmo caso - contabilizaHorasLectivasOCmplementarias', 3),
	(57080, 382, 5, '14:11:51', '14:11:56', '2021-03-17', 'T', '2021-03-18 11:19:45', '0:00:05', ' Ultimmo caso - contabilizaHorasLectivasOCmplementarias', 3),
	(57081, 382, 14, '14:11:31', '14:11:45', '2021-03-17', 'T', '2021-03-18 11:19:45', '0:00:14', ' Ultimmo caso - contabilizaHorasLectivasOCmplementarias', 3),
	(57082, 382, 11, '10:42:56', '10:43:07', '2021-03-18', 'D', '2021-03-18 11:19:45', '0:00:11', ' Ultimmo caso - contabilizaHorasLectivasOCmplementarias', 3),
	(57083, 382, 300, '15:00:00', '15:05:00', '2021-03-17', 'NL', '2021-03-18 11:19:45', '0:05:00', 'contabilizaHorasNoLectivas', 3),
	(57084, 381, 13, '10:42:58', '10:43:11', '2021-03-18', 'T', '2021-03-18 11:19:45', '0:00:13', ' Ultimmo caso - contabilizaHorasLectivasOCmplementarias', 3),
	(57085, 381, 2, '14:11:57', '14:11:59', '2021-03-17', 'T', '2021-03-18 11:19:45', '0:00:02', ' Ultimmo caso - contabilizaHorasLectivasOCmplementarias', 3),
	(57086, 381, 10, '14:11:47', '14:11:57', '2021-03-17', 'T', '2021-03-18 11:19:45', '0:00:10', ' Ultimmo caso - contabilizaHorasLectivasOCmplementarias', 3),
	(57087, 381, 12, '14:11:30', '14:11:42', '2021-03-17', 'T', '2021-03-18 11:19:45', '0:00:12', ' Ultimmo caso - contabilizaHorasLectivasOCmplementarias', 3);
/*!40000 ALTER TABLE `detallesinformes` ENABLE KEYS */;

-- Volcando estructura para tabla fichajes.eventoprofesor
CREATE TABLE IF NOT EXISTS `eventoprofesor` (
  `idEvento` int(11) NOT NULL,
  `idProfesor` int(11) NOT NULL,
  PRIMARY KEY (`idEvento`,`idProfesor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Profesores a los que aplica un determinado evento';

-- Volcando datos para la tabla fichajes.eventoprofesor: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `eventoprofesor` DISABLE KEYS */;
/*!40000 ALTER TABLE `eventoprofesor` ENABLE KEYS */;

-- Volcando estructura para tabla fichajes.eventos
CREATE TABLE IF NOT EXISTS `eventos` (
  `idEvento` int(11) NOT NULL AUTO_INCREMENT,
  `fecha` date NOT NULL,
  `horaIni` time DEFAULT NULL,
  `horaFin` time DEFAULT NULL,
  `diaCompleto` binary(5) DEFAULT NULL,
  `descripcion` varchar(200) NOT NULL,
  `curso` varchar(9) NOT NULL,
  PRIMARY KEY (`idEvento`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='Eventos especiales del colegio';

-- Volcando datos para la tabla fichajes.eventos: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `eventos` DISABLE KEYS */;
/*!40000 ALTER TABLE `eventos` ENABLE KEYS */;

-- Volcando estructura para tabla fichajes.fichajes
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
) ENGINE=InnoDB AUTO_INCREMENT=12399 DEFAULT CHARSET=utf8 COMMENT='Aquí se guardaran los fichajes de todos los profesores, entradas y salidas con sus horas correspondientes';

-- Volcando datos para la tabla fichajes.fichajes: ~26 rows (aproximadamente)
/*!40000 ALTER TABLE `fichajes` DISABLE KEYS */;
INSERT IGNORE INTO `fichajes` (`idFichaje`, `currentTime`, `fecha`, `hora`, `idProfesor`, `terminal`, `dentro`, `curso`, `motivo`) VALUES
	(12373, 1615982026629, '2021-03-17', '12:53:46', 382, 'Secretaria', _binary 0x7472756500000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000, '', NULL),
	(12374, 1615982030901, '2021-03-17', '12:53:50', 382, 'Secretaria', _binary 0x66616C7365000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000, '', NULL),
	(12375, 1615982034197, '2021-03-17', '12:53:54', 381, 'Secretaria', _binary 0x7472756500000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000, '', NULL),
	(12376, 1615982035669, '2021-03-17', '12:53:55', 381, 'Secretaria', _binary 0x66616C7365000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000, '', NULL),
	(12377, 1615982036997, '2021-03-17', '12:53:57', 382, 'Secretaria', _binary 0x7472756500000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000, '', NULL),
	(12378, 1615982038069, '2021-03-17', '12:53:58', 381, 'Secretaria', _binary 0x7472756500000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000, '', NULL),
	(12379, 1615982038820, '2021-03-17', '12:53:58', 382, 'Secretaria', _binary 0x66616C7365000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000, '', NULL),
	(12380, 1615982039413, '2021-03-17', '12:53:59', 381, 'Secretaria', _binary 0x66616C7365000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000, '', NULL),
	(12381, 1615986690145, '2021-03-17', '14:11:30', 381, 'Secretaria', _binary 0x7472756500000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000, '2021', NULL),
	(12382, 1615986691383, '2021-03-17', '14:11:31', 382, 'Secretaria', _binary 0x7472756500000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000, '2021', NULL),
	(12383, 1615986702468, '2021-03-17', '14:11:42', 381, 'Secretaria', _binary 0x66616C7365000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000, '2021', NULL),
	(12384, 1615986705042, '2021-03-17', '14:11:45', 382, 'Secretaria', _binary 0x66616C7365000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000, '2021', NULL),
	(12385, 1615986707949, '2021-03-17', '14:11:47', 381, 'Secretaria', _binary 0x7472756500000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000, '2021', NULL),
	(12386, 1615986711417, '2021-03-17', '14:11:51', 382, 'Secretaria', _binary 0x7472756500000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000, '2021', NULL),
	(12387, 1615986716402, '2021-03-17', '14:11:56', 382, 'Secretaria', _binary 0x66616C7365000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000, '2021', NULL),
	(12388, 1615986717042, '2021-03-17', '14:11:57', 381, 'Secretaria', _binary 0x66616C7365000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000, '2021', NULL),
	(12389, 1615986717715, '2021-03-17', '14:11:57', 381, 'Secretaria', _binary 0x7472756500000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000, '2021', NULL),
	(12390, 1615986718512, '2021-03-17', '14:11:58', 382, 'Secretaria', _binary 0x7472756500000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000, '2021', NULL),
	(12391, 1615986719153, '2021-03-17', '14:11:59', 382, 'Secretaria', _binary 0x66616C7365000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000, '2021', NULL),
	(12392, 1615986719840, '2021-03-17', '14:11:59', 381, 'Secretaria', _binary 0x66616C7365000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000, '2021', NULL),
	(12393, 0, '2021-03-17', '14:50:00', 382, 'Secretaria', _binary 0x7472756500000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000, '2021', 'Prueba'),
	(12394, 0, '2021-03-17', '15:05:00', 382, 'Secretaria', _binary 0x66616C7365000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000, '2021', 'Prueba2'),
	(12395, 1616060575829, '2021-03-18', '10:42:56', 382, 'Despacho', _binary 0x7472756500000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000, '2021', NULL),
	(12396, 1616060578344, '2021-03-18', '10:42:58', 381, 'Despacho', _binary 0x7472756500000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000, '2021', NULL),
	(12397, 1616060587026, '2021-03-18', '10:43:07', 382, 'Despacho', _binary 0x66616C7365000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000, '2021', NULL),
	(12398, 1616060591010, '2021-03-18', '10:43:11', 381, 'Despacho', _binary 0x66616C7365000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000, '2021', NULL);
/*!40000 ALTER TABLE `fichajes` ENABLE KEYS */;

-- Volcando estructura para tabla fichajes.horarios
CREATE TABLE IF NOT EXISTS `horarios` (
  `idFicha` int(11) NOT NULL AUTO_INCREMENT,
  `horaIni` time NOT NULL DEFAULT '00:00:00',
  `horaFin` time NOT NULL DEFAULT '00:00:00',
  `idProfesor` int(11) NOT NULL DEFAULT 0,
  `dia` char(1) NOT NULL DEFAULT '0' COMMENT 'L, M, X, J, V, S, D',
  `tipoHora` char(2) DEFAULT NULL COMMENT 'L,NL,C',
  `curso` varchar(9) DEFAULT NULL,
  PRIMARY KEY (`idFicha`)
) ENGINE=InnoDB AUTO_INCREMENT=15616 DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla fichajes.horarios: ~11 rows (aproximadamente)
/*!40000 ALTER TABLE `horarios` DISABLE KEYS */;
INSERT IGNORE INTO `horarios` (`idFicha`, `horaIni`, `horaFin`, `idProfesor`, `dia`, `tipoHora`, `curso`) VALUES
	(15603, '09:00:00', '15:00:00', 381, 'L', 'T', '2021'),
	(15604, '09:00:00', '15:00:00', 381, 'M', 'T', '2021'),
	(15605, '09:00:00', '15:00:00', 381, 'X', 'T', '2021'),
	(15606, '09:00:00', '15:00:00', 381, 'J', 'T', ''),
	(15607, '09:00:00', '15:00:00', 381, 'V', 'T', ''),
	(15608, '09:00:00', '15:00:00', 382, 'L', 'T', ''),
	(15609, '09:00:00', '15:00:00', 382, 'M', 'T', ''),
	(15610, '09:00:00', '15:00:00', 382, 'X', 'T', ''),
	(15612, '09:00:00', '15:00:00', 382, 'V', 'T', ''),
	(15613, '09:00:00', '14:00:00', 381, 'J', 'D', '2021'),
	(15614, '09:00:00', '14:00:00', 382, 'J', 'D', '2021'),
	(15615, '09:00:00', '14:00:00', 384, 'J', 'D', '2021');
/*!40000 ALTER TABLE `horarios` ENABLE KEYS */;

-- Volcando estructura para tabla fichajes.horasextra
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
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='Aquí se guardaran horas que hacen los profesores sin ser de sus horarios. Cursos, cosas extra, ... en definitiva lo que no esté en el horario lectivo ni en el complementario';

-- Volcando datos para la tabla fichajes.horasextra: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `horasextra` DISABLE KEYS */;
/*!40000 ALTER TABLE `horasextra` ENABLE KEYS */;

-- Volcando estructura para tabla fichajes.informes
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
) ENGINE=InnoDB AUTO_INCREMENT=7455 DEFAULT CHARSET=utf8 COMMENT='Contendrá todos los datos de los informes que se han ido generando de cada profesor';

-- Volcando datos para la tabla fichajes.informes: ~25 rows (aproximadamente)
/*!40000 ALTER TABLE `informes` DISABLE KEYS */;
INSERT IGNORE INTO `informes` (`idInforme`, `fechaGeneracion`, `mes`, `idProfesor`, `observaciones`, `curso`, `horasL`, `horasNL`, `horasC`) VALUES
	(7420, '2021-03-18 10:50:10', 3, 384, 'Correcto', '2021', 0, 0, 0),
	(7431, '2021-03-18 11:19:45', 1, 382, 'Correcto', '2021', 0, 0, 0),
	(7432, '2021-03-18 11:19:45', 1, 381, 'Correcto', '2021', 0, 0, 0),
	(7433, '2021-03-18 11:19:45', 2, 382, 'Correcto', '2021', 0, 0, 0),
	(7434, '2021-03-18 11:19:45', 2, 381, 'Correcto', '2021', 0, 0, 0),
	(7435, '2021-03-18 11:19:45', 3, 382, 'Correcto', '2021', 620, 300, 11),
	(7436, '2021-03-18 11:19:45', 3, 381, 'Correcto', '2021', 37, 0, 0),
	(7437, '2021-03-18 11:19:45', 4, 382, 'Correcto', '2021', 0, 0, 0),
	(7438, '2021-03-18 11:19:45', 4, 381, 'Correcto', '2021', 0, 0, 0),
	(7439, '2021-03-18 11:19:45', 5, 382, 'Correcto', '2021', 0, 0, 0),
	(7440, '2021-03-18 11:19:45', 5, 381, 'Correcto', '2021', 0, 0, 0),
	(7441, '2021-03-18 11:19:45', 6, 382, 'Correcto', '2021', 0, 0, 0),
	(7442, '2021-03-18 11:19:45', 6, 381, 'Correcto', '2021', 0, 0, 0),
	(7443, '2021-03-18 11:19:45', 7, 382, 'Correcto', '2021', 0, 0, 0),
	(7444, '2021-03-18 11:19:45', 7, 381, 'Correcto', '2021', 0, 0, 0),
	(7445, '2021-03-18 11:19:45', 8, 382, 'Correcto', '2021', 0, 0, 0),
	(7446, '2021-03-18 11:19:45', 8, 381, 'Correcto', '2021', 0, 0, 0),
	(7447, '2021-03-18 11:19:45', 9, 382, 'Correcto', '2021', 0, 0, 0),
	(7448, '2021-03-18 11:19:45', 9, 381, 'Correcto', '2021', 0, 0, 0),
	(7449, '2021-03-18 11:19:45', 10, 382, 'Correcto', '2021', 0, 0, 0),
	(7450, '2021-03-18 11:19:45', 10, 381, 'Correcto', '2021', 0, 0, 0),
	(7451, '2021-03-18 11:19:45', 11, 382, 'Correcto', '2021', 0, 0, 0),
	(7452, '2021-03-18 11:19:45', 11, 381, 'Correcto', '2021', 0, 0, 0),
	(7453, '2021-03-18 11:19:45', 12, 382, 'Correcto', '2021', 0, 0, 0),
	(7454, '2021-03-18 11:19:46', 12, 381, 'Correcto', '2021', 0, 0, 0);
/*!40000 ALTER TABLE `informes` ENABLE KEYS */;

-- Volcando estructura para tabla fichajes.profesores
CREATE TABLE IF NOT EXISTS `profesores` (
  `idProfesor` int(11) NOT NULL AUTO_INCREMENT,
  `nombreCorto` varchar(50) DEFAULT NULL,
  `nombre` varchar(50) DEFAULT NULL,
  `apellidos` varchar(100) NOT NULL DEFAULT '',
  `idTarjeta` bigint(20) DEFAULT NULL,
  `activo` binary(50) DEFAULT 'true\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0',
  PRIMARY KEY (`idProfesor`),
  UNIQUE KEY `idTarjeta` (`idTarjeta`),
  UNIQUE KEY `nombreCorto` (`nombreCorto`)
) ENGINE=InnoDB AUTO_INCREMENT=385 DEFAULT CHARSET=utf8 COMMENT='Datos de los profesores';

-- Volcando datos para la tabla fichajes.profesores: ~2 rows (aproximadamente)
/*!40000 ALTER TABLE `profesores` DISABLE KEYS */;
INSERT IGNORE INTO `profesores` (`idProfesor`, `nombreCorto`, `nombre`, `apellidos`, `idTarjeta`, `activo`) VALUES
	(381, 'Sergio', 'Sergio', 'Sanchez Alvarez', 1, _binary 0x7472756500000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000),
	(382, 'victor', 'Victor', 'Palomo Silva', 2, _binary 0x7472756500000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000),
	(384, '', '', '', NULL, _binary 0x7472756500000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000);
/*!40000 ALTER TABLE `profesores` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;

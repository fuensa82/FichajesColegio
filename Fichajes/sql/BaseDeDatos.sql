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
INSERT IGNORE INTO `cursos` (`curso`) VALUES
	('2019-2020');
/*!40000 ALTER TABLE `cursos` ENABLE KEYS */;

-- Volcando estructura para tabla colsan.eventos
DROP TABLE IF EXISTS `eventos`;
CREATE TABLE IF NOT EXISTS `eventos` (
  `idEvento` int(11) NOT NULL AUTO_INCREMENT,
  `fecha` date NOT NULL,
  `horaIni` time DEFAULT NULL,
  `horaFin` time DEFAULT NULL,
  `isDiaCompleto` binary(4) DEFAULT NULL,
  `descripcion` varchar(200) NOT NULL,
  PRIMARY KEY (`idEvento`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Eventos especiales del colegio';

-- Volcando datos para la tabla colsan.eventos: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `eventos` DISABLE KEYS */;
/*!40000 ALTER TABLE `eventos` ENABLE KEYS */;

-- Volcando estructura para tabla colsan.eventroprofesor
DROP TABLE IF EXISTS `eventroprofesor`;
CREATE TABLE IF NOT EXISTS `eventroprofesor` (
  `idEvento` int(11) NOT NULL,
  `idProfesor` int(11) NOT NULL,
  PRIMARY KEY (`idEvento`,`idProfesor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Profesores a los que aplica un determinado evento';

-- Volcando datos para la tabla colsan.eventroprofesor: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `eventroprofesor` DISABLE KEYS */;
/*!40000 ALTER TABLE `eventroprofesor` ENABLE KEYS */;

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
  `motivo` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`idFichaje`)
) ENGINE=InnoDB AUTO_INCREMENT=245 DEFAULT CHARSET=utf8 COMMENT='Aquí se guardaran los fichajes de todos los profesores, entradas y salidas con sus horas correspondientes';

-- Volcando datos para la tabla colsan.fichajes: ~36 rows (aproximadamente)
/*!40000 ALTER TABLE `fichajes` DISABLE KEYS */;
INSERT IGNORE INTO `fichajes` (`idFichaje`, `currentTime`, `fecha`, `hora`, `idProfesor`, `terminal`, `dentro`, `curso`, `motivo`) VALUES
	(192, 1571040715463, '2019-10-14', '10:11:55', 313, 2, _binary 0x7472756500000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000, '2019-2020', NULL),
	(194, 1571040749479, '2019-10-14', '10:12:29', 314, 2, _binary 0x66616C7365000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000, '2019-2020', NULL),
	(196, 1571040751415, '2019-10-14', '10:12:31', 314, 2, _binary 0x7472756500000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000, '2019-2020', NULL),
	(201, 1571040755175, '2019-10-14', '10:12:35', 314, 2, _binary 0x66616C7365000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000, '2019-2020', NULL),
	(202, 1571040756231, '2019-10-14', '10:12:36', 314, 2, _binary 0x7472756500000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000, '2019-2020', NULL),
	(203, 1571040756935, '2019-10-14', '10:12:36', 313, 2, _binary 0x66616C7365000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000, '2019-2020', NULL),
	(204, 1571040757735, '2019-10-14', '10:12:37', 313, 2, _binary 0x7472756500000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000, '2019-2020', NULL),
	(205, 1571040758439, '2019-10-14', '10:12:38', 314, 2, _binary 0x66616C7365000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000, '2019-2020', NULL),
	(206, 1571040759095, '2019-10-14', '10:12:39', 313, 2, _binary 0x66616C7365000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000, '2019-2020', NULL),
	(208, 1571040760391, '2019-10-14', '10:12:40', 314, 2, _binary 0x7472756500000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000, '2019-2020', NULL),
	(210, 1571040761703, '2019-10-14', '10:12:41', 313, 2, _binary 0x7472756500000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000, '2019-2020', NULL),
	(211, 1571040762342, '2019-10-14', '10:12:42', 314, 2, _binary 0x66616C7365000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000, '2019-2020', NULL),
	(213, 1571040763575, '2019-10-14', '10:12:43', 313, 2, _binary 0x66616C7365000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000, '2019-2020', NULL),
	(214, 1571040764247, '2019-10-14', '10:12:44', 314, 2, _binary 0x7472756500000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000, '2019-2020', NULL),
	(216, 1571040765702, '2019-10-14', '10:12:45', 313, 2, _binary 0x7472756500000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000, '2019-2020', NULL),
	(218, 1571040766999, '2019-10-14', '10:12:47', 313, 2, _binary 0x66616C7365000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000, '2019-2020', NULL),
	(219, 1571040767719, '2019-10-14', '10:12:47', 313, 2, _binary 0x7472756500000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000, '2019-2020', NULL),
	(223, 1571042785655, '2019-10-14', '10:46:25', 314, 2, _binary 0x66616C7365000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000, '2019-2020', NULL),
	(224, 1571042790423, '2019-10-14', '10:46:30', 314, 2, _binary 0x7472756500000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000, '2019-2020', NULL),
	(227, 1571042821991, '2019-10-14', '10:47:01', 314, 2, _binary 0x66616C7365000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000, '2019-2020', NULL),
	(228, 1571042848839, '2019-10-14', '10:47:28', 314, 2, _binary 0x7472756500000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000, '2019-2020', NULL),
	(229, 1571042851319, '2019-10-14', '10:47:31', 314, 2, _binary 0x66616C7365000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000, '2019-2020', NULL),
	(230, 1571040699452, '2019-10-13', '08:50:57', 314, 2, _binary 0x7472756500000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000, '2019-2020', NULL),
	(231, 1571040699452, '2019-10-13', '08:53:26', 315, 1, _binary 0x7472756500000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000, '2019-2020', NULL),
	(232, 1571043246199, '2019-10-14', '10:54:06', 313, 2, _binary 0x66616C7365000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000, '2019-2020', NULL),
	(233, 1571043247959, '2019-10-14', '10:54:07', 314, 2, _binary 0x7472756500000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000, '2019-2020', NULL),
	(234, 1571043249751, '2019-10-14', '10:54:09', 315, 2, _binary 0x7472756500000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000, '2019-2020', NULL),
	(235, 1, '2019-10-13', '14:00:00', 315, 1, _binary 0x66616C7365000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000, '2019-2020', NULL),
	(237, 0, '2019-10-21', '09:04:00', 301, 0, _binary 0x7472756500000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000, '2019-2020', 'prueba'),
	(238, 0, '2019-10-21', '14:10:00', 301, 0, _binary 0x66616C7365000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000, '2019-2020', 'Salida'),
	(239, 0, '2019-10-17', '08:00:00', 301, 0, _binary 0x7472756500000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000, '2019-2020', 'prueba'),
	(240, 0, '2019-10-13', '09:00:00', 314, 0, _binary 0x66616C7365000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000, '2019-2020', 'aaa'),
	(241, 0, '2019-10-14', '09:00:00', 314, 0, _binary 0x7472756500000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000, '2019-2020', 'aaa'),
	(242, 0, '2019-10-17', '10:00:00', 301, 0, _binary 0x66616C7365000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000, '2019-2020', 'aaaa'),
	(243, 0, '2019-10-21', '19:00:00', 301, 0, _binary 0x7472756500000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000, '2019-2020', 'aaa'),
	(244, 0, '2019-10-22', '10:00:00', 301, 0, _binary 0x7472756500000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000, '2019-2020', 'Prubas');
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
) ENGINE=InnoDB AUTO_INCREMENT=9497 DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla colsan.horarios: ~347 rows (aproximadamente)
/*!40000 ALTER TABLE `horarios` DISABLE KEYS */;
INSERT IGNORE INTO `horarios` (`idFicha`, `horaIni`, `horaFin`, `idProfesor`, `dia`, `tipoHora`, `curso`) VALUES
	(9138, '12:15:00', '13:10:00', 310, 'V', NULL, '2019-2020'),
	(9139, '12:15:00', '13:10:00', 310, 'L', NULL, '2019-2020'),
	(9140, '09:55:00', '10:50:00', 310, 'J', NULL, '2019-2020'),
	(9141, '13:10:00', '14:05:00', 301, 'X', NULL, '2019-2020'),
	(9142, '12:15:00', '13:10:00', 301, 'J', NULL, '2019-2020'),
	(9143, '13:10:00', '14:05:00', 301, 'V', NULL, '2019-2020'),
	(9144, '09:55:00', '10:50:00', 301, 'M', NULL, '2019-2020'),
	(9145, '12:15:00', '13:10:00', 303, 'J', NULL, '2019-2020'),
	(9146, '09:55:00', '10:50:00', 303, 'L', NULL, '2019-2020'),
	(9147, '13:10:00', '14:05:00', 303, 'L', NULL, '2019-2020'),
	(9148, '09:00:00', '09:55:00', 303, 'M', NULL, '2019-2020'),
	(9149, '12:15:00', '13:10:00', 303, 'X', NULL, '2019-2020'),
	(9150, '09:55:00', '10:50:00', 303, 'J', NULL, '2019-2020'),
	(9151, '09:55:00', '10:50:00', 304, 'V', NULL, '2019-2020'),
	(9152, '13:10:00', '14:05:00', 304, 'M', NULL, '2019-2020'),
	(9153, '10:50:00', '11:45:00', 304, 'L', NULL, '2019-2020'),
	(9154, '10:50:00', '11:45:00', 304, 'M', NULL, '2019-2020'),
	(9155, '10:50:00', '11:45:00', 304, 'X', NULL, '2019-2020'),
	(9156, '13:10:00', '14:05:00', 304, 'J', NULL, '2019-2020'),
	(9157, '10:50:00', '11:45:00', 304, 'V', NULL, '2019-2020'),
	(9158, '09:55:00', '10:50:00', 304, 'L', NULL, '2019-2020'),
	(9159, '10:50:00', '11:45:00', 305, 'J', NULL, '2019-2020'),
	(9160, '09:55:00', '10:50:00', 305, 'V', NULL, '2019-2020'),
	(9161, '13:10:00', '14:05:00', 305, 'M', NULL, '2019-2020'),
	(9162, '10:50:00', '11:45:00', 305, 'L', NULL, '2019-2020'),
	(9163, '09:55:00', '10:50:00', 305, 'M', NULL, '2019-2020'),
	(9164, '12:15:00', '13:10:00', 305, 'X', NULL, '2019-2020'),
	(9165, '09:00:00', '09:55:00', 307, 'L', NULL, '2019-2020'),
	(9166, '09:00:00', '09:55:00', 307, 'J', NULL, '2019-2020'),
	(9167, '10:50:00', '11:45:00', 307, 'M', NULL, '2019-2020'),
	(9168, '12:15:00', '13:10:00', 307, 'V', NULL, '2019-2020'),
	(9169, '10:50:00', '11:45:00', 310, 'L', NULL, '2019-2020'),
	(9170, '12:15:00', '13:10:00', 310, 'X', NULL, '2019-2020'),
	(9171, '09:55:00', '10:50:00', 310, 'M', NULL, '2019-2020'),
	(9172, '10:50:00', '11:45:00', 310, 'V', NULL, '2019-2020'),
	(9173, '13:10:00', '14:05:00', 310, 'J', NULL, '2019-2020'),
	(9174, '10:50:00', '11:45:00', 310, 'X', NULL, '2019-2020'),
	(9175, '10:50:00', '11:45:00', 310, 'M', NULL, '2019-2020'),
	(9176, '10:50:00', '11:45:00', 310, 'J', NULL, '2019-2020'),
	(9177, '13:10:00', '14:05:00', 310, 'L', NULL, '2019-2020'),
	(9178, '13:10:00', '14:05:00', 310, 'V', NULL, '2019-2020'),
	(9179, '12:15:00', '13:10:00', 311, 'V', NULL, '2019-2020'),
	(9180, '12:15:00', '13:10:00', 310, 'J', NULL, '2019-2020'),
	(9181, '12:15:00', '13:10:00', 310, 'M', NULL, '2019-2020'),
	(9182, '09:00:00', '09:55:00', 314, 'X', NULL, '2019-2020'),
	(9183, '09:00:00', '09:55:00', 314, 'V', NULL, '2019-2020'),
	(9184, '13:10:00', '14:05:00', 314, 'J', NULL, '2019-2020'),
	(9185, '10:50:00', '11:45:00', 314, 'M', NULL, '2019-2020'),
	(9186, '09:55:00', '10:50:00', 314, 'L', NULL, '2019-2020'),
	(9187, '12:15:00', '13:10:00', 315, 'V', NULL, '2019-2020'),
	(9188, '09:55:00', '10:50:00', 315, 'M', NULL, '2019-2020'),
	(9189, '09:55:00', '10:50:00', 315, 'X', NULL, '2019-2020'),
	(9190, '09:00:00', '09:55:00', 315, 'M', NULL, '2019-2020'),
	(9191, '13:10:00', '14:05:00', 306, 'X', NULL, '2019-2020'),
	(9192, '13:10:00', '14:05:00', 306, 'M', NULL, '2019-2020'),
	(9193, '13:10:00', '14:05:00', 306, 'V', NULL, '2019-2020'),
	(9194, '10:50:00', '11:45:00', 306, 'V', NULL, '2019-2020'),
	(9195, '09:55:00', '10:50:00', 306, 'J', NULL, '2019-2020'),
	(9196, '12:15:00', '13:10:00', 315, 'L', NULL, '2019-2020'),
	(9197, '13:10:00', '14:05:00', 315, 'L', NULL, '2019-2020'),
	(9198, '09:00:00', '09:55:00', 310, 'V', NULL, '2019-2020'),
	(9199, '09:00:00', '09:55:00', 310, 'X', NULL, '2019-2020'),
	(9200, '09:00:00', '09:55:00', 310, 'J', NULL, '2019-2020'),
	(9201, '09:00:00', '09:55:00', 311, 'J', NULL, '2019-2020'),
	(9202, '13:10:00', '14:05:00', 311, 'M', NULL, '2019-2020'),
	(9203, '13:10:00', '14:05:00', 311, 'X', NULL, '2019-2020'),
	(9204, '13:10:00', '14:05:00', 311, 'V', NULL, '2019-2020'),
	(9205, '09:00:00', '09:55:00', 311, 'M', NULL, '2019-2020'),
	(9206, '09:55:00', '10:50:00', 311, 'X', NULL, '2019-2020'),
	(9207, '09:00:00', '09:55:00', 307, 'X', NULL, '2019-2020'),
	(9208, '09:00:00', '09:55:00', 307, 'V', NULL, '2019-2020'),
	(9209, '09:00:00', '09:55:00', 307, 'M', NULL, '2019-2020'),
	(9210, '09:55:00', '10:50:00', 310, 'V', NULL, '2019-2020'),
	(9211, '09:55:00', '10:50:00', 310, 'X', NULL, '2019-2020'),
	(9212, '13:10:00', '14:05:00', 310, 'M', NULL, '2019-2020'),
	(9213, '09:55:00', '10:50:00', 316, 'M', NULL, '2019-2020'),
	(9214, '09:55:00', '10:50:00', 316, 'L', NULL, '2019-2020'),
	(9215, '10:50:00', '11:45:00', 316, 'V', NULL, '2019-2020'),
	(9216, '12:15:00', '13:10:00', 303, 'M', NULL, '2019-2020'),
	(9217, '10:50:00', '11:45:00', 303, 'X', NULL, '2019-2020'),
	(9218, '13:10:00', '14:05:00', 303, 'V', NULL, '2019-2020'),
	(9219, '13:10:00', '14:05:00', 303, 'J', NULL, '2019-2020'),
	(9220, '12:15:00', '13:10:00', 303, 'L', NULL, '2019-2020'),
	(9221, '10:50:00', '11:45:00', 303, 'J', NULL, '2019-2020'),
	(9222, '13:10:00', '14:05:00', 303, 'X', NULL, '2019-2020'),
	(9223, '09:00:00', '09:55:00', 303, 'V', NULL, '2019-2020'),
	(9224, '12:15:00', '13:10:00', 311, 'J', NULL, '2019-2020'),
	(9225, '12:15:00', '13:10:00', 311, 'X', NULL, '2019-2020'),
	(9226, '09:00:00', '09:55:00', 311, 'L', NULL, '2019-2020'),
	(9227, '13:10:00', '14:05:00', 310, 'X', NULL, '2019-2020'),
	(9228, '09:00:00', '09:55:00', 310, 'L', NULL, '2019-2020'),
	(9229, '09:00:00', '09:55:00', 310, 'M', NULL, '2019-2020'),
	(9230, '09:00:00', '09:55:00', 312, 'J', NULL, '2019-2020'),
	(9231, '10:50:00', '11:45:00', 312, 'V', NULL, '2019-2020'),
	(9232, '09:55:00', '10:50:00', 312, 'L', NULL, '2019-2020'),
	(9233, '12:15:00', '13:10:00', 312, 'M', NULL, '2019-2020'),
	(9234, '12:15:00', '13:10:00', 312, 'J', NULL, '2019-2020'),
	(9235, '12:15:00', '13:10:00', 312, 'X', NULL, '2019-2020'),
	(9236, '10:50:00', '11:45:00', 316, 'L', NULL, '2019-2020'),
	(9237, '10:50:00', '11:45:00', 316, 'M', NULL, '2019-2020'),
	(9238, '13:10:00', '14:05:00', 316, 'J', NULL, '2019-2020'),
	(9239, '13:10:00', '14:05:00', 307, 'X', NULL, '2019-2020'),
	(9240, '13:10:00', '14:05:00', 307, 'V', NULL, '2019-2020'),
	(9241, '12:15:00', '13:10:00', 307, 'L', NULL, '2019-2020'),
	(9242, '09:00:00', '09:55:00', 316, 'M', NULL, '2019-2020'),
	(9243, '12:15:00', '13:10:00', 316, 'J', NULL, '2019-2020'),
	(9244, '09:00:00', '09:55:00', 316, 'V', NULL, '2019-2020'),
	(9245, '09:00:00', '09:55:00', 316, 'X', NULL, '2019-2020'),
	(9246, '09:00:00', '09:55:00', 316, 'L', NULL, '2019-2020'),
	(9247, '09:00:00', '09:55:00', 316, 'J', NULL, '2019-2020'),
	(9248, '13:10:00', '14:05:00', 307, 'M', NULL, '2019-2020'),
	(9249, '10:50:00', '11:45:00', 307, 'X', NULL, '2019-2020'),
	(9250, '10:50:00', '11:45:00', 307, 'V', NULL, '2019-2020'),
	(9251, '10:50:00', '11:45:00', 303, 'L', NULL, '2019-2020'),
	(9252, '09:55:00', '10:50:00', 303, 'M', NULL, '2019-2020'),
	(9253, '09:55:00', '10:50:00', 312, 'X', NULL, '2019-2020'),
	(9254, '12:15:00', '13:10:00', 312, 'L', NULL, '2019-2020'),
	(9255, '13:10:00', '14:05:00', 312, 'V', NULL, '2019-2020'),
	(9256, '09:55:00', '10:50:00', 310, 'L', NULL, '2019-2020'),
	(9257, '09:55:00', '10:50:00', 303, 'X', NULL, '2019-2020'),
	(9258, '10:50:00', '11:45:00', 303, 'M', NULL, '2019-2020'),
	(9259, '10:50:00', '11:45:00', 301, 'J', NULL, '2019-2020'),
	(9260, '09:55:00', '10:50:00', 307, 'M', NULL, '2019-2020'),
	(9261, '09:55:00', '10:50:00', 312, 'M', NULL, '2019-2020'),
	(9262, '09:55:00', '10:50:00', 311, 'M', NULL, '2019-2020'),
	(9263, '10:50:00', '11:45:00', 307, 'J', NULL, '2019-2020'),
	(9264, '10:50:00', '11:45:00', 312, 'J', NULL, '2019-2020'),
	(9265, '10:50:00', '11:45:00', 311, 'J', NULL, '2019-2020'),
	(9266, '10:50:00', '11:45:00', 307, 'L', NULL, '2019-2020'),
	(9267, '10:50:00', '11:45:00', 312, 'L', NULL, '2019-2020'),
	(9268, '10:50:00', '11:45:00', 311, 'L', NULL, '2019-2020'),
	(9269, '09:55:00', '10:50:00', 311, 'L', NULL, '2019-2020'),
	(9270, '12:15:00', '13:10:00', 305, 'V', NULL, '2019-2020'),
	(9271, '10:50:00', '11:45:00', 316, 'X', NULL, '2019-2020'),
	(9272, '10:50:00', '11:45:00', 316, 'J', NULL, '2019-2020'),
	(9273, '12:15:00', '13:10:00', 316, 'M', NULL, '2019-2020'),
	(9274, '09:55:00', '10:50:00', 305, 'J', NULL, '2019-2020'),
	(9275, '13:10:00', '14:05:00', 305, 'L', NULL, '2019-2020'),
	(9276, '13:10:00', '14:05:00', 305, 'X', NULL, '2019-2020'),
	(9277, '09:55:00', '10:50:00', 302, 'X', NULL, '2019-2020'),
	(9278, '10:50:00', '11:45:00', 302, 'L', NULL, '2019-2020'),
	(9279, '12:15:00', '13:10:00', 302, 'J', NULL, '2019-2020'),
	(9280, '12:15:00', '13:10:00', 302, 'V', NULL, '2019-2020'),
	(9281, '12:15:00', '13:10:00', 302, 'M', NULL, '2019-2020'),
	(9282, '09:00:00', '09:55:00', 304, 'M', NULL, '2019-2020'),
	(9283, '09:00:00', '09:55:00', 304, 'V', NULL, '2019-2020'),
	(9284, '09:00:00', '09:55:00', 304, 'X', NULL, '2019-2020'),
	(9285, '12:15:00', '13:10:00', 304, 'J', NULL, '2019-2020'),
	(9286, '09:00:00', '09:55:00', 304, 'L', NULL, '2019-2020'),
	(9287, '12:15:00', '13:10:00', 304, 'M', NULL, '2019-2020'),
	(9288, '13:10:00', '14:05:00', 304, 'V', NULL, '2019-2020'),
	(9289, '13:10:00', '14:05:00', 304, 'L', NULL, '2019-2020'),
	(9290, '13:10:00', '14:05:00', 316, 'L', NULL, '2019-2020'),
	(9291, '13:10:00', '14:05:00', 304, 'X', NULL, '2019-2020'),
	(9292, '09:55:00', '10:50:00', 304, 'J', NULL, '2019-2020'),
	(9293, '09:00:00', '09:55:00', 314, 'L', NULL, '2019-2020'),
	(9294, '09:00:00', '09:55:00', 314, 'M', NULL, '2019-2020'),
	(9295, '10:50:00', '11:45:00', 314, 'X', NULL, '2019-2020'),
	(9296, '09:55:00', '10:50:00', 314, 'V', NULL, '2019-2020'),
	(9297, '09:00:00', '09:55:00', 314, 'J', NULL, '2019-2020'),
	(9298, '09:00:00', '09:55:00', 305, 'V', NULL, '2019-2020'),
	(9299, '09:00:00', '09:55:00', 301, 'V', NULL, '2019-2020'),
	(9300, '09:00:00', '09:55:00', 302, 'V', NULL, '2019-2020'),
	(9301, '09:55:00', '10:50:00', 305, 'L', NULL, '2019-2020'),
	(9302, '09:55:00', '10:50:00', 301, 'L', NULL, '2019-2020'),
	(9303, '09:55:00', '10:50:00', 302, 'L', NULL, '2019-2020'),
	(9304, '10:50:00', '11:45:00', 305, 'M', NULL, '2019-2020'),
	(9305, '10:50:00', '11:45:00', 301, 'M', NULL, '2019-2020'),
	(9306, '10:50:00', '11:45:00', 302, 'M', NULL, '2019-2020'),
	(9307, '13:10:00', '14:05:00', 305, 'J', NULL, '2019-2020'),
	(9308, '13:10:00', '14:05:00', 301, 'J', NULL, '2019-2020'),
	(9309, '13:10:00', '14:05:00', 302, 'J', NULL, '2019-2020'),
	(9310, '10:50:00', '11:45:00', 305, 'X', NULL, '2019-2020'),
	(9311, '10:50:00', '11:45:00', 301, 'X', NULL, '2019-2020'),
	(9312, '10:50:00', '11:45:00', 302, 'X', NULL, '2019-2020'),
	(9313, '09:00:00', '09:55:00', 305, 'J', NULL, '2019-2020'),
	(9314, '09:00:00', '09:55:00', 301, 'J', NULL, '2019-2020'),
	(9315, '09:00:00', '09:55:00', 302, 'J', NULL, '2019-2020'),
	(9316, '09:00:00', '09:55:00', 305, 'M', NULL, '2019-2020'),
	(9317, '09:00:00', '09:55:00', 301, 'M', NULL, '2019-2020'),
	(9318, '09:00:00', '09:55:00', 302, 'M', NULL, '2019-2020'),
	(9319, '09:00:00', '09:55:00', 305, 'X', NULL, '2019-2020'),
	(9320, '09:00:00', '09:55:00', 301, 'X', NULL, '2019-2020'),
	(9321, '09:00:00', '09:55:00', 302, 'X', NULL, '2019-2020'),
	(9322, '09:00:00', '09:55:00', 305, 'L', NULL, '2019-2020'),
	(9323, '09:00:00', '09:55:00', 301, 'L', NULL, '2019-2020'),
	(9324, '09:00:00', '09:55:00', 302, 'L', NULL, '2019-2020'),
	(9325, '10:50:00', '11:45:00', 305, 'V', NULL, '2019-2020'),
	(9326, '10:50:00', '11:45:00', 301, 'V', NULL, '2019-2020'),
	(9327, '10:50:00', '11:45:00', 302, 'V', NULL, '2019-2020'),
	(9328, '12:15:00', '13:10:00', 304, 'V', NULL, '2019-2020'),
	(9329, '12:15:00', '13:10:00', 316, 'V', NULL, '2019-2020'),
	(9330, '12:15:00', '13:10:00', 304, 'X', NULL, '2019-2020'),
	(9331, '12:15:00', '13:10:00', 316, 'X', NULL, '2019-2020'),
	(9332, '10:50:00', '11:45:00', 304, 'J', NULL, '2019-2020'),
	(9333, '12:15:00', '13:10:00', 304, 'L', NULL, '2019-2020'),
	(9334, '09:55:00', '10:50:00', 304, 'M', NULL, '2019-2020'),
	(9335, '09:00:00', '09:55:00', 304, 'J', NULL, '2019-2020'),
	(9336, '09:55:00', '10:50:00', 304, 'X', NULL, '2019-2020'),
	(9337, '09:55:00', '10:50:00', 316, 'X', NULL, '2019-2020'),
	(9338, '10:50:00', '11:45:00', 311, 'V', NULL, '2019-2020'),
	(9339, '09:00:00', '09:55:00', 311, 'X', NULL, '2019-2020'),
	(9340, '09:55:00', '10:50:00', 311, 'J', NULL, '2019-2020'),
	(9341, '12:15:00', '13:10:00', 311, 'L', NULL, '2019-2020'),
	(9342, '12:15:00', '13:10:00', 311, 'M', NULL, '2019-2020'),
	(9343, '09:00:00', '09:55:00', 312, 'L', NULL, '2019-2020'),
	(9344, '09:00:00', '09:55:00', 303, 'L', NULL, '2019-2020'),
	(9345, '12:15:00', '13:10:00', 312, 'V', NULL, '2019-2020'),
	(9346, '12:15:00', '13:10:00', 303, 'V', NULL, '2019-2020'),
	(9347, '09:00:00', '09:55:00', 312, 'X', NULL, '2019-2020'),
	(9348, '09:00:00', '09:55:00', 303, 'X', NULL, '2019-2020'),
	(9349, '12:15:00', '13:10:00', 305, 'L', NULL, '2019-2020'),
	(9350, '13:10:00', '14:05:00', 305, 'V', NULL, '2019-2020'),
	(9351, '13:10:00', '14:05:00', 311, 'J', NULL, '2019-2020'),
	(9352, '13:10:00', '14:05:00', 312, 'J', NULL, '2019-2020'),
	(9353, '09:55:00', '10:50:00', 311, 'V', NULL, '2019-2020'),
	(9354, '09:55:00', '10:50:00', 312, 'V', NULL, '2019-2020'),
	(9355, '10:50:00', '11:45:00', 311, 'M', NULL, '2019-2020'),
	(9356, '10:50:00', '11:45:00', 312, 'M', NULL, '2019-2020'),
	(9357, '09:55:00', '10:50:00', 307, 'L', NULL, '2019-2020'),
	(9358, '12:15:00', '13:10:00', 307, 'X', NULL, '2019-2020'),
	(9359, '13:10:00', '14:05:00', 307, 'J', NULL, '2019-2020'),
	(9360, '12:15:00', '13:10:00', 307, 'J', NULL, '2019-2020'),
	(9361, '12:15:00', '13:10:00', 305, 'J', NULL, '2019-2020'),
	(9362, '12:15:00', '13:10:00', 307, 'M', NULL, '2019-2020'),
	(9363, '12:15:00', '13:10:00', 305, 'M', NULL, '2019-2020'),
	(9364, '09:55:00', '10:50:00', 307, 'X', NULL, '2019-2020'),
	(9365, '09:55:00', '10:50:00', 305, 'X', NULL, '2019-2020'),
	(9366, '09:55:00', '10:50:00', 312, 'J', NULL, '2019-2020'),
	(9367, '13:10:00', '14:05:00', 312, 'X', NULL, '2019-2020'),
	(9368, '13:10:00', '14:05:00', 312, 'M', NULL, '2019-2020'),
	(9369, '09:55:00', '10:50:00', 307, 'J', NULL, '2019-2020'),
	(9370, '13:10:00', '14:05:00', 307, 'L', NULL, '2019-2020'),
	(9371, '09:55:00', '10:50:00', 307, 'V', NULL, '2019-2020'),
	(9372, '13:10:00', '14:05:00', 312, 'L', NULL, '2019-2020'),
	(9373, '13:10:00', '14:05:00', 311, 'L', NULL, '2019-2020'),
	(9374, '09:00:00', '09:55:00', 312, 'V', NULL, '2019-2020'),
	(9375, '09:00:00', '09:55:00', 311, 'V', NULL, '2019-2020'),
	(9376, '10:50:00', '11:45:00', 312, 'X', NULL, '2019-2020'),
	(9377, '10:50:00', '11:45:00', 311, 'X', NULL, '2019-2020'),
	(9378, '12:15:00', '13:10:00', 301, 'X', NULL, '2019-2020'),
	(9379, '12:15:00', '13:10:00', 301, 'M', NULL, '2019-2020'),
	(9380, '09:55:00', '10:50:00', 301, 'V', NULL, '2019-2020'),
	(9381, '13:10:00', '14:05:00', 301, 'L', NULL, '2019-2020'),
	(9382, '09:55:00', '10:50:00', 301, 'J', NULL, '2019-2020'),
	(9383, '09:55:00', '10:50:00', 318, 'V', NULL, '2019-2020'),
	(9384, '13:10:00', '14:05:00', 318, 'L', NULL, '2019-2020'),
	(9385, '10:50:00', '11:45:00', 318, 'J', NULL, '2019-2020'),
	(9386, '12:15:00', '13:10:00', 318, 'X', NULL, '2019-2020'),
	(9387, '10:50:00', '11:45:00', 318, 'L', NULL, '2019-2020'),
	(9388, '10:50:00', '11:45:00', 318, 'V', NULL, '2019-2020'),
	(9389, '12:15:00', '13:10:00', 318, 'J', NULL, '2019-2020'),
	(9390, '13:10:00', '14:05:00', 318, 'M', NULL, '2019-2020'),
	(9391, '12:15:00', '13:10:00', 318, 'V', NULL, '2019-2020'),
	(9392, '09:00:00', '09:55:00', 318, 'X', NULL, '2019-2020'),
	(9393, '09:00:00', '09:55:00', 318, 'L', NULL, '2019-2020'),
	(9394, '10:50:00', '11:45:00', 318, 'X', NULL, '2019-2020'),
	(9395, '09:00:00', '09:55:00', 306, 'J', NULL, '2019-2020'),
	(9396, '09:00:00', '09:55:00', 306, 'V', NULL, '2019-2020'),
	(9397, '09:00:00', '09:55:00', 309, 'M', NULL, '2019-2020'),
	(9398, '10:50:00', '11:45:00', 309, 'L', NULL, '2019-2020'),
	(9399, '09:00:00', '09:55:00', 309, 'X', NULL, '2019-2020'),
	(9400, '09:00:00', '09:55:00', 309, 'J', NULL, '2019-2020'),
	(9401, '10:50:00', '11:45:00', 309, 'V', NULL, '2019-2020'),
	(9402, '09:00:00', '09:55:00', 309, 'L', NULL, '2019-2020'),
	(9403, '10:50:00', '11:45:00', 309, 'J', NULL, '2019-2020'),
	(9404, '09:55:00', '10:50:00', 309, 'M', NULL, '2019-2020'),
	(9405, '10:50:00', '11:45:00', 309, 'X', NULL, '2019-2020'),
	(9406, '09:00:00', '09:55:00', 309, 'V', NULL, '2019-2020'),
	(9407, '09:55:00', '10:50:00', 309, 'L', NULL, '2019-2020'),
	(9408, '09:55:00', '10:50:00', 309, 'X', NULL, '2019-2020'),
	(9409, '12:15:00', '13:10:00', 309, 'J', NULL, '2019-2020'),
	(9410, '12:15:00', '13:10:00', 309, 'M', NULL, '2019-2020'),
	(9411, '12:15:00', '13:10:00', 309, 'V', NULL, '2019-2020'),
	(9412, '13:10:00', '14:05:00', 302, 'X', NULL, '2019-2020'),
	(9413, '13:10:00', '14:05:00', 316, 'X', NULL, '2019-2020'),
	(9414, '13:10:00', '14:05:00', 309, 'X', NULL, '2019-2020'),
	(9415, '13:10:00', '14:05:00', 302, 'M', NULL, '2019-2020'),
	(9416, '13:10:00', '14:05:00', 316, 'M', NULL, '2019-2020'),
	(9417, '13:10:00', '14:05:00', 309, 'M', NULL, '2019-2020'),
	(9418, '12:15:00', '13:10:00', 302, 'L', NULL, '2019-2020'),
	(9419, '12:15:00', '13:10:00', 316, 'L', NULL, '2019-2020'),
	(9420, '12:15:00', '13:10:00', 309, 'L', NULL, '2019-2020'),
	(9421, '13:10:00', '14:05:00', 302, 'V', NULL, '2019-2020'),
	(9422, '13:10:00', '14:05:00', 316, 'V', NULL, '2019-2020'),
	(9423, '13:10:00', '14:05:00', 309, 'V', NULL, '2019-2020'),
	(9424, '09:55:00', '10:50:00', 302, 'J', NULL, '2019-2020'),
	(9425, '09:55:00', '10:50:00', 316, 'J', NULL, '2019-2020'),
	(9426, '09:55:00', '10:50:00', 309, 'J', NULL, '2019-2020'),
	(9427, '12:15:00', '13:10:00', 301, 'L', NULL, '2019-2020'),
	(9428, '13:10:00', '14:05:00', 301, 'M', NULL, '2019-2020'),
	(9429, '12:15:00', '13:10:00', 301, 'V', NULL, '2019-2020'),
	(9430, '09:55:00', '10:50:00', 301, 'X', NULL, '2019-2020'),
	(9431, '09:55:00', '10:50:00', 302, 'V', NULL, '2019-2020'),
	(9432, '13:10:00', '14:05:00', 302, 'L', NULL, '2019-2020'),
	(9433, '09:55:00', '10:50:00', 302, 'M', NULL, '2019-2020'),
	(9434, '12:15:00', '13:10:00', 302, 'X', NULL, '2019-2020'),
	(9435, '10:50:00', '11:45:00', 302, 'J', NULL, '2019-2020'),
	(9436, '10:50:00', '11:45:00', 301, 'L', NULL, '2019-2020'),
	(9437, '10:50:00', '11:45:00', 306, 'M', NULL, '2019-2020'),
	(9438, '09:55:00', '10:50:00', 306, 'X', NULL, '2019-2020'),
	(9439, '09:55:00', '10:50:00', 306, 'V', NULL, '2019-2020'),
	(9440, '12:15:00', '13:10:00', 306, 'M', NULL, '2019-2020'),
	(9441, '13:10:00', '14:05:00', 306, 'J', NULL, '2019-2020'),
	(9442, '12:15:00', '13:10:00', 309, 'X', NULL, '2019-2020'),
	(9443, '09:55:00', '10:50:00', 309, 'V', NULL, '2019-2020'),
	(9444, '13:10:00', '14:05:00', 309, 'L', NULL, '2019-2020'),
	(9445, '13:10:00', '14:05:00', 309, 'J', NULL, '2019-2020'),
	(9446, '10:50:00', '11:45:00', 309, 'M', NULL, '2019-2020'),
	(9447, '10:50:00', '11:45:00', 317, 'M', NULL, '2019-2020'),
	(9448, '10:50:00', '11:45:00', 313, 'M', NULL, '2019-2020'),
	(9449, '13:10:00', '14:05:00', 317, 'J', NULL, '2019-2020'),
	(9450, '13:10:00', '14:05:00', 313, 'J', NULL, '2019-2020'),
	(9451, '09:55:00', '10:50:00', 317, 'V', NULL, '2019-2020'),
	(9452, '09:55:00', '10:50:00', 313, 'V', NULL, '2019-2020'),
	(9453, '09:55:00', '10:50:00', 317, 'L', NULL, '2019-2020'),
	(9454, '09:55:00', '10:50:00', 313, 'L', NULL, '2019-2020'),
	(9455, '10:50:00', '11:45:00', 317, 'X', NULL, '2019-2020'),
	(9456, '10:50:00', '11:45:00', 313, 'X', NULL, '2019-2020'),
	(9457, '09:00:00', '09:55:00', 317, 'L', NULL, '2019-2020'),
	(9458, '09:00:00', '09:55:00', 313, 'L', NULL, '2019-2020'),
	(9459, '09:00:00', '09:55:00', 317, 'J', NULL, '2019-2020'),
	(9460, '09:00:00', '09:55:00', 313, 'J', NULL, '2019-2020'),
	(9461, '09:00:00', '09:55:00', 317, 'M', NULL, '2019-2020'),
	(9462, '09:00:00', '09:55:00', 313, 'M', NULL, '2019-2020'),
	(9463, '09:00:00', '09:55:00', 317, 'V', NULL, '2019-2020'),
	(9464, '09:00:00', '09:55:00', 313, 'V', NULL, '2019-2020'),
	(9465, '09:00:00', '09:55:00', 317, 'X', NULL, '2019-2020'),
	(9466, '09:00:00', '09:55:00', 313, 'X', NULL, '2019-2020'),
	(9467, '13:10:00', '14:05:00', 314, 'M', NULL, '2019-2020'),
	(9468, '13:10:00', '14:05:00', 313, 'M', NULL, '2019-2020'),
	(9469, '13:10:00', '14:05:00', 314, 'X', NULL, '2019-2020'),
	(9470, '13:10:00', '14:05:00', 313, 'X', NULL, '2019-2020'),
	(9471, '12:15:00', '13:10:00', 314, 'L', NULL, '2019-2020'),
	(9472, '12:15:00', '13:10:00', 313, 'L', NULL, '2019-2020'),
	(9473, '12:15:00', '13:10:00', 314, 'V', NULL, '2019-2020'),
	(9474, '12:15:00', '13:10:00', 313, 'V', NULL, '2019-2020'),
	(9475, '12:15:00', '13:10:00', 314, 'J', NULL, '2019-2020'),
	(9476, '12:15:00', '13:10:00', 313, 'J', NULL, '2019-2020'),
	(9477, '13:10:00', '14:05:00', 314, 'V', NULL, '2019-2020'),
	(9478, '13:10:00', '14:05:00', 313, 'V', NULL, '2019-2020'),
	(9479, '12:15:00', '13:10:00', 314, 'X', NULL, '2019-2020'),
	(9480, '12:15:00', '13:10:00', 313, 'X', NULL, '2019-2020'),
	(9481, '10:50:00', '11:45:00', 314, 'L', NULL, '2019-2020'),
	(9482, '10:50:00', '11:45:00', 313, 'L', NULL, '2019-2020'),
	(9483, '09:55:00', '10:50:00', 314, 'M', NULL, '2019-2020'),
	(9484, '09:55:00', '10:50:00', 313, 'M', NULL, '2019-2020'),
	(9485, '09:55:00', '10:50:00', 314, 'J', NULL, '2019-2020'),
	(9486, '09:55:00', '10:50:00', 313, 'J', NULL, '2019-2020'),
	(9487, '12:15:00', '13:10:00', 314, 'M', NULL, '2019-2020'),
	(9488, '12:15:00', '13:10:00', 313, 'M', NULL, '2019-2020'),
	(9489, '10:50:00', '11:45:00', 314, 'V', NULL, '2019-2020'),
	(9490, '10:50:00', '11:45:00', 313, 'V', NULL, '2019-2020'),
	(9491, '10:50:00', '11:45:00', 314, 'J', NULL, '2019-2020'),
	(9492, '10:50:00', '11:45:00', 313, 'J', NULL, '2019-2020'),
	(9493, '09:55:00', '10:50:00', 314, 'X', NULL, '2019-2020'),
	(9494, '09:55:00', '10:50:00', 313, 'X', NULL, '2019-2020'),
	(9495, '13:10:00', '14:05:00', 314, 'L', NULL, '2019-2020'),
	(9496, '13:10:00', '14:05:00', 313, 'L', NULL, '2019-2020');
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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='Aquí se guardaran horas que hacen los profesores sin ser de sus horarios. Cursos, cosas extra, ... en definitiva lo que no esté en el horario lectivo ni en el complementario';

-- Volcando datos para la tabla colsan.horasextra: ~2 rows (aproximadamente)
/*!40000 ALTER TABLE `horasextra` DISABLE KEYS */;
INSERT IGNORE INTO `horasextra` (`idHoraExtra`, `idProfesor`, `fecha`, `horaIni`, `horaFin`, `motivo`, `fechaAlta`, `tipoHora`, `curso`) VALUES
	(7, 313, '2019-10-14', '11:00:00', '13:00:00', 'Guardia', '2019-10-14 10:10:08', 'C', '2019-2020'),
	(8, 301, '2019-10-02', '08:00:00', '10:00:00', 'Baja', '2019-10-23 11:02:02', 'L', '2019-2020');
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
) ENGINE=InnoDB AUTO_INCREMENT=319 DEFAULT CHARSET=utf8 COMMENT='Datos de los profesores';

-- Volcando datos para la tabla colsan.profesores: ~18 rows (aproximadamente)
/*!40000 ALTER TABLE `profesores` DISABLE KEYS */;
INSERT IGNORE INTO `profesores` (`idProfesor`, `nombreCorto`, `nombre`, `apellidos`, `idTarjeta`) VALUES
	(301, 'MJOSE', 'Mª JOSÉ', '', 1),
	(302, 'RAQ', 'RAQUEL', '', NULL),
	(303, 'JONY', 'JONATHAN', '', NULL),
	(304, 'MIG', 'MIGUEL', '', NULL),
	(305, 'MAN', 'MANOLI', '', NULL),
	(306, 'YOL', 'YOLANDA', '', NULL),
	(307, 'BEAR', 'BEATRIZ R.', '', NULL),
	(308, 'MJC', 'Mº JESUS CONEJO', '', NULL),
	(309, 'SAT', 'SATUR', '', NULL),
	(310, 'CRISA', 'CRISTINA A.', '', NULL),
	(311, 'CRISS', 'CRISTINA S', '', NULL),
	(312, 'ELEF', 'ELENA FRAGUAS', '', NULL),
	(313, 'MJA', 'Mª JESÚS ARELLANO', '', 3),
	(314, 'JORM', 'JORGE MARTÍN', '', 2),
	(315, 'Rub', 'Rubén', '', 4),
	(316, 'ANAS', 'ANA SILVA', '', NULL),
	(317, 'JL', 'Jose luis', '', NULL),
	(318, 'HIM', 'HNA INMACULADA', '', NULL);
/*!40000 ALTER TABLE `profesores` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;

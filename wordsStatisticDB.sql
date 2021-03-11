-- --------------------------------------------------------
-- Хост:                         127.0.0.1
-- Версия сервера:               5.5.62 - MySQL Community Server (GPL)
-- Операционная система:         Win64
-- HeidiSQL Версия:              10.2.0.5599
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Дамп структуры базы данных wordsstatisticdb
CREATE DATABASE IF NOT EXISTS `wordsstatisticdb` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;
USE `wordsstatisticdb`;

-- Дамп структуры для таблица wordsstatisticdb.words
CREATE TABLE IF NOT EXISTS `words` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `word` varchar(50) NOT NULL,
  `count` int(11) NOT NULL,
  `url` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `words_word_uindex` (`word`)
) ENGINE=InnoDB AUTO_INCREMENT=830 DEFAULT CHARSET=utf8mb4;

-- Экспортируемые данные не выделены.

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;

-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               8.0.31 - MySQL Community Server - GPL
-- Server OS:                    Win64
-- HeidiSQL Version:             12.5.0.6677
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for db_logistic
CREATE DATABASE IF NOT EXISTS `db_logistic` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `db_logistic`;

-- Dumping structure for table db_logistic.criteria
CREATE TABLE IF NOT EXISTS `criteria` (
  `criteria_id` int NOT NULL DEFAULT (0),
  `criteria_name` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`criteria_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table db_logistic.criteria: ~7 rows (approximately)
INSERT INTO `criteria` (`criteria_id`, `criteria_name`) VALUES
	(1, 'PEMILIHAN & TUJUAN PROYEK PERBAIKAN '),
	(2, 'PENETAPAN TARGET PROYEK PERBAIKAN'),
	(3, 'ANALISA SITUASI & PENENTUAN FAKTOR PENYEBAB MASALAH UTAMA\r\n'),
	(4, 'PENGEMBANGAN & RENCANA SOLUSI PERBAIKAN'),
	(5, 'IMPLEMENTASI SOLUSI PERBAIKAN'),
	(6, 'EVALUASI HASIL PERBAIKAN'),
	(7, 'PENGENDALIAN DAN STANDARISASI');

-- Dumping structure for table db_logistic.multiple_choice
CREATE TABLE IF NOT EXISTS `multiple_choice` (
  `choice_id` int NOT NULL AUTO_INCREMENT,
  `choice_value` double DEFAULT NULL,
  `question_id` int DEFAULT NULL,
  PRIMARY KEY (`choice_id`),
  KEY `FKrmvyshef5iv5mp6m2yopn5xat` (`question_id`),
  CONSTRAINT `FKrmvyshef5iv5mp6m2yopn5xat` FOREIGN KEY (`question_id`) REFERENCES `questions` (`question_id`)
) ENGINE=InnoDB AUTO_INCREMENT=190 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table db_logistic.multiple_choice: ~164 rows (approximately)
INSERT INTO `multiple_choice` (`choice_id`, `choice_value`, `question_id`) VALUES
	(26, 1, 1),
	(27, 0.75, 1),
	(28, 0.5, 1),
	(29, 0.3, 1),
	(30, 1, 2),
	(31, 0.75, 2),
	(32, 0.5, 2),
	(33, 0.3, 2),
	(34, 1, 3),
	(35, 0.75, 3),
	(36, 0.5, 3),
	(37, 0.3, 3),
	(38, 1, 4),
	(39, 0.75, 4),
	(40, 0.5, 4),
	(41, 0.3, 4),
	(42, 1, 5),
	(43, 0.75, 5),
	(44, 0.5, 5),
	(45, 0.3, 5),
	(46, 1, 6),
	(47, 0.75, 6),
	(48, 0.5, 6),
	(49, 0.3, 6),
	(50, 1, 7),
	(51, 0.75, 7),
	(52, 0.5, 7),
	(53, 0.3, 7),
	(54, 0.75, 8),
	(55, 0.56, 8),
	(56, 0.38, 8),
	(57, 0.23, 8),
	(58, 1, 9),
	(59, 0.75, 9),
	(60, 0.5, 9),
	(61, 0.3, 9),
	(62, 1, 10),
	(63, 0.75, 10),
	(64, 0.5, 10),
	(65, 0.3, 10),
	(66, 1, 11),
	(67, 0.75, 11),
	(68, 0.5, 11),
	(69, 0.3, 11),
	(70, 2, 12),
	(71, 1.5, 12),
	(72, 1, 12),
	(73, 0.6, 12),
	(74, 2, 13),
	(75, 1.5, 13),
	(76, 1, 13),
	(77, 0.6, 13),
	(78, 2, 14),
	(79, 1.5, 14),
	(80, 1, 14),
	(81, 0.6, 14),
	(82, 1, 15),
	(83, 0.75, 15),
	(84, 0.5, 15),
	(85, 0.3, 15),
	(86, 1, 16),
	(87, 0.75, 16),
	(88, 0.5, 16),
	(89, 0.3, 16),
	(90, 1, 17),
	(91, 0.75, 17),
	(92, 0.5, 17),
	(93, 0.3, 17),
	(94, 1, 18),
	(95, 0.75, 18),
	(96, 0.5, 18),
	(97, 0.3, 18),
	(98, 2, 19),
	(99, 1.5, 19),
	(100, 1, 19),
	(101, 0.6, 19),
	(102, 1, 20),
	(103, 0.75, 20),
	(104, 0.5, 20),
	(105, 0.3, 20),
	(106, 1, 21),
	(107, 0.75, 21),
	(108, 0.5, 21),
	(109, 0.3, 21),
	(110, 1, 22),
	(111, 0.75, 22),
	(112, 0.5, 22),
	(113, 0.3, 22),
	(114, 2, 23),
	(115, 1.5, 23),
	(116, 1, 23),
	(117, 0.6, 23),
	(118, 2, 24),
	(119, 1.5, 24),
	(120, 1, 24),
	(121, 0.6, 24),
	(122, 10, 25),
	(123, 7.5, 25),
	(124, 5, 25),
	(125, 3.6, 25),
	(126, 10, 26),
	(127, 7.5, 26),
	(128, 5, 26),
	(129, 3.6, 26),
	(130, 10, 27),
	(131, 7.5, 27),
	(132, 5, 27),
	(133, 3.6, 27),
	(134, 5, 28),
	(135, 3.75, 28),
	(136, 2.5, 28),
	(137, 1.5, 28),
	(138, 5, 29),
	(139, 3.75, 29),
	(140, 2.5, 29),
	(141, 1.5, 29),
	(142, 3.33, 30),
	(143, 3.75, 30),
	(144, 1.67, 30),
	(145, 1, 30),
	(146, 3.33, 31),
	(147, 3.75, 31),
	(148, 1.67, 31),
	(149, 1, 31),
	(150, 3.33, 32),
	(151, 3.75, 32),
	(152, 1.67, 32),
	(153, 1, 32),
	(154, 10, 33),
	(155, 7.5, 33),
	(156, 5, 33),
	(157, 3.6, 33),
	(158, 1, 34),
	(159, 0.75, 34),
	(160, 0.5, 34),
	(161, 0.3, 34),
	(162, 1, 35),
	(163, 0.75, 35),
	(164, 0.5, 35),
	(165, 0.3, 35),
	(166, 1, 36),
	(167, 0.75, 36),
	(168, 0.5, 36),
	(169, 0.3, 36),
	(170, 1, 37),
	(171, 0.75, 37),
	(172, 0.5, 37),
	(173, 0.3, 37),
	(174, 1, 38),
	(175, 0.75, 38),
	(176, 0.5, 38),
	(177, 0.3, 38),
	(178, 1.5, 39),
	(179, 1.13, 39),
	(180, 0.75, 39),
	(181, 0.5, 39),
	(182, 1.5, 40),
	(183, 1.13, 40),
	(184, 0.75, 40),
	(185, 0.5, 40),
	(186, 2, 41),
	(187, 1.5, 41),
	(188, 1, 41),
	(189, 0.5, 41);

-- Dumping structure for table db_logistic.nilai
CREATE TABLE IF NOT EXISTS `nilai` (
  `nilai_id` int NOT NULL DEFAULT (0),
  `nilai` double DEFAULT NULL,
  `team_name` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `question_id` int NOT NULL,
  `timestamp` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`nilai_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table db_logistic.nilai: ~82 rows (approximately)
INSERT INTO `nilai` (`nilai_id`, `nilai`, `team_name`, `username`, `question_id`, `timestamp`) VALUES
	(996, 1, 'sugoi', 'wiz', 1, '2023-11-09 01:56:27'),
	(997, 0.3, 'sugoi', 'wiz', 2, '2023-11-09 01:56:27'),
	(998, 0.3, 'sugoi', 'wiz', 3, '2023-11-09 01:56:27'),
	(999, 0.5, 'sugoi', 'wiz', 4, '2023-11-09 01:56:27'),
	(1000, 1, 'sugoi', 'wiz', 5, '2023-11-09 01:56:27'),
	(1001, 0.3, 'sugoi', 'wiz', 6, '2023-11-09 01:56:27'),
	(1002, 0.75, 'sugoi', 'wiz', 7, '2023-11-09 01:56:27'),
	(1003, 0.38, 'sugoi', 'wiz', 8, '2023-11-09 01:56:27'),
	(1004, 1, 'sugoi', 'wiz', 9, '2023-11-09 01:56:27'),
	(1005, 1, 'sugoi', 'wiz', 10, '2023-11-09 01:56:27'),
	(1006, 1, 'sugoi', 'wiz', 11, '2023-11-09 01:56:27'),
	(1007, 1.5, 'sugoi', 'wiz', 12, '2023-11-09 01:56:27'),
	(1008, 2, 'sugoi', 'wiz', 13, '2023-11-09 01:56:27'),
	(1009, 2, 'sugoi', 'wiz', 14, '2023-11-09 01:56:27'),
	(1010, 0.3, 'sugoi', 'wiz', 15, '2023-11-09 01:56:27'),
	(1011, 0.5, 'sugoi', 'wiz', 16, '2023-11-09 01:56:27'),
	(1012, 0.75, 'sugoi', 'wiz', 17, '2023-11-09 01:56:27'),
	(1013, 0.75, 'sugoi', 'wiz', 18, '2023-11-09 01:56:27'),
	(1014, 1, 'sugoi', 'wiz', 19, '2023-11-09 01:56:27'),
	(1015, 1, 'sugoi', 'wiz', 20, '2023-11-09 01:56:27'),
	(1016, 0.5, 'sugoi', 'wiz', 21, '2023-11-09 01:56:27'),
	(1017, 0.3, 'sugoi', 'wiz', 22, '2023-11-09 01:56:27'),
	(1018, 1, 'sugoi', 'wiz', 23, '2023-11-09 01:56:27'),
	(1019, 2, 'sugoi', 'wiz', 24, '2023-11-09 01:56:27'),
	(1020, 7.5, 'sugoi', 'wiz', 25, '2023-11-09 01:56:27'),
	(1021, 7.5, 'sugoi', 'wiz', 26, '2023-11-09 01:56:27'),
	(1022, 7.5, 'sugoi', 'wiz', 27, '2023-11-09 01:56:27'),
	(1023, 2.5, 'sugoi', 'wiz', 28, '2023-11-09 01:56:27'),
	(1024, 3.75, 'sugoi', 'wiz', 29, '2023-11-09 01:56:27'),
	(1025, 3.33, 'sugoi', 'wiz', 30, '2023-11-09 01:56:27'),
	(1026, 1.67, 'sugoi', 'wiz', 31, '2023-11-09 01:56:27'),
	(1027, 1, 'sugoi', 'wiz', 32, '2023-11-09 01:56:27'),
	(1028, 10, 'sugoi', 'wiz', 33, '2023-11-09 01:56:27'),
	(1029, 0.75, 'sugoi', 'wiz', 34, '2023-11-09 01:56:27'),
	(1030, 0.3, 'sugoi', 'wiz', 35, '2023-11-09 01:56:27'),
	(1031, 0.75, 'sugoi', 'wiz', 36, '2023-11-09 01:56:27'),
	(1032, 0.3, 'sugoi', 'wiz', 37, '2023-11-09 01:56:27'),
	(1033, 1, 'sugoi', 'wiz', 38, '2023-11-09 01:56:27'),
	(1034, 1.5, 'sugoi', 'wiz', 39, '2023-11-09 01:56:27'),
	(1035, 1.13, 'sugoi', 'wiz', 40, '2023-11-09 01:56:27'),
	(1036, 0.5, 'sugoi', 'wiz', 41, '2023-11-09 01:56:27'),
	(1037, 0.75, 'baladewa', 'wiz', 1, '2023-11-09 02:11:44'),
	(1038, 0.75, 'baladewa', 'wiz', 2, '2023-11-09 02:11:44'),
	(1039, 1, 'baladewa', 'wiz', 3, '2023-11-09 02:11:44'),
	(1040, 1, 'baladewa', 'wiz', 4, '2023-11-09 02:11:44'),
	(1041, 0.5, 'baladewa', 'wiz', 5, '2023-11-09 02:11:44'),
	(1042, 0.3, 'baladewa', 'wiz', 6, '2023-11-09 02:11:44'),
	(1043, 1, 'baladewa', 'wiz', 7, '2023-11-09 02:11:44'),
	(1044, 0.23, 'baladewa', 'wiz', 8, '2023-11-09 02:11:44'),
	(1045, 0.5, 'baladewa', 'wiz', 9, '2023-11-09 02:11:44'),
	(1046, 0.3, 'baladewa', 'wiz', 10, '2023-11-09 02:11:44'),
	(1047, 1, 'baladewa', 'wiz', 11, '2023-11-09 02:11:44'),
	(1048, 2, 'baladewa', 'wiz', 12, '2023-11-09 02:11:44'),
	(1049, 0.6, 'baladewa', 'wiz', 13, '2023-11-09 02:11:44'),
	(1050, 0.6, 'baladewa', 'wiz', 14, '2023-11-09 02:11:44'),
	(1051, 0.5, 'baladewa', 'wiz', 15, '2023-11-09 02:11:44'),
	(1052, 0.3, 'baladewa', 'wiz', 16, '2023-11-09 02:11:44'),
	(1053, 1, 'baladewa', 'wiz', 17, '2023-11-09 02:11:44'),
	(1054, 0.75, 'baladewa', 'wiz', 18, '2023-11-09 02:11:44'),
	(1055, 1.5, 'baladewa', 'wiz', 19, '2023-11-09 02:11:44'),
	(1056, 1, 'baladewa', 'wiz', 20, '2023-11-09 02:11:44'),
	(1057, 0.75, 'baladewa', 'wiz', 21, '2023-11-09 02:11:44'),
	(1058, 0.5, 'baladewa', 'wiz', 22, '2023-11-09 02:11:44'),
	(1059, 1, 'baladewa', 'wiz', 23, '2023-11-09 02:11:44'),
	(1060, 2, 'baladewa', 'wiz', 24, '2023-11-09 02:11:44'),
	(1061, 5, 'baladewa', 'wiz', 25, '2023-11-09 02:11:44'),
	(1062, 3.6, 'baladewa', 'wiz', 26, '2023-11-09 02:11:44'),
	(1063, 10, 'baladewa', 'wiz', 27, '2023-11-09 02:11:44'),
	(1064, 2.5, 'baladewa', 'wiz', 28, '2023-11-09 02:11:44'),
	(1065, 2.5, 'baladewa', 'wiz', 29, '2023-11-09 02:11:44'),
	(1066, 3.33, 'baladewa', 'wiz', 30, '2023-11-09 02:11:44'),
	(1067, 1, 'baladewa', 'wiz', 31, '2023-11-09 02:11:44'),
	(1068, 3.75, 'baladewa', 'wiz', 32, '2023-11-09 02:11:44'),
	(1069, 5, 'baladewa', 'wiz', 33, '2023-11-09 02:11:44'),
	(1070, 1, 'baladewa', 'wiz', 34, '2023-11-09 02:11:44'),
	(1071, 0.5, 'baladewa', 'wiz', 35, '2023-11-09 02:11:44'),
	(1072, 1, 'baladewa', 'wiz', 36, '2023-11-09 02:11:44'),
	(1073, 0.3, 'baladewa', 'wiz', 37, '2023-11-09 02:11:44'),
	(1074, 0.3, 'baladewa', 'wiz', 38, '2023-11-09 02:11:44'),
	(1075, 0.5, 'baladewa', 'wiz', 39, '2023-11-09 02:11:44'),
	(1076, 1.5, 'baladewa', 'wiz', 40, '2023-11-09 02:11:44'),
	(1077, 1, 'baladewa', 'wiz', 41, '2023-11-09 02:11:44');

-- Dumping structure for table db_logistic.questions
CREATE TABLE IF NOT EXISTS `questions` (
  `question_id` int NOT NULL AUTO_INCREMENT,
  `question_text` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `subcriteria_id` int DEFAULT NULL,
  PRIMARY KEY (`question_id`),
  KEY `FKer5fscwrkeb64njtwe1y8xjvp` (`subcriteria_id`),
  CONSTRAINT `FKer5fscwrkeb64njtwe1y8xjvp` FOREIGN KEY (`subcriteria_id`) REFERENCES `subcriteria` (`subcriteria_id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table db_logistic.questions: ~39 rows (approximately)
INSERT INTO `questions` (`question_id`, `question_text`, `subcriteria_id`) VALUES
	(1, '1. Melakukan identifikasi dan membuat stratifikasi masalah (problem/persoalan)\r\n', 1),
	(2, '2. Memilih project improvement/inovasi berdasarkan data & fakta melalui penggunaan quality methode dan tools secara maksimal (baik dan benar)\r\n', 1),
	(3, '3. Menjelaskan alasan kenapa proyek perbaikan tersebut dipilih\r\n', 1),
	(4, '1. Menjelaskan proyek perbaikan (improvement/inovasi) yang terseleksi mempunyai potensi pengaruh positif terhadap tujuan (strategi) perusahaan\r\n\r\n', 2),
	(5, '2. Teridentifikasi tingkat kesulitan (kompleksitas) masalah / pelaksanaan perbaikan dari proyek yang terseleksi\r\n\r\n', 2),
	(6, '3. Tercermin harapan Customer dan/atau Stakeholders terhadap proyek perbaikan (improvement/inovasi) yang akan dilaksanakan\r\n\r\n', 2),
	(7, '1. Mengidentifikasi Stakeholders yang bisa involve (terlibat) maupun yang dapat memberikan support dalam proyek perbaikan (improvement/inovasi)\r\n', 3),
	(8, '2. Mengidentifikasi Stakeholders yang resisten (menghambat), sehingga menjadikan tantangan terhadap proyek perbaikan (improvement/inovasi) \r\n', 3),
	(9, '3. Menjelaskan bagaimana mereka (stakeholders) akan bisa memberikan dampak positif terhadap proyek perbaikan (Improvement/inovasi)\r\n', 3),
	(10, '1. Target (sasaran) yang ditetapkan mencakup QCDSMP\r\n', 4),
	(11, '2. Penetapan sasaran jangka pendek, dan sasaran tersebut menjadi bagian dari tujuan perbaikan\r\n', 4),
	(12, 'Target (sasaran) jelas dan menantang\r\n\r\n', 5),
	(13, 'Prediksi manfaat dan masalah potensial\r\n\r\n', 6),
	(14, 'Verifikasi stakeholders yang terkait dengan proyek perbaikan\r\n\r\n', 7),
	(15, '1. Akar masalah teridentifikasi dengan jelas dan dapat dibuktikan\r\n', 8),
	(16, '2. Munculnya akar penyebab dari akar masalah pada suatu faktor yang telah dikembangkan\r\n', 8),
	(17, '3. Identifikasi dan validasi (memiliki akurasi penentuan) faktor penyebab dominan (akar penyebab masalah utama)\r\n', 8),
	(18, '4. Kesesuaian masalah dengan prioritas penanganan faktor penyebab dominan\r\n', 8),
	(19, 'Aktualisasi dan keterlibatan team & stakeholders dalam mengembangkan pola hubungan sebab akibat\r\n', 9),
	(20, '1. Menjelaskan metode dan quality tools yang digunakan untuk mencari dan memilih ide perbaikan serta menentukan penanggulangan\r\n', 10),
	(21, '2. Menjelaskan kreatifitas team & stakeholders dalam menemukan alternatif solusi (improvement actions) dan parameter kelebihan serta analisa resiko (kekurangannya)\r\n', 10),
	(22, '3. Menjelaskan keterlibatan team & stakeholders dalam menemukan alternatif solusi (improvement actions) dan parameter kelebihan serta analisa resiko (kekurangannya)\r\n', 10),
	(23, '1. Menjelaskan tentang final solutions/improvement actions, validasi dan benefit/kemampuan dalam menjawab kebutuhan yang diharapkan terhadap inovasi/improvement actions yang akan dilakukan\r\n', 11),
	(24, '2. Menguraikan detail rencana project (5W2H), termasuk distribusi peranan dan keterlibatan stakeholders dalam melakukan perbaikan \r\n', 11),
	(25, '1. Memiliki sistem pantau (monitoring) atas proses pelaksanaan tindakan perbaikan yang dilakukan\r\n', 12),
	(26, '2. Menjelaskan bagaimana mengidentifikasikan serta mengalokasikan hambatan-hambatan (baik yang nyata atau potensial) yang muncul, serta memastikan keterlibatan team & stakeholders untuk meyepakati (buy-in) atas corrective action yang telah ditentukan serta dampaknya\r\n', 12),
	(27, 'Semua anggota Tim memahami perubahan-perubahan yang terjadi di dalam improvement yang dibuat terhadap kondisi sebelumnya (kondisi awal)\r\n', 13),
	(28, '1. Ada analisis komparasi yg valid & tepat serta kesimpulan analisis/faktor penyebab yg belum teratasi\r\n', 14),
	(29, '2. Tools yang dipakai, alasan penggunaan & hasilnya\r\n', 14),
	(30, '1. Manfaat tangible & intangible\r\n', 15),
	(31, '2. Ada hasil-hasil positif lain yang timbul, tapi bukan sasaran penanggulangan/perbaikan yang telah dilaksanakan\r\n', 15),
	(32, '3. Ada analisis dampak terhadap hasil-hasil lain (apakah hasil perbaikan menimbulkan efek samping?) Bila ada, bagaimana tindakan untuk eliminasi hal tersebut? Hasilnya efisien?\r\n', 15),
	(33, 'Proyek perbaikan memberikan kontribusi/ dampak nyata terhadap tujuan perusahaan/plant/divisi/departement  dan stakeholders\r\n', 16),
	(34, '1. Pendekatan sistem atas standar baru\r\nmeliputi standar masukan, proses dan hasil. Dan standar baru tersebut mudah dipahami (informatif)\r\n', 17),
	(35, '2. Kualitas standar pengendalian dan pencegahan terhadap timbulnya masalah (lama atau baru)\r\n', 17),
	(36, '3. Standar baru lebih efektif dan aplikatif dari pada sebelumnya\r\n', 17),
	(37, '4. Standar baru telah dikomunikasikan, sudah diserah terimakan dan dapat diterapkan di tempat lain\r\n', 17),
	(38, '5. Bila terjadi non-conformance (ada peringatan otomatis/tidak otomatis/tidak ada peringatan)\r\n', 17),
	(39, '1. Memiliki kegiatan/tahapan/aktifitas yang menjamin pelaksanaan standar baru secara efektif', 18),
	(40, '2. Memiliki cara melakukan monitoring dan mengevaluasi standar baru\r\n', 18),
	(41, 'Identifikasi & Penetapan Rencana Berikutnya \r\n', 19);

-- Dumping structure for table db_logistic.subcriteria
CREATE TABLE IF NOT EXISTS `subcriteria` (
  `subcriteria_id` int NOT NULL AUTO_INCREMENT,
  `subcriteria_name` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `criteria_id` int DEFAULT NULL,
  PRIMARY KEY (`subcriteria_id`),
  KEY `FKd17jsp88aroh433el5xihkeam` (`criteria_id`),
  CONSTRAINT `FKd17jsp88aroh433el5xihkeam` FOREIGN KEY (`criteria_id`) REFERENCES `criteria` (`criteria_id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table db_logistic.subcriteria: ~19 rows (approximately)
INSERT INTO `subcriteria` (`subcriteria_id`, `subcriteria_name`, `criteria_id`) VALUES
	(1, 'A. Menjelaskan methode, quality tools dan data dalam memilih suatu project\r\n', 1),
	(2, 'B. Tema terkait dengan tujuan & strategi bisnis/perusahaan', 1),
	(3, 'C. Stakeholders Involvement (Keterlibatan Stakeholders) : Pemegang Saham, Customer, Karyawan (Atasan/Bawahan), Pemerintah, Supplier, Community dan Lingkungan\r\n', 1),
	(4, 'D .Target (sasaran) sesuai dengan tema\r\n', 2),
	(5, 'E. Target (sasaran) jelas dan menantang\r\n', 2),
	(6, 'F. Prediksi manfaat dan masalah potensial\r\n', 2),
	(7, 'G. Verifikasi stakeholders yang terkait dengan proyek perbaikan\r\n', 2),
	(8, 'H. Pendekatan/proses yang digunakan untuk mengidentifikasi potensi akar penyebab masalah utama/kemungkinan perbaikan\r\n', 3),
	(9, 'I. Aktualisasi dan keterlibatan team & stakeholders dalam mengembangkan pola hubungan sebab akibat\r\n', 3),
	(10, 'J. Menjelaskan metode dan quality tools yaang digunakan dalam pengembangan solusi dan inovasi/improvement actions\r\n', 4),
	(11, 'K. Kualitas ide solusi / inovation actions\r\n', 4),
	(12, 'L. Menjelaskan pendekatan yang digunakan untuk menerapkan tindakan solusi/improvement dan memastikan hasil yang ingin dicapai\r\n', 5),
	(13, 'M. Semua anggota Tim memahami perubahan-perubahan yang terjadi di dalam improvement yang dibuat terhadap kondisi sebelumnya (kondisi awal)\r\n', 5),
	(14, 'N. Tingkat pencapaian perbaikan dibandingkan dengan target (sasaran) akhir / antara\r\n', 6),
	(15, 'O. Kuantifikasi dampak solusi/improvement actions\r\n\r\n', 6),
	(16, 'P. Proyek perbaikan memberikan kontribusi/ dampak nyata terhadap tujuan perusahaan/plant/divisi/departement  dan stakeholders\r\n\r\n', 6),
	(17, 'Q. Standarisasi', 7),
	(18, 'R. Menjamin pelaksanaan standar baru\r\n', 7),
	(19, 'S. Identifikasi & Penetapan Rencana Berikutnya \r\n', 7);

-- Dumping structure for table db_logistic.teams
CREATE TABLE IF NOT EXISTS `teams` (
  `team_id` int NOT NULL AUTO_INCREMENT,
  `team_name` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`team_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table db_logistic.teams: ~9 rows (approximately)
INSERT INTO `teams` (`team_id`, `team_name`) VALUES
	(1, 'coc'),
	(2, 'sugoi'),
	(3, 'aladin'),
	(4, 'ampibi'),
	(5, 'terasi'),
	(6, 'pilot'),
	(7, 'semongko'),
	(8, 'ares'),
	(9, 'baladewa');

-- Dumping structure for table db_logistic.users
CREATE TABLE IF NOT EXISTS `users` (
  `users_id` int NOT NULL AUTO_INCREMENT,
  `nip` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `username` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `role` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`users_id`),
  UNIQUE KEY `UK_e72fwutcg2xou2qg41w9bn5ed` (`nip`)
) ENGINE=InnoDB AUTO_INCREMENT=75 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table db_logistic.users: ~2 rows (approximately)
INSERT INTO `users` (`users_id`, `nip`, `username`, `password`, `role`) VALUES
	(1, '1', 'admin', 'admin', 'admin'),
	(69, '123', 'wiz', '123', 'user');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;

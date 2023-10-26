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
  `criteria_id` bigint NOT NULL AUTO_INCREMENT,
  `criteria_name` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`criteria_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

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
  `choice_id` bigint NOT NULL AUTO_INCREMENT,
  `choice_value` double DEFAULT NULL,
  `question_id` int DEFAULT NULL,
  PRIMARY KEY (`choice_id`),
  KEY `FKrmvyshef5iv5mp6m2yopn5xat` (`question_id`),
  CONSTRAINT `FKrmvyshef5iv5mp6m2yopn5xat` FOREIGN KEY (`question_id`) REFERENCES `questions` (`question_id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table db_logistic.multiple_choice: ~19 rows (approximately)
INSERT INTO `multiple_choice` (`choice_id`, `choice_value`, `question_id`) VALUES
	(1, 1, 1),
	(2, 0.75, 1),
	(3, 0.5, 1),
	(4, 0.3, 1),
	(5, 0.38, NULL),
	(6, 0.56, NULL),
	(7, 0.23, NULL),
	(13, 2, NULL),
	(14, 1.5, NULL),
	(15, 0.6, NULL),
	(16, 10, NULL),
	(17, 7.5, NULL),
	(18, 3, NULL),
	(19, 5, NULL),
	(21, 3.75, NULL),
	(22, 2.5, NULL),
	(23, 3.33, NULL),
	(24, 1.67, NULL),
	(25, 1.13, NULL);

-- Dumping structure for table db_logistic.nilai
CREATE TABLE IF NOT EXISTS `nilai` (
  `nilai_id` int NOT NULL AUTO_INCREMENT,
  `nilai` int NOT NULL,
  `question_id` int DEFAULT NULL,
  `team_id` int DEFAULT NULL,
  `users_id` int DEFAULT NULL,
  PRIMARY KEY (`nilai_id`),
  KEY `FK802q2lu06cunx7dxmdlnpcowf` (`question_id`),
  KEY `FKdm1m7ndn3evgrgsuw71gxdojw` (`team_id`),
  KEY `FK33kbgh9q2uegw1x7xyqibfjgc` (`users_id`),
  CONSTRAINT `FK33kbgh9q2uegw1x7xyqibfjgc` FOREIGN KEY (`users_id`) REFERENCES `users` (`users_id`),
  CONSTRAINT `FK802q2lu06cunx7dxmdlnpcowf` FOREIGN KEY (`question_id`) REFERENCES `questions` (`question_id`),
  CONSTRAINT `FKdm1m7ndn3evgrgsuw71gxdojw` FOREIGN KEY (`team_id`) REFERENCES `teams` (`team_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table db_logistic.nilai: ~0 rows (approximately)

-- Dumping structure for table db_logistic.questions
CREATE TABLE IF NOT EXISTS `questions` (
  `question_id` int NOT NULL AUTO_INCREMENT,
  `question_text` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `subcriteria_id` int DEFAULT NULL,
  PRIMARY KEY (`question_id`),
  KEY `FKer5fscwrkeb64njtwe1y8xjvp` (`subcriteria_id`),
  CONSTRAINT `FKer5fscwrkeb64njtwe1y8xjvp` FOREIGN KEY (`subcriteria_id`) REFERENCES `subcriteria` (`subcriteria_id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table db_logistic.questions: ~0 rows (approximately)
INSERT INTO `questions` (`question_id`, `question_text`, `subcriteria_id`) VALUES
	(1, 'Melakukan identifikasi dan membuat stratifikasi masalah (problem/persoalan)\r\n', 1),
	(2, 'Memilih project improvement/inovasi berdasarkan data & fakta melalui penggunaan quality methode dan tools secara maksimal (baik dan benar)\r\n', 1),
	(3, 'Menjelaskan alasan kenapa proyek perbaikan tersebut dipilih\r\n', 1),
	(4, 'Menjelaskan proyek perbaikan (improvement/inovasi) yang terseleksi mempunyai potensi pengaruh positif terhadap tujuan (strategi) perusahaan\r\n\r\n', 2),
	(5, 'Teridentifikasi tingkat kesulitan (kompleksitas) masalah / pelaksanaan perbaikan dari proyek yang terseleksi\r\n\r\n', 2),
	(6, 'Tercermin harapan Customer dan/atau Stakeholders terhadap proyek perbaikan (improvement/inovasi) yang akan dilaksanakan\r\n\r\n', 2),
	(7, 'Mengidentifikasi Stakeholders yang bisa involve (terlibat) maupun yang dapat memberikan support dalam proyek perbaikan (improvement/inovasi)\r\n', 3),
	(8, 'Mengidentifikasi Stakeholders yang resisten (menghambat), sehingga menjadikan tantangan terhadap proyek perbaikan (improvement/inovasi) \r\n', 3),
	(9, 'Menjelaskan bagaimana mereka (stakeholders) akan bisa memberikan dampak positif terhadap proyek perbaikan (Improvement/inovasi)\r\n', 3),
	(10, 'Target (sasaran) yang ditetapkan mencakup QCDSMP\r\n', 4),
	(11, 'Penetapan sasaran jangka pendek, dan sasaran tersebut menjadi bagian dari tujuan perbaikan\r\n', 4),
	(12, 'Target (sasaran) jelas dan menantang\r\n\r\n', 5),
	(13, 'Prediksi manfaat dan masalah potensial\r\n\r\n', 6),
	(14, 'Verifikasi stakeholders yang terkait dengan proyek perbaikan\r\n\r\n', 7),
	(15, 'Akar masalah teridentifikasi dengan jelas dan dapat dibuktikan\r\n', 8),
	(16, 'Munculnya akar penyebab dari akar masalah pada suatu faktor yang telah dikembangkan\r\n', 8),
	(17, 'Identifikasi dan validasi (memiliki akurasi penentuan) faktor penyebab dominan (akar penyebab masalah utama)\r\n', 8),
	(18, 'Kesesuaian masalah dengan prioritas penanganan faktor penyebab dominan\r\n', 8),
	(19, 'Aktualisasi dan keterlibatan team & stakeholders dalam mengembangkan pola hubungan sebab akibat\r\n', 9),
	(20, 'Menjelaskan metode dan quality tools yang digunakan untuk mencari dan memilih ide perbaikan serta menentukan penanggulangan\r\n', 10),
	(21, 'Menjelaskan kreatifitas team & stakeholders dalam menemukan alternatif solusi (improvement actions) dan parameter kelebihan serta analisa resiko (kekurangannya)\r\n', 10),
	(22, 'Menjelaskan keterlibatan team & stakeholders dalam menemukan alternatif solusi (improvement actions) dan parameter kelebihan serta analisa resiko (kekurangannya)\r\n', 10),
	(23, 'Menjelaskan tentang final solutions/improvement actions, validasi dan benefit/kemampuan dalam menjawab kebutuhan yang diharapkan terhadap inovasi/improvement actions yang akan dilakukan\r\n', 11),
	(24, 'Menguraikan detail rencana project (5W2H), termasuk distribusi peranan dan keterlibatan stakeholders dalam melakukan perbaikan \r\n', 11),
	(25, 'Memiliki sistem pantau (monitoring) atas proses pelaksanaan tindakan perbaikan yang dilakukan\r\n', 12),
	(26, 'Menjelaskan bagaimana mengidentifikasikan serta mengalokasikan hambatan-hambatan (baik yang nyata atau potensial) yang muncul, serta memastikan keterlibatan team & stakeholders untuk meyepakati (buy-in) atas corrective action yang telah ditentukan serta dampaknya\r\n', 12),
	(27, 'Semua anggota Tim memahami perubahan-perubahan yang terjadi di dalam improvement yang dibuat terhadap kondisi sebelumnya (kondisi awal)\r\n', 13),
	(28, 'Ada analisis komparasi yg valid & tepat serta kesimpulan analisis/faktor penyebab yg belum teratasi\r\n', 14),
	(29, 'Tools yang dipakai, alasan penggunaan & hasilnya\r\n', 14),
	(30, 'Manfaat tangible & intangible\r\n', 15),
	(31, 'Ada hasil-hasil positif lain yang timbul, tapi bukan sasaran penanggulangan/perbaikan yang telah dilaksanakan\r\n', 15),
	(32, 'Ada analisis dampak terhadap hasil-hasil lain (apakah hasil perbaikan menimbulkan efek samping?) Bila ada, bagaimana tindakan untuk eliminasi hal tersebut? Hasilnya efisien?\r\n', 15),
	(33, 'Proyek perbaikan memberikan kontribusi/ dampak nyata terhadap tujuan perusahaan/plant/divisi/departement  dan stakeholders\r\n', 16),
	(34, 'Pendekatan sistem atas standar baru\r\nmeliputi standar masukan, proses dan hasil. Dan standar baru tersebut mudah dipahami (informatif)\r\n', 17),
	(35, 'Kualitas standar pengendalian dan pencegahan terhadap timbulnya masalah (lama atau baru)\r\n', 17),
	(36, 'Standar baru lebih efektif dan aplikatif dari pada sebelumnya\r\n', 17),
	(37, 'Standar baru telah dikomunikasikan, sudah diserah terimakan dan dapat diterapkan di tempat lain\r\n', 17),
	(38, 'Bila terjadi non-conformance (ada peringatan otomatis/tidak otomatis/tidak ada peringatan)\r\n', 17),
	(39, 'Memiliki kegiatan/tahapan/aktifitas yang menjamin pelaksanaan standar baru secara efektif', 18),
	(40, 'Memiliki cara melakukan monitoring dan mengevaluasi standar baru\r\n', 18),
	(41, 'Identifikasi & Penetapan Rencana Berikutnya \r\n', 19);

-- Dumping structure for table db_logistic.subcriteria
CREATE TABLE IF NOT EXISTS `subcriteria` (
  `subcriteria_id` int NOT NULL AUTO_INCREMENT,
  `subcriteria_name` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `criteria_id` bigint DEFAULT NULL,
  PRIMARY KEY (`subcriteria_id`),
  KEY `FKd17jsp88aroh433el5xihkeam` (`criteria_id`),
  CONSTRAINT `FKd17jsp88aroh433el5xihkeam` FOREIGN KEY (`criteria_id`) REFERENCES `criteria` (`criteria_id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table db_logistic.subcriteria: ~1 rows (approximately)
INSERT INTO `subcriteria` (`subcriteria_id`, `subcriteria_name`, `criteria_id`) VALUES
	(1, 'Menjelaskan methode, quality tools dan data dalam memilih suatu project\r\n', 1),
	(2, 'Tema terkait dengan tujuan & strategi bisnis/perusahaan', 1),
	(3, 'Stakeholders Involvement (Keterlibatan Stakeholders) : Pemegang Saham, Customer, Karyawan (Atasan/Bawahan), Pemerintah, Supplier, Community dan Lingkungan\r\n', 1),
	(4, 'Target (sasaran) sesuai dengan tema\r\n', 2),
	(5, 'Target (sasaran) jelas dan menantang\r\n', 2),
	(6, 'Prediksi manfaat dan masalah potensial\r\n', 2),
	(7, 'Verifikasi stakeholders yang terkait dengan proyek perbaikan\r\n', 2),
	(8, 'Pendekatan/proses yang digunakan untuk mengidentifikasi potensi akar penyebab masalah utama/kemungkinan perbaikan\r\n', 3),
	(9, 'Aktualisasi dan keterlibatan team & stakeholders dalam mengembangkan pola hubungan sebab akibat\r\n', 3),
	(10, 'Menjelaskan metode dan quality tools yaang digunakan dalam pengembangan solusi dan inovasi/improvement actions\r\n', 4),
	(11, 'Kualitas ide solusi / inovation actions\r\n', 4),
	(12, 'Menjelaskan pendekatan yang digunakan untuk menerapkan tindakan solusi/improvement dan memastikan hasil yang ingin dicapai\r\n', 5),
	(13, 'Semua anggota Tim memahami perubahan-perubahan yang terjadi di dalam improvement yang dibuat terhadap kondisi sebelumnya (kondisi awal)\r\n', 5),
	(14, 'Tingkat pencapaian perbaikan dibandingkan dengan target (sasaran) akhir / antara\r\n', 6),
	(15, 'Kuantifikasi dampak solusi/improvement actions\r\n\r\n', 6),
	(16, 'Proyek perbaikan memberikan kontribusi/ dampak nyata terhadap tujuan perusahaan/plant/divisi/departement  dan stakeholders\r\n\r\n', 6),
	(17, 'Standarisasi', 7),
	(18, 'Menjamin pelaksanaan standar baru\r\n', 7),
	(19, 'Identifikasi & Penetapan Rencana Berikutnya \r\n', 7);

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
) ENGINE=InnoDB AUTO_INCREMENT=73 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table db_logistic.users: ~2 rows (approximately)
INSERT INTO `users` (`users_id`, `nip`, `username`, `password`, `role`) VALUES
	(69, '123', 'wiz', '123', '2'),
	(70, '1222', 'mama', '111', '2'),
	(71, '444', 'wiz', 'rahasia', '2'),
	(72, '1234', 'veri', '1234', '2');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;

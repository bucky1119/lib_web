CREATE DATABASE `webData` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
CREATE TABLE `article_rating` (
  `id` int NOT NULL AUTO_INCREMENT,
  `article_id` int NOT NULL,
  `expert_id` int NOT NULL,
  `innovation_score` int NOT NULL,
  `disruption_score` int NOT NULL,
  `frontier_score` int NOT NULL,
  `industry_impact_score` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_article_expert` (`article_id`,`expert_id`),
  KEY `article_rating_ibfk_1_idx` (`article_id`),
  KEY `article_rating_ibfk_2_idx` (`expert_id`),
  CONSTRAINT `fk_article_id` FOREIGN KEY (`article_id`) REFERENCES `web_data_table` (`id`),
  CONSTRAINT `fk_expert_id` FOREIGN KEY (`expert_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` enum('EXPERT','LIBRARIAN','ADMIN') NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=1797991113219469314 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `web_data_table` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` text NOT NULL,
  `author` varchar(100) DEFAULT '',
  `info_type` enum('T0','T1','T2','T3','T4') DEFAULT NULL,
  `post_agency` varchar(80) DEFAULT '',
  `nation` varchar(30) DEFAULT '未知',
  `date` date DEFAULT NULL,
  `link_url` text,
  `domain` enum('F0','F1','F2','F3','F4','F5','F6','F7','F8','F9') DEFAULT 'F0',
  `subject` enum('非F1领域','AG0','AG1','AG2','AG3','AG4','AG5','AG6','AG7','AG8','AG9','AG10','AG11') DEFAULT NULL,
  `text` text NOT NULL,
  `average_rating` double DEFAULT NULL,
  `rating_count` int DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1119887362 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

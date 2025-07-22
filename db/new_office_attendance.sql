-- MySQL dump 10.13  Distrib 9.0.1, for macos14 (x86_64)
--
-- Host: localhost    Database: new_office_attendance
-- ------------------------------------------------------
-- Server version	9.0.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `approvals`
--

DROP TABLE IF EXISTS `approvals`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `approvals` (
  `id` int NOT NULL AUTO_INCREMENT,
  `request_id` int NOT NULL,
  `approver_id` int NOT NULL,
  `decision` enum('Approved','Rejected') NOT NULL,
  `decision_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `comments` text NOT NULL,
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `request_id` (`request_id`),
  KEY `approver_id` (`approver_id`),
  CONSTRAINT `approvals_ibfk_1` FOREIGN KEY (`request_id`) REFERENCES `leave_requests` (`id`),
  CONSTRAINT `approvals_ibfk_2` FOREIGN KEY (`approver_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `approvals`
--

LOCK TABLES `approvals` WRITE;
/*!40000 ALTER TABLE `approvals` DISABLE KEYS */;
INSERT INTO `approvals` VALUES (5,1,1,'Approved','2025-06-05 10:30:00','Approved as per policy','2025-06-26 12:48:36',1,'2025-06-26 12:48:36',1),(6,2,2,'Rejected','2025-06-16 14:15:00','Insufficient leave balance','2025-06-26 12:48:36',2,'2025-06-26 12:48:36',2),(11,1,1,'Approved','2025-06-26 12:50:28','Approved by manager','2025-06-26 12:50:28',1,'2025-06-26 12:50:28',1),(16,1,1,'Approved','2025-06-26 12:51:14','Leave approved by manager 1','2025-06-26 12:51:14',1,'2025-06-26 12:51:14',1),(17,2,2,'Rejected','2025-06-26 12:51:14','Insufficient leave balance','2025-06-26 12:51:14',2,'2025-06-26 12:51:14',2),(18,3,1,'Approved','2025-06-26 12:51:14','Approved quickly','2025-06-26 12:51:14',1,'2025-06-26 12:51:14',1);
/*!40000 ALTER TABLE `approvals` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `attendance`
--

DROP TABLE IF EXISTS `attendance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `attendance` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `check_in_time` datetime DEFAULT NULL,
  `check_out_time` datetime DEFAULT NULL,
  `latitude` decimal(10,8) DEFAULT NULL,
  `longitude` decimal(11,8) DEFAULT NULL,
  `status` enum('Present','Absent','Late') NOT NULL,
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `attendance_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attendance`
--

LOCK TABLES `attendance` WRITE;
/*!40000 ALTER TABLE `attendance` DISABLE KEYS */;
INSERT INTO `attendance` VALUES (1,1,NULL,'2025-07-11 17:45:00',12.97160000,77.59460000,'Present','2025-06-26 12:45:23',1,'2025-07-11 12:30:29',NULL),(2,2,'2025-06-24 09:15:00','2025-06-24 17:45:00',28.70405920,77.10249020,'Late','2025-06-26 12:45:23',1,'2025-06-26 12:45:23',1),(3,3,'2025-06-24 00:00:00',NULL,19.07600000,72.87770000,'Absent','2025-06-26 12:45:23',1,'2025-06-26 12:45:23',1),(4,1,'2025-06-25 08:55:00','2025-06-25 18:00:00',12.97159870,77.59456270,'Present','2025-06-26 12:45:23',1,'2025-06-26 12:45:23',1),(5,2,'2025-06-25 09:05:00','2025-06-25 17:30:00',28.70405920,77.10249020,'Present','2025-06-26 12:45:23',1,'2025-06-26 12:45:23',1),(6,1,'2025-06-30 09:00:00',NULL,12.97160000,77.59460000,'Present',NULL,NULL,NULL,NULL),(7,1,'2025-06-30 09:00:00',NULL,12.97160000,77.59460000,'Present',NULL,NULL,NULL,NULL),(8,1,'2025-07-11 00:00:00',NULL,12.97160000,77.59460000,'Present',NULL,NULL,NULL,NULL),(9,1,'2025-07-12 00:00:00',NULL,12.97160000,77.59460000,'Present',NULL,NULL,NULL,NULL),(10,1,'2025-07-13 12:33:00',NULL,12.97160000,77.59460000,'Present',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `attendance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employees`
--

DROP TABLE IF EXISTS `employees`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employees` (
  `id` int NOT NULL AUTO_INCREMENT,
  `blood_group` varchar(10) NOT NULL,
  `emergency_contact` varchar(20) NOT NULL,
  `address` text NOT NULL,
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employees`
--

LOCK TABLES `employees` WRITE;
/*!40000 ALTER TABLE `employees` DISABLE KEYS */;
INSERT INTO `employees` VALUES (1,'A+','9876543210','123 Main St, Springfield, IL','2025-06-26 12:38:24',1,'2025-06-26 12:38:24',1),(2,'B-','8765432109','456 Elm St, Shelbyville, IL','2025-06-26 12:38:24',1,'2025-06-26 12:38:24',1),(3,'O+','7654321098','789 Oak St, Capital City, IL','2025-06-26 12:38:24',1,'2025-06-26 12:38:24',1),(4,'AB+','6543210987','101 Maple Ave, Ogdenville, IL','2025-06-26 12:38:24',1,'2025-06-26 12:38:24',1);
/*!40000 ALTER TABLE `employees` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `holidays`
--

DROP TABLE IF EXISTS `holidays`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `holidays` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(100) NOT NULL,
  `holiday_date` date NOT NULL,
  `description` varchar(100) DEFAULT NULL,
  `recurring` tinyint(1) DEFAULT '0',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `created_by` (`created_by`),
  CONSTRAINT `holidays_ibfk_1` FOREIGN KEY (`created_by`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `holidays`
--

LOCK TABLES `holidays` WRITE;
/*!40000 ALTER TABLE `holidays` DISABLE KEYS */;
/*!40000 ALTER TABLE `holidays` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `leave_balance`
--

DROP TABLE IF EXISTS `leave_balance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `leave_balance` (
  `user_id` int NOT NULL,
  `earned_leave` float DEFAULT '0',
  `sick_leave` float DEFAULT '0',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  CONSTRAINT `leave_balance_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `leave_balance`
--

LOCK TABLES `leave_balance` WRITE;
/*!40000 ALTER TABLE `leave_balance` DISABLE KEYS */;
INSERT INTO `leave_balance` VALUES (1,15,2,'2025-06-26 12:44:33',1,'2025-07-03 13:39:07',1),(2,12.5,8,'2025-06-26 12:44:33',1,'2025-06-26 12:44:33',1),(3,20,12,'2025-06-26 12:44:33',1,'2025-06-26 12:44:33',1);
/*!40000 ALTER TABLE `leave_balance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `leave_requests`
--

DROP TABLE IF EXISTS `leave_requests`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `leave_requests` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `leave_type` enum('SICK','CASUAL','EARNED','MATERNITY','PATERNITY','UNPAID','COMPENSATORY','MOURNING','MARRIAGE','STUDY') NOT NULL,
  `frequency` enum('DAY','HALF_DAY','WEEKLY','MONTHLY','HALF_YEARLY') NOT NULL DEFAULT 'DAY',
  `start_date` date NOT NULL,
  `end_date` date NOT NULL,
  `status` varchar(20) DEFAULT 'PENDING',
  `reason` text,
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `leave_requests_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `leave_requests`
--

LOCK TABLES `leave_requests` WRITE;
/*!40000 ALTER TABLE `leave_requests` DISABLE KEYS */;
INSERT INTO `leave_requests` VALUES (1,1,'SICK','DAY','2025-06-01','2025-06-03','REJECTED','Fever and cold','2025-06-26 12:47:02',1,'2025-07-03 13:35:44',2),(2,2,'CASUAL','HALF_DAY','2025-06-15','2025-06-15','PENDING','Personal work','2025-06-26 12:47:02',2,'2025-06-26 12:47:02',2),(3,3,'EARNED','WEEKLY','2025-07-01','2025-07-07','REJECTED','Vacation','2025-06-26 12:47:02',3,'2025-06-26 12:47:02',3),(4,1,'MATERNITY','MONTHLY','2025-05-01','2025-07-31','APPROVED','Maternity leave','2025-06-26 12:47:02',1,'2025-06-26 12:47:02',1),(5,2,'UNPAID','DAY','2025-06-20','2025-06-20','PENDING','Family emergency','2025-06-26 12:47:02',2,'2025-06-26 12:47:02',2),(6,1,'SICK','DAY','2025-07-01','2025-07-05','PENDING',NULL,'2025-06-26 12:50:13',1,'2025-06-26 12:50:13',1),(7,1,'SICK','DAY','2025-07-02','2025-07-04','PENDING','Personal emergency','2025-07-03 12:10:30',1,NULL,NULL),(8,1,'SICK','DAY','2025-07-02','2025-07-04','PENDING','Personal emergency','2025-07-03 13:04:14',1,NULL,NULL),(9,1,'SICK','DAY','2025-07-02','2025-07-04','PENDING','Personal emergency','2025-07-03 13:09:03',1,NULL,NULL),(10,1,'SICK','DAY','2025-07-02','2025-07-04','PENDING','Personal emergency','2025-07-03 13:32:13',1,NULL,NULL),(11,1,'SICK','DAY','2025-07-02','2025-07-04','PENDING','Personal emergency','2025-07-03 13:37:23',1,NULL,NULL),(12,1,'SICK','DAY','2025-07-02','2025-07-04','PENDING','Personal emergency','2025-07-03 13:37:54',1,NULL,NULL),(13,1,'SICK','DAY','2025-07-02','2025-07-04','PENDING','Personal emergency','2025-07-03 13:38:36',1,NULL,NULL),(14,1,'SICK','DAY','2025-07-02','2025-07-04','PENDING','Personal emergency','2025-07-03 13:38:37',1,NULL,NULL),(15,1,'SICK','DAY','2025-07-02','2025-07-04','PENDING','Personal emergency','2025-07-03 13:38:39',1,NULL,NULL),(16,1,'SICK','DAY','2025-07-02','2025-07-04','PENDING','Personal emergency','2025-07-03 13:38:40',1,NULL,NULL),(17,1,'SICK','DAY','2025-07-02','2025-07-04','PENDING','Personal emergency','2025-07-03 13:38:46',1,NULL,NULL),(18,1,'SICK','DAY','2025-07-02','2025-07-04','PENDING','Personal emergency','2025-07-03 14:49:47',1,NULL,NULL),(19,1,'SICK','DAY','2025-07-02','2025-07-04','PENDING','Personal emergency','2025-07-03 14:53:08',1,NULL,NULL),(20,1,'SICK','DAY','2025-07-02','2025-07-04','PENDING','Personal emergency','2025-07-03 14:53:22',1,NULL,NULL),(21,1,'SICK','DAY','2025-07-02','2025-07-04','PENDING','Personal emergency','2025-07-03 14:53:39',1,NULL,NULL),(22,1,'SICK','DAY','2025-07-02','2025-07-04','PENDING','Personal emergency','2025-07-03 14:53:41',1,NULL,NULL),(23,1,'SICK','DAY','2025-07-02','2025-07-04','PENDING','Personal emergency','2025-07-03 14:53:42',1,NULL,NULL);
/*!40000 ALTER TABLE `leave_requests` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permissions`
--

DROP TABLE IF EXISTS `permissions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `permissions` (
  `id` int NOT NULL AUTO_INCREMENT,
  `permission_key` varchar(100) NOT NULL,
  `description` text NOT NULL,
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `permission_key` (`permission_key`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permissions`
--

LOCK TABLES `permissions` WRITE;
/*!40000 ALTER TABLE `permissions` DISABLE KEYS */;
INSERT INTO `permissions` VALUES (1,'USER_CREATE','Permission to create new users','2025-06-26 12:36:39',1,'2025-06-26 12:36:39',1),(2,'USER_READ','Permission to read user information','2025-06-26 12:36:39',1,'2025-06-26 12:36:39',1),(3,'USER_UPDATE','Permission to update user information','2025-06-26 12:36:39',1,'2025-06-26 12:36:39',1),(4,'USER_DELETE','Permission to delete users','2025-06-26 12:36:39',1,'2025-06-26 12:36:39',1),(5,'ATTENDANCE_MARK','Permission to mark attendance','2025-06-26 12:36:39',1,'2025-06-26 12:36:39',1),(6,'LEAVE_APPLY','Permission to apply for leave','2025-06-26 12:36:39',1,'2025-06-26 12:36:39',1),(7,'LEAVE_APPROVE','Permission to approve or reject leave requests','2025-06-26 12:36:39',1,'2025-06-26 12:36:39',1),(8,'HOLIDAY_MANAGE','Permission to manage holidays','2025-06-26 12:36:39',1,'2025-06-26 12:36:39',1);
/*!40000 ALTER TABLE `permissions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `id` int NOT NULL AUTO_INCREMENT,
  `role_name` varchar(50) NOT NULL,
  `description` text,
  `permission_ids` text,
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `role_name` (`role_name`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'Admin','System-level controller with all permissions','1,2,3,4,5','2025-06-26 12:35:30',1,'2025-06-26 12:35:30',1),(2,'HR','Human Resource responsibilities including leave and holiday management','2,3,5','2025-06-26 12:35:30',1,'2025-06-26 12:35:30',1),(3,'Manager','Team supervision with leave approval and attendance view','3,4','2025-06-26 12:35:30',1,'2025-06-26 12:35:30',1),(4,'Employee','General user for attendance and leave requests','4','2025-06-26 12:35:30',1,'2025-06-26 12:35:30',1),(5,'Guest','Limited access for external users','5','2025-06-26 12:35:30',1,'2025-06-26 12:35:30',1),(6,'ROLE_USER','Default user role','[]','2025-07-22 12:35:37',1,'2025-07-22 12:35:37',1),(7,'ROLE_ADMIN','Administrator role','[]','2025-07-22 12:35:58',1,'2025-07-22 12:35:58',1);
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password_hash` varchar(255) NOT NULL,
  `department` varchar(100) DEFAULT NULL,
  `phone_number` varchar(20) DEFAULT NULL,
  `designation` varchar(100) DEFAULT NULL,
  `date_of_joining` date DEFAULT NULL,
  `role_id` int NOT NULL,
  `manager_id` int DEFAULT NULL,
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  `created_by` int DEFAULT NULL,
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`),
  KEY `role_id` (`role_id`),
  KEY `manager_id` (`manager_id`),
  CONSTRAINT `users_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`),
  CONSTRAINT `users_ibfk_2` FOREIGN KEY (`manager_id`) REFERENCES `users` (`id`) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'Alice Admin','alice.admin@example.com','hashed_password_1','Administration','1234567890','Administrator','2022-01-01',1,NULL,'2025-06-26 12:37:33',1,'2025-06-26 12:37:33',1),(2,'Bob Manager','bob.manager@example.com','hashed_password_2','Sales','2345678901','Sales Manager','2022-02-15',2,1,'2025-06-26 12:37:33',1,'2025-06-26 12:37:33',1),(3,'Charlie Employee','charlie.employee@example.com','hashed_password_3','Sales','3456789012','Sales Executive','2023-03-10',3,2,'2025-06-26 12:37:33',1,'2025-06-26 12:37:33',1),(4,'Diana Employee','diana.employee@example.com','hashed_password_4','Engineering','4567890123','Software Engineer','2023-05-01',3,2,'2025-06-26 12:37:33',1,'2025-06-26 12:37:33',1),(5,'Manager One','manager1@example.com','hashed_password','HR','9876543210','Manager','2023-01-01',1,NULL,'2025-06-26 12:49:57',NULL,'2025-06-26 12:49:57',NULL),(6,'john_doe','john@example.com','$2a$10$VPR7vYd8Z4d1MQJ/FRLljeCSKSNzGzN1GofkVCn5jYPYV9l0DrOkq',NULL,NULL,NULL,NULL,6,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_employee`
--

DROP TABLE IF EXISTS `users_employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users_employee` (
  `user_id` int NOT NULL,
  `employee_id` int NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `employee_id` (`employee_id`),
  CONSTRAINT `users_employee_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `users_employee_ibfk_2` FOREIGN KEY (`employee_id`) REFERENCES `employees` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_employee`
--

LOCK TABLES `users_employee` WRITE;
/*!40000 ALTER TABLE `users_employee` DISABLE KEYS */;
/*!40000 ALTER TABLE `users_employee` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-07-22 13:30:25

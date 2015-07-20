/*
SQLyog Ultimate v11.42 (64 bit)
MySQL - 5.6.21-log : Database - p2pspeedupdb
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`p2pspeedupdb` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `p2pspeedupdb`;

/*Table structure for table `maininfo` */

DROP TABLE IF EXISTS `maininfo`;

CREATE TABLE `maininfo` (
  `ip` varchar(100) NOT NULL,
  `myport` int(11) NOT NULL,
  `movieName` varchar(255) DEFAULT NULL,
  `savePath` varchar(255) DEFAULT NULL,
  `size` bigint(20) DEFAULT NULL,
  `state` int(1) DEFAULT '0',
  PRIMARY KEY (`ip`,`myport`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `maininfo` */

insert  into `maininfo`(`ip`,`myport`,`movieName`,`savePath`,`size`,`state`) values ('192.168.1.105',22222,'硅谷.Silicon.Valley.S02E01.中英字幕.HDTVrip.1024X576.mp4','F:/movie/硅谷/',294680521,1),('192.168.1.105',24444,'硅谷.Silicon.Valley.S02E01.中英字幕.HDTVrip.1024X576.mp4','F:/movie/硅谷/',294680521,0),('192.168.1.105',26666,'硅谷.Silicon.Valley.S02E01.中英字幕.HDTVrip.1024X576.mp4','F:/movie/硅谷/',294680521,1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

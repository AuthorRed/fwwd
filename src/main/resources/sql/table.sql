CREATE TABLE `user0` (
  `ID` bigint(20) NOT NULL,
  `UID` varchar(32) NOT NULL,
  `PWD` varchar(64) NOT NULL,
  `SALT` varchar(32) NOT NULL,
  `ROLE` varchar(16) NOT NULL,
  `NICK_NAME` varchar(32) DEFAULT NULL,
  `PHONE` varchar(32) DEFAULT NULL,
  `STATUS` tinyint(4) NOT NULL DEFAULT '0' COMMENT '0:正常,1:vip,-1:暂时锁定,-2:永久锁定',
  `EMAIL` varchar(32) DEFAULT NULL,
  `UPDATE_TIME` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `UPDATE_PERSON` varchar(32) DEFAULT NULL,
  `HASH` int(11) DEFAULT NULL,
  `HEAD_IMG` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `attach0` (
   `ID` bigint(20) NOT NULL,
   `URL` varchar(64) DEFAULT NULL,
   `HOST` varchar(32) DEFAULT NULL,
   `FID` bigint(32) DEFAULT NULL,
   `FID_HASH` int(11) DEFAULT NULL,
   `DISPLAY_ORDER` tinyint(4) DEFAULT NULL,
   `FILE_NAME` varchar(32) DEFAULT NULL,
   `STATUS` tinyint(4) DEFAULT NULL,
   `TYPE` tinyint(4) DEFAULT NULL,
   `UPLOAD_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   `UPLOAD_UID` varchar(32) DEFAULT NULL,
   `CATEGORY` varchar(16) DEFAULT NULL,
   PRIMARY KEY (`ID`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4

CREATE TABLE `commodity0` (
   `id` bigint(20) NOT NULL,
   `title` varchar(128) DEFAULT NULL,
   `seller` varchar(32) DEFAULT NULL,
   `status` int(11) DEFAULT NULL,
   `price` decimal(8,2) DEFAULT NULL,
   `unit` varchar(16) DEFAULT NULL,
   `update_time` timestamp NULL DEFAULT NULL,
   PRIMARY KEY (`id`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4

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

drop table commodity0;
CREATE TABLE `commodity0` (
   `id` bigint(20) NOT NULL,
   `title` varchar(128) DEFAULT NULL,
   `seller` varchar(32) DEFAULT NULL,
   `status` int(11) DEFAULT NULL,
   `price` decimal(8,2) DEFAULT NULL,
   `unit` varchar(16) DEFAULT NULL,
   `update_time` timestamp NULL DEFAULT NULL,
   `category` varchar(16) DEFAULT NULL,
   `seller_id` bigint(20) DEFAULT NULL,
   `head_img` bigint(20) DEFAULT NULL,
   PRIMARY KEY (`id`),
   KEY `price_index` (`price`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4


CREATE TABLE `order0` (
  `id` bigint(20) NOT NULL,
  `commodity_id` bigint(20) DEFAULT NULL,
  `seller_uid` varchar(32) DEFAULT NULL,
  `buyer_uid` varchar(32) DEFAULT NULL,
  `buyer_address_id` bigint(20) DEFAULT NULL,
  `description` varchar(128) DEFAULT NULL,
  `remark` varchar(64) DEFAULT NULL,
  `price` decimal(8,2) DEFAULT NULL,
  `amount` int(11) DEFAULT NULL,
  `unit` varchar(16) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `buyer_pay` decimal(8,2) DEFAULT NULL,
  `seller_rec` decimal(8,2) DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `add_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



CREATE TABLE `seller_order0` (
  `id` bigint(20) NOT NULL,
  `order_id` bigint(20) DEFAULT NULL,
  `seller_uid` varchar(32) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `buyer_order0` (
  `id` bigint(20) NOT NULL,
  `order_id` bigint(20) DEFAULT NULL,
  `buyer_uid` varchar(32) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `seller_category0` (
  `id` bigint(20) NOT NULL,
  `seller_uid` varchar(32) DEFAULT NULL,
  `categroy` varchar(16) DEFAULT NULL,
  `seller_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `order_detail0` (
  `id` bigint(20) NOT NULL,
  `order_id` bigint(20) DEFAULT NULL,
  `commodity_id` varchar(64) DEFAULT NULL,
  `amount` int(11) DEFAULT NULL,
  `buyer_pay` decimal(8,2) DEFAULT NULL,
  `remark` varchar(128) DEFAULT NULL,
  `commodity_desc` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

#Elastic search create index DSL
PUT /commodity
{
	"settings": {
		"number_of_shards": 2,
		"number_of_replicas": 2
	},
	"mappings": {
		"properties": {
			"id": {
				"type": "long"
			},
			"title": {
				"type": "text",
				"analyzer": "ik_smart",
				"search_analyzer": "ik_smart"
			},
			"img_url": {
				"type": "keyword"
			},
			"img_id": {
				"type": "keyword"
			},
			"seller": {
				"type": "keyword"
			},
			"price": {
				"type": "double"
			}
		}
	}
}
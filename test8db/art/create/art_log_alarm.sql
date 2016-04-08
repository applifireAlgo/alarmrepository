DROP TABLE IF EXISTS `art_log_alarm`;

CREATE TABLE `art_log_alarm` (
  `alarmPkId` varchar(256) NOT NULL,
  `alarm_id` varchar(256) NOT NULL,
  `severity` int(10) NOT NULL,
  `alarm_context` int(10) NOT NULL,
  `connector_order` varchar(256) NOT NULL,
  `message` varchar(1000) NOT NULL,
  `diagnose` varchar(1000) NOT NULL,
  `solution` varchar(1000) NOT NULL,
  `app_layer` int(11) NOT NULL,
  `status` int(10) NOT NULL,
  `event_action` int(11) NOT NULL,
  `exception_class` varchar(256) NOT NULL,
  `created_by` varchar(64) DEFAULT '-1',
  `created_date` datetime DEFAULT '1900-01-01 00:00:00',
  `updated_by` varchar(64) DEFAULT '-1',
  `updated_date` datetime DEFAULT '1900-01-01 00:00:00',
  `version_id` int(11) DEFAULT '-1',
  `active_status` int(1) DEFAULT '1',
  `app_creator_id` varchar(64) NOT NULL,
  `domainPkId` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`alarmPkId`),
  KEY `fk_aws_log_alarm_1_idx` (`domainPkId`),
  CONSTRAINT `fk_aws_log_alarm_1` FOREIGN KEY (`domainPkId`) REFERENCES `art_log_domain` (`domainPkId`) ON DELETE NO ACTION ON UPDATE NO ACTION
);


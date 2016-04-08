DROP TABLE IF EXISTS `art_log_details`;

CREATE TABLE `art_log_details` (
 `log_details_pk_id` varchar(45) NOT NULL,
 `version_number` int(11) NOT NULL,
 `version_id` int(11) DEFAULT '-1',
  PRIMARY KEY (`log_details_pk_id`)
);


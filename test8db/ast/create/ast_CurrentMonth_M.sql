DROP TABLE IF EXISTS `ast_CurrentMonth_M`;

CREATE TABLE `ast_CurrentMonth_M` ( `monthid` INT(10) NOT NULL AUTO_INCREMENT, `monthname` VARCHAR(64) NOT NULL, `createdBy` VARCHAR(64) NULL DEFAULT '-1', `createdDate` DATETIME NULL DEFAULT '1900-01-01', `updatedBy` VARCHAR(64) NULL DEFAULT '-1', `updatedDate` DATETIME NULL DEFAULT '1900-01-01', `versionId` INT(11) NULL DEFAULT '-1', `activeStatus` INT(1) NULL DEFAULT '1', `txnAccessCode` INT(11) NULL DEFAULT NULL, PRIMARY KEY (`monthid`));


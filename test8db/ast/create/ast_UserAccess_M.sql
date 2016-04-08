DROP TABLE IF EXISTS `ast_UserAccess_M`;

CREATE TABLE `ast_UserAccess_M` ( `userId` VARCHAR(64) NOT NULL, `region` VARCHAR(64) NOT NULL, `userAccessId` INT(10) NOT NULL AUTO_INCREMENT, `createdBy` VARCHAR(64) NULL DEFAULT '-1', `createdDate` DATETIME NULL DEFAULT '1900-01-01', `updatedBy` VARCHAR(64) NULL DEFAULT '-1', `updatedDate` DATETIME NULL DEFAULT '1900-01-01', `versionId` INT(11) NULL DEFAULT '-1', `activeStatus` INT(1) NULL DEFAULT '1', `txnAccessCode` INT(11) NULL DEFAULT NULL, PRIMARY KEY (`userAccessId`));


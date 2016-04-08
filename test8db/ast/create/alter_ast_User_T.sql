

ALTER TABLE `ast_User_T` ADD CONSTRAINT FOREIGN KEY (`userAccessDomainId`) REFERENCES `ast_UserAccessDomain_M`(`userAccessDomainId`);



ALTER TABLE `ast_User_T` ADD CONSTRAINT FOREIGN KEY (`userAccessLevelId`) REFERENCES `ast_UserAccessLevel_M`(`userAccessLevelId`);


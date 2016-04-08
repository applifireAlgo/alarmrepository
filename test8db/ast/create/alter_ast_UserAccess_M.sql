

ALTER TABLE `ast_UserAccess_M` ADD CONSTRAINT FOREIGN KEY (`userId`) REFERENCES `ast_User_T`(`userId`);



ALTER TABLE `ast_UserAccess_M` ADD CONSTRAINT FOREIGN KEY (`region`) REFERENCES `ast_SalesRegion_M`(`regioncode`);


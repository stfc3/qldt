CREATE TABLE `users` (
`user_id` bigint(20) NOT NULL AUTO_INCREMENT,
`user_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
`password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
`full_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
`email` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
`mobile` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
`user_type` int(1) NULL,
`status` int(1) NULL,
`description` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
`created_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
`updated_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
PRIMARY KEY (`user_id`) ,
INDEX `user_idx` (`user_id` ASC, `user_name` ASC, `user_type` ASC) USING BTREE
)
COMMENT = 'Bảng chứa thông tin người dùng hệ thống';

CREATE TABLE `roles` (
`role_id` bigint(20) NOT NULL AUTO_INCREMENT,
`role_code` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
`role_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
`status` int(1) NULL,
`description` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
`created_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
`updated_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
PRIMARY KEY (`role_id`) ,
INDEX `role_idx` (`role_id` ASC, `role_code` ASC) USING BTREE
)
COMMENT = 'Bảng vai trò';

CREATE TABLE `objects` (
`object_id` bigint(20) NOT NULL AUTO_INCREMENT,
`object_code` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
`object_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
`object_type` int(1) NULL COMMENT '1: menu; 2: chức năng trên màn hình',
`object_parent` bigint(20) NULL,
`object_icon` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
`app_code` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
`status` int(1) NULL,
`description` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
`created_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
`updated_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
PRIMARY KEY (`object_id`) ,
INDEX `object_idx` (`object_id` ASC, `object_code` ASC) USING BTREE
)
COMMENT = 'Bảng danh sách các chức năng hệ thống';

CREATE TABLE `user_role` (
`user_role_id` bigint(20) NOT NULL AUTO_INCREMENT,
`user_id` bigint(20) NULL,
`role_id` bigint(20) NULL,
`status` int(1) NULL,
`description` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
`created_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
`updated_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
PRIMARY KEY (`user_role_id`) ,
INDEX `user_role_idx` (`user_id` ASC, `role_id` ASC) USING BTREE
)
COMMENT = 'Bảng map người dùng với vai trò';

CREATE TABLE `role_object` (
`role_object_id` bigint(20) NOT NULL AUTO_INCREMENT,
`role_id` bigint(20) NULL,
`object_id` bigint(20) NULL,
`status` int(1) NULL,
`description` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
`created_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
`updated_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
PRIMARY KEY (`role_object_id`) ,
INDEX `role_object_idx` (`role_id` ASC, `object_id` ASC) USING BTREE
)
COMMENT = 'Bảng map vai trò với chức năng hệ thống';

/*
ALTER TABLE `user_role` ADD CONSTRAINT `fk_user_role_user_role_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`);
ALTER TABLE `user_role` ADD CONSTRAINT `fk_user_role_user_role_2` FOREIGN KEY (`role_id`) REFERENCES `roles` (`role_id`);
ALTER TABLE `role_object` ADD CONSTRAINT `fk_role_object_role_object_1` FOREIGN KEY (`role_id`) REFERENCES `roles` (`role_id`);
ALTER TABLE `role_object` ADD CONSTRAINT `fk_role_object_role_object_2` FOREIGN KEY (`object_id`) REFERENCES `objects` (`object_id`);*/


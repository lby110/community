
-- auto Generated on 2020-02-18 12:23:23 
-- DROP TABLE IF EXISTS `comment`; 
CREATE TABLE `comment`(
    `id` BIGINT (15) NOT NULL AUTO_INCREMENT COMMENT 'id',
    `user_id` BIGINT (15) NOT NULL DEFAULT -1 COMMENT 'userId',
    `parent_id` BIGINT (15) NOT NULL DEFAULT -1 COMMENT 'parentId',
    `like_count` VARCHAR (50) NOT NULL DEFAULT '' COMMENT 'likeCount',
    `gmt_create` BIGINT (15) NOT NULL DEFAULT -1 COMMENT 'gmtCreate',
    `gmt_modified` BIGINT (15) NOT NULL DEFAULT -1 COMMENT 'gmtModified',
    PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '`comment`';

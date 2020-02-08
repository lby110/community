-- auto Generated on 2020-02-08 15:41:02 
-- DROP TABLE IF EXISTS `question`; 
CREATE TABLE `question`(
    `id` BIGINT (15) NOT NULL AUTO_INCREMENT COMMENT 'id',
    `title` VARCHAR (50) NOT NULL DEFAULT '' COMMENT 'title',
    `description` VARCHAR (50) NOT NULL DEFAULT '' COMMENT 'description',
    `tag` VARCHAR (50) NOT NULL DEFAULT '' COMMENT 'tag',
    `create_user` VARCHAR (50) NOT NULL DEFAULT '' COMMENT 'createUser',
    `view_count` VARCHAR (50) NOT NULL DEFAULT '' COMMENT 'viewCount',
    `like_count` VARCHAR (50) NOT NULL DEFAULT '' COMMENT 'likeCount',
    `gmt_create` VARCHAR (50) NOT NULL DEFAULT '' COMMENT 'gmtCreate',
    `gmt_modified` VARCHAR (50) NOT NULL DEFAULT '' COMMENT 'gmtModified',
    PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '`question`';

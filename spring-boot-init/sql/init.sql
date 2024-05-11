# 数据库初始化
# @author Lzx

-- 创建库
create database if not exists springboot_init;

-- 切换库
use springboot_init;

-- 用户表
create table if not exists t_member
(
    id           bigint auto_increment comment 'id' primary key,
    account  varchar(256)                           not null comment '账号',
    password varchar(512)                           not null comment '密码',
    name     varchar(256)                           null comment '用户昵称',
    avatar   varchar(1024)                          null comment '用户头像',
    gender   varchar(1)                             null comment '用户性别，0-女，1-男，2-保密',
    profile  varchar(512)                           null comment '用户简介',
    createDate   timestamp     not null default CURRENT_TIMESTAMP comment '创建时间',
    updateDate   timestamp     not null default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete     tinyint      default 0                 not null comment '是否删除'
) comment '用户' collate = utf8mb4_unicode_ci;
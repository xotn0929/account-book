/* CREATE USER 'acb'@'localhost' IDENTIFIED BY 'test';
CREATE USER 'acb'@'%' IDENTIFIED BY 'test';


CREATE DATABASE accountbook DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci; */


/* FLUSH PRIVILEGES; */
/* GRANT ALL ON accountbook.* TO 'acb'@'localhost';
GRANT ALL ON accountbook.* TO 'acb'@'*';

USE accountbook;
 */
DROP TABLE IF EXISTS TB_USER;
CREATE TABLE TB_USER(
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '기본키',
    user_id VARCHAR(20) NOT NULL COMMENT '사용자 아이디',
    user_pw VARCHAR(1000) NOT NULL COMMENT '사용자 비밀번호',
    token VARCHAR(500) COMMENT '재인증 토큰'
) COMMENT '회원' DEFAULT charset=utf8mb4 COLLATE utf8mb4_unicode_ci;

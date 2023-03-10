DROP TABLE IF EXISTS TB_USER;
CREATE TABLE TB_USER(
    id BINARY(16) PRIMARY KEY COMMENT '기본키',
    user_name VARCHAR(50) NOT NULL COMMENT '사용자 아이디',
    password VARCHAR(1000) NOT NULL COMMENT '사용자 비밀번호',
    token VARCHAR(500) COMMENT '재인증 토큰',
    create_date TIMESTAMP COMMENT '생성일시',
    update_date TIMESTAMP COMMENT '수정일시',
    roles VARCHAR(100) COMMENT '권한'
) COMMENT '회원' DEFAULT charset=utf8mb4 COLLATE utf8mb4_unicode_ci;


DROP TABLE IF EXISTS TB_EXPENDITURE;
CREATE TABLE TB_EXPENDITURE(
    id BINARY(16) PRIMARY KEY COMMENT '기본키',
    currency VARCHAR(5) NOT NULL COMMENT '통화',
    money VARCHAR(1000) NOT NULL COMMENT '지출',
    use_date DATETIME NOT NULL COMMENT '지출일',
    memo VARCHAR(2000) COMMENT '메모',
    create_date TIMESTAMP COMMENT '생성일시',
    update_date TIMESTAMP COMMENT '수정일시',
    delete_yn VARCHAR(1) NOT NULL default 'N' COMMENT '삭제여부'
) COMMENT '지출' DEFAULT charset=utf8mb4 COLLATE utf8mb4_unicode_ci;
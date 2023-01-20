# account-book

회원가입, 로그인 로그아웃, 가계부 작성 기능 구현

1. 도메인 정의
```
1. User
    - id (BIGINT)               : 기본키
    - User ID                   : 회원 ID
    - User PW                   : 패스워드(암호화)
    - Token                     : 재인증 토큰

2. Expenditure
    - id (BIGINT)               : 기본키
    - Currency                  : 통화
    - Money                     : 지출
    - Use Date                  : 지출일
    - Memo                      : 메모
    - Create Date               : 생성일
    - Update Date               : 수정일
    - Delete YN ( default 'N' ) : 삭제여부
```

2. 도커 설정
```
1. docker-compose.yml 작성
    - resources/docker-compose.yml

    - mysql 5.7 , mysql-adminer 설정
```
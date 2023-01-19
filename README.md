# account-book

회원가입, 로그인 로그아웃, 가계부 작성 기능 구현

1. 도메인 정의
```
1. User
    - Primary Key (Long)
    - User ID
    - User PW
    - Token

2. Expenditure
    - Primary Key (Long)
    - Currency
    - Money
    - Use Date
    - Memo
    - Create Date
    - Update Date
    - Delete YN ( default 'N' )
```
# 햄버거 주문 관리 시스템

Java, JDBC, MySQL로 만드는 **콘솔 기반 CRUD 및 트랜잭션 연습 프로젝트**입니다.

## 기술 스택
- Java
- JDBC (`Connection`, `PreparedStatement`, `ResultSet`)
- MySQL (테이블 생성: MySQL Workbench)

## 구현할 기능
- [ ] 햄버거 등록 (`INSERT`)
- [ ] 햄버거 목록 조회 (`SELECT`)
- [ ] 햄버거 가격·재고 수정 (`UPDATE`)
- [ ] 햄버거 삭제 (`DELETE`)
- [ ] 주문하기: 재고 감소 + 주문 내역 저장
- [ ] 주문 내역 조회 (`JOIN`)
- [ ] 주문 처리 실패 시 `rollback()`, 성공 시 `commit()`

## 데이터베이스 준비
MySQL Workbench에서 아래 SQL을 실행합니다.

```sql
CREATE DATABASE IF NOT EXISTS burger_shop;
USE burger_shop;

CREATE TABLE burgers (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    price INT NOT NULL,
    stock INT NOT NULL
) ENGINE=InnoDB;

CREATE TABLE orders (
    id INT PRIMARY KEY AUTO_INCREMENT,
    burger_id INT NOT NULL,
    quantity INT NOT NULL,
    total_price INT NOT NULL,
    FOREIGN KEY (burger_id) REFERENCES burgers(id)
) ENGINE=InnoDB;
```

## 콘솔 메뉴 예시
```text
===== 햄버거 주문 관리 =====
1. 햄버거 등록
2. 햄버거 조회
3. 햄버거 수정
4. 햄버거 삭제
5. 주문하기
6. 주문 내역 조회
0. 종료
선택: 
```

## 주문 처리 규칙
1. 햄버거 존재 여부와 재고를 확인합니다.
2. 주문 수량만큼 재고를 차감합니다.
3. 주문 내역을 저장합니다.
4. **모두 성공하면 `commit()`**, 오류가 발생하면 **`rollback()`**합니다.

> 주문 처리 시 `setAutoCommit(false)`를 사용하고, DB 연결과 SQL 실행 자원은 `try-with-resources`로 정리합니다. 재고가 부족하면 주문을 저장하지 않습니다.

## 실행 방법
1. MySQL 서버를 실행하고 위 SQL로 DB와 테이블을 만듭니다.
2. Java 프로젝트에 MySQL Connector/J를 추가합니다.
3. JDBC 연결 URL을 `jdbc:mysql://localhost:3306/burger_shop`으로 설정합니다.
4. DB 계정 정보를 설정한 후 Java 프로그램을 실행합니다.

**목표:** 1~2일 안에 JDBC CRUD, JOIN, 트랜잭션을 직접 구현해 보고 GitHub에 올리기.

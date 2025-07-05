-- 12c 버전 이전 문법 허용 구문
ALTER SESSION SET "_ORACLE_SCRIPT" = TRUE;

-- 계정 생성 구문 (username : kh / password : kh1234)
CREATE USER todoList_boot IDENTIFIED BY todoList1234;

GRANT RESOURCE, CONNECT TO todoList_boot;
-- 사용자 계정 권한 부여 설정
-- RESOURCE : 테이블이나 인덱스 같은 DB 객체를 생성할 권한
-- CONNECT : DB에 연결하고 로그인 할 수 있는 권한

ALTER USER todoList_boot DEFAULT TABLESPACE SYSTEM QUOTA UNLIMITED ON SYSTEM ;
-- 객체가 생성될 수 있는 공간 할당량 무제한 지정
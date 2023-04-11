/* 시퀀스란?
- 유일(UNIQUE)한 값을 생성해주는 오라클 객체이다.
- 시퀀스를 생성하면 기본키와 같이 순차적으로 증가하는 컬럼을 자동적으로 생성 할 수 있다.
- 보통 PRIMARY KEY 값을 생성하기 위해 사용 한다.
- 메모리에 Cache되었을 때 시퀀스값의 액세스 효율이 증가 한다.
- 시퀀스는 테이블과는 독립적으로 저장되고 생성된다.

    ** 사용 이유 **
    재귀 쿼리를 사용하여 부서와 팀 등을 확인하는데,
    이에 대한 식별자를 생성할때 중복을 방지하기 위해 사용
*/

CREATE TABLE seq_category (
    id INT NOT NULL,
    name VARCHAR(50) NOT NULL
) COMMENT '카테고리 시퀀스';

-- 생선된 펑션 삭제
DROP FUNCTION IF EXISTS get_seq

-- 오토 크리먼트 기능 적용하기
DELIMITER $$
CREATE FUNCTION get_seq (p_name VARCHAR(45)) -- 파라미터 선언
RETURNS INT READS SQL DATA -- 반환할 데이터 타입
BEGIN
DECLARE RESULT_ID INT; -- 변수 선언
UPDATE seq_category SET id = LAST_INSERT_ID(id+1) WHERE name = p_name;
SET RESULT_ID = (SELECT LAST_INSERT_ID());
RETURN RESULT_ID;
END $$
DELIMITER ;

-- 시퀀스 생성
INSERT INTO seq_category
VALUES (0, '본부')

-- 시퀀스 삽입
get_seq('')
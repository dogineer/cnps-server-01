WITH RECURSIVE C AS (
    SELECT d.id, d.p_id, d.name AS path
    FROM dept d JOIN emp e on e.dept_id = d.id

    UNION ALL

    SELECT d.id, d.p_id, CONCAT_WS(' > ', C.path, d.name)
    FROM C
    STRAIGHT_JOIN dept d on d.p_id = C.id
)
SELECT id, p_id, path
FROM C ORDER BY path;

-- 유저의 해당 부서 찾기
SELECT d.id, d.name FROM dept d JOIN emp e on d.id = e.dept_id

-- 유저의 상위 부서 찾기

WITH RECURSIVE C AS (
    SELECT d.id, d.p_id, d.name AS path
    FROM dept d
    WHERE 8 = d.id

    UNION

    SELECT d.id, d.p_id, CONCAT_WS(' > ', C.path, d.name)

    FROM C
    JOIN dept d ON d.p_id = C.id
) SELECT path FROM C WHERE C.id = 10;

WITH RECURSIVE C AS (
    SELECT e.account, C.name, d.id, d.p_id, CONCAT_WS(' > ', C.name, d.name) AS path
    FROM dept d
    LEFT JOIN dept C ON d.p_id = C.id
    JOIN emp e ON d.id = e.dept_id
) SELECT C.account, C.path FROM C WHERE C.account = 'admin';

SELECT d.id, d.name
FROM dept d JOIN emp e ON d.id = e.dept_id WHERE e.id = 6


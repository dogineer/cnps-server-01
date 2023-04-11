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

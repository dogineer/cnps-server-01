WITH RECURSIVE C AS (
    SELECT e.account, d.id, d.p_id, d.name AS dept_detail, CONCAT_WS(' > ', d3.name, d2.name, d.name) AS dept_path
    FROM dept d
    INNER JOIN emp e ON d.id = e.dept_id
    LEFT JOIN dept d2 ON d.p_id = d2.id
    LEFT JOIN dept d3 ON d2.p_id = d3.id
)
SELECT e.name, r.name as 'rank', d.name as 'dept', C.dept_detail, t.name as team, e.birth, e.gender, e.email, e.access, e.joined_at, e.approved_at, e.del_fl
    FROM C
    JOIN emp e ON e.account = C.account
    JOIN `dept` d ON d.id = e.dept_id
    JOIN `rank` r ON r.id = e.rank_id
    JOIN `team` t ON t.id = e.team_id
WHERE e.account = 'admin';

WITH RECURSIVE C AS (
        SELECT e.account, d.id, d.p_id, d.name  AS member, CONCAT_WS(' > ',d3.name, d2.name, d.name) AS path
        FROM dept d
        INNER JOIN emp e ON d.id = e.dept_id
        LEFT JOIN dept d2 ON d.p_id = d2.id
        LEFT JOIN dept d3 ON d2.p_id = d3.id
)
SELECT C.path FROM C WHERE C.account = 'admin';

 SELECT
            id,
            member_id as memberId,
            create_at as createAt,
            title,
            program,
            name,
            phone,
            codec,
            del_fl
            FROM ingest

SELECT id, name FROM dept WHERE p_id = 1 AND id != 1


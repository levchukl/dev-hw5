SELECT 'YOUNGEST' AS type, name, birthday
FROM worker
GROUP BY birthday
HAVING birthday IN (SELECT MAX(birthday)
FROM worker)

UNION

SELECT 'ELDEST' AS type, name, birthday
FROM worker
GROUP BY birthday
HAVING birthday IN (SELECT MIN(birthday)
FROM worker)
ORDER BY birthday DESC;
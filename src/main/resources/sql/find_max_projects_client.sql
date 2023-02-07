SELECT name, count(project.id) AS project_count
FROM client
JOIN project ON client.id = project.client_id
GROUP BY name
HAVING project_count IN(
    SELECT count(id)
    FROM project
    GROUP BY client_id
    ORDER BY count(id) DESC
    LIMIT 1
);
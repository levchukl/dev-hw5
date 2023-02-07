SELECT project_id AS name, SUM(DATEDIFF(month, start_date, finish_date)*salary) AS price
FROM project_worker
JOIN project ON project_worker.project_id = project_id
JOIN worker ON project_worker.worker_id = worker_id
GROUP BY project_id
ORDER BY price DESC;
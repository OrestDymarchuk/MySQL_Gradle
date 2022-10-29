SELECT 
    'Eldest' AS 'type',
     name,
     birthday
FROM 
     worker
	 WHERE birthday = 
    (SELECT MIN(birthday) FROM worker)
    
UNION

SELECT 
    'Youngest' AS 'type',
     name,
     birthday
FROM 
    worker
	WHERE birthday = 
    (SELECT MAX(birthday)
    FROM
    worker)
    ;
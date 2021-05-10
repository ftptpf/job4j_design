/*������� � ���� ������ product_type_db*/

/*������ ��������� ���� ��������� � ����� "���"*/
SELECT p.name 
	FROM product AS p 
	JOIN type AS t 
	ON p.type_id = t.id 
	WHERE t.name = '���';

/*������ ��������� ���� ���������, � ���� � ����� ���� ����� "����������"*/
SELECT name 
	FROM product 
	WHERE name LIKE '%���������%';	

/*������ ������� ��� ��������, ���� �������� ������� ������������� � ��������� ������.
������ �������������, �.�. �� ������� �� ����������� ���������� ����������*/
SELECT name 
	FROM product
	WHERE (EXTRACT(MONTH FROM expired_date) =  EXTRACT(MONTH FROM (CURRENT_DATE + INTERVAL'1 MONTH')));	
	

/*������ ������� ����� ������� �������.*/
SELECT name
	FROM product
	WHERE price = (SELECT MAX(price) FROM product);

/*������ ������� ������� ��� ������� ���� ���������� ��������� � ���� �������������. � ���� ���_����, ����������.*/
SELECT t.name, COUNT(p.name) 
	FROM product AS p 
	JOIN type AS t 
	ON p.type_id = t.id 
	GROUP BY t.name;

/*������ ��������� ���� ��������� � ����� "���" � "������"*/
SELECT p.name 
	FROM product AS p 
	JOIN type AS t 
	ON p.type_id = t.id 
	WHERE t.name = '���' or t.name ='������';

/*������ ������� ������� ��� ���������, ������� �������� ������ 10 ����*/
SELECT t.name 
	FROM product AS p 
	JOIN type AS t 
	ON p.type_id = t.id 
	GROUP BY t.name
	HAVING COUNT(p.name) < 10;

/*������ ������� ��� �������� � �� ���*/
SELECT p.name, t.name 
	FROM product AS p 
	JOIN type AS t 
	ON p.type_id = t.id ;

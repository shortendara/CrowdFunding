

INSERT INTO users VALUES (1,'Chadwick_Lynn', 123),(2,'Ono, Yoko', 'password'),(3,'Opie, Julian', 456),(4,'Etty, William', 'pass.word'),(5,'Wallis, Henry', 654);

/*Product ID, current_raised, end_date, product_description, product_goal, product_name, start_date*/
INSERT INTO products VALUES (1, 500.50, TO_DATE('31/01/2017', 'DD/MM/YYYY'),'Emmits Light',2000.0,'Light Bulb', TO_DATE('01/12/2016', 'DD/MM/YYYY'));
INSERT INTO products VALUES	(2, 500.50,'Light Bulb',2000.0,'Emmits Light');
							
INSERT INTO users_products VALUES (1,1);

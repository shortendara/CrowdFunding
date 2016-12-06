

INSERT INTO users VALUES (1,'dara_shorten', 123),(2,'ogorman_liam', 'password'),(3,'darren_sisk', 456),(4,'king_kenny', 'pass.word');

/*Product ID, current_raised, end_date, product_description, product_goal, product_name, start_date, youtube_url*/
INSERT INTO products VALUES (1, 500.50, TO_DATE('31/01/2017', 'DD/MM/YYYY'),'Emmits Light',2000.0,'Light Bulb', TO_DATE('01/12/2016', 'DD/MM/YYYY'), 'http://youtube.com/embed/ClHu3XQBnGU');
INSERT INTO products VALUES (2, 300, TO_DATE('15/01/2017', 'DD/MM/YYYY'), 'Form of transportation', 10000.0,'Car', TO_DATE('12/09/2016', 'DD/MM/YYYY'), 'http://youtube.com/embed/ClHu3XQBnGU');
							
INSERT INTO users_products VALUES (1,1);

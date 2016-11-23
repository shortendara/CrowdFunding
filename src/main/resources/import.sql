CREATE TABLE USERS ( 
  id int(11) NOT NULL AUTO_INCREMENT, 
  USERNAME varchar(100) NOT NULL, 
  PASSWORD varchar(10) NOT NULL, 
  PRIMARY KEY (id) 
);

INSERT INTO users VALUES (1,'Chadwick_Lynn'),(2,'Ono, Yoko'),(3,'Opie, Julian'),(4,'Etty, William'),(5,'Wallis, Henry');

INSERT INTO products VALUES (1, 500.50,'Light Bulb',2000.0,'Emmits Light');
INSERT INTO products VALUES	(2, 500.50,'Light Bulb',2000.0,'Emmits Light');
							
INSERT INTO users_products VALUES (1,1);

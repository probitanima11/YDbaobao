Create Table PRODUCTIMAGES(
 imageId int PRIMARY KEY AUTO_INCREMENT,
 productId int NOT NULL,
 imageName varchar(200) NOT NULL,
 Foreign Key(productId) REFERENCES PRODUCTS(productId) on delete cascade
);

ALTER TABLE STOCKS MODIFY size varchar(50);
ALTER TABLE STOCKS MODIFY quantity int DEFAULT 0;

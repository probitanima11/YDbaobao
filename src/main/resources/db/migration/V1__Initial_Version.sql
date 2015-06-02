drop database YDBAOBAO;

drop user 'ydbaobao'@'localhost';

/* DB 생성 */

Create DATABASE YDBAOBAO DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

/* User 생성 및 DB권한 주기 */
Create User ydbaobao@'localhost' identified by 'ydbaobao';

Grant all privileges on YDBAOBAO.* to ydbaobao@'localhost' identified by 'ydbaobao';

/* Table 생성 */
use YDBAOBAO;

Create Table GRADES(
 gradeId int PRIMARY KEY NOT NULL,
 discount int NOT NULL
);


Create Table CUSTOMERS(
 customerId varchar(50) PRIMARY KEY NOT NULL,
 customerName varchar(50) NOT NULL,
 customerPassword varchar(50) NOT NULL,
 gradeId int DEFAULT 6,
 customerPhone varchar(50) NOT NULL,
 customerEmail varchar(50),
 customerAddress varchar(100),
 customerCreateDate DATETIME DEFAULT CURRENT_TIMESTAMP,
 customerUpdateDate DATETIME DEFAULT CURRENT_TIMESTAMP,
 Foreign Key(gradeId) REFERENCES GRADES(gradeId)
);

Create Table BRANDS(
 brandId int PRIMARY KEY AUTO_INCREMENT,
 brandName varchar(50) NOT NULL
);

Create Table CATEGORY(
 categoryId int PRIMARY KEY AUTO_INCREMENT,
 categoryName varchar(50) NOT NULL
);

Create Table PRODUCTS(
 productId int PRIMARY KEY AUTO_INCREMENT,
 productName varchar(50) NOT NULL,
 categoryId int,
 brandId int,
 productPrice int NOT NULL,
 productImage varchar(50) NOT NULL,
 productDescription text NOT NULL,
 productCreateDate DATETIME DEFAULT CURRENT_TIMESTAMP,
 productUpdateDate DATETIME DEFAULT CURRENT_TIMESTAMP,
 Foreign Key(categoryId) REFERENCES CATEGORY(categoryId),
 Foreign Key(brandId) REFERENCES BRANDS(brandId) on delete cascade
);

Create Table STOCKS(
 stockId int PRIMARY KEY AUTO_INCREMENT,
 productId int,
 size varchar(50) NOT NULL,
 quantity int NOT NULL DEFAULT 0,
 productUpdateDate DATETIME DEFAULT CURRENT_TIMESTAMP,
 Foreign Key(productId) REFERENCES PRODUCTS(productId) on delete cascade
);


Create Table ORDERS(
 orderId int PRIMARY KEY AUTO_INCREMENT,
 orderStatus varchar(50) NOT NULL,
 customerId varchar(50),
 enuri int,
 realPrice int NOT NULL,
 orderAddress varchar(100) NOT NULL,
 orderCreateDate DATETIME DEFAULT CURRENT_TIMESTAMP,
 orderUpdateDate DATETIME DEFAULT CURRENT_TIMESTAMP,
 Foreign Key(customerId) REFERENCES CUSTOMERS(customerId) on delete cascade
);

Create Table ITEMS(
 itemId int PRIMARY KEY AUTO_INCREMENT,
 productId int,
 orderId int,
 size varchar(50) NOT NULL,
 quantity int NOT NULL,
 Foreign Key(orderId) REFERENCES ORDERS(orderId) on delete cascade,
 Foreign Key(productId) REFERENCES PRODUCTS(productId) on delete cascade
);

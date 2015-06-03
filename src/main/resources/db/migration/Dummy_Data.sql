/* CATEGORY Table Dummy Data */

insert into CATEGORY(categoryName) values('상의일반');
insert into CATEGORY(categoryName) values('하의일반');
insert into CATEGORY(categoryName) values('티셔츠');
insert into CATEGORY(categoryName) values('아우터');
insert into CATEGORY(categoryName) values('반바지');
insert into CATEGORY(categoryName) values('멜빵바지');
insert into CATEGORY(categoryName) values('원피스');
insert into CATEGORY(categoryName) values('스커트');
insert into CATEGORY(categoryName) values('상하정장');
insert into CATEGORY(categoryName) values('양말/스타킹');
insert into CATEGORY(categoryName) values('속옷/수영복');
insert into CATEGORY(categoryName) values('액세서리');
insert into CATEGORY(categoryName) values('신발');


/* BRAND Table Dummy Data */
insert into BRANDS(brandName) values('a1');
insert into BRANDS(brandName) values('a2');
insert into BRANDS(brandName) values('a3');
insert into BRANDS(brandName) values('a4');
insert into BRANDS(brandName) values('a5');
insert into BRANDS(brandName) values('a6');
insert into BRANDS(brandName) values('a7');
insert into BRANDS(brandName) values('a8');

insert into BRANDS(brandName) values('b1');
insert into BRANDS(brandName) values('b2');
insert into BRANDS(brandName) values('b3');
insert into BRANDS(brandName) values('b4');
insert into BRANDS(brandName) values('b5');
insert into BRANDS(brandName) values('b6');
insert into BRANDS(brandName) values('b7');
insert into BRANDS(brandName) values('b8');

insert into BRANDS(brandName) values('c1');
insert into BRANDS(brandName) values('c2');
insert into BRANDS(brandName) values('c3');
insert into BRANDS(brandName) values('c4');
insert into BRANDS(brandName) values('c5');
insert into BRANDS(brandName) values('c6');
insert into BRANDS(brandName) values('c7');
insert into BRANDS(brandName) values('c8');

insert into BRANDS(brandName) values('d1');
insert into BRANDS(brandName) values('d2');
insert into BRANDS(brandName) values('d3');
insert into BRANDS(brandName) values('d4');
insert into BRANDS(brandName) values('d5');
insert into BRANDS(brandName) values('d6');
insert into BRANDS(brandName) values('d7');
insert into BRANDS(brandName) values('d8');

insert into BRANDS(brandName) values('a1');
insert into BRANDS(brandName) values('a2');
insert into BRANDS(brandName) values('a3');
insert into BRANDS(brandName) values('a4');
insert into BRANDS(brandName) values('a5');
insert into BRANDS(brandName) values('a6');
insert into BRANDS(brandName) values('a7');
insert into BRANDS(brandName) values('a8');

insert into CUSTOMERS values("tester", "테스터", "1234", 1, "01011112222", "test@yopmail.com", "경기도 분당 삼평동", default, default);

insert into CUSTOMERS values("test", "테스터", "1234", 0, "01011112222", "test@yopmail.com", "경기도 분당 삼평동", default, default);

insert into PRODUCTS (productName, categoryId, brandId, productPrice, productImage, productDescription, productCreateDate, productUpdateDate)values("상의1", "1", "1", 1000, "location", "이쁜옷", default, default);

insert into PRODUCTS (productName, categoryId, brandId, productPrice, productImage, productDescription, productCreateDate, productUpdateDate)values("상의2", "1", "2", 5000, "location", "이쁜옷", default, default);

insert into PRODUCTS (productName, categoryId, brandId, productPrice, productImage, productDescription, productCreateDate, productUpdateDate)values("하의1", "2", "3", 1000, "location", "이쁜옷", default, default);

insert into PRODUCTS (productName, categoryId, brandId, productPrice, productImage, productDescription, productCreateDate, productUpdateDate)values("하의2", "2", "4", 1000, "location", "이쁜옷", default, default);

insert into PRODUCTS (productName, categoryId, brandId, productPrice, productImage, productDescription, productCreateDate, productUpdateDate)values("티셔츠1", "3", "5", 1000, "location", "이쁜옷", default, default);

insert into PRODUCTS (productName, categoryId, brandId, productPrice, productImage, productDescription, productCreateDate, productUpdateDate)values("티셔츠2", "3", "6", 1000, "location", "이쁜옷", default, default);

insert into PRODUCTS (productName, categoryId, brandId, productPrice, productImage, productDescription, productCreateDate, productUpdateDate)values("아우터1", "4", "7", 1000, "location", "이쁜옷", default, default);

insert into PRODUCTS (productName, categoryId, brandId, productPrice, productImage, productDescription, productCreateDate, productUpdateDate)values("아우터2", "4", "8", 1000, "location", "이쁜옷", default, default);

insert into PRODUCTS (productName, categoryId, brandId, productPrice, productImage, productDescription, productCreateDate, productUpdateDate)values("반바지1", "5", "9", 1000, "location", "이쁜옷", default, default);

insert into PRODUCTS (productName, categoryId, brandId, productPrice, productImage, productDescription, productCreateDate, productUpdateDate)values("반바지2", "5", "10", 1000, "location", "이쁜옷", default, default);

insert into PRODUCTS (productName, categoryId, brandId, productPrice, productImage, productDescription, productCreateDate, productUpdateDate)values("멜빵바지1", "6", "11", 1000, "location", "이쁜옷", default, default);

insert into PRODUCTS (productName, categoryId, brandId, productPrice, productImage, productDescription, productCreateDate, productUpdateDate)values("멜빵바지2", "6", "12", 1000, "location", "이쁜옷", default, default);

insert into PRODUCTS (productName, categoryId, brandId, productPrice, productImage, productDescription, productCreateDate, productUpdateDate)values("원피스1", "7", "13", 1000, "location", "이쁜옷", default, default);

insert into PRODUCTS (productName, categoryId, brandId, productPrice, productImage, productDescription, productCreateDate, productUpdateDate)values("원피스2", "7", "14", 1000, "location", "이쁜옷", default, default);

insert into PRODUCTS (productName, categoryId, brandId, productPrice, productImage, productDescription, productCreateDate, productUpdateDate)values("스커트1", "8", "15", 1000, "location", "이쁜옷", default, default);

insert into PRODUCTS (productName, categoryId, brandId, productPrice, productImage, productDescription, productCreateDate, productUpdateDate)values("스커트2", "8", "16", 1000, "location", "이쁜옷", default, default);

insert into STOCKS (productId, size, quantity, productUpdateDate) values("1", "S", 100, default);
insert into STOCKS (productId, size, quantity, productUpdateDate) values("1", "M", 50, default);
insert into STOCKS (productId, size, quantity, productUpdateDate) values("1", "XL", 30, default);
insert into STOCKS (productId, size, quantity, productUpdateDate) values("2", "S", 100, default);
insert into STOCKS (productId, size, quantity, productUpdateDate) values("2", "M", 50, default);
insert into STOCKS (productId, size, quantity, productUpdateDate) values("2", "XL", 30, default);

insert into ORDERS (orderStatus, customerId, enuri, realPrice, orderAddress, orderCreateDate, orderUpdateDate) values("입금완료", "test", 1000, 100000, "경기도 분당 삼평동", default, default);
insert into ORDERS (orderStatus, customerId, enuri, realPrice, orderAddress, orderCreateDate, orderUpdateDate) values("입금대기", "test", 500, 30000, "경기도 분당 서현동", default, default);

insert into ITEMS (productId, orderId, size, quantity) values(1, 1, "S", 5);
insert into ITEMS (productId, orderId, size, quantity) values(1, null, "S", 5);

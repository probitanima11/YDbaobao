Create Table PRODUCTS_STOCKS(
 productId int,
 stockId int,
 Foreign Key(stockId) REFERENCES STOCKS(stockId),
 Foreign Key(productId) REFERENCES PRODUCTS(productId) on delete cascade,
 Primary Key(productId, stockId)
);

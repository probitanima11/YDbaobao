SET PASSWORD FOR 'ydbaobao'@'127.0.0.1' = 'ydbaobao';

ALTER TABLE CUSTOMERS alter column customerCreateDate set default '';
ALTER TABLE CUSTOMERS alter column customerUpdateDate set default '';
ALTER TABLE PRODUCTS alter column productCreateDate set default '';
ALTER TABLE PRODUCTS alter column productUpdateDate set default '';
ALTER TABLE PAYMENTS alter column paymentDate set default '';
ALTER TABLE ORDERS alter column orderCreateDate set default '';
ALTER TABLE ORDERS alter column orderUpdateDate set default '';
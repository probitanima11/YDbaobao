ALTER TABLE PRODUCTS add column isSoldout int(1) default 0;

update PRODUCTS set isSoldout = 0;
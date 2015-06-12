ALTER TABLE BRANDS add column (
	discount_1 int(8) default 0,
	discount_2 int(8) default 0,
	discount_3 int(8) default 0,
	discount_4 int(8) default 0,
	discount_5 int(8) default 0
);

update BRANDS set discount_1 = 0, discount_2 = 0, discount_3 = 0, discount_4 = 0, discount_5 = 0;

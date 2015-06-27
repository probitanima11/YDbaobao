alter table ITEMS drop foreign key items_ibfk_2;
alter table ITEMS drop orderId;
alter table ITEMS add itemStatus varchar(50);
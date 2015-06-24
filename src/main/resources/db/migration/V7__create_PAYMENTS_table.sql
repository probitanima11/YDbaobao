CREATE TABLE PAYMENTS (
  paymentId int(11) unsigned NOT NULL AUTO_INCREMENT,
  customerId varchar(50) NOT NULL,
  paymentType varchar(12) NOT NULL,
  amount int NOT NULL DEFAULT 0,
  paymentDate DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (paymentId)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

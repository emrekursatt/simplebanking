CREATE TABLE `account` (
  `id` int NOT NULL AUTO_INCREMENT,
  `owner` varchar(255)  NOT NULL,
  `account_number` varchar(255) NOT NULL,
  `balance` double NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uq_account_number` (`account_number`)
) ENGINE=InnoDB;

CREATE TABLE `deposit_transaction` (
  `id` int NOT NULL AUTO_INCREMENT,
  `amount` double NOT NULL,
  `approval_code` varchar(255)  DEFAULT NULL,
  `date` datetime(6) DEFAULT NULL,
  `type` varchar(255)  DEFAULT NULL,
  `account_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_deposit_transaction_account_id` (`account_id`),
  CONSTRAINT `fk_deposit_transaction_account_id` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`)
) ENGINE=InnoDB;

CREATE TABLE `phone_bill_payment_transaction` (
  `id` int NOT NULL AUTO_INCREMENT,
  `phone_number` varchar(20)  NOT NULL,
  `amount` double DEFAULT NULL,
  `approval_code` varchar(255)  DEFAULT NULL,
  `date` datetime(6) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `account_id` int NOT NULL,
  `payee` varchar(255)  DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_phone_bill_payment_transaction_account_id` (`account_id`),
  CONSTRAINT `fk_phone_bill_payment_transaction_account_id` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`)
) ENGINE=InnoDB;

CREATE TABLE `bill_payment_transaction` (
  `id` int NOT NULL AUTO_INCREMENT,
  `payee` varchar(255) NOT NULL,
  `amount` double NOT NULL,
  `approval_code` varchar(255)  DEFAULT NULL,
  `date` datetime(6) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `account_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_bill_payment_transaction_account_id` (`account_id`),
  CONSTRAINT `fk_bill_payment_transaction_account_id` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`)
) ENGINE=InnoDB;

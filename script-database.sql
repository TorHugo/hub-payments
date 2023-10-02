DROP TABLE IF EXISTS payment_tb;
DROP TABLE IF EXISTS customer_tb;
DROP TABLE IF EXISTS form_payment_tb;
DROP TABLE IF EXISTS credit_card_tb;
DROP TABLE IF EXISTS flag_card_tb;
DROP TABLE IF EXISTS user_tb;
DROP TABLE IF EXISTS store_tb;

-- ====================== START ====================== --
CREATE TABLE store_tb (
	store_id	SERIAL NOT NULL,
	email 		VARCHAR(500),
	password 	VARCHAR(20),
	cpf_or_cnpj	VARCHAR(14) NOT NULL,
	creat_at 	TIMESTAMPTZ DEFAULT Now(),
	update_at 	TIMESTAMP
);
ALTER TABLE ONLY store_tb
	ADD CONSTRAINT pk_store_tb PRIMARY KEY (store_id);

CREATE INDEX IX01_cnpj
ON store_tb (cpf_or_cnpj);
-- ====================== END ====================== -- 	
-- ====================== START ====================== --
CREATE TABLE user_tb (
	user_id 	SERIAL NOT NULL,
	store_id	BIGINT NOT NULL,
	email 		VARCHAR(500) NOT NULL,
	cpf_or_cnpj VARCHAR(14),
	first_name 	VARCHAR(50),
	last_name 	VARCHAR(50),
	phone 		VARCHAR(15),
	creat_at 	TIMESTAMPTZ DEFAULT Now(),
	update_at 	TIMESTAMP
);
ALTER TABLE ONLY user_tb
	ADD CONSTRAINT pk_user_tb PRIMARY KEY (user_id);
ALTER TABLE ONLY user_tb
	ADD CONSTRAINT fk_user_x_store FOREIGN KEY (store_id) REFERENCES store_tb;
CREATE INDEX IX01_store_x_email
ON user_tb (store_id, email);
-- ====================== END ====================== --
-- ====================== START ====================== --
CREATE TABLE form_payment_tb (
	form_payment_id 	SERIAL NOT NULL,
	name_form_payment 	VARCHAR(50),
	description_form_payment VARCHAR(500),
	creat_at 			TIMESTAMPTZ DEFAULT Now(),
	update_at 			TIMESTAMP
);
ALTER TABLE ONLY form_payment_tb
	ADD CONSTRAINT pk_form_payment_tb PRIMARY KEY (form_payment_id);
-- ====================== END ====================== --
-- ====================== START ====================== --
CREATE TABLE flag_card_tb (
	flag_card_id 	SERIAL NOT NULL,
	name_flag_card	VARCHAR(500),
	creat_at 		TIMESTAMPTZ DEFAULT Now(),
	update_at 		TIMESTAMP
);
ALTER TABLE ONLY flag_card_tb
	ADD CONSTRAINT pk_flag_card_tb PRIMARY KEY (flag_card_id);
-- ====================== END ====================== --
-- ====================== START ====================== --
CREATE TABLE credit_card_tb (
	credit_card_id	SERIAL NOT NULL,
	flag_card_id	BIGINT NOT NULL,
	card_token		VARCHAR(500),
	number			VARCHAR(4),
	creat_at 		TIMESTAMPTZ DEFAULT Now(),
	update_at 		TIMESTAMP
);
ALTER TABLE ONLY credit_card_tb
	ADD CONSTRAINT pk_credit_card_tb PRIMARY KEY (credit_card_id);
ALTER TABLE ONLY credit_card_tb
	ADD CONSTRAINT fk_credit_card_x_flag_card FOREIGN KEY (flag_card_id) REFERENCES flag_card_tb;
-- ====================== END ====================== --
-- ====================== START ====================== --
CREATE TABLE customer_tb (
	customer_id 	VARCHAR(500) NOT NULL,
	user_id 		BIGINT NOT NULL,
	form_payment_id BIGINT,
	credit_card_id	BIGINT,
	in_active		BOOLEAN NOT NULL,
	creat_at 		TIMESTAMPTZ DEFAULT Now(),
	update_at 		TIMESTAMP
);
ALTER TABLE ONLY customer_tb
	ADD CONSTRAINT pk_customer_tb PRIMARY KEY (customer_id);
ALTER TABLE ONLY customer_tb
	ADD CONSTRAINT fk_customer_x_form_payment FOREIGN KEY (form_payment_id) REFERENCES form_payment_tb;	
ALTER TABLE ONLY customer_tb
	ADD CONSTRAINT fk_customer_x_credit_card FOREIGN KEY (credit_card_id) REFERENCES credit_card_tb;	
ALTER TABLE ONLY customer_tb
	ADD CONSTRAINT fk_customer_x_user FOREIGN KEY (user_id) REFERENCES user_tb;
-- ====================== END ====================== --
-- ====================== START ====================== --
CREATE TABLE payment_tb (
	payment_id			VARCHAR(500) NOT NULL,
	customer_id			VARCHAR(500) NOT NULL,
	store_id			BIGINT NOT NULL,
	value				FLOAT NOT NULL,
	status				VARCHAR(50) NOT NULL,
	description			VARCHAR(500),
	due_date			DATE NOT NULL,
	external_reference 	VARCHAR(500),
	date_created		DATE,
	form_payment_id		INT,
	creat_at 			TIMESTAMPTZ DEFAULT Now(),
	update_at 			TIMESTAMP
);
ALTER TABLE ONLY payment_tb
	ADD CONSTRAINT pk_payment_tb PRIMARY KEY (payment_id);
ALTER TABLE ONLY payment_tb
	ADD CONSTRAINT fk_payment_x_customer FOREIGN KEY (customer_id) REFERENCES customer_tb;	
ALTER TABLE ONLY payment_tb
	ADD CONSTRAINT fk_payment_x_store FOREIGN KEY (store_id) REFERENCES store_tb;	
ALTER TABLE ONLY payment_tb
	ADD CONSTRAINT fk_payment_x_form_payment FOREIGN KEY (form_payment_id) REFERENCES form_payment_tb;

CREATE INDEX IX01_customer_x_store_x_external_reference_x_value
ON payment_tb (customer_id, store_id, external_reference, value);
-- ====================== END ====================== --
-- ====================== START ====================== --
INSERT INTO flag_card_tb (name_flag_card)
VALUES 	('VISA'), ('MASTERCARD'), ('ELO');

INSERT INTO form_payment_tb (name_form_payment,	description_form_payment)
VALUES ('CreditCard', 'Representation form payment of CreditCard.');
-- ====================== END ====================== --


SELECT * FROM store_tb
SELECT * FROM user_tb
SELECT * FROM customer_tb
SELECT * FROM payment_tb

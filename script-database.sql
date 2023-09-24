DROP TABLE IF EXISTS customer_tb;
DROP TABLE IF EXISTS form_payment_tb;
DROP TABLE IF EXISTS credit_card_tb;
DROP TABLE IF EXISTS flag_card_tb;
DROP TABLE IF EXISTS user_tb;

CREATE TABLE user_tb (
	user_id 	SERIAL NOT NULL,
	email 		VARCHAR(500),
	password 	VARCHAR(20),
	cpf_or_cnpj VARCHAR(14),
	first_name 	VARCHAR(50),
	last_name 	VARCHAR(50),
	phone 		VARCHAR(15),
	creat_at 	TIMESTAMPTZ DEFAULT Now(),
	update_at 	TIMESTAMP
);

CREATE TABLE form_payment_tb (
	form_payment_id 	SERIAL NOT NULL,
	name_form_payment 	VARCHAR(50),
	description_form_payment VARCHAR(500),
	creat_at 			TIMESTAMPTZ DEFAULT Now(),
	update_at 			TIMESTAMP
);

CREATE TABLE flag_card_tb (
	flag_card_id 	SERIAL NOT NULL,
	name_flag_card	VARCHAR(500),
	creat_at 		TIMESTAMPTZ DEFAULT Now(),
	update_at 		TIMESTAMP
);

CREATE TABLE credit_card_tb (
	credit_card_id	SERIAL NOT NULL,
	flag_card_id	BIGINT NOT NULL,
	card_token		VARCHAR(500),
	creat_at 		TIMESTAMPTZ DEFAULT Now(),
	update_at 		TIMESTAMP
);

CREATE TABLE customer_tb (
	customer_id 	VARCHAR(500) NOT NULL,
	user_id 		BIGINT NOT NULL,
	form_payment_id BIGINT NOT NULL,
	credit_card_id	BIGINT NOT NULL,
	in_active		INT NOT NULL,
	creat_at 		TIMESTAMPTZ DEFAULT Now(),
	update_at 		TIMESTAMP
);

ALTER TABLE ONLY user_tb
	ADD CONSTRAINT pk_user_tb PRIMARY KEY (user_id);
	
ALTER TABLE ONLY form_payment_tb
	ADD CONSTRAINT pk_form_payment_tb PRIMARY KEY (form_payment_id);
	
ALTER TABLE ONLY flag_card_tb
	ADD CONSTRAINT pk_flag_card_tb PRIMARY KEY (flag_card_id);

ALTER TABLE ONLY credit_card_tb
	ADD CONSTRAINT pk_credit_card_tb PRIMARY KEY (credit_card_id);
	
ALTER TABLE ONLY customer_tb
	ADD CONSTRAINT pk_customer_tb PRIMARY KEY (customer_id);
	
ALTER TABLE ONLY credit_card_tb
	ADD CONSTRAINT fk_credit_card_x_flag_card FOREIGN KEY (flag_card_id) REFERENCES flag_card_tb;
	
ALTER TABLE ONLY customer_tb
	ADD CONSTRAINT fk_customer_x_form_payment FOREIGN KEY (form_payment_id) REFERENCES form_payment_tb;
	
ALTER TABLE ONLY customer_tb
	ADD CONSTRAINT fk_customer_x_credit_card FOREIGN KEY (credit_card_id) REFERENCES credit_card_tb;
	
ALTER TABLE ONLY customer_tb
	ADD CONSTRAINT fk_customer_x_user FOREIGN KEY (user_id) REFERENCES user_tb;
	
SELECT * FROM user_tb;

INSERT INTO \
	user_tb (email \
			 , password \
			 , cpf_or_cnpj \
			 , first_name \
			 , last_name \
			 , phone) \
VALUES ( \
	:email \
	, :password \
	, :cpfOrCnpj \
	, :firstName \
	, :lastName \
	, :phone \ 
)

SELECT  \
	user_id 		AS userId \
	, email \
	, password \
	, cpf_or_cnpj 	AS cpfOrCnpj \
	, first_name	AS fisrtName \
	, last_name		AS lastName \
	, phone \
	, creat_at		AS creatAt \
	, update_at		AS updateAt \
FROM user_tb;
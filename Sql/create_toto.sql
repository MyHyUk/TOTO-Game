
CREATE TABLE Animal
(
	Ani_code             Number NOT NULL ,
	ani_name             VARCHAR2(40) NULL ,
	ani_speed            Number NULL 
);



CREATE UNIQUE INDEX PK_Animal_Ani_code ON Animal
(Ani_code   ASC);



ALTER TABLE Animal
	ADD CONSTRAINT  PK_Animal_Ani_code PRIMARY KEY (Ani_code);



CREATE TABLE Bank
(
	bank_code            Number NOT NULL ,
	bank_name            VARCHAR2(40) NULL 
);



CREATE UNIQUE INDEX PK_Bank_bank_code ON Bank
(bank_code   ASC);



ALTER TABLE Bank
	ADD CONSTRAINT  PK_Bank_bank_code PRIMARY KEY (bank_code);



CREATE TABLE Coupon
(
	cp_code              Number NOT NULL ,
	cp_name              VARCHAR2(40) NULL ,
	cp_price             Number NULL 
);



CREATE UNIQUE INDEX PK_Coupon_cp_code ON Coupon
(cp_code   ASC);



ALTER TABLE Coupon
	ADD CONSTRAINT  PK_Coupon_cp_code PRIMARY KEY (cp_code);



CREATE TABLE Coupon_List
(
	cp_list_code         Number NOT NULL ,
	cp_code              Number NULL ,
	cust_id              varchar2(40) NULL ,
	cp_list_startDate    VARCHAR2(40) NULL ,
	cp_list_endDate      VARCHAR2(40) NULL ,
	cp_list_YN           VARCHAR2(40) NULL 
);



CREATE UNIQUE INDEX PK_Coupon_List_cp_list_code ON Coupon_List
(cp_list_code   ASC);



ALTER TABLE Coupon_List
	ADD CONSTRAINT  PK_Coupon_List_cp_list_code PRIMARY KEY (cp_list_code);



CREATE TABLE Customer
(
	cust_id              varchar2(40) NOT NULL ,
	cust_pw              VARCHAR2(40) NULL ,
	cust_name            VARCHAR2(40) NULL ,
	cust_tel             VARCHAR2(40) NULL ,
	cust_adr             VARCHAR2(40) NULL ,
	cust_exPw            VARCHAR2(40) NULL ,
	cust_money           Number NULL ,
	cust_accNum          VARCHAR2(40) NULL ,
	bank_code            Number NULL ,
	cust_point           Number NULL 
);



CREATE UNIQUE INDEX PK_Customer_cust_id ON Customer
(cust_id   ASC);



ALTER TABLE Customer
	ADD CONSTRAINT  PK_Customer_cust_id PRIMARY KEY (cust_id);



CREATE TABLE EPL
(
	epl_code             Number NOT NULL ,
	epl_date             VARCHAR2(40) NULL ,
	epl_bet_code         Number NULL ,
	winner_code          Number NULL 
);



CREATE UNIQUE INDEX PK_EPL_epl_code ON EPL
(epl_code   ASC);



ALTER TABLE EPL
	ADD CONSTRAINT  PK_EPL_epl_code PRIMARY KEY (epl_code);



CREATE TABLE EPL_Bet
(
	epl_bet_code         Number NOT NULL ,
	cust_id              varchar2(40) NULL ,
	epl_bet_price        Number NULL ,
	epl_bet_YN           VARCHAR2(40) NULL ,
	epl_bet_multiple     Number NULL ,
	epl_bet_date         VARCHAR2(40) NULL ,
	epl_bet_prize        Number NULL ,
	team_code            Number NULL 
);



CREATE UNIQUE INDEX PK_EPL_Bet_epl_bet_code ON EPL_Bet
(epl_bet_code   ASC);



ALTER TABLE EPL_Bet
	ADD CONSTRAINT  PK_EPL_Bet_epl_bet_code PRIMARY KEY (epl_bet_code);



CREATE TABLE Exchange
(
	ex_code              Number NOT NULL ,
	ex_money             Number NULL ,
	cust_id              varchar2(40) NULL ,
	ex_date              VARCHAR2(40) NULL 
);



CREATE UNIQUE INDEX PK_Exchange_ex_code ON Exchange
(ex_code   ASC);



ALTER TABLE Exchange
	ADD CONSTRAINT  PK_Exchange_ex_code PRIMARY KEY (ex_code);



CREATE TABLE KBO
(
	kbo_code             Number NOT NULL ,
	kbo_date             VARCHAR2(40) NULL ,
	winner_code          Number NULL ,
	kbo_bet_code         Number NULL 
);



CREATE UNIQUE INDEX PK_KBO_kbo_code ON KBO
(kbo_code   ASC);



ALTER TABLE KBO
	ADD CONSTRAINT  PK_KBO_kbo_code PRIMARY KEY (kbo_code);



CREATE TABLE KBO_Bet
(
	kbo_bet_code         Number NOT NULL ,
	cust_id              varchar2(40) NULL ,
	kbo_bet_price        Number NULL ,
	kbo_bet_YN           VARCHAR2(40) NULL ,
	kbo_bet_multiple     Number NULL ,
	kbo_bet_date         VARCHAR2(40) NULL ,
	kbo_bet_prize        Number NULL ,
	team_code            Number NULL 
);



CREATE UNIQUE INDEX PK_KBO_Bet_kbo_bet_code ON KBO_Bet
(kbo_bet_code   ASC);



ALTER TABLE KBO_Bet
	ADD CONSTRAINT  PK_KBO_Bet_kbo_bet_code PRIMARY KEY (kbo_bet_code);



CREATE TABLE Ladder
(
	ladder_code          Number NOT NULL ,
	ladder_type          VARCHAR2(40) NULL 
);



CREATE UNIQUE INDEX PK_Ladder_ladder_code ON Ladder
(ladder_code   ASC);



ALTER TABLE Ladder
	ADD CONSTRAINT  PK_Ladder_ladder_code PRIMARY KEY (ladder_code);



CREATE TABLE Ladder_Bet
(
	ladder_bet_code      Number NOT NULL ,
	ladder_bet_price     Number NULL ,
	ladder_bet_YN        VARCHAR2(40) NULL ,
	ladder_code          Number NOT NULL ,
	cust_id              varchar2(40) NOT NULL ,
	ladder_bet_date      VARCHAR2(40) NULL ,
	ladder_bet_mulitple  Number NULL ,
	ladder_bet_prize     Number NULL 
);



CREATE UNIQUE INDEX PK_Ladder_Bet_ladder_bet_code ON Ladder_Bet
(ladder_bet_code   ASC);



ALTER TABLE Ladder_Bet
	ADD CONSTRAINT  PK_Ladder_Bet_ladder_bet_code PRIMARY KEY (ladder_bet_code);



CREATE TABLE Lotto
(
	lotto_code           Number NOT NULL ,
	lotto_basicNum       VARCHAR2(40) NULL ,
	lotto_bonusNum       VARCHAR2(40) NULL ,
	lotto_totalPrice     Number NULL ,
	lotto_YN             VARCHAR2(40) NULL ,
	lotto_date           VARCHAR2(40) NULL 
);



CREATE UNIQUE INDEX PK_Lotto_lotto_code ON Lotto
(lotto_code   ASC);



ALTER TABLE Lotto
	ADD CONSTRAINT  PK_Lotto_lotto_code PRIMARY KEY (lotto_code);



CREATE TABLE Lotto_Bet
(
	lotto_bet_code       Number NOT NULL ,
	cust_id              varchar2(40) NULL ,
	lotto_code           Number NOT NULL 
);



CREATE UNIQUE INDEX PK_Lotto_Bet_lotto_bet_code ON Lotto_Bet
(lotto_bet_code   ASC);



ALTER TABLE Lotto_Bet
	ADD CONSTRAINT  PK_Lotto_Bet_lotto_bet_code PRIMARY KEY (lotto_bet_code);



CREATE TABLE Racing
(
	winner_code          Number NULL ,
	racing_bet_code      Number NULL ,
	racing_code          Number NOT NULL 
);



CREATE UNIQUE INDEX PK_Racing_racing_code ON Racing
(racing_code   ASC);



ALTER TABLE Racing
	ADD CONSTRAINT  PK_Racing_racing_code PRIMARY KEY (racing_code);



CREATE TABLE Racing_Bet
(
	racing_bet_code      Number NOT NULL ,
	racing_bet_price     Number NULL ,
	racing_bet_YN        VARCHAR2(40) NULL ,
	racing_bet_multiple  Number NULL ,
	cust_id              varchar2(40) NULL ,
	Ani_code             Number NULL ,
	racing_bet_date      VARCHAR2(40) NULL ,
	racing_bet_prize     Number NULL 
);



CREATE UNIQUE INDEX PK_Racing_Bet_racing_bet_code ON Racing_Bet
(racing_bet_code   ASC);



ALTER TABLE Racing_Bet
	ADD CONSTRAINT  PK_Racing_Bet_racing_bet_code PRIMARY KEY (racing_bet_code);



CREATE TABLE Recharge
(
	rc_code              Number NOT NULL ,
	rc_price             Number NULL ,
	cust_id              varchar2(40) NULL ,
	rc_date              VARCHAR2(40) NULL 
);



CREATE UNIQUE INDEX PK_Recharge_rc_code ON Recharge
(rc_code   ASC);



ALTER TABLE Recharge
	ADD CONSTRAINT  PK_Recharge_rc_code PRIMARY KEY (rc_code);



CREATE TABLE Team
(
	team_code            Number NOT NULL ,
	team_name            VARCHAR2(40) NULL ,
	team_winRate         Number NULL 
);



CREATE UNIQUE INDEX PK_Team_team_code ON Team
(team_code   ASC);



ALTER TABLE Team
	ADD CONSTRAINT  PK_Team_team_code PRIMARY KEY (team_code);



ALTER TABLE Coupon_List
	ADD (CONSTRAINT FK_Coupon_List_cp_code FOREIGN KEY (cp_code) REFERENCES Coupon (cp_code) ON DELETE SET NULL);



ALTER TABLE Coupon_List
	ADD (CONSTRAINT FK_Coupon_List_cust_id FOREIGN KEY (cust_id) REFERENCES Customer (cust_id) ON DELETE SET NULL);



ALTER TABLE Customer
	ADD (CONSTRAINT FK_Customer_bank_code FOREIGN KEY (bank_code) REFERENCES Bank (bank_code) ON DELETE SET NULL);



ALTER TABLE EPL
	ADD (CONSTRAINT FK_EPL_epl_bet_code FOREIGN KEY (epl_bet_code) REFERENCES EPL_Bet (epl_bet_code) ON DELETE SET NULL);



ALTER TABLE EPL
	ADD (CONSTRAINT FK_EPL_winner_code FOREIGN KEY (winner_code) REFERENCES Team (team_code) ON DELETE SET NULL);



ALTER TABLE EPL_Bet
	ADD (CONSTRAINT FK_EPL_Bet_cust_id FOREIGN KEY (cust_id) REFERENCES Customer (cust_id) ON DELETE SET NULL);



ALTER TABLE EPL_Bet
	ADD (CONSTRAINT FK_EPL_Bet_team_code FOREIGN KEY (team_code) REFERENCES Team (team_code) ON DELETE SET NULL);



ALTER TABLE Exchange
	ADD (CONSTRAINT FK_Exchange_cust_id FOREIGN KEY (cust_id) REFERENCES Customer (cust_id) ON DELETE SET NULL);



ALTER TABLE KBO
	ADD (CONSTRAINT FK_KBO_winner_code FOREIGN KEY (winner_code) REFERENCES Team (team_code) ON DELETE SET NULL);



ALTER TABLE KBO
	ADD (CONSTRAINT FK_KBO_kbo_bet_code FOREIGN KEY (kbo_bet_code) REFERENCES KBO_Bet (kbo_bet_code) ON DELETE SET NULL);



ALTER TABLE KBO_Bet
	ADD (CONSTRAINT FK_KBO_Bet_cust_id FOREIGN KEY (cust_id) REFERENCES Customer (cust_id) ON DELETE SET NULL);



ALTER TABLE KBO_Bet
	ADD (CONSTRAINT FK_KBO_Bet_team_code FOREIGN KEY (team_code) REFERENCES Team (team_code) ON DELETE SET NULL);



ALTER TABLE Ladder_Bet
	ADD (CONSTRAINT FK_Ladder_Bet_ladder_code FOREIGN KEY (ladder_code) REFERENCES Ladder (ladder_code));



ALTER TABLE Ladder_Bet
	ADD (CONSTRAINT FK_Ladder_Bet_cust_id FOREIGN KEY (cust_id) REFERENCES Customer (cust_id));



ALTER TABLE Lotto_Bet
	ADD (CONSTRAINT FK_Lotto_Bet_cust_id FOREIGN KEY (cust_id) REFERENCES Customer (cust_id) ON DELETE SET NULL);



ALTER TABLE Lotto_Bet
	ADD (CONSTRAINT FK_Lotto_Bet_lotto_code FOREIGN KEY (lotto_code) REFERENCES Lotto (lotto_code));



ALTER TABLE Racing
	ADD (CONSTRAINT FK_Racing_winner_code FOREIGN KEY (winner_code) REFERENCES Animal (Ani_code) ON DELETE SET NULL);



ALTER TABLE Racing
	ADD (CONSTRAINT FK_Racing_racing_bet_code FOREIGN KEY (racing_bet_code) REFERENCES Racing_Bet (racing_bet_code) ON DELETE SET NULL);



ALTER TABLE Racing_Bet
	ADD (CONSTRAINT FK_Racing_Bet_cust_id FOREIGN KEY (cust_id) REFERENCES Customer (cust_id) ON DELETE SET NULL);



ALTER TABLE Racing_Bet
	ADD (CONSTRAINT FK_Racing_Bet_Ani_code FOREIGN KEY (Ani_code) REFERENCES Animal (Ani_code) ON DELETE SET NULL);



ALTER TABLE Recharge
	ADD (CONSTRAINT FK_Recharge_cust_id FOREIGN KEY (cust_id) REFERENCES Customer (cust_id) ON DELETE SET NULL);



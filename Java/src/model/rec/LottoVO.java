package model.rec;

public class  LottoVO{ //로또게임 원시테이블 코드

   private int lotto_code;
   private String lotto_date;
   
   private String lotto_basicNum; //4개 숫자
   
   private int totalPrice; // 당첨금액
   private int battingMoney; // 로또 한번 할때마다 5천포인트 삭감.
   
   private String lotto_YN;
   
   private String num1,num2,num3,num4,num5; //로또번호 받아오기
   private String cust_id; // 회원아이디

public String getCust_id() {
	return cust_id;
}

public void setCust_id(String cust_id) {
	this.cust_id = cust_id;
}

public String getNum1() {
	return num1;
}

public void setNum1(String num1) {
	this.num1 = num1;
}

public String getNum2() {
	return num2;
}

public void setNum2(String num2) {
	this.num2 = num2;
}

public String getNum3() {
	return num3;
}

public void setNum3(String num3) {
	this.num3 = num3;
}

public String getNum4() {
	return num4;
}

public void setNum4(String num4) {
	this.num4 = num4;
}

public String getNum5() {
	return num5;
}

public void setNum5(String num5) {
	this.num5 = num5;
}

public int getLotto_code() {
	return lotto_code;
}

public void setLotto_code(int lotto_code) {
	this.lotto_code = lotto_code;
}

public String getLotto_date() {
	return lotto_date;
}

public void setLotto_date(String lotto_date) {
	this.lotto_date = lotto_date;
}

public String getLotto_basicNum() {
	return lotto_basicNum;
}

public void setLotto_basicNum(String lotto_basicNum) {
	this.lotto_basicNum = lotto_basicNum;
}

public int getTotalPrice() {
	return totalPrice;
}

public void setTotalPrice(int totalPrice) {
	this.totalPrice = totalPrice;
}

public int getBattingMoney() {
	return battingMoney;
}

public void setBattingMoney(int battingMoney) {
	this.battingMoney = battingMoney;
}

public String getLotto_YN() {
	return lotto_YN;
}

public void setLotto_YN(String lotto_YN) {
	this.lotto_YN = lotto_YN;
}
   
   

   
   
   
}

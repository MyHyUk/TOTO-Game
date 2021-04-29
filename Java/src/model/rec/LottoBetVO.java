package model.rec;

public class LottoBetVO {  //로또배팅
	
	private int lotto_bet_code; // 배팅 시퀀스 번호
	private int lotto_code; // 로또게임 코드
	
	private String cust_id; //회원 아이디

	public int getLotto_bet_code() {
		return lotto_bet_code;
	}

	public void setLotto_bet_code(int lotto_bet_code) {
		this.lotto_bet_code = lotto_bet_code;
	}

	public int getLotto_code() {
		return lotto_code;
	}

	public void setLotto_code(int lotto_code) {
		this.lotto_code = lotto_code;
	}

	public String getCust_id() {
		return cust_id;
	}

	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
	}
	
	
}

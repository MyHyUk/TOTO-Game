package model.rec;

public class LottoBetVO {  //�ζǹ���
	
	private int lotto_bet_code; // ���� ������ ��ȣ
	private int lotto_code; // �ζǰ��� �ڵ�
	
	private String cust_id; //ȸ�� ���̵�

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

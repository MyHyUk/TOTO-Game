package model.rec;

//table : Ladder_Bet(��ٸ����� ���̺�)

public class Ladder_BetVO {

	private Integer ladder_bet_code; // ��ٸ������ڵ�
	private String ladder_bet_date; //��ٸ� ���ó�¥
	private Integer ladder_bet_price; //���ñݾ�
	private double ladder_bet_multiple;// ��ٸ����ù���
	private Integer ladder_bet_prize; //���߱ݾ�
	private String ladder_bet_YN; //��ٸ����ô�÷����
	private String cust_id; // �����̵�
	private Integer ladder_code;// ��ٸ� �ڵ��ȣ
	private Integer user_price;//������� ���纸���ݾ�
	
	public Integer getUser_price() {
		return user_price;
	}
	public void setUser_price(Integer user_price) {
		this.user_price = user_price;
	}
	public Integer getLadder_bet_code() {
		return ladder_bet_code;
	}
	public void setLadder_bet_code(Integer ladder_bet_code) {
		this.ladder_bet_code = ladder_bet_code;
	}
	public String getLadder_bet_date() {
		return ladder_bet_date;
	}
	public void setLadder_bet_date(String ladder_bet_date) {
		this.ladder_bet_date = ladder_bet_date;
	}
	public Integer getLadder_bet_price() {
		return ladder_bet_price;
	}
	public void setLadder_bet_price(Integer ladder_bet_price) {
		this.ladder_bet_price = ladder_bet_price;
	}
	public double getLadder_bet_multiple() {
		return ladder_bet_multiple;
	}
	public void setLadder_bet_multiple(double ladder_bet_multiple) {
		this.ladder_bet_multiple = ladder_bet_multiple;
	}
	public Integer getLadder_bet_prize() {
		return ladder_bet_prize;
	}
	public void setLadder_bet_prize(Integer ladder_bet_prize) {
		this.ladder_bet_prize = ladder_bet_prize;
	}
	public String getLadder_bet_YN() {
		return ladder_bet_YN;
	}
	public void setLadder_bet_YN(String ladder_bet_YN) {
		this.ladder_bet_YN = ladder_bet_YN;
	}
	public String getCust_id() {
		return cust_id;
	}
	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
	}
	public Integer getLadder_code() {
		return ladder_code;
	}
	public void setLadder_code(Integer ladder_code) {
		this.ladder_code = ladder_code;
	}
	




}

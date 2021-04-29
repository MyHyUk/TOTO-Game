package model.rec;

public class CustomerVO {
	private String	cust_id;
	private String	cust_pw;
	private String 	cust_exPw;
	private String 	cust_name;
	private String 	cust_tel;
	private String 	cust_adr;
	private int		cust_money;
	private int		cust_point;
	private String	cust_accNum;
	private int		bank_code;
	
	public int getCust_point() {
		return cust_point;
	}
	public void setCust_point(int cust_point) {
		this.cust_point = cust_point;
	}
	public String getCust_id() {
		return cust_id;
	}
	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
	}
	public String getCust_pw() {
		return cust_pw;
	}
	public void setCust_pw(String cust_pw) {
		this.cust_pw = cust_pw;
	}
	public String getCust_exPw() {
		return cust_exPw;
	}
	public void setCust_exPw(String cust_exPw) {
		this.cust_exPw = cust_exPw;
	}
	public String getCust_name() {
		return cust_name;
	}
	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}
	public String getCust_tel() {
		return cust_tel;
	}
	public void setCust_tel(String cust_tel) {
		this.cust_tel = cust_tel;
	}
	public String getCust_adr() {
		return cust_adr;
	}
	public void setCust_adr(String cust_adr) {
		this.cust_adr = cust_adr;
	}
	public int getCust_money() {
		return cust_money;
	}
	public void setCust_money(int cust_money) {
		this.cust_money = cust_money;
	}
	public String getCust_accNum() {
		return cust_accNum;
	}
	public void setCust_accNum(String cust_accNum) {
		this.cust_accNum = cust_accNum;
	}
	public int getBank_code() {
		return bank_code;
	}
	public void setBank_code(int bank_code) {
		this.bank_code = bank_code;
	}

}

package model.rec;

public class ExchangeVO {
	private int		ex_code;
	private int		ex_money;
	private String	cust_id;
	
	public int getEx_code() {
		return ex_code;
	}
	public void setEx_code(int ex_code) {
		this.ex_code = ex_code;
	}
	public int getEx_money() {
		return ex_money;
	}
	public void setEx_money(int ex_money) {
		this.ex_money = ex_money;
	}
	public String getCust_id() {
		return cust_id;
	}
	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
	}
}

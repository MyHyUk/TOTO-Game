package model;

import java.sql.*;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import model.rec.CustomerVO;

public class CustomerModel {

	Connection con;

	public CustomerModel() throws Exception {
		connectDB();
	}

	private void connectDB() throws Exception {

		con = ConnectionPool.getConnection();
	}

	// ���̵�� ����Ʈ ��ȸ
	public int getPoint(String id) throws SQLException {
		int point = -1;
		String sql = "SELECT cust_point FROM customer WHERE cust_id = ?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, id);
		ResultSet rs = st.executeQuery();
		if (rs.next())
			point = rs.getInt("cust_point");

		rs.close();
		st.close();

		

		return point;
	}

	// ���̵�� �ܾ� ��ȸ
	public int getMoney(String id) throws SQLException {
		int money = -1;
		String sql = "SELECT cust_money FROM customer WHERE cust_id = ?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, id);
		ResultSet rs = st.executeQuery();
		if (rs.next())
			money = rs.getInt("cust_money");

		rs.close();
		st.close();


		return money;
	}

	// ������ü ������ ������ ���¿� �Ա��ϱ�
	public void deposit(String id, int money) throws SQLException {
		String sql = "UPDATE customer SET cust_money = cust_money + ? WHERE cust_accNum = ?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, money);
		st.setString(2, "110358477203"); //�����ڰ��¹�ȣ
		int rowNum = st.executeUpdate();
		if (rowNum > 0)
			JOptionPane.showMessageDialog(null, id+"�� ���¿���\n" + String.format("%,d", money) + "�� ������ ���·� �ԱݿϷ�!!");
		else
			JOptionPane.showMessageDialog(null, "�Աݽ���..");
		st.close();


	}

	// ����Ʈ ����
	public void recharge(String id, int point, double bonus) throws SQLException {
		String sql = "UPDATE customer SET cust_point = cust_point + ? + ? , cust_money = cust_money - ? WHERE cust_id = ?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, point);
		st.setInt(2, (int) (point * bonus));
		st.setInt(3, point);
		st.setString(4, id);
		int rowNum = st.executeUpdate();
		if (rowNum > 0)
			JOptionPane.showMessageDialog(null, String.format("%,d", point + (int) (point * bonus)) + "P �����Ϸ�!!");
		else
			JOptionPane.showMessageDialog(null, "��������..");

		st.close();


	}

	// ����Ʈ ȯ��
	public void exchange(String id, int point) throws SQLException {
		String sql = "UPDATE customer SET cust_money = cust_money + ? , cust_point = cust_point - ? WHERE cust_id = ?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, point);
		st.setInt(2, point);
		st.setString(3, id);
		int rowNum = st.executeUpdate();
		if (rowNum > 0)
			JOptionPane.showMessageDialog(null, String.format("%,d", point) + "P ȯ���Ϸ�!!");
		else
			JOptionPane.showMessageDialog(null, "ȯ������..");

		st.close();

	}

	// ��������� �����ڵ� ��������
	public int getBankCode(String bankName) throws SQLException {
		int result = -1;
		String sql = "SELECT bank_code FROM bank WHERE Bank_name = ?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, bankName);
		ResultSet rs = st.executeQuery();
		if (rs.next())
			result = rs.getInt("bank_Code");

		rs.close();
		st.close();


		return result;
	}

	// ȸ�������� �μ�Ʈ
	public void regist(CustomerVO cust) throws Exception {
		String sql = "INSERT INTO CUSTOMER(bank_code,cust_accNum,cust_adr,cust_exPw,cust_id,cust_name,cust_Pw,cust_tel,cust_money,cust_point)"
				+ " values(?,?,?, ?,?,?, ?,?,0,0)";
		PreparedStatement st = con.prepareStatement(sql);

		st.setInt(1, cust.getBank_code());
		st.setString(2, cust.getCust_accNum());
		st.setString(3, cust.getCust_adr());

		st.setString(4, cust.getCust_exPw());
		st.setString(5, cust.getCust_id());
		st.setString(6, cust.getCust_name());

		st.setString(7, cust.getCust_pw());
		st.setString(8, cust.getCust_tel());

		int rowNum = st.executeUpdate();
		if (rowNum > 0)
			JOptionPane.showMessageDialog(null, "��� �Ϸ�");
		else
			JOptionPane.showMessageDialog(null, "��� ����");

		st.close();


	}

	// ȸ������ ������Ʈ
	public void modify(CustomerVO cust, String current_id) throws Exception {
		String sql = "UPDATE CUSTOMER SET bank_code = ? cust_accNum=?,cust_adr=?,cust_exPw=?,cust_id=?,cust_name=?,cust_Pw=?,cust_tel=?"
				+ " WHERE cust_id = ?";
		PreparedStatement st = con.prepareStatement(sql);

		st.setInt(1, cust.getBank_code());
		st.setString(2, cust.getCust_accNum());
		st.setString(3, cust.getCust_adr());

		st.setString(4, cust.getCust_exPw());
		st.setString(5, cust.getCust_id());
		st.setString(6, cust.getCust_name());

		st.setString(7, cust.getCust_pw());
		st.setString(8, cust.getCust_tel());
		st.setString(9, current_id);

		int rowNum = st.executeUpdate();
		if (rowNum > 0) 
			JOptionPane.showMessageDialog(null, "�����Ϸ�");
		else
			JOptionPane.showMessageDialog(null, "��������");
		st.close();


	}

	// ȸ�� ���� ����
	public void delete(String id) throws Exception {
		String sql = "DELETE FROM customer WHERE cust_id = ?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, id);
		int rowNum = st.executeUpdate();
		if (rowNum > 0)
			JOptionPane.showMessageDialog(null, "�����Ϸ�");
		else
			JOptionPane.showMessageDialog(null, "��������");

		st.close();


	}

	// �̸��� ����ó�� ���� ���̵� ã��
	public void searchID(String name, String tel) throws Exception {

		String sql = "SELECT cust_id FROM CUSTOMER WHERE cust_name = ?  and cust_tel = ? ";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, name);
		st.setString(2, tel);
		ResultSet rs = st.executeQuery();
		if (rs.next()) {
			String id = new String();
			String pw = new String();
			id = rs.getString("cust_id");
			JOptionPane.showMessageDialog(null, "���̵�� " + id + " �Դϴ�.");
			pw = JOptionPane.showInputDialog("���ο� ��й�ȣ �Է�");
			sql = "UPDATE CUSTOMER SET cust_pw = ? WHERE cust_id = ?";
			st = con.prepareStatement(sql);
			st.setString(1, pw);
			st.setString(2, id);
			int rowNum = st.executeUpdate();
			if (rowNum > 0)
				JOptionPane.showMessageDialog(null, "���漺��");
			else
				JOptionPane.showMessageDialog(null, "�������");
		} else {
			JOptionPane.showMessageDialog(null, "�ش� ������ ���������ʽ��ϴ�.");
		}

		rs.close();
		st.close();

	}

	// ȸ�� ���̵� �����ϴ��� üũ
	public boolean checkLogin(String id) throws SQLException {
		String sql = "SELECT * FROM customer WHERE cust_id = ? ";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, id);
		int rowNum = st.executeUpdate();
		if (rowNum > 0)
			return true;
		return false;

	}

	// ȸ�� ���̵�� ��й�ȣ�� �´��� üũ
	public boolean checkLogin(String id, String pw) throws SQLException {
		String sql = "SELECT * FROM customer WHERE cust_id = ? and cust_pw = ?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, id);
		st.setString(2, pw);
		int rowNum = st.executeUpdate();
		st.close();

		//con.close();

		if (rowNum > 0)
			return true;
		return false;

	}

	// ���ฮ��Ʈ �ҷ�����
	public ArrayList loadBankList() throws SQLException {
		// TODO Auto-generated method stub
		ArrayList list = new ArrayList();
		String sql = "SELECT * FROM BANK";
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		while (rs.next()) {
			list.add(rs.getString("bank_name"));
		}

		st.close();
		rs.close();

		return list;
	}

	public String getBankName(int code) throws SQLException {
		String sql = "SELECT bank_name FROM bank WHERE bank_code = ?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, code);
		ResultSet rs = st.executeQuery();
		String name = null;
		if (rs.next())
			name = rs.getString(1);

		st.close();
		rs.close();


		return name;
	}

	public ArrayList getInfo(String id) throws SQLException {
		// TODO Auto-generated method stub
		ArrayList list = new ArrayList();
		String sql = "SELECT * FROM customer WHERE cust_id = ?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, id);
		ResultSet rs = st.executeQuery();
		if (rs.next()) {
			list.add(rs.getString("cust_ID"));
			list.add(rs.getString("cust_PW"));
			list.add(rs.getString("cust_ExPW"));
			list.add(rs.getString("cust_Name"));
			list.add(rs.getString("cust_Tel"));
			list.add(rs.getString("cust_Adr"));
			list.add(rs.getInt("cust_Money"));
			list.add(rs.getInt("cust_Point"));
			list.add(rs.getString("cust_AccNum"));
			list.add(getBankName(rs.getInt("bank_code")));

		}

		st.close();
		rs.close();

		return list;
	}
	
	//�����ݾ� DB�� insert
	public void getRecharge(String id, int price) throws SQLException {
		String cust_id = id;
		int rc_price = price;

		
		String sql = "insert into recharge(rc_code, rc_price, cust_id, rc_date)"
				+ " values(SEQ_RC_CODE.nextval,?,?,sysdate)";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, rc_price);
		ps.setString(2, cust_id);
		
		ps.executeUpdate();
		ps.close();
		System.out.println("�Է��� �Ϸ�Ǿ����ϴ�");
	}
	
	//ȯ���ݾ� DB�� insert
	public void getExchange(String id, int price)throws SQLException {
		String cust_id = id;
		int ex_price = price;

		
		String sql = "insert into exchange(ex_code, ex_money, cust_id, ex_date)"
				+ " values(SEQ_ex_CODE.nextval,?,?,sysdate)";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, ex_price);
		ps.setString(2, cust_id);
		
		ps.executeUpdate();
		ps.close();
		System.out.println("�Է��� �Ϸ�Ǿ����ϴ�");
	}

	public void rechargeByPhone(String tel, int sumPrice) throws SQLException {
		String sql1 = "UPDATE customer SET cust_money = cust_money + ? WHERE cust_accNum = ?";
		PreparedStatement st1 = con.prepareStatement(sql1);
		st1.setInt(1, sumPrice);	
		st1.setString(2, "110358477203"); //�����ڰ��¹�ȣ
		int rowNum1 = st1.executeUpdate();
		
		String sql2 = "UPDATE customer SET cust_money = cust_money - ? WHERE cust_tel = ?";
		PreparedStatement st2 = con.prepareStatement(sql2);
		st2.setInt(1, sumPrice);	
		st2.setString(2, tel);
		int rowNum2 = st2.executeUpdate();
		if (rowNum1 > 0 && rowNum2 > 0)
			JOptionPane.showMessageDialog(null, tel + "��ȣ�� ��������\n" +String.format("%,d", sumPrice) + "�����Ϸ�!");
		else
			JOptionPane.showMessageDialog(null, "��������..");

		st1.close();
		st2.close();
		
	}

}

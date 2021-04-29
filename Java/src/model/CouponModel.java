package model;

import java.awt.Window.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import javax.swing.JOptionPane;

public class CouponModel {

	Connection con;

	public CouponModel() throws Exception {
		connectDB();
	}

	private void connectDB() throws Exception {
		con = ConnectionPool.getConnection();
	}


	
	//��ȸ���� �����߱� �޼���
	public void registCoupon(String coupon_name)  throws SQLException{
		String sql = "INSERT INTO coupon_List(cp_list_code,				cp_code,cp_list_startDate,	cp_list_endDate,cp_list_YN) "
				+ "						VALUES(seq_cp_list_code.nextval,?,		sysdate,			sysdate+15,		?)";
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, getCouponCode(coupon_name));
		st.setString(2,"N");

		int rowNum = st.executeUpdate();
		if(rowNum > 0){
			String sqlSelect = "SELECT max(cp_list_code) FROM coupon_list WHERE cust_id is NULL";
			Statement stSelect = con.createStatement();
			ResultSet rsSelect = stSelect.executeQuery(sqlSelect);
			int cp_list_code = -1;
			if(rsSelect.next())
				cp_list_code = rsSelect.getInt(1);
			rsSelect.close();
			stSelect.close();
			JOptionPane.showMessageDialog(null, coupon_name + "���� �߱޿Ϸ�!\n ������ȣ : " + cp_list_code);
		}
		else
			JOptionPane.showMessageDialog(null, "�����߱޽���");
		
		st.close();
		
		
	}

	// ȸ���� �����߱� �޼���
	public void registCoupon(String id, String coupon_name) throws SQLException {
		String sql = "INSERT INTO coupon_List(cp_list_code,cp_code,cust_id,cp_list_startDate,cp_list_endDate,cp_list_YN) "
				+ "						VALUES(seq_cp_list_code.nextval,?,?,sysdate,sysdate+15,?)";
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, getCouponCode(coupon_name));
		st.setString(2, id);
		st.setString(3, "N");

		int rowNum = st.executeUpdate();
		if (rowNum > 0)
			JOptionPane.showMessageDialog(null, "������ �߱޵Ǿ����ϴ�.");
		else
			JOptionPane.showMessageDialog(null, "�����߱޽���");
		st.close();

	}

	// ���̵�� ����� �����ڵ带 �Է¹޾� ����ϴ� �޼���
	public double useCoupon(String id, int cp_list_code) throws SQLException {
		System.out.println(id + cp_list_code);
		String sql = "UPDATE coupon_list SET cp_list_YN = 'Y' ,cust_id = ? WHERE cp_list_code = ? AND to_date(cp_list_endDate,'YY/MM/DD') > sysdate AND cp_list_YN = 'N' " ;
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, id);
		st.setInt(2, cp_list_code);

		int rowNum = st.executeUpdate();
		if (rowNum > 0) {
			String sqlSelect = "SELECT c.cp_price FROM coupon c , coupon_list cl  WHERE c.cp_code = cl.cp_code AND cl.cp_list_code = ?";
			PreparedStatement stSelect = con.prepareStatement(sqlSelect);
			stSelect.setInt(1, cp_list_code);
			ResultSet rsSelect = stSelect.executeQuery();
			double price = -1;
			if(rsSelect.next()){
				price = rsSelect.getDouble(1);
				JOptionPane.showMessageDialog(null, price+"%�߰� ���� ���� ���Ϸ�!");
			}
			rsSelect.close();
			stSelect.close();
			return price;
		} else
			JOptionPane.showMessageDialog(null, "��밡���� ������ �����ϴ�.");
		st.close();
		return 0;
	}
		
	//�����̸��� ���� �����ڵ带 ��ȯ�ϴ� �޼���
	public int getCouponCode(String coupon_name) throws SQLException{
		int code = -1;
		String sql = "SELECT cp_code FROM coupon WHERE cp_name = ?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, coupon_name);
		ResultSet rs = st.executeQuery();
		if(rs.next())
			code = rs.getInt("cp_code");
		rs.close();
		st.close();
		return code;
	}
	
	
	
	
}

package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.rec.LottoBetVO;

public class LottoModel {
        
	 private Connection con;
	 
	 
	 public LottoModel() throws Exception {
		 
		 con = ConnectionPool.getConnection();
	 }
	 
	 //�� �ܾ׺��� Ȯ�ΰ����� ����?
	 public int yourMoney(String cust_id) throws Exception{
		 
	  int yourMoney =0;
		 
		 String sql ="select cust_point from customer where cust_id = ?";
		 
		  PreparedStatement ps = con.prepareStatement(sql); 
		 
		 ps.setString(1,cust_id); //�̸�ã��
         ResultSet result = ps.executeQuery(); 
         
   	  if(result.next()){
   	 //�÷��̸��� ������ش�.
   	    yourMoney = result.getInt("cust_point");
   	  }
   	 
		 
   	 ps.close();
   	 result.close();
   	 
   	 
	 return yourMoney;
	 }
	 
	  //���̵�� �����ܾ��� �޾ƿ´�
	 public void updateMoney(String cust_id,int yourMoney) throws Exception
	 { 
		 //�ζǴ� �����Ҷ����� õ���� ����
		 int lostMoney = yourMoney - 1000;
		 
		 String sql = "update customer set cust_point = ? where cust_id = ?";
		 
		 PreparedStatement ps = con.prepareStatement(sql);
			
	     ps.setInt(1, lostMoney); // ���� update
	     ps.setString(2,cust_id); // ���̵� �˻��ؼ�
	  	 
		 ps.executeQuery();		 
	   	 ps.close();
	   
		 
	 }
	 public void jackpot(String cust_id,int yourMoney,int lottoMoney) throws Exception
	 { 
		 //�ζǴ� �����Ҷ����� õ���� ����
		 int money = yourMoney + lottoMoney;
		 
		 String sql = "update customer set cust_point = ? where cust_id = ?";
		 
		 PreparedStatement ps = con.prepareStatement(sql);
			
	     ps.setInt(1, money); // ���� update
	     ps.setString(2,cust_id); // ���̵� �˻��ؼ�
	  	 
		 ps.executeQuery();		 
	   	 ps.close();
	   
		 
	 }
	 
	 
	 
	 //Lotto �������̺� ������ �޼ҵ�
	  public void lotto_insert(String lotto_number,int lotto_price) throws Exception{
		  // �ڵ�� ����Ʈ�� ���� �������� sysdate�� 
		  String sql = "insert into lotto(lotto_code,lotto_basicNum,lotto_totalprice,lotto_date) values(seq_lotto_code.nextval,?,?,sysdate)";
			
			 PreparedStatement ps = con.prepareStatement(sql);
		  
			 ps.setString(1,lotto_number);
			 ps.setInt(2,lotto_price);
		     
		     ps.executeUpdate();// ����!
		     ps.close();
		    
		     }
	 
	 //�ζ� ��Ʈ ���̺��� �ζ� ��������ȣ �޾ƿ���
	 public LottoBetVO seq(String user_id) throws Exception{
		 
		 LottoBetVO lottobet = new LottoBetVO();
		 
		 String sql = "select lb.lotto_code from lotto lb where "
		 		+ "not exists(select lo.lotto_code from lotto_bet lo where lb.lotto_code = lo.lotto_code  and cust_id =  ?)";
		 
		 PreparedStatement ps = con.prepareStatement(sql);
		 ps.setString(1,user_id);
		 
		 ResultSet rs = ps.executeQuery();
		 
		 if(rs.next()){
			 lottobet.setLotto_code(rs.getInt("lotto_code"));
		 }
		 
		 ps.close();
		 rs.close();
		
		 return lottobet;
	 }
	 
	 public void lottoBetInsert(String user_id, int seq,String yn) throws Exception{
		 
		 String sql = "insert into lotto_bet(lotto_bet_code,cust_id,lotto_code,lotto_bet_yn)"
		 		+ "values(seq_lotto_bet_code.nextval,?,?,?)";
		 
		 
		 PreparedStatement ps = con.prepareStatement(sql);
		  
		 ps.setString(1,user_id);
		 ps.setInt(2,seq);
	     ps.setString(3,yn);
		 
	     ps.executeUpdate();// ����!
	     ps.close();

	 }
	 
	 
	 
	 
	 
	 
	 
	 
	 
}

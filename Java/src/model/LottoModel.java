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
	 
	 //니 잔액보유 확인가능함 ㅇㅋ?
	 public int yourMoney(String cust_id) throws Exception{
		 
	  int yourMoney =0;
		 
		 String sql ="select cust_point from customer where cust_id = ?";
		 
		  PreparedStatement ps = con.prepareStatement(sql); 
		 
		 ps.setString(1,cust_id); //이름찾는
         ResultSet result = ps.executeQuery(); 
         
   	  if(result.next()){
   	 //컬럼이름을 명시해준다.
   	    yourMoney = result.getInt("cust_point");
   	  }
   	 
		 
   	 ps.close();
   	 result.close();
   	 
   	 
	 return yourMoney;
	 }
	 
	  //아이디와 현재잔액을 받아온다
	 public void updateMoney(String cust_id,int yourMoney) throws Exception
	 { 
		 //로또는 실행할때마다 천원이 깎임
		 int lostMoney = yourMoney - 1000;
		 
		 String sql = "update customer set cust_point = ? where cust_id = ?";
		 
		 PreparedStatement ps = con.prepareStatement(sql);
			
	     ps.setInt(1, lostMoney); // 돈을 update
	     ps.setString(2,cust_id); // 아이디를 검색해서
	  	 
		 ps.executeQuery();		 
	   	 ps.close();
	   
		 
	 }
	 public void jackpot(String cust_id,int yourMoney,int lottoMoney) throws Exception
	 { 
		 //로또는 실행할때마다 천원이 깎임
		 int money = yourMoney + lottoMoney;
		 
		 String sql = "update customer set cust_point = ? where cust_id = ?";
		 
		 PreparedStatement ps = con.prepareStatement(sql);
			
	     ps.setInt(1, money); // 돈을 update
	     ps.setString(2,cust_id); // 아이디를 검색해서
	  	 
		 ps.executeQuery();		 
	   	 ps.close();
	   
		 
	 }
	 
	 
	 
	 //Lotto 원시테이블 저장할 메소드
	  public void lotto_insert(String lotto_number,int lotto_price) throws Exception{
		  // 코드와 데이트는 각각 시퀀스와 sysdate로 
		  String sql = "insert into lotto(lotto_code,lotto_basicNum,lotto_totalprice,lotto_date) values(seq_lotto_code.nextval,?,?,sysdate)";
			
			 PreparedStatement ps = con.prepareStatement(sql);
		  
			 ps.setString(1,lotto_number);
			 ps.setInt(2,lotto_price);
		     
		     ps.executeUpdate();// 실행!
		     ps.close();
		    
		     }
	 
	 //로또 배트 테이블에서 로또 시퀀스번호 받아오기
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
		 
	     ps.executeUpdate();// 실행!
	     ps.close();

	 }
	 
	 
	 
	 
	 
	 
	 
	 
	 
}

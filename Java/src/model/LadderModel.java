package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.naming.spi.DirStateFactory.Result;
import model.rec.CustomerVO;
import model.rec.Ladder_BetVO;
import model.*;
import model.rec.*;
import view.*;

public class LadderModel {

	public LadderModel() throws Exception {
		connectDB();
	}

	Connection con;

	void connectDB() throws Exception {

		con = ConnectionPool.getConnection();
	}

	// 사용자 현재 보유금액 리턴(point)
	public int user_price(String user_id) throws Exception {
		String id = user_id;

		String sql = "select cust_point from customer where cust_id=?";

		PreparedStatement pstmt = con.prepareStatement(sql);

		pstmt.setString(1, id);

		ResultSet rs = pstmt.executeQuery();
		CustomerVO cu = new CustomerVO();

		if (rs.next()) {

			return (rs.getInt("cust_point"));

		} else
			return 0;

	}
	
	
	
	//사다리배팅내역 insert(win, 하나 적중시) 
	  public void w_ladder_type1(Ladder_BetVO lbVO) throws Exception{
		  int bet_price = lbVO.getLadder_bet_price(); //배팅금액
		  int bet_prize = lbVO.getLadder_bet_prize(); //적중금액
		  String cust_id = lbVO.getCust_id();   //회원ID
		  int ladder_code = lbVO.getLadder_code(); //사다리코드
		
		  
		  String sql = "insert into ladder_bet(ladder_bet_code,ladder_bet_price,ladder_bet_date,ladder_bet_multiple, "
		  		+ "ladder_bet_prize,ladder_bet_YN,cust_id,ladder_code)"
		  		+ "values(seq_ladder_bet_code.nextval,?,sysdate,1.97,?,'Y',?,?)";
		  
		  PreparedStatement ps = con.prepareStatement(sql);
		  ps.setInt(1,bet_price); //ladder_bet_price
		  ps.setInt(2, bet_prize);//ladder_bet_prize
		  ps.setString(3,cust_id);//cust_id
		  ps.setInt(4, ladder_code);//ladder_code
		  
		  ps.executeUpdate();
		  ps.close();
		  
		  System.out.println("+"+bet_prize +" : 입력성공");
	}
		//사다리배팅내역 insert(win, 두개 적중시) 
	  public void w_ladder_type2(Ladder_BetVO lbVO) throws Exception{
		  int bet_price = lbVO.getLadder_bet_price(); //배팅금액
		  int bet_prize = lbVO.getLadder_bet_prize(); //적중금액
		  String cust_id = lbVO.getCust_id();   //회원ID
		  int ladder_code = lbVO.getLadder_code(); //사다리코드
		
		  
		  String sql = "insert into ladder_bet(ladder_bet_code,ladder_bet_price,ladder_bet_date,ladder_bet_multiple, "
		  		+ "ladder_bet_prize,ladder_bet_YN,cust_id,ladder_code)"
		  		+ "values(seq_ladder_bet_code.nextval,?,sysdate,3.88,?,'Y',?,?)";
		  
		  PreparedStatement ps = con.prepareStatement(sql);
		  ps.setInt(1,bet_price); //ladder_bet_price
		  ps.setInt(2, bet_prize);//ladder_bet_prize
		  ps.setString(3,cust_id);//cust_id
		  ps.setInt(4, ladder_code);//ladder_code
		  
		  ps.executeUpdate();
		  ps.close();
		  
		  System.out.println("+"+bet_prize +" : 입력성공");
	}
	  
	  //사다리 배팅내역에 들어간 정보를 토대로 회원의 포인트를 update(적중시)
	  public void w_cust_point(Ladder_BetVO lbVO)throws Exception
	  {
		  String cust_id = lbVO.getCust_id(); //회원ID
		  int user_price = lbVO.getUser_price(); //사용자보유금액
		  int bet_price = lbVO.getLadder_bet_price(); //배팅금액
		  int bet_prize = lbVO.getLadder_bet_prize(); //적중금액
		  
		  int result_price = user_price-bet_price+bet_prize;
		  
		  String sql="update customer set cust_point=? where cust_id=?";
		  PreparedStatement ps = con.prepareStatement(sql);
		
		  ps.setInt(1, result_price);
		  ps.setString(2, cust_id);
		  ps.executeUpdate();
		  ps.close();
		  
		  System.out.println("+"+result_price+"포인트 업데이트 완료");
		  
	  }
	  
	//사다리배팅내역 insert(lose, 하나 미적중시) 
	  public void l_ladder_type(Ladder_BetVO lbVO) throws Exception{
		  int bet_price = lbVO.getLadder_bet_price(); //배팅금액
		  int bet_prize = lbVO.getLadder_bet_prize(); //적중금액
		  String cust_id = lbVO.getCust_id();   //회원ID
		  int ladder_code = lbVO.getLadder_code(); //사다리코드

		  
		  String sql = "insert into ladder_bet(ladder_bet_code,ladder_bet_price,ladder_bet_date,ladder_bet_multiple, "
		  		+ "ladder_bet_prize,ladder_bet_YN,cust_id,ladder_code)"
		  		+ "values(seq_ladder_bet_code.nextval,?,sysdate,1.97,?,'N',?,?)";
		  
		  PreparedStatement ps = con.prepareStatement(sql);
		  ps.setInt(1,bet_price); //ladder_bet_price
		  ps.setInt(2, bet_prize);//ladder_bet_prize
		  ps.setString(3,cust_id);//cust_id
		ps.setInt(4, ladder_code);//ladder_code
		  
		  ps.executeUpdate();
		  ps.close();
		  
		  System.out.println("-"+bet_prize +" : 입력성공");
	}
		//사다리배팅내역 insert(lose, 두개 미적중시) 
	  public void l_ladder_type2(Ladder_BetVO lbVO) throws Exception{
		  int bet_price = lbVO.getLadder_bet_price(); //배팅금액
		  int bet_prize = lbVO.getLadder_bet_prize(); //적중금액
		  String cust_id = lbVO.getCust_id();   //회원ID
		  int ladder_code = lbVO.getLadder_code(); //사다리코드

		  
		  String sql = "insert into ladder_bet(ladder_bet_code,ladder_bet_price,ladder_bet_date,ladder_bet_multiple, "
		  		+ "ladder_bet_prize,ladder_bet_YN,cust_id,ladder_code)"
		  		+ "values(seq_ladder_bet_code.nextval,?,sysdate,3.88,?,'N',?,?)";
		  
		  PreparedStatement ps = con.prepareStatement(sql);
		  ps.setInt(1,bet_price); //ladder_bet_price
		  ps.setInt(2, bet_prize);//ladder_bet_prize
		  ps.setString(3,cust_id);//cust_id
		  ps.setInt(4, ladder_code);//ladder_code
		  
		  ps.executeUpdate();
	
		  
		  System.out.println("-"+bet_prize +" : 입력성공");
	}
	  
	  //사다리 배팅내역에 들어간 정보를 토대로 회원의 포인트를 update(미적중시)
	  public void l_cust_point(Ladder_BetVO lbVO)throws Exception
	  {
		  String cust_id = lbVO.getCust_id(); //회원ID
		  int user_price = lbVO.getUser_price(); //사용자보유금액
		  int bet_price = lbVO.getLadder_bet_price(); //배팅금액
		  
		  int result_price = user_price-bet_price;
		  
		  String sql="update customer set cust_point=? where cust_id=?";
		  PreparedStatement ps = con.prepareStatement(sql);
		
		  ps.setInt(1, result_price);
		  ps.setString(2, cust_id);
		  ps.executeUpdate();
		  ps.close();
		  
		  System.out.println(result_price+"포인트 업데이트 완료");
		  
	  }
	  
	  
	  
	

}

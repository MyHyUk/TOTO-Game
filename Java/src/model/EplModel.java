package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import model.rec.CustomerVO;
import model.rec.MatchVO;
import model.rec.Racing_BetVO;
import model.rec.ScBetVO;
import model.rec.TeamVO;

public class EplModel {

	private String date = null;
	
	public EplModel() throws Exception {
		Date time = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMdd");
		date = sdf.format(time);
		
		date = "20170422"; //날짜 강제로 22일 고정
		
		connectDB();
	}

	Connection con;

	void connectDB() throws Exception {

		con = ConnectionPool.getConnection();
	}
	  public void update_betY(int sc_bet_code) throws Exception{
		  
		   String sql = "update sc_bet set sc_bet_yn = 'Y' where sc_bet_code = ?";
			 
			  PreparedStatement ps = con.prepareStatement(sql);
			  ps.setInt(1,sc_bet_code);
			  
			  System.out.println("적중업데이트 완료!");
			  
			   ps.executeQuery();		 
			   ps.close();

	   }
	
	 public void changeMoney(double bettingMoney,String cust_id) throws Exception{
		   

		   String sql = "update customer set cust_point =cust_point+? where cust_id = ?";
		   
		   PreparedStatement ps = con.prepareStatement(sql);
			
		     ps.setDouble(1,bettingMoney); // 돈을 update
		     ps.setString(2,cust_id); // 아이디를 검색해서
		  	
		     System.out.println("돈 업데이트 완료!");
		     
			 ps.executeQuery();		 
		   	 ps.close();
		   
		   
	   }
	
	public void epl_bet(ScBetVO sb) throws Exception {

		String sql = "insert into sc_bet values(seq_sc_bet_code.nextval,?,?,'N',?,"+date+",?,?,?)";

		PreparedStatement ps = con.prepareStatement(sql);

		ps.setString(1, sb.getCust_id());
		ps.setInt(2, sb.getSc_bet_price());
		ps.setDouble(3, sb.getSc_multiple());
		ps.setDouble(4, sb.getSc_bet_prize());
		ps.setInt(5, sb.getMatch_code());
		ps.setString(6, sb.getSc_bet_result());

		ps.executeQuery();
		ps.close();

	}
	
	public String winteam(int num) throws Exception {

		String sql = "select match_result from match where match_code=?";

		PreparedStatement pstmt = con.prepareStatement(sql);

		pstmt.setInt(1, num);

		ResultSet rs = pstmt.executeQuery();
		

		if (rs.next()) {

			return (rs.getString("match_result"));
			
		} else
			return null;

	}
	
	public int homematchcode(String name) throws Exception {

		String sql = "select match_code from match m,home h where m.home_code=h.home_code and team_name=? and substr(match_date,1,8)=(select "+date+" from dual) ";

		PreparedStatement pstmt = con.prepareStatement(sql);

		pstmt.setString(1, name);

		ResultSet rs = pstmt.executeQuery();
		

		if (rs.next()) {
			
			return (rs.getInt("match_code"));
			
		} else
			return 0;

	}
	
     public boolean matchcode(int matchcode,String id) throws Exception{
    	 String sql = "select * from sc_bet where match_code=? and cust_id=? ";
    	 PreparedStatement pstmt = con.prepareStatement(sql);

 		pstmt.setInt(1, matchcode);
 		pstmt.setString(2, id);
 		ResultSet rs = pstmt.executeQuery();
 		if(rs.next()){
 			return false;
 		}
    	 return true;
    	 
     }
	
	public ArrayList prize(String id) throws Exception {

		String sql = "select sc_bet_code,sc_bet_prize from sc_bet s,match m where s.match_code=m.match_code and m.match_result=s.select_team_name and cust_id=?";


		PreparedStatement pstmt = con.prepareStatement(sql);

		pstmt.setString(1, id);

		ResultSet rs = pstmt.executeQuery();
		ArrayList<ScBetVO> sb = new ArrayList<ScBetVO>();

		while (rs.next()) {
               ScBetVO sc = new ScBetVO();
               sc.setSc_bet_prize(rs.getDouble("sc_bet_prize"));
               sc.setSc_bet_code(rs.getInt("sc_bet_code"));
               sb.add(sc);
          
		} 
		return sb;

	}
	
	public int awaymatchcode(String name) throws Exception {

		String sql = "select match_code from match m,away a where m.away_code=a.away_code and team_name=? and substr(match_date,1,8)=(select "+date+" from dual)";

		PreparedStatement pstmt = con.prepareStatement(sql);

		pstmt.setString(1, name);

		ResultSet rs = pstmt.executeQuery();
		

		if (rs.next()) {
          
			return (rs.getInt("match_code"));
			
		} else
			return 0;

	}
	
	public int eplmoney(String user_id) throws Exception {
		String id = user_id;
		int point  = 0;
		
		String sql = "select cust_point from customer where cust_id=?";

		PreparedStatement pstmt = con.prepareStatement(sql);

		pstmt.setString(1, id);

		ResultSet rs = pstmt.executeQuery();
		CustomerVO cu = new CustomerVO();

		if (rs.next()) {
			point = rs.getInt("cust_point");
		}
		pstmt.close();
		return point;
			
		
	}
	//홈팀 불러오기
	public ArrayList homematch() throws Exception{
		
		String sql = "select t.team_name,team_winrate,substr(match_date,3,13) as day ,m.match_code from match m,home h,team t where m.home_code=h.home_code and h.team_name=t.team_name and substr(match_date,1,8)= "+date+" and match_type in('EPL','BUNDE') order by 4";
		Statement pstmt = con.createStatement();
		ResultSet rs = pstmt.executeQuery(sql);
		ArrayList<TeamVO> mt = new ArrayList<TeamVO>();
		
		while(rs.next()){
			TeamVO t = new TeamVO();
			t.setTeam_name(rs.getString("team_name"));
			t.setWinRate(rs.getDouble("team_winrate"));
			t.setStarttime(rs.getString("day"));
			mt.add(t);
			
			
		
		}
		pstmt.close();
		return mt;
	}

	 public ArrayList awaymatch() throws Exception{
		
		String sql = "select t.team_name,team_winrate,substr(match_date,9,13),m.match_code from match m,away a,team t where m.away_code=a.away_code and a.team_name=t.team_name and substr(match_date,1,8)=(select "+date+" from dual) and (match_type='EPL' or match_type='BUNDE') order by 4 ";
		Statement pstmt = con.createStatement();
		ResultSet rs = pstmt.executeQuery(sql);
		ArrayList<TeamVO> mt2 = new ArrayList<TeamVO>();
		
		while(rs.next()){
			TeamVO t = new TeamVO();
			t.setTeam_name(rs.getString("team_name"));
			t.setWinRate(rs.getDouble("team_winrate"));
			t.setStarttime(rs.getString("substr(match_date,9,13)"));
			mt2.add(t);
			
			
			
		}
		
		return mt2;
	}
	
	
	
	
	
	
}

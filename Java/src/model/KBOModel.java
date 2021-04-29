package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import model.rec.KBOBetVO;

public class KBOModel {

	private Connection con;
	
	public KBOModel() throws Exception{
		 con = ConnectionPool.getConnection();
	}
	 
	public ArrayList getAwayTeam() throws Exception{
		
	   ArrayList<KBOBetVO> list = new ArrayList<KBOBetVO>();
	 
	   String sql = "select t.team_name,t.team_winrate from match m,away a,team t "
				+ "where m.away_code = a.away_code and t.team_name = a.team_name and m.match_type= 'KBO'"
				+ "and substr(m.match_date,1,8) = to_char(sysdate,'yyyymmdd') order by match_code";
	   
	   Statement st = con.createStatement();
	   ResultSet rs = st.executeQuery(sql);
		
		while(rs.next()){
			KBOBetVO kbo = new KBOBetVO(); 
			kbo.setAwayTeam(rs.getString("team_name"));
			kbo.setAwayWinrate(rs.getString("team_winrate"));
			list.add(kbo);
		}
		
		st.close();
		rs.close();
	   
		return list;
	}
	
	
	
	
	//팀이름 가져오기
	public ArrayList getTeamName() throws Exception{
		
		
		ArrayList<KBOBetVO> list = new ArrayList<KBOBetVO>();
	
		
		String sql = "select t.team_name,t.team_winrate from match m,home h,team t "
				+ "where m.home_code = h.home_code and t.team_name = h.team_name and m.match_type= 'KBO'"
				+ "and substr(m.match_date,1,8) = to_char(sysdate,'yyyymmdd') order by match_code";
				
       Statement st = con.createStatement();
	   ResultSet rs = st.executeQuery(sql);
		
		while(rs.next()){
			KBOBetVO kbo = new KBOBetVO(); 
			kbo.setHomeTeam(rs.getString("team_name"));
			kbo.setHomeWinrate(rs.getString("team_winrate"));
			list.add(kbo);
		}
		
		st.close();
		rs.close();

		
		return list;
	}
	
	//팀이름을 받아와서 팀승률을 구해오기
	public KBOBetVO getTeamWin(String homeTeam,String awayTeam) throws Exception{
		
		KBOBetVO kbo = new KBOBetVO(); 
		
		
		return kbo;
	}
	//게임경기 날짜 구하기
		public String getDay() throws Exception{
			
			String day = null;
			String sql = "select substr(match_date,5,10) as day from match where match_type = 'KBO'"
					    + " and substr(match_date,1,8) = to_char(sysdate,'yyyymmdd')";
			
			PreparedStatement ps = con.prepareStatement(sql); 
			ResultSet result = ps.executeQuery();
				
			if(result.next()){	   
			   	   day = result.getString("day");
			   	  }
				
			return day;
		}
	
	// 행라인 수  구하기
	   public int getCountGame() throws Exception{		   
		   int count=0;
		   
		   String sql = "select count(*) as count from match where match_type = 'KBO'"
		   		+ "and substr(match_date,1,8) = to_char(sysdate,'yyyymmdd')";
		   
		   PreparedStatement ps = con.prepareStatement(sql); 
			ResultSet result = ps.executeQuery();
				
			if(result.next()){	   
			   	   count = result.getInt("count");
			   	  }	   
			
			ps.close();
			result.close();
			
		   return count;
	   }
	   
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
	   
	   
	   //배팅금액을 받아와서 보유금액을 update
	   public int changeMoney(int bettingMoney,int yourMoney,String cust_id) throws Exception{
		   
		   int money = yourMoney - bettingMoney;
		   
		   String sql = "update customer set cust_point = ? where cust_id = ?";
		   
		   PreparedStatement ps = con.prepareStatement(sql);
			
		     ps.setInt(1,money); // 돈을 update
		     ps.setString(2,cust_id); // 아이디를 검색해서
		  	 
			 ps.executeQuery();		 
		   	 ps.close();
	   
		   return money;
	   }
	  //유저아이디,배팅금액,배당률,날짜,당첨금액,경기번호,선택한팀
	   public void inKBO_bet(String cust_id,int bat_m,double batRate,String date
			,int batPrize,int matchCode,String teamName)throws Exception{
  
		   
		   String sql= "insert into KBO_bet(kbo_bet_code,cust_id,kbo_bet_price,KBO_BET_MULTIPLE,KBO_BET_DATE"
		   		+ ",KBO_BET_PRIZE,MATCH_CODE,SELECT_TEAM_NAME) values(SEQ_KBO_BET_CODE.nextval,?,?,?,?,?,?,?)";  
		   
		   PreparedStatement ps = con.prepareStatement(sql);
	 
		   ps.setString(1, cust_id);
		   ps.setInt(2, bat_m);
		   ps.setDouble(3, batRate);
		   ps.setString(4, date);
		   ps.setInt(5, batPrize);
		   ps.setInt(6, matchCode);
		   ps.setString(7, teamName);
		   
		   ps.executeUpdate();// 실행!
		   ps.close();		   
	   }   
	   public int getHomeCode(String teamName) throws Exception{
		   
		   int match_code=0;
		   
		   String sql = "select m.match_code as code from match m,home h "
		   		+ "where m.home_code = h.home_code and h.team_name =?"
		   		+ "and substr(m.match_date,1,8) = to_char(sysdate,'yyyymmdd')";
		   
		   PreparedStatement ps = con.prepareStatement(sql);
		   ps.setString(1,teamName);

		   ResultSet result = ps.executeQuery();
		  
			   if(result.next()){
				   match_code = result.getInt("code");
			   }   
			   ps.close();		
		   	result.close();
		  	
		   return match_code;
	   }
	  
	   public int getAwayCode(String teamName) throws Exception{
		   
		   int match_code=0;

		   String sql2 = "select m.match_code as code from match m,away a "
			   		+ "where m.away_code = a.away_code and a.team_name =?"
			   		+ "and substr(m.match_date,1,8) = to_char(sysdate,'yyyymmdd')";
		   
		   PreparedStatement ps2 = con.prepareStatement(sql2);
		   ps2.setString(1,teamName);
		   
		   ResultSet result2 = ps2.executeQuery();
		   
			   if(result2.next()){
				   match_code = result2.getInt("code");
			   }   
			   
			    ps2.close();		
			   	result2.close();
			 	   		   	
		  return match_code;
	 
	   }
	   //이긴팀 가져오기
	   public String getWinTeam(int match_code) throws Exception{
		   
		   String winTeam  = null;
		   
		   String sql = "select match_result from match where match_code = ?";
		   
		   PreparedStatement ps = con.prepareStatement(sql);
		   ps.setInt(1,match_code);
		   
		   ResultSet result = ps.executeQuery();
		   
		   if(result.next()){
			   winTeam = result.getString("match_result");
		   }
		   
		   ps.close();
		   result.close();
		   
		   return winTeam;
	   }
	   
	   //선택한 팀
	   public String getSelectTeam(String user_id,int match_code) throws Exception{
		   
		   String selectTeam = null;
		   
		   String sql = "select SELECT_TEAM_NAME from kbo_bet where CUST_ID = ? and match_code = ?";
		   
		   PreparedStatement ps = con.prepareStatement(sql);
		   
		   ps.setString(1,user_id);
		   ps.setInt(2,match_code);
		   
		   ResultSet result = ps.executeQuery();
		   
		   if(result.next()){
			   selectTeam = result.getString("SELECT_TEAM_NAME");
		   }		   
		   ps.close();
		   result.close();	
		   
		   return selectTeam;	   
	   }  
	     
	   public ArrayList getMatchCode(String user_id)throws Exception{	   
		   ArrayList<KBOBetVO> list = new ArrayList<KBOBetVO>();
		     
		   String sql="select match_code from kbo_bet where cust_id = ? and kbo_bet_yn is null";
		   
		   PreparedStatement ps = con.prepareStatement(sql);
		   ps.setString(1, user_id);
		
		   
		   ResultSet result = ps.executeQuery();
		   
		   while(result.next()){
				KBOBetVO kbo = new KBOBetVO(); 
			
				kbo.setMatchCode(result.getInt("match_code"));
				list.add(kbo);
			}
		   return list;	  
	   }
	   
	   
	   public void update_betN(int match_code) throws Exception{
		   
		  String sql = "update kbo_bet set kbo_bet_yn = 'N' where match_code = ?";
		 
		  PreparedStatement ps = con.prepareStatement(sql);
		  ps.setInt(1,match_code);
		  
		   ps.executeQuery();		 
		   ps.close();
   
	   }
	   public void update_betY(int match_code) throws Exception{
		  
		   String sql = "update kbo_bet set kbo_bet_yn = 'Y' where match_code = ?";
			 
			  PreparedStatement ps = con.prepareStatement(sql);
			  ps.setInt(1,match_code);
			  
			   ps.executeQuery();		 
			   ps.close();
 
	   }
	   
	   public int getMoney(int match_code) throws Exception{
		   int money = 0;
		   
		   String sql = "select KBO_BET_PRIZE as money from kbo_bet where match_code = ?";
		   
		   PreparedStatement ps = con.prepareStatement(sql);
		   ps.setInt(1, match_code);
		   
		   ResultSet result = ps.executeQuery();
		   
		   if(result.next()){
			   money = result.getInt("money");
		   }
		   
		   ps.close();
		   result.close();
		   
		   return money;
	   }
	   
	   
	   public int nowMoney(String user_id)throws Exception{	  
		   int money =0;
		  
		   String sql = "select cust_point from customer where cust_id = ?";
		   
		   PreparedStatement ps = con.prepareStatement(sql);
		   ps.setString(1,user_id);
		   
		   ResultSet result = ps.executeQuery();
		   
		   if(result.next()){
			   money = result.getInt("cust_point");
		   }
		   
		   ps.close();
		   result.close();
		   
		   return money;
	   }
	      
	   public void updatePoint(int recentMoney,int money,String user_id) throws Exception{
		   
		   int resultMoney = recentMoney + money;
	   
		   String sql = "update customer set cust_point = ? where cust_id = ?";
		   		   
		   PreparedStatement ps = con.prepareStatement(sql);
  
		     ps.setInt(1,resultMoney);
		     ps.setString(2,user_id); 
		  	 
			 ps.executeQuery();		 
		   	 ps.close();	   	   
	   }
	   
	   public int checkCount(int match_code,String user_id)throws Exception{
		   int count = 0;
		   
		   String sql= "select count(*) as cnt from kbo_bet where match_code = ? and cust_id = ?";
		   
		   PreparedStatement ps = con.prepareStatement(sql);
		   ps.setInt(1,match_code);
		   ps.setString(2,user_id);
		   
		   ResultSet result = ps.executeQuery();
		   
		   if(result.next()){
			  count = result.getInt("cnt");
		   }
		   return count;
	   }
	   
	   public void checkSelectedTeam(String user_ID) throws Exception{
		   try{
			    ArrayList<KBOBetVO> list = new ArrayList<KBOBetVO>();
				KBOModel m = new KBOModel();
				
				list = m.getMatchCode(user_ID);

				for (int x = 0; x < list.size(); x++) {
					String winTeam = m.getWinTeam(list.get(x).getMatchCode());
					String selectTeam = m.getSelectTeam(user_ID, list.get(x).getMatchCode());
					if (winTeam == null) {
						System.out.println("아직경기가 끝나지 않았습니다.");
					} else {
						if (winTeam.equals(selectTeam)) {
							// 같으면 y로바꿔주고~
							m.update_betY(list.get(x).getMatchCode());
							int prize = m.getMoney(list.get(x).getMatchCode());
							int recentMoney = m.nowMoney(user_ID);
							// 현재돈하고 적중액 더해서 업데이트실시
							m.updatePoint(recentMoney, prize, user_ID);
						} else if (winTeam.equals("무") && selectTeam.equals("없음")) { 
							m.update_betY(list.get(x).getMatchCode());
							int prize = m.getMoney(list.get(x).getMatchCode());
							int recentMoney = m.nowMoney(user_ID);
							m.updatePoint(recentMoney, prize, user_ID);
						} else {
							// 아니면 n으로 바꿔주고~
							m.update_betN(list.get(x).getMatchCode());
						}
					}
				}
			   
		   }catch(Exception e){
			   e.printStackTrace();
		   }
		  
	   }
	   
	  
}

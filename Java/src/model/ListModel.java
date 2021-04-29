package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.spi.DirStateFactory.Result;
import model.*;
import model.rec.*;
import view.*;
import model.rec.CustomerVO;

public class ListModel {
	public ListModel() throws Exception {
		connectDB();
	}

	Connection con;

	void connectDB() throws Exception {
		con = ConnectionPool.getConnection();
	}

	// 사다리 배팅내역
	public ArrayList ladder_bet(String user_id) throws Exception {
		ArrayList al = new ArrayList();
		String id = user_id;
		String sql = "select lb.ladder_bet_date,l.ladder_type, lb.ladder_bet_price, lb.ladder_bet_yn, lb.ladder_bet_multiple,lb.ladder_bet_prize"
				+ " from ladder_bet lb,ladder l " + " where lb.cust_id=? and lb.ladder_code=l.ladder_code"
				+ " order by lb.ladder_bet_date desc, lb.ladder_bet_code desc";
		// 배팅날짜,사다리코드,배팅금액,적중여부,배당률,적중금액
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, id);
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			ArrayList list = new ArrayList();
			list.add(rs.getString(1));
			list.add(rs.getString(2));
			list.add(rs.getInt(3));
			list.add(rs.getString(4));
			list.add((rs.getDouble(5)));
			list.add(rs.getInt(6));
			al.add(list);

		}

		pstmt.close();
		rs.close();
		return al;

	}

	// 로또 배팅내역
	public ArrayList lotto_bet(String user_id) throws Exception {
		ArrayList al = new ArrayList();
		String id = user_id;
		String sql = "select l.lotto_date,l.lotto_code,lb.lotto_bet_yn,l.lotto_totalprice "
				+ " from lotto l, lotto_bet lb " + " where cust_id=? and l.lotto_code=lb.lotto_code"
				+ " order by l.lotto_date desc, lb.lotto_bet_code desc";

		// 로또날짜,로또회차, 당첨여부, 적중금액
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, id);
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			ArrayList list = new ArrayList();
			list.add(rs.getString(1));
			list.add(rs.getInt(2));
			list.add(rs.getString(3));
			list.add((rs.getInt(4)));
			al.add(list);

		}

		pstmt.close();
		rs.close();
		return al;

	}

	// 경주 배팅내역
	public ArrayList race_bet(String user_id) throws Exception {
		ArrayList al = new ArrayList();
		String id = user_id;
		String sql = "select rb.racing_bet_date,a.ani_name, rb.racing_bet_price, rb.racing_bet_yn,rb.racing_bet_multiple, rb.racing_bet_prize "
				+ " from racing_bet rb, animal a, racing r "
				+ " where cust_id=? and rb.racing_bet_code = r.racing_bet_code and rb.ani_code = a.ani_code "
				+ " order by rb.racing_bet_date desc, rb.racing_bet_code desc";

		// 경주배팅날짜,배팅한동물,배팅금액,적중여부,배당률,적중금액
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, id);
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			ArrayList list = new ArrayList();
			list.add(rs.getString(1));
			list.add(rs.getString(2));
			list.add(rs.getInt(3));
			list.add((rs.getString(4)));
			list.add((rs.getDouble(5)));
			list.add((rs.getInt(6)));
			al.add(list);

		}

		pstmt.close();
		rs.close();
		return al;

	}

	// KBO 배팅내역
	public ArrayList KBO_bet(String user_id) throws Exception {
		ArrayList al = new ArrayList();
		String id = user_id;
		String sql = "select kb.kbo_bet_date,h.team_name,a.team_name, kb.select_team_name, kb.kbo_bet_price, kb.kbo_bet_yn, kb.kbo_bet_multiple, kb.kbo_bet_prize "
				+ " from kbo_bet kb, match m, home h, away a " + " where cust_id=? and kb.match_code = m.match_code  "
				+ " and m.home_code = h.home_code and m.away_code = a.away_code "
				+ " order by kb.kbo_bet_date desc, kb.kbo_bet_code desc";

		//야구배팅날짜, 홈팀, 원정팀, 선택팀명, 배팅금액, 적중여부, 배당률, 적중금액
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, id);
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			ArrayList list = new ArrayList();
			list.add(rs.getString(1));
			list.add(rs.getString(2));
			list.add(rs.getString(3));
			list.add((rs.getString(4)));
			list.add((rs.getInt(5)));
			list.add((rs.getString(6)));
			list.add((rs.getDouble(7)));
			list.add((rs.getInt(8)));
			al.add(list);
		}
		pstmt.close();
		rs.close();
		return al;

	}

	// EPL 배팅내역
	public ArrayList EPL_bet(String user_id) throws Exception {
		ArrayList al = new ArrayList();
		String id = user_id;
		String sql ="select sb.sc_bet_date, h.team_name, a.team_name, sb.select_team_name, sb.sc_bet_price, sb.sc_bet_yn, sb.sc_bet_multiple, sb.sc_bet_prize "
				+ " from sc_bet sb, match m, home h, away a "
				+ " where cust_id=? and sb.match_code = m.match_code  "
				+ " and m.home_code = h.home_code and m.away_code = a.away_code "
				+ " order by sb.sc_bet_date desc, sb.sc_bet_code desc";


		//축구배팅날짜, 홈팀, 원정팀, 선택팀명, 배팅금액, 적중여부, 배당률, 적중금액
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, id);
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			ArrayList list = new ArrayList();
			list.add(rs.getString(1));
			list.add(rs.getString(2));
			list.add(rs.getString(3));
			list.add((rs.getString(4)));
			list.add((rs.getInt(5)));
			list.add((rs.getString(6)));
			list.add((rs.getDouble(7)));
			list.add((rs.getInt(8)));
			al.add(list);
		}
		pstmt.close();
		rs.close();
		return al;

	}
	
	// 충전리스트내역
	public ArrayList recharge(String user_id) throws Exception {
		ArrayList al = new ArrayList();
		String id = user_id;
		String sql = "select rc_date, rc_price from recharge where cust_id=?"
				+ " order by rc_date desc, rc_code desc";

		//날짜, 가격
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, id);
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			ArrayList list = new ArrayList();
			list.add(rs.getString(1));
			list.add(rs.getInt(2));
		
			al.add(list);
		}
		pstmt.close();
		rs.close();
		return al;

	}
	
	// 환전리스트내역
	public ArrayList exchange(String user_id) throws Exception {
		ArrayList al = new ArrayList();
		String id = user_id;
		String sql = "select ex_date, ex_money from exchange where cust_id=?"
				+ " order by ex_date desc, ex_code desc";

		//날짜, 가격
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, id);
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			ArrayList list = new ArrayList();
			list.add(rs.getString(1));
			list.add(rs.getInt(2));
		
			al.add(list);
		}
		pstmt.close();
		rs.close();
		return al;

	}
}

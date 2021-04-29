package model;

import java.awt.image.AreaAveragingScaleFilter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import model.rec.AnimalVO;
import model.rec.CustomerVO;
import model.rec.RacingVO;
import model.rec.Racing_BetVO;

public class RacingModel {

	public RacingModel() throws Exception {
		connectDB();
	}

	Connection con;

	void connectDB() throws Exception {

		con = ConnectionPool.getConnection();
	}

	// 경주 동물 뽑아오기
	public ArrayList animal() throws Exception {

		String sql = "SELECT * FROM (SELECT * FROM animal order by DBMS_RANDOM.RANDOM) WHERE rownum < 5";

		Statement pstmt = con.createStatement();
		ResultSet rs = pstmt.executeQuery(sql);
		ArrayList<AnimalVO> an = new ArrayList<AnimalVO>();

		while (rs.next()) {
			AnimalVO v = new AnimalVO();
			v.setAni_name(rs.getString("ani_name"));
			v.setAni_speed(rs.getInt("ani_speed"));
			v.setAni_code(rs.getInt("ani_code"));
			an.add(v);
		}

		return an;

	}
	// 레이싱 코드 뽑아오기

	public Racing_BetVO racingcode(String user_id) throws Exception {
		
		String sql = "select racing_bet_code from racing_bet b "
				+ "where not exists (select * from racing c where b.racing_bet_code =c.racing_bet_code) "
				+ "and cust_id=?";

		PreparedStatement pstmt = con.prepareStatement(sql);

		pstmt.setString(1, user_id);
		ResultSet rs = pstmt.executeQuery();
		Racing_BetVO rb = new Racing_BetVO();

		if (rs.next()) {

			rb.setRacing_bet_code(rs.getInt("racing_bet_code"));
			return rb;
		} else
			return null;

	}

	// 보유 금액뽑아오기
	public int racing_bet(String user_id) throws Exception {
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

	// 경주 배팅내역

	public void racingbetting(Racing_BetVO rb) throws Exception {

		String sql = "insert into racing_bet(racing_bet_code,racing_bet_date,racing_bet_multiple,racing_bet_price,racing_bet_prize,racing_bet_yn,cust_id,ani_code) "
				+ "values(seq_racing_bet_code.nextval,sysdate,?,?,?,'N',?,?)";

		PreparedStatement ps = con.prepareStatement(sql);

		ps.setDouble(1, rb.getRacing_bet_multiple());
		ps.setInt(2, rb.getRacing_price());
		ps.setInt(3, rb.getRacing_prize());
		ps.setString(4, rb.getCust_id());
		ps.setInt(5, rb.getAni_code());

		ps.executeQuery();
		ps.close();

	}

	// racing 입력
	public void racingresult(RacingVO rc) {
		int win = rc.getWinner_code();
		int code = rc.getRacing_bet_code();
		try {
			String sql = "insert into racing(winner_code,racing_bet_code,racing_code) values(?,?,seq_racing_code.nextval)";

			PreparedStatement ps = con.prepareStatement(sql);

			ps.setInt(1, win);
			ps.setInt(2, code);

			ps.executeQuery();
			ps.close();
		} catch (Exception e) {
			e.getStackTrace();
		}

	}

	// 레이싱 당첨여부 바꾸는 업데이트

	public void racingupdate(int code) throws Exception {

		String sql = "update racing_bet set racing_bet_yn='Y' where racing_bet_code=?";

		PreparedStatement ps = con.prepareStatement(sql);

		ps.setInt(1, code);

		ps.executeQuery();
		ps.close();

	}
	// 적중시 금액 추가

	public void racingmoney(String id, int money) throws Exception {

		String sql = "update customer set cust_point=cust_point+? where cust_id=?";

		PreparedStatement ps = con.prepareStatement(sql);

		ps.setInt(1, money);
		ps.setString(2, id);

		ps.executeQuery();
		ps.close();

	}

	/// 미적중시 금액 감소
	public void racingmoneym(String id, int money) throws Exception {

		String sql = "update customer set cust_point=cust_point-? where cust_id=?";

		PreparedStatement ps = con.prepareStatement(sql);

		ps.setInt(1, money);
		ps.setString(2, id);

		ps.executeQuery();
		ps.close();

	}

}

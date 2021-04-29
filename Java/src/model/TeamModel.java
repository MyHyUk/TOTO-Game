package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Pattern;

import javax.naming.ldap.PagedResultsControl;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import model.rec.TeamVO;

public class TeamModel {

	static Connection con;

	public TeamModel() throws Exception {
		connectDB();
	}

	private void connectDB() throws Exception {

		con = ConnectionPool.getConnection();
	}

	
	public int getTeamCode(String pos, String name) throws SQLException {
		String sql = "SELECT " + pos + "_code FROM " + pos + " WHERE team_name  = ? ";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, name);
		ResultSet rs = st.executeQuery();
		if (rs.next()) {
			return rs.getInt(1);
		}
		return -1;
	}

	public Boolean checkFinish(ArrayList<String> list){
		for(String data : list){
			if( data.equals("VS")){
				return false;
			}
		}
		return true;
	}
	public String findSCName(String name) throws SQLException {
		String sql = "SELECT team_name FROM team WHERE team_name like ? ";
		PreparedStatement st = con.prepareStatement(sql);
		// ����Ʈ����� '��'������ �νĺҰ�.. ���ο���ó��
		if (name.equals("����Ʈ���"))
			st.setString(1, "����Ʈ ��ι�ġ �˺�� FC");
		else if (name.equals("H.������"))
			st.setString(1, "�츣Ÿ BSC ������");
		// �������� ex)'�ǽ�Ƽ => replaceAll() => %��%��%Ƽ% == ��ü���� ��Ƽ' �� ���� �������
		// ó��.
		else
			st.setString(1, name.replaceAll("", "%"));

		ResultSet rs = st.executeQuery();
		if (rs.next()) {
			String result = rs.getString(1);
			return result;
		} else
			System.out.println(name + "�ش��̸� ����");
		st.close();
		return null;
	}

	public String cutKBOName(String name) {
		name = name.toUpperCase();
		return name.substring(0, 2).equals("KI") ? name.substring(0, 3) : name.substring(0, 2);
	}

	public void updateMatchData(String type) throws Exception {
		try {
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMdd");
			String time = sdf.format(date);

			ArrayList recordList = new ArrayList();

			if (type.equals("EPL")) {
				String epl_url = "https://search.naver.com/search.naver?sm=tab_hty.top&where=nexearch&oquery=KBO&ie=utf8&query=EPL";
				Document doc = Jsoup.connect(epl_url).get();
				Elements html_data = doc.select(".tb_type tr.schedule_" + time + " td");
				for (int i = 1; i < html_data.size(); i += 4) {
					recordList.add(html_data.get(i).text() + " " + html_data.get(i + 1).text());
				}
			} else if (type.equals("KBO")) {
				String kbo_url = "https://search.naver.com/search.naver?sm=tab_hty.top&where=nexearch&oquery=epl&ie=utf8&query=kbo";
				Document doc = Jsoup.connect(kbo_url).get();
				Elements html_data = doc.select(".tb_type tr.schedule_" + time + " td");
				for (int i = 0; i < html_data.size(); i += 5) {
					recordList.add(html_data.get(i).text() + " " + html_data.get(i + 1).text());
				}
			} else if (type.equals("BUNDE")) {
				String bunde_url = "https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=1&ie=utf8&query=�е�������";
				Document doc = Jsoup.connect(bunde_url).get();
				Elements html_data = doc.select(".tb_type tr.schedule_" + time + " td");
				for (int i = 1; i < html_data.size(); i += 4) {
					recordList.add(html_data.get(i).text() + " " + html_data.get(i + 1).text());
				}
			}

			// �Ľ��� ������ ���
			for (Object record : recordList) {
				String[] row = ((String) record).split(" ");

				ArrayList<String> list = new ArrayList<String>();
				for (String element : row) {
					list.add(element.trim());
				}
				

				for(int i = 0 ; i+1 < list.size() ; i++){
					if( !(list.get(i).charAt(0) >= '0' && list.get(i).charAt(0) <= '9') && !(list.get(i).equals("VS")) ){
						if( !(list.get(i+1).charAt(0) >='0' && list.get(i+1).charAt(0) <= '9') && !(list.get(i+1).equals("VS")) ){
							list.set(i, list.get(i)+list.get(i+1));
							list.remove(i+1);
						}else if(list.get(i+1).equals("04") || list.get(i+1).equals("05")){
							list.set(i, list.get(i)+list.get(i+1));
							list.remove(i+1);
						}
					}
				}
				System.out.println("DATAS >>"+list +"SIZE >>" + list.size() );
					



				String sql_select = "SELECT match_code FROM match WHERE match_date = ? and home_code =? and away_code =?";
				PreparedStatement st_select = con.prepareStatement(sql_select);
				st_select.setString(1, time + " " + list.get(0));

				if (type.equals("EPL") || type.equals("BUNDE")) {
					st_select.setInt(2, getTeamCode("HOME", findSCName(list.get(1))));
					if (!checkFinish(list)) {
						st_select.setInt(3, getTeamCode("AWAY", findSCName(list.get(3))));
					} else {
						st_select.setInt(3, getTeamCode("AWAY", findSCName(list.get(5))));
					}
				} else {
					st_select.setInt(2, getTeamCode("HOME", cutKBOName(list.get(1))));
					if (!checkFinish(list)) {
						st_select.setInt(3, getTeamCode("AWAY", cutKBOName(list.get(3))));
					} else {
						st_select.setInt(3, getTeamCode("AWAY", cutKBOName(list.get(5))));
					}
					
				}

				ResultSet rs_select = st_select.executeQuery();
				
				if (rs_select.next()) {
					System.out.println("���� ������ ����");
					if (!checkFinish(list)) {
						System.out.println("�������� �����ȵ�.");
					} else {
						System.out.println("�������� Ȯ�� UPDATE START");

						int match_code = rs_select.getInt("match_code");
						String sql_update = "UPDATE match SET match_result = ? WHERE match_code = ?";
						PreparedStatement st_update = con.prepareStatement(sql_update);
						int homeScore = Integer.parseInt(list.get(2));
						int awayScore = Integer.parseInt(list.get(4));

						if (type.equals("EPL") || type.equals("BUNDE")) {
							
							if (homeScore > awayScore)
								st_update.setString(1, findSCName(list.get(1)));
							else if (homeScore < awayScore)
								st_update.setString(1, findSCName(list.get(5)));
							else if (homeScore == awayScore)
								st_update.setString(1, "��");

						} else {
							if (homeScore > awayScore)
								st_update.setString(1, cutKBOName(list.get(1)));
							else if (homeScore < awayScore)
								st_update.setString(1, cutKBOName(list.get(5)));
							else if (homeScore == awayScore)
								st_update.setString(1, "��");
						}
						st_update.setInt(2, match_code);
						int rowNum = st_update.executeUpdate();
						if (rowNum > 0)
							System.out.println("������Ʈ �Ϸ�");
						else
							System.err.println("������Ʈ ����");
						st_update.close();
					}
					st_select.close();
					rs_select.close();
				} else {
					String sql_insert = "INSERT INTO match(match_code,	match_date,	match_result,	match_type,	home_code,	away_code)  "
							+ "VALUES(seq_match_code.nextval,	?,			?,				?,			?,			?)";
					// 1:��ġ��¥ 2:��ġ���� 3:��ġ���� 4:Ȩ���ڵ� 5:��������ڵ�

					PreparedStatement st_insert = con.prepareStatement(sql_insert);
					st_insert.setString(1, time + " " + list.get(0));
					st_insert.setString(3, type);

					if (type.equals("EPL") || type.equals("BUNDE")) {
						st_insert.setInt(4, getTeamCode("HOME", findSCName(list.get(1)))); // Ȩ���ڵ�
						if (!checkFinish(list)) {
							st_insert.setNull(2, Types.VARCHAR);
							st_insert.setInt(5, getTeamCode("AWAY", findSCName(list.get(3)))); // ��������ڵ�
						} else {
							int homeScore = Integer.parseInt(list.get(2));
							int awayScore = Integer.parseInt(list.get(4));
							if (homeScore > awayScore)
								st_insert.setString(2, findSCName(list.get(1)));
							else if (homeScore < awayScore)
								st_insert.setString(2, findSCName(list.get(5)));
							else if (homeScore == awayScore)
								st_insert.setString(2, "��");
							st_insert.setInt(5, getTeamCode("AWAY", findSCName(list.get(5)))); // ��������ڵ�
						}
					} else if (type.equals("KBO")) {
						st_insert.setInt(4, getTeamCode("HOME", cutKBOName(list.get(1)))); // Ȩ���ڵ�
						if (!checkFinish(list)) {
							st_insert.setNull(2, Types.VARCHAR);
							st_insert.setInt(5, getTeamCode("AWAY", cutKBOName(list.get(3)))); // ��������ڵ�
						} else {
							int homeScore = Integer.parseInt(list.get(2));
							int awayScore = Integer.parseInt(list.get(4));
							if (homeScore > awayScore)
								st_insert.setString(2, cutKBOName(list.get(1)));
							else if (homeScore < awayScore)
								st_insert.setString(2, cutKBOName(list.get(5)));
							else if (homeScore == awayScore)
								st_insert.setString(2, "��");

							st_insert.setInt(5, getTeamCode("AWAY", cutKBOName(list.get(5)))); // ��������ڵ�
						}
						System.out.println();
					}

					int rowNum = st_insert.executeUpdate();
					if (rowNum > 0)
						System.out.println("�μ�Ʈ �Ϸ�");
					else
						System.out.println("�μ�Ʈ ����");
					st_insert.close();

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void updateSCData() throws Exception {
		try {
			ArrayList teamList = new ArrayList();
			ArrayList team = new ArrayList();
			String[] urls = {
					"https://search.naver.com/search.naver?sm=tab_hty.top&where=nexearch&oquery=KBO&ie=utf8&query=EPL",
					"https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=1&ie=utf8&query=�е�������" };

			for (String url : urls) {
				Document doc = Jsoup.connect(url).get();
				Elements html_data = doc.select("#teamRankTabPanel_0 .tb_type2 td");

				int cnt = 0;
				for (Element e : html_data) {
					team.add(e.text());
					cnt++;
					if (cnt == 9) {
						cnt = 0;
						teamList.add(team);
						team = new ArrayList();
					}

				}

				// �Ľ��� ������ ���
				for (Object teamsList : teamList) {
					ArrayList<String> teams = (ArrayList) teamsList;
					teams.set(0, findSCName(teams.get(0)));
					for (Object data : teams) {
						//System.out.printf(data + "\t");
					}
					//System.out.println();
				}

				// 0:���� 1:��� 2:���� 3:�� 4:�� 5:�� 6:���� 7:���� 8:�����
				// �·� = ��� / ��;
				// �·� = (1) / (3);

				for (Object teams : teamList) {
					ArrayList<String> teamInfo = (ArrayList) teams;
					String sql = "UPDATE team SET team_winrate = ? WHERE team_name = ? ";
					// String sql = "INSERT INTO team(team_winrate,team_name)
					// VALUES(?,?) ";
					// String sql = "DELETE team WHERE team_name = ? ";
					PreparedStatement st = con.prepareStatement(sql);
					String teamName = teamInfo.get(0);
					double winrate = (Double.parseDouble(teamInfo.get(3)) + (Double.parseDouble(teamInfo.get(4)) / 2))
							/ Double.parseDouble(teamInfo.get(1));
					winrate = Math.round(winrate * 100d) / 100d;
					st.setDouble(1, winrate);
					st.setString(2, teamName);
					int rowNum = st.executeUpdate();
					if (rowNum > 0)
						System.out.println(teamName + "������ >" + winrate + " �� ������Ʈ����");
					else
						System.out.println("����");
					st.close();
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void updateKBOData() throws Exception {
		try {
			ArrayList teamList = new ArrayList();
			ArrayList team = new ArrayList();
			String url_kbo = "https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=0&ie=utf8&query=KBO";
			Document doc = Jsoup.connect(url_kbo).get();
			Elements html_data = doc.select(".tb_type2 tbody td");
			int cnt = 0;
			for (Element e : html_data) {
				team.add(e.text());
				cnt++;
				if (cnt == 9) {
					cnt = 0;
					teamList.add(team);
					team = new ArrayList();
				}

			}
			// �Ľ��� ������ ���

			/*
			for (Object teamsList : teamList) {
				ArrayList teams = (ArrayList) teamsList;
				for (Object data : teams) {
					System.out.print((String) data + "\t");
				}
				System.out.println();
			}
			*/

			// 0:���� 1:��� 2:�� 3:�� 4:�� 5:�·� 6:������ 7:���� 8:�ֱ�10���

			for (Object teams : teamList) {
				ArrayList<String> teamInfo = (ArrayList) teams;
				String sql = "UPDATE team SET team_winrate = ? WHERE team_name = ? ";
				PreparedStatement st = con.prepareStatement(sql);

				String teamName = (teamInfo.get(0)).toUpperCase();
				double winrate = Double.parseDouble(teamInfo.get(5));
				st.setDouble(1, winrate);
				st.setString(2, teamName);
				int rowNum = st.executeUpdate();
				
				if (rowNum > 0)
					System.out.println(teamName + "������ >" + winrate + " �� ������Ʈ����");
				else
					System.out.println("����");
					
				st.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

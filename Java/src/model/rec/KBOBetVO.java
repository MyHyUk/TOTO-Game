package model.rec;

public class KBOBetVO {
	
   //���̸�
   private String awayTeam;
   private String homeTeam;
   
   //���·� 
   private String homeWinrate;
   private String awayWinrate;
   
   //���۽ð� 
   private String startTime;
   
   //��ġ�ڵ�
   private int matchCode;
	   
public int getMatchCode() {
	return matchCode;
}
public void setMatchCode(int matchCode) {
	this.matchCode = matchCode;
}

public String getStartTime() {
	return startTime;
}
public void setStartTime(String startTime) {
	this.startTime = startTime;
}
public String getAwayTeam() {
	return awayTeam;
}
public void setAwayTeam(String awayTeam) {
	this.awayTeam = awayTeam;
}
public String getHomeTeam() {
	return homeTeam;
}
public void setHomeTeam(String homeTeam) {
	this.homeTeam = homeTeam;
}
public String getHomeWinrate() {
	return homeWinrate;
}
public void setHomeWinrate(String homeWinrate) {
	this.homeWinrate = homeWinrate;
}
public String getAwayWinrate() {
	return awayWinrate;
}
public void setAwayWinrate(String awayWinrate) {
	this.awayWinrate = awayWinrate;
}
   
   
}
